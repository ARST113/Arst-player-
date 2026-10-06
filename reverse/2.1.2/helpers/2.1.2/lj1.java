package defpackage;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.SurfaceTexture;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.HandlerThread;
import android.os.SystemClock;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.Surface;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageButton;
import androidx.lifecycle.ProcessLifecycleOwner;
import androidx.media3.decoder.DecoderInputBuffer;
import androidx.media3.decoder.VideoDecoderOutputBuffer;
import androidx.recyclerview.widget.RecyclerView;
import com.brouken.player.PlayerActivity;
import com.brouken.player.SettingsActivity;
import com.google.android.material.sidesheet.SideSheetBehavior;
import com.google.android.material.textfield.TextInputLayout;
import com.justplus.player.R;
import io.sentry.ILogger;
import io.sentry.android.core.NetworkBreadcrumbsIntegration;
import io.sentry.android.core.SystemEventsBreadcrumbsIntegration;
import io.sentry.android.core.a;
import io.sentry.android.core.d;
import io.sentry.android.core.d1;
import io.sentry.android.core.i;
import io.sentry.android.core.r;
import io.sentry.android.core.t0;
import io.sentry.internal.modules.f;
import io.sentry.ndk.NativeScope;
import io.sentry.q1;
import io.sentry.s;
import io.sentry.s4;
import io.sentry.w3;
import io.sentry.x5;
import java.io.File;
import java.util.Iterator;
import java.util.Locale;
import java.util.regex.Matcher;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: compiled from: r8-map-id-d5d7d661e67c62f2588b5a5666a7a20281e417b1121a202706e8fa77676a2bdd */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class lj1 implements Runnable {
    public final /* synthetic */ byte l;
    public final /* synthetic */ Object m;

    public /* synthetic */ lj1(a aVar, q1 q1Var) {
        this.l = (byte) 22;
        this.m = aVar;
    }

    /* JADX WARN: Code duplicated, block: B:261:0x03e5 A[Catch: all -> 0x03ab, TryCatch #7 {, blocks: (B:233:0x03a0, B:235:0x03a4, B:242:0x03b0, B:246:0x03b7, B:252:0x03c2, B:254:0x03c6, B:256:0x03cc, B:258:0x03d6, B:260:0x03e0, B:262:0x03f1, B:261:0x03e5, B:263:0x03f3, B:265:0x0406, B:267:0x040e), top: B:292:0x03a0 }] */
    /* JADX WARN: Code duplicated, block: B:88:0x014b  */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.lang.Runnable
    public final void run() {
        String strQ;
        TelephonyManager telephonyManager;
        String strL;
        int i;
        JSONObject jSONObject;
        byte b = this.l;
        int i2 = 2;
        j72 j72Var = null;
        int i3 = 0;
        Object obj = this.m;
        switch (b) {
            case DecoderInputBuffer.BUFFER_REPLACEMENT_MODE_DISABLED /* 0 */:
                mj1 mj1Var = (mj1) obj;
                w20 w20Var = (w20) mj1Var.a.get();
                if (w20Var != null) {
                    int iB = mj1Var.c.b();
                    x20 x20Var = w20Var.a;
                    synchronized (x20Var) {
                        int i4 = x20Var.n;
                        if (i4 == 0 || x20Var.e) {
                            if (i4 != iB || x20Var.o == null) {
                                x20Var.n = iB;
                                if (iB != 1 && iB != 0 && iB != 8) {
                                    if (x20Var.o == null) {
                                        Context context = x20Var.a;
                                        String str = ot2.a;
                                        if (context == null || (telephonyManager = (TelephonyManager) context.getSystemService("phone")) == null) {
                                            strQ = sj.Q(Locale.getDefault().getCountry());
                                        } else {
                                            String networkCountryIso = telephonyManager.getNetworkCountryIso();
                                            if (TextUtils.isEmpty(networkCountryIso)) {
                                                strQ = sj.Q(Locale.getDefault().getCountry());
                                            } else {
                                                strQ = sj.Q(networkCountryIso);
                                            }
                                        }
                                        x20Var.o = strQ;
                                    }
                                    x20Var.l = x20Var.c(iB);
                                    x20Var.d.getClass();
                                    long jElapsedRealtime = SystemClock.elapsedRealtime();
                                    x20Var.d(x20Var.g > 0 ? (int) (jElapsedRealtime - x20Var.h) : 0, x20Var.i, x20Var.l);
                                    x20Var.h = jElapsedRealtime;
                                    x20Var.i = 0L;
                                    x20Var.k = 0L;
                                    x20Var.j = 0L;
                                    me2 me2Var = x20Var.f;
                                    me2Var.a.clear();
                                    me2Var.b = -1;
                                    me2Var.c = 0;
                                    me2Var.d = 0;
                                    return;
                                }
                                return;
                            }
                            return;
                        }
                        return;
                    }
                }
                return;
            case 1:
                ((no1) obj).m--;
                return;
            case 2:
                ((ImageButton) obj).requestFocus();
                return;
            case VideoDecoderOutputBuffer.COLORSPACE_BT2020 /* 3 */:
                PlayerActivity playerActivity = (PlayerActivity) ((gr1) obj).m;
                playerActivity.B.t(0, playerActivity.Z1);
                return;
            case 4:
                ((vr1) obj).s();
                return;
            case 5:
                ProcessLifecycleOwner processLifecycleOwner = (ProcessLifecycleOwner) obj;
                q01 q01Var = processLifecycleOwner.q;
                ProcessLifecycleOwner processLifecycleOwner2 = ProcessLifecycleOwner.t;
                if (processLifecycleOwner.m == 0) {
                    processLifecycleOwner.n = true;
                    q01Var.e(f01.ON_PAUSE);
                }
                if (processLifecycleOwner.l == 0 && processLifecycleOwner.n) {
                    q01Var.e(f01.ON_STOP);
                    processLifecycleOwner.o = true;
                    return;
                }
                return;
            case 6:
                ((ow1) obj).m();
                return;
            case 7:
                ((nz1) obj).b();
                return;
            case 8:
                ((HandlerThread) obj).quit();
                return;
            case 9:
                ((l82) obj).c();
                return;
            case 10:
                RecyclerView recyclerView = (RecyclerView) obj;
                try {
                    if (recyclerView.getHeight() <= 0) {
                        return;
                    }
                    View viewFindViewById = recyclerView.findViewById(R.id.deco_t1);
                    View viewFindViewById2 = recyclerView.findViewById(R.id.deco_t2);
                    if (viewFindViewById == null || viewFindViewById2 == null || viewFindViewById.getVisibility() != 0 || viewFindViewById2.getVisibility() != 0 || viewFindViewById.getHeight() <= 0 || viewFindViewById2.getHeight() <= 0 || viewFindViewById.getAlpha() < 0.99f || viewFindViewById2.getAlpha() < 0.99f) {
                        wt2.r = true;
                    }
                    if (viewFindViewById == null || !(viewFindViewById.getParent() instanceof ViewGroup)) {
                        return;
                    }
                    ViewGroup viewGroup = (ViewGroup) viewFindViewById.getParent();
                    Rect rect = new Rect();
                    viewFindViewById.getGlobalVisibleRect(rect);
                    if (rect.isEmpty()) {
                        return;
                    }
                    for (int i5 = 0; i5 < viewGroup.getChildCount(); i5++) {
                        View childAt = viewGroup.getChildAt(i5);
                        if (childAt != viewFindViewById && childAt != viewFindViewById2) {
                            Rect rect2 = new Rect();
                            childAt.getGlobalVisibleRect(rect2);
                            if (rect2.contains(rect)) {
                                wt2.s = true;
                                return;
                            }
                        }
                    }
                    return;
                } catch (Throwable unused) {
                    return;
                }
            case 11:
                ((SettingsActivity) obj).z();
                return;
            case 12:
                qj qjVar = (qj) obj;
                qjVar.c = false;
                SideSheetBehavior sideSheetBehavior = (SideSheetBehavior) qjVar.e;
                mw2 mw2Var = sideSheetBehavior.i;
                if (mw2Var != null && mw2Var.f()) {
                    qjVar.d(qjVar.b);
                    return;
                } else {
                    if (sideSheetBehavior.h == 2) {
                        sideSheetBehavior.r(qjVar.b);
                        return;
                    }
                    return;
                }
            case 13:
                ig2 ig2Var = (ig2) obj;
                int i6 = ig2.w;
                Surface surface = ig2Var.s;
                if (surface != null) {
                    Iterator it = ig2Var.l.iterator();
                    while (it.hasNext()) {
                        ((pg0) it.next()).l.u1(null);
                    }
                }
                SurfaceTexture surfaceTexture = ig2Var.r;
                if (surfaceTexture != null) {
                    surfaceTexture.release();
                }
                if (surface != null) {
                    surface.release();
                }
                ig2Var.r = null;
                ig2Var.s = null;
                return;
            case 14:
                ((dh2) obj).y();
                return;
            case 15:
                hk2 hk2Var = (hk2) obj;
                Uri uriA = hk2Var.a();
                if (uriA == null) {
                    return;
                }
                hk2Var.a.runOnUiThread(new hj1((Object) hk2Var, (Object) uriA, (byte) 17));
                return;
            case 16:
                ((kk2) obj).d();
                return;
            case 17:
                am2 am2Var = ((cm2) obj).a;
                ViewParent parent = am2Var.getParent();
                if (parent instanceof ViewGroup) {
                    ((ViewGroup) parent).removeView(am2Var);
                    return;
                }
                return;
            case 18:
                int[][] iArr = TextInputLayout.O0;
                ((TextInputLayout) obj).p.requestLayout();
                return;
            case 19:
                ct2 ct2Var = (ct2) obj;
                vk1 vk1Var = new vk1();
                vk1Var.c("https://api.github.com/repos/just-plus-player/just-plus-player/releases");
                vk1Var.a("Accept", "application/vnd.github+json");
                vk1Var.a("User-Agent", "JustPlusPlayer/2.1.2");
                gv1 gv1Var = new gv1(vk1Var);
                try {
                    fl1 fl1Var = dt2.b;
                    fl1Var.getClass();
                    r12 r12VarE = new dx1(fl1Var, gv1Var, false).e();
                    try {
                        if (r12VarE.B) {
                            u12 u12Var = r12VarE.r;
                            strL = u12Var != null ? u12Var.l() : null;
                            r12VarE.close();
                            break;
                        } else {
                            r12VarE.close();
                            strL = null;
                        }
                        if (strL != null) {
                            try {
                                JSONArray jSONArray = new JSONArray(strL);
                                int i7 = 0;
                                while (true) {
                                    if (i7 < jSONArray.length()) {
                                        JSONObject jSONObjectOptJSONObject = jSONArray.optJSONObject(i7);
                                        if (jSONObjectOptJSONObject != null && !jSONObjectOptJSONObject.optBoolean("draft") && !jSONObjectOptJSONObject.optBoolean("prerelease")) {
                                            String strOptString = jSONObjectOptJSONObject.optString("tag_name", "");
                                            if (strOptString == null) {
                                                i = i3;
                                            } else {
                                                Matcher matcher = dt2.a.matcher(strOptString);
                                                if (matcher.find()) {
                                                    try {
                                                        i = (Integer.parseInt(matcher.group(i2)) * 1000) + (Integer.parseInt(matcher.group(1)) * 1000000) + Integer.parseInt(matcher.group(3));
                                                    } catch (NumberFormatException unused2) {
                                                        i = i3;
                                                    }
                                                } else {
                                                    i = i3;
                                                }
                                            }
                                            if (i > 2001002) {
                                                JSONArray jSONArrayOptJSONArray = jSONObjectOptJSONObject.optJSONArray("assets");
                                                if (jSONArrayOptJSONArray == null) {
                                                    jSONObject = j72Var;
                                                } else {
                                                    int i8 = i3;
                                                    while (true) {
                                                        if (i8 < jSONArrayOptJSONArray.length()) {
                                                            JSONObject jSONObjectOptJSONObject2 = jSONArrayOptJSONArray.optJSONObject(i8);
                                                            if (jSONObjectOptJSONObject2 != null) {
                                                                String lowerCase = jSONObjectOptJSONObject2.optString("name", "").toLowerCase(Locale.ROOT);
                                                                String strOptString2 = jSONObjectOptJSONObject2.optString("browser_download_url", "");
                                                                if (lowerCase.endsWith(".apk") && !strOptString2.isEmpty()) {
                                                                    jSONObject = jSONObjectOptJSONObject2;
                                                                }
                                                            }
                                                            i8++;
                                                        } else {
                                                            jSONObject = 0;
                                                        }
                                                    }
                                                }
                                                if (jSONObject != 0) {
                                                    j72Var = new j72(i, strOptString, strOptString.startsWith("v") ? strOptString.substring(1) : strOptString, jSONObjectOptJSONObject.optString("body", ""), jSONObject.optString("browser_download_url", ""), jSONObject.optLong("size", 0L), jSONObjectOptJSONObject.optString("published_at", ""));
                                                }
                                                j72Var = null;
                                            } else {
                                                continue;
                                            }
                                        }
                                        i7++;
                                        i2 = 2;
                                        j72Var = null;
                                        i3 = 0;
                                    } else {
                                        j72Var = null;
                                    }
                                }
                            } catch (Exception unused3) {
                            }
                        }
                        if (Thread.currentThread().isInterrupted()) {
                            return;
                        }
                        ct2Var.e(j72Var);
                        return;
                    } catch (Throwable th) {
                        try {
                            r12VarE.close();
                            throw th;
                        } catch (Throwable th2) {
                            th.addSuppressed(th2);
                            throw th;
                        }
                    }
                } catch (Exception unused4) {
                }
                break;
            case 20:
                ((iv2) obj).c();
                return;
            case 21:
                File[] fileArrListFiles = ((File) obj).listFiles();
                if (fileArrListFiles == null) {
                    return;
                }
                int length = fileArrListFiles.length;
                while (i3 < length) {
                    File file = fileArrListFiles[i3];
                    if (file.lastModified() < w3.f - 300000) {
                        io.sentry.config.a.f(file);
                    }
                    i3++;
                }
                return;
            case 22:
                a aVar = (a) obj;
                aVar.s = SystemClock.uptimeMillis();
                aVar.t.set(false);
                return;
            case 23:
                ((d) obj).a.a.A();
                return;
            case 24:
                ((i) obj).e(true);
                return;
            case 25:
                ((r) obj).a(null, true);
                return;
            case 26:
                NetworkBreadcrumbsIntegration networkBreadcrumbsIntegration = (NetworkBreadcrumbsIntegration) obj;
                s sVarA = networkBreadcrumbsIntegration.o.a();
                try {
                    if (networkBreadcrumbsIntegration.r != null) {
                        Context context2 = networkBreadcrumbsIntegration.l;
                        ILogger iLogger = networkBreadcrumbsIntegration.n;
                        t0 t0Var = networkBreadcrumbsIntegration.r;
                        ConnectivityManager connectivityManagerE = io.sentry.android.core.internal.util.a.e(context2, iLogger);
                        if (connectivityManagerE != null) {
                            try {
                                connectivityManagerE.unregisterNetworkCallback(t0Var);
                            } catch (Throwable th3) {
                                iLogger.p(s4.WARNING, "unregisterNetworkCallback failed", th3);
                            }
                        }
                        networkBreadcrumbsIntegration.n.e(s4.DEBUG, "NetworkBreadcrumbsIntegration removed.", new Object[0]);
                        break;
                    }
                    networkBreadcrumbsIntegration.r = null;
                    sVarA.close();
                    return;
                } catch (Throwable th4) {
                    try {
                        sVarA.close();
                        throw th4;
                    } catch (Throwable th5) {
                        th4.addSuppressed(th5);
                        throw th4;
                    }
                }
            case 27:
                SystemEventsBreadcrumbsIntegration systemEventsBreadcrumbsIntegration = (SystemEventsBreadcrumbsIntegration) obj;
                d1 d1Var = systemEventsBreadcrumbsIntegration.n;
                if (d1Var != null) {
                    ProcessLifecycleOwner.t.q.g(d1Var);
                }
                systemEventsBreadcrumbsIntegration.n = null;
                return;
            case 28:
                ((f) obj).a();
                return;
            default:
                x5 x5Var = (x5) obj;
                NativeScope.nativeSetTrace(x5Var.l.toString(), x5Var.m.toString());
                return;
        }
    }

    public /* synthetic */ lj1(io.sentry.android.ndk.d dVar, x5 x5Var) {
        this.l = (byte) 29;
        this.m = x5Var;
    }

    public /* synthetic */ lj1(Object obj, byte b) {
        this.l = b;
        this.m = obj;
    }
}
