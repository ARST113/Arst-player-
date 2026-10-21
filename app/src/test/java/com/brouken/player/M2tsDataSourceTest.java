package com.brouken.player;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class M2tsDataSourceTest {
    @Test
    public void virtualPositionsAlwaysSkipTheFourByteArrivalTime() {
        assertEquals(4L, M2tsDataSource.toPhysicalPosition(0L));
        assertEquals(191L, M2tsDataSource.toPhysicalPosition(187L));
        assertEquals(196L, M2tsDataSource.toPhysicalPosition(188L));
        assertEquals(197L, M2tsDataSource.toPhysicalPosition(189L));
        assertEquals(388L, M2tsDataSource.toPhysicalPosition(376L));
        assertEquals(772L, M2tsDataSource.toPhysicalPosition(752L));
    }

    @Test
    public void conversionHandlesIncompletePacketPrefixes() {
        assertEquals(0L, M2tsDataSource.toLogicalLength(0L));
        assertEquals(0L, M2tsDataSource.toLogicalLength(4L));
        assertEquals(1L, M2tsDataSource.toLogicalLength(5L));
        assertEquals(188L, M2tsDataSource.toLogicalLength(192L));
        assertEquals(188L, M2tsDataSource.toLogicalLength(196L));
        assertEquals(189L, M2tsDataSource.toLogicalLength(197L));
        assertEquals(564L, M2tsDataSource.toLogicalLength(576L));
    }

    @Test
    public void regularTsAndAdaptiveManifestsRemainUntouched() {
        assertTrue(M2tsDataSource.isPacketizedPath("movie.m2ts"));
        assertTrue(M2tsDataSource.isPacketizedPath("camera.MTS"));
        assertFalse(M2tsDataSource.isPacketizedPath("movie.ts"));
        assertFalse(M2tsDataSource.isPacketizedPath("segment.ts"));
        assertFalse(M2tsDataSource.isPacketizedPath("film.mkv"));
        assertFalse(M2tsDataSource.isPacketizedPath("playlist.m3u8"));
        assertFalse(M2tsDataSource.isPacketizedPath(null));
    }
}
