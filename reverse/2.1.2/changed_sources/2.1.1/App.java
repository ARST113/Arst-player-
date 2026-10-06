package com.brouken.player;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Process;
import android.os.SystemClock;
import com.brouken.player.ErrorActivity;
import com.justplus.player.R;
import defpackage.an;
import defpackage.f8;
import defpackage.gt2;
import defpackage.pa;
import defpackage.qt1;
import defpackage.s7;
import defpackage.va;
import defpackage.vk1;
import defpackage.vt1;
import defpackage.w92;
import defpackage.wk1;
import defpackage.zm;
import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: r8-map-id-ba8d2c2760819bd03a19aa4133a73a2576203ef152dace5b65b91a4c4fd30466 */
/* JADX INFO: loaded from: classes.dex */
public class App extends Application {
    public static final long l = SystemClock.elapsedRealtime();

    @Override // android.app.Application
    public final void onCreate() {
        super.onCreate();
        boolean z = false;
        vt1.q(getSharedPreferences(qt1.a(this), 0));
        if (gt2.F(this) && s7.m != 2) {
            s7.m = 2;
            synchronized (s7.s) {
                try {
                    va vaVar = s7.r;
                    vaVar.getClass();
                    pa paVar = new pa(vaVar);
                    while (paVar.hasNext()) {
                        s7 s7Var = (s7) ((WeakReference) paVar.next()).get();
                        if (s7Var != null) {
                            ((f8) s7Var).s(true, true);
                        }
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        String strA = qt1.a(this);
        SharedPreferences sharedPreferences = getSharedPreferences("_has_set_default_values", 0);
        if (!sharedPreferences.getBoolean("_has_set_default_values", false)) {
            qt1 qt1Var = new qt1(this);
            qt1Var.f = strA;
            qt1Var.c = null;
            qt1Var.e(this);
            sharedPreferences.edit().putBoolean("_has_set_default_values", true).apply();
        }
        SharedPreferences sharedPreferences2 = getSharedPreferences(qt1.a(this), 0);
        if (!sharedPreferences2.getBoolean("allowSystemFrameRateTvDefault", false)) {
            SharedPreferences.Editor editorPutBoolean = sharedPreferences2.edit().putBoolean("allowSystemFrameRateTvDefault", true);
            if (!gt2.F(this) && sharedPreferences2.getBoolean("allowSystemFrameRate", true)) {
                z = true;
            }
            editorPutBoolean.putBoolean("allowSystemFrameRate", z).apply();
        }
        File cacheDir = getCacheDir();
        if (cacheDir == null) {
            an anVar = w92.a;
        } else {
            vk1 vk1VarA = w92.d.a();
            vk1VarA.l = new zm(new File(cacheDir, "segments"));
            w92.d = new wk1(vk1VarA);
            w92.c = new File(cacheDir, "animeskip");
        }
        int i = ErrorActivity.P;
        final Context applicationContext = getApplicationContext();
        final Thread.UncaughtExceptionHandler defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() { // from class: pe0
            @Override // java.lang.Thread.UncaughtExceptionHandler
            public final void uncaughtException(Thread thread, Throwable th2) {
                Context context = applicationContext;
                int i2 = ErrorActivity.P;
                try {
                    Intent intentPutExtra = new Intent(context, (Class<?>) ErrorActivity.class).addFlags(268468224).putExtra("title", context.getString(R.string.crash_report_title));
                    String strZ = ErrorActivity.z(th2);
                    String simpleName = th2.getClass().getSimpleName();
                    if (strZ != null) {
                        simpleName = simpleName + "\n" + strZ;
                    }
                    Intent intentPutExtra2 = intentPutExtra.putExtra("summary", simpleName);
                    StringWriter stringWriter = new StringWriter();
                    th2.printStackTrace(new PrintWriter(stringWriter));
                    context.startActivity(intentPutExtra2.putExtra("report", stringWriter.toString()));
                } catch (Throwable unused) {
                }
                Thread.UncaughtExceptionHandler uncaughtExceptionHandler = defaultUncaughtExceptionHandler;
                if (uncaughtExceptionHandler != null) {
                    uncaughtExceptionHandler.uncaughtException(thread, th2);
                } else {
                    Process.killProcess(Process.myPid());
                    System.exit(10);
                }
            }
        });
    }
}
