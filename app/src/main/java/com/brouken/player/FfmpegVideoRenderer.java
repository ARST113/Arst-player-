package com.brouken.player;

import android.os.Handler;
import android.view.Surface;
import androidx.annotation.Nullable;
import androidx.media3.common.C;
import androidx.media3.common.Format;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.decoder.CryptoConfig;
import androidx.media3.decoder.VideoDecoderOutputBuffer;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.media3.exoplayer.video.DecoderVideoRenderer;
import androidx.media3.exoplayer.video.VideoRendererEventListener;

/** Native HEVC and MPEG-4 software video renderer reconstructed from Just+ 2.2.2. */
@UnstableApi
final class FfmpegVideoRenderer extends DecoderVideoRenderer {
    @Nullable private FfmpegVideoDecoder decoder;

    FfmpegVideoRenderer(Handler handler, VideoRendererEventListener eventListener) {
        super(5000L, handler, eventListener, 50);
    }

    @Override
    public String getName() {
        return "FfmpegVideoRenderer";
    }

    @Override
    public int supportsFormat(Format format) {
        if (!FfmpegVideoDecoder.isAvailable()) {
            return RendererCapabilities.create(C.FORMAT_UNSUPPORTED_TYPE);
        }
        String mime = format.sampleMimeType;
        if (!"video/hevc".equals(mime) && !"video/mp4v-es".equals(mime)) {
            return RendererCapabilities.create(C.FORMAT_UNSUPPORTED_TYPE);
        }
        if (format.cryptoType != C.CRYPTO_TYPE_NONE) {
            return RendererCapabilities.create(C.FORMAT_UNSUPPORTED_DRM);
        }
        return RendererCapabilities.create(C.FORMAT_HANDLED);
    }

    @Override
    protected FfmpegVideoDecoder createDecoder(Format format, @Nullable CryptoConfig cryptoConfig)
            throws FfmpegVideoDecoder.FfmpegVideoException {
        decoder = new FfmpegVideoDecoder(format);
        return decoder;
    }

    @Override
    protected void renderOutputBufferToSurface(VideoDecoderOutputBuffer buffer, Surface surface)
            throws FfmpegVideoDecoder.FfmpegVideoException {
        int result = FfmpegVideoDecoder.nRender(surface, buffer.data,
                buffer.width, buffer.height, buffer.yStride);
        buffer.release();
        if (result != 0) {
            throw new FfmpegVideoDecoder.FfmpegVideoException("FFmpeg surface render failed: " + result);
        }
    }

    @Override
    protected void setDecoderOutputMode(@C.VideoOutputMode int mode) {
        if (decoder != null) {
            decoder.setOutputMode(mode);
        }
    }
}
