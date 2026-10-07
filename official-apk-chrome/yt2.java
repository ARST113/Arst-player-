package defpackage;

import android.app.Activity;
import android.app.Dialog;
import android.app.UiModeManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.content.pm.SigningInfo;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.InsetDrawable;
import android.media.AudioManager;
import android.media.MediaScannerConnection;
import android.media.audiofx.LoudnessEnhancer;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.LocaleList;
import android.os.SystemClock;
import android.os.storage.StorageManager;
import android.os.storage.StorageVolume;
import android.provider.Settings;
import android.text.Html;
import android.view.ContextThemeWrapper;
import android.view.Display;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowInsets;
import android.view.WindowInsetsController;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.window.OnBackInvokedDispatcher;
import androidx.media3.decoder.VideoDecoderOutputBuffer;
import com.brouken.player.PlayerActivity;
import com.brouken.player.SettingsActivity;
import com.bumptech.glide.a;
import com.google.android.material.button.MaterialButton;
import com.justplus.player.R;
import io.sentry.w3;
import j$.util.Objects;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.text.Collator;
import java.text.DecimalFormatSymbols;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/* JADX INFO: compiled from: r8-map-id-0cee80150bab987b3edc91bcfc1e04786a1418881e5c92a3b999d44d61001263 */
/* JADX INFO: loaded from: classes.dex */
public abstract class yt2 {
    public static String f;
    public static int g;
    public static final String[] a = {"3gp", "avi", "m4v", "mkv", "mov", "mp4", "ts", "webm"};
    public static final String[] b = {"srt", "ssa", "ass", "vtt", "ttml", "dfxp", "xml"};
    public static final String[] c = {"application/x-subrip", "text/x-ssa", "text/vtt", "application/ttml+xml", "text/*", "application/octet-stream"};
    public static final ArrayDeque d = new ArrayDeque();
    public static final long e = SystemClock.elapsedRealtime();
    public static final Pattern h = Pattern.compile("((?:https?|rtsps?)://[^\\s?#]+)[?#][^\\s,)}\\]\"']*");
    public static volatile byte[][] i = null;
    public static volatile byte[][] j = null;
    public static volatile String k = null;
    public static volatile String l = null;
    public static volatile int m = 0;
    public static volatile int n = 0;
    public static volatile boolean o = false;
    public static volatile int p = 0;
    public static volatile int q = 0;
    public static volatile boolean r = false;
    public static volatile boolean s = false;
    public static volatile boolean t = false;
    public static volatile boolean u = false;
    public static volatile long v = 0;
    public static final char[] w = {'a', 'b', 'o', 'u', 't', 'D', 'e', 'c', 'o'};
    public static final char[] x = {'p', 'r', 'e', 'f', 'e', 'r', 'e', 'n', 'c', 'e', '_', 'a', 'b', 'o', 'u', 't', '_', 'd', 'e', 'c', 'o'};

    public static boolean A(Uri uri) {
        return E(uri) || gj1.f(uri);
    }

    public static boolean B(Context context) {
        return Build.VERSION.SDK_INT >= 26 && context.getPackageManager().hasSystemFeature("android.software.picture_in_picture");
    }

    public static boolean C(Context context) {
        return Settings.Global.getFloat(context.getContentResolver(), "animator_duration_scale", 1.0f) == 0.0f;
    }

    public static boolean D(zl0 zl0Var) {
        int i2 = zl0Var.C;
        return i2 == 90 || i2 == 270;
    }

    public static boolean E(Uri uri) {
        String scheme;
        if (uri == null || (scheme = uri.getScheme()) == null) {
            return false;
        }
        return scheme.startsWith("http") || scheme.equals("rtsp");
    }

    public static boolean F(Context context) {
        PackageManager packageManager = context.getPackageManager();
        if (((UiModeManager) context.getSystemService("uimode")).getCurrentModeType() == 4 || packageManager.hasSystemFeature("android.software.leanback") || packageManager.hasSystemFeature("amazon.hardware.fire_tv")) {
            return true;
        }
        Intent intent = new Intent("android.intent.action.OPEN_DOCUMENT");
        intent.addCategory("android.intent.category.OPENABLE");
        intent.setType("video/*");
        if (intent.resolveActivity(packageManager) == null) {
            return true;
        }
        if (Build.VERSION.SDK_INT < 30) {
            return !(packageManager.hasSystemFeature("android.hardware.touchscreen") || packageManager.hasSystemFeature("android.hardware.telephony")) || packageManager.hasSystemFeature("android.hardware.hdmi.cec") || Build.MANUFACTURER.equalsIgnoreCase("zidoo");
        }
        return false;
    }

    public static void G(Dialog dialog, ViewGroup viewGroup) {
        s2.n(dialog);
        if (Build.VERSION.SDK_INT >= 30) {
            View view = (View) viewGroup.getParent();
            final int paddingBottom = view.getPaddingBottom();
            view.setOnApplyWindowInsetsListener(new View.OnApplyWindowInsetsListener() { // from class: vt2
                @Override // android.view.View.OnApplyWindowInsetsListener
                public final WindowInsets onApplyWindowInsets(View view2, WindowInsets windowInsets) {
                    view2.setPadding(view2.getPaddingLeft(), view2.getPaddingTop(), view2.getPaddingRight(), paddingBottom + windowInsets.getInsets(WindowInsets.Type.ime()).bottom);
                    return windowInsets;
                }
            });
        }
        Window window = dialog.getWindow();
        if (window != null) {
            window.setSoftInputMode(20);
        }
    }

    public static String I(String str, List list) {
        if (str != null && !str.isEmpty() && !list.isEmpty()) {
            int size = list.size();
            for (String str2 : str.split("[^A-Za-z]+")) {
                if (str2.length() == 3) {
                    xp2 xp2Var = xp2.f;
                    String strD = ha1.D(str2);
                    int iIndexOf = strD == null ? -1 : list.indexOf(strD);
                    if (iIndexOf >= 0 && iIndexOf < size) {
                        size = iIndexOf;
                    }
                }
            }
            if (size < list.size()) {
                return (String) list.get(size);
            }
        }
        return null;
    }

    public static boolean J(String str, int[] iArr) {
        if (str != null && iArr.length == str.length()) {
            for (int i2 = 0; i2 < iArr.length; i2++) {
                if (((char) (iArr[i2] ^ Integer.rotateLeft(1035275825, (i2 * 3) & 31))) == str.charAt(i2)) {
                }
            }
            return true;
        }
        return false;
    }

    public static void K(String str) {
        w3.b().B(str);
        ArrayDeque arrayDeque = d;
        synchronized (arrayDeque) {
            try {
                if (str.equals(f)) {
                    g++;
                    arrayDeque.removeLast();
                    arrayDeque.addLast(String.format(Locale.US, "%6.2f %s", Float.valueOf((SystemClock.elapsedRealtime() - e) / 1000.0f), str) + " (x" + (g + 1) + ")");
                    return;
                }
                f = str;
                g = 0;
                while (true) {
                    ArrayDeque arrayDeque2 = d;
                    if (arrayDeque2.size() < 500) {
                        arrayDeque2.addLast(String.format(Locale.US, "%6.2f %s", Float.valueOf((SystemClock.elapsedRealtime() - e) / 1000.0f), str));
                        return;
                    }
                    arrayDeque2.removeFirst();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public static String L(Display.Mode mode) {
        return mode.getPhysicalWidth() + "x" + mode.getPhysicalHeight() + "@" + mode.getRefreshRate();
    }

    public static Display.Mode M(ArrayList arrayList, float f2, int i2) {
        Iterator it = arrayList.iterator();
        Display.Mode mode = null;
        int i3 = 0;
        while (it.hasNext()) {
            Display.Mode mode2 = (Display.Mode) it.next();
            float refreshRate = mode2.getRefreshRate() / f2;
            int iRound = Math.round(refreshRate);
            if (iRound >= 1) {
                float f3 = iRound;
                if (Math.abs(refreshRate - f3) < f3 * 2.0E-4f) {
                    int iAbs = Math.abs(iRound - i2);
                    if (mode == null || iAbs < i3 || (iAbs == i3 && ((int) (mode2.getRefreshRate() * 100.0f)) < ((int) (mode.getRefreshRate() * 100.0f)))) {
                        mode = mode2;
                        i3 = iAbs;
                    }
                }
            }
        }
        return mode;
    }

    public static String N(String str, String str2) {
        String str3;
        if (str2.contains("[Script Info]")) {
            str3 = ".ass";
        } else {
            if (!str2.contains("WEBVTT")) {
                if (str2.contains("<tt ") || str2.contains("<tt\n") || str2.contains("<?xml")) {
                    str3 = ".ttml";
                }
                return str;
            }
            str3 = ".vtt";
        }
        if (!str.endsWith(str3)) {
            int iLastIndexOf = str.lastIndexOf(46);
            if (iLastIndexOf > 0) {
                str = str.substring(0, iLastIndexOf);
            }
            return str.concat(str3);
        }
        return str;
    }

    public static byte[][] O(Context context) throws PackageManager.NameNotFoundException {
        Signature[] signatureArr;
        SigningInfo signingInfo;
        Signature[] apkContentsSigners;
        PackageManager packageManager = context.getPackageManager();
        String packageName = context.getPackageName();
        int i2 = 0;
        if (Build.VERSION.SDK_INT < 28) {
            PackageInfo packageInfo = packageManager.getPackageInfo(packageName, 64);
            if (packageInfo == null || (signatureArr = packageInfo.signatures) == null) {
                return null;
            }
            int length = signatureArr.length;
            byte[][] bArr = new byte[length][];
            while (i2 < length) {
                bArr[i2] = packageInfo.signatures[i2].toByteArray();
                i2++;
            }
            return bArr;
        }
        PackageInfo packageInfo2 = packageManager.getPackageInfo(packageName, 134217728);
        if (packageInfo2 == null || (signingInfo = packageInfo2.signingInfo) == null || (apkContentsSigners = signingInfo.getApkContentsSigners()) == null) {
            return null;
        }
        int length2 = apkContentsSigners.length;
        byte[][] bArr2 = new byte[length2][];
        while (i2 < length2) {
            bArr2[i2] = apkContentsSigners[i2].toByteArray();
            i2++;
        }
        return bArr2;
    }

    public static void P(Dialog dialog, final Runnable runnable) {
        if (Build.VERSION.SDK_INT < 33) {
            dialog.setOnKeyListener(new DialogInterface.OnKeyListener() { // from class: rt2
                @Override // android.content.DialogInterface.OnKeyListener
                public final boolean onKey(DialogInterface dialogInterface, int i2, KeyEvent keyEvent) {
                    if (i2 != 4 || keyEvent.getAction() != 1) {
                        return false;
                    }
                    runnable.run();
                    return true;
                }
            });
            return;
        }
        OnBackInvokedDispatcher onBackInvokedDispatcher = dialog.getOnBackInvokedDispatcher();
        Objects.requireNonNull(runnable);
        onBackInvokedDispatcher.registerOnBackInvokedCallback(0, new uo2(runnable, (byte) 1));
    }

    public static boolean Q(Context context) {
        return Build.VERSION.SDK_INT < 30 || context.getApplicationInfo().targetSdkVersion <= 29;
    }

    public static MaterialButton R(ContextThemeWrapper contextThemeWrapper, ss2 ss2Var, CharSequence charSequence) {
        MaterialButton materialButton = new MaterialButton(contextThemeWrapper, null, R.attr.materialButtonOutlinedStyle);
        materialButton.setShapeAppearanceModel(hc2.g(contextThemeWrapper, R.style.ShapeAppearance_JustPlus_Segment, 0).a());
        materialButton.setId(View.generateViewId());
        materialButton.setText(charSequence);
        materialButton.setMaxLines(1);
        materialButton.setInsetTop(0);
        materialButton.setInsetBottom(0);
        materialButton.setMinHeight(ss2Var.b(48.0f));
        r(materialButton);
        materialButton.setPadding(ss2Var.b(4.0f), materialButton.getPaddingTop(), ss2Var.b(4.0f), materialButton.getPaddingBottom());
        int iR = ((int) ss2Var.r()) - 4;
        int iR2 = (int) ss2Var.r();
        if (Build.VERSION.SDK_INT >= 27) {
            oc.n(materialButton, iR, iR2);
            return materialButton;
        }
        materialButton.setAutoSizeTextTypeUniformWithConfiguration(iR, iR2, 1, 2);
        return materialButton;
    }

    public static ColorStateList S(int i2) {
        return new ColorStateList(new int[][]{new int[]{android.R.attr.state_focused, -16842919}, new int[0]}, new int[]{0, i2});
    }

    public static FrameLayout T(Context context, int i2, int i3) {
        synchronized (wv2.class) {
            if (!wv2.a) {
                wv2.a = true;
                a.a(context.getApplicationContext()).n.b().i(new e10(context.getApplicationContext(), (byte) 16));
            }
        }
        FrameLayout frameLayout = new FrameLayout(context);
        frameLayout.setBackgroundColor(sj.n(context, R.attr.colorSurfaceContainerHighest, context.getColor(R.color.thumb_box)));
        frameLayout.setClipToOutline(true);
        frameLayout.setOutlineProvider(new r70(i2, (byte) 3));
        ImageView imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setAlpha(0.5f);
        imageView.setVisibility(8);
        frameLayout.addView(imageView, new FrameLayout.LayoutParams(-1, -1));
        ImageView imageView2 = new ImageView(context);
        imageView2.setScaleType(ImageView.ScaleType.FIT_CENTER);
        frameLayout.addView(imageView2, new FrameLayout.LayoutParams(-1, -1));
        ImageView imageView3 = new ImageView(context);
        imageView3.setImageTintList(ColorStateList.valueOf(eu.f(sj.n(context, R.attr.colorOnSurfaceVariant, context.getColor(R.color.ink_secondary)), 92)));
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(i3, i3);
        layoutParams.gravity = 17;
        frameLayout.addView(imageView3, layoutParams);
        View view = new View(context);
        view.setBackgroundColor(Integer.MIN_VALUE);
        view.setVisibility(8);
        frameLayout.addView(view, new FrameLayout.LayoutParams(-1, p(4), 8388691));
        View view2 = new View(context);
        view2.setVisibility(8);
        frameLayout.addView(view2, new FrameLayout.LayoutParams(0, p(4), 8388691));
        return frameLayout;
    }

    public static Bitmap U(int i2, String str) {
        try {
            ni niVarL = ag.l(str, i2, i2, Collections.singletonMap(ce0.n, 2));
            int i3 = niVarL.l;
            int i4 = niVarL.m;
            int[] iArr = new int[i3 * i4];
            for (int i5 = 0; i5 < i4; i5++) {
                int i6 = i5 * i3;
                for (int i7 = 0; i7 < i3; i7++) {
                    int i8 = i6 + i7;
                    boolean z = true;
                    if (((niVarL.o[(i7 / 32) + (niVarL.n * i5)] >>> (i7 & 31)) & 1) == 0) {
                        z = false;
                    }
                    iArr[i8] = z ? -16777216 : -1;
                }
            }
            Bitmap bitmapCreateBitmap = Bitmap.createBitmap(i3, i4, Bitmap.Config.ARGB_8888);
            bitmapCreateBitmap.setPixels(iArr, 0, i3, 0, 0, i3, i4);
            return bitmapCreateBitmap;
        } catch (Exception unused) {
            return null;
        }
    }

    public static String V() {
        ArrayDeque<String> arrayDeque = d;
        synchronized (arrayDeque) {
            try {
                if (arrayDeque.isEmpty()) {
                    return "";
                }
                StringBuilder sb = new StringBuilder();
                for (String str : arrayDeque) {
                    if (sb.length() > 0) {
                        sb.append('\n');
                    }
                    sb.append(str);
                }
                return sb.toString();
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public static String W(Uri uri, boolean z) {
        if (uri == null) {
            return null;
        }
        if (!z) {
            return uri.toString();
        }
        if (E(uri)) {
            return n0(uri);
        }
        return uri.getScheme() + " (local)";
    }

    public static void X(Context context) {
        int i2;
        try {
            Resources resources = context.getResources();
            k = resources.getString(R.string.deco_line_1);
            l = resources.getString(R.string.deco_line_2);
        } catch (Throwable unused) {
        }
        try {
            m = context.getColor(R.color.deco_fill_1);
            n = context.getColor(R.color.deco_fill_2);
            o = true;
        } catch (Throwable unused2) {
        }
        try {
            p = !n(context) ? 1 : 0;
        } catch (Throwable unused3) {
            p = 0;
        }
        try {
            i2 = context.getPackageManager().getActivityInfo(new ComponentName(context, (Class<?>) SettingsActivity.class), 0) != null ? 1 : 0;
        } catch (PackageManager.NameNotFoundException unused4) {
        } catch (Throwable unused5) {
        }
        try {
            q = i2 ^ 1;
        } catch (Throwable unused6) {
            q = 0;
        }
    }

    public static void Y(PlayerActivity playerActivity) {
        List<StorageVolume> storageVolumes = ((StorageManager) playerActivity.getSystemService("storage")).getStorageVolumes();
        ArrayList arrayList = new ArrayList();
        Iterator<StorageVolume> it = storageVolumes.iterator();
        while (it.hasNext()) {
            File directory = it.next().getDirectory();
            if (directory != null) {
                arrayList.add(directory.getAbsolutePath());
            }
        }
        MediaScannerConnection.scanFile(playerActivity, (String[]) arrayList.toArray(new String[0]), new String[]{"*/*"}, null);
    }

    public static byte[][] Z(Context context) {
        ZipFile zipFile;
        InputStream inputStream;
        try {
            zipFile = new ZipFile(context.getApplicationInfo().sourceDir);
            try {
                CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
                ArrayList arrayList = new ArrayList();
                Enumeration<? extends ZipEntry> enumerationEntries = zipFile.entries();
                while (enumerationEntries.hasMoreElements()) {
                    ZipEntry zipEntryNextElement = enumerationEntries.nextElement();
                    String upperCase = zipEntryNextElement.getName().toUpperCase(Locale.US);
                    if (upperCase.startsWith("META-INF/") && (upperCase.endsWith(".RSA") || upperCase.endsWith(".DSA") || upperCase.endsWith(".EC"))) {
                        try {
                            inputStream = zipFile.getInputStream(zipEntryNextElement);
                            try {
                                Iterator<? extends Certificate> it = certificateFactory.generateCertificates(inputStream).iterator();
                                while (it.hasNext()) {
                                    arrayList.add(it.next().getEncoded());
                                }
                                if (inputStream != null) {
                                    inputStream.close();
                                }
                            } catch (Throwable th) {
                                th = th;
                                if (inputStream != null) {
                                    try {
                                        inputStream.close();
                                    } catch (Throwable unused) {
                                    }
                                }
                                throw th;
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            inputStream = null;
                        }
                    }
                }
                byte[][] bArrO = o((byte[][]) arrayList.toArray(new byte[0][]));
                try {
                    zipFile.close();
                } catch (Throwable unused2) {
                }
                return bArrO;
            } catch (Throwable unused3) {
                if (zipFile != null) {
                    try {
                        zipFile.close();
                    } catch (Throwable unused4) {
                    }
                }
                return null;
            }
        } catch (Throwable unused5) {
            zipFile = null;
        }
    }

    /* JADX WARN: Code duplicated, block: B:41:0x0078  */
    /* JADX WARN: Code duplicated, block: B:43:0x0080 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:44:0x0082  */
    /* JADX WARN: Code duplicated, block: B:45:0x0084  */
    /* JADX WARN: Code duplicated, block: B:50:0x0098  */
    /* JADX WARN: Code duplicated, block: B:53:0x009e  */
    /* JADX WARN: Code duplicated, block: B:55:0x00a4  */
    /* JADX WARN: Code duplicated, block: B:59:0x00af  */
    /* JADX WARN: Code duplicated, block: B:60:0x00b5  */
    /* JADX WARN: Code duplicated, block: B:63:0x00c7  */
    /* JADX WARN: Code duplicated, block: B:66:0x00d7  */
    public static void a(PlayerActivity playerActivity, AudioManager audioManager, cz czVar, boolean z, boolean z2) {
        boolean z3;
        int iX;
        float fMax;
        vg0 vg0Var;
        int i2;
        int i3;
        czVar.removeCallbacks(czVar.C0);
        if (PlayerActivity.m6 == null) {
            try {
                LoudnessEnhancer loudnessEnhancer = PlayerActivity.l6;
                if (loudnessEnhancer == null || !loudnessEnhancer.hasControl()) {
                    z2 = false;
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        if (PlayerActivity.P6) {
            iX = x(playerActivity, false, audioManager);
            z3 = iX == x(playerActivity, true, audioManager);
        } else {
            z3 = PlayerActivity.Q6 >= 99.5f;
            iX = 0;
        }
        if (!z3) {
            PlayerActivity.N6 = 0.0f;
        }
        if (z3) {
            float f2 = PlayerActivity.N6;
            if (f2 != 0.0f || z) {
                if (z2 && z && f2 < 10.0f) {
                    PlayerActivity.N6 = Math.min(10.0f, ((float) Math.floor(f2)) + 1.0f);
                } else if (!z && f2 > 0.0f) {
                    PlayerActivity.N6 = Math.max(0.0f, ((float) Math.ceil(f2)) - 1.0f);
                }
                c();
            } else {
                c();
                if (PlayerActivity.P6) {
                    if (z) {
                        i2 = 1;
                    } else {
                        i2 = -1;
                    }
                    audioManager.adjustStreamVolume(3, i2, 8);
                    int iX2 = x(playerActivity, false, audioManager);
                    if (z || iX != iX2) {
                        czVar.o0 = 0;
                        i3 = 0;
                    } else {
                        i3 = czVar.o0 + 1;
                        czVar.o0 = i3;
                    }
                    if (i3 > 4) {
                        if (audioManager.getStreamVolume(3) != (Build.VERSION.SDK_INT >= 28 ? audioManager.getStreamMinVolume(3) : 0)) {
                            audioManager.adjustStreamVolume(3, 1, 9);
                        }
                    }
                } else {
                    fMax = 100.0f / Math.max(1, audioManager.getStreamMaxVolume(3));
                    float f3 = PlayerActivity.Q6;
                    if (!z) {
                        fMax = -fMax;
                    }
                    PlayerActivity.Q6 = Math.max(0.0f, Math.min(100.0f, f3 + fMax));
                    vg0Var = PlayerActivity.n6;
                    if (vg0Var != null) {
                        vg0Var.f(PlayerActivity.Q6 / 100.0f);
                    }
                }
            }
        } else {
            c();
            if (PlayerActivity.P6) {
                if (z) {
                    i2 = 1;
                } else {
                    i2 = -1;
                }
                audioManager.adjustStreamVolume(3, i2, 8);
                int iX3 = x(playerActivity, false, audioManager);
                if (z) {
                    czVar.o0 = 0;
                    i3 = 0;
                } else {
                    czVar.o0 = 0;
                    i3 = 0;
                }
                if (i3 > 4) {
                    if (audioManager.getStreamVolume(3) != (Build.VERSION.SDK_INT >= 28 ? audioManager.getStreamMinVolume(3) : 0)) {
                        audioManager.adjustStreamVolume(3, 1, 9);
                    }
                }
            } else {
                fMax = 100.0f / Math.max(1, audioManager.getStreamMaxVolume(3));
                float f4 = PlayerActivity.Q6;
                if (!z) {
                    fMax = -fMax;
                }
                PlayerActivity.Q6 = Math.max(0.0f, Math.min(100.0f, f4 + fMax));
                vg0Var = PlayerActivity.n6;
                if (vg0Var != null) {
                    vg0Var.f(PlayerActivity.Q6 / 100.0f);
                }
            }
        }
        o0(playerActivity);
        czVar.A(y(playerActivity, audioManager));
        czVar.postDelayed(czVar.C0, 800L);
    }

    public static void a0(ContextThemeWrapper contextThemeWrapper, ImageButton imageButton, boolean z) {
        imageButton.setEnabled(z);
        imageButton.setAlpha((z ? contextThemeWrapper.getResources().getInteger(R.integer.exo_media_button_opacity_percentage_enabled) : contextThemeWrapper.getResources().getInteger(R.integer.exo_media_button_opacity_percentage_disabled)) / 100.0f);
    }

    public static LinkedHashMap b() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Locale locale : Locale.getAvailableLocales()) {
            try {
                String iSO3Language = locale.getISO3Language();
                if (!linkedHashMap.containsKey(iSO3Language)) {
                    String displayLanguage = locale.getDisplayLanguage();
                    int iOffsetByCodePoints = displayLanguage.offsetByCodePoints(0, 1);
                    if (!displayLanguage.isEmpty()) {
                        displayLanguage = displayLanguage.substring(0, iOffsetByCodePoints).toUpperCase(locale) + displayLanguage.substring(iOffsetByCodePoints);
                    }
                    linkedHashMap.put(iSO3Language, displayLanguage + " [" + iSO3Language + "]");
                }
            } catch (MissingResourceException e2) {
                e2.printStackTrace();
            }
        }
        Collator collator = Collator.getInstance();
        collator.setStrength(0);
        ea eaVar = new ea(collator, (byte) 1);
        ArrayList<Map.Entry> arrayList = new ArrayList(linkedHashMap.entrySet());
        Collections.sort(arrayList, new r51(eaVar, (byte) 3));
        linkedHashMap.clear();
        for (Map.Entry entry : arrayList) {
            linkedHashMap.put(entry.getKey(), entry.getValue());
        }
        return linkedHashMap;
    }

    public static void b0(PlayerActivity playerActivity, int i2) {
        int iU = lf2.u(i2);
        if (iU != 0) {
            if (iU == 1) {
                playerActivity.setRequestedOrientation(-1);
                return;
            } else if (iU == 2) {
                playerActivity.setRequestedOrientation(4);
                return;
            } else {
                if (iU != 3) {
                    return;
                }
                playerActivity.setRequestedOrientation(6);
                return;
            }
        }
        vg0 vg0Var = PlayerActivity.n6;
        if (vg0Var == null) {
            playerActivity.setRequestedOrientation(6);
            return;
        }
        vg0Var.A1();
        zl0 zl0Var = vg0Var.V;
        if (zl0Var != null) {
            boolean zD = D(zl0Var);
            int i3 = zl0Var.x;
            int i4 = zl0Var.w;
            if (!zD ? i3 > i4 : i4 > i3) {
                playerActivity.setRequestedOrientation(7);
                return;
            }
        }
        playerActivity.setRequestedOrientation(6);
    }

    public static void c() {
        LoudnessEnhancer loudnessEnhancer = PlayerActivity.l6;
        boolean z = false;
        if (loudnessEnhancer != null) {
            try {
                loudnessEnhancer.setTargetGain(Math.round(PlayerActivity.N6 * 200.0f));
                PlayerActivity.l6.setEnabled(PlayerActivity.N6 > 0.0f);
                z = true;
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        ej ejVar = PlayerActivity.m6;
        if (ejVar != null) {
            ejVar.i = z ? 1.0f : 1.0f + (PlayerActivity.N6 * 0.1f);
        }
    }

    public static void c0(View view, int i2, int i3, int i4, int i5) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        if (marginLayoutParams.leftMargin == i2 && marginLayoutParams.topMargin == i3 && marginLayoutParams.rightMargin == i4 && marginLayoutParams.bottomMargin == i5) {
            return;
        }
        marginLayoutParams.setMargins(i2, i3, i4, i5);
        view.setLayoutParams(marginLayoutParams);
    }

    public static boolean d(Context context) {
        try {
            Intent intent = new Intent("android.settings.MANAGE_APP_ALL_FILES_ACCESS_PERMISSION", Uri.parse("package:" + context.getPackageName()));
            if (!(context instanceof Activity)) {
                intent = intent.addFlags(268435456);
            }
            context.startActivity(intent);
            return true;
        } catch (Exception e2) {
            e2.printStackTrace();
            try {
                Intent intent2 = new Intent("android.settings.MANAGE_ALL_FILES_ACCESS_PERMISSION");
                if (!(context instanceof Activity)) {
                    intent2 = intent2.addFlags(268435456);
                }
                context.startActivity(intent2);
                return true;
            } catch (Exception e3) {
                e3.printStackTrace();
                return false;
            }
        }
    }

    public static GradientDrawable d0(int i2, float f2) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(i2);
        gradientDrawable.setCornerRadius(f2);
        return gradientDrawable;
    }

    public static void e(FrameLayout frameLayout, Uri uri, Uri uri2, int i2, int i3) {
        Uri uri3 = uri != null ? uri : uri2;
        StringBuilder sb = new StringBuilder();
        sb.append(uri3 == null ? "" : uri3.toString());
        sb.append("\u0000");
        sb.append(i2);
        String string = sb.toString();
        if (string.equals(frameLayout.getTag())) {
            return;
        }
        frameLayout.setTag(string);
        ImageView imageView = (ImageView) frameLayout.getChildAt(0);
        ImageView imageView2 = (ImageView) frameLayout.getChildAt(1);
        ImageView imageView3 = (ImageView) frameLayout.getChildAt(2);
        ViewGroup.LayoutParams layoutParams = imageView3.getLayoutParams();
        if (layoutParams.width != i3) {
            layoutParams.width = i3;
            layoutParams.height = i3;
            imageView3.setLayoutParams(layoutParams);
        }
        frameLayout.getChildAt(3).setVisibility(8);
        frameLayout.getChildAt(4).setVisibility(8);
        Context context = frameLayout.getContext();
        imageView3.setImageTintList(ColorStateList.valueOf(eu.f(sj.n(context, R.attr.colorOnSurfaceVariant, context.getColor(R.color.ink_secondary)), 92)));
        imageView3.setImageResource(i2);
        imageView3.setVisibility(0);
        imageView2.setVisibility(0);
        s02 s02VarD = a.d(frameLayout.getContext());
        s02VarD.getClass();
        s02VarD.l(new q02(imageView2));
        imageView2.setImageDrawable(null);
        imageView.setImageDrawable(null);
        imageView.setVisibility(8);
        if (uri != null) {
            uri2 = uri;
        } else {
            String scheme = uri2 == null ? null : uri2.getScheme();
            if (!"file".equals(scheme) && !"content".equals(scheme)) {
                uri2 = null;
            }
        }
        if (uri2 == null) {
            imageView2.setVisibility(8);
            return;
        }
        s02 s02VarD2 = a.d(frameLayout.getContext());
        s02VarD2.getClass();
        l02 l02VarZ = new l02(s02VarD2.l, s02VarD2, Bitmap.class, s02VarD2.m).a(s02.v).z(uri2);
        if (uri == null) {
            l02VarZ.getClass();
            l02VarZ = (l02) l02VarZ.l(zu2.d, 1000000L);
        }
        l02VarZ.y(new wt2(imageView2, imageView3, imageView)).x(imageView2);
    }

    public static ArrayList e0(String str) {
        ArrayList arrayList = new ArrayList();
        for (String str2 : str.split(",")) {
            String strTrim = str2.trim();
            if (!strTrim.isEmpty() && !arrayList.contains(strTrim)) {
                arrayList.add(strTrim);
            }
        }
        return arrayList;
    }

    public static int f(byte[][] bArr) {
        byte[] bArr2;
        if (bArr != null && bArr.length != 0) {
            int[] iArr = {3, 5};
            int[] iArr2 = {-634410906, 1921281469};
            for (int i2 = 0; i2 < bArr.length && (bArr2 = bArr[i2]) != null && bArr2.length == 32; i2++) {
                for (int i3 = 0; i3 < 2; i3++) {
                    int i4 = iArr[i3];
                    int i5 = i4 * 4;
                    if ((Integer.rotateLeft(1499182951, (i4 * 3) & 31) ^ iArr2[i3]) != ((bArr2[i5 + 3] & 255) | ((bArr2[i5] & 255) << 24) | ((bArr2[i5 + 1] & 255) << 16) | ((bArr2[i5 + 2] & 255) << 8))) {
                        return 1;
                    }
                }
            }
        }
        return 0;
    }

    public static boolean f0() {
        return (J(k, bq0.l) && J(l, bq0.m)) ? false : true;
    }

    public static int g(byte[][] bArr) {
        byte[] bArr2;
        if (bArr != null && bArr.length != 0) {
            int[] iArr = {6, 7};
            int[] iArr2 = {289883712, 2134563488};
            for (int i2 = 0; i2 < bArr.length && (bArr2 = bArr[i2]) != null && bArr2.length == 32; i2++) {
                for (int i3 = 0; i3 < 2; i3++) {
                    int i4 = iArr[i3];
                    int i5 = i4 * 4;
                    if ((Integer.rotateLeft(-99232239, (i4 * 3) & 31) ^ iArr2[i3]) != ((bArr2[i5 + 3] & 255) | ((bArr2[i5] & 255) << 24) | ((bArr2[i5 + 1] & 255) << 16) | ((bArr2[i5 + 2] & 255) << 8))) {
                        return 1;
                    }
                }
            }
        }
        return 0;
    }

    public static void g0(Context context) {
        byte[][] bArrO;
        try {
            if (!u) {
                u = true;
                try {
                    bArrO = o(O(context));
                } catch (Throwable unused) {
                    bArrO = null;
                }
                i = bArrO;
                j = Z(context);
            }
            long jElapsedRealtime = SystemClock.elapsedRealtime();
            if (jElapsedRealtime - v > 30000) {
                v = jElapsedRealtime;
                X(context);
            }
        } catch (Throwable unused2) {
        }
    }

    public static boolean h(Context context) {
        if (Q(context)) {
            return context.checkSelfPermission("android.permission.READ_EXTERNAL_STORAGE") == 0;
        }
        return Environment.isExternalStorageManager();
    }

    public static int h0() {
        try {
            int i2 = p | q;
            if (f0()) {
                i2 |= 1;
            }
            return (i2 | (t ? 1 : 0)) == true ? 1 : 0;
        } catch (Throwable unused) {
            return 0;
        }
    }

    /* JADX WARN: Code duplicated, block: B:28:0x0068 A[PHI: r5
      0x0068: PHI (r5v8 int) = (r5v7 int), (r5v9 int) binds: [B:34:0x0073, B:27:0x0066] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:29:0x006a A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:30:0x006c  */
    /* JADX WARN: Code duplicated, block: B:36:0x0076  */
    /* JADX WARN: Code duplicated, block: B:37:0x0078  */
    /* JADX WARN: Code duplicated, block: B:71:0x00ef A[PHI: r21 r22
      0x00ef: PHI (r21v4 android.view.Display$Mode) = (r21v2 android.view.Display$Mode), (r21v5 android.view.Display$Mode) binds: [B:70:0x00ed, B:65:0x00d4] A[DONT_GENERATE, DONT_INLINE]
      0x00ef: PHI (r22v2 android.view.Display$Mode[]) = (r22v0 android.view.Display$Mode[]), (r22v3 android.view.Display$Mode[]) binds: [B:70:0x00ed, B:65:0x00d4] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:73:0x00fd  */
    /* JADX WARN: Code duplicated, block: B:77:0x0112  */
    public static boolean i(PlayerActivity playerActivity, float f2, int i2) {
        float f3;
        String strValueOf;
        boolean z;
        vg0 vg0Var;
        ro2 ro2Var;
        Display.Mode mode;
        Display.Mode[] modeArr;
        int i3;
        int i4;
        playerActivity.k3 = false;
        float f4 = (playerActivity.H.D && ((i4 = (int) (f2 * 100.0f)) == 2400 || i4 == 3000 || i4 == 6000)) ? (1000.0f * f2) / 1001.0f : f2;
        Display display = f4 > 0.0f ? playerActivity.getWindow().getDecorView().getDisplay() : null;
        if (display == null) {
            return false;
        }
        Display.Mode[] supportedModes = display.getSupportedModes();
        Display.Mode mode2 = display.getMode();
        if (supportedModes.length <= 1) {
            K("display mode: video " + i2 + "w @" + f4 + ", active " + L(mode2) + ", the display offers no other mode");
            return false;
        }
        int i5 = -1;
        if (playerActivity.H.C) {
            int physicalWidth = mode2.getPhysicalWidth();
            if (i2 <= 0) {
                f3 = 100.0f;
            } else {
                f3 = 100.0f;
                if (i2 > 1920) {
                    i3 = 3840;
                    if (physicalWidth < 3840) {
                        i5 = i3;
                    } else if (i2 != 1920) {
                        i3 = 1280;
                        if (i2 <= 1280 && physicalWidth < 1920) {
                            i5 = 1920;
                        } else if (i2 == 1280) {
                            i5 = i3;
                        }
                    } else {
                        i5 = 1920;
                    }
                } else if (i2 != 1920) {
                    i3 = 1280;
                    if (i2 <= 1280) {
                    }
                    if (i2 == 1280) {
                        i5 = i3;
                    }
                } else {
                    i5 = 1920;
                }
            }
        } else {
            f3 = 100.0f;
        }
        if (playerActivity.H.C) {
            strValueOf = i5 > 0 ? String.valueOf(i5) : "none for this video";
        } else {
            strValueOf = "off";
        }
        if (i5 > 0 && i5 != mode2.getPhysicalWidth()) {
            int length = supportedModes.length;
            int i6 = 0;
            while (true) {
                if (i6 >= length) {
                    z = false;
                    break;
                }
                Display.Mode mode3 = supportedModes[i6];
                if (mode3.getPhysicalWidth() == i5 && ((int) (mode3.getRefreshRate() * f3)) >= ((int) (f4 * f3))) {
                    z = true;
                    break;
                }
                i6++;
            }
        } else {
            z = false;
            break;
        }
        ArrayList arrayList = new ArrayList();
        Display.Mode mode4 = z ? null : mode2;
        int length2 = supportedModes.length;
        Display.Mode mode5 = mode4;
        int i7 = 0;
        int i8 = 0;
        while (i8 < length2) {
            boolean z2 = z;
            Display.Mode mode6 = supportedModes[i8];
            if (z2) {
                mode = mode2;
                modeArr = supportedModes;
                if (mode6.getPhysicalWidth() == i5) {
                    i7++;
                    if (((int) (mode6.getRefreshRate() * f3)) >= ((int) (f4 * f3))) {
                        arrayList.add(mode6);
                    }
                    if (mode5 != null || ((int) (mode6.getRefreshRate() * f3)) > ((int) (mode5.getRefreshRate() * f3))) {
                        mode5 = mode6;
                    }
                }
            } else {
                mode = mode2;
                modeArr = supportedModes;
                if (mode6.getPhysicalWidth() == mode.getPhysicalWidth() && mode6.getPhysicalHeight() == mode.getPhysicalHeight()) {
                    i7++;
                    if (((int) (mode6.getRefreshRate() * f3)) >= ((int) (f4 * f3))) {
                        arrayList.add(mode6);
                    }
                    if (mode5 != null) {
                        mode5 = mode6;
                    } else {
                        mode5 = mode6;
                    }
                }
            }
            i8++;
            z = z2;
            mode2 = mode;
            supportedModes = modeArr;
        }
        Display.Mode mode7 = mode2;
        if (!z ? i7 > 1 : i7 > 0) {
            K("display mode: video " + i2 + "w @" + f4 + ", active " + L(mode7) + ", target width " + strValueOf + ", " + i7 + " candidates, nothing to switch to");
            return false;
        }
        int i9 = (!playerActivity.H.E || f4 >= 31.0f) ? 1 : 2;
        Display.Mode modeM = M(arrayList, f4, i9);
        if (modeM == null && f4 != f2) {
            modeM = M(arrayList, f2, i9);
        }
        Window window = playerActivity.getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        if (modeM != null) {
            mode5 = modeM;
        }
        boolean z3 = mode5.getModeId() != mode7.getModeId();
        StringBuilder sb = new StringBuilder("display mode: video ");
        sb.append(i2);
        sb.append("w @");
        sb.append(f4);
        sb.append(", active ");
        sb.append(L(mode7));
        sb.append(", target width ");
        sb.append(strValueOf);
        sb.append(", ");
        sb.append(i7);
        sb.append(" candidates");
        sb.append(z3 ? ", switching to ".concat(L(mode5)) : ", staying put");
        K(sb.toString());
        if (z3) {
            playerActivity.k3 = mode5.getPhysicalWidth() != mode7.getPhysicalWidth();
            playerActivity.U5 = mode5.getModeId();
            if (!playerActivity.F2 && (vg0Var = PlayerActivity.n6) != null && vg0Var.w() && ((ro2Var = playerActivity.a5) == null || !ro2Var.j())) {
                PlayerActivity.n6.k(false);
                playerActivity.F2 = true;
            }
            attributes.preferredDisplayModeId = mode5.getModeId();
            window.setAttributes(attributes);
        }
        return z3;
    }

    public static int i0() {
        try {
            boolean zF0 = f0();
            return ((((zF0 ? 1 : 0) | p) | (s ? 1 : 0)) == true ? 1 : 0) | (t ? 1 : 0);
        } catch (Throwable unused) {
            return 0;
        }
    }

    public static xt2 j(PlayerActivity playerActivity, int i2, int i3, int i4) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setCornerRadius(10000.0f);
        gradientDrawable.setStroke(playerActivity.getResources().getDimensionPixelSize(R.dimen.focus_ring_width), new ColorStateList(new int[][]{new int[]{android.R.attr.state_focused}, new int[0]}, new int[]{i4, 0}));
        xt2 xt2Var = new xt2(S(playerActivity.getColor(R.color.ripple_chrome)), new InsetDrawable((Drawable) gradientDrawable, i2), new InsetDrawable((Drawable) d0(-1, 10000.0f), i3));
        xt2Var.setPaddingMode(1);
        return xt2Var;
    }

    public static int j0() {
        try {
            boolean zF0 = f0();
            int i2 = (zF0 ? 1 : 0) | p;
            if (q()) {
                i2 |= 1;
            }
            return (i2 | (t ? 1 : 0)) == true ? 1 : 0;
        } catch (Throwable unused) {
            return 0;
        }
    }

    public static int k(String str, String str2) {
        int length;
        int length2;
        int i2 = 0;
        int i3 = 0;
        while (i2 < str.length() && i3 < str2.length()) {
            char cCharAt = str.charAt(i2);
            char cCharAt2 = str2.charAt(i3);
            if (Character.isDigit(cCharAt) && Character.isDigit(cCharAt2)) {
                while (i2 < str.length() - 1 && str.charAt(i2) == '0') {
                    int i4 = i2 + 1;
                    if (!Character.isDigit(str.charAt(i4))) {
                        break;
                    }
                    i2 = i4;
                }
                while (i3 < str2.length() - 1 && str2.charAt(i3) == '0') {
                    int i5 = i3 + 1;
                    if (!Character.isDigit(str2.charAt(i5))) {
                        break;
                    }
                    i3 = i5;
                }
                int i6 = i2;
                while (i6 < str.length() && Character.isDigit(str.charAt(i6))) {
                    i6++;
                }
                int i7 = i3;
                while (i7 < str2.length() && Character.isDigit(str2.charAt(i7))) {
                    i7++;
                }
                int i8 = i6 - i2;
                int i9 = i7 - i3;
                if (i8 != i9) {
                    return i8 - i9;
                }
                while (i2 < i6) {
                    if (str.charAt(i2) != str2.charAt(i3)) {
                        length = str.charAt(i2);
                        length2 = str2.charAt(i3);
                        return length - length2;
                    }
                    i2++;
                    i3++;
                }
            } else {
                char lowerCase = Character.toLowerCase(cCharAt);
                char lowerCase2 = Character.toLowerCase(cCharAt2);
                if (lowerCase != lowerCase2) {
                    return lowerCase - lowerCase2;
                }
                i2++;
                i3++;
            }
        }
        length = str.length() - i2;
        length2 = str2.length() - i3;
        return length - length2;
    }

    public static void k0(Activity activity, cz czVar, boolean z) {
        WindowInsetsController insetsController;
        if (Build.VERSION.SDK_INT < 31) {
            if (z) {
                czVar.setSystemUiVisibility(1792);
                return;
            } else {
                czVar.setSystemUiVisibility(4871);
                return;
            }
        }
        Window window = activity.getWindow();
        if (window == null || (insetsController = window.getInsetsController()) == null) {
            return;
        }
        if (z) {
            insetsController.show(WindowInsets.Type.systemBars());
        } else {
            insetsController.hide(WindowInsets.Type.systemBars());
        }
    }

    public static Uri l(Context context, Uri uri, InputStream inputStream, String str) {
        File file;
        boolean z;
        int i2;
        try {
            Charset charset = StandardCharsets.UTF_8;
            p10 p10VarA = nq.a(inputStream);
            Charset charset2 = p10VarA.l;
            boolean zE = E(uri);
            if (charset.equals(charset2) && !zE) {
                return uri;
            }
            byte b2 = 1;
            if (str == null) {
                String path = uri.getPath();
                str = path.substring(path.lastIndexOf("/") + 1);
            }
            if (str.endsWith(".gz")) {
                str = str.substring(0, str.length() - 3);
            }
            if (!str.contains(".")) {
                if (str.isEmpty()) {
                    str = "subtitle";
                }
                str = str.concat(".srt");
            }
            try {
                BufferedReader bufferedReader = new BufferedReader(p10VarA);
                char[] cArr = new char[512];
                int i3 = 0;
                while (i3 < 512 && (i2 = bufferedReader.read(cArr, i3, 512 - i3)) != -1) {
                    i3 += i2;
                }
                file = new File(context.getCacheDir(), N(str, new String(cArr, 0, i3)));
                try {
                    BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
                    int i4 = 0;
                    while (true) {
                        if (i3 <= 0) {
                            z = true;
                            break;
                        }
                        bufferedWriter.write(cArr, 0, i3);
                        i4++;
                        if (i4 * 512 > 2000000) {
                            z = false;
                            break;
                        }
                        i3 = 0;
                        while (i3 < 512) {
                            int i5 = bufferedReader.read(cArr, i3, 512 - i3);
                            if (i5 == -1) {
                                break;
                            }
                            i3 += i5;
                        }
                    }
                    bufferedWriter.close();
                    bufferedReader.close();
                } catch (IOException e2) {
                    e = e2;
                    e.printStackTrace();
                    z = false;
                }
            } catch (IOException e3) {
                e = e3;
                file = null;
            }
            if (!z || file == null) {
                if (file != null) {
                    file.delete();
                }
                if (zE) {
                    return uri;
                }
                return null;
            }
            File[] fileArrListFiles = context.getCacheDir().listFiles(new dl2(b2));
            if (fileArrListFiles != null && fileArrListFiles.length > 20) {
                Arrays.sort(fileArrListFiles, new vf2((byte) 7));
                for (int i6 = 0; i6 < fileArrListFiles.length - 20; i6++) {
                    fileArrListFiles[i6].delete();
                }
            }
            return Uri.fromFile(file);
        } catch (IOException e4) {
            e4.printStackTrace();
            return uri;
        }
    }

    public static String l0(String str, Locale locale) {
        char decimalSeparator = DecimalFormatSymbols.getInstance(locale).getDecimalSeparator();
        if (str.indexOf(decimalSeparator) < 0) {
            return str;
        }
        int length = str.length();
        while (length > 0 && str.charAt(length - 1) == '0') {
            length--;
        }
        if (length > 0 && str.charAt(length - 1) == decimalSeparator) {
            length--;
        }
        return str.substring(0, length);
    }

    public static int m(byte[][] bArr) {
        if (bArr != null && bArr.length != 0) {
            for (byte[] bArr2 : bArr) {
                if (bArr2 == null || bArr2.length != 32) {
                    break;
                }
                if ((((bArr2[0] & 255) << 24) | ((bArr2[1] & 255) << 16) | ((bArr2[2] & 255) << 8) | (bArr2[3] & 255)) == ((-823407395) ^ Integer.rotateLeft(1581569660, 0))) {
                    if (((bArr2[15] & 255) | ((bArr2[12] & 255) << 24) | ((bArr2[13] & 255) << 16) | ((bArr2[14] & 255) << 8)) == (Integer.rotateLeft(1581569660, 21) ^ (-1563775410))) {
                    }
                }
                return 1;
            }
        }
        return 0;
    }

    public static String m0(String str) {
        if (str == null || str.indexOf(38) < 0) {
            return str;
        }
        return (Build.VERSION.SDK_INT >= 24 ? zh.f(str) : Html.fromHtml(str)).toString().trim();
    }

    public static boolean n(Context context) {
        XmlResourceParser xml = null;
        try {
            xml = context.getResources().getXml(R.xml.root_preferences);
            String str = new String(w);
            String str2 = new String(x);
            while (true) {
                int next = xml.next();
                if (next == 1) {
                    xml.close();
                    return false;
                }
                if (next == 2) {
                    int attributeCount = xml.getAttributeCount();
                    for (int i2 = 0; i2 < attributeCount; i2++) {
                        String attributeValue = xml.getAttributeValue(i2);
                        if (attributeValue != null && (attributeValue.contains(str) || attributeValue.contains(str2))) {
                            xml.close();
                            return true;
                        }
                    }
                }
            }
        } catch (Throwable unused) {
            if (xml != null) {
                xml.close();
            }
            return true;
        }
    }

    public static String n0(Uri uri) {
        if (uri == null) {
            return null;
        }
        String host = uri.getHost();
        if (host == null) {
            return uri.getScheme();
        }
        StringBuilder sb = new StringBuilder();
        if (uri.getScheme() != null) {
            sb.append(uri.getScheme());
            sb.append("://");
        }
        sb.append(host);
        if (uri.getPort() != -1) {
            sb.append(':');
            sb.append(uri.getPort());
        }
        if (uri.getEncodedPath() != null) {
            sb.append(uri.getEncodedPath());
        }
        return sb.toString();
    }

    public static byte[][] o(byte[][] bArr) throws NoSuchAlgorithmException {
        if (bArr == null || bArr.length == 0) {
            return null;
        }
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        byte[][] bArr2 = new byte[bArr.length][];
        for (int i2 = 0; i2 < bArr.length; i2++) {
            byte[] bArr3 = bArr[i2];
            if (bArr3 == null) {
                return null;
            }
            bArr2[i2] = messageDigest.digest(bArr3);
        }
        return bArr2;
    }

    public static void o0(Context context) {
        if (PlayerActivity.O6 || PlayerActivity.N6 <= 0.0f) {
            return;
        }
        PlayerActivity.O6 = true;
        if (context instanceof PlayerActivity) {
            ((PlayerActivity) context).o3(context.getString(R.string.volume_high_warning), false, R.drawable.ic_volume_up_24dp);
        }
    }

    public static int p(int i2) {
        return (int) (i2 * Resources.getSystem().getDisplayMetrics().density);
    }

    public static boolean q() {
        if (o) {
            return (m == -16754761 && n == -10496) ? false : true;
        }
        return false;
    }

    public static void r(final MaterialButton materialButton) {
        final int strokeWidth = materialButton.getStrokeWidth();
        final ColorStateList strokeColor = materialButton.getStrokeColor();
        final int iMax = Math.max(strokeWidth, materialButton.getResources().getDimensionPixelSize(R.dimen.focus_ring_width));
        materialButton.setRippleColor(dz0.v(materialButton.getContext(), R.color.ripple_button));
        materialButton.setOutlineProvider(new lt((byte) 1));
        materialButton.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: st2
            @Override // android.view.View.OnFocusChangeListener
            public final void onFocusChange(View view, boolean z) {
                int i2 = z ? iMax : strokeWidth;
                MaterialButton materialButton2 = materialButton;
                materialButton2.setStrokeWidth(i2);
                materialButton2.setStrokeColor(z ? ColorStateList.valueOf(sj.n(materialButton2.getContext(), R.attr.colorOnSurface, -1)) : strokeColor);
                materialButton2.setTranslationZ(z ? 1.0f : 0.0f);
            }
        });
    }

    public static String s(int i2) {
        switch (i2) {
            case 1:
                return "1.0";
            case 2:
                return "2.0";
            case VideoDecoderOutputBuffer.COLORSPACE_BT2020 /* 3 */:
                return "2.1";
            case 4:
                return "4.0";
            case 5:
                return "5.0";
            case 6:
                return "5.1";
            case 7:
                return "6.1";
            case 8:
                return "7.1";
            default:
                return i2 + "ch";
        }
    }

    public static String t(long j2) {
        int iAbs = Math.abs(((int) j2) / 1000);
        int i2 = iAbs % 60;
        int i3 = (iAbs % 3600) / 60;
        int i4 = iAbs / 3600;
        return i4 > 0 ? String.format("%d:%02d:%02d", Integer.valueOf(i4), Integer.valueOf(i3), Integer.valueOf(i2)) : String.format("%02d:%02d", Integer.valueOf(i3), Integer.valueOf(i2));
    }

    public static String u(long j2) {
        if (j2 <= -1000 || j2 >= 1000) {
            return (j2 < 0 ? "−" : "+").concat(t(j2));
        }
        return t(j2);
    }

    public static String[] v() {
        ArrayList arrayList = new ArrayList();
        if (Build.VERSION.SDK_INT >= 24) {
            LocaleList locales = Resources.getSystem().getConfiguration().getLocales();
            for (int i2 = 0; i2 < locales.size(); i2++) {
                String language = locales.get(i2).getLanguage();
                xp2 xp2Var = xp2.f;
                String strD = ha1.D(language);
                if (strD != null && !arrayList.contains(strD)) {
                    arrayList.add(strD);
                }
            }
        } else {
            String language2 = Resources.getSystem().getConfiguration().locale.getLanguage();
            xp2 xp2Var2 = xp2.f;
            String strD2 = ha1.D(language2);
            if (strD2 != null && !arrayList.contains(strD2)) {
                arrayList.add(strD2);
            }
        }
        return (String[]) arrayList.toArray(new String[0]);
    }

    public static String w(Context context, Uri uri) {
        Uri uri2;
        int i2;
        int iLastIndexOf;
        int columnIndex;
        if (f90.d(uri) && uri.getLastPathSegment() != null) {
            return uri.getLastPathSegment();
        }
        String path = null;
        try {
            if ("content".equals(uri.getScheme())) {
                uri2 = uri;
                Cursor cursorQuery = context.getContentResolver().query(uri2, new String[]{"_display_name"}, null, null, null);
                if (cursorQuery != null) {
                    try {
                        if (cursorQuery.moveToFirst() && (columnIndex = cursorQuery.getColumnIndex("_display_name")) > -1) {
                            path = cursorQuery.getString(columnIndex);
                        }
                    } catch (Throwable th) {
                        try {
                            cursorQuery.close();
                            throw th;
                        } catch (Throwable th2) {
                            th.addSuppressed(th2);
                            throw th;
                        }
                    }
                }
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
            } else {
                uri2 = uri;
            }
            if (path == null && (iLastIndexOf = (path = uri2.getPath()).lastIndexOf(47)) != -1) {
                path = path.substring(iLastIndexOf + 1);
            }
            int iLastIndexOf2 = path.lastIndexOf(".");
            if (iLastIndexOf2 > 0) {
                String strSubstring = path.substring(iLastIndexOf2 + 1);
                if (!strSubstring.isEmpty() && strSubstring.length() <= 5) {
                    for (0; i2 < strSubstring.length(); i2 + 1) {
                        char cCharAt = strSubstring.charAt(i2);
                        i2 = (cCharAt <= 127 && Character.isLetterOrDigit(cCharAt)) ? i2 + 1 : 0;
                    }
                    return path.substring(0, iLastIndexOf2);
                }
            }
            return path;
        } catch (Exception e2) {
            e2.printStackTrace();
            return path;
        }
    }

    public static int x(Context context, boolean z, AudioManager audioManager) {
        int iIntValue;
        if (Build.VERSION.SDK_INT >= 30 && Build.MANUFACTURER.equalsIgnoreCase("samsung")) {
            try {
                Class<?> cls = Class.forName("com.samsung.android.media.SemSoundAssistantManager");
                Object objInvoke = cls.getDeclaredMethod("getMediaVolumeInterval", null).invoke(cls.getConstructor(Context.class).newInstance(context), null);
                if ((objInvoke instanceof Integer) && (iIntValue = ((Integer) objInvoke).intValue()) < 10) {
                    Object objInvoke2 = AudioManager.class.getDeclaredMethod("semGetFineVolume", Integer.TYPE).invoke(audioManager, 3);
                    if (objInvoke2 instanceof Integer) {
                        return z ? 150 / iIntValue : ((Integer) objInvoke2).intValue() / iIntValue;
                    }
                }
            } catch (Exception unused) {
            }
        }
        return z ? audioManager.getStreamMaxVolume(3) : audioManager.getStreamVolume(3);
    }

    public static int y(Context context, AudioManager audioManager) {
        float f2 = PlayerActivity.N6;
        if (f2 > 0.0f) {
            return Math.round(f2 * 10.0f) + 100;
        }
        if (!PlayerActivity.P6) {
            return Math.round(PlayerActivity.Q6);
        }
        int iX = x(context, true, audioManager);
        if (iX <= 0) {
            return 0;
        }
        return Math.round((x(context, false, audioManager) * 100.0f) / iX);
    }

    public static boolean z(String str) {
        String lowerCase = str.toLowerCase();
        for (int i2 = 0; i2 < 8; i2++) {
            if (lowerCase.endsWith("." + a[i2])) {
                return true;
            }
        }
        return false;
    }
}
