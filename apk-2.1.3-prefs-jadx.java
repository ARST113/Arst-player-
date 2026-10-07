package defpackage;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.provider.DocumentsContract;
import android.text.TextUtils;
import android.view.accessibility.CaptioningManager;
import com.brouken.player.PlayerActivity;
import j$.util.DesugarCollections;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: r8-map-id-0cee80150bab987b3edc91bcfc1e04786a1418881e5c92a3b999d44d61001263 */
/* JADX INFO: loaded from: classes.dex */
public final class hu1 {
    public static final Set V0 = DesugarCollections.unmodifiableSet(new HashSet(Arrays.asList("mediaUri", "mediaType", "subtitleUri", "subtitleSecondaryUri", "audioTrackId", "subtitleTrackId", "brightness", "brightnessPercent", "volumePercent", "speed", "holdSpeed", "playlistGrid", "scopeUri", "askScope", "firstRun", "restoreAutoRotate", "browseTrail", "browseDest", "updateLastCheck", "updatePending", "updateSkippedVersionCode", "subtitleSearch", "subtitleSearchStrict", "subtitleTranslate", "subtitleTranslateMode", "mediaCacheBuild")));
    public static final String[] W0 = {"resizeMode_", "scale_", "aspectRatio_"};
    public int K;
    public long N0;
    public int O0;
    public l72 P0;
    public Set Q0;
    public LinkedHashMap R0;
    public final Activity a;
    public final SharedPreferences b;
    public Uri c;
    public boolean d;
    public Uri e;
    public Uri f;
    public Uri g;
    public String h;
    public float m;
    public String n;
    public String o;
    public int p;
    public int q;
    public final boolean r;
    public boolean s;
    public boolean t;
    public int i = 0;
    public int U0 = 4;
    public float j = 1.0f;
    public float k = 0.0f;
    public int l = -1;
    public boolean u = false;
    public boolean v = false;
    public String w = "adjust";
    public boolean x = false;
    public boolean y = false;
    public int z = 5000;
    public String A = "time";
    public int B = 30;
    public boolean C = false;
    public boolean D = false;
    public boolean E = false;
    public int F = 0;
    public boolean G = true;
    public boolean H = false;
    public boolean I = true;
    public boolean J = false;
    public boolean L = false;
    public boolean M = false;
    public boolean N = false;
    public boolean O = false;
    public boolean P = false;
    public int Q = 1;
    public int R = 0;
    public int S = 0;
    public boolean T = false;
    public boolean U = false;
    public boolean V = false;
    public String W = "";
    public String X = "";
    public String Y = "";
    public boolean Z = false;
    public boolean a0 = false;
    public boolean b0 = false;
    public boolean c0 = true;
    public String d0 = "mozhi,google";
    public boolean e0 = false;
    public float f0 = 1.0f;
    public int g0 = -1;
    public int h0 = 0;
    public int i0 = 1;
    public String j0 = "always";
    public int k0 = -3355444;
    public int l0 = Integer.MIN_VALUE;
    public float m0 = 1.0f;
    public boolean n0 = true;
    public String o0 = "brief";
    public String p0 = "brief";
    public boolean q0 = false;
    public String r0 = "ring";
    public boolean s0 = true;
    public String t0 = "all";
    public boolean u0 = false;
    public boolean v0 = false;
    public boolean w0 = false;
    public boolean x0 = true;
    public boolean y0 = false;
    public String z0 = "logo";
    public String A0 = "detailed";
    public String B0 = "askOpen";
    public boolean C0 = false;
    public String D0 = "every";
    public boolean E0 = true;
    public String F0 = "";
    public String G0 = "";
    public boolean H0 = false;
    public String I0 = "";
    public String J0 = "";
    public boolean K0 = false;
    public boolean L0 = true;
    public boolean M0 = true;
    public boolean S0 = true;
    public long T0 = -1;

    public hu1(Activity activity) {
        l72 l72Var;
        int i;
        this.m = 1.0f;
        this.p = -1;
        this.q = 100;
        this.r = true;
        this.s = true;
        this.t = false;
        this.N0 = 0L;
        this.O0 = 0;
        this.Q0 = Collections.EMPTY_SET;
        this.a = activity;
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(activity);
        this.b = defaultSharedPreferences;
        if (defaultSharedPreferences.contains("mediaUri")) {
            this.c = Uri.parse(defaultSharedPreferences.getString("mediaUri", null));
        }
        if (defaultSharedPreferences.contains("mediaType")) {
            this.h = defaultSharedPreferences.getString("mediaType", null);
        }
        if (defaultSharedPreferences.contains("brightnessPercent")) {
            this.p = defaultSharedPreferences.getInt("brightnessPercent", this.p);
        } else {
            int i2 = defaultSharedPreferences.getInt("brightness", -1);
            this.p = i2 >= 0 ? (i2 * 100) / 30 : -1;
        }
        this.q = defaultSharedPreferences.getInt("volumePercent", this.q);
        this.r = defaultSharedPreferences.getBoolean("firstRun", this.r);
        if (defaultSharedPreferences.contains("subtitleUri")) {
            this.e = Uri.parse(defaultSharedPreferences.getString("subtitleUri", null));
        }
        if (defaultSharedPreferences.contains("subtitleSecondaryUri")) {
            this.f = Uri.parse(defaultSharedPreferences.getString("subtitleSecondaryUri", null));
        }
        if (defaultSharedPreferences.contains("audioTrackId")) {
            this.o = defaultSharedPreferences.getString("audioTrackId", this.o);
        }
        if (defaultSharedPreferences.contains("subtitleTrackId")) {
            this.n = defaultSharedPreferences.getString("subtitleTrackId", this.n);
        }
        if (defaultSharedPreferences.contains("scopeUri")) {
            this.g = Uri.parse(defaultSharedPreferences.getString("scopeUri", null));
        }
        this.s = defaultSharedPreferences.getBoolean("askScope", this.s);
        this.t = defaultSharedPreferences.getBoolean("restoreAutoRotate", this.t);
        this.m = defaultSharedPreferences.getFloat("speed", this.m);
        this.N0 = defaultSharedPreferences.getLong("updateLastCheck", this.N0);
        this.O0 = defaultSharedPreferences.getInt("updateSkippedVersionCode", this.O0);
        String string = defaultSharedPreferences.getString("updatePending", null);
        if (string == null || string.isEmpty()) {
            l72Var = null;
        } else {
            try {
                JSONObject jSONObject = new JSONObject(string);
                l72Var = new l72(jSONObject.optInt("versionCode"), jSONObject.optString("tagName"), jSONObject.optString("versionName"), jSONObject.optString("changelog"), jSONObject.optString("apkUrl"), jSONObject.optLong("size"), jSONObject.optString("publishedAt"));
            } catch (JSONException unused) {
                l72Var = null;
            }
        }
        this.P0 = l72Var;
        if (l72Var != null && ((i = l72Var.a) <= 2001003 || i == this.O0)) {
            this.P0 = null;
        }
        p();
        SharedPreferences sharedPreferences = this.b;
        if (!sharedPreferences.getBoolean("revokedAudioMimesRelearned3", false)) {
            this.Q0 = Collections.EMPTY_SET;
            sharedPreferences.edit().remove("revokedAudioMimes").putBoolean("revokedAudioMimesRelearned3", true).apply();
        }
        o();
    }

    /* JADX WARN: Code duplicated, block: B:10:0x0029  */
    /* JADX WARN: Code duplicated, block: B:12:0x002e  */
    public static int a(Context context, boolean z) {
        String string = PreferenceManager.getDefaultSharedPreferences(context).getString("accentTheme", "coral");
        for (int i : lf2.y(32)) {
            if (uh0.j(i).equals(string)) {
                return z ? uh0.k(i) : uh0.h(i);
            }
        }
        i = 1;
        if (z) {
        }
    }

    public static String b(Uri uri) {
        if (!"content".equals(uri.getScheme())) {
            return null;
        }
        try {
            return uri.getAuthority() + '/' + DocumentsContract.getDocumentId(uri);
        } catch (IllegalArgumentException unused) {
            return null;
        }
    }

    public static String c(Context context) {
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        String string = defaultSharedPreferences.getString("holdSpeedMode", null);
        if (string != null) {
            return string;
        }
        String str = defaultSharedPreferences.getBoolean("holdSpeed", true) ? "adjust" : "off";
        defaultSharedPreferences.edit().putString("holdSpeedMode", str).remove("holdSpeed").apply();
        return str;
    }

    public static int d(Context context) {
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        String string = defaultSharedPreferences.getString("keepAwakeMinutes", null);
        if (string != null) {
            return Integer.parseInt(string);
        }
        int i = (yt2.F(context) && defaultSharedPreferences.getBoolean("keepAwakeOnPause", true)) ? 120 : 0;
        defaultSharedPreferences.edit().putString("keepAwakeMinutes", String.valueOf(i)).remove("keepAwakeOnPause").apply();
        return i;
    }

    public static String e(Context context) {
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        String string = defaultSharedPreferences.getString("languageAudio", null);
        if (string != null && !"default".equals(string) && !"device".equals(string)) {
            return string;
        }
        String strJoin = "default".equals(string) ? "" : TextUtils.join(",", yt2.v());
        defaultSharedPreferences.edit().putString("languageAudio", strJoin).apply();
        return strJoin;
    }

    public static String f(Context context) {
        String strD;
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        String string = defaultSharedPreferences.getString("languageSubtitle", null);
        if (string != null) {
            return string;
        }
        CaptioningManager captioningManager = (CaptioningManager) context.getSystemService("captioning");
        Locale locale = captioningManager != null ? captioningManager.getLocale() : null;
        if (locale == null) {
            strD = "";
        } else {
            String language = locale.getLanguage();
            String[] strArr = yt2.a;
            xp2 xp2Var = xp2.f;
            strD = ha1.D(language);
        }
        String str = strD != null ? strD : "";
        defaultSharedPreferences.edit().putString("languageSubtitle", str).apply();
        return str;
    }

    public static int g(Context context) {
        String string = PreferenceManager.getDefaultSharedPreferences(context).getString("themeMode", "system");
        string.getClass();
        if (string.equals("dark")) {
            return 2;
        }
        return !string.equals("light") ? -100 : 1;
    }

    public static ArrayList i(Context context) {
        ArrayList arrayList = new ArrayList();
        for (String str : PreferenceManager.getDefaultSharedPreferences(context).getString("rememberedDubs", "").split("\n")) {
            int iIndexOf = str.indexOf(9);
            if (iIndexOf > 0 && iIndexOf < str.length() - 1) {
                arrayList.add(new String[]{str.substring(0, iIndexOf), str.substring(iIndexOf + 1)});
            }
        }
        return arrayList;
    }

    public static String j(Context context) {
        String str;
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        String string = defaultSharedPreferences.getString("subtitleSearchMode", null);
        if (string != null) {
            return string;
        }
        if (defaultSharedPreferences.getBoolean("subtitleSearch", false)) {
            str = defaultSharedPreferences.getBoolean("subtitleSearchStrict", false) ? "none" : "first";
        } else {
            str = "off";
        }
        defaultSharedPreferences.edit().putString("subtitleSearchMode", str).remove("subtitleSearch").remove("subtitleSearchStrict").apply();
        return str;
    }

    public static boolean k(Context context) {
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        boolean z = true;
        if (defaultSharedPreferences.contains("subtitleTranslateOn")) {
            return defaultSharedPreferences.getBoolean("subtitleTranslateOn", true);
        }
        Object obj = defaultSharedPreferences.getAll().get("subtitleTranslateMode");
        if (!(obj instanceof String)) {
            z = defaultSharedPreferences.getBoolean("subtitleTranslate", true);
        } else if ("off".equals(obj)) {
            z = false;
        }
        defaultSharedPreferences.edit().putBoolean("subtitleTranslateOn", z).remove("subtitleTranslateMode").remove("subtitleTranslate").apply();
        return z;
    }

    public static boolean l(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean("amoledBlack", false);
    }

    public static boolean m(Context context) {
        String string = PreferenceManager.getDefaultSharedPreferences(context).getString("themeMode", "system");
        if ("light".equals(string)) {
            return true;
        }
        return ("dark".equals(string) || yt2.F(context) || (context.getResources().getConfiguration().uiMode & 48) == 32) ? false : true;
    }

    public static yj2 n(Context context, zp2 zp2Var) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("dubs", 0);
        yj2 yj2Var = new yj2(zp2Var, sharedPreferences.getString("habit", ""), sharedPreferences.getString("titles", ""));
        ArrayList arrayListI = i(context);
        if (!arrayListI.isEmpty()) {
            for (int i = 0; i < arrayListI.size() && i < 40; i++) {
                ((ArrayList) yj2Var.n).add(new eb0(((String[]) arrayListI.get(i))[0], ((String[]) arrayListI.get(i))[1], Math.max(0.1d, 2.0d - (((double) i) * 0.1d)), null, 0.0d));
            }
            t(context, yj2Var);
            PreferenceManager.getDefaultSharedPreferences(context).edit().remove("rememberedDubs").apply();
        }
        return yj2Var;
    }

    public static String q(SharedPreferences sharedPreferences) {
        String string = sharedPreferences.getString("skipCountdown", "ring");
        String str = "off".equals(string) ? "off" : "ring";
        if (!str.equals(string)) {
            sharedPreferences.edit().putString("skipCountdown", str).apply();
        }
        return str;
    }

    public static Map r(Context context, String str) {
        try {
            FileInputStream fileInputStreamOpenFileInput = context.openFileInput(str);
            try {
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStreamOpenFileInput);
                try {
                    LinkedHashMap linkedHashMap = (LinkedHashMap) objectInputStream.readObject();
                    objectInputStream.close();
                    if (fileInputStreamOpenFileInput != null) {
                        fileInputStreamOpenFileInput.close();
                    }
                    return linkedHashMap;
                } catch (Throwable th) {
                    try {
                        objectInputStream.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                if (fileInputStreamOpenFileInput != null) {
                    try {
                        fileInputStreamOpenFileInput.close();
                    } catch (Throwable th4) {
                        th3.addSuppressed(th4);
                    }
                }
                throw th3;
            }
        } catch (Exception unused) {
            return Collections.EMPTY_MAP;
        }
    }

    public static void s(Context context, String str, Uri uri, long j) {
        LinkedHashMap linkedHashMap;
        if (uri == null) {
            return;
        }
        try {
            FileInputStream fileInputStreamOpenFileInput = context.openFileInput(str);
            try {
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStreamOpenFileInput);
                try {
                    linkedHashMap = (LinkedHashMap) objectInputStream.readObject();
                    objectInputStream.close();
                    if (fileInputStreamOpenFileInput != null) {
                        fileInputStreamOpenFileInput.close();
                    }
                } catch (Throwable th) {
                    try {
                        objectInputStream.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                if (fileInputStreamOpenFileInput != null) {
                    try {
                        fileInputStreamOpenFileInput.close();
                    } catch (Throwable th4) {
                        th3.addSuppressed(th4);
                    }
                }
                throw th3;
            }
        } catch (Exception unused) {
            linkedHashMap = new LinkedHashMap(10);
        }
        Long l = (Long) linkedHashMap.get(uri.toString());
        if (l == null || l.longValue() != j) {
            while (linkedHashMap.size() > 100) {
                linkedHashMap.remove(linkedHashMap.keySet().toArray()[0]);
            }
            linkedHashMap.put(uri.toString(), Long.valueOf(j));
            try {
                FileOutputStream fileOutputStreamOpenFileOutput = context.openFileOutput(str, 0);
                try {
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStreamOpenFileOutput);
                    try {
                        objectOutputStream.writeObject(linkedHashMap);
                        objectOutputStream.close();
                        if (fileOutputStreamOpenFileOutput != null) {
                            fileOutputStreamOpenFileOutput.close();
                        }
                    } catch (Throwable th5) {
                        try {
                            objectOutputStream.close();
                        } catch (Throwable th6) {
                            th5.addSuppressed(th6);
                        }
                        throw th5;
                    }
                } catch (Throwable th7) {
                    if (fileOutputStreamOpenFileOutput != null) {
                        try {
                            fileOutputStreamOpenFileOutput.close();
                        } catch (Throwable th8) {
                            th7.addSuppressed(th8);
                        }
                    }
                    throw th7;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void t(Context context, yj2 yj2Var) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences("dubs", 0).edit();
        StringBuilder sb = new StringBuilder();
        for (eb0 eb0Var : (ArrayList) yj2Var.n) {
            sb.append(eb0Var.a);
            sb.append('\t');
            sb.append(yj2.B(eb0Var.b));
            sb.append('\t');
            sb.append(eb0Var.c);
            sb.append('\t');
            String str = eb0Var.d;
            sb.append(str == null ? "" : yj2.B(str));
            sb.append('\t');
            sb.append(eb0Var.e);
            sb.append('\n');
        }
        SharedPreferences.Editor editorPutString = editorEdit.putString("habit", sb.toString());
        StringBuilder sb2 = new StringBuilder();
        for (Map.Entry entry : ((LinkedHashMap) yj2Var.o).entrySet()) {
            sb2.append(yj2.B((String) entry.getKey()));
            sb2.append('\t');
            sb2.append(((String[]) entry.getValue())[0]);
            sb2.append('\t');
            sb2.append(yj2.B(((String[]) entry.getValue())[1]));
            sb2.append('\n');
        }
        editorPutString.putString("titles", sb2.toString()).apply();
    }

    public final void A(Uri uri, long j) {
        LinkedHashMap linkedHashMap;
        Activity activity = this.a;
        if (uri == null) {
            return;
        }
        if (!this.S0) {
            if (uri.equals(this.c)) {
                this.T0 = j;
                return;
            }
            return;
        }
        while (true) {
            int size = this.R0.size();
            linkedHashMap = this.R0;
            if (size <= 100) {
                break;
            } else {
                linkedHashMap.remove(linkedHashMap.keySet().toArray()[0]);
            }
        }
        linkedHashMap.put(uri.toString(), Long.valueOf(j));
        try {
            FileOutputStream fileOutputStreamOpenFileOutput = activity.openFileOutput("positions", 0);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStreamOpenFileOutput);
            objectOutputStream.writeObject(this.R0);
            objectOutputStream.close();
            fileOutputStreamOpenFileOutput.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (np2.e(uri)) {
            new Thread(new gu1(activity.getApplicationContext(), uri, j, (byte) 0), "torr-viewed").start();
        }
    }

    public final void B(Uri uri) {
        this.f = uri;
        if (this.S0) {
            SharedPreferences.Editor editorEdit = this.b.edit();
            if (uri == null) {
                editorEdit.remove("subtitleSecondaryUri");
            } else {
                editorEdit.putString("subtitleSecondaryUri", uri.toString());
            }
            editorEdit.apply();
        }
    }

    public final void C(Uri uri) {
        this.e = uri;
        this.n = null;
        if (this.S0) {
            SharedPreferences.Editor editorEdit = this.b.edit();
            if (uri == null) {
                editorEdit.remove("subtitleUri");
            } else {
                editorEdit.putString("subtitleUri", uri.toString());
            }
            editorEdit.remove("subtitleTrackId");
            editorEdit.apply();
        }
    }

    public final long h(Uri uri) {
        if (!this.S0) {
            if (uri == null || !uri.equals(this.c)) {
                return 0L;
            }
            return this.T0;
        }
        Object obj = this.R0.get(uri.toString());
        if (obj != null) {
            return ((Long) obj).longValue();
        }
        String strB = b(uri);
        if (strB == null) {
            return 0L;
        }
        Object[] array = this.R0.keySet().toArray();
        for (int length = array.length; length > 0; length--) {
            String str = (String) array[length - 1];
            if (strB.equals(b(Uri.parse(str)))) {
                return ((Long) this.R0.get(str)).longValue();
            }
        }
        return 0L;
    }

    public final void o() {
        try {
            FileInputStream fileInputStreamOpenFileInput = this.a.openFileInput("positions");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStreamOpenFileInput);
            this.R0 = (LinkedHashMap) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStreamOpenFileInput.close();
        } catch (Exception e) {
            e.printStackTrace();
            this.R0 = new LinkedHashMap(10);
        }
    }

    public final void p() {
        SharedPreferences sharedPreferences = this.b;
        if (!sharedPreferences.getBoolean("screenOrientationLandscapeDefault", false)) {
            SharedPreferences.Editor editorPutBoolean = sharedPreferences.edit().putBoolean("screenOrientationLandscapeDefault", true);
            if ("0".equals(sharedPreferences.getString("screenOrientation", null))) {
                editorPutBoolean.putString("screenOrientation", String.valueOf(3));
            }
            editorPutBoolean.apply();
        }
        if (!sharedPreferences.getBoolean("headerInfoDetailedDefault", false)) {
            SharedPreferences.Editor editorPutBoolean2 = sharedPreferences.edit().putBoolean("headerInfoDetailedDefault", true);
            if ("brief".equals(sharedPreferences.getString("headerInfo", null))) {
                editorPutBoolean2.putString("headerInfo", "detailed");
            }
            editorPutBoolean2.apply();
        }
        Activity activity = this.a;
        this.U0 = yt2.F(activity) ? 4 : lf2.y(4)[Integer.parseInt(sharedPreferences.getString("screenOrientation", String.valueOf(3)))];
        this.u = sharedPreferences.getBoolean("autoPiP", this.u);
        this.v = sharedPreferences.getBoolean("disableVolumeBrightnessGestures", this.v);
        this.w = c(activity);
        this.x = sharedPreferences.getBoolean("tunneling", this.x);
        this.y = sharedPreferences.getBoolean("frameRateMatching", this.y);
        this.z = Integer.parseInt(sharedPreferences.getString("backBufferMs", String.valueOf(this.z)));
        this.A = sharedPreferences.getString("bufferMode", this.A);
        this.B = Integer.parseInt(sharedPreferences.getString("bufferResumePercent", String.valueOf(this.B)));
        this.C = sharedPreferences.getBoolean("displayResolutionMatching", this.C);
        this.D = sharedPreferences.getBoolean("frameRateCorrection", this.D);
        this.E = sharedPreferences.getBoolean("frameRateDoubling", this.E);
        this.F = Integer.parseInt(sharedPreferences.getString("modeSwitchPauseMs", String.valueOf(this.F)));
        this.G = sharedPreferences.getBoolean("allowSystemFrameRate", !yt2.F(activity));
        this.H = sharedPreferences.getBoolean("repeatToggle", this.H);
        this.I = sharedPreferences.getBoolean("playlistGrid", this.I);
        this.J = sharedPreferences.getBoolean("tvSingleBack", this.J);
        this.K = d(activity);
        this.L = sharedPreferences.getBoolean("askStillWatching", this.L);
        this.M = sharedPreferences.getBoolean("audioPassthrough", this.M);
        this.N = sharedPreferences.getBoolean("audioPassthroughForce", this.N);
        this.O = sharedPreferences.getBoolean("centreBoost", this.O);
        this.P = sharedPreferences.getBoolean("dynamicRange", this.P);
        this.Q = Integer.parseInt(sharedPreferences.getString("decoderPriority", String.valueOf(this.Q)));
        this.R = Integer.parseInt(sharedPreferences.getString("audioSyncMs", String.valueOf(this.R)));
        this.S = Integer.parseInt(sharedPreferences.getString("audioPassthroughSyncMs", String.valueOf(this.S)));
        this.T = sharedPreferences.getBoolean("mapDV7ToHevc", this.T);
        this.U = sharedPreferences.getBoolean("removeHdr10Plus", this.U);
        this.V = sharedPreferences.getBoolean("refuseDolbyVision", this.V);
        this.W = e(activity);
        this.X = f(activity);
        this.Y = PreferenceManager.getDefaultSharedPreferences(activity).getString("languageSubtitleSecondary", "");
        String strJ = j(activity);
        this.Z = !"off".equals(strJ);
        this.a0 = "none".equals(strJ);
        this.b0 = sharedPreferences.getBoolean("subtitleSearchLanguage", this.b0);
        this.c0 = k(activity);
        this.d0 = cl2.e("mozhi,google");
        this.e0 = sharedPreferences.getBoolean("subtitleStyleBold", this.e0);
        this.f0 = Float.parseFloat(sharedPreferences.getString("subtitleScale", String.valueOf(this.f0)));
        this.g0 = Color.parseColor(sharedPreferences.getString("subtitleTextColor", "#FFFFFFFF"));
        this.h0 = Color.parseColor(sharedPreferences.getString("subtitleBackground", "#00000000"));
        this.j0 = sharedPreferences.getString("subtitleSecondaryMode", this.j0);
        this.m0 = Float.parseFloat(sharedPreferences.getString("subtitleSecondaryScale", String.valueOf(this.m0)));
        this.k0 = Color.parseColor(sharedPreferences.getString("subtitleSecondaryTextColor", "#FFCCCCCC"));
        this.l0 = Color.parseColor(sharedPreferences.getString("subtitleSecondaryBackground", "#80000000"));
        this.i0 = Integer.parseInt(sharedPreferences.getString("subtitleEdge", String.valueOf(this.i0)));
        this.n0 = sharedPreferences.getBoolean("skipEnabled", this.n0);
        this.o0 = sharedPreferences.getString("skipMode", this.o0);
        this.p0 = sharedPreferences.getString("skipModeCredits", this.p0);
        this.s0 = sharedPreferences.getBoolean("skipFetchOnline", this.s0);
        this.t0 = sharedPreferences.getString("skipUndo", this.t0);
        this.q0 = sharedPreferences.getBoolean("skipHideWhenLocked", this.q0);
        this.r0 = q(sharedPreferences);
        this.u0 = sharedPreferences.getBoolean("showClock", this.u0);
        this.v0 = sharedPreferences.getBoolean("timeRemaining", this.v0);
        this.w0 = sharedPreferences.getBoolean("showStats", this.w0);
        this.x0 = sharedPreferences.getBoolean("askPreferAudio", this.x0);
        this.y0 = sharedPreferences.getBoolean("showTransfer", this.y0);
        this.z0 = sharedPreferences.getString("headerArt", this.z0);
        this.A0 = sharedPreferences.getString("headerInfo", this.A0);
        this.B0 = sharedPreferences.getString("resumeMode", this.B0);
        this.C0 = sharedPreferences.getBoolean("roundValueButtons", this.C0);
        this.D0 = sharedPreferences.getString("loadingScreenMode", this.D0);
        this.E0 = yt2.F(activity) || sharedPreferences.getBoolean("systemVolume", this.E0);
        this.G0 = sharedPreferences.getString("togetherPassword", this.G0);
        this.H0 = sharedPreferences.getBoolean("togetherPublic", this.H0);
        this.I0 = sharedPreferences.getString("togetherRelay", this.I0);
        this.J0 = sharedPreferences.getString("togetherInvitePage", this.J0);
        String string = sharedPreferences.getString("togetherNick", "");
        this.F0 = string;
        if (string.isEmpty()) {
            this.F0 = l5.a();
            sharedPreferences.edit().putString("togetherNick", this.F0).apply();
        }
        this.K0 = sharedPreferences.getBoolean("crashReporting", this.K0);
        this.L0 = sharedPreferences.getBoolean("maskReports", this.L0);
        this.M0 = sharedPreferences.getBoolean("autoUpdate", this.M0);
        Set<String> stringSet = sharedPreferences.getStringSet("revokedAudioMimes", Collections.EMPTY_SET);
        this.Q0 = stringSet;
        if (stringSet.contains("audio/raw")) {
            HashSet hashSet = new HashSet(this.Q0);
            hashSet.remove("audio/raw");
            this.Q0 = hashSet;
            sharedPreferences.edit().putStringSet("revokedAudioMimes", hashSet).apply();
        }
    }

    public final void u(l72 l72Var) {
        String string;
        this.P0 = l72Var;
        SharedPreferences.Editor editorEdit = this.b.edit();
        if (l72Var == null) {
            editorEdit.remove("updatePending");
        } else {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("versionCode", l72Var.a);
                jSONObject.put("tagName", (String) l72Var.c);
                jSONObject.put("versionName", (String) l72Var.d);
                jSONObject.put("changelog", (String) l72Var.e);
                jSONObject.put("apkUrl", (String) l72Var.f);
                jSONObject.put("size", l72Var.b);
                jSONObject.put("publishedAt", (String) l72Var.g);
                string = jSONObject.toString();
            } catch (JSONException unused) {
                string = null;
            }
            editorEdit.putString("updatePending", string);
        }
        editorEdit.apply();
    }

    public final HashMap v() {
        HashMap map = new HashMap(this.b.getAll());
        map.remove("togetherNick");
        map.remove("togetherPassword");
        map.remove("togetherPublic");
        map.remove("togetherRelay");
        map.remove("themeMode");
        map.remove("amoledBlack");
        map.remove("roundValueButtons");
        map.remove("headerInfo");
        map.remove("resumeMode");
        map.keySet().removeAll(V0);
        Iterator it = map.keySet().iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            for (String str2 : W0) {
                if (str.startsWith(str2)) {
                    it.remove();
                    break;
                }
            }
        }
        return map;
    }

    public final void w(float f, float f2, int i) {
        this.i = i;
        this.j = f;
        this.k = f2;
        if (!this.S0 || this.l < 0) {
            return;
        }
        this.b.edit().putInt("resizeMode_" + this.l, i).putFloat("scale_" + this.l, f).putFloat("aspectRatio_" + this.l, f2).apply();
    }

    public final void x(PlayerActivity playerActivity, Uri uri, String str) {
        Uri uri2;
        this.c = uri;
        this.h = str;
        this.d = false;
        C(null);
        B(null);
        this.l = -1;
        y(null, null, 0, 1.0f, 0.0f, 1.0f);
        this.T0 = -1L;
        String str2 = this.h;
        if (str2 != null && str2.endsWith("/*")) {
            this.h = null;
        }
        if (this.h == null && (uri2 = this.c) != null && "content".equals(uri2.getScheme())) {
            this.h = playerActivity.getContentResolver().getType(this.c);
        }
        if (this.S0) {
            SharedPreferences.Editor editorEdit = this.b.edit();
            Uri uri3 = this.c;
            if (uri3 == null) {
                editorEdit.remove("mediaUri");
            } else {
                editorEdit.putString("mediaUri", uri3.toString());
            }
            String str3 = this.h;
            if (str3 == null) {
                editorEdit.remove("mediaType");
            } else {
                editorEdit.putString("mediaType", str3);
            }
            editorEdit.apply();
        }
    }

    public final void y(String str, String str2, int i, float f, float f2, float f3) {
        this.o = str;
        this.n = str2;
        w(f, f2, i);
        this.m = f3;
        if (this.S0) {
            SharedPreferences.Editor editorEdit = this.b.edit();
            if (str == null) {
                editorEdit.remove("audioTrackId");
            } else {
                editorEdit.putString("audioTrackId", str);
            }
            if (str2 == null) {
                editorEdit.remove("subtitleTrackId");
            } else {
                editorEdit.putString("subtitleTrackId", str2);
            }
            editorEdit.putFloat("speed", f3);
            editorEdit.apply();
        }
    }

    public final void z(long j) {
        A(this.c, j);
    }
}
