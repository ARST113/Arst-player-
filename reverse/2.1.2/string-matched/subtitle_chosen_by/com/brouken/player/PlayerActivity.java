package com.brouken.player;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.AppOpsManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.RemoteAction;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.Icon;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.hardware.display.DisplayManager;
import android.media.AudioManager;
import android.media.MediaCodec;
import android.media.MediaCodecInfo;
import android.media.MediaCodecList;
import android.media.audiofx.LoudnessEnhancer;
import android.net.Uri;
import android.opengl.GLSurfaceView;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcelable;
import android.os.Process;
import android.os.SystemClock;
import android.provider.Settings;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.StaticLayout;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.LeadingMarginSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.TabStopSpan;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Pair;
import android.util.Rational;
import android.util.SparseArray;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.Display;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowInsets;
import android.view.WindowInsetsController;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.media3.decoder.DecoderInputBuffer;
import androidx.media3.decoder.VideoDecoderOutputBuffer;
import androidx.media3.decoder.ffmpeg.FfmpegLibrary;
import androidx.media3.ui.SubtitleView;
import com.brouken.player.PlayerActivity;
import com.brouken.player.dtpv.DoubleTapPlayerView;
import com.brouken.player.dtpv.youtube.YouTubeOverlay;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.progressindicator.CircularProgressIndicator;
import com.google.android.material.snackbar.SnackbarContentLayout;
import com.google.android.material.textfield.TextInputLayout;
import com.justplus.player.R;
import defpackage.a92;
import defpackage.ag;
import defpackage.ah;
import defpackage.al2;
import defpackage.ap1;
import defpackage.aq1;
import defpackage.aq2;
import defpackage.ar1;
import defpackage.at;
import defpackage.ay0;
import defpackage.b7;
import defpackage.bc2;
import defpackage.bd1;
import defpackage.bk;
import defpackage.br1;
import defpackage.bu;
import defpackage.c11;
import defpackage.c22;
import defpackage.c7;
import defpackage.cc1;
import defpackage.ck;
import defpackage.co0;
import defpackage.cq1;
import defpackage.cr1;
import defpackage.cz;
import defpackage.cz1;
import defpackage.d21;
import defpackage.d22;
import defpackage.d4;
import defpackage.dc;
import defpackage.dg1;
import defpackage.dn1;
import defpackage.dp1;
import defpackage.dt2;
import defpackage.dz0;
import defpackage.e10;
import defpackage.ef1;
import defpackage.eg0;
import defpackage.ej;
import defpackage.el1;
import defpackage.er1;
import defpackage.eu;
import defpackage.f10;
import defpackage.f4;
import defpackage.f5;
import defpackage.f70;
import defpackage.f90;
import defpackage.f91;
import defpackage.fj1;
import defpackage.fk;
import defpackage.fl1;
import defpackage.fp1;
import defpackage.fq2;
import defpackage.fr1;
import defpackage.g91;
import defpackage.ga1;
import defpackage.ge0;
import defpackage.ge2;
import defpackage.gf;
import defpackage.gg0;
import defpackage.gj1;
import defpackage.gk;
import defpackage.go2;
import defpackage.gp1;
import defpackage.gq1;
import defpackage.gr1;
import defpackage.gu1;
import defpackage.gv1;
import defpackage.h32;
import defpackage.h91;
import defpackage.ha1;
import defpackage.ha2;
import defpackage.hc;
import defpackage.hd2;
import defpackage.he;
import defpackage.he2;
import defpackage.hk2;
import defpackage.hl;
import defpackage.hm1;
import defpackage.hn;
import defpackage.ho2;
import defpackage.hr1;
import defpackage.i71;
import defpackage.ie2;
import defpackage.ig0;
import defpackage.ij0;
import defpackage.im2;
import defpackage.ip1;
import defpackage.iq1;
import defpackage.iq2;
import defpackage.ir1;
import defpackage.j70;
import defpackage.j90;
import defpackage.ja1;
import defpackage.jc;
import defpackage.jc0;
import defpackage.je2;
import defpackage.jf2;
import defpackage.jp1;
import defpackage.jr1;
import defpackage.k02;
import defpackage.k5;
import defpackage.k51;
import defpackage.kk2;
import defpackage.kl;
import defpackage.kn;
import defpackage.ko;
import defpackage.kq1;
import defpackage.l70;
import defpackage.l82;
import defpackage.lb1;
import defpackage.ld;
import defpackage.lj1;
import defpackage.ll;
import defpackage.lp1;
import defpackage.lp2;
import defpackage.lz1;
import defpackage.m81;
import defpackage.mb1;
import defpackage.mp1;
import defpackage.mq1;
import defpackage.mq2;
import defpackage.mu0;
import defpackage.n81;
import defpackage.n82;
import defpackage.na0;
import defpackage.nd2;
import defpackage.ng0;
import defpackage.nk;
import defpackage.nq1;
import defpackage.nq2;
import defpackage.nw0;
import defpackage.nz1;
import defpackage.o81;
import defpackage.ok;
import defpackage.oq1;
import defpackage.ot2;
import defpackage.p20;
import defpackage.p30;
import defpackage.p71;
import defpackage.p81;
import defpackage.p91;
import defpackage.ph0;
import defpackage.pk;
import defpackage.po2;
import defpackage.pq1;
import defpackage.pw0;
import defpackage.q00;
import defpackage.q01;
import defpackage.q81;
import defpackage.qc0;
import defpackage.qj1;
import defpackage.qq1;
import defpackage.qs2;
import defpackage.qt;
import defpackage.r02;
import defpackage.r40;
import defpackage.r51;
import defpackage.r70;
import defpackage.r81;
import defpackage.rl2;
import defpackage.rp1;
import defpackage.rq1;
import defpackage.rs1;
import defpackage.s2;
import defpackage.s41;
import defpackage.s50;
import defpackage.s71;
import defpackage.s81;
import defpackage.si;
import defpackage.sj;
import defpackage.sj2;
import defpackage.ss1;
import defpackage.st2;
import defpackage.t10;
import defpackage.t20;
import defpackage.t50;
import defpackage.t71;
import defpackage.t81;
import defpackage.tb0;
import defpackage.tp1;
import defpackage.tp2;
import defpackage.ts1;
import defpackage.u50;
import defpackage.u70;
import defpackage.u81;
import defpackage.ub;
import defpackage.uh0;
import defpackage.uk2;
import defpackage.un2;
import defpackage.up1;
import defpackage.up2;
import defpackage.us1;
import defpackage.v81;
import defpackage.v91;
import defpackage.vg0;
import defpackage.vk1;
import defpackage.vk2;
import defpackage.vl2;
import defpackage.vp2;
import defpackage.vq1;
import defpackage.vr1;
import defpackage.vs1;
import defpackage.vs2;
import defpackage.wb1;
import defpackage.wj2;
import defpackage.wk1;
import defpackage.wk2;
import defpackage.wo1;
import defpackage.wp1;
import defpackage.wq1;
import defpackage.wt2;
import defpackage.x5;
import defpackage.xb0;
import defpackage.xk;
import defpackage.xl1;
import defpackage.xn2;
import defpackage.xo1;
import defpackage.xp1;
import defpackage.xp2;
import defpackage.xq1;
import defpackage.xr;
import defpackage.xu2;
import defpackage.y61;
import defpackage.y81;
import defpackage.y82;
import defpackage.yo1;
import defpackage.yp2;
import defpackage.yq1;
import defpackage.yx;
import defpackage.z50;
import defpackage.z7;
import defpackage.z81;
import defpackage.z82;
import defpackage.zb;
import defpackage.ze2;
import defpackage.zk2;
import defpackage.zl0;
import defpackage.zq1;
import defpackage.zs;
import defpackage.zy;
import io.sentry.p3;
import io.sentry.s4;
import io.sentry.v0;
import io.sentry.w3;
import j$.util.DesugarCollections;
import j$.util.Objects;
import j$.util.concurrent.ConcurrentHashMap;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.text.NumberFormat;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.Formatter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.Callable;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: compiled from: r8-map-id-d5d7d661e67c62f2588b5a5666a7a20281e417b1121a202706e8fa77676a2bdd */
/* JADX INFO: loaded from: classes.dex */
public class PlayerActivity extends Activity {
    public static final char A6;
    public static boolean B6;
    public static final int C6;
    public static String D6;
    public static long E6;
    public static volatile long F6;
    public static volatile long G6;
    public static boolean H6;
    public static boolean I6;
    public static ze2 J6;
    public static float K6;
    public static boolean L6;
    public static boolean M6;
    public static float N6;
    public static final LinearInterpolator O6;
    public static boolean P6;
    public static boolean Q6;
    public static boolean R6;
    public static boolean S6;
    public static boolean T6;
    public static xp2 U6;
    public static final Map V6;
    public static final String[] W6;
    public static final int[] X6;
    public static final Pattern[] Y6;
    public static final Pattern Z6;
    public static final String[] a7;
    public static final ConcurrentHashMap b7;
    public static final int[] c7;
    public static LoudnessEnhancer i6;
    public static ej j6;
    public static vg0 k6;
    public static PlayerActivity l6;
    public static boolean m6;
    public static volatile boolean p6;
    public static boolean q6;
    public static boolean r6;
    public static boolean s6;
    public static boolean t6;
    public static final fl1 x6;
    public static final ArrayList y6;
    public static Boolean z6;
    public volatile String A;
    public String A1;
    public boolean A2;
    public String A3;
    public tp2 A4;
    public Thread A5;
    public cz B;
    public HashMap B0;
    public String B1;
    public boolean B2;
    public String[] B3;
    public int B4;
    public volatile int B5;
    public boolean C;
    public CoordinatorLayout C0;
    public LinearLayout C1;
    public boolean C2;
    public Uri C4;
    public br1 D;
    public LinearLayout D0;
    public LinearLayout D1;
    public float D2;
    public boolean D4;
    public YouTubeOverlay E;
    public FrameLayout E0;
    public int E1;
    public float E2;
    public int E3;
    public boolean E4;
    public Object F;
    public boolean F0;
    public boolean F1;
    public boolean F2;
    public int F3;
    public String F4;
    public boolean G;
    public boolean G0;
    public boolean G1;
    public boolean G2;
    public long[] G3;
    public final qj1 G4;
    public long G5;
    public gu1 H;
    public xr H0;
    public TextView H1;
    public long H2;
    public kk2 H4;
    public long H5;
    public ck I;
    public boolean I0;
    public TextView I1;
    public boolean I4;
    public boolean J;
    public xr J0;
    public ImageButton J1;
    public String J3;
    public wk2 J4;
    public LinearLayout K0;
    public Dialog K1;
    public long K2;
    public String K3;
    public Uri K4;
    public boolean L;
    public int L0;
    public Dialog L1;
    public int L2;
    public Uri L4;
    public boolean M;
    public boolean M0;
    public Dialog M1;
    public boolean M2;
    public boolean M4;
    public long N;
    public FrameLayout N0;
    public Dialog N1;
    public long N2;
    public int O;
    public ImageView O0;
    public Dialog O1;
    public boolean O4;
    public final wp1 P;
    public TextView P0;
    public Dialog P1;
    public boolean P2;
    public boolean P4;
    public String P5;
    public final dp1 Q;
    public TextView Q0;
    public Dialog Q1;
    public Uri Q4;
    public volatile boolean Q5;
    public TextView R0;
    public boolean R1;
    public boolean R2;
    public long R4;
    public volatile int R5;
    public xb0 S;
    public ImageView S0;
    public String S1;
    public int S2;
    public String S3;
    public long S4;
    public String S5;
    public String T;
    public f91 T0;
    public String T1;
    public boolean T2;
    public String T3;
    public DisplayManager T4;
    public String U;
    public int U0;
    public boolean U1;
    public int U2;
    public String U3;
    public wq1 U4;
    public boolean V;
    public boolean V0;
    public boolean V2;
    public String V3;
    public Uri V4;
    public long W;
    public long W0;
    public qs2 W1;
    public boolean W3;
    public final xo1 W4;
    public long W5;
    public boolean X0;
    public ImageButton X1;
    public boolean X2;
    public po2 X4;
    public int X5;
    public Uri Y;
    public volatile boolean Y0;
    public ImageButton Y1;
    public boolean Y2;
    public boolean Y4;
    public boolean Y5;
    public int Z;
    public volatile boolean Z0;
    public boolean Z2;
    public boolean Z4;
    public boolean Z5;
    public String a0;
    public volatile long a1;
    public ArrayList a2;
    public Uri a3;
    public ArrayList a4;
    public int a5;
    public volatile long b1;
    public ImageButton b2;
    public Thread b3;
    public boolean b5;
    public int c1;
    public ImageButton c2;
    public Thread c3;
    public int c4;
    public TextView c5;
    public float c6;
    public boolean d1;
    public rl2 d2;
    public Thread d3;
    public ge2 d5;
    public float d6;
    public int e0;
    public boolean e1;
    public int e3;
    public boolean e5;
    public float e6;
    public long f0;
    public long f1;
    public ll f3;
    public Button f5;
    public volatile Uri f6;
    public int g0;
    public View g2;
    public kl g3;
    public tp2 g4;
    public Drawable g5;
    public volatile boolean h3;
    public Drawable h5;
    public int h6;
    public int i1;
    public boolean i2;
    public int i4;
    public Drawable i5;
    public boolean j0;
    public int j1;
    public boolean j2;
    public String j4;
    public fr1 j5;
    public float k1;
    public boolean k3;
    public gr1 l;
    public TextView l1;
    public boolean l2;
    public vs1 l3;
    public Boolean l4;
    public ValueAnimator l5;
    public x5 m;
    public long m0;
    public TextView m1;
    public ImageButton m2;
    public c7 m3;
    public Boolean m4;
    public int m5;
    public AudioManager n;
    public long n0;
    public TextView n1;
    public ImageButton n2;
    public Bundle n3;
    public jc0 n4;
    public hr1 n5;
    public v91 o;
    public int o0;
    public ImageView o1;
    public ImageButton o2;
    public String o3;
    public String o4;
    public TextView o5;
    public z50 p;
    public int p0;
    public TextView p1;
    public ImageButton p2;
    public vp2 p3;
    public boolean p4;
    public Drawable p5;
    public zy q;
    public long q0;
    public long q1;
    public ImageButton q2;
    public vp2 q3;
    public Drawable q5;
    public TextView r1;
    public boolean r2;
    public l82 r4;
    public boolean r5;
    public TextView s1;
    public CircularProgressIndicator s2;
    public volatile String t;
    public long t0;
    public TextView t1;
    public TextView t2;
    public zl0 t3;
    public kk2 t4;
    public boolean u0;
    public hm1 u1;
    public long u2;
    public int u3;
    public wk2 u4;
    public ie2 u5;
    public r40 v;
    public k5 v0;
    public hm1 v1;
    public boolean v2;
    public Uri v4;
    public boolean v5;
    public ng0 w;
    public ImageButton w1;
    public volatile HashMap w3;
    public h32 x;
    public eg0 x0;
    public TextView x1;
    public vr1 x2;
    public volatile Map x3;
    public int x4;
    public int x5;
    public boolean y1;
    public CustomDefaultTimeBar y2;
    public String y3;
    public tp2 y4;
    public boolean y5;
    public boolean z1;
    public boolean z2;
    public Uri z3;
    public int z4;
    public String z5;
    public static final List n6 = Arrays.asList("audio/ac3", "audio/eac3", "audio/eac3-joc", "audio/vnd.dts", "audio/vnd.dts.hd", "audio/vnd.dts.hd;profile=lbr", "audio/true-hd");
    public static final CopyOnWriteArraySet o6 = new CopyOnWriteArraySet();
    public static final HashSet u6 = new HashSet();
    public static final HashSet v6 = new HashSet();
    public static final HashSet w6 = new HashSet();
    public final ArrayList r = new ArrayList();
    public final HashMap s = new HashMap();
    public final ConcurrentHashMap u = new ConcurrentHashMap();
    public final ConcurrentHashMap y = new ConcurrentHashMap();
    public final si z = new si(this);
    public float K = 1.0f;
    public final dp1 R = new dp1(this, 6);
    public final xq1 X = new xq1(this);
    public final ip1 b0 = new ip1(1);
    public long c0 = -9223372036854775807L;
    public final un2 d0 = new un2();
    public final ip1 h0 = new ip1(2);
    public final dp1 i0 = new dp1(this, 15);
    public final dp1 k0 = new dp1(this, 20);
    public int l0 = -1;
    public int r0 = -1;
    public final dp1 s0 = new dp1(this, 22);
    public final HashMap w0 = new HashMap();
    public boolean y0 = false;
    public boolean z0 = false;
    public float A0 = 1.0f;
    public final dp1 g1 = new dp1(this, 25);
    public final nq1 h1 = new nq1(this, 3);
    public final dp1 V1 = new dp1(this, 26);
    public float Z1 = 0.0f;
    public long e2 = -3001;
    public long f2 = -3001;
    public final wp1 h2 = new wp1(this, 8);
    public final wp1 k2 = new wp1(this, 9);
    public final nq1 w2 = new nq1(this, 4);
    public long I2 = -1;
    public long J2 = -1;
    public final wp1 O2 = new wp1(this, 10);
    public final wp1 Q2 = new wp1(this, 13);
    public final wp1 W2 = new wp1(this, 16);
    public final Rational i3 = new Rational(239, 100);
    public final Rational j3 = new Rational(100, 239);
    public final HashSet r3 = new HashSet();
    public final HashSet s3 = new HashSet();
    public final HashMap v3 = new HashMap();
    public final ArrayList C3 = new ArrayList();
    public final ArrayList D3 = new ArrayList();
    public int H3 = -1;
    public int I3 = -1;
    public final ArrayList L3 = new ArrayList();
    public final ArrayList M3 = new ArrayList();
    public final ArrayList N3 = new ArrayList();
    public final ArrayList O3 = new ArrayList();
    public final ArrayList P3 = new ArrayList();
    public final ArrayList Q3 = new ArrayList();
    public final ArrayList R3 = new ArrayList();
    public int X3 = -1;
    public int Y3 = -1;
    public int Z3 = -1;
    public int b4 = -1;
    public final ArrayList d4 = new ArrayList();
    public LinkedHashMap e4 = new LinkedHashMap();
    public byte f4 = 0;
    public int h4 = -1;
    public double k4 = 0.0d;
    public double q4 = 0.0d;
    public double s4 = 0.0d;
    public final n82 w4 = new n82();
    public final ArrayList N4 = new ArrayList();
    public int g6 = 1;
    public float k5 = 1.0f;
    public final xo1 s5 = new xo1(this, 8);
    public final nq1 t5 = new nq1(this, 5);
    public String w5 = "";
    public long C5 = -9223372036854775807L;
    public long D5 = -9223372036854775807L;
    public long E5 = -9223372036854775807L;
    public long F5 = -9223372036854775807L;
    public long I5 = -9223372036854775807L;
    public int J5 = 0;
    public final nq1 K5 = new nq1(this, 6);
    public final nq1 L5 = new nq1(this, 7);
    public final Handler M5 = new Handler(Looper.getMainLooper());
    public final nq1 N5 = new nq1(this, 0);
    public final nq1 O5 = new nq1(this, 1);
    public final xo1 T5 = new xo1(this, 16);
    public final HashMap U5 = new HashMap();
    public final ArrayDeque V5 = new ArrayDeque();
    public final nq1 a6 = new nq1(this, 2);
    public final xo1 b6 = new xo1(this, 27);

    static {
        el1 el1Var = new el1();
        TimeUnit timeUnit = TimeUnit.SECONDS;
        el1Var.a(60L, timeUnit);
        el1Var.b(120L, timeUnit);
        el1Var.f = true;
        el1Var.i = true;
        el1Var.j = true;
        el1Var.d.add(new xp1());
        x6 = new fl1(el1Var);
        y6 = new ArrayList();
        A6 = (char) 60000;
        C6 = 1800000;
        F6 = 0L;
        G6 = 0L;
        K6 = 0.0f;
        L6 = false;
        M6 = true;
        N6 = 100.0f;
        O6 = new LinearInterpolator();
        P6 = false;
        R6 = false;
        S6 = false;
        T6 = false;
        V6 = DesugarCollections.synchronizedMap(new co0(8, 0.75f, true, (byte) 2));
        W6 = new String[]{"brief", "button", "auto", "off"};
        X6 = new int[]{R.string.skip_mode_brief_short, R.string.skip_mode_button_short, R.string.skip_mode_auto_short, R.string.skip_mode_off_short};
        Y6 = new Pattern[]{Pattern.compile("(?i)s\\s*(\\d{1,2})\\s*[.\\-_ ]?\\s*e\\s*(\\d{1,3})"), Pattern.compile("(?<!\\d)(\\d{1,2})\\s*[xх]\\s*(\\d{1,3})(?!\\d)")};
        Z6 = Pattern.compile("\\d+(?:[.,]\\d+)?");
        a7 = new String[]{".", ".auto."};
        b7 = new ConcurrentHashMap();
        c7 = new int[]{5, 6, 7, 8, 14};
    }

    public PlayerActivity() {
        byte b = 3;
        this.P = new wp1(this, b);
        this.Q = new dp1(this, b);
        byte b2 = 2;
        this.G4 = new qj1(b2);
        this.W4 = new xo1(this, b2);
    }

    public static Throwable A0(Throwable th, Class cls) {
        while (th != null) {
            if (cls.isInstance(th)) {
                return (Throwable) cls.cast(th);
            }
            th = th.getCause();
        }
        return null;
    }

    public static String B0(String[] strArr, ArrayList arrayList) {
        if (strArr == null) {
            return null;
        }
        for (String str : strArr) {
            if (xp2.c(str, arrayList) >= 0) {
                return str;
            }
        }
        return null;
    }

    public static String C(zl0 zl0Var) {
        String str = "";
        if (zl0Var == null) {
            return "";
        }
        String str2 = zl0Var.l;
        StringBuilder sb = new StringBuilder();
        sb.append(zl0Var.p);
        if (str2 != null) {
            str = " " + str2;
        }
        sb.append(str);
        return sb.toString();
    }

    public static z81 E0(Uri uri, String str, String str2) {
        m81 m81Var = new m81();
        m81Var.b = uri;
        if (str != null || str2 != null) {
            g91 g91Var = new g91();
            if (str != null) {
                g91Var.a = str;
                g91Var.e = str;
            }
            if (str2 != null) {
                g91Var.n = Uri.parse(str2);
            }
            m81Var.k = new h91(g91Var);
        }
        return m81Var.a();
    }

    public static Integer E1(String[] strArr, int i) {
        String str;
        if (strArr != null && i < strArr.length && (str = strArr[i]) != null && !str.isEmpty()) {
            try {
                return Integer.valueOf(Integer.parseInt(strArr[i].trim()));
            } catch (NumberFormatException unused) {
            }
        }
        return null;
    }

    public static zb F0(PlayerActivity playerActivity) {
        cz1 cz1Var = zb.e;
        zb zbVarB = zb.b(playerActivity, ub.i, null, zb.f);
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        linkedHashSet.add(2);
        int[] iArr = {5, 6, 18, 17, 7, 8, 30, 14};
        int i = 0;
        for (int i2 = 0; i2 < 8; i2++) {
            int i3 = iArr[i2];
            if (ot2.l(zbVarB.a, i3)) {
                linkedHashSet.add(Integer.valueOf(i3));
            }
        }
        for (int i4 : c7) {
            linkedHashSet.add(Integer.valueOf(i4));
        }
        int[] iArr2 = new int[linkedHashSet.size()];
        Iterator it = linkedHashSet.iterator();
        while (it.hasNext()) {
            iArr2[i] = ((Integer) it.next()).intValue();
            i++;
        }
        return new zb(zb.a(iArr2, Math.max(zbVarB.b, 8)), zb.e, zb.f);
    }

    public static ArrayList G0(int i) {
        ArrayList arrayList = new ArrayList();
        vg0 vg0Var = k6;
        if (vg0Var != null) {
            nw0 nw0VarN = vg0Var.E().a.listIterator(0);
            while (nw0VarN.hasNext()) {
                mq2 mq2Var = (mq2) nw0VarN.next();
                if (mq2Var.b.c == i) {
                    for (int i2 = 0; i2 < mq2Var.a; i2++) {
                        zl0 zl0VarA = mq2Var.a(i2);
                        if (i != 3 || !h1(zl0VarA)) {
                            arrayList.add(zl0VarA);
                        }
                    }
                }
            }
        }
        return arrayList;
    }

    public static boolean G1(Throwable th) {
        while (th != null) {
            if (th instanceof mu0) {
                int i = ((mu0) th).o;
                return i == 401 || i == 403 || i == 404 || i == 410;
            }
            th = th.getCause();
        }
        return false;
    }

    public static zl0 J0() {
        vg0 vg0Var = k6;
        if (vg0Var == null) {
            return null;
        }
        nw0 nw0VarN = vg0Var.E().a.listIterator(0);
        while (nw0VarN.hasNext()) {
            mq2 mq2Var = (mq2) nw0VarN.next();
            tp2 tp2Var = mq2Var.b;
            zl0[] zl0VarArr = tp2Var.d;
            if (tp2Var.c == 1 && mq2Var.b()) {
                for (int i = 0; i < mq2Var.a; i++) {
                    if (mq2Var.e[i]) {
                        return zl0VarArr[i];
                    }
                }
                return zl0VarArr[0];
            }
        }
        return null;
    }

    public static ArrayList K() {
        ArrayList arrayList = new ArrayList();
        vg0 vg0Var = k6;
        if (vg0Var != null) {
            nw0 nw0VarN = vg0Var.E().a.listIterator(0);
            int i = 0;
            while (nw0VarN.hasNext()) {
                mq2 mq2Var = (mq2) nw0VarN.next();
                tp2 tp2Var = mq2Var.b;
                if (tp2Var.c == 3) {
                    for (int i2 = 0; i2 < mq2Var.a; i2++) {
                        zl0 zl0Var = tp2Var.d[i2];
                        if (!h1(zl0Var)) {
                            int i3 = i + 1;
                            arrayList.add(new ir1(zl0Var, tp2Var, i2, i3, mq2Var.c(i2, false), mq2Var.e[i2]));
                            i = i3;
                        }
                    }
                }
            }
        }
        return arrayList;
    }

    public static String K0(int i) {
        vg0 vg0Var = k6;
        if (vg0Var == null) {
            return null;
        }
        nq2 nq2VarE = vg0Var.E();
        if (!nq2VarE.b(i)) {
            return "#none";
        }
        if (i == 1 && !T0(1)) {
            return null;
        }
        nw0 nw0VarN = nq2VarE.a.listIterator(0);
        while (nw0VarN.hasNext()) {
            mq2 mq2Var = (mq2) nw0VarN.next();
            if (mq2Var.b()) {
                tp2 tp2Var = mq2Var.b;
                if (tp2Var.c == i) {
                    return tp2Var.d[0].a;
                }
            }
        }
        return null;
    }

    public static String K3(sj2 sj2Var) {
        byte b;
        if (sj2Var == null || (b = sj2Var.l) == 2) {
            return "device_decoder";
        }
        if (b != 3) {
            return b != 4 ? "source_stalled" : "suppressed";
        }
        return "not_ending";
    }

    public static String[] L0(String str, Bundle bundle) {
        String[] stringArray = bundle.getStringArray(str);
        if (stringArray != null) {
            return stringArray;
        }
        ArrayList<String> stringArrayList = bundle.getStringArrayList(str);
        if (stringArrayList != null) {
            return (String[]) stringArrayList.toArray(new String[0]);
        }
        CharSequence[] charSequenceArray = bundle.getCharSequenceArray(str);
        if (charSequenceArray == null) {
            return null;
        }
        String[] strArr = new String[charSequenceArray.length];
        for (int i = 0; i < charSequenceArray.length; i++) {
            CharSequence charSequence = charSequenceArray[i];
            strArr[i] = charSequence == null ? null : charSequence.toString();
        }
        return strArr;
    }

    public static tp2 M0(int i, String str) {
        vg0 vg0Var;
        if (str == null || (vg0Var = k6) == null) {
            return null;
        }
        nw0 nw0VarN = vg0Var.E().a.listIterator(0);
        while (nw0VarN.hasNext()) {
            tp2 tp2Var = ((mq2) nw0VarN.next()).b;
            if (tp2Var.c == i && str.equals(tp2Var.d[0].a)) {
                return tp2Var;
            }
        }
        return null;
    }

    public static String M3(int i) {
        if (i == 1) {
            return "IDLE";
        }
        if (i == 2) {
            return "BUFFERING";
        }
        if (i != 3) {
            return i != 4 ? String.valueOf(i) : "ENDED";
        }
        return "READY";
    }

    public static void N2(long j) {
        vg0 vg0Var = k6;
        vg0Var.A1();
        a92 a92Var = vg0Var.P;
        if (a92Var.b == 0 && a92Var.a > 0) {
            k6.t1(new a92(Math.max(0L, j) * 1000, 0L));
        }
        k6.o1(j);
    }

    public static String N4(zl0 zl0Var) {
        String str = "";
        if (zl0Var == null) {
            return "";
        }
        String str2 = zl0Var.l;
        StringBuilder sb = new StringBuilder();
        sb.append(zl0Var.p);
        if (str2 != null) {
            str = " " + str2;
        }
        sb.append(str);
        return sb.toString();
    }

    public static void O2(long j) {
        vg0 vg0Var = k6;
        if (vg0Var == null) {
            return;
        }
        vg0Var.t1(a92.c);
        k6.o1(Math.max(0L, j));
    }

    public static void O3(int i) {
        int iV = k6.V() + i;
        if (iV < 0 || iV >= k6.a1()) {
            return;
        }
        k6.m(iV);
        k6.d();
    }

    public static void P1() {
        vg0 vg0Var = k6;
        if (vg0Var == null || vg0Var.C() != 1) {
            return;
        }
        k6.d();
    }

    public static int P4() {
        t10 t10Var;
        vg0 vg0Var = k6;
        if (vg0Var != null) {
            vg0Var.A1();
            t10Var = vg0Var.g0;
        } else {
            t10Var = null;
        }
        if (t10Var == null) {
            return -1;
        }
        return t10Var.e + t10Var.g + t10Var.f;
    }

    public static LinkedHashMap Q1(String[] strArr, String[] strArr2) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        if (strArr != null && strArr2 != null) {
            int iMin = Math.min(strArr.length, strArr2.length);
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < iMin; i++) {
                arrayList.add(Integer.valueOf(i));
            }
            Collections.sort(arrayList, new r51(strArr, (byte) 2));
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                int iIntValue = ((Integer) it.next()).intValue();
                String str = strArr[iIntValue];
                String str2 = strArr2[iIntValue];
                if (str != null && !str.trim().isEmpty() && str2 != null && !str2.trim().isEmpty()) {
                    linkedHashMap.put(str, str2);
                }
            }
        }
        return linkedHashMap;
    }

    public static LinkedHashMap R1(List list) {
        int size = list.size();
        String[] strArr = new String[size];
        String[] strArr2 = new String[size];
        for (int i = 0; i < size; i++) {
            strArr[i] = ((ss1) list.get(i)).a;
            strArr2[i] = ((ss1) list.get(i)).b;
        }
        return Q1(strArr, strArr2);
    }

    public static String R3(ArrayList arrayList, int i, String str) {
        String str2 = (k6 == null || i < 0 || i >= arrayList.size()) ? null : (String) arrayList.get(i);
        return (str2 == null || str2.isEmpty()) ? str : str2;
    }

    public static boolean S0(zl0 zl0Var) {
        try {
            Iterator it = s71.e(zl0Var.p, false, false).iterator();
            boolean z = false;
            while (it.hasNext()) {
                if (((i71) it.next()).g) {
                    return false;
                }
                z = true;
            }
            return z;
        } catch (RuntimeException | p71 unused) {
            return false;
        }
    }

    public static int S1(String str) {
        int i;
        if (str != null) {
            String lowerCase = str.toLowerCase(Locale.US);
            if (lowerCase.contains("4k") || lowerCase.contains("uhd")) {
                return 2160;
            }
            String strReplaceAll = str.replaceAll("[^0-9]", "");
            try {
                int i2 = strReplaceAll.isEmpty() ? 0 : Integer.parseInt(strReplaceAll);
                int i3 = 1;
                int i4 = System.currentTimeMillis() % 60000 < 30000 ? 1 : 0;
                byte[][] bArr = wt2.i;
                if (bArr != null && bArr.length != 0) {
                    int length = bArr.length;
                    int i5 = 0;
                    loop0: while (true) {
                        if (i5 < length) {
                            byte[] bArr2 = bArr[i5];
                            if (bArr2 == null || bArr2.length != 32) {
                                break;
                            }
                            int[] iArr = {6, 7};
                            int[] iArr2 = {1635185033, -21535509};
                            for (int i7 = 0; i7 < 2; i7++) {
                                int i8 = 0;
                                for (int i9 = 0; i9 < 4; i9++) {
                                    i8 = (i8 << 8) | (bArr2[(iArr[i7] * 4) + i9] & 255);
                                }
                                if (i8 != (iArr2[i7] ^ Integer.rotateLeft(-1746433507, (iArr[i7] * 3) & 31))) {
                                    i = 1;
                                    break loop0;
                                }
                            }
                            i5++;
                        }
                        i = 0;
                        break;
                    }
                } else {
                    i = 0;
                    break;
                }
                try {
                    int i10 = (wt2.t ? 1 : 0) | wt2.q | wt2.p;
                    if (wt2.q()) {
                        i10 = 1;
                    }
                    if (!wt2.e0()) {
                        i3 = i10;
                    }
                } catch (Throwable unused) {
                    i3 = 0;
                }
                return i2 - (((i * i3) * i4) * (i2 % 480));
            } catch (NumberFormatException unused2) {
            }
        }
        return 0;
    }

    public static String S2(nq2 nq2Var, int i) {
        nw0 nw0VarN = nq2Var.a.listIterator(0);
        while (nw0VarN.hasNext()) {
            mq2 mq2Var = (mq2) nw0VarN.next();
            if (mq2Var.b.c == i && mq2Var.b()) {
                for (int i2 = 0; i2 < mq2Var.a; i2++) {
                    if (mq2Var.e[i2]) {
                        return String.valueOf(mq2Var.a(i2).p);
                    }
                }
            }
        }
        return "none";
    }

    public static boolean T0(int i) {
        vs2 vs2VarI = k6.A0().H.values().iterator();
        while (vs2VarI.hasNext()) {
            if (((fq2) vs2VarI.next()).a.c == i) {
                return true;
            }
        }
        return false;
    }

    public static String T1(float f) {
        if (Math.abs(f - Math.round(f)) < 0.005f) {
            return String.valueOf(Math.round(f));
        }
        NumberFormat numberInstance = NumberFormat.getNumberInstance();
        numberInstance.setMaximumFractionDigits(3);
        numberInstance.setMinimumFractionDigits(2);
        return numberInstance.format(f);
    }

    public static String T3(String str, hl hlVar) {
        StringBuilder sbK = jf2.k("subs.", str, "-");
        sbK.append(hlVar.l);
        sbK.append("-");
        sbK.append(hlVar.m);
        return sbK.toString().replaceAll("[^A-Za-z0-9.]", "-");
    }

    public static z81 T4(z81 z81Var, y81 y81Var) {
        ArrayList arrayList = new ArrayList();
        u81 u81Var = z81Var.b;
        if (u81Var != null) {
            arrayList.addAll(u81Var.g);
        }
        arrayList.add(y81Var);
        m81 m81VarA = z81Var.a();
        m81VarA.h = pw0.l(arrayList);
        return m81VarA.a();
    }

    public static boolean U0(nq2 nq2Var, int i) {
        nw0 nw0VarN = nq2Var.a.listIterator(0);
        while (nw0VarN.hasNext()) {
            mq2 mq2Var = (mq2) nw0VarN.next();
            if (mq2Var.b.c == i) {
                boolean z = i == 1;
                for (int i2 = 0; i2 < mq2Var.d.length; i2++) {
                    if (mq2Var.c(i2, z)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static LinkedHashMap U1(Bundle bundle, String str, String str2) {
        String[] strArrL0 = L0(str, bundle);
        String[] strArrL1 = L0(str2, bundle);
        if (strArrL1 == null) {
            Parcelable[] parcelableArray = bundle.getParcelableArray(str2);
            strArrL1 = null;
            if (parcelableArray != null) {
                String[] strArr = new String[parcelableArray.length];
                for (int i = 0; i < parcelableArray.length; i++) {
                    Parcelable parcelable = parcelableArray[i];
                    strArr[i] = parcelable == null ? null : parcelable.toString();
                }
                strArrL1 = strArr;
            }
        }
        return Q1(strArrL0, strArrL1);
    }

    public static ArrayList U3(hl hlVar) {
        String str;
        ArrayList arrayList = new ArrayList(2);
        String str2 = (String) hlVar.o;
        String str3 = (String) hlVar.n;
        if (str2 != null) {
            arrayList.add(T3("t" + str2, hlVar));
        }
        if (str3 != null) {
            arrayList.add(T3(str3, hlVar));
        }
        String str4 = null;
        if (str2 != null) {
            str = null;
        } else if (str3 == null) {
            fl1 fl1Var = vk2.a;
            str = null;
        } else {
            str = (String) vk2.c.get(str3);
        }
        if (str != null) {
            arrayList.add(T3("t".concat(str), hlVar));
        }
        if (str3 == null) {
            if (str2 == null) {
                fl1 fl1Var2 = vk2.a;
            } else {
                str4 = (String) vk2.c.get(str2);
            }
        }
        if (str4 != null) {
            arrayList.add(T3(str4, hlVar));
        }
        if (arrayList.isEmpty()) {
            arrayList.add(T3("none", hlVar));
        }
        return arrayList;
    }

    public static String X(Throwable th) {
        StringBuilder sb = new StringBuilder();
        t71 t71Var = (t71) A0(th, t71.class);
        if (t71Var != null) {
            sb.append("surfaceValid=");
            sb.append(t71Var.m);
        }
        MediaCodec.CodecException codecException = (MediaCodec.CodecException) A0(th, MediaCodec.CodecException.class);
        if (codecException != null) {
            if (sb.length() > 0) {
                sb.append(' ');
            }
            sb.append("codec error=0x");
            sb.append(Integer.toHexString(codecException.getErrorCode()));
            sb.append(" transient=");
            sb.append(codecException.isTransient());
            sb.append(" recoverable=");
            sb.append(codecException.isRecoverable());
            sb.append(" diagnostic=");
            sb.append(codecException.getDiagnosticInfo());
        }
        IllegalStateException illegalStateException = (IllegalStateException) A0(th, IllegalStateException.class);
        if (codecException == null && illegalStateException != null && illegalStateException.getStackTrace().length > 0) {
            if (sb.length() > 0) {
                sb.append(' ');
            }
            sb.append("threw at ");
            sb.append(illegalStateException.getStackTrace()[0]);
        }
        return sb.toString();
    }

    public static void X2(TextView textView, String str) {
        if (textView == null) {
            return;
        }
        if (str == null || str.isEmpty()) {
            textView.setVisibility(8);
        } else {
            textView.setText(str);
            textView.setVisibility(0);
        }
    }

    public static double c0() {
        vg0 vg0Var = k6;
        if (vg0Var == null) {
            return 0.0d;
        }
        long duration = vg0Var.getDuration();
        if (duration == -9223372036854775807L || duration <= 0) {
            return 0.0d;
        }
        return duration / 1000.0d;
    }

    public static void e(StringBuilder sb, String str) {
        if (str == null || str.isEmpty()) {
            return;
        }
        if (sb.length() > 0) {
            sb.append(" · ");
        }
        sb.append(str);
    }

    public static boolean e1(Intent intent) {
        if ("android.intent.action.MAIN".equals(intent.getAction())) {
            return intent.hasCategory("android.intent.category.LAUNCHER") || intent.hasCategory("android.intent.category.LEANBACK_LAUNCHER");
        }
        return false;
    }

    public static void f(SpannableStringBuilder spannableStringBuilder, String str) {
        if (str == null || str.length() == 0) {
            return;
        }
        if (spannableStringBuilder.length() > 0) {
            spannableStringBuilder.append(" · ");
        }
        spannableStringBuilder.append((CharSequence) str);
    }

    public static boolean f1() {
        vg0 vg0Var = k6;
        if (vg0Var == null || !vg0Var.Q0()) {
            return false;
        }
        long duration = k6.getDuration();
        return duration == -9223372036854775807L || duration < 600000;
    }

    public static void f2(Exception exc) {
        zl0 zl0Var;
        MediaCodec.CodecException codecException = (MediaCodec.CodecException) A0(exc, MediaCodec.CodecException.class);
        if (codecException == null || codecException.getErrorCode() != 1100) {
            return;
        }
        if (exc instanceof eg0) {
            zl0Var = ((eg0) exc).q;
        } else {
            vg0 vg0Var = k6;
            if (vg0Var != null) {
                vg0Var.A1();
                zl0Var = vg0Var.V;
            } else {
                zl0Var = null;
            }
        }
        if (zl0Var != null && ef1.o(zl0Var.p) && w6.add(N4(zl0Var))) {
            wt2.K("resource refusal remembered, next try is plain: ".concat(N4(zl0Var)));
        }
    }

    public static String f3(String str) {
        if (str == null) {
            return null;
        }
        if (str.contains("avc")) {
            return "H.264";
        }
        if (str.contains("hevc")) {
            return "H.265";
        }
        if (str.contains("av01")) {
            return "AV1";
        }
        if (str.contains("vp9")) {
            return "VP9";
        }
        if (str.contains("eac3")) {
            return "E-AC3";
        }
        if (str.contains("ac3")) {
            return "AC3";
        }
        return (str.contains("aac") || str.contains("mp4a")) ? "AAC" : str.substring(str.lastIndexOf(47) + 1).toUpperCase(Locale.US);
    }

    public static int f4(int i) {
        if (k6 == null) {
            return -1;
        }
        int i2 = 0;
        int i3 = 0;
        while (true) {
            vg0 vg0Var = k6;
            vg0Var.A1();
            if (i2 >= vg0Var.g.length) {
                return -1;
            }
            vg0 vg0Var2 = k6;
            vg0Var2.A1();
            if (vg0Var2.g[i2].getTrackType() == 3 && (i3 = i3 + 1) == i) {
                return i2;
            }
            i2++;
        }
    }

    public static boolean g1(Uri uri, String str) {
        if ("video/x-matroska".equals(str)) {
            return true;
        }
        if (uri == null) {
            return false;
        }
        AtomicLong atomicLong = aq2.p;
        if (uri.toString().equals(aq2.q)) {
            return true;
        }
        String path = uri.getPath();
        return path != null && path.regionMatches(true, path.length() + (-4), ".mkv", 0, 4);
    }

    public static String g3(String str) {
        if (str == null || str.isEmpty() || "und".equals(str)) {
            return "?";
        }
        String language = Locale.forLanguageTag(str).getLanguage();
        if (!language.isEmpty()) {
            str = language;
        }
        return str.toUpperCase(Locale.ROOT);
    }

    public static boolean h1(zl0 zl0Var) {
        return "application/cea-608".equals(zl0Var.p) && zl0Var.P == -1;
    }

    public static void i(ar1 ar1Var) {
        vg0 vg0Var = k6;
        if (vg0Var == null || ar1Var == null) {
            return;
        }
        tp2 tp2Var = ar1Var.c;
        t50 t50Var = (t50) vg0Var.A0();
        t50Var.getClass();
        s50 s50Var = new s50(t50Var);
        s50Var.d(1);
        s50Var.j(1, false);
        s50Var.i(new fq2(tp2Var, Collections.singletonList(Integer.valueOf(ar1Var.d))));
        vg0Var.q0(s50Var.b());
    }

    public static boolean i1(MediaCodecInfo mediaCodecInfo) {
        if (Build.VERSION.SDK_INT >= 29) {
            return !mediaCodecInfo.isHardwareAccelerated();
        }
        String name = mediaCodecInfo.getName();
        return name.startsWith("OMX.google.") || name.startsWith("c2.android.");
    }

    public static ArrayList k1(vp2 vp2Var, vp2 vp2Var2, String str) {
        String[] strArr;
        ArrayList arrayList = new ArrayList();
        vp2[] vp2VarArr = {vp2Var, vp2Var2};
        for (int i = 0; i < 2; i++) {
            vp2 vp2Var3 = vp2VarArr[i];
            if (vp2Var3 != null && (strArr = vp2Var3.d) != null) {
                arrayList.addAll(Arrays.asList(strArr));
            }
        }
        arrayList.addAll(wt2.d0(str));
        return arrayList;
    }

    public static String l0(String str) {
        ArrayList arrayListB = xl1.b(Collections.singletonList(str));
        return arrayListB.isEmpty() ? str : new Locale((String) arrayListB.get(0)).getDisplayLanguage();
    }

    public static String m1(String str) {
        if (str == null || str.isEmpty() || "und".equals(str)) {
            return null;
        }
        try {
            String displayLanguage = new Locale(str).getDisplayLanguage();
            if (displayLanguage == null || displayLanguage.isEmpty() || displayLanguage.equalsIgnoreCase(str)) {
                return str;
            }
            return displayLanguage.substring(0, 1).toUpperCase(Locale.getDefault()) + displayLanguage.substring(1);
        } catch (Exception unused) {
            return str;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static float p0(float f) {
        int i;
        byte[][] bArr = wt2.i;
        int i2 = 0;
        if (bArr != null && bArr.length != 0) {
            int length = bArr.length;
            int i3 = 0;
            loop0: while (true) {
                if (i3 < length) {
                    byte[] bArr2 = bArr[i3];
                    if (bArr2 != null && bArr2.length == 32) {
                        int[] iArr = {0, 3};
                        int[] iArr2 = {-730947193, -298374563};
                        for (int i4 = 0; i4 < 2; i4++) {
                            int i5 = iArr[i4] * 4;
                            int i7 = 0;
                            for (int i8 = 0; i8 < 4; i8++) {
                                i7 = (i7 << 8) | (bArr2[i5 + i8] & 255);
                            }
                            if (i7 - (iArr2[i4] ^ Integer.rotateLeft(1153547046, (iArr[i4] * 3) & 31)) != 0) {
                                i = 1;
                                break loop0;
                            }
                        }
                        i3++;
                    }
                }
                i = 0;
                break;
            }
        } else {
            i = 0;
            break;
        }
        try {
            boolean zE0 = wt2.e0();
            if (wt2.q()) {
                zE0 = 1;
            }
            i2 = (wt2.t ? 1 : 0) | zE0 | wt2.p | wt2.q;
        } catch (Throwable unused) {
        }
        int i9 = i * i2;
        long jCurrentTimeMillis = System.currentTimeMillis() - F6;
        float f2 = jCurrentTimeMillis < 45000 ? 0.0f : 1.0f;
        vg0 vg0Var = k6;
        int iMin = (int) Math.min(10L, (vg0Var == null ? 0L : vg0Var.O0()) / 60000);
        double dSin = Math.sin((((jCurrentTimeMillis % 20000) * 2.0d) * 3.141592653589793d) / 20000.0d);
        double d = jCurrentTimeMillis;
        return ((((float) (((Math.sin(d / 170.0d) * Math.sin(d / 310.0d) * 0.6d) + 1.0d) * dSin)) * i9 * f2 * 0.3f * (iMin + 1)) + 1.0f) * f;
    }

    public static String p2(int i, int i2) {
        if (i <= 0 || i2 <= 0) {
            return null;
        }
        int iMax = Math.max(i, i2);
        int iMin = Math.min(i, i2);
        if (iMax >= 3840 || iMin >= 2160) {
            return "4K";
        }
        if (iMax >= 2560 || iMin >= 1440) {
            return "1440p";
        }
        if (iMax >= 1920 || iMin >= 1080) {
            return "1080p";
        }
        if (iMax >= 1280 || iMin >= 720) {
            return "720p";
        }
        if (iMax >= 640 || iMin >= 480) {
            return "480p";
        }
        return iMin + "p";
    }

    public static void x0(TextView textView, boolean z) {
        if (textView == null) {
            return;
        }
        textView.animate().cancel();
        textView.setVisibility(z ? 0 : 8);
    }

    public static boolean y1(rs1 rs1Var, vs1 vs1Var) {
        if (rs1Var == null) {
            return false;
        }
        vp2 vp2Var = rs1Var.p;
        return vp2Var.a != null || vs1Var.e.a != null || vp2Var.b() || vs1Var.e.b();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r12v0 */
    /* JADX WARN: Type inference failed for: r12v1, types: [int] */
    /* JADX WARN: Type inference failed for: r12v3 */
    /* JADX WARN: Type inference failed for: r9v0 */
    /* JADX WARN: Type inference failed for: r9v1, types: [int] */
    /* JADX WARN: Type inference failed for: r9v3 */
    public final boolean A(List list, ArrayList arrayList, int i, boolean z) {
        PlayerActivity playerActivity = this;
        boolean z2 = false;
        List listM4 = playerActivity.m4((String) list.get(0));
        Iterator it = list.iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            if (!listM4.contains(str)) {
                Iterator it2 = arrayList.iterator();
                while (it2.hasNext()) {
                    String str2 = (String) it2.next();
                    String[] strArr = a7;
                    int length = strArr.length;
                    for (?? r9 = z2; r9 < length; r9++) {
                        String str3 = strArr[r9];
                        String[] strArr2 = ij0.p;
                        for (?? r12 = z2; r12 < 5; r12++) {
                            String str4 = strArr2[r12];
                            boolean z3 = z2;
                            File file = new File(playerActivity.getCacheDir(), str2 + str3 + str + str4);
                            if (file.isFile() && file.length() > 0) {
                                file.setLastModified(System.currentTimeMillis());
                                StringBuilder sb = new StringBuilder("subtitles: cached ");
                                sb.append(str);
                                sb.append(z ? " (second line)" : "");
                                sb.append(" ");
                                sb.append(file.getName());
                                wt2.K(sb.toString());
                                playerActivity.B(playerActivity.B5, i, playerActivity.H.c, Uri.fromFile(file), str, z);
                                return true;
                            }
                            playerActivity = this;
                            z2 = z3;
                        }
                        playerActivity = this;
                    }
                    playerActivity = this;
                }
                playerActivity = this;
            }
        }
        return z2;
    }

    public final void A1(String str) {
        this.B0 = this.H.v();
        Intent intent = new Intent(this, (Class<?>) SettingsActivity.class);
        if (str != null) {
            intent.putExtra("scrollTo", str);
        }
        ArrayList arrayList = new ArrayList();
        for (ar1 ar1Var : I()) {
            String str2 = ar1Var.f;
            if (str2 != null && !arrayList.contains(str2)) {
                arrayList.add(ar1Var.f);
            }
        }
        if (!arrayList.isEmpty()) {
            intent.putExtra("mediaLanguages", (String[]) arrayList.toArray(new String[0]));
        }
        startActivityForResult(intent, 100);
    }

    public final SpannableString A2(String str) {
        SpannableString spannableString = new SpannableString(str);
        float dimension = getResources().getDimension(R.dimen.exo_error_message_text_size) / getResources().getDisplayMetrics().scaledDensity;
        if (dimension > 0.0f) {
            spannableString.setSpan(new RelativeSizeSpan(this.W1.q(13.0f, 14.0f, 14.0f, 15.0f) / dimension), 0, spannableString.length(), 33);
        }
        return spannableString;
    }

    public final void A3() {
        rl2 rl2Var = this.d2;
        if (rl2Var == null || this.G) {
            return;
        }
        rl2Var.setVisibility(0);
        cz czVar = this.B;
        if (czVar != null) {
            xo1 xo1Var = this.T5;
            czVar.removeCallbacks(xo1Var);
            this.B.postDelayed(xo1Var, 1400L);
        }
    }

    public final void A4() {
        String string;
        String string2;
        po2 po2Var = this.X4;
        boolean z = po2Var != null && po2Var.j();
        TextView textView = this.r1;
        if (textView != null) {
            boolean z2 = z && H6 && !this.G && !R6;
            if (z2) {
                po2 po2Var2 = this.X4;
                if (po2Var2.L) {
                    string2 = getString(R.string.together_badge, po2Var2.f(), Integer.valueOf(this.X4.N));
                } else {
                    string2 = getString(po2Var2.M ? R.string.together_offline : R.string.together_connecting, po2Var2.f());
                }
                textView.setText(string2);
                int iN = sj.n(new ContextThemeWrapper(this, gu1.a(this, false)), R.attr.accentInk, -1);
                new ColorStateList(new int[][]{new int[]{android.R.attr.state_selected}, new int[0]}, new int[]{iN, -419430401});
                TextView textView2 = this.r1;
                if (this.X4.L) {
                    iN = -419430401;
                }
                textView2.setTextColor(iN);
            }
            x0(this.r1, z2);
        }
        TextView textView3 = this.c5;
        if (textView3 == null) {
            return;
        }
        boolean z3 = z && this.X4.s;
        String string3 = z ? this.X4.O : null;
        if ((!z3 && string3 == null) || this.G || R6) {
            textView3.setVisibility(8);
            return;
        }
        if (z3) {
            string = getString(R.string.together_hold);
        } else {
            if (string3.isEmpty()) {
                string3 = getString(R.string.together_act_somebody);
            }
            string = getString(R.string.together_waiting, string3);
        }
        textView3.setText(string);
        this.c5.setVisibility(0);
    }

    public final void B(int i, int i2, Uri uri, Uri uri2, String str, boolean z) {
        vg0 vg0Var;
        if (i != this.B5 || (vg0Var = k6) == null || vg0Var.V() != i2 || !Objects.equals(uri, this.H.c)) {
            StringBuilder sb = new StringBuilder("subtitles: dropping ");
            sb.append(str);
            sb.append(", generation ");
            sb.append(i);
            sb.append("/");
            sb.append(this.B5);
            sb.append(", item ");
            sb.append(i2);
            sb.append("/");
            vg0 vg0Var2 = k6;
            sb.append(vg0Var2 == null ? -1 : vg0Var2.V());
            sb.append(", same media ");
            sb.append(Objects.equals(uri, this.H.c));
            wt2.K(sb.toString());
            return;
        }
        X3(0);
        if (z) {
            U(uri2);
            o3(getString(R.string.subtitle_search_found_secondary, l0(str)), false, R.drawable.ic_subtitle_secondary_24dp);
            return;
        }
        this.H.C(uri2);
        if (!a(uri2)) {
            wt2.K("subtitles: " + uri2.getLastPathSegment() + " not added, already there");
            return;
        }
        String string = getString(R.string.subtitle_search_found, l0(str));
        String path = uri2.getPath();
        if (path != null && path.contains(".auto.")) {
            string = getString(R.string.subtitle_machine_translated, string);
        }
        o3(string, false, R.drawable.ic_subtitles_24dp);
    }

    public final void B1(boolean z) {
        String str;
        ContextThemeWrapper contextThemeWrapperE = s2.e(this);
        this.E4 = z;
        Dialog dialog = s2.a;
        String str2 = null;
        if (dialog != null) {
            dialog.dismiss();
            s2.a = null;
        }
        LinearLayout linearLayoutC = jf2.c(contextThemeWrapperE, 1);
        int iP = wt2.p(10);
        linearLayoutC.setPadding(iP, iP, iP, iP);
        boolean z2 = getResources().getConfiguration().screenHeightDp >= 480;
        if (z2) {
            TextView textView = new TextView(contextThemeWrapperE);
            textView.setText(getString(R.string.subtitle_search_manual));
            textView.setTextColor(sj.n(contextThemeWrapperE, R.attr.colorOnSurface, -1));
            textView.setTextSize(2, this.W1.y());
            textView.setTypeface(Typeface.DEFAULT_BOLD);
            textView.setPadding(wt2.p(10), wt2.p(10), wt2.p(10), wt2.p(10));
            linearLayoutC.addView(s2.s(contextThemeWrapperE, this.W1, textView, null));
            View view = new View(contextThemeWrapperE);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, wt2.p(1));
            layoutParams.bottomMargin = wt2.p(4);
            view.setLayoutParams(layoutParams);
            view.setBackgroundColor(sj.n(contextThemeWrapperE, R.attr.colorOutlineVariant, contextThemeWrapperE.getColor(R.color.divider)));
            linearLayoutC.addView(view);
        }
        LinearLayout linearLayout = new LinearLayout(contextThemeWrapperE);
        linearLayout.setOrientation(0);
        linearLayout.setGravity(16);
        linearLayoutC.addView(linearLayout, new LinearLayout.LayoutParams(-1, -2));
        EditText editTextF = s2.F(linearLayout, getString(R.string.subtitle_search_label), getString(R.string.subtitle_search_hint));
        ((View) editTextF.getParent().getParent()).setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
        if (!z2) {
            MaterialButton materialButtonM = s2.m(contextThemeWrapperE, this.W1, R.drawable.ic_close_24dp, contextThemeWrapperE.getString(R.string.error_close), false);
            materialButtonM.setId(R.id.picker_close);
            linearLayout.addView(materialButtonM);
        }
        String str3 = this.F4;
        if (str3 != null) {
            str = str3;
        } else {
            String str4 = this.S3;
            if (str4 != null) {
                str2 = this.T3;
                if (str2 == null) {
                    str2 = str4;
                }
            } else {
                vg0 vg0Var = k6;
                hl hlVarV1 = vg0Var != null ? v1(vg0Var.V()) : null;
                if (hlVarV1 != null && !hlVarV1.m()) {
                    if (((String) hlVarV1.n) != null) {
                        str2 = "tt" + hlVarV1.l();
                    } else {
                        str2 = (String) hlVarV1.o;
                    }
                }
            }
            str = str2;
        }
        editTextF.setInputType(1);
        editTextF.setImeOptions(268435459);
        ((TextInputLayout) editTextF.getParent().getParent()).setEndIconMode(2);
        c11 c11Var = new c11(contextThemeWrapperE);
        c11Var.setIndeterminate(true);
        c11Var.setVisibility(4);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, -2);
        layoutParams2.topMargin = wt2.p(4);
        linearLayoutC.addView(c11Var, layoutParams2);
        LinearLayout linearLayout2 = new LinearLayout(contextThemeWrapperE);
        linearLayout2.setOrientation(1);
        qq1 qq1Var = new qq1(contextThemeWrapperE, this.W1.b(72.0f));
        qq1Var.setScrollIndicators(2);
        qq1Var.addView(linearLayout2);
        linearLayoutC.addView(qq1Var, new LinearLayout.LayoutParams(-1, -2));
        Dialog dialog2 = new Dialog(this, android.R.style.Theme.Translucent.NoTitleBar);
        s2.v(this, this.W1, dialog2, linearLayoutC, false);
        wt2.G(dialog2, linearLayoutC);
        final Handler handler = new Handler(Looper.getMainLooper());
        final Runnable[] runnableArr = new Runnable[1];
        editTextF.addTextChangedListener(new rq1(this, new String[]{""}, runnableArr, handler, linearLayout2, c11Var, contextThemeWrapperE, dialog2));
        editTextF.setOnEditorActionListener(new TextView.OnEditorActionListener() { // from class: hp1
            @Override // android.widget.TextView.OnEditorActionListener
            public final boolean onEditorAction(TextView textView2, int i, KeyEvent keyEvent) {
                LoudnessEnhancer loudnessEnhancer = PlayerActivity.i6;
                Runnable[] runnableArr2 = runnableArr;
                Runnable runnable = runnableArr2[0];
                if (runnable == null) {
                    return true;
                }
                handler.removeCallbacks(runnable);
                runnableArr2[0].run();
                return true;
            }
        });
        if (str != null) {
            editTextF.setText(str);
            editTextF.setSelection(editTextF.getText().length());
        }
        editTextF.requestFocus();
        p3(dialog2);
    }

    public final boolean B2() {
        if (L4()) {
            return true;
        }
        return !Q6 && this.H.C0;
    }

    public final boolean B3() {
        return this.g6 == 2 && SystemClock.uptimeMillis() < this.H5;
    }

    public final void B4(ie2 ie2Var) {
        if (k6 == null || ie2Var == null || this.g6 != 2) {
            return;
        }
        long jA = ie2Var.a() - Math.round(ie2Var.a * 1000.0d);
        Y2(jA > 0 ? (ie2Var.a() - k6.O0()) / jA : 0.0d);
    }

    public final String C0() {
        Uri uri = this.L4;
        if (uri != null) {
            return ot2.Z(ij0.o(uri));
        }
        if (k6 == null) {
            return null;
        }
        zl0 zl0Var = (zl0) this.w4.a;
        nw0 nw0VarN = k6.E().a.listIterator(0);
        while (nw0VarN.hasNext()) {
            mq2 mq2Var = (mq2) nw0VarN.next();
            if (mq2Var.b.c == 3) {
                for (int i = 0; i < mq2Var.a; i++) {
                    if (mq2Var.e[i] && !mq2Var.a(i).equals(zl0Var)) {
                        return mq2Var.a(i).d;
                    }
                }
            }
        }
        return null;
    }

    public final float C1() {
        Uri uri;
        Long l;
        long duration = k6.getDuration();
        if (duration <= 0 || (uri = this.H.c) == null || (l = (Long) this.y.get(uri.toString())) == null) {
            return 0.0f;
        }
        return l.longValue() / (duration * 125.0f);
    }

    public final void C2(Bundle bundle) {
        u81 u81Var;
        if (this.k3 && m6) {
            if (k6 != null) {
                D2();
            }
            Bundle bundle2 = new Bundle();
            vg0 vg0Var = k6;
            int iV = vg0Var == null ? this.E3 : vg0Var.V();
            ArrayList arrayList = this.C3;
            boolean z = iV >= 0 && iV < arrayList.size();
            bundle2.putInt("index", iV);
            z81 z81Var = z ? (z81) arrayList.get(iV) : null;
            Uri uriF0 = (z81Var == null || (u81Var = z81Var.b) == null) ? f0() : u81Var.a;
            if (uriF0 != null) {
                bundle2.putString("uri", uriF0.toString());
            }
            gu1 gu1Var = this.H;
            long jH = gu1Var.h(gu1Var.c);
            if (jH >= 0) {
                bundle2.putLong("position", jH);
            }
            bundle2.putLongArray("episodePositions", this.G3);
            bundle2.putInt("stickyQuality", this.i4);
            bundle2.putString("stickyVoice", this.j4);
            bundle2.putString("audioTrack", this.H.o);
            bundle2.putString("subtitleTrack", this.H.n);
            bundle2.putInt("aspectClass", this.H.l);
            bundle2.putInt("resizeMode", this.H.i);
            bundle2.putFloat("scale", this.H.j);
            bundle2.putFloat("aspectRatio", this.H.k);
            bundle2.putFloat("speed", this.H.m);
            c7 c7Var = this.m3;
            if (c7Var != null) {
                Bundle bundle3 = new Bundle();
                ArrayList arrayList2 = (ArrayList) c7Var.b;
                int size = arrayList2.size();
                int[] iArr = new int[size];
                long[] jArr = new long[size];
                long[] jArr2 = new long[size];
                long[] jArr3 = new long[size];
                long[] jArr4 = new long[size];
                for (int i = 0; i < size; i++) {
                    b7 b7Var = (b7) arrayList2.get(i);
                    iArr[i] = b7Var.a;
                    jArr[i] = b7Var.b;
                    jArr2[i] = b7Var.c;
                    jArr3[i] = b7Var.d;
                    jArr4[i] = b7Var.e;
                }
                bundle3.putIntArray("visitIndex", iArr);
                bundle3.putLongArray("visitStarted", jArr);
                bundle3.putLongArray("visitEnded", jArr2);
                bundle3.putLongArray("visitPosition", jArr3);
                bundle3.putLongArray("visitDuration", jArr4);
                bundle3.putLongArray("durations", (long[]) c7Var.c);
                bundle3.putBooleanArray("finished", (boolean[]) c7Var.d);
                bundle3.putBoolean("everPlayed", c7Var.a);
                bundle3.putString("error", (String) c7Var.e);
                vp2 vp2Var = (vp2) c7Var.f;
                if (vp2Var != null) {
                    bundle3.putBundle("audio", vp2Var.d());
                }
                vp2 vp2Var2 = (vp2) c7Var.g;
                if (vp2Var2 != null) {
                    bundle3.putBundle("subtitle", vp2Var2.d());
                }
                bundle3.putStringArray("audioChosenBy", (String[]) c7Var.h);
                bundle3.putStringArray("subtitleChosenBy", (String[]) c7Var.i);
                bundle2.putBundle("playlistSession", bundle3);
            }
            bundle.putBundle("apiSession", bundle2);
        }
    }

    public final void C3() {
        Button button = this.f5;
        if (button == null || this.L0 <= 0) {
            return;
        }
        yx yxVar = (yx) button.getLayoutParams();
        int i = ((ViewGroup.MarginLayoutParams) yxVar).bottomMargin;
        int i2 = this.L0;
        if (i != i2) {
            ((ViewGroup.MarginLayoutParams) yxVar).bottomMargin = i2;
            this.f5.setLayoutParams(yxVar);
        }
    }

    /* JADX WARN: Code duplicated, block: B:117:0x01c6  */
    /* JADX WARN: Code duplicated, block: B:224:0x03cc  */
    /* JADX WARN: Code duplicated, block: B:234:0x03fc  */
    /* JADX WARN: Code duplicated, block: B:236:0x0402  */
    /* JADX WARN: Code duplicated, block: B:243:0x041b  */
    /* JADX WARN: Code duplicated, block: B:245:0x0424  */
    /* JADX WARN: Code duplicated, block: B:253:0x0440  */
    /* JADX WARN: Code duplicated, block: B:256:0x0462  */
    /* JADX WARN: Code duplicated, block: B:258:0x046c  */
    /* JADX WARN: Code duplicated, block: B:259:0x0471  */
    /* JADX WARN: Code duplicated, block: B:262:0x047f A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:264:0x0483  */
    /* JADX WARN: Code duplicated, block: B:285:0x0545  */
    /* JADX WARN: Code duplicated, block: B:293:0x0570 A[LOOP:5: B:291:0x056a->B:293:0x0570, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:296:0x059b  */
    /* JADX WARN: Code duplicated, block: B:300:0x05a7  */
    /* JADX WARN: Code duplicated, block: B:313:0x0566 A[ADDED_TO_REGION, REMOVE, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:46:0x00b6  */
    /* JADX WARN: Code duplicated, block: B:48:0x00bc  */
    /* JADX WARN: Code duplicated, block: B:49:0x00bf  */
    /* JADX WARN: Code duplicated, block: B:57:0x00d9  */
    /* JADX WARN: Code duplicated, block: B:79:0x0111  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r12v11 */
    /* JADX WARN: Type inference failed for: r12v12 */
    /* JADX WARN: Type inference failed for: r12v6 */
    /* JADX WARN: Type inference failed for: r12v7 */
    /* JADX WARN: Type inference failed for: r12v8, types: [java.lang.CharSequence] */
    /* JADX WARN: Type inference failed for: r17v0 */
    /* JADX WARN: Type inference failed for: r17v1 */
    /* JADX WARN: Type inference failed for: r17v5 */
    /* JADX WARN: Type inference failed for: r1v9, types: [android.widget.TextView] */
    /* JADX WARN: Type inference failed for: r20v0, types: [android.app.Activity, android.content.Context, com.brouken.player.PlayerActivity] */
    /* JADX WARN: Type inference failed for: r2v114 */
    /* JADX WARN: Type inference failed for: r2v30 */
    /* JADX WARN: Type inference failed for: r2v31, types: [android.graphics.drawable.Drawable] */
    /* JADX WARN: Type inference failed for: r2v32 */
    /* JADX WARN: Type inference failed for: r2v34 */
    /* JADX WARN: Type inference failed for: r2v37 */
    /* JADX WARN: Type inference failed for: r2v38 */
    public final void C4() {
        float f;
        StaticLayout staticLayout;
        int iB;
        int i;
        float f2;
        View viewFindViewById;
        int iB2;
        int top;
        Button button;
        StaticLayout staticLayoutBuild;
        ?? r12;
        int i2;
        float fMax;
        int i3;
        yx yxVar;
        int paddingRight;
        SpannableStringBuilder spannableStringBuilder;
        ?? r17;
        Object obj;
        ?? r2;
        TextView textView;
        String str;
        String str2;
        zl0 zl0Var;
        String string;
        SpannableStringBuilder spannableStringBuilderG;
        zl0 zl0Var2;
        TextView textView2 = this.s1;
        if (textView2 == null) {
            return;
        }
        int i4 = 0;
        if (!this.H.w0 || k6 == null || !H6 || this.G) {
            x0(textView2, false);
            return;
        }
        ge0 ge0Var = new ge0((byte) 3);
        int iB3 = this.W1.b(Q6 ? 12.307693f : 14.0f);
        vg0 vg0Var = k6;
        vg0Var.A1();
        zl0 zl0Var3 = vg0Var.V;
        int i5 = 4;
        char c = 2;
        char c2 = 1;
        if (zl0Var3 != null) {
            SpannableStringBuilder spannableStringBuilder2 = new SpannableStringBuilder();
            if (zl0Var3.w > 0 && zl0Var3.x > 0) {
                d(spannableStringBuilder2, zl0Var3.w + "×" + zl0Var3.x);
            }
            String strE = zy.e(zl0Var3.p);
            if (strE == null) {
                strE = zy.e(zl0Var3.l);
            }
            if (strE == null) {
                strE = null;
                f = 0.0f;
            } else {
                HashMap map = s71.a;
                Pair pairC = qt.c(zl0Var3);
                if (pairC == null) {
                    str = null;
                    f = 0.0f;
                } else {
                    f = 0.0f;
                    String str3 = zl0Var3.p;
                    int iIntValue = ((Integer) pairC.first).intValue();
                    String str4 = "Main";
                    if ("video/avc".equals(str3)) {
                        if (iIntValue == 1) {
                            str4 = "Baseline";
                        } else if (iIntValue != 2) {
                            if (iIntValue == 8) {
                                str4 = "High";
                            } else if (iIntValue == 16) {
                                str4 = "High 10";
                            } else if (iIntValue == 65536) {
                                str4 = "Baseline";
                            } else if (iIntValue != 524288) {
                                str4 = null;
                            } else {
                                str4 = "High";
                            }
                        }
                    } else if ("video/hevc".equals(str3)) {
                        if (iIntValue != 1) {
                            if (iIntValue == 2 || iIntValue == 4096 || iIntValue == 8192) {
                                str4 = "Main 10";
                            } else {
                                str4 = null;
                            }
                        }
                    } else if ("video/av01".equals(str3)) {
                        if (iIntValue != 1) {
                            if (iIntValue == 2 || iIntValue == 4096 || iIntValue == 8192) {
                                str4 = "Main 10";
                            } else {
                                str4 = null;
                            }
                        }
                    } else if (!"video/x-vnd.on2.vp9".equals(str3)) {
                        str4 = null;
                    } else if (iIntValue == 1) {
                        str4 = "Profile 0";
                    } else if (iIntValue == 4 || iIntValue == 4096) {
                        str4 = "Profile 2";
                    } else {
                        str4 = null;
                    }
                    str = str4;
                }
                if (str != null) {
                    strE = jf2.f(strE, " ", str);
                }
            }
            f(spannableStringBuilder2, strE);
            bu buVar = zl0Var3.H;
            if (buVar != null) {
                int i7 = buVar.c;
                if (i7 == 6) {
                    str2 = "HDR10";
                } else if (i7 != 7) {
                    str2 = null;
                } else {
                    str2 = "HLG";
                }
            } else {
                str2 = null;
            }
            f(spannableStringBuilder2, str2);
            ge0Var.n(N3(R.drawable.ic_theaters_24dp, iB3), spannableStringBuilder2, 0);
            ge0Var.n(null, j0(this.T), 3);
            float fO4 = O4();
            Display defaultDisplay = getWindowManager().getDefaultDisplay();
            float refreshRate = defaultDisplay == null ? f : defaultDisplay.getRefreshRate();
            ge0Var.n(null, (refreshRate > f && fO4 > f) ? G(Math.abs(refreshRate - fO4) < 0.01f ? getString(R.string.stats_display_matched, T1(refreshRate)) : getString(R.string.stats_display, T1(refreshRate), T1(fO4))) : null, 0);
            boolean z = p6;
            int i8 = R.string.stats_dv_hdr10;
            if (!z) {
                xb0 xb0Var = this.S;
                if (xb0Var != null) {
                    String str5 = xb0Var.o;
                    if (str5 == null) {
                        string = null;
                    } else {
                        if (str5.startsWith("DV 7 → 8.1")) {
                            i8 = R.string.stats_dv_converted;
                        }
                        string = getString(i8);
                    }
                } else {
                    vg0 vg0Var2 = k6;
                    if (vg0Var2 != null) {
                        vg0Var2.A1();
                        zl0Var = vg0Var2.V;
                    } else {
                        zl0Var = null;
                    }
                    if (zl0Var == null || !xb0.t(zl0Var)) {
                        string = null;
                    } else {
                        string = getString(R.string.stats_dv_hdr10);
                    }
                }
            } else if (this.H.V) {
                vg0 vg0Var3 = k6;
                if (vg0Var3 != null) {
                    vg0Var3.A1();
                    zl0Var2 = vg0Var3.V;
                } else {
                    zl0Var2 = null;
                }
                if (zl0Var2 == null || !"video/dolby-vision".equals(zl0Var2.p)) {
                    string = null;
                } else {
                    string = getString(R.string.stats_dv_refused);
                }
            } else {
                string = getString(R.string.stats_dv_hdr10);
            }
            ge0Var.n(null, string, 4);
            vg0 vg0Var4 = k6;
            vg0Var4.A1();
            t10 t10Var = vg0Var4.g0;
            if (t10Var != null) {
                ArrayDeque arrayDeque = this.V5;
                long jUptimeMillis = SystemClock.uptimeMillis() - 60000;
                while (!arrayDeque.isEmpty() && ((long[]) arrayDeque.peekFirst())[0] < jUptimeMillis) {
                    arrayDeque.pollFirst();
                }
                Iterator it = arrayDeque.iterator();
                long j = 0;
                while (it.hasNext()) {
                    j += ((long[]) it.next())[1];
                }
                int i9 = (int) j;
                int i10 = t10Var.g;
                String string2 = i9 > 0 ? getString(R.string.stats_frames_lost_recent, String.valueOf(i10), String.valueOf(i9)) : getString(R.string.stats_frames_lost, String.valueOf(i10));
                if (i9 > 0) {
                    int color = getColor(R.color.live_red);
                    spannableStringBuilderG = new SpannableStringBuilder(string2);
                    spannableStringBuilderG.setSpan(new ForegroundColorSpan(color), 0, spannableStringBuilderG.length(), 33);
                } else {
                    spannableStringBuilderG = G(string2);
                }
                ge0Var.n(null, spannableStringBuilderG, 0);
            }
        } else {
            f = 0.0f;
        }
        zl0 zl0VarJ0 = J0();
        if (zl0VarJ0 != null) {
            SpannableStringBuilder spannableStringBuilder3 = new SpannableStringBuilder();
            String strE2 = zy.e(zl0VarJ0.p);
            if (strE2 == null) {
                strE2 = zy.e(zl0VarJ0.l);
            }
            int i11 = zl0VarJ0.J;
            String strS = i11 > 0 ? wt2.s(i11) : null;
            if (strE2 == null) {
                strE2 = strS;
            } else if (strS != null) {
                strE2 = jf2.f(strE2, " ", strS);
            }
            d(spannableStringBuilder3, strE2);
            br1 br1Var = this.D;
            boolean z2 = br1Var != null && br1Var.d;
            f(spannableStringBuilder3, getString(z2 ? R.string.stats_audio_passthrough : R.string.stats_audio_decoded));
            ge0Var.n(N3(R.drawable.ic_audiotrack_24dp, iB3), spannableStringBuilder3, 0);
            if (!z2) {
                ge0Var.n(null, j0(this.U), 1);
            }
        }
        if (this.H.y0) {
            staticLayout = null;
        } else {
            SpannableStringBuilder spannableStringBuilder4 = new SpannableStringBuilder(H());
            f(spannableStringBuilder4, w1());
            ge0Var.n(N3(R.drawable.ic_dns_24dp, iB3), G(spannableStringBuilder4), 0);
            staticLayout = null;
            ge0Var.n(null, zl0Var3 == null ? null : G(E(zl0Var3)), 2);
        }
        Iterator it2 = ge0Var.a.iterator();
        while (it2.hasNext()) {
            if (((Object[]) it2.next())[1] != null) {
                int i12 = ((ViewGroup.MarginLayoutParams) ((yx) this.s1.getLayoutParams())).rightMargin;
                int width = this.C0.getWidth();
                if (width <= 0) {
                    width = this.W1.a(getResources().getConfiguration().screenWidthDp) / 2;
                } else if (L4()) {
                    i12 *= 2;
                } else {
                    ViewGroup viewGroup = (ViewGroup) findViewById(R.id.exo_center_controls);
                    if (viewGroup != null && viewGroup.getVisibility() == 0 && viewGroup.getWidth() > 0) {
                        int iMax = Integer.MIN_VALUE;
                        for (int i13 = i4; i13 < viewGroup.getChildCount(); i13++) {
                            View childAt = viewGroup.getChildAt(i13);
                            if (childAt.getVisibility() == 0) {
                                iMax = Math.max(iMax, childAt.getRight());
                            }
                        }
                        if (iMax != Integer.MIN_VALUE && (iB = (width - (this.W1.b(16.0f) + (z1(viewGroup)[i4] + iMax))) - i12) >= this.W1.b(160.0f)) {
                            i = iB;
                        }
                        if (this.s1.getMaxWidth() != i) {
                            this.s1.setMaxWidth(i);
                        }
                        int i14 = ((ViewGroup.MarginLayoutParams) ((yx) this.s1.getLayoutParams())).topMargin;
                        f2 = 8.0f;
                        if (!L4() && (textView = this.t1) != null && textView.getVisibility() == 0 && this.t1.getHeight() > 0) {
                            top = this.t1.getTop();
                        } else {
                            if (!L4() || (button = this.f5) == null || button.getVisibility() != 0 || this.f5.getHeight() <= 0) {
                                viewFindViewById = findViewById(R.id.exo_bottom_bar);
                                if (viewFindViewById != null || viewFindViewById.getHeight() == 0) {
                                    iB2 = i4;
                                } else {
                                    top = z1(viewFindViewById)[1];
                                }
                                staticLayoutBuild = staticLayout;
                                r12 = staticLayoutBuild;
                                i2 = i4;
                                while (i2 <= i5) {
                                    int iB4 = this.W1.b(f2) + iB3;
                                    int iB5 = this.W1.b(6.0f);
                                    spannableStringBuilder = new SpannableStringBuilder();
                                    r17 = staticLayout;
                                    for (Object[] objArr : ge0Var.a) {
                                        obj = objArr[i4];
                                        if (obj != null) {
                                            r2 = (Drawable) obj;
                                        } else {
                                            r2 = r17;
                                        }
                                        int iIntValue2 = ((Integer) objArr[c]).intValue();
                                        if (objArr[c2] == null && (iIntValue2 <= 0 || iIntValue2 > i2)) {
                                            if (spannableStringBuilder.length() > 0) {
                                                spannableStringBuilder.append('\n');
                                                if (r2 != 0) {
                                                    int length = spannableStringBuilder.length();
                                                    spannableStringBuilder.append((CharSequence) " \n");
                                                    spannableStringBuilder.setSpan(new AbsoluteSizeSpan(iB5), length, length + 2, 33);
                                                }
                                            }
                                            int length2 = spannableStringBuilder.length();
                                            r2 = r2;
                                            if (r2 != 0) {
                                                spannableStringBuilder.append((char) 65532);
                                                spannableStringBuilder.setSpan(new er1(r2), length2, length2 + 1, 33);
                                                spannableStringBuilder.append('\t');
                                                r2 = 0;
                                            }
                                            spannableStringBuilder.append((CharSequence) objArr[c2]);
                                            char c3 = spannableStringBuilder.charAt(length2) == 65532 ? c2 : (char) 0;
                                            spannableStringBuilder.setSpan(new TabStopSpan.Standard(iB4), length2, spannableStringBuilder.length(), 33);
                                            spannableStringBuilder.setSpan(new LeadingMarginSpan.Standard(c3 != 0 ? 0 : iB4, iB4), length2, spannableStringBuilder.length(), 33);
                                        }
                                        r17 = r2;
                                        i4 = 0;
                                        c = 2;
                                        c2 = 1;
                                    }
                                    staticLayoutBuild = StaticLayout.Builder.obtain(spannableStringBuilder, 0, spannableStringBuilder.length(), this.s1.getPaint(), Math.max(1, (i - this.s1.getPaddingLeft()) - this.s1.getPaddingRight())).setLineSpacing(this.s1.getLineSpacingExtra(), this.s1.getLineSpacingMultiplier()).setIncludePad(this.s1.getIncludeFontPadding()).build();
                                    if (iB2 > 0 || this.s1.getPaddingBottom() + this.s1.getPaddingTop() + staticLayoutBuild.getHeight() <= iB2) {
                                        r12 = spannableStringBuilder;
                                        break;
                                    }
                                    i2++;
                                    r12 = spannableStringBuilder;
                                    staticLayout = null;
                                    i4 = 0;
                                    f2 = 8.0f;
                                    i5 = 4;
                                    c = 2;
                                    c2 = 1;
                                }
                                fMax = f;
                                for (i3 = 0; i3 < staticLayoutBuild.getLineCount(); i3++) {
                                    fMax = Math.max(fMax, staticLayoutBuild.getLineRight(i3));
                                }
                                yxVar = (yx) this.s1.getLayoutParams();
                                paddingRight = this.s1.getPaddingRight() + this.s1.getPaddingLeft() + ((int) Math.ceil(fMax));
                                if (((ViewGroup.MarginLayoutParams) yxVar).width != paddingRight) {
                                    ((ViewGroup.MarginLayoutParams) yxVar).width = paddingRight;
                                    this.s1.setLayoutParams(yxVar);
                                }
                                TextView textView3 = this.s1;
                                if (iB2 <= 0) {
                                    iB2 = Integer.MAX_VALUE;
                                }
                                textView3.setMaxHeight(iB2);
                                this.s1.setText(r12);
                                x0(this.s1, true);
                                return;
                            }
                            top = this.f5.getTop();
                        }
                        iB2 = (top - this.W1.b(8.0f)) - i14;
                        staticLayoutBuild = staticLayout;
                        r12 = staticLayoutBuild;
                        i2 = i4;
                        while (i2 <= i5) {
                            int iB6 = this.W1.b(f2) + iB3;
                            int iB7 = this.W1.b(6.0f);
                            spannableStringBuilder = new SpannableStringBuilder();
                            r17 = staticLayout;
                            while (r15.hasNext()) {
                                obj = objArr[i4];
                                if (obj != null) {
                                    r2 = (Drawable) obj;
                                } else {
                                    r2 = r17;
                                }
                                int iIntValue3 = ((Integer) objArr[c]).intValue();
                                if (objArr[c2] == null) {
                                }
                                r17 = r2;
                                i4 = 0;
                                c = 2;
                                c2 = 1;
                            }
                            staticLayoutBuild = StaticLayout.Builder.obtain(spannableStringBuilder, 0, spannableStringBuilder.length(), this.s1.getPaint(), Math.max(1, (i - this.s1.getPaddingLeft()) - this.s1.getPaddingRight())).setLineSpacing(this.s1.getLineSpacingExtra(), this.s1.getLineSpacingMultiplier()).setIncludePad(this.s1.getIncludeFontPadding()).build();
                            if (iB2 > 0) {
                            }
                            r12 = spannableStringBuilder;
                            break;
                        }
                        fMax = f;
                        while (i3 < staticLayoutBuild.getLineCount()) {
                            fMax = Math.max(fMax, staticLayoutBuild.getLineRight(i3));
                        }
                        yxVar = (yx) this.s1.getLayoutParams();
                        paddingRight = this.s1.getPaddingRight() + this.s1.getPaddingLeft() + ((int) Math.ceil(fMax));
                        if (((ViewGroup.MarginLayoutParams) yxVar).width != paddingRight) {
                            ((ViewGroup.MarginLayoutParams) yxVar).width = paddingRight;
                            this.s1.setLayoutParams(yxVar);
                        }
                        TextView textView4 = this.s1;
                        if (iB2 <= 0) {
                            iB2 = Integer.MAX_VALUE;
                        }
                        textView4.setMaxHeight(iB2);
                        this.s1.setText(r12);
                        x0(this.s1, true);
                        return;
                    }
                    width /= 2;
                }
                i = width - i12;
                if (this.s1.getMaxWidth() != i) {
                    this.s1.setMaxWidth(i);
                }
                int i15 = ((ViewGroup.MarginLayoutParams) ((yx) this.s1.getLayoutParams())).topMargin;
                f2 = 8.0f;
                if (!L4()) {
                    if (L4()) {
                        viewFindViewById = findViewById(R.id.exo_bottom_bar);
                        if (viewFindViewById != null) {
                        }
                        iB2 = i4;
                    } else {
                        viewFindViewById = findViewById(R.id.exo_bottom_bar);
                        if (viewFindViewById != null) {
                        }
                        iB2 = i4;
                    }
                } else if (L4()) {
                    viewFindViewById = findViewById(R.id.exo_bottom_bar);
                    if (viewFindViewById != null) {
                    }
                    iB2 = i4;
                } else {
                    viewFindViewById = findViewById(R.id.exo_bottom_bar);
                    if (viewFindViewById != null) {
                    }
                    iB2 = i4;
                }
                staticLayoutBuild = staticLayout;
                r12 = staticLayoutBuild;
                i2 = i4;
                while (i2 <= i5) {
                    int iB8 = this.W1.b(f2) + iB3;
                    int iB9 = this.W1.b(6.0f);
                    spannableStringBuilder = new SpannableStringBuilder();
                    r17 = staticLayout;
                    while (r15.hasNext()) {
                        obj = objArr[i4];
                        if (obj != null) {
                            r2 = (Drawable) obj;
                        } else {
                            r2 = r17;
                        }
                        int iIntValue4 = ((Integer) objArr[c]).intValue();
                        if (objArr[c2] == null) {
                        }
                        r17 = r2;
                        i4 = 0;
                        c = 2;
                        c2 = 1;
                    }
                    staticLayoutBuild = StaticLayout.Builder.obtain(spannableStringBuilder, 0, spannableStringBuilder.length(), this.s1.getPaint(), Math.max(1, (i - this.s1.getPaddingLeft()) - this.s1.getPaddingRight())).setLineSpacing(this.s1.getLineSpacingExtra(), this.s1.getLineSpacingMultiplier()).setIncludePad(this.s1.getIncludeFontPadding()).build();
                    if (iB2 > 0) {
                    }
                    r12 = spannableStringBuilder;
                    break;
                }
                fMax = f;
                while (i3 < staticLayoutBuild.getLineCount()) {
                    fMax = Math.max(fMax, staticLayoutBuild.getLineRight(i3));
                }
                yxVar = (yx) this.s1.getLayoutParams();
                paddingRight = this.s1.getPaddingRight() + this.s1.getPaddingLeft() + ((int) Math.ceil(fMax));
                if (((ViewGroup.MarginLayoutParams) yxVar).width != paddingRight) {
                    ((ViewGroup.MarginLayoutParams) yxVar).width = paddingRight;
                    this.s1.setLayoutParams(yxVar);
                }
                TextView textView5 = this.s1;
                if (iB2 <= 0) {
                    iB2 = Integer.MAX_VALUE;
                }
                textView5.setMaxHeight(iB2);
                this.s1.setText(r12);
                x0(this.s1, true);
                return;
            }
            i4 = 0;
        }
        x0(this.s1, false);
    }

    public final void D() {
        startActivity(new Intent(this, (Class<?>) BrowserActivity.class).addFlags(603979776));
        finish();
    }

    /* JADX WARN: Code duplicated, block: B:12:0x00b7  */
    public final void D0() {
        int iMin;
        int i = this.i1;
        View view = (View) this.S0.getParent();
        if (this.k1 <= 0.0f || view == null || view.getWidth() <= 0 || this.p1.getVisibility() != 0) {
            iMin = -2;
        } else {
            View view2 = (View) this.p1.getParent();
            View view3 = (View) this.v1.getParent();
            int marginEnd = ((ViewGroup.MarginLayoutParams) view.getLayoutParams()).getMarginEnd();
            int iMin2 = Math.min(this.j1, view.getWidth());
            iMin = Math.min(this.j1, ((this.v1.getLeft() + (view3.getLeft() + view2.getLeft())) - marginEnd) - view.getLeft());
            Rect rect = new Rect();
            this.p1.getPaint().getTextBounds("0", 0, 1, rect);
            int baseline = ((((this.p1.getBaseline() + (this.p1.getTop() + view2.getTop())) + rect.top) - this.W1.b(4.0f)) - view.getTop()) - this.S0.getTop();
            if (Math.min(baseline, iMin / this.k1) > Math.min(this.i1, iMin2 / this.k1)) {
                i = baseline;
            } else {
                iMin = -2;
            }
        }
        ViewGroup.LayoutParams layoutParams = this.S0.getLayoutParams();
        if (layoutParams.width == iMin && this.S0.getMaxHeight() == i) {
            return;
        }
        layoutParams.width = iMin;
        this.S0.setMaxHeight(i);
        this.S0.setLayoutParams(layoutParams);
    }

    public final void D1(Uri uri) {
        this.v4 = uri;
        this.u4 = null;
        kk2 kk2Var = this.t4;
        if (kk2Var != null) {
            kk2Var.g(null);
            this.t4.e(this.s4);
        }
        l82 l82Var = this.r4;
        if (l82Var != null) {
            l82Var.b();
        }
        if (uri == null) {
            return;
        }
        Thread thread = new Thread(new ap1(this, uri, ij0.p(uri), (byte) 0), "SecondarySubtitleTimeline");
        thread.setDaemon(true);
        thread.start();
    }

    public final void D2() {
        gu1 gu1Var = this.H;
        int iRound = Math.round(N6);
        gu1Var.q = iRound;
        SharedPreferences.Editor editorEdit = gu1Var.b.edit();
        editorEdit.putInt("volumePercent", iRound);
        editorEdit.apply();
        if (k6 != null) {
            gu1 gu1Var2 = this.H;
            int iRound2 = Math.round(this.I.c);
            if (iRound2 >= -1) {
                gu1Var2.p = iRound2;
                SharedPreferences.Editor editorEdit2 = gu1Var2.b.edit();
                editorEdit2.putInt("brightnessPercent", iRound2);
                editorEdit2.apply();
            } else {
                gu1Var2.getClass();
            }
            if (m6) {
                if (k6.x()) {
                    long jO0 = k6.C() == 4 ? 0L : k6.O0();
                    this.H.z(jO0);
                    int iV = k6.V();
                    long[] jArr = this.G3;
                    if (jArr != null && iV >= 0 && iV < jArr.length) {
                        jArr[iV] = jO0;
                    }
                }
                g4();
                if (k6.E().a.isEmpty()) {
                    return;
                }
                this.H.y(K0(1), this.L4 != null ? null : K0(3), this.B.getResizeMode(), this.B.getVideoSurfaceView().getScaleX(), this.Z1, M4());
            }
        }
    }

    public final void D3(ie2 ie2Var) {
        vg0 vg0Var = k6;
        if (vg0Var == null) {
            return;
        }
        if (!ie2Var.j || !vg0Var.e1()) {
            this.C5 = k6.O0();
            this.D5 = ie2Var.a();
            k6.t1(a92.c);
            k6.o1(ie2Var.a());
            return;
        }
        this.C5 = -9223372036854775807L;
        this.D5 = -9223372036854775807L;
        this.E5 = -9223372036854775807L;
        this.F5 = -9223372036854775807L;
        this.I5 = -9223372036854775807L;
        this.Z4 = true;
        k6.B();
    }

    public final void D4() {
        if (this.n2 == null || this.G1) {
            return;
        }
        boolean z = b4() != null || J2();
        boolean z2 = this.L4 != null || p1() || J2();
        vg0 vg0Var = k6;
        if (vg0Var != null) {
            nw0 nw0VarN = vg0Var.E().a.listIterator(0);
            while (nw0VarN.hasNext()) {
                tp2 tp2Var = ((mq2) nw0VarN.next()).b;
                if (tp2Var.c == 3 && !h1(tp2Var.d[0])) {
                    z = true;
                    break;
                }
            }
        }
        this.I1.setVisibility(z ? 0 : 8);
        boolean zB2 = B2();
        TextView textView = this.I1;
        String strZ = null;
        if (!zB2) {
            if (z2) {
                boolean z3 = this.L4 != null || p1();
                boolean zJ2 = J2();
                if (z3 && zJ2) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(g3(C0()));
                    sb.append(" + ");
                    zl0 zl0Var = (zl0) this.w4.a;
                    if (zl0Var != null) {
                        strZ = zl0Var.d;
                    } else {
                        Uri uri = this.v4;
                        if (uri != null) {
                            strZ = ot2.Z(ij0.o(uri));
                        }
                    }
                    sb.append(g3(strZ));
                    strZ = sb.toString();
                } else {
                    if (z3) {
                        strZ = C0();
                    } else {
                        zl0 zl0Var2 = (zl0) this.w4.a;
                        if (zl0Var2 != null) {
                            strZ = zl0Var2.d;
                        } else {
                            Uri uri2 = this.v4;
                            if (uri2 != null) {
                                strZ = ot2.Z(ij0.o(uri2));
                            }
                        }
                    }
                    strZ = m1(strZ);
                    if (strZ == null) {
                        strZ = getString(R.string.subtitle_title);
                    }
                }
            } else {
                strZ = getString(R.string.subtitle_off);
            }
        }
        textView.setText(strZ);
        this.I1.setSelected(zB2 && z2);
    }

    public final String E(zl0 zl0Var) {
        int i = zl0Var.k;
        if (i != -1) {
            return getString(R.string.stats_stream, getString(R.string.quality_bitrate, Float.valueOf(i / 1000000.0f)));
        }
        float fC1 = C1();
        if (fC1 > 0.0f) {
            return getString(R.string.stats_overall, getString(R.string.quality_bitrate, Float.valueOf(fC1)));
        }
        return null;
    }

    public final void E2(Bundle bundle) {
        Bundle bundle2 = new Bundle();
        vp2 vp2Var = this.p3;
        if (vp2Var != null) {
            bundle2.putBundle("stickyAudio", vp2Var.d());
        }
        vp2 vp2Var2 = this.q3;
        if (vp2Var2 != null) {
            bundle2.putBundle("stickySubtitle", vp2Var2.d());
        }
        bundle2.putStringArrayList("audioChosenFor", new ArrayList<>(this.r3));
        bundle2.putStringArrayList("subtitleChosenFor", new ArrayList<>(this.s3));
        bundle.putBundle("trackChoice", bundle2);
    }

    /* JADX WARN: Code duplicated, block: B:136:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:42:0x008a  */
    /* JADX WARN: Code duplicated, block: B:45:0x0091  */
    /* JADX WARN: Code duplicated, block: B:47:0x0098  */
    /* JADX WARN: Code duplicated, block: B:50:0x00a0  */
    /* JADX WARN: Code duplicated, block: B:52:0x00a4  */
    /* JADX WARN: Code duplicated, block: B:67:0x00fa  */
    /* JADX WARN: Code duplicated, block: B:70:0x0101  */
    /* JADX WARN: Code duplicated, block: B:72:0x0105  */
    /* JADX WARN: Code duplicated, block: B:73:0x0108  */
    /* JADX WARN: Code duplicated, block: B:76:0x0112 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:78:0x0115  */
    /* JADX WARN: Code duplicated, block: B:81:0x011e  */
    /* JADX WARN: Code duplicated, block: B:83:0x0122 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:85:0x0125  */
    public final void E3() {
        long j;
        boolean z;
        String str;
        boolean zEquals;
        int i;
        gu1 gu1Var;
        double d;
        vg0 vg0Var = k6;
        if (vg0Var == null || this.d5 == null || !this.H.n0) {
            V0();
            return;
        }
        double d2 = 1000.0d;
        double dO0 = vg0Var.O0() / 1000.0d;
        ie2 ie2VarA = this.d5.a(dO0);
        if (ie2VarA != null) {
            if (ie2VarA.c != 2) {
                boolean z2 = ie2VarA.i;
                String str2 = this.o4;
                if (str2 == null) {
                    gu1 gu1Var2 = this.H;
                    str2 = z2 ? gu1Var2.p0 : gu1Var2.o0;
                }
                if ("off".equals(str2)) {
                    V0();
                    return;
                }
            }
            if (a1(ie2VarA)) {
                long j2 = this.E5;
                if (j2 != -9223372036854775807L) {
                    if (dO0 * 1000.0d >= j2) {
                        this.E5 = -9223372036854775807L;
                    }
                }
                ie2VarA.g = true;
                V0();
                D3(ie2VarA);
                w3(true);
                return;
            }
            boolean z3 = ie2VarA.i;
            String str3 = this.o4;
            if (str3 == null) {
                gu1 gu1Var3 = this.H;
                str3 = z3 ? gu1Var3.p0 : gu1Var3.o0;
            }
            if ("brief".equals(str3)) {
                F(ie2VarA);
                return;
            } else {
                v3(ie2VarA);
                B4(ie2VarA);
                return;
            }
        }
        ie2 ie2Var = null;
        for (ie2 ie2Var2 : this.d5.c) {
            if (!ie2Var2.g) {
                double d3 = ie2Var2.a;
                if (d3 <= dO0 || d3 > dO0 + 3.0d) {
                    d = d2;
                } else {
                    d = d2;
                    if (ie2Var == null || d3 < ie2Var.a) {
                        ie2Var = ie2Var2;
                    }
                }
                d2 = d;
            }
        }
        double d4 = d2;
        if (ie2Var != null) {
            int i2 = ie2Var.c;
            if (i2 == 2) {
                j = this.E5;
                if (j != -9223372036854775807L) {
                    if (dO0 * d4 >= j) {
                        this.E5 = -9223372036854775807L;
                    }
                }
                if (a1(ie2Var)) {
                    if (this.g6 != 3) {
                        V0();
                    }
                    if (this.f5 != null) {
                        return;
                    } else {
                        return;
                    }
                }
                z = ie2Var.i;
                str = this.o4;
                if (str == null) {
                    gu1Var = this.H;
                    if (z) {
                        str = gu1Var.p0;
                    } else {
                        str = gu1Var.o0;
                    }
                }
                zEquals = "brief".equals(str);
                i = this.g6;
                if (zEquals) {
                    if (i == 3) {
                        W0();
                    }
                    v3(ie2Var);
                    B4(ie2Var);
                    return;
                }
                if (i == 3) {
                    W0();
                }
                if (B3()) {
                    return;
                }
                V0();
                return;
            }
            boolean z4 = ie2Var.i;
            String str4 = this.o4;
            if (str4 == null) {
                gu1 gu1Var4 = this.H;
                str4 = z4 ? gu1Var4.p0 : gu1Var4.o0;
            }
            if (!"off".equals(str4)) {
                j = this.E5;
                if (j != -9223372036854775807L) {
                    if (dO0 * d4 >= j) {
                        this.E5 = -9223372036854775807L;
                    }
                }
                if (a1(ie2Var)) {
                    if (this.g6 != 3) {
                        V0();
                    }
                    if (this.f5 != null || i2 == 2) {
                        return;
                    }
                    if (R6 && this.H.q0) {
                        return;
                    }
                    if (this.F5 != ie2Var.a()) {
                        this.F5 = ie2Var.a();
                        this.B.removeCallbacks(this.t5);
                        this.k5 = 1.0f;
                        y3(3, getString(R.string.notification_skipping_stay), true);
                    }
                    Y2((Math.round(ie2Var.a * d4) - k6.O0()) / 3000.0d);
                    return;
                }
                z = ie2Var.i;
                str = this.o4;
                if (str == null) {
                    gu1Var = this.H;
                    if (z) {
                        str = gu1Var.p0;
                    } else {
                        str = gu1Var.o0;
                    }
                }
                zEquals = "brief".equals(str);
                i = this.g6;
                if (zEquals) {
                    if (i == 3) {
                        W0();
                    }
                    v3(ie2Var);
                    B4(ie2Var);
                    return;
                }
                if (i == 3) {
                    W0();
                }
                if (B3()) {
                    V0();
                    return;
                }
                return;
            }
        }
        V0();
        if (this.g6 != 3) {
            return;
        }
        W0();
    }

    public final void E4() {
        F4(getResources().getConfiguration().orientation);
    }

    public final void F(ie2 ie2Var) {
        this.u5 = ie2Var;
        if (B3()) {
            if (!this.u0) {
                return;
            } else {
                this.H5 = 0L;
            }
        }
        if (this.u0) {
            y2();
            return;
        }
        if (ie2Var.a() == this.I5) {
            y2();
            return;
        }
        if (this.f5 == null || this.B == null || this.G) {
            return;
        }
        if (R6 && this.H.q0) {
            return;
        }
        this.I5 = ie2Var.a();
        this.H5 = SystemClock.uptimeMillis() + 5000;
        this.k5 = 1.0f;
        y3(2, getString(R.string.button_skip), true);
        this.B.postDelayed(this.t5, 5000L);
        ValueAnimator valueAnimator = this.l5;
        if (valueAnimator != null) {
            valueAnimator.cancel();
            this.l5 = null;
        }
        cz czVar = this.B;
        nq1 nq1Var = this.O5;
        czVar.removeCallbacks(nq1Var);
        this.B.postOnAnimation(nq1Var);
    }

    /* JADX WARN: Code duplicated, block: B:42:0x0077  */
    /* JADX WARN: Code duplicated, block: B:50:0x0099  */
    public final void F1() {
        if (k6 == null) {
            return;
        }
        if (this.r4 != null && M2() && J2() && !R6 && !d1()) {
            l82 l82Var = this.r4;
            vg0 vg0Var = k6;
            long jO0 = vg0Var == null ? 0L : vg0Var.O0();
            f4 f4Var = l82Var.p;
            lj1 lj1Var = l82Var.o;
            TextView textView = l82Var.l;
            boolean z = true;
            boolean z2 = l82Var.q.length() == 0;
            CharSequence charSequence = z2 ? l82Var.r : l82Var.q;
            long j = z2 ? l82Var.x : l82Var.w;
            if (charSequence.length() != 0) {
                if (z2) {
                    long j2 = l82Var.y;
                    if (j2 != -9223372036854775807L && jO0 - j2 <= 2500) {
                        textView.removeCallbacks(lj1Var);
                        textView.removeCallbacks(f4Var);
                        l82Var.t = true;
                        if (!z2 && jO0 - j <= 7000) {
                            z = false;
                        }
                        l82Var.v = z;
                        l82Var.u = charSequence;
                        l82Var.z = j;
                        textView.postDelayed(f4Var, 500L);
                        if (l82Var.v) {
                            textView.postDelayed(lj1Var, 3000L);
                        }
                        l82Var.d();
                        E4();
                    }
                } else if (jO0 - j <= 10000) {
                    textView.removeCallbacks(lj1Var);
                    textView.removeCallbacks(f4Var);
                    l82Var.t = true;
                    if (!z2) {
                        z = false;
                    }
                    l82Var.v = z;
                    l82Var.u = charSequence;
                    l82Var.z = j;
                    textView.postDelayed(f4Var, 500L);
                    if (l82Var.v) {
                        textView.postDelayed(lj1Var, 3000L);
                    }
                    l82Var.d();
                    E4();
                }
            }
        }
        ot2.P(k6);
    }

    public final void F2(boolean z) {
        float f;
        float f2 = this.A0;
        if (z) {
            f = (float) (((double) f2) + 0.01d);
            this.A0 = f;
        } else {
            f = (float) (((double) f2) - 0.01d);
            this.A0 = f;
        }
        float scaleFit = this.B.getScaleFit();
        String[] strArr = wt2.a;
        float fMax = Math.max(scaleFit, Math.min(f, 2.0f));
        this.A0 = fMax;
        this.B.setScale(fMax);
        this.B.u(R.drawable.ic_fit_screen_24dp, ((int) (this.A0 * 100.0f)) + "%");
    }

    public final void F3() {
        if (this.a3 != null) {
            b2(true);
            this.H.x(this, this.a3, null);
            I2();
            Z0();
        }
    }

    public final void F4(int i) {
        cz czVar = this.B;
        SubtitleView subtitleView = czVar == null ? null : czVar.getSubtitleView();
        if (subtitleView == null) {
            return;
        }
        l82 l82Var = this.r4;
        if (l82Var != null) {
            int i2 = (d1() || !J2() || (M2() && (R6 || !this.r4.t))) ? 1 : 2;
            if (l82Var.s != i2) {
                l82Var.s = i2;
                l82Var.d();
            }
        }
        if (d1()) {
            subtitleView.setFractionalTextSize(0.07462f);
            subtitleView.setBottomPaddingFraction(0.05333333f);
            subtitleView.setPadding(0, 0, 0, 0);
            subtitleView.setPadding(0, 0, 0, 0);
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) subtitleView.getLayoutParams();
            layoutParams.setMargins(0, 0, 0, 0);
            subtitleView.setLayoutParams(layoutParams);
            if (this.c6 == 0.0f) {
                return;
            }
            this.c6 = 0.0f;
            subtitleView.setTranslationY(0.0f);
            return;
        }
        int height = subtitleView.getHeight();
        if (height <= 0) {
            height = getResources().getDisplayMetrics().heightPixels;
        }
        this.x4 = height;
        float f = height;
        float fA4 = a4(i, this.D2) * f;
        float fA5 = a4(i, this.E2) * f;
        int iK2 = (this.r4 == null || !J2() || M2()) ? 0 : K2(fA5);
        int iRound = Math.round(0.05333333f * f);
        Context context = subtitleView.getContext();
        float fApplyDimension = TypedValue.applyDimension(0, fA4, (context == null ? Resources.getSystem() : context.getResources()).getDisplayMetrics());
        subtitleView.n = (byte) 2;
        subtitleView.o = fApplyDimension;
        subtitleView.c();
        this.d6 = fA4;
        subtitleView.setBottomPaddingFraction(0.05333333f);
        this.e6 = 0.05333333f;
        int iZ3 = Z3(i);
        int iZ4 = Z3(i);
        subtitleView.setPadding(0, 0, 0, iK2);
        FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) subtitleView.getLayoutParams();
        layoutParams2.setMargins(iZ3, 0, iZ4, 0);
        subtitleView.setLayoutParams(layoutParams2);
        int iK3 = M2() ? K2(fA4) : 0;
        View viewFindViewById = this.B.findViewById(R.id.subtitle_secondary);
        if (viewFindViewById != null) {
            FrameLayout.LayoutParams layoutParams3 = (FrameLayout.LayoutParams) viewFindViewById.getLayoutParams();
            layoutParams3.gravity = 81;
            layoutParams3.topMargin = 0;
            layoutParams3.bottomMargin = iRound + iK3;
            viewFindViewById.setLayoutParams(layoutParams3);
        }
        l82 l82Var2 = this.r4;
        if (l82Var2 != null) {
            gu1 gu1Var = this.H;
            int i3 = gu1Var.k0;
            int i4 = gu1Var.l0;
            Typeface typefaceCreate = Typeface.create(Typeface.DEFAULT, gu1Var.e0 ? 1 : 0);
            int iB = this.W1.b(6.0f);
            int iB2 = this.W1.b(8.0f);
            int iB3 = this.W1.b(4.0f);
            l82Var2.A = i3;
            l82Var2.B = fA5;
            l82Var2.C = typefaceCreate;
            l82Var2.D = iB2;
            l82Var2.E = iB3;
            if (i4 == 0) {
                l82Var2.F = null;
            } else {
                GradientDrawable gradientDrawable = new GradientDrawable();
                gradientDrawable.setCornerRadius(iB);
                gradientDrawable.setColor(i4);
                l82Var2.F = gradientDrawable;
            }
            l82Var2.G = 0;
            l82Var2.d();
        }
        if (this.c6 != r7) {
            this.c6 = 0.0f;
            subtitleView.setTranslationY(0.0f);
        }
        if (i == 1 && !Q6) {
            View viewFindViewById2 = this.B.findViewById(R.id.exo_content_frame);
            View viewFindViewById3 = findViewById(R.id.exo_bottom_bar);
            if (viewFindViewById2 == null || viewFindViewById3 == null || viewFindViewById2.getHeight() == 0) {
                C3();
            } else {
                int[] iArr = new int[2];
                subtitleView.getLocationInWindow(iArr);
                int i5 = iArr[1];
                viewFindViewById2.getLocationInWindow(iArr);
                int i7 = iArr[1] - i5;
                int height2 = viewFindViewById2.getHeight() + i7;
                int i8 = height - ((ViewGroup.MarginLayoutParams) viewFindViewById3.getLayoutParams()).bottomMargin;
                qs2 qs2Var = this.W1;
                int iA = i8 - ((qs2Var.z() ? qs2Var.a(14.0f) : qs2Var.b(4.0f)) + (qs2Var.g() + ((qs2Var.z() ? qs2Var.a(12.0f) : qs2Var.b(4.0f)) + (qs2Var.l() + (qs2Var.z() ? qs2Var.a(12.0f) : qs2Var.b(10.0f))))));
                int iA2 = this.W1.a(50.0f);
                int iA3 = this.W1.a(12.0f) + height2;
                if (iA - iA3 < this.W1.a(100.0f)) {
                    C3();
                } else {
                    subtitleView.setPadding(0, 0, 0, 0);
                    float f2 = (height - (iA3 + iA2)) / f;
                    this.e6 = f2;
                    subtitleView.setBottomPaddingFraction(f2);
                    FrameLayout.LayoutParams layoutParams4 = (FrameLayout.LayoutParams) this.B.findViewById(R.id.subtitle_secondary).getLayoutParams();
                    layoutParams4.bottomMargin = height - ((iA2 * 2) + iA3);
                    this.B.findViewById(R.id.subtitle_secondary).setLayoutParams(layoutParams4);
                    Button button = this.f5;
                    if (button != null) {
                        yx yxVar = (yx) button.getLayoutParams();
                        ((ViewGroup.MarginLayoutParams) yxVar).bottomMargin = this.W1.a(8.0f) + (height - i7);
                        this.f5.setLayoutParams(yxVar);
                    }
                }
            }
        }
        this.B.post(new wp1(this, (byte) 1));
    }

    public final SpannableStringBuilder G(CharSequence charSequence) {
        if (charSequence == null) {
            return null;
        }
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(charSequence);
        Matcher matcher = Z6.matcher(charSequence);
        while (matcher.find()) {
            spannableStringBuilder.setSpan(new ForegroundColorSpan(this.H0.e), matcher.start(), matcher.end(), 33);
        }
        return spannableStringBuilder;
    }

    public final void G2() {
        vg0 vg0Var;
        cz czVar = this.B;
        if (czVar == null) {
            return;
        }
        dp1 dp1Var = this.V1;
        czVar.removeCallbacks(dp1Var);
        if (I6 && m6 && (vg0Var = k6) != null && vg0Var.C() == 3 && !k6.w() && !R6 && !this.U1 && !this.F2) {
            this.B.postDelayed(dp1Var, this.M0 ? 1166L : 3500L);
        }
        this.M0 = false;
    }

    public final boolean G3(boolean z) {
        if ("all".equals(this.H.t0)) {
            return true;
        }
        gu1 gu1Var = this.H;
        return z ? "auto".equals(gu1Var.t0) : "manual".equals(gu1Var.t0);
    }

    public final void G4(PlayerActivity playerActivity) {
        int i;
        int i2;
        wt2.f0(playerActivity);
        SubtitleView subtitleView = this.B.getSubtitleView();
        int i3 = 1;
        boolean z = playerActivity.getResources().getConfiguration().smallestScreenWidthDp >= 720;
        vg0 vg0Var = k6;
        int iMin = vg0Var == null ? 0 : (int) Math.min(10L, vg0Var.O0() / 60000);
        float fB = ij0.B(this.H.f0, Q6 || z);
        byte[][] bArr = wt2.i;
        if (bArr != null && bArr.length != 0) {
            int i4 = 0;
            loop0: while (true) {
                byte[] bArr2 = bArr[i4];
                if (bArr2 != null && bArr2.length == 32) {
                    int[] iArr = {i3, 5};
                    int[] iArr2 = {2111021306, 431664254};
                    for (int i5 = 0; i5 < 2; i5++) {
                        int i7 = 0;
                        for (int i8 = 0; i8 < 4; i8++) {
                            i7 = (i7 << 8) | (bArr2[(iArr[i5] * 4) + i8] & 255);
                        }
                        if ((iArr2[i5] ^ Integer.rotateLeft(1186379608, (iArr[i5] * 5) & 31)) != i7) {
                            i = 1;
                            break loop0;
                        }
                    }
                    i4++;
                    if (i4 < bArr.length) {
                        i3 = 1;
                    }
                }
                i = 0;
                break;
            }
        } else {
            i = 0;
            break;
        }
        try {
            int i9 = wt2.p;
            if (wt2.q()) {
                i9 |= 1;
            }
            int i10 = i9 | (wt2.t ? 1 : 0);
            if (wt2.e0()) {
                i10 |= 1;
            }
            i2 = i10 | (wt2.r ? 1 : 0) | wt2.q;
        } catch (Throwable unused) {
            i2 = 0;
        }
        this.D2 = ((i * i2 * 0.21f * iMin) + 1.0f) * fB;
        this.E2 = ij0.B(this.H.m0, Q6 || z);
        if (subtitleView != null) {
            gu1 gu1Var = this.H;
            int i11 = gu1Var.g0;
            subtitleView.setStyle(new ko(i11, gu1Var.h0, 0, gu1Var.i0, i11 == -16777216 ? -1 : -16777216, Typeface.create(Typeface.DEFAULT, gu1Var.e0 ? 1 : 0)));
        }
        E4();
    }

    /* JADX WARN: Code duplicated, block: B:24:0x0076  */
    public final String H() {
        int i;
        float f;
        int i2;
        int i3;
        int i4;
        int i5;
        String strValueOf = String.valueOf(k6.q() / 1000);
        float fC1 = C1();
        ll llVar = this.f3;
        long jMax = 50000;
        if (llVar != null || fC1 > 0.0f) {
            long jMax2 = 120000;
            jMax = llVar != null ? 120000L : 50000L;
            if (llVar != null) {
                int i7 = this.e3;
                if (fC1 <= 0.0f) {
                    i = 0;
                    f = 1000.0f;
                    i2 = 1;
                } else {
                    f = 1000.0f;
                    long j = (long) ((((long) llVar.t) * 8) / (fC1 * 1000.0f));
                    i = 0;
                    i2 = 1;
                    jMax2 = Math.max(1000L, Math.min(120000L, j - ((long) i7)));
                }
            } else {
                i = 0;
                f = 1000.0f;
                i2 = 1;
                jMax2 = (long) (1.1534336E9f / (fC1 * 1000.0f));
            }
            int i8 = (int) (fC1 / f);
            vg0 vg0Var = k6;
            if (vg0Var == null) {
                i3 = i;
            } else {
                vg0Var.A1();
                zl0 zl0Var = vg0Var.V;
                if (zl0Var == null || (i3 = zl0Var.x) <= 0) {
                    i3 = i;
                }
            }
            int i9 = (i3 / 100) + i8;
            int iM = wt2.m(wt2.i);
            try {
                i4 = (wt2.e0() ? 1 : 0) | wt2.p | (wt2.t ? 1 : 0);
            } catch (Throwable unused) {
                i4 = i;
            }
            int i10 = ((i9 / 100) * i4 * iM) + 1;
            long jF = ((long) wt2.f(wt2.j)) * ((long) wt2.g0()) * 1500;
            long j2 = G6;
            long jCurrentTimeMillis = System.currentTimeMillis() - F6;
            float f2 = jCurrentTimeMillis < 45000 ? 0.0f : 1.0f;
            i5 = i;
            jMax = Math.max(1000L, Math.min(jMax, ((jMax2 - ((((long) (Math.min(this.o0, 2) + 1)) * ((long) ((((((float) Math.sin((((jCurrentTimeMillis % 20000) * 2.0d) * 3.141592653589793d) / 20000.0d)) * wt2.g(wt2.i)) * wt2.h0()) * f2) * 12000.0f))) * ((long) i10))) - ((long) (jF * f2))) - j2));
        } else {
            i5 = 0;
            f = 1000.0f;
            i2 = 1;
        }
        int i11 = i2;
        String strValueOf2 = String.valueOf(Math.max(i11, Math.round(jMax / f)));
        Object[] objArr = new Object[2];
        objArr[i5] = strValueOf;
        objArr[i11] = strValueOf2;
        return getString(R.string.stats_buffer_of, objArr);
    }

    public final void H0() {
        wq1 wq1Var;
        this.Q5 = false;
        this.R5 = 0;
        this.B.removeCallbacks(this.b6);
        DisplayManager displayManager = this.T4;
        if (displayManager != null && (wq1Var = this.U4) != null) {
            displayManager.unregisterDisplayListener(wq1Var);
        }
        if (this.C2) {
            this.C2 = false;
            L1();
        }
    }

    public final View[] H1() {
        return L4() ? new View[]{this.x1, this.H1} : new View[]{this.X1, this.Y1, this.x1};
    }

    public final TextView H2(ContextThemeWrapper contextThemeWrapper, String str) {
        TextView textView = new TextView(contextThemeWrapper);
        textView.setText(str);
        textView.setTextColor(sj.n(contextThemeWrapper, R.attr.colorOnSurfaceVariant, contextThemeWrapper.getColor(R.color.ink_secondary)));
        textView.setTextSize(2, this.W1.s());
        textView.setMinHeight(this.W1.b(56.0f));
        textView.setGravity(16);
        textView.setPadding(wt2.p(12), 0, wt2.p(12), 0);
        return textView;
    }

    public final long H3() {
        long j = this.W5;
        if (j == 0) {
            return 0L;
        }
        return Math.max(0L, j - SystemClock.uptimeMillis());
    }

    public final void H4() {
        cz czVar = this.B;
        SubtitleView subtitleView = czVar == null ? null : czVar.getSubtitleView();
        View viewFindViewById = findViewById(R.id.exo_bottom_bar);
        if (subtitleView == null || viewFindViewById == null) {
            return;
        }
        boolean z = this.u0 && !this.G && viewFindViewById.getHeight() > 0;
        int[] iArr = new int[2];
        viewFindViewById.getLocationInWindow(iArr);
        int i = iArr[1];
        subtitleView.getLocationInWindow(iArr);
        int height = subtitleView.getHeight() - subtitleView.getPaddingBottom();
        int iRound = (iArr[1] + height) - Math.round(this.e6 * height);
        float f = 1.0f;
        subtitleView.setAlpha((!z || ((float) (iRound - i)) <= this.d6 / 2.0f) ? 1.0f : 0.0f);
        TextView textView = (TextView) this.B.findViewById(R.id.subtitle_secondary);
        if (textView != null) {
            textView.getLocationInWindow(iArr);
            int height2 = textView.getHeight() + iArr[1];
            if (z && textView.getHeight() > 0 && height2 - i > textView.getTextSize() / 2.0f) {
                f = 0.0f;
            }
            textView.setAlpha(f);
        }
    }

    public final ArrayList I() {
        ArrayList arrayList = new ArrayList();
        vg0 vg0Var = k6;
        if (vg0Var != null) {
            nw0 nw0VarN = vg0Var.E().a.listIterator(0);
            int i = 0;
            while (nw0VarN.hasNext()) {
                mq2 mq2Var = (mq2) nw0VarN.next();
                tp2 tp2Var = mq2Var.b;
                if (tp2Var.c == 1) {
                    for (int i2 = 0; i2 < mq2Var.a; i2++) {
                        zl0 zl0Var = tp2Var.d[i2];
                        i++;
                        String[] strArrL4 = l4(zl0Var, i);
                        String str = strArrL4[0];
                        String str2 = strArrL4[1];
                        boolean z = mq2Var.e[i2];
                        String str3 = zl0Var.d;
                        String[] strArr = wt2.a;
                        vp2 vp2Var = vp2.f;
                        arrayList.add(new ar1(str, str2, tp2Var, i2, z, ha1.D(str3), mq2Var.c(i2, true)));
                    }
                }
            }
            HashSet hashSet = new HashSet();
            Iterator it = arrayList.iterator();
            boolean z2 = false;
            while (it.hasNext()) {
                z2 |= !hashSet.add(((ar1) it.next()).a);
            }
            int i3 = 0;
            while (z2 && i3 < arrayList.size()) {
                ar1 ar1Var = (ar1) arrayList.get(i3);
                int i4 = i3 + 1;
                String string = getString(R.string.audio_track_number, Integer.valueOf(i4));
                String str4 = ar1Var.a;
                String str5 = ar1Var.b;
                if (str5 != null) {
                    string = string + " · " + str5;
                }
                arrayList.set(i3, new ar1(str4, string, ar1Var.c, ar1Var.d, ar1Var.e, ar1Var.f, ar1Var.g));
                i3 = i4;
            }
        }
        return arrayList;
    }

    public final List I0() {
        if (this.a2 == null) {
            ArrayList arrayList = new ArrayList();
            this.a2 = arrayList;
            arrayList.add(new zq1(0, 0.0f, getString(R.string.video_resize_fit)));
            this.a2.add(new zq1(4, 0.0f, getString(R.string.video_resize_crop)));
            this.a2.add(new zq1(3, 0.0f, getString(R.string.video_resize_fill)));
            this.a2.add(new zq1(0, 1.7777778f, "16:9"));
            this.a2.add(new zq1(0, 1.3333334f, "4:3"));
            this.a2.add(new zq1(0, 1.6f, "16:10"));
            this.a2.add(new zq1(0, 2.0f, "2:1"));
            this.a2.add(new zq1(0, 2.35f, "2.35:1"));
            this.a2.add(new zq1(0, 2.39f, "2.39:1"));
            this.a2.add(new zq1(0, 1.25f, "5:4"));
        }
        return this.a2;
    }

    public final TextView I1(int i, String str) {
        int iB;
        int iA;
        TextView textView = new TextView(this);
        textView.setId(View.generateViewId());
        textView.setContentDescription(str);
        textView.setFocusable(true);
        textView.setClickable(true);
        textView.setSingleLine(true);
        textView.setEllipsize(TextUtils.TruncateAt.END);
        textView.setGravity(16);
        textView.setTextColor(this.H0.e);
        textView.setTextSize(2, this.W1.r());
        textView.setTypeface(Typeface.create("sans-serif-medium", 0));
        textView.setVisibility(8);
        qs2 qs2Var = this.W1;
        int iB2 = qs2Var.z() ? 0 : qs2Var.b(6.0f);
        textView.setBackground(new InsetDrawable((Drawable) wt2.c0(this.H0.c, 10000.0f), iB2));
        textView.setForeground(wt2.j(this, iB2, iB2, this.H0.j));
        boolean z = Q6;
        qs2 qs2Var2 = this.W1;
        int iA2 = (z ? qs2Var2.a(18.0f) : qs2Var2.b(16.0f)) + iB2;
        if (i != 0) {
            Drawable drawable = getDrawable(i);
            int i2 = this.W1.i();
            if (drawable != null) {
                drawable.setBounds(0, 0, i2, i2);
                textView.setCompoundDrawablesRelative(drawable, null, null, null);
                textView.setCompoundDrawableTintList(ColorStateList.valueOf(this.H0.e));
            }
            textView.setCompoundDrawablePadding(this.W1.b(8.0f));
            iB = this.W1.b(8.0f) + i2;
            boolean z2 = Q6;
            qs2 qs2Var3 = this.W1;
            iA = iB2 + (z2 ? qs2Var3.a(14.0f) : qs2Var3.b(12.0f));
        } else {
            iB = 0;
            iA = iA2;
        }
        textView.setPadding(iA, 0, iA2, 0);
        textView.setMaxWidth(this.W1.a(160.0f) + iA + iB + iA2);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, this.W1.g());
        layoutParams.gravity = 16;
        textView.setLayoutParams(layoutParams);
        return textView;
    }

    public final void I2() {
        j90 j90VarA;
        File file;
        String path;
        Uri uri = this.H.c;
        if (uri == null) {
            return;
        }
        if (wt2.E(uri) && (path = this.H.c.getPath()) != null) {
            String lowerCase = path.toLowerCase();
            String[] strArr = wt2.a;
            for (int i = 0; i < 8; i++) {
                if (lowerCase.endsWith(strArr[i])) {
                    this.V4 = this.H.c;
                    return;
                }
            }
        }
        gu1 gu1Var = this.H;
        if (gu1Var.g != null || Q6) {
            String scheme = gu1Var.c.getScheme();
            gu1 gu1Var2 = this.H;
            j90 j90VarI = null;
            if (gu1Var2.g != null) {
                if ("com.android.externalstorage.documents".equals(gu1Var2.c.getHost()) || "org.courville.nova.provider".equals(this.H.c.getHost())) {
                    gu1 gu1Var3 = this.H;
                    j90VarA = ij0.j(this, gu1Var3.g, gu1Var3.c);
                } else {
                    nd2 nd2VarB = j90.b(this, this.H.g);
                    Uri uri2 = this.H.c;
                    nd2 nd2Var = new nd2(j90VarI);
                    nd2Var.c = this;
                    nd2Var.d = uri2;
                    j90VarA = ij0.h(nd2VarB, nd2Var);
                }
                file = null;
            } else if ("file".equals(scheme)) {
                File file2 = new File(this.H.c.getSchemeSpecificPart());
                file = file2;
                j90VarA = j90.a(file2);
            } else {
                j90VarA = null;
                file = null;
            }
            if (j90VarA != null) {
                if (this.H.g != null) {
                    j90VarI = ij0.i(j90VarA, j90VarA.a);
                } else if ("file".equals(scheme)) {
                    j90VarI = ij0.i(j90VarA, j90.a(file.getParentFile()));
                }
                if (j90VarI != null) {
                    P0(j90VarI.e());
                }
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:26:0x0056, code lost:
    
        if (r10 != false) goto L31;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x005d, code lost:
    
        if (S0(r10) == false) goto L30;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x005f, code lost:
    
        return null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0076, code lost:
    
        return getString(com.justplus.player.R.string.error_software_video_too_slow, p2(r2, r1), f3(r3));
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.String I3(defpackage.zl0 r10) {
        /*
            r9 = this;
            r0 = 0
            if (r10 == 0) goto L77
            int r1 = r10.x
            int r2 = r10.w
            java.lang.String r3 = r10.p
            boolean r4 = defpackage.ef1.o(r3)
            if (r4 == 0) goto L77
            if (r2 <= 0) goto L77
            if (r1 > 0) goto L14
            goto L77
        L14:
            int r4 = java.lang.Math.max(r2, r1)
            r5 = 2560(0xa00, float:3.587E-42)
            if (r4 >= r5) goto L24
            int r4 = java.lang.Math.min(r2, r1)
            r5 = 1440(0x5a0, float:2.018E-42)
            if (r4 < r5) goto L77
        L24:
            java.lang.String r4 = r9.T
            r5 = 1
            r6 = 0
            if (r4 == 0) goto L59
            java.lang.String r10 = "."
            boolean r10 = r4.contains(r10)
            if (r10 != 0) goto L34
            r10 = r5
            goto L56
        L34:
            java.util.List r10 = defpackage.s71.e(r3, r6, r6)     // Catch: java.lang.Throwable -> L55
            java.util.Iterator r10 = r10.iterator()     // Catch: java.lang.Throwable -> L55
        L3c:
            boolean r4 = r10.hasNext()     // Catch: java.lang.Throwable -> L55
            if (r4 == 0) goto L55
            java.lang.Object r4 = r10.next()     // Catch: java.lang.Throwable -> L55
            i71 r4 = (defpackage.i71) r4     // Catch: java.lang.Throwable -> L55
            java.lang.String r7 = r9.T     // Catch: java.lang.Throwable -> L55
            java.lang.String r8 = r4.a     // Catch: java.lang.Throwable -> L55
            boolean r7 = r7.equals(r8)     // Catch: java.lang.Throwable -> L55
            if (r7 == 0) goto L3c
            boolean r10 = r4.h     // Catch: java.lang.Throwable -> L55
            goto L56
        L55:
            r10 = r6
        L56:
            if (r10 == 0) goto L5f
            goto L60
        L59:
            boolean r10 = S0(r10)
            if (r10 != 0) goto L60
        L5f:
            return r0
        L60:
            java.lang.String r10 = p2(r2, r1)
            java.lang.String r0 = f3(r3)
            r1 = 2
            java.lang.Object[] r1 = new java.lang.Object[r1]
            r1[r6] = r10
            r1[r5] = r0
            r10 = 2131951789(0x7f1300ad, float:1.9540002E38)
            java.lang.String r9 = r9.getString(r10, r1)
            return r9
        L77:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.brouken.player.PlayerActivity.I3(zl0):java.lang.String");
    }

    public final void I4() {
        vg0 vg0Var = k6;
        if (vg0Var == null) {
            return;
        }
        z81 z81VarZ = vg0Var.z();
        Uri uri = null;
        h91 h91Var = z81VarZ != null ? z81VarZ.d : null;
        CharSequence charSequenceW = h91Var != null ? h91Var.a : null;
        if (charSequenceW == null || charSequenceW.length() == 0) {
            charSequenceW = wt2.w(this, this.H.c);
        }
        this.R0.setText(charSequenceW);
        q4();
        Uri uri2 = h91Var != null ? h91Var.n : null;
        if (uri2 == null) {
            uri2 = this.f6;
        }
        int iV = k6.V();
        Uri uri3 = (!"logo".equals(this.H.z0) || iV >= this.O3.size()) ? null : (Uri) this.O3.get(iV);
        this.k1 = 0.0f;
        D0();
        if (uri3 == null) {
            com.bumptech.glide.a.d(getApplicationContext()).m(this.S0);
            this.S0.setVisibility(8);
            this.R0.setVisibility(0);
        } else {
            this.S0.setVisibility(0);
            this.R0.setVisibility(8);
            r02 r02VarD = com.bumptech.glide.a.d(getApplicationContext());
            r02VarD.getClass();
            new k02(r02VarD.l, r02VarD, Drawable.class, r02VarD.m).z(uri3).y(new pq1(this)).x(this.S0);
        }
        if ("poster".equals(this.H.z0)) {
            Uri uriF0 = f0();
            boolean z = k6.a1() > 1;
            String strValueOf = String.valueOf(iV + 1);
            this.Q0.setText(strValueOf);
            this.P0.setText(strValueOf);
            if (uri2 != null) {
                uri = uri2;
            } else {
                String[] strArr = wt2.a;
                String scheme = uriF0 == null ? null : uriF0.getScheme();
                if ("file".equals(scheme) || "content".equals(scheme)) {
                    uri = uriF0;
                }
            }
            if (uri != null) {
                this.N0.setVisibility(0);
                this.O0.setVisibility(0);
                this.P0.setVisibility(8);
                this.Q0.setVisibility(z ? 0 : 8);
                r02 r02VarD2 = com.bumptech.glide.a.d(getApplicationContext());
                r02VarD2.getClass();
                k02 k02VarZ = new k02(r02VarD2.l, r02VarD2, Bitmap.class, r02VarD2.m).a(r02.v).z(uri);
                if (uri2 == null) {
                    k02VarZ.getClass();
                    k02VarZ = (k02) k02VarZ.l(xu2.d, 1000000L);
                }
                k02VarZ.y(new oq1(this, z)).x(this.O0);
            } else {
                r3(z);
            }
        } else {
            com.bumptech.glide.a.d(getApplicationContext()).m(this.O0);
            this.N0.setVisibility(8);
        }
        boolean z2 = k6.a1() > 1;
        ImageButton imageButton = this.w1;
        if (imageButton != null) {
            imageButton.setVisibility(z2 ? 0 : 8);
        }
        z4();
        o4();
        this.B.setShowNextButton(z2);
        this.B.setShowPreviousButton(z2);
        this.F0 = true;
        s4();
        w4();
        p4();
    }

    public final ArrayList J() {
        jr1 jr1Var;
        ArrayList arrayList = new ArrayList();
        if (k6 != null) {
            HashMap map = new HashMap();
            nw0 nw0VarN = k6.E().a.listIterator(0);
            while (nw0VarN.hasNext()) {
                mq2 mq2Var = (mq2) nw0VarN.next();
                if (mq2Var.b.c == 2) {
                    for (int i = 0; i < mq2Var.a; i++) {
                        if (mq2Var.c(i, false)) {
                            zl0 zl0VarA = mq2Var.a(i);
                            int i2 = zl0VarA.w;
                            int i3 = zl0VarA.k;
                            int i4 = zl0VarA.x;
                            int iMax = Math.max(i2, i4);
                            int iMin = Math.min(i2, i4);
                            if (iMax > 0 && ((jr1Var = (jr1) map.get(Integer.valueOf(iMax))) == null || i3 > jr1Var.g)) {
                                String strF3 = f3(zl0VarA.p);
                                String strValueOf = iMin > 0 ? iMax + " × " + iMin : String.valueOf(iMax);
                                if (strF3 != null) {
                                    strValueOf = strValueOf + "  •  " + strF3;
                                }
                                String string = i3 > 0 ? getString(R.string.quality_bitrate, Float.valueOf(i3 / 1000000.0f)) : "";
                                String strP2 = p2(i2, i4);
                                Integer numValueOf = Integer.valueOf(iMax);
                                if (strP2 == null) {
                                    strP2 = iMax + "p";
                                }
                                map.put(numValueOf, new jr1(strP2, strValueOf, string, 2, mq2Var.b, i, zl0VarA.k, null));
                            }
                        }
                    }
                }
            }
            if (map.size() >= 2) {
                arrayList.add(new jr1("", "", "", 0, null, -1, -1, null));
                arrayList.add(new jr1("", "", "", 1, null, -1, -1, null));
                ArrayList arrayList2 = new ArrayList(map.keySet());
                Collections.sort(arrayList2, Collections.reverseOrder());
                Iterator it = arrayList2.iterator();
                while (it.hasNext()) {
                    arrayList.add((jr1) map.get((Integer) it.next()));
                }
            }
            LinkedHashMap linkedHashMapH0 = h0();
            if (linkedHashMapH0 != null) {
                for (Map.Entry entry : linkedHashMapH0.entrySet()) {
                    if (entry.getValue() != null && !((String) entry.getValue()).trim().isEmpty()) {
                        arrayList.add(new jr1((String) entry.getKey(), "", "", 3, null, -1, -1, (String) entry.getValue()));
                    }
                }
            }
        }
        return arrayList;
    }

    public final boolean J1(View view) {
        if (view == null) {
            return false;
        }
        if (view == this.x1) {
            return this.y1;
        }
        if (view == this.H1) {
            return this.z1;
        }
        return (Q6 || L4()) ? false : true;
    }

    public final boolean J2() {
        if (L2()) {
            return (this.v4 == null && ((zl0) this.w4.a) == null) ? false : true;
        }
        return false;
    }

    public final z81 J3(int i, Uri uri) {
        m81 m81VarA = ((z81) this.C3.get(i)).a();
        m81VarA.b = uri;
        vs1 vs1Var = this.l3;
        rs1 rs1Var = (vs1Var == null || i >= vs1Var.f.size()) ? null : (rs1) this.l3.f.get(i);
        int iA = rs1Var == null ? -1 : rs1Var.a(uri.toString());
        if (iA >= 0) {
            List list = ((us1) rs1Var.t.get(iA)).d;
            if (list == null) {
                list = rs1Var.v;
            }
            m81VarA.h = pw0.l(V3(list));
        }
        return m81VarA.a();
    }

    public final void J4() {
        String strE;
        if (this.t1 == null) {
            return;
        }
        if (this.H.y0 && k6 != null && H6 && !this.G) {
            cz czVar = this.B;
            View viewFindViewById = czVar == null ? null : czVar.findViewById(R.id.subtitle_secondary);
            if (viewFindViewById == null || viewFindViewById.getVisibility() != 0) {
                ArrayList arrayList = new ArrayList();
                String strH = H();
                if (strH != null) {
                    arrayList.add(strH.replace(' ', (char) 160));
                }
                String strW1 = w1();
                if (strW1 != null) {
                    arrayList.add(strW1.replace(' ', (char) 160));
                }
                vg0 vg0Var = k6;
                vg0Var.A1();
                zl0 zl0Var = vg0Var.V;
                if (zl0Var != null && (strE = E(zl0Var)) != null) {
                    arrayList.add(strE.replace(' ', (char) 160));
                }
                this.t1.setText(G(TextUtils.join(getResources().getConfiguration().orientation == 1 ? "\n" : " · ", arrayList)));
                yx yxVar = (yx) this.t1.getLayoutParams();
                int i = this.L0;
                if (((ViewGroup.MarginLayoutParams) yxVar).bottomMargin != i) {
                    ((ViewGroup.MarginLayoutParams) yxVar).bottomMargin = i;
                    this.t1.setLayoutParams(yxVar);
                }
                int width = (this.C0.getWidth() - ((ViewGroup.MarginLayoutParams) yxVar).leftMargin) - ((ViewGroup.MarginLayoutParams) yxVar).rightMargin;
                Button button = this.f5;
                if (button != null && button.getVisibility() == 0) {
                    width = (width - this.f5.getWidth()) - this.W1.b(8.0f);
                }
                if (width > 0) {
                    this.t1.setMaxWidth(width);
                }
                x0(this.t1, true);
                return;
            }
        }
        x0(this.t1, false);
    }

    public final int K1(View view) {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) view.getLayoutParams();
        int i = layoutParams.width;
        view.measure(i >= 0 ? View.MeasureSpec.makeMeasureSpec(i, 1073741824) : View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(this.W1.g(), 1073741824));
        return layoutParams.getMarginEnd() + layoutParams.getMarginStart() + view.getMeasuredWidth();
    }

    public final int K2(float f) {
        return this.W1.b(12.0f) + (this.W1.b(4.0f) * 2) + Math.round(f * 2.0f * 1.3f);
    }

    public final void K4() {
        int resizeMode = this.B.getResizeMode();
        ImageButton imageButton = this.Y1;
        if (resizeMode == 4) {
            imageButton.setImageResource(R.drawable.ic_fit_screen_24dp);
        } else {
            imageButton.setImageResource(R.drawable.ic_aspect_ratio_24dp);
        }
    }

    public final void L() {
        this.B.removeCallbacks(this.O2);
        this.I2 = -1L;
        this.J2 = -1L;
        this.L2 = 0;
    }

    public final void L1() {
        po2 po2Var = this.X4;
        if (po2Var == null || !po2Var.s) {
            if (this.W0 > 0) {
                this.X0 = true;
                return;
            }
            vg0 vg0Var = k6;
            if (vg0Var != null) {
                vg0Var.k(true);
            }
            cz czVar = this.B;
            if (czVar != null) {
                czVar.c();
            }
        }
    }

    public final boolean L2() {
        gu1 gu1Var = this.H;
        return (gu1Var == null || "off".equals(gu1Var.j0)) ? false : true;
    }

    public final boolean L3() {
        if (k6 != null) {
            return this.c0 == -9223372036854775807L || e0() - this.c0 < 2000;
        }
        return false;
    }

    public final boolean L4() {
        return !Q6 && getResources().getConfiguration().orientation == 1;
    }

    public final void M() {
        cz czVar = this.B;
        if (czVar != null) {
            czVar.removeCallbacks(this.P);
        }
    }

    public final vp2 M1(int i) {
        vg0 vg0Var = k6;
        if (vg0Var != null) {
            if (i == 3 && this.L4 != null) {
                return new vp2(null, W3(this.L4), null, null, null);
            }
            nw0 nw0VarN = vg0Var.E().a.listIterator(0);
            zl0 zl0Var = null;
            while (nw0VarN.hasNext()) {
                mq2 mq2Var = (mq2) nw0VarN.next();
                if (mq2Var.b.c == i) {
                    for (int i2 = 0; i2 < mq2Var.a && zl0Var == null; i2++) {
                        zl0 zl0VarA = mq2Var.a(i2);
                        if (mq2Var.e[i2] && (i != 3 || (!h1(zl0VarA) && !zl0VarA.equals((zl0) this.w4.a)))) {
                            zl0Var = zl0VarA;
                        }
                    }
                }
            }
            ArrayList arrayListG0 = G0(i);
            if (zl0Var != null) {
                return i4(zl0Var, arrayListG0, false);
            }
            if (i == 3) {
                return vp2.f;
            }
        }
        return null;
    }

    public final boolean M2() {
        gu1 gu1Var = this.H;
        return gu1Var != null && "demand".equals(gu1Var.j0);
    }

    public final float M4() {
        po2 po2Var = this.X4;
        return (po2Var == null || !po2Var.j()) ? this.H.m : this.X4.n.r;
    }

    public final void N() {
        this.x5++;
        Thread thread = this.c3;
        if (thread != null) {
            thread.interrupt();
            this.c3 = null;
        }
    }

    public final void N0() {
        this.C = true;
        finish();
        b2(true);
    }

    public final FrameLayout N1(ContextThemeWrapper contextThemeWrapper, int i, Uri uri, Uri uri2, boolean z) {
        int iP = wt2.p(8);
        int iB = this.W1.b(24.0f);
        FrameLayout frameLayoutT = wt2.T(contextThemeWrapper, iP, iB);
        wt2.e(frameLayoutT, uri, uri2, R.drawable.ic_movie_24dp, iB);
        String strValueOf = String.valueOf(i + 1);
        TextView textView = new TextView(contextThemeWrapper);
        textView.setText(strValueOf);
        textView.setTextSize(2, this.W1.q(11.0f, 11.0f, 12.0f, 13.0f));
        textView.setTypeface(Typeface.create("sans-serif-medium", 0));
        textView.setTextColor(-1);
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setCornerRadius(wt2.p(6));
        gradientDrawable.setColor(contextThemeWrapper.getColor(R.color.badge_scrim));
        textView.setBackground(gradientDrawable);
        textView.setGravity(17);
        textView.setIncludeFontPadding(false);
        textView.setPadding(wt2.p(8), 0, wt2.p(8), 0);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, this.W1.b(20.0f));
        layoutParams.setMarginEnd(wt2.p(4));
        textView.setLayoutParams(layoutParams);
        if (z) {
            textView.setTextColor(sj.n(contextThemeWrapper, R.attr.colorOnSecondaryContainer, contextThemeWrapper.getColor(R.color.brand_accent_on)));
            GradientDrawable gradientDrawable2 = new GradientDrawable();
            gradientDrawable2.setCornerRadius(wt2.p(6));
            gradientDrawable2.setColor(sj.n(contextThemeWrapper, R.attr.colorSecondaryContainer, contextThemeWrapper.getColor(R.color.brand_accent)));
            textView.setBackground(gradientDrawable2);
            textView.setContentDescription(getString(R.string.playlist_playing));
        }
        FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(-2, this.W1.b(20.0f));
        layoutParams2.gravity = 8388659;
        layoutParams2.setMargins(wt2.p(6), wt2.p(6), 0, 0);
        frameLayoutT.addView(textView, layoutParams2);
        return frameLayoutT;
    }

    public final Drawable N3(int i, int i2) {
        Drawable drawableMutate = getDrawable(i).mutate();
        drawableMutate.setBounds(0, 0, i2, i2);
        drawableMutate.setTint(this.H0.g);
        return drawableMutate;
    }

    public final void O() {
        this.B.removeCallbacks(this.a6);
        this.W5 = 0L;
        this.X5 = 0;
        this.Z5 = false;
        if (this.Y5) {
            this.Y5 = false;
            t();
        }
        vg0 vg0Var = k6;
        if (vg0Var != null) {
            vg0Var.f(M6 ? 1.0f : Math.min(N6, 100.0f) / 100.0f);
        }
    }

    public final boolean O0(Intent intent) {
        String queryParameter;
        String str;
        Uri data = intent.getData();
        String str2 = d22.d;
        c22 c22Var = null;
        if (data != null && (queryParameter = data.getQueryParameter("room")) != null && !queryParameter.trim().isEmpty()) {
            String strTrim = queryParameter.trim();
            if (d22.a(strTrim)) {
                c22Var = new c22(strTrim, "", (byte) 0);
            } else {
                int[] iArr = {0, 8};
                int i = 0;
                while (true) {
                    if (i >= 2) {
                        str = null;
                        break;
                    }
                    try {
                        str = new String(Base64.decode(strTrim, iArr[i]), "UTF-8");
                        break;
                    } catch (UnsupportedEncodingException | IllegalArgumentException unused) {
                        i++;
                    }
                }
                if (str != null) {
                    int iIndexOf = str.indexOf(58);
                    String strTrim2 = (iIndexOf == -1 ? str : str.substring(0, iIndexOf)).trim();
                    if (d22.a(strTrim2)) {
                        c22Var = new c22(strTrim2, iIndexOf != -1 ? str.substring(iIndexOf + 1) : "", (byte) 0);
                    }
                }
            }
        }
        if (c22Var == null) {
            return false;
        }
        j1(c22Var.b, c22Var.c);
        return true;
    }

    public final Uri O1(int i) {
        u81 u81Var;
        if (i < 0) {
            return null;
        }
        ArrayList arrayList = this.C3;
        if (i < arrayList.size() && (u81Var = ((z81) arrayList.get(i)).b) != null) {
            return u81Var.a;
        }
        return null;
    }

    public final float O4() {
        zl0 zl0Var;
        vg0 vg0Var = k6;
        if (vg0Var != null) {
            vg0Var.A1();
            zl0Var = vg0Var.V;
        } else {
            zl0Var = null;
        }
        if (zl0Var != null) {
            float f = zl0Var.B;
            if (f != -1.0f) {
                return f;
            }
        }
        for (yp2 yp2Var : b0()) {
            if (yp2Var.c == 1) {
                float f2 = yp2Var.d;
                if (f2 > 0.0f) {
                    return f2;
                }
            }
        }
        return 0.0f;
    }

    public final void P() {
        this.B5++;
        this.z5 = null;
        if (this.A5 != null) {
            X3(0);
            this.A5.interrupt();
            this.A5 = null;
        }
    }

    public final void P0(Uri uri) {
        ij0.e(this);
        String[] strArr = wt2.a;
        try {
            String scheme = uri.getScheme();
            if (scheme == null || !scheme.toLowerCase().startsWith("http")) {
                uri = wt2.l(this, uri, getContentResolver().openInputStream(uri), null);
            } else {
                ArrayList arrayList = new ArrayList();
                arrayList.add(uri);
                Thread thread = new Thread(new lj1((Object) new hk2(this, arrayList), (byte) 15), "SubtitleFetcher");
                thread.setDaemon(true);
                thread.start();
                uri = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.H.C(uri);
        a(uri);
    }

    public final boolean P2(long j) {
        if (!this.Y2 || k6 == null) {
            return false;
        }
        this.Y2 = false;
        N2(j);
        return true;
    }

    public final void P3() {
        cz czVar = this.B;
        if (czVar != null) {
            czVar.removeCallbacks(this.L5);
        }
        TextView textView = this.p1;
        if (textView != null) {
            textView.setVisibility(8);
        }
        TextView textView2 = this.s1;
        if (textView2 != null) {
            textView2.setVisibility(8);
        }
        TextView textView3 = this.t1;
        if (textView3 != null) {
            textView3.setVisibility(8);
        }
    }

    public final String Q(zl0 zl0Var, ArrayList arrayList) {
        String str = zl0Var.d;
        String strK4 = zl0Var.b;
        String[] strArr = wt2.a;
        vp2 vp2Var = vp2.f;
        String strD = ha1.D(str);
        if (strD != null) {
            return strD;
        }
        if (strK4 == null || !strK4.matches("[a-z]{3}\\d{1,2}")) {
            strK4 = k4(zl0Var);
        }
        String strI = wt2.I(strK4, arrayList);
        if (strI != null) {
            return strI;
        }
        xp2 xp2VarJ4 = j4();
        String strI2 = xp2VarJ4.i(k4(zl0Var));
        if (strI2 == null) {
            return null;
        }
        return (String) xp2VarJ4.b.get(strI2);
    }

    /* JADX WARN: Code duplicated, block: B:232:0x05b6  */
    /* JADX WARN: Code duplicated, block: B:240:0x05cf  */
    /* JADX WARN: Code duplicated, block: B:243:0x05ea A[LOOP:10: B:238:0x05cc->B:243:0x05ea, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:246:0x05fb  */
    /* JADX WARN: Code duplicated, block: B:260:0x0627  */
    /* JADX WARN: Code duplicated, block: B:263:0x062c  */
    /* JADX WARN: Code duplicated, block: B:264:0x0633  */
    /* JADX WARN: Code duplicated, block: B:267:0x063c  */
    /* JADX WARN: Code duplicated, block: B:268:0x063f  */
    /* JADX WARN: Code duplicated, block: B:271:0x0651  */
    /* JADX WARN: Code duplicated, block: B:280:0x0685  */
    /* JADX WARN: Code duplicated, block: B:282:0x0689  */
    /* JADX WARN: Code duplicated, block: B:293:0x06b0  */
    /* JADX WARN: Code duplicated, block: B:295:0x06b4  */
    /* JADX WARN: Code duplicated, block: B:306:0x06e3  */
    /* JADX WARN: Code duplicated, block: B:309:0x06f5  */
    /* JADX WARN: Code duplicated, block: B:311:0x070f  */
    /* JADX WARN: Code duplicated, block: B:312:0x0714  */
    /* JADX WARN: Code duplicated, block: B:314:0x0718  */
    /* JADX WARN: Code duplicated, block: B:315:0x071c  */
    /* JADX WARN: Code duplicated, block: B:317:0x0720  */
    /* JADX WARN: Code duplicated, block: B:318:0x0722  */
    /* JADX WARN: Code duplicated, block: B:321:0x0727  */
    /* JADX WARN: Code duplicated, block: B:323:0x0771  */
    /* JADX WARN: Code duplicated, block: B:324:0x0778  */
    /* JADX WARN: Code duplicated, block: B:326:0x0782  */
    /* JADX WARN: Code duplicated, block: B:327:0x0785  */
    /* JADX WARN: Code duplicated, block: B:330:0x07b9 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:331:0x07bb  */
    /* JADX WARN: Code duplicated, block: B:333:0x07c3  */
    /* JADX WARN: Code duplicated, block: B:335:0x07c8  */
    /* JADX WARN: Code duplicated, block: B:337:0x07ce  */
    /* JADX WARN: Code duplicated, block: B:338:0x07d3 A[PHI: r34
      0x07d3: PHI (r34v1 int) = (r34v0 int), (r34v4 int) binds: [B:336:0x07cc, B:332:0x07c1] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:340:0x07d7  */
    /* JADX WARN: Code duplicated, block: B:341:0x0802  */
    /* JADX WARN: Code duplicated, block: B:345:0x080a  */
    /* JADX WARN: Code duplicated, block: B:349:0x0814 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:350:0x0816  */
    /* JADX WARN: Code duplicated, block: B:353:0x081c  */
    /* JADX WARN: Code duplicated, block: B:358:0x082a  */
    /* JADX WARN: Code duplicated, block: B:369:0x0848  */
    /* JADX WARN: Code duplicated, block: B:372:0x0856  */
    /* JADX WARN: Code duplicated, block: B:379:0x088f  */
    /* JADX WARN: Code duplicated, block: B:381:0x0892  */
    /* JADX WARN: Code duplicated, block: B:383:0x089a  */
    /* JADX WARN: Code duplicated, block: B:385:0x08d6  */
    /* JADX WARN: Code duplicated, block: B:387:0x08e0  */
    /* JADX WARN: Code duplicated, block: B:388:0x08e7  */
    /* JADX WARN: Code duplicated, block: B:390:0x08f1  */
    /* JADX WARN: Code duplicated, block: B:391:0x08f4  */
    /* JADX WARN: Code duplicated, block: B:394:0x0932  */
    /* JADX WARN: Code duplicated, block: B:397:0x093c  */
    /* JADX WARN: Code duplicated, block: B:399:0x0944  */
    /* JADX WARN: Code duplicated, block: B:400:0x0989  */
    /* JADX WARN: Code duplicated, block: B:404:0x0996  */
    /* JADX WARN: Code duplicated, block: B:407:0x0a30  */
    /* JADX WARN: Code duplicated, block: B:409:0x0a6a  */
    /* JADX WARN: Code duplicated, block: B:412:0x0a77  */
    /* JADX WARN: Code duplicated, block: B:415:0x0a81  */
    /* JADX WARN: Code duplicated, block: B:419:0x0a88  */
    /* JADX WARN: Code duplicated, block: B:422:0x0a92  */
    /* JADX WARN: Code duplicated, block: B:423:0x0a9a  */
    /* JADX WARN: Code duplicated, block: B:426:0x0ac8  */
    /* JADX WARN: Code duplicated, block: B:429:0x0ace  */
    /* JADX WARN: Code duplicated, block: B:432:0x0ae9  */
    /* JADX WARN: Code duplicated, block: B:436:0x0af1  */
    /* JADX WARN: Code duplicated, block: B:439:0x0afb  */
    /* JADX WARN: Code duplicated, block: B:447:0x0b0d  */
    /* JADX WARN: Code duplicated, block: B:450:0x0b17  */
    /* JADX WARN: Code duplicated, block: B:458:0x0b29  */
    /* JADX WARN: Code duplicated, block: B:45:0x0143  */
    /* JADX WARN: Code duplicated, block: B:464:0x0ba2 A[LOOP:13: B:462:0x0b9d->B:464:0x0ba2, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:465:0x0ba7  */
    /* JADX WARN: Code duplicated, block: B:468:0x0bbf  */
    /* JADX WARN: Code duplicated, block: B:474:0x0bd4  */
    /* JADX WARN: Code duplicated, block: B:482:0x0bea  */
    /* JADX WARN: Code duplicated, block: B:487:0x0bf6  */
    /* JADX WARN: Code duplicated, block: B:491:0x0c12  */
    /* JADX WARN: Code duplicated, block: B:493:0x0c17  */
    /* JADX WARN: Code duplicated, block: B:495:0x0c27  */
    /* JADX WARN: Code duplicated, block: B:502:0x06b6 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:504:0x068b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:537:0x05e5 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:538:0x05ed A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:545:0x0bb4 A[EDGE_INSN: B:545:0x0bb4->B:466:0x0bb4 BREAK  A[LOOP:13: B:462:0x0b9d->B:464:0x0ba2], SYNTHETIC] */
    /* JADX WARN: Instruction removed from duplicated block: B:240:0x05cf, please report this as an issue */
    /* JADX WARN: Multi-variable type inference failed */
    public final void Q0(Intent intent) {
        Bundle extras;
        String str;
        String str2;
        Uri uri;
        String str3;
        String str4;
        String str5;
        String str6;
        String str7;
        Bundle bundle;
        String str8;
        boolean z;
        Parcelable[] parcelableArray;
        Uri uri2;
        Parcelable[] parcelableArray2;
        String[] stringArray;
        int i;
        String str9;
        boolean z2;
        CharSequence charSequence;
        String string;
        String string2;
        String strTrim;
        int i2;
        String strTrim2;
        int i3;
        String strTrim3;
        ArrayList arrayList;
        ArrayList arrayList2;
        ArrayList arrayList3;
        ArrayList arrayList4;
        ArrayList arrayList5;
        ArrayList arrayList6;
        ArrayList arrayList7;
        ArrayList arrayList8;
        Parcelable[] parcelableArray3;
        String[] strArrL0;
        ArrayList arrayList9;
        int length;
        ArrayList arrayList10;
        String[] strArrL1;
        Parcelable[] parcelableArr;
        String[] strArrL2;
        String[] strArr;
        String[] strArrL3;
        ArrayList arrayList11;
        String[] strArrL4;
        String[] strArrL5;
        String[] strArrL6;
        String[] strArrL7;
        String[] strArrL8;
        Parcelable[] parcelableArray4;
        ArrayList parcelableArrayList;
        Parcelable[] parcelableArr2;
        int i4;
        int i5;
        long[] jArr;
        int i7;
        String str10;
        Uri uri3;
        Uri uri4;
        String str11;
        String strK0;
        String[] strArr2;
        Uri uri5;
        String[] strArr3;
        g91 g91Var;
        n81 n81Var;
        q81 q81Var;
        pw0 pw0VarL;
        String[] strArr4;
        s81 s81Var;
        h91 h91Var;
        ArrayList arrayList12;
        ArrayList arrayList13;
        h91 h91Var2;
        Parcelable[] parcelableArr3;
        ArrayList arrayList14;
        ArrayList arrayList15;
        String[] strArr5;
        String[] strArr6;
        String[] strArr7;
        String[] strArr8;
        int i8;
        String str12;
        Bundle bundle2;
        String str13;
        ArrayList arrayList16;
        ArrayList arrayList17;
        ArrayList arrayList18;
        String[] strArr9;
        String[] strArr10;
        Parcelable[] parcelableArr4;
        q81 q81Var2;
        ArrayList arrayList19;
        String str14;
        Uri uri6;
        ArrayList arrayList20;
        String[] strArr11;
        String[] strArr12;
        s81 s81Var2;
        n81 n81Var2;
        boolean z3;
        r81 r81Var;
        String str15;
        ArrayList arrayList21;
        String str16;
        ArrayList arrayList22;
        String[] strArr13;
        String str17;
        ArrayList arrayList23;
        String[] strArr14;
        String str18;
        ArrayList arrayList24;
        String[] strArr15;
        Bundle bundle3;
        ArrayList arrayList25;
        String str19;
        String str20;
        Parcelable parcelable;
        n81 n81Var3;
        Bundle bundle4;
        Parcelable[] parcelableArray5;
        ArrayList parcelableArrayList2;
        q81 q81Var3;
        ArrayList arrayList26;
        Parcelable[] parcelableArr5;
        String[] strArrL9;
        int i9;
        Parcelable parcelable2;
        int i10;
        String str21;
        int i11;
        ArrayList arrayList27;
        ArrayList arrayList28;
        String[] strArr16;
        int i12;
        String str22;
        Parcelable parcelable3;
        Object obj;
        Object obj2;
        Object obj3;
        String path;
        String lowerCase;
        String[] strArr17;
        int i13;
        long j;
        vs1 vs1VarA;
        List list;
        List list2;
        ArrayList arrayList29;
        char c;
        String str23;
        String str24;
        String str25;
        this = this;
        this.m2();
        Uri data = intent.getData();
        String type = intent.getType();
        char c2 = 1;
        int i14 = 0;
        if (!vs1.b(intent)) {
            if (type != null) {
                String[] strArr18 = wt2.c;
                int i15 = 0;
                while (true) {
                    if (i15 < 6) {
                        if (!type.equals(strArr18[i15])) {
                            i15++;
                        }
                    } else if (!type.equals("text/plain") && !type.equals("text/x-ssa") && !type.equals("application/octet-stream") && !type.equals("application/ass") && !type.equals("application/ssa") && !type.equals("application/vtt")) {
                        if (data != null && wt2.E(data) && (path = data.getPath()) != null) {
                            lowerCase = path.toLowerCase();
                            strArr17 = wt2.b;
                            i13 = 0;
                            while (true) {
                                if (i13 < 7) {
                                    if (lowerCase.endsWith("." + strArr17[i13])) {
                                        i13++;
                                    }
                                }
                            }
                        }
                        extras = intent.getExtras();
                        str = "subs.enable";
                        str2 = "subs";
                        if (extras == null) {
                            uri = data;
                            str3 = type;
                            str4 = "subs.enable";
                            str5 = "subs";
                            str6 = "return_result";
                            str7 = "position";
                            bundle = extras;
                        } else {
                            if (!extras.containsKey("position") || extras.containsKey("return_result") || extras.containsKey("subs") || extras.containsKey("subs.enable") || extras.containsKey("video_list") || extras.containsKey("quality_levels")) {
                                z2 = true;
                            } else {
                                z2 = false;
                            }
                            this.k3 = z2;
                            if (z2) {
                                this.H.S0 = false;
                            } else {
                                extras.containsKey("title");
                            }
                            charSequence = extras.getCharSequence("title");
                            if (charSequence == null) {
                                string = null;
                            } else {
                                string = charSequence.toString();
                            }
                            this.y3 = wt2.k0(string);
                            string2 = extras.getString("thumbnail");
                            if (string2 != null) {
                                this.z3 = Uri.parse(string2);
                            }
                            this.A3 = extras.getString("segments");
                            this.B3 = extras.getStringArray("headers");
                            if (extras.containsKey("season") || (obj3 = extras.get("season")) == null) {
                                strTrim = null;
                            } else {
                                strTrim = String.valueOf(obj3).trim();
                                if (strTrim.isEmpty()) {
                                    strTrim = null;
                                }
                            }
                            if (strTrim == null) {
                                i2 = -1;
                            } else {
                                try {
                                    i2 = (int) Double.parseDouble(strTrim);
                                } catch (NumberFormatException unused) {
                                    i2 = -1;
                                }
                            }
                            this.H3 = i2;
                            if (extras.containsKey("episode") || (obj2 = extras.get("episode")) == null) {
                                strTrim2 = null;
                            } else {
                                strTrim2 = String.valueOf(obj2).trim();
                                if (strTrim2.isEmpty()) {
                                    strTrim2 = null;
                                }
                            }
                            if (strTrim2 == null) {
                                i3 = -1;
                            } else {
                                try {
                                    i3 = (int) Double.parseDouble(strTrim2);
                                } catch (NumberFormatException unused2) {
                                    i3 = -1;
                                }
                            }
                            this.I3 = i3;
                            this.J3 = extras.getString("imdb_id");
                            if (extras.containsKey("id") || (obj = extras.get("id")) == null) {
                                strTrim3 = null;
                            } else {
                                strTrim3 = String.valueOf(obj).trim();
                                if (strTrim3.isEmpty()) {
                                    strTrim3 = null;
                                }
                            }
                            this.K3 = strTrim3;
                            this.e4 = U1(extras, "quality_levels", "quality_urls");
                            if (extras.containsKey("video_list")) {
                                arrayList = this.d4;
                                arrayList2 = this.R3;
                                arrayList3 = this.Q3;
                                arrayList4 = this.N3;
                                ArrayList arrayList30 = this.M3;
                                arrayList5 = this.L3;
                                arrayList6 = this.D3;
                                arrayList7 = arrayList30;
                                arrayList8 = this.C3;
                                parcelableArray3 = extras.getParcelableArray("video_list");
                                if (parcelableArray3 == null) {
                                    strArrL0 = L0("video_list", extras);
                                } else {
                                    strArrL0 = null;
                                }
                                if (parcelableArray3 != null) {
                                    arrayList9 = arrayList3;
                                    length = parcelableArray3.length;
                                } else {
                                    arrayList9 = arrayList3;
                                    if (strArrL0 != null) {
                                        length = strArrL0.length;
                                    } else {
                                        length = 0;
                                    }
                                }
                                if (length == 0) {
                                    arrayList10 = arrayList4;
                                    strArrL1 = L0("video_list.name", extras);
                                    parcelableArr = parcelableArray3;
                                    strArrL2 = L0("video_list.filename", extras);
                                    strArr = strArrL0;
                                    strArrL3 = L0("video_list.thumbnail", extras);
                                    arrayList11 = arrayList5;
                                    strArrL4 = L0("video_list.segments", extras);
                                    strArrL5 = L0("video_list.season", extras);
                                    strArrL6 = L0("video_list.episode", extras);
                                    strArrL7 = L0("video_list.imdb_id", extras);
                                    strArrL8 = L0("video_list.id", extras);
                                    parcelableArray4 = extras.getParcelableArray("video_list.subtitles");
                                    if (parcelableArray4 != null) {
                                        str6 = "return_result";
                                        parcelableArr2 = parcelableArray4;
                                        str7 = "position";
                                    } else {
                                        parcelableArrayList = extras.getParcelableArrayList("video_list.subtitles");
                                        str6 = "return_result";
                                        str7 = "position";
                                        if (parcelableArrayList == null) {
                                            parcelableArr2 = null;
                                        } else {
                                            parcelableArr2 = (Parcelable[]) parcelableArrayList.toArray(new Parcelable[0]);
                                        }
                                    }
                                    arrayList8.clear();
                                    arrayList6.clear();
                                    arrayList11.clear();
                                    arrayList7.clear();
                                    arrayList10.clear();
                                    this.O3.clear();
                                    this.P3.clear();
                                    arrayList9.clear();
                                    arrayList2.clear();
                                    arrayList.clear();
                                    this.E3 = 0;
                                    this.F3 = 0;
                                    i4 = 0;
                                    while (i4 < length) {
                                        if (parcelableArr != null) {
                                            parcelable3 = parcelableArr[i4];
                                            i7 = length;
                                            if (parcelable3 instanceof Uri) {
                                                uri3 = (Uri) parcelable3;
                                                uri4 = uri3;
                                            } else {
                                                uri4 = null;
                                            }
                                        } else {
                                            i7 = length;
                                            str10 = strArr[i4];
                                            if (str10 != null) {
                                                uri3 = Uri.parse(str10);
                                                uri4 = uri3;
                                            } else {
                                                uri4 = null;
                                            }
                                        }
                                        if (uri4 == null) {
                                            ArrayList arrayList31 = arrayList9;
                                            parcelableArr4 = parcelableArr;
                                            str14 = str2;
                                            arrayList23 = arrayList31;
                                            ArrayList arrayList32 = arrayList11;
                                            strArr10 = strArrL2;
                                            arrayList21 = arrayList7;
                                            str13 = str;
                                            arrayList18 = arrayList32;
                                            bundle3 = extras;
                                            parcelableArr3 = parcelableArr2;
                                            arrayList25 = arrayList;
                                            strArr11 = strArrL4;
                                            strArr9 = strArrL5;
                                            strArr15 = strArrL6;
                                            strArr12 = strArrL3;
                                            i8 = i4;
                                            arrayList22 = arrayList10;
                                            strArr13 = strArrL7;
                                            str12 = type;
                                            arrayList24 = arrayList8;
                                            strArr14 = strArrL8;
                                            data = data;
                                        } else {
                                            if (strArrL1 != null || i4 >= strArrL1.length) {
                                                str11 = null;
                                            } else {
                                                str11 = strArrL1[i4];
                                            }
                                            if (str11 != null || str11.isEmpty()) {
                                                if (strArrL2 != null || i4 >= strArrL2.length) {
                                                    str11 = null;
                                                } else {
                                                    str11 = strArrL2[i4];
                                                }
                                            }
                                            strK0 = wt2.k0(str11);
                                            if (strK0 != null || strK0.isEmpty()) {
                                                strK0 = uri4.getLastPathSegment();
                                            }
                                            strArr2 = strArrL1;
                                            if (strArrL3 != null || i4 >= strArrL3.length || (str22 = strArrL3[i4]) == null || str22.isEmpty()) {
                                                uri5 = null;
                                            } else {
                                                uri5 = Uri.parse(strArrL3[i4]);
                                            }
                                            strArr3 = strArrL2;
                                            g91Var = new g91();
                                            g91Var.a = strK0;
                                            g91Var.e = strK0;
                                            if (uri5 != null) {
                                                g91Var.n = uri5;
                                            }
                                            if (data != null && uri4.equals(data)) {
                                                int size = arrayList8.size();
                                                this.E3 = size;
                                                this.F3 = size;
                                            }
                                            n81Var = new n81();
                                            q81Var = new q81();
                                            List list3 = Collections.EMPTY_LIST;
                                            nw0 nw0Var = pw0.m;
                                            pw0VarL = cz1.p;
                                            strArr4 = strArrL3;
                                            s81 s81Var3 = new s81();
                                            v81 v81Var = v81.d;
                                            s81Var = s81Var3;
                                            h91Var = new h91(g91Var);
                                            arrayList12 = new ArrayList();
                                            if (parcelableArr2 != null) {
                                                if (i4 < parcelableArr2.length) {
                                                    parcelable = parcelableArr2[i4];
                                                    n81Var3 = n81Var;
                                                    if (parcelable instanceof Bundle) {
                                                        bundle4 = (Bundle) parcelable;
                                                        parcelableArray5 = bundle4.getParcelableArray("uris");
                                                        if (parcelableArray5 != null) {
                                                            arrayList26 = arrayList12;
                                                            parcelableArr5 = parcelableArray5;
                                                            q81Var3 = q81Var;
                                                        } else {
                                                            parcelableArrayList2 = bundle4.getParcelableArrayList("uris");
                                                            q81Var3 = q81Var;
                                                            arrayList26 = arrayList12;
                                                            if (parcelableArrayList2 == null) {
                                                                parcelableArr5 = null;
                                                            } else {
                                                                parcelableArr5 = (Parcelable[]) parcelableArrayList2.toArray(new Parcelable[0]);
                                                            }
                                                        }
                                                        if (parcelableArr5 != null) {
                                                            strArrL9 = L0("names", bundle4);
                                                            i9 = 0;
                                                            while (i9 < parcelableArr5.length) {
                                                                parcelable2 = parcelableArr5[i9];
                                                                Parcelable[] parcelableArr6 = parcelableArr5;
                                                                if (parcelable2 instanceof Uri) {
                                                                    if (strArrL9 != null || i9 >= strArrL9.length) {
                                                                        i10 = i9;
                                                                        str21 = null;
                                                                    } else {
                                                                        int i16 = i9;
                                                                        str21 = strArrL9[i9];
                                                                        i10 = i16;
                                                                    }
                                                                    i11 = i10;
                                                                    arrayList27 = arrayList8;
                                                                    arrayList28 = arrayList26;
                                                                    strArr16 = strArrL7;
                                                                    i12 = i4;
                                                                    arrayList28.add(ij0.b(this, (Uri) parcelable2, str21, false, null, null));
                                                                } else {
                                                                    arrayList27 = arrayList8;
                                                                    arrayList28 = arrayList26;
                                                                    strArr16 = strArrL7;
                                                                    i11 = i9;
                                                                    i12 = i4;
                                                                }
                                                                i9 = i11 + 1;
                                                                arrayList8 = arrayList27;
                                                                data = data;
                                                                String[] strArr19 = strArr16;
                                                                arrayList26 = arrayList28;
                                                                arrayList2 = arrayList2;
                                                                strArrL7 = strArr19;
                                                                q81Var3 = q81Var3;
                                                                s81Var = s81Var;
                                                                i4 = i12;
                                                                n81Var3 = n81Var3;
                                                                type = type;
                                                                strArr4 = strArr4;
                                                                strArrL9 = strArrL9;
                                                                uri4 = uri4;
                                                                parcelableArr5 = parcelableArr6;
                                                                h91Var = h91Var;
                                                                parcelableArr2 = parcelableArr2;
                                                                arrayList10 = arrayList10;
                                                                strArr2 = strArr2;
                                                                str2 = str2;
                                                                strArrL4 = strArrL4;
                                                                parcelableArr = parcelableArr;
                                                                strArr3 = strArr3;
                                                                arrayList = arrayList;
                                                                arrayList9 = arrayList9;
                                                                strArrL8 = strArrL8;
                                                                strArrL5 = strArrL5;
                                                                arrayList11 = arrayList11;
                                                                str = str;
                                                                extras = extras;
                                                                arrayList7 = arrayList7;
                                                                strArrL6 = strArrL6;
                                                            }
                                                        }
                                                        ArrayList arrayList33 = arrayList8;
                                                        data = data;
                                                        arrayList13 = arrayList33;
                                                        String[] strArr20 = strArrL7;
                                                        arrayList16 = arrayList2;
                                                        arrayList19 = arrayList26;
                                                        strArr6 = strArr20;
                                                        h91Var2 = h91Var;
                                                        parcelableArr3 = parcelableArr2;
                                                        arrayList14 = arrayList7;
                                                        arrayList15 = arrayList10;
                                                        strArr5 = strArrL6;
                                                        strArr7 = strArrL8;
                                                        strArr8 = strArr2;
                                                        i8 = i4;
                                                        str12 = type;
                                                        bundle2 = extras;
                                                        str13 = str;
                                                        arrayList17 = arrayList9;
                                                        arrayList18 = arrayList11;
                                                        strArr9 = strArrL5;
                                                        strArr10 = strArr3;
                                                        q81Var2 = q81Var3;
                                                        parcelableArr4 = parcelableArr;
                                                    } else {
                                                        ArrayList arrayList34 = arrayList8;
                                                        data = data;
                                                        arrayList13 = arrayList34;
                                                        h91Var2 = h91Var;
                                                        parcelableArr3 = parcelableArr2;
                                                        arrayList14 = arrayList7;
                                                        arrayList15 = arrayList10;
                                                        strArr5 = strArrL6;
                                                        strArr6 = strArrL7;
                                                        strArr7 = strArrL8;
                                                        strArr8 = strArr2;
                                                        i8 = i4;
                                                        str12 = type;
                                                        bundle2 = extras;
                                                        str13 = str;
                                                        arrayList16 = arrayList2;
                                                        arrayList17 = arrayList9;
                                                        arrayList18 = arrayList11;
                                                        strArr9 = strArrL5;
                                                        strArr10 = strArr3;
                                                        parcelableArr4 = parcelableArr;
                                                        q81Var2 = q81Var;
                                                        arrayList19 = arrayList12;
                                                    }
                                                    str14 = str2;
                                                    uri6 = uri4;
                                                    arrayList20 = arrayList;
                                                    strArr11 = strArrL4;
                                                    strArr12 = strArr4;
                                                    s81Var2 = s81Var;
                                                    n81Var2 = n81Var3;
                                                } else {
                                                    arrayList13 = arrayList8;
                                                    this = this;
                                                }
                                                if (!arrayList19.isEmpty()) {
                                                    pw0VarL = pw0.l(arrayList19);
                                                }
                                                pw0 pw0Var = pw0VarL;
                                                if (q81Var2.b == null && q81Var2.a == null) {
                                                    z3 = false;
                                                } else {
                                                    z3 = true;
                                                }
                                                ha1.s(z3);
                                                if (q81Var2.a != null) {
                                                    r81Var = new r81(q81Var2);
                                                } else {
                                                    r81Var = null;
                                                }
                                                arrayList13.add(new z81("", new p81(n81Var2), new u81(uri6, null, r81Var, null, list3, null, pw0Var, -9223372036854775807L), new t81(s81Var2), h91Var2, v81Var));
                                                if (strArr11 != null || i8 >= strArr11.length) {
                                                    str15 = null;
                                                } else {
                                                    str15 = strArr11[i8];
                                                }
                                                arrayList6.add(str15);
                                                arrayList18.add(E1(strArr9, i8));
                                                String[] strArr21 = strArr5;
                                                arrayList21 = arrayList14;
                                                arrayList21.add(E1(strArr21, i8));
                                                strArrL1 = strArr8;
                                                if (strArr8 != null || i8 >= strArrL1.length) {
                                                    str16 = null;
                                                } else {
                                                    str16 = strArrL1[i8];
                                                }
                                                arrayList22 = arrayList15;
                                                arrayList22.add(str16);
                                                strArr13 = strArr6;
                                                if (strArr6 != null || i8 >= strArr13.length || (str20 = strArr13[i8]) == null || str20.isEmpty()) {
                                                    str17 = null;
                                                } else {
                                                    str17 = strArr13[i8];
                                                }
                                                arrayList23 = arrayList17;
                                                arrayList23.add(str17);
                                                strArr14 = strArr7;
                                                if (strArr14 != null || i8 >= strArr14.length || (str19 = strArr14[i8]) == null || str19.isEmpty()) {
                                                    str18 = null;
                                                } else {
                                                    str18 = strArr14[i8];
                                                }
                                                arrayList2 = arrayList16;
                                                arrayList2.add(str18);
                                                arrayList24 = arrayList13;
                                                strArr15 = strArr21;
                                                bundle3 = bundle2;
                                                arrayList25 = arrayList20;
                                                arrayList25.add(U1(bundle3, "video_list.quality_levels." + i8, "video_list.quality_urls." + i8));
                                            } else {
                                                arrayList13 = arrayList8;
                                            }
                                            h91Var2 = h91Var;
                                            parcelableArr3 = parcelableArr2;
                                            arrayList14 = arrayList7;
                                            arrayList15 = arrayList10;
                                            strArr5 = strArrL6;
                                            strArr6 = strArrL7;
                                            strArr7 = strArrL8;
                                            strArr8 = strArr2;
                                            i8 = i4;
                                            str12 = type;
                                            bundle2 = extras;
                                            str13 = str;
                                            arrayList16 = arrayList2;
                                            arrayList17 = arrayList9;
                                            arrayList18 = arrayList11;
                                            strArr9 = strArrL5;
                                            strArr10 = strArr3;
                                            parcelableArr4 = parcelableArr;
                                            q81Var2 = q81Var;
                                            arrayList19 = arrayList12;
                                            str14 = str2;
                                            uri6 = uri4;
                                            arrayList20 = arrayList;
                                            strArr11 = strArrL4;
                                            strArr12 = strArr4;
                                            s81Var2 = s81Var;
                                            n81Var2 = n81Var;
                                            if (!arrayList19.isEmpty()) {
                                                pw0VarL = pw0.l(arrayList19);
                                            }
                                            pw0 pw0Var2 = pw0VarL;
                                            if (q81Var2.b == null) {
                                                z3 = true;
                                            } else {
                                                z3 = true;
                                            }
                                            ha1.s(z3);
                                            if (q81Var2.a != null) {
                                                r81Var = new r81(q81Var2);
                                            } else {
                                                r81Var = null;
                                            }
                                            arrayList13.add(new z81("", new p81(n81Var2), new u81(uri6, null, r81Var, null, list3, null, pw0Var2, -9223372036854775807L), new t81(s81Var2), h91Var2, v81Var));
                                            if (strArr11 != null) {
                                                str15 = null;
                                            } else {
                                                str15 = null;
                                            }
                                            arrayList6.add(str15);
                                            arrayList18.add(E1(strArr9, i8));
                                            String[] strArr22 = strArr5;
                                            arrayList21 = arrayList14;
                                            arrayList21.add(E1(strArr22, i8));
                                            strArrL1 = strArr8;
                                            if (strArr8 != null) {
                                                str16 = null;
                                            } else {
                                                str16 = null;
                                            }
                                            arrayList22 = arrayList15;
                                            arrayList22.add(str16);
                                            strArr13 = strArr6;
                                            if (strArr6 != null) {
                                                str17 = null;
                                            } else {
                                                str17 = null;
                                            }
                                            arrayList23 = arrayList17;
                                            arrayList23.add(str17);
                                            strArr14 = strArr7;
                                            if (strArr14 != null) {
                                                str18 = null;
                                            } else {
                                                str18 = null;
                                            }
                                            arrayList2 = arrayList16;
                                            arrayList2.add(str18);
                                            arrayList24 = arrayList13;
                                            strArr15 = strArr22;
                                            bundle3 = bundle2;
                                            arrayList25 = arrayList20;
                                            arrayList25.add(U1(bundle3, "video_list.quality_levels." + i8, "video_list.quality_urls." + i8));
                                        }
                                        int i17 = i8 + 1;
                                        ArrayList arrayList35 = arrayList23;
                                        str2 = str14;
                                        parcelableArr = parcelableArr4;
                                        arrayList9 = arrayList35;
                                        String str26 = str13;
                                        arrayList7 = arrayList21;
                                        strArrL2 = strArr10;
                                        arrayList11 = arrayList18;
                                        str = str26;
                                        strArrL8 = strArr14;
                                        strArrL5 = strArr9;
                                        extras = bundle3;
                                        type = str12;
                                        parcelableArr2 = parcelableArr3;
                                        strArrL6 = strArr15;
                                        arrayList10 = arrayList22;
                                        i4 = i17;
                                        data = data;
                                        arrayList8 = arrayList24;
                                        strArrL7 = strArr13;
                                        strArrL3 = strArr12;
                                        strArrL4 = strArr11;
                                        arrayList = arrayList25;
                                        length = i7;
                                    }
                                    str3 = type;
                                    bundle = extras;
                                    str4 = str;
                                    str5 = str2;
                                    ArrayList arrayList36 = arrayList8;
                                    uri = data;
                                    this.G3 = new long[arrayList36.size()];
                                    i5 = 0;
                                    while (true) {
                                        jArr = this.G3;
                                        if (i5 < jArr.length) {
                                            break;
                                        }
                                        jArr[i5] = -9223372036854775807L;
                                        i5++;
                                    }
                                } else {
                                    uri = data;
                                    str3 = type;
                                    str4 = "subs.enable";
                                    str5 = "subs";
                                    str6 = "return_result";
                                    str7 = "position";
                                    bundle = extras;
                                }
                            } else {
                                uri = data;
                                str3 = type;
                                str4 = "subs.enable";
                                str5 = "subs";
                                str6 = "return_result";
                                str7 = "position";
                                bundle = extras;
                            }
                        }
                        this.H.x(this, uri, str3);
                        if (bundle != null) {
                            parcelableArray = bundle.getParcelableArray(str4);
                            if (parcelableArray != null || parcelableArray.length <= 0) {
                                uri2 = null;
                            } else {
                                uri2 = (Uri) parcelableArray[0];
                            }
                            parcelableArray2 = bundle.getParcelableArray(str5);
                            stringArray = bundle.getStringArray("subs.name");
                            if (parcelableArray2 != null && parcelableArray2.length > 0) {
                                for (i = 0; i < parcelableArray2.length; i++) {
                                    Uri uri7 = (Uri) parcelableArray2[i];
                                    if (stringArray != null || stringArray.length <= i) {
                                        str9 = null;
                                    } else {
                                        str9 = stringArray[i];
                                    }
                                    this.N4.add(ij0.b(this, uri7, str9, uri7.equals(uri2), null, null));
                                }
                            }
                        }
                        if (this.N4.isEmpty()) {
                            this.I2();
                        }
                        if (bundle != null) {
                            this.O4 = bundle.getBoolean(str6);
                            str8 = str7;
                            if (bundle.containsKey(str8)) {
                                this.H.z(bundle.getInt(str8));
                                z = true;
                                this.V0 = true;
                            }
                        }
                    }
                    this.P0(data);
                }
            } else {
                if (data != null) {
                    lowerCase = path.toLowerCase();
                    strArr17 = wt2.b;
                    i13 = 0;
                    while (true) {
                        if (i13 < 7) {
                            if (lowerCase.endsWith("." + strArr17[i13])) {
                                i13++;
                            } else {
                                this.P0(data);
                            }
                        }
                    }
                }
                extras = intent.getExtras();
                str = "subs.enable";
                str2 = "subs";
                if (extras == null) {
                    uri = data;
                    str3 = type;
                    str4 = "subs.enable";
                    str5 = "subs";
                    str6 = "return_result";
                    str7 = "position";
                    bundle = extras;
                } else {
                    if (extras.containsKey("position")) {
                        z2 = true;
                    } else {
                        z2 = true;
                    }
                    this.k3 = z2;
                    if (z2) {
                        this.H.S0 = false;
                    } else {
                        extras.containsKey("title");
                    }
                    charSequence = extras.getCharSequence("title");
                    if (charSequence == null) {
                        string = null;
                    } else {
                        string = charSequence.toString();
                    }
                    this.y3 = wt2.k0(string);
                    string2 = extras.getString("thumbnail");
                    if (string2 != null) {
                        this.z3 = Uri.parse(string2);
                    }
                    this.A3 = extras.getString("segments");
                    this.B3 = extras.getStringArray("headers");
                    if (extras.containsKey("season")) {
                        strTrim = null;
                    } else {
                        strTrim = String.valueOf(obj3).trim();
                        if (strTrim.isEmpty()) {
                            strTrim = null;
                        }
                    }
                    if (strTrim == null) {
                        i2 = -1;
                    } else {
                        i2 = (int) Double.parseDouble(strTrim);
                    }
                    this.H3 = i2;
                    if (extras.containsKey("episode")) {
                        strTrim2 = null;
                    } else {
                        strTrim2 = String.valueOf(obj2).trim();
                        if (strTrim2.isEmpty()) {
                            strTrim2 = null;
                        }
                    }
                    if (strTrim2 == null) {
                        i3 = -1;
                    } else {
                        i3 = (int) Double.parseDouble(strTrim2);
                    }
                    this.I3 = i3;
                    this.J3 = extras.getString("imdb_id");
                    if (extras.containsKey("id")) {
                        strTrim3 = null;
                    } else {
                        strTrim3 = String.valueOf(obj).trim();
                        if (strTrim3.isEmpty()) {
                            strTrim3 = null;
                        }
                    }
                    this.K3 = strTrim3;
                    this.e4 = U1(extras, "quality_levels", "quality_urls");
                    if (extras.containsKey("video_list")) {
                        arrayList = this.d4;
                        arrayList2 = this.R3;
                        arrayList3 = this.Q3;
                        arrayList4 = this.N3;
                        ArrayList arrayList37 = this.M3;
                        arrayList5 = this.L3;
                        arrayList6 = this.D3;
                        arrayList7 = arrayList37;
                        arrayList8 = this.C3;
                        parcelableArray3 = extras.getParcelableArray("video_list");
                        if (parcelableArray3 == null) {
                            strArrL0 = L0("video_list", extras);
                        } else {
                            strArrL0 = null;
                        }
                        if (parcelableArray3 != null) {
                            arrayList9 = arrayList3;
                            length = parcelableArray3.length;
                        } else {
                            arrayList9 = arrayList3;
                            if (strArrL0 != null) {
                                length = strArrL0.length;
                            } else {
                                length = 0;
                            }
                        }
                        if (length == 0) {
                            arrayList10 = arrayList4;
                            strArrL1 = L0("video_list.name", extras);
                            parcelableArr = parcelableArray3;
                            strArrL2 = L0("video_list.filename", extras);
                            strArr = strArrL0;
                            strArrL3 = L0("video_list.thumbnail", extras);
                            arrayList11 = arrayList5;
                            strArrL4 = L0("video_list.segments", extras);
                            strArrL5 = L0("video_list.season", extras);
                            strArrL6 = L0("video_list.episode", extras);
                            strArrL7 = L0("video_list.imdb_id", extras);
                            strArrL8 = L0("video_list.id", extras);
                            parcelableArray4 = extras.getParcelableArray("video_list.subtitles");
                            if (parcelableArray4 != null) {
                                str6 = "return_result";
                                parcelableArr2 = parcelableArray4;
                                str7 = "position";
                            } else {
                                parcelableArrayList = extras.getParcelableArrayList("video_list.subtitles");
                                str6 = "return_result";
                                str7 = "position";
                                if (parcelableArrayList == null) {
                                    parcelableArr2 = null;
                                } else {
                                    parcelableArr2 = (Parcelable[]) parcelableArrayList.toArray(new Parcelable[0]);
                                }
                            }
                            arrayList8.clear();
                            arrayList6.clear();
                            arrayList11.clear();
                            arrayList7.clear();
                            arrayList10.clear();
                            this.O3.clear();
                            this.P3.clear();
                            arrayList9.clear();
                            arrayList2.clear();
                            arrayList.clear();
                            this.E3 = 0;
                            this.F3 = 0;
                            i4 = 0;
                            while (i4 < length) {
                                if (parcelableArr != null) {
                                    parcelable3 = parcelableArr[i4];
                                    i7 = length;
                                    if (parcelable3 instanceof Uri) {
                                        uri3 = (Uri) parcelable3;
                                        uri4 = uri3;
                                    } else {
                                        uri4 = null;
                                    }
                                } else {
                                    i7 = length;
                                    str10 = strArr[i4];
                                    if (str10 != null) {
                                        uri3 = Uri.parse(str10);
                                        uri4 = uri3;
                                    } else {
                                        uri4 = null;
                                    }
                                }
                                if (uri4 == null) {
                                    ArrayList arrayList38 = arrayList9;
                                    parcelableArr4 = parcelableArr;
                                    str14 = str2;
                                    arrayList23 = arrayList38;
                                    ArrayList arrayList39 = arrayList11;
                                    strArr10 = strArrL2;
                                    arrayList21 = arrayList7;
                                    str13 = str;
                                    arrayList18 = arrayList39;
                                    bundle3 = extras;
                                    parcelableArr3 = parcelableArr2;
                                    arrayList25 = arrayList;
                                    strArr11 = strArrL4;
                                    strArr9 = strArrL5;
                                    strArr15 = strArrL6;
                                    strArr12 = strArrL3;
                                    i8 = i4;
                                    arrayList22 = arrayList10;
                                    strArr13 = strArrL7;
                                    str12 = type;
                                    arrayList24 = arrayList8;
                                    strArr14 = strArrL8;
                                    data = data;
                                } else {
                                    if (strArrL1 != null) {
                                        str11 = null;
                                    } else {
                                        str11 = null;
                                    }
                                    if (str11 != null) {
                                        if (strArrL2 != null) {
                                            str11 = null;
                                        } else {
                                            str11 = null;
                                        }
                                    } else if (strArrL2 != null) {
                                        str11 = null;
                                    } else {
                                        str11 = null;
                                    }
                                    strK0 = wt2.k0(str11);
                                    if (strK0 != null) {
                                        strK0 = uri4.getLastPathSegment();
                                    } else {
                                        strK0 = uri4.getLastPathSegment();
                                    }
                                    strArr2 = strArrL1;
                                    if (strArrL3 != null) {
                                        uri5 = null;
                                    } else {
                                        uri5 = null;
                                    }
                                    strArr3 = strArrL2;
                                    g91Var = new g91();
                                    g91Var.a = strK0;
                                    g91Var.e = strK0;
                                    if (uri5 != null) {
                                        g91Var.n = uri5;
                                    }
                                    if (data != null) {
                                        int size2 = arrayList8.size();
                                        this.E3 = size2;
                                        this.F3 = size2;
                                    }
                                    n81Var = new n81();
                                    q81Var = new q81();
                                    List list4 = Collections.EMPTY_LIST;
                                    nw0 nw0Var2 = pw0.m;
                                    pw0VarL = cz1.p;
                                    strArr4 = strArrL3;
                                    s81 s81Var4 = new s81();
                                    v81 v81Var2 = v81.d;
                                    s81Var = s81Var4;
                                    h91Var = new h91(g91Var);
                                    arrayList12 = new ArrayList();
                                    if (parcelableArr2 != null) {
                                        if (i4 < parcelableArr2.length) {
                                            parcelable = parcelableArr2[i4];
                                            n81Var3 = n81Var;
                                            if (parcelable instanceof Bundle) {
                                                ArrayList arrayList310 = arrayList8;
                                                data = data;
                                                arrayList13 = arrayList310;
                                                h91Var2 = h91Var;
                                                parcelableArr3 = parcelableArr2;
                                                arrayList14 = arrayList7;
                                                arrayList15 = arrayList10;
                                                strArr5 = strArrL6;
                                                strArr6 = strArrL7;
                                                strArr7 = strArrL8;
                                                strArr8 = strArr2;
                                                i8 = i4;
                                                str12 = type;
                                                bundle2 = extras;
                                                str13 = str;
                                                arrayList16 = arrayList2;
                                                arrayList17 = arrayList9;
                                                arrayList18 = arrayList11;
                                                strArr9 = strArrL5;
                                                strArr10 = strArr3;
                                                parcelableArr4 = parcelableArr;
                                                q81Var2 = q81Var;
                                                arrayList19 = arrayList12;
                                            } else {
                                                bundle4 = (Bundle) parcelable;
                                                parcelableArray5 = bundle4.getParcelableArray("uris");
                                                if (parcelableArray5 != null) {
                                                    arrayList26 = arrayList12;
                                                    parcelableArr5 = parcelableArray5;
                                                    q81Var3 = q81Var;
                                                } else {
                                                    parcelableArrayList2 = bundle4.getParcelableArrayList("uris");
                                                    q81Var3 = q81Var;
                                                    arrayList26 = arrayList12;
                                                    if (parcelableArrayList2 == null) {
                                                        parcelableArr5 = null;
                                                    } else {
                                                        parcelableArr5 = (Parcelable[]) parcelableArrayList2.toArray(new Parcelable[0]);
                                                    }
                                                }
                                                if (parcelableArr5 != null) {
                                                    strArrL9 = L0("names", bundle4);
                                                    i9 = 0;
                                                    while (i9 < parcelableArr5.length) {
                                                        parcelable2 = parcelableArr5[i9];
                                                        Parcelable[] parcelableArr7 = parcelableArr5;
                                                        if (parcelable2 instanceof Uri) {
                                                            arrayList27 = arrayList8;
                                                            arrayList28 = arrayList26;
                                                            strArr16 = strArrL7;
                                                            i11 = i9;
                                                            i12 = i4;
                                                        } else {
                                                            if (strArrL9 != null) {
                                                                i10 = i9;
                                                                str21 = null;
                                                            } else {
                                                                i10 = i9;
                                                                str21 = null;
                                                            }
                                                            i11 = i10;
                                                            arrayList27 = arrayList8;
                                                            arrayList28 = arrayList26;
                                                            strArr16 = strArrL7;
                                                            i12 = i4;
                                                            arrayList28.add(ij0.b(this, (Uri) parcelable2, str21, false, null, null));
                                                        }
                                                        i9 = i11 + 1;
                                                        arrayList8 = arrayList27;
                                                        data = data;
                                                        String[] strArr110 = strArr16;
                                                        arrayList26 = arrayList28;
                                                        arrayList2 = arrayList2;
                                                        strArrL7 = strArr110;
                                                        q81Var3 = q81Var3;
                                                        s81Var = s81Var;
                                                        i4 = i12;
                                                        n81Var3 = n81Var3;
                                                        type = type;
                                                        strArr4 = strArr4;
                                                        strArrL9 = strArrL9;
                                                        uri4 = uri4;
                                                        parcelableArr5 = parcelableArr7;
                                                        h91Var = h91Var;
                                                        parcelableArr2 = parcelableArr2;
                                                        arrayList10 = arrayList10;
                                                        strArr2 = strArr2;
                                                        str2 = str2;
                                                        strArrL4 = strArrL4;
                                                        parcelableArr = parcelableArr;
                                                        strArr3 = strArr3;
                                                        arrayList = arrayList;
                                                        arrayList9 = arrayList9;
                                                        strArrL8 = strArrL8;
                                                        strArrL5 = strArrL5;
                                                        arrayList11 = arrayList11;
                                                        str = str;
                                                        extras = extras;
                                                        arrayList7 = arrayList7;
                                                        strArrL6 = strArrL6;
                                                    }
                                                }
                                                ArrayList arrayList311 = arrayList8;
                                                data = data;
                                                arrayList13 = arrayList311;
                                                String[] strArr23 = strArrL7;
                                                arrayList16 = arrayList2;
                                                arrayList19 = arrayList26;
                                                strArr6 = strArr23;
                                                h91Var2 = h91Var;
                                                parcelableArr3 = parcelableArr2;
                                                arrayList14 = arrayList7;
                                                arrayList15 = arrayList10;
                                                strArr5 = strArrL6;
                                                strArr7 = strArrL8;
                                                strArr8 = strArr2;
                                                i8 = i4;
                                                str12 = type;
                                                bundle2 = extras;
                                                str13 = str;
                                                arrayList17 = arrayList9;
                                                arrayList18 = arrayList11;
                                                strArr9 = strArrL5;
                                                strArr10 = strArr3;
                                                q81Var2 = q81Var3;
                                                parcelableArr4 = parcelableArr;
                                            }
                                            str14 = str2;
                                            uri6 = uri4;
                                            arrayList20 = arrayList;
                                            strArr11 = strArrL4;
                                            strArr12 = strArr4;
                                            s81Var2 = s81Var;
                                            n81Var2 = n81Var3;
                                        } else {
                                            arrayList13 = arrayList8;
                                            this = this;
                                        }
                                        if (!arrayList19.isEmpty()) {
                                            pw0VarL = pw0.l(arrayList19);
                                        }
                                        pw0 pw0Var3 = pw0VarL;
                                        if (q81Var2.b == null) {
                                            z3 = true;
                                        } else {
                                            z3 = true;
                                        }
                                        ha1.s(z3);
                                        if (q81Var2.a != null) {
                                            r81Var = new r81(q81Var2);
                                        } else {
                                            r81Var = null;
                                        }
                                        arrayList13.add(new z81("", new p81(n81Var2), new u81(uri6, null, r81Var, null, list4, null, pw0Var3, -9223372036854775807L), new t81(s81Var2), h91Var2, v81Var2));
                                        if (strArr11 != null) {
                                            str15 = null;
                                        } else {
                                            str15 = null;
                                        }
                                        arrayList6.add(str15);
                                        arrayList18.add(E1(strArr9, i8));
                                        String[] strArr24 = strArr5;
                                        arrayList21 = arrayList14;
                                        arrayList21.add(E1(strArr24, i8));
                                        strArrL1 = strArr8;
                                        if (strArr8 != null) {
                                            str16 = null;
                                        } else {
                                            str16 = null;
                                        }
                                        arrayList22 = arrayList15;
                                        arrayList22.add(str16);
                                        strArr13 = strArr6;
                                        if (strArr6 != null) {
                                            str17 = null;
                                        } else {
                                            str17 = null;
                                        }
                                        arrayList23 = arrayList17;
                                        arrayList23.add(str17);
                                        strArr14 = strArr7;
                                        if (strArr14 != null) {
                                            str18 = null;
                                        } else {
                                            str18 = null;
                                        }
                                        arrayList2 = arrayList16;
                                        arrayList2.add(str18);
                                        arrayList24 = arrayList13;
                                        strArr15 = strArr24;
                                        bundle3 = bundle2;
                                        arrayList25 = arrayList20;
                                        arrayList25.add(U1(bundle3, "video_list.quality_levels." + i8, "video_list.quality_urls." + i8));
                                    } else {
                                        arrayList13 = arrayList8;
                                    }
                                    h91Var2 = h91Var;
                                    parcelableArr3 = parcelableArr2;
                                    arrayList14 = arrayList7;
                                    arrayList15 = arrayList10;
                                    strArr5 = strArrL6;
                                    strArr6 = strArrL7;
                                    strArr7 = strArrL8;
                                    strArr8 = strArr2;
                                    i8 = i4;
                                    str12 = type;
                                    bundle2 = extras;
                                    str13 = str;
                                    arrayList16 = arrayList2;
                                    arrayList17 = arrayList9;
                                    arrayList18 = arrayList11;
                                    strArr9 = strArrL5;
                                    strArr10 = strArr3;
                                    parcelableArr4 = parcelableArr;
                                    q81Var2 = q81Var;
                                    arrayList19 = arrayList12;
                                    str14 = str2;
                                    uri6 = uri4;
                                    arrayList20 = arrayList;
                                    strArr11 = strArrL4;
                                    strArr12 = strArr4;
                                    s81Var2 = s81Var;
                                    n81Var2 = n81Var;
                                    if (!arrayList19.isEmpty()) {
                                        pw0VarL = pw0.l(arrayList19);
                                    }
                                    pw0 pw0Var4 = pw0VarL;
                                    if (q81Var2.b == null) {
                                        z3 = true;
                                    } else {
                                        z3 = true;
                                    }
                                    ha1.s(z3);
                                    if (q81Var2.a != null) {
                                        r81Var = new r81(q81Var2);
                                    } else {
                                        r81Var = null;
                                    }
                                    arrayList13.add(new z81("", new p81(n81Var2), new u81(uri6, null, r81Var, null, list4, null, pw0Var4, -9223372036854775807L), new t81(s81Var2), h91Var2, v81Var2));
                                    if (strArr11 != null) {
                                        str15 = null;
                                    } else {
                                        str15 = null;
                                    }
                                    arrayList6.add(str15);
                                    arrayList18.add(E1(strArr9, i8));
                                    String[] strArr25 = strArr5;
                                    arrayList21 = arrayList14;
                                    arrayList21.add(E1(strArr25, i8));
                                    strArrL1 = strArr8;
                                    if (strArr8 != null) {
                                        str16 = null;
                                    } else {
                                        str16 = null;
                                    }
                                    arrayList22 = arrayList15;
                                    arrayList22.add(str16);
                                    strArr13 = strArr6;
                                    if (strArr6 != null) {
                                        str17 = null;
                                    } else {
                                        str17 = null;
                                    }
                                    arrayList23 = arrayList17;
                                    arrayList23.add(str17);
                                    strArr14 = strArr7;
                                    if (strArr14 != null) {
                                        str18 = null;
                                    } else {
                                        str18 = null;
                                    }
                                    arrayList2 = arrayList16;
                                    arrayList2.add(str18);
                                    arrayList24 = arrayList13;
                                    strArr15 = strArr25;
                                    bundle3 = bundle2;
                                    arrayList25 = arrayList20;
                                    arrayList25.add(U1(bundle3, "video_list.quality_levels." + i8, "video_list.quality_urls." + i8));
                                }
                                int i18 = i8 + 1;
                                ArrayList arrayList312 = arrayList23;
                                str2 = str14;
                                parcelableArr = parcelableArr4;
                                arrayList9 = arrayList312;
                                String str27 = str13;
                                arrayList7 = arrayList21;
                                strArrL2 = strArr10;
                                arrayList11 = arrayList18;
                                str = str27;
                                strArrL8 = strArr14;
                                strArrL5 = strArr9;
                                extras = bundle3;
                                type = str12;
                                parcelableArr2 = parcelableArr3;
                                strArrL6 = strArr15;
                                arrayList10 = arrayList22;
                                i4 = i18;
                                data = data;
                                arrayList8 = arrayList24;
                                strArrL7 = strArr13;
                                strArrL3 = strArr12;
                                strArrL4 = strArr11;
                                arrayList = arrayList25;
                                length = i7;
                            }
                            str3 = type;
                            bundle = extras;
                            str4 = str;
                            str5 = str2;
                            ArrayList arrayList313 = arrayList8;
                            uri = data;
                            this.G3 = new long[arrayList313.size()];
                            i5 = 0;
                            while (true) {
                                jArr = this.G3;
                                if (i5 < jArr.length) {
                                    break;
                                    break;
                                } else {
                                    jArr[i5] = -9223372036854775807L;
                                    i5++;
                                }
                            }
                        } else {
                            uri = data;
                            str3 = type;
                            str4 = "subs.enable";
                            str5 = "subs";
                            str6 = "return_result";
                            str7 = "position";
                            bundle = extras;
                        }
                    } else {
                        uri = data;
                        str3 = type;
                        str4 = "subs.enable";
                        str5 = "subs";
                        str6 = "return_result";
                        str7 = "position";
                        bundle = extras;
                    }
                }
                this.H.x(this, uri, str3);
                if (bundle != null) {
                    parcelableArray = bundle.getParcelableArray(str4);
                    if (parcelableArray != null) {
                        uri2 = null;
                    } else {
                        uri2 = null;
                    }
                    parcelableArray2 = bundle.getParcelableArray(str5);
                    stringArray = bundle.getStringArray("subs.name");
                    if (parcelableArray2 != null) {
                        while (i < parcelableArray2.length) {
                            Uri uri8 = (Uri) parcelableArray2[i];
                            if (stringArray != null) {
                                str9 = null;
                            } else {
                                str9 = null;
                            }
                            this.N4.add(ij0.b(this, uri8, str9, uri8.equals(uri2), null, null));
                        }
                    }
                }
                if (this.N4.isEmpty()) {
                    this.I2();
                }
                if (bundle != null) {
                    this.O4 = bundle.getBoolean(str6);
                    str8 = str7;
                    if (bundle.containsKey(str8)) {
                        this.H.z(bundle.getInt(str8));
                        z = true;
                        this.V0 = true;
                    }
                }
            }
            P6 = z;
            this.S(false, false);
        }
        Bundle bundle5 = intent.getExtras().getBundle("playlist");
        String strY = ha1.Y("title", bundle5);
        Integer numA = ha1.A("start_index", bundle5);
        String[] strArrZ = ha1.Z(bundle5);
        ArrayList arrayList40 = new ArrayList();
        vp2 vp2VarC = vp2.c(bundle5, "audio", "playlist", arrayList40);
        vp2 vp2VarC2 = vp2.c(bundle5, "subtitle", "playlist", arrayList40);
        String strY2 = ha1.Y("logo", bundle5);
        String strY3 = ha1.Y("background", bundle5);
        Parcelable[] parcelableArrK = ha1.K("items", bundle5);
        if (parcelableArrK == null || parcelableArrK.length == 0) {
            j = -9223372036854775807L;
            vs1VarA = vs1.a(strY, "playlist has no items");
        } else {
            ArrayList arrayList41 = new ArrayList();
            int i19 = 0;
            j = -9223372036854775807L;
            while (true) {
                if (i19 >= parcelableArrK.length) {
                    ArrayList arrayList42 = arrayList40;
                    int iIntValue = numA == null ? 0 : numA.intValue();
                    if (iIntValue >= 0 && iIntValue < arrayList41.size()) {
                        Double dF = ha1.F("report_interval", bundle5);
                        long jMax = (dF == null || dF.doubleValue() <= 0.0d) ? 0L : Math.max(30000L, (long) Math.floor(dF.doubleValue()));
                        String strY4 = ha1.Y("resume_mode", bundle5);
                        if (strY4 != null) {
                            List list5 = vs1.k;
                            if (list5.contains(strY4)) {
                                str25 = strY4;
                            } else {
                                ha1.h0(arrayList42, "playlist.resume_mode", "\"" + strY4 + "\" is not one of " + list5);
                                str25 = null;
                            }
                        } else {
                            str25 = strY4;
                        }
                        vs1VarA = new vs1(strY, iIntValue, strArrZ, vp2VarC, vp2VarC2, DesugarCollections.unmodifiableList(arrayList41), jMax, str25, null, DesugarCollections.unmodifiableList(arrayList42));
                        break;
                    }
                    StringBuilder sbV = uh0.v("start_index ", iIntValue, " is out of range 0..");
                    sbV.append(arrayList41.size() - 1);
                    vs1VarA = vs1.a(strY, sbV.toString());
                    break;
                }
                Parcelable parcelable4 = parcelableArrK[i19];
                if (!(parcelable4 instanceof Bundle)) {
                    vs1VarA = vs1.a(strY, "items[" + i19 + "] is not a Bundle");
                    break;
                }
                try {
                    Bundle bundle6 = (Bundle) parcelable4;
                    Integer num = vp2VarC2.a;
                    ArrayList arrayList43 = arrayList40;
                    arrayList41.add(vs1.d(bundle6, strY2, strY3, num != null && num.intValue() >= 0, "items[" + i19 + "]", arrayList43));
                    i19++;
                    arrayList40 = arrayList43;
                } catch (IllegalArgumentException e) {
                    StringBuilder sbV2 = uh0.v("items[", i19, "] ");
                    sbV2.append(e.getMessage());
                    vs1VarA = vs1.a(strY, sbV2.toString());
                }
            }
        }
        this.k3 = true;
        this.O4 = true;
        this.H.S0 = false;
        this.l3 = vs1VarA;
        Iterator it = vs1VarA.j.iterator();
        while (it.hasNext()) {
            wt2.K("playlist key dropped: " + ((String) it.next()));
        }
        if (vs1VarA.i != null) {
            wt2.K("playlist refused: " + vs1VarA.i);
            c7 c7Var = new c7(0);
            this.m3 = c7Var;
            this.o3 = null;
            c7Var.e = vs1VarA.i;
            this.U2();
            Toast.makeText(this, R.string.api_bad_playlist, 1).show();
            this.finish();
        } else {
            this.y3 = wt2.k0(vs1VarA.a);
            this.G3 = new long[vs1VarA.f.size()];
            HashMap map = new HashMap();
            int i20 = 0;
            while (true) {
                int size3 = vs1VarA.f.size();
                list = vs1VarA.f;
                if (i20 >= size3) {
                    break;
                }
                rs1 rs1Var = (rs1) list.get(i20);
                String str28 = rs1Var.b;
                if (str28 == null) {
                    str28 = vs1VarA.a;
                }
                String strK1 = wt2.k0(str28);
                if (strK1 == null || strK1.isEmpty()) {
                    strK1 = rs1Var.a.getLastPathSegment();
                }
                String strT0 = this.t0(wt2.k0(rs1Var.c), rs1Var.i, rs1Var.j);
                g91 g91Var2 = new g91();
                g91Var2.a = strK1;
                g91Var2.e = strK1;
                g91Var2.f = strT0;
                Uri uri9 = rs1Var.d;
                if (uri9 != null) {
                    g91Var2.n = uri9;
                }
                n81 n81Var4 = new n81();
                q81 q81Var4 = new q81();
                List list6 = Collections.EMPTY_LIST;
                nw0 nw0Var3 = pw0.m;
                cz1 cz1Var = cz1.p;
                s81 s81Var5 = new s81();
                v81 v81Var3 = v81.d;
                Uri uri10 = rs1Var.a;
                h91 h91Var3 = new h91(g91Var2);
                int i21 = i14;
                long j2 = rs1Var.m;
                if (j2 > 0 || rs1Var.n != Long.MIN_VALUE) {
                    n81 n81Var5 = new n81();
                    long jY = ot2.Y(j2);
                    ha1.h(jY >= 0 ? 1 : i21);
                    n81Var5.a = jY;
                    long jY2 = ot2.Y(rs1Var.n);
                    ha1.h((jY2 == Long.MIN_VALUE || jY2 >= 0) ? 1 : i21);
                    n81Var5.b = jY2;
                    n81Var4 = new o81(n81Var5).a();
                }
                pw0 pw0VarL2 = !rs1Var.s.isEmpty() ? pw0.l(this.V3(rs1Var.s)) : cz1Var;
                ArrayList arrayList44 = this.C3;
                ha1.s((q81Var4.b == null || q81Var4.a != null) ? 1 : i21);
                arrayList44.add(new z81("", new p81(n81Var4), uri10 != null ? new u81(uri10, null, q81Var4.a != null ? new r81(q81Var4) : null, null, list6, null, pw0VarL2, -9223372036854775807L) : null, new t81(s81Var5), h91Var3, v81Var3));
                this.D3.add(rs1Var.q);
                ArrayList arrayList45 = this.L3;
                int i22 = rs1Var.i;
                arrayList45.add(i22 < 0 ? null : Integer.valueOf(i22));
                ArrayList arrayList46 = this.M3;
                int i23 = rs1Var.j;
                arrayList46.add(i23 < 0 ? null : Integer.valueOf(i23));
                this.N3.add(rs1Var.c);
                this.O3.add(rs1Var.e);
                this.P3.add(rs1Var.f);
                this.Q3.add(rs1Var.g);
                this.R3.add(rs1Var.h);
                this.d4.add(R1(rs1Var.r));
                this.G3[i20] = rs1Var.l;
                TreeMap treeMap = new TreeMap(String.CASE_INSENSITIVE_ORDER);
                String[] strArr26 = vs1VarA.c;
                if (strArr26 != null) {
                    int i24 = i21;
                    while (true) {
                        int i25 = i24 + 1;
                        if (i25 >= strArr26.length) {
                            break;
                        }
                        String str29 = strArr26[i24];
                        if (str29 != null && (str24 = strArr26[i25]) != null) {
                            treeMap.put(str29, str24);
                        }
                        i24 += 2;
                    }
                }
                String[] strArr27 = rs1Var.k;
                if (strArr27 != null) {
                    int i26 = i21;
                    while (true) {
                        int i27 = i26 + 1;
                        if (i27 >= strArr27.length) {
                            break;
                        }
                        String str30 = strArr27[i26];
                        if (str30 != null && (str23 = strArr27[i27]) != null) {
                            treeMap.put(str30, str23);
                        }
                        i26 += 2;
                    }
                }
                if (!treeMap.isEmpty()) {
                    map.put(rs1Var.a.toString(), treeMap);
                }
                i20++;
                i14 = i21;
            }
            int i28 = i14;
            this.m3 = new c7(list.size());
            this.o3 = null;
            Handler handler = this.M5;
            nq1 nq1Var = this.N5;
            handler.removeCallbacks(nq1Var);
            vs1 vs1Var = this.l3;
            if (vs1Var != null) {
                long j3 = vs1Var.g;
                if (j3 > 0) {
                    handler.postDelayed(nq1Var, j3);
                }
            }
            int i29 = vs1VarA.b;
            this.E3 = i29;
            this.F3 = i29;
            this.w3 = map.isEmpty() ? null : map;
            this.x3 = (Map) map.get(((rs1) vs1VarA.f.get(vs1VarA.b)).a.toString());
            HashMap map2 = this.v3;
            map2.clear();
            wj2 wj2VarN = gu1.n(this, this.j4());
            ArrayList<String> arrayListD0 = wt2.d0(this.H.W);
            ArrayList arrayList47 = new ArrayList();
            for (String str31 : arrayListD0) {
                for (String[] strArr28 : wj2VarN.Q()) {
                    if (strArr28[i28].equals(str31)) {
                        arrayList47.add(strArr28);
                    }
                }
            }
            if (arrayListD0.isEmpty()) {
                arrayList47.addAll(wj2VarN.Q());
            }
            int i30 = i28;
            while (true) {
                int size4 = vs1VarA.f.size();
                list2 = vs1VarA.f;
                if (i30 >= size4) {
                    break;
                }
                rs1 rs1Var2 = (rs1) list2.get(i30);
                List list7 = rs1Var2.t;
                Uri uri11 = rs1Var2.a;
                if (list7.size() < 2 || rs1Var2.u) {
                    arrayList29 = arrayList47;
                    c = c2;
                } else {
                    String strN0 = this.n0(i30);
                    String[] strArr29 = strN0 == null ? null : (String[]) ((LinkedHashMap) wj2VarN.o).get(strN0);
                    int iS4 = strArr29 == null ? -1 : this.S4(rs1Var2, strArr29[c2]);
                    int i31 = R.string.audio_memory_as_before;
                    int iS5 = iS4;
                    int i32 = i28;
                    while (true) {
                        c = c2;
                        if (iS5 >= 0 || i32 >= arrayList47.size()) {
                            break;
                        }
                        iS5 = this.S4(rs1Var2, ((String[]) arrayList47.get(i32))[c]);
                        if (iS5 >= 0 && i30 == vs1VarA.b) {
                            if (strArr29 == null) {
                                wj2VarN.R(strN0, null, ((us1) list7.get(iS5)).a);
                            }
                            wj2VarN.M(strN0, strN0 != null ? strN0 : uri11.toString(), ((String[]) arrayList47.get(i32))[i28], ((us1) list7.get(iS5)).a);
                            gu1.t(this, wj2VarN);
                        }
                        i32++;
                        i31 = R.string.audio_memory_usual;
                        arrayList47 = arrayList47;
                        c2 = c;
                    }
                    arrayList29 = arrayList47;
                    if (iS5 >= 0) {
                        map2.put(Integer.valueOf(i30), new int[]{iS5, i31});
                        if (iS5 != rs1Var2.a(uri11.toString())) {
                            this.C3.set(i30, this.J3(i30, ((us1) list7.get(iS5)).b));
                        }
                    }
                }
                i30++;
                arrayList47 = arrayList29;
                c2 = c;
            }
            boolean z4 = c2;
            rs1 rs1Var3 = (rs1) list2.get(vs1VarA.b);
            this.H.x(this, rs1Var3.a, type);
            long j4 = rs1Var3.l;
            if (j4 != j) {
                this.H.z(j4);
                this.V0 = z4;
            }
            if (!y1(rs1Var3, vs1VarA)) {
                this.I2();
            }
        }
        z = true;
        P6 = z;
        this.S(false, false);
    }

    /* JADX WARN: Code duplicated, block: B:51:0x00da  */
    public final boolean Q2(boolean z, boolean z2) {
        long j;
        if (k6 == null) {
            return false;
        }
        long jUptimeMillis = SystemClock.uptimeMillis();
        if (z2) {
            if (jUptimeMillis - this.N2 < (this.Y2 ? 200L : 600L)) {
                return true;
            }
        }
        cz czVar = this.B;
        czVar.removeCallbacks(czVar.C0);
        this.B.z();
        long jO0 = k6.O0();
        cz czVar2 = this.B;
        if (czVar2.n0 == -1) {
            czVar2.n0 = jO0;
        }
        long duration = k6.getDuration();
        if (duration <= 0) {
            L();
            long jMax = Math.max(0L, jO0 + ((long) (z ? 3000 : -3000)));
            k6.t1(a92.c);
            N2(jMax);
            this.N2 = jUptimeMillis;
            m3(jMax);
            return true;
        }
        this.L2 = (z != this.M2 || (!z2 && jUptimeMillis - this.N2 >= 450)) ? 0 : this.L2 + 1;
        this.M2 = z;
        this.N2 = jUptimeMillis;
        long j2 = this.I2;
        if (j2 < 0) {
            this.K2 = jO0;
            j2 = jO0;
        }
        long jMin = 3000;
        long jMin2 = Math.min(60000L, Math.max(3000L, duration / 10));
        int i = this.L2;
        if (i >= 2) {
            if (i < 4) {
                jMin = Math.min(10000L, jMin2);
            } else {
                jMin = i < 8 ? Math.min(30000L, jMin2) : jMin2;
            }
        }
        long jMax2 = Math.max(0L, Math.min(duration, j2 + (z ? jMin : -jMin)));
        this.I2 = jMax2;
        if (k6 != null) {
            Uri uriD0 = d0();
            z82 z82Var = uriD0 == null ? null : (z82) V6.get(uriD0.toString());
            if (z82Var == null || !z82Var.d()) {
                j = -9223372036854775807L;
            } else {
                y82 y82VarE = z82Var.e(Math.max(0L, jMax2) * 1000);
                long j3 = y82VarE.a.a / 1000;
                long j4 = y82VarE.b.a / 1000;
                boolean z3 = jMax2 > jO0;
                j = z3 ? j4 : j3;
                if (Math.abs(j - jMax2) > jMin) {
                    if (!z3) {
                        j3 = j4;
                    }
                    j = j3;
                }
                if (!z3 ? j < jO0 : j > jO0) {
                    j = -9223372036854775807L;
                } else if (Math.abs(j - jMax2) > jMin) {
                    j = -9223372036854775807L;
                }
            }
        } else {
            j = -9223372036854775807L;
        }
        if (j != -9223372036854775807L) {
            this.I2 = j;
        }
        m3(this.I2);
        k6.t1(a92.c);
        if (P2(this.I2)) {
            this.J2 = this.I2;
        }
        cz czVar3 = this.B;
        wp1 wp1Var = this.O2;
        czVar3.removeCallbacks(wp1Var);
        this.B.postDelayed(wp1Var, 700L);
        return true;
    }

    public final void Q3(String str, String str2) {
        c7 c7Var = this.m3;
        if (c7Var != null) {
            c7Var.e = str;
        }
        vg0 vg0Var = k6;
        if (vg0Var != null && m6 && vg0Var.x()) {
            this.H.z(k6.O0());
        }
        z3(str, str2, new dp1(this, (byte) 18));
        b2(false);
        this.B.setPlayer(null);
        this.B.setControllerShowTimeoutMs(-1);
        this.B.j();
    }

    public final void Q4(boolean z) {
        vg0 vg0Var;
        if (this.m3 == null || (vg0Var = k6) == null) {
            return;
        }
        int iV = vg0Var.V();
        c7 c7Var = this.m3;
        if (z) {
            if (c7Var.j(iV)) {
                ((String[]) c7Var.h)[iV] = "viewer";
            }
        } else if (c7Var.j(iV)) {
            ((String[]) c7Var.i)[iV] = "viewer";
        }
    }

    public final boolean R() {
        Boolean bool = this.m4;
        return bool != null ? bool.booleanValue() : this.H.O;
    }

    public final int R0(zl0 zl0Var, String str) {
        int i;
        int i2;
        int i3;
        String str2;
        int i4 = 0;
        if (zl0Var != null) {
            float f = zl0Var.B;
            int i5 = zl0Var.x;
            int i7 = zl0Var.w;
            String str3 = zl0Var.p;
            if (str3 != null && i7 != -1 && i5 != -1) {
                if (str != null) {
                    str3 = str;
                }
                boolean z = f != -1.0f && f > 0.0f;
                StringBuilder sb = new StringBuilder(str3);
                sb.append(' ');
                sb.append(i7);
                sb.append('x');
                sb.append(i5);
                sb.append('@');
                sb.append(z ? Math.round(f) : 0);
                String string = sb.toString();
                HashMap map = this.w0;
                Integer num = (Integer) map.get(string);
                if (num != null) {
                    return num.intValue();
                }
                try {
                    MediaCodecInfo[] codecInfos = new MediaCodecList(0).getCodecInfos();
                    int length = codecInfos.length;
                    int i8 = 0;
                    boolean z2 = false;
                    boolean z3 = false;
                    while (true) {
                        if (i8 >= length) {
                            i = i4;
                            i2 = i;
                            break;
                        }
                        MediaCodecInfo mediaCodecInfo = codecInfos[i8];
                        if (mediaCodecInfo.isEncoder() || i1(mediaCodecInfo)) {
                            i = i4;
                        } else {
                            i = i4;
                            try {
                                String[] supportedTypes = mediaCodecInfo.getSupportedTypes();
                                int length2 = supportedTypes.length;
                                int i9 = i;
                                while (i9 < length2) {
                                    String[] strArr = supportedTypes;
                                    if (strArr[i9].equalsIgnoreCase(str3)) {
                                        MediaCodecInfo.VideoCapabilities videoCapabilities = mediaCodecInfo.getCapabilitiesForType(str3).getVideoCapabilities();
                                        if (videoCapabilities == null) {
                                            break;
                                        }
                                        if (!videoCapabilities.isSizeSupported(i7, i5)) {
                                            z2 = true;
                                            break;
                                        }
                                        if (z && !videoCapabilities.areSizeAndRateSupported(i7, i5, f)) {
                                            z2 = true;
                                            z3 = true;
                                            break;
                                        }
                                        i2 = 1;
                                        z2 = true;
                                        z3 = true;
                                        break;
                                    }
                                    i9++;
                                    supportedTypes = strArr;
                                }
                            } catch (Exception e) {
                                e = e;
                                wt2.K("decoder query failed: " + e);
                                return i;
                            }
                        }
                        i8++;
                        i4 = i;
                    }
                    if (!z2) {
                        i3 = 3;
                    } else if (i2 != 0) {
                        i3 = i;
                    } else {
                        i3 = z3 ? 1 : 2;
                    }
                    map.put(string, Integer.valueOf(i3));
                    StringBuilder sb2 = new StringBuilder("decoder verdict: ");
                    sb2.append(string);
                    sb2.append(' ');
                    if (i3 == 1) {
                        str2 = "hardware takes the size but not the rate - asking best-effort";
                    } else if (i3 != 2) {
                        str2 = i3 != 3 ? "within the hardware" : "no hardware decoder for this codec";
                    } else {
                        str2 = "no hardware decoder takes this size";
                    }
                    sb2.append(str2);
                    wt2.K(sb2.toString());
                    return i3;
                } catch (Exception e2) {
                    e = e2;
                    i = i4;
                }
            }
        }
        return 0;
    }

    /* JADX WARN: Code duplicated, block: B:25:0x004a  */
    /* JADX WARN: Code duplicated, block: B:28:0x0058  */
    /* JADX WARN: Code duplicated, block: B:31:0x0068  */
    /* JADX WARN: Code duplicated, block: B:34:0x007b  */
    /* JADX WARN: Code duplicated, block: B:40:0x008d  */
    /* JADX WARN: Code duplicated, block: B:42:0x0097  */
    /* JADX WARN: Code duplicated, block: B:44:0x009f  */
    /* JADX WARN: Code duplicated, block: B:47:0x00aa  */
    /* JADX WARN: Code duplicated, block: B:51:0x00b5  */
    /* JADX WARN: Code duplicated, block: B:55:0x00c4  */
    /* JADX WARN: Code duplicated, block: B:57:0x00ca  */
    /* JADX WARN: Code duplicated, block: B:58:0x00cc  */
    /* JADX WARN: Code duplicated, block: B:67:0x00e0  */
    /* JADX WARN: Code duplicated, block: B:71:0x0088 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:73:0x0075 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:76:0x00da A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:77:0x00da A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:80:0x00da A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:81:0x00da A[SYNTHETIC] */
    public final boolean R2() {
        nq2 nq2VarE;
        ArrayList arrayListD0;
        nw0 nw0VarN;
        tp2 tp2Var;
        int i;
        boolean z;
        mq2 mq2Var;
        int i2;
        zl0 zl0VarA;
        boolean zH1;
        String strK4;
        String str;
        int iIndexOf;
        boolean z2;
        if (k6 != null && this.L4 == null && this.H.n == null && !T0(3) && !this.I4) {
            vg0 vg0Var = k6;
            if (vg0Var == null) {
                nq2VarE = k6.E();
                if (!p1()) {
                    arrayListD0 = wt2.d0(this.H.X);
                    if (!arrayListD0.isEmpty()) {
                        int size = arrayListD0.size();
                        nw0VarN = nq2VarE.a.listIterator(0);
                        tp2Var = null;
                        i = 0;
                        z = true;
                        while (nw0VarN.hasNext()) {
                            mq2Var = (mq2) nw0VarN.next();
                            if (mq2Var.b.c != 3) {
                                while (i2 < mq2Var.a) {
                                    zl0VarA = mq2Var.a(i2);
                                    if (mq2Var.c(i2, false)) {
                                        zH1 = h1(zl0VarA);
                                        strK4 = zl0VarA.b;
                                        if (!zH1) {
                                            str = zl0VarA.d;
                                            vp2 vp2Var = vp2.f;
                                            if (ha1.D(str) == null) {
                                                if (strK4 != null) {
                                                    strK4 = k4(zl0VarA);
                                                } else {
                                                    strK4 = k4(zl0VarA);
                                                }
                                                iIndexOf = arrayListD0.indexOf(wt2.I(strK4, arrayListD0));
                                                if (iIndexOf >= 0) {
                                                    if ((zl0VarA.e & 2) != 0) {
                                                        z2 = true;
                                                    } else {
                                                        z2 = false;
                                                    }
                                                    if (!z) {
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        if (tp2Var != null) {
                            w(tp2Var, i);
                            return true;
                        }
                    }
                }
            } else if (!vg0Var.A0().I.contains(3)) {
                int iF4 = f4(1);
                z50 z50Var = this.p;
                if (z50Var == null || iF4 < 0 || !z50Var.f.G0.get(iF4)) {
                    nq2VarE = k6.E();
                    if (!p1()) {
                        arrayListD0 = wt2.d0(this.H.X);
                        if (!arrayListD0.isEmpty()) {
                            int size2 = arrayListD0.size();
                            nw0VarN = nq2VarE.a.listIterator(0);
                            tp2Var = null;
                            i = 0;
                            z = true;
                            while (nw0VarN.hasNext()) {
                                mq2Var = (mq2) nw0VarN.next();
                                if (mq2Var.b.c != 3) {
                                    for (i2 = 0; i2 < mq2Var.a; i2++) {
                                        zl0VarA = mq2Var.a(i2);
                                        if (mq2Var.c(i2, false)) {
                                            zH1 = h1(zl0VarA);
                                            strK4 = zl0VarA.b;
                                            if (!zH1) {
                                                str = zl0VarA.d;
                                                vp2 vp2Var2 = vp2.f;
                                                if (ha1.D(str) == null) {
                                                    if (strK4 != null || !strK4.matches("[a-z]{3}\\d{1,2}")) {
                                                        strK4 = k4(zl0VarA);
                                                    }
                                                    iIndexOf = arrayListD0.indexOf(wt2.I(strK4, arrayListD0));
                                                    if (iIndexOf >= 0) {
                                                        if ((zl0VarA.e & 2) != 0) {
                                                            z2 = true;
                                                        } else {
                                                            z2 = false;
                                                        }
                                                        if ((!z && !z2) || (z == z2 && iIndexOf < size2)) {
                                                            tp2Var = mq2Var.b;
                                                            i = i2;
                                                            z = z2;
                                                            size2 = iIndexOf;
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            if (tp2Var != null) {
                                w(tp2Var, i);
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public final int R4(int i) {
        u81 u81Var;
        vs1 vs1Var = this.l3;
        if (vs1Var == null || i < 0 || i >= vs1Var.f.size()) {
            return -1;
        }
        ArrayList arrayList = this.C3;
        if (i < arrayList.size() && (u81Var = ((z81) arrayList.get(i)).b) != null) {
            return ((rs1) this.l3.f.get(i)).a(u81Var.a.toString());
        }
        return -1;
    }

    /* JADX WARN: Code duplicated, block: B:28:0x0054  */
    public final void S(boolean z, boolean z2) {
        po2 po2Var;
        JSONObject jSONObjectV2;
        JSONObject jSONObjectPut;
        if (this.Y4 || (po2Var = this.X4) == null || !po2Var.j()) {
            return;
        }
        JSONObject jSONObject = this.X4.G;
        JSONObject jSONObjectPut2 = null;
        if ((jSONObject == null ? null : jSONObject.optString("uri", null)) == null) {
            return;
        }
        Uri uriF0 = f0();
        if (uriF0 != null) {
            JSONObject jSONObject2 = this.X4.G;
            if ((jSONObject2 == null ? null : jSONObject2.optString("uri", null)).equals(uriF0.toString())) {
                return;
            }
        }
        if (!z) {
            jSONObjectV2 = null;
        } else if (po2.S.equals(this.X4.H)) {
            jSONObjectV2 = V2();
        } else {
            jSONObjectV2 = null;
        }
        if (jSONObjectV2 == null) {
            if (!z || !z2) {
                this.X4.k();
                return;
            }
            po2 po2Var2 = this.X4;
            JSONObject jSONObjectV3 = V2();
            if (jSONObjectV3 != null) {
                po2Var2.G = jSONObjectV3;
            }
            po2Var2.o(false);
            return;
        }
        po2 po2Var3 = this.X4;
        String str = po2.S;
        if (po2Var3.C == null || !str.equals(po2Var3.H)) {
            return;
        }
        po2Var3.G = jSONObjectV2;
        nz1 nz1Var = po2Var3.C;
        String strOptString = jSONObjectV2.optString("uri", null);
        JSONObject jSONObjectZ2 = po2Var3.l.l.z2();
        String str2 = "";
        String strOptString2 = jSONObjectZ2 == null ? "" : jSONObjectZ2.optString("title", "");
        JSONObject jSONObjectC = ha1.c("url", str);
        if (jSONObjectC == null) {
            jSONObjectPut = null;
        } else {
            try {
                JSONObject jSONObjectPut3 = jSONObjectC.put("url", strOptString);
                if (strOptString2 != null) {
                    str2 = strOptString2;
                }
                jSONObjectPut = jSONObjectPut3.put("ti", str2);
            } catch (Exception unused) {
                jSONObjectPut = null;
            }
        }
        nz1Var.d(jSONObjectPut);
        nz1 nz1Var2 = po2Var3.C;
        JSONObject jSONObjectC2 = ha1.c("jsess", str);
        if (jSONObjectC2 != null) {
            try {
                jSONObjectPut2 = jSONObjectC2.put("ses", jSONObjectV2);
            } catch (Exception unused2) {
            }
        }
        nz1Var2.d(jSONObjectPut2);
        po2Var3.o(true);
    }

    public final void S3(ImageButton imageButton, int i, boolean z) {
        qs2 qs2Var = this.W1;
        int iB = qs2Var.z() ? 0 : qs2Var.b(6.0f);
        int i2 = (i - this.W1.i()) / 2;
        imageButton.setScaleType(ImageView.ScaleType.FIT_CENTER);
        imageButton.setImageTintList(this.H0.p);
        imageButton.setBackground(new InsetDrawable((Drawable) wt2.c0(this.H0.c, 10000.0f), iB));
        imageButton.setPadding(i2, i2, i2, i2);
        imageButton.setForeground(wt2.j(this, iB, iB, this.H0.j));
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(i, i);
        layoutParams.gravity = 16;
        if (z) {
            qs2 qs2Var2 = this.W1;
            layoutParams.setMarginStart(qs2Var2.z() ? qs2Var2.a(8.0f) : 0);
        }
        imageButton.setLayoutParams(layoutParams);
    }

    public final int S4(rs1 rs1Var, String str) {
        int i = 0;
        while (true) {
            List list = rs1Var.t;
            if (i >= list.size()) {
                int i2 = -1;
                for (int i3 = 0; i3 < list.size(); i3++) {
                    String str2 = ((us1) list.get(i3)).a;
                    if (j4().h(str2, str)) {
                        if (str2.matches("(?s).*(?<!\\d)18\\s*\\+.*") == (str != null && str.matches("(?s).*(?<!\\d)18\\s*\\+.*"))) {
                            return i3;
                        }
                        if (i2 == -1) {
                            i2 = i3;
                        }
                    }
                }
                return i2;
            }
            if (((us1) list.get(i)).a.equals(str)) {
                return i;
            }
            i++;
        }
    }

    public final void T(ho2 ho2Var, ArrayList arrayList, int i, Runnable runnable) {
        ArrayList arrayList2 = new ArrayList();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            go2 go2Var = (go2) it.next();
            if (go2Var.a == i) {
                String string = getString(R.string.subtitle_search_episode, Integer.valueOf(go2Var.b));
                String str = go2Var.d;
                String str2 = go2Var.c;
                String str3 = str2 != null ? str2 : string;
                if (str2 == null) {
                    string = null;
                }
                arrayList2.add(new u70(0, str, str3, string, false, new bd1(this, ho2Var, i, go2Var, arrayList)));
            }
        }
        if (arrayList2.isEmpty()) {
            l(ho2Var, i, -1, arrayList);
        } else {
            arrayList2.add(0, new u70(R.drawable.ic_keyboard_24dp, null, getString(R.string.subtitle_search_type), null, false, new ok(this, ho2Var, arrayList, new bd1(this, ho2Var, arrayList, i, runnable), (byte) 10)));
            s2.p(this, this.W1, new wp1(this, (byte) 11), ho2Var.c, arrayList2, 72, 41, runnable);
        }
    }

    public final int T2(ArrayList arrayList) {
        String str;
        if (this.f4 != 3) {
            for (int i = 0; i < arrayList.size(); i++) {
                jr1 jr1Var = (jr1) arrayList.get(i);
                byte b = jr1Var.d;
                if ((b == 2 && this.f4 == 2 && jr1Var.e == this.g4 && jr1Var.f == this.h4) || ((b == 0 || b == 1) && b == this.f4)) {
                    return i;
                }
            }
        }
        Uri uriF0 = f0();
        if (uriF0 != null) {
            for (int i2 = 0; i2 < arrayList.size(); i2++) {
                jr1 jr1Var2 = (jr1) arrayList.get(i2);
                if (jr1Var2.d == 3 && (str = jr1Var2.h) != null && str.equals(uriF0.toString())) {
                    return i2;
                }
            }
        }
        return 0;
    }

    public final void U(Uri uri) {
        cz czVar;
        this.C4 = this.H.c;
        Z2(uri);
        if (uri == null || !M2() || (czVar = this.B) == null) {
            return;
        }
        s2.D(czVar, getString(R.string.subtitle_secondary_peek_hint), R.drawable.ic_subtitle_secondary_24dp, 3000L);
    }

    public final void U2() {
        Intent intent = getIntent();
        List list = vs1.k;
        Bundle extras = intent == null ? null : intent.getExtras();
        Bundle bundle = extras == null ? null : extras.getBundle("playlist");
        Object obj = bundle == null ? null : bundle.get("result_callback");
        PendingIntent pendingIntent = obj instanceof PendingIntent ? (PendingIntent) obj : null;
        if (pendingIntent == null || this.m3 == null) {
            return;
        }
        Bundle bundleC = c();
        String strD = c7.d(bundleC);
        if (strD.equals(this.o3)) {
            return;
        }
        this.o3 = strD;
        try {
            pendingIntent.send(this, 0, new Intent().putExtras(bundleC));
        } catch (PendingIntent.CanceledException unused) {
            wt2.K("result callback cancelled");
        }
    }

    public final void V() {
        X0();
        this.e2 = -3001L;
        gu1 gu1Var = this.H;
        if (gu1Var != null) {
            wt2.b0(this, gu1Var.U0);
        }
        A4();
    }

    public final void V0() {
        if (this.g6 == 4) {
            return;
        }
        W0();
    }

    public final void V1() {
        ArrayList arrayList;
        ge2 ge2Var = this.d5;
        if (ge2Var == null) {
            return;
        }
        ge2Var.d = this.k4;
        double dC0 = c0();
        je2 je2Var = ge2Var.a;
        List<ie2> listA = je2Var != null ? je2Var.a(dC0) : Collections.EMPTY_LIST;
        double d = 0.0d;
        if (ge2Var.d != 0.0d) {
            boolean z = dC0 > 0.0d && !Double.isNaN(dC0);
            ArrayList arrayList2 = new ArrayList(listA.size());
            for (ie2 ie2Var : listA) {
                double d2 = ie2Var.a;
                double d3 = d;
                double d4 = ge2Var.d;
                double d5 = d2 + d4;
                double d6 = ie2Var.b + d4;
                double d7 = d5 < d3 ? d3 : d5;
                double d8 = (!z || d6 <= dC0) ? d6 : dC0;
                if (d8 > d7) {
                    ie2 ie2Var2 = new ie2(d7, d8, ie2Var.c, ie2Var.d, ie2Var.e, ie2Var.f);
                    ie2Var2.h = ie2Var.h;
                    arrayList2.add(ie2Var2);
                }
                d = d3;
            }
            listA = arrayList2;
        }
        double d9 = d;
        if (!ge2Var.b.isEmpty()) {
            Iterator it = ge2Var.b.iterator();
            boolean z2 = false;
            while (it.hasNext()) {
                z2 |= ((ie2) it.next()).d == he2.n;
            }
            boolean z3 = dC0 > d9;
            ArrayList arrayList3 = new ArrayList(listA.size());
            for (ie2 ie2Var3 : listA) {
                boolean z4 = false;
                for (ie2 ie2Var4 : ge2Var.b) {
                    boolean z5 = z3;
                    z4 |= ie2Var3.a < ie2Var4.b && ie2Var4.a < ie2Var3.b;
                    z3 = z5;
                }
                boolean z7 = z3;
                boolean z8 = z7 && ie2Var3.b >= dC0 * 0.75d;
                if (ie2Var3.c != 1 || (!z4 && (!z2 || !z8))) {
                    arrayList3.add(ie2Var3);
                }
                z3 = z7;
            }
            listA = arrayList3;
        }
        ArrayList<ie2> arrayList4 = new ArrayList(listA);
        for (ie2 ie2Var5 : ge2Var.b) {
            arrayList4.add(new ie2(ie2Var5.a, ie2Var5.b, ie2Var5.c, ie2Var5.d, ie2Var5.e, ie2Var5.f));
        }
        if (dC0 <= d9) {
            arrayList = new ArrayList(arrayList4.size());
            for (ie2 ie2Var6 : arrayList4) {
                double d10 = ie2Var6.a;
                double d11 = ie2Var6.b;
                if (!Double.isNaN(d10) && !Double.isInfinite(ie2Var6.a) && !Double.isNaN(d11) && d11 < 86400.0d) {
                    arrayList.add(ie2Var6);
                }
            }
        } else {
            double d12 = 0.3333333333333333d * dC0;
            ArrayList arrayList5 = new ArrayList(arrayList4.size());
            for (ie2 ie2Var7 : arrayList4) {
                double dMin = Math.min(ie2Var7.b, dC0);
                double d13 = ie2Var7.a;
                if (dMin > d13 && dMin - d13 <= d12) {
                    if (dMin == ie2Var7.b) {
                        arrayList5.add(ie2Var7);
                    } else {
                        ie2 ie2Var8 = new ie2(d13, dMin, ie2Var7.c, ie2Var7.d, ie2Var7.e, ie2Var7.f);
                        ie2Var8.h = ie2Var7.h;
                        arrayList5.add(ie2Var8);
                    }
                }
            }
            arrayList = arrayList5;
        }
        ge2Var.c = arrayList;
        boolean z9 = dC0 > d9 && !Double.isNaN(dC0);
        for (ie2 ie2Var9 : ge2Var.c) {
            ie2Var9.i = z9 && ie2Var9.c == 1 && ie2Var9.b >= dC0 * 0.75d;
            ie2Var9.j = z9 && ie2Var9.b >= dC0 - 1.5d;
        }
        if (this.y2 != null) {
            ge2 ge2Var2 = this.d5;
            List list = ge2Var2 != null ? ge2Var2.c : null;
            vg0 vg0Var = k6;
            long duration = vg0Var != null ? vg0Var.getDuration() : -9223372036854775807L;
            if (list == null || list.isEmpty() || duration == -9223372036854775807L || duration <= 0 || !this.H.n0) {
                this.y2.h();
            } else {
                int size = list.size();
                long[] jArr = new long[size];
                long[] jArr2 = new long[size];
                int[] iArr = new int[size];
                int iN = sj.n(this, R.attr.accentSkip, getColor(R.color.skip_fill));
                int color = getColor(R.color.ad_fill);
                for (int i = 0; i < size; i++) {
                    ie2 ie2Var10 = (ie2) list.get(i);
                    boolean z10 = ie2Var10.c == 2;
                    jArr[i] = Math.round(ie2Var10.a * 1000.0d);
                    jArr2[i] = ie2Var10.a();
                    iArr[i] = z10 ? color : iN;
                }
                CustomDefaultTimeBar customDefaultTimeBar = this.y2;
                customDefaultTimeBar.v0 = jArr;
                customDefaultTimeBar.w0 = jArr2;
                customDefaultTimeBar.x0 = iArr;
                customDefaultTimeBar.y0 = duration;
                customDefaultTimeBar.invalidate();
            }
        }
        if (this.d5.c.isEmpty()) {
            return;
        }
        this.p4 = true;
    }

    public final JSONObject V2() {
        vg0 vg0Var;
        Uri uriF0 = f0();
        if (uriF0 == null || !wt2.E(uriF0)) {
            return null;
        }
        try {
            JSONObject jSONObjectPut = new JSONObject().put("uri", uriF0.toString());
            Intent intent = getIntent();
            if (intent != null) {
                if (intent.getType() != null) {
                    jSONObjectPut.put("type", intent.getType());
                }
                Bundle extras = intent.getExtras();
                if (extras != null && vs1.b(intent)) {
                    vg0 vg0Var2 = k6;
                    int iV = vg0Var2 == null ? -1 : vg0Var2.V();
                    Bundle bundle = new Bundle(extras);
                    Bundle bundle2 = new Bundle(extras.getBundle("playlist"));
                    bundle2.remove("result_callback");
                    if (iV >= 0) {
                        bundle2.putInt("start_index", iV);
                    }
                    bundle.putBundle("playlist", bundle2);
                    jSONObjectPut.put("extras", dn1.Q(0, bundle));
                    return jSONObjectPut;
                }
                if (extras != null) {
                    Bundle bundle3 = new Bundle(extras);
                    bundle3.remove("return_result");
                    if (bundle3.containsKey("position") && (vg0Var = k6) != null && vg0Var.x()) {
                        bundle3.putInt("position", (int) Math.max(0L, k6.O0()));
                    }
                    jSONObjectPut.put("extras", dn1.Q(0, bundle3));
                }
            }
            return jSONObjectPut;
        } catch (Exception unused) {
            return null;
        }
    }

    public final ArrayList V3(List list) {
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            ts1 ts1Var = (ts1) it.next();
            PlayerActivity playerActivity = this;
            arrayList.add(ij0.b(playerActivity, ts1Var.a, ts1Var.d, ts1Var.e, ts1Var.b, ts1Var.c));
            this = playerActivity;
        }
        return arrayList;
    }

    public final void W() {
        this.L4 = null;
        this.J4 = null;
        this.K4 = null;
        kk2 kk2Var = this.H4;
        if (kk2Var != null) {
            kk2Var.g(null);
        }
    }

    public final void W0() {
        cz czVar;
        ValueAnimator valueAnimator = this.l5;
        if (valueAnimator != null) {
            valueAnimator.cancel();
            this.l5 = null;
        }
        this.k5 = 1.0f;
        this.j5 = null;
        this.h6 = 0;
        this.g6 = 1;
        this.F5 = -9223372036854775807L;
        this.u5 = null;
        cz czVar2 = this.B;
        if (czVar2 != null) {
            czVar2.removeCallbacks(this.t5);
        }
        Button button = this.f5;
        if (button != null) {
            if (Q6 && button.hasFocus() && (czVar = this.B) != null) {
                czVar.requestFocus();
            }
            this.f5.animate().cancel();
            this.f5.animate().alpha(0.0f).setDuration(250L).setInterpolator(O6).withEndAction(new wp1(this, (byte) 2)).start();
            J4();
        }
    }

    public final boolean W1(eg0 eg0Var, zl0 zl0Var) {
        if (k6 == null || p6 || zl0Var == null || !"video/dolby-vision".equals(zl0Var.p) || !"video/hevc".equals(s71.c(zl0Var, true))) {
            return false;
        }
        if (!r6) {
            r6 = true;
            w3.b().p(eg0Var, new nk((Object) this, (Object) eg0Var, (Object) zl0Var, (byte) 8));
        }
        wt2.K("rebuild: Dolby Vision " + zl0Var.l + " as HEVC");
        p6 = true;
        q6 = true;
        boolean zW = k6.w();
        this.A2 = zW;
        this.M4 = !zW;
        this.B.post(new dp1(this, (byte) 16));
        return true;
    }

    public final void W2(boolean z) {
        this.r2 = z;
        r4();
    }

    public final String W3(Uri uri) {
        String strM1 = m1(ot2.Z(ij0.o(uri)));
        if (strM1 == null) {
            strM1 = wt2.w(this, uri);
        }
        String path = uri.getPath();
        return (path == null || !path.contains(".auto.")) ? strM1 : getString(R.string.subtitle_machine_translated, strM1);
    }

    public final void X0() {
        if (this.d2 == null) {
            return;
        }
        cz czVar = this.B;
        if (czVar != null) {
            czVar.removeCallbacks(this.T5);
        }
        this.d2.setVisibility(8);
    }

    public final boolean X1() {
        LinkedHashMap linkedHashMapH0;
        byte b = 0;
        if (k6 != null && !this.V && (linkedHashMapH0 = h0()) != null && !linkedHashMapH0.isEmpty()) {
            Uri uriF0 = f0();
            String str = null;
            int i = 0;
            String str2 = null;
            for (Map.Entry entry : linkedHashMapH0.entrySet()) {
                int iS1 = S1((String) entry.getKey());
                String str3 = (String) entry.getValue();
                if (iS1 > 0 && iS1 <= 1080 && iS1 > i && str3 != null && !str3.trim().isEmpty() && !Uri.parse(str3).equals(uriF0)) {
                    str2 = (String) entry.getKey();
                    i = iS1;
                    str = str3;
                }
            }
            if (str != null) {
                this.V = true;
                wt2.K("quality lowered to " + str2);
                this.B.post(new rp1(this, str2, str, b));
                return true;
            }
        }
        return false;
    }

    public final void X3(int i) {
        cz czVar = this.B;
        if (czVar == null) {
            return;
        }
        if (i != 0) {
            s2.D(czVar, getString(i), R.drawable.ic_subtitles_24dp, 90000L);
        } else {
            czVar.removeCallbacks(czVar.C0);
            this.B.C0.run();
        }
    }

    public final TextView Y(int i) {
        TextView textView = new TextView(this);
        textView.setTextColor(this.J0.g);
        textView.setTextSize(2, this.W1.v());
        textView.setMaxLines(1);
        textView.setEllipsize(TextUtils.TruncateAt.END);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        layoutParams.topMargin = i;
        textView.setLayoutParams(layoutParams);
        textView.setVisibility(8);
        return textView;
    }

    public final void Y0(TextView textView, int i, int i2, int i3) {
        Drawable drawable = getDrawable(i);
        if (drawable == null) {
            return;
        }
        int iB = this.W1.b(Q6 ? 12.307693f : 14.0f);
        int iRound = Math.round((iB * i2) / 24.0f);
        drawable.setBounds(-iRound, 0, iB - iRound, iB);
        textView.setCompoundDrawablesRelative(drawable, null, null, null);
        textView.setCompoundDrawableTintList(textView.getTextColors());
        textView.setCompoundDrawablePadding((this.W1.b(8.0f) + Math.round(((i3 - i2) * iB) / 24.0f)) - iB);
    }

    public final boolean Y1(eg0 eg0Var) {
        if (!Q6 || s6 || !m6 || k6 == null) {
            return false;
        }
        s6 = true;
        if (p6) {
            q6 = true;
        }
        t6 = !k6.w();
        wt2.K(eg0Var != null ? "restarting the screen: the decoder will not come back on this one" : "restarting the screen: the retained decoder did not come back with the window");
        g2("screen-restarted", eg0Var);
        b2(true);
        this.B.post(new dp1(this, (byte) 27));
        return true;
    }

    public final void Y2(double d) {
        float fMax = (float) Math.max(0.0d, Math.min(1.0d, d));
        cz czVar = this.B;
        if (czVar != null) {
            czVar.removeCallbacks(this.O5);
        }
        ValueAnimator valueAnimator = this.l5;
        if (valueAnimator != null) {
            valueAnimator.cancel();
            this.l5 = null;
        }
        float f = this.k5;
        if (fMax >= f) {
            o(fMax);
            return;
        }
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(f, fMax);
        this.l5 = valueAnimatorOfFloat;
        valueAnimatorOfFloat.setDuration(250L);
        this.l5.setInterpolator(new LinearInterpolator());
        this.l5.addUpdateListener(new ah(this, (byte) 9));
        this.l5.start();
    }

    public final uk2 Y3(hl hlVar, List list, String str, int i, int i2, Uri uri, final long j, final dg1 dg1Var, final AtomicBoolean atomicBoolean, boolean z, boolean z2) {
        final hl hlVar2 = hlVar;
        String str2 = (String) list.get(0);
        List<String> listM4 = m4(str2);
        String str3 = listM4.isEmpty() ? null : str2;
        final ArrayList arrayList = new ArrayList(list);
        for (String str4 : listM4) {
            if (!arrayList.contains(str4)) {
                arrayList.add(str4);
            }
        }
        gu1 gu1Var = this.H;
        tp1 tp1Var = new tp1(this, i, str, j, str3, listM4, z2, i2, uri, z);
        fl1 fl1Var = vk2.a;
        String str5 = (String) hlVar2.o;
        String str6 = (String) hlVar2.n;
        int i3 = hlVar2.m;
        if (hlVar2.m() || arrayList.isEmpty()) {
            StringBuilder sb = new StringBuilder("subtitles: nothing to ask with (id=");
            sb.append(hlVar2.m() ? "empty" : "ok");
            sb.append(", want=");
            sb.append(arrayList);
            sb.append(")");
            wt2.K(sb.toString());
            return null;
        }
        byte b = 1;
        if (!hlVar2.n() && i3 < 1) {
            wt2.K("subtitles: no episode number, not searching");
            return null;
        }
        if (str6 == null || str5 == null) {
            gu1Var.getClass();
            int i4 = hlVar2.l;
            try {
                if (str6 == null && str5 != null) {
                    String strN = ha2.N(Long.parseLong(str5), hlVar2.n());
                    if (strN != null) {
                        hlVar2 = new hl(i4, i3, strN, str5);
                    }
                } else if (str5 == null && str6 != null) {
                    long jO = ha2.O(str6.startsWith("tt") ? str6 : "tt" + str6, hlVar2.n());
                    if (jO >= 0) {
                        hlVar2 = new hl(i4, i3, str6, String.valueOf(jO));
                    }
                }
            } catch (Exception e) {
                wt2.K("subtitles: id lookup " + e);
            }
        }
        String str7 = (String) hlVar2.o;
        String str8 = (String) hlVar2.n;
        if (str8 != null && str7 != null) {
            ConcurrentHashMap concurrentHashMap = vk2.c;
            concurrentHashMap.put(str8, str7);
            concurrentHashMap.put(str7, str8);
        }
        if (vk2.b()) {
            return null;
        }
        wt2.K("subtitles: " + str8 + " / " + str7 + " s" + hlVar2.l + "e" + hlVar2.m + " want=" + arrayList + " media=" + j + "ms");
        final boolean z3 = gu1Var.c0;
        ArrayList arrayList2 = new ArrayList(4);
        arrayList2.add(new Callable() { // from class: qk2
            /* JADX WARN: Code duplicated, block: B:84:0x01fc  */
            /* JADX WARN: Code duplicated, block: B:85:0x01fe  */
            @Override // java.util.concurrent.Callable
            public final Object call() {
                List list2;
                boolean z4;
                boolean z5;
                ArrayList arrayList3 = arrayList;
                ArrayList arrayListB = xl1.b(arrayList3);
                if (arrayListB.isEmpty()) {
                    wt2.K("subtitles: openSubtitles has no 639-1 code for " + arrayList3);
                    return null;
                }
                hl hlVar3 = hlVar2;
                boolean zM = hlVar3.m();
                dg1 dg1Var2 = dg1Var;
                if ((zM && dg1Var2 == null) || arrayListB.isEmpty()) {
                    list2 = Collections.EMPTY_LIST;
                } else {
                    TreeMap treeMap = new TreeMap();
                    treeMap.put("languages", TextUtils.join(",", arrayListB));
                    if (!zM) {
                        if (hlVar3.l() != null) {
                            treeMap.put("imdb_id", hlVar3.l());
                        } else {
                            treeMap.put("tmdb_id", (String) hlVar3.o);
                        }
                        if (!hlVar3.n()) {
                            treeMap.put("season_number", String.valueOf(hlVar3.l));
                            int i5 = hlVar3.m;
                            if (i5 > 0) {
                                treeMap.put("episode_number", String.valueOf(i5));
                            }
                        }
                    }
                    if (dg1Var2 != null) {
                        treeMap.put("moviehash", dg1Var2.a);
                        treeMap.put("moviebytesize", String.valueOf(dg1Var2.b));
                    }
                    StringBuilder sb2 = new StringBuilder("https://api.opensubtitles.com/api/v1/subtitles?");
                    boolean z7 = true;
                    for (Map.Entry entry : treeMap.entrySet()) {
                        if (!z7) {
                            sb2.append('&');
                        }
                        sb2.append((String) entry.getKey());
                        sb2.append('=');
                        sb2.append(Uri.encode((String) entry.getValue(), ","));
                        z7 = false;
                    }
                    String string = sb2.toString();
                    vk1 vk1Var = new vk1();
                    vk1Var.c(string);
                    vk1Var.a("Api-Key", "IxrxupVBKx7dhBkAAtW7QbwnhDMgOdEO");
                    vk1Var.a("User-Agent", "JustPlayer v2.1.2");
                    vk1Var.a("Accept", "application/json");
                    String strA = xl1.a(new gv1(vk1Var));
                    if (strA == null) {
                        list2 = Collections.EMPTY_LIST;
                    } else {
                        ArrayList arrayList4 = new ArrayList();
                        try {
                            JSONArray jSONArrayOptJSONArray = new JSONObject(strA).optJSONArray("data");
                            if (jSONArrayOptJSONArray == null) {
                                list2 = Collections.EMPTY_LIST;
                            } else {
                                for (int i7 = 0; i7 < jSONArrayOptJSONArray.length(); i7++) {
                                    JSONObject jSONObjectOptJSONObject = jSONArrayOptJSONArray.getJSONObject(i7).optJSONObject("attributes");
                                    if (jSONObjectOptJSONObject != null && !jSONObjectOptJSONObject.optBoolean("foreign_parts_only")) {
                                        boolean z8 = jSONObjectOptJSONObject.optBoolean("machine_translated") || jSONObjectOptJSONObject.optBoolean("ai_translated");
                                        JSONArray jSONArrayOptJSONArray2 = jSONObjectOptJSONObject.optJSONArray("files");
                                        if (jSONArrayOptJSONArray2 != null && jSONArrayOptJSONArray2.length() != 0) {
                                            long jOptLong = jSONArrayOptJSONArray2.getJSONObject(0).optLong("file_id", -1L);
                                            if (jOptLong >= 0) {
                                                int iOptInt = jSONObjectOptJSONObject.optInt("new_download_count", jSONObjectOptJSONObject.optInt("download_count"));
                                                String strOptString = jSONObjectOptJSONObject.optString("language");
                                                jSONObjectOptJSONObject.optString("release");
                                                arrayList4.add(new wl1(strOptString, jOptLong, iOptInt, z8, jSONObjectOptJSONObject.optBoolean("moviehash_match")));
                                            }
                                        }
                                    }
                                }
                                list2 = arrayList4;
                            }
                        } catch (Exception e2) {
                            wt2.K("OpenSubtitles: " + e2);
                            list2 = Collections.EMPTY_LIST;
                        }
                    }
                }
                Iterator it = list2.iterator();
                wl1 wl1Var = null;
                while (true) {
                    boolean zHasNext = it.hasNext();
                    z4 = z3;
                    if (!zHasNext) {
                        break;
                    }
                    wl1 wl1Var2 = (wl1) it.next();
                    String str9 = wl1Var2.a;
                    boolean z9 = wl1Var2.d;
                    if (str9 != null && (!z9 || z4)) {
                        Locale locale = Locale.US;
                        if (arrayListB.indexOf(str9.toLowerCase(locale)) >= 0) {
                            if (wl1Var != null) {
                                int iIndexOf = arrayListB.indexOf(wl1Var2.a.toLowerCase(locale));
                                int iIndexOf2 = arrayListB.indexOf(wl1Var.a.toLowerCase(locale));
                                if (iIndexOf == iIndexOf2) {
                                    z5 = wl1Var2.e;
                                    if (z5 == wl1Var.e && z9 == (z5 = wl1Var.d)) {
                                        if (wl1Var2.c > wl1Var.c) {
                                            z5 = true;
                                        } else {
                                            z5 = false;
                                        }
                                    }
                                } else if (iIndexOf < iIndexOf2) {
                                    z5 = true;
                                } else {
                                    z5 = false;
                                }
                                if (z5) {
                                }
                            }
                            wl1Var = wl1Var2;
                        }
                    }
                }
                if (wl1Var != null) {
                    String str10 = wl1Var.a;
                    if (!vk2.b()) {
                        StringBuilder sb3 = new StringBuilder("subtitles: openSubtitles has 1 ");
                        sb3.append(str10);
                        sb3.append(wl1Var.d ? " machine-translated" : "");
                        sb3.append(wl1Var.e ? " matching this file" : "");
                        sb3.append(" of ");
                        sb3.append(list2.size());
                        wt2.K(sb3.toString());
                        vp2 vp2Var = vp2.f;
                        return new uk2("openSubtitles", ha1.D(str10), Collections.EMPTY_LIST, wl1Var.d, wl1Var.b);
                    }
                }
                StringBuilder sb4 = new StringBuilder("subtitles: openSubtitles has ");
                sb4.append(list2.size());
                sb4.append(" file(s), none taken");
                sb4.append(z4 ? "" : " (machine translations refused)");
                wt2.K(sb4.toString());
                return null;
            }
        });
        final hl hlVar3 = hlVar2;
        arrayList2.add(new Callable() { // from class: rk2
            @Override // java.util.concurrent.Callable
            public final Object call() {
                List list2;
                boolean zEquals;
                int i5;
                AtomicBoolean atomicBoolean2 = atomicBoolean;
                hl hlVar4 = hlVar3;
                String str9 = (String) hlVar4.n;
                int i7 = hlVar4.m;
                ArrayList<String> arrayList3 = arrayList;
                if (str9 == null) {
                    wt2.K("subtitles: restOpenSubtitles needs an imdb id, and there is none");
                    list2 = Collections.EMPTY_LIST;
                } else {
                    String strL = hlVar4.l();
                    for (String str10 : arrayList3) {
                        if (vk2.b()) {
                            break;
                        }
                        StringBuilder sb2 = new StringBuilder("https://rest.opensubtitles.org/search/");
                        if (!hlVar4.n() && i7 > 0) {
                            sb2.append("episode-");
                            sb2.append(i7);
                            sb2.append('/');
                        }
                        sb2.append("imdbid-");
                        sb2.append(strL);
                        sb2.append('/');
                        if (!hlVar4.n()) {
                            sb2.append("season-");
                            sb2.append(hlVar4.l);
                            sb2.append('/');
                        }
                        sb2.append("sublanguageid-");
                        String str11 = (String) vk2.d.get(str10);
                        if (str11 != null) {
                            str10 = str11;
                        }
                        sb2.append(str10);
                        ArrayList arrayList4 = new ArrayList();
                        try {
                            String strD = vk2.d(sb2.toString(), atomicBoolean2);
                            if (strD != null) {
                                JSONArray jSONArray = new JSONArray(strD);
                                for (int i8 = 0; i8 < jSONArray.length(); i8++) {
                                    JSONObject jSONObject = jSONArray.getJSONObject(i8);
                                    String strOptString = jSONObject.optString("SubDownloadLink", null);
                                    String strOptString2 = jSONObject.optString("SubLanguageID");
                                    String[] strArr = wt2.a;
                                    vp2 vp2Var = vp2.f;
                                    String strD2 = ha1.D(strOptString2);
                                    if (strOptString != null && strD2 != null && !"1".equals(jSONObject.optString("SubForeignPartsOnly"))) {
                                        String strOptString3 = jSONObject.optString("SubFileName");
                                        if (!(strOptString3 != null && vk2.e.matcher(strOptString3).find()) && (!(zEquals = "1".equals(jSONObject.optString("SubAutoTranslation"))) || z3)) {
                                            try {
                                                i5 = Integer.parseInt(jSONObject.optString("SubDownloadsCnt").trim());
                                            } catch (Exception unused) {
                                                i5 = 0;
                                            }
                                            String strOptString4 = jSONObject.optString("SubLastTS");
                                            long j2 = 0;
                                            if (strOptString4 != null) {
                                                Matcher matcher = vk2.b.matcher(strOptString4);
                                                if (matcher.matches()) {
                                                    j2 = 1000 * (Long.parseLong(matcher.group(3)) + (Long.parseLong(matcher.group(2)) * 60) + (Long.parseLong(matcher.group(1)) * 3600));
                                                }
                                            }
                                            arrayList4.add(new tk2(strD2, i5, strOptString, zEquals, j2));
                                        }
                                    }
                                }
                                if (!arrayList4.isEmpty()) {
                                    list2 = arrayList4;
                                }
                            }
                        } catch (Exception e2) {
                            wt2.K("rest.opensubtitles.org: " + e2);
                        }
                    }
                    list2 = Collections.EMPTY_LIST;
                }
                return vk2.a(j, "restOpenSubtitles", arrayList3, list2);
            }
        });
        final byte b2 = 0;
        arrayList2.add(new Callable() { // from class: sk2
            /* JADX WARN: Code duplicated, block: B:45:0x00ea  */
            @Override // java.util.concurrent.Callable
            public final Object call() {
                String string;
                List list2;
                List list3;
                String str9;
                int i5;
                byte b3 = b2;
                long j2 = j;
                ArrayList arrayList3 = arrayList;
                AtomicBoolean atomicBoolean2 = atomicBoolean;
                hl hlVar4 = hlVar3;
                switch (b3) {
                    case DecoderInputBuffer.BUFFER_REPLACEMENT_MODE_DISABLED /* 0 */:
                        String strE = (String) hlVar4.n;
                        if (strE == null) {
                            wt2.K("subtitles: stremio needs an imdb id, and there is none");
                            list2 = Collections.EMPTY_LIST;
                        } else {
                            if (!strE.startsWith("tt")) {
                                strE = jf2.e("tt", strE);
                            }
                            if (hlVar4.n()) {
                                string = jf2.e("movie/", strE);
                            } else {
                                StringBuilder sbK = jf2.k("series/", strE, ":");
                                sbK.append(hlVar4.l);
                                sbK.append(":");
                                sbK.append(Math.max(hlVar4.m, 1));
                                string = sbK.toString();
                            }
                            ArrayList arrayList4 = new ArrayList();
                            try {
                                String strD = vk2.d("https://opensubtitles-v3.strem.io/subtitles/" + string + ".json", atomicBoolean2);
                                JSONArray jSONArrayOptJSONArray = (strD == null ? new JSONObject() : new JSONObject(strD)).optJSONArray("subtitles");
                                if (jSONArrayOptJSONArray != null) {
                                    for (int i7 = 0; i7 < jSONArrayOptJSONArray.length(); i7++) {
                                        JSONObject jSONObject = jSONArrayOptJSONArray.getJSONObject(i7);
                                        String strOptString = jSONObject.optString("lang");
                                        String[] strArr = wt2.a;
                                        vp2 vp2Var = vp2.f;
                                        String strD2 = ha1.D(strOptString);
                                        String strOptString2 = jSONObject.optString("url", null);
                                        if (strD2 != null && strOptString2 != null) {
                                            arrayList4.add(new tk2(strD2, 0, strOptString2, false, 0L));
                                        }
                                        break;
                                    }
                                }
                            } catch (Exception e2) {
                                wt2.K("stremio: " + e2);
                            }
                            list2 = arrayList4;
                        }
                        return vk2.a(j2, "stremio", arrayList3, list2);
                    default:
                        String str10 = (String) hlVar4.o;
                        int i8 = hlVar4.m;
                        if (str10 == null) {
                            wt2.K("subtitles: shegu needs a tmdb id, and there is none");
                            list3 = Collections.EMPTY_LIST;
                        } else {
                            StringBuilder sb2 = new StringBuilder("https://subtitles.shegu.st/subtitles?tmdb=");
                            sb2.append(Uri.encode(str10));
                            sb2.append("&type=");
                            sb2.append(hlVar4.n() ? "movie" : "tv");
                            if (!hlVar4.n()) {
                                sb2.append("&season=");
                                sb2.append(hlVar4.l);
                                if (i8 > 0) {
                                    sb2.append("&episode=");
                                    sb2.append(i8);
                                }
                            }
                            ArrayList arrayList5 = new ArrayList();
                            try {
                                String strD3 = vk2.d(sb2.toString(), atomicBoolean2);
                                JSONArray jSONArrayOptJSONArray2 = (strD3 == null ? new JSONObject() : new JSONObject(strD3)).optJSONArray("subtitles");
                                if (jSONArrayOptJSONArray2 != null) {
                                    for (int i9 = 0; i9 < jSONArrayOptJSONArray2.length(); i9++) {
                                        JSONObject jSONObject2 = jSONArrayOptJSONArray2.getJSONObject(i9);
                                        String strOptString3 = jSONObject2.optString("language");
                                        String[] strArr2 = wt2.a;
                                        vp2 vp2Var2 = vp2.f;
                                        String strD4 = ha1.D(strOptString3);
                                        String strOptString4 = jSONObject2.optString("url", null);
                                        if (strD4 != null && strOptString4 != null) {
                                            String strOptString5 = jSONObject2.optString("display");
                                            if (!(strOptString5 != null && vk2.e.matcher(strOptString5).find())) {
                                                int iLastIndexOf = strOptString4.lastIndexOf(47);
                                                if (iLastIndexOf < 0 || (i5 = iLastIndexOf + 1) >= strOptString4.length()) {
                                                    str9 = null;
                                                } else {
                                                    try {
                                                        str9 = new String(Base64.decode(strOptString4.substring(i5), 8), "UTF-8");
                                                        if (!str9.startsWith("https://")) {
                                                            str9 = null;
                                                        }
                                                    } catch (Exception unused) {
                                                    }
                                                }
                                                arrayList5.add(new tk2(strD4, 0, str9 != null ? str9 : strOptString4, false, 0L));
                                            }
                                        }
                                        break;
                                    }
                                }
                            } catch (Exception e3) {
                                wt2.K("shegu.st: " + e3);
                            }
                            list3 = arrayList5;
                        }
                        return vk2.a(j2, "shegu", arrayList3, list3);
                }
            }
        });
        final byte b3 = 1;
        arrayList2.add(new Callable() { // from class: sk2
            /* JADX WARN: Code duplicated, block: B:45:0x00ea  */
            @Override // java.util.concurrent.Callable
            public final Object call() {
                String string;
                List list2;
                List list3;
                String str9;
                int i5;
                byte b4 = b3;
                long j2 = j;
                ArrayList arrayList3 = arrayList;
                AtomicBoolean atomicBoolean2 = atomicBoolean;
                hl hlVar4 = hlVar3;
                switch (b4) {
                    case DecoderInputBuffer.BUFFER_REPLACEMENT_MODE_DISABLED /* 0 */:
                        String strE = (String) hlVar4.n;
                        if (strE == null) {
                            wt2.K("subtitles: stremio needs an imdb id, and there is none");
                            list2 = Collections.EMPTY_LIST;
                        } else {
                            if (!strE.startsWith("tt")) {
                                strE = jf2.e("tt", strE);
                            }
                            if (hlVar4.n()) {
                                string = jf2.e("movie/", strE);
                            } else {
                                StringBuilder sbK = jf2.k("series/", strE, ":");
                                sbK.append(hlVar4.l);
                                sbK.append(":");
                                sbK.append(Math.max(hlVar4.m, 1));
                                string = sbK.toString();
                            }
                            ArrayList arrayList4 = new ArrayList();
                            try {
                                String strD = vk2.d("https://opensubtitles-v3.strem.io/subtitles/" + string + ".json", atomicBoolean2);
                                JSONArray jSONArrayOptJSONArray = (strD == null ? new JSONObject() : new JSONObject(strD)).optJSONArray("subtitles");
                                if (jSONArrayOptJSONArray != null) {
                                    for (int i7 = 0; i7 < jSONArrayOptJSONArray.length(); i7++) {
                                        JSONObject jSONObject = jSONArrayOptJSONArray.getJSONObject(i7);
                                        String strOptString = jSONObject.optString("lang");
                                        String[] strArr = wt2.a;
                                        vp2 vp2Var = vp2.f;
                                        String strD2 = ha1.D(strOptString);
                                        String strOptString2 = jSONObject.optString("url", null);
                                        if (strD2 != null && strOptString2 != null) {
                                            arrayList4.add(new tk2(strD2, 0, strOptString2, false, 0L));
                                        }
                                        break;
                                    }
                                }
                            } catch (Exception e2) {
                                wt2.K("stremio: " + e2);
                            }
                            list2 = arrayList4;
                        }
                        return vk2.a(j2, "stremio", arrayList3, list2);
                    default:
                        String str10 = (String) hlVar4.o;
                        int i8 = hlVar4.m;
                        if (str10 == null) {
                            wt2.K("subtitles: shegu needs a tmdb id, and there is none");
                            list3 = Collections.EMPTY_LIST;
                        } else {
                            StringBuilder sb2 = new StringBuilder("https://subtitles.shegu.st/subtitles?tmdb=");
                            sb2.append(Uri.encode(str10));
                            sb2.append("&type=");
                            sb2.append(hlVar4.n() ? "movie" : "tv");
                            if (!hlVar4.n()) {
                                sb2.append("&season=");
                                sb2.append(hlVar4.l);
                                if (i8 > 0) {
                                    sb2.append("&episode=");
                                    sb2.append(i8);
                                }
                            }
                            ArrayList arrayList5 = new ArrayList();
                            try {
                                String strD3 = vk2.d(sb2.toString(), atomicBoolean2);
                                JSONArray jSONArrayOptJSONArray2 = (strD3 == null ? new JSONObject() : new JSONObject(strD3)).optJSONArray("subtitles");
                                if (jSONArrayOptJSONArray2 != null) {
                                    for (int i9 = 0; i9 < jSONArrayOptJSONArray2.length(); i9++) {
                                        JSONObject jSONObject2 = jSONArrayOptJSONArray2.getJSONObject(i9);
                                        String strOptString3 = jSONObject2.optString("language");
                                        String[] strArr2 = wt2.a;
                                        vp2 vp2Var2 = vp2.f;
                                        String strD4 = ha1.D(strOptString3);
                                        String strOptString4 = jSONObject2.optString("url", null);
                                        if (strD4 != null && strOptString4 != null) {
                                            String strOptString5 = jSONObject2.optString("display");
                                            if (!(strOptString5 != null && vk2.e.matcher(strOptString5).find())) {
                                                int iLastIndexOf = strOptString4.lastIndexOf(47);
                                                if (iLastIndexOf < 0 || (i5 = iLastIndexOf + 1) >= strOptString4.length()) {
                                                    str9 = null;
                                                } else {
                                                    try {
                                                        str9 = new String(Base64.decode(strOptString4.substring(i5), 8), "UTF-8");
                                                        if (!str9.startsWith("https://")) {
                                                            str9 = null;
                                                        }
                                                    } catch (Exception unused) {
                                                    }
                                                }
                                                arrayList5.add(new tk2(strD4, 0, str9 != null ? str9 : strOptString4, false, 0L));
                                            }
                                        }
                                        break;
                                    }
                                }
                            } catch (Exception e3) {
                                wt2.K("shegu.st: " + e3);
                            }
                            list3 = arrayList5;
                        }
                        return vk2.a(j2, "shegu", arrayList3, list3);
                }
            }
        });
        if (arrayList2.isEmpty()) {
            return null;
        }
        ExecutorService executorServiceNewFixedThreadPool = Executors.newFixedThreadPool(arrayList2.size(), new lz1((byte) 3));
        try {
            ArrayList<Future> arrayList3 = new ArrayList(arrayList2.size());
            Iterator it = arrayList2.iterator();
            while (it.hasNext()) {
                arrayList3.add(executorServiceNewFixedThreadPool.submit((Callable) it.next()));
            }
            long jCurrentTimeMillis = System.currentTimeMillis() + 15000;
            ArrayList<uk2> arrayList4 = new ArrayList(arrayList2.size());
            for (Future future : arrayList3) {
                if (!vk2.b()) {
                    try {
                        uk2 uk2Var = (uk2) future.get(Math.max(jCurrentTimeMillis - System.currentTimeMillis(), 0L), TimeUnit.MILLISECONDS);
                        if (uk2Var != null) {
                            if (!((String) arrayList.get(0)).equals(uk2Var.b)) {
                                arrayList4.add(uk2Var);
                            } else if (vk2.c(uk2Var, tp1Var)) {
                                executorServiceNewFixedThreadPool.shutdownNow();
                                return uk2Var;
                            }
                        }
                    } catch (InterruptedException unused) {
                        Thread.currentThread().interrupt();
                    } catch (Exception e2) {
                        wt2.K("subtitles: source failed " + e2);
                    }
                }
                executorServiceNewFixedThreadPool.shutdownNow();
                return null;
            }
            Collections.sort(arrayList4, new lp1(arrayList, b));
            for (uk2 uk2Var2 : arrayList4) {
                if (vk2.b()) {
                    break;
                }
                if (vk2.c(uk2Var2, tp1Var)) {
                    executorServiceNewFixedThreadPool.shutdownNow();
                    return uk2Var2;
                }
            }
            executorServiceNewFixedThreadPool.shutdownNow();
            return null;
        } catch (Throwable th) {
            executorServiceNewFixedThreadPool.shutdownNow();
            throw th;
        }
    }

    public final TextView Z() {
        TextView textView = new TextView(this);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
        layoutParams.gravity = 8388659;
        layoutParams.setMargins(wt2.p(6), wt2.p(6), 0, 0);
        textView.setLayoutParams(layoutParams);
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(getColor(R.color.badge_scrim));
        gradientDrawable.setCornerRadius(wt2.p(6));
        textView.setBackground(gradientDrawable);
        textView.setGravity(17);
        textView.setMinWidth(wt2.p(18));
        textView.setPadding(wt2.p(5), 0, wt2.p(5), wt2.p(1));
        textView.setTextColor(-1);
        textView.setTextSize(2, this.W1.q(11.0f, 11.0f, 12.0f, 13.0f));
        textView.setTypeface(Typeface.DEFAULT_BOLD);
        textView.setVisibility(8);
        return textView;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:127:0x0367  */
    /* JADX WARN: Code duplicated, block: B:153:0x03a5  */
    /* JADX WARN: Code duplicated, block: B:188:0x049b  */
    /* JADX WARN: Code duplicated, block: B:214:0x04e4  */
    /* JADX WARN: Code duplicated, block: B:291:0x06fe  */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue
    java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.RegisterArg.getSVar()" because "arg" is null
    	at jadx.core.dex.instructions.args.RegisterArg.sameCodeVar(RegisterArg.java:193)
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.extractConstNumber(SwitchOverStringVisitor.java:369)
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.collectPart1RegionCases(SwitchOverStringVisitor.java:207)
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.restoreSwitchOverString(SwitchOverStringVisitor.java:108)
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.visitRegion(SwitchOverStringVisitor.java:72)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:140)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterative(DepthRegionTraversal.java:47)
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.visit(SwitchOverStringVisitor.java:66)
     */
    public final void Z0() {
        int i;
        boolean z;
        Uri uri;
        float f;
        h91 h91Var;
        y81 y81VarB;
        long jH;
        int i2;
        int i3;
        boolean zExists;
        String str;
        boolean z2;
        hd2 hd2VarS;
        kn knVar;
        Object obj;
        int iR;
        wt2.f0(this);
        gu1 gu1Var = this.H;
        if (gu1Var.S0) {
            gu1Var.o();
        }
        boolean zA = wt2.A(this.H.c);
        gu1 gu1Var2 = this.H;
        m6 = (gu1Var2.c == null || gu1Var2.d) ? false : true;
        if (getIntent() != null && getIntent().getBooleanExtra("join_room", false)) {
            this.R1 = true;
            getIntent().removeExtra("join_room");
        }
        if (getIntent() != null && getIntent().getStringExtra("join_code") != null) {
            this.R1 = true;
            this.S1 = getIntent().getStringExtra("join_code");
            this.T1 = getIntent().getStringExtra("join_password");
            getIntent().removeExtra("join_code");
            getIntent().removeExtra("join_password");
        }
        boolean z3 = this.R1;
        if (z3) {
            m6 = false;
        }
        this.Z4 = false;
        boolean z4 = m6 && this.H.c.equals(this.Y);
        this.Y = null;
        boolean z5 = this.M4;
        this.M4 = false;
        this.j0 = true;
        M();
        if (q6) {
            q6 = false;
        } else {
            p6 = this.H.V;
        }
        Uri uri2 = this.H.c;
        String string = uri2 != null ? uri2.toString() : null;
        if (string == null || !string.equals(this.a0)) {
            this.a0 = string;
            this.Z = 0;
            this.g0 = 0;
            this.o0 = 0;
            this.p0 = 0;
        }
        if (F6 == 0) {
            F6 = System.currentTimeMillis();
        }
        G6 = 0L;
        this.e0 = 0;
        this.l0 = -1;
        this.c0 = -9223372036854775807L;
        this.T = null;
        this.U = null;
        this.V5.clear();
        this.W = 0L;
        this.P2 = false;
        this.T2 = false;
        this.X2 = false;
        this.t0 = 0L;
        this.B.removeCallbacks(this.Q);
        this.B.removeCallbacks(this.W2);
        this.U2 = 0;
        this.V2 = false;
        this.R2 = false;
        this.j2 = false;
        this.r.clear();
        this.t = null;
        this.P5 = null;
        this.Q5 = false;
        this.s.clear();
        this.x0 = null;
        vg0 vg0Var = k6;
        if (vg0Var != null) {
            vg0Var.e0(this.l);
            vg0 vg0Var2 = k6;
            xq1 xq1Var = this.X;
            vg0Var2.A1();
            p20 p20Var = vg0Var2.s;
            xq1Var.getClass();
            p20Var.q.e(xq1Var);
            k6.y();
            k6.k1();
            k6 = null;
            this.D = null;
            j6 = null;
            this.n4 = null;
        }
        z50 z50Var = new z50(this, new cr1((byte) 9));
        this.p = z50Var;
        s50 s50VarD = z50Var.d();
        s50VarD.R = true;
        z50Var.o(new t50(s50VarD));
        if (this.H.x) {
            z50 z50Var2 = this.p;
            s50 s50VarD2 = z50Var2.d();
            s50VarD2.P = true;
            z50Var2.o(new t50(s50VarD2));
        }
        r();
        z50 z50Var3 = this.p;
        s50 s50VarD3 = z50Var3.d();
        s50VarD3.C = 1;
        z50Var3.o(new t50(s50VarD3));
        if (this.p != null) {
            vs1 vs1Var = this.l3;
            String[] strArr = vs1Var == null ? null : vs1Var.e.d;
            List listAsList = this.I4 ? Collections.EMPTY_LIST : strArr != null ? Arrays.asList(strArr) : wt2.d0(this.H.X);
            if (!listAsList.isEmpty()) {
                z50 z50Var4 = this.p;
                s50 s50VarD4 = z50Var4.d();
                s50VarD4.y = iq2.g((String[]) listAsList.toArray(new String[0]));
                s50VarD4.A = false;
                z50Var4.o(new t50(s50VarD4));
            }
        }
        qc0 qc0Var = new qc0((byte) 25);
        p30 p30Var = new p30();
        p30Var.c(qc0Var);
        synchronized (p30Var) {
            p30Var.l = (byte) 64;
        }
        p30Var.e();
        gu1 gu1Var3 = this.H;
        Uri uri3 = gu1Var3.c;
        String str2 = gu1Var3.h;
        boolean z7 = gu1Var3.x;
        boolean z8 = gu1Var3.Q == 0;
        HashSet hashSet = new HashSet(this.H.Q0);
        hashSet.addAll(u6);
        q01 q01Var = new q01(this, this, hashSet);
        gu1 gu1Var4 = this.H;
        q01Var.a = gu1Var4.Q;
        q01Var.b = true;
        q01Var.c = gu1Var4.T || gu1Var4.V;
        if (p6 || Q6) {
            q01Var.f = new ja1(uri3, str2, z7, z8);
        }
        gg0 gg0Var = new gg0(this, q01Var);
        z50 z50Var5 = this.p;
        ha1.s(!gg0Var.m);
        z50Var5.getClass();
        gg0Var.d = new hc(z50Var5, (byte) 3);
        ga1 ga1Var = new ga1(this);
        this.w = null;
        byte b = 28;
        byte b2 = 2;
        Object obj2 = ga1Var;
        obj2 = ga1Var;
        if (m6 && zA && this.H.c.getScheme().toLowerCase().startsWith("http")) {
            obj2 = ga1Var;
            TreeMap treeMap = new TreeMap(String.CASE_INSENSITIVE_ORDER);
            treeMap.put("Accept", "*/*");
            treeMap.put("Accept-Language", Locale.getDefault().toLanguageTag());
            try {
                str = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
            } catch (PackageManager.NameNotFoundException unused) {
                str = "?";
            }
            StringBuilder sbK = jf2.k("JustPlusPlayer/", str, " (Linux;Android ");
            sbK.append(Build.VERSION.RELEASE);
            sbK.append(") AndroidXMedia3/1.11.1");
            treeMap.put("User-Agent", sbK.toString());
            if (this.B3 != null) {
                int i4 = 0;
                while (true) {
                    int i5 = i4 + 1;
                    String[] strArr2 = this.B3;
                    if (i5 >= strArr2.length) {
                        break;
                    }
                    String str3 = strArr2[i4];
                    String str4 = strArr2[i5];
                    if (str3 != null && str4 != null) {
                        treeMap.put(str3, str4);
                    }
                    i4 += 2;
                }
            }
            String userInfo = this.H.c.getUserInfo();
            if (userInfo != null && userInfo.length() > 0 && userInfo.contains(":")) {
                treeMap.put("Authorization", "Basic " + Base64.encodeToString(userInfo.getBytes(), 2));
            }
            ng0 ng0Var = new ng0(x6);
            ng0Var.X(treeMap);
            ng0 ng0Var2 = new ng0(new ga1(this, ng0Var), new wb1((byte) 25), b);
            gu1 gu1Var5 = this.H;
            String str5 = gu1Var5.h;
            if (str5 == null) {
                str5 = (String) this.u.get(gu1Var5.c.toString());
            }
            if (ot2.R(this.H.c) == 4) {
                Uri uri4 = this.H.c;
                if (str5 != null) {
                    switch (str5) {
                        case "application/x-mpegURL":
                            iR = 2;
                            break;
                        case "application/vnd.ms-sstr+xml":
                            iR = 1;
                            break;
                        case "application/dash+xml":
                            iR = 0;
                            break;
                        case "application/x-rtsp":
                            iR = 3;
                            break;
                        default:
                            iR = 4;
                            break;
                    }
                } else {
                    iR = ot2.R(uri4);
                }
                if (iR != 4) {
                    z2 = true;
                } else {
                    z2 = false;
                }
            } else {
                z2 = true;
            }
            y61.x(this, this.H.c.toString());
            if (z2 || (hd2VarS = y61.s(this)) == null) {
                obj = ng0Var2;
            } else {
                knVar = new kn();
                knVar.m = new na0((byte) 8);
                knVar.l = hd2VarS;
                knVar.p = ng0Var2;
                knVar.n = new he(hd2VarS, (byte) 26);
                knVar.o = false;
                knVar.q = (byte) 2;
            }
            if (z2) {
                obj = knVar;
                ng0Var2 = null;
            }
            obj = knVar;
            this.w = ng0Var2;
            obj2 = obj;
        }
        obj2 = ga1Var;
        h32 h32Var = new h32(this, new ng0(new ng0(obj2, new yo1(this, (byte) 10), b), new yo1(this, (byte) 11), b));
        this.x = h32Var;
        lb1 lb1Var = new lb1(new h32(h32Var, this.z, (byte) 16), b);
        boolean z9 = (this.H.T || p6) ? false : true;
        xb0 xb0Var = z9 ? new xb0(p30Var, qc0Var, this.H.U) : null;
        this.S = xb0Var;
        ph0 ph0Var = p30Var;
        if (z9) {
            ph0Var = xb0Var;
        }
        r40 r40Var = new r40(this, new tb0(new gf(new tb0(ph0Var, (byte) 1), qc0Var), (byte) 0));
        r40Var.b = lb1Var;
        gv1 gv1Var = (gv1) r40Var.d;
        if (lb1Var != ((q00) gv1Var.e)) {
            gv1Var.e = lb1Var;
            ((HashMap) gv1Var.c).clear();
            ((HashMap) gv1Var.d).clear();
        }
        r40Var.g(new vq1((byte) 24));
        this.v = r40Var;
        ha1.s(!gg0Var.m);
        byte b3 = 4;
        gg0Var.c = new hc(r40Var, b3);
        Uri uri5 = this.H.c;
        String scheme = uri5 == null ? null : uri5.getScheme();
        if (scheme != null) {
            String lowerCase = scheme.toLowerCase();
            lowerCase.getClass();
            switch (lowerCase.hashCode()) {
                case -1255746506:
                    if (!lowerCase.equals("rawresource")) {
                        b3 = -1;
                    } else {
                        b3 = 0;
                    }
                    break;
                case -368816979:
                    if (!lowerCase.equals("android.resource")) {
                        b3 = -1;
                    } else {
                        b3 = 1;
                    }
                    break;
                case 3076010:
                    if (!lowerCase.equals("data")) {
                        b3 = -1;
                    } else {
                        b3 = 2;
                    }
                    break;
                case 3143036:
                    if (!lowerCase.equals("file")) {
                        b3 = -1;
                    } else {
                        b3 = 3;
                    }
                    break;
                case 93121264:
                    if (!lowerCase.equals("asset")) {
                        b3 = -1;
                    }
                    break;
                case 951530617:
                    if (!lowerCase.equals("content")) {
                        b3 = -1;
                    } else {
                        b3 = 5;
                    }
                    break;
                default:
                    b3 = -1;
                    break;
            }
            switch (b3) {
                case DecoderInputBuffer.BUFFER_REPLACEMENT_MODE_DISABLED /* 0 */:
                case 1:
                case 2:
                case VideoDecoderOutputBuffer.COLORSPACE_BT2020 /* 3 */:
                case 4:
                case 5:
                    i = 0;
                    break;
                default:
                    i = this.H.z;
                    break;
            }
        } else {
            i = 0;
        }
        this.e3 = i;
        if ("memory".equals(this.H.A)) {
            ll llVar = new ll(this.H.B, this.e3);
            this.f3 = llVar;
            this.g3 = null;
            ha1.s(!gg0Var.m);
            gg0Var.e = new hc(llVar, b2);
            z = true;
        } else {
            this.f3 = null;
            kl klVar = new kl(this.H.B, this.e3);
            this.g3 = klVar;
            z = true;
            ha1.s(!gg0Var.m);
            gg0Var.e = new hc(klVar, b2);
        }
        ha1.s(gg0Var.m ^ z);
        gg0Var.m = z;
        vg0 vg0Var3 = new vg0(gg0Var);
        k6 = vg0Var3;
        if (!this.H.G) {
            vg0Var3.A1();
            if (vg0Var3.e0 != Integer.MIN_VALUE) {
                vg0Var3.e0 = Integer.MIN_VALUE;
                vg0Var3.q1(2, 5, Integer.MIN_VALUE);
            }
        }
        k6.P(new ub(3, 0, 1, 1, 0, false, true), true);
        vg0 vg0Var4 = k6;
        if (vg0Var4 != null) {
            vg0Var4.f(M6 ? 1.0f : Math.min(N6, 100.0f) / 100.0f);
        }
        t();
        YouTubeOverlay youTubeOverlay = this.E;
        vg0 vg0Var5 = k6;
        youTubeOverlay.D = vg0Var5;
        this.B.setPlayer(vg0Var5);
        v91 v91Var = this.o;
        if (v91Var != null) {
            v91Var.a();
        }
        k6.getClass();
        try {
            this.o = new p91(k6, this).a();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
        this.B.setControllerShowTimeoutMs(-1);
        if (R6) {
            R6 = false;
            V();
        }
        if (m6) {
            wt2.b0(this, this.H.U0);
            ck ckVar = this.I;
            boolean zC = wt2.C(this);
            float f2 = ckVar.c;
            if (f2 >= 0.0f) {
                double d = (((double) f2) * 0.00936d) + 0.064d;
                f = (float) (d * d);
            } else {
                f = -1.0f;
            }
            ValueAnimator valueAnimator = ckVar.b;
            if (valueAnimator != null) {
                valueAnimator.cancel();
                ckVar.b = null;
            }
            float fB = ckVar.a.getWindow().getAttributes().screenBrightness;
            if (fB < r5) {
                fB = ckVar.b();
            }
            float fB2 = f < 0 ? ckVar.b() : f;
            if (zC || Math.abs(fB2 - fB) < 0.01f) {
                ckVar.a(f);
            } else {
                ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(fB, fB2);
                ckVar.b = valueAnimatorOfFloat;
                valueAnimatorOfFloat.setDuration(300L);
                ckVar.b.addUpdateListener(new ah(ckVar, b2));
                ckVar.b.addListener(new bk(ckVar, f));
                ckVar.b.start();
            }
            CustomDefaultTimeBar customDefaultTimeBar = this.y2;
            xr xrVar = this.H0;
            if (zA) {
                customDefaultTimeBar.setBufferedColor(xrVar.l);
            } else {
                customDefaultTimeBar.setBufferedColor(xrVar.m);
            }
            v();
            n81 n81Var = new n81();
            q81 q81Var = new q81();
            List list = Collections.EMPTY_LIST;
            nw0 nw0Var = pw0.m;
            pw0 pw0VarL = cz1.p;
            s81 s81Var = new s81();
            v81 v81Var = v81.d;
            gu1 gu1Var6 = this.H;
            Uri uri6 = gu1Var6.c;
            String str6 = gu1Var6.h;
            String strW = this.y3;
            if (strW == null) {
                strW = wt2.w(this, uri6);
            }
            if (strW != null) {
                g91 g91Var = new g91();
                g91Var.a = strW;
                g91Var.e = strW;
                g91Var.n = this.z3;
                h91Var = new h91(g91Var);
            } else {
                h91Var = null;
            }
            Uri uri7 = this.H.e;
            if (uri7 != null) {
                String scheme2 = uri7.getScheme();
                if ("content".equals(scheme2)) {
                    try {
                        getContentResolver().openInputStream(uri7).close();
                        zExists = true;
                    } catch (Exception unused2) {
                        zExists = false;
                    }
                } else {
                    zExists = new File("file".equals(scheme2) ? uri7.getPath() : uri7.toString()).exists();
                }
                if (zExists) {
                    y81VarB = ij0.b(this, this.H.e, null, true, null, null);
                } else {
                    y81VarB = null;
                }
            } else {
                y81VarB = null;
            }
            ArrayList arrayList = new ArrayList();
            if (this.k3) {
                arrayList.addAll(this.N4);
            }
            if (y81VarB != null) {
                arrayList.add(y81VarB);
            }
            if (!arrayList.isEmpty()) {
                pw0VarL = pw0.l(arrayList);
            }
            pw0 pw0Var = pw0VarL;
            this.W0 = 0L;
            this.X0 = false;
            if (this.U0 == 0 || this.V0) {
                y81VarB = y81VarB;
                gu1 gu1Var7 = this.H;
                jH = gu1Var7.h(gu1Var7.c);
            } else {
                Uri uri8 = this.H.c;
                int i7 = this.C3.isEmpty() ? -1 : this.E3;
                gu1 gu1Var8 = this.H;
                jH = x2(uri8, i7, gu1Var8.h(gu1Var8.c), "askEvery".equals(w2()) || ("askOpen".equals(w2()) && this.U0 == 1));
            }
            if (this.C3.isEmpty()) {
                vg0 vg0Var6 = k6;
                ha1.s(q81Var.b == null || q81Var.a != null);
                u81 u81Var = uri6 != null ? new u81(uri6, str6, q81Var.a != null ? new r81(q81Var) : null, null, list, null, pw0Var, -9223372036854775807L) : null;
                p81 p81Var = new p81(n81Var);
                t81 t81Var = new t81(s81Var);
                if (h91Var == null) {
                    h91Var = h91.M;
                }
                vg0Var6.u(new z81("", p81Var, u81Var, t81Var, h91Var, v81Var), jH);
            } else {
                ArrayList arrayList2 = new ArrayList(this.C3);
                if (y81VarB != null && (i3 = this.E3) >= 0 && i3 < arrayList2.size()) {
                    int i8 = this.E3;
                    arrayList2.set(i8, T4((z81) arrayList2.get(i8), y81VarB));
                }
                k6.h(arrayList2, this.E3, jH);
                if (this.w3 != null && this.E3 < this.l3.f.size()) {
                    this.x3 = (Map) this.w3.get(((rs1) this.l3.f.get(this.E3)).a.toString());
                }
                c7 c7Var = this.m3;
                if (c7Var != null) {
                    c7Var.k(this.E3, true);
                }
            }
            try {
                LoudnessEnhancer loudnessEnhancer = i6;
                if (loudnessEnhancer != null) {
                    loudnessEnhancer.release();
                }
                vg0 vg0Var7 = k6;
                vg0Var7.A1();
                i6 = new LoudnessEnhancer(((Integer) vg0Var7.C.j()).intValue());
                wt2.c();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            x1(true);
            this.J = true;
            this.L = false;
            this.M = false;
            v4(!z4);
            if (!z4 && ((i2 = this.U0) == 1 || (i2 == 2 && "every".equals(this.H.D0)))) {
                n3();
            }
            if (!z5 && !t6 && !z4) {
                this.C2 = true;
            }
            t6 = false;
            if (z4) {
                this.W0 = 0L;
            } else {
                long j = this.W0;
                if (j > 0) {
                    y(j, null);
                }
            }
            this.U0 = 0;
            this.V0 = false;
            I4();
            e3();
            ImageButton imageButton = this.X1;
            if (imageButton != null) {
                wt2.a0(this, imageButton, true);
            }
            wt2.a0(this, this.Y1, true);
            ((DoubleTapPlayerView) this.B).setDoubleTapEnabled(true);
            if (!this.k3) {
                Thread thread = this.b3;
                if (thread != null) {
                    thread.interrupt();
                }
                this.a3 = null;
                Thread thread2 = new Thread(new dp1(this, (byte) 29));
                this.b3 = thread2;
                thread2.start();
            }
            vg0 vg0Var8 = k6;
            boolean z10 = !Q6;
            vg0Var8.A1();
            if (!vg0Var8.p0) {
                vg0Var8.y.n(z10);
            }
        } else if (z3) {
            this.B.j();
            String str7 = this.S1;
            if (str7 != null) {
                String str8 = this.T1;
                this.S1 = null;
                this.T1 = null;
                this.B.post(new rp1(this, str7, str8, (byte) 1));
            } else {
                this.B.post(new wp1(this, (byte) 0));
            }
        } else {
            D();
        }
        k6.H(this.l);
        vg0 vg0Var9 = k6;
        xq1 xq1Var2 = this.X;
        p20 p20Var2 = vg0Var9.s;
        xq1Var2.getClass();
        p20Var2.getClass();
        p20Var2.q.a(xq1Var2);
        Uri uriD0 = d0();
        if (uriD0 != null) {
            wt2.K("media=" + wt2.W(uriD0, this.H.L0));
        }
        if (z6 == null && this.H.Q != 0) {
            z6 = Boolean.valueOf(FfmpegLibrary.a.isAvailable());
        }
        if (z4) {
            W2(false);
            cz czVar = this.B;
            ImageButton imageButton2 = this.o2;
            Objects.requireNonNull(imageButton2);
            czVar.post(new lj1(imageButton2, b2));
        } else {
            k6.d();
        }
        this.q1 = SystemClock.elapsedRealtime();
        if (!L2() || ((zl0) this.w4.a) == null || (uri = this.H.c) == null || !uri.equals(this.C4)) {
            this.A4 = null;
            a3(null);
            Z2(L2() ? this.H.f : null);
        } else {
            l82 l82Var = this.r4;
            if (l82Var != null) {
                l82Var.b();
            }
            this.D4 = true;
        }
        if (this.A2 && this.Z2) {
            this.A2 = false;
            this.B.j();
            this.B.setControllerShowTimeoutMs(3500);
            k6.k(true);
        }
    }

    public final boolean Z1(String str, boolean z) {
        if (str == null || "audio/raw".equals(str) || k6 == null || this.D == null || this.H.Q0.contains(str)) {
            return false;
        }
        HashSet hashSet = u6;
        if (hashSet.contains(str)) {
            return false;
        }
        StringBuilder sb = new StringBuilder("audio passthrough revoked: ");
        sb.append(str);
        sb.append(z ? ", persisted" : "");
        wt2.K(sb.toString());
        hashSet.add(str);
        if (z) {
            gu1 gu1Var = this.H;
            gu1Var.getClass();
            HashSet hashSet2 = new HashSet(gu1Var.Q0);
            hashSet2.add(str);
            gu1Var.Q0 = hashSet2;
            gu1Var.b.edit().putStringSet("revokedAudioMimes", hashSet2).apply();
        }
        this.D.b.add(str);
        if (this.H.Q != 0) {
            k6.d();
            return true;
        }
        this.A2 = true;
        q6 = true;
        this.B.post(new dp1(this, (byte) 19));
        return true;
    }

    public final void Z2(Uri uri) {
        if (this.r4 == null) {
            return;
        }
        if (uri == null || !uri.equals(this.v4)) {
            this.H.B(uri);
            a3(null);
            D1(uri);
            E4();
            D4();
            return;
        }
        kk2 kk2Var = this.t4;
        if (kk2Var != null) {
            kk2Var.g(this.u4);
            this.t4.e(this.s4);
        }
    }

    public final int Z3(int i) {
        zl0 zl0Var;
        vg0 vg0Var = k6;
        if (vg0Var == null) {
            zl0Var = null;
        } else {
            vg0Var.A1();
            zl0Var = vg0Var.V;
        }
        if (zl0Var == null || i != 2) {
            return 0;
        }
        boolean zD = wt2.D(zl0Var);
        int i2 = zl0Var.w;
        int i3 = zl0Var.x;
        Rational rational = zD ? new Rational(i3, i2) : new Rational(i2, i3);
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        if (new Rational(displayMetrics.widthPixels, displayMetrics.heightPixels).floatValue() <= rational.floatValue()) {
            return 0;
        }
        return (displayMetrics.widthPixels - (rational.getNumerator() * (displayMetrics.heightPixels / rational.getDenominator()))) / 2;
    }

    public final boolean a(Uri uri) {
        vg0 vg0Var = k6;
        if (vg0Var != null && uri != null) {
            int iV = vg0Var.V();
            int iA1 = k6.a1();
            if (iV >= 0 && iV < iA1 && !uri.equals(this.L4)) {
                u81 u81Var = k6.Z0(iV).b;
                if (u81Var != null) {
                    nw0 nw0VarN = u81Var.g.listIterator(0);
                    while (nw0VarN.hasNext()) {
                        if (((y81) nw0VarN.next()).a.equals(uri)) {
                        }
                    }
                }
                wt2.K("subtitles: painting " + uri.getLastPathSegment());
                this.I4 = false;
                this.L4 = uri;
                this.K4 = uri;
                this.J4 = null;
                kk2 kk2Var = this.H4;
                if (kk2Var != null) {
                    kk2Var.g(null);
                }
                Thread thread = new Thread(new ap1(this, uri, ij0.p(uri), (byte) 2), "SubtitleTimeline");
                thread.setDaemon(true);
                thread.start();
                return true;
            }
        }
        return false;
    }

    public final boolean a0(View view) {
        View[] viewArrH1 = H1();
        for (int i = 0; i < this.E1 && i < viewArrH1.length; i++) {
            if (viewArrH1[i] == view) {
                return true;
            }
        }
        return false;
    }

    public final boolean a1(ie2 ie2Var) {
        if (ie2Var.c == 2) {
            return true;
        }
        boolean z = ie2Var.i;
        String str = this.o4;
        if (str == null) {
            gu1 gu1Var = this.H;
            str = z ? gu1Var.p0 : gu1Var.o0;
        }
        return "auto".equals(str);
    }

    public final void a2() {
        ImageButton imageButton = this.m2;
        if (imageButton != null) {
            gu1 gu1Var = this.H;
            imageButton.setVisibility((!gu1Var.M0 || gu1Var.P0 == null) ? 8 : 0);
        }
    }

    public final void a3(zl0 zl0Var) {
        if (Objects.equals((zl0) this.w4.a, zl0Var)) {
            return;
        }
        e2();
        this.w4.a = zl0Var;
        this.D4 = zl0Var != null;
        if (zl0Var == null) {
            this.y4 = null;
        }
        if (zl0Var != null) {
            D1(null);
            this.H.B(null);
        }
        l82 l82Var = this.r4;
        if (l82Var != null) {
            l82Var.b();
        }
        s();
        E4();
        D4();
    }

    public final float a4(int i, float f) {
        if (i == 2) {
            return f * 0.0533f;
        }
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        float f2 = displayMetrics.heightPixels / displayMetrics.widthPixels;
        if (f2 < 1.0f) {
            f2 = 1.0f / f2;
        }
        return (f * 0.0533f) / f2;
    }

    public final void b(boolean z, Integer num) {
        this.W0 = 0L;
        this.T0.e(false);
        this.s2.setAlpha(1.0f);
        this.t2.setAlpha(1.0f);
        vg0 vg0Var = k6;
        if (vg0Var == null) {
            this.T0.c();
            return;
        }
        if (!z && vg0Var.C() == 3) {
            this.T0.c();
        }
        if (z) {
            if (num != null) {
                k6.n1(num.intValue(), 0L, false);
            } else {
                k6.o1(0L);
            }
            if (this.T0.isShown()) {
                n3();
            }
        }
        if (num != null || this.X0) {
            this.X0 = false;
            L1();
        }
    }

    public final List b0() {
        Uri uriD0 = d0();
        return (uriD0 == null || !uriD0.toString().equals(this.t)) ? Collections.EMPTY_LIST : this.r;
    }

    public final boolean b1(eg0 eg0Var) {
        if ((eg0Var instanceof eg0) && eg0Var.n == 0 && wt2.A(d0())) {
            Throwable cause = eg0Var;
            while (cause != null) {
                StackTraceElement[] stackTrace = cause.getStackTrace();
                if (stackTrace.length <= 0 || !stackTrace[0].getClassName().startsWith("com.brouken.player.")) {
                    cause = cause.getCause() == cause ? null : cause.getCause();
                }
            }
            int i = eg0Var.l;
            if (i != 2006 && i != 2007 && i != 3003 && i != 3004) {
                return true;
            }
        }
        return false;
    }

    public final void b2(boolean z) {
        if (k6 != null) {
            wt2.K("release player".concat(z ? ", saving" : ""));
        }
        M();
        cz czVar = this.B;
        if (czVar != null) {
            czVar.removeCallbacks(this.b0);
            this.B.removeCallbacks(this.h0);
            this.B.removeCallbacks(this.i0);
            this.B.removeCallbacks(this.k0);
            this.B.removeCallbacks(this.s0);
            this.r0 = -1;
            this.B.removeCallbacks(this.Q2);
            this.B.removeCallbacks(this.W2);
            this.B.removeCallbacks(this.Q);
            this.j2 = false;
            this.R2 = false;
            this.P2 = false;
            this.T2 = false;
            this.X2 = false;
            this.B.removeCallbacks(this.R);
            this.B.removeCallbacks(this.O2);
        }
        this.I2 = -1L;
        this.J2 = -1L;
        this.L2 = 0;
        this.v2 = false;
        cz czVar2 = this.B;
        if (czVar2 != null) {
            czVar2.removeCallbacks(this.w2);
        }
        TextView textView = this.t2;
        if (textView != null) {
            textView.setVisibility(8);
        }
        this.x0 = null;
        if (z) {
            D2();
        }
        if (k6 != null) {
            x1(false);
            v91 v91Var = this.o;
            if (v91Var != null) {
                v91Var.a();
            }
            if (k6.J() && this.B2) {
                this.A2 = true;
            }
            k6.e0(this.l);
            vg0 vg0Var = k6;
            vg0Var.A1();
            p20 p20Var = vg0Var.s;
            xq1 xq1Var = this.X;
            xq1Var.getClass();
            p20Var.q.e(xq1Var);
            k6.y();
            k6.k1();
            k6 = null;
            this.D = null;
            j6 = null;
            this.n4 = null;
        }
        cz czVar3 = this.B;
        if (czVar3 != null) {
            czVar3.removeCallbacks(this.K5);
        }
        N();
        this.L4 = null;
        W0();
        this.e5 = false;
        CustomDefaultTimeBar customDefaultTimeBar = this.y2;
        if (customDefaultTimeBar != null) {
            customDefaultTimeBar.h();
        }
        P3();
        hm1 hm1Var = this.u1;
        if (hm1Var != null) {
            hm1Var.setVisibility(8);
        }
        W2(false);
        com.bumptech.glide.a.d(getApplicationContext()).m(this.O0);
        this.N0.setVisibility(8);
        this.F0 = false;
        s4();
        Dialog dialog = this.L1;
        if (dialog != null) {
            dialog.dismiss();
            this.L1 = null;
        }
        Dialog dialog2 = this.K1;
        if (dialog2 != null) {
            dialog2.dismiss();
            this.K1 = null;
        }
        Dialog dialog3 = this.M1;
        if (dialog3 != null) {
            dialog3.dismiss();
            this.M1 = null;
        }
        Dialog dialog4 = this.O1;
        if (dialog4 != null) {
            dialog4.dismiss();
            this.O1 = null;
        }
        Dialog dialog5 = this.N1;
        if (dialog5 != null) {
            dialog5.dismiss();
            this.N1 = null;
        }
        Dialog dialog6 = this.P1;
        if (dialog6 != null) {
            dialog6.dismiss();
            this.P1 = null;
        }
        Dialog dialog7 = this.Q1;
        if (dialog7 != null) {
            dialog7.dismiss();
            this.Q1 = null;
        }
        Dialog dialog8 = s2.a;
        if (dialog8 != null) {
            dialog8.dismiss();
            s2.a = null;
        }
        ImageButton imageButton = this.w1;
        if (imageButton != null) {
            imageButton.setVisibility(this.C3.size() > 1 ? 0 : 8);
        }
        if (this.x1 != null) {
            this.y1 = false;
            q();
        }
        ImageButton imageButton2 = this.X1;
        if (imageButton2 != null) {
            wt2.a0(this, imageButton2, false);
        }
        wt2.a0(this, this.Y1, false);
    }

    public final void b3(boolean z) {
        TextView textView = this.o5;
        if (textView != null) {
            textView.setVisibility((!z || this.G) ? 8 : 0);
        }
    }

    public final Uri b4() {
        vg0 vg0Var;
        u81 u81Var;
        Uri uri = this.H.e;
        if (uri == null || (vg0Var = k6) == null) {
            return null;
        }
        z81 z81VarZ = vg0Var.z();
        if (z81VarZ != null && (u81Var = z81VarZ.b) != null) {
            nw0 nw0VarN = u81Var.g.listIterator(0);
            while (nw0VarN.hasNext()) {
                if (((y81) nw0VarN.next()).a.equals(uri)) {
                    return null;
                }
            }
        }
        return uri;
    }

    /* JADX WARN: Code duplicated, block: B:137:0x0136 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:138:0x00e4 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:37:0x008a  */
    /* JADX WARN: Code duplicated, block: B:46:0x00d0  */
    /* JADX WARN: Code duplicated, block: B:49:0x00da  */
    /* JADX WARN: Code duplicated, block: B:52:0x00e6  */
    /* JADX WARN: Code duplicated, block: B:54:0x00ec  */
    /* JADX WARN: Code duplicated, block: B:55:0x00fd  */
    /* JADX WARN: Code duplicated, block: B:56:0x0104  */
    /* JADX WARN: Code duplicated, block: B:63:0x011b  */
    /* JADX WARN: Code duplicated, block: B:66:0x0127  */
    /* JADX WARN: Code duplicated, block: B:67:0x012a  */
    /* JADX WARN: Code duplicated, block: B:69:0x0133 A[LOOP:1: B:47:0x00d4->B:69:0x0133, LOOP_END] */
    public final Bundle c() {
        long j;
        String str;
        String str2;
        Iterator it;
        b7 b7Var;
        long j2;
        long jMax;
        b7 b7Var2;
        Bundle bundle = this.n3;
        if (bundle != null) {
            return bundle;
        }
        g4();
        vg0 vg0Var = k6;
        int iV = vg0Var != null ? vg0Var.V() : this.E3;
        c7 c7Var = this.m3;
        Uri uriO1 = O1(iV);
        long[] jArr = this.G3;
        boolean z = this.P4;
        long[] jArr2 = (long[]) c7Var.c;
        ArrayList arrayList = (ArrayList) c7Var.b;
        if (z) {
            c7Var.e(iV, 0L, -9223372036854775807L, true);
        }
        Bundle bundle2 = new Bundle();
        bundle2.putString("uri", uriO1 == null ? null : uriO1.toString());
        bundle2.putInt("index", c7Var.j(iV) ? iV : -1);
        b7 b7Var3 = arrayList.isEmpty() ? null : (b7) jf2.d(1, arrayList);
        long j3 = 0;
        long j4 = (b7Var3 == null || b7Var3.a != iV) ? 0L : b7Var3.d;
        if (c7Var.j(iV)) {
            long j5 = jArr2[iV];
            if (j5 > 0) {
                j = j5;
            } else {
                j = 0;
            }
        } else {
            j = 0;
        }
        b7 b7Var4 = b7Var3;
        bundle2.putLong("position_ms", Math.max(0L, j4));
        bundle2.putInt("position_sec", c7.h(j4));
        bundle2.putLong("duration_ms", j);
        bundle2.putInt("duration_sec", c7.h(j));
        int length = jArr2.length;
        long[] jArr3 = new long[length];
        int i = 0;
        while (i < length) {
            long j7 = j3;
            if (!((boolean[]) c7Var.d)[i]) {
                it = arrayList.iterator();
                while (true) {
                    if (it.hasNext()) {
                        jArr2 = jArr2;
                        b7Var = b7Var4;
                        z = z;
                        jArr3[i] = -1;
                        break;
                        break;
                    }
                    if (((b7) it.next()).a == i) {
                        if (b7Var4 != null) {
                            b7Var2 = b7Var4;
                            if (b7Var2.a == i) {
                                z = z;
                                jArr2 = jArr2;
                                b7Var = b7Var2;
                                jArr3[i] = Math.max(j7, b7Var2.d);
                                break;
                                break;
                            }
                            b7Var = b7Var2;
                        } else {
                            b7Var = b7Var4;
                        }
                        long[] jArr4 = jArr;
                        if (jArr != null) {
                            j2 = -9223372036854775807L;
                        } else {
                            j2 = -9223372036854775807L;
                        }
                        jArr = jArr4;
                        if (j2 == -9223372036854775807L) {
                            jMax = 0;
                        } else {
                            jMax = Math.max(0L, j2);
                        }
                        jArr3[i] = jMax;
                        break;
                        break;
                    }
                    j7 = 0;
                }
            } else {
                long j8 = jArr2[i];
                if (j8 <= j7) {
                    it = arrayList.iterator();
                    while (true) {
                        if (it.hasNext()) {
                            jArr2 = jArr2;
                            b7Var = b7Var4;
                            z = z;
                            jArr3[i] = -1;
                            break;
                        }
                        if (((b7) it.next()).a == i) {
                            if (b7Var4 != null) {
                                b7Var2 = b7Var4;
                                if (b7Var2.a == i) {
                                    z = z;
                                    jArr2 = jArr2;
                                    b7Var = b7Var2;
                                    jArr3[i] = Math.max(j7, b7Var2.d);
                                    break;
                                }
                                b7Var = b7Var2;
                            } else {
                                b7Var = b7Var4;
                            }
                            long[] jArr5 = jArr;
                            if (jArr != null || i >= jArr5.length) {
                                j2 = -9223372036854775807L;
                            } else {
                                j2 = jArr5[i];
                            }
                            jArr = jArr5;
                            if (j2 == -9223372036854775807L) {
                                jMax = 0;
                            } else {
                                jMax = Math.max(0L, j2);
                            }
                            jArr3[i] = jMax;
                            break;
                        }
                        j7 = 0;
                    }
                } else {
                    jArr3[i] = j8;
                    jArr2 = jArr2;
                    b7Var = b7Var4;
                    z = z;
                }
            }
            i++;
            z = z;
            jArr2 = jArr2;
            b7Var4 = b7Var;
            j3 = 0;
        }
        boolean z2 = z;
        int[] iArr = new int[length];
        for (int i2 = 0; i2 < length; i2++) {
            long j9 = jArr3[i2];
            iArr[i2] = j9 < 0 ? -1 : c7.h(j9);
        }
        bundle2.putLongArray("positions_ms", jArr3);
        bundle2.putIntArray("positions_sec", iArr);
        if (((String) c7Var.e) != null) {
            str = "error";
        } else if (c7Var.a) {
            str = z2 ? "completion" : "user";
        } else {
            str = "cancelled";
        }
        bundle2.putString("end_by", str);
        String str3 = (String) c7Var.e;
        if (str3 != null) {
            bundle2.putString("error_message", str3);
        }
        vp2 vp2Var = (vp2) c7Var.f;
        if (vp2Var != null) {
            vp2Var.e("audio", bundle2);
        }
        vp2 vp2Var2 = (vp2) c7Var.g;
        if (vp2Var2 != null) {
            vp2Var2.e("subtitle", bundle2);
            str2 = null;
        } else {
            str2 = null;
            bundle2.putString("subtitle_language", null);
        }
        bundle2.putString("audio_chosen_by", c7Var.j(iV) ? ((String[]) c7Var.h)[iV] : str2);
        bundle2.putString("subtitle_chosen_by", c7Var.j(iV) ? ((String[]) c7Var.i)[iV] : str2);
        int size = arrayList.size();
        Parcelable[] parcelableArr = new Parcelable[size];
        int i3 = 0;
        while (i3 < size) {
            b7 b7Var5 = (b7) arrayList.get(i3);
            Bundle bundle3 = new Bundle();
            bundle3.putInt("index", b7Var5.a);
            int i4 = i3;
            bundle3.putLong("started_at", b7Var5.b);
            ArrayList arrayList2 = arrayList;
            bundle3.putLong("ended_at", b7Var5.c);
            bundle3.putLong("position_ms", Math.max(0L, b7Var5.d));
            bundle3.putInt("position_sec", c7.h(b7Var5.d));
            long j10 = b7Var5.e;
            if (j10 <= 0) {
                j10 = -1;
            }
            bundle3.putLong("duration_ms", j10);
            long j11 = b7Var5.e;
            bundle3.putInt("duration_sec", j11 > 0 ? c7.h(j11) : -1);
            parcelableArr[i4] = bundle3;
            i3 = i4 + 1;
            arrayList = arrayList2;
        }
        bundle2.putParcelableArray("history", parcelableArr);
        int iR4 = R4(iV);
        if (iR4 >= 0) {
            bundle2.putString("voice_label", ((us1) ((rs1) this.l3.f.get(iV)).t.get(iR4)).a);
        }
        vs1 vs1Var = this.l3;
        List list = vs1Var == null ? null : vs1Var.j;
        if (list != null && !list.isEmpty()) {
            ArrayList arrayList3 = new ArrayList(list.subList(0, Math.min(20, list.size())));
            if (list.size() > 20) {
                arrayList3.set(19, "… " + (list.size() - 19) + " more");
            }
            bundle2.putStringArray("warnings", (String[]) arrayList3.toArray(new String[0]));
        }
        return bundle2;
    }

    public final boolean c1(zq1 zq1Var) {
        float f = zq1Var.b;
        float f2 = this.Z1;
        if (f > 0.0f) {
            return Math.abs(f - f2) < 0.001f;
        }
        return f2 == 0.0f && this.B.getResizeMode() == zq1Var.a;
    }

    public final void c2(String str, String str2, ArrayList arrayList, boolean z) {
        String string;
        wj2 wj2Var;
        if (k6 == null) {
            return;
        }
        if (str2 == null) {
            String strN0 = f1() ? null : n0(k6.V());
            if (str == null || strN0 == null) {
                return;
            }
            wj2 wj2VarN = gu1.n(this, j4());
            wj2VarN.R(strN0, str, "");
            gu1.t(this, wj2VarN);
            return;
        }
        ArrayList arrayListD0 = wt2.d0(this.H.W);
        String strI = str == null ? wt2.I(str2, arrayListD0) : str;
        if (strI == null) {
            xp2 xp2VarJ4 = j4();
            String strI2 = xp2VarJ4.i(str2);
            strI = strI2 == null ? null : (String) xp2VarJ4.b.get(strI2);
        }
        String str3 = strI;
        String strN1 = f1() ? null : n0(k6.V());
        rs1 rs1VarG0 = z ? g0() : null;
        if (strN1 != null) {
            string = strN1;
        } else {
            string = rs1VarG0 != null ? rs1VarG0.a.toString() : String.valueOf(d0());
        }
        wj2 wj2VarN2 = gu1.n(this, j4());
        if (z) {
            wj2VarN2.R(strN1, str3, str2);
            if (str3 == null && !arrayListD0.isEmpty()) {
                str3 = (String) arrayListD0.get(0);
            }
            wj2Var = wj2VarN2;
            wj2Var.O(null, string, str3, str2, arrayList);
        } else {
            String str4 = string;
            String str5 = strN1;
            wj2Var = wj2VarN2;
            wj2Var.O(str5, str4, str3, str2, arrayList);
        }
        gu1.t(this, wj2Var);
    }

    public final void c3(ImageButton imageButton, int i, int i2, int i3) {
        if (imageButton == null) {
            return;
        }
        ViewGroup.LayoutParams layoutParams = imageButton.getLayoutParams();
        layoutParams.width = i;
        layoutParams.height = i;
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
            marginLayoutParams.setMarginStart(i3);
            marginLayoutParams.setMarginEnd(i3);
        }
        imageButton.setLayoutParams(layoutParams);
        imageButton.setPadding(i2, i2, i2, i2);
        imageButton.setScaleType(ImageView.ScaleType.FIT_CENTER);
        imageButton.setImageTintList(ColorStateList.valueOf(this.H0.e));
        imageButton.setBackground(wt2.c0(this.H0.b, 10000.0f));
        imageButton.setForeground(wt2.j(this, 0, 0, this.H0.j));
    }

    public final void c4(Uri uri, long j, boolean z) {
        if (k6 == null || uri == null) {
            return;
        }
        D2();
        int iV = k6.V();
        ArrayList arrayList = this.C3;
        if (arrayList.isEmpty() || iV < 0 || iV >= arrayList.size()) {
            this.H.c = uri;
        } else {
            arrayList.set(iV, J3(iV, uri));
            this.E3 = iV;
        }
        this.H.z(j);
        this.M4 = !z;
        this.A2 = z;
        this.G1 = true;
        Z0();
    }

    public final void d(SpannableStringBuilder spannableStringBuilder, String str) {
        if (str == null || str.isEmpty()) {
            return;
        }
        f(spannableStringBuilder, str);
        spannableStringBuilder.setSpan(new ForegroundColorSpan(this.H0.e), spannableStringBuilder.length() - str.length(), spannableStringBuilder.length(), 33);
    }

    public final Uri d0() {
        u81 u81Var;
        vg0 vg0Var = k6;
        z81 z81VarZ = vg0Var != null ? vg0Var.z() : null;
        return (z81VarZ == null || (u81Var = z81VarZ.b) == null) ? this.H.c : u81Var.a;
    }

    public final boolean d1() {
        if (wt2.B(this)) {
            return isInPictureInPictureMode();
        }
        return false;
    }

    public final void d2() {
        this.H.w(this.B.getVideoSurfaceView().getScaleX(), this.Z1, this.B.getResizeMode());
    }

    public final void d3() {
        int iB = this.W1.b(46.0f);
        int iB2 = this.W1.b(10.0f);
        int iB3 = this.W1.b(6.0f);
        ImageButton imageButton = this.p2;
        if (imageButton != null) {
            imageButton.setImageResource(R.drawable.ic_skip_previous);
        }
        ImageButton imageButton2 = this.q2;
        if (imageButton2 != null) {
            imageButton2.setImageResource(R.drawable.ic_skip_next);
        }
        c3(this.p2, iB, iB2, iB3);
        c3(this.q2, iB, iB2, iB3);
        ImageButton imageButton3 = (ImageButton) findViewById(R.id.next);
        if (imageButton3 != null) {
            imageButton3.setImageResource(R.drawable.ic_skip_next);
        }
        ImageButton imageButton4 = (ImageButton) findViewById(R.id.delete);
        if (imageButton4 != null) {
            imageButton4.setImageResource(R.drawable.ic_delete_24dp);
        }
        c3(imageButton3, iB, iB2, iB3);
        c3(imageButton4, iB, iB2, iB3);
        ImageButton imageButton5 = this.p2;
        byte b = 0;
        if (imageButton5 != null) {
            imageButton5.setOnClickListener(new fp1(this, b));
        }
        ImageButton imageButton6 = this.q2;
        if (imageButton6 != null) {
            imageButton6.setOnClickListener(new fp1(this, (byte) 1));
        }
        cz czVar = this.B;
        if (czVar != null) {
            czVar.getViewTreeObserver().addOnPreDrawListener(new gp1(this, b));
        }
    }

    public final void d4(int i) {
        rs1 rs1VarG0 = g0();
        int iI0 = i0();
        if (rs1VarG0 != null) {
            List list = rs1VarG0.v;
            List list2 = rs1VarG0.t;
            if (iI0 < 0 || i == iI0) {
                return;
            }
            us1 us1Var = (us1) list2.get(i);
            Uri uriF0 = f0();
            int iS1 = 0;
            for (ss1 ss1Var : ((us1) list2.get(iI0)).c) {
                if (uriF0 != null && ss1Var.b.equals(uriF0.toString())) {
                    iS1 = S1(ss1Var.a);
                }
            }
            Uri uri = us1Var.b;
            for (ss1 ss1Var2 : us1Var.c) {
                if (iS1 > 0 && S1(ss1Var2.a) == iS1) {
                    uri = Uri.parse(ss1Var2.b);
                    break;
                }
            }
            String strH4 = h4();
            this.r3.remove(strH4);
            List list3 = us1Var.d;
            if (list3 == null) {
                list3 = list;
            }
            List list4 = ((us1) list2.get(iI0)).d;
            if (list4 != null) {
                list = list4;
            }
            if (list3 != list) {
                this.s3.remove(strH4);
                c7 c7Var = this.m3;
                if (c7Var != null) {
                    int iV = k6.V();
                    if (c7Var.j(iV)) {
                        ((String[]) c7Var.i)[iV] = null;
                    }
                }
            }
            c4(uri, Math.max(0L, k6.O0()), k6.w());
        }
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public final boolean dispatchKeyEvent(KeyEvent keyEvent) {
        int keyCode;
        Button button;
        int keyCode2;
        this.a5 = 0;
        if (!n2() && (!this.T0.isShown() || (keyCode2 = keyEvent.getKeyCode()) == 4 || keyCode2 == 164 || keyCode2 == 24 || keyCode2 == 25)) {
            if (!R6 || keyEvent.getKeyCode() == 4) {
                if (this.y0) {
                    int keyCode3 = keyEvent.getKeyCode();
                    if (keyEvent.getAction() == 0) {
                        if (keyCode3 == 19) {
                            F2(true);
                            return true;
                        }
                        if (keyCode3 == 20) {
                            F2(false);
                            return true;
                        }
                    } else if (keyEvent.getAction() == 1 && keyCode3 != 19 && keyCode3 != 20) {
                        if (this.z0) {
                            this.z0 = false;
                            return true;
                        }
                        this.y0 = false;
                        cz czVar = this.B;
                        czVar.postDelayed(czVar.C0, 200L);
                        vg0 vg0Var = k6;
                        if (vg0Var != null && !vg0Var.J()) {
                            this.B.j();
                        }
                        if (Math.abs(this.B.getScaleFit() - this.A0) < 0.005d) {
                            this.B.setScale(1.0f);
                            this.B.setResizeMode(0);
                        }
                        K4();
                        d2();
                        return true;
                    }
                } else {
                    if (Q6 && ((keyCode = keyEvent.getKeyCode()) == 23 || keyCode == 66 || keyCode == 96 || keyCode == 108 || keyCode == 160)) {
                        if (keyEvent.getAction() == 0 && !R6 && !I6 && (button = this.f5) != null && button.getVisibility() == 0) {
                            this.f5.performClick();
                            this.J5 = keyEvent.getKeyCode();
                            return true;
                        }
                        if (keyEvent.getAction() == 1 && this.J5 == keyEvent.getKeyCode()) {
                            this.J5 = 0;
                            return true;
                        }
                    }
                    if (!Q6 || I6 || keyEvent.getKeyCode() == 4) {
                        if (keyEvent.getAction() == 0) {
                            G2();
                        }
                        return super.dispatchKeyEvent(keyEvent);
                    }
                    if (keyEvent.getAction() == 0) {
                        onKeyDown(keyEvent.getKeyCode(), keyEvent);
                        return true;
                    }
                    if (keyEvent.getAction() == 1) {
                        onKeyUp(keyEvent.getKeyCode(), keyEvent);
                    }
                }
            } else if (keyEvent.getAction() == 0 && keyEvent.getRepeatCount() == 0) {
                A3();
                return true;
            }
        }
        return true;
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public final boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getActionMasked() == 0) {
            this.i2 = n2();
        }
        return this.i2 || super.dispatchTouchEvent(motionEvent);
    }

    public final long e0() {
        vg0 vg0Var = k6;
        if (vg0Var == null) {
            return -9223372036854775807L;
        }
        long jO0 = vg0Var.O0();
        xn2 xn2VarR0 = k6.r0();
        return (xn2VarR0.p() || k6.U() != -1) ? jO0 : jO0 - ot2.p0(xn2VarR0.f(k6.L(), this.d0, false).e);
    }

    public final void e2() {
        if (k6 == null) {
            return;
        }
        zl0 zl0Var = (zl0) this.w4.a;
        nw0 nw0VarN = k6.E().a.listIterator(0);
        while (nw0VarN.hasNext()) {
            mq2 mq2Var = (mq2) nw0VarN.next();
            if (mq2Var.b.c == 3) {
                for (int i = 0; i < mq2Var.a; i++) {
                    if (mq2Var.e[i]) {
                        zl0 zl0VarA = mq2Var.a(i);
                        if (zl0Var == null || !zl0Var.equals(zl0VarA)) {
                            this.A4 = mq2Var.b;
                            this.B4 = i;
                            return;
                        }
                    }
                }
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:15:0x003c  */
    public final void e3() {
        String str;
        if (this.d5 == null) {
            ge2 ge2Var = new ge2();
            List list = Collections.EMPTY_LIST;
            ge2Var.b = list;
            ge2Var.c = list;
            ge2Var.d = 0.0d;
            this.d5 = ge2Var;
        }
        this.e5 = false;
        if (k6 != null) {
            ArrayList arrayList = this.D3;
            if (arrayList.isEmpty()) {
                str = this.A3;
            } else {
                int iV = k6.V();
                str = (iV < 0 || iV >= arrayList.size()) ? null : (String) arrayList.get(iV);
            }
        } else {
            str = this.A3;
        }
        boolean z = (str == null || str.isEmpty()) ? false : true;
        this.v5 = z;
        ge2 ge2Var2 = this.d5;
        ge2Var2.a = z ? new ay0(str) : null;
        List list2 = Collections.EMPTY_LIST;
        ge2Var2.c = list2;
        this.d5.b = list2;
        this.w5 = "";
        this.y5 = false;
        this.C5 = -9223372036854775807L;
        this.D5 = -9223372036854775807L;
        this.E5 = -9223372036854775807L;
        this.F5 = -9223372036854775807L;
        this.I5 = -9223372036854775807L;
        CustomDefaultTimeBar customDefaultTimeBar = this.y2;
        if (customDefaultTimeBar != null) {
            customDefaultTimeBar.h();
        }
        if (c0() <= 0.0d) {
            s1();
        }
    }

    public final void e4() {
        hm1 hm1Var;
        if (this.u1 == null || (hm1Var = this.v1) == null || this.C0 == null || hm1Var.getWidth() == 0) {
            return;
        }
        int[] iArr = new int[2];
        int[] iArr2 = new int[2];
        this.v1.getLocationInWindow(iArr);
        this.C0.getLocationInWindow(iArr2);
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.u1.getLayoutParams();
        int i = iArr[0] - iArr2[0];
        int i2 = iArr[1] - iArr2[1];
        if (marginLayoutParams.leftMargin == i && marginLayoutParams.topMargin == i2) {
            return;
        }
        marginLayoutParams.leftMargin = i;
        marginLayoutParams.topMargin = i2;
        this.u1.setLayoutParams(marginLayoutParams);
    }

    public final Uri f0() {
        z81 z81VarZ;
        u81 u81Var;
        vg0 vg0Var = k6;
        return (vg0Var == null || (z81VarZ = vg0Var.z()) == null || (u81Var = z81VarZ.b) == null) ? this.H.c : u81Var.a;
    }

    /* JADX WARN: Code duplicated, block: B:27:0x0076  */
    @Override // android.app.Activity
    public final void finish() {
        boolean z;
        long jO0;
        long duration;
        if (this.m3 != null) {
            Bundle bundleC = c();
            this.n3 = bundleC;
            Intent intentPutExtras = new Intent("com.justplus.player.result").putExtras(bundleC);
            String string = bundleC.getString("uri");
            if (string != null) {
                intentPutExtras.setData(Uri.parse(string));
            }
            setResult(-1, intentPutExtras);
        } else if (this.O4) {
            Intent intent = new Intent("com.mxtech.intent.result.VIEW");
            Uri uriD0 = d0();
            if (!this.P4) {
                vg0 vg0Var = k6;
                if (vg0Var != null && !vg0Var.Q0() && k6.getDuration() != -9223372036854775807L) {
                    long jO1 = k6.O0();
                    ge2 ge2Var = this.d5;
                    z = jO1 >= ge2.b(k6.getDuration(), ge2Var == null ? null : ge2Var.c);
                }
            }
            intent.putExtra("end_by", z ? "playback_completion" : "user");
            if (!z) {
                vg0 vg0Var2 = k6;
                if (vg0Var2 != null) {
                    duration = vg0Var2.getDuration();
                    if (duration == -9223372036854775807L) {
                        duration = 0;
                    }
                    jO0 = k6.x() ? k6.O0() : 0L;
                } else {
                    jO0 = 0;
                    duration = 0;
                }
                if (jO0 <= 0 || duration <= 0) {
                    long j = this.S4;
                    if (j > 0) {
                        uriD0 = this.Q4;
                        jO0 = this.R4;
                        duration = j;
                    }
                }
                if (duration > 0) {
                    intent.putExtra("duration", (int) duration);
                }
                if (jO0 > 0) {
                    intent.putExtra("position", (int) jO0);
                }
            }
            if (uriD0 != null) {
                intent.setData(uriD0);
            }
            setResult(-1, intent);
        }
        super.finish();
    }

    /* JADX WARN: Code duplicated, block: B:135:0x029f  */
    /* JADX WARN: Code duplicated, block: B:138:0x02d2 A[LOOP:4: B:136:0x02cc->B:138:0x02d2, LOOP_END] */
    public final void g(StringBuilder sb) {
        LinkedHashMap linkedHashMap;
        int i;
        zl0 zl0Var;
        int i2;
        String str;
        zb zbVarB;
        String str2;
        int i3;
        int i4;
        String str3;
        String strValueOf;
        if (k6 == null) {
            return;
        }
        LinkedHashMap linkedHashMap2 = new LinkedHashMap();
        int i5 = 0;
        nw0 nw0VarN = k6.E().a.listIterator(0);
        int i7 = 0;
        int i8 = 0;
        int i9 = 0;
        zl0 zl0Var2 = null;
        zl0 zl0Var3 = null;
        while (nw0VarN.hasNext()) {
            mq2 mq2Var = (mq2) nw0VarN.next();
            int i10 = i5;
            while (i10 < mq2Var.a) {
                int i11 = mq2Var.d[i10];
                int i12 = i5;
                if (i11 != 4) {
                    StringBuilder sb2 = new StringBuilder(zl0.c(mq2Var.a(i10)));
                    sb2.append(" (");
                    if (i11 == 0) {
                        strValueOf = "unsupported type";
                    } else if (i11 == 1) {
                        strValueOf = "no decoder";
                    } else if (i11 != 2) {
                        strValueOf = i11 != 3 ? String.valueOf(i11) : "exceeds capabilities";
                    } else {
                        strValueOf = "unsupported DRM";
                    }
                    sb2.append(strValueOf);
                    sb2.append(')');
                    j$.util.Map.EL.merge(linkedHashMap2, sb2.toString(), 1, new jp1());
                }
                i10++;
                i5 = i12;
            }
            int i13 = i5;
            int i14 = mq2Var.b.c;
            if (i14 == 1) {
                i8++;
            } else if (i14 == 2) {
                i7++;
                if (mq2Var.b() && zl0Var2 == null) {
                    zl0Var2 = mq2Var.b.d[i13];
                }
            } else if (i14 == 3) {
                i9++;
                if (mq2Var.b()) {
                    zl0Var3 = mq2Var.b.d[i13];
                }
            }
            i5 = i13;
        }
        int i15 = i5;
        sb.append("\nVideo: ");
        vg0 vg0Var = k6;
        vg0Var.A1();
        sb.append(zl0.c(vg0Var.V));
        if (m0() != null) {
            sb.append("\nVideo dropped by the extractor: ");
            sb.append(m0());
        }
        sb.append("\nAudio: ");
        vg0 vg0Var2 = k6;
        vg0Var2.A1();
        sb.append(zl0.c(vg0Var2.W));
        if (this.T != null || this.U != null) {
            sb.append("\nDecoders: ");
            String str4 = this.T;
            if (str4 == null) {
                str4 = "none";
            }
            sb.append(str4);
            sb.append(" / ");
            String str5 = this.U;
            if (str5 == null) {
                str5 = "none";
            }
            sb.append(str5);
        }
        vg0 vg0Var3 = k6;
        vg0Var3.A1();
        if (vg0Var3.V != null) {
            vg0 vg0Var4 = k6;
            vg0Var4.A1();
            zl0Var2 = vg0Var4.V;
        }
        String str6 = "yes";
        if (zl0Var2 == null || (str2 = zl0Var2.p) == null || (i3 = zl0Var2.w) == -1 || (i4 = zl0Var2.x) == -1) {
            linkedHashMap = linkedHashMap2;
            i = i8;
            zl0Var = zl0Var3;
            i2 = i9;
        } else {
            float f = zl0Var2.B;
            if (f == -1.0f || f <= 0.0f) {
                f = 30.0f;
            }
            ArrayList<String> arrayList = new ArrayList();
            try {
                MediaCodecInfo[] codecInfos = new MediaCodecList(i15).getCodecInfos();
                int length = codecInfos.length;
                int i16 = 0;
                while (i16 < length) {
                    int i17 = i16;
                    MediaCodecInfo mediaCodecInfo = codecInfos[i17];
                    if (mediaCodecInfo.isEncoder()) {
                        linkedHashMap = linkedHashMap2;
                        str3 = str6;
                    } else {
                        linkedHashMap = linkedHashMap2;
                        try {
                            String[] supportedTypes = mediaCodecInfo.getSupportedTypes();
                            str3 = str6;
                            int length2 = supportedTypes.length;
                            int i18 = 0;
                            while (true) {
                                if (i18 < length2) {
                                    int i19 = i18;
                                    if (supportedTypes[i19].equalsIgnoreCase(str2)) {
                                        MediaCodecInfo.CodecCapabilities capabilitiesForType = mediaCodecInfo.getCapabilitiesForType(str2);
                                        MediaCodecInfo.VideoCapabilities videoCapabilities = capabilitiesForType.getVideoCapabilities();
                                        if (videoCapabilities != null) {
                                            boolean zIsSizeSupported = videoCapabilities.isSizeSupported(i3, i4);
                                            MediaCodecInfo mediaCodecInfo2 = mediaCodecInfo;
                                            StringBuilder sb3 = new StringBuilder(mediaCodecInfo2.getName());
                                            zl0Var = zl0Var3;
                                            if (Build.VERSION.SDK_INT >= 29) {
                                                try {
                                                    sb3.append(mediaCodecInfo2.isHardwareAccelerated() ? " (hw)" : " (sw)");
                                                } catch (Exception e) {
                                                    e = e;
                                                    i = i8;
                                                    i2 = i9;
                                                }
                                            }
                                            sb3.append(" max ");
                                            sb3.append(videoCapabilities.getSupportedWidths().getUpper());
                                            sb3.append('x');
                                            sb3.append(videoCapabilities.getSupportedHeights().getUpper());
                                            sb3.append(", size ");
                                            sb3.append(zIsSizeSupported ? str3 : "no");
                                            sb3.append(", turned ");
                                            sb3.append(videoCapabilities.isSizeSupported(i4, i3) ? str3 : "no");
                                            if (zIsSizeSupported) {
                                                sb3.append(", up to ");
                                                i = i8;
                                                i2 = i9;
                                                try {
                                                    sb3.append(Math.round(((Double) videoCapabilities.getSupportedFrameRatesFor(i3, i4).getUpper()).doubleValue()));
                                                    sb3.append(" fps, this rate ");
                                                    sb3.append(videoCapabilities.areSizeAndRateSupported(i3, i4, (double) f) ? str3 : "no");
                                                } catch (Exception e2) {
                                                    e = e2;
                                                }
                                            } else {
                                                i = i8;
                                                i2 = i9;
                                            }
                                            sb3.append(", instances ");
                                            sb3.append(capabilitiesForType.getMaxSupportedInstances());
                                            arrayList.add(sb3.toString());
                                            break;
                                        }
                                    } else {
                                        zl0Var3 = zl0Var3;
                                        i18 = i19 + 1;
                                        mediaCodecInfo = mediaCodecInfo;
                                    }
                                    i2 = i9;
                                    arrayList.add("query failed: " + e);
                                    if (!arrayList.isEmpty()) {
                                        sb.append("\nDecoder limits for ");
                                        sb.append(i3);
                                        sb.append('x');
                                        sb.append(i4);
                                        sb.append(" @");
                                        sb.append(Math.round(f));
                                        sb.append(" fps ");
                                        sb.append(str2);
                                        sb.append(':');
                                        for (String str7 : arrayList) {
                                            sb.append("\n  ");
                                            sb.append(str7);
                                        }
                                    }
                                }
                            }
                            i16 = i17 + 1;
                            i8 = i;
                            i9 = i2;
                            linkedHashMap2 = linkedHashMap;
                            str6 = str3;
                            zl0Var3 = zl0Var;
                        } catch (Exception e3) {
                            e = e3;
                            i = i8;
                            zl0Var = zl0Var3;
                        }
                    }
                    i = i8;
                    zl0Var = zl0Var3;
                    i2 = i9;
                    i16 = i17 + 1;
                    i8 = i;
                    i9 = i2;
                    linkedHashMap2 = linkedHashMap;
                    str6 = str3;
                    zl0Var3 = zl0Var;
                }
                linkedHashMap = linkedHashMap2;
                i = i8;
                zl0Var = zl0Var3;
                i2 = i9;
            } catch (Exception e4) {
                e = e4;
                linkedHashMap = linkedHashMap2;
            }
            if (!arrayList.isEmpty()) {
                sb.append("\nDecoder limits for ");
                sb.append(i3);
                sb.append('x');
                sb.append(i4);
                sb.append(" @");
                sb.append(Math.round(f));
                sb.append(" fps ");
                sb.append(str2);
                sb.append(':');
                while (r0.hasNext()) {
                    sb.append("\n  ");
                    sb.append(str7);
                }
            }
        }
        sb.append("\nAudio out: ");
        gu1 gu1Var = this.H;
        boolean z = gu1Var.M && gu1Var.N;
        if (z) {
            zbVarB = F0(this);
            str = null;
        } else {
            cz1 cz1Var = zb.e;
            str = null;
            zbVarB = zb.b(this, ub.i, null, zb.f);
        }
        StringBuilder sb4 = new StringBuilder();
        int[] iArr = {5, 6, 18, 17, 7, 8, 14};
        String[] strArr = {"AC3", "E-AC3", "E-AC3 JOC", "AC4", "DTS", "DTS-HD", "TrueHD"};
        for (int i20 = 0; i20 < 7; i20++) {
            if (ot2.l(zbVarB.a, iArr[i20])) {
                sb4.append(sb4.length() == 0 ? "" : " ");
                sb4.append(strArr[i20]);
            }
        }
        String str8 = z ? ", declared by the app, not by the route" : "";
        StringBuilder sb5 = new StringBuilder();
        sb5.append(zbVarB.b);
        sb5.append(" ch, ");
        sb5.append(sb4.length() == 0 ? "PCM only" : "bitstream " + ((Object) sb4));
        sb5.append(str8);
        sb.append(sb5.toString());
        sb.append("\nSubtitle: ");
        sb.append(zl0Var != null ? zl0.c(zl0Var) : "none");
        sb.append("\nTracks: ");
        sb.append(i7);
        sb.append(" video, ");
        sb.append(i);
        sb.append(" audio, ");
        sb.append(i2);
        sb.append(" subtitle");
        if (!linkedHashMap.isEmpty()) {
            ArrayList arrayList2 = new ArrayList(linkedHashMap.size());
            for (Map.Entry entry : linkedHashMap.entrySet()) {
                arrayList2.add(((Integer) entry.getValue()).intValue() > 1 ? ((String) entry.getKey()) + " ×" + entry.getValue() : (String) entry.getKey());
            }
            sb.append("\nRefused: ");
            sb.append(TextUtils.join(", ", arrayList2));
        }
        long duration = k6.getDuration();
        sb.append("\nPosition: ");
        sb.append(k6.O0());
        sb.append('/');
        sb.append(duration == -9223372036854775807L ? "unknown" : String.valueOf(duration));
        sb.append(" ms, buffered ");
        sb.append(k6.v());
        sb.append(" ms, state ");
        sb.append(M3(k6.C()));
        sb.append(k6.J() ? " (playing)" : k6.w() ? " (play when ready)" : " (paused)");
        sb.append(", item ");
        sb.append(k6.V() + 1);
        sb.append('/');
        sb.append(k6.a1());
        vg0 vg0Var5 = k6;
        vg0Var5.A1();
        t10 t10Var = vg0Var5.g0;
        if (t10Var != null) {
            synchronized (t10Var) {
            }
            sb.append("\nVideo frames: ");
            sb.append(t10Var.e);
            sb.append(" rendered, ");
            sb.append(t10Var.g);
            sb.append(" dropped (");
            sb.append(t10Var.i);
            sb.append(" in a row), ");
            sb.append(t10Var.f);
            sb.append(" skipped");
            if (t10Var.l > 0) {
                sb.append(", mean offset ");
                sb.append(t10Var.k / ((long) t10Var.l));
                sb.append(" us");
            }
        }
        long jO = k6.o();
        if (jO != -9223372036854775807L) {
            sb.append("\nLive: ");
            sb.append(jO);
            sb.append(" ms behind the edge");
        }
        sb.append("\nPlayback: decoder priority ");
        sb.append(this.H.Q);
        sb.append(", speed ");
        sb.append(this.H.m);
        sb.append(", resize ");
        sb.append(this.H.i);
        sb.append(this.H.x ? ", tunneling" : "");
        sb.append(this.H.y ? ", frame rate matching" : "");
        sb.append(this.e3 > 0 ? ", back buffer " + (this.e3 / 1000) + "s" : "");
        gu1 gu1Var2 = this.H;
        sb.append((gu1Var2.y && gu1Var2.C) ? ", resolution matching" : "");
        sb.append(this.H.T ? ", map DV7" : "");
        sb.append(this.H.V ? ", no DV" : "");
        sb.append(this.H.U ? ", no HDR10+ under DV" : "");
        sb.append(R() ? ", dialogue lift" : "");
        sb.append(o0() ? ", night mode" : "");
        sb.append("\nRecovery: retries source=");
        sb.append(this.Z);
        sb.append(" decoder=");
        sb.append(this.g0);
        sb.append(" freeze=");
        sb.append(this.o0);
        sb.append((!p6 || this.H.V) ? "" : ", forced HEVC for Dolby Vision");
        sb.append("; audio restart pending=");
        sb.append(this.R2);
        sb.append(" inFlight=");
        sb.append(this.P2);
        sb.append(" settling=");
        sb.append(this.T2);
        if (this.t0 != 0) {
            sb.append(", last reselect ");
            sb.append(SystemClock.elapsedRealtime() - this.t0);
            sb.append(" ms ago");
        }
        xb0 xb0Var = this.S;
        String str9 = xb0Var != null ? xb0Var.n : str;
        if (str9 != null) {
            sb.append("\nDolby Vision profile 7: ");
            sb.append(str9);
        }
        if (!this.H.Q0.isEmpty()) {
            sb.append("\nAudio passthrough revoked: ");
            sb.append(this.H.Q0);
        }
        String strV = wt2.V();
        if (strV.isEmpty()) {
            return;
        }
        sb.append("\n\nTrace:\n");
        sb.append(strV);
    }

    public final rs1 g0() {
        vg0 vg0Var;
        int iV;
        if (this.l3 == null || (vg0Var = k6) == null || (iV = vg0Var.V()) < 0 || iV >= this.l3.f.size()) {
            return null;
        }
        return (rs1) this.l3.f.get(iV);
    }

    public final void g2(String str, eg0 eg0Var) {
        w3.b().q("Playback ".concat(str), new nk((Object) this, str, (Serializable) eg0Var, (byte) 7));
    }

    public final void g4() {
        vg0 vg0Var;
        if (this.m3 == null || (vg0Var = k6) == null || !vg0Var.x()) {
            return;
        }
        this.m3.f(k6.V(), k6.O0(), k6.getDuration());
    }

    public final void h(int i) {
        zq1 zq1Var = (zq1) ((ArrayList) I0()).get(i);
        float f = zq1Var.b;
        this.Z1 = f;
        this.B.t(zq1Var.a, f);
        s2.E(this.B, zq1Var.c, R.drawable.ic_aspect_ratio_24dp);
        K4();
        d2();
    }

    public final LinkedHashMap h0() {
        int iV;
        int iI0 = i0();
        if (iI0 >= 0) {
            return R1(((us1) g0().t.get(iI0)).c);
        }
        if (k6 != null) {
            ArrayList arrayList = this.d4;
            if (!arrayList.isEmpty() && (iV = k6.V()) >= 0 && iV < arrayList.size()) {
                return (LinkedHashMap) arrayList.get(iV);
            }
        }
        return this.e4;
    }

    public final void h2(long j) {
        long j2 = j - this.H2;
        if (Math.abs(j2) > 1000) {
            this.G2 = true;
        }
        TextView textView = (TextView) this.B.findViewById(R.id.exo_position);
        if (textView != null) {
            StringBuilder sb = new StringBuilder();
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(ot2.L(sb, new Formatter(sb, Locale.getDefault()), j));
            if (this.G2 && Q6) {
                int length = spannableStringBuilder.length();
                spannableStringBuilder.append((CharSequence) "  ").append((CharSequence) wt2.u(j2));
                spannableStringBuilder.setSpan(new ForegroundColorSpan(this.H0.g), length, spannableStringBuilder.length(), 33);
                spannableStringBuilder.setSpan(new AbsoluteSizeSpan(Math.round((textView.getTextSize() * 14.0f) / 16.0f)), length, spannableStringBuilder.length(), 33);
            }
            textView.setText(spannableStringBuilder);
        }
        P2(j);
    }

    public final void h3() {
        List listI0 = I0();
        ArrayList arrayList = new ArrayList();
        byte b = 0;
        int i = 0;
        while (true) {
            ArrayList arrayList2 = (ArrayList) listI0;
            if (i >= arrayList2.size()) {
                s2.q(this, this.W1, new xo1(this, b), getString(R.string.button_crop), arrayList);
                return;
            } else {
                arrayList.add(new u70(((zq1) arrayList2.get(i)).c, null, c1((zq1) arrayList2.get(i)), new wo1(this, i, b)));
                i++;
            }
        }
    }

    public final String h4() {
        if (k6 == null) {
            return null;
        }
        if (this.k3) {
            return "#" + k6.V();
        }
        Uri uriD0 = d0();
        if (uriD0 == null) {
            return null;
        }
        return uriD0.toString();
    }

    public final int i0() {
        vg0 vg0Var = k6;
        if (vg0Var == null) {
            return -1;
        }
        return R4(vg0Var.V());
    }

    public final void i2(final eg0 eg0Var, final sj2 sj2Var, final String str) {
        final String strK3 = K3(sj2Var);
        final String str2 = L3() ? "at-start" : "mid-stream";
        w3.b().p(eg0Var, new p3() { // from class: qp1
            @Override // io.sentry.p3
            public final void g(v0 v0Var) {
                LoudnessEnhancer loudnessEnhancer = PlayerActivity.i6;
                String str3 = str;
                String str4 = str3 != null ? "stuck-recovered" : "stuck";
                String str5 = strK3;
                String str6 = str2;
                v0Var.i(Arrays.asList(str4, str5, str6));
                v0Var.g("player.stall_class", str5);
                v0Var.g("player.stall_when", str6);
                sj2 sj2Var2 = sj2Var;
                v0Var.g("player.stuck_type", sj2Var2 != null ? String.valueOf((int) sj2Var2.l) : "unknown");
                if (str3 != null) {
                    v0Var.g("player.stuck_recovery", str3);
                    v0Var.l(s4.INFO);
                }
                this.l.q0(eg0Var, v0Var);
            }
        });
    }

    public final void i3() {
        int i;
        boolean z;
        zl0 zl0Var;
        ArrayList arrayListI = I();
        rs1 rs1VarG0 = g0();
        int iI0 = i0();
        byte b = 1;
        boolean z2 = iI0 >= 0 && rs1VarG0.t.size() >= 2;
        if (arrayListI.size() >= 2 || z2) {
            ArrayList arrayList = new ArrayList();
            if (z2) {
                int i2 = 0;
                while (i2 < rs1VarG0.t.size()) {
                    us1 us1Var = (us1) rs1VarG0.t.get(i2);
                    int[] iArr = (int[]) this.v3.get(Integer.valueOf(k6.V()));
                    String string = null;
                    for (ss1 ss1Var : us1Var.c) {
                        if (string == null || S1(ss1Var.a) > S1(string)) {
                            string = ss1Var.a;
                        }
                    }
                    if (i2 == iI0 && iArr != null && iArr[0] == i2) {
                        string = string == null ? getString(iArr[1]) : getString(iArr[1]) + " · " + string;
                    }
                    arrayList.add(new u70(us1Var.a, string, i2 == iI0, new wo1(this, i2, b)));
                    i2++;
                }
                z = false;
                i = 2;
                if (arrayListI.size() >= 2) {
                    arrayList.add(new u70(getString(R.string.audio_in_file)));
                }
            } else {
                i = 2;
                z = false;
            }
            for (ar1 ar1Var : (!z2 || arrayListI.size() >= i) ? arrayListI : Collections.EMPTY_LIST) {
                boolean z3 = ar1Var.g;
                boolean z4 = ar1Var.e;
                String str = ar1Var.a;
                String string2 = ar1Var.b;
                if (z3) {
                    boolean z5 = z;
                    if (z4 && (zl0Var = this.t3) != null) {
                        if (zl0Var.equals(ar1Var.c.d[ar1Var.d])) {
                            int i3 = this.u3;
                            string2 = string2 == null ? getString(i3) : getString(i3) + " · " + string2;
                        }
                    }
                    arrayList.add(new u70(str, string2, z4, new ld(this, ar1Var, arrayListI, (byte) 24)));
                    z = z5;
                } else {
                    u70 u70Var = new u70(str, string2 == null ? getString(R.string.notice_track_unsupported) : string2 + " · " + getString(R.string.notice_track_unsupported), z, new xo1(this, b));
                    u70Var.h = true;
                    arrayList.add(u70Var);
                }
            }
            s2.q(this, this.W1, new xo1(this, (byte) 3), getString(z2 ? R.string.audio_voices_title : R.string.audio_title), arrayList);
        }
    }

    public final vp2 i4(zl0 zl0Var, ArrayList arrayList, boolean z) {
        String str = zl0Var.d;
        String[] strArr = wt2.a;
        vp2 vp2Var = vp2.f;
        String strD = ha1.D(str);
        Iterator it = arrayList.iterator();
        int i = -1;
        int i2 = 0;
        while (it.hasNext()) {
            zl0 zl0Var2 = (zl0) it.next();
            if (i < 0 && zl0Var2.equals(zl0Var)) {
                i = i2;
            }
            if (Objects.equals(strD, ha1.D(zl0Var2.d))) {
                i2++;
            }
        }
        Integer numValueOf = z ? null : Integer.valueOf(arrayList.indexOf(zl0Var));
        if (strD == null) {
            strD = null;
        } else if (!z) {
            strD = zl0Var.d;
        }
        return new vp2(numValueOf, k4(zl0Var), Integer.valueOf(Math.max(0, i)), strD != null ? new String[]{strD} : null, Integer.valueOf(i2));
    }

    public final void j() {
        if (this.K0 == null) {
            return;
        }
        boolean zL4 = L4();
        this.K0.setOrientation(zL4 ? 1 : 0);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.m1.getLayoutParams();
        layoutParams.width = zL4 ? -1 : -2;
        this.m1.setLayoutParams(layoutParams);
        LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) this.n1.getLayoutParams();
        layoutParams2.width = zL4 ? -1 : 0;
        layoutParams2.weight = zL4 ? 0.0f : 1.0f;
        layoutParams2.topMargin = zL4 ? this.W1.b(4.0f) : 0;
        this.n1.setLayoutParams(layoutParams2);
        t4();
        q4();
    }

    public final SpannableStringBuilder j0(String str) {
        if (str == null || str.isEmpty()) {
            return null;
        }
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        HashMap map = this.U5;
        Boolean boolValueOf = (Boolean) map.get(str);
        if (boolValueOf == null) {
            boolValueOf = Boolean.FALSE;
            try {
                for (MediaCodecInfo mediaCodecInfo : new MediaCodecList(1).getCodecInfos()) {
                    if (mediaCodecInfo.getName().equals(str)) {
                        boolValueOf = Boolean.valueOf(!i1(mediaCodecInfo));
                        break;
                    }
                }
            } catch (RuntimeException e) {
                wt2.K("decoder kind: " + e);
            }
            map.put(str, boolValueOf);
        }
        d(spannableStringBuilder, getString(boolValueOf.booleanValue() ? R.string.stats_hardware : R.string.stats_software));
        f(spannableStringBuilder, str.replace(".decoder", ""));
        return spannableStringBuilder;
    }

    public final void j1(String str, String str2) {
        r0();
        po2 po2Var = this.X4;
        String str3 = this.H.F0;
        if (str3 == null || str3.isEmpty()) {
            str3 = Build.MODEL;
        }
        po2Var.h(str, str2, str3);
        po2Var.I = true;
        po2Var.l.e();
    }

    public final void j2(String str) {
        StringBuilder sbK = jf2.k("video freeze: ", str, " (");
        sbK.append(this.p0);
        sbK.append("/2)");
        wt2.K(sbK.toString());
        w3.b().q("Video frozen while audio plays", new fk((Object) this, (Object) str, (byte) 16));
    }

    public final void j3(eg0 eg0Var) {
        k3(v0(eg0Var), u0(eg0Var), eg0Var.l == 4001 ? I3(eg0Var.q) : null);
    }

    public final xp2 j4() {
        xp2 xp2Var = U6;
        if (xp2Var != null) {
            return xp2Var;
        }
        ArrayList arrayList = new ArrayList();
        HashMap map = new HashMap();
        HashMap map2 = new HashMap();
        try {
            InputStream inputStreamOpenRawResource = getResources().openRawResource(R.raw.voice_studios);
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                byte[] bArr = new byte[8192];
                while (true) {
                    int i = inputStreamOpenRawResource.read(bArr);
                    if (i <= 0) {
                        break;
                    }
                    byteArrayOutputStream.write(bArr, 0, i);
                    wt2.K("voice studios: " + e);
                    xp2 xp2Var2 = new xp2(arrayList, map, map2);
                    U6 = xp2Var2;
                    return xp2Var2;
                }
                JSONObject jSONObject = new JSONObject(byteArrayOutputStream.toString("UTF-8"));
                JSONArray jSONArray = jSONObject.getJSONArray("studios");
                for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                    arrayList.add(jSONArray.getString(i2));
                }
                JSONObject jSONObject2 = jSONObject.getJSONObject("aliases");
                Iterator<String> itKeys = jSONObject2.keys();
                while (itKeys.hasNext()) {
                    String next = itKeys.next();
                    JSONArray jSONArray2 = jSONObject2.getJSONArray(next);
                    ArrayList arrayList2 = new ArrayList();
                    for (int i3 = 0; i3 < jSONArray2.length(); i3++) {
                        arrayList2.add(jSONArray2.getString(i3));
                    }
                    map.put(next, arrayList2);
                }
                JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("languages");
                if (jSONObjectOptJSONObject != null) {
                    Iterator<String> itKeys2 = jSONObjectOptJSONObject.keys();
                    while (itKeys2.hasNext()) {
                        String next2 = itKeys2.next();
                        map2.put(next2, jSONObjectOptJSONObject.getString(next2));
                    }
                }
                inputStreamOpenRawResource.close();
            } catch (Throwable th) {
                if (inputStreamOpenRawResource != null) {
                    try {
                        inputStreamOpenRawResource.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                }
                throw th;
            }
        } catch (Exception e) {
            wt2.K("voice studios: " + e);
        }
        xp2 xp2Var3 = new xp2(arrayList, map, map2);
        U6 = xp2Var3;
        return xp2Var3;
    }

    public final void k() {
        int iF4;
        if (this.p != null && (iF4 = f4(1)) >= 0) {
            s41 s41Var = this.p.c;
            up2 up2Var = s41Var == null ? null : s41Var.c[iF4];
            int iB = (up2Var == null || this.A4 == null || ((zl0) this.w4.a) == null) ? -1 : up2Var.b(this.A4);
            s50 s50VarD = this.p.d();
            if (iB < 0) {
                SparseArray sparseArray = s50VarD.S;
                Map map = (Map) sparseArray.get(iF4);
                if (map != null && !map.isEmpty()) {
                    sparseArray.remove(iF4);
                }
            } else {
                s50VarD.q(iF4, up2Var, new u50(new int[]{this.B4}, iB));
            }
            s50VarD.p(iF4, this.I4);
            z50 z50Var = this.p;
            z50Var.getClass();
            z50Var.o(new t50(s50VarD));
        }
    }

    public final void k0() {
        if (k6 == null) {
            return;
        }
        U(null);
        if (this.L4 != null) {
            W();
            D4();
        }
        kk2 kk2Var = this.H4;
        if (kk2Var != null) {
            kk2Var.c();
        }
        kk2 kk2Var2 = this.t4;
        if (kk2Var2 != null) {
            kk2Var2.c();
        }
        this.I4 = true;
        int iF4 = this.p == null ? -1 : f4(1);
        if (iF4 < 0) {
            vg0 vg0Var = k6;
            t50 t50Var = (t50) vg0Var.A0();
            t50Var.getClass();
            s50 s50Var = new s50(t50Var);
            s50Var.d(3);
            s50Var.j(3, true);
            vg0Var.q0(s50Var.b());
            return;
        }
        s50 s50VarD = this.p.d();
        s50VarD.n();
        s50VarD.r();
        s50VarD.p(iF4, true);
        z50 z50Var = this.p;
        z50Var.getClass();
        z50Var.o(new t50(s50VarD));
    }

    public final void k2() {
        byte b;
        int i;
        float f;
        gu1 gu1Var = this.H;
        if (!gu1Var.y || gu1Var.C) {
            return;
        }
        Uri uriD0 = d0();
        String string = uriD0 != null ? uriD0.toString() : null;
        if (string == null || string.equals(this.P5)) {
            return;
        }
        Iterator it = b0().iterator();
        while (true) {
            b = 1;
            if (!it.hasNext()) {
                i = 0;
                f = 0.0f;
                break;
            }
            yp2 yp2Var = (yp2) it.next();
            if (yp2Var.c == 1) {
                f = yp2Var.d;
                if (f > 0.0f) {
                    i = yp2Var.e;
                    break;
                }
            }
        }
        if (f <= 0.0f) {
            return;
        }
        this.P5 = string;
        String[] strArr = wt2.a;
        runOnUiThread(new st2(this, f, i, b));
    }

    public final void k3(String str, String str2, String str3) {
        this.Y = this.H.c;
        c7 c7Var = this.m3;
        if (c7Var != null) {
            c7Var.e = str;
        }
        vg0 vg0Var = k6;
        if (vg0Var != null && m6 && vg0Var.x()) {
            this.H.z(k6.O0());
        }
        Intent intentPutExtra = new Intent(this, (Class<?>) ErrorActivity.class).putExtra("title", getString(R.string.error_report_title)).putExtra("summary", str).putExtra("report", str2);
        if (str3 != null) {
            intentPutExtra.putExtra("message", str3);
        }
        startActivity(intentPutExtra);
    }

    public final String k4(zl0 zl0Var) {
        String str = zl0Var.b;
        if (str != null && !str.isEmpty() && (str == null || !str.matches("[a-z]{3}\\d{1,2}"))) {
            return str;
        }
        String str2 = zl0Var.a;
        if (str2 != null) {
            return (String) this.s.get(str2);
        }
        return null;
    }

    public final void l(ho2 ho2Var, int i, int i2, ArrayList arrayList) {
        String str = ho2Var.a;
        boolean z = ho2Var.b;
        this.S3 = str;
        String str2 = (str == null || !str.equals(this.U3)) ? null : this.V3;
        this.T3 = str2;
        if (str2 == null && str != null) {
            Thread thread = new Thread(new up1(this, str, z), "TitleImdb");
            thread.setDaemon(true);
            thread.start();
        }
        this.W3 = z;
        this.X3 = i;
        this.Y3 = i2;
        vg0 vg0Var = k6;
        this.Z3 = vg0Var != null ? vg0Var.V() : -1;
        this.a4 = null;
        this.b4 = -1;
        if (arrayList != null) {
            ArrayList arrayList2 = new ArrayList(arrayList.size());
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                go2 go2Var = (go2) it.next();
                if (go2Var.a > 0) {
                    arrayList2.add(go2Var);
                }
            }
            this.a4 = arrayList2;
            for (int i3 = 0; i3 < arrayList2.size(); i3++) {
                if (((go2) arrayList2.get(i3)).a == i && ((go2) arrayList2.get(i3)).b == i2) {
                    this.b4 = i3;
                    break;
                }
            }
        }
        vg0 vg0Var2 = k6;
        if (vg0Var2 == null) {
            return;
        }
        if (!this.H.b0) {
            t1(vg0Var2.E(), true, null, this.E4);
            return;
        }
        boolean z2 = this.E4;
        String string = getString(R.string.subtitle_search_language_title);
        gu1 gu1Var = this.H;
        ArrayList arrayListD0 = wt2.d0(z2 ? gu1Var.Y : gu1Var.X);
        LinkedHashMap linkedHashMapB = wt2.b();
        ArrayList arrayList3 = new ArrayList(Arrays.asList(wt2.v()));
        for (ar1 ar1Var : I()) {
            String str3 = ar1Var.f;
            if (str3 != null && !arrayList3.contains(str3)) {
                arrayList3.add(ar1Var.f);
            }
        }
        t20.p(this, string, R.string.pref_language_subtitle_none, R.string.pref_language_audio_add, arrayListD0, linkedHashMapB, arrayList3, new ig0((byte) 4, this, z2));
    }

    public final void l1(int i, WindowInsets windowInsets) {
        int iA;
        if (windowInsets != null) {
            int i2 = Build.VERSION.SDK_INT;
            if (i2 >= 31) {
                boolean zIsVisible = windowInsets.isVisible(WindowInsets.Type.statusBars());
                boolean z = getResources().getConfiguration().orientation == 2;
                xo1 xo1Var = this.W4;
                if (!zIsVisible || (H6 && (!z || Q6))) {
                    this.B.removeCallbacks(xo1Var);
                } else {
                    this.B.postDelayed(xo1Var, 2500L);
                }
            }
            int systemWindowInsetLeft = windowInsets.getSystemWindowInsetLeft();
            int systemWindowInsetRight = windowInsets.getSystemWindowInsetRight();
            int iE = this.W1.e();
            int iMax = Math.max(Math.max(systemWindowInsetLeft, systemWindowInsetRight), this.W1.d());
            findViewById(R.id.exo_top).getLayoutParams().height = 0;
            boolean z2 = Q6;
            qs2 qs2Var = this.W1;
            if (z2) {
                iA = qs2Var.z() ? qs2Var.a(32.0f) : qs2Var.a(16.0f);
            } else {
                iA = iMax + (qs2Var.z() ? qs2Var.a(32.0f) : qs2Var.a(16.0f));
            }
            int i3 = i2 >= 30 ? windowInsets.getInsets(WindowInsets.Type.navigationBars()).bottom : 0;
            boolean z3 = Q6;
            qs2 qs2Var2 = this.W1;
            int iA2 = z3 ? qs2Var2.a(12.0f) : Math.max(qs2Var2.a(12.0f), Math.max(windowInsets.getStableInsetBottom(), i3));
            View viewFindViewById = findViewById(R.id.exo_bottom_bar);
            int iJ = this.W1.j();
            qs2 qs2Var3 = this.W1;
            int iA3 = qs2Var3.z() ? qs2Var3.a(12.0f) : qs2Var3.b(10.0f);
            int iJ2 = this.W1.j();
            qs2 qs2Var4 = this.W1;
            int iA4 = qs2Var4.z() ? qs2Var4.a(14.0f) : qs2Var4.b(4.0f);
            String[] strArr = wt2.a;
            viewFindViewById.setPadding(iJ, iA3, iJ2, iA4);
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) viewFindViewById.getLayoutParams();
            layoutParams.setMargins(iA, 0, iA, iA2);
            viewFindViewById.setLayoutParams(layoutParams);
            qs2 qs2Var5 = this.W1;
            int iG = qs2Var5.g() + (qs2Var5.z() ? qs2Var5.a(12.0f) : qs2Var5.b(4.0f)) + qs2Var5.l() + (qs2Var5.z() ? qs2Var5.a(12.0f) : qs2Var5.b(10.0f));
            int iA5 = this.W1.a(8.0f) + (qs2Var5.z() ? qs2Var5.a(14.0f) : qs2Var5.b(4.0f)) + iG + iA2;
            this.L0 = iA5;
            boolean z4 = Q6;
            qs2 qs2Var6 = this.W1;
            int iD = z4 ? qs2Var6.d() : iA + qs2Var6.j();
            if (i2 >= 35) {
                findViewById(R.id.exo_left).getLayoutParams().width = windowInsets.getInsets(WindowInsets.Type.navigationBars()).left;
                findViewById(R.id.exo_right).getLayoutParams().width = windowInsets.getInsets(WindowInsets.Type.navigationBars()).right;
            }
            int iMax2 = i2 >= 30 ? Math.max(windowInsets.getSystemWindowInsetTop(), windowInsets.getInsetsIgnoringVisibility(WindowInsets.Type.statusBars()).top) : windowInsets.getSystemWindowInsetTop();
            LinearLayout linearLayout = this.D0;
            linearLayout.setPadding(iD, iE + iMax2 + (Q6 ? 0 : this.W1.a(12.0f)), iD, i);
            FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) linearLayout.getLayoutParams();
            layoutParams2.setMargins(0, 0, 0, 0);
            linearLayout.setLayoutParams(layoutParams2);
            View childAt = this.E0.getChildAt(0);
            childAt.getLayoutParams().height = this.W1.p() + iMax2;
            childAt.setLayoutParams(childAt.getLayoutParams());
            Button button = this.f5;
            if (button != null) {
                yx yxVar = (yx) button.getLayoutParams();
                if (!L4()) {
                    ((ViewGroup.MarginLayoutParams) yxVar).bottomMargin = iA5;
                }
                ((ViewGroup.MarginLayoutParams) yxVar).rightMargin = iD;
                this.f5.setLayoutParams(yxVar);
            }
            TextView textView = this.t1;
            if (textView != null) {
                yx yxVar2 = (yx) textView.getLayoutParams();
                ((ViewGroup.MarginLayoutParams) yxVar2).bottomMargin = iA5;
                ((ViewGroup.MarginLayoutParams) yxVar2).leftMargin = iD;
                ((ViewGroup.MarginLayoutParams) yxVar2).rightMargin = iD;
                this.t1.setLayoutParams(yxVar2);
            }
            TextView textView2 = this.s1;
            if (textView2 != null) {
                yx yxVar3 = (yx) textView2.getLayoutParams();
                ((ViewGroup.MarginLayoutParams) yxVar3).rightMargin = iD;
                this.s1.setLayoutParams(yxVar3);
            }
            View viewFindViewById2 = findViewById(R.id.exo_error_message);
            int systemWindowInsetTop = windowInsets.getSystemWindowInsetTop() / 2;
            int systemWindowInsetBottom = (windowInsets.getSystemWindowInsetBottom() / 2) + getResources().getDimensionPixelSize(R.dimen.exo_error_message_margin_bottom);
            FrameLayout.LayoutParams layoutParams3 = (FrameLayout.LayoutParams) viewFindViewById2.getLayoutParams();
            layoutParams3.setMargins(0, systemWindowInsetTop, 0, systemWindowInsetBottom);
            viewFindViewById2.setLayoutParams(layoutParams3);
            windowInsets.consumeSystemWindowInsets();
        }
    }

    public final void l2(float f) {
        if (k6 == null) {
            return;
        }
        float fP0 = p0(f);
        this.K = fP0;
        k6.g(fP0);
    }

    public final void l3(String str) {
        ContextThemeWrapper contextThemeWrapperE = s2.e(this);
        ClipboardManager clipboardManager = (ClipboardManager) getSystemService("clipboard");
        if (clipboardManager != null) {
            clipboardManager.setPrimaryClip(ClipData.newPlainText("", str));
        }
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        Bitmap bitmapU = wt2.U((int) (Math.min(displayMetrics.widthPixels, displayMetrics.heightPixels) * 0.6f), str);
        if (bitmapU == null) {
            z3(getString(R.string.together_created, this.X4.f()), null, null);
            return;
        }
        ImageView imageView = new ImageView(contextThemeWrapperE);
        imageView.setImageBitmap(bitmapU);
        imageView.setAdjustViewBounds(true);
        int iRound = Math.round(displayMetrics.density * 16.0f);
        imageView.setPadding(iRound, iRound, iRound, iRound);
        s2.w(this, getString(R.string.together_qr_title, this.X4.f()), getString(R.string.together_qr_hint), imageView);
    }

    public final String[] l4(zl0 zl0Var, int i) {
        String string;
        String strK4 = k4(zl0Var);
        String strM1 = m1(zl0Var.d);
        if (strK4 == null || strK4.isEmpty()) {
            string = (strM1 == null || strM1.isEmpty()) ? getString(R.string.audio_track_number, Integer.valueOf(i)) : strM1;
        } else {
            string = strK4;
        }
        StringBuilder sb = new StringBuilder(zy.f(zl0Var, false));
        if (strK4 != null && !strK4.isEmpty() && strM1 != null && !strM1.isEmpty()) {
            if (sb.length() > 0) {
                sb.append(" · ");
            }
            sb.append(strM1);
        }
        return new String[]{string, sb.length() == 0 ? null : sb.toString()};
    }

    public final void m() {
        if (Build.VERSION.SDK_INT < 30) {
            this.B.setSystemUiVisibility(1284);
            return;
        }
        WindowInsetsController insetsController = getWindow() != null ? getWindow().getInsetsController() : null;
        if (insetsController != null) {
            insetsController.hide(WindowInsets.Type.statusBars());
            insetsController.show(WindowInsets.Type.navigationBars());
        }
    }

    public final String m0() {
        vg0 vg0Var = k6;
        if (vg0Var == null || vg0Var.E().a.isEmpty() || k6.E().a()) {
            return null;
        }
        for (yp2 yp2Var : b0()) {
            int i = yp2Var.c;
            String str = yp2Var.f;
            if (i == 1 && str != null) {
                return str.startsWith("V_") ? str.substring(2) : str;
            }
        }
        return null;
    }

    public final void m2() {
        this.V0 = false;
        this.k3 = false;
        this.l3 = null;
        this.m3 = null;
        this.n3 = null;
        this.p3 = null;
        this.q3 = null;
        this.r3.clear();
        this.s3.clear();
        this.w3 = null;
        this.x3 = null;
        this.O4 = false;
        this.P4 = false;
        this.y3 = null;
        this.z3 = null;
        this.A3 = null;
        this.B3 = null;
        this.C3.clear();
        this.D3.clear();
        this.E3 = 0;
        this.F3 = 0;
        this.G3 = null;
        this.u.clear();
        this.H3 = -1;
        this.I3 = -1;
        this.J3 = null;
        this.K3 = null;
        this.S3 = null;
        this.T3 = null;
        this.W3 = false;
        this.X3 = -1;
        this.Y3 = -1;
        this.Z3 = -1;
        this.a4 = null;
        this.b4 = -1;
        this.L3.clear();
        this.M3.clear();
        this.N3.clear();
        this.O3.clear();
        this.P3.clear();
        this.Q3.clear();
        this.R3.clear();
        this.d4.clear();
        this.e4 = new LinkedHashMap();
        this.f4 = (byte) 0;
        this.g4 = null;
        this.h4 = -1;
        this.i4 = 0;
        this.j4 = null;
        this.v3.clear();
        this.N4.clear();
        this.H.S0 = true;
        ge2 ge2Var = this.d5;
        if (ge2Var != null) {
            ge2Var.a = null;
            List list = Collections.EMPTY_LIST;
            ge2Var.b = list;
            ge2Var.c = list;
        }
        this.e5 = false;
        this.v5 = false;
        this.y5 = false;
        this.C5 = -9223372036854775807L;
        this.D5 = -9223372036854775807L;
        this.E5 = -9223372036854775807L;
        this.F5 = -9223372036854775807L;
        this.I5 = -9223372036854775807L;
        N();
        P();
        W0();
        this.k4 = 0.0d;
        this.o4 = null;
        this.l4 = null;
        this.m4 = null;
        u();
        this.p4 = false;
        Dialog dialog = this.M1;
        if (dialog != null && dialog.isShowing()) {
            this.M1.dismiss();
        }
        Dialog dialog2 = this.N1;
        if (dialog2 != null && dialog2.isShowing()) {
            this.N1.dismiss();
        }
        this.q4 = 0.0d;
        kk2 kk2Var = this.H4;
        if (kk2Var != null) {
            kk2Var.e(0.0d);
        }
        W();
        this.I4 = false;
        Dialog dialog3 = this.O1;
        if (dialog3 != null && dialog3.isShowing()) {
            this.O1.dismiss();
        }
        CustomDefaultTimeBar customDefaultTimeBar = this.y2;
        if (customDefaultTimeBar != null) {
            customDefaultTimeBar.h();
        }
    }

    public final void m3(long j) {
        vg0 vg0Var = k6;
        long duration = vg0Var != null ? vg0Var.getDuration() : -9223372036854775807L;
        StringBuilder sb = new StringBuilder(wt2.t(j));
        if (duration > 0) {
            sb.append(" · ");
            sb.append(Math.round((j * 100.0f) / duration));
            sb.append('%');
        }
        cz czVar = this.B;
        long j2 = j - czVar.n0;
        czVar.u(j2 < 0 ? R.drawable.ic_rewind_24dp : R.drawable.ic_fast_forward_24dp, wt2.u(j2) + "\n" + ((Object) sb));
    }

    public final List m4(String str) {
        if (!this.H.c0) {
            return Collections.EMPTY_LIST;
        }
        zk2[] zk2VarArr = al2.a;
        ArrayList arrayList = new ArrayList(2);
        if ("ukr".equals(str)) {
            arrayList.add("rus");
        }
        if (!"eng".equals(str)) {
            arrayList.add("eng");
        }
        return arrayList;
    }

    public final void n(int i, CharSequence charSequence) {
        Drawable drawable;
        this.r5 = true;
        this.f5.setText(charSequence);
        Button button = this.f5;
        if (i == 3) {
            drawable = this.h5;
        } else {
            drawable = i == 4 ? this.i5 : this.g5;
        }
        Drawable drawable2 = drawable;
        if ("ring".equals(this.H.r0)) {
            if (this.j5 == null || this.h6 != i) {
                int iP = wt2.p(28);
                fr1 fr1Var = new fr1(drawable2, sj.n(this, R.attr.accentSkip, sj.n(this, R.attr.colorPrimary, -1)), this.H0.e, wt2.p(2), wt2.p(6));
                this.j5 = fr1Var;
                fr1Var.setBounds(0, 0, iP, iP);
                this.h6 = i;
            }
            drawable2 = this.j5;
        } else {
            this.j5 = null;
            this.h6 = 0;
            if (i == 4 && drawable2 != null) {
                int i2 = this.m5;
                drawable2.setBounds(0, 0, i2, i2);
            } else if (i != 4) {
                drawable2 = null;
            }
        }
        button.setCompoundDrawablesRelative(drawable2, null, null, null);
        this.f5.setClickable(true);
        this.f5.setFocusable(true);
    }

    public final String n0(int i) {
        hl hlVarV1 = v1(i);
        String str = (String) hlVarV1.o;
        String str2 = (String) hlVarV1.n;
        if (str2 != null) {
            return "imdb:" + str2;
        }
        if (str == null) {
            return null;
        }
        return "tmdb:" + str;
    }

    public final void n1(String str, String str2, vp2 vp2Var, ArrayList arrayList) {
        if (vp2Var == null) {
            return;
        }
        String str3 = vp2Var.b;
        Integer num = vp2Var.a;
        if (num != null && num.intValue() >= 0) {
            wt2.K(str + "_index " + num + " (" + str2 + "): " + j4().e(arrayList, vp2Var));
        }
        if (str3 != null) {
            wt2.K(str + "_label \"" + str3 + "\" (" + str2 + "): not in the file");
        }
    }

    public final boolean n2() {
        cz czVar = this.B;
        wp1 wp1Var = this.h2;
        czVar.removeCallbacks(wp1Var);
        cz czVar2 = this.B;
        wp1 wp1Var2 = this.k2;
        czVar2.removeCallbacks(wp1Var2);
        vg0 vg0Var = k6;
        boolean z = vg0Var != null && vg0Var.J();
        gu1 gu1Var = this.H;
        boolean z2 = (gu1Var == null || gu1Var.K <= 0 || !m6 || d1() || this.b5) ? false : true;
        if (z || z2) {
            getWindow().addFlags(128);
        } else {
            getWindow().clearFlags(128);
        }
        if (z2 && !z) {
            this.B.postDelayed(wp1Var2, ((long) this.H.K) * 60000);
        }
        View view = this.g2;
        if (view == null) {
            return false;
        }
        boolean z3 = view.getVisibility() == 0;
        if (z3) {
            this.g2.animate().cancel();
            this.g2.animate().alpha(0.0f).setDuration(300L).withEndAction(new dp1(this, (byte) 28));
        }
        if (z2 && !z) {
            this.B.postDelayed(wp1Var, 60000L);
        }
        return z3;
    }

    public final void n3() {
        int iV = k6.V();
        boolean z = iV < this.O3.size();
        Uri uri = z ? (Uri) this.O3.get(iV) : null;
        Uri uri2 = z ? (Uri) this.P3.get(iV) : null;
        CharSequence charSequence = iV < this.C3.size() ? ((z81) this.C3.get(iV)).d.a : null;
        if ((!"first".equals(this.H.D0) && !"every".equals(this.H.D0)) || (uri == null && (uri2 == null || TextUtils.isEmpty(charSequence)))) {
            this.T0.c();
            return;
        }
        this.B.c();
        f91 f91Var = this.T0;
        im2 im2Var = f91Var.B;
        ImageView imageView = f91Var.l;
        View view = f91Var.m;
        f91Var.animate().cancel();
        f91Var.setAlpha(1.0f);
        f91Var.setVisibility(0);
        f91Var.u.setVisibility(8);
        ValueAnimator valueAnimator = f91Var.w;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        f91Var.y = 0;
        f91Var.z = 0;
        f91Var.e(false);
        f91Var.t = charSequence;
        if (uri2 != null) {
            view.setVisibility(0);
            r02 r02VarD = com.bumptech.glide.a.d(f91Var.getContext().getApplicationContext());
            r02VarD.getClass();
            new k02(r02VarD.l, r02VarD, Drawable.class, r02VarD.m).z(uri2).x(imageView);
        } else {
            com.bumptech.glide.a.d(f91Var.getContext().getApplicationContext()).m(imageView);
            view.setVisibility(8);
        }
        if (uri != null) {
            f91Var.f(false);
            r02 r02VarD2 = com.bumptech.glide.a.d(f91Var.getContext().getApplicationContext());
            r02VarD2.getClass();
            k02 k02VarZ = new k02(r02VarD2.l, r02VarD2, Bitmap.class, r02VarD2.m).a(r02.v).z(uri);
            k02VarZ.w(im2Var, k02VarZ);
        } else {
            com.bumptech.glide.a.d(f91Var.getContext().getApplicationContext()).l(im2Var);
            f91Var.f(true);
        }
        if (!wt2.C(f91Var.getContext())) {
            f91Var.v.start();
        }
        this.e1 = false;
        this.d1 = false;
        this.Z0 = false;
        this.Y0 = false;
        this.a1 = 0L;
        this.c1 = 0;
        this.f1 = aq2.p.get();
        this.B.removeCallbacks(this.h1);
        this.B.post(this.h1);
    }

    public final u70 n4(ir1 ir1Var) {
        String string;
        String[] strArrL4 = l4(ir1Var.a, ir1Var.d);
        if (strArrL4[1] == null) {
            string = getString(R.string.notice_track_unsupported);
        } else {
            string = strArrL4[1] + " · " + getString(R.string.notice_track_unsupported);
        }
        u70 u70Var = new u70(strArrL4[0], string, false, new dp1(this, (byte) 4));
        u70Var.h = true;
        return u70Var;
    }

    public final void o(float f) {
        this.k5 = f;
        fr1 fr1Var = this.j5;
        if (fr1Var != null) {
            float fMax = Math.max(0.0f, Math.min(1.0f, f));
            if (fMax != fr1Var.e) {
                fr1Var.e = fMax;
                fr1Var.invalidateSelf();
            }
        }
    }

    public final boolean o0() {
        Boolean bool = this.l4;
        return bool != null ? bool.booleanValue() : this.H.P;
    }

    public final String o1() {
        Uri uri = this.L4;
        if (uri == null) {
            uri = this.H.e;
        }
        if (uri != null) {
            String strO = ij0.o(uri);
            String[] strArr = wt2.a;
            vp2 vp2Var = vp2.f;
            String strD = ha1.D(strO);
            if (strD != null) {
                return strD;
            }
        }
        vg0 vg0Var = k6;
        if (vg0Var == null) {
            return null;
        }
        nw0 nw0VarN = vg0Var.E().a.listIterator(0);
        while (nw0VarN.hasNext()) {
            mq2 mq2Var = (mq2) nw0VarN.next();
            if (mq2Var.b.c == 3) {
                for (int i = 0; i < mq2Var.a; i++) {
                    if (mq2Var.e[i]) {
                        String str = mq2Var.a(i).d;
                        String[] strArr2 = wt2.a;
                        vp2 vp2Var2 = vp2.f;
                        return ha1.D(str);
                    }
                }
            }
        }
        return null;
    }

    public final void o2() {
        vg0 vg0Var;
        if (m6 && (vg0Var = k6) != null && vg0Var.J()) {
            this.B.setControllerShowTimeoutMs(3500);
        }
    }

    public final void o3(CharSequence charSequence, boolean z, int i) {
        cz czVar = this.B;
        if (czVar != null) {
            czVar.u(0, null);
        }
        ze2 ze2VarZ = y61.z(this, charSequence, z, i);
        if (ze2VarZ == null) {
            Toast.makeText(this, charSequence, z ? 1 : 0).show();
        } else {
            ze2VarZ.h();
        }
    }

    public final void o4() {
        if (this.H1 == null || this.G1) {
            return;
        }
        rs1 rs1VarG0 = g0();
        int iI0 = i0();
        this.z1 = k6 != null && (I().size() >= 2 || (iI0 >= 0 && rs1VarG0.t.size() >= 2));
        q();
        zl0 zl0VarJ0 = J0();
        String strK4 = zl0VarJ0 != null ? k4(zl0VarJ0) : null;
        String strM1 = zl0VarJ0 != null ? m1(zl0VarJ0.d) : null;
        if (iI0 >= 0) {
            strK4 = ((us1) rs1VarG0.t.get(iI0)).a;
        } else if (strK4 == null || strK4.isEmpty()) {
            strK4 = strM1 != null ? strM1 : getString(R.string.button_audio_track);
        }
        this.B1 = strK4;
        this.H1.setText(B2() ? null : this.B1);
    }

    @Override // android.app.Activity
    public final void onActivityResult(int i, int i2, Intent intent) {
        u2();
        if (i2 == -1 && this.Z2) {
            b2(true);
        }
        if (i == 10) {
            if (i2 == -1) {
                Uri data = intent.getData();
                try {
                    getContentResolver().takePersistableUriPermission(data, 3);
                    gu1 gu1Var = this.H;
                    gu1Var.g = data;
                    SharedPreferences.Editor editorEdit = gu1Var.b.edit();
                    if (data == null) {
                        editorEdit.remove("scopeUri");
                    } else {
                        editorEdit.putString("scopeUri", data.toString());
                    }
                    editorEdit.apply();
                    gu1 gu1Var2 = this.H;
                    gu1Var2.s = false;
                    SharedPreferences.Editor editorEdit2 = gu1Var2.b.edit();
                    editorEdit2.putBoolean("askScope", false);
                    editorEdit2.apply();
                    I2();
                } catch (SecurityException e) {
                    e.printStackTrace();
                }
            }
        } else if (i == 100) {
            HashMap map = this.B0;
            this.B0 = null;
            this.H.p();
            M6 = this.H.E0;
            p();
            w4();
            vg0 vg0Var = k6;
            if (vg0Var != null) {
                vg0Var.f(M6 ? 1.0f : Math.min(N6, 100.0f) / 100.0f);
            }
            G4(this);
            x4();
            C4();
            n2();
            HashMap mapV = this.H.v();
            if (this.I0) {
                return;
            }
            if (k6 != null && (map == null || !map.equals(mapV))) {
                this.M4 = true;
                b2(true);
                Z0();
            }
        } else {
            super.onActivityResult(i, i2, intent);
        }
        if (i2 == -1 && this.Z2) {
            Z0();
        }
    }

    @Override // android.app.Activity, android.view.ContextThemeWrapper
    public final void onApplyThemeResource(Resources.Theme theme, int i, boolean z) {
        super.onApplyThemeResource(theme, i, z);
        theme.applyStyle(gu1.a(this, false), true);
    }

    /* JADX WARN: Code restructure failed: missing block: B:31:0x007d, code lost:
    
        if (r13.H.J == false) goto L32;
     */
    @Override // android.app.Activity
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void onBackPressed() {
        /*
            r13 = this;
            long r0 = android.os.SystemClock.elapsedRealtime()
            boolean r2 = com.brouken.player.PlayerActivity.R6
            r3 = 2131230958(0x7f0800ee, float:1.8077983E38)
            r4 = 2131952365(0x7f1302ed, float:1.954117E38)
            r5 = 3000(0xbb8, double:1.482E-320)
            if (r2 == 0) goto L27
            long r7 = r13.e2
            long r7 = r0 - r7
            int r7 = (r7 > r5 ? 1 : (r7 == r5 ? 0 : -1))
            if (r7 <= 0) goto L27
            r13.e2 = r0
            r13.A3()
            cz r0 = r13.B
            java.lang.String r13 = r13.getString(r4)
            defpackage.s2.D(r0, r13, r3, r5)
            return
        L27:
            boolean r7 = com.brouken.player.PlayerActivity.Q6
            r8 = 0
            if (r7 == 0) goto L9d
            boolean r7 = com.brouken.player.PlayerActivity.m6
            if (r7 == 0) goto L9d
            if (r2 != 0) goto L9d
            long r9 = r13.f2
            long r9 = r0 - r9
            int r2 = (r9 > r5 ? 1 : (r9 == r5 ? 0 : -1))
            if (r2 <= 0) goto L9d
            long r9 = r13.I2
            r11 = 0
            int r2 = (r9 > r11 ? 1 : (r9 == r11 ? 0 : -1))
            if (r2 < 0) goto L6f
            long r9 = r13.J2
            int r2 = (r9 > r11 ? 1 : (r9 == r11 ? 0 : -1))
            if (r2 < 0) goto L4a
            r2 = 1
            goto L4b
        L4a:
            r2 = r8
        L4b:
            r13.L()
            if (r2 == 0) goto L60
            vg0 r2 = com.brouken.player.PlayerActivity.k6
            if (r2 == 0) goto L60
            a92 r7 = defpackage.a92.c
            r2.t1(r7)
            vg0 r2 = com.brouken.player.PlayerActivity.k6
            long r9 = r13.K2
            r2.o1(r9)
        L60:
            cz r2 = r13.B
            bz r7 = r2.C0
            r2.removeCallbacks(r7)
            cz r2 = r13.B
            bz r2 = r2.C0
            r2.run()
            goto L7f
        L6f:
            boolean r2 = com.brouken.player.PlayerActivity.H6
            if (r2 == 0) goto L79
            cz r2 = r13.B
            r2.c()
            goto L7f
        L79:
            gu1 r2 = r13.H
            boolean r2 = r2.J
            if (r2 != 0) goto L9d
        L7f:
            r13.f2 = r0
            gu1 r0 = r13.H
            boolean r0 = r0.J
            if (r0 != 0) goto L9c
            f91 r0 = r13.T0
            boolean r0 = r0.isShown()
            if (r0 == 0) goto L93
            defpackage.y61.O(r13, r4, r8, r3)
            return
        L93:
            cz r0 = r13.B
            java.lang.String r13 = r13.getString(r4)
            defpackage.s2.D(r0, r13, r3, r5)
        L9c:
            return
        L9d:
            r13.B2 = r8
            super.onBackPressed()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.brouken.player.PlayerActivity.onBackPressed():void");
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        F4(configuration.orientation);
        p();
        j();
        if (H6 && !this.U1 && !this.G) {
            wt2.i0(this, this.B, true);
        }
        qs2 qs2Var = new qs2(this, Q6);
        qs2 qs2Var2 = this.W1;
        if (qs2Var2 != null && qs2Var.a == qs2Var2.a && qs2Var.d == qs2Var2.d && qs2Var.e == qs2Var2.e) {
            return;
        }
        this.W1 = qs2Var;
        Dialog[] dialogArr = {this.K1, this.L1, this.M1, this.O1, this.N1, this.P1, s2.a};
        for (int i = 0; i < 7; i++) {
            Dialog dialog = dialogArr[i];
            if (dialog != null && dialog.isShowing()) {
                dialog.dismiss();
            }
        }
        vr1 vr1Var = this.x2;
        if (vr1Var != null) {
            vr1Var.requestApplyInsets();
        }
    }

    /* JADX WARN: Code duplicated, block: B:27:0x009f  */
    @Override // android.app.Activity
    public final void onCreate(Bundle bundle) {
        Intent intent;
        Bundle bundle2;
        boolean z;
        Window window;
        PlayerActivity playerActivity = l6;
        if (playerActivity == null || playerActivity == this || playerActivity.isFinishing()) {
            intent = null;
            bundle2 = null;
        } else {
            intent = l6.getIntent();
            bundle2 = new Bundle();
            l6.C2(bundle2);
            l6.E2(bundle2);
            l6.N0();
        }
        gu1 gu1Var = new gu1(this);
        this.H = gu1Var;
        M6 = gu1Var.E0;
        N6 = gu1Var.q;
        K6 = 0.0f;
        final byte b = 0;
        L6 = false;
        if (getIntent().getData() != null || "android.intent.action.SEND".equals(getIntent().getAction()) || vs1.b(getIntent())) {
            wt2.b0(this, this.H.U0);
        } else {
            setRequestedOrientation(-1);
        }
        super.onCreate(bundle);
        int i = Build.VERSION.SDK_INT;
        if (i == 28 && Build.MANUFACTURER.equalsIgnoreCase("xiaomi")) {
            String str = Build.DEVICE;
            if (str.equalsIgnoreCase("oneday") || str.equalsIgnoreCase("once")) {
                setContentView(R.layout.activity_player_textureview);
            } else {
                setContentView(R.layout.activity_player);
            }
        } else {
            setContentView(R.layout.activity_player);
        }
        final byte b2 = 1;
        if (i >= 31 && (window = getWindow()) != null) {
            window.setDecorFitsSystemWindows(false);
            WindowInsetsController insetsController = window.getInsetsController();
            if (insetsController != null) {
                insetsController.setSystemBarsBehavior(1);
            }
        }
        boolean zF = wt2.F(this);
        Q6 = zF;
        this.W1 = qs2.c(this, zF);
        this.H0 = new xr(this, false);
        this.J0 = new xr(this, true);
        this.T0 = new f91(this);
        ((ViewGroup) findViewById(android.R.id.content)).addView(this.T0);
        this.U0 = bundle == null ? 1 : 0;
        if (bundle == null) {
            q6 = false;
        }
        final byte b3 = 2;
        if (i >= 33) {
            getOnBackInvokedDispatcher().registerOnBackInvokedCallback(0, new z7(this, b3));
        }
        Intent intent2 = getIntent();
        String action = intent2.getAction();
        String type = intent2.getType();
        if (!O0(intent2)) {
            if ("android.intent.action.SEND".equals(action) && "text/plain".equals(type)) {
                String stringExtra = intent2.getStringExtra("android.intent.extra.TEXT");
                if (stringExtra != null) {
                    Uri uri = Uri.parse(stringExtra);
                    if (uri.isAbsolute()) {
                        this.H.x(this, uri, null);
                        P6 = true;
                    }
                }
            } else if (intent2.getData() != null || vs1.b(intent2)) {
                Q0(intent2);
            } else if (intent != null && (intent.getData() != null || vs1.b(intent))) {
                setIntent(intent);
                Q0(intent);
            } else if (e1(intent2)) {
                this.H.d = true;
            }
        }
        t2(bundle != null ? bundle : bundle2);
        if (bundle != null) {
            bundle2 = bundle;
        }
        v2(bundle2);
        this.C0 = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);
        this.g2 = findViewById(R.id.dim_overlay);
        this.n = (AudioManager) getSystemService("audio");
        cz czVar = (cz) findViewById(R.id.video_view);
        this.B = czVar;
        View viewFindViewById = czVar.findViewById(R.id.subtitle_secondary);
        viewFindViewById.addOnLayoutChangeListener(new View.OnLayoutChangeListener(this) { // from class: zp1
            public final /* synthetic */ PlayerActivity b;

            {
                this.b = this;
            }

            @Override // android.view.View.OnLayoutChangeListener
            public final void onLayoutChange(View view, int i2, int i3, int i4, int i5, int i7, int i8, int i9, int i10) {
                byte b4 = b2;
                PlayerActivity playerActivity2 = this.b;
                switch (b4) {
                    case DecoderInputBuffer.BUFFER_REPLACEMENT_MODE_DISABLED /* 0 */:
                        LoudnessEnhancer loudnessEnhancer = PlayerActivity.i6;
                        playerActivity2.D0();
                        View view2 = (View) playerActivity2.l1.getParent();
                        int iB = playerActivity2.W1.b(2.0f);
                        if (view2 != null && playerActivity2.l1.getVisibility() == 0 && playerActivity2.p1.getVisibility() == 0) {
                            View view3 = playerActivity2.S0.getVisibility() == 0 ? playerActivity2.S0 : playerActivity2.R0;
                            int bottom = view3.getBottom() + ((ViewGroup.MarginLayoutParams) view3.getLayoutParams()).bottomMargin;
                            iB = Math.max(view3.getBottom() - bottom, (((playerActivity2.p1.getBaseline() + (playerActivity2.p1.getTop() + ((View) playerActivity2.p1.getParent()).getTop())) - view2.getTop()) - playerActivity2.l1.getBaseline()) - bottom);
                        }
                        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) playerActivity2.l1.getLayoutParams();
                        if (marginLayoutParams.topMargin != iB) {
                            marginLayoutParams.topMargin = iB;
                            playerActivity2.l1.setLayoutParams(marginLayoutParams);
                        }
                        break;
                    case 1:
                        LoudnessEnhancer loudnessEnhancer2 = PlayerActivity.i6;
                        if (i5 - i3 != i10 - i8) {
                            playerActivity2.H4();
                        }
                        break;
                    case 2:
                        LoudnessEnhancer loudnessEnhancer3 = PlayerActivity.i6;
                        if (playerActivity2.s1 != null) {
                            ViewGroup viewGroup = (ViewGroup) view;
                            int iB2 = playerActivity2.W1.b(8.0f) + viewGroup.getChildAt(viewGroup.getChildCount() - 1).getBottom() + i3;
                            yx yxVar = (yx) playerActivity2.s1.getLayoutParams();
                            if (((ViewGroup.MarginLayoutParams) yxVar).topMargin != iB2) {
                                ((ViewGroup.MarginLayoutParams) yxVar).topMargin = iB2;
                                playerActivity2.s1.setLayoutParams(yxVar);
                            }
                            break;
                        }
                        break;
                    case VideoDecoderOutputBuffer.COLORSPACE_BT2020 /* 3 */:
                        LoudnessEnhancer loudnessEnhancer4 = PlayerActivity.i6;
                        playerActivity2.e4();
                        break;
                    case 4:
                        LoudnessEnhancer loudnessEnhancer5 = PlayerActivity.i6;
                        if (i4 - i2 != i9 - i7) {
                            playerActivity2.F1 = true;
                        }
                        break;
                    case 5:
                        LoudnessEnhancer loudnessEnhancer6 = PlayerActivity.i6;
                        if (i5 - i3 != i10 - i8 && playerActivity2.L4()) {
                            playerActivity2.E4();
                            break;
                        }
                        break;
                    default:
                        LoudnessEnhancer loudnessEnhancer7 = PlayerActivity.i6;
                        if (i5 - i3 != playerActivity2.x4) {
                            playerActivity2.E4();
                        }
                        break;
                }
            }
        });
        this.r4 = new l82((TextView) viewFindViewById, new wp1(this, (byte) 7), this.G4);
        final byte b4 = 5;
        this.B.findViewById(R.id.exo_content_frame).addOnLayoutChangeListener(new View.OnLayoutChangeListener(this) { // from class: zp1
            public final /* synthetic */ PlayerActivity b;

            {
                this.b = this;
            }

            @Override // android.view.View.OnLayoutChangeListener
            public final void onLayoutChange(View view, int i2, int i3, int i4, int i5, int i7, int i8, int i9, int i10) {
                byte b5 = b4;
                PlayerActivity playerActivity2 = this.b;
                switch (b5) {
                    case DecoderInputBuffer.BUFFER_REPLACEMENT_MODE_DISABLED /* 0 */:
                        LoudnessEnhancer loudnessEnhancer = PlayerActivity.i6;
                        playerActivity2.D0();
                        View view2 = (View) playerActivity2.l1.getParent();
                        int iB = playerActivity2.W1.b(2.0f);
                        if (view2 != null && playerActivity2.l1.getVisibility() == 0 && playerActivity2.p1.getVisibility() == 0) {
                            View view3 = playerActivity2.S0.getVisibility() == 0 ? playerActivity2.S0 : playerActivity2.R0;
                            int bottom = view3.getBottom() + ((ViewGroup.MarginLayoutParams) view3.getLayoutParams()).bottomMargin;
                            iB = Math.max(view3.getBottom() - bottom, (((playerActivity2.p1.getBaseline() + (playerActivity2.p1.getTop() + ((View) playerActivity2.p1.getParent()).getTop())) - view2.getTop()) - playerActivity2.l1.getBaseline()) - bottom);
                        }
                        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) playerActivity2.l1.getLayoutParams();
                        if (marginLayoutParams.topMargin != iB) {
                            marginLayoutParams.topMargin = iB;
                            playerActivity2.l1.setLayoutParams(marginLayoutParams);
                        }
                        break;
                    case 1:
                        LoudnessEnhancer loudnessEnhancer2 = PlayerActivity.i6;
                        if (i5 - i3 != i10 - i8) {
                            playerActivity2.H4();
                        }
                        break;
                    case 2:
                        LoudnessEnhancer loudnessEnhancer3 = PlayerActivity.i6;
                        if (playerActivity2.s1 != null) {
                            ViewGroup viewGroup = (ViewGroup) view;
                            int iB2 = playerActivity2.W1.b(8.0f) + viewGroup.getChildAt(viewGroup.getChildCount() - 1).getBottom() + i3;
                            yx yxVar = (yx) playerActivity2.s1.getLayoutParams();
                            if (((ViewGroup.MarginLayoutParams) yxVar).topMargin != iB2) {
                                ((ViewGroup.MarginLayoutParams) yxVar).topMargin = iB2;
                                playerActivity2.s1.setLayoutParams(yxVar);
                            }
                            break;
                        }
                        break;
                    case VideoDecoderOutputBuffer.COLORSPACE_BT2020 /* 3 */:
                        LoudnessEnhancer loudnessEnhancer4 = PlayerActivity.i6;
                        playerActivity2.e4();
                        break;
                    case 4:
                        LoudnessEnhancer loudnessEnhancer5 = PlayerActivity.i6;
                        if (i4 - i2 != i9 - i7) {
                            playerActivity2.F1 = true;
                        }
                        break;
                    case 5:
                        LoudnessEnhancer loudnessEnhancer6 = PlayerActivity.i6;
                        if (i5 - i3 != i10 - i8 && playerActivity2.L4()) {
                            playerActivity2.E4();
                            break;
                        }
                        break;
                    default:
                        LoudnessEnhancer loudnessEnhancer7 = PlayerActivity.i6;
                        if (i5 - i3 != playerActivity2.x4) {
                            playerActivity2.E4();
                        }
                        break;
                }
            }
        });
        View viewFindViewById2 = this.B.findViewById(R.id.exo_subtitles);
        final byte b5 = 6;
        if (viewFindViewById2 != null) {
            viewFindViewById2.addOnLayoutChangeListener(new View.OnLayoutChangeListener(this) { // from class: zp1
                public final /* synthetic */ PlayerActivity b;

                {
                    this.b = this;
                }

                @Override // android.view.View.OnLayoutChangeListener
                public final void onLayoutChange(View view, int i2, int i3, int i4, int i5, int i7, int i8, int i9, int i10) {
                    byte b6 = b5;
                    PlayerActivity playerActivity2 = this.b;
                    switch (b6) {
                        case DecoderInputBuffer.BUFFER_REPLACEMENT_MODE_DISABLED /* 0 */:
                            LoudnessEnhancer loudnessEnhancer = PlayerActivity.i6;
                            playerActivity2.D0();
                            View view2 = (View) playerActivity2.l1.getParent();
                            int iB = playerActivity2.W1.b(2.0f);
                            if (view2 != null && playerActivity2.l1.getVisibility() == 0 && playerActivity2.p1.getVisibility() == 0) {
                                View view3 = playerActivity2.S0.getVisibility() == 0 ? playerActivity2.S0 : playerActivity2.R0;
                                int bottom = view3.getBottom() + ((ViewGroup.MarginLayoutParams) view3.getLayoutParams()).bottomMargin;
                                iB = Math.max(view3.getBottom() - bottom, (((playerActivity2.p1.getBaseline() + (playerActivity2.p1.getTop() + ((View) playerActivity2.p1.getParent()).getTop())) - view2.getTop()) - playerActivity2.l1.getBaseline()) - bottom);
                            }
                            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) playerActivity2.l1.getLayoutParams();
                            if (marginLayoutParams.topMargin != iB) {
                                marginLayoutParams.topMargin = iB;
                                playerActivity2.l1.setLayoutParams(marginLayoutParams);
                            }
                            break;
                        case 1:
                            LoudnessEnhancer loudnessEnhancer2 = PlayerActivity.i6;
                            if (i5 - i3 != i10 - i8) {
                                playerActivity2.H4();
                            }
                            break;
                        case 2:
                            LoudnessEnhancer loudnessEnhancer3 = PlayerActivity.i6;
                            if (playerActivity2.s1 != null) {
                                ViewGroup viewGroup = (ViewGroup) view;
                                int iB2 = playerActivity2.W1.b(8.0f) + viewGroup.getChildAt(viewGroup.getChildCount() - 1).getBottom() + i3;
                                yx yxVar = (yx) playerActivity2.s1.getLayoutParams();
                                if (((ViewGroup.MarginLayoutParams) yxVar).topMargin != iB2) {
                                    ((ViewGroup.MarginLayoutParams) yxVar).topMargin = iB2;
                                    playerActivity2.s1.setLayoutParams(yxVar);
                                }
                                break;
                            }
                            break;
                        case VideoDecoderOutputBuffer.COLORSPACE_BT2020 /* 3 */:
                            LoudnessEnhancer loudnessEnhancer4 = PlayerActivity.i6;
                            playerActivity2.e4();
                            break;
                        case 4:
                            LoudnessEnhancer loudnessEnhancer5 = PlayerActivity.i6;
                            if (i4 - i2 != i9 - i7) {
                                playerActivity2.F1 = true;
                            }
                            break;
                        case 5:
                            LoudnessEnhancer loudnessEnhancer6 = PlayerActivity.i6;
                            if (i5 - i3 != i10 - i8 && playerActivity2.L4()) {
                                playerActivity2.E4();
                                break;
                            }
                            break;
                        default:
                            LoudnessEnhancer loudnessEnhancer7 = PlayerActivity.i6;
                            if (i5 - i3 != playerActivity2.x4) {
                                playerActivity2.E4();
                            }
                            break;
                    }
                }
            });
        }
        TextView textView = (TextView) findViewById(R.id.exo_error_message);
        textView.setBackgroundTintList(ColorStateList.valueOf(this.J0.d));
        textView.setTextColor(this.J0.e);
        textView.setCompoundDrawableTintList(ColorStateList.valueOf(this.J0.e));
        ImageButton imageButton = (ImageButton) findViewById(R.id.exo_play_pause);
        this.o2 = imageButton;
        imageButton.setBackground(new InsetDrawable((Drawable) wt2.c0(this.H0.b, 10000.0f), this.W1.b(10.0f)));
        ViewGroup.LayoutParams layoutParams = this.o2.getLayoutParams();
        layoutParams.width = this.W1.b(90.0f);
        layoutParams.height = this.W1.b(90.0f);
        this.o2.setLayoutParams(layoutParams);
        this.o2.setImageTintList(ColorStateList.valueOf(this.H0.n));
        ImageButton imageButton2 = this.o2;
        ImageView.ScaleType scaleType = ImageView.ScaleType.FIT_CENTER;
        imageButton2.setScaleType(scaleType);
        this.o2.setPadding(0, 0, 0, 0);
        this.o2.setForeground(wt2.j(this, this.W1.b(10.0f), this.W1.b(10.0f), this.H0.j));
        CircularProgressIndicator circularProgressIndicator = (CircularProgressIndicator) findViewById(R.id.loading);
        this.s2 = circularProgressIndicator;
        circularProgressIndicator.setIndicatorSize(this.W1.b(60.0f));
        this.s2.setIndicatorInset(0);
        this.s2.setIndicatorColor(this.H0.n);
        this.s2.setTrackColor(this.H0.k);
        if (i >= 26) {
            this.s2.setDefaultFocusHighlightEnabled(false);
        }
        TextView textView2 = (TextView) findViewById(R.id.loading_speed);
        this.t2 = textView2;
        textView2.setTextSize(2, this.W1.q(13.0f, 14.0f, 14.0f, 15.0f));
        this.t2.setTextColor(this.H0.f);
        this.t2.setBackground(wt2.c0(this.H0.b, this.W1.b(8.0f)));
        this.t2.setPadding(this.W1.a(10.0f), this.W1.a(4.0f), this.W1.a(10.0f), this.W1.a(4.0f));
        FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) this.t2.getLayoutParams();
        layoutParams2.topMargin = this.W1.b(12.0f) + (this.W1.b(60.0f) / 2);
        this.t2.setLayoutParams(layoutParams2);
        this.p2 = (ImageButton) findViewById(R.id.exo_prev);
        this.q2 = (ImageButton) findViewById(R.id.exo_next);
        d3();
        this.B.setShowNextButton(false);
        this.B.setShowPreviousButton(false);
        this.B.setShowFastForwardButton(false);
        this.B.setShowRewindButton(false);
        this.B.setRepeatToggleModes(1);
        this.B.setControllerHideOnTouch(false);
        this.B.setControllerAutoShow(true);
        ((DoubleTapPlayerView) this.B).setDoubleTapEnabled(false);
        CustomDefaultTimeBar customDefaultTimeBar = (CustomDefaultTimeBar) this.B.findViewById(R.id.exo_progress);
        this.y2 = customDefaultTimeBar;
        yq1 yq1Var = new yq1(this);
        customDefaultTimeBar.getClass();
        customDefaultTimeBar.F.add(yq1Var);
        ImageButton imageButton3 = new ImageButton(this, null, 0, R.style.ExoStyledControls_Button_Bottom);
        this.w1 = imageButton3;
        imageButton3.setImageResource(R.drawable.ic_playlist_24dp);
        this.w1.setId(View.generateViewId());
        this.w1.setContentDescription("Playlist");
        this.w1.setVisibility(8);
        this.w1.setOnClickListener(new fp1(this, (byte) 12));
        TextView textViewI1 = I1(0, getString(R.string.button_quality));
        this.x1 = textViewI1;
        textViewI1.setOnClickListener(new fp1(this, (byte) 13));
        TextView textViewI2 = I1(R.drawable.ic_audiotrack_plate_24dp, getString(R.string.button_audio_track));
        this.H1 = textViewI2;
        textViewI2.setOnClickListener(new fp1(this, (byte) 14));
        TextView textViewI3 = I1(R.drawable.ic_subtitles_24dp, getString(R.string.subtitle_title));
        this.I1 = textViewI3;
        textViewI3.setCompoundDrawableTintList(this.H0.p);
        this.I1.setOnClickListener(new fp1(this, (byte) 15));
        final byte b6 = 4;
        this.I1.setOnLongClickListener(new View.OnLongClickListener(this) { // from class: yp1
            public final /* synthetic */ PlayerActivity m;

            {
                this.m = this;
            }

            @Override // android.view.View.OnLongClickListener
            public final boolean onLongClick(View view) {
                byte b8 = b6;
                PlayerActivity playerActivity2 = this.m;
                switch (b8) {
                    case DecoderInputBuffer.BUFFER_REPLACEMENT_MODE_DISABLED /* 0 */:
                        LoudnessEnhancer loudnessEnhancer = PlayerActivity.i6;
                        playerActivity2.A1(null);
                        return true;
                    case 1:
                        LoudnessEnhancer loudnessEnhancer2 = PlayerActivity.i6;
                        playerActivity2.y0 = true;
                        if (playerActivity2.B.getResizeMode() != 4) {
                            playerActivity2.B.setResizeMode(4);
                        }
                        playerActivity2.A0 = playerActivity2.B.getVideoSurfaceView().getScaleX();
                        cz czVar2 = playerActivity2.B;
                        czVar2.removeCallbacks(czVar2.C0);
                        playerActivity2.B.u(R.drawable.ic_fit_screen_24dp, ((int) (playerActivity2.A0 * 100.0f)) + "%");
                        playerActivity2.B.c();
                        playerActivity2.z0 = true;
                        playerActivity2.K4();
                        return true;
                    case 2:
                        LoudnessEnhancer loudnessEnhancer3 = PlayerActivity.i6;
                        playerActivity2.h3();
                        return true;
                    case VideoDecoderOutputBuffer.COLORSPACE_BT2020 /* 3 */:
                        if (PlayerActivity.R6) {
                            return false;
                        }
                        playerActivity2.x3();
                        return true;
                    default:
                        LoudnessEnhancer loudnessEnhancer4 = PlayerActivity.i6;
                        playerActivity2.A1("subtitlesScreen");
                        return true;
                }
            }
        });
        ImageButton imageButton4 = new ImageButton(this, null, 0, R.style.ExoStyledControls_Button_Bottom);
        this.J1 = imageButton4;
        imageButton4.setImageResource(R.drawable.ic_more_vert_24dp);
        this.J1.setId(View.generateViewId());
        this.J1.setContentDescription(getString(R.string.button_more));
        this.J1.setOnClickListener(new fp1(this, b3));
        this.J1.setOnLongClickListener(new View.OnLongClickListener(this) { // from class: yp1
            public final /* synthetic */ PlayerActivity m;

            {
                this.m = this;
            }

            @Override // android.view.View.OnLongClickListener
            public final boolean onLongClick(View view) {
                byte b8 = b;
                PlayerActivity playerActivity2 = this.m;
                switch (b8) {
                    case DecoderInputBuffer.BUFFER_REPLACEMENT_MODE_DISABLED /* 0 */:
                        LoudnessEnhancer loudnessEnhancer = PlayerActivity.i6;
                        playerActivity2.A1(null);
                        return true;
                    case 1:
                        LoudnessEnhancer loudnessEnhancer2 = PlayerActivity.i6;
                        playerActivity2.y0 = true;
                        if (playerActivity2.B.getResizeMode() != 4) {
                            playerActivity2.B.setResizeMode(4);
                        }
                        playerActivity2.A0 = playerActivity2.B.getVideoSurfaceView().getScaleX();
                        cz czVar2 = playerActivity2.B;
                        czVar2.removeCallbacks(czVar2.C0);
                        playerActivity2.B.u(R.drawable.ic_fit_screen_24dp, ((int) (playerActivity2.A0 * 100.0f)) + "%");
                        playerActivity2.B.c();
                        playerActivity2.z0 = true;
                        playerActivity2.K4();
                        return true;
                    case 2:
                        LoudnessEnhancer loudnessEnhancer3 = PlayerActivity.i6;
                        playerActivity2.h3();
                        return true;
                    case VideoDecoderOutputBuffer.COLORSPACE_BT2020 /* 3 */:
                        if (PlayerActivity.R6) {
                            return false;
                        }
                        playerActivity2.x3();
                        return true;
                    default:
                        LoudnessEnhancer loudnessEnhancer4 = PlayerActivity.i6;
                        playerActivity2.A1("subtitlesScreen");
                        return true;
                }
            }
        });
        ImageButton imageButton5 = new ImageButton(this, null, 0, R.style.ExoStyledControls_Button_Bottom);
        this.m2 = imageButton5;
        imageButton5.setImageResource(R.drawable.ic_update_24dp);
        this.m2.setId(View.generateViewId());
        this.m2.setContentDescription(getString(R.string.button_update));
        this.m2.setVisibility(8);
        this.m2.setOnClickListener(new fp1(this, (byte) 3));
        if (wt2.B(this)) {
            this.F = jc.a();
            if (y4(R.drawable.ic_play_arrow_24dp, R.string.exo_controls_play_description, 1, 1)) {
                ImageButton imageButton6 = new ImageButton(this, null, 0, R.style.ExoStyledControls_Button_Bottom);
                this.X1 = imageButton6;
                imageButton6.setContentDescription(getString(R.string.button_pip));
                this.X1.setImageResource(R.drawable.ic_picture_in_picture_alt_24dp);
                this.X1.setOnClickListener(new fp1(this, b6));
            }
        }
        ImageButton imageButton7 = new ImageButton(this, null, 0, R.style.ExoStyledControls_Button_Bottom);
        this.Y1 = imageButton7;
        imageButton7.setId(2147483547);
        this.Y1.setContentDescription(getString(R.string.button_crop));
        K4();
        this.Y1.setOnClickListener(new fp1(this, b4));
        if (!Q6 || i < 24) {
            this.Y1.setOnLongClickListener(new View.OnLongClickListener(this) { // from class: yp1
                public final /* synthetic */ PlayerActivity m;

                {
                    this.m = this;
                }

                @Override // android.view.View.OnLongClickListener
                public final boolean onLongClick(View view) {
                    byte b8 = b3;
                    PlayerActivity playerActivity2 = this.m;
                    switch (b8) {
                        case DecoderInputBuffer.BUFFER_REPLACEMENT_MODE_DISABLED /* 0 */:
                            LoudnessEnhancer loudnessEnhancer = PlayerActivity.i6;
                            playerActivity2.A1(null);
                            return true;
                        case 1:
                            LoudnessEnhancer loudnessEnhancer2 = PlayerActivity.i6;
                            playerActivity2.y0 = true;
                            if (playerActivity2.B.getResizeMode() != 4) {
                                playerActivity2.B.setResizeMode(4);
                            }
                            playerActivity2.A0 = playerActivity2.B.getVideoSurfaceView().getScaleX();
                            cz czVar2 = playerActivity2.B;
                            czVar2.removeCallbacks(czVar2.C0);
                            playerActivity2.B.u(R.drawable.ic_fit_screen_24dp, ((int) (playerActivity2.A0 * 100.0f)) + "%");
                            playerActivity2.B.c();
                            playerActivity2.z0 = true;
                            playerActivity2.K4();
                            return true;
                        case 2:
                            LoudnessEnhancer loudnessEnhancer3 = PlayerActivity.i6;
                            playerActivity2.h3();
                            return true;
                        case VideoDecoderOutputBuffer.COLORSPACE_BT2020 /* 3 */:
                            if (PlayerActivity.R6) {
                                return false;
                            }
                            playerActivity2.x3();
                            return true;
                        default:
                            LoudnessEnhancer loudnessEnhancer4 = PlayerActivity.i6;
                            playerActivity2.A1("subtitlesScreen");
                            return true;
                    }
                }
            });
        } else {
            this.Y1.setOnLongClickListener(new View.OnLongClickListener(this) { // from class: yp1
                public final /* synthetic */ PlayerActivity m;

                {
                    this.m = this;
                }

                @Override // android.view.View.OnLongClickListener
                public final boolean onLongClick(View view) {
                    byte b8 = b2;
                    PlayerActivity playerActivity2 = this.m;
                    switch (b8) {
                        case DecoderInputBuffer.BUFFER_REPLACEMENT_MODE_DISABLED /* 0 */:
                            LoudnessEnhancer loudnessEnhancer = PlayerActivity.i6;
                            playerActivity2.A1(null);
                            return true;
                        case 1:
                            LoudnessEnhancer loudnessEnhancer2 = PlayerActivity.i6;
                            playerActivity2.y0 = true;
                            if (playerActivity2.B.getResizeMode() != 4) {
                                playerActivity2.B.setResizeMode(4);
                            }
                            playerActivity2.A0 = playerActivity2.B.getVideoSurfaceView().getScaleX();
                            cz czVar2 = playerActivity2.B;
                            czVar2.removeCallbacks(czVar2.C0);
                            playerActivity2.B.u(R.drawable.ic_fit_screen_24dp, ((int) (playerActivity2.A0 * 100.0f)) + "%");
                            playerActivity2.B.c();
                            playerActivity2.z0 = true;
                            playerActivity2.K4();
                            return true;
                        case 2:
                            LoudnessEnhancer loudnessEnhancer3 = PlayerActivity.i6;
                            playerActivity2.h3();
                            return true;
                        case VideoDecoderOutputBuffer.COLORSPACE_BT2020 /* 3 */:
                            if (PlayerActivity.R6) {
                                return false;
                            }
                            playerActivity2.x3();
                            return true;
                        default:
                            LoudnessEnhancer loudnessEnhancer4 = PlayerActivity.i6;
                            playerActivity2.A1("subtitlesScreen");
                            return true;
                    }
                }
            });
        }
        ImageButton imageButton8 = new ImageButton(this, null, 0, R.style.ExoStyledControls_Button_Bottom);
        this.b2 = imageButton8;
        imageButton8.setContentDescription(getString(R.string.button_rotate));
        this.b2.setImageResource(R.drawable.ic_screen_rotation_24dp);
        this.b2.setOnClickListener(new fp1(this, b5));
        ImageButton imageButton9 = new ImageButton(this, null, 0, R.style.ExoStyledControls_Button_Bottom);
        this.c2 = imageButton9;
        imageButton9.setImageResource(R.drawable.ic_lock_24dp);
        this.c2.setImageTintList(this.H0.p);
        this.c2.setId(View.generateViewId());
        this.c2.setContentDescription(getString(R.string.button_lock));
        this.c2.setOnClickListener(new fp1(this, (byte) 7));
        final int dimensionPixelOffset = getResources().getDimensionPixelOffset(R.dimen.exo_styled_bottom_bar_time_padding);
        FrameLayout frameLayout = new FrameLayout(this);
        this.E0 = frameLayout;
        frameLayout.setVisibility(4);
        this.E0.setAlpha(0.0f);
        View view = new View(this);
        int iP = this.W1.p();
        ShapeDrawable shapeDrawable = new ShapeDrawable(new RectShape());
        shapeDrawable.setShaderFactory(new mq1(this));
        view.setBackground(shapeDrawable);
        this.E0.addView(view, new FrameLayout.LayoutParams(-1, iP));
        LinearLayout linearLayout = new LinearLayout(this);
        this.D0 = linearLayout;
        linearLayout.setOrientation(0);
        this.D0.setGravity(48);
        this.D0.setLayoutParams(new FrameLayout.LayoutParams(-1, -2));
        this.D0.setClipChildren(false);
        this.D0.addOnLayoutChangeListener(new View.OnLayoutChangeListener(this) { // from class: zp1
            public final /* synthetic */ PlayerActivity b;

            {
                this.b = this;
            }

            @Override // android.view.View.OnLayoutChangeListener
            public final void onLayoutChange(View view2, int i2, int i3, int i4, int i5, int i7, int i8, int i9, int i10) {
                byte b8 = b;
                PlayerActivity playerActivity2 = this.b;
                switch (b8) {
                    case DecoderInputBuffer.BUFFER_REPLACEMENT_MODE_DISABLED /* 0 */:
                        LoudnessEnhancer loudnessEnhancer = PlayerActivity.i6;
                        playerActivity2.D0();
                        View view3 = (View) playerActivity2.l1.getParent();
                        int iB = playerActivity2.W1.b(2.0f);
                        if (view3 != null && playerActivity2.l1.getVisibility() == 0 && playerActivity2.p1.getVisibility() == 0) {
                            View view4 = playerActivity2.S0.getVisibility() == 0 ? playerActivity2.S0 : playerActivity2.R0;
                            int bottom = view4.getBottom() + ((ViewGroup.MarginLayoutParams) view4.getLayoutParams()).bottomMargin;
                            iB = Math.max(view4.getBottom() - bottom, (((playerActivity2.p1.getBaseline() + (playerActivity2.p1.getTop() + ((View) playerActivity2.p1.getParent()).getTop())) - view3.getTop()) - playerActivity2.l1.getBaseline()) - bottom);
                        }
                        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) playerActivity2.l1.getLayoutParams();
                        if (marginLayoutParams.topMargin != iB) {
                            marginLayoutParams.topMargin = iB;
                            playerActivity2.l1.setLayoutParams(marginLayoutParams);
                        }
                        break;
                    case 1:
                        LoudnessEnhancer loudnessEnhancer2 = PlayerActivity.i6;
                        if (i5 - i3 != i10 - i8) {
                            playerActivity2.H4();
                        }
                        break;
                    case 2:
                        LoudnessEnhancer loudnessEnhancer3 = PlayerActivity.i6;
                        if (playerActivity2.s1 != null) {
                            ViewGroup viewGroup = (ViewGroup) view2;
                            int iB2 = playerActivity2.W1.b(8.0f) + viewGroup.getChildAt(viewGroup.getChildCount() - 1).getBottom() + i3;
                            yx yxVar = (yx) playerActivity2.s1.getLayoutParams();
                            if (((ViewGroup.MarginLayoutParams) yxVar).topMargin != iB2) {
                                ((ViewGroup.MarginLayoutParams) yxVar).topMargin = iB2;
                                playerActivity2.s1.setLayoutParams(yxVar);
                            }
                            break;
                        }
                        break;
                    case VideoDecoderOutputBuffer.COLORSPACE_BT2020 /* 3 */:
                        LoudnessEnhancer loudnessEnhancer4 = PlayerActivity.i6;
                        playerActivity2.e4();
                        break;
                    case 4:
                        LoudnessEnhancer loudnessEnhancer5 = PlayerActivity.i6;
                        if (i4 - i2 != i9 - i7) {
                            playerActivity2.F1 = true;
                        }
                        break;
                    case 5:
                        LoudnessEnhancer loudnessEnhancer6 = PlayerActivity.i6;
                        if (i5 - i3 != i10 - i8 && playerActivity2.L4()) {
                            playerActivity2.E4();
                            break;
                        }
                        break;
                    default:
                        LoudnessEnhancer loudnessEnhancer7 = PlayerActivity.i6;
                        if (i5 - i3 != playerActivity2.x4) {
                            playerActivity2.E4();
                        }
                        break;
                }
            }
        });
        this.N0 = new FrameLayout(this);
        LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(-2, this.W1.m());
        layoutParams3.setMarginEnd(this.W1.b(16.0f));
        layoutParams3.gravity = 48;
        this.N0.setLayoutParams(layoutParams3);
        this.N0.setBackgroundColor(getColor(R.color.placeholder_card));
        int iP2 = wt2.p(4);
        this.N0.setClipToOutline(true);
        this.N0.setOutlineProvider(new r70(iP2, (byte) 1));
        this.N0.setVisibility(8);
        ImageView imageView = new ImageView(this);
        this.O0 = imageView;
        imageView.setLayoutParams(new FrameLayout.LayoutParams(-2, -1));
        this.O0.setAdjustViewBounds(true);
        this.O0.setScaleType(scaleType);
        this.N0.addView(this.O0);
        TextView textView3 = new TextView(this);
        this.P0 = textView3;
        textView3.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        this.P0.setMinWidth(wt2.p(54));
        this.P0.setGravity(17);
        this.P0.setTextColor(getColor(R.color.ink_tertiary));
        this.P0.setTextSize(2, this.W1.w());
        TextView textView4 = this.P0;
        Typeface typeface = Typeface.DEFAULT_BOLD;
        textView4.setTypeface(typeface);
        this.P0.setVisibility(8);
        this.N0.addView(this.P0);
        TextView textViewZ = Z();
        this.Q0 = textViewZ;
        this.N0.addView(textViewZ);
        this.D0.addView(this.N0);
        LinearLayout linearLayout2 = new LinearLayout(this);
        linearLayout2.setOrientation(1);
        LinearLayout.LayoutParams layoutParams4 = new LinearLayout.LayoutParams(0, -2, 1.0f);
        layoutParams4.gravity = 48;
        layoutParams4.setMarginEnd(wt2.p(16));
        linearLayout2.setLayoutParams(layoutParams4);
        TextView textView5 = new TextView(this);
        this.R0 = textView5;
        textView5.setTextColor(this.J0.e);
        this.R0.setTypeface(Typeface.create("sans-serif-medium", 0));
        this.R0.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
        this.R0.setTextSize(2, this.W1.u());
        this.R0.setMaxLines(1);
        TextView textView6 = this.R0;
        TextUtils.TruncateAt truncateAt = TextUtils.TruncateAt.END;
        textView6.setEllipsize(truncateAt);
        this.R0.setTextDirection(5);
        this.R0.setIncludeFontPadding(false);
        this.i1 = (this.W1.m() * 18) / 25;
        this.j1 = (this.W1.m() * 16) / 5;
        this.S0 = new ImageView(this);
        LinearLayout.LayoutParams layoutParams5 = new LinearLayout.LayoutParams(-2, -2);
        layoutParams5.bottomMargin = this.W1.b(4.0f);
        this.S0.setLayoutParams(layoutParams5);
        this.S0.setAdjustViewBounds(true);
        this.S0.setMaxHeight(this.i1);
        this.S0.setMaxWidth(this.j1);
        this.S0.setScaleType(ImageView.ScaleType.FIT_START);
        this.S0.setVisibility(8);
        linearLayout2.addView(this.S0);
        linearLayout2.addView(this.R0);
        TextView textViewY = Y(this.W1.b(2.0f));
        this.l1 = textViewY;
        textViewY.setTextColor(this.J0.f);
        this.l1.setTextSize(2, this.W1.t());
        linearLayout2.addView(this.l1);
        this.K0 = new LinearLayout(this);
        LinearLayout.LayoutParams layoutParams6 = new LinearLayout.LayoutParams(-1, -2);
        layoutParams6.topMargin = this.W1.b(6.0f);
        linearLayout2.addView(this.K0, layoutParams6);
        TextView textViewY2 = Y(0);
        this.m1 = textViewY2;
        Y0(textViewY2, R.drawable.ic_theaters_24dp, 4, 20);
        this.K0.addView(this.m1);
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(eu.f(this.J0.g, 89));
        gradientDrawable.setSize(Math.max(1, this.W1.b(1.0f)), Math.round(this.W1.v() * getResources().getDisplayMetrics().scaledDensity * 0.72f));
        ImageView imageView2 = new ImageView(this);
        imageView2.setScaleType(ImageView.ScaleType.CENTER);
        imageView2.setImageDrawable(gradientDrawable);
        this.o1 = imageView2;
        LinearLayout.LayoutParams layoutParams7 = new LinearLayout.LayoutParams(-2, -1);
        layoutParams7.setMarginStart(this.W1.b(12.0f));
        layoutParams7.setMarginEnd(this.W1.b(12.0f));
        this.K0.addView(this.o1, layoutParams7);
        TextView textViewY3 = Y(0);
        this.n1 = textViewY3;
        Y0(textViewY3, R.drawable.ic_audiotrack_24dp, 3, 21);
        this.K0.addView(this.n1);
        j();
        this.D0.addView(linearLayout2);
        LinearLayout linearLayout3 = new LinearLayout(this);
        linearLayout3.setOrientation(1);
        linearLayout3.setGravity(8388613);
        LinearLayout.LayoutParams layoutParams8 = new LinearLayout.LayoutParams(-2, -2);
        layoutParams8.gravity = 48;
        LinearLayout linearLayout4 = new LinearLayout(this);
        linearLayout4.setOrientation(0);
        linearLayout4.setGravity(16);
        LinearLayout.LayoutParams layoutParams9 = new LinearLayout.LayoutParams(-2, -2);
        layoutParams9.gravity = 8388613;
        linearLayout4.setLayoutParams(layoutParams9);
        hm1 hm1Var = new hm1(this);
        this.v1 = hm1Var;
        hm1Var.setFormat12Hour("h:mm a");
        this.v1.setFormat24Hour("HH:mm");
        this.v1.setTextColor(this.J0.h);
        this.v1.setOutlineColor(this.J0.i);
        this.v1.setTypeface(Typeface.create("sans-serif-medium", 0));
        this.v1.setTextSize(2, this.W1.q(20.0f, 21.0f, 22.0f, 22.0f));
        linearLayout4.addView(this.v1);
        linearLayout3.addView(linearLayout4);
        TextView textView7 = new TextView(this);
        this.p1 = textView7;
        textView7.setTextColor(this.J0.g);
        this.p1.setTextSize(2, this.W1.t());
        this.p1.setVisibility(8);
        LinearLayout.LayoutParams layoutParams10 = new LinearLayout.LayoutParams(-2, -2);
        layoutParams10.gravity = 8388613;
        layoutParams10.topMargin = this.W1.a(2.0f);
        linearLayout3.addView(this.p1, layoutParams10);
        this.v1.setIncludeFontPadding(false);
        layoutParams8.topMargin = Math.max(0, this.v1.getPaint().getFontMetricsInt().ascent - this.R0.getPaint().getFontMetricsInt().ascent);
        linearLayout3.setLayoutParams(layoutParams8);
        Rect rect = new Rect();
        this.v1.getPaint().getTextBounds("0", 0, 1, rect);
        ((LinearLayout.LayoutParams) this.S0.getLayoutParams()).topMargin = Math.max(0, (-this.R0.getPaint().getFontMetricsInt().ascent) + rect.top);
        this.D0.addView(linearLayout3);
        this.D0.addOnLayoutChangeListener(new View.OnLayoutChangeListener(this) { // from class: zp1
            public final /* synthetic */ PlayerActivity b;

            {
                this.b = this;
            }

            @Override // android.view.View.OnLayoutChangeListener
            public final void onLayoutChange(View view2, int i2, int i3, int i4, int i5, int i7, int i8, int i9, int i10) {
                byte b8 = b3;
                PlayerActivity playerActivity2 = this.b;
                switch (b8) {
                    case DecoderInputBuffer.BUFFER_REPLACEMENT_MODE_DISABLED /* 0 */:
                        LoudnessEnhancer loudnessEnhancer = PlayerActivity.i6;
                        playerActivity2.D0();
                        View view3 = (View) playerActivity2.l1.getParent();
                        int iB = playerActivity2.W1.b(2.0f);
                        if (view3 != null && playerActivity2.l1.getVisibility() == 0 && playerActivity2.p1.getVisibility() == 0) {
                            View view4 = playerActivity2.S0.getVisibility() == 0 ? playerActivity2.S0 : playerActivity2.R0;
                            int bottom = view4.getBottom() + ((ViewGroup.MarginLayoutParams) view4.getLayoutParams()).bottomMargin;
                            iB = Math.max(view4.getBottom() - bottom, (((playerActivity2.p1.getBaseline() + (playerActivity2.p1.getTop() + ((View) playerActivity2.p1.getParent()).getTop())) - view3.getTop()) - playerActivity2.l1.getBaseline()) - bottom);
                        }
                        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) playerActivity2.l1.getLayoutParams();
                        if (marginLayoutParams.topMargin != iB) {
                            marginLayoutParams.topMargin = iB;
                            playerActivity2.l1.setLayoutParams(marginLayoutParams);
                        }
                        break;
                    case 1:
                        LoudnessEnhancer loudnessEnhancer2 = PlayerActivity.i6;
                        if (i5 - i3 != i10 - i8) {
                            playerActivity2.H4();
                        }
                        break;
                    case 2:
                        LoudnessEnhancer loudnessEnhancer3 = PlayerActivity.i6;
                        if (playerActivity2.s1 != null) {
                            ViewGroup viewGroup = (ViewGroup) view2;
                            int iB2 = playerActivity2.W1.b(8.0f) + viewGroup.getChildAt(viewGroup.getChildCount() - 1).getBottom() + i3;
                            yx yxVar = (yx) playerActivity2.s1.getLayoutParams();
                            if (((ViewGroup.MarginLayoutParams) yxVar).topMargin != iB2) {
                                ((ViewGroup.MarginLayoutParams) yxVar).topMargin = iB2;
                                playerActivity2.s1.setLayoutParams(yxVar);
                            }
                            break;
                        }
                        break;
                    case VideoDecoderOutputBuffer.COLORSPACE_BT2020 /* 3 */:
                        LoudnessEnhancer loudnessEnhancer4 = PlayerActivity.i6;
                        playerActivity2.e4();
                        break;
                    case 4:
                        LoudnessEnhancer loudnessEnhancer5 = PlayerActivity.i6;
                        if (i4 - i2 != i9 - i7) {
                            playerActivity2.F1 = true;
                        }
                        break;
                    case 5:
                        LoudnessEnhancer loudnessEnhancer6 = PlayerActivity.i6;
                        if (i5 - i3 != i10 - i8 && playerActivity2.L4()) {
                            playerActivity2.E4();
                            break;
                        }
                        break;
                    default:
                        LoudnessEnhancer loudnessEnhancer7 = PlayerActivity.i6;
                        if (i5 - i3 != playerActivity2.x4) {
                            playerActivity2.E4();
                        }
                        break;
                }
            }
        });
        this.E0.addView(this.D0);
        this.B.getOverlayFrameLayout().addView(this.E0, new FrameLayout.LayoutParams(-1, -1));
        int iO = this.W1.o();
        int iP3 = wt2.p(12);
        Button button = new Button(this);
        this.f5 = button;
        button.setText(R.string.button_skip);
        this.f5.setAllCaps(false);
        this.f5.setTextColor(this.H0.e);
        this.f5.setTextSize(2, this.W1.r());
        this.f5.setTypeface(typeface);
        this.f5.setMinHeight(iO);
        this.f5.setMinimumHeight(iO);
        this.m5 = wt2.p(r5);
        Drawable drawable = getDrawable(R.drawable.ic_double_arrow_24dp);
        if (drawable != null) {
            drawable.mutate();
            drawable.setTint(this.H0.e);
        }
        this.g5 = drawable;
        Drawable drawable2 = getDrawable(R.drawable.ic_play_arrow_24dp);
        if (drawable2 != null) {
            drawable2.mutate();
            drawable2.setTint(this.H0.e);
        }
        this.h5 = drawable2;
        Drawable drawable3 = getDrawable(R.drawable.ic_double_arrow_back_24dp);
        if (drawable3 != null) {
            drawable3.mutate();
            drawable3.setTint(this.H0.e);
        }
        this.i5 = drawable3;
        this.f5.setCompoundDrawablesRelative(null, null, null, null);
        this.f5.setCompoundDrawablePadding(wt2.p(8));
        this.f5.setCompoundDrawableTintList(ColorStateList.valueOf(this.H0.e));
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.focus_ring_width);
        xr xrVar = this.H0;
        this.n5 = new hr1(xrVar.b, (xrVar.j & 16777215) | 1191182336, dimensionPixelSize, iP3);
        this.f5.setBackground(new InsetDrawable((Drawable) this.n5, 0, wt2.p(4), 0, wt2.p(4)));
        this.f5.setPadding(wt2.p(20), 0, wt2.p(20), 0);
        this.f5.setOnClickListener(new fp1(this, (byte) 8));
        final byte b8 = 3;
        this.f5.setOnLongClickListener(new View.OnLongClickListener(this) { // from class: yp1
            public final /* synthetic */ PlayerActivity m;

            {
                this.m = this;
            }

            @Override // android.view.View.OnLongClickListener
            public final boolean onLongClick(View view2) {
                byte b9 = b8;
                PlayerActivity playerActivity2 = this.m;
                switch (b9) {
                    case DecoderInputBuffer.BUFFER_REPLACEMENT_MODE_DISABLED /* 0 */:
                        LoudnessEnhancer loudnessEnhancer = PlayerActivity.i6;
                        playerActivity2.A1(null);
                        return true;
                    case 1:
                        LoudnessEnhancer loudnessEnhancer2 = PlayerActivity.i6;
                        playerActivity2.y0 = true;
                        if (playerActivity2.B.getResizeMode() != 4) {
                            playerActivity2.B.setResizeMode(4);
                        }
                        playerActivity2.A0 = playerActivity2.B.getVideoSurfaceView().getScaleX();
                        cz czVar2 = playerActivity2.B;
                        czVar2.removeCallbacks(czVar2.C0);
                        playerActivity2.B.u(R.drawable.ic_fit_screen_24dp, ((int) (playerActivity2.A0 * 100.0f)) + "%");
                        playerActivity2.B.c();
                        playerActivity2.z0 = true;
                        playerActivity2.K4();
                        return true;
                    case 2:
                        LoudnessEnhancer loudnessEnhancer3 = PlayerActivity.i6;
                        playerActivity2.h3();
                        return true;
                    case VideoDecoderOutputBuffer.COLORSPACE_BT2020 /* 3 */:
                        if (PlayerActivity.R6) {
                            return false;
                        }
                        playerActivity2.x3();
                        return true;
                    default:
                        LoudnessEnhancer loudnessEnhancer4 = PlayerActivity.i6;
                        playerActivity2.A1("subtitlesScreen");
                        return true;
                }
            }
        });
        this.f5.setOnFocusChangeListener(new at(this, b8));
        yx yxVar = new yx(-2, -2);
        yxVar.c = 8388693;
        yxVar.setMargins(0, 0, wt2.p(24), wt2.p(96));
        this.f5.setLayoutParams(yxVar);
        this.f5.setVisibility(8);
        this.C0.addView(this.f5);
        TextView textView8 = new TextView(this);
        this.o5 = textView8;
        textView8.setText("2.0×");
        this.o5.setAllCaps(false);
        this.o5.setTextColor(this.H0.e);
        this.o5.setTextSize(2, this.W1.q(13.0f, 14.0f, 14.0f, 15.0f));
        this.o5.setTypeface(typeface);
        this.o5.setGravity(16);
        byte b9 = 9;
        this.o5.setPadding(wt2.p(14), wt2.p(9), wt2.p(16), wt2.p(9));
        this.o5.setClickable(false);
        this.o5.setFocusable(false);
        this.p5 = getDrawable(R.drawable.exo_icon_fastforward);
        this.q5 = getDrawable(R.drawable.exo_icon_rewind);
        int iP4 = wt2.p(18);
        Drawable drawable4 = this.p5;
        if (drawable4 != null) {
            drawable4.setBounds(0, 0, iP4, iP4);
        }
        Drawable drawable5 = this.q5;
        if (drawable5 != null) {
            drawable5.setBounds(0, 0, iP4, iP4);
        }
        this.o5.setCompoundDrawablesRelative(null, null, this.p5, null);
        this.o5.setCompoundDrawablePadding(wt2.p(6));
        this.o5.setCompoundDrawableTintList(ColorStateList.valueOf(this.H0.n));
        GradientDrawable gradientDrawable2 = new GradientDrawable();
        gradientDrawable2.setColor(this.H0.b);
        gradientDrawable2.setCornerRadius(this.W1.b(8.0f));
        this.o5.setBackground(gradientDrawable2);
        yx yxVar2 = new yx(-2, -2);
        yxVar2.c = 49;
        yxVar2.setMargins(0, wt2.p(28), 0, 0);
        this.o5.setLayoutParams(yxVar2);
        this.o5.setVisibility(8);
        this.C0.addView(this.o5);
        hm1 hm1Var2 = new hm1(this);
        this.u1 = hm1Var2;
        hm1Var2.setFormat12Hour("h:mm a");
        this.u1.setFormat24Hour("HH:mm");
        this.u1.setTextColor(this.J0.h);
        this.u1.setOutlineColor(this.J0.i);
        this.u1.setTypeface(typeface);
        this.u1.setTextSize(2, this.W1.q(20.0f, 21.0f, 22.0f, 22.0f));
        yx yxVar3 = new yx(-2, -2);
        yxVar3.c = 8388659;
        this.u1.setLayoutParams(yxVar3);
        this.u1.setVisibility(8);
        this.C0.addView(this.u1);
        TextView textView9 = new TextView(this);
        this.s1 = textView9;
        textView9.setTextColor(this.H0.f);
        this.s1.setTextSize(2, this.W1.v());
        this.s1.setFontFeatureSettings("tnum");
        GradientDrawable gradientDrawable3 = new GradientDrawable();
        gradientDrawable3.setColor(this.H0.b);
        gradientDrawable3.setCornerRadius(this.W1.b(12.0f));
        this.s1.setBackground(gradientDrawable3);
        this.s1.setPadding(this.W1.b(12.0f), this.W1.b(10.0f), this.W1.b(12.0f), this.W1.b(10.0f));
        yx yxVar4 = new yx(-2, -2);
        yxVar4.c = 8388661;
        this.s1.setLayoutParams(yxVar4);
        this.s1.setVisibility(8);
        this.C0.addView(this.s1);
        int iN = sj.n(new ContextThemeWrapper(this, gu1.a(this, false)), R.attr.accentInk, -1);
        new ColorStateList(new int[][]{new int[]{android.R.attr.state_selected}, new int[0]}, new int[]{iN, -419430401});
        TextView textView10 = new TextView(this);
        this.r1 = textView10;
        textView10.setTextColor(-419430401);
        this.r1.setTextSize(2, this.W1.v());
        GradientDrawable gradientDrawable4 = new GradientDrawable();
        gradientDrawable4.setColor(-872415232);
        gradientDrawable4.setCornerRadius(this.W1.b(8.0f));
        this.r1.setBackground(gradientDrawable4);
        byte b10 = 10;
        this.r1.setPadding(wt2.p(10), wt2.p(5), wt2.p(10), wt2.p(5));
        Drawable drawable6 = getDrawable(R.drawable.ic_together_24dp);
        if (drawable6 != null) {
            int iRound = Math.round(this.r1.getTextSize());
            drawable6.setBounds(0, 0, iRound, iRound);
            this.r1.setCompoundDrawablesRelative(drawable6, null, null, null);
            this.r1.setCompoundDrawablePadding(this.W1.b(6.0f));
        }
        this.r1.setCompoundDrawableTintList(ColorStateList.valueOf(iN));
        LinearLayout.LayoutParams layoutParams11 = new LinearLayout.LayoutParams(-2, -2);
        layoutParams11.gravity = 8388613;
        layoutParams11.topMargin = this.W1.a(8.0f);
        this.r1.setVisibility(8);
        ((ViewGroup) this.p1.getParent()).addView(this.r1, layoutParams11);
        TextView textView11 = new TextView(this);
        this.t1 = textView11;
        textView11.setTextColor(this.H0.f);
        this.t1.setFontFeatureSettings("tnum");
        this.t1.setTextSize(2, this.W1.v());
        this.t1.setMaxLines(3);
        this.t1.setEllipsize(truncateAt);
        GradientDrawable gradientDrawable5 = new GradientDrawable();
        gradientDrawable5.setColor(this.H0.b);
        gradientDrawable5.setCornerRadius(this.W1.b(8.0f));
        this.t1.setBackground(gradientDrawable5);
        this.t1.setPadding(this.W1.b(14.0f), this.W1.b(6.0f), this.W1.b(14.0f), this.W1.b(6.0f));
        yx yxVar5 = new yx(-2, -2);
        yxVar5.c = 8388691;
        this.t1.setLayoutParams(yxVar5);
        this.t1.setVisibility(8);
        this.C0.addView(this.t1);
        TextView textView12 = new TextView(this);
        this.c5 = textView12;
        textView12.setTextColor(this.H0.e);
        this.c5.setTextSize(2, this.W1.v());
        GradientDrawable gradientDrawable6 = new GradientDrawable();
        gradientDrawable6.setColor(this.H0.b);
        gradientDrawable6.setCornerRadius(this.W1.b(8.0f));
        this.c5.setBackground(gradientDrawable6);
        this.c5.setPadding(wt2.p(10), wt2.p(5), wt2.p(10), wt2.p(5));
        yx yxVar6 = new yx(-2, -2);
        yxVar6.c = 17;
        ((ViewGroup.MarginLayoutParams) yxVar6).topMargin = this.W1.b(12.0f) + (this.W1.b(90.0f) / 2);
        this.c5.setLayoutParams(yxVar6);
        this.c5.setVisibility(8);
        this.C0.addView(this.c5);
        final byte b11 = 3;
        this.v1.addOnLayoutChangeListener(new View.OnLayoutChangeListener(this) { // from class: zp1
            public final /* synthetic */ PlayerActivity b;

            {
                this.b = this;
            }

            @Override // android.view.View.OnLayoutChangeListener
            public final void onLayoutChange(View view2, int i2, int i3, int i4, int i5, int i7, int i8, int i9, int i10) {
                byte b12 = b11;
                PlayerActivity playerActivity2 = this.b;
                switch (b12) {
                    case DecoderInputBuffer.BUFFER_REPLACEMENT_MODE_DISABLED /* 0 */:
                        LoudnessEnhancer loudnessEnhancer = PlayerActivity.i6;
                        playerActivity2.D0();
                        View view3 = (View) playerActivity2.l1.getParent();
                        int iB = playerActivity2.W1.b(2.0f);
                        if (view3 != null && playerActivity2.l1.getVisibility() == 0 && playerActivity2.p1.getVisibility() == 0) {
                            View view4 = playerActivity2.S0.getVisibility() == 0 ? playerActivity2.S0 : playerActivity2.R0;
                            int bottom = view4.getBottom() + ((ViewGroup.MarginLayoutParams) view4.getLayoutParams()).bottomMargin;
                            iB = Math.max(view4.getBottom() - bottom, (((playerActivity2.p1.getBaseline() + (playerActivity2.p1.getTop() + ((View) playerActivity2.p1.getParent()).getTop())) - view3.getTop()) - playerActivity2.l1.getBaseline()) - bottom);
                        }
                        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) playerActivity2.l1.getLayoutParams();
                        if (marginLayoutParams.topMargin != iB) {
                            marginLayoutParams.topMargin = iB;
                            playerActivity2.l1.setLayoutParams(marginLayoutParams);
                        }
                        break;
                    case 1:
                        LoudnessEnhancer loudnessEnhancer2 = PlayerActivity.i6;
                        if (i5 - i3 != i10 - i8) {
                            playerActivity2.H4();
                        }
                        break;
                    case 2:
                        LoudnessEnhancer loudnessEnhancer3 = PlayerActivity.i6;
                        if (playerActivity2.s1 != null) {
                            ViewGroup viewGroup = (ViewGroup) view2;
                            int iB2 = playerActivity2.W1.b(8.0f) + viewGroup.getChildAt(viewGroup.getChildCount() - 1).getBottom() + i3;
                            yx yxVar7 = (yx) playerActivity2.s1.getLayoutParams();
                            if (((ViewGroup.MarginLayoutParams) yxVar7).topMargin != iB2) {
                                ((ViewGroup.MarginLayoutParams) yxVar7).topMargin = iB2;
                                playerActivity2.s1.setLayoutParams(yxVar7);
                            }
                            break;
                        }
                        break;
                    case VideoDecoderOutputBuffer.COLORSPACE_BT2020 /* 3 */:
                        LoudnessEnhancer loudnessEnhancer4 = PlayerActivity.i6;
                        playerActivity2.e4();
                        break;
                    case 4:
                        LoudnessEnhancer loudnessEnhancer5 = PlayerActivity.i6;
                        if (i4 - i2 != i9 - i7) {
                            playerActivity2.F1 = true;
                        }
                        break;
                    case 5:
                        LoudnessEnhancer loudnessEnhancer6 = PlayerActivity.i6;
                        if (i5 - i3 != i10 - i8 && playerActivity2.L4()) {
                            playerActivity2.E4();
                            break;
                        }
                        break;
                    default:
                        LoudnessEnhancer loudnessEnhancer7 = PlayerActivity.i6;
                        if (i5 - i3 != playerActivity2.x4) {
                            playerActivity2.E4();
                        }
                        break;
                }
            }
        });
        if (!Q6) {
            rl2 rl2Var = new rl2(this);
            this.d2 = rl2Var;
            rl2Var.setVisibility(8);
            yx yxVar7 = new yx(wt2.p(260), wt2.p(48));
            yxVar7.c = 81;
            ((ViewGroup.MarginLayoutParams) yxVar7).bottomMargin = wt2.p(48);
            this.d2.setLayoutParams(yxVar7);
            this.d2.setOnUnlockListener(new wp1(this, (byte) 4));
            this.d2.setOnStartTouchingListener(new wp1(this, b4));
            this.d2.setOnStopTouchingListener(new wp1(this, b5));
            this.C0.addView(this.d2);
        }
        if (i >= 35) {
            z = false;
            getWindow().setNavigationBarContrastEnforced(false);
        } else {
            z = false;
        }
        vr1 vr1Var = (vr1) this.B.findViewById(R.id.exo_controller);
        this.x2 = vr1Var;
        if (Q6) {
            vr1Var.setTimeBarScrubbingEnabled(z);
        }
        TextView textView13 = (TextView) this.B.findViewById(R.id.exo_position);
        TextView textView14 = (TextView) this.B.findViewById(R.id.exo_duration);
        StringBuilder sb = new StringBuilder();
        this.x2.setProgressUpdateListener(new aq1(this, textView13, sb, new Formatter(sb, Locale.getDefault()), textView14));
        this.x2.setOnApplyWindowInsetsListener(new View.OnApplyWindowInsetsListener() { // from class: bq1
            @Override // android.view.View.OnApplyWindowInsetsListener
            public final WindowInsets onApplyWindowInsets(View view2, WindowInsets windowInsets) {
                LoudnessEnhancer loudnessEnhancer = PlayerActivity.i6;
                this.a.l1(dimensionPixelOffset, windowInsets);
                return windowInsets;
            }
        });
        this.y2.setAdMarkerColor(Color.argb(0, 255, 255, 255));
        this.y2.setPlayedAdMarkerColor(Color.argb(152, 255, 255, 255));
        int i2 = this.H0.n;
        this.y2.setPlayedColor(i2);
        this.y2.setScrubberColor(i2);
        this.y2.setUnplayedColor(this.H0.k);
        this.y2.k(this.H0.j);
        try {
            zy zyVar = new zy(getResources());
            this.q = zyVar;
            zyVar.n = this.s;
            Field declaredField = vr1.class.getDeclaredField("trackNameProvider");
            declaredField.setAccessible(true);
            declaredField.set(this.x2, this.q);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }
        findViewById(R.id.delete).setOnClickListener(new fp1(this, b9));
        findViewById(R.id.next).setOnClickListener(new fp1(this, b10));
        this.o2.setOnClickListener(new fp1(this, (byte) 11));
        findViewById(R.id.exo_bottom_bar).setOnTouchListener(new cq1());
        this.l = new gr1(this, (byte) 0);
        ck ckVar = new ck(this);
        this.I = ckVar;
        ckVar.c = this.H.p;
        this.B.setBrightnessControl(ckVar);
        LinearLayout linearLayout5 = (LinearLayout) this.B.findViewById(R.id.exo_basic_controls);
        ImageButton imageButton10 = (ImageButton) linearLayout5.findViewById(R.id.exo_subtitle);
        this.n2 = imageButton10;
        imageButton10.setVisibility(8);
        linearLayout5.removeAllViews();
        ImageButton imageButton11 = this.X1;
        if (imageButton11 != null) {
            wt2.a0(this, imageButton11, false);
        }
        wt2.a0(this, this.Y1, false);
        BottomBarLayout bottomBarLayout = (BottomBarLayout) findViewById(R.id.exo_bottom_bar);
        bottomBarLayout.setBackground(wt2.c0(this.H0.b, this.W1.h()));
        bottomBarLayout.setClipChildren(false);
        bottomBarLayout.setClipToPadding(false);
        View viewFindViewById3 = findViewById(R.id.plate_time_row);
        LinearLayout.LayoutParams layoutParams12 = (LinearLayout.LayoutParams) viewFindViewById3.getLayoutParams();
        layoutParams12.height = this.W1.l();
        if (!Q6) {
            int iA = (this.W1.a(48.0f) - this.W1.l()) / 2;
            layoutParams12.height = this.W1.a(48.0f);
            int i3 = -iA;
            layoutParams12.topMargin = i3;
            layoutParams12.bottomMargin = i3;
        }
        viewFindViewById3.setLayoutParams(layoutParams12);
        View viewFindViewById4 = findViewById(R.id.plate_row);
        LinearLayout.LayoutParams layoutParams13 = (LinearLayout.LayoutParams) viewFindViewById4.getLayoutParams();
        layoutParams13.height = this.W1.g();
        qs2 qs2Var = this.W1;
        layoutParams13.topMargin = qs2Var.z() ? qs2Var.a(12.0f) : qs2Var.b(4.0f);
        viewFindViewById4.setLayoutParams(layoutParams13);
        int[] iArr = {R.id.exo_position, R.id.exo_duration};
        for (int i4 = 0; i4 < 2; i4++) {
            int i5 = iArr[i4];
            TextView textView15 = (TextView) findViewById(i5);
            xr xrVar2 = this.H0;
            textView15.setTextColor(i5 == R.id.exo_position ? xrVar2.e : xrVar2.g);
            textView15.setTextSize(2, this.W1.x());
            if (i5 == R.id.exo_position) {
                textView15.setTypeface(Typeface.create("sans-serif-medium", 0));
            }
        }
        LinearLayout.LayoutParams layoutParams14 = (LinearLayout.LayoutParams) this.y2.getLayoutParams();
        layoutParams14.setMarginStart(this.W1.b(8.0f));
        layoutParams14.setMarginEnd(this.W1.b(8.0f));
        this.y2.setLayoutParams(layoutParams14);
        HorizontalScrollView horizontalScrollView = (HorizontalScrollView) getLayoutInflater().inflate(R.layout.controls, (ViewGroup) null);
        final LinearLayout linearLayout6 = (LinearLayout) horizontalScrollView.findViewById(R.id.controls);
        this.D1 = linearLayout6;
        linearLayout6.setClipChildren(false);
        horizontalScrollView.setClipChildren(false);
        View[] viewArr = {this.x1, this.H1, this.I1, this.w1, this.m2, this.J1};
        for (int i7 = 0; i7 < 6; i7++) {
            View view2 = viewArr[i7];
            if (view2 instanceof ImageButton) {
                S3((ImageButton) view2, this.W1.g(), linearLayout6.getChildCount() > 0);
            } else if (linearLayout6.getChildCount() > 0) {
                LinearLayout.LayoutParams layoutParams15 = (LinearLayout.LayoutParams) view2.getLayoutParams();
                qs2 qs2Var2 = this.W1;
                layoutParams15.setMarginStart(qs2Var2.z() ? qs2Var2.a(8.0f) : 0);
            }
            linearLayout6.addView(view2);
        }
        this.m2.setSelected(true);
        a2();
        linearLayout5.addView(horizontalScrollView, new LinearLayout.LayoutParams(-2, -1));
        LinearLayout linearLayout7 = (LinearLayout) findViewById(R.id.plate_left);
        this.C1 = linearLayout7;
        linearLayout7.setClipChildren(false);
        if (Q6) {
            View viewFindViewById5 = findViewById(R.id.next);
            View viewFindViewById6 = findViewById(R.id.delete);
            View[] viewArr2 = {this.p2, this.o2, this.q2, viewFindViewById5, viewFindViewById6};
            for (int i8 = 0; i8 < 5; i8++) {
                View view3 = viewArr2[i8];
                ((ViewGroup) view3.getParent()).removeView(view3);
            }
            this.o2.setBackground(wt2.c0(this.H0.c, 10000.0f));
            this.o2.setForeground(wt2.j(this, 0, 0, this.H0.j));
            this.o2.setPadding(0, 0, 0, 0);
            this.o2.setScaleType(ImageView.ScaleType.MATRIX);
            float fK = this.W1.k() / this.W1.a(24.0f);
            Matrix matrix = new Matrix();
            matrix.setScale(fK, fK);
            matrix.postTranslate((this.W1.a(48.0f) - (this.W1.a(72.0f) * fK)) / 2.0f, (this.W1.a(48.0f) - (this.W1.a(72.0f) * fK)) / 2.0f);
            this.o2.setImageMatrix(matrix);
            S3(this.p2, this.W1.g(), false);
            this.p2.setImageTintList(ColorStateList.valueOf(this.H0.e));
            int iG = (this.W1.g() - Math.round((this.W1.g() * 26.0f) / 46.0f)) / 2;
            this.p2.setPadding(iG, iG, iG, iG);
            this.C1.addView(this.p2);
            LinearLayout.LayoutParams layoutParams16 = new LinearLayout.LayoutParams(this.W1.a(48.0f), this.W1.a(48.0f));
            qs2 qs2Var3 = this.W1;
            layoutParams16.setMarginStart(qs2Var3.z() ? qs2Var3.a(8.0f) : 0);
            this.C1.addView(this.o2, layoutParams16);
            boolean z2 = true;
            View[] viewArr3 = {this.q2, viewFindViewById5, viewFindViewById6};
            int i9 = 0;
            while (i9 < 3) {
                View view4 = viewArr3[i9];
                ImageButton imageButton12 = (ImageButton) view4;
                S3(imageButton12, this.W1.g(), z2);
                imageButton12.setImageTintList(ColorStateList.valueOf(this.H0.e));
                int iG2 = (this.W1.g() - Math.round((this.W1.g() * 26.0f) / 46.0f)) / 2;
                view4.setPadding(iG2, iG2, iG2, iG2);
                this.C1.addView(view4);
                i9++;
                z2 = true;
            }
            int iN2 = (this.W1.n() - this.W1.b(60.0f)) / 2;
            this.s2.setBackground(wt2.c0(this.H0.b, 10000.0f));
            this.s2.setIndicatorInset(iN2);
            this.B.getViewTreeObserver().addOnGlobalFocusChangeListener(new ViewTreeObserver.OnGlobalFocusChangeListener() { // from class: dq1
                @Override // android.view.ViewTreeObserver.OnGlobalFocusChangeListener
                public final void onGlobalFocusChanged(View view5, View view6) {
                    LoudnessEnhancer loudnessEnhancer = PlayerActivity.i6;
                    if (view6 != null) {
                        PlayerActivity playerActivity2 = this.l;
                        if (view6 == playerActivity2.y2 || view6.getParent() == null) {
                            return;
                        }
                        if (view6.getParent() == playerActivity2.C1 || view6.getParent() == linearLayout6) {
                            view6.setNextFocusUpId(R.id.exo_progress);
                            playerActivity2.y2.setNextFocusDownId(view6.getId());
                        }
                    }
                }
            });
        } else {
            ArrayList<ImageButton> arrayList = new ArrayList();
            arrayList.add(this.c2);
            arrayList.add(this.b2);
            arrayList.add(this.Y1);
            ImageButton imageButton13 = this.X1;
            if (imageButton13 != null) {
                arrayList.add(imageButton13);
            }
            for (ImageButton imageButton14 : arrayList) {
                S3(imageButton14, this.W1.g(), this.C1.getChildCount() > 0);
                this.C1.addView(imageButton14);
            }
        }
        int i10 = Build.VERSION.SDK_INT;
        if (i10 > 23) {
            horizontalScrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() { // from class: eq1
                @Override // android.view.View.OnScrollChangeListener
                public final void onScrollChange(View view5, int i11, int i12, int i13, int i14) {
                    LoudnessEnhancer loudnessEnhancer = PlayerActivity.i6;
                    this.a.o2();
                }
            });
        }
        p();
        final byte b12 = 4;
        View.OnLayoutChangeListener onLayoutChangeListener = new View.OnLayoutChangeListener(this) { // from class: zp1
            public final /* synthetic */ PlayerActivity b;

            {
                this.b = this;
            }

            @Override // android.view.View.OnLayoutChangeListener
            public final void onLayoutChange(View view5, int i11, int i12, int i13, int i14, int i15, int i16, int i17, int i18) {
                byte b13 = b12;
                PlayerActivity playerActivity2 = this.b;
                switch (b13) {
                    case DecoderInputBuffer.BUFFER_REPLACEMENT_MODE_DISABLED /* 0 */:
                        LoudnessEnhancer loudnessEnhancer = PlayerActivity.i6;
                        playerActivity2.D0();
                        View view6 = (View) playerActivity2.l1.getParent();
                        int iB = playerActivity2.W1.b(2.0f);
                        if (view6 != null && playerActivity2.l1.getVisibility() == 0 && playerActivity2.p1.getVisibility() == 0) {
                            View view7 = playerActivity2.S0.getVisibility() == 0 ? playerActivity2.S0 : playerActivity2.R0;
                            int bottom = view7.getBottom() + ((ViewGroup.MarginLayoutParams) view7.getLayoutParams()).bottomMargin;
                            iB = Math.max(view7.getBottom() - bottom, (((playerActivity2.p1.getBaseline() + (playerActivity2.p1.getTop() + ((View) playerActivity2.p1.getParent()).getTop())) - view6.getTop()) - playerActivity2.l1.getBaseline()) - bottom);
                        }
                        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) playerActivity2.l1.getLayoutParams();
                        if (marginLayoutParams.topMargin != iB) {
                            marginLayoutParams.topMargin = iB;
                            playerActivity2.l1.setLayoutParams(marginLayoutParams);
                        }
                        break;
                    case 1:
                        LoudnessEnhancer loudnessEnhancer2 = PlayerActivity.i6;
                        if (i14 - i12 != i18 - i16) {
                            playerActivity2.H4();
                        }
                        break;
                    case 2:
                        LoudnessEnhancer loudnessEnhancer3 = PlayerActivity.i6;
                        if (playerActivity2.s1 != null) {
                            ViewGroup viewGroup = (ViewGroup) view5;
                            int iB2 = playerActivity2.W1.b(8.0f) + viewGroup.getChildAt(viewGroup.getChildCount() - 1).getBottom() + i12;
                            yx yxVar8 = (yx) playerActivity2.s1.getLayoutParams();
                            if (((ViewGroup.MarginLayoutParams) yxVar8).topMargin != iB2) {
                                ((ViewGroup.MarginLayoutParams) yxVar8).topMargin = iB2;
                                playerActivity2.s1.setLayoutParams(yxVar8);
                            }
                            break;
                        }
                        break;
                    case VideoDecoderOutputBuffer.COLORSPACE_BT2020 /* 3 */:
                        LoudnessEnhancer loudnessEnhancer4 = PlayerActivity.i6;
                        playerActivity2.e4();
                        break;
                    case 4:
                        LoudnessEnhancer loudnessEnhancer5 = PlayerActivity.i6;
                        if (i13 - i11 != i17 - i15) {
                            playerActivity2.F1 = true;
                        }
                        break;
                    case 5:
                        LoudnessEnhancer loudnessEnhancer6 = PlayerActivity.i6;
                        if (i14 - i12 != i18 - i16 && playerActivity2.L4()) {
                            playerActivity2.E4();
                            break;
                        }
                        break;
                    default:
                        LoudnessEnhancer loudnessEnhancer7 = PlayerActivity.i6;
                        if (i14 - i12 != playerActivity2.x4) {
                            playerActivity2.E4();
                        }
                        break;
                }
            }
        };
        viewFindViewById4.addOnLayoutChangeListener(onLayoutChangeListener);
        linearLayout6.addOnLayoutChangeListener(onLayoutChangeListener);
        this.C1.addOnLayoutChangeListener(onLayoutChangeListener);
        viewFindViewById4.getViewTreeObserver().addOnPreDrawListener(new gp1(this, (byte) 1));
        final View viewFindViewById7 = findViewById(R.id.exo_controls_background);
        final View viewFindViewById8 = findViewById(R.id.exo_bottom_bar);
        this.B.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() { // from class: fq1
            /* JADX WARN: Code duplicated, block: B:9:0x001c  */
            @Override // android.view.ViewTreeObserver.OnPreDrawListener
            public final boolean onPreDraw() {
                float alpha;
                LoudnessEnhancer loudnessEnhancer = PlayerActivity.i6;
                PlayerActivity playerActivity2 = this.l;
                vr1 vr1Var2 = playerActivity2.x2;
                if (vr1Var2 == null || vr1Var2.getVisibility() != 0) {
                    alpha = 0.0f;
                } else {
                    View view5 = viewFindViewById7;
                    if (view5.getVisibility() == 0) {
                        alpha = view5.getAlpha();
                    } else {
                        alpha = 0.0f;
                    }
                }
                viewFindViewById8.setAlpha(alpha);
                float f = (!playerActivity2.F0 || playerActivity2.G) ? 0.0f : alpha;
                playerActivity2.E0.setAlpha(f);
                int i11 = f > 0.0f ? 0 : 4;
                if (playerActivity2.E0.getVisibility() != i11) {
                    playerActivity2.E0.setVisibility(i11);
                }
                View[] viewArr4 = {playerActivity2.s1, playerActivity2.t1, playerActivity2.r1};
                for (int i12 = 0; i12 < 3; i12++) {
                    View view6 = viewArr4[i12];
                    if (view6 != null && view6.getVisibility() == 0) {
                        view6.setAlpha(alpha);
                    }
                }
                return true;
            }
        });
        this.B.setControllerVisibilityListener(new si(this));
        YouTubeOverlay youTubeOverlay = (YouTubeOverlay) findViewById(R.id.youtube_overlay);
        this.E = youTubeOverlay;
        youTubeOverlay.E = new si(this);
        if (Q6 && i10 >= 30) {
            wt2.Y(this);
        }
        r1();
        l6 = this;
    }

    @Override // android.app.Activity
    public final void onDestroy() {
        if (!this.C) {
            b2(false);
        }
        if (l6 == this) {
            l6 = null;
        }
        l82 l82Var = this.r4;
        if (l82Var != null) {
            l82Var.b();
        }
        po2 po2Var = this.X4;
        if (po2Var != null) {
            po2Var.k();
            this.X4 = null;
        }
        super.onDestroy();
    }

    @Override // android.app.Activity
    public final boolean onGenericMotionEvent(MotionEvent motionEvent) {
        if (R6) {
            return true;
        }
        if ((motionEvent.getSource() & 2) != 0) {
            if (motionEvent.getAction() == 8) {
                float axisValue = motionEvent.getAxisValue(9);
                wt2.a(this, this.n, this.B, axisValue > 0.0f, Math.abs(axisValue) > 1.0f);
                return true;
            }
        } else if ((motionEvent.getSource() & 16777232) == 16777232 && motionEvent.getAction() == 2) {
            float axisValue2 = motionEvent.getAxisValue(14);
            for (int i = 0; i < motionEvent.getHistorySize(); i++) {
                float historicalAxisValue = motionEvent.getHistoricalAxisValue(14, i);
                if (Math.abs(historicalAxisValue) > axisValue2) {
                    axisValue2 = historicalAxisValue;
                }
            }
            if (Math.abs(axisValue2) == 1.0f) {
                wt2.a(this, this.n, this.B, axisValue2 < 0.0f, true);
            }
        }
        return super.onGenericMotionEvent(motionEvent);
    }

    /* JADX WARN: Code duplicated, block: B:122:0x0153  */
    /* JADX WARN: Code duplicated, block: B:125:0x0158 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:126:0x015a  */
    /* JADX WARN: Code duplicated, block: B:128:0x015e A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:129:0x0160  */
    /* JADX WARN: Code duplicated, block: B:131:0x0169  */
    /* JADX WARN: Code duplicated, block: B:133:0x016f  */
    /* JADX WARN: Code duplicated, block: B:135:0x0173  */
    /* JADX WARN: Code duplicated, block: B:137:0x017c  */
    /* JADX WARN: Code duplicated, block: B:140:0x0181  */
    /* JADX WARN: Code duplicated, block: B:150:0x019b  */
    /* JADX WARN: Code duplicated, block: B:152:0x019f  */
    /* JADX WARN: Code restructure failed: missing block: B:100:0x0107, code lost:
    
        if (Q2(true, r9.getRepeatCount() > 0) != false) goto L121;
     */
    /* JADX WARN: Code restructure failed: missing block: B:110:0x011d, code lost:
    
        if (Q2(false, r9.getRepeatCount() > 0) != false) goto L121;
     */
    @Override // android.app.Activity, android.view.KeyEvent.Callback
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean onKeyDown(int r8, android.view.KeyEvent r9) {
        /*
            Method dump skipped, instruction units count: 452
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.brouken.player.PlayerActivity.onKeyDown(int, android.view.KeyEvent):boolean");
    }

    /* JADX WARN: Code duplicated, block: B:21:0x002c  */
    /* JADX WARN: Code duplicated, block: B:23:0x0030  */
    /* JADX WARN: Code duplicated, block: B:26:0x0041  */
    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public final boolean onKeyUp(int i, KeyEvent keyEvent) {
        if (i == 21 || i == 22) {
            if (!this.F2) {
                cz czVar = this.B;
                czVar.postDelayed(czVar.C0, 1000L);
            }
            if (this.I2 >= 0) {
                cz czVar2 = this.B;
                wp1 wp1Var = this.O2;
                czVar2.removeCallbacks(wp1Var);
                this.B.postDelayed(wp1Var, 520L);
            }
        } else {
            if (i == 24 || i == 25) {
                cz czVar3 = this.B;
                czVar3.postDelayed(czVar3.C0, 800L);
                return true;
            }
            if (i == 89 || i == 90 || i == 104 || i == 105) {
                if (!this.F2) {
                    cz czVar4 = this.B;
                    czVar4.postDelayed(czVar4.C0, 1000L);
                }
                if (this.I2 >= 0) {
                    cz czVar5 = this.B;
                    wp1 wp1Var2 = this.O2;
                    czVar5.removeCallbacks(wp1Var2);
                    this.B.postDelayed(wp1Var2, 520L);
                }
            }
        }
        return super.onKeyUp(i, keyEvent);
    }

    @Override // android.app.Activity
    public final void onNewIntent(Intent intent) {
        String stringExtra;
        super.onNewIntent(intent);
        if (intent != null) {
            String action = intent.getAction();
            String type = intent.getType();
            Uri data = intent.getData();
            this.M4 = false;
            if (O0(intent)) {
                return;
            }
            if ("android.intent.action.VIEW".equals(action) && (data != null || vs1.b(intent))) {
                U2();
                setIntent(intent);
                Q0(intent);
                if (isFinishing()) {
                    return;
                }
                this.U0 = 1;
                Z0();
                return;
            }
            if ("android.intent.action.SEND".equals(action) && "text/plain".equals(type) && (stringExtra = intent.getStringExtra("android.intent.extra.TEXT")) != null) {
                Uri uri = Uri.parse(stringExtra);
                if (uri.isAbsolute()) {
                    U2();
                    m2();
                    this.H.x(this, uri, null);
                    P6 = true;
                    this.U0 = 1;
                    Z0();
                }
            }
        }
    }

    @Override // android.app.Activity
    public final void onPause() {
        super.onPause();
        if (this.C) {
            return;
        }
        D2();
    }

    @Override // android.app.Activity
    public final void onPictureInPictureModeChanged(boolean z, Configuration configuration) {
        super.onPictureInPictureModeChanged(z, configuration);
        this.G = z;
        s4();
        if (z) {
            this.B.c();
            W0();
            X0();
            b3(false);
            A4();
            x4();
            E4();
            this.B.setScale(1.0f);
            x5 x5Var = new x5(this, (byte) 5);
            this.m = x5Var;
            dz0.I(this, x5Var, new IntentFilter("media_control"));
            return;
        }
        E4();
        x4();
        A4();
        gu1 gu1Var = this.H;
        float f = gu1Var.k;
        if (f > 0.0f) {
            this.B.t(gu1Var.i, f);
        } else if (gu1Var.i == 4) {
            this.B.setScale(gu1Var.j);
        }
        x5 x5Var2 = this.m;
        if (x5Var2 != null) {
            unregisterReceiver(x5Var2);
            this.m = null;
        }
        this.B.setControllerAutoShow(true);
        vg0 vg0Var = k6;
        if (vg0Var != null) {
            if (vg0Var.J()) {
                wt2.i0(this, this.B, false);
            } else if (!R6) {
                this.B.j();
            }
        }
        if (R6) {
            A3();
        }
    }

    @Override // android.app.Activity
    public final void onResume() {
        super.onResume();
        this.B2 = true;
        n2();
        u2();
    }

    @Override // android.app.Activity
    public final void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        C2(bundle);
        E2(bundle);
    }

    /* JADX WARN: Code duplicated, block: B:62:0x0125  */
    @Override // android.app.Activity
    public final void onStart() {
        String str;
        super.onStart();
        Handler handler = this.M5;
        nq1 nq1Var = this.N5;
        handler.removeCallbacks(nq1Var);
        vs1 vs1Var = this.l3;
        if (vs1Var != null) {
            long j = vs1Var.g;
            if (j > 0) {
                handler.postDelayed(nq1Var, j);
            }
        }
        if (this.H0 != null) {
            boolean zM = gu1.m(this);
            boolean z = !zM && gu1.l(this);
            int i = -16777216;
            int iN = zM ? sj.n(new ContextThemeWrapper(this, gu1.a(this, true)), R.attr.accentFill, -16777216) : sj.n(new ContextThemeWrapper(this, gu1.a(this, false)), R.attr.accentInk, -1);
            int i2 = -419430401;
            if (zM) {
                i = -419430401;
                i2 = -570425344;
            } else if (!z) {
                i = -872415232;
            }
            new ColorStateList(new int[][]{new int[]{android.R.attr.state_selected}, new int[0]}, new int[]{iN, i2});
            xr xrVar = this.H0;
            if (xrVar.a != zM || xrVar.b != i || xrVar.n != iN) {
                this.I0 = true;
                if (p6) {
                    q6 = true;
                }
                if (k6 != null) {
                    b2(true);
                }
                recreate();
                return;
            }
        }
        this.Z2 = true;
        vg0 vg0Var = k6;
        if (vg0Var == null) {
            str = ", no player";
        } else {
            vg0Var.A1();
            if (vg0Var.w0.f != null) {
                str = ", player failed while away";
            } else {
                str = ", state " + M3(k6.C());
            }
        }
        wt2.K("onStart".concat(str));
        G4(this);
        if (Build.VERSION.SDK_INT >= 31) {
            this.B.removeCallbacks(this.W4);
            wt2.i0(this, this.B, true);
        }
        this.B.removeCallbacks(this.i0);
        this.A2 = false;
        if (R6) {
            A3();
        } else {
            this.B.j();
        }
        vg0 vg0Var2 = k6;
        if (vg0Var2 == null) {
            Z0();
        } else {
            vg0Var2.A1();
            if (vg0Var2.w0.f == null) {
                this.Y = null;
                if (this.j2) {
                    this.j2 = false;
                    vg0 vg0Var3 = k6;
                    t50 t50Var = (t50) vg0Var3.A0();
                    t50Var.getClass();
                    s50 s50Var = new s50(t50Var);
                    s50Var.j(1, false);
                    vg0Var3.q0(s50Var.b());
                }
                if (this.c0 != -9223372036854775807L) {
                    this.j0 = false;
                    this.B.postDelayed(this.k0, 3000L);
                }
                if (k6.C() == 2) {
                    this.O = 0;
                    if (this.B != null) {
                        M();
                        this.N = aq2.p.get();
                        this.B.postDelayed(this.P, 30000L);
                    }
                }
            } else if (this.Y == null) {
                vg0 vg0Var4 = k6;
                vg0Var4.A1();
                if (!Y1(vg0Var4.w0.f)) {
                    this.M4 = true;
                    b2(false);
                    Z0();
                }
            } else {
                this.M4 = true;
                b2(false);
                Z0();
            }
        }
        po2 po2Var = this.X4;
        if (po2Var != null && po2Var.B != null && !po2Var.K) {
            vl2 vl2Var = po2Var.n;
            vl2Var.g = false;
            vl2Var.b(SystemClock.elapsedRealtime() + 3000);
            po2Var.K = true;
            po2Var.m.postDelayed(po2Var.Q, 250L);
        }
        A4();
    }

    @Override // android.app.Activity
    public final void onStop() {
        po2 po2Var;
        vg0 vg0Var;
        wq1 wq1Var;
        super.onStop();
        this.Z2 = false;
        wt2.K("onStop".concat(isFinishing() ? ", finishing" : ""));
        this.M5.removeCallbacks(this.N5);
        if (Build.VERSION.SDK_INT >= 31) {
            this.B.removeCallbacks(this.W4);
        }
        this.B.u(0, null);
        po2 po2Var2 = this.X4;
        if (po2Var2 != null) {
            po2Var2.K = false;
            po2Var2.m.removeCallbacks(po2Var2.Q);
        }
        if (this.C) {
            return;
        }
        if (!isChangingConfigurations()) {
            U2();
        }
        this.B.removeCallbacks(this.b6);
        DisplayManager displayManager = this.T4;
        if (displayManager != null && (wq1Var = this.U4) != null) {
            displayManager.unregisterDisplayListener(wq1Var);
        }
        if (isFinishing() || (vg0Var = k6) == null || !m6) {
            if (isFinishing() && (po2Var = this.X4) != null) {
                po2Var.k();
            }
            b2(false);
            return;
        }
        this.C2 = false;
        vg0Var.k(false);
        br1 br1Var = this.D;
        if (br1Var != null && br1Var.d) {
            wt2.K("background: releasing the passthrough output");
            this.P2 = false;
            this.j2 = true;
            vg0 vg0Var2 = k6;
            t50 t50Var = (t50) vg0Var2.A0();
            t50Var.getClass();
            s50 s50Var = new s50(t50Var);
            s50Var.j(1, true);
            vg0Var2.q0(s50Var.b());
        }
        M();
        this.B.removeCallbacks(this.k0);
        if (Q6) {
            return;
        }
        this.B.postDelayed(this.i0, C6);
    }

    @Override // android.app.Activity
    public final void onUserInteraction() {
        super.onUserInteraction();
        this.a5 = 0;
    }

    @Override // android.app.Activity
    public final void onUserLeaveHint() {
        vg0 vg0Var;
        gu1 gu1Var = this.H;
        if (gu1Var != null && gu1Var.u && (vg0Var = k6) != null && vg0Var.J() && wt2.B(this)) {
            s0();
        } else {
            super.onUserLeaveHint();
        }
    }

    public final void p() {
        if (Q6 || this.I1 == null) {
            return;
        }
        boolean zL4 = L4();
        this.E1 = 0;
        this.F1 = true;
        q();
        qs2 qs2Var = this.W1;
        int iB = qs2Var.z() ? 0 : qs2Var.b(6.0f);
        int i = this.W1.i();
        boolean zB2 = B2();
        Drawable drawable = zB2 ? getDrawable(R.drawable.ic_high_quality_24dp) : null;
        if (drawable != null) {
            drawable.setBounds(0, 0, i, i);
        }
        this.x1.setCompoundDrawablesRelative(drawable, null, null, null);
        this.x1.setCompoundDrawableTintList(this.H0.p);
        TextView[] textViewArr = {this.x1, this.H1, this.I1};
        for (int i2 = 0; i2 < 3; i2++) {
            TextView textView = textViewArr[i2];
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) textView.getLayoutParams();
            layoutParams.width = zB2 ? this.W1.g() : -2;
            textView.setLayoutParams(layoutParams);
            textView.setCompoundDrawablePadding(zB2 ? 0 : this.W1.b(8.0f));
            if (zB2) {
                textView.setPadding((this.W1.g() - i) / 2, 0, 0, 0);
            } else {
                TextView textView2 = this.x1;
                qs2 qs2Var2 = this.W1;
                if (textView == textView2) {
                    int iB2 = qs2Var2.b(16.0f) + iB;
                    textView.setPadding(iB2, 0, iB2, 0);
                } else {
                    textView.setPadding(qs2Var2.b(12.0f) + iB, 0, this.W1.b(16.0f) + iB, 0);
                }
            }
        }
        this.R0.setMaxLines(zL4 ? 2 : 1);
        z4();
        o4();
        D4();
        w4();
    }

    public final boolean p1() {
        if (k6 != null) {
            zl0 zl0Var = (zl0) this.w4.a;
            nw0 nw0VarN = k6.E().a.listIterator(0);
            while (nw0VarN.hasNext()) {
                mq2 mq2Var = (mq2) nw0VarN.next();
                if (mq2Var.b.c == 3) {
                    for (int i = 0; i < mq2Var.a; i++) {
                        if (mq2Var.e[i] && !mq2Var.a(i).equals(zl0Var)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public final void p3(Dialog dialog) {
        this.U1 = true;
        this.B.c();
        m();
        dialog.setOnDismissListener(new j70(this, (byte) 1));
        dialog.show();
    }

    public final void p4() {
        if (k6 == null || this.p1 == null) {
            return;
        }
        boolean zF1 = f1();
        this.B.findViewById(R.id.exo_duration).setVisibility(!zF1 ? 0 : 8);
        if (!H6 && !this.G0) {
            this.p1.setVisibility(8);
            return;
        }
        TextView textView = this.p1;
        if (zF1) {
            textView.setText(R.string.time_live);
            this.p1.setTextColor(getColor(R.color.live_red));
            this.p1.setVisibility(0);
            return;
        }
        textView.setTextColor(this.J0.g);
        long duration = k6.getDuration();
        if (duration == -9223372036854775807L || duration <= 0) {
            this.p1.setVisibility(8);
            return;
        }
        long jMax = Math.max(0L, duration - k6.O0());
        float f = k6.getPlaybackParameters().a;
        if (f <= 0.0f) {
            f = 1.0f;
        }
        this.p1.setText(getString(R.string.time_ends_at, DateFormat.getTimeFormat(this).format(new Date(System.currentTimeMillis() + ((long) (jMax / f))))));
        this.p1.setVisibility(0);
    }

    public final void q() {
        TextView textView = this.H1;
        if (textView != null) {
            textView.setVisibility((!this.z1 || a0(textView)) ? 8 : 0);
        }
        View[] viewArr = {this.X1, this.Y1, this.x1};
        for (int i = 0; i < 3; i++) {
            View view = viewArr[i];
            if (view != null) {
                view.setVisibility((!J1(view) || a0(view)) ? 8 : 0);
            }
        }
    }

    public final void q0(eg0 eg0Var, v0 v0Var) {
        if (eg0Var != null) {
            v0Var.g("player.error_code", eg0Var.b());
        }
        Uri uriD0 = d0();
        if (wt2.E(uriD0)) {
            v0Var.m("media_uri", wt2.l0(uriD0));
        }
        v0Var.g("decoder.priority", String.valueOf(this.H.Q));
        v0Var.g("player.tunneling", String.valueOf(this.H.x));
        Boolean bool = z6;
        if (bool != null) {
            v0Var.g("decoder.ffmpeg", String.valueOf(bool));
        }
        vg0 vg0Var = k6;
        if (vg0Var == null) {
            return;
        }
        vg0Var.A1();
        zl0 zl0Var = vg0Var.V;
        vg0 vg0Var2 = k6;
        vg0Var2.A1();
        zl0 zl0Var2 = vg0Var2.W;
        if (zl0Var != null) {
            v0Var.g("media.video_mime", String.valueOf(zl0Var.p));
            v0Var.g("media.video_hw_decoder", String.valueOf(!S0(zl0Var)));
        }
        if (zl0Var2 != null) {
            v0Var.g("media.audio_mime", String.valueOf(zl0Var2.p));
        }
        String str = this.T;
        if (str != null) {
            v0Var.g("decoder.video_name", str);
        }
        String str2 = this.U;
        if (str2 != null) {
            v0Var.g("decoder.audio_name", str2);
        }
        v0Var.g("media.is_live", String.valueOf(k6.Q0()));
        br1 br1Var = this.D;
        if (br1Var != null) {
            v0Var.g("audio.sink_passthrough", String.valueOf(br1Var.d));
        }
        if (eg0Var != null) {
            MediaCodec.CodecException codecException = (MediaCodec.CodecException) A0(eg0Var, MediaCodec.CodecException.class);
            if (codecException != null) {
                v0Var.g("codec.error_code", "0x" + Integer.toHexString(codecException.getErrorCode()));
                v0Var.g("codec.transient", String.valueOf(codecException.isTransient()));
                v0Var.g("codec.recoverable", String.valueOf(codecException.isRecoverable()));
                v0Var.m("codec_diagnostic", String.valueOf(codecException.getDiagnosticInfo()));
            }
            t71 t71Var = (t71) A0(eg0Var, t71.class);
            if (t71Var != null) {
                v0Var.g("video.surface_valid", String.valueOf(t71Var.m));
            }
        }
        if (this.t0 != 0) {
            v0Var.g("player.reselect_ms_ago", String.valueOf(SystemClock.elapsedRealtime() - this.t0));
        }
        StringBuilder sb = new StringBuilder();
        g(sb);
        v0Var.m("player_state", sb.toString());
        v0Var.J(new io.sentry.a("trace.txt", null, wt2.h.matcher(wt2.V()).replaceAll("$1").getBytes(StandardCharsets.UTF_8)));
    }

    public final void q1() {
        int i;
        if (!this.H.y) {
            H0();
            return;
        }
        DisplayManager displayManager = this.T4;
        if (displayManager == null) {
            displayManager = (DisplayManager) getSystemService("display");
            this.T4 = displayManager;
        }
        wq1 wq1Var = this.U4;
        if (wq1Var == null) {
            wq1Var = new wq1(this);
            this.U4 = wq1Var;
        }
        displayManager.registerDisplayListener(wq1Var, null);
        float fO4 = O4();
        vg0 vg0Var = k6;
        byte b = 0;
        if (vg0Var != null) {
            nw0 nw0VarN = vg0Var.E().a.listIterator(0);
            i = 0;
            while (nw0VarN.hasNext()) {
                mq2 mq2Var = (mq2) nw0VarN.next();
                if (mq2Var.b.c == 2) {
                    for (int i2 = 0; i2 < mq2Var.a; i2++) {
                        if (mq2Var.e[i2]) {
                            zl0 zl0VarA = mq2Var.a(i2);
                            int i3 = wt2.D(zl0VarA) ? zl0VarA.x : zl0VarA.w;
                            if (i3 > i) {
                                i = i3;
                            }
                        }
                    }
                }
            }
            if (i <= 0) {
                vg0 vg0Var2 = k6;
                vg0Var2.A1();
                zl0 zl0Var = vg0Var2.V;
                if (zl0Var != null) {
                    i = wt2.D(zl0Var) ? zl0Var.x : zl0Var.w;
                }
            }
        } else {
            i = 0;
        }
        int iMax = Math.max(i, 0);
        if (fO4 > 0.0f) {
            String[] strArr = wt2.a;
            runOnUiThread(new st2(this, fO4, iMax, b));
        } else {
            Uri uriD0 = d0();
            String[] strArr2 = wt2.a;
            Thread thread = this.d3;
            if (thread != null) {
                thread.interrupt();
            }
            Thread thread2 = new Thread(new d21(this, uriD0, iMax));
            this.d3 = thread2;
            thread2.start();
        }
        this.B.removeCallbacks(this.b6);
        if (this.C2) {
            this.B.postDelayed(this.b6, ((long) this.H.F) + (this.h3 ? 6000L : 3000L));
        }
    }

    public final void q2(int i, int i2) {
        Integer numValueOf;
        String str;
        String str2;
        ArrayList arrayList = new ArrayList();
        for (yp2 yp2Var : b0()) {
            if (yp2Var.c == i2) {
                arrayList.add(yp2Var);
            }
        }
        Collections.sort(arrayList, new dc((byte) 21));
        nw0 nw0VarN = k6.E().a.listIterator(0);
        int i3 = 0;
        while (nw0VarN.hasNext()) {
            mq2 mq2Var = (mq2) nw0VarN.next();
            if (mq2Var.b.c == i) {
                for (int i4 = 0; i4 < mq2Var.a; i4++) {
                    zl0 zl0Var = mq2Var.b.d[i4];
                    String str3 = zl0Var.a;
                    String str4 = null;
                    if (str3 != null) {
                        try {
                            numValueOf = Integer.valueOf(Integer.parseInt(str3));
                        } catch (NumberFormatException unused) {
                            numValueOf = null;
                        }
                        if (numValueOf != null) {
                            for (yp2 yp2Var2 : b0()) {
                                int i5 = yp2Var2.a;
                                String str5 = yp2Var2.b;
                                if (i5 == numValueOf.intValue() && str5 != null && !str5.isEmpty()) {
                                    str4 = str5;
                                    break;
                                }
                            }
                        }
                    }
                    if (str4 == null && i3 < arrayList.size() && (str2 = ((yp2) arrayList.get(i3)).b) != null && !str2.isEmpty()) {
                        str4 = str2;
                    }
                    if (str4 != null && (str = zl0Var.a) != null) {
                        this.s.put(str, str4);
                    }
                    i3++;
                }
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r9v10, types: [boolean, byte] */
    /* JADX WARN: Type inference failed for: r9v17 */
    /* JADX WARN: Type inference failed for: r9v8 */
    public final void q3() {
        Object obj;
        HorizontalScrollView horizontalScrollView;
        int i;
        int i2;
        boolean z;
        char c;
        int i3;
        int i4;
        ?? r9;
        View view;
        byte b;
        gu1 gu1Var;
        final PlayerActivity playerActivity = this;
        vg0 vg0Var = k6;
        boolean z2 = vg0Var != null && vg0Var.a1() > 1;
        ArrayList arrayList = new ArrayList();
        if (z2) {
            for (int i5 = 0; i5 < k6.a1(); i5++) {
                arrayList.add(k6.Z0(i5));
            }
        } else {
            arrayList.addAll(playerActivity.C3);
        }
        if (arrayList.size() <= 1) {
            return;
        }
        int size = arrayList.size();
        int iV = z2 ? k6.V() : playerActivity.E3;
        Object[] objArr = new View[1];
        ContextThemeWrapper contextThemeWrapperE = s2.e(playerActivity);
        int iN = sj.n(contextThemeWrapperE, R.attr.colorOnSurface, -1);
        LinearLayout linearLayoutC = jf2.c(contextThemeWrapperE, 1);
        int iP = wt2.p(10);
        linearLayoutC.setPadding(iP, iP, iP, iP);
        boolean z3 = playerActivity.getResources().getConfiguration().orientation == 2 || playerActivity.W1.a == 4;
        final boolean z4 = z3 && ((gu1Var = playerActivity.H) == null || gu1Var.I);
        LinearLayout linearLayout = new LinearLayout(contextThemeWrapperE);
        linearLayout.setOrientation(0);
        linearLayout.setGravity(16);
        int i7 = 16;
        TextView textView = new TextView(contextThemeWrapperE);
        textView.setText(playerActivity.getString(R.string.playlist));
        textView.setTextColor(iN);
        textView.setTextSize(2, playerActivity.W1.y());
        textView.setTypeface(Typeface.DEFAULT_BOLD);
        textView.setPadding(wt2.p(8), wt2.p(10), 0, wt2.p(10));
        textView.setMaxLines(1);
        textView.setEllipsize(TextUtils.TruncateAt.END);
        linearLayout.addView(textView, new LinearLayout.LayoutParams(0, -2, 1.0f));
        TextView textView2 = new TextView(contextThemeWrapperE);
        textView2.setText(playerActivity.getResources().getQuantityString(R.plurals.playlist_items, size, Integer.valueOf(size)));
        textView2.setTextColor(sj.n(contextThemeWrapperE, R.attr.colorOnSurfaceVariant, contextThemeWrapperE.getColor(R.color.ink_secondary)));
        textView2.setTextSize(2, playerActivity.W1.t());
        textView2.setSingleLine(true);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        layoutParams.setMarginStart(wt2.p(8));
        linearLayout.addView(textView2, layoutParams);
        if (z3) {
            MaterialButton materialButtonM = s2.m(contextThemeWrapperE, playerActivity.W1, z4 ? R.drawable.ic_view_list_24dp : R.drawable.ic_view_grid_24dp, playerActivity.getString(z4 ? R.string.playlist_view_list : R.string.playlist_view_grid), false);
            materialButtonM.setOnClickListener(new View.OnClickListener() { // from class: jq1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    LoudnessEnhancer loudnessEnhancer = PlayerActivity.i6;
                    PlayerActivity playerActivity2 = this.l;
                    gu1 gu1Var2 = playerActivity2.H;
                    boolean z5 = !z4;
                    gu1Var2.I = z5;
                    SharedPreferences.Editor editorEdit = gu1Var2.b.edit();
                    editorEdit.putBoolean("playlistGrid", z5);
                    editorEdit.apply();
                    playerActivity2.q3();
                }
            });
            LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-2, -2);
            layoutParams2.setMarginStart(playerActivity.W1.b(16.0f));
            linearLayout.addView(materialButtonM, layoutParams2);
        }
        MaterialButton materialButtonM2 = s2.m(contextThemeWrapperE, playerActivity.W1, R.drawable.ic_close_24dp, contextThemeWrapperE.getString(R.string.error_close), false);
        materialButtonM2.setId(R.id.picker_close);
        linearLayout.addView(materialButtonM2);
        linearLayoutC.addView(linearLayout);
        int iB = playerActivity.W1.b(z3 ? 120.0f : 190.0f);
        String str = null;
        if (z4) {
            HorizontalScrollView horizontalScrollView2 = new HorizontalScrollView(contextThemeWrapperE);
            horizontalScrollView2.setHorizontalScrollBarEnabled(false);
            horizontalScrollView2.setHorizontalFadingEdgeEnabled(true);
            horizontalScrollView2.setFadingEdgeLength(playerActivity.W1.b(28.0f));
            horizontalScrollView2.setClipToPadding(false);
            LinearLayout linearLayout2 = new LinearLayout(contextThemeWrapperE);
            linearLayout2.setOrientation(0);
            horizontalScrollView2.addView(linearLayout2);
            linearLayoutC.addView(horizontalScrollView2);
            horizontalScrollView = horizontalScrollView2;
            obj = linearLayout2;
        } else {
            obj = null;
            horizontalScrollView = null;
        }
        int i8 = 0;
        Object obj2 = obj;
        Object[] objArr2 = objArr;
        HorizontalScrollView horizontalScrollView3 = horizontalScrollView;
        while (i8 < size) {
            z81 z81Var = (z81) arrayList.get(i8);
            h91 h91Var = z81Var.d;
            CharSequence charSequenceK0 = playerActivity.l3 != null ? wt2.k0(R3(playerActivity.N3, i8, str)) : str;
            if (charSequenceK0 == null) {
                if (h91Var == null) {
                    charSequenceK0 = str;
                } else {
                    charSequenceK0 = h91Var.f;
                    if (charSequenceK0 == null) {
                        charSequenceK0 = h91Var.a;
                    }
                }
            }
            if (charSequenceK0 == null || charSequenceK0.length() == 0) {
                charSequenceK0 = "Video " + (i8 + 1);
            }
            Uri uri = h91Var != null ? h91Var.n : null;
            if (uri == null && i8 == iV) {
                uri = playerActivity.f6;
            }
            u81 u81Var = z81Var.b;
            Uri uri2 = u81Var != null ? u81Var.a : null;
            Object obj3 = obj2;
            boolean z5 = i8 == iV;
            Uri uri3 = uri;
            if (z4) {
                LinearLayout linearLayoutC2 = jf2.c(contextThemeWrapperE, 1);
                int iP2 = wt2.p(6);
                linearLayoutC2.setPadding(iP2, iP2, iP2, wt2.p(8));
                i = 4;
                playerActivity = this;
                int i9 = i8;
                linearLayoutC2.addView(playerActivity.N1(contextThemeWrapperE, i8, uri3, uri2, z5), new LinearLayout.LayoutParams(-1, ((iB - (iP2 * 2)) * 9) / 16));
                TextView textView3 = new TextView(contextThemeWrapperE);
                textView3.setText(charSequenceK0);
                textView3.setTextColor(sj.n(contextThemeWrapperE, R.attr.colorOnSurface, -1));
                boolean z7 = z5;
                textView3.setTextSize(2, playerActivity.W1.q(15.0f, 16.0f, 17.0f, 18.0f));
                if (z7) {
                    textView3.setTypeface(Typeface.DEFAULT_BOLD);
                }
                LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(-1, -2);
                layoutParams3.topMargin = wt2.p(8);
                linearLayoutC2.addView(textView3, layoutParams3);
                textView3.setLines(1);
                textView3.setEllipsize(TextUtils.TruncateAt.END);
                i2 = i9;
                i4 = i7;
                z = z7;
                r9 = 1;
                c = 0;
                i3 = 0;
                view = linearLayoutC2;
            } else {
                i = 4;
                LinearLayout linearLayout3 = new LinearLayout(contextThemeWrapperE);
                linearLayout3.setOrientation(0);
                linearLayout3.setGravity(i7);
                linearLayout3.setPadding(wt2.p(8), wt2.p(7), wt2.p(10), wt2.p(7));
                linearLayout3.setMinimumHeight(playerActivity.W1.o());
                LinearLayout.LayoutParams layoutParams4 = new LinearLayout.LayoutParams(playerActivity.W1.b(88.0f), playerActivity.W1.b(50.0f));
                layoutParams4.setMarginEnd(wt2.p(12));
                layoutParams4.gravity = 16;
                i2 = i8;
                z = z5;
                contextThemeWrapperE = contextThemeWrapperE;
                linearLayout3.addView(playerActivity.N1(contextThemeWrapperE, i2, uri3, uri2, z), layoutParams4);
                LinearLayout linearLayout4 = new LinearLayout(contextThemeWrapperE);
                linearLayout4.setOrientation(1);
                TextView textView4 = new TextView(contextThemeWrapperE);
                textView4.setText(charSequenceK0);
                textView4.setTextColor(sj.n(contextThemeWrapperE, R.attr.colorOnSurface, -1));
                c = 0;
                textView4.setTextSize(2, playerActivity.W1.q(15.0f, 16.0f, 17.0f, 18.0f));
                if (z) {
                    textView4.setTypeface(Typeface.DEFAULT_BOLD);
                }
                linearLayout4.addView(textView4);
                i3 = 0;
                LinearLayout.LayoutParams layoutParams5 = new LinearLayout.LayoutParams(0, -2, 1.0f);
                i4 = 16;
                layoutParams5.gravity = 16;
                linearLayout3.addView(linearLayout4, layoutParams5);
                r9 = 1;
                s2.j(playerActivity, playerActivity.W1, linearLayout3, textView4);
                view = linearLayout3;
            }
            view.setClickable(r9);
            view.setFocusable((boolean) r9);
            view.setBackground(s2.t(contextThemeWrapperE, i3));
            view.setSelected(z);
            if (z) {
                objArr2[i3] = view;
            }
            view.setOnClickListener(new gk(playerActivity, i2, r9));
            if (z4 != 0) {
                b = -2;
                obj3.addView(view, new LinearLayout.LayoutParams(iB, -2));
            } else {
                b = -2;
                LinearLayout.LayoutParams layoutParams6 = new LinearLayout.LayoutParams(-1, -2);
                layoutParams6.bottomMargin = i2 == size + (-1) ? 0 : wt2.p(i);
                linearLayoutC.addView(view, layoutParams6);
            }
            i8 = i2 + 1;
            i7 = i4;
            z4 = z4;
            iV = iV;
            size = size;
            objArr2 = objArr2;
            horizontalScrollView3 = horizontalScrollView3;
            arrayList = arrayList;
            obj2 = obj3;
            str = null;
        }
        boolean z8 = z4;
        Object[] objArr3 = objArr2;
        HorizontalScrollView horizontalScrollView4 = horizontalScrollView3;
        ScrollView scrollView = new ScrollView(contextThemeWrapperE);
        scrollView.addView(linearLayoutC);
        scrollView.setPadding(0, wt2.p(8), 0, 0);
        Dialog dialog = playerActivity.L1;
        if (dialog != null) {
            dialog.dismiss();
        }
        Dialog dialog2 = new Dialog(playerActivity, android.R.style.Theme.Translucent.NoTitleBar);
        playerActivity.L1 = dialog2;
        s2.v(playerActivity, playerActivity.W1, dialog2, scrollView, z8);
        playerActivity.p3(playerActivity.L1);
        byte b2 = 0;
        GLSurfaceView gLSurfaceView = objArr3[0];
        if (gLSurfaceView != 0) {
            gLSurfaceView.post(new kq1(gLSurfaceView, horizontalScrollView4, scrollView, b2));
        }
    }

    public final void q4() {
        vg0 vg0Var = k6;
        if (vg0Var == null) {
            return;
        }
        int iV = vg0Var.V();
        vs1 vs1Var = this.l3;
        rs1 rs1Var = (vs1Var == null || iV < 0 || iV >= vs1Var.f.size()) ? null : (rs1) this.l3.f.get(iV);
        String strK0 = rs1Var == null ? null : wt2.k0(rs1Var.c);
        if (rs1Var != null && vs1.c(rs1Var.j, strK0)) {
            strK0 = null;
        }
        boolean z = (!L4() || strK0 == null || strK0.isEmpty()) ? false : true;
        if (z) {
            String strT0 = t0(null, rs1Var.i, rs1Var.j);
            if (strT0 != null) {
                strK0 = jf2.f(strT0, "\n", strK0);
            }
        } else {
            z81 z81VarZ = k6.z();
            CharSequence charSequence = z81VarZ == null ? null : z81VarZ.d.f;
            strK0 = charSequence == null ? null : charSequence.toString();
            if (strK0 == null && iV == this.F3) {
                strK0 = t0(null, this.H3, this.I3);
            }
        }
        this.l1.setMaxLines(z ? 2 : 1);
        X2(this.l1, strK0);
    }

    public final void r() {
        if (this.p == null) {
            return;
        }
        vs1 vs1Var = this.l3;
        String[] strArr = vs1Var == null ? null : vs1Var.d.d;
        List listD0 = (strArr == null || strArr.length <= 0) ? wt2.d0(this.H.W) : Arrays.asList(strArr);
        if (listD0.isEmpty()) {
            return;
        }
        z50 z50Var = this.p;
        s50 s50VarD = z50Var.d();
        s50VarD.q = iq2.g((String[]) listD0.toArray(new String[0]));
        z50Var.o(new t50(s50VarD));
    }

    public final void r0() {
        nz1.e(this.H.I0);
        String str = this.H.J0;
        String str2 = d22.d;
        String strTrim = str == null ? "" : str.trim();
        if (strTrim.isEmpty() || !(strTrim.startsWith("http://") || strTrim.startsWith("https://"))) {
            d22.d = "https://siaivo.isroot.in/lparty/";
        } else {
            int iIndexOf = strTrim.indexOf(63);
            if (iIndexOf != -1) {
                strTrim = strTrim.substring(0, iIndexOf);
            }
            d22.d = strTrim;
        }
        if (this.X4 == null) {
            this.X4 = new po2(new si(this));
        }
    }

    public final void r1() {
        if (this.H.M0) {
            long jCurrentTimeMillis = System.currentTimeMillis();
            gu1 gu1Var = this.H;
            if (jCurrentTimeMillis - gu1Var.N0 < 3600000) {
                return;
            }
            gu1Var.N0 = jCurrentTimeMillis;
            SharedPreferences.Editor editorEdit = gu1Var.b.edit();
            editorEdit.putLong("updateLastCheck", jCurrentTimeMillis);
            editorEdit.apply();
            dt2.a(new yo1(this, (byte) 0));
        }
    }

    public final void r2() {
        this.s.clear();
        if (k6 == null || b0().isEmpty()) {
            return;
        }
        q2(1, 2);
        q2(3, 3);
    }

    public final void r3(boolean z) {
        FrameLayout frameLayout = this.N0;
        if (!z) {
            frameLayout.setVisibility(8);
            return;
        }
        frameLayout.setVisibility(0);
        this.O0.setVisibility(8);
        this.Q0.setVisibility(8);
        this.P0.setVisibility(0);
    }

    public final void r4() {
        vg0 vg0Var;
        vg0 vg0Var2;
        boolean z = false;
        boolean z2 = (this.r2 || (vg0Var2 = k6) == null || !vg0Var2.f1()) ? false : true;
        if (!this.r2 && (vg0Var = k6) != null && vg0Var.e1()) {
            z = true;
        }
        ImageButton imageButton = this.p2;
        if (imageButton != null && imageButton.isEnabled() != z2) {
            wt2.a0(this, this.p2, z2);
        }
        ImageButton imageButton2 = this.q2;
        if (imageButton2 == null || imageButton2.isEnabled() == z) {
            return;
        }
        wt2.a0(this, this.q2, z);
    }

    public final void s() {
        int iF4;
        if (this.p != null && (iF4 = f4(2)) >= 0) {
            s50 s50VarD = this.p.d();
            if (((zl0) this.w4.a) == null || this.y4 == null) {
                SparseArray sparseArray = s50VarD.S;
                Map map = (Map) sparseArray.get(iF4);
                if (map != null && !map.isEmpty()) {
                    sparseArray.remove(iF4);
                }
            } else {
                s50VarD.q(iF4, new up2(this.y4), new u50(new int[]{this.z4}, 0));
            }
            s50VarD.p(iF4, false);
            z50 z50Var = this.p;
            z50Var.getClass();
            z50Var.o(new t50(s50VarD));
        }
    }

    public final void s0() {
        if (((AppOpsManager) getSystemService("appops")).checkOpNoThrow("android:picture_in_picture", Process.myUid(), getPackageName()) != 0) {
            Intent intent = new Intent("android.settings.PICTURE_IN_PICTURE_SETTINGS", Uri.fromParts("package", getPackageName(), null));
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
                return;
            }
            return;
        }
        if (k6 == null) {
            return;
        }
        this.B.setControllerAutoShow(false);
        this.B.c();
        vg0 vg0Var = k6;
        vg0Var.A1();
        zl0 zl0Var = vg0Var.V;
        if (zl0Var != null) {
            int i = zl0Var.x;
            int i2 = zl0Var.w;
            View videoSurfaceView = this.B.getVideoSurfaceView();
            if (videoSurfaceView instanceof SurfaceView) {
                ((SurfaceView) videoSurfaceView).getHolder().setFixedSize(i2, i);
            }
            Rational rational = wt2.D(zl0Var) ? new Rational(i, i2) : new Rational(i2, i);
            int i3 = Build.VERSION.SDK_INT;
            Rational rational2 = this.j3;
            Rational rational3 = this.i3;
            if (i3 >= 33 && getPackageManager().hasSystemFeature("android.software.expanded_picture_in_picture") && (rational.floatValue() > rational3.floatValue() || rational.floatValue() < rational2.floatValue())) {
                wb1.e(this.F).setExpandedAspectRatio(rational);
            }
            if (rational.floatValue() > rational3.floatValue()) {
                rational = rational3;
            } else if (rational.floatValue() < rational2.floatValue()) {
                rational = rational2;
            }
            wb1.e(this.F).setAspectRatio(rational);
        }
        enterPictureInPictureMode(wb1.e(this.F).build());
    }

    public final void s1() {
        String str;
        vg0 vg0Var;
        vg0 vg0Var2 = k6;
        if (vg0Var2 == null || this.d5 == null) {
            return;
        }
        gu1 gu1Var = this.H;
        boolean z = gu1Var.n0;
        if (!z || !gu1Var.s0 || this.v5) {
            if (this.v5) {
                str = "the launcher sent them";
            } else {
                str = !z ? "skipping is off" : "online search is off";
            }
            wt2.K("segments: not searching, ".concat(str));
            return;
        }
        int iV = vg0Var2.V();
        hl hlVarV1 = v1(iV);
        if (hlVarV1.m()) {
            wt2.K("segments: no title id, not searching");
            return;
        }
        N();
        int i = this.x5;
        final String str2 = (String) hlVarV1.n;
        final String str3 = (String) hlVarV1.o;
        final int i2 = hlVarV1.l;
        final int i3 = hlVarV1.m;
        final double dC0 = c0();
        final mb1 mb1Var = new mb1(i, iV, this);
        hn hnVar = ha2.a;
        Thread thread = new Thread(new Runnable() { // from class: r92
            @Override // java.lang.Runnable
            public final void run() {
                try {
                    ha2.w(str2, str3, i2, i3, dC0, new h32((Object) mb1Var, (byte) 4));
                } catch (Throwable th) {
                    wt2.K("segments: lookup failed " + th);
                    w3.b().u(th);
                }
            }
        }, "SegmentFinder");
        thread.setDaemon(true);
        thread.start();
        this.c3 = thread;
        if (this.y5 || (vg0Var = k6) == null || !vg0Var.e1()) {
            return;
        }
        int iV2 = k6.V();
        hl hlVarV2 = v1(k6.b1());
        if (hlVarV2.m()) {
            return;
        }
        hl hlVarV3 = v1(iV2);
        if (TextUtils.equals((String) hlVarV2.n, (String) hlVarV3.n) && TextUtils.equals((String) hlVarV2.o, (String) hlVarV3.o) && hlVarV2.l == hlVarV3.l && hlVarV2.m == hlVarV3.m) {
            return;
        }
        this.y5 = true;
        final String str4 = (String) hlVarV2.n;
        final String str5 = (String) hlVarV2.o;
        final int i4 = hlVarV2.l;
        final int i5 = hlVarV2.m;
        final wb1 wb1Var = new wb1((byte) 24);
        final double d = 0.0d;
        Thread thread2 = new Thread(new Runnable() { // from class: r92
            @Override // java.lang.Runnable
            public final void run() {
                try {
                    ha2.w(str4, str5, i4, i5, d, new h32((Object) wb1Var, (byte) 4));
                } catch (Throwable th) {
                    wt2.K("segments: lookup failed " + th);
                    w3.b().u(th);
                }
            }
        }, "SegmentFinder");
        thread2.setDaemon(true);
        thread2.start();
    }

    public final boolean s2(long j) {
        if (this.X4 != null || this.W5 != 0 || this.Y5 || d1()) {
            return false;
        }
        long duration = k6.getDuration();
        if ((duration != -9223372036854775807L && duration - j < 30000) || P4() < 20) {
            return false;
        }
        Uri uriD0 = d0();
        String string = uriD0 != null ? uriD0.toString() : null;
        if (string == null || string.equals(D6) || !Y1(null)) {
            return false;
        }
        wt2.K("video freeze: the screen was started over");
        D6 = string;
        E6 = j;
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void s3() {
        String string;
        String string2;
        int i;
        TextView textView;
        int i2 = 0;
        if (k6 == null) {
            o3(getString(R.string.quality_unavailable), false, R.drawable.ic_high_quality_24dp);
            return;
        }
        ArrayList arrayListJ = J();
        if (arrayListJ.size() < 2) {
            o3(getString(R.string.quality_unavailable), false, R.drawable.ic_high_quality_24dp);
            return;
        }
        int iT2 = T2(arrayListJ);
        View[] viewArr = new View[1];
        ContextThemeWrapper contextThemeWrapperE = s2.e(this);
        int iN = sj.n(contextThemeWrapperE, R.attr.colorOnSurface, -1);
        int iN2 = sj.n(contextThemeWrapperE, R.attr.colorSecondaryContainer, contextThemeWrapperE.getColor(R.color.brand_container));
        int iN3 = sj.n(contextThemeWrapperE, R.attr.colorOnSecondaryContainer, -1);
        LinearLayout linearLayoutC = jf2.c(contextThemeWrapperE, 1);
        int iP = wt2.p(10);
        linearLayoutC.setPadding(iP, iP, iP, iP);
        TextView textView2 = new TextView(contextThemeWrapperE);
        textView2.setText(getString(R.string.quality_title));
        textView2.setTextColor(iN);
        textView2.setTextSize(2, this.W1.y());
        textView2.setTypeface(Typeface.DEFAULT_BOLD);
        int i3 = 1;
        textView2.setPadding(wt2.p(10), wt2.p(10), wt2.p(10), wt2.p(10));
        linearLayoutC.addView(s2.s(contextThemeWrapperE, this.W1, textView2, null));
        View view = new View(contextThemeWrapperE);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, wt2.p(1));
        layoutParams.bottomMargin = wt2.p(4);
        view.setLayoutParams(layoutParams);
        view.setBackgroundColor(sj.n(contextThemeWrapperE, R.attr.colorOutlineVariant, contextThemeWrapperE.getColor(R.color.divider)));
        linearLayoutC.addView(view);
        int i4 = 0;
        while (i4 < arrayListJ.size()) {
            jr1 jr1Var = (jr1) arrayListJ.get(i4);
            int i5 = i4 == iT2 ? i3 : i2;
            LinearLayout linearLayout = new LinearLayout(contextThemeWrapperE);
            linearLayout.setOrientation(i2);
            linearLayout.setGravity(16);
            int i7 = i2;
            ArrayList arrayList = arrayListJ;
            linearLayout.setPadding(wt2.p(12), wt2.p(10), wt2.p(12), wt2.p(10));
            boolean z = i3;
            linearLayout.setClickable(z);
            linearLayout.setFocusable(z);
            linearLayout.setMinimumHeight(this.W1.o());
            linearLayout.setBackground(s2.t(contextThemeWrapperE, i5 != 0 ? iN2 : i7));
            if (i5 != 0) {
                viewArr[i7] = linearLayout;
            }
            LinearLayout linearLayoutC2 = jf2.c(contextThemeWrapperE, 1);
            int i8 = iT2;
            LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(i7, -2, 1.0f);
            layoutParams2.gravity = 16;
            linearLayoutC2.setLayoutParams(layoutParams2);
            TextView textView3 = new TextView(contextThemeWrapperE);
            byte b = jr1Var.d;
            String str = jr1Var.c;
            if (b != 0) {
                string = b != 1 ? jr1Var.a : getString(R.string.quality_maximum);
            } else {
                string = getString(R.string.quality_auto);
            }
            textView3.setText(string);
            textView3.setTextColor(i5 != 0 ? iN3 : iN);
            textView3.setTextSize(2, this.W1.s());
            if (i5 != 0) {
                textView3.setTypeface(Typeface.DEFAULT_BOLD);
            }
            linearLayoutC2.addView(textView3);
            byte b2 = jr1Var.d;
            if (b2 != 0) {
                string2 = b2 != 1 ? jr1Var.b : getString(R.string.quality_maximum_badge);
            } else {
                string2 = getString(R.string.quality_auto_description);
            }
            if (string2 == null || string2.isEmpty()) {
                i = 2;
                textView = null;
            } else {
                textView = new TextView(contextThemeWrapperE);
                textView.setText(string2);
                textView.setTextColor(sj.n(contextThemeWrapperE, R.attr.colorOnSurfaceVariant, contextThemeWrapperE.getColor(R.color.ink_secondary)));
                i = 2;
                textView.setTextSize(2, this.W1.t());
                linearLayoutC2.addView(textView);
            }
            linearLayout.addView(linearLayoutC2);
            qs2 qs2Var = this.W1;
            TextView[] textViewArr = new TextView[i];
            textViewArr[0] = textView3;
            textViewArr[1] = textView;
            s2.j(this, qs2Var, linearLayout, textViewArr);
            if (str != null && !str.isEmpty()) {
                TextView textView4 = new TextView(contextThemeWrapperE);
                textView4.setText(str);
                textView4.setTextColor(sj.n(contextThemeWrapperE, R.attr.colorOnSurfaceVariant, contextThemeWrapperE.getColor(R.color.ink_secondary)));
                textView4.setTextSize(2, this.W1.t());
                textView4.setGravity(8388629);
                textView4.setSingleLine(true);
                LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(-2, -2);
                layoutParams3.setMarginEnd(wt2.p(10));
                layoutParams3.gravity = 16;
                textView4.setLayoutParams(layoutParams3);
                linearLayout.addView(textView4);
            }
            linearLayout.setOnClickListener(new xk(this, jr1Var, (byte) 3));
            linearLayoutC.addView(linearLayout);
            i4++;
            arrayListJ = arrayList;
            iT2 = i8;
            i2 = 0;
            i3 = 1;
        }
        ScrollView scrollView = new ScrollView(contextThemeWrapperE);
        scrollView.addView(linearLayoutC);
        scrollView.setPadding(0, wt2.p(8), 0, 0);
        Dialog dialog = this.K1;
        if (dialog != null) {
            dialog.dismiss();
        }
        Dialog dialog2 = new Dialog(this, android.R.style.Theme.Translucent.NoTitleBar);
        this.K1 = dialog2;
        s2.v(this, this.W1, dialog2, scrollView, false);
        p3(this.K1);
        View view2 = viewArr[0];
        if (view2 != null) {
            view2.post(new d4(viewArr, (byte) 1));
        }
    }

    public final void s4() {
        cz czVar;
        if (this.E0 == null) {
            return;
        }
        this.B.invalidate();
        boolean z = this.F0 && !this.G && this.u0;
        if (z == this.G0) {
            return;
        }
        this.G0 = z;
        if (!z || (czVar = this.B) == null) {
            return;
        }
        nq1 nq1Var = this.L5;
        czVar.removeCallbacks(nq1Var);
        this.B.post(nq1Var);
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public final void t() {
        vg0 vg0Var = k6;
        if (vg0Var != null) {
            boolean z = this.Y5;
            vg0Var.A1();
            if (vg0Var.R == z) {
                return;
            }
            vg0Var.R = z;
            vg0Var.l.r.b(23, z ? 1 : 0, 0).b();
        }
    }

    public final String t0(String str, int i, int i2) {
        ArrayList arrayList = new ArrayList();
        if (i > 0) {
            arrayList.add(getString(R.string.subtitle_search_season, Integer.valueOf(i)));
        }
        if (i2 > 0) {
            arrayList.add(getString(R.string.subtitle_search_episode, Integer.valueOf(i2)));
        }
        if (str != null && !str.isEmpty() && !vs1.c(i2, str)) {
            arrayList.add(str);
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        return TextUtils.join(" · ", arrayList);
    }

    public final void t1(nq2 nq2Var, final boolean z, ArrayList arrayList, boolean z2) {
        List listD0;
        List listSubList;
        List<String> listSingletonList;
        Uri uri;
        if (k6 == null || nq2Var.a.isEmpty() || k6.C() == 1) {
            return;
        }
        if (!z && f1()) {
            wt2.K("subtitles: live stream, not searching");
            return;
        }
        boolean z3 = false;
        boolean z4 = (this.H.Z || z) ? false : true;
        if (arrayList == null || arrayList.isEmpty()) {
            gu1 gu1Var = this.H;
            listD0 = wt2.d0(z2 ? gu1Var.Y : gu1Var.X);
        } else {
            listD0 = arrayList;
        }
        if (listD0.isEmpty()) {
            if (z) {
                String language = Locale.getDefault().getLanguage();
                String[] strArr = wt2.a;
                vp2 vp2Var = vp2.f;
                String strD = ha1.D(language);
                if (strD == null) {
                    wt2.K("subtitles: no language list and no device language, not searching");
                    return;
                }
                listD0 = Collections.singletonList(strD);
            } else if (z2 || wt2.d0(this.H.Y).isEmpty()) {
                wt2.K("subtitles: no subtitle language set, not searching".concat(this.H.Z ? "" : " (online search is off too)"));
                return;
            }
        }
        if (z2) {
            listSubList = Collections.EMPTY_LIST;
        } else {
            HashSet hashSet = new HashSet();
            nw0 nw0VarN = nq2Var.a.listIterator(0);
            while (nw0VarN.hasNext()) {
                mq2 mq2Var = (mq2) nw0VarN.next();
                if (mq2Var.b.c == 3) {
                    for (int i = 0; i < mq2Var.a; i++) {
                        zl0 zl0VarA = mq2Var.a(i);
                        if (!h1(zl0VarA)) {
                            String str = zl0VarA.d;
                            String[] strArr2 = wt2.a;
                            vp2 vp2Var2 = vp2.f;
                            String strD2 = ha1.D(str);
                            if (strD2 == null) {
                                String str2 = zl0VarA.b;
                                strD2 = wt2.I((str2 == null || !str2.matches("[a-z]{3}\\d{1,2}")) ? k4(zl0VarA) : zl0VarA.b, listD0);
                            }
                            if (strD2 != null) {
                                hashSet.add(strD2);
                            }
                        }
                    }
                }
            }
            int size = listD0.size();
            for (int i2 = 0; i2 < listD0.size(); i2++) {
                if (hashSet.contains(listD0.get(i2))) {
                    size = i2;
                    break;
                }
            }
            listSubList = (size == 0 || (this.H.a0 && size < listD0.size())) ? Collections.EMPTY_LIST : listD0.subList(0, size);
        }
        if (z2) {
            listSingletonList = listD0;
        } else if (arrayList == null && this.r4 != null && L2() && !J2() && ((uri = this.H.c) == null || !uri.equals(this.C4))) {
            ArrayList arrayListD0 = wt2.d0(this.H.Y);
            if (!arrayListD0.isEmpty()) {
                HashSet hashSet2 = new HashSet();
                Iterator it = w0().iterator();
                while (it.hasNext()) {
                    String strO = ij0.o((Uri) it.next());
                    String[] strArr3 = wt2.a;
                    vp2 vp2Var3 = vp2.f;
                    String strD3 = ha1.D(strO);
                    if (strD3 != null) {
                        hashSet2.add(strD3);
                    }
                }
                String strO1 = o1();
                Iterator it2 = arrayListD0.iterator();
                while (true) {
                    if (!it2.hasNext()) {
                        listSingletonList = Collections.EMPTY_LIST;
                        break;
                    }
                    String str3 = (String) it2.next();
                    if (!hashSet2.contains(str3) && !str3.equals(strO1)) {
                        listSingletonList = Collections.singletonList(str3);
                        break;
                    }
                }
            } else {
                listSingletonList = Collections.EMPTY_LIST;
            }
        } else {
            listSingletonList = Collections.EMPTY_LIST;
        }
        if (!listSubList.isEmpty() && listSingletonList.contains(listSubList.get(0))) {
            ArrayList arrayList2 = new ArrayList(listSingletonList);
            arrayList2.remove(listSubList.get(0));
            listSingletonList = arrayList2;
        }
        if (listSubList.isEmpty() && listSingletonList.isEmpty()) {
            if (!z) {
                wt2.K("subtitles: nothing wanted (want=" + listD0 + " already satisfied by the tracks present), not searching");
                return;
            }
            if (z2) {
                listSingletonList = listD0;
                listD0 = listSubList;
            }
        } else {
            listD0 = listSubList;
        }
        StringBuilder sb = new StringBuilder("subtitles: search ");
        sb.append(z ? "manual" : "auto");
        sb.append(", online=");
        sb.append(this.H.Z);
        sb.append(", strict=");
        sb.append(this.H.a0);
        sb.append(", translate=");
        sb.append(this.H.c0);
        sb.append(", sources=os rest stremio shegu, list=");
        this.H.getClass();
        this.H.getClass();
        this.H.getClass();
        this.H.getClass();
        sb.append(this.H.X);
        sb.append("/");
        sb.append(this.H.Y);
        wt2.K(sb.toString());
        final hl hlVarV1 = v1(k6.V());
        if (hlVarV1.m()) {
            wt2.K("subtitles: no title id, not searching");
            if (z) {
                String string = getString(R.string.subtitle_search_none);
                StringBuilder sb2 = new StringBuilder();
                g(sb2);
                z3(string, sb2.toString().trim(), null);
                return;
            }
            return;
        }
        ArrayList arrayList3 = new ArrayList(listD0);
        for (String str4 : listSingletonList) {
            if (!arrayList3.contains(str4)) {
                arrayList3.add(str4);
            }
        }
        Iterator it3 = new ArrayList(arrayList3).iterator();
        while (it3.hasNext()) {
            for (String str5 : m4((String) it3.next())) {
                if (!arrayList3.contains(str5)) {
                    arrayList3.add(str5);
                }
            }
        }
        StringBuilder sb3 = new StringBuilder(((String) hlVarV1.n) + "|" + ((String) hlVarV1.o) + "|" + hlVarV1.l + "|" + hlVarV1.m);
        sb3.append("|");
        sb3.append(arrayList3);
        sb3.append("|1234");
        this.H.getClass();
        this.H.getClass();
        this.H.getClass();
        this.H.getClass();
        final String string2 = sb3.toString();
        if (z) {
            b7.remove(string2);
        } else {
            if (string2.equals(this.z5)) {
                return;
            }
            Long l = (Long) b7.get(string2);
            if (l != null && System.currentTimeMillis() - l.longValue() < 1800000) {
                return;
            }
        }
        final int iV = k6.V();
        ArrayList arrayListU3 = U3(hlVarV1);
        final String str6 = (String) arrayListU3.get(0);
        P();
        this.z5 = string2;
        boolean z5 = (z || listD0.isEmpty() || !A(listD0, arrayListU3, iV, false)) ? false : true;
        if (!z && !listSingletonList.isEmpty() && A(listSingletonList, arrayListU3, iV, true)) {
            z3 = true;
        }
        if (z5) {
            listD0 = Collections.EMPTY_LIST;
        }
        final List list = listD0;
        if (z3) {
            listSingletonList = Collections.EMPTY_LIST;
        }
        final List list2 = listSingletonList;
        if (list.isEmpty() && list2.isEmpty()) {
            wt2.K("subtitles: already cached for this item, not searching");
            return;
        }
        if (z4) {
            wt2.K("subtitles: online search is off, only the cache was asked");
            return;
        }
        final int i3 = this.B5;
        final Uri uri2 = this.H.c;
        final long duration = k6.getDuration();
        this.H.getClass();
        final h32 h32Var = this.x;
        if (z) {
            X3(R.string.subtitle_search_searching);
        }
        Thread thread = new Thread(new Runnable() { // from class: np1
            @Override // java.lang.Runnable
            public final void run() {
                hl hlVar;
                dg1 dg1Var;
                PlayerActivity playerActivity = this.l;
                q00 q00Var = h32Var;
                Uri uri3 = uri2;
                long j = duration;
                List list3 = list;
                hl hlVar2 = hlVarV1;
                String str7 = str6;
                int i4 = i3;
                int i5 = iV;
                boolean z7 = z;
                List list4 = list2;
                String str8 = string2;
                LoudnessEnhancer loudnessEnhancer = PlayerActivity.i6;
                AtomicBoolean atomicBoolean = new AtomicBoolean();
                uk2 uk2VarY3 = null;
                try {
                    dg1 dg1VarA = eg1.a(q00Var, uri3, j);
                    if (list3.isEmpty()) {
                        hlVar = hlVar2;
                        dg1Var = dg1VarA;
                    } else {
                        hlVar = hlVar2;
                        dg1Var = dg1VarA;
                        uk2VarY3 = playerActivity.Y3(hlVar, list3, str7, i4, i5, uri3, j, dg1Var, atomicBoolean, false, z7);
                    }
                    if (!list4.isEmpty() && i4 == playerActivity.B5 && !Thread.currentThread().isInterrupted()) {
                        uk2 uk2VarY4 = playerActivity.Y3(hlVar, list4, str7, i4, i5, uri3, j, dg1Var, atomicBoolean, true, z7);
                        if (uk2VarY3 == null) {
                            uk2VarY3 = uk2VarY4;
                        }
                    }
                } catch (Throwable th) {
                    wt2.K("subtitles: search failed " + th);
                }
                if (uk2VarY3 == null && atomicBoolean.get() && !Thread.currentThread().isInterrupted()) {
                    PlayerActivity.b7.put(str8, Long.valueOf(System.currentTimeMillis()));
                }
                boolean z8 = uk2VarY3 == null;
                if (z7 && z8 && !Thread.currentThread().isInterrupted()) {
                    playerActivity.runOnUiThread(new wo1(playerActivity, i4, (byte) 2));
                }
            }
        }, "SubtitleSearch");
        thread.setDaemon(true);
        this.A5 = thread;
        thread.start();
    }

    public final void t2(Bundle bundle) {
        Bundle bundle2;
        long[] jArr;
        if (bundle == null || !this.k3 || (bundle2 = bundle.getBundle("apiSession")) == null) {
            return;
        }
        String string = bundle2.getString("uri");
        Uri uri = string == null ? null : Uri.parse(string);
        int i = bundle2.getInt("index");
        if (i >= 0) {
            ArrayList arrayList = this.C3;
            if (i < arrayList.size()) {
                this.E3 = i;
                if (uri != null) {
                    arrayList.set(i, J3(i, uri));
                }
            }
        }
        if (uri != null) {
            this.H.c = uri;
        }
        long[] longArray = bundle2.getLongArray("episodePositions");
        if (longArray != null && (jArr = this.G3) != null && longArray.length == jArr.length) {
            this.G3 = longArray;
        }
        this.i4 = bundle2.getInt("stickyQuality");
        this.j4 = bundle2.getString("stickyVoice");
        if (this.l3 != null && bundle2.containsKey("playlistSession")) {
            Bundle bundle3 = bundle2.getBundle("playlistSession");
            int size = this.l3.f.size();
            c7 c7Var = new c7(size);
            long[] longArray2 = bundle3 != null ? bundle3.getLongArray("durations") : null;
            if (longArray2 != null && longArray2.length == size) {
                System.arraycopy(longArray2, 0, (long[]) c7Var.c, 0, size);
                boolean[] booleanArray = bundle3.getBooleanArray("finished");
                if (booleanArray != null && booleanArray.length == size) {
                    System.arraycopy(booleanArray, 0, (boolean[]) c7Var.d, 0, size);
                }
                int[] intArray = bundle3.getIntArray("visitIndex");
                long[] longArray3 = bundle3.getLongArray("visitStarted");
                long[] longArray4 = bundle3.getLongArray("visitEnded");
                long[] longArray5 = bundle3.getLongArray("visitPosition");
                long[] longArray6 = bundle3.getLongArray("visitDuration");
                if (intArray != null && longArray3 != null && longArray5 != null && longArray6 != null) {
                    for (int i2 = 0; i2 < intArray.length; i2++) {
                        b7 b7Var = new b7(longArray3[i2], intArray[i2]);
                        b7Var.d = longArray5[i2];
                        if (longArray4 != null && longArray4.length == intArray.length) {
                            b7Var.c = longArray4[i2];
                        }
                        b7Var.e = longArray6[i2];
                        ((ArrayList) c7Var.b).add(b7Var);
                    }
                }
                c7Var.a = bundle3.getBoolean("everPlayed");
                c7Var.e = bundle3.getString("error");
                c7Var.f = vp2.a(bundle3.getBundle("audio"));
                c7Var.g = vp2.a(bundle3.getBundle("subtitle"));
                String[] stringArray = bundle3.getStringArray("audioChosenBy");
                String[] strArr = (String[]) c7Var.h;
                if (stringArray != null && stringArray.length == strArr.length) {
                    System.arraycopy(stringArray, 0, strArr, 0, strArr.length);
                }
                String[] stringArray2 = bundle3.getStringArray("subtitleChosenBy");
                String[] strArr2 = (String[]) c7Var.i;
                if (stringArray2 != null && stringArray2.length == strArr2.length) {
                    System.arraycopy(stringArray2, 0, strArr2, 0, strArr2.length);
                }
            }
            this.m3 = c7Var;
        }
        this.H.l = bundle2.getInt("aspectClass", -1);
        this.H.y(bundle2.getString("audioTrack"), bundle2.getString("subtitleTrack"), bundle2.getInt("resizeMode"), bundle2.getFloat("scale"), bundle2.getFloat("aspectRatio"), bundle2.getFloat("speed"));
        if (bundle2.containsKey("position")) {
            this.H.z(bundle2.getLong("position"));
        }
    }

    public final void t3(boolean z) {
        CustomDefaultTimeBar customDefaultTimeBar = this.y2;
        xr xrVar = this.H0;
        customDefaultTimeBar.setScrubberColor(z ? xrVar.e : xrVar.n);
        View viewFindViewById = findViewById(R.id.plate_row);
        viewFindViewById.animate().cancel();
        viewFindViewById.animate().alpha(z ? 0.38f : 1.0f).setDuration(wt2.C(this) ? 0L : 200L);
    }

    public final void t4() {
        ImageView imageView = this.o1;
        if (imageView == null) {
            return;
        }
        imageView.setVisibility((!L4() && this.m1.getVisibility() == 0 && this.n1.getVisibility() == 0) ? 0 : 8);
    }

    public final void u() {
        jc0 jc0Var = this.n4;
        if (jc0Var != null) {
            jc0Var.k = o0();
        }
        ej ejVar = j6;
        if (ejVar != null) {
            boolean zR = R();
            int i = ejVar.m;
            ejVar.j = zR;
            ejVar.m = i;
        }
    }

    public final String u0(eg0 eg0Var) {
        StringBuilder sb = new StringBuilder("Error code: ");
        sb.append(eg0Var.b());
        Uri uriD0 = d0();
        if (uriD0 != null) {
            sb.append("\nMedia: ");
            sb.append(wt2.W(uriD0, this.H.L0));
        }
        String strX = X(eg0Var);
        if (!strX.isEmpty()) {
            sb.append("\nCodec: ");
            sb.append(strX);
        }
        g(sb);
        sb.append("\n\n");
        StringWriter stringWriter = new StringWriter();
        eg0Var.printStackTrace(new PrintWriter(stringWriter));
        sb.append(stringWriter.toString());
        return sb.toString();
    }

    /* JADX WARN: Code duplicated, block: B:15:0x0058  */
    public final j90[] u1() {
        j90 j90VarH;
        j90 j90Var;
        fj1 fj1Var;
        fj1 fj1Var2;
        fj1 fj1Var3;
        fj1 fj1VarC;
        j90 j90Var2 = null;
        if ("file".equals(this.H.c.getScheme())) {
            File file = new File(this.H.c.getSchemeSpecificPart());
            File parentFile = file.getParentFile();
            if (parentFile != null) {
                return new j90[]{j90.a(file), j90.a(parentFile)};
            }
        } else {
            boolean zF = gj1.f(this.H.c);
            gu1 gu1Var = this.H;
            if (zF) {
                Uri uri = gu1Var.c;
                if (f90.d(uri)) {
                    if (uri.getQueryParameter("id") == null) {
                        fj1Var2 = null;
                    } else {
                        String strB = f90.b(uri);
                        String queryParameter = uri.getQueryParameter("p");
                        List<String> pathSegments = uri.getPathSegments();
                        if (pathSegments.size() < 2 || queryParameter == null || queryParameter.isEmpty() || "0".equals(queryParameter)) {
                            fj1Var3 = new fj1(null, new Uri.Builder().scheme("dlna").encodedAuthority(uri.getAuthority()).appendQueryParameter("c", strB).appendQueryParameter("id", "0").build(), uri.getHost(), true, 0L, 0L, f90.b);
                            fj1Var2 = fj1Var3;
                        } else {
                            Uri.Builder builderEncodedAuthority = new Uri.Builder().scheme("dlna").encodedAuthority(uri.getAuthority());
                            for (int i = 0; i < pathSegments.size() - 1; i++) {
                                builderEncodedAuthority.appendPath(pathSegments.get(i));
                            }
                            fj1Var2 = new fj1(null, builderEncodedAuthority.appendQueryParameter("c", strB).appendQueryParameter("id", queryParameter).build(), pathSegments.get(pathSegments.size() - 2), true, 0L, 0L, f90.b);
                        }
                    }
                } else if (lp2.e(uri)) {
                    if (lp2.d(uri) == null) {
                        fj1Var2 = null;
                    } else {
                        String strA = lp2.a(uri);
                        List<String> pathSegments2 = uri.getPathSegments();
                        if (uri.getQueryParameter("i") == null || pathSegments2.size() < 2) {
                            Uri uri2 = Uri.parse(strA);
                            Uri.Builder builderEncodedAuthority2 = new Uri.Builder().scheme("https".equals(uri2.getScheme()) ? "torrs" : "torr").encodedAuthority(uri2.getEncodedAuthority() == null ? "" : uri2.getEncodedAuthority());
                            Iterator<String> it = uri2.getPathSegments().iterator();
                            while (it.hasNext()) {
                                builderEncodedAuthority2.appendPath(it.next());
                            }
                            Uri uriBuild = builderEncodedAuthority2.build();
                            List<String> pathSegments3 = uriBuild.getPathSegments();
                            fj1VarC = lp2.c(this, uriBuild, pathSegments3.isEmpty() ? uriBuild.getHost() : pathSegments3.get(pathSegments3.size() - 1));
                        } else {
                            Uri.Builder builderEncodedAuthority3 = new Uri.Builder().scheme(uri.getScheme()).encodedAuthority(uri.getAuthority());
                            for (int i2 = 0; i2 < pathSegments2.size() - 1; i2++) {
                                builderEncodedAuthority3.appendPath(pathSegments2.get(i2));
                            }
                            fj1VarC = lp2.c(this, builderEncodedAuthority3.appendQueryParameter("b", strA).appendQueryParameter("h", lp2.d(uri)).build(), pathSegments2.get(pathSegments2.size() - 2));
                        }
                        fj1Var2 = fj1VarC;
                    }
                } else if (ha1.X(uri)) {
                    Uri uriA = gj1.a(uri);
                    if (uriA == null) {
                        fj1Var2 = null;
                    } else {
                        List<String> pathSegments4 = uriA.getPathSegments();
                        fj1Var3 = new fj1(null, uriA, pathSegments4.isEmpty() ? String.valueOf(uriA.getHost()) : pathSegments4.get(pathSegments4.size() - 1), true, 0L, 0L, new e10((Activity) this, (byte) 5));
                        fj1Var2 = fj1Var3;
                    }
                } else {
                    if (bc2.e(uri)) {
                        Uri uriA2 = gj1.a(uri);
                        if (uriA2 != null) {
                            List<String> pathSegments5 = uriA2.getPathSegments();
                            fj1Var3 = new fj1(null, uriA2, pathSegments5.isEmpty() ? String.valueOf(uriA2.getHost()) : pathSegments5.get(pathSegments5.size() - 1), true, 0L, 0L, new e10((Activity) this, (byte) 12));
                            fj1Var2 = fj1Var3;
                        }
                    } else if (f10.b(uri)) {
                        List<String> pathSegments6 = uri.getPathSegments();
                        if (!pathSegments6.isEmpty()) {
                            Uri.Builder builderPath = uri.buildUpon().path("");
                            for (int i3 = 0; i3 < pathSegments6.size() - 1; i3++) {
                                builderPath.appendPath(pathSegments6.get(i3));
                            }
                            Uri uriBuild2 = builderPath.build();
                            List<String> pathSegments7 = uriBuild2.getPathSegments();
                            fj1Var = new fj1(null, uriBuild2, pathSegments7.isEmpty() ? uriBuild2.getHost() : pathSegments7.get(pathSegments7.size() - 1), true, 0L, 0L, new e10((Activity) this, (byte) 0));
                            fj1Var2 = fj1Var;
                        }
                    } else {
                        List<String> pathSegments8 = uri.getPathSegments();
                        if (pathSegments8.size() >= 2) {
                            Uri.Builder builderPath2 = uri.buildUpon().path("");
                            for (int i4 = 0; i4 < pathSegments8.size() - 1; i4++) {
                                builderPath2.appendPath(pathSegments8.get(i4));
                            }
                            Uri uriBuild3 = builderPath2.build();
                            List<String> pathSegments9 = uriBuild3.getPathSegments();
                            fj1Var = new fj1(null, uriBuild3, pathSegments9.isEmpty() ? "" : pathSegments9.get(pathSegments9.size() - 1), true, 0L, 0L, new e10((Activity) this, (byte) 14));
                            fj1Var2 = fj1Var;
                        }
                    }
                    fj1Var2 = null;
                }
                if (fj1Var2 != null) {
                    Uri uri3 = this.H.c;
                    List<String> pathSegments10 = uri3.getPathSegments();
                    return new j90[]{new fj1(null, uri3, pathSegments10.isEmpty() ? "" : pathSegments10.get(pathSegments10.size() - 1), false, 0L, 0L, null), fj1Var2};
                }
            } else if (gu1Var.g != null) {
                boolean zEquals = "com.android.externalstorage.documents".equals(gu1Var.c.getHost());
                gu1 gu1Var2 = this.H;
                if (zEquals) {
                    j90VarH = ij0.j(this, gu1Var2.g, gu1Var2.c);
                } else {
                    nd2 nd2VarB = j90.b(this, gu1Var2.g);
                    Uri uri4 = this.H.c;
                    nd2 nd2Var = new nd2(j90Var2);
                    nd2Var.c = this;
                    nd2Var.d = uri4;
                    j90VarH = ij0.h(nd2VarB, nd2Var);
                }
                if (j90VarH != null && (j90Var = j90VarH.a) != null) {
                    return new j90[]{j90VarH, j90Var};
                }
            }
        }
        return null;
    }

    public final void u2() {
        if (this.z2 || this.H.t) {
            try {
                Settings.System.putInt(getContentResolver(), "accelerometer_rotation", 0);
            } catch (Exception e) {
                e.printStackTrace();
            }
            this.z2 = false;
            gu1 gu1Var = this.H;
            gu1Var.t = false;
            SharedPreferences.Editor editorEdit = gu1Var.b.edit();
            editorEdit.putBoolean("restoreAutoRotate", false);
            editorEdit.commit();
        }
    }

    public final void u3(ho2 ho2Var, ArrayList arrayList, ArrayList arrayList2, dp1 dp1Var) {
        ArrayList arrayList3 = new ArrayList(arrayList2.size() + 1);
        arrayList3.add(new u70(R.drawable.ic_keyboard_24dp, null, getString(R.string.subtitle_search_type), null, false, new ok(this, ho2Var, arrayList, new iq1(this, ho2Var, arrayList, arrayList2, dp1Var, (byte) 0), (byte) 10)));
        Iterator it = arrayList2.iterator();
        while (it.hasNext()) {
            Integer num = (Integer) it.next();
            int iIntValue = num.intValue();
            arrayList3.add(new u70(getString(iIntValue == 0 ? R.string.subtitle_search_specials : R.string.subtitle_search_season, num), null, false, new cc1(this, ho2Var, arrayList, iIntValue, arrayList2, dp1Var)));
        }
        s2.p(this, this.W1, new wp1(this, (byte) 12), ho2Var.c, arrayList3, 34, 48, dp1Var);
    }

    /* JADX WARN: Code duplicated, block: B:13:0x001a  */
    public final void u4() {
        int i;
        if (k6 == null) {
            return;
        }
        float fC1 = C1();
        vg0 vg0Var = k6;
        if (vg0Var == null) {
            i = 0;
        } else {
            vg0Var.A1();
            zl0 zl0Var = vg0Var.V;
            if (zl0Var == null || (i = zl0Var.x) <= 0) {
                i = 0;
            }
        }
        ll llVar = this.f3;
        if (llVar != null) {
            int i2 = this.o0;
            llVar.y = fC1;
            llVar.z = i;
            llVar.A = i2;
        }
        kl klVar = this.g3;
        if (klVar != null) {
            int i3 = this.o0;
            klVar.v = fC1;
            klVar.w = i;
            klVar.x = i3;
        }
    }

    public final void v() {
        this.B.setResizeMode(this.H.i);
        gu1 gu1Var = this.H;
        float f = gu1Var.k;
        this.Z1 = f;
        if (f > 0.0f) {
            this.B.t(gu1Var.i, f);
        } else {
            int i = gu1Var.i;
            cz czVar = this.B;
            if (i == 4) {
                czVar.setScale(gu1Var.j);
            } else {
                czVar.setScale(1.0f);
            }
        }
        K4();
    }

    public final String v0(eg0 eg0Var) {
        StringBuilder sb = new StringBuilder(eg0Var.b());
        String strZ = ErrorActivity.z(eg0Var);
        if (strZ != null) {
            sb.append('\n');
            sb.append(strZ);
        }
        Uri uriD0 = d0();
        if (wt2.E(uriD0) || (uriD0 != null && !this.H.L0)) {
            sb.append("\n\n");
            sb.append(wt2.W(uriD0, this.H.L0));
        }
        return sb.toString();
    }

    /* JADX WARN: Code duplicated, block: B:24:0x0045  */
    /* JADX WARN: Code duplicated, block: B:36:0x0067  */
    public final hl v1(int i) {
        Integer num;
        Integer num2;
        int i2;
        int[] iArr;
        int[] iArr2 = null;
        String strR3 = this.S3 != null ? null : R3(this.Q3, i, this.J3);
        String strR4 = this.S3;
        if (strR4 == null) {
            strR4 = R3(this.R3, i, this.K3);
        }
        boolean z = i == this.F3;
        int i3 = -1;
        int iIntValue = z ? this.H3 : -1;
        if (k6 == null || i < 0) {
            num = null;
        } else {
            ArrayList arrayList = this.L3;
            if (i < arrayList.size()) {
                num = (Integer) arrayList.get(i);
            } else {
                num = null;
            }
        }
        if (num != null) {
            iIntValue = num.intValue();
        }
        int iIntValue2 = z ? this.I3 : -1;
        if (k6 == null || i < 0) {
            num2 = null;
        } else {
            ArrayList arrayList2 = this.M3;
            if (i < arrayList2.size()) {
                num2 = (Integer) arrayList2.get(i);
            } else {
                num2 = null;
            }
        }
        if (num2 != null) {
            iIntValue2 = num2.intValue();
        }
        if (iIntValue2 < 1) {
            String strR5 = R3(this.N3, i, null);
            if (strR5 != null) {
                Pattern[] patternArr = Y6;
                int length = patternArr.length;
                int i4 = 0;
                while (true) {
                    if (i4 >= length) {
                        iArr2 = null;
                        break;
                    }
                    Matcher matcher = patternArr[i4].matcher(strR5);
                    if (matcher.find()) {
                        try {
                            iArr2 = new int[]{Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2))};
                            break;
                        } catch (NumberFormatException unused) {
                            continue;
                            i4++;
                        }
                    }
                    i4++;
                }
            }
            if (iArr2 != null) {
                if (iIntValue < 1) {
                    iIntValue = iArr2[0];
                }
                iIntValue2 = iArr2[1];
            }
        }
        if (this.S3 == null) {
            i2 = iIntValue2;
            i3 = iIntValue;
        } else if (this.W3) {
            i2 = -1;
        } else if (i == this.Z3) {
            i3 = this.X3;
            i2 = this.Y3;
        } else {
            ArrayList arrayList3 = this.a4;
            if (arrayList3 != null && this.b4 >= 0) {
                Iterator it = arrayList3.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        int i5 = (i - this.Z3) + this.b4;
                        if (i5 >= 0 && i5 < this.a4.size()) {
                            go2 go2Var = (go2) this.a4.get(i5);
                            iArr = new int[]{go2Var.a, go2Var.b};
                            break;
                        }
                        if (iIntValue < 1) {
                            iIntValue = 1;
                        }
                        iArr = new int[]{iIntValue, iIntValue2};
                        break;
                    }
                    go2 go2Var2 = (go2) it.next();
                    if (go2Var2.a == iIntValue && go2Var2.b == iIntValue2) {
                        iArr = new int[]{iIntValue, iIntValue2};
                        break;
                    }
                }
            } else {
                if (iIntValue < 1) {
                    iIntValue = 1;
                }
                iArr = new int[]{iIntValue, iIntValue2};
            }
            i3 = iArr[0];
            i2 = iArr[1];
        }
        return new hl(i3, i2, strR3, strR4);
    }

    public final void v2(Bundle bundle) {
        Bundle bundle2 = bundle == null ? null : bundle.getBundle("trackChoice");
        if (bundle2 == null) {
            return;
        }
        this.p3 = vp2.a(bundle2.getBundle("stickyAudio"));
        this.q3 = vp2.a(bundle2.getBundle("stickySubtitle"));
        ArrayList<String> stringArrayList = bundle2.getStringArrayList("audioChosenFor");
        if (stringArrayList != null) {
            this.r3.addAll(stringArrayList);
        }
        ArrayList<String> stringArrayList2 = bundle2.getStringArrayList("subtitleChosenFor");
        if (stringArrayList2 != null) {
            this.s3.addAll(stringArrayList2);
        }
    }

    public final void v3(ie2 ie2Var) {
        if (R6 && this.H.q0) {
            V0();
        } else {
            this.u5 = ie2Var;
            y3(2, getString(R.string.button_skip), true);
        }
    }

    public final void v4(boolean z) {
        cz czVar = this.B;
        if (czVar != null) {
            czVar.removeCallbacks(this.R);
        }
        nq1 nq1Var = this.w2;
        if (z) {
            boolean zHasFocus = this.o2.hasFocus();
            if (!Q6) {
                this.o2.setVisibility(4);
            }
            this.s2.setVisibility(0);
            if (zHasFocus && !Q6) {
                this.s2.setFocusable(true);
                this.s2.requestFocus();
            }
            Uri uriF0 = f0();
            if (uriF0 == null) {
                uriF0 = this.H.c;
            }
            if (!wt2.A(uriF0) || this.v2) {
                return;
            }
            this.v2 = true;
            this.u2 = aq2.p.get();
            this.B.postDelayed(nq1Var, 2500L);
            return;
        }
        this.v2 = false;
        cz czVar2 = this.B;
        if (czVar2 != null) {
            czVar2.removeCallbacks(nq1Var);
        }
        TextView textView = this.t2;
        if (textView != null) {
            textView.setVisibility(8);
        }
        this.B.removeCallbacks(this.g1);
        if (this.W0 == 0) {
            this.T0.c();
        }
        this.s2.setAlpha(1.0f);
        this.t2.setAlpha(1.0f);
        boolean zHasFocus2 = this.s2.hasFocus();
        this.s2.setFocusable(false);
        this.s2.setVisibility(8);
        this.o2.setVisibility(0);
        if (P6 || zHasFocus2) {
            P6 = false;
            this.o2.requestFocus();
        }
    }

    public final void w(tp2 tp2Var, int i) {
        if (k6 != null) {
            if (this.L4 != null) {
                W();
                D4();
            }
            this.I4 = false;
            int iF4 = this.p == null ? -1 : f4(1);
            if (iF4 >= 0) {
                s50 s50VarD = this.p.d();
                s50VarD.p(iF4, false);
                z50 z50Var = this.p;
                z50Var.getClass();
                z50Var.o(new t50(s50VarD));
            }
            vg0 vg0Var = k6;
            t50 t50Var = (t50) vg0Var.A0();
            t50Var.getClass();
            s50 s50Var = new s50(t50Var);
            s50Var.d(3);
            s50Var.j(3, false);
            s50Var.i(new fq2(tp2Var, Collections.singletonList(Integer.valueOf(i))));
            vg0Var.q0(s50Var.b());
            this.A4 = tp2Var;
            this.B4 = i;
            k();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final ArrayList w0() {
        File[] fileArrListFiles;
        u81 u81Var;
        ArrayList arrayList = new ArrayList();
        vg0 vg0Var = k6;
        if (vg0Var != null) {
            z81 z81VarZ = vg0Var.z();
            Object[] objArr = 0;
            if (z81VarZ != null && (u81Var = z81VarZ.b) != null) {
                nw0 nw0VarN = u81Var.g.listIterator(0);
                while (nw0VarN.hasNext()) {
                    y81 y81Var = (y81) nw0VarN.next();
                    if (!arrayList.contains(y81Var.a)) {
                        arrayList.add(y81Var.a);
                    }
                }
            }
            Uri[] uriArr = {this.L4, this.H.e, this.v4};
            for (int i = 0; i < 3; i++) {
                Uri uri = uriArr[i];
                if (uri != null && !arrayList.contains(uri)) {
                    arrayList.add(uri);
                }
            }
            ArrayList<Uri> arrayList2 = new ArrayList();
            vg0 vg0Var2 = k6;
            if (vg0Var2 != null) {
                hl hlVarV1 = v1(vg0Var2.V());
                if (!hlVarV1.m() && (fileArrListFiles = getCacheDir().listFiles(new mp1(U3(hlVarV1), objArr == true ? 1 : 0))) != null) {
                    Arrays.sort(fileArrListFiles, new dc((byte) 22));
                    for (File file : fileArrListFiles) {
                        if (file.isFile() && file.length() > 0) {
                            arrayList2.add(Uri.fromFile(file));
                        }
                    }
                }
            }
            for (Uri uri2 : arrayList2) {
                if (!arrayList.contains(uri2)) {
                    arrayList.add(uri2);
                }
            }
        }
        return arrayList;
    }

    public final String w1() {
        long j = this.W;
        if (j > 0) {
            return getString(R.string.stats_network, getString(R.string.quality_bitrate, Float.valueOf(j / 1000000.0f)));
        }
        return null;
    }

    public final String w2() {
        vs1 vs1Var = this.l3;
        String str = vs1Var == null ? null : vs1Var.h;
        if ("ask_open".equals(str)) {
            return "askOpen";
        }
        if ("ask_every".equals(str)) {
            return "askEvery";
        }
        if ("always".equals(str)) {
            return "always";
        }
        return "never".equals(str) ? "never" : this.H.B0;
    }

    public final void w3(boolean z) {
        if (this.f5 == null) {
            return;
        }
        if (R6 && this.H.q0) {
            return;
        }
        if (this.C5 == -9223372036854775807L || !G3(z)) {
            W0();
            o3(getString(R.string.notification_skipped), false, R.drawable.ic_double_arrow_24dp);
            return;
        }
        this.G5 = SystemClock.uptimeMillis() + 5000;
        this.k5 = 1.0f;
        y3(4, getString(R.string.notification_skipped_back), true);
        this.B.postDelayed(this.t5, 5000L);
        ValueAnimator valueAnimator = this.l5;
        if (valueAnimator != null) {
            valueAnimator.cancel();
            this.l5 = null;
        }
        cz czVar = this.B;
        nq1 nq1Var = this.O5;
        czVar.removeCallbacks(nq1Var);
        this.B.postOnAnimation(nq1Var);
    }

    /* JADX WARN: Code duplicated, block: B:23:0x005c  */
    public final void w4() {
        String str;
        String string;
        vg0 vg0Var = k6;
        if (vg0Var == null) {
            return;
        }
        vg0Var.A1();
        zl0 zl0Var = vg0Var.V;
        z4();
        o4();
        boolean zEquals = "detailed".equals(this.H.A0);
        TextView textView = this.m1;
        float fO4 = O4();
        boolean z = false;
        boolean z2 = this.x1.getVisibility() != 0;
        String string2 = null;
        if (zl0Var == null) {
            string = null;
        } else {
            StringBuilder sb = new StringBuilder();
            if (z2) {
                e(sb, p2(zl0Var.w, zl0Var.x));
            }
            if (zEquals) {
                String strE = zy.e(zl0Var.p);
                if (strE == null) {
                    strE = zy.e(zl0Var.l);
                }
                e(sb, strE);
            }
            bu buVar = zl0Var.H;
            if (buVar != null) {
                int i = buVar.c;
                if (i == 6) {
                    str = "HDR10";
                } else if (i != 7) {
                    str = null;
                } else {
                    str = "HLG";
                }
            } else {
                str = null;
            }
            e(sb, str);
            if (zEquals && fO4 > 0.0f) {
                e(sb, String.format(Locale.US, "%.2f fps", Float.valueOf(fO4)));
            }
            string = sb.toString();
        }
        X2(textView, string);
        TextView textView2 = this.n1;
        zl0 zl0VarJ0 = J0();
        boolean z3 = this.H1.getVisibility() != 0;
        if (zl0VarJ0 != null) {
            String strM1 = m1(zl0VarJ0.d);
            String strK4 = k4(zl0VarJ0);
            StringBuilder sb2 = new StringBuilder();
            if (z3 && strK4 != null && !strK4.isEmpty()) {
                e(sb2, strK4);
            }
            if (!z3 && (strK4 == null || strK4.isEmpty())) {
                z = true;
            }
            if (strM1 != null && !z && !strM1.equals(strK4)) {
                e(sb2, strM1);
            }
            if (zEquals) {
                e(sb2, zy.f(zl0VarJ0, true));
            } else {
                int i2 = zl0VarJ0.J;
                if (i2 > 2) {
                    e(sb2, wt2.s(i2));
                }
            }
            string2 = sb2.toString();
        }
        X2(textView2, string2);
        t4();
    }

    public final void x(jr1 jr1Var) {
        vg0 vg0Var = k6;
        if (vg0Var != null) {
            int i = jr1Var.f;
            String str = jr1Var.h;
            tp2 tp2Var = jr1Var.e;
            byte b = jr1Var.d;
            if (b == 3) {
                if (str == null || str.trim().isEmpty()) {
                    return;
                }
                Uri uri = Uri.parse(str);
                if (uri.equals(f0())) {
                    return;
                }
                this.f4 = (byte) 3;
                this.g4 = null;
                this.h4 = -1;
                this.i4 = S1(jr1Var.a);
                c4(uri, Math.max(0L, k6.O0()), k6.w());
                return;
            }
            this.f4 = b;
            this.g4 = tp2Var;
            this.h4 = i;
            this.i4 = 0;
            t50 t50Var = (t50) vg0Var.A0();
            t50Var.getClass();
            s50 s50Var = new s50(t50Var);
            s50Var.d(2);
            s50Var.h(b == 1);
            if (b == 1) {
                s50Var.e();
            } else {
                s50Var.m();
            }
            if (b == 2 && tp2Var != null) {
                s50Var.i(new fq2(tp2Var, Collections.singletonList(Integer.valueOf(i))));
            }
            k6.q0(s50Var.b());
        }
    }

    public final void x1(boolean z) {
        Intent intent = new Intent(z ? "android.media.action.OPEN_AUDIO_EFFECT_CONTROL_SESSION" : "android.media.action.CLOSE_AUDIO_EFFECT_CONTROL_SESSION");
        vg0 vg0Var = k6;
        vg0Var.A1();
        intent.putExtra("android.media.extra.AUDIO_SESSION", ((Integer) vg0Var.C.j()).intValue());
        intent.putExtra("android.media.extra.PACKAGE_NAME", getPackageName());
        if (z) {
            intent.putExtra("android.media.extra.CONTENT_TYPE", 1);
        }
        try {
            sendBroadcast(intent);
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

    public final long x2(Uri uri, int i, long j, boolean z) {
        String str;
        c7 c7Var;
        this.W0 = 0L;
        if (j > 0 && !"never".equals(w2())) {
            ArrayList arrayListA = null;
            Long l = uri == null ? null : (Long) gu1.r(this, "lengths").get(uri.toString());
            long jLongValue = l == null ? -9223372036854775807L : l.longValue();
            vs1 vs1Var = this.l3;
            rs1 rs1Var = (vs1Var == null || i < 0 || i >= vs1Var.f.size()) ? null : (rs1) this.l3.f.get(i);
            if (jLongValue <= 0 && (c7Var = this.m3) != null) {
                jLongValue = c7Var.j(i) ? ((long[]) c7Var.c)[i] : -9223372036854775807L;
            }
            ay0 ay0Var = (rs1Var == null || (str = rs1Var.q) == null) ? null : new ay0(str);
            if (jLongValue <= 0 && ay0Var != null) {
                jLongValue = Math.round(ay0Var.n * 1000.0d);
            }
            if (ay0Var != null && jLongValue > 0) {
                arrayListA = ay0Var.a(jLongValue / 1000.0d);
            }
            if (j < ge2.b(jLongValue, arrayListA)) {
                if (!z) {
                    return j;
                }
                if (j >= 30000) {
                    this.W0 = j;
                    return j;
                }
            }
        }
        return 0L;
    }

    public final void x3() {
        vk1[] vk1VarArr;
        if (k6 == null) {
            return;
        }
        Dialog dialog = this.M1;
        if (dialog != null) {
            dialog.dismiss();
        }
        ge2 ge2Var = this.d5;
        byte b = 1;
        if (ge2Var == null) {
            vk1VarArr = new vk1[0];
            break;
        }
        Iterator it = ge2Var.c.iterator();
        while (true) {
            if (!it.hasNext()) {
                vk1VarArr = new vk1[0];
                break;
            }
            if (((ie2) it.next()).c != 2) {
                String[] strArr = W6;
                int length = strArr.length;
                CharSequence[] charSequenceArr = new CharSequence[length];
                for (int i = 0; i < length; i++) {
                    charSequenceArr[i] = getString(X6[i]);
                }
                gu1 gu1Var = this.H;
                String str = gu1Var.o0.equals(gu1Var.p0) ? this.H.o0 : null;
                String str2 = this.o4;
                if (str2 == null) {
                    str2 = str;
                }
                yo1 yo1Var = new yo1(this, (byte) 4);
                vk1 vk1Var = new vk1();
                vk1Var.m = charSequenceArr;
                vk1Var.n = strArr;
                vk1Var.l = str2;
                vk1Var.o = str;
                vk1Var.p = yo1Var;
                vk1VarArr = new vk1[]{vk1Var};
                break;
            }
        }
        vk1[] vk1VarArr2 = vk1VarArr;
        Dialog dialogI = ag.i(this, this.W1, getString(R.string.skip_session_title), 30.0d, 0.25d, vk1VarArr2, null, new wk1(vk1VarArr2.length != 0 ? getString(R.string.skip_offset_title) : null, this.k4, new yo1(this, b)));
        this.M1 = dialogI;
        p3(dialogI);
    }

    public final void x4() {
        if (this.u1 == null) {
            return;
        }
        boolean z = this.H.u0 && !this.G;
        hm1 hm1Var = this.v1;
        if (hm1Var != null) {
            hm1Var.setAlpha(z ? 0.0f : 1.0f);
        }
        if (z) {
            e4();
        }
        this.u1.setVisibility(z ? 0 : 8);
    }

    public final void y(long j, final Integer num) {
        this.W0 = j;
        final byte b = 1;
        this.T0.e(true);
        this.s2.setAlpha(0.0f);
        this.t2.setAlpha(0.0f);
        StringBuilder sb = new StringBuilder();
        String strL = ot2.L(sb, new Formatter(sb, Locale.getDefault()), j);
        final byte b2 = 0;
        s2.d(this, getString(R.string.resume_ask_title), getString(R.string.resume_ask_message, strL), getString(R.string.resume_ask_continue), new Runnable(this) { // from class: lq1
            public final /* synthetic */ PlayerActivity m;

            {
                this.m = this;
            }

            @Override // java.lang.Runnable
            public final void run() {
                byte b3 = b2;
                Integer num2 = num;
                PlayerActivity playerActivity = this.m;
                switch (b3) {
                    case DecoderInputBuffer.BUFFER_REPLACEMENT_MODE_DISABLED /* 0 */:
                        LoudnessEnhancer loudnessEnhancer = PlayerActivity.i6;
                        playerActivity.b(false, num2);
                        break;
                    default:
                        LoudnessEnhancer loudnessEnhancer2 = PlayerActivity.i6;
                        playerActivity.b(true, num2);
                        break;
                }
            }
        }, getString(R.string.resume_ask_start_over), new Runnable(this) { // from class: lq1
            public final /* synthetic */ PlayerActivity m;

            {
                this.m = this;
            }

            @Override // java.lang.Runnable
            public final void run() {
                byte b3 = b;
                Integer num2 = num;
                PlayerActivity playerActivity = this.m;
                switch (b3) {
                    case DecoderInputBuffer.BUFFER_REPLACEMENT_MODE_DISABLED /* 0 */:
                        LoudnessEnhancer loudnessEnhancer = PlayerActivity.i6;
                        playerActivity.b(false, num2);
                        break;
                    default:
                        LoudnessEnhancer loudnessEnhancer2 = PlayerActivity.i6;
                        playerActivity.b(true, num2);
                        break;
                }
            }
        }, true);
    }

    public final Uri y0() {
        j90[] j90VarArrU1 = u1();
        if (j90VarArrU1 != null) {
            boolean z = false;
            j90 j90Var = j90VarArrU1[0];
            ArrayList<j90> arrayListA = ij0.A(j90VarArrU1[1]);
            String strC = j90Var == null ? null : j90Var.c();
            if (strC == null) {
                j90Var = null;
            } else {
                for (j90 j90Var2 : arrayListA) {
                    if (j90Var2.c().equals(strC)) {
                        z = true;
                    } else if (!z || !ij0.x(j90Var2)) {
                    }
                }
                j90Var2 = null;
            }
            if (j90Var2 != null) {
                return j90Var2.e();
            }
        }
        return null;
    }

    public final void y2() {
        if (R6 && this.H.q0) {
            return;
        }
        if (this.u0) {
            y3(2, getString(R.string.button_skip), false);
            B4(this.u5);
        } else if (this.g6 == 2) {
            V0();
        }
    }

    public final void y3(int i, String str, boolean z) {
        if (this.f5 == null) {
            return;
        }
        if (this.G || this.b5) {
            W0();
            return;
        }
        cz czVar = this.B;
        if (czVar != null) {
            czVar.removeCallbacks(this.t5);
        }
        boolean z2 = this.g6 != i;
        this.g6 = i;
        boolean z3 = this.f5.getVisibility() != 0;
        this.f5.animate().cancel();
        if (!z2 || z3) {
            n(i, str);
            Button button = this.f5;
            if (z3) {
                button.setAlpha(0.0f);
                this.f5.setVisibility(0);
                this.f5.animate().alpha(1.0f).setDuration(250L).setInterpolator(O6).start();
                this.f5.post(new wp1(this, (byte) 15));
            } else {
                button.setAlpha(1.0f);
            }
        } else {
            n(i, str);
            this.f5.setClickable(false);
            this.f5.setAlpha(1.0f);
            this.f5.animate().alpha(0.4f).setDuration(100L).withEndAction(new wp1(this, (byte) 14)).start();
            cz czVar2 = this.B;
            if (czVar2 != null) {
                xo1 xo1Var = this.s5;
                czVar2.removeCallbacks(xo1Var);
                this.B.postDelayed(xo1Var, 200L);
            }
        }
        if (Q6 && z) {
            if (z3 || z2) {
                this.f5.requestFocus();
            }
        }
    }

    public final boolean y4(int i, int i2, int i3, int i4) {
        try {
            ArrayList arrayList = new ArrayList();
            PendingIntent broadcast = PendingIntent.getBroadcast(this, i4, new Intent("media_control").putExtra("control_type", i3), 67108864);
            Icon iconCreateWithResource = Icon.createWithResource(this, i);
            String string = getString(i2);
            arrayList.add(new RemoteAction(iconCreateWithResource, string, string, broadcast));
            wb1.e(this.F).setActions(arrayList);
            setPictureInPictureParams(wb1.e(this.F).build());
            return true;
        } catch (IllegalStateException e) {
            e.printStackTrace();
            return false;
        }
    }

    public final void z(ho2 ho2Var, ArrayList arrayList, Runnable runnable) {
        int i;
        int i2;
        ContextThemeWrapper contextThemeWrapperE = s2.e(this);
        vg0 vg0Var = k6;
        hl hlVarV1 = vg0Var != null ? v1(vg0Var.V()) : null;
        LinearLayout linearLayoutC = jf2.c(contextThemeWrapperE, 1);
        int iP = wt2.p(10);
        linearLayoutC.setPadding(iP, iP, iP, iP);
        Dialog dialog = new Dialog(this, android.R.style.Theme.Translucent.NoTitleBar);
        l70 l70Var = new l70(dialog, runnable, (byte) 2);
        TextView textView = new TextView(contextThemeWrapperE);
        textView.setText(getString(R.string.subtitle_search_type));
        textView.setTextColor(sj.n(contextThemeWrapperE, R.attr.colorOnSurface, -1));
        textView.setTextSize(2, this.W1.y());
        textView.setTypeface(Typeface.DEFAULT_BOLD);
        textView.setPadding(wt2.p(6), wt2.p(10), wt2.p(6), wt2.p(10));
        linearLayoutC.addView(s2.s(contextThemeWrapperE, this.W1, textView, l70Var));
        EditText editTextF = s2.F(linearLayoutC, getString(R.string.subtitle_search_season_label), null);
        editTextF.setInputType(2);
        if (hlVarV1 != null && (i2 = hlVarV1.l) >= 0) {
            editTextF.setText(String.valueOf(i2));
        }
        EditText editTextF2 = s2.F(linearLayoutC, getString(R.string.subtitle_search_episode_label), null);
        editTextF2.setInputType(2);
        editTextF2.setImeOptions(268435462);
        if (hlVarV1 != null && (i = hlVarV1.m) >= 1) {
            editTextF2.setText(String.valueOf(i));
        }
        final gq1 gq1Var = new gq1(this, dialog, ho2Var, editTextF, editTextF2, arrayList);
        MaterialButton materialButton = new MaterialButton(contextThemeWrapperE, null, R.attr.materialButtonStyle);
        materialButton.setText(android.R.string.ok);
        materialButton.setTextSize(2, this.W1.r());
        materialButton.setInsetTop(0);
        materialButton.setInsetBottom(0);
        materialButton.setMinHeight(this.W1.b(48.0f));
        wt2.r(materialButton);
        materialButton.setOnClickListener(new zs(gq1Var, (byte) 9));
        editTextF2.setOnEditorActionListener(new TextView.OnEditorActionListener() { // from class: hq1
            @Override // android.widget.TextView.OnEditorActionListener
            public final boolean onEditorAction(TextView textView2, int i3, KeyEvent keyEvent) {
                LoudnessEnhancer loudnessEnhancer = PlayerActivity.i6;
                gq1Var.run();
                return true;
            }
        });
        LinearLayout linearLayout = new LinearLayout(contextThemeWrapperE);
        linearLayout.setOrientation(0);
        linearLayout.setGravity(8388613);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        layoutParams.topMargin = wt2.p(12);
        linearLayout.addView(materialButton);
        linearLayoutC.addView(linearLayout, layoutParams);
        ScrollView scrollView = new ScrollView(contextThemeWrapperE);
        scrollView.addView(linearLayoutC, new ViewGroup.LayoutParams(-1, -2));
        s2.v(this, this.W1, dialog, scrollView, false);
        wt2.G(dialog, scrollView);
        wt2.P(dialog, l70Var);
        editTextF.requestFocus();
        p3(dialog);
    }

    public final void z0() {
        this.B.removeCallbacks(this.a6);
        this.W5 = 0L;
        this.X5 = 0;
        this.Y5 = false;
        this.Z5 = false;
        D2();
        vg0 vg0Var = k6;
        if (vg0Var != null) {
            vg0Var.k(false);
        }
        finish();
    }

    public final int[] z1(View view) {
        int[] iArr = new int[2];
        int[] iArr2 = new int[2];
        view.getLocationInWindow(iArr);
        this.C0.getLocationInWindow(iArr2);
        return new int[]{iArr[0] - iArr2[0], iArr[1] - iArr2[1]};
    }

    public final JSONObject z2() {
        int i;
        Uri uriF0 = f0();
        if (uriF0 != null && wt2.E(uriF0)) {
            String strW = this.y3;
            if (strW == null) {
                strW = wt2.w(this, uriF0);
            }
            String str = this.K3;
            if (str != null) {
                try {
                    i = Integer.parseInt(str);
                } catch (NumberFormatException unused) {
                    i = 0;
                }
            } else {
                i = 0;
            }
            try {
                JSONObject jSONObjectPut = new JSONObject().put("url", uriF0.toString());
                if (strW == null) {
                    strW = "";
                }
                return jSONObjectPut.put("title", strW).put("poster", wt2.E(this.z3) ? this.z3.toString() : "").put("tmdb", i).put("source", "tmdb").put("type", this.H3 > 0 ? "tv" : "movie");
            } catch (Exception unused2) {
            }
        }
        return null;
    }

    /* JADX WARN: Type inference failed for: r8v13, types: [vo1] */
    public final k5 z3(String str, final String str2, dp1 dp1Var) {
        ContextThemeWrapper contextThemeWrapperE = s2.e(this);
        final byte b = 1;
        final byte b2 = 0;
        if (Q6) {
            k51 k51Var = new k51(contextThemeWrapperE);
            f5 f5Var = k51Var.a;
            f5Var.f = str;
            if (dp1Var != null) {
                k51Var.setPositiveButton(R.string.error_retry, new f70(dp1Var, (byte) 3));
                k51Var.setNegativeButton(android.R.string.ok, new DialogInterface.OnClickListener() { // from class: uo1
                    @Override // android.content.DialogInterface.OnClickListener
                    public final void onClick(DialogInterface dialogInterface, int i) {
                        switch (b) {
                            case DecoderInputBuffer.BUFFER_REPLACEMENT_MODE_DISABLED /* 0 */:
                                LoudnessEnhancer loudnessEnhancer = PlayerActivity.i6;
                                dialogInterface.dismiss();
                                break;
                            default:
                                LoudnessEnhancer loudnessEnhancer2 = PlayerActivity.i6;
                                dialogInterface.dismiss();
                                break;
                        }
                    }
                });
            } else {
                k51Var.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() { // from class: uo1
                    @Override // android.content.DialogInterface.OnClickListener
                    public final void onClick(DialogInterface dialogInterface, int i) {
                        switch (b2) {
                            case DecoderInputBuffer.BUFFER_REPLACEMENT_MODE_DISABLED /* 0 */:
                                LoudnessEnhancer loudnessEnhancer = PlayerActivity.i6;
                                dialogInterface.dismiss();
                                break;
                            default:
                                LoudnessEnhancer loudnessEnhancer2 = PlayerActivity.i6;
                                dialogInterface.dismiss();
                                break;
                        }
                    }
                });
            }
            if (str2 != null) {
                ?? r8 = new DialogInterface.OnClickListener() { // from class: vo1
                    @Override // android.content.DialogInterface.OnClickListener
                    public final void onClick(DialogInterface dialogInterface, int i) {
                        LoudnessEnhancer loudnessEnhancer = PlayerActivity.i6;
                        PlayerActivity playerActivity = this.l;
                        String str3 = str2;
                        playerActivity.k3(str3, str3, null);
                    }
                };
                f5Var.k = f5Var.a.getText(R.string.error_details);
                f5Var.l = r8;
            }
            k5 k5VarCreate = k51Var.create();
            k5VarCreate.show();
            return k5VarCreate;
        }
        cz czVar = this.B;
        if (czVar != null) {
            czVar.u(0, null);
        }
        ze2 ze2VarZ = y61.z(this, str, true, R.drawable.ic_info_24dp);
        J6 = ze2VarZ;
        if (ze2VarZ == null) {
            return null;
        }
        if (str2 != null) {
            ze2VarZ.g(R.string.error_details, new xk(this, str2, (byte) 2));
            ze2 ze2Var = J6;
            ((SnackbarContentLayout) ze2Var.i.getChildAt(0)).getActionView().setTextColor(sj.n(this, R.attr.colorPrimary, -1));
        } else if (dp1Var != null) {
            ze2VarZ.g(R.string.error_retry, new pk(dp1Var, (byte) 4));
            ze2 ze2Var2 = J6;
            ((SnackbarContentLayout) ze2Var2.i.getChildAt(0)).getActionView().setTextColor(sj.n(this, R.attr.colorPrimary, -1));
        }
        J6.h();
        return null;
    }

    public final void z4() {
        zl0 zl0Var;
        String strP2;
        if (this.x1 == null || this.G1) {
            return;
        }
        ArrayList arrayListJ = J();
        this.y1 = k6 != null && arrayListJ.size() >= 2;
        q();
        int iT2 = arrayListJ.isEmpty() ? -1 : T2(arrayListJ);
        vg0 vg0Var = k6;
        if (vg0Var != null) {
            vg0Var.A1();
            zl0Var = vg0Var.V;
        } else {
            zl0Var = null;
        }
        if (iT2 < 0 || ((jr1) arrayListJ.get(iT2)).d != 3) {
            strP2 = zl0Var != null ? p2(zl0Var.w, zl0Var.x) : null;
        } else {
            strP2 = ((jr1) arrayListJ.get(iT2)).a;
        }
        if (strP2 == null) {
            strP2 = getString(R.string.quality_auto);
        }
        this.A1 = strP2;
        this.x1.setText(B2() ? null : this.A1);
    }
}
