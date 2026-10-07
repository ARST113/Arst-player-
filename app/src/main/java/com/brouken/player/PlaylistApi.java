package com.brouken.player;

import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.Set;

/**
 * Parser/model for the public Just+ Player 2.1.2 playlist contract.
 *
 * This deliberately mirrors the contract implemented by the official 2.1.2 APK:
 * lenient scalar coercion for ordinary fields, strict-but-nonfatal track selectors,
 * fatal structural errors for malformed items/voices, and warning collection for
 * dropped selector/subtitle values.
 */
final class PlaylistApi {

    static final long TIME_UNSET = Long.MIN_VALUE + 1;

    private static final List<String> RESUME_MODES =
            Collections.unmodifiableList(Arrays.asList("ask_open", "ask_every", "always", "never"));

    static final class Parsed {
        final Playlist playlist;
        final String error;

        Parsed(@Nullable Playlist playlist, @Nullable String error) {
            this.playlist = playlist;
            this.error = error;
        }

        boolean ok() {
            return playlist != null && error == null;
        }
    }

    static final class Playlist {
        final String title;
        final Uri logo;
        final Uri background;
        final int startIndex;
        final String[] headers;
        final Parcelable resultCallback;
        final long reportIntervalMs;
        final String resumeMode;
        final TrackRequest audio;
        final TrackRequest subtitle;
        final List<Item> items;
        final List<String> warnings;

        Playlist(String title, Uri logo, Uri background, int startIndex, String[] headers,
                 Parcelable resultCallback, long reportIntervalMs, String resumeMode,
                 TrackRequest audio, TrackRequest subtitle, List<Item> items, List<String> warnings) {
            this.title = title;
            this.logo = logo;
            this.background = background;
            this.startIndex = startIndex;
            this.headers = headers;
            this.resultCallback = resultCallback;
            this.reportIntervalMs = reportIntervalMs;
            this.resumeMode = resumeMode;
            this.audio = audio;
            this.subtitle = subtitle;
            this.items = items;
            this.warnings = warnings;
        }
    }

    static final class Item {
        Uri uri;
        final String title;
        final String episodeTitle;
        final Uri thumbnail;
        final Uri logo;
        final Uri background;
        final String imdbId;
        final String tmdbId;
        final int season;
        final int episode;
        final String[] headers;
        final long positionMs;
        final long clipStartMs;
        final long clipEndMs;
        final String segments;
        final TrackRequest audio;
        final TrackRequest subtitle;
        final List<Quality> qualities;
        final List<Voice> voices;
        final List<ExternalSubtitle> subtitles;
        int selectedVoice;

        Item(Uri uri, String title, String episodeTitle, Uri thumbnail, Uri logo, Uri background,
             String imdbId, String tmdbId, int season, int episode, String[] headers,
             long positionMs, long clipStartMs, long clipEndMs, String segments,
             TrackRequest audio, TrackRequest subtitle, List<Quality> qualities,
             List<Voice> voices, List<ExternalSubtitle> subtitles, int selectedVoice) {
            this.uri = uri;
            this.title = title;
            this.episodeTitle = episodeTitle;
            this.thumbnail = thumbnail;
            this.logo = logo;
            this.background = background;
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
            this.subtitles = subtitles;
            this.selectedVoice = selectedVoice;
        }

        @Nullable
        Voice currentVoice() {
            return selectedVoice >= 0 && selectedVoice < voices.size() ? voices.get(selectedVoice) : null;
        }

        List<Quality> activeQualities() {
            final Voice voice = currentVoice();
            return voice != null ? voice.qualities : qualities;
        }

        List<ExternalSubtitle> activeSubtitles() {
            final Voice voice = currentVoice();
            return voice != null && voice.subtitles != null ? voice.subtitles : subtitles;
        }

        @Nullable
        String voiceLabel() {
            final Voice voice = currentVoice();
            return voice == null ? null : voice.label;
        }
    }

    static final class Quality {
        final String label;
        final Uri uri;
        final boolean selected;

        Quality(String label, Uri uri, boolean selected) {
            this.label = label;
            this.uri = uri;
            this.selected = selected;
        }
    }

    static final class Voice {
        final String label;
        final Uri uri;
        final List<Quality> qualities;
        final List<ExternalSubtitle> subtitles;
        final boolean selected;

        Voice(String label, Uri uri, List<Quality> qualities,
              @Nullable List<ExternalSubtitle> subtitles, boolean selected) {
            this.label = label;
            this.uri = uri;
            this.qualities = qualities;
            this.subtitles = subtitles;
            this.selected = selected;
        }
    }

    static final class ExternalSubtitle {
        final Uri uri;
        final String mime;
        final String language;
        final String label;
        final boolean selected;

        ExternalSubtitle(Uri uri, String mime, String language, String label, boolean selected) {
            this.uri = uri;
            this.mime = mime;
            this.language = language;
            this.label = label;
            this.selected = selected;
        }
    }

    static final class TrackRequest {
        final Integer index;
        final String label;
        final Integer ordinal;
        final String[] languages;
        final Integer count;

        TrackRequest(Integer index, String label, Integer ordinal, String[] languages, Integer count) {
            this.index = index;
            this.label = emptyToNull(label);
            this.ordinal = ordinal;
            this.languages = languages;
            this.count = count;
        }

        boolean off() {
            return (index != null && index == -1)
                    || (languages != null && languages.length == 0);
        }

        boolean hasAny() {
            return index != null || label != null || ordinal != null || languages != null || count != null;
        }
    }

    private static final class Fatal extends Exception {
        Fatal(String message) {
            super(message);
        }
    }

    private PlaylistApi() {
    }

    static Parsed parse(Bundle root) {
        if (root == null) {
            return new Parsed(null, "playlist has no items");
        }

        final ArrayList<String> warnings = new ArrayList<>();
        final Parcelable[] rawItems = parcelables(root, "items");
        if (rawItems == null || rawItems.length == 0) {
            return new Parsed(null, "playlist has no items");
        }

        final Integer requestedStart = integer(root, "start_index");
        final int start = requestedStart == null ? 0 : requestedStart;
        if (start < 0 || start >= rawItems.length) {
            return new Parsed(null, "start_index " + start + " is out of range 0.." + (rawItems.length - 1));
        }

        final String title = text(root, "title");
        final Uri logo = uri(text(root, "logo"));
        final Uri background = uri(text(root, "background"));
        final String[] headers = headers(root);

        final TrackRequest playlistAudio = trackRequest(root, "audio", "playlist", warnings);
        final TrackRequest playlistSubtitle = trackRequest(root, "subtitle", "playlist", warnings);

        final Double interval = timeMs(root, "report_interval");
        final long reportIntervalMs = interval == null || interval <= 0
                ? 0L : Math.max(30_000L, (long) Math.floor(interval));

        String resumeMode = text(root, "resume_mode");
        if (resumeMode != null && !RESUME_MODES.contains(resumeMode)) {
            warn(warnings, "playlist.resume_mode",
                    "\"" + resumeMode + "\" is not one of " + RESUME_MODES);
            resumeMode = null;
        }

        final ArrayList<Item> items = new ArrayList<>(rawItems.length);
        try {
            for (int i = 0; i < rawItems.length; i++) {
                if (!(rawItems[i] instanceof Bundle)) {
                    throw new Fatal("items[" + i + "] is not a Bundle");
                }
                items.add(parseItem((Bundle) rawItems[i], i, logo, background,
                        playlistSubtitle, warnings));
            }
        } catch (Fatal fatal) {
            return new Parsed(null, fatal.getMessage());
        }

        final Object callback = root.get("result_callback");
        final Parcelable resultCallback = callback instanceof Parcelable ? (Parcelable) callback : null;

        return new Parsed(new Playlist(
                title, logo, background, start, headers, resultCallback,
                reportIntervalMs, resumeMode, playlistAudio, playlistSubtitle,
                Collections.unmodifiableList(items),
                Collections.unmodifiableList(warnings)), null);
    }

    private static Item parseItem(Bundle bundle, int index, Uri playlistLogo, Uri playlistBackground,
                                  TrackRequest playlistSubtitle, ArrayList<String> warnings)
            throws Fatal {
        final String path = "items[" + index + "]";
        final TrackRequest subtitleRequest = trackRequest(bundle, "subtitle", path, warnings);
        final TrackRequest audioRequest = trackRequest(bundle, "audio", path, warnings);

        final boolean subtitleIsNumbered =
                (playlistSubtitle != null && playlistSubtitle.index != null && playlistSubtitle.index >= 0)
                        || (subtitleRequest.index != null && subtitleRequest.index >= 0);

        final List<ExternalSubtitle> itemSubs =
                parseSubtitles(bundle, path, subtitleIsNumbered, warnings);

        final ArrayList<Quality> qualities = parseQualities(bundle);
        final Uri direct = uri(text(bundle, "uri"));
        final Uri itemStream = chooseStream(direct, qualities);

        final Parcelable[] rawVoices = parcelables(bundle, "voices");
        final ArrayList<Voice> voices = new ArrayList<>();
        int selectedVoice = -1;

        if (rawVoices != null && rawVoices.length > 0) {
            if (itemStream != null) {
                throw new Fatal(path + " has both voices and uri or qualities");
            }
            for (int v = 0; v < rawVoices.length; v++) {
                if (!(rawVoices[v] instanceof Bundle)) {
                    throw new Fatal(path + " voices[" + v + "] is not a Bundle");
                }
                final Bundle voiceBundle = (Bundle) rawVoices[v];
                final String label = text(voiceBundle, "label");
                if (label == null) {
                    throw new Fatal(path + " voices[" + v + "] has no label");
                }
                final ArrayList<Quality> voiceQualities = parseQualities(voiceBundle);
                final Uri voiceDirect = uri(text(voiceBundle, "uri"));
                final Uri voiceUri = chooseStream(voiceDirect, voiceQualities);
                if (voiceUri == null) {
                    throw new Fatal(path + " voices[" + v + "] has neither uri nor qualities");
                }

                final List<ExternalSubtitle> voiceSubs;
                if (parcelables(voiceBundle, "subtitles") == null) {
                    voiceSubs = null;
                } else {
                    voiceSubs = parseSubtitles(voiceBundle,
                            path + " voices[" + v + "]", subtitleIsNumbered, warnings);
                }
                final boolean selected = selected(voiceBundle);
                voices.add(new Voice(label, voiceUri,
                        Collections.unmodifiableList(voiceQualities), voiceSubs, selected));
                if (selectedVoice < 0 && selected) {
                    selectedVoice = v;
                }
            }
            if (selectedVoice < 0) {
                selectedVoice = 0;
            }
        } else if (itemStream == null) {
            throw new Fatal(path + " has neither uri nor qualities");
        }

        final Uri selectedUri = voices.isEmpty() ? itemStream : voices.get(selectedVoice).uri;

        final Double start = timeMs(bundle, "clip_start");
        final Double end = timeMs(bundle, "clip_end");
        final long clipStartMs = start == null || start <= 0 ? 0L : Math.round(start);
        final long clipEndMs = end == null || end <= clipStartMs ? Long.MIN_VALUE : Math.round(end);

        final Double position = timeMs(bundle, "position");
        long positionMs = position == null ? TIME_UNSET : Math.max(0L, (long) Math.floor(position));
        if (positionMs != TIME_UNSET && clipEndMs != Long.MIN_VALUE) {
            positionMs = Math.min(positionMs, clipEndMs - clipStartMs);
        }

        final Integer seasonValue = integer(bundle, "season");
        final Integer episodeValue = integer(bundle, "episode");

        final Uri itemLogo = uri(text(bundle, "logo"));
        final Uri itemBackground = uri(text(bundle, "background"));

        return new Item(
                selectedUri,
                text(bundle, "title"),
                text(bundle, "episode_title"),
                uri(text(bundle, "thumbnail")),
                itemLogo != null ? itemLogo : playlistLogo,
                itemBackground != null ? itemBackground : playlistBackground,
                text(bundle, "imdb_id"),
                text(bundle, "tmdb_id"),
                seasonValue == null ? -1 : seasonValue,
                episodeValue == null ? -1 : episodeValue,
                headers(bundle),
                positionMs,
                clipStartMs,
                clipEndMs,
                text(bundle, "segments"),
                audioRequest,
                subtitleRequest,
                Collections.unmodifiableList(qualities),
                Collections.unmodifiableList(voices),
                itemSubs,
                selectedVoice);
    }

    private static ArrayList<Quality> parseQualities(Bundle bundle) {
        final ArrayList<Quality> out = new ArrayList<>();
        final Parcelable[] raw = parcelables(bundle, "qualities");
        if (raw == null) {
            return out;
        }
        for (Parcelable value : raw) {
            if (!(value instanceof Bundle)) {
                continue;
            }
            final Bundle quality = (Bundle) value;
            final String label = text(quality, "label");
            final Uri uri = uri(text(quality, "uri"));
            if (label != null && uri != null) {
                out.add(new Quality(label, uri, selected(quality)));
            }
        }
        return out;
    }

    private static List<ExternalSubtitle> parseSubtitles(Bundle bundle, String path,
                                                         boolean indexCountsEntries,
                                                         ArrayList<String> warnings)
            throws Fatal {
        final ArrayList<ExternalSubtitle> out = new ArrayList<>();
        final Parcelable[] raw = parcelables(bundle, "subtitles");
        if (raw == null) {
            return Collections.emptyList();
        }

        for (int i = 0; i < raw.length; i++) {
            final Bundle sub = raw[i] instanceof Bundle ? (Bundle) raw[i] : null;
            final Uri uri = sub == null ? null : uri(text(sub, "uri"));
            if (uri == null) {
                final String reason = "subtitles[" + i + "] "
                        + (sub == null ? "is not a Bundle" : "has no uri");
                if (indexCountsEntries) {
                    throw new Fatal(path + " " + reason + ", and subtitle_index counts on it");
                }
                warn(warnings, path, reason + "; skipped");
                continue;
            }
            out.add(new ExternalSubtitle(
                    uri,
                    text(sub, "mime"),
                    text(sub, "language"),
                    text(sub, "label"),
                    selected(sub)));
        }
        return Collections.unmodifiableList(out);
    }

    @Nullable
    private static Uri chooseStream(@Nullable Uri direct, List<Quality> qualities) {
        for (Quality quality : qualities) {
            if (quality.selected) {
                return quality.uri;
            }
        }
        if (direct != null) {
            return direct;
        }
        return qualities.isEmpty() ? null : qualities.get(0).uri;
    }

    private static TrackRequest trackRequest(Bundle bundle, String kind, String path,
                                             ArrayList<String> warnings) {
        final boolean subtitle = "subtitle".equals(kind);
        final String prefix = path + "." + kind;

        Integer index = wholeNumber(bundle.get(kind + "_index"), prefix + "_index", warnings);
        if (index != null && index < (subtitle ? -1 : 0)) {
            warn(warnings, prefix + "_index", index
                    + (subtitle ? " is below -1 (off)" : " is negative; audio cannot be off"));
            index = null;
        }

        final String label = strictText(bundle.get(kind + "_label"), prefix + "_label", warnings);

        String[] languages = parseLanguages(bundle.get(kind + "_languages"),
                prefix + "_languages", warnings);

        final String singleLanguage = strictText(bundle.get(kind + "_language"),
                prefix + "_language", warnings);
        if (singleLanguage != null) {
            final String normalized = normalizeLanguage(singleLanguage);
            if (normalized == null) {
                warn(warnings, prefix + "_language",
                        "\"" + singleLanguage + "\" is not a language code");
            } else if (languages != null) {
                warn(warnings, prefix + "_language",
                        "ignored: " + kind + "_languages is given");
            } else {
                languages = new String[]{normalized};
            }
        }

        if (!subtitle && languages != null && languages.length == 0) {
            languages = null;
        }

        Integer ordinal = wholeNumber(bundle.get(kind + "_language_ordinal"),
                prefix + "_language_ordinal", warnings);
        if (ordinal != null && ordinal < 0) {
            warn(warnings, prefix + "_language_ordinal", ordinal + " is negative");
            ordinal = null;
        }
        if (ordinal != null && (languages == null || languages.length == 0)) {
            warn(warnings, prefix + "_language_ordinal", "ignored: no language to count in");
            ordinal = null;
        }

        Integer count = wholeNumber(bundle.get(kind + "_language_count"),
                prefix + "_language_count", warnings);
        if (count != null && count < 1) {
            warn(warnings, prefix + "_language_count", count + " is below 1");
            count = null;
        }

        return new TrackRequest(index, label, ordinal, languages, count);
    }

    @Nullable
    private static String[] parseLanguages(Object value, String path,
                                           ArrayList<String> warnings) {
        if (value == null) {
            return null;
        }

        final List<?> input;
        if (value instanceof String[]) {
            input = Arrays.asList((String[]) value);
        } else if (value instanceof ArrayList) {
            input = (ArrayList<?>) value;
        } else if (value instanceof CharSequence) {
            final String text = value.toString().trim();
            if (text.isEmpty()) {
                return null;
            }
            input = Arrays.asList(text.split("[\\s,]+"));
        } else {
            warn(warnings, path, printable(value) + " is not a list of language codes");
            return null;
        }

        if (input.isEmpty()) {
            return new String[0];
        }

        final Set<String> normalized = new LinkedHashSet<>();
        for (int i = 0; i < input.size(); i++) {
            final Object raw = input.get(i);
            if (raw == null || raw.toString().trim().isEmpty()) {
                continue;
            }
            final String language = raw instanceof CharSequence
                    ? normalizeLanguage(raw.toString()) : null;
            if (language == null) {
                warn(warnings, path + "[" + i + "]",
                        printable(raw) + " is not a language code");
            } else {
                normalized.add(language);
            }
        }

        if (normalized.isEmpty()) {
            return null;
        }
        return normalized.toArray(new String[0]);
    }

    @Nullable
    private static String normalizeLanguage(String value) {
        if (value == null || value.trim().isEmpty()) {
            return null;
        }
        try {
            final String tag = value.trim().replace('_', '-');
            final String iso3 = Locale.forLanguageTag(tag).getISO3Language();
            return iso3.isEmpty() || "und".equals(iso3) ? null : iso3;
        } catch (MissingResourceException ignored) {
            return null;
        }
    }

    @Nullable
    private static Integer wholeNumber(Object value, String path,
                                       ArrayList<String> warnings) {
        if (value == null) {
            return null;
        }
        final Double number = number(value);
        if (number != null
                && number == Math.rint(number)
                && number >= Integer.MIN_VALUE
                && number <= Integer.MAX_VALUE) {
            return number.intValue();
        }
        warn(warnings, path, printable(value) + " is not a whole number");
        return null;
    }

    @Nullable
    private static String strictText(Object value, String path,
                                     ArrayList<String> warnings) {
        if (value == null) {
            return null;
        }
        if (!(value instanceof CharSequence)) {
            warn(warnings, path, printable(value) + " is not text");
            return null;
        }
        return emptyToNull(value.toString().trim());
    }

    @Nullable
    private static Integer integer(Bundle bundle, String key) {
        final Double number = number(bundle.get(key));
        if (number == null || number < Integer.MIN_VALUE || number > Integer.MAX_VALUE) {
            return null;
        }
        return (int) Math.floor(number);
    }

    @Nullable
    private static Double timeMs(Bundle bundle, String base) {
        final Double ms = number(bundle.get(base + "_ms"));
        if (ms != null) {
            return ms;
        }
        final Double sec = number(bundle.get(base + "_sec"));
        return sec == null ? null : sec * 1000.0d;
    }

    @Nullable
    private static Double number(Object value) {
        if (value instanceof Number) {
            final double d = ((Number) value).doubleValue();
            return Double.isNaN(d) || Double.isInfinite(d) ? null : d;
        }
        if (value instanceof CharSequence) {
            try {
                final double d = Double.parseDouble(value.toString().trim());
                return Double.isNaN(d) || Double.isInfinite(d) ? null : d;
            } catch (NumberFormatException ignored) {
                return null;
            }
        }
        return null;
    }

    @Nullable
    private static String text(Bundle bundle, String key) {
        final Object value = bundle.get(key);
        if (value == null) {
            return null;
        }
        return emptyToNull(value.toString().trim());
    }

    @Nullable
    private static String emptyToNull(@Nullable String value) {
        return value == null || value.trim().isEmpty() ? null : value;
    }

    @Nullable
    private static Uri uri(@Nullable String value) {
        return value == null ? null : Uri.parse(value);
    }

    @Nullable
    private static Parcelable[] parcelables(Bundle bundle, String key) {
        final Object value = bundle.get(key);
        if (value instanceof Parcelable[]) {
            return (Parcelable[]) value;
        }
        if (!(value instanceof ArrayList)) {
            return null;
        }
        final ArrayList<?> list = (ArrayList<?>) value;
        final Parcelable[] out = new Parcelable[list.size()];
        for (int i = 0; i < list.size(); i++) {
            out[i] = list.get(i) instanceof Parcelable ? (Parcelable) list.get(i) : null;
        }
        return out;
    }

    @Nullable
    private static String[] headers(Bundle bundle) {
        final Object value = bundle.get("headers");
        if (value instanceof String[]) {
            return (String[]) value;
        }
        if (!(value instanceof ArrayList)) {
            return null;
        }
        final ArrayList<?> list = (ArrayList<?>) value;
        final String[] out = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            final Object entry = list.get(i);
            out[i] = entry == null ? null : entry.toString();
        }
        return out;
    }

    private static boolean selected(Bundle bundle) {
        final Object value = bundle.get("selected");
        if (value instanceof Boolean) {
            return (Boolean) value;
        }
        return value != null && "true".equals(value.toString());
    }

    private static String printable(Object value) {
        return value instanceof CharSequence ? "\"" + value + "\"" : String.valueOf(value);
    }

    private static void warn(ArrayList<String> warnings, String path, String problem) {
        warnings.add(path + ": " + problem);
    }
}
