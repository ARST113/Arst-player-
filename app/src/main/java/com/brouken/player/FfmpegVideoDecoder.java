package com.brouken.player;

import android.view.Surface;
import androidx.annotation.Nullable;
import androidx.media3.common.Format;
import androidx.media3.decoder.DecoderException;
import androidx.media3.decoder.DecoderInputBuffer;
import androidx.media3.decoder.SimpleDecoder;
import androidx.media3.decoder.VideoDecoderOutputBuffer;
import java.nio.ByteBuffer;

/**
 * FFmpeg video JNI bridge, reconstructed from the official 2.2.2 APK.
 * Independent from the audio FFmpeg extension and from ARX NextLib fallback.
 */
final class FfmpegVideoDecoder extends SimpleDecoder<DecoderInputBuffer, VideoDecoderOutputBuffer, FfmpegVideoDecoder.FfmpegVideoException> {
    private static final boolean LIBRARY_AVAILABLE;
    private final long nativeContext;
    private final int[] frameSize = new int[2];
    private final String decoderName;
    private volatile int outputMode = -1;

    static {
        boolean loaded;
        try {
            System.loadLibrary("ffvideoJNI");
            loaded = true;
        } catch (UnsatisfiedLinkError e) {
            loaded = false;
        }
        LIBRARY_AVAILABLE = loaded;
    }

    static boolean isAvailable() {
        return LIBRARY_AVAILABLE;
    }

    FfmpegVideoDecoder(Format format) throws FfmpegVideoException {
        super(new DecoderInputBuffer[8], new VideoDecoderOutputBuffer[8]);
        boolean hevc = "video/hevc".equals(format.sampleMimeType);
        decoderName = hevc ? "ffmpeg-hevc" : "ffmpeg-mpeg4";
        byte[] codecData = format.initializationData.isEmpty() ? null : format.initializationData.get(0);
        nativeContext = nInit(hevc ? 1 : 0, codecData,
                hevc ? Math.min(8, Runtime.getRuntime().availableProcessors()) : 1);
        if (nativeContext == 0) {
            throw new FfmpegVideoException("Failed to open FFmpeg decoder: " + decoderName);
        }
        setInitialInputBufferSize(format.maxInputSize == Format.NO_VALUE ? 1048576 : format.maxInputSize);
    }

    void setOutputMode(int mode) {
        outputMode = mode;
    }

    @Override
    protected DecoderInputBuffer createInputBuffer() {
        return new DecoderInputBuffer(DecoderInputBuffer.BUFFER_REPLACEMENT_MODE_DIRECT);
    }

    @Override
    protected VideoDecoderOutputBuffer createOutputBuffer() {
        return new VideoDecoderOutputBuffer(this::releaseOutputBuffer);
    }

    @Override
    protected FfmpegVideoException createUnexpectedDecodeException(Throwable error) {
        return new FfmpegVideoException("Unexpected FFmpeg video decode error", error);
    }

    @Override
    @Nullable
    protected FfmpegVideoException decode(DecoderInputBuffer input, VideoDecoderOutputBuffer output,
            boolean reset) {
        if (reset) {
            nFlush(nativeContext);
        }
        ByteBuffer data = input.data;
        if (data == null) {
            return new FfmpegVideoException("Missing compressed video input buffer");
        }
        int result = nDecode(nativeContext, data, data.limit(), input.timeUs);
        if (result < 0) {
            return new FfmpegVideoException("FFmpeg decode error " + result + " (" + decoderName + ")");
        }
        if (result == 0) {
            output.shouldBeSkipped = true;
            return null;
        }
        output.init(nFrameInfo(nativeContext, frameSize), outputMode, null);
        if (outputMode != -1) {
            int rowStride = frameSize[0] * 4;
            if (!output.initForYuvFrame(frameSize[0], frameSize[1], rowStride, 0, 0)) {
                return new FfmpegVideoException("Picture too large: " + frameSize[0] + "x" + frameSize[1]);
            }
            nCopyFrame(nativeContext, output.data, rowStride);
        }
        return null;
    }

    @Override
    public String getName() {
        return decoderName;
    }

    @Override
    public void release() {
        super.release();
        nRelease(nativeContext);
    }

    static native int nRender(Surface surface, ByteBuffer data, int width, int height, int rowStride);
    private static native long nInit(int codec, byte[] config, int threads);
    private static native void nRelease(long context);
    private static native void nFlush(long context);
    private static native int nDecode(long context, ByteBuffer data, int limit, long timeUs);
    private static native long nFrameInfo(long context, int[] dimensions);
    private static native void nCopyFrame(long context, ByteBuffer output, int rowStride);

    static final class FfmpegVideoException extends DecoderException {
        FfmpegVideoException(String message) { super(message); }
        FfmpegVideoException(String message, Throwable cause) { super(message, cause); }
    }
}
