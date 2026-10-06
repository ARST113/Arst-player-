package defpackage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: compiled from: r8-map-id-d5d7d661e67c62f2588b5a5666a7a20281e417b1121a202706e8fa77676a2bdd */
/* JADX INFO: loaded from: classes.dex */
public final class xp2 {
    public static final HashSet d;
    public static final Pattern e;
    public static final Pattern f;
    public static final Pattern g;
    public final ArrayList a;
    public final HashMap b;
    public String c;

    static {
        HashSet hashSet = new HashSet(Arrays.asList("aac", "ac3", "eac3", "e-ac3", "dts", "dd", "ddp", "atmos", "truehd", "flac", "mp3", "opus", "hd", "ma", "es", "stereo", "mono", "kbps", "kbit", "ch", "rus", "ru", "russian", "ukr", "uk", "ua", "ukrainian", "eng", "en", "english", "original", "рус", "русский", "укр", "украинский", "український", "англ", "английский", "оригинал", "оригинальный", "оригінал", "дубляж", "дублированный", "дубльований", "дубльовано", "dub", "dubbed", "mvo", "dvo", "avo", "vo", "многоголосый", "многоголосная", "двухголосый", "двухголосная", "одноголосый", "одноголосная", "закадровый", "закадровая", "закадровий", "багатоголосий", "авторский", "авторская", "лицензия", "профессиональный", "любительский", "полное", "дублирование", "studio", "studios", "tv", "sub", "subs", "full", "track", "ac", "e", "x", "lc", "he", "pcm", "lpcm", "av1", "vp9", "vorbis", "dolby", "digital", "khz", "hz", "mbps", "dubbing", "дублювання", "повне", "двоголосий", "двохголосий", "одноголосий", "авторський", "українська", "русская", "англійська", "английская"));
        d = hashSet;
        e = Pattern.compile("([\\d\\s.]+p?\\+?|[48]k|uhd|fhd|hd|sd|hdr\\S*|sdr|dv|blu-?ray|bd-?(rip|remux)?|remux|web-?(dl|rip)?|hdtv|hd-?rip|dvd-?rip|hevc|avc|[hx]\\.?26[45]|\\d{1,2}-?bit|dolby\\s*vision)", 2);
        f = Pattern.compile("\\[([^\\]]*)]|\\(([^)]*)\\)");
        g = Pattern.compile("\\d+\\p{L}{1,3}");
        ArrayList arrayList = new ArrayList(hashSet);
        hashSet.clear();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            d.add(d((String) it.next()));
        }
    }

    public xp2(ArrayList arrayList, HashMap map, HashMap map2) {
        this.b = map2;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            linkedHashMap.put(d(str), str);
        }
        for (Map.Entry entry : map.entrySet()) {
            linkedHashMap.put(d((String) entry.getKey()), (String) entry.getKey());
            Iterator it2 = ((List) entry.getValue()).iterator();
            while (it2.hasNext()) {
                linkedHashMap.put(d((String) it2.next()), (String) entry.getKey());
            }
        }
        ArrayList arrayList2 = new ArrayList(linkedHashMap.entrySet());
        this.a = arrayList2;
        Collections.sort(arrayList2, new tf2((byte) 6));
    }

    public static String a(String str) {
        Matcher matcher = f.matcher(str);
        String str2 = null;
        while (matcher.find()) {
            for (String str3 : matcher.group(matcher.group(1) == null ? 2 : 1).split(",")) {
                String strTrim = str3.trim();
                if (!strTrim.isEmpty() && !e.matcher(strTrim).matches() && !l(strTrim).isEmpty()) {
                    if (str2 != null) {
                        return null;
                    }
                    str2 = strTrim;
                }
            }
        }
        return str2;
    }

    public static int c(String str, ArrayList arrayList) {
        int i = -1;
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            wp2 wp2Var = (wp2) arrayList.get(i2);
            int iA = wp2Var.a();
            if (wp2Var.c && (iA & 4) == 0 && str.equals(wp2Var.a)) {
                if ((iA & 3) == 0) {
                    return i2;
                }
                if (i == -1) {
                    i = i2;
                }
            }
        }
        return i;
    }

    /* JADX WARN: Code duplicated, block: B:21:0x0047  */
    public static String d(String str) {
        StringBuilder sb = new StringBuilder(str.length());
        for (char c : str.toLowerCase(Locale.ROOT).toCharArray()) {
            if (c == 1099) {
                sb.append((char) 1080);
            } else if (c == 1105 || c == 1108) {
                sb.append((char) 1077);
            } else if (c == 1169) {
                sb.append((char) 1075);
            } else if (c == 1110 || c == 1111) {
                sb.append((char) 1080);
            } else if (Character.isLetterOrDigit(c)) {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /* JADX WARN: Code duplicated, block: B:47:0x00aa A[PHI: r5
      0x00aa: PHI (r5v2 java.lang.String) = (r5v1 java.lang.String), (r5v1 java.lang.String), (r5v1 java.lang.String), (r5v3 java.lang.String) binds: [B:23:0x0056, B:25:0x005e, B:27:0x0064, B:46:0x00a8] A[DONT_GENERATE, DONT_INLINE]] */
    public static String f(String str) {
        String str2 = null;
        if (str == null) {
            return null;
        }
        for (String str3 : str.split("[^\\p{L}]+")) {
            String strD = d(str3);
            if (strD.startsWith("дубляж") || strD.startsWith("дублир") || strD.startsWith("дубльов") || strD.startsWith("дублюв") || strD.equals("dub") || strD.equals("dubbed") || strD.equals("dubbing")) {
                return "dub";
            }
            String str4 = "avo";
            if (strD.startsWith("одноголос") || strD.startsWith("автор") || strD.equals("avo")) {
                str2 = str4;
            } else if (!strD.startsWith("двухголос") && !strD.startsWith("двоголос") && !strD.startsWith("двохголос") && !strD.equals("dvo")) {
                str4 = "mvo";
                if ((strD.startsWith("многоголос") || strD.startsWith("багатоголос") || strD.startsWith("закадров") || strD.equals("mvo")) && str2 == null) {
                    str2 = str4;
                }
            } else if (!"avo".equals(str2)) {
                str2 = "dvo";
            }
        }
        return str2;
    }

    public static int g(String str) {
        if (str == null) {
            return 0;
        }
        int i = 0;
        for (String str2 : str.toLowerCase(Locale.ROOT).split("[^\\p{L}]+")) {
            if (str2.startsWith("forced") || str2.startsWith("форсир") || str2.equals("signs") || str2.startsWith("надпис")) {
                i |= 1;
            } else if (str2.equals("sdh") || str2.equals("cc") || str2.startsWith("hearing") || str2.startsWith("глух")) {
                i |= 2;
            } else if (str2.startsWith("comment") || str2.startsWith("коммент") || str2.startsWith("комент")) {
                i |= 4;
            }
        }
        return i;
    }

    public static ArrayList k(String str) {
        String strA;
        ArrayList arrayListL = l(str);
        return (!arrayListL.isEmpty() || str == null || (strA = a(str)) == null) ? arrayListL : l(strA);
    }

    public static ArrayList l(String str) {
        ArrayList arrayList = new ArrayList();
        if (str != null) {
            for (String str2 : str.toLowerCase(Locale.ROOT).replaceAll("\\[[^\\]]*]|\\([^)]*\\)", " ").replaceAll("\\d+[.,]\\d+", " ").replaceAll("[^\\p{L}\\p{N}]+", " ").trim().split(" +")) {
                String strD = d(str2);
                if (!strD.isEmpty() && !d.contains(strD) && !strD.matches("\\d+") && !g.matcher(strD).matches() && !e.matcher(strD).matches()) {
                    arrayList.add(strD);
                }
            }
        }
        return arrayList;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r14v0 */
    /* JADX WARN: Type inference failed for: r14v1, types: [int] */
    /* JADX WARN: Type inference failed for: r14v3 */
    /* JADX WARN: Type inference failed for: r15v0 */
    /* JADX WARN: Type inference failed for: r15v1 */
    /* JADX WARN: Type inference failed for: r15v2, types: [int] */
    /* JADX WARN: Type inference failed for: r15v3 */
    /* JADX WARN: Type inference failed for: r15v4 */
    /* JADX WARN: Type inference failed for: r15v5 */
    /* JADX WARN: Type inference failed for: r15v6 */
    /* JADX WARN: Type inference failed for: r15v7 */
    /* JADX WARN: Type inference failed for: r18v0, types: [java.util.ArrayList] */
    /* JADX WARN: Type inference failed for: r3v10 */
    /* JADX WARN: Type inference failed for: r3v11 */
    /* JADX WARN: Type inference failed for: r3v12 */
    /* JADX WARN: Type inference failed for: r3v15 */
    /* JADX WARN: Type inference failed for: r3v16 */
    /* JADX WARN: Type inference failed for: r3v17 */
    /* JADX WARN: Type inference failed for: r3v8 */
    public final int b(ArrayList arrayList, vp2 vp2Var, boolean z) {
        this.c = null;
        byte b = -1;
        if (vp2Var != null) {
            String[] strArr = vp2Var.d;
            String str = vp2Var.b;
            Integer num = vp2Var.a;
            Integer num2 = vp2Var.c;
            if (num != null || str != null || num2 != null || strArr != null) {
                if (z && vp2Var.b()) {
                    this.c = (num == null || num.intValue() != -1) ? "languages" : "index";
                    return -2;
                }
                if (e(arrayList, vp2Var) == null) {
                    this.c = "index";
                    return num.intValue();
                }
                boolean z2 = false;
                if (str != null) {
                    String[] strArr2 = (strArr == null || strArr.length == 0) ? new String[]{null} : strArr;
                    int length = strArr2.length;
                    int i = 0;
                    while (i < length) {
                        String str2 = strArr2[i];
                        byte b2 = b;
                        ?? r15 = b2 == true ? 1 : 0;
                        boolean z3 = z2;
                        ?? r14 = z3;
                        ?? r3 = b2;
                        while (r14 < arrayList.size()) {
                            wp2 wp2Var = (wp2) arrayList.get(r14);
                            boolean z4 = wp2Var.c;
                            String str3 = wp2Var.b;
                            if (z4 && ((str2 == null || str2.equals(wp2Var.a)) && g(str) == wp2Var.a() && h(str, str3))) {
                                if (r3 == -1) {
                                    r3 = r3;
                                    r3 = r14;
                                }
                                if (r15 == -1 && k(str).equals(k(str3))) {
                                    if (str.matches("(?s).*(?<!\\d)18\\s*\\+.*") == ((str3 == null || !str3.matches("(?s).*(?<!\\d)18\\s*\\+.*")) ? z3 : true)) {
                                        r15 = r14;
                                    }
                                }
                            }
                            b = -1;
                            r3 = r3;
                            r14++;
                            r15 = r15;
                        }
                        byte b3 = b;
                        ?? r16 = r15;
                        if (r15 == b3) {
                            r16 = r3 == true ? 1 : 0;
                        }
                        if (r16 != b3) {
                            this.c = "label";
                            return r16;
                        }
                        i++;
                        z2 = z3;
                        b = -1;
                    }
                }
                boolean z5 = z2;
                String str4 = (strArr == null || strArr.length == 0) ? null : strArr[z5 ? 1 : 0];
                if (num2 != null && str4 != null) {
                    ArrayList arrayList2 = new ArrayList();
                    for (int i2 = z5 ? 1 : 0; i2 < arrayList.size(); i2++) {
                        if (str4.equals(((wp2) arrayList.get(i2)).a)) {
                            arrayList2.add(Integer.valueOf(i2));
                        }
                    }
                    Integer num3 = vp2Var.e;
                    if ((num3 == null || num3.intValue() == arrayList2.size()) && num2.intValue() >= 0 && num2.intValue() < arrayList2.size()) {
                        int iIntValue = ((Integer) arrayList2.get(num2.intValue())).intValue();
                        if (((wp2) arrayList.get(iIntValue)).c) {
                            this.c = "language_ordinal";
                            return iIntValue;
                        }
                    }
                }
                return -1;
            }
        }
        return -1;
    }

    public final String e(ArrayList arrayList, vp2 vp2Var) {
        Integer num;
        if (vp2Var == null || (num = vp2Var.a) == null) {
            return "none given";
        }
        if (num.intValue() < 0) {
            return "names no track";
        }
        if (num.intValue() >= arrayList.size()) {
            StringBuilder sb = new StringBuilder("out of range 0..");
            sb.append(arrayList.size() - 1);
            return sb.toString();
        }
        wp2 wp2Var = (wp2) arrayList.get(num.intValue());
        if (!wp2Var.c) {
            return "not supported";
        }
        String str = vp2Var.b;
        if (str == null) {
            return null;
        }
        if (g(str) == wp2Var.a() && h(str, wp2Var.b)) {
            return null;
        }
        return "contradicts the label";
    }

    public final boolean h(String str, String str2) {
        String strI = i(str);
        String strI2 = i(str2);
        if (strI != null || strI2 != null) {
            return strI != null && strI.equals(strI2);
        }
        ArrayList arrayListK = k(str);
        ArrayList arrayListK2 = k(str2);
        if (arrayListK.isEmpty() && arrayListK2.isEmpty()) {
            String strF = f(str);
            return strF != null && strF.equals(f(str2));
        }
        if (arrayListK.isEmpty() || arrayListK2.isEmpty()) {
            return false;
        }
        ArrayList arrayList = arrayListK.size() <= arrayListK2.size() ? arrayListK : arrayListK2;
        if ((arrayList == arrayListK ? arrayListK2 : arrayListK).containsAll(arrayList)) {
            return true;
        }
        StringBuilder sb = new StringBuilder();
        Iterator it = arrayListK.iterator();
        if (it.hasNext()) {
            while (true) {
                sb.append((CharSequence) it.next());
                if (!it.hasNext()) {
                    break;
                }
                sb.append((CharSequence) "");
            }
        }
        String string = sb.toString();
        StringBuilder sb2 = new StringBuilder();
        Iterator it2 = arrayListK2.iterator();
        if (it2.hasNext()) {
            while (true) {
                sb2.append((CharSequence) it2.next());
                if (!it2.hasNext()) {
                    break;
                }
                sb2.append((CharSequence) "");
            }
        }
        return string.equals(sb2.toString());
    }

    public final String i(String str) {
        if (str == null) {
            return null;
        }
        int iIndexOf = str.indexOf(91);
        String strJ = j(iIndexOf >= 0 ? str.substring(0, iIndexOf) : str);
        if (strJ != null) {
            return strJ;
        }
        String strA = a(str);
        if (strA == null) {
            return null;
        }
        return j(strA);
    }

    public final String j(String str) {
        String[] strArrSplit = str.replaceAll("\\d+[.,]\\d+", " ").split("[^\\p{L}\\p{N}]+");
        HashSet hashSet = new HashSet();
        for (int i = 0; i < strArrSplit.length; i++) {
            StringBuilder sb = new StringBuilder();
            for (int i2 = i; i2 < strArrSplit.length; i2++) {
                sb.append(d(strArrSplit[i2]));
                if (sb.length() > 0) {
                    hashSet.add(sb.toString());
                }
            }
        }
        for (Map.Entry entry : this.a) {
            if (hashSet.contains(entry.getKey())) {
                return (String) entry.getValue();
            }
        }
        return null;
    }
}
