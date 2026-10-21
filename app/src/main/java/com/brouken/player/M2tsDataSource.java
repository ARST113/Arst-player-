package com.brouken.player;

import android.net.Uri;

import androidx.annotation.Nullable;
import androidx.media3.common.C;
import androidx.media3.datasource.DataSource;
import androidx.media3.datasource.DataSpec;
import androidx.media3.datasource.TransferListener;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Adapts Blu-ray/camcorder M2TS (192 bytes: 4-byte arrival timestamp followed
 * by a 188-byte MPEG transport packet) into ordinary 188-byte MPEG-TS.
 *
 * <p>Media3's TsExtractor expects the sync byte every 188 bytes and cannot
 * sniff an unmodified Blu-ray stream. This adapter exposes a virtual logical
 * 188-byte address space, including on non-zero seek and range requests. The
 * physical bytes below this adapter still flow through the existing SMB/HTTP,
 * credential and on-disk cache stack.
 *
 * <p>Only .m2ts/.mts files are converted. Traditional 188-byte .ts, HLS
 * segments, and manifests are left alone. This adapter does not add missing
 * elementary audio readers: HDMV TrueHD/AC-3 core extraction requires further
 * MPEG-TS extractor work.
 */
final class M2tsDataSource implements DataSource {
    private static final int PACKET_BYTES = 192;
    private static final int TS_BYTES = 188;
    private static final int PREFIX_BYTES = 4;
    private final DataSource upstream;
    private final byte[] physicalBuffer = new byte[PACKET_BYTES * 128];
    private int bufferedStart;
    private int bufferedEnd;
    private int packetBytesRemaining;
    private int headerBytesRemaining;
    private long logicalBytesRemaining;
    private boolean convert;

    static final class Factory implements DataSource.Factory {
        private final DataSource.Factory upstream;

        Factory(DataSource.Factory upstream) {
            this.upstream = upstream;
        }

        @Override
        public DataSource createDataSource() {
            return new M2tsDataSource(upstream.createDataSource());
        }
    }

    private M2tsDataSource(DataSource upstream) {
        this.upstream = upstream;
    }

    static boolean isPacketizedPath(@Nullable String path) {
        if (path == null) return false;
        final String lower = path.toLowerCase(Locale.ROOT);
        return lower.endsWith(".m2ts") || lower.endsWith(".mts");
    }

    /** Start of virtual data byte, after the four-byte Blu-ray packet prefix. */
    static long toPhysicalPosition(long logical) {
        return logical / TS_BYTES * PACKET_BYTES + PREFIX_BYTES + logical % TS_BYTES;
    }

    /** Number of complete payload bytes before a physical stream position. */
    static long toLogicalLength(long physicalEnd) {
        final long complete = physicalEnd / PACKET_BYTES * TS_BYTES;
        final long partial = Math.max(0L, Math.min(TS_BYTES,
                physicalEnd % PACKET_BYTES - PREFIX_BYTES));
        return complete + partial;
    }

    @Override
    public void addTransferListener(TransferListener transferListener) {
        upstream.addTransferListener(transferListener);
    }

    @Override
    public long open(DataSpec dataSpec) throws IOException {
        final Uri uri = dataSpec.uri;
        convert = isPacketizedPath(uri.getLastPathSegment());
        bufferedStart = 0;
        bufferedEnd = 0;
        packetBytesRemaining = TS_BYTES - (int) (dataSpec.position % TS_BYTES);
        headerBytesRemaining = 0;
        if (!convert) {
            logicalBytesRemaining = dataSpec.length;
            return upstream.open(dataSpec);
        }
        final long start = toPhysicalPosition(dataSpec.position);
        final long physicalLength;
        if (dataSpec.length == C.LENGTH_UNSET) {
            physicalLength = C.LENGTH_UNSET;
        } else {
            // Last byte of the requested logical range; crossing a packet
            // boundary includes the next packet's arrival-time prefix.
            final long last = dataSpec.position + dataSpec.length - 1L;
            physicalLength = toPhysicalPosition(last) + 1L - start;
        }
        final DataSpec physicalSpec = dataSpec.buildUpon()
                .setPosition(start).setLength(physicalLength).build();
        final long available = upstream.open(physicalSpec);
        if (dataSpec.length != C.LENGTH_UNSET) {
            logicalBytesRemaining = dataSpec.length;
        } else if (available != C.LENGTH_UNSET) {
            logicalBytesRemaining = Math.max(0L,
                    toLogicalLength(start + available) - dataSpec.position);
        } else {
            logicalBytesRemaining = C.LENGTH_UNSET;
        }
        return logicalBytesRemaining;
    }

    private boolean buffered() throws IOException {
        if (bufferedStart < bufferedEnd) return true;
        bufferedStart = 0;
        bufferedEnd = upstream.read(physicalBuffer, 0, physicalBuffer.length);
        return bufferedEnd > 0;
    }

    @Override
    public int read(byte[] target, int offset, int length) throws IOException {
        if (length == 0) return 0;
        if (!convert) return upstream.read(target, offset, length);
        if (logicalBytesRemaining == 0) return C.RESULT_END_OF_INPUT;
        int copied = 0;
        while (copied < length) {
            if (logicalBytesRemaining == 0) break;
            if (packetBytesRemaining == 0 && headerBytesRemaining == 0) {
                headerBytesRemaining = PREFIX_BYTES;
                packetBytesRemaining = TS_BYTES;
            }
            if (!buffered()) break;
            final int available = bufferedEnd - bufferedStart;
            if (headerBytesRemaining != 0) {
                final int skip = Math.min(headerBytesRemaining, available);
                bufferedStart += skip;
                headerBytesRemaining -= skip;
                continue;
            }
            int count = Math.min(length - copied, Math.min(available, packetBytesRemaining));
            if (logicalBytesRemaining != C.LENGTH_UNSET)
                count = (int) Math.min(count, logicalBytesRemaining);
            System.arraycopy(physicalBuffer, bufferedStart, target, offset + copied, count);
            bufferedStart += count;
            packetBytesRemaining -= count;
            copied += count;
            if (logicalBytesRemaining != C.LENGTH_UNSET) logicalBytesRemaining -= count;
        }
        return copied == 0 ? C.RESULT_END_OF_INPUT : copied;
    }

    @Nullable
    @Override
    public Uri getUri() {
        return upstream.getUri();
    }

    @Override
    public Map<String, List<String>> getResponseHeaders() {
        return upstream.getResponseHeaders();
    }

    @Override
    public void close() throws IOException {
        convert = false;
        bufferedStart = 0;
        bufferedEnd = 0;
        logicalBytesRemaining = 0;
        upstream.close();
    }
}
