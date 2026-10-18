package com.brouken.player;

import androidx.media3.common.C;
import androidx.media3.common.audio.BaseAudioProcessor;
import java.nio.ByteBuffer;
import java.util.Arrays;

/**
 * Ten independent constant-Q parametric bands. Processes decoded PCM only;
 * encoded AC3/DTS passthrough bypasses the Media3 AudioProcessor chain.
 *
 * RBJ peaking EQ, Q=1.2, coefficients normalised by a0. Multiple channels
 * have independent delays; immutable coefficient snapshots are switched
 * between input buffers to avoid half-updated filters on the playback thread.
 */
public final class ArxEqualizerProcessor extends BaseAudioProcessor {
    static final int[] HZ = {31, 62, 125, 250, 500, 1000, 2000, 4000, 8000, 16000};
    private static final double Q = 1.2;
    private volatile float[] decibels = new float[HZ.length];
    private volatile boolean enabled;
    private volatile double[][] coefficients;
    private double[][] z1, z2;
    private int channelCount;
    private boolean resetNeeded;

    public ArxEqualizerProcessor(boolean enabled, float[] bands) {
        setBands(enabled, bands);
    }

    public void setBands(boolean on, float[] bands) {
        if (bands == null || bands.length != HZ.length) throw new IllegalArgumentException("10 bands required");
        float[] next = Arrays.copyOf(bands, HZ.length);
        for (int i = 0; i < next.length; i++)
            next[i] = Math.max(-12f, Math.min(12f, next[i]));
        decibels = next;
        enabled = on;
        resetNeeded = true;
        if (inputAudioFormat.sampleRate > 0)
            coefficients = calculate(next, inputAudioFormat.sampleRate);
    }

    public boolean isEnabled() { return enabled; }

    static double[][] calculate(float[] gains, int sampleRate) {
        double[][] result = new double[HZ.length][5];
        for (int i = 0; i < HZ.length; i++) {
            double frequency = Math.min(HZ[i], sampleRate * .46);
            double omega = 2.0 * Math.PI * frequency / sampleRate;
            double alpha = Math.sin(omega) / (2.0 * Q);
            double A = Math.pow(10.0, gains[i] / 40.0);
            double a0 = 1 + alpha / A;
            result[i][0] = (1 + alpha * A) / a0;
            result[i][1] = (-2 * Math.cos(omega)) / a0;
            result[i][2] = (1 - alpha * A) / a0;
            result[i][3] = (-2 * Math.cos(omega)) / a0;
            result[i][4] = (1 - alpha / A) / a0;
        }
        return result;
    }

    @Override
    protected AudioFormat onConfigure(AudioFormat format) {
        if (format.encoding != C.ENCODING_PCM_16BIT && format.encoding != C.ENCODING_PCM_FLOAT)
            return AudioFormat.NOT_SET;
        channelCount = format.channelCount;
        coefficients = calculate(decibels, format.sampleRate);
        z1 = new double[HZ.length][channelCount];
        z2 = new double[HZ.length][channelCount];
        resetNeeded = false;
        return format;
    }

    @Override
    protected void onFlush() {
        clearDelay();
    }

    private void clearDelay() {
        if (z1 != null) for (double[] state : z1) Arrays.fill(state, 0);
        if (z2 != null) for (double[] state : z2) Arrays.fill(state, 0);
    }

    @Override
    public void queueInput(ByteBuffer input) {
        if (!input.hasRemaining()) return;
        ByteBuffer out = replaceOutputBuffer(input.remaining());
        if (!enabled || channelCount < 1 || coefficients == null) {
            out.put(input);
            out.flip();
            resetNeeded = true;
            return;
        }
        if (resetNeeded) {
            clearDelay();
            resetNeeded = false;
        }
        final double[][] c = coefficients;
        final boolean floatPcm = inputAudioFormat.encoding == C.ENCODING_PCM_FLOAT;
        int sample = 0;
        while (input.remaining() >= (floatPcm ? 4 : 2)) {
            final int channel = sample++ % channelCount;
            double x = floatPcm ? input.getFloat() : input.getShort() / 32768.0;
            for (int i = 0; i < c.length; i++) {
                // Transposed direct-form II: two samples of state per channel/band.
                double y = c[i][0] * x + z1[i][channel];
                z1[i][channel] = c[i][1] * x - c[i][3] * y + z2[i][channel];
                z2[i][channel] = c[i][2] * x - c[i][4] * y;
                x = y;
            }
            x = Math.max(-1, Math.min(1, x));
            if (floatPcm) out.putFloat((float) x);
            else out.putShort((short)Math.round(x * 32767));
        }
        while (input.hasRemaining()) out.put(input.get());
        out.flip();
    }

    @Override
    protected void onReset() {
        coefficients = null;
        z1 = null;
        z2 = null;
        channelCount = 0;
    }
}
