package com.brouken.player;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Process;
import android.os.SystemClock;
import com.brouken.player.ErrorActivity;
import com.justplus.player.R;
import defpackage.bu1;
import defpackage.el1;
import defpackage.fl1;
import defpackage.g8;
import defpackage.gn;
import defpackage.gu1;
import defpackage.ha2;
import defpackage.hn;
import defpackage.qa;
import defpackage.t7;
import defpackage.wa;
import defpackage.wt2;
import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: r8-map-id-d5d7d661e67c62f2588b5a5666a7a20281e417b1121a202706e8fa77676a2bdd */
/* JADX INFO: loaded from: classes.dex */
public class App extends Application {
    public static final long l = SystemClock.elapsedRealtime();

    @Override // android.app.Application
    public final void onCreate() {
        super.onCreate();
        boolean z = false;
        gu1.q(getSharedPreferences(bu1.a(this), 0));
        if (wt2.F(this) && t7.m != 2) {
            t7.m = 2;
            synchronized (t7.s) {
                try {
                    wa waVar = t7.r;
                    waVar.getClass();
                    qa qaVar = new qa(waVar);
                    while (qaVar.hasNext()) {
                        t7 t7Var = (t7) ((WeakReference) qaVar.next()).get();
                        if (t7Var != null) {
                            ((g8) t7Var).s(true, true);
                        }
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        String strA = bu1.a(this);
        SharedPreferences sharedPreferences = getSharedPreferences("_has_set_default_values", 0);
        if (!sharedPreferences.getBoolean("_has_set_default_values", false)) {
            bu1 bu1Var = new bu1(this);
            bu1Var.f = strA;
            bu1Var.c = null;
            bu1Var.e(this);
            sharedPreferences.edit().putBoolean("_has_set_default_values", true).apply();
        }
        SharedPreferences sharedPreferences2 = getSharedPreferences(bu1.a(this), 0);
        if (!sharedPreferences2.getBoolean("allowSystemFrameRateTvDefault", false)) {
            SharedPreferences.Editor editorPutBoolean = sharedPreferences2.edit().putBoolean("allowSystemFrameRateTvDefault", true);
            if (!wt2.F(this) && sharedPreferences2.getBoolean("allowSystemFrameRate", true)) {
                z = true;
            }
            editorPutBoolean.putBoolean("allowSystemFrameRate", z).apply();
        }
        File cacheDir = getCacheDir();
        if (cacheDir == null) {
            hn hnVar = ha2.a;
        } else {
            el1 el1VarA = ha2.d.a();
            el1VarA.l = new gn(new File(cacheDir, "segments"));
            ha2.d = new fl1(el1VarA);
            ha2.c = new File(cacheDir, "animeskip");
        }
        int i = ErrorActivity.P;
        final Context applicationContext = getApplicationContext();
        final Thread.UncaughtExceptionHandler defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() { // from class: xe0
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
