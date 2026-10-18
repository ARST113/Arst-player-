package com.brouken.player;

import android.content.Context;
import android.preference.PreferenceManager;

import androidx.media3.common.C;
import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.exoplayer.audio.AudioOutput;
import androidx.media3.exoplayer.audio.AudioOutputProvider;
import androidx.media3.exoplayer.audio.ForwardingAudioOutput;
import androidx.media3.exoplayer.audio.ForwardingAudioOutputProvider;
import androidx.media3.exoplayer.audio.AudioTrackAudioOutputProvider;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * Experimental native Dolby output after the Media3 PCM processor pipeline.
 *
 * By installing at AudioOutputProvider, the EQ, loudness boost, night mode
 * and time stretching finish before the PCM is fed to FFmpeg. Direct/bitstreamed
 * tracks are passed through unchanged. Opt-in and never enabled by default.
 */
final class ArxDolbyProvider extends ForwardingAudioOutputProvider {
    private final AudioOutputProvider original;
    private final int mode; // 0 off, 1 automatic when route <=2 PCM, 2 always
    private final int bitrate;
    private final int routeChannels;
    private Format selectedPcm;
    private boolean selectedEac3;

    static int mode(Context c) {
        try {
            return Integer.parseInt(PreferenceManager.getDefaultSharedPreferences(c)
                    .getString("arxDolbyEncodeMode", "0"));
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    static int bitrate(Context c) {
        return "448000".equals(PreferenceManager.getDefaultSharedPreferences(c)
                .getString("arxDolbyBitrate", "640000")) ? 448000 : 640000;
    }

    ArxDolbyProvider(Context c, int routeChannels) {
        this(new AudioTrackAudioOutputProvider.Builder(c).build(),
                mode(c), bitrate(c), routeChannels);
    }

    private ArxDolbyProvider(AudioOutputProvider original, int mode, int bitrate, int routeChannels) {
        super(original);
        this.original = original;
        this.mode = mode;
        this.bitrate = bitrate;
        this.routeChannels = routeChannels;
    }

    private boolean isCandidate(Format pcm) {
        return mode > 0 && (mode == 2 || routeChannels <= 2) && pcm != null
                && MimeTypes.AUDIO_RAW.equals(pcm.sampleMimeType)
                && pcm.channelCount > 2 && pcm.channelCount <= 8
                && pcm.sampleRate == 48000
                && (pcm.pcmEncoding == C.ENCODING_PCM_16BIT
                    || pcm.pcmEncoding == C.ENCODING_PCM_FLOAT);
    }

    private Format encodedFormat(boolean eac3) {
        return new Format.Builder()
                .setSampleMimeType(eac3 ? MimeTypes.AUDIO_E_AC3 : MimeTypes.AUDIO_AC3)
                .setSampleRate(48000).setChannelCount(6).build();
    }

    private AudioOutputProvider.FormatConfig asEncoded(AudioOutputProvider.FormatConfig cfg,
                                                        boolean eac3) {
        // Keep original audio attributes, output session, device and virtual-device context.
        final AudioOutputProvider.FormatConfig.Builder builder =
                new AudioOutputProvider.FormatConfig.Builder(encodedFormat(eac3))
                        .setAudioAttributes(cfg.audioAttributes)
                        .setAudioSessionId(cfg.audioSessionId)
                        .setEnableOffload(false)
                        .setEnableTunneling(false)
                        .setEnablePlaybackParameters(false)
                        .setVirtualDeviceId(cfg.virtualDeviceId)
                        .setPreferredDevice(cfg.preferredDevice);
        return builder.build();
    }

    private int chosenEncoding(AudioOutputProvider.FormatConfig cfg) {
        if (!isCandidate(cfg.format)) return 0;
        // Prefer DD for optical and old ARC receivers; DD+ if DD is not supported.
        for (boolean plus : new boolean[]{false, true}) {
            if (!ArxAc3Encoder.available(plus)) continue;
            final AudioOutputProvider.FormatSupport supported =
                    original.getFormatSupport(asEncoded(cfg,plus));
            if (supported.supportLevel == AudioOutputProvider.FORMAT_SUPPORTED_DIRECTLY) {
                return plus ? C.ENCODING_E_AC3 : C.ENCODING_AC3;
            }
        }
        return 0;
    }

    @Override
    public AudioOutputProvider.FormatSupport getFormatSupport(
            AudioOutputProvider.FormatConfig config) {
        if (chosenEncoding(config) != 0) {
            return new AudioOutputProvider.FormatSupport.Builder()
                    .setFormatSupportLevel(AudioOutputProvider.FORMAT_SUPPORTED_WITH_TRANSCODING)
                    .build();
        }
        return super.getFormatSupport(config);
    }

    @Override
    public AudioOutputProvider.OutputConfig getOutputConfig(
            AudioOutputProvider.FormatConfig config)
            throws AudioOutputProvider.ConfigurationException {
        final int encoding = chosenEncoding(config);
        if (encoding == 0) {
            selectedPcm = null;
            return super.getOutputConfig(config);
        }
        selectedPcm = config.format;
        selectedEac3 = encoding == C.ENCODING_E_AC3;
        Utils.log("Dolby PCM encoder: " + selectedPcm.channelCount + "ch -> "
                + (selectedEac3 ? "DD+" : "DD") + " @ " + bitrate + " bps");
        return super.getOutputConfig(asEncoded(config, selectedEac3));
    }

    @Override
    public AudioOutput getAudioOutput(AudioOutputProvider.OutputConfig config)
            throws AudioOutputProvider.InitializationException {
        final AudioOutput raw = super.getAudioOutput(config);
        final Format pcm = selectedPcm;
        if (pcm == null || (config.encoding != C.ENCODING_AC3
                && config.encoding != C.ENCODING_E_AC3)) return raw;
        final ArxAc3Encoder encoder = ArxAc3Encoder.create(pcm.sampleRate,
                pcm.pcmEncoding == C.ENCODING_PCM_FLOAT, pcm.channelCount,
                bitrate, selectedEac3);
        if (encoder == null) {
            // Fail with a concrete signal instead of silently playing a stream of no audio.
            raw.release();
            throw new IllegalStateException("FFmpeg Dolby encoder could not be initialized");
        }
        return new EncodedOutput(raw, encoder, pcm);
    }

    private static final class EncodedOutput extends ForwardingAudioOutput {
        private final AudioOutput output;
        private final ArxAc3Encoder codec;
        private final int bytesPerFrame;
        private final ByteBuffer pcmBlock;
        private final ByteBuffer compressed = ByteBuffer.allocateDirect(4096);
        private final long delayUs;
        private long framesInput;
        private long lastPts;
        private boolean draining;
        private boolean released;

        EncodedOutput(AudioOutput sink, ArxAc3Encoder nativeEncoder, Format pcm) {
            super(sink);
            output = sink;
            codec = nativeEncoder;
            bytesPerFrame = pcm.channelCount * (pcm.pcmEncoding == C.ENCODING_PCM_FLOAT ? 4 : 2);
            pcmBlock = ByteBuffer.allocateDirect(bytesPerFrame * 1536).order(ByteOrder.nativeOrder());
            compressed.limit(0);
            delayUs = (long)codec.delay() * 1_000_000L / 48000L;
        }

        /** A previously encoded AC3 frame must drain before more source PCM is consumed. */
        private boolean flushPending() throws AudioOutput.WriteException {
            if (!compressed.hasRemaining()) return true;
            return output.write(compressed,1,lastPts);
        }

        private int pull(long pts) {
            compressed.clear();
            int bytes = codec.pull(compressed,1f);
            if (bytes < 0) {
                compressed.limit(0);
                Utils.log("Dolby encoder pull failed: " + bytes);
                return bytes;
            }
            compressed.position(0);
            compressed.limit(bytes);
            lastPts = pts;
            return bytes;
        }

        @Override
        public boolean write(ByteBuffer buffer,int encodedAccessUnits,long pts)
                throws AudioOutput.WriteException {
            if (released) return true;
            if (!flushPending()) return false;
            for (int attempts=0;attempts<256;attempts++) {
                int produced = pull(pts);
                if (produced < 0) throw new IllegalStateException("Dolby encoder pull failed");
                if (produced > 0) {
                    if (!flushPending()) return false;
                    continue;
                }
                if (buffer.remaining() < bytesPerFrame) return true;
                int frames = Math.min(1536,buffer.remaining()/bytesPerFrame);
                int length = frames*bytesPerFrame;
                ByteBuffer segment = buffer.duplicate().order(ByteOrder.nativeOrder());
                segment.limit(segment.position()+length);
                pcmBlock.clear();
                pcmBlock.put(segment);
                pcmBlock.flip();
                int pushed = codec.push(pcmBlock,frames);
                if (pushed < 0) throw new IllegalStateException("Dolby encoder push failed: " + pushed);
                buffer.position(buffer.position()+length);
                framesInput += frames;
            }
            return !buffer.hasRemaining();
        }

        @Override public long getPositionUs() {
            final long value = super.getPositionUs();
            return value < 0 ? value : Math.max(0,value-delayUs);
        }

        @Override public void stop() {
            if (!draining) {
                draining=true;
                codec.finish();
            }
            try {
                for(int i=0;i<32;i++){
                    if (!flushPending()) break;
                    if (pull(lastPts)<=0) break;
                }
            } catch (AudioOutput.WriteException err) {
                Utils.log("Dolby tail: " + err);
            }
            super.stop();
        }

        @Override public void flush() {
            super.flush();
            compressed.limit(0);
            // Output instance is re-created by DefaultAudioSink for a new format.
        }

        @Override public void release() {
            if (!released) {
                released=true;
                codec.release();
            }
            super.release();
        }
    }
}
