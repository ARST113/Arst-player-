package com.brouken.player;

import java.nio.ByteBuffer;

/**
 * JNI bridge reconstructed from the Just+ Player 2.4.4 Ac3Encoder class.
 * The existing, SHA-verified 2.4.1/2.4.4 libffmpegJNI.so exports these exact symbols.
 */
final class ArxAc3Encoder {
    private static Boolean ac3, eac3;
    private long handle;
    private final boolean plus;
    private final int bitrate;

    private ArxAc3Encoder(long handle, boolean plus, int bitrate) {
        this.handle = handle;
        this.plus = plus;
        this.bitrate = bitrate;
    }

    static synchronized boolean available(boolean plus) {
        final Boolean previous = plus ? eac3 : ac3;
        if (previous != null) return previous;
        boolean ok = false;
        try {
            System.loadLibrary("ffmpegJNI");
            ok = nAvailable(plus);
        } catch (LinkageError | RuntimeException error) {
            Utils.log("AC3 encoder unavailable: " + error.getClass().getSimpleName());
        }
        if (plus) eac3 = ok;
        else ac3 = ok;
        return ok;
    }

    /**
     * JNI: nOpen(sampleRate, floatPcm, channels, bitrate, eac3).
     * The first three inputs are the decoded PCM format; bitrate is bits/sec.
     */
    static ArxAc3Encoder create(int sampleRate, boolean floatPcm, int channels, int bitrate,
                                boolean plus) {
        if (!available(plus)) return null;
        try {
            long ptr = nOpen(sampleRate, floatPcm, channels, bitrate, plus);
            return ptr == 0L ? null : new ArxAc3Encoder(ptr, plus, bitrate);
        } catch (LinkageError | RuntimeException e) {
            Utils.log("AC3 open refused: " + e);
            return null;
        }
    }

    int delay() { return handle == 0 ? 0 : nDelay(handle); }
    int finish() { return handle == 0 ? -1 : nFinish(handle); }
    int pull(ByteBuffer output, float volume) {
        return handle == 0 ? -1 : nPull(handle, output, output.capacity(), volume);
    }
    int push(ByteBuffer input, int frames) {
        return handle == 0 ? -1 : nPush(handle, input, frames);
    }
    synchronized void release() {
        if (handle == 0) return;
        nRelease(handle);
        handle = 0;
    }

    private static native boolean nAvailable(boolean eac3);
    private static native long nOpen(int sampleRate, boolean floatPcm, int channels,
                                     int bitrate, boolean eac3);
    private static native int nDelay(long ctx);
    private static native int nFinish(long ctx);
    private static native int nPull(long ctx, ByteBuffer output, int capacity, float volume);
    private static native int nPush(long ctx, ByteBuffer input, int frameCount);
    private static native void nRelease(long ctx);
}
