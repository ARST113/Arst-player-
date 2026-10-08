package com.brouken.player;

import android.content.Context;

import androidx.annotation.Nullable;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Readable port of the label/track matcher recovered from the official Just+ Player 2.1.2 APK
 * (obfuscated class xp2). It deliberately keeps the same matching rules: studio aliases first,
 * then normalized words, then dub-kind matching; subtitle kind flags must agree.
 */
final class PlaylistTrackMatcher {

    static final int OFF = -2;
    static final int NONE = -1;

    static final class Candidate {
        final String label;
        final String language;
        final boolean supported;

        Candidate(@Nullable String label, @Nullable String language, boolean supported) {
            this.label = label;
            this.language = language;
            this.supported = supported;
        }
    }

    static final class Match {
        final int index;
        final String chosenBy;

        Match(int index, @Nullable String chosenBy) {
            this.index = index;
            this.chosenBy = chosenBy;
        }

        boolean matched() {
            return index >= 0 || index == OFF;
        }
    }

    private static final Set<String> STOP_WORDS;
    private static final Pattern RELEASE_TOKEN = Pattern.compile(
            "([\\d\\s.]+p?\\+?|[48]k|uhd|fhd|hd|sd|hdr\\S*|sdr|dv|blu-?ray|"
                    + "bd-?(rip|remux)?|remux|web-?(dl|rip)?|hdtv|hd-?rip|dvd-?rip|"
                    + "hevc|avc|[hx]\\.?26[45]|\\d{1,2}-?bit|dolby\\s*vision)",
            Pattern.CASE_INSENSITIVE);
    private static final Pattern BRACKETS = Pattern.compile("\\[([^\\]]*)]|\\(([^)]*)\\)");
    private static final Pattern DIGITS_WITH_SUFFIX = Pattern.compile("\\d+\\p{L}{1,3}");
    private static final Pattern ADULT = Pattern.compile("(?s).*(?<!\\d)18\\s*\\+.*");

    static {
        final Set<String> words = new HashSet<>(Arrays.asList(
                "aac","ac3","eac3","e-ac3","dts","dd","ddp","atmos","truehd","flac","mp3",
                "opus","hd","ma","es","stereo","mono","kbps","kbit","ch","rus","ru","russian",
                "ukr","uk","ua","ukrainian","eng","en","english","original","рус","русский",
                "укр","украинский","український","англ","английский","оригинал","оригинальный",
                "оригінал","дубляж","дублированный","дубльований","дубльовано","dub","dubbed",
                "mvo","dvo","avo","vo","многоголосый","многоголосная","двухголосый",
                "двухголосная","одноголосый","одноголосная","закадровый","закадровая",
                "закадровий","багатоголосий","авторский","авторская","лицензия",
                "профессиональный","любительский","полное","дублирование","studio","studios",
                "tv","sub","subs","full","track","ac","e","x","lc","he","pcm","lpcm","av1",
                "vp9","vorbis","dolby","digital","khz","hz","mbps","dubbing","дублювання",
                "повне","двоголосий","двохголосий","одноголосий","авторський","українська",
                "русская","англійська","английская"));
        final Set<String> normalized = new HashSet<>();
        for (String word : words) normalized.add(normalizeKey(word));
        STOP_WORDS = Collections.unmodifiableSet(normalized);
    }

    private final List<Map.Entry<String, String>> studioKeys = new ArrayList<>();
    private final Map<String, String> studioLanguages = new HashMap<>();

    static PlaylistTrackMatcher load(Context context) {
        final PlaylistTrackMatcher matcher = new PlaylistTrackMatcher();
        final int id = context.getResources().getIdentifier(
                "voice_studios", "raw", context.getPackageName());
        if (id == 0) {
            Utils.log("voice studios: raw resource missing");
            return matcher;
        }
        try (InputStream in = context.getResources().openRawResource(id);
             ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            final byte[] buffer = new byte[8192];
            int read;
            while ((read = in.read(buffer)) > 0) out.write(buffer, 0, read);
            matcher.readJson(new String(out.toByteArray(), StandardCharsets.UTF_8));
        } catch (Exception e) {
            Utils.log("voice studios: " + e);
        }
        return matcher;
    }

    private void readJson(String json) throws Exception {
        final JSONObject root = new JSONObject(json);
        final LinkedHashMap<String, String> canonical = new LinkedHashMap<>();

        final JSONArray studios = root.optJSONArray("studios");
        if (studios != null) {
            for (int i = 0; i < studios.length(); i++) {
                final String studio = studios.optString(i, null);
                if (studio != null) canonical.put(normalizeKey(studio), studio);
            }
        }

        final JSONObject aliases = root.optJSONObject("aliases");
        if (aliases != null) {
            final java.util.Iterator<String> keys = aliases.keys();
            while (keys.hasNext()) {
                final String studio = keys.next();
                canonical.put(normalizeKey(studio), studio);
                final JSONArray values = aliases.optJSONArray(studio);
                if (values == null) continue;
                for (int i = 0; i < values.length(); i++) {
                    final String alias = values.optString(i, null);
                    if (alias != null) canonical.put(normalizeKey(alias), studio);
                }
            }
        }

        final JSONObject languages = root.optJSONObject("languages");
        if (languages != null) {
            final java.util.Iterator<String> keys = languages.keys();
            while (keys.hasNext()) {
                final String studio = keys.next();
                studioLanguages.put(studio, languages.optString(studio, null));
            }
        }

        studioKeys.clear();
        studioKeys.addAll(canonical.entrySet());
        // xp2 sorts the normalized keys before matching. Longest first is what prevents a generic
        // suffix such as "studio" from stealing a more specific "rezkastudio" match.
        studioKeys.sort((a, b) -> Integer.compare(b.getKey().length(), a.getKey().length()));
    }

    @Nullable
    String studioLanguage(@Nullable String label) {
        final String studio = studio(label);
        return studio == null ? null : studioLanguages.get(studio);
    }

    @Nullable
    String studio(@Nullable String label) {
        if (label == null) return null;
        final int bracket = label.indexOf('[');
        final String direct = studioIn(bracket >= 0 ? label.substring(0, bracket) : label);
        if (direct != null) return direct;
        final String bracketName = singleMeaningfulBracket(label);
        return bracketName == null ? null : studioIn(bracketName);
    }

    @Nullable
    private String studioIn(String text) {
        final String[] pieces = text.replaceAll("\\d+[.,]\\d+", " ")
                .split("[^\\p{L}\\p{N}]+");
        final Set<String> joins = new HashSet<>();
        for (int start = 0; start < pieces.length; start++) {
            final StringBuilder joined = new StringBuilder();
            for (int i = start; i < pieces.length; i++) {
                joined.append(normalizeKey(pieces[i]));
                if (joined.length() > 0) joins.add(joined.toString());
            }
        }
        for (Map.Entry<String, String> entry : studioKeys) {
            if (joins.contains(entry.getKey())) return entry.getValue();
        }
        return null;
    }

    boolean labelsMatch(@Nullable String wanted, @Nullable String actual) {
        final String wantedStudio = studio(wanted);
        final String actualStudio = studio(actual);
        if (wantedStudio != null || actualStudio != null) {
            return wantedStudio != null && wantedStudio.equals(actualStudio);
        }

        final List<String> a = meaningfulWords(wanted);
        final List<String> b = meaningfulWords(actual);
        if (a.isEmpty() && b.isEmpty()) {
            final String ka = dubKind(wanted);
            return ka != null && ka.equals(dubKind(actual));
        }
        if (a.isEmpty() || b.isEmpty()) return false;

        final List<String> shorter = a.size() <= b.size() ? a : b;
        final List<String> longer = shorter == a ? b : a;
        if (longer.containsAll(shorter)) return true;

        return join(a).equals(join(b));
    }

    Match choose(List<Candidate> candidates, @Nullable PlaylistApi.TrackRequest request,
                 boolean subtitle) {
        if (request == null || !request.hasAny()) return new Match(NONE, null);

        if (subtitle && request.off()) {
            return new Match(OFF, request.index != null && request.index == -1
                    ? "index" : "languages");
        }

        if (request.index != null) {
            final int index = request.index;
            if (index >= 0 && index < candidates.size()) {
                final Candidate candidate = candidates.get(index);
                if (candidate.supported
                        && (request.label == null
                        || (kindFlags(request.label) == kindFlags(candidate.label)
                        && labelsMatch(request.label, candidate.label)))) {
                    return new Match(index, "index");
                }
            }
        }

        if (request.label != null) {
            final String[] languages = request.languages == null || request.languages.length == 0
                    ? new String[]{null} : request.languages;
            for (String language : languages) {
                int fallback = NONE;
                int exact = NONE;
                for (int i = 0; i < candidates.size(); i++) {
                    final Candidate candidate = candidates.get(i);
                    if (!candidate.supported) continue;
                    if (language != null && !language.equals(candidate.language)) continue;
                    if (kindFlags(request.label) != kindFlags(candidate.label)) continue;
                    if (!labelsMatch(request.label, candidate.label)) continue;
                    if (fallback == NONE) fallback = i;
                    if (meaningfulWords(request.label).equals(meaningfulWords(candidate.label))
                            && ADULT.matcher(request.label).matches()
                            == (candidate.label != null && ADULT.matcher(candidate.label).matches())) {
                        exact = i;
                        break;
                    }
                }
                final int chosen = exact != NONE ? exact : fallback;
                if (chosen != NONE) return new Match(chosen, "label");
            }
        }

        if (request.ordinal != null && request.languages != null && request.languages.length > 0) {
            final String language = request.languages[0];
            final List<Integer> indices = new ArrayList<>();
            for (int i = 0; i < candidates.size(); i++) {
                if (language.equals(candidates.get(i).language)) indices.add(i);
            }
            if ((request.count == null || request.count == indices.size())
                    && request.ordinal >= 0 && request.ordinal < indices.size()) {
                final int index = indices.get(request.ordinal);
                if (candidates.get(index).supported) {
                    return new Match(index, "language_ordinal");
                }
            }
        }

        return new Match(NONE, null);
    }

    static int languageOrdinal(List<Candidate> candidates, int index) {
        if (index < 0 || index >= candidates.size()) return -1;
        final String language = candidates.get(index).language;
        if (language == null) return -1;
        int ordinal = 0;
        for (int i = 0; i < index; i++) {
            if (language.equals(candidates.get(i).language)) ordinal++;
        }
        return ordinal;
    }

    static int languageCount(List<Candidate> candidates, int index) {
        if (index < 0 || index >= candidates.size()) return -1;
        final String language = candidates.get(index).language;
        if (language == null) return -1;
        int count = 0;
        for (Candidate candidate : candidates) {
            if (language.equals(candidate.language)) count++;
        }
        return count;
    }

    static int kindFlags(@Nullable String label) {
        if (label == null) return 0;
        int flags = 0;
        for (String word : label.toLowerCase(Locale.ROOT).split("[^\\p{L}]+")) {
            if (word.startsWith("forced") || word.startsWith("форсир")
                    || word.equals("signs") || word.startsWith("надпис")) {
                flags |= 1;
            } else if (word.equals("sdh") || word.equals("cc")
                    || word.startsWith("hearing") || word.startsWith("глух")) {
                flags |= 2;
            } else if (word.startsWith("comment") || word.startsWith("коммент")
                    || word.startsWith("комент")) {
                flags |= 4;
            }
        }
        return flags;
    }

    @Nullable
    private static String dubKind(@Nullable String label) {
        if (label == null) return null;
        String result = null;
        for (String word : label.split("[^\\p{L}]+")) {
            final String w = normalizeKey(word);
            if (w.startsWith("дубляж") || w.startsWith("дублир") || w.startsWith("дубльов")
                    || w.startsWith("дублюв") || w.equals("dub") || w.equals("dubbed")
                    || w.equals("dubbing")) return "dub";
            if (w.startsWith("одноголос") || w.startsWith("автор") || w.equals("avo")) {
                result = "avo";
            } else if (w.startsWith("двухголос") || w.startsWith("двоголос")
                    || w.startsWith("двохголос") || w.equals("dvo")) {
                if (!"avo".equals(result)) result = "dvo";
            } else if ((w.startsWith("многоголос") || w.startsWith("багатоголос")
                    || w.startsWith("закадров") || w.equals("mvo")) && result == null) {
                result = "mvo";
            }
        }
        return result;
    }

    @Nullable
    private static String singleMeaningfulBracket(String label) {
        final Matcher matcher = BRACKETS.matcher(label);
        String found = null;
        while (matcher.find()) {
            final String group = matcher.group(matcher.group(1) == null ? 2 : 1);
            for (String piece : group.split(",")) {
                final String text = piece.trim();
                if (text.isEmpty() || RELEASE_TOKEN.matcher(text).matches()
                        || meaningfulWords(text).isEmpty()) continue;
                if (found != null) return null;
                found = text;
            }
        }
        return found;
    }

    private static List<String> meaningfulWords(@Nullable String label) {
        final List<String> out = new ArrayList<>();
        if (label == null) return out;
        final String cleaned = label.toLowerCase(Locale.ROOT)
                .replaceAll("\\[[^\\]]*]|\\([^)]*\\)", " ")
                .replaceAll("\\d+[.,]\\d+", " ")
                .replaceAll("[^\\p{L}\\p{N}]+", " ").trim();
        if (cleaned.isEmpty()) return out;
        for (String word : cleaned.split(" +")) {
            final String normalized = normalizeKey(word);
            if (!normalized.isEmpty() && !STOP_WORDS.contains(normalized)
                    && !normalized.matches("\\d+")
                    && !DIGITS_WITH_SUFFIX.matcher(normalized).matches()
                    && !RELEASE_TOKEN.matcher(normalized).matches()) {
                out.add(normalized);
            }
        }
        return out;
    }

    private static String normalizeKey(String value) {
        final StringBuilder out = new StringBuilder(value.length());
        for (char c : value.toLowerCase(Locale.ROOT).toCharArray()) {
            if (c == 'ы') out.append('и');
            else if (c == 'ё' || c == 'є') out.append('е');
            else if (c == 'ґ') out.append('г');
            else if (c == 'і' || c == 'ї') out.append('и');
            else if (Character.isLetterOrDigit(c)) out.append(c);
        }
        return out.toString();
    }

    private static String join(List<String> words) {
        final StringBuilder out = new StringBuilder();
        for (String word : words) out.append(word);
        return out.toString();
    }
}
