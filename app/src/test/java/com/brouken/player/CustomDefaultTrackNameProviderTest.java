package com.brouken.player;

import org.junit.Test;
import java.util.Locale;
import static org.junit.Assert.assertEquals;

public class CustomDefaultTrackNameProviderTest {
    @Test
    public void iso3LabelsResolveToHumanLanguageNames() {
        assertEquals("English", CustomDefaultTrackNameProvider.displayLanguageCode("eng", Locale.ENGLISH));
        assertEquals("German", CustomDefaultTrackNameProvider.displayLanguageCode("deu", Locale.ENGLISH));
        assertEquals("Russian", CustomDefaultTrackNameProvider.displayLanguageCode("rus", Locale.ENGLISH));
    }

    @Test
    public void oldBibliographicIso639LabelsWork() {
        assertEquals("German", CustomDefaultTrackNameProvider.displayLanguageCode("ger", Locale.ENGLISH));
        assertEquals("French", CustomDefaultTrackNameProvider.displayLanguageCode("fre", Locale.ENGLISH));
    }

    @Test
    public void arbitraryStudioAndDescriptionAreNotRewritten() {
        assertEquals("Studio ENG", CustomDefaultTrackNameProvider.displayLanguageCode("Studio ENG", Locale.ENGLISH));
        assertEquals("XZY", CustomDefaultTrackNameProvider.displayLanguageCode("XZY", Locale.ENGLISH));
        assertEquals("English subtitles", CustomDefaultTrackNameProvider.displayLanguageCode("English subtitles", Locale.ENGLISH));
        assertEquals(null, CustomDefaultTrackNameProvider.displayLanguageCode(null, Locale.ENGLISH));
    }
}
