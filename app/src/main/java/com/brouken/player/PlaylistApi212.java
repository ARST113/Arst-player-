package com.brouken.player;

import android.app.PendingIntent;
import android.os.Bundle;
import android.os.Parcelable;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * Typed reader for the Just+ Player 2.1.2 nested playlist contract.
 *
 * The release source for 2.1.2 is not public, so this class mirrors the public PLAYLIST_API.md
 * contract and the parser behavior recovered from the official 2.1.2 APK. Keep launcher parsing
 * here instead of spreading Bundle coercion through PlayerActivity.
 */
final class PlaylistApi212 {

    static final class Playlist {
        final String title;
        final String logo;
        final String background;
        final String[] headers;
        final int startIndex;
        final TrackRequest audio;
        final TrackRequest subtitle;
        final List<Item> items;
        final PendingIntent resultCallback;
        final long reportIntervalMs;
        final String resumeMode;
        final List<String> warnings;
        final String error;

        Playlist(String title, String logo, String background, String[] headers, int startIndex,
                 TrackRequest audio, TrackRequest subtitle, List<Item> items,
                 PendingIntent resultCallback, long reportIntervalMs, String resumeMode,
                 List<String> warnings, String error) {
            this.title = title;
            this.logo = logo;
            this.background = background;
            this.headers = headers;
            this.startIndex = startIndex;
            this.audio = audio;
            this.subtitle = subtitle;
            this.items = items;
            this.resultCallback = resultCallback;
            this.reportIntervalMs = reportIntervalMs;
            this.resumeMode = resumeMode;
            this.warnings = warnings;
            this.error = error;
        }

        static Playlist error(String title, String message) {
            return new Playlist(title, null, null, null, -1, TrackRequest.EMPTY, TrackRequest.EMPTY,
                    Collections.emptyList(), null, 0L, null, Collections.emptyList(), message);
        }
    }

    static final class Item {
        final String uri;
        final String title;
        final String episodeTitle;
        final String logo;
        final String background;
        final String thumbnail;
        final String imdbId;
        final String tmdbId;
        final Integer season;
        final Integer episode;
        final String[] headers;
        final Long positionMs;
        final Long clipStartMs;
        final Long clipEndMs;
        final String segments;
        final TrackRequest audio;
        final TrackRequest subtitle;
        final List<Quality> qualities;
        final List<Voice> voices;
        final int selectedVoiceIndex;
        final List<Subtitle> subtitles;

        Item(String uri, String title, String episodeTitle, String logo, String background,
             String thumbnail, String imdbId, String tmdbId, Integer season, Integer episode,
             String[] headers, Long positionMs, Long clipStartMs, Long clipEndMs, String segments,
             TrackRequest audio, TrackRequest subtitle, List<Quality> qualities, List<Voice> voices,
             int selectedVoiceIndex, List<Subtitle> subtitles) {
            this.uri = uri;
            this.title = title;
            this.episodeTitle = episodeTitle;
            this.logo = logo;
            this.background = background;
            this.thumbnail = thumbnail;
            this.imdbId = imdbId;
            this.tmdbId = tmdbId;
            this.season = season;
            this.episode = episode;
            this.headers = headers;
            this.positionMs = positionMs;
            this.clipStartMs = clipStartMs;
            this.clipEndMs = clipEndMs;
            this.segments = segments;
            this.audio = audio;
            this.subtitle = subtitle;
            this.qualities = qualities;
            this.voices = voices;
            this.selectedVoiceIndex = selectedVoiceIndex;
            this.subtitles = subtitles;
        }

        boolean hasVoices() {
            return !voices.isEmpty();
        }

        Voice selectedVoice() {
            if (selectedVoiceIndex < 0 || selectedVoiceIndex >= voices.size()) {
                return null;
            }
            return voices.get(selectedVoiceIndex);
        }

        String selectedUri() {
            final Voice voice = selectedVoice();
            if (voice != null) {
                return voice.selectedUri();
            }
            if (uri != null) {
                for (Quality quality : qualities) {
                    if (quality.selected) {
                        return quality.uri;
                    }
                }
                return uri;
            }
            for (Quality quality : qualities) {
                if (quality.selected) {
                    return quality.uri;
                }
            }
            return qualities.isEmpty() ? null : qualities.get(0).uri;
        }

        List<Quality> activeQualities() {
            final Voice voice = selectedVoice();
            return voice == null ? qualities : voice.qualities;
        }

        List<Subtitle> activeSubtitles() {
            final Voice voice = selectedVoice();
            if (voice != null && !voice.subtitles.isEmpty()) {
                return voice.subtitles;
            }
            return subtitles;
        }

        String[] mergedHeaders(String[] playlistHeaders) {
            return mergeHeaders(playlistHeaders, headers);
        }
    }

    static final class Quality {
        final String label;
        final String uri;
        final boolean selected;

        Quality(String label, String uri, boolean selected) {
            this.label = label;
            this.uri = uri;
            this.selected = selected;
        }
    }

    static final class Voice {
        final String label;
        final String uri;
        final List<Quality> qualities;
        final boolean selected;
        final List<Subtitle> subtitles;
        final TrackRequest audio;
        final TrackRequest subtitle;
        final String[] headers;

        Voice(String label, String uri, List<Quality> qualities, boolean selected,
              List<Subtitle> subtitles, TrackRequest audio, TrackRequest subtitle, String[] headers) {
            this.label = label;
            this.uri = uri;
            this.qualities = qualities;
            this.selected = selected;
            this.subtitles = subtitles;
            this.audio = audio;
            this.subtitle = subtitle;
            this.headers = headers;
        }

        String selectedUri() {
            if (uri != null) {
                for (Quality quality : qualities) {
                    if (quality.selected) {
                        return quality.uri;
                    }
                }
                return uri;
            }
            for (Quality quality : qualities) {
                if (quality.selected) {
                    return quality.uri;
                }
            }
            return qualities.isEmpty() ? null : qualities.get(0).uri;
        }
    }

    static final class Subtitle {
        final String uri;
        final String language;
        final String label;
        final boolean selected;

        Subtitle(String uri, String language, String label, boolean selected) {
            this.uri = uri;
            this.language = language;
            this.label = label;
            this.selected = selected;
        }
    }

    static final class TrackRequest {
        static final TrackRequest EMPTY = new TrackRequest(null, null, null, null, null, false);

        final Integer index;
        final String label;
        final Integer languageOrdinal;
        final Integer languageCount;
        final List<String> languages;
        final boolean languagesSent;

        TrackRequest(Integer index, String label, Integer languageOrdinal, Integer languageCount,
                     List<String> languages, boolean languagesSent) {
            this.index = index;
            this.label = label;
            this.languageOrdinal = languageOrdinal;
            this.languageCount = languageCount;
            this.languages = languages == null ? Collections.emptyList() : languages;
            this.languagesSent = languagesSent;
        }

        boolean subtitleOff() {
            return (index != null && index == -1) || (languagesSent && languages.isEmpty());
        }

        boolean isEmpty() {
            return index == null && label == null && languageOrdinal == null && languageCount == null
                    && !languagesSent;
        }

        static TrackRequest overlay(TrackRequest playlist, TrackRequest item) {
            if (playlist == null) playlist = EMPTY;
            if (item == null) item = EMPTY;
            return new TrackRequest(
                    item.index != null ? item.index : playlist.index,
                    item.label != null ? item.label : playlist.label,
                    item.languageOrdinal != null ? item.languageOrdinal : playlist.languageOrdinal,
                    item.languageCount != null ? item.languageCount : playlist.languageCount,
                    item.languagesSent ? item.languages : playlist.languages,
                    item.languagesSent || playlist.languagesSent);
        }
    }

    private static final class Source {
        final String uri;
        final List<Quality> qualities;

        Source(String uri, List<Quality> qualities) {
            this.uri = uri;
            this.qualities = qualities;
        }

        boolean usable() {
            return uri != null || !qualities.isEmpty();
        }
    }

    private PlaylistApi212() {}

    static Playlist parse(Bundle playlist) {
        if (playlist == null) {
            return Playlist.error(null, "playlist has no items");
        }
        final String title = string(playlist, "title");
        final List<String> warnings = new ArrayList<>();
        final TrackRequest audio = track(playlist, "audio", "playlist", warnings);
        final TrackRequest subtitle = track(playlist, "subtitle", "playlist", warnings);
        final Parcelable[] rawItems = bundleArray(playlist, "items");
        if (rawItems == null || rawItems.length == 0) {
            return Playlist.error(title, "playlist has no items");
        }

        final Integer requested = integer(playlist, "start_index");
        final int startIndex = requested == null ? 0 : requested;
        if (startIndex < 0 || startIndex >= rawItems.length) {
            return Playlist.error(title, "start_index " + startIndex + " is out of range 0.."
                    + (rawItems.length - 1));
        }

        final String logo = string(playlist, "logo");
        final String background = string(playlist, "background");
        final String[] headers = headers(playlist);
        final List<Item> items = new ArrayList<>(rawItems.length);
        final boolean parentSubtitleIndexed = subtitle.index != null && subtitle.index >= 0;

        for (int i = 0; i < rawItems.length; i++) {
            if (!(rawItems[i] instanceof Bundle)) {
                return Playlist.error(title, "items[" + i + "] is not a Bundle");
            }
            final String path = "items[" + i + "]";
            try {
                items.add(item((Bundle) rawItems[i], logo, background, parentSubtitleIndexed,
                        path, warnings));
            } catch (IllegalArgumentException e) {
                return Playlist.error(title, path + " " + e.getMessage());
            }
        }

        final Object callback = playlist.get("result_callback");
        final PendingIntent resultCallback = callback instanceof PendingIntent
                ? (PendingIntent) callback : null;
        final Long interval = timeMs(playlist, "report_interval");
        final long reportIntervalMs = interval == null || interval <= 0
                ? 0L : Math.max(30_000L, interval);

        String resumeMode = string(playlist, "resume_mode");
        if (resumeMode != null && !"ask_open".equals(resumeMode) && !"ask_every".equals(resumeMode)
                && !"always".equals(resumeMode) && !"never".equals(resumeMode)) {
            warning(warnings, "playlist.resume_mode", "\"" + resumeMode
                    + "\" is not one of [ask_open, ask_every, always, never]");
            resumeMode = null;
        }

        return new Playlist(title, logo, background, headers, startIndex, audio, subtitle,
                Collections.unmodifiableList(items), resultCallback, reportIntervalMs, resumeMode,
                Collections.unmodifiableList(warnings), null);
    }

    private static Item item(Bundle bundle, String playlistLogo, String playlistBackground,
                             boolean parentSubtitleIndexed, String path, List<String> warnings) {
        final TrackRequest audio = track(bundle, "audio", path, warnings);
        final TrackRequest subtitle = track(bundle, "subtitle", path, warnings);
        final boolean subtitleIndexed = parentSubtitleIndexed
                || (subtitle.index != null && subtitle.index >= 0);

        final String direct = string(bundle, "uri");
        final List<Quality> qualities = qualities(bundle);
        final boolean hasNormalSource = direct != null || !qualities.isEmpty();

        final Parcelable[] rawVoices = bundleArray(bundle, "voices");
        final List<Voice> voices = new ArrayList<>();
        int selectedVoice = -1;
        if (rawVoices != null && rawVoices.length > 0) {
            if (hasNormalSource) {
                throw new IllegalArgumentException("has both voices and uri or qualities");
            }
            for (int i = 0; i < rawVoices.length; i++) {
                if (!(rawVoices[i] instanceof Bundle)) {
                    throw new IllegalArgumentException("voices[" + i + "] is not a Bundle");
                }
                final Bundle vb = (Bundle) rawVoices[i];
                final String voicePath = path + " voices[" + i + "]";
                final String label = textOnly(vb, "label");
                if (label == null) {
                    throw new IllegalArgumentException("voices[" + i + "] has no label");
                }
                final Source source = source(vb);
                if (!source.usable()) {
                    throw new IllegalArgumentException("voices[" + i + "] has neither uri nor qualities");
                }
                final TrackRequest voiceAudio = track(vb, "audio", voicePath, warnings);
                final TrackRequest voiceSubtitle = track(vb, "subtitle", voicePath, warnings);
                final boolean voiceSubtitleIndexed = subtitleIndexed
                        || (voiceSubtitle.index != null && voiceSubtitle.index >= 0);
                final List<Subtitle> voiceSubs = subtitles(vb, voiceSubtitleIndexed, voicePath, warnings);
                final boolean selected = bool(vb, "selected");
                if (selected && selectedVoice < 0) {
                    selectedVoice = i;
                }
                voices.add(new Voice(label, source.uri, source.qualities, selected, voiceSubs,
                        voiceAudio, voiceSubtitle, headers(vb)));
            }
            if (selectedVoice < 0) {
                selectedVoice = 0;
            }
        } else if (!hasNormalSource) {
            throw new IllegalArgumentException("has neither uri nor qualities");
        }

        final List<Subtitle> subtitles = subtitles(bundle, subtitleIndexed, path, warnings);
        Long clipStart = timeMs(bundle, "clip_start");
        Long clipEnd = timeMs(bundle, "clip_end");
        if (clipStart != null && clipStart < 0) clipStart = 0L;
        if (clipEnd != null && clipEnd < 0) clipEnd = null;
        if (clipStart != null && clipEnd != null && clipEnd <= clipStart) {
            clipEnd = null;
        }

        return new Item(
                direct,
                string(bundle, "title"),
                string(bundle, "episode_title"),
                first(string(bundle, "logo"), playlistLogo),
                first(string(bundle, "background"), playlistBackground),
                string(bundle, "thumbnail"),
                string(bundle, "imdb_id"),
                string(bundle, "tmdb_id"),
                integer(bundle, "season"),
                integer(bundle, "episode"),
                headers(bundle),
                nonNegative(timeMs(bundle, "position")),
                clipStart,
                clipEnd,
                string(bundle, "segments"),
                audio,
                subtitle,
                Collections.unmodifiableList(qualities),
                Collections.unmodifiableList(voices),
                selectedVoice,
                Collections.unmodifiableList(subtitles));
    }

    private static Source source(Bundle bundle) {
        return new Source(string(bundle, "uri"), Collections.unmodifiableList(qualities(bundle)));
    }

    private static List<Quality> qualities(Bundle bundle) {
        final Parcelable[] values = bundleArray(bundle, "qualities");
        if (values == null || values.length == 0) {
            return new ArrayList<>();
        }
        final List<Quality> result = new ArrayList<>();
        boolean selectedSeen = false;
        for (Parcelable value : values) {
            if (!(value instanceof Bundle)) {
                continue;
            }
            final Bundle q = (Bundle) value;
            final String label = textOnly(q, "label");
            final String uri = string(q, "uri");
            if (label == null || uri == null) {
                continue;
            }
            final boolean selected = !selectedSeen && bool(q, "selected");
            if (selected) selectedSeen = true;
            result.add(new Quality(label, uri, selected));
        }
        return result;
    }

    private static List<Subtitle> subtitles(Bundle bundle, boolean indexed, String path,
                                            List<String> warnings) {
        final Parcelable[] values = bundleArray(bundle, "subtitles");
        if (values == null || values.length == 0) {
            return new ArrayList<>();
        }
        final List<Subtitle> result = new ArrayList<>();
        boolean selectedSeen = false;
        for (int i = 0; i < values.length; i++) {
            if (!(values[i] instanceof Bundle)) {
                if (indexed) {
                    throw new IllegalArgumentException("subtitles[" + i
                            + "] is not a Bundle, and subtitle_index counts on it");
                }
                warning(warnings, path, "subtitles[" + i + "] is not a Bundle; skipped");
                continue;
            }
            final Bundle s = (Bundle) values[i];
            final String uri = string(s, "uri");
            if (uri == null) {
                if (indexed) {
                    throw new IllegalArgumentException("subtitles[" + i
                            + "] has no uri, and subtitle_index counts on it");
                }
                warning(warnings, path, "subtitles[" + i + "] has no uri; skipped");
                continue;
            }
            final String rawLanguage = textOnly(s, "language");
            final String language = rawLanguage == null ? null : Utils.toIso3Language(rawLanguage);
            final boolean selected = !selectedSeen && bool(s, "selected");
            if (selected) selectedSeen = true;
            result.add(new Subtitle(uri, language, textOnly(s, "label"), selected));
        }
        return result;
    }

    private static TrackRequest track(Bundle bundle, String prefix, String path, List<String> warnings) {
        final String p = path + "." + prefix;
        Integer index = wholeNumber(bundle, prefix + "_index", p + "_index", warnings);
        if (index != null) {
            if ("audio".equals(prefix) && index < 0) {
                warning(warnings, p + "_index", index + " is negative");
                index = null;
            } else if ("subtitle".equals(prefix) && index < -1) {
                warning(warnings, p + "_index", index + " is below -1");
                index = null;
            }
        }

        String label = null;
        if (bundle.containsKey(prefix + "_label")) {
            final Object value = bundle.get(prefix + "_label");
            if (value instanceof String) {
                label = trim((String) value);
            } else if (value != null) {
                warning(warnings, p + "_label", String.valueOf(value) + " is not text");
            }
        }

        Integer ordinal = wholeNumber(bundle, prefix + "_language_ordinal",
                p + "_language_ordinal", warnings);
        if (ordinal != null && ordinal < 0) {
            warning(warnings, p + "_language_ordinal", ordinal + " is negative");
            ordinal = null;
        }

        Integer count = wholeNumber(bundle, prefix + "_language_count",
                p + "_language_count", warnings);
        if (count != null && count < 1) {
            warning(warnings, p + "_language_count", count + " is below 1");
            count = null;
        }

        final String pluralKey = prefix + "_languages";
        final String singularKey = prefix + "_language";
        boolean languagesSent = bundle.containsKey(pluralKey);
        List<String> languages = Collections.emptyList();
        if (languagesSent) {
            final Object raw = bundle.get(pluralKey);
            final List<String> pieces = new ArrayList<>();
            if (raw instanceof String[]) {
                Collections.addAll(pieces, (String[]) raw);
            } else if (raw instanceof String) {
                final String text = ((String) raw).trim();
                if (!text.isEmpty()) {
                    Collections.addAll(pieces, text.split("[,\\s]+"));
                }
            } else if (raw != null) {
                warning(warnings, p + "_languages", raw.getClass().getSimpleName() + " is not text");
                languagesSent = false;
            }
            if (languagesSent) {
                languages = normalizeLanguages(pieces, p + "_languages", warnings);
            }
        } else if (bundle.containsKey(singularKey)) {
            final Object raw = bundle.get(singularKey);
            if (raw instanceof String) {
                final String v = trim((String) raw);
                if (v != null) {
                    final String normalized = Utils.toIso3Language(v);
                    if (normalized == null) {
                        warning(warnings, p + "_language", "\"" + v + "\" is not a language code");
                    } else {
                        languages = Collections.singletonList(normalized);
                        languagesSent = true;
                    }
                }
            } else if (raw != null) {
                warning(warnings, p + "_language", String.valueOf(raw) + " is not text");
            }
        }

        if (ordinal != null && !languagesSent) {
            warning(warnings, p + "_language_ordinal", "has no language on the same level");
            ordinal = null;
            count = null;
        }

        return new TrackRequest(index, label, ordinal, count,
                Collections.unmodifiableList(new ArrayList<>(languages)), languagesSent);
    }

    private static List<String> normalizeLanguages(List<String> raw, String path, List<String> warnings) {
        final Set<String> result = new LinkedHashSet<>();
        for (String value : raw) {
            final String trimmed = trim(value);
            if (trimmed == null) continue;
            final String language = Utils.toIso3Language(trimmed);
            if (language == null) {
                warning(warnings, path, "\"" + trimmed + "\" is not a language code");
                continue;
            }
            result.add(language);
        }
        return new ArrayList<>(result);
    }

    @Nullable
    static Parcelable[] bundleArray(Bundle bundle, String key) {
        if (bundle == null || !bundle.containsKey(key)) {
            return null;
        }
        final Object value = bundle.get(key);
        if (value instanceof Parcelable[]) {
            return (Parcelable[]) value;
        }
        if (value instanceof ArrayList) {
            final ArrayList<?> list = (ArrayList<?>) value;
            final Parcelable[] result = new Parcelable[list.size()];
            for (int i = 0; i < list.size(); i++) {
                final Object entry = list.get(i);
                if (!(entry instanceof Parcelable)) {
                    return null;
                }
                result[i] = (Parcelable) entry;
            }
            return result;
        }
        return null;
    }

    @Nullable
    static String string(Bundle bundle, String key) {
        if (bundle == null || !bundle.containsKey(key)) return null;
        final Object value = bundle.get(key);
        if (value == null) return null;
        return trim(String.valueOf(value));
    }

    @Nullable
    private static String textOnly(Bundle bundle, String key) {
        if (bundle == null || !bundle.containsKey(key)) return null;
        final Object value = bundle.get(key);
        return value instanceof String ? trim((String) value) : null;
    }

    @Nullable
    static Integer integer(Bundle bundle, String key) {
        if (bundle == null || !bundle.containsKey(key)) return null;
        final Double n = number(bundle.get(key));
        if (n == null || n < Integer.MIN_VALUE || n > Integer.MAX_VALUE) return null;
        return (int) Math.floor(n);
    }

    @Nullable
    private static Integer wholeNumber(Bundle bundle, String key, String path, List<String> warnings) {
        if (bundle == null || !bundle.containsKey(key)) return null;
        final Object raw = bundle.get(key);
        final Double n = number(raw);
        if (n == null || n < Integer.MIN_VALUE || n > Integer.MAX_VALUE || Math.rint(n) != n) {
            warning(warnings, path, "\"" + String.valueOf(raw) + "\" is not a whole number");
            return null;
        }
        return n.intValue();
    }

    @Nullable
    static Long timeMs(Bundle bundle, String baseKey) {
        if (bundle == null) return null;
        if (bundle.containsKey(baseKey + "_ms")) {
            final Double ms = number(bundle.get(baseKey + "_ms"));
            if (ms != null) return (long) Math.floor(ms);
        }
        if (bundle.containsKey(baseKey + "_sec")) {
            final Double sec = number(bundle.get(baseKey + "_sec"));
            if (sec != null) return (long) Math.floor(sec * 1000.0d);
        }
        return null;
    }

    private static boolean bool(Bundle bundle, String key) {
        if (bundle == null || !bundle.containsKey(key)) return false;
        final Object value = bundle.get(key);
        return value instanceof Boolean ? (Boolean) value : "true".equals(value);
    }

    @Nullable
    private static Double number(Object value) {
        if (value instanceof Number) {
            final double n = ((Number) value).doubleValue();
            return Double.isFinite(n) ? n : null;
        }
        if (value instanceof String) {
            try {
                final double n = Double.parseDouble(((String) value).trim());
                return Double.isFinite(n) ? n : null;
            } catch (NumberFormatException ignored) {
                return null;
            }
        }
        return null;
    }

    @Nullable
    private static String[] headers(Bundle bundle) {
        if (bundle == null || !bundle.containsKey("headers")) return null;
        final Object value = bundle.get("headers");
        return value instanceof String[] ? (String[]) value : null;
    }

    static String[] mergeHeaders(String[] parent, String[] child) {
        if ((parent == null || parent.length == 0) && (child == null || child.length == 0)) {
            return null;
        }
        final LinkedHashMap<String, String> merged = new LinkedHashMap<>();
        appendHeaders(merged, parent);
        appendHeaders(merged, child);
        final String[] out = new String[merged.size() * 2];
        int i = 0;
        for (java.util.Map.Entry<String, String> entry : merged.entrySet()) {
            out[i++] = entry.getKey();
            out[i++] = entry.getValue();
        }
        return out;
    }

    private static void appendHeaders(LinkedHashMap<String, String> out, String[] values) {
        if (values == null) return;
        for (int i = 0; i + 1 < values.length; i += 2) {
            final String name = trim(values[i]);
            final String value = values[i + 1];
            if (name == null || value == null) continue;
            String previousKey = null;
            for (String existing : out.keySet()) {
                if (existing.equalsIgnoreCase(name)) {
                    previousKey = existing;
                    break;
                }
            }
            if (previousKey != null) out.remove(previousKey);
            out.put(name, value);
        }
    }

    private static void warning(List<String> warnings, String path, String problem) {
        warnings.add(path + ": " + problem);
    }

    @Nullable
    private static Long nonNegative(Long value) {
        return value != null && value >= 0 ? value : null;
    }

    @Nullable
    private static String first(String a, String b) {
        return a != null ? a : b;
    }

    @Nullable
    private static String trim(String value) {
        if (value == null) return null;
        final String text = value.trim();
        return text.isEmpty() ? null : text;
    }
}
