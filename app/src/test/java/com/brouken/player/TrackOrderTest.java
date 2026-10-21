package com.brouken.player;

import androidx.media3.common.C;
import androidx.media3.common.Format;
import org.junit.Test;
import java.util.Arrays;
import java.util.Collections;
import static org.junit.Assert.*;

public class TrackOrderTest {
    private static Format emptyLanguage() {
        return new Format.Builder().setLanguage("und").build();
    }

    @Test public void recognizesForcedTitlesWithoutFalsePositive() {
        final Format f = emptyLanguage();
        assertTrue(TrackOrder.forced(f, "Forced"));
        assertTrue(TrackOrder.forced(f, "English signs"));
        assertTrue(TrackOrder.forced(f, "Русские надписи"));
        assertFalse(TrackOrder.forced(f, "Unforced subtitles"));
        assertFalse(TrackOrder.forced(f, "Russian Full"));
    }

    @Test public void honorsContainerForcedFlag() {
        final Format f = new Format.Builder().setSelectionFlags(C.SELECTION_FLAG_FORCED).build();
        assertTrue(TrackOrder.forced(f, "No name"));
    }

    @Test public void infersKnownStudioLanguageWithoutTag() {
        assertEquals("ukr", TrackOrder.language(emptyLanguage(), "Дубляж [Postmodern]"));
        assertEquals("rus", TrackOrder.language(emptyLanguage(), "LostFilm"));
        assertEquals("rus", TrackOrder.language(emptyLanguage(), "Russian subs"));
        assertNull(TrackOrder.language(emptyLanguage(), "Track 2"));
    }

    @Test public void ordersPreferredAudioBeforeUnpreferred() {
        assertTrue(TrackOrder.compareAudio("rus", "Дубляж", "eng", "English",
                Arrays.asList("rus", "eng")) < 0);
        assertTrue(TrackOrder.compareAudio("eng", "Original", "rus", "Дубляж",
                Collections.singletonList("rus")) < 0);
    }

    @Test public void ordersForcedWithinLanguageBeforeFull() {
        final Format f = emptyLanguage();
        final Format forced = new Format.Builder().setSelectionFlags(C.SELECTION_FLAG_FORCED).build();
        assertTrue(TrackOrder.compareSubtitle(forced, "English", f, "English",
                Collections.singletonList("eng")) < 0);
    }

    @Test public void sortsUnpreferredSubtitleTracksByDisplayName() {
        final Format french = new Format.Builder().setLanguage("fra").build();
        final Format japanese = new Format.Builder().setLanguage("jpn").build();
        assertTrue(TrackOrder.compareSubtitle(japanese, "Alpha Japanese", french,
                "Zulu French", Collections.singletonList("rus")) < 0);
        assertTrue(TrackOrder.compareSubtitle(french, "Zulu French", japanese,
                "Alpha Japanese", Collections.singletonList("rus")) > 0);
    }

    @Test public void preferredSubtitlesRemainAboveAlphabetizedExtras() {
        final Format russian = new Format.Builder().setLanguage("rus").build();
        final Format french = new Format.Builder().setLanguage("fra").build();
        assertTrue(TrackOrder.compareSubtitle(russian, "Zulu Russian", french,
                "Alpha French", Collections.singletonList("rus")) < 0);
    }
}
