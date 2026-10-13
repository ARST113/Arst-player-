package com.brouken.player;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.junit.Assert.*;

/** Pure-JVM regression tests for AVI header parsing, independent of a hardware decoder. */
public class AviMetadataReaderTest {
    private static void fourcc(ByteArrayOutputStream stream, String tag) throws IOException {
        stream.write(tag.getBytes(StandardCharsets.US_ASCII));
    }

    private static void littleEndian32(ByteArrayOutputStream stream, long value) {
        for (int i = 0; i < 4; i++) {
            stream.write((int) (value >>> (8 * i)) & 0xff);
        }
    }

    @Test
    public void extractsFrameRateFromRiffAviMainHeader() throws Exception {
        final ByteArrayOutputStream data = new ByteArrayOutputStream();
        fourcc(data, "RIFF");
        littleEndian32(data, 32);
        fourcc(data, "AVI ");
        fourcc(data, "LIST");
        littleEndian32(data, 20);
        fourcc(data, "hdrl");
        fourcc(data, "avih");
        littleEndian32(data, 8);
        littleEndian32(data, 41708); // 23.976 fps
        final List<TrackMetadata> tracks =
                AviMetadataReader.parse(new ByteArrayInputStream(data.toByteArray()));
        assertEquals(1, tracks.size());
        assertEquals(TrackMetadata.Type.VIDEO, tracks.get(0).type);
        assertEquals(23.976f, tracks.get(0).frameRate, 0.02f);
    }

    @Test
    public void rejectsIncompleteAndNonAviInput() {
        assertTrue(AviMetadataReader.parse(new ByteArrayInputStream(new byte[3])).isEmpty());
        assertTrue(AviMetadataReader.parse(
                new ByteArrayInputStream("not an AVI".getBytes(StandardCharsets.US_ASCII)))
                .isEmpty());
    }
}
