package com.brouken.player.skip;

import androidx.media3.common.C;
import androidx.media3.common.Metadata;
import androidx.media3.common.Tracks;
import androidx.media3.extractor.metadata.Chapter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * Converts Media3 chapter metadata into the file-accurate intro/credits segments used by Just+ 2.1.2.
 */
public final class ChapterSegments {
    private static final Pattern INTRO = Pattern.compile(
            "(?<![\\p{L}\\p{N}])(?:opening|intro|op|заставка|вступление|вступ|опенинг)(?![\\p{L}\\p{N}])",
            Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
    private static final Pattern CREDITS = Pattern.compile(
            "(?<![\\p{L}\\p{N}])(?:credits|ending|outro|ed|титры|титри|эндинг|ендінг)(?![\\p{L}\\p{N}])",
            Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);

    private ChapterSegments() {
    }

    public static List<SkipSegment> fromTracks(Tracks tracks) {
        final List<SkipSegment> out = new ArrayList<>();
        final Set<Long> starts = new HashSet<>();
        if (tracks == null) {
            return out;
        }
        for (Tracks.Group group : tracks.getGroups()) {
            for (int i = 0; i < group.length; i++) {
                final Metadata metadata = group.getTrackFormat(i).metadata;
                if (metadata == null) {
                    continue;
                }
                for (int j = 0; j < metadata.length(); j++) {
                    final Metadata.Entry entry = metadata.get(j);
                    if (!(entry instanceof Chapter)) {
                        continue;
                    }
                    final Chapter chapter = (Chapter) entry;
                    if (chapter.isHidden() || chapter.getTitle() == null
                            || chapter.getTitle().value == null) {
                        continue;
                    }
                    final String title = chapter.getTitle().value;
                    final SkipSegment.Category category;
                    if (INTRO.matcher(title).find()) {
                        category = SkipSegment.Category.INTRO;
                    } else if (CREDITS.matcher(title).find()) {
                        category = SkipSegment.Category.CREDITS;
                    } else {
                        continue;
                    }
                    final long startMs = chapter.getStartTimeMs();
                    if (!starts.add(startMs)) {
                        continue;
                    }
                    final long endMs = chapter.getEndTimeMs();
                    out.add(new SkipSegment(startMs / 1000.0,
                            endMs == C.TIME_UNSET || endMs <= startMs ? 99999.0 : endMs / 1000.0,
                            SkipSegment.Type.SKIP, category, SkipSegment.CoordBase.CHAPTER,
                            SkipSegment.TIME_TRUST_CHAPTER));
                }
            }
        }
        return out;
    }
}
