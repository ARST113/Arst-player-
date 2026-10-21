package com.brouken.player;

import androidx.media3.common.C;
import androidx.media3.common.Format;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/**
 * v2.3.0-style ordering of audio and subtitle menus.
 *
 * No container track indices are modified: an entry keeps its original TrackGroup/index
 * and the ordering affects only presentation. A missing language tag may be recovered
 * from known ISO-639 codes or explicit language names in the track label.
 */
final class TrackOrder {
    private static final List<String> RECOGNIZED = Arrays.asList(
            "rus", "ukr", "eng", "deu", "fra", "spa", "ita", "por",
            "jpn", "kor", "zho", "pol", "tur", "hin", "ara", "ces",
            "nld", "swe", "fin", "ron", "hun", "ell", "heb");

    private TrackOrder() {}

    static String language(Format format, String label) {
        final String tagged = Utils.toIso3Language(format.language);
        if (tagged != null) {
            return tagged;
        }
        final String iso = Utils.languageInName(label, RECOGNIZED);
        if (iso != null) {
            return iso;
        }
        if (label == null) {
            return null;
        }
        final String text = label.toLowerCase(Locale.ROOT);
        // A language-less MKV often has only the studio's name in its track label.
        // Names here are specific to long-standing dubbing teams, not general words like
        // "DUB" or "VOICE" that cannot reveal the actual language.
        if (containsAny(text, "postmodern", "так треба продакшн", "цікава ідея",
                "le-doyen", "українською")) return "ukr";
        if (containsAny(text, "lostfilm", "newstudio", "кубик в кубе",
                "alexfilm", "колобок", "байбако", "дубляж red head sound")) return "rus";
        if (containsAny(text, "русск", "російсь", "russian")) return "rus";
        if (containsAny(text, "україн", "украин", "ukrainian")) return "ukr";
        if (containsAny(text, "англий", "англій", "english")) return "eng";
        if (containsAny(text, "немец", "німець", "german", "deutsch")) return "deu";
        if (containsAny(text, "француз", "french", "français")) return "fra";
        if (containsAny(text, "испан", "іспан", "spanish", "español")) return "spa";
        if (containsAny(text, "итальян", "італій", "italian")) return "ita";
        if (containsAny(text, "япон", "japanese")) return "jpn";
        if (containsAny(text, "корей", "korean")) return "kor";
        if (containsAny(text, "польск", "польсь", "polish")) return "pol";
        if (containsAny(text, "турец", "турець", "turkish")) return "tur";
        if (containsAny(text, "португал", "portuguese")) return "por";
        return null;
    }

    static boolean original(String label) {
        return label != null && containsAny(label.toLowerCase(Locale.ROOT),
                "original", "оригинал", "оригінал", "originale");
    }

    static boolean forced(Format format, String label) {
        if ((format.selectionFlags & C.SELECTION_FLAG_FORCED) != 0) {
            return true;
        }
        if (label == null) {
            return false;
        }
        final String text = label.toLowerCase(Locale.ROOT);
        // Include explicit signs-only tracks without the Matroska forced selection flag.
        // Match English keywords on token boundaries: "unforced" is not a forced track.
        return text.matches(".*(^|[^a-z])(forced|signs|foreign[ -]?parts?)([^a-z]|$).*")
                || containsAny(text, "sign & song", "надписи", "написи",
                        "форсирован", "форсован");
    }

    static int compareAudio(String langA, String nameA, String langB, String nameB,
                            List<String> preferred) {
        int order = Boolean.compare(!original(nameA), !original(nameB));
        if (order != 0) return order;
        order = Integer.compare(languageRank(langA, preferred), languageRank(langB, preferred));
        if (order != 0) return order;
        order = compareLanguages(langA, langB);
        return order != 0 ? order : compareLabels(nameA, nameB);
    }

    static int compareSubtitle(Format a, String nameA, Format b, String nameB,
                               List<String> priorities) {
        final String langA = language(a, nameA);
        final String langB = language(b, nameB);
        // Just+ 2.4.2: show each preferred language first, in the user's order.
        // Everything else is ordered by the actual top-line display label,
        // including tracks without a language tag. Do not group those extras
        // alphabetically by ISO language: that hid familiar subtitle labels.
        final int preferredA = priorities.indexOf(langA);
        final int preferredB = priorities.indexOf(langB);
        final int rankA = preferredA >= 0 ? preferredA : priorities.size();
        final int rankB = preferredB >= 0 ? preferredB : priorities.size();
        int order = Integer.compare(rankA, rankB);
        if (order != 0) return order;
        if (preferredA < 0) {
            return compareLabels(nameA, nameB);
        }
        order = Boolean.compare(!forced(a, nameA), !forced(b, nameB));
        return order != 0 ? order : compareLabels(nameA, nameB);
    }

    static void addLanguageIfMissing(List<String> priorities, String language) {
        if (language != null && !priorities.contains(language)) {
            priorities.add(language);
        }
    }

    private static int languageRank(String language, List<String> priorities) {
        if (language == null) return priorities.size() + 1;
        final int index = priorities.indexOf(language);
        return index < 0 ? priorities.size() : index;
    }

    private static int compareLanguages(String a, String b) {
        if (a == null) return b == null ? 0 : 1;
        if (b == null) return -1;
        return a.compareToIgnoreCase(b);
    }

    private static int compareLabels(String a, String b) {
        if (a == null) return b == null ? 0 : 1;
        if (b == null) return -1;
        return a.compareToIgnoreCase(b);
    }

    private static boolean containsAny(String haystack, String... tokens) {
        for (String token : tokens) {
            if (haystack.contains(token)) return true;
        }
        return false;
    }
}
