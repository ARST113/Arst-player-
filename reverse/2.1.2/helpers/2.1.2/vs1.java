package defpackage;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import j$.util.DesugarCollections;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: compiled from: r8-map-id-d5d7d661e67c62f2588b5a5666a7a20281e417b1121a202706e8fa77676a2bdd */
/* JADX INFO: loaded from: classes.dex */
public final class vs1 {
    public static final List k = DesugarCollections.unmodifiableList(Arrays.asList("ask_open", "ask_every", "always", "never"));
    public static final Pattern l = Pattern.compile("\\s*(?:(?:episode|ep|e|эпизод|епізод|серия|серія)\\.?\\s*#?\\s*(\\d+)|(\\d+)\\s*-?\\s*(?:episode|ep|e|эпизод|епізод|серия|серія)|#?(\\d+))\\s*", 66);
    public final String a;
    public final int b;
    public final String[] c;
    public final vp2 d;
    public final vp2 e;
    public final List f;
    public final long g;
    public final String h;
    public final String i;
    public final List j;

    public vs1(String str, int i, String[] strArr, vp2 vp2Var, vp2 vp2Var2, List list, long j, String str2, String str3, List list2) {
        this.a = str;
        this.h = str2;
        this.j = list2;
        this.g = j;
        this.b = i;
        this.c = strArr;
        this.d = vp2Var;
        this.e = vp2Var2;
        this.f = list;
        this.i = str3;
    }

    public static vs1 a(String str, String str2) {
        vp2 vp2Var = new vp2(null, null, null, null, null);
        List list = Collections.EMPTY_LIST;
        return new vs1(str, 0, null, vp2Var, vp2Var, list, 0L, null, str2, list);
    }

    public static boolean b(Intent intent) {
        Bundle extras = intent == null ? null : intent.getExtras();
        return extras != null && (extras.get("playlist") instanceof Bundle);
    }

    public static boolean c(int i, String str) {
        String strGroup;
        Matcher matcher = (str == null || i < 0) ? null : l.matcher(str);
        if (matcher != null && matcher.matches()) {
            if (matcher.group(1) != null) {
                strGroup = matcher.group(1);
            } else {
                strGroup = matcher.group(matcher.group(2) == null ? 3 : 2);
            }
            if (strGroup.length() < 6 && Integer.parseInt(strGroup) == i) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static rs1 d(Bundle bundle, String str, String str2, boolean z, String str3, ArrayList arrayList) {
        vp2 vp2Var;
        ArrayList arrayList2;
        us1 us1Var;
        int i;
        long j;
        long jMax;
        List list;
        Integer num;
        vp2 vp2VarC = vp2.c(bundle, "subtitle", str3, arrayList);
        boolean z2 = z || ((num = vp2VarC.a) != null && num.intValue() >= 0);
        ArrayList arrayList3 = new ArrayList();
        String strE = e(bundle, arrayList3);
        List listF = f(bundle, "", z2, str3, arrayList);
        ArrayList arrayList4 = new ArrayList();
        Parcelable[] parcelableArrK = ha1.K("voices", bundle);
        if (parcelableArrK == null || parcelableArrK.length <= 0) {
            vp2Var = vp2VarC;
            arrayList2 = arrayList3;
            us1Var = null;
            if (strE == null) {
                bl.d("has neither uri nor qualities");
                return null;
            }
            i = -1;
        } else {
            if (strE != null) {
                bl.d("has both voices and uri or qualities");
                return null;
            }
            int i2 = 0;
            i = -1;
            while (i2 < parcelableArrK.length) {
                Parcelable parcelable = parcelableArrK[i2];
                if (!(parcelable instanceof Bundle)) {
                    bl.d(uh0.t("voices[", i2, "] is not a Bundle"));
                    return null;
                }
                Bundle bundle2 = (Bundle) parcelable;
                String strY = ha1.Y("label", bundle2);
                if (strY == null || strY.trim().isEmpty()) {
                    bl.d(uh0.t("voices[", i2, "] has no label"));
                    return null;
                }
                ArrayList arrayList5 = new ArrayList();
                String strE2 = e(bundle2, arrayList5);
                if (strE2 == null) {
                    bl.d(uh0.t("voices[", i2, "] has neither uri nor qualities"));
                    return null;
                }
                vp2 vp2Var2 = vp2VarC;
                Uri uri = Uri.parse(strE2);
                ArrayList arrayList6 = arrayList3;
                arrayList4.add(new us1(strY, uri, DesugarCollections.unmodifiableList(arrayList5), ha1.K("subtitles", bundle2) == null ? null : f(bundle2, uh0.t("voices[", i2, "] "), z2, str3, arrayList)));
                if (i < 0 && ha1.d(bundle2)) {
                    i = i2;
                }
                i2++;
                arrayList3 = arrayList6;
                vp2VarC = vp2Var2;
            }
            vp2Var = vp2VarC;
            arrayList2 = arrayList3;
            us1Var = null;
        }
        us1 us1Var2 = arrayList4.isEmpty() ? us1Var : (us1) arrayList4.get(Math.max(0, i));
        if (us1Var2 != null) {
            strE = us1Var2.b.toString();
        }
        List listUnmodifiableList = us1Var2 == null ? DesugarCollections.unmodifiableList(arrayList2) : us1Var2.c;
        List list2 = (us1Var2 == null || (list = us1Var2.d) == null) ? listF : list;
        Double dF = ha1.F("clip_start", bundle);
        Double dF2 = ha1.F("clip_end", bundle);
        long jRound = (dF == null || dF.doubleValue() <= 0.0d) ? 0L : Math.round(dF.doubleValue());
        long jRound2 = (dF2 == null || dF2.doubleValue() <= ((double) jRound)) ? Long.MIN_VALUE : Math.round(dF2.doubleValue());
        Double dF3 = ha1.F("position", bundle);
        if (dF3 == null) {
            jMax = -9223372036854775807L;
            j = -9223372036854775807L;
        } else {
            j = -9223372036854775807L;
            jMax = Math.max(0L, (long) Math.floor(dF3.doubleValue()));
        }
        if (jMax != j && jRound2 != Long.MIN_VALUE) {
            jMax = Math.min(jMax, jRound2 - jRound);
        }
        Integer numA = ha1.A("season", bundle);
        Integer numA2 = ha1.A("episode", bundle);
        String strY2 = ha1.Y("thumbnail", bundle);
        String strY3 = ha1.Y("logo", bundle);
        if (strY3 == null) {
            strY3 = str;
        }
        String strY4 = ha1.Y("background", bundle);
        if (strY4 == null) {
            strY4 = str2;
        }
        Object obj = us1Var;
        long j2 = jMax;
        return new rs1(Uri.parse(strE), ha1.Y("title", bundle), ha1.Y("episode_title", bundle), strY2 == null ? obj : Uri.parse(strY2), strY3 == null ? obj : Uri.parse(strY3), strY4 == null ? obj : Uri.parse(strY4), ha1.Y("imdb_id", bundle), ha1.Y("tmdb_id", bundle), numA == null ? -1 : numA.intValue(), numA2 == null ? -1 : numA2.intValue(), ha1.Z(bundle), j2, jRound, jRound2, vp2.c(bundle, "audio", str3, arrayList), vp2Var, ha1.Y("segments", bundle), listUnmodifiableList, list2, DesugarCollections.unmodifiableList(arrayList4), i >= 0, listF);
    }

    public static String e(Bundle bundle, ArrayList arrayList) {
        Parcelable[] parcelableArrK = ha1.K("qualities", bundle);
        String strY = null;
        if (parcelableArrK != null) {
            for (Parcelable parcelable : parcelableArrK) {
                if (parcelable instanceof Bundle) {
                    Bundle bundle2 = (Bundle) parcelable;
                    String strY2 = ha1.Y("label", bundle2);
                    String strY3 = ha1.Y("uri", bundle2);
                    if (strY2 != null && strY3 != null) {
                        arrayList.add(new ss1(strY2, strY3));
                        if (strY == null && ha1.d(bundle2)) {
                            strY = strY3;
                        }
                    }
                }
            }
        }
        if (strY == null) {
            strY = ha1.Y("uri", bundle);
        }
        return (strY != null || arrayList.isEmpty()) ? strY : ((ss1) arrayList.get(0)).b;
    }

    public static List f(Bundle bundle, String str, boolean z, String str2, ArrayList arrayList) {
        ArrayList arrayList2 = new ArrayList();
        Parcelable[] parcelableArrK = ha1.K("subtitles", bundle);
        if (parcelableArrK != null) {
            for (int i = 0; i < parcelableArrK.length; i++) {
                Parcelable parcelable = parcelableArrK[i];
                Bundle bundle2 = parcelable instanceof Bundle ? (Bundle) parcelable : null;
                String strY = bundle2 == null ? null : ha1.Y("uri", bundle2);
                if (strY == null) {
                    StringBuilder sb = new StringBuilder(str);
                    sb.append("subtitles[");
                    sb.append(i);
                    sb.append("] ");
                    sb.append(bundle2 == null ? "is not a Bundle" : "has no uri");
                    String string = sb.toString();
                    if (z) {
                        bl.d(string.concat(", and subtitle_index counts on it"));
                        return null;
                    }
                    ha1.h0(arrayList, str2, string.concat("; skipped"));
                } else {
                    arrayList2.add(new ts1(Uri.parse(strY), ha1.Y("mime", bundle2), ha1.Y("language", bundle2), ha1.Y("label", bundle2), ha1.d(bundle2)));
                }
            }
        }
        return DesugarCollections.unmodifiableList(arrayList2);
    }
}
