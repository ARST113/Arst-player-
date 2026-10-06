package com.brouken.player;

import android.app.ActivityManager;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.hardware.display.DisplayManager;
import android.os.Build;
import android.os.Bundle;
import android.os.StatFs;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.content.FileProvider;
import androidx.media3.decoder.DecoderInputBuffer;
import androidx.media3.decoder.VideoDecoderOutputBuffer;
import com.brouken.player.ErrorActivity;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;
import com.justplus.player.R;
import defpackage.as2;
import defpackage.df1;
import defpackage.f7;
import defpackage.gt2;
import defpackage.lv2;
import defpackage.o61;
import defpackage.pz2;
import defpackage.qz2;
import defpackage.re0;
import defpackage.rz2;
import defpackage.tj0;
import defpackage.tv2;
import defpackage.tz2;
import defpackage.vt1;
import defpackage.xi0;
import defpackage.xs2;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.WeakHashMap;

/* JADX INFO: compiled from: r8-map-id-ba8d2c2760819bd03a19aa4133a73a2576203ef152dace5b65b91a4c4fd30466 */
/* JADX INFO: loaded from: classes.dex */
public class ErrorActivity extends f7 {
    public static final /* synthetic */ int P = 0;
    public String I;
    public String J;
    public boolean K;
    public MaterialButton L;
    public View M;
    public TextView N;
    public ImageView O;

    public static String w(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            byte[] bArrDigest = MessageDigest.getInstance("SHA-256").digest((Build.VERSION.SDK_INT >= 28 ? packageManager.getPackageInfo(context.getPackageName(), 134217728).signingInfo.getApkContentsSigners() : packageManager.getPackageInfo(context.getPackageName(), 64).signatures)[0].toByteArray());
            return String.format(Locale.US, "%02x%02x%02x%02x", Byte.valueOf(bArrDigest[0]), Byte.valueOf(bArrDigest[1]), Byte.valueOf(bArrDigest[2]), Byte.valueOf(bArrDigest[3]));
        } catch (Exception unused) {
            return "-";
        }
    }

    public static String z(Throwable th) {
        String str = null;
        while (th != null) {
            String localizedMessage = th.getLocalizedMessage();
            if (localizedMessage != null && !localizedMessage.isEmpty()) {
                str = localizedMessage;
            }
            th = th.getCause();
        }
        return str;
    }

    public final void A(int i, float f) {
        ((TextView) findViewById(i)).setTextSize(2, f);
    }

    @Override // android.app.Activity, android.view.ContextThemeWrapper
    public final void onApplyThemeResource(Resources.Theme theme, int i, boolean z) {
        super.onApplyThemeResource(theme, i, z);
        theme.applyStyle(vt1.a(this, vt1.m(this)), true);
    }

    /* JADX WARN: Code duplicated, block: B:102:0x03c2  */
    @Override // defpackage.f7, defpackage.ru, defpackage.qu, android.app.Activity
    public final void onCreate(Bundle bundle) {
        tj0 qz2Var;
        String stringExtra;
        String stringExtra2;
        int i;
        k().p(vt1.g(this));
        super.onCreate(bundle);
        boolean zM = vt1.m(this);
        final byte b = 1;
        if (!zM && vt1.l(this)) {
            getTheme().applyStyle(R.style.ThemeOverlay_JustPlus_Amoled, true);
        }
        Window window = getWindow();
        df1 df1Var = new df1(getWindow().getDecorView());
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 35) {
            qz2Var = new tz2(window, df1Var);
        } else if (i2 >= 30) {
            qz2Var = new rz2(window, df1Var);
        } else {
            qz2Var = i2 >= 26 ? new qz2(window, df1Var) : new pz2(window, df1Var);
        }
        qz2Var.Z(zM);
        final byte b2 = 0;
        xi0.y(getWindow(), false);
        setContentView(R.layout.activity_error);
        View viewFindViewById = findViewById(R.id.error_root);
        re0 re0Var = new re0(new as2(this, gt2.F(this)), gt2.p(16), findViewById(R.id.content));
        WeakHashMap weakHashMap = tv2.a;
        lv2.j(viewFindViewById, re0Var);
        boolean z = PreferenceManager.getDefaultSharedPreferences(this).getBoolean("maskReports", true);
        if (z) {
            String stringExtra3 = getIntent().getStringExtra("summary");
            stringExtra = stringExtra3 == null ? null : gt2.h.matcher(stringExtra3).replaceAll("$1");
        } else {
            stringExtra = getIntent().getStringExtra("summary");
        }
        if (z) {
            String stringExtra4 = getIntent().getStringExtra("report");
            stringExtra2 = stringExtra4 == null ? null : gt2.h.matcher(stringExtra4).replaceAll("$1");
        } else {
            stringExtra2 = getIntent().getStringExtra("report");
        }
        Locale locale = Locale.US;
        String str = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z", locale).format(new Date());
        StringBuilder sb = new StringBuilder("com.justplus.player@2.1.1 (build 2001001, latestUniversal release, ");
        sb.append(w(this));
        sb.append(")\nDevice: ");
        sb.append(Build.MANUFACTURER);
        sb.append(' ');
        sb.append(Build.MODEL);
        sb.append(" (Android ");
        sb.append(Build.VERSION.RELEASE);
        sb.append(", API ");
        int i3 = Build.VERSION.SDK_INT;
        sb.append(i3);
        sb.append(")\nTime: ");
        sb.append(str);
        sb.append("\nBuild: ");
        sb.append(Build.FINGERPRINT);
        sb.append("\nHardware: ");
        sb.append(Build.DEVICE);
        sb.append('/');
        sb.append(Build.HARDWARE);
        if (i3 >= 31) {
            sb.append(' ');
            sb.append(Build.SOC_MODEL);
        }
        sb.append(", ");
        String[] strArr = Build.SUPPORTED_ABIS;
        String str2 = "?";
        sb.append(strArr.length > 0 ? strArr[0] : "?");
        sb.append(", patch ");
        sb.append(Build.VERSION.SECURITY_PATCH);
        sb.append("\nDisplay: ");
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        sb.append(displayMetrics.widthPixels);
        sb.append('x');
        sb.append(displayMetrics.heightPixels);
        sb.append(" @");
        sb.append(displayMetrics.densityDpi);
        sb.append("dpi ");
        DisplayManager displayManager = (DisplayManager) getSystemService("display");
        Display display = displayManager != null ? displayManager.getDisplay(0) : null;
        sb.append(String.format(locale, "%.2fHz", Float.valueOf(display != null ? display.getRefreshRate() : 0.0f)));
        sb.append(", ");
        sb.append(gt2.F(this) ? "tv" : getResources().getConfiguration().smallestScreenWidthDp >= 720 ? "tablet" : "phone");
        final byte b3 = 2;
        sb.append(getResources().getConfiguration().orientation == 2 ? ", landscape" : ", portrait");
        sb.append("\nLocale: ");
        sb.append(Locale.getDefault());
        sb.append(", ");
        sb.append(TimeZone.getDefault().getID());
        sb.append("\nRuntime: up ");
        sb.append((SystemClock.elapsedRealtime() - App.l) / 1000);
        sb.append("s, installer ");
        try {
            String installerPackageName = getPackageManager().getInstallerPackageName(getPackageName());
            str2 = installerPackageName != null ? installerPackageName : "none (sideloaded)";
        } catch (Exception unused) {
        }
        sb.append(str2);
        sb.append("\nMemory: heap ");
        Runtime runtime = Runtime.getRuntime();
        sb.append((runtime.totalMemory() - runtime.freeMemory()) >> 20);
        sb.append('/');
        sb.append(runtime.maxMemory() >> 20);
        sb.append(" MB");
        ActivityManager activityManager = (ActivityManager) getSystemService("activity");
        if (activityManager != null) {
            ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
            activityManager.getMemoryInfo(memoryInfo);
            sb.append(", device ");
            sb.append(memoryInfo.availMem >> 20);
            sb.append(" MB free");
            sb.append(memoryInfo.lowMemory ? " (low)" : "");
        }
        sb.append(", storage ");
        sb.append(new StatFs(getFilesDir().getAbsolutePath()).getAvailableBytes() >> 20);
        sb.append(" MB free\nPrefs: crash reporting off");
        sb.append(PreferenceManager.getDefaultSharedPreferences(this).getBoolean("maskReports", true) ? "" : ", masking off");
        sb.append("\n\n");
        sb.append(stringExtra2 != null ? stringExtra2 : "");
        this.I = sb.toString();
        MaterialToolbar materialToolbar = (MaterialToolbar) findViewById(R.id.toolbar);
        materialToolbar.setNavigationOnClickListener(new View.OnClickListener(this) { // from class: qe0
            public final /* synthetic */ ErrorActivity m;

            {
                this.m = this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                byte b4 = b2;
                ErrorActivity errorActivity = this.m;
                switch (b4) {
                    case DecoderInputBuffer.BUFFER_REPLACEMENT_MODE_DISABLED /* 0 */:
                        int i4 = ErrorActivity.P;
                        errorActivity.finish();
                        return;
                    case 1:
                        int i5 = ErrorActivity.P;
                        errorActivity.x(errorActivity.I);
                        return;
                    case 2:
                        int i6 = ErrorActivity.P;
                        String str3 = errorActivity.I;
                        Intent intent = new Intent("android.intent.action.SEND");
                        intent.setType("text/plain");
                        intent.putExtra("android.intent.extra.SUBJECT", errorActivity.getString(R.string.error_share_subject));
                        try {
                            File file = new File(errorActivity.getCacheDir(), "report");
                            File[] fileArrListFiles = file.listFiles();
                            if (fileArrListFiles != null) {
                                for (File file2 : fileArrListFiles) {
                                    file2.delete();
                                }
                            }
                            file.mkdirs();
                            File file3 = new File(file, "justplus-report-" + new SimpleDateFormat("yyyyMMdd-HHmmss", Locale.US).format(new Date()) + ".txt");
                            FileOutputStream fileOutputStream = new FileOutputStream(file3);
                            try {
                                fileOutputStream.write(str3.getBytes(StandardCharsets.UTF_8));
                                fileOutputStream.close();
                                intent.putExtra("android.intent.extra.STREAM", FileProvider.c(errorActivity, errorActivity.getPackageName() + ".provider", file3));
                                intent.addFlags(1);
                            } catch (Throwable th) {
                                try {
                                    fileOutputStream.close();
                                    break;
                                } catch (Throwable th2) {
                                    th.addSuppressed(th2);
                                }
                                throw th;
                            }
                        } catch (IOException | IllegalArgumentException unused2) {
                            intent.putExtra("android.intent.extra.TEXT", str3);
                        }
                        errorActivity.startActivity(Intent.createChooser(intent, errorActivity.getString(R.string.error_share)));
                        return;
                    case VideoDecoderOutputBuffer.COLORSPACE_BT2020 /* 3 */:
                        int i7 = ErrorActivity.P;
                        if (errorActivity.K) {
                            return;
                        }
                        String str4 = errorActivity.J;
                        if (str4 != null) {
                            errorActivity.x(str4);
                            return;
                        }
                        errorActivity.K = true;
                        MaterialButton materialButton = errorActivity.L;
                        js jsVar = new js(errorActivity, null, 0, R.style.Widget_Material3_CircularProgressIndicator_ExtraSmall);
                        jsVar.r = gt2.p(24);
                        jsVar.a = gt2.p(2);
                        jsVar.s = 0;
                        jsVar.e = new int[]{lj.n(errorActivity.L.getContext(), R.attr.colorOnPrimary, -1)};
                        int i8 = vw0.B;
                        vw0 vw0VarG = vw0.g(errorActivity, jsVar, new ds(jsVar));
                        vw0VarG.d(true, true, true);
                        vw0VarG.start();
                        materialButton.setIcon(vw0VarG);
                        errorActivity.y(errorActivity.L, R.string.error_uploading);
                        new Thread(new h4(errorActivity, (byte) 23)).start();
                        return;
                    default:
                        int i9 = ErrorActivity.P;
                        String str5 = errorActivity.J;
                        if (str5 != null) {
                            errorActivity.x(str5);
                            return;
                        }
                        return;
                }
            }
        });
        TextView textView = (TextView) findViewById(R.id.errorDetails);
        if (stringExtra == null) {
            stringExtra = "";
        }
        textView.setText(stringExtra);
        String stringExtra5 = getIntent().getStringExtra("title");
        if (stringExtra5 != null) {
            materialToolbar.setTitle(stringExtra5);
        }
        String stringExtra6 = getIntent().getStringExtra("message");
        if (stringExtra6 != null) {
            TextView textView2 = (TextView) findViewById(R.id.errorMessage);
            textView2.setText(stringExtra6);
            textView2.setVisibility(0);
        }
        ((TextView) findViewById(R.id.rowDevice)).setText(Build.MANUFACTURER + " " + Build.MODEL + " · Android " + Build.VERSION.RELEASE);
        TextView textView3 = (TextView) findViewById(R.id.rowApp);
        StringBuilder sb2 = new StringBuilder();
        sb2.append(getString(R.string.app_name));
        sb2.append(" 2.1.1");
        textView3.setText(sb2.toString());
        findViewById(R.id.rowPlayer).setVisibility((stringExtra2 == null || !stringExtra2.contains("\nVideo: ")) ? 8 : 0);
        if (stringExtra2 == null || stringExtra2.isEmpty()) {
            i = 0;
        } else {
            int iIndexOf = stringExtra2.indexOf("Trace:\n");
            String strSubstring = iIndexOf >= 0 ? stringExtra2.substring(iIndexOf + 7) : stringExtra2.trim();
            if (strSubstring.isEmpty()) {
                i = 0;
            } else {
                i = 1;
                for (int i4 = 0; i4 < strSubstring.length(); i4++) {
                    if (strSubstring.charAt(i4) == '\n') {
                        i++;
                    }
                }
            }
        }
        TextView textView4 = (TextView) findViewById(R.id.rowLog);
        textView4.setVisibility(i > 0 ? 0 : 8);
        textView4.setText(getResources().getQuantityString(R.plurals.report_log_lines, i, Integer.valueOf(i)));
        this.L = (MaterialButton) findViewById(R.id.btnUpload);
        this.M = findViewById(R.id.uploadResult);
        this.N = (TextView) findViewById(R.id.uploadUrl);
        this.O = (ImageView) findViewById(R.id.qrImage);
        MaterialButton materialButton = (MaterialButton) findViewById(R.id.btnCopy);
        MaterialButton materialButton2 = (MaterialButton) findViewById(R.id.btnShare);
        y(materialButton, R.string.error_copy);
        y(materialButton2, R.string.error_share);
        y(this.L, R.string.error_upload);
        final byte b4 = 3;
        MaterialButton[] materialButtonArr = {materialButton, materialButton2, this.L};
        for (int i5 = 0; i5 < 3; i5++) {
            gt2.r(materialButtonArr[i5]);
        }
        materialButton.setOnClickListener(new View.OnClickListener(this) { // from class: qe0
            public final /* synthetic */ ErrorActivity m;

            {
                this.m = this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                byte b5 = b;
                ErrorActivity errorActivity = this.m;
                switch (b5) {
                    case DecoderInputBuffer.BUFFER_REPLACEMENT_MODE_DISABLED /* 0 */:
                        int i6 = ErrorActivity.P;
                        errorActivity.finish();
                        return;
                    case 1:
                        int i7 = ErrorActivity.P;
                        errorActivity.x(errorActivity.I);
                        return;
                    case 2:
                        int i8 = ErrorActivity.P;
                        String str3 = errorActivity.I;
                        Intent intent = new Intent("android.intent.action.SEND");
                        intent.setType("text/plain");
                        intent.putExtra("android.intent.extra.SUBJECT", errorActivity.getString(R.string.error_share_subject));
                        try {
                            File file = new File(errorActivity.getCacheDir(), "report");
                            File[] fileArrListFiles = file.listFiles();
                            if (fileArrListFiles != null) {
                                for (File file2 : fileArrListFiles) {
                                    file2.delete();
                                }
                            }
                            file.mkdirs();
                            File file3 = new File(file, "justplus-report-" + new SimpleDateFormat("yyyyMMdd-HHmmss", Locale.US).format(new Date()) + ".txt");
                            FileOutputStream fileOutputStream = new FileOutputStream(file3);
                            try {
                                fileOutputStream.write(str3.getBytes(StandardCharsets.UTF_8));
                                fileOutputStream.close();
                                intent.putExtra("android.intent.extra.STREAM", FileProvider.c(errorActivity, errorActivity.getPackageName() + ".provider", file3));
                                intent.addFlags(1);
                            } catch (Throwable th) {
                                try {
                                    fileOutputStream.close();
                                    break;
                                } catch (Throwable th2) {
                                    th.addSuppressed(th2);
                                }
                                throw th;
                            }
                        } catch (IOException | IllegalArgumentException unused2) {
                            intent.putExtra("android.intent.extra.TEXT", str3);
                        }
                        errorActivity.startActivity(Intent.createChooser(intent, errorActivity.getString(R.string.error_share)));
                        return;
                    case VideoDecoderOutputBuffer.COLORSPACE_BT2020 /* 3 */:
                        int i9 = ErrorActivity.P;
                        if (errorActivity.K) {
                            return;
                        }
                        String str4 = errorActivity.J;
                        if (str4 != null) {
                            errorActivity.x(str4);
                            return;
                        }
                        errorActivity.K = true;
                        MaterialButton materialButton3 = errorActivity.L;
                        js jsVar = new js(errorActivity, null, 0, R.style.Widget_Material3_CircularProgressIndicator_ExtraSmall);
                        jsVar.r = gt2.p(24);
                        jsVar.a = gt2.p(2);
                        jsVar.s = 0;
                        jsVar.e = new int[]{lj.n(errorActivity.L.getContext(), R.attr.colorOnPrimary, -1)};
                        int i10 = vw0.B;
                        vw0 vw0VarG = vw0.g(errorActivity, jsVar, new ds(jsVar));
                        vw0VarG.d(true, true, true);
                        vw0VarG.start();
                        materialButton3.setIcon(vw0VarG);
                        errorActivity.y(errorActivity.L, R.string.error_uploading);
                        new Thread(new h4(errorActivity, (byte) 23)).start();
                        return;
                    default:
                        int i11 = ErrorActivity.P;
                        String str5 = errorActivity.J;
                        if (str5 != null) {
                            errorActivity.x(str5);
                            return;
                        }
                        return;
                }
            }
        });
        if (gt2.F(this)) {
            materialButton.setVisibility(8);
            materialButton2.setVisibility(8);
        }
        materialButton2.setOnClickListener(new View.OnClickListener(this) { // from class: qe0
            public final /* synthetic */ ErrorActivity m;

            {
                this.m = this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                byte b5 = b3;
                ErrorActivity errorActivity = this.m;
                switch (b5) {
                    case DecoderInputBuffer.BUFFER_REPLACEMENT_MODE_DISABLED /* 0 */:
                        int i6 = ErrorActivity.P;
                        errorActivity.finish();
                        return;
                    case 1:
                        int i7 = ErrorActivity.P;
                        errorActivity.x(errorActivity.I);
                        return;
                    case 2:
                        int i8 = ErrorActivity.P;
                        String str3 = errorActivity.I;
                        Intent intent = new Intent("android.intent.action.SEND");
                        intent.setType("text/plain");
                        intent.putExtra("android.intent.extra.SUBJECT", errorActivity.getString(R.string.error_share_subject));
                        try {
                            File file = new File(errorActivity.getCacheDir(), "report");
                            File[] fileArrListFiles = file.listFiles();
                            if (fileArrListFiles != null) {
                                for (File file2 : fileArrListFiles) {
                                    file2.delete();
                                }
                            }
                            file.mkdirs();
                            File file3 = new File(file, "justplus-report-" + new SimpleDateFormat("yyyyMMdd-HHmmss", Locale.US).format(new Date()) + ".txt");
                            FileOutputStream fileOutputStream = new FileOutputStream(file3);
                            try {
                                fileOutputStream.write(str3.getBytes(StandardCharsets.UTF_8));
                                fileOutputStream.close();
                                intent.putExtra("android.intent.extra.STREAM", FileProvider.c(errorActivity, errorActivity.getPackageName() + ".provider", file3));
                                intent.addFlags(1);
                            } catch (Throwable th) {
                                try {
                                    fileOutputStream.close();
                                    break;
                                } catch (Throwable th2) {
                                    th.addSuppressed(th2);
                                }
                                throw th;
                            }
                        } catch (IOException | IllegalArgumentException unused2) {
                            intent.putExtra("android.intent.extra.TEXT", str3);
                        }
                        errorActivity.startActivity(Intent.createChooser(intent, errorActivity.getString(R.string.error_share)));
                        return;
                    case VideoDecoderOutputBuffer.COLORSPACE_BT2020 /* 3 */:
                        int i9 = ErrorActivity.P;
                        if (errorActivity.K) {
                            return;
                        }
                        String str4 = errorActivity.J;
                        if (str4 != null) {
                            errorActivity.x(str4);
                            return;
                        }
                        errorActivity.K = true;
                        MaterialButton materialButton3 = errorActivity.L;
                        js jsVar = new js(errorActivity, null, 0, R.style.Widget_Material3_CircularProgressIndicator_ExtraSmall);
                        jsVar.r = gt2.p(24);
                        jsVar.a = gt2.p(2);
                        jsVar.s = 0;
                        jsVar.e = new int[]{lj.n(errorActivity.L.getContext(), R.attr.colorOnPrimary, -1)};
                        int i10 = vw0.B;
                        vw0 vw0VarG = vw0.g(errorActivity, jsVar, new ds(jsVar));
                        vw0VarG.d(true, true, true);
                        vw0VarG.start();
                        materialButton3.setIcon(vw0VarG);
                        errorActivity.y(errorActivity.L, R.string.error_uploading);
                        new Thread(new h4(errorActivity, (byte) 23)).start();
                        return;
                    default:
                        int i11 = ErrorActivity.P;
                        String str5 = errorActivity.J;
                        if (str5 != null) {
                            errorActivity.x(str5);
                            return;
                        }
                        return;
                }
            }
        });
        this.L.setOnClickListener(new View.OnClickListener(this) { // from class: qe0
            public final /* synthetic */ ErrorActivity m;

            {
                this.m = this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                byte b5 = b4;
                ErrorActivity errorActivity = this.m;
                switch (b5) {
                    case DecoderInputBuffer.BUFFER_REPLACEMENT_MODE_DISABLED /* 0 */:
                        int i6 = ErrorActivity.P;
                        errorActivity.finish();
                        return;
                    case 1:
                        int i7 = ErrorActivity.P;
                        errorActivity.x(errorActivity.I);
                        return;
                    case 2:
                        int i8 = ErrorActivity.P;
                        String str3 = errorActivity.I;
                        Intent intent = new Intent("android.intent.action.SEND");
                        intent.setType("text/plain");
                        intent.putExtra("android.intent.extra.SUBJECT", errorActivity.getString(R.string.error_share_subject));
                        try {
                            File file = new File(errorActivity.getCacheDir(), "report");
                            File[] fileArrListFiles = file.listFiles();
                            if (fileArrListFiles != null) {
                                for (File file2 : fileArrListFiles) {
                                    file2.delete();
                                }
                            }
                            file.mkdirs();
                            File file3 = new File(file, "justplus-report-" + new SimpleDateFormat("yyyyMMdd-HHmmss", Locale.US).format(new Date()) + ".txt");
                            FileOutputStream fileOutputStream = new FileOutputStream(file3);
                            try {
                                fileOutputStream.write(str3.getBytes(StandardCharsets.UTF_8));
                                fileOutputStream.close();
                                intent.putExtra("android.intent.extra.STREAM", FileProvider.c(errorActivity, errorActivity.getPackageName() + ".provider", file3));
                                intent.addFlags(1);
                            } catch (Throwable th) {
                                try {
                                    fileOutputStream.close();
                                    break;
                                } catch (Throwable th2) {
                                    th.addSuppressed(th2);
                                }
                                throw th;
                            }
                        } catch (IOException | IllegalArgumentException unused2) {
                            intent.putExtra("android.intent.extra.TEXT", str3);
                        }
                        errorActivity.startActivity(Intent.createChooser(intent, errorActivity.getString(R.string.error_share)));
                        return;
                    case VideoDecoderOutputBuffer.COLORSPACE_BT2020 /* 3 */:
                        int i9 = ErrorActivity.P;
                        if (errorActivity.K) {
                            return;
                        }
                        String str4 = errorActivity.J;
                        if (str4 != null) {
                            errorActivity.x(str4);
                            return;
                        }
                        errorActivity.K = true;
                        MaterialButton materialButton3 = errorActivity.L;
                        js jsVar = new js(errorActivity, null, 0, R.style.Widget_Material3_CircularProgressIndicator_ExtraSmall);
                        jsVar.r = gt2.p(24);
                        jsVar.a = gt2.p(2);
                        jsVar.s = 0;
                        jsVar.e = new int[]{lj.n(errorActivity.L.getContext(), R.attr.colorOnPrimary, -1)};
                        int i10 = vw0.B;
                        vw0 vw0VarG = vw0.g(errorActivity, jsVar, new ds(jsVar));
                        vw0VarG.d(true, true, true);
                        vw0VarG.start();
                        materialButton3.setIcon(vw0VarG);
                        errorActivity.y(errorActivity.L, R.string.error_uploading);
                        new Thread(new h4(errorActivity, (byte) 23)).start();
                        return;
                    default:
                        int i11 = ErrorActivity.P;
                        String str5 = errorActivity.J;
                        if (str5 != null) {
                            errorActivity.x(str5);
                            return;
                        }
                        return;
                }
            }
        });
        final byte b5 = 4;
        this.M.setOnClickListener(new View.OnClickListener(this) { // from class: qe0
            public final /* synthetic */ ErrorActivity m;

            {
                this.m = this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                byte b6 = b5;
                ErrorActivity errorActivity = this.m;
                switch (b6) {
                    case DecoderInputBuffer.BUFFER_REPLACEMENT_MODE_DISABLED /* 0 */:
                        int i6 = ErrorActivity.P;
                        errorActivity.finish();
                        return;
                    case 1:
                        int i7 = ErrorActivity.P;
                        errorActivity.x(errorActivity.I);
                        return;
                    case 2:
                        int i8 = ErrorActivity.P;
                        String str3 = errorActivity.I;
                        Intent intent = new Intent("android.intent.action.SEND");
                        intent.setType("text/plain");
                        intent.putExtra("android.intent.extra.SUBJECT", errorActivity.getString(R.string.error_share_subject));
                        try {
                            File file = new File(errorActivity.getCacheDir(), "report");
                            File[] fileArrListFiles = file.listFiles();
                            if (fileArrListFiles != null) {
                                for (File file2 : fileArrListFiles) {
                                    file2.delete();
                                }
                            }
                            file.mkdirs();
                            File file3 = new File(file, "justplus-report-" + new SimpleDateFormat("yyyyMMdd-HHmmss", Locale.US).format(new Date()) + ".txt");
                            FileOutputStream fileOutputStream = new FileOutputStream(file3);
                            try {
                                fileOutputStream.write(str3.getBytes(StandardCharsets.UTF_8));
                                fileOutputStream.close();
                                intent.putExtra("android.intent.extra.STREAM", FileProvider.c(errorActivity, errorActivity.getPackageName() + ".provider", file3));
                                intent.addFlags(1);
                            } catch (Throwable th) {
                                try {
                                    fileOutputStream.close();
                                    break;
                                } catch (Throwable th2) {
                                    th.addSuppressed(th2);
                                }
                                throw th;
                            }
                        } catch (IOException | IllegalArgumentException unused2) {
                            intent.putExtra("android.intent.extra.TEXT", str3);
                        }
                        errorActivity.startActivity(Intent.createChooser(intent, errorActivity.getString(R.string.error_share)));
                        return;
                    case VideoDecoderOutputBuffer.COLORSPACE_BT2020 /* 3 */:
                        int i9 = ErrorActivity.P;
                        if (errorActivity.K) {
                            return;
                        }
                        String str4 = errorActivity.J;
                        if (str4 != null) {
                            errorActivity.x(str4);
                            return;
                        }
                        errorActivity.K = true;
                        MaterialButton materialButton3 = errorActivity.L;
                        js jsVar = new js(errorActivity, null, 0, R.style.Widget_Material3_CircularProgressIndicator_ExtraSmall);
                        jsVar.r = gt2.p(24);
                        jsVar.a = gt2.p(2);
                        jsVar.s = 0;
                        jsVar.e = new int[]{lj.n(errorActivity.L.getContext(), R.attr.colorOnPrimary, -1)};
                        int i10 = vw0.B;
                        vw0 vw0VarG = vw0.g(errorActivity, jsVar, new ds(jsVar));
                        vw0VarG.d(true, true, true);
                        vw0VarG.start();
                        materialButton3.setIcon(vw0VarG);
                        errorActivity.y(errorActivity.L, R.string.error_uploading);
                        new Thread(new h4(errorActivity, (byte) 23)).start();
                        return;
                    default:
                        int i11 = ErrorActivity.P;
                        String str5 = errorActivity.J;
                        if (str5 != null) {
                            errorActivity.x(str5);
                            return;
                        }
                        return;
                }
            }
        });
        if (gt2.F(this)) {
            A(R.id.errorMessage, 17.0f);
            A(R.id.reportHeading, 22.0f);
            A(R.id.errorDetails, 15.0f);
            A(R.id.uploadUrl, 17.0f);
            A(R.id.uploadHint, 16.0f);
            int[] iArr = {R.id.rowDevice, R.id.rowApp, R.id.rowPlayer, R.id.rowLog};
            for (int i6 = 0; i6 < 4; i6++) {
                A(iArr[i6], 17.0f);
            }
        }
        if (!gt2.F(this)) {
            View viewFindViewById2 = findViewById(R.id.content);
            viewFindViewById2.setFocusableInTouchMode(true);
            viewFindViewById2.requestFocus();
        }
        if (gt2.F(this)) {
            this.L.requestFocus();
        }
    }

    @Override // defpackage.f7
    public final void q() {
        getTheme().applyStyle(vt1.a(this, vt1.m(this)), true);
    }

    public final void x(String str) {
        ClipboardManager clipboardManager = (ClipboardManager) getSystemService("clipboard");
        if (clipboardManager != null) {
            clipboardManager.setPrimaryClip(ClipData.newPlainText("Just+ Player error", str));
            o61.M(this, R.string.error_copied, false, R.drawable.ic_content_copy_24dp);
        }
    }

    public final void y(MaterialButton materialButton, int i) {
        materialButton.setContentDescription(getString(i));
        xs2.w(materialButton, getString(i));
        if (materialButton.getText().length() > 0) {
            materialButton.setText(i);
        }
    }
}
