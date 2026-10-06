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
import defpackage.a51;
import defpackage.a7;
import defpackage.a70;
import defpackage.a82;
import defpackage.ag0;
import defpackage.al;
import defpackage.an;
import defpackage.as2;
import defpackage.az1;
import defpackage.b7;
import defpackage.b90;
import defpackage.bb1;
import defpackage.bc0;
import defpackage.bd2;
import defpackage.bl2;
import defpackage.bp1;
import defpackage.bq1;
import defpackage.c4;
import defpackage.c70;
import defpackage.c81;
import defpackage.c82;
import defpackage.cb1;
import defpackage.cc;
import defpackage.cj1;
import defpackage.ck0;
import defpackage.co;
import defpackage.cp1;
import defpackage.cq1;
import defpackage.ct2;
import defpackage.cz1;
import defpackage.d81;
import defpackage.dj2;
import defpackage.dl;
import defpackage.dn;
import defpackage.dp2;
import defpackage.dq1;
import defpackage.e4;
import defpackage.e5;
import defpackage.e81;
import defpackage.el;
import defpackage.en2;
import defpackage.ep2;
import defpackage.eq1;
import defpackage.eu0;
import defpackage.f71;
import defpackage.f81;
import defpackage.f91;
import defpackage.fa0;
import defpackage.fk2;
import defpackage.fl2;
import defpackage.fp2;
import defpackage.fq1;
import defpackage.fs2;
import defpackage.fw0;
import defpackage.g02;
import defpackage.g20;
import defpackage.g30;
import defpackage.g81;
import defpackage.gc;
import defpackage.ge;
import defpackage.gh0;
import defpackage.gk;
import defpackage.gk2;
import defpackage.gq1;
import defpackage.gs1;
import defpackage.gt;
import defpackage.gt2;
import defpackage.h00;
import defpackage.h01;
import defpackage.h51;
import defpackage.h81;
import defpackage.hj1;
import defpackage.hj2;
import defpackage.hk;
import defpackage.hk2;
import defpackage.hn2;
import defpackage.hp1;
import defpackage.hp2;
import defpackage.hs1;
import defpackage.hu2;
import defpackage.hw0;
import defpackage.i40;
import defpackage.i41;
import defpackage.i70;
import defpackage.i71;
import defpackage.i81;
import defpackage.ic;
import defpackage.ic0;
import defpackage.ip2;
import defpackage.is1;
import defpackage.j5;
import defpackage.j50;
import defpackage.j71;
import defpackage.j81;
import defpackage.jp1;
import defpackage.js1;
import defpackage.k10;
import defpackage.k20;
import defpackage.k50;
import defpackage.k81;
import defpackage.kd;
import defpackage.ke;
import defpackage.kk2;
import defpackage.ko1;
import defpackage.kp1;
import defpackage.kp2;
import defpackage.kq1;
import defpackage.kr1;
import defpackage.ks1;
import defpackage.l50;
import defpackage.l70;
import defpackage.l81;
import defpackage.l91;
import defpackage.lb0;
import defpackage.lh0;
import defpackage.li;
import defpackage.lj;
import defpackage.lk2;
import defpackage.lo1;
import defpackage.lq1;
import defpackage.mb1;
import defpackage.me2;
import defpackage.mg0;
import defpackage.mk1;
import defpackage.mo1;
import defpackage.mq1;
import defpackage.n82;
import defpackage.nk1;
import defpackage.np1;
import defpackage.nq1;
import defpackage.ns2;
import defpackage.o61;
import defpackage.o81;
import defpackage.o82;
import defpackage.ol1;
import defpackage.oo1;
import defpackage.oq1;
import defpackage.p81;
import defpackage.p82;
import defpackage.pb0;
import defpackage.pb2;
import defpackage.po1;
import defpackage.pp1;
import defpackage.pp2;
import defpackage.pq1;
import defpackage.pr;
import defpackage.px;
import defpackage.q50;
import defpackage.qk;
import defpackage.qn2;
import defpackage.qq1;
import defpackage.qs;
import defpackage.qy;
import defpackage.r12;
import defpackage.r2;
import defpackage.rc1;
import defpackage.rl0;
import defpackage.rn2;
import defpackage.rp1;
import defpackage.rq1;
import defpackage.rs;
import defpackage.ry1;
import defpackage.s12;
import defpackage.sb1;
import defpackage.sj2;
import defpackage.sl2;
import defpackage.sp2;
import defpackage.st;
import defpackage.sx0;
import defpackage.t01;
import defpackage.t11;
import defpackage.tb;
import defpackage.tf;
import defpackage.tg;
import defpackage.tm1;
import defpackage.to1;
import defpackage.tq1;
import defpackage.ty;
import defpackage.ue1;
import defpackage.uf1;
import defpackage.uj;
import defpackage.un0;
import defpackage.uq1;
import defpackage.uy0;
import defpackage.v00;
import defpackage.v81;
import defpackage.vc2;
import defpackage.vd2;
import defpackage.vj;
import defpackage.vj2;
import defpackage.vk1;
import defpackage.vo1;
import defpackage.vo2;
import defpackage.vp1;
import defpackage.vq1;
import defpackage.vt;
import defpackage.vt1;
import defpackage.vu1;
import defpackage.w00;
import defpackage.w5;
import defpackage.w81;
import defpackage.w91;
import defpackage.w92;
import defpackage.wd2;
import defpackage.we2;
import defpackage.wf0;
import defpackage.wi1;
import defpackage.wk1;
import defpackage.wo1;
import defpackage.wp2;
import defpackage.wq1;
import defpackage.x62;
import defpackage.x80;
import defpackage.x81;
import defpackage.x91;
import defpackage.xi;
import defpackage.xi1;
import defpackage.xp1;
import defpackage.xp2;
import defpackage.xq1;
import defpackage.y61;
import defpackage.y7;
import defpackage.yb;
import defpackage.yd0;
import defpackage.yf0;
import defpackage.yj;
import defpackage.yl1;
import defpackage.yo1;
import defpackage.yq1;
import defpackage.ys2;
import defpackage.z91;
import defpackage.zi0;
import defpackage.zj;
import defpackage.zn2;
import defpackage.zo1;
import defpackage.zp1;
import defpackage.zz1;
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

/* JADX INFO: compiled from: r8-map-id-ba8d2c2760819bd03a19aa4133a73a2576203ef152dace5b65b91a4c4fd30466 */
/* JADX INFO: loaded from: classes.dex */
public class PlayerActivity extends Activity {
    public static volatile long A6;
    public static volatile long B6;
    public static boolean C6;
    public static boolean D6;
    public static me2 E6;
    public static float F6;
    public static boolean G6;
    public static boolean H6;
    public static float I6;
    public static final LinearInterpolator J6;
    public static boolean K6;
    public static boolean L6;
    public static boolean M6;
    public static boolean N6;
    public static boolean O6;
    public static hp2 P6;
    public static final Map Q6;
    public static final String[] R6;
    public static final int[] S6;
    public static final Pattern[] T6;
    public static final Pattern U6;
    public static final String[] V6;
    public static final ConcurrentHashMap W6;
    public static final int[] X6;
    public static LoudnessEnhancer e6;
    public static xi f6;
    public static mg0 g6;
    public static PlayerActivity h6;
    public static boolean i6;
    public static volatile boolean l6;
    public static boolean m6;
    public static boolean n6;
    public static boolean o6;
    public static boolean p6;
    public static final wk1 t6;
    public static Boolean u6;
    public static final char v6;
    public static boolean w6;
    public static final int x6;
    public static String y6;
    public static long z6;
    public volatile String A;
    public String A1;
    public boolean A2;
    public Uri A4;
    public ty B;
    public HashMap B0;
    public LinearLayout B1;
    public boolean B2;
    public boolean B4;
    public boolean C;
    public CoordinatorLayout C0;
    public LinearLayout C1;
    public float C2;
    public int C3;
    public boolean C4;
    public qq1 D;
    public LinearLayout D0;
    public int D1;
    public float D2;
    public int D3;
    public String D4;
    public long D5;
    public YouTubeOverlay E;
    public FrameLayout E0;
    public boolean E1;
    public boolean E2;
    public long[] E3;
    public long E5;
    public Object F;
    public boolean F0;
    public boolean F1;
    public boolean F2;
    public vj2 F4;
    public boolean G;
    public boolean G0;
    public TextView G1;
    public long G2;
    public boolean G4;
    public vt1 H;
    public pr H0;
    public TextView H1;
    public String H3;
    public hk2 H4;
    public vj I;
    public boolean I0;
    public ImageButton I1;
    public String I3;
    public Uri I4;
    public boolean J;
    public pr J0;
    public Dialog J1;
    public long J2;
    public Uri J4;
    public LinearLayout K0;
    public Dialog K1;
    public int K2;
    public boolean K4;
    public boolean L;
    public int L0;
    public Dialog L1;
    public boolean L2;
    public boolean M;
    public boolean M0;
    public Dialog M1;
    public long M2;
    public boolean M4;
    public String M5;
    public long N;
    public FrameLayout N0;
    public Dialog N1;
    public boolean N4;
    public volatile boolean N5;
    public int O;
    public ImageView O0;
    public Dialog O1;
    public boolean O2;
    public Uri O4;
    public String O5;
    public TextView P0;
    public Dialog P1;
    public long P4;
    public TextView Q0;
    public boolean Q1;
    public boolean Q2;
    public String Q3;
    public long Q4;
    public TextView R0;
    public String R1;
    public int R2;
    public String R3;
    public DisplayManager R4;
    public pb0 S;
    public ImageView S0;
    public String S1;
    public boolean S2;
    public String S3;
    public lq1 S4;
    public long S5;
    public String T;
    public v81 T0;
    public boolean T1;
    public int T2;
    public String T3;
    public Uri T4;
    public int T5;
    public String U;
    public int U0;
    public boolean U2;
    public boolean U3;
    public boolean U5;
    public boolean V;
    public long V0;
    public as2 V1;
    public zn2 V4;
    public boolean V5;
    public long W;
    public boolean W0;
    public ImageButton W1;
    public boolean W2;
    public boolean W4;
    public volatile boolean X0;
    public ImageButton X1;
    public boolean X2;
    public boolean X4;
    public Uri Y;
    public volatile boolean Y0;
    public boolean Y2;
    public ArrayList Y3;
    public int Y4;
    public float Y5;
    public int Z;
    public volatile long Z0;
    public ArrayList Z1;
    public Uri Z2;
    public boolean Z4;
    public float Z5;
    public String a0;
    public volatile long a1;
    public ImageButton a2;
    public Thread a3;
    public int a4;
    public TextView a5;
    public float a6;
    public int b1;
    public ImageButton b2;
    public Thread b3;
    public sx0 b5;
    public volatile Uri b6;
    public boolean c1;
    public bl2 c2;
    public Thread c3;
    public boolean c5;
    public boolean d1;
    public int d3;
    public Button d5;
    public int d6;
    public int e0;
    public long e1;
    public el e3;
    public dp2 e4;
    public Drawable e5;
    public long f0;
    public View f2;
    public dl f3;
    public Drawable f5;
    public int g0;
    public volatile boolean g3;
    public int g4;
    public Drawable g5;
    public int h1;
    public boolean h2;
    public String h4;
    public uq1 h5;
    public int i1;
    public boolean i2;
    public boolean j0;
    public float j1;
    public boolean j3;
    public Boolean j4;
    public ValueAnimator j5;
    public TextView k1;
    public boolean k2;
    public ks1 k3;
    public Boolean k4;
    public int k5;
    public vq1 l;
    public TextView l1;
    public ImageButton l2;
    public b7 l3;
    public bc0 l4;
    public wq1 l5;
    public w5 m;
    public long m0;
    public TextView m1;
    public ImageButton m2;
    public Bundle m3;
    public String m4;
    public TextView m5;
    public AudioManager n;
    public long n0;
    public ImageView n1;
    public ImageButton n2;
    public fp2 n3;
    public boolean n4;
    public Drawable n5;
    public l91 o;
    public int o0;
    public TextView o1;
    public ImageButton o2;
    public fp2 o3;
    public Drawable o5;
    public q50 p;
    public int p0;
    public long p1;
    public ImageButton p2;
    public a82 p4;
    public boolean p5;
    public qy q;
    public long q0;
    public TextView q1;
    public boolean q2;
    public TextView r1;
    public CircularProgressIndicator r2;
    public rl0 r3;
    public vj2 r4;
    public TextView s1;
    public TextView s2;
    public int s3;
    public hk2 s4;
    public vd2 s5;
    public volatile String t;
    public long t0;
    public yl1 t1;
    public long t2;
    public Uri t4;
    public boolean t5;
    public boolean u0;
    public yl1 u1;
    public boolean u2;
    public volatile HashMap u3;
    public int u5;
    public i40 v;
    public j5 v0;
    public ImageButton v1;
    public volatile Map v3;
    public int v4;
    public boolean v5;
    public ck0 w;
    public TextView w1;
    public kr1 w2;
    public String w3;
    public dp2 w4;
    public String w5;
    public x62 x;
    public wf0 x0;
    public boolean x1;
    public CustomDefaultTimeBar x2;
    public Uri x3;
    public int x4;
    public Thread x5;
    public boolean y1;
    public boolean y2;
    public String y3;
    public dp2 y4;
    public volatile int y5;
    public String z1;
    public boolean z2;
    public String[] z3;
    public int z4;
    public static final List j6 = Arrays.asList("audio/ac3", "audio/eac3", "audio/eac3-joc", "audio/vnd.dts", "audio/vnd.dts.hd", "audio/vnd.dts.hd;profile=lbr", "audio/true-hd");
    public static final CopyOnWriteArraySet k6 = new CopyOnWriteArraySet();
    public static final HashSet q6 = new HashSet();
    public static final HashSet r6 = new HashSet();
    public static final HashSet s6 = new HashSet();
    public final ArrayList r = new ArrayList();
    public final HashMap s = new HashMap();
    public final ConcurrentHashMap u = new ConcurrentHashMap();
    public final ConcurrentHashMap y = new ConcurrentHashMap();
    public final li z = new li(this);
    public float K = 1.0f;
    public final np1 P = new np1(this, 1);
    public final to1 Q = new to1(this, 2);
    public final to1 R = new to1(this, 5);
    public final mq1 X = new mq1(this);
    public final yo1 b0 = new yo1(1);
    public long c0 = -9223372036854775807L;
    public final en2 d0 = new en2();
    public final yo1 h0 = new yo1(2);
    public final to1 i0 = new to1(this, 15);
    public final to1 k0 = new to1(this, 18);
    public int l0 = -1;
    public int r0 = -1;
    public final to1 s0 = new to1(this, 22);
    public final HashMap w0 = new HashMap();
    public boolean y0 = false;
    public boolean z0 = false;
    public float A0 = 1.0f;
    public final to1 f1 = new to1(this, 23);
    public final cq1 g1 = new cq1(this, 3);
    public final to1 U1 = new to1(this, 24);
    public float Y1 = 0.0f;
    public long d2 = -3001;
    public long e2 = -3001;
    public final np1 g2 = new np1(this, 6);
    public final np1 j2 = new np1(this, 7);
    public final cq1 v2 = new cq1(this, 4);
    public long H2 = -1;
    public long I2 = -1;
    public final np1 N2 = new np1(this, 9);
    public final np1 P2 = new np1(this, 11);
    public final np1 V2 = new np1(this, 15);
    public final Rational h3 = new Rational(239, 100);
    public final Rational i3 = new Rational(100, 239);
    public final HashSet p3 = new HashSet();
    public final HashSet q3 = new HashSet();
    public final HashMap t3 = new HashMap();
    public final ArrayList A3 = new ArrayList();
    public final ArrayList B3 = new ArrayList();
    public int F3 = -1;
    public int G3 = -1;
    public final ArrayList J3 = new ArrayList();
    public final ArrayList K3 = new ArrayList();
    public final ArrayList L3 = new ArrayList();
    public final ArrayList M3 = new ArrayList();
    public final ArrayList N3 = new ArrayList();
    public final ArrayList O3 = new ArrayList();
    public final ArrayList P3 = new ArrayList();
    public int V3 = -1;
    public int W3 = -1;
    public int X3 = -1;
    public int Z3 = -1;
    public final ArrayList b4 = new ArrayList();
    public LinkedHashMap c4 = new LinkedHashMap();
    public byte d4 = 0;
    public int f4 = -1;
    public double i4 = 0.0d;
    public double o4 = 0.0d;
    public double q4 = 0.0d;
    public final c82 u4 = new c82();
    public final hj1 E4 = new hj1(2);
    public final ArrayList L4 = new ArrayList();
    public final lo1 U4 = new lo1(this, 5);
    public int c6 = 1;
    public float i5 = 1.0f;
    public final lo1 q5 = new lo1(this, 10);
    public final cq1 r5 = new cq1(this, 5);
    public long z5 = -9223372036854775807L;
    public long A5 = -9223372036854775807L;
    public long B5 = -9223372036854775807L;
    public long C5 = -9223372036854775807L;
    public long F5 = -9223372036854775807L;
    public int G5 = 0;
    public final cq1 H5 = new cq1(this, 6);
    public final cq1 I5 = new cq1(this, 7);
    public final Handler J5 = new Handler(Looper.getMainLooper());
    public final cq1 K5 = new cq1(this, 0);
    public final cq1 L5 = new cq1(this, 1);
    public final lo1 P5 = new lo1(this, 19);
    public final HashMap Q5 = new HashMap();
    public final ArrayDeque R5 = new ArrayDeque();
    public final cq1 W5 = new cq1(this, 2);
    public final to1 X5 = new to1(this, 0);

    static {
        vk1 vk1Var = new vk1();
        TimeUnit timeUnit = TimeUnit.SECONDS;
        vk1Var.a(60L, timeUnit);
        vk1Var.b(120L, timeUnit);
        vk1Var.f = true;
        vk1Var.i = true;
        vk1Var.j = true;
        t6 = new wk1(vk1Var);
        v6 = (char) 60000;
        x6 = 1800000;
        A6 = 0L;
        B6 = 0L;
        F6 = 0.0f;
        G6 = false;
        H6 = true;
        I6 = 100.0f;
        J6 = new LinearInterpolator();
        K6 = false;
        M6 = false;
        N6 = false;
        O6 = false;
        Q6 = DesugarCollections.synchronizedMap(new un0(8, 0.75f, true, (byte) 2));
        R6 = new String[]{"brief", "button", "auto", "off"};
        S6 = new int[]{R.string.skip_mode_brief_short, R.string.skip_mode_button_short, R.string.skip_mode_auto_short, R.string.skip_mode_off_short};
        T6 = new Pattern[]{Pattern.compile("(?i)s\\s*(\\d{1,2})\\s*[.\\-_ ]?\\s*e\\s*(\\d{1,3})"), Pattern.compile("(?<!\\d)(\\d{1,2})\\s*[xх]\\s*(\\d{1,3})(?!\\d)")};
        U6 = Pattern.compile("\\d+(?:[.,]\\d+)?");
        V6 = new String[]{".", ".auto."};
        W6 = new ConcurrentHashMap();
        X6 = new int[]{5, 6, 7, 8, 14};
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
            if (hp2.c(str, arrayList) >= 0) {
                return str;
            }
        }
        return null;
    }

    public static String C(rl0 rl0Var) {
        String str = "";
        if (rl0Var == null) {
            return "";
        }
        String str2 = rl0Var.l;
        StringBuilder sb = new StringBuilder();
        sb.append(rl0Var.p);
        if (str2 != null) {
            str = " " + str2;
        }
        sb.append(str);
        return sb.toString();
    }

    public static p81 E0(Uri uri, String str, String str2) {
        c81 c81Var = new c81();
        c81Var.b = uri;
        if (str != null || str2 != null) {
            w81 w81Var = new w81();
            if (str != null) {
                w81Var.a = str;
                w81Var.e = str;
            }
            if (str2 != null) {
                w81Var.n = Uri.parse(str2);
            }
            c81Var.k = new x81(w81Var);
        }
        return c81Var.a();
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

    public static yb F0(PlayerActivity playerActivity) {
        ry1 ry1Var = yb.e;
        yb ybVarB = yb.b(playerActivity, tb.i, null, yb.f);
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        linkedHashSet.add(2);
        int[] iArr = {5, 6, 18, 17, 7, 8, 30, 14};
        int i = 0;
        for (int i2 = 0; i2 < 8; i2++) {
            int i3 = iArr[i2];
            if (ys2.l(ybVarB.a, i3)) {
                linkedHashSet.add(Integer.valueOf(i3));
            }
        }
        for (int i4 : X6) {
            linkedHashSet.add(Integer.valueOf(i4));
        }
        int[] iArr2 = new int[linkedHashSet.size()];
        Iterator it = linkedHashSet.iterator();
        while (it.hasNext()) {
            iArr2[i] = ((Integer) it.next()).intValue();
            i++;
        }
        return new yb(yb.a(iArr2, Math.max(ybVarB.b, 8)), yb.e, yb.f);
    }

    public static ArrayList G0(int i) {
        ArrayList arrayList = new ArrayList();
        mg0 mg0Var = g6;
        if (mg0Var != null) {
            fw0 fw0VarN = mg0Var.E().a.listIterator(0);
            while (fw0VarN.hasNext()) {
                wp2 wp2Var = (wp2) fw0VarN.next();
                if (wp2Var.b.c == i) {
                    for (int i2 = 0; i2 < wp2Var.a; i2++) {
                        rl0 rl0VarA = wp2Var.a(i2);
                        if (i != 3 || !h1(rl0VarA)) {
                            arrayList.add(rl0VarA);
                        }
                    }
                }
            }
        }
        return arrayList;
    }

    public static boolean G1(Throwable th) {
        while (th != null) {
            if (th instanceof eu0) {
                int i = ((eu0) th).o;
                return i == 401 || i == 403 || i == 404 || i == 410;
            }
            th = th.getCause();
        }
        return false;
    }

    public static rl0 J0() {
        mg0 mg0Var = g6;
        if (mg0Var == null) {
            return null;
        }
        fw0 fw0VarN = mg0Var.E().a.listIterator(0);
        while (fw0VarN.hasNext()) {
            wp2 wp2Var = (wp2) fw0VarN.next();
            dp2 dp2Var = wp2Var.b;
            rl0[] rl0VarArr = dp2Var.d;
            if (dp2Var.c == 1 && wp2Var.b()) {
                for (int i = 0; i < wp2Var.a; i++) {
                    if (wp2Var.e[i]) {
                        return rl0VarArr[i];
                    }
                }
                return rl0VarArr[0];
            }
        }
        return null;
    }

    public static String J3(dj2 dj2Var) {
        byte b;
        if (dj2Var == null || (b = dj2Var.l) == 2) {
            return "device_decoder";
        }
        if (b != 3) {
            return b != 4 ? "source_stalled" : "suppressed";
        }
        return "not_ending";
    }

    public static ArrayList K() {
        ArrayList arrayList = new ArrayList();
        mg0 mg0Var = g6;
        if (mg0Var != null) {
            fw0 fw0VarN = mg0Var.E().a.listIterator(0);
            int i = 0;
            while (fw0VarN.hasNext()) {
                wp2 wp2Var = (wp2) fw0VarN.next();
                dp2 dp2Var = wp2Var.b;
                if (dp2Var.c == 3) {
                    for (int i2 = 0; i2 < wp2Var.a; i2++) {
                        rl0 rl0Var = dp2Var.d[i2];
                        if (!h1(rl0Var)) {
                            int i3 = i + 1;
                            arrayList.add(new xq1(rl0Var, dp2Var, i2, i3, wp2Var.c(i2, false), wp2Var.e[i2]));
                            i = i3;
                        }
                    }
                }
            }
        }
        return arrayList;
    }

    public static String K0(int i) {
        mg0 mg0Var = g6;
        if (mg0Var == null) {
            return null;
        }
        xp2 xp2VarE = mg0Var.E();
        if (!xp2VarE.b(i)) {
            return "#none";
        }
        if (i == 1 && !T0(1)) {
            return null;
        }
        fw0 fw0VarN = xp2VarE.a.listIterator(0);
        while (fw0VarN.hasNext()) {
            wp2 wp2Var = (wp2) fw0VarN.next();
            if (wp2Var.b()) {
                dp2 dp2Var = wp2Var.b;
                if (dp2Var.c == i) {
                    return dp2Var.d[0].a;
                }
            }
        }
        return null;
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

    public static String L3(int i) {
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

    public static dp2 M0(int i, String str) {
        mg0 mg0Var;
        if (str == null || (mg0Var = g6) == null) {
            return null;
        }
        fw0 fw0VarN = mg0Var.E().a.listIterator(0);
        while (fw0VarN.hasNext()) {
            dp2 dp2Var = ((wp2) fw0VarN.next()).b;
            if (dp2Var.c == i && str.equals(dp2Var.d[0].a)) {
                return dp2Var;
            }
        }
        return null;
    }

    public static void M2(long j) {
        mg0 mg0Var = g6;
        mg0Var.A1();
        p82 p82Var = mg0Var.P;
        if (p82Var.b == 0 && p82Var.a > 0) {
            g6.t1(new p82(Math.max(0L, j) * 1000, 0L));
        }
        g6.o1(j);
    }

    public static String M4(rl0 rl0Var) {
        String str = "";
        if (rl0Var == null) {
            return "";
        }
        String str2 = rl0Var.l;
        StringBuilder sb = new StringBuilder();
        sb.append(rl0Var.p);
        if (str2 != null) {
            str = " " + str2;
        }
        sb.append(str);
        return sb.toString();
    }

    public static void N2(long j) {
        mg0 mg0Var = g6;
        if (mg0Var == null) {
            return;
        }
        mg0Var.t1(p82.c);
        g6.o1(Math.max(0L, j));
    }

    public static void N3(int i) {
        int iV = g6.V() + i;
        if (iV < 0 || iV >= g6.a1()) {
            return;
        }
        g6.m(iV);
        g6.d();
    }

    public static int O4() {
        k10 k10Var;
        mg0 mg0Var = g6;
        if (mg0Var != null) {
            mg0Var.A1();
            k10Var = mg0Var.g0;
        } else {
            k10Var = null;
        }
        if (k10Var == null) {
            return -1;
        }
        return k10Var.e + k10Var.g + k10Var.f;
    }

    public static void P1() {
        mg0 mg0Var = g6;
        if (mg0Var == null || mg0Var.C() != 1) {
            return;
        }
        g6.d();
    }

    public static LinkedHashMap Q1(String[] strArr, String[] strArr2) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        if (strArr != null && strArr2 != null) {
            int iMin = Math.min(strArr.length, strArr2.length);
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < iMin; i++) {
                arrayList.add(Integer.valueOf(i));
            }
            Collections.sort(arrayList, new h51(strArr, (byte) 2));
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

    public static String Q3(ArrayList arrayList, int i, String str) {
        String str2 = (g6 == null || i < 0 || i >= arrayList.size()) ? null : (String) arrayList.get(i);
        return (str2 == null || str2.isEmpty()) ? str : str2;
    }

    public static LinkedHashMap R1(List list) {
        int size = list.size();
        String[] strArr = new String[size];
        String[] strArr2 = new String[size];
        for (int i = 0; i < size; i++) {
            strArr[i] = ((hs1) list.get(i)).a;
            strArr2[i] = ((hs1) list.get(i)).b;
        }
        return Q1(strArr, strArr2);
    }

    public static String R2(xp2 xp2Var, int i) {
        fw0 fw0VarN = xp2Var.a.listIterator(0);
        while (fw0VarN.hasNext()) {
            wp2 wp2Var = (wp2) fw0VarN.next();
            if (wp2Var.b.c == i && wp2Var.b()) {
                for (int i2 = 0; i2 < wp2Var.a; i2++) {
                    if (wp2Var.e[i2]) {
                        return String.valueOf(wp2Var.a(i2).p);
                    }
                }
            }
        }
        return "none";
    }

    public static boolean S0(rl0 rl0Var) {
        try {
            Iterator it = i71.e(rl0Var.p, false, false).iterator();
            boolean z = false;
            while (it.hasNext()) {
                if (((y61) it.next()).g) {
                    return false;
                }
                z = true;
            }
            return z;
        } catch (f71 | RuntimeException unused) {
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
                byte[][] bArr = gt2.i;
                if (bArr != null && bArr.length != 0) {
                    int length = bArr.length;
                    int i5 = 0;
                    loop0: while (true) {
                        if (i5 < length) {
                            byte[] bArr2 = bArr[i5];
                            if (bArr2 == null || bArr2.length != 32) {
                                break;
                            }
                            int[] iArr = {6, 5};
                            int[] iArr2 = {-705420022, 1171285320};
                            for (int i7 = 0; i7 < 2; i7++) {
                                int i8 = 0;
                                for (int i9 = 0; i9 < 4; i9++) {
                                    i8 = (i8 << 8) | (bArr2[(iArr[i7] * 4) + i9] & 255);
                                }
                                if (i8 != (iArr2[i7] ^ Integer.rotateLeft(1943153782, (iArr[i7] * 5) & 31))) {
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
                    int i10 = (gt2.t ? 1 : 0) | (gt2.r ? 1 : 0) | gt2.p;
                    if (!gt2.d0()) {
                        i3 = i10;
                    }
                } catch (Throwable unused) {
                    i3 = 0;
                }
                return i2 - (((i * i3) * i4) * (i2 % 528));
            } catch (NumberFormatException unused2) {
            }
        }
        return 0;
    }

    public static String S3(String str, al alVar) {
        StringBuilder sbK = we2.k("subs.", str, "-");
        sbK.append(alVar.l);
        sbK.append("-");
        sbK.append(alVar.m);
        return sbK.toString().replaceAll("[^A-Za-z0-9.]", "-");
    }

    public static p81 S4(p81 p81Var, o81 o81Var) {
        ArrayList arrayList = new ArrayList();
        k81 k81Var = p81Var.b;
        if (k81Var != null) {
            arrayList.addAll(k81Var.g);
        }
        arrayList.add(o81Var);
        c81 c81VarA = p81Var.a();
        c81VarA.h = hw0.l(arrayList);
        return c81VarA.a();
    }

    public static boolean T0(int i) {
        fs2 fs2VarI = g6.A0().H.values().iterator();
        while (fs2VarI.hasNext()) {
            if (((pp2) fs2VarI.next()).a.c == i) {
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

    public static ArrayList T3(al alVar) {
        String str;
        ArrayList arrayList = new ArrayList(2);
        String str2 = (String) alVar.o;
        String str3 = (String) alVar.n;
        if (str2 != null) {
            arrayList.add(S3("t" + str2, alVar));
        }
        if (str3 != null) {
            arrayList.add(S3(str3, alVar));
        }
        String str4 = null;
        if (str2 != null) {
            str = null;
        } else if (str3 == null) {
            wk1 wk1Var = gk2.a;
            str = null;
        } else {
            str = (String) gk2.c.get(str3);
        }
        if (str != null) {
            arrayList.add(S3("t".concat(str), alVar));
        }
        if (str3 == null) {
            if (str2 == null) {
                wk1 wk1Var2 = gk2.a;
            } else {
                str4 = (String) gk2.c.get(str2);
            }
        }
        if (str4 != null) {
            arrayList.add(S3(str4, alVar));
        }
        if (arrayList.isEmpty()) {
            arrayList.add(S3("none", alVar));
        }
        return arrayList;
    }

    public static boolean U0(xp2 xp2Var, int i) {
        fw0 fw0VarN = xp2Var.a.listIterator(0);
        while (fw0VarN.hasNext()) {
            wp2 wp2Var = (wp2) fw0VarN.next();
            if (wp2Var.b.c == i) {
                boolean z = i == 1;
                for (int i2 = 0; i2 < wp2Var.d.length; i2++) {
                    if (wp2Var.c(i2, z)) {
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

    public static void W2(TextView textView, String str) {
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

    public static String X(Throwable th) {
        StringBuilder sb = new StringBuilder();
        j71 j71Var = (j71) A0(th, j71.class);
        if (j71Var != null) {
            sb.append("surfaceValid=");
            sb.append(j71Var.m);
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

    public static double c0() {
        mg0 mg0Var = g6;
        if (mg0Var == null) {
            return 0.0d;
        }
        long duration = mg0Var.getDuration();
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

    public static String e3(String str) {
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

    public static int e4(int i) {
        if (g6 == null) {
            return -1;
        }
        int i2 = 0;
        int i3 = 0;
        while (true) {
            mg0 mg0Var = g6;
            mg0Var.A1();
            if (i2 >= mg0Var.g.length) {
                return -1;
            }
            mg0 mg0Var2 = g6;
            mg0Var2.A1();
            if (mg0Var2.g[i2].getTrackType() == 3 && (i3 = i3 + 1) == i) {
                return i2;
            }
            i2++;
        }
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
        mg0 mg0Var = g6;
        if (mg0Var == null || !mg0Var.Q0()) {
            return false;
        }
        long duration = g6.getDuration();
        return duration == -9223372036854775807L || duration < 600000;
    }

    public static void f2(Exception exc) {
        rl0 rl0Var;
        MediaCodec.CodecException codecException = (MediaCodec.CodecException) A0(exc, MediaCodec.CodecException.class);
        if (codecException == null || codecException.getErrorCode() != 1100) {
            return;
        }
        if (exc instanceof wf0) {
            rl0Var = ((wf0) exc).q;
        } else {
            mg0 mg0Var = g6;
            if (mg0Var != null) {
                mg0Var.A1();
                rl0Var = mg0Var.V;
            } else {
                rl0Var = null;
            }
        }
        if (rl0Var != null && ue1.o(rl0Var.p) && s6.add(M4(rl0Var))) {
            gt2.K("resource refusal remembered, next try is plain: ".concat(M4(rl0Var)));
        }
    }

    public static String f3(String str) {
        if (str == null || str.isEmpty() || "und".equals(str)) {
            return "?";
        }
        String language = Locale.forLanguageTag(str).getLanguage();
        if (!language.isEmpty()) {
            str = language;
        }
        return str.toUpperCase(Locale.ROOT);
    }

    public static boolean g1(Uri uri, String str) {
        if ("video/x-matroska".equals(str)) {
            return true;
        }
        if (uri == null) {
            return false;
        }
        AtomicLong atomicLong = kp2.p;
        if (uri.toString().equals(kp2.q)) {
            return true;
        }
        String path = uri.getPath();
        return path != null && path.regionMatches(true, path.length() + (-4), ".mkv", 0, 4);
    }

    public static boolean h1(rl0 rl0Var) {
        return "application/cea-608".equals(rl0Var.p) && rl0Var.P == -1;
    }

    public static void i(pq1 pq1Var) {
        mg0 mg0Var = g6;
        if (mg0Var == null || pq1Var == null) {
            return;
        }
        dp2 dp2Var = pq1Var.c;
        k50 k50Var = (k50) mg0Var.A0();
        k50Var.getClass();
        j50 j50Var = new j50(k50Var);
        j50Var.d(1);
        j50Var.j(1, false);
        j50Var.i(new pp2(dp2Var, Collections.singletonList(Integer.valueOf(pq1Var.d))));
        mg0Var.q0(j50Var.b());
    }

    public static boolean i1(MediaCodecInfo mediaCodecInfo) {
        if (Build.VERSION.SDK_INT >= 29) {
            return !mediaCodecInfo.isHardwareAccelerated();
        }
        String name = mediaCodecInfo.getName();
        return name.startsWith("OMX.google.") || name.startsWith("c2.android.");
    }

    public static ArrayList k1(fp2 fp2Var, fp2 fp2Var2, String str) {
        String[] strArr;
        ArrayList arrayList = new ArrayList();
        fp2[] fp2VarArr = {fp2Var, fp2Var2};
        for (int i = 0; i < 2; i++) {
            fp2 fp2Var3 = fp2VarArr[i];
            if (fp2Var3 != null && (strArr = fp2Var3.d) != null) {
                arrayList.addAll(Arrays.asList(strArr));
            }
        }
        arrayList.addAll(gt2.c0(str));
        return arrayList;
    }

    public static String l0(String str) {
        ArrayList arrayListB = ol1.b(Collections.singletonList(str));
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
        byte[][] bArr = gt2.i;
        int i2 = 0;
        if (bArr != null && bArr.length != 0) {
            int length = bArr.length;
            int i3 = 0;
            loop0: while (true) {
                if (i3 < length) {
                    byte[] bArr2 = bArr[i3];
                    if (bArr2 != null && bArr2.length == 32) {
                        int[] iArr = {0, 6};
                        int[] iArr2 = {-2008654011, 1329266542};
                        for (int i4 = 0; i4 < 2; i4++) {
                            int i5 = iArr[i4] * 4;
                            int i7 = 0;
                            for (int i8 = 0; i8 < 4; i8++) {
                                i7 = (i7 << 8) | (bArr2[i5 + i8] & 255);
                            }
                            if (i7 - (iArr2[i4] ^ Integer.rotateLeft(417940964, (iArr[i4] * 5) & 31)) != 0) {
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
            boolean zD0 = gt2.d0();
            if (gt2.q()) {
                zD0 = 1;
            }
            i2 = (gt2.t ? 1 : 0) | zD0 | gt2.p | gt2.q | (gt2.r ? 1 : 0);
        } catch (Throwable unused) {
        }
        int i9 = i * i2;
        long jCurrentTimeMillis = System.currentTimeMillis() - A6;
        float f2 = jCurrentTimeMillis < 45000 ? 0.0f : 1.0f;
        mg0 mg0Var = g6;
        int iMin = (int) Math.min(10L, (mg0Var == null ? 0L : mg0Var.O0()) / 60000);
        double dSin = Math.sin((((jCurrentTimeMillis % 15000) * 2.0d) * 3.141592653589793d) / 15000.0d);
        double d = jCurrentTimeMillis;
        return ((((float) (((Math.sin(d / 170.0d) * Math.sin(d / 310.0d) * 0.6d) + 1.0d) * dSin)) * i9 * f2 * 0.2f * (iMin + 1)) + 1.0f) * f;
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

    public static boolean y1(gs1 gs1Var, ks1 ks1Var) {
        if (gs1Var == null) {
            return false;
        }
        fp2 fp2Var = gs1Var.p;
        return fp2Var.a != null || ks1Var.e.a != null || fp2Var.b() || ks1Var.e.b();
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
        List listL4 = playerActivity.l4((String) list.get(0));
        Iterator it = list.iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            if (!listL4.contains(str)) {
                Iterator it2 = arrayList.iterator();
                while (it2.hasNext()) {
                    String str2 = (String) it2.next();
                    String[] strArr = V6;
                    int length = strArr.length;
                    for (?? r9 = z2; r9 < length; r9++) {
                        String str3 = strArr[r9];
                        String[] strArr2 = zi0.p;
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
                                gt2.K(sb.toString());
                                playerActivity.B(playerActivity.y5, i, playerActivity.H.c, Uri.fromFile(file), str, z);
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
        for (pq1 pq1Var : I()) {
            String str2 = pq1Var.f;
            if (str2 != null && !arrayList.contains(str2)) {
                arrayList.add(pq1Var.f);
            }
        }
        if (!arrayList.isEmpty()) {
            intent.putExtra("mediaLanguages", (String[]) arrayList.toArray(new String[0]));
        }
        startActivityForResult(intent, 100);
    }

    public final boolean A2() {
        if (K4()) {
            return true;
        }
        return !L6 && this.H.z0;
    }

    public final boolean A3() {
        return this.c6 == 2 && SystemClock.uptimeMillis() < this.E5;
    }

    public final void A4(vd2 vd2Var) {
        if (g6 == null || vd2Var == null || this.c6 != 2) {
            return;
        }
        long jA = vd2Var.a() - Math.round(vd2Var.a * 1000.0d);
        X2(jA > 0 ? (vd2Var.a() - g6.O0()) / jA : 0.0d);
    }

    public final void B(int i, int i2, Uri uri, Uri uri2, String str, boolean z) {
        mg0 mg0Var;
        if (i != this.y5 || (mg0Var = g6) == null || mg0Var.V() != i2 || !Objects.equals(uri, this.H.c)) {
            StringBuilder sb = new StringBuilder("subtitles: dropping ");
            sb.append(str);
            sb.append(", generation ");
            sb.append(i);
            sb.append("/");
            sb.append(this.y5);
            sb.append(", item ");
            sb.append(i2);
            sb.append("/");
            mg0 mg0Var2 = g6;
            sb.append(mg0Var2 == null ? -1 : mg0Var2.V());
            sb.append(", same media ");
            sb.append(Objects.equals(uri, this.H.c));
            gt2.K(sb.toString());
            return;
        }
        W3(0);
        if (z) {
            U(uri2);
            n3(getString(R.string.subtitle_search_found_secondary, l0(str)), false, R.drawable.ic_subtitle_secondary_24dp);
            return;
        }
        this.H.C(uri2);
        if (!a(uri2)) {
            gt2.K("subtitles: " + uri2.getLastPathSegment() + " not added, already there");
            return;
        }
        String string = getString(R.string.subtitle_search_found, l0(str));
        String path = uri2.getPath();
        if (path != null && path.contains(".auto.")) {
            string = getString(R.string.subtitle_machine_translated, string);
        }
        n3(string, false, R.drawable.ic_subtitles_24dp);
    }

    public final void B1(boolean z) {
        String str;
        ContextThemeWrapper contextThemeWrapperE = r2.e(this);
        this.C4 = z;
        Dialog dialog = r2.a;
        String str2 = null;
        if (dialog != null) {
            dialog.dismiss();
            r2.a = null;
        }
        LinearLayout linearLayoutC = we2.c(contextThemeWrapperE, 1);
        int iP = gt2.p(10);
        linearLayoutC.setPadding(iP, iP, iP, iP);
        boolean z2 = getResources().getConfiguration().screenHeightDp >= 480;
        if (z2) {
            TextView textView = new TextView(contextThemeWrapperE);
            textView.setText(getString(R.string.subtitle_search_manual));
            textView.setTextColor(lj.n(contextThemeWrapperE, R.attr.colorOnSurface, -1));
            textView.setTextSize(2, this.V1.y());
            textView.setTypeface(Typeface.DEFAULT_BOLD);
            textView.setPadding(gt2.p(10), gt2.p(10), gt2.p(10), gt2.p(10));
            linearLayoutC.addView(r2.s(contextThemeWrapperE, this.V1, textView, null));
            View view = new View(contextThemeWrapperE);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, gt2.p(1));
            layoutParams.bottomMargin = gt2.p(4);
            view.setLayoutParams(layoutParams);
            view.setBackgroundColor(lj.n(contextThemeWrapperE, R.attr.colorOutlineVariant, contextThemeWrapperE.getColor(R.color.divider)));
            linearLayoutC.addView(view);
        }
        LinearLayout linearLayout = new LinearLayout(contextThemeWrapperE);
        linearLayout.setOrientation(0);
        linearLayout.setGravity(16);
        linearLayoutC.addView(linearLayout, new LinearLayout.LayoutParams(-1, -2));
        EditText editTextF = r2.F(linearLayout, getString(R.string.subtitle_search_label), getString(R.string.subtitle_search_hint));
        ((View) editTextF.getParent().getParent()).setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
        if (!z2) {
            MaterialButton materialButtonM = r2.m(contextThemeWrapperE, this.V1, R.drawable.ic_close_24dp, contextThemeWrapperE.getString(R.string.error_close), false);
            materialButtonM.setId(R.id.picker_close);
            linearLayout.addView(materialButtonM);
        }
        String str3 = this.D4;
        if (str3 != null) {
            str = str3;
        } else {
            String str4 = this.Q3;
            if (str4 != null) {
                str2 = this.R3;
                if (str2 == null) {
                    str2 = str4;
                }
            } else {
                mg0 mg0Var = g6;
                al alVarV1 = mg0Var != null ? v1(mg0Var.V()) : null;
                if (alVarV1 != null && !alVarV1.m()) {
                    if (((String) alVarV1.n) != null) {
                        str2 = "tt" + alVarV1.l();
                    } else {
                        str2 = (String) alVarV1.o;
                    }
                }
            }
            str = str2;
        }
        editTextF.setInputType(1);
        editTextF.setImeOptions(268435459);
        ((TextInputLayout) editTextF.getParent().getParent()).setEndIconMode(2);
        t01 t01Var = new t01(contextThemeWrapperE);
        t01Var.setIndeterminate(true);
        t01Var.setVisibility(4);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, -2);
        layoutParams2.topMargin = gt2.p(4);
        linearLayoutC.addView(t01Var, layoutParams2);
        LinearLayout linearLayout2 = new LinearLayout(contextThemeWrapperE);
        linearLayout2.setOrientation(1);
        fq1 fq1Var = new fq1(contextThemeWrapperE, this.V1.b(72.0f));
        fq1Var.setScrollIndicators(2);
        fq1Var.addView(linearLayout2);
        linearLayoutC.addView(fq1Var, new LinearLayout.LayoutParams(-1, -2));
        Dialog dialog2 = new Dialog(this, android.R.style.Theme.Translucent.NoTitleBar);
        r2.v(this, this.V1, dialog2, linearLayoutC, false);
        gt2.G(dialog2, linearLayoutC);
        final Handler handler = new Handler(Looper.getMainLooper());
        final Runnable[] runnableArr = new Runnable[1];
        editTextF.addTextChangedListener(new gq1(this, new String[]{""}, runnableArr, handler, linearLayout2, t01Var, contextThemeWrapperE, dialog2));
        editTextF.setOnEditorActionListener(new TextView.OnEditorActionListener() { // from class: xo1
            @Override // android.widget.TextView.OnEditorActionListener
            public final boolean onEditorAction(TextView textView2, int i, KeyEvent keyEvent) {
                LoudnessEnhancer loudnessEnhancer = PlayerActivity.e6;
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
        o3(dialog2);
    }

    public final void B2(Bundle bundle) {
        k81 k81Var;
        if (this.j3 && i6) {
            if (g6 != null) {
                C2();
            }
            Bundle bundle2 = new Bundle();
            mg0 mg0Var = g6;
            int iV = mg0Var == null ? this.C3 : mg0Var.V();
            ArrayList arrayList = this.A3;
            boolean z = iV >= 0 && iV < arrayList.size();
            bundle2.putInt("index", iV);
            p81 p81Var = z ? (p81) arrayList.get(iV) : null;
            Uri uriF0 = (p81Var == null || (k81Var = p81Var.b) == null) ? f0() : k81Var.a;
            if (uriF0 != null) {
                bundle2.putString("uri", uriF0.toString());
            }
            vt1 vt1Var = this.H;
            long jH = vt1Var.h(vt1Var.c);
            if (jH >= 0) {
                bundle2.putLong("position", jH);
            }
            bundle2.putLongArray("episodePositions", this.E3);
            bundle2.putInt("stickyQuality", this.g4);
            bundle2.putString("stickyVoice", this.h4);
            bundle2.putString("audioTrack", this.H.o);
            bundle2.putString("subtitleTrack", this.H.n);
            bundle2.putInt("aspectClass", this.H.l);
            bundle2.putInt("resizeMode", this.H.i);
            bundle2.putFloat("scale", this.H.j);
            bundle2.putFloat("aspectRatio", this.H.k);
            bundle2.putFloat("speed", this.H.m);
            b7 b7Var = this.l3;
            if (b7Var != null) {
                Bundle bundle3 = new Bundle();
                ArrayList arrayList2 = (ArrayList) b7Var.b;
                int size = arrayList2.size();
                int[] iArr = new int[size];
                long[] jArr = new long[size];
                long[] jArr2 = new long[size];
                long[] jArr3 = new long[size];
                for (int i = 0; i < size; i++) {
                    a7 a7Var = (a7) arrayList2.get(i);
                    iArr[i] = a7Var.a;
                    jArr[i] = a7Var.b;
                    jArr2[i] = a7Var.c;
                    jArr3[i] = a7Var.d;
                }
                bundle3.putIntArray("visitIndex", iArr);
                bundle3.putLongArray("visitStarted", jArr);
                bundle3.putLongArray("visitPosition", jArr2);
                bundle3.putLongArray("visitDuration", jArr3);
                bundle3.putLongArray("durations", (long[]) b7Var.c);
                bundle3.putBooleanArray("finished", (boolean[]) b7Var.d);
                bundle3.putBoolean("everPlayed", b7Var.a);
                bundle3.putString("error", (String) b7Var.e);
                fp2 fp2Var = (fp2) b7Var.f;
                if (fp2Var != null) {
                    bundle3.putBundle("audio", fp2Var.d());
                }
                fp2 fp2Var2 = (fp2) b7Var.g;
                if (fp2Var2 != null) {
                    bundle3.putBundle("subtitle", fp2Var2.d());
                }
                bundle3.putStringArray("audioChosenBy", (String[]) b7Var.h);
                bundle3.putStringArray("subtitleChosenBy", (String[]) b7Var.i);
                bundle2.putBundle("playlistSession", bundle3);
            }
            bundle.putBundle("apiSession", bundle2);
        }
    }

    public final void B3() {
        Button button = this.d5;
        if (button == null || this.L0 <= 0) {
            return;
        }
        px pxVar = (px) button.getLayoutParams();
        int i = ((ViewGroup.MarginLayoutParams) pxVar).bottomMargin;
        int i2 = this.L0;
        if (i != i2) {
            ((ViewGroup.MarginLayoutParams) pxVar).bottomMargin = i2;
            this.d5.setLayoutParams(pxVar);
        }
    }

    /* JADX WARN: Code duplicated, block: B:117:0x01c6  */
    /* JADX WARN: Code duplicated, block: B:224:0x03cb  */
    /* JADX WARN: Code duplicated, block: B:232:0x03f5  */
    /* JADX WARN: Code duplicated, block: B:234:0x03fe  */
    /* JADX WARN: Code duplicated, block: B:242:0x041a  */
    /* JADX WARN: Code duplicated, block: B:245:0x043c  */
    /* JADX WARN: Code duplicated, block: B:247:0x0446  */
    /* JADX WARN: Code duplicated, block: B:248:0x044b  */
    /* JADX WARN: Code duplicated, block: B:251:0x0459 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:253:0x045d  */
    /* JADX WARN: Code duplicated, block: B:274:0x0521  */
    /* JADX WARN: Code duplicated, block: B:282:0x054f A[LOOP:5: B:280:0x0549->B:282:0x054f, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:285:0x057a  */
    /* JADX WARN: Code duplicated, block: B:288:0x0585  */
    /* JADX WARN: Code duplicated, block: B:289:0x0587  */
    /* JADX WARN: Code duplicated, block: B:302:0x0545 A[ADDED_TO_REGION, REMOVE, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:46:0x00b6  */
    /* JADX WARN: Code duplicated, block: B:48:0x00bc  */
    /* JADX WARN: Code duplicated, block: B:49:0x00bf  */
    /* JADX WARN: Code duplicated, block: B:57:0x00d9  */
    /* JADX WARN: Code duplicated, block: B:79:0x0111  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r13v12 */
    /* JADX WARN: Type inference failed for: r13v13 */
    /* JADX WARN: Type inference failed for: r13v7 */
    /* JADX WARN: Type inference failed for: r13v8 */
    /* JADX WARN: Type inference failed for: r13v9, types: [java.lang.CharSequence] */
    /* JADX WARN: Type inference failed for: r1v7, types: [android.widget.TextView] */
    /* JADX WARN: Type inference failed for: r20v0, types: [android.app.Activity, android.content.Context, com.brouken.player.PlayerActivity] */
    public final void B4() {
        float f;
        StaticLayout staticLayout;
        int iB;
        int i;
        TextView textView;
        float f2;
        View viewFindViewById;
        int iB2;
        int top;
        StaticLayout staticLayoutBuild;
        ?? r13;
        int i2;
        float fMax;
        int i3;
        px pxVar;
        int paddingRight;
        int i4;
        SpannableStringBuilder spannableStringBuilder;
        Drawable drawable;
        Object obj;
        Drawable drawable2;
        String str;
        String str2;
        rl0 rl0Var;
        String string;
        SpannableStringBuilder spannableStringBuilderG;
        rl0 rl0Var2;
        TextView textView2 = this.r1;
        if (textView2 == null) {
            return;
        }
        int i5 = 0;
        if (!this.H.t0 || g6 == null || !C6 || this.G) {
            x0(textView2, false);
            return;
        }
        yd0 yd0Var = new yd0((byte) 3);
        int iB3 = this.V1.b(L6 ? 12.307693f : 14.0f);
        mg0 mg0Var = g6;
        mg0Var.A1();
        rl0 rl0Var3 = mg0Var.V;
        int i7 = 4;
        char c = 2;
        char c2 = 1;
        if (rl0Var3 != null) {
            SpannableStringBuilder spannableStringBuilder2 = new SpannableStringBuilder();
            if (rl0Var3.w > 0 && rl0Var3.x > 0) {
                d(spannableStringBuilder2, rl0Var3.w + "×" + rl0Var3.x);
            }
            String strE = qy.e(rl0Var3.p);
            if (strE == null) {
                strE = qy.e(rl0Var3.l);
            }
            if (strE == null) {
                strE = null;
                f = 0.0f;
            } else {
                HashMap map = i71.a;
                Pair pairC = gt.c(rl0Var3);
                if (pairC == null) {
                    str = null;
                    f = 0.0f;
                } else {
                    f = 0.0f;
                    String str3 = rl0Var3.p;
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
                    strE = we2.f(strE, " ", str);
                }
            }
            f(spannableStringBuilder2, strE);
            st stVar = rl0Var3.H;
            if (stVar != null) {
                int i8 = stVar.c;
                if (i8 == 6) {
                    str2 = "HDR10";
                } else if (i8 != 7) {
                    str2 = null;
                } else {
                    str2 = "HLG";
                }
            } else {
                str2 = null;
            }
            f(spannableStringBuilder2, str2);
            yd0Var.n(M3(R.drawable.ic_theaters_24dp, iB3), spannableStringBuilder2, 0);
            yd0Var.n(null, j0(this.T), 3);
            float fN4 = N4();
            Display defaultDisplay = getWindowManager().getDefaultDisplay();
            float refreshRate = defaultDisplay == null ? f : defaultDisplay.getRefreshRate();
            yd0Var.n(null, (refreshRate > f && fN4 > f) ? G(Math.abs(refreshRate - fN4) < 0.01f ? getString(R.string.stats_display_matched, T1(refreshRate)) : getString(R.string.stats_display, T1(refreshRate), T1(fN4))) : null, 0);
            boolean z = l6;
            int i9 = R.string.stats_dv_hdr10;
            if (!z) {
                pb0 pb0Var = this.S;
                if (pb0Var != null) {
                    String str5 = pb0Var.o;
                    if (str5 == null) {
                        string = null;
                    } else {
                        if (str5.startsWith("DV 7 → 8.1")) {
                            i9 = R.string.stats_dv_converted;
                        }
                        string = getString(i9);
                    }
                } else {
                    mg0 mg0Var2 = g6;
                    if (mg0Var2 != null) {
                        mg0Var2.A1();
                        rl0Var = mg0Var2.V;
                    } else {
                        rl0Var = null;
                    }
                    if (rl0Var == null || !pb0.t(rl0Var)) {
                        string = null;
                    } else {
                        string = getString(R.string.stats_dv_hdr10);
                    }
                }
            } else if (this.H.S) {
                mg0 mg0Var3 = g6;
                if (mg0Var3 != null) {
                    mg0Var3.A1();
                    rl0Var2 = mg0Var3.V;
                } else {
                    rl0Var2 = null;
                }
                if (rl0Var2 == null || !"video/dolby-vision".equals(rl0Var2.p)) {
                    string = null;
                } else {
                    string = getString(R.string.stats_dv_refused);
                }
            } else {
                string = getString(R.string.stats_dv_hdr10);
            }
            yd0Var.n(null, string, 4);
            mg0 mg0Var4 = g6;
            mg0Var4.A1();
            k10 k10Var = mg0Var4.g0;
            if (k10Var != null) {
                ArrayDeque arrayDeque = this.R5;
                long jUptimeMillis = SystemClock.uptimeMillis() - 60000;
                while (!arrayDeque.isEmpty() && ((long[]) arrayDeque.peekFirst())[0] < jUptimeMillis) {
                    arrayDeque.pollFirst();
                }
                Iterator it = arrayDeque.iterator();
                long j = 0;
                while (it.hasNext()) {
                    j += ((long[]) it.next())[1];
                }
                int i10 = (int) j;
                int i11 = k10Var.g;
                String string2 = i10 > 0 ? getString(R.string.stats_frames_lost_recent, String.valueOf(i11), String.valueOf(i10)) : getString(R.string.stats_frames_lost, String.valueOf(i11));
                if (i10 > 0) {
                    int color = getColor(R.color.live_red);
                    spannableStringBuilderG = new SpannableStringBuilder(string2);
                    spannableStringBuilderG.setSpan(new ForegroundColorSpan(color), 0, spannableStringBuilderG.length(), 33);
                } else {
                    spannableStringBuilderG = G(string2);
                }
                yd0Var.n(null, spannableStringBuilderG, 0);
            }
        } else {
            f = 0.0f;
        }
        rl0 rl0VarJ0 = J0();
        if (rl0VarJ0 != null) {
            SpannableStringBuilder spannableStringBuilder3 = new SpannableStringBuilder();
            String strE2 = qy.e(rl0VarJ0.p);
            if (strE2 == null) {
                strE2 = qy.e(rl0VarJ0.l);
            }
            int i12 = rl0VarJ0.J;
            String strS = i12 > 0 ? gt2.s(i12) : null;
            if (strE2 == null) {
                strE2 = strS;
            } else if (strS != null) {
                strE2 = we2.f(strE2, " ", strS);
            }
            d(spannableStringBuilder3, strE2);
            qq1 qq1Var = this.D;
            boolean z2 = qq1Var != null && qq1Var.d;
            f(spannableStringBuilder3, getString(z2 ? R.string.stats_audio_passthrough : R.string.stats_audio_decoded));
            yd0Var.n(M3(R.drawable.ic_audiotrack_24dp, iB3), spannableStringBuilder3, 0);
            if (!z2) {
                yd0Var.n(null, j0(this.U), 1);
            }
        }
        if (this.H.v0) {
            staticLayout = null;
        } else {
            SpannableStringBuilder spannableStringBuilder4 = new SpannableStringBuilder(H());
            f(spannableStringBuilder4, w1());
            yd0Var.n(M3(R.drawable.ic_dns_24dp, iB3), G(spannableStringBuilder4), 0);
            staticLayout = null;
            yd0Var.n(null, rl0Var3 == null ? null : G(E(rl0Var3)), 2);
        }
        Iterator it2 = yd0Var.a.iterator();
        while (it2.hasNext()) {
            if (((Object[]) it2.next())[1] != null) {
                int i13 = ((ViewGroup.MarginLayoutParams) ((px) this.r1.getLayoutParams())).leftMargin;
                int width = this.C0.getWidth();
                if (width <= 0) {
                    width = this.V1.a(getResources().getConfiguration().screenWidthDp) / 2;
                } else if (K4()) {
                    i13 *= 2;
                } else {
                    ViewGroup viewGroup = (ViewGroup) findViewById(R.id.exo_center_controls);
                    if (viewGroup != null && viewGroup.getVisibility() == 0 && viewGroup.getWidth() > 0) {
                        int iMin = Integer.MAX_VALUE;
                        for (int i14 = i5; i14 < viewGroup.getChildCount(); i14++) {
                            View childAt = viewGroup.getChildAt(i14);
                            if (childAt.getVisibility() == 0) {
                                iMin = Math.min(iMin, childAt.getLeft());
                            }
                        }
                        if (iMin != Integer.MAX_VALUE && (iB = ((z1(viewGroup)[i5] + iMin) - this.V1.b(16.0f)) - i13) >= this.V1.b(160.0f)) {
                            i = iB;
                        }
                        if (this.r1.getMaxWidth() != i) {
                            this.r1.setMaxWidth(i);
                        }
                        int i15 = ((ViewGroup.MarginLayoutParams) ((px) this.r1.getLayoutParams())).topMargin;
                        textView = this.s1;
                        f2 = 8.0f;
                        if (textView != null || textView.getVisibility() != 0 || this.s1.getHeight() <= 0) {
                            viewFindViewById = findViewById(R.id.exo_bottom_bar);
                            if (viewFindViewById != null || viewFindViewById.getHeight() == 0) {
                                iB2 = i5;
                            } else {
                                top = z1(viewFindViewById)[1];
                            }
                            staticLayoutBuild = staticLayout;
                            r13 = staticLayoutBuild;
                            i2 = i5;
                            while (i2 <= i7) {
                                int iB4 = this.V1.b(f2) + iB3;
                                int iB5 = this.V1.b(6.0f);
                                spannableStringBuilder = new SpannableStringBuilder();
                                drawable = null;
                                for (Object[] objArr : yd0Var.a) {
                                    obj = objArr[i5];
                                    if (obj != null) {
                                        drawable2 = (Drawable) obj;
                                    } else {
                                        drawable2 = drawable;
                                    }
                                    int iIntValue2 = ((Integer) objArr[c]).intValue();
                                    if (objArr[c2] == null && (iIntValue2 <= 0 || iIntValue2 > i2)) {
                                        if (spannableStringBuilder.length() > 0) {
                                            spannableStringBuilder.append('\n');
                                            if (drawable2 != null) {
                                                int length = spannableStringBuilder.length();
                                                spannableStringBuilder.append((CharSequence) " \n");
                                                spannableStringBuilder.setSpan(new AbsoluteSizeSpan(iB5), length, length + 2, 33);
                                            }
                                        }
                                        int length2 = spannableStringBuilder.length();
                                        if (drawable2 != null) {
                                            spannableStringBuilder.append((char) 65532);
                                            spannableStringBuilder.setSpan(new tq1(drawable2), length2, length2 + 1, 33);
                                            spannableStringBuilder.append('\t');
                                            drawable2 = null;
                                        }
                                        spannableStringBuilder.append((CharSequence) objArr[c2]);
                                        char c3 = spannableStringBuilder.charAt(length2) == 65532 ? c2 : (char) 0;
                                        spannableStringBuilder.setSpan(new TabStopSpan.Standard(iB4), length2, spannableStringBuilder.length(), 33);
                                        spannableStringBuilder.setSpan(new LeadingMarginSpan.Standard(c3 != 0 ? 0 : iB4, iB4), length2, spannableStringBuilder.length(), 33);
                                    }
                                    drawable = drawable2;
                                    i5 = 0;
                                    c = 2;
                                    c2 = 1;
                                }
                                staticLayoutBuild = StaticLayout.Builder.obtain(spannableStringBuilder, 0, spannableStringBuilder.length(), this.r1.getPaint(), Math.max(1, (i - this.r1.getPaddingLeft()) - this.r1.getPaddingRight())).setLineSpacing(this.r1.getLineSpacingExtra(), this.r1.getLineSpacingMultiplier()).setIncludePad(this.r1.getIncludeFontPadding()).build();
                                if (iB2 > 0 || this.r1.getPaddingBottom() + this.r1.getPaddingTop() + staticLayoutBuild.getHeight() <= iB2) {
                                    r13 = spannableStringBuilder;
                                    break;
                                }
                                i2++;
                                r13 = spannableStringBuilder;
                                i5 = 0;
                                i7 = 4;
                                c = 2;
                                c2 = 1;
                                f2 = 8.0f;
                            }
                            fMax = f;
                            for (i3 = 0; i3 < staticLayoutBuild.getLineCount(); i3++) {
                                fMax = Math.max(fMax, staticLayoutBuild.getLineRight(i3));
                            }
                            pxVar = (px) this.r1.getLayoutParams();
                            paddingRight = this.r1.getPaddingRight() + this.r1.getPaddingLeft() + ((int) Math.ceil(fMax));
                            if (((ViewGroup.MarginLayoutParams) pxVar).width != paddingRight) {
                                ((ViewGroup.MarginLayoutParams) pxVar).width = paddingRight;
                                this.r1.setLayoutParams(pxVar);
                            }
                            TextView textView3 = this.r1;
                            if (iB2 > 0) {
                                i4 = iB2;
                            } else {
                                i4 = Integer.MAX_VALUE;
                            }
                            textView3.setMaxHeight(i4);
                            this.r1.setText(r13);
                            x0(this.r1, true);
                            return;
                        }
                        top = this.s1.getTop();
                        iB2 = (top - this.V1.b(8.0f)) - i15;
                        staticLayoutBuild = staticLayout;
                        r13 = staticLayoutBuild;
                        i2 = i5;
                        while (i2 <= i7) {
                            int iB6 = this.V1.b(f2) + iB3;
                            int iB7 = this.V1.b(6.0f);
                            spannableStringBuilder = new SpannableStringBuilder();
                            drawable = null;
                            while (r2.hasNext()) {
                                obj = objArr[i5];
                                if (obj != null) {
                                    drawable2 = (Drawable) obj;
                                } else {
                                    drawable2 = drawable;
                                }
                                int iIntValue3 = ((Integer) objArr[c]).intValue();
                                if (objArr[c2] == null) {
                                }
                                drawable = drawable2;
                                i5 = 0;
                                c = 2;
                                c2 = 1;
                            }
                            staticLayoutBuild = StaticLayout.Builder.obtain(spannableStringBuilder, 0, spannableStringBuilder.length(), this.r1.getPaint(), Math.max(1, (i - this.r1.getPaddingLeft()) - this.r1.getPaddingRight())).setLineSpacing(this.r1.getLineSpacingExtra(), this.r1.getLineSpacingMultiplier()).setIncludePad(this.r1.getIncludeFontPadding()).build();
                            if (iB2 > 0) {
                            }
                            r13 = spannableStringBuilder;
                            break;
                        }
                        fMax = f;
                        while (i3 < staticLayoutBuild.getLineCount()) {
                            fMax = Math.max(fMax, staticLayoutBuild.getLineRight(i3));
                        }
                        pxVar = (px) this.r1.getLayoutParams();
                        paddingRight = this.r1.getPaddingRight() + this.r1.getPaddingLeft() + ((int) Math.ceil(fMax));
                        if (((ViewGroup.MarginLayoutParams) pxVar).width != paddingRight) {
                            ((ViewGroup.MarginLayoutParams) pxVar).width = paddingRight;
                            this.r1.setLayoutParams(pxVar);
                        }
                        TextView textView4 = this.r1;
                        if (iB2 > 0) {
                            i4 = iB2;
                        } else {
                            i4 = Integer.MAX_VALUE;
                        }
                        textView4.setMaxHeight(i4);
                        this.r1.setText(r13);
                        x0(this.r1, true);
                        return;
                    }
                    width /= 2;
                }
                i = width - i13;
                if (this.r1.getMaxWidth() != i) {
                    this.r1.setMaxWidth(i);
                }
                int i16 = ((ViewGroup.MarginLayoutParams) ((px) this.r1.getLayoutParams())).topMargin;
                textView = this.s1;
                f2 = 8.0f;
                if (textView != null) {
                    viewFindViewById = findViewById(R.id.exo_bottom_bar);
                    if (viewFindViewById != null) {
                    }
                    iB2 = i5;
                } else {
                    viewFindViewById = findViewById(R.id.exo_bottom_bar);
                    if (viewFindViewById != null) {
                    }
                    iB2 = i5;
                }
                staticLayoutBuild = staticLayout;
                r13 = staticLayoutBuild;
                i2 = i5;
                while (i2 <= i7) {
                    int iB8 = this.V1.b(f2) + iB3;
                    int iB9 = this.V1.b(6.0f);
                    spannableStringBuilder = new SpannableStringBuilder();
                    drawable = null;
                    while (r2.hasNext()) {
                        obj = objArr[i5];
                        if (obj != null) {
                            drawable2 = (Drawable) obj;
                        } else {
                            drawable2 = drawable;
                        }
                        int iIntValue4 = ((Integer) objArr[c]).intValue();
                        if (objArr[c2] == null) {
                        }
                        drawable = drawable2;
                        i5 = 0;
                        c = 2;
                        c2 = 1;
                    }
                    staticLayoutBuild = StaticLayout.Builder.obtain(spannableStringBuilder, 0, spannableStringBuilder.length(), this.r1.getPaint(), Math.max(1, (i - this.r1.getPaddingLeft()) - this.r1.getPaddingRight())).setLineSpacing(this.r1.getLineSpacingExtra(), this.r1.getLineSpacingMultiplier()).setIncludePad(this.r1.getIncludeFontPadding()).build();
                    if (iB2 > 0) {
                    }
                    r13 = spannableStringBuilder;
                    break;
                }
                fMax = f;
                while (i3 < staticLayoutBuild.getLineCount()) {
                    fMax = Math.max(fMax, staticLayoutBuild.getLineRight(i3));
                }
                pxVar = (px) this.r1.getLayoutParams();
                paddingRight = this.r1.getPaddingRight() + this.r1.getPaddingLeft() + ((int) Math.ceil(fMax));
                if (((ViewGroup.MarginLayoutParams) pxVar).width != paddingRight) {
                    ((ViewGroup.MarginLayoutParams) pxVar).width = paddingRight;
                    this.r1.setLayoutParams(pxVar);
                }
                TextView textView5 = this.r1;
                if (iB2 > 0) {
                    i4 = iB2;
                } else {
                    i4 = Integer.MAX_VALUE;
                }
                textView5.setMaxHeight(i4);
                this.r1.setText(r13);
                x0(this.r1, true);
                return;
            }
            i5 = 0;
        }
        x0(this.r1, false);
    }

    public final String C0() {
        Uri uri = this.J4;
        if (uri != null) {
            return ys2.Z(zi0.o(uri));
        }
        if (g6 == null) {
            return null;
        }
        rl0 rl0Var = (rl0) this.u4.a;
        fw0 fw0VarN = g6.E().a.listIterator(0);
        while (fw0VarN.hasNext()) {
            wp2 wp2Var = (wp2) fw0VarN.next();
            if (wp2Var.b.c == 3) {
                for (int i = 0; i < wp2Var.a; i++) {
                    if (wp2Var.e[i] && !wp2Var.a(i).equals(rl0Var)) {
                        return wp2Var.a(i).d;
                    }
                }
            }
        }
        return null;
    }

    public final float C1() {
        Uri uri;
        Long l;
        long duration = g6.getDuration();
        if (duration <= 0 || (uri = this.H.c) == null || (l = (Long) this.y.get(uri.toString())) == null) {
            return 0.0f;
        }
        return l.longValue() / (duration * 125.0f);
    }

    public final void C2() {
        vt1 vt1Var = this.H;
        int iRound = Math.round(I6);
        vt1Var.q = iRound;
        SharedPreferences.Editor editorEdit = vt1Var.b.edit();
        editorEdit.putInt("volumePercent", iRound);
        editorEdit.apply();
        if (g6 != null) {
            vt1 vt1Var2 = this.H;
            int iRound2 = Math.round(this.I.c);
            if (iRound2 >= -1) {
                vt1Var2.p = iRound2;
                SharedPreferences.Editor editorEdit2 = vt1Var2.b.edit();
                editorEdit2.putInt("brightnessPercent", iRound2);
                editorEdit2.apply();
            } else {
                vt1Var2.getClass();
            }
            if (i6) {
                if (g6.x()) {
                    long jO0 = g6.C() == 4 ? 0L : g6.O0();
                    this.H.z(jO0);
                    int iV = g6.V();
                    long[] jArr = this.E3;
                    if (jArr != null && iV >= 0 && iV < jArr.length) {
                        jArr[iV] = jO0;
                    }
                }
                f4();
                if (g6.E().a.isEmpty()) {
                    return;
                }
                this.H.y(K0(1), this.J4 != null ? null : K0(3), this.B.getResizeMode(), this.B.getVideoSurfaceView().getScaleX(), this.Y1, L4());
            }
        }
    }

    public final void C3(vd2 vd2Var) {
        mg0 mg0Var = g6;
        if (mg0Var == null) {
            return;
        }
        if (!vd2Var.j || !mg0Var.e1()) {
            this.z5 = g6.O0();
            this.A5 = vd2Var.a();
            g6.t1(p82.c);
            g6.o1(vd2Var.a());
            return;
        }
        this.z5 = -9223372036854775807L;
        this.A5 = -9223372036854775807L;
        this.B5 = -9223372036854775807L;
        this.C5 = -9223372036854775807L;
        this.F5 = -9223372036854775807L;
        this.X4 = true;
        g6.B();
    }

    public final void C4() {
        if (this.m2 == null || this.F1) {
            return;
        }
        boolean z = a4() != null || I2();
        boolean z2 = this.J4 != null || p1() || I2();
        mg0 mg0Var = g6;
        if (mg0Var != null) {
            fw0 fw0VarN = mg0Var.E().a.listIterator(0);
            while (fw0VarN.hasNext()) {
                dp2 dp2Var = ((wp2) fw0VarN.next()).b;
                if (dp2Var.c == 3 && !h1(dp2Var.d[0])) {
                    z = true;
                    break;
                }
            }
        }
        this.H1.setVisibility(z ? 0 : 8);
        boolean zA2 = A2();
        TextView textView = this.H1;
        String strZ = null;
        if (!zA2) {
            if (z2) {
                boolean z3 = this.J4 != null || p1();
                boolean zI2 = I2();
                if (z3 && zI2) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(f3(C0()));
                    sb.append(" + ");
                    rl0 rl0Var = (rl0) this.u4.a;
                    if (rl0Var != null) {
                        strZ = rl0Var.d;
                    } else {
                        Uri uri = this.t4;
                        if (uri != null) {
                            strZ = ys2.Z(zi0.o(uri));
                        }
                    }
                    sb.append(f3(strZ));
                    strZ = sb.toString();
                } else {
                    if (z3) {
                        strZ = C0();
                    } else {
                        rl0 rl0Var2 = (rl0) this.u4.a;
                        if (rl0Var2 != null) {
                            strZ = rl0Var2.d;
                        } else {
                            Uri uri2 = this.t4;
                            if (uri2 != null) {
                                strZ = ys2.Z(zi0.o(uri2));
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
        this.H1.setSelected(zA2 && z2);
    }

    public final void D() {
        startActivity(new Intent(this, (Class<?>) BrowserActivity.class).addFlags(603979776));
        finish();
    }

    /* JADX WARN: Code duplicated, block: B:12:0x00b7  */
    public final void D0() {
        int iMin;
        int i = this.h1;
        View view = (View) this.S0.getParent();
        if (this.j1 <= 0.0f || view == null || view.getWidth() <= 0 || this.o1.getVisibility() != 0) {
            iMin = -2;
        } else {
            View view2 = (View) this.o1.getParent();
            View view3 = (View) this.u1.getParent();
            int marginEnd = ((ViewGroup.MarginLayoutParams) view.getLayoutParams()).getMarginEnd();
            int iMin2 = Math.min(this.i1, view.getWidth());
            iMin = Math.min(this.i1, ((this.u1.getLeft() + (view3.getLeft() + view2.getLeft())) - marginEnd) - view.getLeft());
            Rect rect = new Rect();
            this.o1.getPaint().getTextBounds("0", 0, 1, rect);
            int baseline = ((((this.o1.getBaseline() + (this.o1.getTop() + view2.getTop())) + rect.top) - this.V1.b(4.0f)) - view.getTop()) - this.S0.getTop();
            if (Math.min(baseline, iMin / this.j1) > Math.min(this.h1, iMin2 / this.j1)) {
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
        this.t4 = uri;
        this.s4 = null;
        vj2 vj2Var = this.r4;
        if (vj2Var != null) {
            vj2Var.g(null);
            this.r4.e(this.q4);
        }
        a82 a82Var = this.p4;
        if (a82Var != null) {
            a82Var.b();
        }
        if (uri == null) {
            return;
        }
        Thread thread = new Thread(new oo1(this, uri, zi0.p(uri), (byte) 0), "SecondarySubtitleTimeline");
        thread.setDaemon(true);
        thread.start();
    }

    public final void D2(Bundle bundle) {
        Bundle bundle2 = new Bundle();
        fp2 fp2Var = this.n3;
        if (fp2Var != null) {
            bundle2.putBundle("stickyAudio", fp2Var.d());
        }
        fp2 fp2Var2 = this.o3;
        if (fp2Var2 != null) {
            bundle2.putBundle("stickySubtitle", fp2Var2.d());
        }
        bundle2.putStringArrayList("audioChosenFor", new ArrayList<>(this.p3));
        bundle2.putStringArrayList("subtitleChosenFor", new ArrayList<>(this.q3));
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
    public final void D3() {
        long j;
        boolean z;
        String str;
        boolean zEquals;
        int i;
        vt1 vt1Var;
        double d;
        mg0 mg0Var = g6;
        if (mg0Var == null || this.b5 == null || !this.H.k0) {
            V0();
            return;
        }
        double d2 = 1000.0d;
        double dO0 = mg0Var.O0() / 1000.0d;
        vd2 vd2VarA = this.b5.a(dO0);
        if (vd2VarA != null) {
            if (vd2VarA.c != 2) {
                boolean z2 = vd2VarA.i;
                String str2 = this.m4;
                if (str2 == null) {
                    vt1 vt1Var2 = this.H;
                    str2 = z2 ? vt1Var2.m0 : vt1Var2.l0;
                }
                if ("off".equals(str2)) {
                    V0();
                    return;
                }
            }
            if (a1(vd2VarA)) {
                long j2 = this.B5;
                if (j2 != -9223372036854775807L) {
                    if (dO0 * 1000.0d >= j2) {
                        this.B5 = -9223372036854775807L;
                    }
                }
                vd2VarA.g = true;
                V0();
                C3(vd2VarA);
                v3(true);
                return;
            }
            boolean z3 = vd2VarA.i;
            String str3 = this.m4;
            if (str3 == null) {
                vt1 vt1Var3 = this.H;
                str3 = z3 ? vt1Var3.m0 : vt1Var3.l0;
            }
            if ("brief".equals(str3)) {
                F(vd2VarA);
                return;
            } else {
                u3(vd2VarA);
                A4(vd2VarA);
                return;
            }
        }
        vd2 vd2Var = null;
        for (vd2 vd2Var2 : this.b5.n) {
            if (!vd2Var2.g) {
                double d3 = vd2Var2.a;
                if (d3 <= dO0 || d3 > dO0 + 3.0d) {
                    d = d2;
                } else {
                    d = d2;
                    if (vd2Var == null || d3 < vd2Var.a) {
                        vd2Var = vd2Var2;
                    }
                }
                d2 = d;
            }
        }
        double d4 = d2;
        if (vd2Var != null) {
            int i2 = vd2Var.c;
            if (i2 == 2) {
                j = this.B5;
                if (j != -9223372036854775807L) {
                    if (dO0 * d4 >= j) {
                        this.B5 = -9223372036854775807L;
                    }
                }
                if (a1(vd2Var)) {
                    if (this.c6 != 3) {
                        V0();
                    }
                    if (this.d5 != null) {
                        return;
                    } else {
                        return;
                    }
                }
                z = vd2Var.i;
                str = this.m4;
                if (str == null) {
                    vt1Var = this.H;
                    if (z) {
                        str = vt1Var.m0;
                    } else {
                        str = vt1Var.l0;
                    }
                }
                zEquals = "brief".equals(str);
                i = this.c6;
                if (zEquals) {
                    if (i == 3) {
                        W0();
                    }
                    u3(vd2Var);
                    A4(vd2Var);
                    return;
                }
                if (i == 3) {
                    W0();
                }
                if (A3()) {
                    return;
                }
                V0();
                return;
            }
            boolean z4 = vd2Var.i;
            String str4 = this.m4;
            if (str4 == null) {
                vt1 vt1Var4 = this.H;
                str4 = z4 ? vt1Var4.m0 : vt1Var4.l0;
            }
            if (!"off".equals(str4)) {
                j = this.B5;
                if (j != -9223372036854775807L) {
                    if (dO0 * d4 >= j) {
                        this.B5 = -9223372036854775807L;
                    }
                }
                if (a1(vd2Var)) {
                    if (this.c6 != 3) {
                        V0();
                    }
                    if (this.d5 != null || i2 == 2) {
                        return;
                    }
                    if (M6 && this.H.n0) {
                        return;
                    }
                    if (this.C5 != vd2Var.a()) {
                        this.C5 = vd2Var.a();
                        this.B.removeCallbacks(this.r5);
                        this.i5 = 1.0f;
                        x3(3, getString(R.string.notification_skipping_stay), true);
                    }
                    X2((Math.round(vd2Var.a * d4) - g6.O0()) / 3000.0d);
                    return;
                }
                z = vd2Var.i;
                str = this.m4;
                if (str == null) {
                    vt1Var = this.H;
                    if (z) {
                        str = vt1Var.m0;
                    } else {
                        str = vt1Var.l0;
                    }
                }
                zEquals = "brief".equals(str);
                i = this.c6;
                if (zEquals) {
                    if (i == 3) {
                        W0();
                    }
                    u3(vd2Var);
                    A4(vd2Var);
                    return;
                }
                if (i == 3) {
                    W0();
                }
                if (A3()) {
                    V0();
                    return;
                }
                return;
            }
        }
        V0();
        if (this.c6 != 3) {
            return;
        }
        W0();
    }

    public final void D4() {
        E4(getResources().getConfiguration().orientation);
    }

    public final String E(rl0 rl0Var) {
        int i = rl0Var.k;
        if (i != -1) {
            return getString(R.string.stats_stream, getString(R.string.quality_bitrate, Float.valueOf(i / 1000000.0f)));
        }
        float fC1 = C1();
        if (fC1 > 0.0f) {
            return getString(R.string.stats_overall, getString(R.string.quality_bitrate, Float.valueOf(fC1)));
        }
        return null;
    }

    public final void E2(boolean z) {
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
        String[] strArr = gt2.a;
        float fMax = Math.max(scaleFit, Math.min(f, 2.0f));
        this.A0 = fMax;
        this.B.setScale(fMax);
        this.B.u(R.drawable.ic_fit_screen_24dp, ((int) (this.A0 * 100.0f)) + "%");
    }

    public final void E3() {
        if (this.Z2 != null) {
            b2(true);
            this.H.x(this, this.Z2, null);
            H2();
            Z0();
        }
    }

    public final void E4(int i) {
        ty tyVar = this.B;
        SubtitleView subtitleView = tyVar == null ? null : tyVar.getSubtitleView();
        if (subtitleView == null) {
            return;
        }
        a82 a82Var = this.p4;
        if (a82Var != null) {
            int i2 = (d1() || !I2() || (L2() && (M6 || !this.p4.t))) ? 1 : 2;
            if (a82Var.s != i2) {
                a82Var.s = i2;
                a82Var.d();
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
            if (this.Y5 == 0.0f) {
                return;
            }
            this.Y5 = 0.0f;
            subtitleView.setTranslationY(0.0f);
            return;
        }
        int height = subtitleView.getHeight();
        if (height <= 0) {
            height = getResources().getDisplayMetrics().heightPixels;
        }
        this.v4 = height;
        float f = height;
        float fZ3 = Z3(i, this.C2) * f;
        float fZ4 = Z3(i, this.D2) * f;
        int iJ2 = (this.p4 == null || !I2() || L2()) ? 0 : J2(fZ4);
        int iRound = Math.round(0.05333333f * f);
        Context context = subtitleView.getContext();
        float fApplyDimension = TypedValue.applyDimension(0, fZ3, (context == null ? Resources.getSystem() : context.getResources()).getDisplayMetrics());
        subtitleView.n = (byte) 2;
        subtitleView.o = fApplyDimension;
        subtitleView.c();
        this.Z5 = fZ3;
        subtitleView.setBottomPaddingFraction(0.05333333f);
        this.a6 = 0.05333333f;
        int iY3 = Y3(i);
        int iY4 = Y3(i);
        subtitleView.setPadding(0, 0, 0, iJ2);
        FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) subtitleView.getLayoutParams();
        layoutParams2.setMargins(iY3, 0, iY4, 0);
        subtitleView.setLayoutParams(layoutParams2);
        int iJ3 = L2() ? J2(fZ3) : 0;
        View viewFindViewById = this.B.findViewById(R.id.subtitle_secondary);
        if (viewFindViewById != null) {
            FrameLayout.LayoutParams layoutParams3 = (FrameLayout.LayoutParams) viewFindViewById.getLayoutParams();
            layoutParams3.gravity = 81;
            layoutParams3.topMargin = 0;
            layoutParams3.bottomMargin = iRound + iJ3;
            viewFindViewById.setLayoutParams(layoutParams3);
        }
        a82 a82Var2 = this.p4;
        if (a82Var2 != null) {
            vt1 vt1Var = this.H;
            int i3 = vt1Var.h0;
            int i4 = vt1Var.i0;
            Typeface typefaceCreate = Typeface.create(Typeface.DEFAULT, vt1Var.b0 ? 1 : 0);
            int iB = this.V1.b(6.0f);
            int iB2 = this.V1.b(8.0f);
            int iB3 = this.V1.b(4.0f);
            a82Var2.A = i3;
            a82Var2.B = fZ4;
            a82Var2.C = typefaceCreate;
            a82Var2.D = iB2;
            a82Var2.E = iB3;
            if (i4 == 0) {
                a82Var2.F = null;
            } else {
                GradientDrawable gradientDrawable = new GradientDrawable();
                gradientDrawable.setCornerRadius(iB);
                gradientDrawable.setColor(i4);
                a82Var2.F = gradientDrawable;
            }
            a82Var2.G = 0;
            a82Var2.d();
        }
        if (this.Y5 != r7) {
            this.Y5 = 0.0f;
            subtitleView.setTranslationY(0.0f);
        }
        if (i == 1 && !L6) {
            View viewFindViewById2 = this.B.findViewById(R.id.exo_content_frame);
            View viewFindViewById3 = findViewById(R.id.exo_bottom_bar);
            if (viewFindViewById2 == null || viewFindViewById3 == null || viewFindViewById2.getHeight() == 0) {
                B3();
            } else {
                int[] iArr = new int[2];
                subtitleView.getLocationInWindow(iArr);
                int i5 = iArr[1];
                viewFindViewById2.getLocationInWindow(iArr);
                int i7 = iArr[1] - i5;
                int height2 = viewFindViewById2.getHeight() + i7;
                int i8 = height - ((ViewGroup.MarginLayoutParams) viewFindViewById3.getLayoutParams()).bottomMargin;
                as2 as2Var = this.V1;
                int iA = i8 - ((as2Var.z() ? as2Var.a(14.0f) : as2Var.b(4.0f)) + (as2Var.g() + ((as2Var.z() ? as2Var.a(12.0f) : as2Var.b(4.0f)) + (as2Var.l() + (as2Var.z() ? as2Var.a(12.0f) : as2Var.b(10.0f))))));
                int iA2 = this.V1.a(50.0f);
                int iA3 = this.V1.a(12.0f) + height2;
                if (iA - iA3 < this.V1.a(100.0f)) {
                    B3();
                } else {
                    subtitleView.setPadding(0, 0, 0, 0);
                    float f2 = (height - (iA3 + iA2)) / f;
                    this.a6 = f2;
                    subtitleView.setBottomPaddingFraction(f2);
                    FrameLayout.LayoutParams layoutParams4 = (FrameLayout.LayoutParams) this.B.findViewById(R.id.subtitle_secondary).getLayoutParams();
                    layoutParams4.bottomMargin = height - ((iA2 * 2) + iA3);
                    this.B.findViewById(R.id.subtitle_secondary).setLayoutParams(layoutParams4);
                    Button button = this.d5;
                    if (button != null) {
                        px pxVar = (px) button.getLayoutParams();
                        ((ViewGroup.MarginLayoutParams) pxVar).bottomMargin = this.V1.a(8.0f) + (height - i7);
                        this.d5.setLayoutParams(pxVar);
                    }
                }
            }
        }
        this.B.post(new to1(this, (byte) 29));
    }

    public final void F(vd2 vd2Var) {
        this.s5 = vd2Var;
        if (A3()) {
            if (!this.u0) {
                return;
            } else {
                this.E5 = 0L;
            }
        }
        if (this.u0) {
            x2();
            return;
        }
        if (vd2Var.a() == this.F5) {
            x2();
            return;
        }
        if (this.d5 == null || this.B == null || this.G) {
            return;
        }
        if (M6 && this.H.n0) {
            return;
        }
        this.F5 = vd2Var.a();
        this.E5 = SystemClock.uptimeMillis() + 5000;
        this.i5 = 1.0f;
        x3(2, getString(R.string.button_skip), true);
        this.B.postDelayed(this.r5, 5000L);
        ValueAnimator valueAnimator = this.j5;
        if (valueAnimator != null) {
            valueAnimator.cancel();
            this.j5 = null;
        }
        ty tyVar = this.B;
        cq1 cq1Var = this.L5;
        tyVar.removeCallbacks(cq1Var);
        this.B.postOnAnimation(cq1Var);
    }

    /* JADX WARN: Code duplicated, block: B:42:0x0077  */
    /* JADX WARN: Code duplicated, block: B:50:0x0099  */
    public final void F1() {
        if (g6 == null) {
            return;
        }
        if (this.p4 != null && L2() && I2() && !M6 && !d1()) {
            a82 a82Var = this.p4;
            mg0 mg0Var = g6;
            long jO0 = mg0Var == null ? 0L : mg0Var.O0();
            e4 e4Var = a82Var.p;
            cj1 cj1Var = a82Var.o;
            TextView textView = a82Var.l;
            boolean z = true;
            boolean z2 = a82Var.q.length() == 0;
            CharSequence charSequence = z2 ? a82Var.r : a82Var.q;
            long j = z2 ? a82Var.x : a82Var.w;
            if (charSequence.length() != 0) {
                if (z2) {
                    long j2 = a82Var.y;
                    if (j2 != -9223372036854775807L && jO0 - j2 <= 2500) {
                        textView.removeCallbacks(cj1Var);
                        textView.removeCallbacks(e4Var);
                        a82Var.t = true;
                        if (!z2 && jO0 - j <= 7000) {
                            z = false;
                        }
                        a82Var.v = z;
                        a82Var.u = charSequence;
                        a82Var.z = j;
                        textView.postDelayed(e4Var, 500L);
                        if (a82Var.v) {
                            textView.postDelayed(cj1Var, 3000L);
                        }
                        a82Var.d();
                        D4();
                    }
                } else if (jO0 - j <= 10000) {
                    textView.removeCallbacks(cj1Var);
                    textView.removeCallbacks(e4Var);
                    a82Var.t = true;
                    if (!z2) {
                        z = false;
                    }
                    a82Var.v = z;
                    a82Var.u = charSequence;
                    a82Var.z = j;
                    textView.postDelayed(e4Var, 500L);
                    if (a82Var.v) {
                        textView.postDelayed(cj1Var, 3000L);
                    }
                    a82Var.d();
                    D4();
                }
            }
        }
        ys2.P(g6);
    }

    public final void F2() {
        mg0 mg0Var;
        ty tyVar = this.B;
        if (tyVar == null) {
            return;
        }
        to1 to1Var = this.U1;
        tyVar.removeCallbacks(to1Var);
        if (D6 && i6 && (mg0Var = g6) != null && mg0Var.C() == 3 && !g6.w() && !M6 && !this.T1 && !this.E2) {
            this.B.postDelayed(to1Var, this.M0 ? 1166L : 3500L);
        }
        this.M0 = false;
    }

    public final boolean F3(boolean z) {
        if ("all".equals(this.H.q0)) {
            return true;
        }
        vt1 vt1Var = this.H;
        return z ? "auto".equals(vt1Var.q0) : "manual".equals(vt1Var.q0);
    }

    public final void F4(PlayerActivity playerActivity) {
        int i;
        int i2;
        gt2.e0(playerActivity);
        SubtitleView subtitleView = this.B.getSubtitleView();
        int i3 = 0;
        boolean z = playerActivity.getResources().getConfiguration().smallestScreenWidthDp >= 720;
        mg0 mg0Var = g6;
        int iMin = mg0Var == null ? 0 : (int) Math.min(10L, mg0Var.O0() / 60000);
        float fB = zi0.B(this.H.c0, L6 || z);
        byte[][] bArr = gt2.i;
        if (bArr != null && bArr.length != 0) {
            int i4 = 0;
            loop0: while (true) {
                byte[] bArr2 = bArr[i4];
                if (bArr2 != null && bArr2.length == 32) {
                    int[] iArr = {i3, 2};
                    int[] iArr2 = {-1625840614, 1072758144};
                    int i5 = i3;
                    while (i5 < 2) {
                        int i7 = i3;
                        int i8 = i7;
                        while (i7 < 4) {
                            i8 = (i8 << 8) | (bArr2[(iArr[i5] * 4) + i7] & 255);
                            i7++;
                        }
                        if ((iArr2[i5] ^ Integer.rotateLeft(263765691, (iArr[i5] * 7) & 31)) != i8) {
                            i = 1;
                            break loop0;
                        } else {
                            i5++;
                            i3 = 0;
                        }
                    }
                    i4++;
                    if (i4 < bArr.length) {
                        i3 = 0;
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
            int i9 = gt2.p;
            if (gt2.q()) {
                i9 |= 1;
            }
            int i10 = i9 | (gt2.t ? 1 : 0);
            if (gt2.d0()) {
                i10 |= 1;
            }
            i2 = i10 | gt2.q;
        } catch (Throwable unused) {
            i2 = 0;
        }
        this.C2 = ((i * i2 * 0.18f * iMin) + 1.0f) * fB;
        this.D2 = zi0.B(this.H.j0, L6 || z);
        if (subtitleView != null) {
            vt1 vt1Var = this.H;
            int i11 = vt1Var.d0;
            subtitleView.setStyle(new co(i11, vt1Var.e0, 0, vt1Var.f0, i11 == -16777216 ? -1 : -16777216, Typeface.create(Typeface.DEFAULT, vt1Var.b0 ? 1 : 0)));
        }
        D4();
    }

    public final SpannableStringBuilder G(CharSequence charSequence) {
        if (charSequence == null) {
            return null;
        }
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(charSequence);
        Matcher matcher = U6.matcher(charSequence);
        while (matcher.find()) {
            spannableStringBuilder.setSpan(new ForegroundColorSpan(this.H0.e), matcher.start(), matcher.end(), 33);
        }
        return spannableStringBuilder;
    }

    public final TextView G2(ContextThemeWrapper contextThemeWrapper, String str) {
        TextView textView = new TextView(contextThemeWrapper);
        textView.setText(str);
        textView.setTextColor(lj.n(contextThemeWrapper, R.attr.colorOnSurfaceVariant, contextThemeWrapper.getColor(R.color.ink_secondary)));
        textView.setTextSize(2, this.V1.s());
        textView.setMinHeight(this.V1.b(56.0f));
        textView.setGravity(16);
        textView.setPadding(gt2.p(12), 0, gt2.p(12), 0);
        return textView;
    }

    public final long G3() {
        long j = this.S5;
        if (j == 0) {
            return 0L;
        }
        return Math.max(0L, j - SystemClock.uptimeMillis());
    }

    public final void G4() {
        ty tyVar = this.B;
        SubtitleView subtitleView = tyVar == null ? null : tyVar.getSubtitleView();
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
        int iRound = (iArr[1] + height) - Math.round(this.a6 * height);
        float f = 1.0f;
        subtitleView.setAlpha((!z || ((float) (iRound - i)) <= this.Z5 / 2.0f) ? 1.0f : 0.0f);
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

    /* JADX WARN: Failed to calculate best type for var: r4v1 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r4v1 ??, new type: float
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.calculateFromBounds(FixTypesVisitor.java:159)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.setBestType(FixTypesVisitor.java:136)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.deduceType(FixTypesVisitor.java:241)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryDeduceTypes(FixTypesVisitor.java:224)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
    	... 6 more
     */
    /* JADX WARN: Failed to calculate best type for var: r4v1 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r4v1 ??, new type: float
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
    	... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r4v2 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r4v2 ??, new type: float
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
    	... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r4v5 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r4v5 ??, new type: float
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
    	... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r5v10 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r5v10 ??, new type: float
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
    	... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r5v11 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r5v11 ??, new type: float
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
    	... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r5v12 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r5v12 ??, new type: float
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
    	... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r5v13 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r5v13 ??, new type: float
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
    	... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r5v17 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r5v17 ??, new type: float
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
    	... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r5v18 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r5v18 ??, new type: float
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
    	... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r5v9 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r5v9 ??, new type: float
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.calculateFromBounds(FixTypesVisitor.java:159)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.setBestType(FixTypesVisitor.java:136)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.deduceType(FixTypesVisitor.java:241)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryDeduceTypes(FixTypesVisitor.java:224)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
    	... 6 more
     */
    /* JADX WARN: Failed to calculate best type for var: r5v9 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r5v9 ??, new type: float
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
    	... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r6v2 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r6v2 ??, new type: float
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
    	... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r6v4 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r6v4 ??, new type: float
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
    	... 7 more
     */
    /*  JADX ERROR: Types fix failed
        jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r5v9 ??, new type: float
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryPossibleTypes(FixTypesVisitor.java:186)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.deduceType(FixTypesVisitor.java:245)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryDeduceTypes(FixTypesVisitor.java:224)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
        Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        	... 5 more
        */
    public final java.lang.String H() {
        /*
            Method dump skipped, instruction units count: 297
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.brouken.player.PlayerActivity.H():java.lang.String");
    }

    public final void H0() {
        lq1 lq1Var;
        this.N5 = false;
        this.B.removeCallbacks(this.X5);
        DisplayManager displayManager = this.R4;
        if (displayManager != null && (lq1Var = this.S4) != null) {
            displayManager.unregisterDisplayListener(lq1Var);
        }
        if (this.B2) {
            this.B2 = false;
            L1();
        }
    }

    public final View[] H1() {
        return K4() ? new View[]{this.w1, this.G1} : new View[]{this.W1, this.X1, this.w1};
    }

    public final void H2() {
        b90 b90VarA;
        File file;
        String path;
        Uri uri = this.H.c;
        if (uri == null) {
            return;
        }
        if (gt2.E(uri) && (path = this.H.c.getPath()) != null) {
            String lowerCase = path.toLowerCase();
            String[] strArr = gt2.a;
            for (int i = 0; i < 8; i++) {
                if (lowerCase.endsWith(strArr[i])) {
                    this.T4 = this.H.c;
                    return;
                }
            }
        }
        vt1 vt1Var = this.H;
        if (vt1Var.g != null || L6) {
            String scheme = vt1Var.c.getScheme();
            vt1 vt1Var2 = this.H;
            b90 b90VarI = null;
            if (vt1Var2.g != null) {
                if ("com.android.externalstorage.documents".equals(vt1Var2.c.getHost()) || "org.courville.nova.provider".equals(this.H.c.getHost())) {
                    vt1 vt1Var3 = this.H;
                    b90VarA = zi0.j(this, vt1Var3.g, vt1Var3.c);
                } else {
                    bd2 bd2VarB = b90.b(this, this.H.g);
                    Uri uri2 = this.H.c;
                    bd2 bd2Var = new bd2(b90VarI);
                    bd2Var.c = this;
                    bd2Var.d = uri2;
                    b90VarA = zi0.h(bd2VarB, bd2Var);
                }
                file = null;
            } else if ("file".equals(scheme)) {
                File file2 = new File(this.H.c.getSchemeSpecificPart());
                file = file2;
                b90VarA = b90.a(file2);
            } else {
                b90VarA = null;
                file = null;
            }
            if (b90VarA != null) {
                if (this.H.g != null) {
                    b90VarI = zi0.i(b90VarA, b90VarA.a);
                } else if ("file".equals(scheme)) {
                    b90VarI = zi0.i(b90VarA, b90.a(file.getParentFile()));
                }
                if (b90VarI != null) {
                    P0(b90VarI.e());
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
    
        return getString(com.justplus.player.R.string.error_software_video_too_slow, p2(r2, r1), e3(r3));
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.String H3(defpackage.rl0 r10) {
        /*
            r9 = this;
            r0 = 0
            if (r10 == 0) goto L77
            int r1 = r10.x
            int r2 = r10.w
            java.lang.String r3 = r10.p
            boolean r4 = defpackage.ue1.o(r3)
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
            java.util.List r10 = defpackage.i71.e(r3, r6, r6)     // Catch: java.lang.Throwable -> L55
            java.util.Iterator r10 = r10.iterator()     // Catch: java.lang.Throwable -> L55
        L3c:
            boolean r4 = r10.hasNext()     // Catch: java.lang.Throwable -> L55
            if (r4 == 0) goto L55
            java.lang.Object r4 = r10.next()     // Catch: java.lang.Throwable -> L55
            y61 r4 = (defpackage.y61) r4     // Catch: java.lang.Throwable -> L55
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
            java.lang.String r0 = e3(r3)
            r1 = 2
            java.lang.Object[] r1 = new java.lang.Object[r1]
            r1[r6] = r10
            r1[r5] = r0
            r10 = 2131951788(0x7f1300ac, float:1.954E38)
            java.lang.String r9 = r9.getString(r10, r1)
            return r9
        L77:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.brouken.player.PlayerActivity.H3(rl0):java.lang.String");
    }

    public final void H4() {
        mg0 mg0Var = g6;
        if (mg0Var == null) {
            return;
        }
        p81 p81VarZ = mg0Var.z();
        Uri uri = null;
        x81 x81Var = p81VarZ != null ? p81VarZ.d : null;
        CharSequence charSequenceW = x81Var != null ? x81Var.a : null;
        if (charSequenceW == null || charSequenceW.length() == 0) {
            charSequenceW = gt2.w(this, this.H.c);
        }
        this.R0.setText(charSequenceW);
        p4();
        Uri uri2 = x81Var != null ? x81Var.n : null;
        if (uri2 == null) {
            uri2 = this.b6;
        }
        int iV = g6.V();
        Uri uri3 = (!"logo".equals(this.H.w0) || iV >= this.M3.size()) ? null : (Uri) this.M3.get(iV);
        this.j1 = 0.0f;
        D0();
        if (uri3 == null) {
            com.bumptech.glide.a.d(getApplicationContext()).m(this.S0);
            this.S0.setVisibility(8);
            this.R0.setVisibility(0);
        } else {
            this.S0.setVisibility(0);
            this.R0.setVisibility(8);
            g02 g02VarD = com.bumptech.glide.a.d(getApplicationContext());
            g02VarD.getClass();
            new zz1(g02VarD.l, g02VarD, Drawable.class, g02VarD.m).z(uri3).y(new eq1(this)).x(this.S0);
        }
        if ("poster".equals(this.H.w0)) {
            Uri uriF0 = f0();
            boolean z = g6.a1() > 1;
            String strValueOf = String.valueOf(iV + 1);
            this.Q0.setText(strValueOf);
            this.P0.setText(strValueOf);
            if (uri2 != null) {
                uri = uri2;
            } else {
                String[] strArr = gt2.a;
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
                g02 g02VarD2 = com.bumptech.glide.a.d(getApplicationContext());
                g02VarD2.getClass();
                zz1 zz1VarZ = new zz1(g02VarD2.l, g02VarD2, Bitmap.class, g02VarD2.m).a(g02.v).z(uri);
                if (uri2 == null) {
                    zz1VarZ.getClass();
                    zz1VarZ = (zz1) zz1VarZ.l(hu2.d, 1000000L);
                }
                zz1VarZ.y(new dq1(this, z)).x(this.O0);
            } else {
                q3(z);
            }
        } else {
            com.bumptech.glide.a.d(getApplicationContext()).m(this.O0);
            this.N0.setVisibility(8);
        }
        boolean z2 = g6.a1() > 1;
        ImageButton imageButton = this.v1;
        if (imageButton != null) {
            imageButton.setVisibility(z2 ? 0 : 8);
        }
        y4();
        n4();
        this.B.setShowNextButton(z2);
        this.B.setShowPreviousButton(z2);
        this.F0 = true;
        r4();
        v4();
        o4();
    }

    public final ArrayList I() {
        ArrayList arrayList = new ArrayList();
        mg0 mg0Var = g6;
        if (mg0Var != null) {
            fw0 fw0VarN = mg0Var.E().a.listIterator(0);
            int i = 0;
            while (fw0VarN.hasNext()) {
                wp2 wp2Var = (wp2) fw0VarN.next();
                dp2 dp2Var = wp2Var.b;
                if (dp2Var.c == 1) {
                    for (int i2 = 0; i2 < wp2Var.a; i2++) {
                        rl0 rl0Var = dp2Var.d[i2];
                        i++;
                        String[] strArrK4 = k4(rl0Var, i);
                        String str = strArrK4[0];
                        String str2 = strArrK4[1];
                        boolean z = wp2Var.e[i2];
                        String str3 = rl0Var.d;
                        String[] strArr = gt2.a;
                        fp2 fp2Var = fp2.f;
                        arrayList.add(new pq1(str, str2, dp2Var, i2, z, x91.D(str3), wp2Var.c(i2, true)));
                    }
                }
            }
            HashSet hashSet = new HashSet();
            Iterator it = arrayList.iterator();
            boolean z2 = false;
            while (it.hasNext()) {
                z2 |= !hashSet.add(((pq1) it.next()).a);
            }
            int i3 = 0;
            while (z2 && i3 < arrayList.size()) {
                pq1 pq1Var = (pq1) arrayList.get(i3);
                int i4 = i3 + 1;
                String string = getString(R.string.audio_track_number, Integer.valueOf(i4));
                String str4 = pq1Var.a;
                String str5 = pq1Var.b;
                if (str5 != null) {
                    string = string + " · " + str5;
                }
                arrayList.set(i3, new pq1(str4, string, pq1Var.c, pq1Var.d, pq1Var.e, pq1Var.f, pq1Var.g));
                i3 = i4;
            }
        }
        return arrayList;
    }

    public final List I0() {
        if (this.Z1 == null) {
            ArrayList arrayList = new ArrayList();
            this.Z1 = arrayList;
            arrayList.add(new oq1(0, 0.0f, getString(R.string.video_resize_fit)));
            this.Z1.add(new oq1(4, 0.0f, getString(R.string.video_resize_crop)));
            this.Z1.add(new oq1(3, 0.0f, getString(R.string.video_resize_fill)));
            this.Z1.add(new oq1(0, 1.7777778f, "16:9"));
            this.Z1.add(new oq1(0, 1.3333334f, "4:3"));
            this.Z1.add(new oq1(0, 1.6f, "16:10"));
            this.Z1.add(new oq1(0, 2.0f, "2:1"));
            this.Z1.add(new oq1(0, 2.35f, "2.35:1"));
            this.Z1.add(new oq1(0, 2.39f, "2.39:1"));
            this.Z1.add(new oq1(0, 1.25f, "5:4"));
        }
        return this.Z1;
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
        textView.setTextSize(2, this.V1.r());
        textView.setTypeface(Typeface.create("sans-serif-medium", 0));
        textView.setVisibility(8);
        as2 as2Var = this.V1;
        int iB2 = as2Var.z() ? 0 : as2Var.b(6.0f);
        textView.setBackground(new InsetDrawable((Drawable) gt2.b0(this.H0.c, 10000.0f), iB2));
        textView.setForeground(gt2.j(this, iB2, iB2, this.H0.j));
        boolean z = L6;
        as2 as2Var2 = this.V1;
        int iA2 = (z ? as2Var2.a(18.0f) : as2Var2.b(16.0f)) + iB2;
        if (i != 0) {
            Drawable drawable = getDrawable(i);
            int i2 = this.V1.i();
            if (drawable != null) {
                drawable.setBounds(0, 0, i2, i2);
                textView.setCompoundDrawablesRelative(drawable, null, null, null);
                textView.setCompoundDrawableTintList(ColorStateList.valueOf(this.H0.e));
            }
            textView.setCompoundDrawablePadding(this.V1.b(8.0f));
            iB = this.V1.b(8.0f) + i2;
            boolean z2 = L6;
            as2 as2Var3 = this.V1;
            iA = iB2 + (z2 ? as2Var3.a(14.0f) : as2Var3.b(12.0f));
        } else {
            iB = 0;
            iA = iA2;
        }
        textView.setPadding(iA, 0, iA2, 0);
        textView.setMaxWidth(this.V1.a(160.0f) + iA + iB + iA2);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, this.V1.g());
        layoutParams.gravity = 16;
        textView.setLayoutParams(layoutParams);
        return textView;
    }

    public final boolean I2() {
        if (K2()) {
            return (this.t4 == null && ((rl0) this.u4.a) == null) ? false : true;
        }
        return false;
    }

    public final p81 I3(int i, Uri uri) {
        c81 c81VarA = ((p81) this.A3.get(i)).a();
        c81VarA.b = uri;
        ks1 ks1Var = this.k3;
        gs1 gs1Var = (ks1Var == null || i >= ks1Var.f.size()) ? null : (gs1) this.k3.f.get(i);
        int iA = gs1Var == null ? -1 : gs1Var.a(uri.toString());
        if (iA >= 0) {
            List list = ((js1) gs1Var.t.get(iA)).d;
            if (list == null) {
                list = gs1Var.v;
            }
            c81VarA.h = hw0.l(U3(list));
        }
        return c81VarA.a();
    }

    public final void I4() {
        String strE;
        if (this.s1 == null) {
            return;
        }
        if (this.H.v0 && g6 != null && C6 && !this.G) {
            ty tyVar = this.B;
            View viewFindViewById = tyVar == null ? null : tyVar.findViewById(R.id.subtitle_secondary);
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
                mg0 mg0Var = g6;
                mg0Var.A1();
                rl0 rl0Var = mg0Var.V;
                if (rl0Var != null && (strE = E(rl0Var)) != null) {
                    arrayList.add(strE.replace(' ', (char) 160));
                }
                this.s1.setText(G(TextUtils.join(getResources().getConfiguration().orientation == 1 ? "\n" : " · ", arrayList)));
                px pxVar = (px) this.s1.getLayoutParams();
                int i = this.L0;
                if (((ViewGroup.MarginLayoutParams) pxVar).bottomMargin != i) {
                    ((ViewGroup.MarginLayoutParams) pxVar).bottomMargin = i;
                    this.s1.setLayoutParams(pxVar);
                }
                int width = (this.C0.getWidth() - ((ViewGroup.MarginLayoutParams) pxVar).leftMargin) - ((ViewGroup.MarginLayoutParams) pxVar).rightMargin;
                Button button = this.d5;
                if (button != null && button.getVisibility() == 0) {
                    width = (width - this.d5.getWidth()) - this.V1.b(8.0f);
                }
                if (width > 0) {
                    this.s1.setMaxWidth(width);
                }
                x0(this.s1, true);
                return;
            }
        }
        x0(this.s1, false);
    }

    public final ArrayList J() {
        yq1 yq1Var;
        ArrayList arrayList = new ArrayList();
        if (g6 != null) {
            HashMap map = new HashMap();
            fw0 fw0VarN = g6.E().a.listIterator(0);
            while (fw0VarN.hasNext()) {
                wp2 wp2Var = (wp2) fw0VarN.next();
                if (wp2Var.b.c == 2) {
                    for (int i = 0; i < wp2Var.a; i++) {
                        if (wp2Var.c(i, false)) {
                            rl0 rl0VarA = wp2Var.a(i);
                            int i2 = rl0VarA.w;
                            int i3 = rl0VarA.k;
                            int i4 = rl0VarA.x;
                            int iMax = Math.max(i2, i4);
                            int iMin = Math.min(i2, i4);
                            if (iMax > 0 && ((yq1Var = (yq1) map.get(Integer.valueOf(iMax))) == null || i3 > yq1Var.g)) {
                                String strE3 = e3(rl0VarA.p);
                                String strValueOf = iMin > 0 ? iMax + " × " + iMin : String.valueOf(iMax);
                                if (strE3 != null) {
                                    strValueOf = strValueOf + "  •  " + strE3;
                                }
                                String string = i3 > 0 ? getString(R.string.quality_bitrate, Float.valueOf(i3 / 1000000.0f)) : "";
                                String strP2 = p2(i2, i4);
                                Integer numValueOf = Integer.valueOf(iMax);
                                if (strP2 == null) {
                                    strP2 = iMax + "p";
                                }
                                map.put(numValueOf, new yq1(strP2, strValueOf, string, 2, wp2Var.b, i, rl0VarA.k, null));
                            }
                        }
                    }
                }
            }
            if (map.size() >= 2) {
                arrayList.add(new yq1("", "", "", 0, null, -1, -1, null));
                arrayList.add(new yq1("", "", "", 1, null, -1, -1, null));
                ArrayList arrayList2 = new ArrayList(map.keySet());
                Collections.sort(arrayList2, Collections.reverseOrder());
                Iterator it = arrayList2.iterator();
                while (it.hasNext()) {
                    arrayList.add((yq1) map.get((Integer) it.next()));
                }
            }
            LinkedHashMap linkedHashMapH0 = h0();
            if (linkedHashMapH0 != null) {
                for (Map.Entry entry : linkedHashMapH0.entrySet()) {
                    if (entry.getValue() != null && !((String) entry.getValue()).trim().isEmpty()) {
                        arrayList.add(new yq1((String) entry.getKey(), "", "", 3, null, -1, -1, (String) entry.getValue()));
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
        if (view == this.w1) {
            return this.x1;
        }
        if (view == this.G1) {
            return this.y1;
        }
        return (L6 || K4()) ? false : true;
    }

    public final int J2(float f) {
        return this.V1.b(12.0f) + (this.V1.b(4.0f) * 2) + Math.round(f * 2.0f * 1.3f);
    }

    public final void J4() {
        int resizeMode = this.B.getResizeMode();
        ImageButton imageButton = this.X1;
        if (resizeMode == 4) {
            imageButton.setImageResource(R.drawable.ic_fit_screen_24dp);
        } else {
            imageButton.setImageResource(R.drawable.ic_aspect_ratio_24dp);
        }
    }

    public final int K1(View view) {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) view.getLayoutParams();
        int i = layoutParams.width;
        view.measure(i >= 0 ? View.MeasureSpec.makeMeasureSpec(i, 1073741824) : View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(this.V1.g(), 1073741824));
        return layoutParams.getMarginEnd() + layoutParams.getMarginStart() + view.getMeasuredWidth();
    }

    public final boolean K2() {
        vt1 vt1Var = this.H;
        return (vt1Var == null || "off".equals(vt1Var.g0)) ? false : true;
    }

    public final boolean K3() {
        if (g6 != null) {
            return this.c0 == -9223372036854775807L || e0() - this.c0 < 2000;
        }
        return false;
    }

    public final boolean K4() {
        return !L6 && getResources().getConfiguration().orientation == 1;
    }

    public final void L() {
        this.B.removeCallbacks(this.N2);
        this.H2 = -1L;
        this.I2 = -1L;
        this.K2 = 0;
    }

    public final void L1() {
        zn2 zn2Var = this.V4;
        if (zn2Var == null || !zn2Var.s) {
            if (this.V0 > 0) {
                this.W0 = true;
                return;
            }
            mg0 mg0Var = g6;
            if (mg0Var != null) {
                mg0Var.k(true);
            }
            ty tyVar = this.B;
            if (tyVar != null) {
                tyVar.c();
            }
        }
    }

    public final boolean L2() {
        vt1 vt1Var = this.H;
        return vt1Var != null && "demand".equals(vt1Var.g0);
    }

    public final float L4() {
        zn2 zn2Var = this.V4;
        return (zn2Var == null || !zn2Var.j()) ? this.H.m : this.V4.n.r;
    }

    public final void M() {
        ty tyVar = this.B;
        if (tyVar != null) {
            tyVar.removeCallbacks(this.P);
        }
    }

    public final fp2 M1(int i) {
        mg0 mg0Var = g6;
        if (mg0Var != null) {
            if (i == 3 && this.J4 != null) {
                return new fp2(null, V3(this.J4), null, null, null);
            }
            fw0 fw0VarN = mg0Var.E().a.listIterator(0);
            rl0 rl0Var = null;
            while (fw0VarN.hasNext()) {
                wp2 wp2Var = (wp2) fw0VarN.next();
                if (wp2Var.b.c == i) {
                    for (int i2 = 0; i2 < wp2Var.a && rl0Var == null; i2++) {
                        rl0 rl0VarA = wp2Var.a(i2);
                        if (wp2Var.e[i2] && (i != 3 || (!h1(rl0VarA) && !rl0VarA.equals((rl0) this.u4.a)))) {
                            rl0Var = rl0VarA;
                        }
                    }
                }
            }
            ArrayList arrayListG0 = G0(i);
            if (rl0Var != null) {
                return h4(rl0Var, arrayListG0, false);
            }
            if (i == 3) {
                return fp2.f;
            }
        }
        return null;
    }

    public final Drawable M3(int i, int i2) {
        Drawable drawableMutate = getDrawable(i).mutate();
        drawableMutate.setBounds(0, 0, i2, i2);
        drawableMutate.setTint(this.H0.g);
        return drawableMutate;
    }

    public final void N() {
        this.u5++;
        Thread thread = this.b3;
        if (thread != null) {
            thread.interrupt();
            this.b3 = null;
        }
    }

    public final void N0() {
        this.C = true;
        finish();
        b2(true);
    }

    public final FrameLayout N1(ContextThemeWrapper contextThemeWrapper, int i, Uri uri, Uri uri2, boolean z) {
        int iP = gt2.p(8);
        int iB = this.V1.b(24.0f);
        FrameLayout frameLayoutS = gt2.S(contextThemeWrapper, iP, iB);
        gt2.e(frameLayoutS, uri, uri2, R.drawable.ic_movie_24dp, iB);
        String strValueOf = String.valueOf(i + 1);
        TextView textView = new TextView(contextThemeWrapper);
        textView.setText(strValueOf);
        textView.setTextSize(2, this.V1.q(11.0f, 11.0f, 12.0f, 13.0f));
        textView.setTypeface(Typeface.create("sans-serif-medium", 0));
        textView.setTextColor(-1);
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setCornerRadius(gt2.p(6));
        gradientDrawable.setColor(contextThemeWrapper.getColor(R.color.badge_scrim));
        textView.setBackground(gradientDrawable);
        textView.setGravity(17);
        textView.setIncludeFontPadding(false);
        textView.setPadding(gt2.p(8), 0, gt2.p(8), 0);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, this.V1.b(20.0f));
        layoutParams.setMarginEnd(gt2.p(4));
        textView.setLayoutParams(layoutParams);
        if (z) {
            textView.setTextColor(lj.n(contextThemeWrapper, R.attr.colorOnSecondaryContainer, contextThemeWrapper.getColor(R.color.brand_accent_on)));
            GradientDrawable gradientDrawable2 = new GradientDrawable();
            gradientDrawable2.setCornerRadius(gt2.p(6));
            gradientDrawable2.setColor(lj.n(contextThemeWrapper, R.attr.colorSecondaryContainer, contextThemeWrapper.getColor(R.color.brand_accent)));
            textView.setBackground(gradientDrawable2);
            textView.setContentDescription(getString(R.string.playlist_playing));
        }
        FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(-2, this.V1.b(20.0f));
        layoutParams2.gravity = 8388659;
        layoutParams2.setMargins(gt2.p(6), gt2.p(6), 0, 0);
        frameLayoutS.addView(textView, layoutParams2);
        return frameLayoutS;
    }

    public final float N4() {
        rl0 rl0Var;
        mg0 mg0Var = g6;
        if (mg0Var != null) {
            mg0Var.A1();
            rl0Var = mg0Var.V;
        } else {
            rl0Var = null;
        }
        if (rl0Var != null) {
            float f = rl0Var.B;
            if (f != -1.0f) {
                return f;
            }
        }
        for (ip2 ip2Var : b0()) {
            if (ip2Var.c == 1) {
                float f2 = ip2Var.d;
                if (f2 > 0.0f) {
                    return f2;
                }
            }
        }
        return 0.0f;
    }

    public final void O() {
        this.B.removeCallbacks(this.W5);
        this.S5 = 0L;
        this.T5 = 0;
        this.V5 = false;
        if (this.U5) {
            this.U5 = false;
            t();
        }
        mg0 mg0Var = g6;
        if (mg0Var != null) {
            mg0Var.f(H6 ? 1.0f : Math.min(I6, 100.0f) / 100.0f);
        }
    }

    public final boolean O0(Intent intent) {
        String queryParameter;
        String str;
        Uri data = intent.getData();
        String str2 = s12.d;
        r12 r12Var = null;
        if (data != null && (queryParameter = data.getQueryParameter("room")) != null && !queryParameter.trim().isEmpty()) {
            String strTrim = queryParameter.trim();
            if (s12.a(strTrim)) {
                r12Var = new r12(strTrim, "", (byte) 0);
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
                    if (s12.a(strTrim2)) {
                        r12Var = new r12(strTrim2, iIndexOf != -1 ? str.substring(iIndexOf + 1) : "", (byte) 0);
                    }
                }
            }
        }
        if (r12Var == null) {
            return false;
        }
        j1(r12Var.b, r12Var.c);
        return true;
    }

    public final Uri O1(int i) {
        k81 k81Var;
        if (i < 0) {
            return null;
        }
        ArrayList arrayList = this.A3;
        if (i < arrayList.size() && (k81Var = ((p81) arrayList.get(i)).b) != null) {
            return k81Var.a;
        }
        return null;
    }

    public final boolean O2(long j) {
        if (!this.X2 || g6 == null) {
            return false;
        }
        this.X2 = false;
        M2(j);
        return true;
    }

    public final void O3() {
        ty tyVar = this.B;
        if (tyVar != null) {
            tyVar.removeCallbacks(this.I5);
        }
        TextView textView = this.o1;
        if (textView != null) {
            textView.setVisibility(8);
        }
        TextView textView2 = this.r1;
        if (textView2 != null) {
            textView2.setVisibility(8);
        }
        TextView textView3 = this.s1;
        if (textView3 != null) {
            textView3.setVisibility(8);
        }
    }

    public final void P() {
        this.y5++;
        this.w5 = null;
        if (this.x5 != null) {
            W3(0);
            this.x5.interrupt();
            this.x5 = null;
        }
    }

    public final void P0(Uri uri) {
        zi0.e(this);
        String[] strArr = gt2.a;
        try {
            String scheme = uri.getScheme();
            if (scheme == null || !scheme.toLowerCase().startsWith("http")) {
                uri = gt2.l(this, uri, getContentResolver().openInputStream(uri), null);
            } else {
                ArrayList arrayList = new ArrayList();
                arrayList.add(uri);
                Thread thread = new Thread(new cj1((Object) new sj2(this, arrayList), (byte) 15), "SubtitleFetcher");
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

    /* JADX WARN: Code duplicated, block: B:51:0x00da  */
    public final boolean P2(boolean z, boolean z2) {
        long j;
        if (g6 == null) {
            return false;
        }
        long jUptimeMillis = SystemClock.uptimeMillis();
        if (z2) {
            if (jUptimeMillis - this.M2 < (this.X2 ? 200L : 600L)) {
                return true;
            }
        }
        ty tyVar = this.B;
        tyVar.removeCallbacks(tyVar.C0);
        this.B.z();
        long jO0 = g6.O0();
        ty tyVar2 = this.B;
        if (tyVar2.n0 == -1) {
            tyVar2.n0 = jO0;
        }
        long duration = g6.getDuration();
        if (duration <= 0) {
            L();
            long jMax = Math.max(0L, jO0 + ((long) (z ? 3000 : -3000)));
            g6.t1(p82.c);
            M2(jMax);
            this.M2 = jUptimeMillis;
            l3(jMax);
            return true;
        }
        this.K2 = (z != this.L2 || (!z2 && jUptimeMillis - this.M2 >= 450)) ? 0 : this.K2 + 1;
        this.L2 = z;
        this.M2 = jUptimeMillis;
        long j2 = this.H2;
        if (j2 < 0) {
            this.J2 = jO0;
            j2 = jO0;
        }
        long jMin = 3000;
        long jMin2 = Math.min(60000L, Math.max(3000L, duration / 10));
        int i = this.K2;
        if (i >= 2) {
            if (i < 4) {
                jMin = Math.min(10000L, jMin2);
            } else {
                jMin = i < 8 ? Math.min(30000L, jMin2) : jMin2;
            }
        }
        long jMax2 = Math.max(0L, Math.min(duration, j2 + (z ? jMin : -jMin)));
        this.H2 = jMax2;
        if (g6 != null) {
            Uri uriD0 = d0();
            o82 o82Var = uriD0 == null ? null : (o82) Q6.get(uriD0.toString());
            if (o82Var == null || !o82Var.d()) {
                j = -9223372036854775807L;
            } else {
                n82 n82VarE = o82Var.e(Math.max(0L, jMax2) * 1000);
                long j3 = n82VarE.a.a / 1000;
                long j4 = n82VarE.b.a / 1000;
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
            this.H2 = j;
        }
        l3(this.H2);
        g6.t1(p82.c);
        if (O2(this.H2)) {
            this.I2 = this.H2;
        }
        ty tyVar3 = this.B;
        np1 np1Var = this.N2;
        tyVar3.removeCallbacks(np1Var);
        this.B.postDelayed(np1Var, 700L);
        return true;
    }

    public final void P3(String str, String str2) {
        b7 b7Var = this.l3;
        if (b7Var != null) {
            b7Var.e = str;
        }
        y3(str, str2);
        b2(false);
        this.B.setPlayer(null);
        this.B.setControllerShowTimeoutMs(-1);
        this.B.j();
    }

    public final void P4(boolean z) {
        mg0 mg0Var;
        if (this.l3 == null || (mg0Var = g6) == null) {
            return;
        }
        int iV = mg0Var.V();
        b7 b7Var = this.l3;
        if (z) {
            if (b7Var.i(iV)) {
                ((String[]) b7Var.h)[iV] = "viewer";
            }
        } else if (b7Var.i(iV)) {
            ((String[]) b7Var.i)[iV] = "viewer";
        }
    }

    public final String Q(rl0 rl0Var, ArrayList arrayList) {
        String str = rl0Var.d;
        String strJ4 = rl0Var.b;
        String[] strArr = gt2.a;
        fp2 fp2Var = fp2.f;
        String strD = x91.D(str);
        if (strD != null) {
            return strD;
        }
        if (strJ4 == null || !strJ4.matches("[a-z]{3}\\d{1,2}")) {
            strJ4 = j4(rl0Var);
        }
        String strI = gt2.I(strJ4, arrayList);
        if (strI != null) {
            return strI;
        }
        hp2 hp2VarI4 = i4();
        String strI2 = hp2VarI4.i(j4(rl0Var));
        if (strI2 == null) {
            return null;
        }
        return (String) hp2VarI4.b.get(strI2);
    }

    /* JADX WARN: Code duplicated, block: B:224:0x0572  */
    /* JADX WARN: Code duplicated, block: B:232:0x058b  */
    /* JADX WARN: Code duplicated, block: B:235:0x05a6 A[LOOP:10: B:230:0x0588->B:235:0x05a6, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:238:0x05b7  */
    /* JADX WARN: Code duplicated, block: B:252:0x05e3  */
    /* JADX WARN: Code duplicated, block: B:255:0x05e9  */
    /* JADX WARN: Code duplicated, block: B:256:0x05f0  */
    /* JADX WARN: Code duplicated, block: B:259:0x05f9  */
    /* JADX WARN: Code duplicated, block: B:260:0x05fb  */
    /* JADX WARN: Code duplicated, block: B:263:0x060d  */
    /* JADX WARN: Code duplicated, block: B:272:0x0641  */
    /* JADX WARN: Code duplicated, block: B:274:0x0644  */
    /* JADX WARN: Code duplicated, block: B:285:0x066b  */
    /* JADX WARN: Code duplicated, block: B:287:0x066e  */
    /* JADX WARN: Code duplicated, block: B:298:0x069d  */
    /* JADX WARN: Code duplicated, block: B:301:0x06ae  */
    /* JADX WARN: Code duplicated, block: B:303:0x06c8  */
    /* JADX WARN: Code duplicated, block: B:304:0x06cd  */
    /* JADX WARN: Code duplicated, block: B:306:0x06d0  */
    /* JADX WARN: Code duplicated, block: B:307:0x06d4  */
    /* JADX WARN: Code duplicated, block: B:309:0x06d8  */
    /* JADX WARN: Code duplicated, block: B:310:0x06da  */
    /* JADX WARN: Code duplicated, block: B:313:0x06df  */
    /* JADX WARN: Code duplicated, block: B:315:0x0729  */
    /* JADX WARN: Code duplicated, block: B:316:0x0730  */
    /* JADX WARN: Code duplicated, block: B:318:0x073a  */
    /* JADX WARN: Code duplicated, block: B:319:0x073c  */
    /* JADX WARN: Code duplicated, block: B:322:0x0770 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:323:0x0772  */
    /* JADX WARN: Code duplicated, block: B:325:0x077a  */
    /* JADX WARN: Code duplicated, block: B:327:0x077e  */
    /* JADX WARN: Code duplicated, block: B:329:0x0784  */
    /* JADX WARN: Code duplicated, block: B:330:0x0789 A[PHI: r32
      0x0789: PHI (r32v1 int) = (r32v0 int), (r32v4 int) binds: [B:328:0x0782, B:324:0x0778] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:332:0x078c  */
    /* JADX WARN: Code duplicated, block: B:333:0x07b7  */
    /* JADX WARN: Code duplicated, block: B:337:0x07bf  */
    /* JADX WARN: Code duplicated, block: B:341:0x07c8 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:342:0x07ca  */
    /* JADX WARN: Code duplicated, block: B:345:0x07d0  */
    /* JADX WARN: Code duplicated, block: B:350:0x07dd  */
    /* JADX WARN: Code duplicated, block: B:361:0x07fb  */
    /* JADX WARN: Code duplicated, block: B:364:0x0808  */
    /* JADX WARN: Code duplicated, block: B:371:0x0841  */
    /* JADX WARN: Code duplicated, block: B:373:0x0844  */
    /* JADX WARN: Code duplicated, block: B:375:0x084c  */
    /* JADX WARN: Code duplicated, block: B:378:0x0884  */
    /* JADX WARN: Code duplicated, block: B:380:0x088e  */
    /* JADX WARN: Code duplicated, block: B:381:0x0895  */
    /* JADX WARN: Code duplicated, block: B:383:0x089f  */
    /* JADX WARN: Code duplicated, block: B:384:0x08a1  */
    /* JADX WARN: Code duplicated, block: B:387:0x08d4  */
    /* JADX WARN: Code duplicated, block: B:390:0x08de  */
    /* JADX WARN: Code duplicated, block: B:392:0x08e6  */
    /* JADX WARN: Code duplicated, block: B:393:0x0925  */
    /* JADX WARN: Code duplicated, block: B:397:0x092d  */
    /* JADX WARN: Code duplicated, block: B:400:0x09ba  */
    /* JADX WARN: Code duplicated, block: B:402:0x09e0  */
    /* JADX WARN: Code duplicated, block: B:405:0x09ed  */
    /* JADX WARN: Code duplicated, block: B:408:0x09f7  */
    /* JADX WARN: Code duplicated, block: B:412:0x09fe  */
    /* JADX WARN: Code duplicated, block: B:415:0x0a09  */
    /* JADX WARN: Code duplicated, block: B:416:0x0a11  */
    /* JADX WARN: Code duplicated, block: B:419:0x0a41  */
    /* JADX WARN: Code duplicated, block: B:422:0x0a47  */
    /* JADX WARN: Code duplicated, block: B:425:0x0a61  */
    /* JADX WARN: Code duplicated, block: B:429:0x0a69  */
    /* JADX WARN: Code duplicated, block: B:432:0x0a72  */
    /* JADX WARN: Code duplicated, block: B:440:0x0a84  */
    /* JADX WARN: Code duplicated, block: B:443:0x0a8d  */
    /* JADX WARN: Code duplicated, block: B:451:0x0a9f  */
    /* JADX WARN: Code duplicated, block: B:457:0x0b17 A[LOOP:13: B:455:0x0b12->B:457:0x0b17, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:458:0x0b1c  */
    /* JADX WARN: Code duplicated, block: B:461:0x0b34  */
    /* JADX WARN: Code duplicated, block: B:467:0x0b48  */
    /* JADX WARN: Code duplicated, block: B:475:0x0b5d  */
    /* JADX WARN: Code duplicated, block: B:480:0x0b69  */
    /* JADX WARN: Code duplicated, block: B:484:0x0b84  */
    /* JADX WARN: Code duplicated, block: B:486:0x0b89  */
    /* JADX WARN: Code duplicated, block: B:488:0x0b99  */
    /* JADX WARN: Code duplicated, block: B:495:0x0646 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:497:0x0670 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:530:0x05a9 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:531:0x05a1 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:538:0x0b29 A[EDGE_INSN: B:538:0x0b29->B:459:0x0b29 BREAK  A[LOOP:13: B:455:0x0b12->B:457:0x0b17], SYNTHETIC] */
    /* JADX WARN: Instruction removed from duplicated block: B:232:0x058b, please report this as an issue */
    /* JADX WARN: Multi-variable type inference failed */
    public final void Q0(Intent intent) {
        boolean z;
        Bundle extras;
        String str;
        String str2;
        Uri uri;
        String str3;
        Bundle bundle;
        String str4;
        String str5;
        String str6;
        String str7;
        String str8;
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
        w81 w81Var;
        d81 d81Var;
        g81 g81Var;
        hw0 hw0VarL;
        String[] strArr3;
        i81 i81Var;
        x81 x81Var;
        ArrayList arrayList12;
        ArrayList arrayList13;
        Uri uri6;
        ArrayList arrayList14;
        String str12;
        ArrayList arrayList15;
        String[] strArr4;
        String[] strArr5;
        Uri uri7;
        g81 g81Var2;
        boolean z3;
        h81 h81Var;
        String str13;
        ArrayList arrayList16;
        String str14;
        ArrayList arrayList17;
        String[] strArr6;
        String str15;
        ArrayList arrayList18;
        String[] strArr7;
        String str16;
        ArrayList arrayList19;
        ArrayList arrayList20;
        String[] strArr8;
        Bundle bundle2;
        ArrayList arrayList21;
        String str17;
        String str18;
        Parcelable parcelable;
        Uri uri8;
        Bundle bundle3;
        Parcelable[] parcelableArray5;
        ArrayList parcelableArrayList2;
        d81 d81Var2;
        ArrayList arrayList22;
        Parcelable[] parcelableArr3;
        String[] strArrL9;
        int i8;
        Parcelable parcelable2;
        String str19;
        ArrayList arrayList23;
        ArrayList arrayList24;
        g81 g81Var3;
        String str20;
        Parcelable parcelable3;
        Object obj;
        Object obj2;
        Object obj3;
        String path;
        String lowerCase;
        String[] strArr9;
        int i9;
        long j;
        ks1 ks1VarA;
        List list;
        List list2;
        ArrayList arrayList25;
        String str21;
        String str22;
        PlayerActivity playerActivity = this;
        playerActivity.m2();
        Uri data = intent.getData();
        String type = intent.getType();
        boolean z4 = true;
        int i10 = 0;
        if (ks1.b(intent)) {
            Bundle bundle4 = intent.getExtras().getBundle("playlist");
            String strX = x91.X("title", bundle4);
            Integer numA = x91.A("start_index", bundle4);
            String[] strArrY = x91.Y(bundle4);
            ArrayList arrayList26 = new ArrayList();
            fp2 fp2VarC = fp2.c(bundle4, "audio", "playlist", arrayList26);
            fp2 fp2VarC2 = fp2.c(bundle4, "subtitle", "playlist", arrayList26);
            String strX2 = x91.X("logo", bundle4);
            String strX3 = x91.X("background", bundle4);
            Parcelable[] parcelableArrJ = x91.J("items", bundle4);
            if (parcelableArrJ == null || parcelableArrJ.length == 0) {
                j = -9223372036854775807L;
                ks1VarA = ks1.a(strX, "playlist has no items");
            } else {
                ArrayList arrayList27 = new ArrayList();
                int i11 = 0;
                j = -9223372036854775807L;
                while (true) {
                    if (i11 >= parcelableArrJ.length) {
                        ArrayList arrayList28 = arrayList26;
                        int iIntValue = numA == null ? 0 : numA.intValue();
                        if (iIntValue < 0 || iIntValue >= arrayList27.size()) {
                            StringBuilder sbV = lh0.v("start_index ", iIntValue, " is out of range 0..");
                            sbV.append(arrayList27.size() - 1);
                            ks1VarA = ks1.a(strX, sbV.toString());
                            break;
                        } else {
                            Integer numA2 = x91.A("report_interval_sec", bundle4);
                            ks1VarA = new ks1(strX, iIntValue, strArrY, fp2VarC, fp2VarC2, DesugarCollections.unmodifiableList(arrayList27), (numA2 == null || numA2.intValue() <= 0) ? 0L : ((long) Math.max(30, numA2.intValue())) * 1000, null, DesugarCollections.unmodifiableList(arrayList28));
                            break;
                        }
                    }
                    Parcelable parcelable4 = parcelableArrJ[i11];
                    if (!(parcelable4 instanceof Bundle)) {
                        ks1VarA = ks1.a(strX, "items[" + i11 + "] is not a Bundle");
                        break;
                    }
                    try {
                        Bundle bundle5 = (Bundle) parcelable4;
                        Integer num = fp2VarC2.a;
                        ArrayList arrayList29 = arrayList26;
                        arrayList27.add(ks1.d(bundle5, strX2, strX3, num != null && num.intValue() >= 0, "items[" + i11 + "]", arrayList29));
                        i11++;
                        arrayList26 = arrayList29;
                    } catch (IllegalArgumentException e) {
                        StringBuilder sbV2 = lh0.v("items[", i11, "] ");
                        sbV2.append(e.getMessage());
                        ks1VarA = ks1.a(strX, sbV2.toString());
                    }
                }
            }
            playerActivity.j3 = true;
            playerActivity.M4 = true;
            playerActivity.H.P0 = false;
            playerActivity.k3 = ks1VarA;
            Iterator it = ks1VarA.i.iterator();
            while (it.hasNext()) {
                gt2.K("playlist key dropped: " + ((String) it.next()));
            }
            if (ks1VarA.h != null) {
                gt2.K("playlist refused: " + ks1VarA.h);
                b7 b7Var = new b7(0);
                playerActivity.l3 = b7Var;
                b7Var.e = ks1VarA.h;
                playerActivity.T2();
                Toast.makeText(playerActivity, R.string.api_bad_playlist, 1).show();
                playerActivity.finish();
                z = true;
            } else {
                playerActivity.w3 = gt2.k0(ks1VarA.a);
                playerActivity.E3 = new long[ks1VarA.f.size()];
                HashMap map = new HashMap();
                int i12 = 0;
                while (true) {
                    int size = ks1VarA.f.size();
                    list = ks1VarA.f;
                    if (i12 >= size) {
                        break;
                    }
                    gs1 gs1Var = (gs1) list.get(i12);
                    String str23 = gs1Var.b;
                    if (str23 == null) {
                        str23 = ks1VarA.a;
                    }
                    String strK1 = gt2.k0(str23);
                    if (strK1 == null || strK1.isEmpty()) {
                        strK1 = gs1Var.a.getLastPathSegment();
                    }
                    String strT0 = playerActivity.t0(gt2.k0(gs1Var.c), gs1Var.i, gs1Var.j);
                    w81 w81Var2 = new w81();
                    w81Var2.a = strK1;
                    w81Var2.e = strK1;
                    w81Var2.f = strT0;
                    Uri uri9 = gs1Var.d;
                    if (uri9 != null) {
                        w81Var2.n = uri9;
                    }
                    d81 d81Var3 = new d81();
                    g81 g81Var4 = new g81();
                    List list3 = Collections.EMPTY_LIST;
                    fw0 fw0Var = hw0.m;
                    ry1 ry1Var = ry1.p;
                    i81 i81Var2 = new i81();
                    l81 l81Var = l81.d;
                    Uri uri10 = gs1Var.a;
                    x81 x81Var2 = new x81(w81Var2);
                    boolean z5 = z4;
                    int i13 = i10;
                    long j2 = gs1Var.m;
                    if (j2 > 0 || gs1Var.n != Long.MIN_VALUE) {
                        d81 d81Var4 = new d81();
                        long jY = ys2.Y(j2);
                        x91.h(jY >= 0 ? z5 : i13);
                        d81Var4.a = jY;
                        long jY2 = ys2.Y(gs1Var.n);
                        x91.h((jY2 == Long.MIN_VALUE || jY2 >= 0) ? z5 : i13);
                        d81Var4.b = jY2;
                        d81Var3 = new e81(d81Var4).a();
                    }
                    hw0 hw0VarL2 = !gs1Var.s.isEmpty() ? hw0.l(playerActivity.U3(gs1Var.s)) : ry1Var;
                    ArrayList arrayList30 = playerActivity.A3;
                    x91.s((g81Var4.b == null || g81Var4.a != null) ? z5 : i13);
                    arrayList30.add(new p81("", new f81(d81Var3), uri10 != null ? new k81(uri10, null, g81Var4.a != null ? new h81(g81Var4) : null, null, list3, null, hw0VarL2, -9223372036854775807L) : null, new j81(i81Var2), x81Var2, l81Var));
                    playerActivity.B3.add(gs1Var.q);
                    ArrayList arrayList31 = playerActivity.J3;
                    int i14 = gs1Var.i;
                    arrayList31.add(i14 < 0 ? null : Integer.valueOf(i14));
                    ArrayList arrayList32 = playerActivity.K3;
                    int i15 = gs1Var.j;
                    arrayList32.add(i15 < 0 ? null : Integer.valueOf(i15));
                    playerActivity.L3.add(gs1Var.c);
                    playerActivity.M3.add(gs1Var.e);
                    playerActivity.N3.add(gs1Var.f);
                    playerActivity.O3.add(gs1Var.g);
                    playerActivity.P3.add(gs1Var.h);
                    playerActivity.b4.add(R1(gs1Var.r));
                    playerActivity.E3[i12] = gs1Var.l;
                    TreeMap treeMap = new TreeMap(String.CASE_INSENSITIVE_ORDER);
                    String[] strArr10 = ks1VarA.c;
                    if (strArr10 != null) {
                        int i16 = i13;
                        while (true) {
                            int i17 = i16 + 1;
                            if (i17 >= strArr10.length) {
                                break;
                            }
                            String str24 = strArr10[i16];
                            if (str24 != null && (str22 = strArr10[i17]) != null) {
                                treeMap.put(str24, str22);
                            }
                            i16 += 2;
                        }
                    }
                    String[] strArr11 = gs1Var.k;
                    if (strArr11 != null) {
                        int i18 = i13;
                        while (true) {
                            int i19 = i18 + 1;
                            if (i19 >= strArr11.length) {
                                break;
                            }
                            String str25 = strArr11[i18];
                            if (str25 != null && (str21 = strArr11[i19]) != null) {
                                treeMap.put(str25, str21);
                            }
                            i18 += 2;
                        }
                    }
                    if (!treeMap.isEmpty()) {
                        map.put(gs1Var.a.toString(), treeMap);
                    }
                    i12++;
                    i10 = i13;
                    z4 = z5;
                }
                z = z4;
                int i20 = i10;
                playerActivity.l3 = new b7(list.size());
                Handler handler = playerActivity.J5;
                cq1 cq1Var = playerActivity.K5;
                handler.removeCallbacks(cq1Var);
                ks1 ks1Var = playerActivity.k3;
                if (ks1Var != null) {
                    long j3 = ks1Var.g;
                    if (j3 > 0) {
                        handler.postDelayed(cq1Var, j3);
                    }
                }
                int i21 = ks1VarA.b;
                playerActivity.C3 = i21;
                playerActivity.D3 = i21;
                playerActivity.u3 = map.isEmpty() ? null : map;
                playerActivity.v3 = (Map) map.get(((gs1) ks1VarA.f.get(ks1VarA.b)).a.toString());
                HashMap map2 = playerActivity.t3;
                map2.clear();
                hj2 hj2VarN = vt1.n(playerActivity, playerActivity.i4());
                ArrayList<String> arrayListC0 = gt2.c0(playerActivity.H.T);
                ArrayList arrayList33 = new ArrayList();
                for (String str26 : arrayListC0) {
                    for (String[] strArr12 : hj2VarN.F()) {
                        if (strArr12[i20].equals(str26)) {
                            arrayList33.add(strArr12);
                        }
                    }
                }
                if (arrayListC0.isEmpty()) {
                    arrayList33.addAll(hj2VarN.F());
                }
                int i22 = i20;
                while (true) {
                    int size2 = ks1VarA.f.size();
                    list2 = ks1VarA.f;
                    if (i22 >= size2) {
                        break;
                    }
                    gs1 gs1Var2 = (gs1) list2.get(i22);
                    List list4 = gs1Var2.t;
                    Uri uri11 = gs1Var2.a;
                    if (list4.size() < 2 || gs1Var2.u) {
                        arrayList25 = arrayList33;
                    } else {
                        String strN0 = playerActivity.n0(i22);
                        String[] strArr13 = strN0 == null ? null : (String[]) ((LinkedHashMap) hj2VarN.o).get(strN0);
                        int iR4 = strArr13 == null ? -1 : playerActivity.R4(gs1Var2, strArr13[z ? 1 : 0]);
                        int i23 = R.string.audio_memory_as_before;
                        int i24 = i20;
                        while (iR4 < 0 && i24 < arrayList33.size()) {
                            iR4 = playerActivity.R4(gs1Var2, ((String[]) arrayList33.get(i24))[z ? 1 : 0]);
                            if (iR4 >= 0 && i22 == ks1VarA.b) {
                                if (strArr13 == null) {
                                    hj2VarN.G(strN0, null, ((js1) list4.get(iR4)).a);
                                }
                                hj2VarN.D(strN0, strN0 != null ? strN0 : uri11.toString(), ((String[]) arrayList33.get(i24))[i20], ((js1) list4.get(iR4)).a);
                                vt1.t(playerActivity, hj2VarN);
                            }
                            i24++;
                            i23 = R.string.audio_memory_usual;
                            arrayList33 = arrayList33;
                        }
                        arrayList25 = arrayList33;
                        if (iR4 >= 0) {
                            map2.put(Integer.valueOf(i22), new int[]{iR4, i23});
                            if (iR4 != gs1Var2.a(uri11.toString())) {
                                playerActivity.A3.set(i22, playerActivity.I3(i22, ((js1) list4.get(iR4)).b));
                            }
                        }
                    }
                    i22++;
                    arrayList33 = arrayList25;
                }
                gs1 gs1Var3 = (gs1) list2.get(ks1VarA.b);
                playerActivity.H.x(playerActivity, gs1Var3.a, type);
                long j4 = gs1Var3.l;
                if (j4 != j) {
                    playerActivity.H.z(j4);
                }
                if (!y1(gs1Var3, ks1VarA)) {
                    playerActivity.H2();
                }
            }
        } else {
            z = true;
            if (type != null) {
                String[] strArr14 = gt2.c;
                int i25 = 0;
                while (true) {
                    if (i25 < 6) {
                        if (!type.equals(strArr14[i25])) {
                            i25++;
                        }
                    } else if (!type.equals("text/plain") && !type.equals("text/x-ssa") && !type.equals("application/octet-stream") && !type.equals("application/ass") && !type.equals("application/ssa") && !type.equals("application/vtt")) {
                        if (data != null && gt2.E(data) && (path = data.getPath()) != null) {
                            lowerCase = path.toLowerCase();
                            strArr9 = gt2.b;
                            i9 = 0;
                            while (true) {
                                if (i9 < 7) {
                                    if (lowerCase.endsWith("." + strArr9[i9])) {
                                        i9++;
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
                            bundle = extras;
                            str4 = "subs.enable";
                            str5 = "subs";
                            str6 = "return_result";
                            str7 = "position";
                        } else {
                            if (!extras.containsKey("position") || extras.containsKey("return_result") || extras.containsKey("subs") || extras.containsKey("subs.enable") || extras.containsKey("video_list") || extras.containsKey("quality_levels")) {
                                z2 = true;
                            } else {
                                z2 = false;
                            }
                            playerActivity.j3 = z2;
                            if (z2) {
                                playerActivity.H.P0 = false;
                            } else {
                                extras.containsKey("title");
                            }
                            charSequence = extras.getCharSequence("title");
                            if (charSequence == null) {
                                string = null;
                            } else {
                                string = charSequence.toString();
                            }
                            playerActivity.w3 = gt2.k0(string);
                            string2 = extras.getString("thumbnail");
                            if (string2 != null) {
                                playerActivity.x3 = Uri.parse(string2);
                            }
                            playerActivity.y3 = extras.getString("segments");
                            playerActivity.z3 = extras.getStringArray("headers");
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
                            playerActivity.F3 = i2;
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
                            playerActivity.G3 = i3;
                            playerActivity.H3 = extras.getString("imdb_id");
                            if (extras.containsKey("id") || (obj = extras.get("id")) == null) {
                                strTrim3 = null;
                            } else {
                                strTrim3 = String.valueOf(obj).trim();
                                if (strTrim3.isEmpty()) {
                                    strTrim3 = null;
                                }
                            }
                            playerActivity.I3 = strTrim3;
                            playerActivity.c4 = U1(extras, "quality_levels", "quality_urls");
                            if (extras.containsKey("video_list")) {
                                arrayList = playerActivity.b4;
                                arrayList2 = playerActivity.P3;
                                arrayList3 = playerActivity.O3;
                                arrayList4 = playerActivity.L3;
                                arrayList5 = playerActivity.K3;
                                ArrayList arrayList34 = playerActivity.J3;
                                arrayList6 = playerActivity.B3;
                                arrayList7 = arrayList34;
                                arrayList8 = playerActivity.A3;
                                parcelableArray3 = extras.getParcelableArray("video_list");
                                if (parcelableArray3 == null) {
                                    strArrL0 = L0("video_list", extras);
                                } else {
                                    strArrL0 = null;
                                }
                                if (parcelableArray3 != null) {
                                    arrayList9 = arrayList4;
                                    length = parcelableArray3.length;
                                } else {
                                    arrayList9 = arrayList4;
                                    if (strArrL0 != null) {
                                        length = strArrL0.length;
                                    } else {
                                        length = 0;
                                    }
                                }
                                if (length == 0) {
                                    arrayList10 = arrayList5;
                                    strArrL1 = L0("video_list.name", extras);
                                    parcelableArr = parcelableArray3;
                                    strArrL2 = L0("video_list.filename", extras);
                                    strArr = strArrL0;
                                    strArrL3 = L0("video_list.thumbnail", extras);
                                    arrayList11 = arrayList6;
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
                                    arrayList11.clear();
                                    arrayList7.clear();
                                    arrayList10.clear();
                                    arrayList9.clear();
                                    playerActivity.M3.clear();
                                    playerActivity.N3.clear();
                                    arrayList3.clear();
                                    arrayList2.clear();
                                    arrayList.clear();
                                    playerActivity.C3 = 0;
                                    playerActivity.D3 = 0;
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
                                            ArrayList arrayList35 = arrayList7;
                                            str = str;
                                            arrayList13 = arrayList35;
                                            parcelableArr2 = parcelableArr2;
                                            arrayList21 = arrayList;
                                            arrayList18 = arrayList3;
                                            arrayList17 = arrayList9;
                                            arrayList16 = arrayList10;
                                            strArr8 = strArrL6;
                                            parcelableArr = parcelableArr;
                                            str12 = type;
                                            bundle2 = extras;
                                            str2 = str2;
                                            arrayList19 = arrayList2;
                                            arrayList15 = arrayList8;
                                            arrayList20 = arrayList11;
                                            strArr4 = strArrL4;
                                            strArrL5 = strArrL5;
                                            strArr7 = strArrL8;
                                            uri7 = data;
                                            strArr5 = strArrL3;
                                            strArr6 = strArrL7;
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
                                            strK0 = gt2.k0(str11);
                                            if (strK0 != null || strK0.isEmpty()) {
                                                strK0 = uri4.getLastPathSegment();
                                            }
                                            strArr2 = strArrL1;
                                            if (strArrL3 != null || i4 >= strArrL3.length || (str20 = strArrL3[i4]) == null || str20.isEmpty()) {
                                                uri5 = null;
                                            } else {
                                                uri5 = Uri.parse(strArrL3[i4]);
                                            }
                                            w81Var = new w81();
                                            w81Var.a = strK0;
                                            w81Var.e = strK0;
                                            if (uri5 != null) {
                                                w81Var.n = uri5;
                                            }
                                            if (data != null && uri4.equals(data)) {
                                                int size3 = arrayList8.size();
                                                playerActivity.C3 = size3;
                                                playerActivity.D3 = size3;
                                            }
                                            d81Var = new d81();
                                            g81Var = new g81();
                                            List list5 = Collections.EMPTY_LIST;
                                            fw0 fw0Var2 = hw0.m;
                                            hw0VarL = ry1.p;
                                            strArr3 = strArrL3;
                                            i81 i81Var3 = new i81();
                                            l81 l81Var2 = l81.d;
                                            i81Var = i81Var3;
                                            x81Var = new x81(w81Var);
                                            arrayList12 = new ArrayList();
                                            if (parcelableArr2 != null) {
                                                if (i4 < parcelableArr2.length) {
                                                    parcelable = parcelableArr2[i4];
                                                    uri8 = uri4;
                                                    if (parcelable instanceof Bundle) {
                                                        bundle3 = (Bundle) parcelable;
                                                        parcelableArray5 = bundle3.getParcelableArray("uris");
                                                        if (parcelableArray5 != null) {
                                                            arrayList22 = arrayList12;
                                                            parcelableArr3 = parcelableArray5;
                                                            d81Var2 = d81Var;
                                                        } else {
                                                            parcelableArrayList2 = bundle3.getParcelableArrayList("uris");
                                                            d81Var2 = d81Var;
                                                            arrayList22 = arrayList12;
                                                            if (parcelableArrayList2 == null) {
                                                                parcelableArr3 = null;
                                                            } else {
                                                                parcelableArr3 = (Parcelable[]) parcelableArrayList2.toArray(new Parcelable[0]);
                                                            }
                                                        }
                                                        if (parcelableArr3 != null) {
                                                            strArrL9 = L0("names", bundle3);
                                                            i8 = 0;
                                                            while (i8 < parcelableArr3.length) {
                                                                parcelable2 = parcelableArr3[i8];
                                                                Parcelable[] parcelableArr4 = parcelableArr3;
                                                                if (parcelable2 instanceof Uri) {
                                                                    if (strArrL9 != null || i8 >= strArrL9.length) {
                                                                        str19 = null;
                                                                    } else {
                                                                        str19 = strArrL9[i8];
                                                                    }
                                                                    arrayList23 = arrayList7;
                                                                    arrayList24 = arrayList22;
                                                                    g81Var3 = g81Var;
                                                                    arrayList24.add(zi0.b(this, (Uri) parcelable2, str19, false, null, null));
                                                                } else {
                                                                    arrayList23 = arrayList7;
                                                                    arrayList24 = arrayList22;
                                                                    g81Var3 = g81Var;
                                                                }
                                                                i8++;
                                                                arrayList7 = arrayList23;
                                                                str = str;
                                                                d81Var2 = d81Var2;
                                                                g81Var = g81Var3;
                                                                arrayList22 = arrayList24;
                                                                arrayList = arrayList;
                                                                arrayList3 = arrayList3;
                                                                strArrL9 = strArrL9;
                                                                uri8 = uri8;
                                                                parcelableArr3 = parcelableArr4;
                                                                x81Var = x81Var;
                                                                parcelableArr2 = parcelableArr2;
                                                                strArrL7 = strArrL7;
                                                                arrayList11 = arrayList11;
                                                                i81Var = i81Var;
                                                                data = data;
                                                                strArr3 = strArr3;
                                                                str2 = str2;
                                                                arrayList8 = arrayList8;
                                                                strArrL4 = strArrL4;
                                                                type = type;
                                                                parcelableArr = parcelableArr;
                                                                extras = extras;
                                                                arrayList9 = arrayList9;
                                                                strArrL8 = strArrL8;
                                                                arrayList10 = arrayList10;
                                                                strArrL5 = strArrL5;
                                                                arrayList2 = arrayList2;
                                                                strArrL6 = strArrL6;
                                                            }
                                                        }
                                                        ArrayList arrayList36 = arrayList7;
                                                        str = str;
                                                        arrayList13 = arrayList36;
                                                        playerActivity = this;
                                                        x81Var = x81Var;
                                                        parcelableArr2 = parcelableArr2;
                                                        arrayList10 = arrayList10;
                                                        strArrL6 = strArrL6;
                                                        strArrL7 = strArrL7;
                                                        strArrL8 = strArrL8;
                                                        d81Var = d81Var2;
                                                        parcelableArr = parcelableArr;
                                                        str2 = str2;
                                                        arrayList2 = arrayList2;
                                                        arrayList3 = arrayList3;
                                                        arrayList9 = arrayList9;
                                                        strArrL5 = strArrL5;
                                                        i81Var = i81Var;
                                                        uri6 = uri8;
                                                        arrayList14 = arrayList22;
                                                    } else {
                                                        ArrayList arrayList37 = arrayList7;
                                                        str = str;
                                                        arrayList13 = arrayList37;
                                                        playerActivity = this;
                                                        uri6 = uri8;
                                                        arrayList14 = arrayList12;
                                                    }
                                                } else {
                                                    arrayList13 = arrayList7;
                                                    playerActivity = this;
                                                }
                                                str12 = type;
                                                Bundle bundle6 = extras;
                                                arrayList15 = arrayList8;
                                                strArr4 = strArrL4;
                                                strArr5 = strArr3;
                                                uri7 = data;
                                                ArrayList arrayList38 = arrayList11;
                                                ArrayList arrayList39 = arrayList;
                                                g81Var2 = g81Var;
                                                if (!arrayList14.isEmpty()) {
                                                    hw0VarL = hw0.l(arrayList14);
                                                }
                                                hw0 hw0Var = hw0VarL;
                                                if (g81Var2.b == null && g81Var2.a == null) {
                                                    z3 = false;
                                                } else {
                                                    z3 = true;
                                                }
                                                x91.s(z3);
                                                if (g81Var2.a != null) {
                                                    h81Var = new h81(g81Var2);
                                                } else {
                                                    h81Var = null;
                                                }
                                                arrayList15.add(new p81("", new f81(d81Var), new k81(uri6, null, h81Var, null, list5, null, hw0Var, -9223372036854775807L), new j81(i81Var), x81Var, l81Var2));
                                                if (strArr4 != null || i4 >= strArr4.length) {
                                                    str13 = null;
                                                } else {
                                                    str13 = strArr4[i4];
                                                }
                                                arrayList38.add(str13);
                                                arrayList13.add(E1(strArrL5, i4));
                                                String[] strArr15 = strArrL6;
                                                arrayList16 = arrayList10;
                                                arrayList16.add(E1(strArr15, i4));
                                                strArrL1 = strArr2;
                                                if (strArr2 != null || i4 >= strArrL1.length) {
                                                    str14 = null;
                                                } else {
                                                    str14 = strArrL1[i4];
                                                }
                                                arrayList17 = arrayList9;
                                                arrayList17.add(str14);
                                                strArr6 = strArrL7;
                                                if (strArr6 != null || i4 >= strArr6.length || (str18 = strArr6[i4]) == null || str18.isEmpty()) {
                                                    str15 = null;
                                                } else {
                                                    str15 = strArr6[i4];
                                                }
                                                arrayList18 = arrayList3;
                                                arrayList18.add(str15);
                                                strArr7 = strArrL8;
                                                if (strArr7 != null || i4 >= strArr7.length || (str17 = strArr7[i4]) == null || str17.isEmpty()) {
                                                    str16 = null;
                                                } else {
                                                    str16 = strArr7[i4];
                                                }
                                                arrayList19 = arrayList2;
                                                arrayList19.add(str16);
                                                arrayList20 = arrayList38;
                                                strArr8 = strArr15;
                                                bundle2 = bundle6;
                                                arrayList21 = arrayList39;
                                                arrayList21.add(U1(bundle2, "video_list.quality_levels." + i4, "video_list.quality_urls." + i4));
                                            } else {
                                                arrayList13 = arrayList7;
                                            }
                                            uri6 = uri4;
                                            arrayList14 = arrayList12;
                                            str12 = type;
                                            Bundle bundle7 = extras;
                                            arrayList15 = arrayList8;
                                            strArr4 = strArrL4;
                                            strArr5 = strArr3;
                                            uri7 = data;
                                            ArrayList arrayList310 = arrayList11;
                                            ArrayList arrayList311 = arrayList;
                                            g81Var2 = g81Var;
                                            if (!arrayList14.isEmpty()) {
                                                hw0VarL = hw0.l(arrayList14);
                                            }
                                            hw0 hw0Var2 = hw0VarL;
                                            if (g81Var2.b == null) {
                                                z3 = true;
                                            } else {
                                                z3 = true;
                                            }
                                            x91.s(z3);
                                            if (g81Var2.a != null) {
                                                h81Var = new h81(g81Var2);
                                            } else {
                                                h81Var = null;
                                            }
                                            arrayList15.add(new p81("", new f81(d81Var), new k81(uri6, null, h81Var, null, list5, null, hw0Var2, -9223372036854775807L), new j81(i81Var), x81Var, l81Var2));
                                            if (strArr4 != null) {
                                                str13 = null;
                                            } else {
                                                str13 = null;
                                            }
                                            arrayList310.add(str13);
                                            arrayList13.add(E1(strArrL5, i4));
                                            String[] strArr16 = strArrL6;
                                            arrayList16 = arrayList10;
                                            arrayList16.add(E1(strArr16, i4));
                                            strArrL1 = strArr2;
                                            if (strArr2 != null) {
                                                str14 = null;
                                            } else {
                                                str14 = null;
                                            }
                                            arrayList17 = arrayList9;
                                            arrayList17.add(str14);
                                            strArr6 = strArrL7;
                                            if (strArr6 != null) {
                                                str15 = null;
                                            } else {
                                                str15 = null;
                                            }
                                            arrayList18 = arrayList3;
                                            arrayList18.add(str15);
                                            strArr7 = strArrL8;
                                            if (strArr7 != null) {
                                                str16 = null;
                                            } else {
                                                str16 = null;
                                            }
                                            arrayList19 = arrayList2;
                                            arrayList19.add(str16);
                                            arrayList20 = arrayList310;
                                            strArr8 = strArr16;
                                            bundle2 = bundle7;
                                            arrayList21 = arrayList311;
                                            arrayList21.add(U1(bundle2, "video_list.quality_levels." + i4, "video_list.quality_urls." + i4));
                                        }
                                        i4++;
                                        String str27 = str;
                                        arrayList7 = arrayList13;
                                        str = str27;
                                        strArrL7 = strArr6;
                                        strArrL8 = strArr7;
                                        strArrL5 = strArrL5;
                                        arrayList2 = arrayList19;
                                        data = uri7;
                                        strArrL3 = strArr5;
                                        arrayList11 = arrayList20;
                                        strArrL6 = strArr8;
                                        str2 = str2;
                                        arrayList8 = arrayList15;
                                        strArrL4 = strArr4;
                                        arrayList3 = arrayList18;
                                        extras = bundle2;
                                        type = str12;
                                        parcelableArr = parcelableArr;
                                        parcelableArr2 = parcelableArr2;
                                        arrayList = arrayList21;
                                        arrayList10 = arrayList16;
                                        arrayList9 = arrayList17;
                                        length = i7;
                                        strArrL2 = strArrL2;
                                    }
                                    str3 = type;
                                    bundle = extras;
                                    str4 = str;
                                    str5 = str2;
                                    ArrayList arrayList40 = arrayList8;
                                    uri = data;
                                    playerActivity.E3 = new long[arrayList40.size()];
                                    i5 = 0;
                                    while (true) {
                                        jArr = playerActivity.E3;
                                        if (i5 < jArr.length) {
                                            break;
                                        }
                                        jArr[i5] = -9223372036854775807L;
                                        i5++;
                                    }
                                } else {
                                    uri = data;
                                    str3 = type;
                                    bundle = extras;
                                    str4 = "subs.enable";
                                    str5 = "subs";
                                    str6 = "return_result";
                                    str7 = "position";
                                }
                            } else {
                                uri = data;
                                str3 = type;
                                bundle = extras;
                                str4 = "subs.enable";
                                str5 = "subs";
                                str6 = "return_result";
                                str7 = "position";
                            }
                        }
                        playerActivity.H.x(playerActivity, uri, str3);
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
                                    Uri uri12 = (Uri) parcelableArray2[i];
                                    if (stringArray != null || stringArray.length <= i) {
                                        str9 = null;
                                    } else {
                                        str9 = stringArray[i];
                                    }
                                    playerActivity.L4.add(zi0.b(playerActivity, uri12, str9, uri12.equals(uri2), null, null));
                                }
                            }
                        }
                        if (playerActivity.L4.isEmpty()) {
                            playerActivity.H2();
                        }
                        if (bundle != null) {
                            playerActivity.M4 = bundle.getBoolean(str6);
                            str8 = str7;
                            if (bundle.containsKey(str8)) {
                                playerActivity.H.z(bundle.getInt(str8));
                            }
                        }
                    }
                    playerActivity.P0(data);
                }
            } else {
                if (data != null) {
                    lowerCase = path.toLowerCase();
                    strArr9 = gt2.b;
                    i9 = 0;
                    while (true) {
                        if (i9 < 7) {
                            if (lowerCase.endsWith("." + strArr9[i9])) {
                                i9++;
                            } else {
                                playerActivity.P0(data);
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
                    bundle = extras;
                    str4 = "subs.enable";
                    str5 = "subs";
                    str6 = "return_result";
                    str7 = "position";
                } else {
                    if (extras.containsKey("position")) {
                        z2 = true;
                    } else {
                        z2 = true;
                    }
                    playerActivity.j3 = z2;
                    if (z2) {
                        playerActivity.H.P0 = false;
                    } else {
                        extras.containsKey("title");
                    }
                    charSequence = extras.getCharSequence("title");
                    if (charSequence == null) {
                        string = null;
                    } else {
                        string = charSequence.toString();
                    }
                    playerActivity.w3 = gt2.k0(string);
                    string2 = extras.getString("thumbnail");
                    if (string2 != null) {
                        playerActivity.x3 = Uri.parse(string2);
                    }
                    playerActivity.y3 = extras.getString("segments");
                    playerActivity.z3 = extras.getStringArray("headers");
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
                    playerActivity.F3 = i2;
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
                    playerActivity.G3 = i3;
                    playerActivity.H3 = extras.getString("imdb_id");
                    if (extras.containsKey("id")) {
                        strTrim3 = null;
                    } else {
                        strTrim3 = String.valueOf(obj).trim();
                        if (strTrim3.isEmpty()) {
                            strTrim3 = null;
                        }
                    }
                    playerActivity.I3 = strTrim3;
                    playerActivity.c4 = U1(extras, "quality_levels", "quality_urls");
                    if (extras.containsKey("video_list")) {
                        arrayList = playerActivity.b4;
                        arrayList2 = playerActivity.P3;
                        arrayList3 = playerActivity.O3;
                        arrayList4 = playerActivity.L3;
                        arrayList5 = playerActivity.K3;
                        ArrayList arrayList312 = playerActivity.J3;
                        arrayList6 = playerActivity.B3;
                        arrayList7 = arrayList312;
                        arrayList8 = playerActivity.A3;
                        parcelableArray3 = extras.getParcelableArray("video_list");
                        if (parcelableArray3 == null) {
                            strArrL0 = L0("video_list", extras);
                        } else {
                            strArrL0 = null;
                        }
                        if (parcelableArray3 != null) {
                            arrayList9 = arrayList4;
                            length = parcelableArray3.length;
                        } else {
                            arrayList9 = arrayList4;
                            if (strArrL0 != null) {
                                length = strArrL0.length;
                            } else {
                                length = 0;
                            }
                        }
                        if (length == 0) {
                            arrayList10 = arrayList5;
                            strArrL1 = L0("video_list.name", extras);
                            parcelableArr = parcelableArray3;
                            strArrL2 = L0("video_list.filename", extras);
                            strArr = strArrL0;
                            strArrL3 = L0("video_list.thumbnail", extras);
                            arrayList11 = arrayList6;
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
                            arrayList11.clear();
                            arrayList7.clear();
                            arrayList10.clear();
                            arrayList9.clear();
                            playerActivity.M3.clear();
                            playerActivity.N3.clear();
                            arrayList3.clear();
                            arrayList2.clear();
                            arrayList.clear();
                            playerActivity.C3 = 0;
                            playerActivity.D3 = 0;
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
                                    ArrayList arrayList313 = arrayList7;
                                    str = str;
                                    arrayList13 = arrayList313;
                                    parcelableArr2 = parcelableArr2;
                                    arrayList21 = arrayList;
                                    arrayList18 = arrayList3;
                                    arrayList17 = arrayList9;
                                    arrayList16 = arrayList10;
                                    strArr8 = strArrL6;
                                    parcelableArr = parcelableArr;
                                    str12 = type;
                                    bundle2 = extras;
                                    str2 = str2;
                                    arrayList19 = arrayList2;
                                    arrayList15 = arrayList8;
                                    arrayList20 = arrayList11;
                                    strArr4 = strArrL4;
                                    strArrL5 = strArrL5;
                                    strArr7 = strArrL8;
                                    uri7 = data;
                                    strArr5 = strArrL3;
                                    strArr6 = strArrL7;
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
                                    strK0 = gt2.k0(str11);
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
                                    w81Var = new w81();
                                    w81Var.a = strK0;
                                    w81Var.e = strK0;
                                    if (uri5 != null) {
                                        w81Var.n = uri5;
                                    }
                                    if (data != null) {
                                        int size4 = arrayList8.size();
                                        playerActivity.C3 = size4;
                                        playerActivity.D3 = size4;
                                    }
                                    d81Var = new d81();
                                    g81Var = new g81();
                                    List list6 = Collections.EMPTY_LIST;
                                    fw0 fw0Var3 = hw0.m;
                                    hw0VarL = ry1.p;
                                    strArr3 = strArrL3;
                                    i81 i81Var4 = new i81();
                                    l81 l81Var3 = l81.d;
                                    i81Var = i81Var4;
                                    x81Var = new x81(w81Var);
                                    arrayList12 = new ArrayList();
                                    if (parcelableArr2 != null) {
                                        if (i4 < parcelableArr2.length) {
                                            parcelable = parcelableArr2[i4];
                                            uri8 = uri4;
                                            if (parcelable instanceof Bundle) {
                                                ArrayList arrayList314 = arrayList7;
                                                str = str;
                                                arrayList13 = arrayList314;
                                                playerActivity = this;
                                                uri6 = uri8;
                                                arrayList14 = arrayList12;
                                            } else {
                                                bundle3 = (Bundle) parcelable;
                                                parcelableArray5 = bundle3.getParcelableArray("uris");
                                                if (parcelableArray5 != null) {
                                                    arrayList22 = arrayList12;
                                                    parcelableArr3 = parcelableArray5;
                                                    d81Var2 = d81Var;
                                                } else {
                                                    parcelableArrayList2 = bundle3.getParcelableArrayList("uris");
                                                    d81Var2 = d81Var;
                                                    arrayList22 = arrayList12;
                                                    if (parcelableArrayList2 == null) {
                                                        parcelableArr3 = null;
                                                    } else {
                                                        parcelableArr3 = (Parcelable[]) parcelableArrayList2.toArray(new Parcelable[0]);
                                                    }
                                                }
                                                if (parcelableArr3 != null) {
                                                    strArrL9 = L0("names", bundle3);
                                                    i8 = 0;
                                                    while (i8 < parcelableArr3.length) {
                                                        parcelable2 = parcelableArr3[i8];
                                                        Parcelable[] parcelableArr5 = parcelableArr3;
                                                        if (parcelable2 instanceof Uri) {
                                                            arrayList23 = arrayList7;
                                                            arrayList24 = arrayList22;
                                                            g81Var3 = g81Var;
                                                        } else {
                                                            if (strArrL9 != null) {
                                                                str19 = null;
                                                            } else {
                                                                str19 = null;
                                                            }
                                                            arrayList23 = arrayList7;
                                                            arrayList24 = arrayList22;
                                                            g81Var3 = g81Var;
                                                            arrayList24.add(zi0.b(this, (Uri) parcelable2, str19, false, null, null));
                                                        }
                                                        i8++;
                                                        arrayList7 = arrayList23;
                                                        str = str;
                                                        d81Var2 = d81Var2;
                                                        g81Var = g81Var3;
                                                        arrayList22 = arrayList24;
                                                        arrayList = arrayList;
                                                        arrayList3 = arrayList3;
                                                        strArrL9 = strArrL9;
                                                        uri8 = uri8;
                                                        parcelableArr3 = parcelableArr5;
                                                        x81Var = x81Var;
                                                        parcelableArr2 = parcelableArr2;
                                                        strArrL7 = strArrL7;
                                                        arrayList11 = arrayList11;
                                                        i81Var = i81Var;
                                                        data = data;
                                                        strArr3 = strArr3;
                                                        str2 = str2;
                                                        arrayList8 = arrayList8;
                                                        strArrL4 = strArrL4;
                                                        type = type;
                                                        parcelableArr = parcelableArr;
                                                        extras = extras;
                                                        arrayList9 = arrayList9;
                                                        strArrL8 = strArrL8;
                                                        arrayList10 = arrayList10;
                                                        strArrL5 = strArrL5;
                                                        arrayList2 = arrayList2;
                                                        strArrL6 = strArrL6;
                                                    }
                                                }
                                                ArrayList arrayList315 = arrayList7;
                                                str = str;
                                                arrayList13 = arrayList315;
                                                playerActivity = this;
                                                x81Var = x81Var;
                                                parcelableArr2 = parcelableArr2;
                                                arrayList10 = arrayList10;
                                                strArrL6 = strArrL6;
                                                strArrL7 = strArrL7;
                                                strArrL8 = strArrL8;
                                                d81Var = d81Var2;
                                                parcelableArr = parcelableArr;
                                                str2 = str2;
                                                arrayList2 = arrayList2;
                                                arrayList3 = arrayList3;
                                                arrayList9 = arrayList9;
                                                strArrL5 = strArrL5;
                                                i81Var = i81Var;
                                                uri6 = uri8;
                                                arrayList14 = arrayList22;
                                            }
                                        } else {
                                            arrayList13 = arrayList7;
                                            playerActivity = this;
                                        }
                                        str12 = type;
                                        Bundle bundle8 = extras;
                                        arrayList15 = arrayList8;
                                        strArr4 = strArrL4;
                                        strArr5 = strArr3;
                                        uri7 = data;
                                        ArrayList arrayList316 = arrayList11;
                                        ArrayList arrayList317 = arrayList;
                                        g81Var2 = g81Var;
                                        if (!arrayList14.isEmpty()) {
                                            hw0VarL = hw0.l(arrayList14);
                                        }
                                        hw0 hw0Var3 = hw0VarL;
                                        if (g81Var2.b == null) {
                                            z3 = true;
                                        } else {
                                            z3 = true;
                                        }
                                        x91.s(z3);
                                        if (g81Var2.a != null) {
                                            h81Var = new h81(g81Var2);
                                        } else {
                                            h81Var = null;
                                        }
                                        arrayList15.add(new p81("", new f81(d81Var), new k81(uri6, null, h81Var, null, list6, null, hw0Var3, -9223372036854775807L), new j81(i81Var), x81Var, l81Var3));
                                        if (strArr4 != null) {
                                            str13 = null;
                                        } else {
                                            str13 = null;
                                        }
                                        arrayList316.add(str13);
                                        arrayList13.add(E1(strArrL5, i4));
                                        String[] strArr17 = strArrL6;
                                        arrayList16 = arrayList10;
                                        arrayList16.add(E1(strArr17, i4));
                                        strArrL1 = strArr2;
                                        if (strArr2 != null) {
                                            str14 = null;
                                        } else {
                                            str14 = null;
                                        }
                                        arrayList17 = arrayList9;
                                        arrayList17.add(str14);
                                        strArr6 = strArrL7;
                                        if (strArr6 != null) {
                                            str15 = null;
                                        } else {
                                            str15 = null;
                                        }
                                        arrayList18 = arrayList3;
                                        arrayList18.add(str15);
                                        strArr7 = strArrL8;
                                        if (strArr7 != null) {
                                            str16 = null;
                                        } else {
                                            str16 = null;
                                        }
                                        arrayList19 = arrayList2;
                                        arrayList19.add(str16);
                                        arrayList20 = arrayList316;
                                        strArr8 = strArr17;
                                        bundle2 = bundle8;
                                        arrayList21 = arrayList317;
                                        arrayList21.add(U1(bundle2, "video_list.quality_levels." + i4, "video_list.quality_urls." + i4));
                                    } else {
                                        arrayList13 = arrayList7;
                                    }
                                    uri6 = uri4;
                                    arrayList14 = arrayList12;
                                    str12 = type;
                                    Bundle bundle9 = extras;
                                    arrayList15 = arrayList8;
                                    strArr4 = strArrL4;
                                    strArr5 = strArr3;
                                    uri7 = data;
                                    ArrayList arrayList318 = arrayList11;
                                    ArrayList arrayList319 = arrayList;
                                    g81Var2 = g81Var;
                                    if (!arrayList14.isEmpty()) {
                                        hw0VarL = hw0.l(arrayList14);
                                    }
                                    hw0 hw0Var4 = hw0VarL;
                                    if (g81Var2.b == null) {
                                        z3 = true;
                                    } else {
                                        z3 = true;
                                    }
                                    x91.s(z3);
                                    if (g81Var2.a != null) {
                                        h81Var = new h81(g81Var2);
                                    } else {
                                        h81Var = null;
                                    }
                                    arrayList15.add(new p81("", new f81(d81Var), new k81(uri6, null, h81Var, null, list6, null, hw0Var4, -9223372036854775807L), new j81(i81Var), x81Var, l81Var3));
                                    if (strArr4 != null) {
                                        str13 = null;
                                    } else {
                                        str13 = null;
                                    }
                                    arrayList318.add(str13);
                                    arrayList13.add(E1(strArrL5, i4));
                                    String[] strArr18 = strArrL6;
                                    arrayList16 = arrayList10;
                                    arrayList16.add(E1(strArr18, i4));
                                    strArrL1 = strArr2;
                                    if (strArr2 != null) {
                                        str14 = null;
                                    } else {
                                        str14 = null;
                                    }
                                    arrayList17 = arrayList9;
                                    arrayList17.add(str14);
                                    strArr6 = strArrL7;
                                    if (strArr6 != null) {
                                        str15 = null;
                                    } else {
                                        str15 = null;
                                    }
                                    arrayList18 = arrayList3;
                                    arrayList18.add(str15);
                                    strArr7 = strArrL8;
                                    if (strArr7 != null) {
                                        str16 = null;
                                    } else {
                                        str16 = null;
                                    }
                                    arrayList19 = arrayList2;
                                    arrayList19.add(str16);
                                    arrayList20 = arrayList318;
                                    strArr8 = strArr18;
                                    bundle2 = bundle9;
                                    arrayList21 = arrayList319;
                                    arrayList21.add(U1(bundle2, "video_list.quality_levels." + i4, "video_list.quality_urls." + i4));
                                }
                                i4++;
                                String str28 = str;
                                arrayList7 = arrayList13;
                                str = str28;
                                strArrL7 = strArr6;
                                strArrL8 = strArr7;
                                strArrL5 = strArrL5;
                                arrayList2 = arrayList19;
                                data = uri7;
                                strArrL3 = strArr5;
                                arrayList11 = arrayList20;
                                strArrL6 = strArr8;
                                str2 = str2;
                                arrayList8 = arrayList15;
                                strArrL4 = strArr4;
                                arrayList3 = arrayList18;
                                extras = bundle2;
                                type = str12;
                                parcelableArr = parcelableArr;
                                parcelableArr2 = parcelableArr2;
                                arrayList = arrayList21;
                                arrayList10 = arrayList16;
                                arrayList9 = arrayList17;
                                length = i7;
                                strArrL2 = strArrL2;
                            }
                            str3 = type;
                            bundle = extras;
                            str4 = str;
                            str5 = str2;
                            ArrayList arrayList41 = arrayList8;
                            uri = data;
                            playerActivity.E3 = new long[arrayList41.size()];
                            i5 = 0;
                            while (true) {
                                jArr = playerActivity.E3;
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
                            bundle = extras;
                            str4 = "subs.enable";
                            str5 = "subs";
                            str6 = "return_result";
                            str7 = "position";
                        }
                    } else {
                        uri = data;
                        str3 = type;
                        bundle = extras;
                        str4 = "subs.enable";
                        str5 = "subs";
                        str6 = "return_result";
                        str7 = "position";
                    }
                }
                playerActivity.H.x(playerActivity, uri, str3);
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
                            Uri uri13 = (Uri) parcelableArray2[i];
                            if (stringArray != null) {
                                str9 = null;
                            } else {
                                str9 = null;
                            }
                            playerActivity.L4.add(zi0.b(playerActivity, uri13, str9, uri13.equals(uri2), null, null));
                        }
                    }
                }
                if (playerActivity.L4.isEmpty()) {
                    playerActivity.H2();
                }
                if (bundle != null) {
                    playerActivity.M4 = bundle.getBoolean(str6);
                    str8 = str7;
                    if (bundle.containsKey(str8)) {
                        playerActivity.H.z(bundle.getInt(str8));
                    }
                }
            }
        }
        K6 = z;
        playerActivity.S(false, false);
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
    public final boolean Q2() {
        xp2 xp2VarE;
        ArrayList arrayListC0;
        fw0 fw0VarN;
        dp2 dp2Var;
        int i;
        boolean z;
        wp2 wp2Var;
        int i2;
        rl0 rl0VarA;
        boolean zH1;
        String strJ4;
        String str;
        int iIndexOf;
        boolean z2;
        if (g6 != null && this.J4 == null && this.H.n == null && !T0(3) && !this.G4) {
            mg0 mg0Var = g6;
            if (mg0Var == null) {
                xp2VarE = g6.E();
                if (!p1()) {
                    arrayListC0 = gt2.c0(this.H.U);
                    if (!arrayListC0.isEmpty()) {
                        int size = arrayListC0.size();
                        fw0VarN = xp2VarE.a.listIterator(0);
                        dp2Var = null;
                        i = 0;
                        z = true;
                        while (fw0VarN.hasNext()) {
                            wp2Var = (wp2) fw0VarN.next();
                            if (wp2Var.b.c != 3) {
                                while (i2 < wp2Var.a) {
                                    rl0VarA = wp2Var.a(i2);
                                    if (wp2Var.c(i2, false)) {
                                        zH1 = h1(rl0VarA);
                                        strJ4 = rl0VarA.b;
                                        if (!zH1) {
                                            str = rl0VarA.d;
                                            fp2 fp2Var = fp2.f;
                                            if (x91.D(str) == null) {
                                                if (strJ4 != null) {
                                                    strJ4 = j4(rl0VarA);
                                                } else {
                                                    strJ4 = j4(rl0VarA);
                                                }
                                                iIndexOf = arrayListC0.indexOf(gt2.I(strJ4, arrayListC0));
                                                if (iIndexOf >= 0) {
                                                    if ((rl0VarA.e & 2) != 0) {
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
                        if (dp2Var != null) {
                            w(dp2Var, i);
                            return true;
                        }
                    }
                }
            } else if (!mg0Var.A0().I.contains(3)) {
                int iE4 = e4(1);
                q50 q50Var = this.p;
                if (q50Var == null || iE4 < 0 || !q50Var.f.G0.get(iE4)) {
                    xp2VarE = g6.E();
                    if (!p1()) {
                        arrayListC0 = gt2.c0(this.H.U);
                        if (!arrayListC0.isEmpty()) {
                            int size2 = arrayListC0.size();
                            fw0VarN = xp2VarE.a.listIterator(0);
                            dp2Var = null;
                            i = 0;
                            z = true;
                            while (fw0VarN.hasNext()) {
                                wp2Var = (wp2) fw0VarN.next();
                                if (wp2Var.b.c != 3) {
                                    for (i2 = 0; i2 < wp2Var.a; i2++) {
                                        rl0VarA = wp2Var.a(i2);
                                        if (wp2Var.c(i2, false)) {
                                            zH1 = h1(rl0VarA);
                                            strJ4 = rl0VarA.b;
                                            if (!zH1) {
                                                str = rl0VarA.d;
                                                fp2 fp2Var2 = fp2.f;
                                                if (x91.D(str) == null) {
                                                    if (strJ4 != null || !strJ4.matches("[a-z]{3}\\d{1,2}")) {
                                                        strJ4 = j4(rl0VarA);
                                                    }
                                                    iIndexOf = arrayListC0.indexOf(gt2.I(strJ4, arrayListC0));
                                                    if (iIndexOf >= 0) {
                                                        if ((rl0VarA.e & 2) != 0) {
                                                            z2 = true;
                                                        } else {
                                                            z2 = false;
                                                        }
                                                        if ((!z && !z2) || (z == z2 && iIndexOf < size2)) {
                                                            dp2Var = wp2Var.b;
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
                            if (dp2Var != null) {
                                w(dp2Var, i);
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public final int Q4(int i) {
        k81 k81Var;
        ks1 ks1Var = this.k3;
        if (ks1Var == null || i < 0 || i >= ks1Var.f.size()) {
            return -1;
        }
        ArrayList arrayList = this.A3;
        if (i < arrayList.size() && (k81Var = ((p81) arrayList.get(i)).b) != null) {
            return ((gs1) this.k3.f.get(i)).a(k81Var.a.toString());
        }
        return -1;
    }

    public final boolean R() {
        Boolean bool = this.k4;
        return bool != null ? bool.booleanValue() : this.H.L;
    }

    public final int R0(rl0 rl0Var, String str) {
        int i;
        int i2;
        int i3;
        String str2;
        int i4 = 0;
        if (rl0Var != null) {
            float f = rl0Var.B;
            int i5 = rl0Var.x;
            int i7 = rl0Var.w;
            String str3 = rl0Var.p;
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
                                gt2.K("decoder query failed: " + e);
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
                    gt2.K(sb2.toString());
                    return i3;
                } catch (Exception e2) {
                    e = e2;
                    i = i4;
                }
            }
        }
        return 0;
    }

    public final void R3(ImageButton imageButton, int i, boolean z) {
        as2 as2Var = this.V1;
        int iB = as2Var.z() ? 0 : as2Var.b(6.0f);
        int i2 = (i - this.V1.i()) / 2;
        imageButton.setScaleType(ImageView.ScaleType.FIT_CENTER);
        imageButton.setImageTintList(this.H0.p);
        imageButton.setBackground(new InsetDrawable((Drawable) gt2.b0(this.H0.c, 10000.0f), iB));
        imageButton.setPadding(i2, i2, i2, i2);
        imageButton.setForeground(gt2.j(this, iB, iB, this.H0.j));
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(i, i);
        layoutParams.gravity = 16;
        if (z) {
            as2 as2Var2 = this.V1;
            layoutParams.setMarginStart(as2Var2.z() ? as2Var2.a(8.0f) : 0);
        }
        imageButton.setLayoutParams(layoutParams);
    }

    public final int R4(gs1 gs1Var, String str) {
        int i = 0;
        while (true) {
            List list = gs1Var.t;
            if (i >= list.size()) {
                int i2 = -1;
                for (int i3 = 0; i3 < list.size(); i3++) {
                    String str2 = ((js1) list.get(i3)).a;
                    if (i4().h(str2, str)) {
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
            if (((js1) list.get(i)).a.equals(str)) {
                return i;
            }
            i++;
        }
    }

    /* JADX WARN: Code duplicated, block: B:28:0x0054  */
    public final void S(boolean z, boolean z2) {
        zn2 zn2Var;
        JSONObject jSONObjectU2;
        JSONObject jSONObjectPut;
        if (this.W4 || (zn2Var = this.V4) == null || !zn2Var.j()) {
            return;
        }
        JSONObject jSONObject = this.V4.G;
        JSONObject jSONObjectPut2 = null;
        if ((jSONObject == null ? null : jSONObject.optString("uri", null)) == null) {
            return;
        }
        Uri uriF0 = f0();
        if (uriF0 != null) {
            JSONObject jSONObject2 = this.V4.G;
            if ((jSONObject2 == null ? null : jSONObject2.optString("uri", null)).equals(uriF0.toString())) {
                return;
            }
        }
        if (!z) {
            jSONObjectU2 = null;
        } else if (zn2.S.equals(this.V4.H)) {
            jSONObjectU2 = U2();
        } else {
            jSONObjectU2 = null;
        }
        if (jSONObjectU2 == null) {
            if (!z || !z2) {
                this.V4.k();
                return;
            }
            zn2 zn2Var2 = this.V4;
            JSONObject jSONObjectU3 = U2();
            if (jSONObjectU3 != null) {
                zn2Var2.G = jSONObjectU3;
            }
            zn2Var2.o(false);
            return;
        }
        zn2 zn2Var3 = this.V4;
        String str = zn2.S;
        if (zn2Var3.C == null || !str.equals(zn2Var3.H)) {
            return;
        }
        zn2Var3.G = jSONObjectU2;
        cz1 cz1Var = zn2Var3.C;
        String strOptString = jSONObjectU2.optString("uri", null);
        JSONObject jSONObjectY2 = zn2Var3.l.l.y2();
        String str2 = "";
        String strOptString2 = jSONObjectY2 == null ? "" : jSONObjectY2.optString("title", "");
        JSONObject jSONObjectC = x91.c("url", str);
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
        cz1Var.d(jSONObjectPut);
        cz1 cz1Var2 = zn2Var3.C;
        JSONObject jSONObjectC2 = x91.c("jsess", str);
        if (jSONObjectC2 != null) {
            try {
                jSONObjectPut2 = jSONObjectC2.put("ses", jSONObjectU2);
            } catch (Exception unused2) {
            }
        }
        cz1Var2.d(jSONObjectPut2);
        zn2Var3.o(true);
    }

    public final int S2(ArrayList arrayList) {
        String str;
        if (this.d4 != 3) {
            for (int i = 0; i < arrayList.size(); i++) {
                yq1 yq1Var = (yq1) arrayList.get(i);
                byte b = yq1Var.d;
                if ((b == 2 && this.d4 == 2 && yq1Var.e == this.e4 && yq1Var.f == this.f4) || ((b == 0 || b == 1) && b == this.d4)) {
                    return i;
                }
            }
        }
        Uri uriF0 = f0();
        if (uriF0 != null) {
            for (int i2 = 0; i2 < arrayList.size(); i2++) {
                yq1 yq1Var2 = (yq1) arrayList.get(i2);
                if (yq1Var2.d == 3 && (str = yq1Var2.h) != null && str.equals(uriF0.toString())) {
                    return i2;
                }
            }
        }
        return 0;
    }

    public final void T(rn2 rn2Var, ArrayList arrayList, int i, Runnable runnable) {
        ArrayList arrayList2 = new ArrayList();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            qn2 qn2Var = (qn2) it.next();
            if (qn2Var.a == i) {
                String string = getString(R.string.subtitle_search_episode, Integer.valueOf(qn2Var.b));
                String str = qn2Var.d;
                String str2 = qn2Var.c;
                String str3 = str2 != null ? str2 : string;
                if (str2 == null) {
                    string = null;
                }
                arrayList2.add(new l70(0, str, str3, string, false, new rc1(this, rn2Var, i, qn2Var, arrayList)));
            }
        }
        if (arrayList2.isEmpty()) {
            l(rn2Var, i, -1, arrayList);
        } else {
            arrayList2.add(0, new l70(R.drawable.ic_keyboard_24dp, null, getString(R.string.subtitle_search_type), null, false, new hk(this, rn2Var, arrayList, new rc1(this, rn2Var, arrayList, i, runnable), (byte) 10)));
            r2.p(this, this.V1, new np1(this, (byte) 8), rn2Var.c, arrayList2, 72, 41, runnable);
        }
    }

    public final void T2() {
        Intent intent = getIntent();
        Pattern pattern = ks1.j;
        Bundle extras = intent == null ? null : intent.getExtras();
        Bundle bundle = extras == null ? null : extras.getBundle("playlist");
        Object obj = bundle == null ? null : bundle.get("result_callback");
        PendingIntent pendingIntent = obj instanceof PendingIntent ? (PendingIntent) obj : null;
        if (pendingIntent == null || this.l3 == null) {
            return;
        }
        try {
            pendingIntent.send(this, 0, new Intent().putExtras(c()));
        } catch (PendingIntent.CanceledException unused) {
            gt2.K("result callback cancelled");
        }
    }

    public final void U(Uri uri) {
        ty tyVar;
        this.A4 = this.H.c;
        Y2(uri);
        if (uri == null || !L2() || (tyVar = this.B) == null) {
            return;
        }
        r2.D(tyVar, getString(R.string.subtitle_secondary_peek_hint), R.drawable.ic_subtitle_secondary_24dp, 3000L);
    }

    public final JSONObject U2() {
        mg0 mg0Var;
        Uri uriF0 = f0();
        if (uriF0 == null || !gt2.E(uriF0)) {
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
                if (extras != null && ks1.b(intent)) {
                    mg0 mg0Var2 = g6;
                    int iV = mg0Var2 == null ? -1 : mg0Var2.V();
                    Bundle bundle = new Bundle(extras);
                    Bundle bundle2 = new Bundle(extras.getBundle("playlist"));
                    bundle2.remove("result_callback");
                    if (iV >= 0) {
                        bundle2.putInt("start_index", iV);
                    }
                    bundle.putBundle("playlist", bundle2);
                    jSONObjectPut.put("extras", tm1.Q(0, bundle));
                    return jSONObjectPut;
                }
                if (extras != null) {
                    Bundle bundle3 = new Bundle(extras);
                    bundle3.remove("return_result");
                    if (bundle3.containsKey("position") && (mg0Var = g6) != null && mg0Var.x()) {
                        bundle3.putInt("position", (int) Math.max(0L, g6.O0()));
                    }
                    jSONObjectPut.put("extras", tm1.Q(0, bundle3));
                }
            }
            return jSONObjectPut;
        } catch (Exception unused) {
            return null;
        }
    }

    public final ArrayList U3(List list) {
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            is1 is1Var = (is1) it.next();
            PlayerActivity playerActivity = this;
            arrayList.add(zi0.b(playerActivity, is1Var.a, is1Var.d, is1Var.e, is1Var.b, is1Var.c));
            this = playerActivity;
        }
        return arrayList;
    }

    public final void V() {
        X0();
        this.d2 = -3001L;
        vt1 vt1Var = this.H;
        if (vt1Var != null) {
            gt2.a0(this, vt1Var.R0);
        }
        z4();
    }

    public final void V0() {
        if (this.c6 == 4) {
            return;
        }
        W0();
    }

    public final void V1() {
        ArrayList arrayList;
        sx0 sx0Var = this.b5;
        if (sx0Var == null) {
            return;
        }
        sx0Var.l = this.i4;
        double dC0 = c0();
        wd2 wd2Var = (wd2) sx0Var.m;
        List<vd2> listB = wd2Var != null ? wd2Var.b(dC0) : Collections.EMPTY_LIST;
        double d = 0.0d;
        if (sx0Var.l != 0.0d) {
            boolean z = dC0 > 0.0d && !Double.isNaN(dC0);
            ArrayList arrayList2 = new ArrayList(listB.size());
            for (vd2 vd2Var : listB) {
                double d2 = vd2Var.a;
                double d3 = sx0Var.l;
                double d4 = d2 + d3;
                double d5 = d;
                double d6 = vd2Var.b + d3;
                double d7 = d4 < d5 ? d5 : d4;
                double d8 = (!z || d6 <= dC0) ? d6 : dC0;
                if (d8 > d7) {
                    vd2 vd2Var2 = new vd2(d7, d8, vd2Var.c, vd2Var.d, vd2Var.e, vd2Var.f);
                    vd2Var2.h = vd2Var.h;
                    arrayList2.add(vd2Var2);
                }
                d = d5;
            }
            listB = arrayList2;
        }
        double d9 = d;
        if (dC0 <= d9) {
            arrayList = new ArrayList(listB.size());
            for (vd2 vd2Var3 : listB) {
                double d10 = vd2Var3.a;
                double d11 = vd2Var3.b;
                if (!Double.isNaN(d10) && !Double.isInfinite(vd2Var3.a) && !Double.isNaN(d11) && d11 < 86400.0d) {
                    arrayList.add(vd2Var3);
                }
            }
        } else {
            double d12 = 0.3333333333333333d * dC0;
            ArrayList arrayList3 = new ArrayList(listB.size());
            for (vd2 vd2Var4 : listB) {
                double dMin = Math.min(vd2Var4.b, dC0);
                double d13 = vd2Var4.a;
                if (dMin > d13 && dMin - d13 <= d12) {
                    if (dMin == vd2Var4.b) {
                        arrayList3.add(vd2Var4);
                    } else {
                        vd2 vd2Var5 = new vd2(d13, dMin, vd2Var4.c, vd2Var4.d, vd2Var4.e, vd2Var4.f);
                        vd2Var5.h = vd2Var4.h;
                        arrayList3.add(vd2Var5);
                        dC0 = dC0;
                    }
                }
            }
            arrayList = arrayList3;
        }
        double d14 = dC0;
        sx0Var.n = arrayList;
        boolean z2 = dC0 > d9 && !Double.isNaN(d14);
        for (vd2 vd2Var6 : sx0Var.n) {
            vd2Var6.i = z2 && vd2Var6.c == 1 && vd2Var6.b >= 0.75d * d14;
            vd2Var6.j = z2 && vd2Var6.b >= d14 - 1.5d;
        }
        if (this.x2 != null) {
            sx0 sx0Var2 = this.b5;
            List list = sx0Var2 != null ? sx0Var2.n : null;
            mg0 mg0Var = g6;
            long duration = mg0Var != null ? mg0Var.getDuration() : -9223372036854775807L;
            if (list == null || list.isEmpty() || duration == -9223372036854775807L || duration <= 0 || !this.H.k0) {
                this.x2.h();
            } else {
                int size = list.size();
                long[] jArr = new long[size];
                long[] jArr2 = new long[size];
                int[] iArr = new int[size];
                int iN = lj.n(this, R.attr.accentSkip, getColor(R.color.skip_fill));
                int color = getColor(R.color.ad_fill);
                for (int i = 0; i < size; i++) {
                    vd2 vd2Var7 = (vd2) list.get(i);
                    boolean z3 = vd2Var7.c == 2;
                    jArr[i] = Math.round(vd2Var7.a * 1000.0d);
                    jArr2[i] = vd2Var7.a();
                    iArr[i] = z3 ? color : iN;
                }
                CustomDefaultTimeBar customDefaultTimeBar = this.x2;
                customDefaultTimeBar.v0 = jArr;
                customDefaultTimeBar.w0 = jArr2;
                customDefaultTimeBar.x0 = iArr;
                customDefaultTimeBar.y0 = duration;
                customDefaultTimeBar.invalidate();
            }
        }
        if (this.b5.n.isEmpty()) {
            return;
        }
        this.n4 = true;
    }

    public final void V2(boolean z) {
        this.q2 = z;
        q4();
    }

    public final String V3(Uri uri) {
        String strM1 = m1(ys2.Z(zi0.o(uri)));
        if (strM1 == null) {
            strM1 = gt2.w(this, uri);
        }
        String path = uri.getPath();
        return (path == null || !path.contains(".auto.")) ? strM1 : getString(R.string.subtitle_machine_translated, strM1);
    }

    public final void W() {
        this.J4 = null;
        this.H4 = null;
        this.I4 = null;
        vj2 vj2Var = this.F4;
        if (vj2Var != null) {
            vj2Var.g(null);
        }
    }

    public final void W0() {
        ty tyVar;
        ValueAnimator valueAnimator = this.j5;
        if (valueAnimator != null) {
            valueAnimator.cancel();
            this.j5 = null;
        }
        this.i5 = 1.0f;
        this.h5 = null;
        byte b = 0;
        this.d6 = 0;
        this.c6 = 1;
        this.C5 = -9223372036854775807L;
        this.s5 = null;
        ty tyVar2 = this.B;
        if (tyVar2 != null) {
            tyVar2.removeCallbacks(this.r5);
        }
        Button button = this.d5;
        if (button != null) {
            if (L6 && button.hasFocus() && (tyVar = this.B) != null) {
                tyVar.requestFocus();
            }
            this.d5.animate().cancel();
            this.d5.animate().alpha(0.0f).setDuration(250L).setInterpolator(J6).withEndAction(new np1(this, b)).start();
            I4();
        }
    }

    public final boolean W1(wf0 wf0Var, rl0 rl0Var) {
        if (g6 == null || l6 || rl0Var == null || !"video/dolby-vision".equals(rl0Var.p) || !"video/hevc".equals(i71.c(rl0Var, true))) {
            return false;
        }
        if (!n6) {
            n6 = true;
            w3.b().p(wf0Var, new gk((Object) this, (Object) wf0Var, (Object) rl0Var, (byte) 8));
        }
        gt2.K("rebuild: Dolby Vision " + rl0Var.l + " as HEVC");
        l6 = true;
        m6 = true;
        boolean zW = g6.w();
        this.z2 = zW;
        this.K4 = !zW;
        this.B.post(new to1(this, (byte) 14));
        return true;
    }

    public final void W3(int i) {
        ty tyVar = this.B;
        if (tyVar == null) {
            return;
        }
        if (i != 0) {
            r2.D(tyVar, getString(i), R.drawable.ic_subtitles_24dp, 90000L);
        } else {
            tyVar.removeCallbacks(tyVar.C0);
            this.B.C0.run();
        }
    }

    public final void X0() {
        if (this.c2 == null) {
            return;
        }
        ty tyVar = this.B;
        if (tyVar != null) {
            tyVar.removeCallbacks(this.P5);
        }
        this.c2.setVisibility(8);
    }

    public final boolean X1() {
        LinkedHashMap linkedHashMapH0;
        byte b = 0;
        if (g6 != null && !this.V && (linkedHashMapH0 = h0()) != null && !linkedHashMapH0.isEmpty()) {
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
                gt2.K("quality lowered to " + str2);
                this.B.post(new hp1(this, str2, str, b));
                return true;
            }
        }
        return false;
    }

    public final void X2(double d) {
        float fMax = (float) Math.max(0.0d, Math.min(1.0d, d));
        ty tyVar = this.B;
        if (tyVar != null) {
            tyVar.removeCallbacks(this.L5);
        }
        ValueAnimator valueAnimator = this.j5;
        if (valueAnimator != null) {
            valueAnimator.cancel();
            this.j5 = null;
        }
        float f = this.i5;
        if (fMax >= f) {
            o(fMax);
            return;
        }
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(f, fMax);
        this.j5 = valueAnimatorOfFloat;
        valueAnimatorOfFloat.setDuration(250L);
        this.j5.setInterpolator(new LinearInterpolator());
        this.j5.addUpdateListener(new tg(this, (byte) 9));
        this.j5.start();
    }

    public final fk2 X3(al alVar, List list, String str, int i, int i2, Uri uri, final long j, final uf1 uf1Var, final AtomicBoolean atomicBoolean, boolean z, boolean z2) {
        final al alVar2 = alVar;
        String str2 = (String) list.get(0);
        List<String> listL4 = l4(str2);
        String str3 = listL4.isEmpty() ? null : str2;
        final ArrayList arrayList = new ArrayList(list);
        for (String str4 : listL4) {
            if (!arrayList.contains(str4)) {
                arrayList.add(str4);
            }
        }
        vt1 vt1Var = this.H;
        jp1 jp1Var = new jp1(this, i, str, j, str3, listL4, z2, i2, uri, z);
        wk1 wk1Var = gk2.a;
        String str5 = (String) alVar2.o;
        String str6 = (String) alVar2.n;
        int i3 = alVar2.m;
        if (alVar2.m() || arrayList.isEmpty()) {
            StringBuilder sb = new StringBuilder("subtitles: nothing to ask with (id=");
            sb.append(alVar2.m() ? "empty" : "ok");
            sb.append(", want=");
            sb.append(arrayList);
            sb.append(")");
            gt2.K(sb.toString());
            return null;
        }
        byte b = 1;
        if (!alVar2.n() && i3 < 1) {
            gt2.K("subtitles: no episode number, not searching");
            return null;
        }
        if (str6 == null || str5 == null) {
            vt1Var.getClass();
            int i4 = alVar2.l;
            try {
                if (str6 == null && str5 != null) {
                    String strN = w92.N(Long.parseLong(str5), alVar2.n());
                    if (strN != null) {
                        alVar2 = new al(i4, i3, strN, str5);
                    }
                } else if (str5 == null && str6 != null) {
                    long jO = w92.O(str6.startsWith("tt") ? str6 : "tt" + str6, alVar2.n());
                    if (jO >= 0) {
                        alVar2 = new al(i4, i3, str6, String.valueOf(jO));
                    }
                }
            } catch (Exception e) {
                gt2.K("subtitles: id lookup " + e);
            }
        }
        String str7 = (String) alVar2.o;
        String str8 = (String) alVar2.n;
        if (str8 != null && str7 != null) {
            ConcurrentHashMap concurrentHashMap = gk2.c;
            concurrentHashMap.put(str8, str7);
            concurrentHashMap.put(str7, str8);
        }
        if (gk2.b()) {
            return null;
        }
        gt2.K("subtitles: " + str8 + " / " + str7 + " s" + alVar2.l + "e" + alVar2.m + " want=" + arrayList + " media=" + j + "ms");
        final boolean z3 = vt1Var.Z;
        ArrayList arrayList2 = new ArrayList(4);
        arrayList2.add(new Callable() { // from class: bk2
            /* JADX WARN: Code duplicated, block: B:84:0x01fc  */
            /* JADX WARN: Code duplicated, block: B:85:0x01fe  */
            @Override // java.util.concurrent.Callable
            public final Object call() {
                List list2;
                boolean z4;
                boolean z5;
                ArrayList arrayList3 = arrayList;
                ArrayList arrayListB = ol1.b(arrayList3);
                if (arrayListB.isEmpty()) {
                    gt2.K("subtitles: openSubtitles has no 639-1 code for " + arrayList3);
                    return null;
                }
                al alVar3 = alVar2;
                boolean zM = alVar3.m();
                uf1 uf1Var2 = uf1Var;
                if ((zM && uf1Var2 == null) || arrayListB.isEmpty()) {
                    list2 = Collections.EMPTY_LIST;
                } else {
                    TreeMap treeMap = new TreeMap();
                    treeMap.put("languages", TextUtils.join(",", arrayListB));
                    if (!zM) {
                        if (alVar3.l() != null) {
                            treeMap.put("imdb_id", alVar3.l());
                        } else {
                            treeMap.put("tmdb_id", (String) alVar3.o);
                        }
                        if (!alVar3.n()) {
                            treeMap.put("season_number", String.valueOf(alVar3.l));
                            int i5 = alVar3.m;
                            if (i5 > 0) {
                                treeMap.put("episode_number", String.valueOf(i5));
                            }
                        }
                    }
                    if (uf1Var2 != null) {
                        treeMap.put("moviehash", uf1Var2.a);
                        treeMap.put("moviebytesize", String.valueOf(uf1Var2.b));
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
                    mk1 mk1Var = new mk1();
                    mk1Var.e(string);
                    mk1Var.b("Api-Key", "IxrxupVBKx7dhBkAAtW7QbwnhDMgOdEO");
                    mk1Var.b("User-Agent", "JustPlayer v2.1.1");
                    mk1Var.b("Accept", "application/json");
                    String strA = ol1.a(new vu1(mk1Var));
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
                                                arrayList4.add(new nl1(strOptString, jOptLong, iOptInt, z8, jSONObjectOptJSONObject.optBoolean("moviehash_match")));
                                            }
                                        }
                                    }
                                }
                                list2 = arrayList4;
                            }
                        } catch (Exception e2) {
                            gt2.K("OpenSubtitles: " + e2);
                            list2 = Collections.EMPTY_LIST;
                        }
                    }
                }
                Iterator it = list2.iterator();
                nl1 nl1Var = null;
                while (true) {
                    boolean zHasNext = it.hasNext();
                    z4 = z3;
                    if (!zHasNext) {
                        break;
                    }
                    nl1 nl1Var2 = (nl1) it.next();
                    String str9 = nl1Var2.a;
                    boolean z9 = nl1Var2.d;
                    if (str9 != null && (!z9 || z4)) {
                        Locale locale = Locale.US;
                        if (arrayListB.indexOf(str9.toLowerCase(locale)) >= 0) {
                            if (nl1Var != null) {
                                int iIndexOf = arrayListB.indexOf(nl1Var2.a.toLowerCase(locale));
                                int iIndexOf2 = arrayListB.indexOf(nl1Var.a.toLowerCase(locale));
                                if (iIndexOf == iIndexOf2) {
                                    z5 = nl1Var2.e;
                                    if (z5 == nl1Var.e && z9 == (z5 = nl1Var.d)) {
                                        if (nl1Var2.c > nl1Var.c) {
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
                            nl1Var = nl1Var2;
                        }
                    }
                }
                if (nl1Var != null) {
                    String str10 = nl1Var.a;
                    if (!gk2.b()) {
                        StringBuilder sb3 = new StringBuilder("subtitles: openSubtitles has 1 ");
                        sb3.append(str10);
                        sb3.append(nl1Var.d ? " machine-translated" : "");
                        sb3.append(nl1Var.e ? " matching this file" : "");
                        sb3.append(" of ");
                        sb3.append(list2.size());
                        gt2.K(sb3.toString());
                        fp2 fp2Var = fp2.f;
                        return new fk2("openSubtitles", x91.D(str10), Collections.EMPTY_LIST, nl1Var.d, nl1Var.b);
                    }
                }
                StringBuilder sb4 = new StringBuilder("subtitles: openSubtitles has ");
                sb4.append(list2.size());
                sb4.append(" file(s), none taken");
                sb4.append(z4 ? "" : " (machine translations refused)");
                gt2.K(sb4.toString());
                return null;
            }
        });
        final al alVar3 = alVar2;
        arrayList2.add(new Callable() { // from class: ck2
            @Override // java.util.concurrent.Callable
            public final Object call() {
                List list2;
                boolean zEquals;
                int i5;
                AtomicBoolean atomicBoolean2 = atomicBoolean;
                al alVar4 = alVar3;
                String str9 = (String) alVar4.n;
                int i7 = alVar4.m;
                ArrayList<String> arrayList3 = arrayList;
                if (str9 == null) {
                    gt2.K("subtitles: restOpenSubtitles needs an imdb id, and there is none");
                    list2 = Collections.EMPTY_LIST;
                } else {
                    String strL = alVar4.l();
                    for (String str10 : arrayList3) {
                        if (gk2.b()) {
                            break;
                        }
                        StringBuilder sb2 = new StringBuilder("https://rest.opensubtitles.org/search/");
                        if (!alVar4.n() && i7 > 0) {
                            sb2.append("episode-");
                            sb2.append(i7);
                            sb2.append('/');
                        }
                        sb2.append("imdbid-");
                        sb2.append(strL);
                        sb2.append('/');
                        if (!alVar4.n()) {
                            sb2.append("season-");
                            sb2.append(alVar4.l);
                            sb2.append('/');
                        }
                        sb2.append("sublanguageid-");
                        String str11 = (String) gk2.d.get(str10);
                        if (str11 != null) {
                            str10 = str11;
                        }
                        sb2.append(str10);
                        ArrayList arrayList4 = new ArrayList();
                        try {
                            String strD = gk2.d(sb2.toString(), atomicBoolean2);
                            if (strD != null) {
                                JSONArray jSONArray = new JSONArray(strD);
                                for (int i8 = 0; i8 < jSONArray.length(); i8++) {
                                    JSONObject jSONObject = jSONArray.getJSONObject(i8);
                                    String strOptString = jSONObject.optString("SubDownloadLink", null);
                                    String strOptString2 = jSONObject.optString("SubLanguageID");
                                    String[] strArr = gt2.a;
                                    fp2 fp2Var = fp2.f;
                                    String strD2 = x91.D(strOptString2);
                                    if (strOptString != null && strD2 != null && !"1".equals(jSONObject.optString("SubForeignPartsOnly"))) {
                                        String strOptString3 = jSONObject.optString("SubFileName");
                                        if (!(strOptString3 != null && gk2.e.matcher(strOptString3).find()) && (!(zEquals = "1".equals(jSONObject.optString("SubAutoTranslation"))) || z3)) {
                                            try {
                                                i5 = Integer.parseInt(jSONObject.optString("SubDownloadsCnt").trim());
                                            } catch (Exception unused) {
                                                i5 = 0;
                                            }
                                            String strOptString4 = jSONObject.optString("SubLastTS");
                                            long j2 = 0;
                                            if (strOptString4 != null) {
                                                Matcher matcher = gk2.b.matcher(strOptString4);
                                                if (matcher.matches()) {
                                                    j2 = 1000 * (Long.parseLong(matcher.group(3)) + (Long.parseLong(matcher.group(2)) * 60) + (Long.parseLong(matcher.group(1)) * 3600));
                                                }
                                            }
                                            arrayList4.add(new ek2(strD2, i5, strOptString, zEquals, j2));
                                        }
                                    }
                                }
                                if (!arrayList4.isEmpty()) {
                                    list2 = arrayList4;
                                }
                            }
                        } catch (Exception e2) {
                            gt2.K("rest.opensubtitles.org: " + e2);
                        }
                    }
                    list2 = Collections.EMPTY_LIST;
                }
                return gk2.a(j, "restOpenSubtitles", arrayList3, list2);
            }
        });
        final byte b2 = 0;
        arrayList2.add(new Callable() { // from class: dk2
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
                al alVar4 = alVar3;
                switch (b3) {
                    case DecoderInputBuffer.BUFFER_REPLACEMENT_MODE_DISABLED /* 0 */:
                        String strE = (String) alVar4.n;
                        if (strE == null) {
                            gt2.K("subtitles: stremio needs an imdb id, and there is none");
                            list2 = Collections.EMPTY_LIST;
                        } else {
                            if (!strE.startsWith("tt")) {
                                strE = we2.e("tt", strE);
                            }
                            if (alVar4.n()) {
                                string = we2.e("movie/", strE);
                            } else {
                                StringBuilder sbK = we2.k("series/", strE, ":");
                                sbK.append(alVar4.l);
                                sbK.append(":");
                                sbK.append(Math.max(alVar4.m, 1));
                                string = sbK.toString();
                            }
                            ArrayList arrayList4 = new ArrayList();
                            try {
                                String strD = gk2.d("https://opensubtitles-v3.strem.io/subtitles/" + string + ".json", atomicBoolean2);
                                JSONArray jSONArrayOptJSONArray = (strD == null ? new JSONObject() : new JSONObject(strD)).optJSONArray("subtitles");
                                if (jSONArrayOptJSONArray != null) {
                                    for (int i7 = 0; i7 < jSONArrayOptJSONArray.length(); i7++) {
                                        JSONObject jSONObject = jSONArrayOptJSONArray.getJSONObject(i7);
                                        String strOptString = jSONObject.optString("lang");
                                        String[] strArr = gt2.a;
                                        fp2 fp2Var = fp2.f;
                                        String strD2 = x91.D(strOptString);
                                        String strOptString2 = jSONObject.optString("url", null);
                                        if (strD2 != null && strOptString2 != null) {
                                            arrayList4.add(new ek2(strD2, 0, strOptString2, false, 0L));
                                        }
                                        break;
                                    }
                                }
                            } catch (Exception e2) {
                                gt2.K("stremio: " + e2);
                            }
                            list2 = arrayList4;
                        }
                        return gk2.a(j2, "stremio", arrayList3, list2);
                    default:
                        String str10 = (String) alVar4.o;
                        int i8 = alVar4.m;
                        if (str10 == null) {
                            gt2.K("subtitles: shegu needs a tmdb id, and there is none");
                            list3 = Collections.EMPTY_LIST;
                        } else {
                            StringBuilder sb2 = new StringBuilder("https://subtitles.shegu.st/subtitles?tmdb=");
                            sb2.append(Uri.encode(str10));
                            sb2.append("&type=");
                            sb2.append(alVar4.n() ? "movie" : "tv");
                            if (!alVar4.n()) {
                                sb2.append("&season=");
                                sb2.append(alVar4.l);
                                if (i8 > 0) {
                                    sb2.append("&episode=");
                                    sb2.append(i8);
                                }
                            }
                            ArrayList arrayList5 = new ArrayList();
                            try {
                                String strD3 = gk2.d(sb2.toString(), atomicBoolean2);
                                JSONArray jSONArrayOptJSONArray2 = (strD3 == null ? new JSONObject() : new JSONObject(strD3)).optJSONArray("subtitles");
                                if (jSONArrayOptJSONArray2 != null) {
                                    for (int i9 = 0; i9 < jSONArrayOptJSONArray2.length(); i9++) {
                                        JSONObject jSONObject2 = jSONArrayOptJSONArray2.getJSONObject(i9);
                                        String strOptString3 = jSONObject2.optString("language");
                                        String[] strArr2 = gt2.a;
                                        fp2 fp2Var2 = fp2.f;
                                        String strD4 = x91.D(strOptString3);
                                        String strOptString4 = jSONObject2.optString("url", null);
                                        if (strD4 != null && strOptString4 != null) {
                                            String strOptString5 = jSONObject2.optString("display");
                                            if (!(strOptString5 != null && gk2.e.matcher(strOptString5).find())) {
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
                                                arrayList5.add(new ek2(strD4, 0, str9 != null ? str9 : strOptString4, false, 0L));
                                            }
                                        }
                                        break;
                                    }
                                }
                            } catch (Exception e3) {
                                gt2.K("shegu.st: " + e3);
                            }
                            list3 = arrayList5;
                        }
                        return gk2.a(j2, "shegu", arrayList3, list3);
                }
            }
        });
        final byte b3 = 1;
        arrayList2.add(new Callable() { // from class: dk2
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
                al alVar4 = alVar3;
                switch (b4) {
                    case DecoderInputBuffer.BUFFER_REPLACEMENT_MODE_DISABLED /* 0 */:
                        String strE = (String) alVar4.n;
                        if (strE == null) {
                            gt2.K("subtitles: stremio needs an imdb id, and there is none");
                            list2 = Collections.EMPTY_LIST;
                        } else {
                            if (!strE.startsWith("tt")) {
                                strE = we2.e("tt", strE);
                            }
                            if (alVar4.n()) {
                                string = we2.e("movie/", strE);
                            } else {
                                StringBuilder sbK = we2.k("series/", strE, ":");
                                sbK.append(alVar4.l);
                                sbK.append(":");
                                sbK.append(Math.max(alVar4.m, 1));
                                string = sbK.toString();
                            }
                            ArrayList arrayList4 = new ArrayList();
                            try {
                                String strD = gk2.d("https://opensubtitles-v3.strem.io/subtitles/" + string + ".json", atomicBoolean2);
                                JSONArray jSONArrayOptJSONArray = (strD == null ? new JSONObject() : new JSONObject(strD)).optJSONArray("subtitles");
                                if (jSONArrayOptJSONArray != null) {
                                    for (int i7 = 0; i7 < jSONArrayOptJSONArray.length(); i7++) {
                                        JSONObject jSONObject = jSONArrayOptJSONArray.getJSONObject(i7);
                                        String strOptString = jSONObject.optString("lang");
                                        String[] strArr = gt2.a;
                                        fp2 fp2Var = fp2.f;
                                        String strD2 = x91.D(strOptString);
                                        String strOptString2 = jSONObject.optString("url", null);
                                        if (strD2 != null && strOptString2 != null) {
                                            arrayList4.add(new ek2(strD2, 0, strOptString2, false, 0L));
                                        }
                                        break;
                                    }
                                }
                            } catch (Exception e2) {
                                gt2.K("stremio: " + e2);
                            }
                            list2 = arrayList4;
                        }
                        return gk2.a(j2, "stremio", arrayList3, list2);
                    default:
                        String str10 = (String) alVar4.o;
                        int i8 = alVar4.m;
                        if (str10 == null) {
                            gt2.K("subtitles: shegu needs a tmdb id, and there is none");
                            list3 = Collections.EMPTY_LIST;
                        } else {
                            StringBuilder sb2 = new StringBuilder("https://subtitles.shegu.st/subtitles?tmdb=");
                            sb2.append(Uri.encode(str10));
                            sb2.append("&type=");
                            sb2.append(alVar4.n() ? "movie" : "tv");
                            if (!alVar4.n()) {
                                sb2.append("&season=");
                                sb2.append(alVar4.l);
                                if (i8 > 0) {
                                    sb2.append("&episode=");
                                    sb2.append(i8);
                                }
                            }
                            ArrayList arrayList5 = new ArrayList();
                            try {
                                String strD3 = gk2.d(sb2.toString(), atomicBoolean2);
                                JSONArray jSONArrayOptJSONArray2 = (strD3 == null ? new JSONObject() : new JSONObject(strD3)).optJSONArray("subtitles");
                                if (jSONArrayOptJSONArray2 != null) {
                                    for (int i9 = 0; i9 < jSONArrayOptJSONArray2.length(); i9++) {
                                        JSONObject jSONObject2 = jSONArrayOptJSONArray2.getJSONObject(i9);
                                        String strOptString3 = jSONObject2.optString("language");
                                        String[] strArr2 = gt2.a;
                                        fp2 fp2Var2 = fp2.f;
                                        String strD4 = x91.D(strOptString3);
                                        String strOptString4 = jSONObject2.optString("url", null);
                                        if (strD4 != null && strOptString4 != null) {
                                            String strOptString5 = jSONObject2.optString("display");
                                            if (!(strOptString5 != null && gk2.e.matcher(strOptString5).find())) {
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
                                                arrayList5.add(new ek2(strD4, 0, str9 != null ? str9 : strOptString4, false, 0L));
                                            }
                                        }
                                        break;
                                    }
                                }
                            } catch (Exception e3) {
                                gt2.K("shegu.st: " + e3);
                            }
                            list3 = arrayList5;
                        }
                        return gk2.a(j2, "shegu", arrayList3, list3);
                }
            }
        });
        if (arrayList2.isEmpty()) {
            return null;
        }
        ExecutorService executorServiceNewFixedThreadPool = Executors.newFixedThreadPool(arrayList2.size(), new az1((byte) 3));
        try {
            ArrayList<Future> arrayList3 = new ArrayList(arrayList2.size());
            Iterator it = arrayList2.iterator();
            while (it.hasNext()) {
                arrayList3.add(executorServiceNewFixedThreadPool.submit((Callable) it.next()));
            }
            long jCurrentTimeMillis = System.currentTimeMillis() + 15000;
            ArrayList<fk2> arrayList4 = new ArrayList(arrayList2.size());
            for (Future future : arrayList3) {
                if (!gk2.b()) {
                    try {
                        fk2 fk2Var = (fk2) future.get(Math.max(jCurrentTimeMillis - System.currentTimeMillis(), 0L), TimeUnit.MILLISECONDS);
                        if (fk2Var != null) {
                            if (!((String) arrayList.get(0)).equals(fk2Var.b)) {
                                arrayList4.add(fk2Var);
                            } else if (gk2.c(fk2Var, jp1Var)) {
                                executorServiceNewFixedThreadPool.shutdownNow();
                                return fk2Var;
                            }
                        }
                    } catch (InterruptedException unused) {
                        Thread.currentThread().interrupt();
                    } catch (Exception e2) {
                        gt2.K("subtitles: source failed " + e2);
                    }
                }
                executorServiceNewFixedThreadPool.shutdownNow();
                return null;
            }
            Collections.sort(arrayList4, new bp1(arrayList, b));
            for (fk2 fk2Var2 : arrayList4) {
                if (gk2.b()) {
                    break;
                }
                if (gk2.c(fk2Var2, jp1Var)) {
                    executorServiceNewFixedThreadPool.shutdownNow();
                    return fk2Var2;
                }
            }
            executorServiceNewFixedThreadPool.shutdownNow();
            return null;
        } catch (Throwable th) {
            executorServiceNewFixedThreadPool.shutdownNow();
            throw th;
        }
    }

    public final TextView Y(int i) {
        TextView textView = new TextView(this);
        textView.setTextColor(this.J0.g);
        textView.setTextSize(2, this.V1.v());
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
        int iB = this.V1.b(L6 ? 12.307693f : 14.0f);
        int iRound = Math.round((iB * i2) / 24.0f);
        drawable.setBounds(-iRound, 0, iB - iRound, iB);
        textView.setCompoundDrawablesRelative(drawable, null, null, null);
        textView.setCompoundDrawableTintList(textView.getTextColors());
        textView.setCompoundDrawablePadding((this.V1.b(8.0f) + Math.round(((i3 - i2) * iB) / 24.0f)) - iB);
    }

    public final boolean Y1(wf0 wf0Var) {
        if (!L6 || o6 || !i6 || g6 == null) {
            return false;
        }
        o6 = true;
        if (l6) {
            m6 = true;
        }
        p6 = !g6.w();
        gt2.K(wf0Var != null ? "restarting the screen: the decoder will not come back on this one" : "restarting the screen: the retained decoder did not come back with the window");
        g2("screen-restarted", wf0Var);
        b2(true);
        this.B.post(new to1(this, (byte) 25));
        return true;
    }

    public final void Y2(Uri uri) {
        if (this.p4 == null) {
            return;
        }
        if (uri == null || !uri.equals(this.t4)) {
            this.H.B(uri);
            Z2(null);
            D1(uri);
            D4();
            C4();
            return;
        }
        vj2 vj2Var = this.r4;
        if (vj2Var != null) {
            vj2Var.g(this.s4);
            this.r4.e(this.q4);
        }
    }

    public final int Y3(int i) {
        rl0 rl0Var;
        mg0 mg0Var = g6;
        if (mg0Var == null) {
            rl0Var = null;
        } else {
            mg0Var.A1();
            rl0Var = mg0Var.V;
        }
        if (rl0Var == null || i != 2) {
            return 0;
        }
        boolean zD = gt2.D(rl0Var);
        int i2 = rl0Var.w;
        int i3 = rl0Var.x;
        Rational rational = zD ? new Rational(i3, i2) : new Rational(i2, i3);
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        if (new Rational(displayMetrics.widthPixels, displayMetrics.heightPixels).floatValue() <= rational.floatValue()) {
            return 0;
        }
        return (displayMetrics.widthPixels - (rational.getNumerator() * (displayMetrics.heightPixels / rational.getDenominator()))) / 2;
    }

    public final TextView Z() {
        TextView textView = new TextView(this);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
        layoutParams.gravity = 8388659;
        layoutParams.setMargins(gt2.p(6), gt2.p(6), 0, 0);
        textView.setLayoutParams(layoutParams);
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(getColor(R.color.badge_scrim));
        gradientDrawable.setCornerRadius(gt2.p(6));
        textView.setBackground(gradientDrawable);
        textView.setGravity(17);
        textView.setMinWidth(gt2.p(18));
        textView.setPadding(gt2.p(5), 0, gt2.p(5), gt2.p(1));
        textView.setTextColor(-1);
        textView.setTextSize(2, this.V1.q(11.0f, 11.0f, 12.0f, 13.0f));
        textView.setTypeface(Typeface.DEFAULT_BOLD);
        textView.setVisibility(8);
        return textView;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:131:0x0337  */
    /* JADX WARN: Code duplicated, block: B:157:0x0376  */
    /* JADX WARN: Code duplicated, block: B:192:0x0469  */
    /* JADX WARN: Code duplicated, block: B:219:0x04bb  */
    /* JADX WARN: Code duplicated, block: B:296:0x06d1  */
    public final void Z0() {
        int i;
        boolean z;
        Uri uri;
        float f;
        x81 x81Var;
        o81 o81VarB;
        long jW2;
        k81 k81Var;
        int i2;
        int i3;
        boolean zExists;
        String str;
        boolean z2;
        vc2 vc2VarR;
        dn dnVar;
        Object obj;
        int iR;
        List listAsList;
        gt2.e0(this);
        vt1 vt1Var = this.H;
        if (vt1Var.P0) {
            vt1Var.o();
        }
        boolean zA = gt2.A(this.H.c);
        vt1 vt1Var2 = this.H;
        i6 = (vt1Var2.c == null || vt1Var2.d) ? false : true;
        if (getIntent() != null && getIntent().getBooleanExtra("join_room", false)) {
            this.Q1 = true;
            getIntent().removeExtra("join_room");
        }
        if (getIntent() != null && getIntent().getStringExtra("join_code") != null) {
            this.Q1 = true;
            this.R1 = getIntent().getStringExtra("join_code");
            this.S1 = getIntent().getStringExtra("join_password");
            getIntent().removeExtra("join_code");
            getIntent().removeExtra("join_password");
        }
        boolean z3 = this.Q1;
        if (z3) {
            i6 = false;
        }
        this.X4 = false;
        boolean z4 = i6 && this.H.c.equals(this.Y);
        this.Y = null;
        boolean z5 = this.K4;
        this.K4 = false;
        this.j0 = true;
        M();
        if (m6) {
            m6 = false;
        } else {
            l6 = this.H.S;
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
        if (A6 == 0) {
            A6 = System.currentTimeMillis();
        }
        B6 = 0L;
        this.e0 = 0;
        this.l0 = -1;
        this.c0 = -9223372036854775807L;
        this.T = null;
        this.U = null;
        this.R5.clear();
        this.W = 0L;
        this.O2 = false;
        this.S2 = false;
        this.W2 = false;
        this.t0 = 0L;
        this.B.removeCallbacks(this.Q);
        this.B.removeCallbacks(this.V2);
        this.T2 = 0;
        this.U2 = false;
        this.Q2 = false;
        this.i2 = false;
        this.r.clear();
        this.t = null;
        this.M5 = null;
        this.N5 = false;
        this.s.clear();
        this.x0 = null;
        mg0 mg0Var = g6;
        if (mg0Var != null) {
            mg0Var.e0(this.l);
            mg0 mg0Var2 = g6;
            mq1 mq1Var = this.X;
            mg0Var2.A1();
            g20 g20Var = mg0Var2.s;
            mq1Var.getClass();
            g20Var.q.e(mq1Var);
            g6.y();
            g6.k1();
            g6 = null;
            this.D = null;
            f6 = null;
            this.l4 = null;
        }
        q50 q50Var = new q50(this, new rq1((byte) 9));
        this.p = q50Var;
        j50 j50VarD = q50Var.d();
        j50VarD.R = true;
        q50Var.o(new k50(j50VarD));
        if (this.H.x) {
            q50 q50Var2 = this.p;
            j50 j50VarD2 = q50Var2.d();
            j50VarD2.P = true;
            q50Var2.o(new k50(j50VarD2));
        }
        r();
        q50 q50Var3 = this.p;
        j50 j50VarD3 = q50Var3.d();
        j50VarD3.C = 1;
        q50Var3.o(new k50(j50VarD3));
        if (this.p != null) {
            ks1 ks1Var = this.k3;
            String[] strArr = ks1Var == null ? null : ks1Var.e.d;
            if (this.G4) {
                listAsList = Collections.EMPTY_LIST;
            } else {
                listAsList = strArr != null ? Arrays.asList(strArr) : gt2.c0(this.H.U);
            }
            if (!listAsList.isEmpty()) {
                q50 q50Var4 = this.p;
                j50 j50VarD4 = q50Var4.d();
                j50VarD4.y = sp2.g((String[]) listAsList.toArray(new String[0]));
                j50VarD4.A = false;
                q50Var4.o(new k50(j50VarD4));
            }
        }
        ic0 ic0Var = new ic0((byte) 25);
        g30 g30Var = new g30();
        g30Var.c(ic0Var);
        synchronized (g30Var) {
            g30Var.l = (byte) 64;
        }
        g30Var.e();
        vt1 vt1Var3 = this.H;
        Uri uri3 = vt1Var3.c;
        String str2 = vt1Var3.h;
        boolean z7 = vt1Var3.x;
        boolean z8 = vt1Var3.N == 0;
        HashSet hashSet = new HashSet(this.H.N0);
        hashSet.addAll(q6);
        h01 h01Var = new h01(this, this, hashSet);
        vt1 vt1Var4 = this.H;
        h01Var.a = vt1Var4.N;
        h01Var.b = true;
        h01Var.c = vt1Var4.Q || vt1Var4.S;
        if (l6 || L6) {
            h01Var.f = new z91(uri3, str2, z7, z8);
        }
        yf0 yf0Var = new yf0(this, h01Var);
        q50 q50Var5 = this.p;
        x91.s(!yf0Var.m);
        q50Var5.getClass();
        yf0Var.d = new gc(q50Var5, (byte) 3);
        w91 w91Var = new w91(this);
        this.w = null;
        byte b = 26;
        byte b2 = 2;
        Object obj2 = w91Var;
        obj2 = w91Var;
        if (i6 && zA && this.H.c.getScheme().toLowerCase().startsWith("http")) {
            HashMap map = new HashMap();
            if (this.z3 != null) {
                obj2 = w91Var;
                str = null;
                int i4 = 0;
                while (true) {
                    int i5 = i4 + 1;
                    String[] strArr2 = this.z3;
                    if (i5 >= strArr2.length) {
                        break;
                    }
                    String str3 = strArr2[i4];
                    String str4 = strArr2[i5];
                    if (str3 != null && str4 != null) {
                        if ("User-Agent".equalsIgnoreCase(str3)) {
                            str = str4;
                        } else {
                            map.put(str3, str4);
                        }
                    }
                    i4 += 2;
                }
            } else {
                obj2 = w91Var;
                str = null;
            }
            String userInfo = this.H.c.getUserInfo();
            if (userInfo != null && userInfo.length() > 0 && userInfo.contains(":")) {
                map.put("Authorization", "Basic " + Base64.encodeToString(userInfo.getBytes(), 2));
            }
            ke keVar = new ke(t6);
            if (str != null) {
                keVar.m = str;
            }
            keVar.c0(map);
            ck0 ck0Var = new ck0(new w91(this, keVar), new mb1((byte) 25), b);
            vt1 vt1Var5 = this.H;
            String str5 = vt1Var5.h;
            if (str5 == null) {
                str5 = (String) this.u.get(vt1Var5.c.toString());
            }
            if (ys2.R(this.H.c) == 4) {
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
                    iR = ys2.R(uri4);
                }
                if (iR != 4) {
                    z2 = true;
                } else {
                    z2 = false;
                }
            } else {
                z2 = true;
            }
            o61.w(this, this.H.c.toString());
            if (z2 || (vc2VarR = o61.r(this)) == null) {
                obj = ck0Var;
            } else {
                dnVar = new dn();
                dnVar.m = new fa0((byte) 8);
                dnVar.l = vc2VarR;
                dnVar.p = ck0Var;
                dnVar.n = new ge(vc2VarR, b);
                dnVar.o = false;
                dnVar.q = (byte) 2;
            }
            if (z2) {
                obj = dnVar;
                ck0Var = null;
            }
            obj = dnVar;
            this.w = ck0Var;
            obj2 = obj;
        }
        obj2 = w91Var;
        x62 x62Var = new x62(this, new ck0(new ck0(obj2, new mo1(this, (byte) 10), b), new mo1(this, (byte) 11), b));
        this.x = x62Var;
        x62 x62Var2 = new x62(x62Var, this.z, (byte) 14, false);
        byte b3 = 28;
        bb1 bb1Var = new bb1(x62Var2, b3);
        boolean z9 = (this.H.Q || l6) ? false : true;
        pb0 pb0Var = z9 ? new pb0(g30Var, ic0Var, this.H.R) : null;
        this.S = pb0Var;
        gh0 gh0Var = g30Var;
        if (z9) {
            gh0Var = pb0Var;
        }
        i40 i40Var = new i40(this, new lb0(new lb0(gh0Var, (byte) 1), (byte) 0));
        i40Var.b = bb1Var;
        vu1 vu1Var = (vu1) i40Var.d;
        if (bb1Var != ((h00) vu1Var.e)) {
            vu1Var.e = bb1Var;
            ((HashMap) vu1Var.c).clear();
            ((HashMap) vu1Var.d).clear();
        }
        i40Var.g(new kq1((byte) 24));
        this.v = i40Var;
        x91.s(!yf0Var.m);
        yf0Var.c = new gc(i40Var, (byte) 4);
        Uri uri5 = this.H.c;
        String scheme = uri5 == null ? null : uri5.getScheme();
        if (scheme != null) {
            String lowerCase = scheme.toLowerCase();
            lowerCase.getClass();
            switch (lowerCase) {
                case "rawresource":
                case "android.resource":
                case "data":
                case "file":
                case "asset":
                case "content":
                    i = 0;
                    break;
                default:
                    i = this.H.z;
                    break;
            }
        } else {
            i = 0;
        }
        this.d3 = i;
        if ("memory".equals(this.H.A)) {
            el elVar = new el(this.H.B, this.d3);
            this.e3 = elVar;
            this.f3 = null;
            x91.s(!yf0Var.m);
            yf0Var.e = new gc(elVar, b2);
            z = true;
        } else {
            this.e3 = null;
            dl dlVar = new dl(this.H.B, this.d3);
            this.f3 = dlVar;
            z = true;
            x91.s(!yf0Var.m);
            yf0Var.e = new gc(dlVar, b2);
        }
        x91.s(yf0Var.m ^ z);
        yf0Var.m = z;
        mg0 mg0Var3 = new mg0(yf0Var);
        g6 = mg0Var3;
        if (!this.H.D) {
            mg0Var3.A1();
            if (mg0Var3.e0 != Integer.MIN_VALUE) {
                mg0Var3.e0 = Integer.MIN_VALUE;
                mg0Var3.q1(2, 5, Integer.MIN_VALUE);
            }
        }
        g6.P(new tb(3, 0, 1, 1, 0, false, true), true);
        mg0 mg0Var4 = g6;
        if (mg0Var4 != null) {
            mg0Var4.f(H6 ? 1.0f : Math.min(I6, 100.0f) / 100.0f);
        }
        t();
        YouTubeOverlay youTubeOverlay = this.E;
        mg0 mg0Var5 = g6;
        youTubeOverlay.D = mg0Var5;
        this.B.setPlayer(mg0Var5);
        l91 l91Var = this.o;
        if (l91Var != null) {
            l91Var.a();
        }
        g6.getClass();
        try {
            this.o = new f91(g6, this).a();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
        this.B.setControllerShowTimeoutMs(-1);
        if (M6) {
            M6 = false;
            V();
        }
        if (i6) {
            gt2.a0(this, this.H.R0);
            vj vjVar = this.I;
            boolean zC = gt2.C(this);
            float f2 = vjVar.c;
            if (f2 >= 0.0f) {
                double d = (((double) f2) * 0.00936d) + 0.064d;
                f = (float) (d * d);
            } else {
                f = -1.0f;
            }
            ValueAnimator valueAnimator = vjVar.b;
            if (valueAnimator != null) {
                valueAnimator.cancel();
                vjVar.b = null;
            }
            float fB = vjVar.a.getWindow().getAttributes().screenBrightness;
            if (fB < 0.0f) {
                fB = vjVar.b();
            }
            float fB2 = f < 0.0f ? vjVar.b() : f;
            if (zC || Math.abs(fB2 - fB) < 0.01f) {
                vjVar.a(f);
            } else {
                ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(fB, fB2);
                vjVar.b = valueAnimatorOfFloat;
                valueAnimatorOfFloat.setDuration(300L);
                vjVar.b.addUpdateListener(new tg(vjVar, b2));
                vjVar.b.addListener(new uj(vjVar, f));
                vjVar.b.start();
            }
            CustomDefaultTimeBar customDefaultTimeBar = this.x2;
            pr prVar = this.H0;
            if (zA) {
                customDefaultTimeBar.setBufferedColor(prVar.l);
            } else {
                customDefaultTimeBar.setBufferedColor(prVar.m);
            }
            v();
            d81 d81Var = new d81();
            g81 g81Var = new g81();
            List list = Collections.EMPTY_LIST;
            fw0 fw0Var = hw0.m;
            hw0 hw0VarL = ry1.p;
            i81 i81Var = new i81();
            l81 l81Var = l81.d;
            vt1 vt1Var6 = this.H;
            Uri uri6 = vt1Var6.c;
            String str6 = vt1Var6.h;
            String strW = this.w3;
            if (strW == null) {
                strW = gt2.w(this, uri6);
            }
            if (strW != null) {
                w81 w81Var = new w81();
                w81Var.a = strW;
                w81Var.e = strW;
                w81Var.n = this.x3;
                x81Var = new x81(w81Var);
            } else {
                x81Var = null;
            }
            Uri uri7 = this.H.e;
            if (uri7 != null) {
                String scheme2 = uri7.getScheme();
                if ("content".equals(scheme2)) {
                    try {
                        getContentResolver().openInputStream(uri7).close();
                        zExists = true;
                    } catch (Exception unused) {
                        zExists = false;
                    }
                } else {
                    zExists = new File("file".equals(scheme2) ? uri7.getPath() : uri7.toString()).exists();
                }
                if (zExists) {
                    o81VarB = zi0.b(this, this.H.e, null, true, null, null);
                } else {
                    o81VarB = null;
                }
            } else {
                o81VarB = null;
            }
            ArrayList arrayList = new ArrayList();
            if (this.j3) {
                arrayList.addAll(this.L4);
            }
            if (o81VarB != null) {
                arrayList.add(o81VarB);
            }
            if (!arrayList.isEmpty()) {
                hw0VarL = hw0.l(arrayList);
            }
            hw0 hw0Var = hw0VarL;
            this.V0 = 0L;
            this.W0 = false;
            int i7 = this.U0;
            vt1 vt1Var7 = this.H;
            if (i7 == 0) {
                jW2 = vt1Var7.h(vt1Var7.c);
            } else {
                Uri uri8 = vt1Var7.c;
                jW2 = w2(uri8, vt1Var7.h(uri8), "askEvery".equals(this.H.y0) || ("askOpen".equals(this.H.y0) && this.U0 == 1));
            }
            if (this.A3.isEmpty()) {
                mg0 mg0Var6 = g6;
                x91.s(g81Var.b == null || g81Var.a != null);
                if (uri6 != null) {
                    k81Var = new k81(uri6, str6, g81Var.a != null ? new h81(g81Var) : null, null, list, null, hw0Var, -9223372036854775807L);
                } else {
                    k81Var = null;
                }
                f81 f81Var = new f81(d81Var);
                j81 j81Var = new j81(i81Var);
                if (x81Var == null) {
                    x81Var = x81.M;
                }
                mg0Var6.u(new p81("", f81Var, k81Var, j81Var, x81Var, l81Var), jW2);
            } else {
                ArrayList arrayList2 = new ArrayList(this.A3);
                if (o81VarB != null && (i3 = this.C3) >= 0 && i3 < arrayList2.size()) {
                    int i8 = this.C3;
                    arrayList2.set(i8, S4((p81) arrayList2.get(i8), o81VarB));
                }
                g6.h(arrayList2, this.C3, jW2);
                if (this.u3 != null && this.C3 < this.k3.f.size()) {
                    this.v3 = (Map) this.u3.get(((gs1) this.k3.f.get(this.C3)).a.toString());
                }
                b7 b7Var = this.l3;
                if (b7Var != null) {
                    b7Var.j(this.C3, true);
                }
            }
            try {
                LoudnessEnhancer loudnessEnhancer = e6;
                if (loudnessEnhancer != null) {
                    loudnessEnhancer.release();
                }
                mg0 mg0Var7 = g6;
                mg0Var7.A1();
                e6 = new LoudnessEnhancer(((Integer) mg0Var7.C.j()).intValue());
                gt2.c();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            x1(true);
            this.J = true;
            this.L = false;
            this.M = false;
            u4(!z4);
            if (!z4 && ((i2 = this.U0) == 1 || (i2 == 2 && "every".equals(this.H.A0)))) {
                m3();
            }
            if (!z5 && !p6 && !z4) {
                this.B2 = true;
            }
            p6 = false;
            if (z4) {
                this.V0 = 0L;
            } else {
                long j = this.V0;
                if (j > 0) {
                    y(j, null);
                }
            }
            this.U0 = 0;
            H4();
            d3();
            ImageButton imageButton = this.W1;
            if (imageButton != null) {
                gt2.Z(this, imageButton, true);
            }
            gt2.Z(this, this.X1, true);
            ((DoubleTapPlayerView) this.B).setDoubleTapEnabled(true);
            if (!this.j3) {
                Thread thread = this.a3;
                if (thread != null) {
                    thread.interrupt();
                }
                this.Z2 = null;
                Thread thread2 = new Thread(new to1(this, (byte) 27));
                this.a3 = thread2;
                thread2.start();
            }
            mg0 mg0Var8 = g6;
            boolean z10 = !L6;
            mg0Var8.A1();
            if (!mg0Var8.p0) {
                mg0Var8.y.n(z10);
            }
        } else if (z3) {
            this.B.j();
            String str7 = this.R1;
            if (str7 != null) {
                String str8 = this.S1;
                this.R1 = null;
                this.S1 = null;
                this.B.post(new hp1(this, str7, str8, (byte) 1));
            } else {
                this.B.post(new to1(this, b3));
            }
        } else {
            D();
        }
        g6.H(this.l);
        mg0 mg0Var9 = g6;
        mq1 mq1Var2 = this.X;
        g20 g20Var2 = mg0Var9.s;
        mq1Var2.getClass();
        g20Var2.getClass();
        g20Var2.q.a(mq1Var2);
        Uri uriD0 = d0();
        if (uriD0 != null) {
            gt2.K("media=" + gt2.V(uriD0, this.H.I0));
        }
        if (u6 == null && this.H.N != 0) {
            u6 = Boolean.valueOf(FfmpegLibrary.a.isAvailable());
        }
        if (z4) {
            V2(false);
            ty tyVar = this.B;
            ImageButton imageButton2 = this.n2;
            Objects.requireNonNull(imageButton2);
            tyVar.post(new cj1(imageButton2, b2));
        } else {
            g6.d();
        }
        this.p1 = SystemClock.elapsedRealtime();
        if (!K2() || ((rl0) this.u4.a) == null || (uri = this.H.c) == null || !uri.equals(this.A4)) {
            this.y4 = null;
            Z2(null);
            Y2(K2() ? this.H.f : null);
        } else {
            a82 a82Var = this.p4;
            if (a82Var != null) {
                a82Var.b();
            }
            this.B4 = true;
        }
        if (this.z2 && this.Y2) {
            this.z2 = false;
            this.B.j();
            this.B.setControllerShowTimeoutMs(3500);
            g6.k(true);
        }
    }

    public final boolean Z1(String str, boolean z) {
        if (str == null || "audio/raw".equals(str) || g6 == null || this.D == null || this.H.N0.contains(str)) {
            return false;
        }
        HashSet hashSet = q6;
        if (hashSet.contains(str)) {
            return false;
        }
        StringBuilder sb = new StringBuilder("audio passthrough revoked: ");
        sb.append(str);
        sb.append(z ? ", persisted" : "");
        gt2.K(sb.toString());
        hashSet.add(str);
        if (z) {
            vt1 vt1Var = this.H;
            vt1Var.getClass();
            HashSet hashSet2 = new HashSet(vt1Var.N0);
            hashSet2.add(str);
            vt1Var.N0 = hashSet2;
            vt1Var.b.edit().putStringSet("revokedAudioMimes", hashSet2).apply();
        }
        this.D.b.add(str);
        if (this.H.N != 0) {
            g6.d();
            return true;
        }
        this.z2 = true;
        m6 = true;
        this.B.post(new to1(this, (byte) 17));
        return true;
    }

    public final void Z2(rl0 rl0Var) {
        if (Objects.equals((rl0) this.u4.a, rl0Var)) {
            return;
        }
        e2();
        this.u4.a = rl0Var;
        this.B4 = rl0Var != null;
        if (rl0Var == null) {
            this.w4 = null;
        }
        if (rl0Var != null) {
            D1(null);
            this.H.B(null);
        }
        a82 a82Var = this.p4;
        if (a82Var != null) {
            a82Var.b();
        }
        s();
        D4();
        C4();
    }

    public final float Z3(int i, float f) {
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

    public final boolean a(Uri uri) {
        mg0 mg0Var = g6;
        if (mg0Var != null && uri != null) {
            int iV = mg0Var.V();
            int iA1 = g6.a1();
            if (iV >= 0 && iV < iA1 && !uri.equals(this.J4)) {
                k81 k81Var = g6.Z0(iV).b;
                if (k81Var != null) {
                    fw0 fw0VarN = k81Var.g.listIterator(0);
                    while (fw0VarN.hasNext()) {
                        if (((o81) fw0VarN.next()).a.equals(uri)) {
                        }
                    }
                }
                gt2.K("subtitles: painting " + uri.getLastPathSegment());
                this.G4 = false;
                this.J4 = uri;
                this.I4 = uri;
                this.H4 = null;
                vj2 vj2Var = this.F4;
                if (vj2Var != null) {
                    vj2Var.g(null);
                }
                Thread thread = new Thread(new oo1(this, uri, zi0.p(uri), (byte) 2), "SubtitleTimeline");
                thread.setDaemon(true);
                thread.start();
                return true;
            }
        }
        return false;
    }

    public final boolean a0(View view) {
        View[] viewArrH1 = H1();
        for (int i = 0; i < this.D1 && i < viewArrH1.length; i++) {
            if (viewArrH1[i] == view) {
                return true;
            }
        }
        return false;
    }

    public final boolean a1(vd2 vd2Var) {
        if (vd2Var.c == 2) {
            return true;
        }
        boolean z = vd2Var.i;
        String str = this.m4;
        if (str == null) {
            vt1 vt1Var = this.H;
            str = z ? vt1Var.m0 : vt1Var.l0;
        }
        return "auto".equals(str);
    }

    public final void a2() {
        ImageButton imageButton = this.l2;
        if (imageButton != null) {
            vt1 vt1Var = this.H;
            imageButton.setVisibility((!vt1Var.J0 || vt1Var.M0 == null) ? 8 : 0);
        }
    }

    public final void a3(boolean z) {
        TextView textView = this.m5;
        if (textView != null) {
            textView.setVisibility((!z || this.G) ? 8 : 0);
        }
    }

    public final Uri a4() {
        mg0 mg0Var;
        k81 k81Var;
        Uri uri = this.H.e;
        if (uri == null || (mg0Var = g6) == null) {
            return null;
        }
        p81 p81VarZ = mg0Var.z();
        if (p81VarZ != null && (k81Var = p81VarZ.b) != null) {
            fw0 fw0VarN = k81Var.g.listIterator(0);
            while (fw0VarN.hasNext()) {
                if (((o81) fw0VarN.next()).a.equals(uri)) {
                    return null;
                }
            }
        }
        return uri;
    }

    public final void b(boolean z, Integer num) {
        this.V0 = 0L;
        this.T0.e(false);
        this.r2.setAlpha(1.0f);
        this.s2.setAlpha(1.0f);
        mg0 mg0Var = g6;
        if (mg0Var == null) {
            this.T0.c();
            return;
        }
        if (!z && mg0Var.C() == 3) {
            this.T0.c();
        }
        if (z) {
            if (num != null) {
                g6.n1(num.intValue(), 0L, false);
            } else {
                g6.o1(0L);
            }
            if (this.T0.isShown()) {
                m3();
            }
        }
        if (num != null || this.W0) {
            this.W0 = false;
            L1();
        }
    }

    public final List b0() {
        Uri uriD0 = d0();
        return (uriD0 == null || !uriD0.toString().equals(this.t)) ? Collections.EMPTY_LIST : this.r;
    }

    public final boolean b1(wf0 wf0Var) {
        if ((wf0Var instanceof wf0) && wf0Var.n == 0 && gt2.A(d0())) {
            Throwable cause = wf0Var;
            while (cause != null) {
                StackTraceElement[] stackTrace = cause.getStackTrace();
                if (stackTrace.length <= 0 || !stackTrace[0].getClassName().startsWith("com.brouken.player.")) {
                    cause = cause.getCause() == cause ? null : cause.getCause();
                }
            }
            int i = wf0Var.l;
            if (i != 2006 && i != 2007 && i != 3003 && i != 3004) {
                return true;
            }
        }
        return false;
    }

    public final void b2(boolean z) {
        if (g6 != null) {
            gt2.K("release player".concat(z ? ", saving" : ""));
        }
        M();
        ty tyVar = this.B;
        if (tyVar != null) {
            tyVar.removeCallbacks(this.b0);
            this.B.removeCallbacks(this.h0);
            this.B.removeCallbacks(this.i0);
            this.B.removeCallbacks(this.k0);
            this.B.removeCallbacks(this.s0);
            this.r0 = -1;
            this.B.removeCallbacks(this.P2);
            this.B.removeCallbacks(this.V2);
            this.B.removeCallbacks(this.Q);
            this.i2 = false;
            this.Q2 = false;
            this.O2 = false;
            this.S2 = false;
            this.W2 = false;
            this.B.removeCallbacks(this.R);
            this.B.removeCallbacks(this.N2);
        }
        this.H2 = -1L;
        this.I2 = -1L;
        this.K2 = 0;
        this.u2 = false;
        ty tyVar2 = this.B;
        if (tyVar2 != null) {
            tyVar2.removeCallbacks(this.v2);
        }
        TextView textView = this.s2;
        if (textView != null) {
            textView.setVisibility(8);
        }
        this.x0 = null;
        if (z) {
            C2();
        }
        if (g6 != null) {
            x1(false);
            l91 l91Var = this.o;
            if (l91Var != null) {
                l91Var.a();
            }
            if (g6.J() && this.A2) {
                this.z2 = true;
            }
            g6.e0(this.l);
            mg0 mg0Var = g6;
            mg0Var.A1();
            g20 g20Var = mg0Var.s;
            mq1 mq1Var = this.X;
            mq1Var.getClass();
            g20Var.q.e(mq1Var);
            g6.y();
            g6.k1();
            g6 = null;
            this.D = null;
            f6 = null;
            this.l4 = null;
        }
        ty tyVar3 = this.B;
        if (tyVar3 != null) {
            tyVar3.removeCallbacks(this.H5);
        }
        N();
        this.J4 = null;
        W0();
        this.c5 = false;
        CustomDefaultTimeBar customDefaultTimeBar = this.x2;
        if (customDefaultTimeBar != null) {
            customDefaultTimeBar.h();
        }
        O3();
        yl1 yl1Var = this.t1;
        if (yl1Var != null) {
            yl1Var.setVisibility(8);
        }
        V2(false);
        com.bumptech.glide.a.d(getApplicationContext()).m(this.O0);
        this.N0.setVisibility(8);
        this.F0 = false;
        r4();
        Dialog dialog = this.K1;
        if (dialog != null) {
            dialog.dismiss();
            this.K1 = null;
        }
        Dialog dialog2 = this.J1;
        if (dialog2 != null) {
            dialog2.dismiss();
            this.J1 = null;
        }
        Dialog dialog3 = this.L1;
        if (dialog3 != null) {
            dialog3.dismiss();
            this.L1 = null;
        }
        Dialog dialog4 = this.N1;
        if (dialog4 != null) {
            dialog4.dismiss();
            this.N1 = null;
        }
        Dialog dialog5 = this.M1;
        if (dialog5 != null) {
            dialog5.dismiss();
            this.M1 = null;
        }
        Dialog dialog6 = this.O1;
        if (dialog6 != null) {
            dialog6.dismiss();
            this.O1 = null;
        }
        Dialog dialog7 = this.P1;
        if (dialog7 != null) {
            dialog7.dismiss();
            this.P1 = null;
        }
        Dialog dialog8 = r2.a;
        if (dialog8 != null) {
            dialog8.dismiss();
            r2.a = null;
        }
        ImageButton imageButton = this.v1;
        if (imageButton != null) {
            imageButton.setVisibility(this.A3.size() > 1 ? 0 : 8);
        }
        if (this.w1 != null) {
            this.x1 = false;
            q();
        }
        ImageButton imageButton2 = this.W1;
        if (imageButton2 != null) {
            gt2.Z(this, imageButton2, false);
        }
        gt2.Z(this, this.X1, false);
    }

    public final void b3(ImageButton imageButton, int i, int i2, int i3) {
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
        imageButton.setBackground(gt2.b0(this.H0.b, 10000.0f));
        imageButton.setForeground(gt2.j(this, 0, 0, this.H0.j));
    }

    public final void b4(Uri uri, long j, boolean z) {
        if (g6 == null || uri == null) {
            return;
        }
        C2();
        int iV = g6.V();
        ArrayList arrayList = this.A3;
        if (arrayList.isEmpty() || iV < 0 || iV >= arrayList.size()) {
            this.H.c = uri;
        } else {
            arrayList.set(iV, I3(iV, uri));
            this.C3 = iV;
        }
        this.H.z(j);
        this.K4 = !z;
        this.z2 = z;
        this.F1 = true;
        Z0();
    }

    /* JADX WARN: Code duplicated, block: B:121:0x00d0 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:122:0x0104 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:36:0x0091  */
    /* JADX WARN: Code duplicated, block: B:44:0x00b8  */
    /* JADX WARN: Code duplicated, block: B:47:0x00c2  */
    /* JADX WARN: Code duplicated, block: B:53:0x00e2  */
    /* JADX WARN: Code duplicated, block: B:55:0x00ec  */
    /* JADX WARN: Code duplicated, block: B:58:0x00f2  */
    /* JADX WARN: Code duplicated, block: B:61:0x00f8  */
    /* JADX WARN: Code duplicated, block: B:62:0x00fa  */
    /* JADX WARN: Code duplicated, block: B:64:0x0101 A[LOOP:1: B:45:0x00bc->B:64:0x0101, LOOP_END] */
    /* JADX WARN: Multi-variable type inference failed */
    public final Bundle c() {
        long j;
        long j2;
        int iG;
        String str;
        List list;
        Iterator it;
        Iterator it2;
        long j3;
        int iG2;
        Bundle bundle = this.m3;
        if (bundle != null) {
            return bundle;
        }
        f4();
        mg0 mg0Var = g6;
        int iV = mg0Var != null ? mg0Var.V() : this.C3;
        b7 b7Var = this.l3;
        Uri uriO1 = O1(iV);
        long[] jArr = this.E3;
        boolean z = this.N4;
        long[] jArr2 = (long[]) b7Var.c;
        ArrayList arrayList = (ArrayList) b7Var.b;
        if (z) {
            b7Var.d(iV, 0L, -9223372036854775807L, true);
        }
        Bundle bundle2 = new Bundle();
        bundle2.putString("uri", uriO1 == null ? null : uriO1.toString());
        bundle2.putInt("index", b7Var.i(iV) ? iV : -1);
        a7 a7Var = arrayList.isEmpty() ? null : (a7) we2.d(1, arrayList);
        if (a7Var == null || a7Var.a != iV) {
            j = 0;
            j2 = 0;
        } else {
            j = 0;
            j2 = a7Var.c;
        }
        bundle2.putInt("position_sec", b7.g(j2));
        if (b7Var.i(iV)) {
            long j4 = jArr2[iV];
            if (j4 > j) {
                iG = b7.g(j4);
            } else {
                iG = 0;
            }
        } else {
            iG = 0;
        }
        bundle2.putInt("duration_sec", iG);
        int length = jArr2.length;
        long j5 = j;
        int[] iArr = new int[length];
        int i = 0;
        while (i < length) {
            if (!((boolean[]) b7Var.d)[i]) {
                it = arrayList.iterator();
                while (true) {
                    if (it.hasNext()) {
                        iArr[i] = -1;
                        break;
                        break;
                    }
                    it2 = it;
                    if (((a7) it.next()).a == i) {
                        if (a7Var != null) {
                            if (jArr != null) {
                                j3 = -9223372036854775807L;
                            } else {
                                j3 = -9223372036854775807L;
                            }
                            if (j3 == -9223372036854775807L) {
                                iG2 = 0;
                            } else {
                                iG2 = b7.g(j3);
                            }
                            iArr[i] = iG2;
                            break;
                            break;
                        }
                        if (jArr != null) {
                            j3 = -9223372036854775807L;
                        } else {
                            j3 = -9223372036854775807L;
                        }
                        if (j3 == -9223372036854775807L) {
                            iG2 = 0;
                        } else {
                            iG2 = b7.g(j3);
                        }
                        iArr[i] = iG2;
                        break;
                        break;
                    }
                    it = it2;
                }
            } else {
                long j7 = jArr2[i];
                if (j7 <= j5) {
                    it = arrayList.iterator();
                    while (true) {
                        if (it.hasNext()) {
                            iArr[i] = -1;
                            break;
                        }
                        it2 = it;
                        if (((a7) it.next()).a == i) {
                            if (a7Var != null && a7Var.a == i) {
                                iArr[i] = b7.g(a7Var.c);
                                break;
                            }
                            if (jArr != null || i >= jArr.length) {
                                j3 = -9223372036854775807L;
                            } else {
                                j3 = jArr[i];
                            }
                            if (j3 == -9223372036854775807L) {
                                iG2 = 0;
                            } else {
                                iG2 = b7.g(j3);
                            }
                            iArr[i] = iG2;
                            break;
                        }
                        it = it2;
                    }
                } else {
                    iArr[i] = b7.g(j7);
                }
            }
            i++;
            z = z;
            jArr2 = jArr2;
        }
        boolean z2 = z;
        bundle2.putIntArray("positions_sec", iArr);
        if (((String) b7Var.e) != null) {
            str = "error";
        } else if (b7Var.a) {
            str = z2 ? "completion" : "user";
        } else {
            str = "cancelled";
        }
        bundle2.putString("end_by", str);
        String str2 = (String) b7Var.e;
        if (str2 != null) {
            bundle2.putString("error_message", str2);
        }
        fp2 fp2Var = (fp2) b7Var.f;
        if (fp2Var != null) {
            fp2Var.e("audio", bundle2);
        }
        fp2 fp2Var2 = (fp2) b7Var.g;
        if (fp2Var2 != null) {
            fp2Var2.e("subtitle", bundle2);
            list = null;
        } else {
            list = null;
            bundle2.putString("subtitle_language", null);
        }
        bundle2.putString("audio_chosen_by", b7Var.i(iV) ? ((String[]) b7Var.h)[iV] : list);
        bundle2.putString("subtitle_chosen_by", b7Var.i(iV) ? ((String[]) b7Var.i)[iV] : list);
        int size = arrayList.size();
        Parcelable[] parcelableArr = new Parcelable[size];
        for (int i2 = 0; i2 < size; i2++) {
            a7 a7Var2 = (a7) arrayList.get(i2);
            Bundle bundle3 = new Bundle();
            bundle3.putInt("index", a7Var2.a);
            bundle3.putLong("started_at", a7Var2.b);
            bundle3.putInt("position_sec", b7.g(a7Var2.c));
            long j8 = a7Var2.d;
            bundle3.putInt("duration_sec", j8 > j5 ? b7.g(j8) : -1);
            parcelableArr[i2] = bundle3;
        }
        bundle2.putParcelableArray("history", parcelableArr);
        int iQ4 = Q4(iV);
        if (iQ4 >= 0) {
            bundle2.putString("voice_label", ((js1) ((gs1) this.k3.f.get(iV)).t.get(iQ4)).a);
        }
        ks1 ks1Var = this.k3;
        if (ks1Var != null) {
            list = ks1Var.i;
        }
        if (list != null && !list.isEmpty()) {
            ArrayList arrayList2 = new ArrayList(list.subList(0, Math.min(20, list.size())));
            if (list.size() > 20) {
                arrayList2.set(19, "… " + (list.size() - 19) + " more");
            }
            bundle2.putStringArray("warnings", (String[]) arrayList2.toArray(new String[0]));
        }
        return bundle2;
    }

    public final boolean c1(oq1 oq1Var) {
        float f = oq1Var.b;
        float f2 = this.Y1;
        if (f > 0.0f) {
            return Math.abs(f - f2) < 0.001f;
        }
        return f2 == 0.0f && this.B.getResizeMode() == oq1Var.a;
    }

    public final void c2(String str, String str2, ArrayList arrayList, boolean z) {
        String string;
        hj2 hj2Var;
        if (g6 == null) {
            return;
        }
        if (str2 == null) {
            String strN0 = f1() ? null : n0(g6.V());
            if (str == null || strN0 == null) {
                return;
            }
            hj2 hj2VarN = vt1.n(this, i4());
            hj2VarN.G(strN0, str, "");
            vt1.t(this, hj2VarN);
            return;
        }
        ArrayList arrayListC0 = gt2.c0(this.H.T);
        String strI = str == null ? gt2.I(str2, arrayListC0) : str;
        if (strI == null) {
            hp2 hp2VarI4 = i4();
            String strI2 = hp2VarI4.i(str2);
            strI = strI2 == null ? null : (String) hp2VarI4.b.get(strI2);
        }
        String str3 = strI;
        String strN1 = f1() ? null : n0(g6.V());
        gs1 gs1VarG0 = z ? g0() : null;
        if (strN1 != null) {
            string = strN1;
        } else {
            string = gs1VarG0 != null ? gs1VarG0.a.toString() : String.valueOf(d0());
        }
        hj2 hj2VarN2 = vt1.n(this, i4());
        if (z) {
            hj2VarN2.G(strN1, str3, str2);
            if (str3 == null && !arrayListC0.isEmpty()) {
                str3 = (String) arrayListC0.get(0);
            }
            hj2Var = hj2VarN2;
            hj2Var.E(null, string, str3, str2, arrayList);
        } else {
            String str4 = string;
            String str5 = strN1;
            hj2Var = hj2VarN2;
            hj2Var.E(str5, str4, str3, str2, arrayList);
        }
        vt1.t(this, hj2Var);
    }

    public final void c3() {
        int iB = this.V1.b(46.0f);
        int iB2 = this.V1.b(10.0f);
        int iB3 = this.V1.b(6.0f);
        ImageButton imageButton = this.o2;
        if (imageButton != null) {
            imageButton.setImageResource(R.drawable.ic_skip_previous);
        }
        ImageButton imageButton2 = this.p2;
        if (imageButton2 != null) {
            imageButton2.setImageResource(R.drawable.ic_skip_next);
        }
        b3(this.o2, iB, iB2, iB3);
        b3(this.p2, iB, iB2, iB3);
        ImageButton imageButton3 = (ImageButton) findViewById(R.id.next);
        if (imageButton3 != null) {
            imageButton3.setImageResource(R.drawable.ic_skip_next);
        }
        ImageButton imageButton4 = (ImageButton) findViewById(R.id.delete);
        if (imageButton4 != null) {
            imageButton4.setImageResource(R.drawable.ic_delete_24dp);
        }
        b3(imageButton3, iB, iB2, iB3);
        b3(imageButton4, iB, iB2, iB3);
        ImageButton imageButton5 = this.o2;
        byte b = 0;
        if (imageButton5 != null) {
            imageButton5.setOnClickListener(new vo1(this, b));
        }
        ImageButton imageButton6 = this.p2;
        if (imageButton6 != null) {
            imageButton6.setOnClickListener(new vo1(this, (byte) 1));
        }
        ty tyVar = this.B;
        if (tyVar != null) {
            tyVar.getViewTreeObserver().addOnPreDrawListener(new wo1(this, b));
        }
    }

    public final void c4(int i) {
        gs1 gs1VarG0 = g0();
        int iI0 = i0();
        if (gs1VarG0 != null) {
            List list = gs1VarG0.v;
            List list2 = gs1VarG0.t;
            if (iI0 < 0 || i == iI0) {
                return;
            }
            js1 js1Var = (js1) list2.get(i);
            Uri uriF0 = f0();
            int iS1 = 0;
            for (hs1 hs1Var : ((js1) list2.get(iI0)).c) {
                if (uriF0 != null && hs1Var.b.equals(uriF0.toString())) {
                    iS1 = S1(hs1Var.a);
                }
            }
            Uri uri = js1Var.b;
            for (hs1 hs1Var2 : js1Var.c) {
                if (iS1 > 0 && S1(hs1Var2.a) == iS1) {
                    uri = Uri.parse(hs1Var2.b);
                    break;
                }
            }
            String strG4 = g4();
            this.p3.remove(strG4);
            List list3 = js1Var.d;
            if (list3 == null) {
                list3 = list;
            }
            List list4 = ((js1) list2.get(iI0)).d;
            if (list4 != null) {
                list = list4;
            }
            if (list3 != list) {
                this.q3.remove(strG4);
                b7 b7Var = this.l3;
                if (b7Var != null) {
                    int iV = g6.V();
                    if (b7Var.i(iV)) {
                        ((String[]) b7Var.i)[iV] = null;
                    }
                }
            }
            b4(uri, Math.max(0L, g6.O0()), g6.w());
        }
    }

    public final void d(SpannableStringBuilder spannableStringBuilder, String str) {
        if (str == null || str.isEmpty()) {
            return;
        }
        f(spannableStringBuilder, str);
        spannableStringBuilder.setSpan(new ForegroundColorSpan(this.H0.e), spannableStringBuilder.length() - str.length(), spannableStringBuilder.length(), 33);
    }

    public final Uri d0() {
        k81 k81Var;
        mg0 mg0Var = g6;
        p81 p81VarZ = mg0Var != null ? mg0Var.z() : null;
        return (p81VarZ == null || (k81Var = p81VarZ.b) == null) ? this.H.c : k81Var.a;
    }

    public final boolean d1() {
        if (gt2.B(this)) {
            return isInPictureInPictureMode();
        }
        return false;
    }

    public final void d2() {
        this.H.w(this.B.getVideoSurfaceView().getScaleX(), this.Y1, this.B.getResizeMode());
    }

    /* JADX WARN: Code duplicated, block: B:15:0x003a  */
    /* JADX WARN: Code duplicated, block: B:30:0x0076  */
    public final void d3() {
        String str;
        double d;
        if (this.b5 == null) {
            sx0 sx0Var = new sx0();
            sx0Var.n = Collections.EMPTY_LIST;
            sx0Var.l = 0.0d;
            this.b5 = sx0Var;
        }
        this.c5 = false;
        sx0 sx0Var2 = null;
        if (g6 != null) {
            ArrayList arrayList = this.B3;
            if (arrayList.isEmpty()) {
                str = this.y3;
            } else {
                int iV = g6.V();
                str = (iV < 0 || iV >= arrayList.size()) ? null : (String) arrayList.get(iV);
            }
        } else {
            str = this.y3;
        }
        boolean z = (str == null || str.isEmpty()) ? false : true;
        this.t5 = z;
        sx0 sx0Var3 = this.b5;
        if (z) {
            sx0Var2 = new sx0();
            ArrayList arrayList2 = new ArrayList();
            sx0Var2.m = arrayList2;
            ArrayList arrayList3 = new ArrayList();
            sx0Var2.n = arrayList3;
            if (str != null && !str.isEmpty()) {
                try {
                    JSONObject jSONObject = new JSONObject(str);
                    Object objOpt = jSONObject.opt("duration_ms");
                    if (objOpt == null) {
                        d = 0.0d;
                    } else {
                        try {
                            double d2 = Double.parseDouble(String.valueOf(objOpt));
                            if (Double.isNaN(d2) || d2 <= 0.0d) {
                                d = 0.0d;
                            } else {
                                d = d2 / 1000.0d;
                            }
                        } catch (NumberFormatException unused) {
                        }
                    }
                    sx0Var2.l = d;
                    sx0.c(jSONObject.optJSONArray("skip"), arrayList2);
                    sx0.c(jSONObject.optJSONArray("ad"), arrayList3);
                } catch (Exception unused2) {
                    arrayList2.clear();
                    arrayList3.clear();
                }
            }
        }
        sx0Var3.m = sx0Var2;
        sx0Var3.n = Collections.EMPTY_LIST;
        this.v5 = false;
        this.z5 = -9223372036854775807L;
        this.A5 = -9223372036854775807L;
        this.B5 = -9223372036854775807L;
        this.C5 = -9223372036854775807L;
        this.F5 = -9223372036854775807L;
        CustomDefaultTimeBar customDefaultTimeBar = this.x2;
        if (customDefaultTimeBar != null) {
            customDefaultTimeBar.h();
        }
        if (c0() <= 0.0d) {
            s1();
        }
    }

    public final void d4() {
        yl1 yl1Var;
        if (this.t1 == null || (yl1Var = this.u1) == null || this.C0 == null || yl1Var.getWidth() == 0) {
            return;
        }
        int[] iArr = new int[2];
        int[] iArr2 = new int[2];
        this.u1.getLocationInWindow(iArr);
        this.C0.getLocationInWindow(iArr2);
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.t1.getLayoutParams();
        int i = iArr[0] - iArr2[0];
        int i2 = iArr[1] - iArr2[1];
        if (marginLayoutParams.leftMargin == i && marginLayoutParams.topMargin == i2) {
            return;
        }
        marginLayoutParams.leftMargin = i;
        marginLayoutParams.topMargin = i2;
        this.t1.setLayoutParams(marginLayoutParams);
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public final boolean dispatchKeyEvent(KeyEvent keyEvent) {
        int keyCode;
        Button button;
        int keyCode2;
        this.Y4 = 0;
        if (!n2() && (!this.T0.isShown() || (keyCode2 = keyEvent.getKeyCode()) == 4 || keyCode2 == 164 || keyCode2 == 24 || keyCode2 == 25)) {
            if (!M6 || keyEvent.getKeyCode() == 4) {
                if (this.y0) {
                    int keyCode3 = keyEvent.getKeyCode();
                    if (keyEvent.getAction() == 0) {
                        if (keyCode3 == 19) {
                            E2(true);
                            return true;
                        }
                        if (keyCode3 == 20) {
                            E2(false);
                            return true;
                        }
                    } else if (keyEvent.getAction() == 1 && keyCode3 != 19 && keyCode3 != 20) {
                        if (this.z0) {
                            this.z0 = false;
                            return true;
                        }
                        this.y0 = false;
                        ty tyVar = this.B;
                        tyVar.postDelayed(tyVar.C0, 200L);
                        mg0 mg0Var = g6;
                        if (mg0Var != null && !mg0Var.J()) {
                            this.B.j();
                        }
                        if (Math.abs(this.B.getScaleFit() - this.A0) < 0.005d) {
                            this.B.setScale(1.0f);
                            this.B.setResizeMode(0);
                        }
                        J4();
                        d2();
                        return true;
                    }
                } else {
                    if (L6 && ((keyCode = keyEvent.getKeyCode()) == 23 || keyCode == 66 || keyCode == 96 || keyCode == 108 || keyCode == 160)) {
                        if (keyEvent.getAction() == 0 && !M6 && !D6 && (button = this.d5) != null && button.getVisibility() == 0) {
                            this.d5.performClick();
                            this.G5 = keyEvent.getKeyCode();
                            return true;
                        }
                        if (keyEvent.getAction() == 1 && this.G5 == keyEvent.getKeyCode()) {
                            this.G5 = 0;
                            return true;
                        }
                    }
                    if (!L6 || D6 || keyEvent.getKeyCode() == 4) {
                        if (keyEvent.getAction() == 0) {
                            F2();
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
                z3();
                return true;
            }
        }
        return true;
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public final boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getActionMasked() == 0) {
            this.h2 = n2();
        }
        return this.h2 || super.dispatchTouchEvent(motionEvent);
    }

    public final long e0() {
        mg0 mg0Var = g6;
        if (mg0Var == null) {
            return -9223372036854775807L;
        }
        long jO0 = mg0Var.O0();
        hn2 hn2VarR0 = g6.r0();
        return (hn2VarR0.p() || g6.U() != -1) ? jO0 : jO0 - ys2.p0(hn2VarR0.f(g6.L(), this.d0, false).e);
    }

    public final void e2() {
        if (g6 == null) {
            return;
        }
        rl0 rl0Var = (rl0) this.u4.a;
        fw0 fw0VarN = g6.E().a.listIterator(0);
        while (fw0VarN.hasNext()) {
            wp2 wp2Var = (wp2) fw0VarN.next();
            if (wp2Var.b.c == 3) {
                for (int i = 0; i < wp2Var.a; i++) {
                    if (wp2Var.e[i]) {
                        rl0 rl0VarA = wp2Var.a(i);
                        if (rl0Var == null || !rl0Var.equals(rl0VarA)) {
                            this.y4 = wp2Var.b;
                            this.z4 = i;
                            return;
                        }
                    }
                }
            }
        }
    }

    public final Uri f0() {
        p81 p81VarZ;
        k81 k81Var;
        mg0 mg0Var = g6;
        return (mg0Var == null || (p81VarZ = mg0Var.z()) == null || (k81Var = p81VarZ.b) == null) ? this.H.c : k81Var.a;
    }

    public final void f4() {
        mg0 mg0Var;
        if (this.l3 == null || (mg0Var = g6) == null || !mg0Var.x()) {
            return;
        }
        this.l3.e(g6.V(), g6.O0(), g6.getDuration());
    }

    @Override // android.app.Activity
    public final void finish() {
        long duration;
        long jO0;
        if (this.l3 != null) {
            Bundle bundleC = c();
            this.m3 = bundleC;
            Intent intentPutExtras = new Intent("com.justplus.player.result").putExtras(bundleC);
            String string = bundleC.getString("uri");
            if (string != null) {
                intentPutExtras.setData(Uri.parse(string));
            }
            setResult(-1, intentPutExtras);
        } else if (this.M4) {
            Intent intent = new Intent("com.mxtech.intent.result.VIEW");
            Uri uriD0 = d0();
            intent.putExtra("end_by", this.N4 ? "playback_completion" : "user");
            if (!this.N4) {
                mg0 mg0Var = g6;
                if (mg0Var != null) {
                    duration = mg0Var.getDuration();
                    if (duration == -9223372036854775807L) {
                        duration = 0;
                    }
                    jO0 = g6.x() ? g6.O0() : 0L;
                } else {
                    duration = 0;
                    jO0 = 0;
                }
                if (jO0 <= 0 || duration <= 0) {
                    long j = this.Q4;
                    if (j > 0) {
                        uriD0 = this.O4;
                        jO0 = this.P4;
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
        rl0 rl0Var;
        int i2;
        String str;
        yb ybVarB;
        String str2;
        int i3;
        int i4;
        String str3;
        String strValueOf;
        if (g6 == null) {
            return;
        }
        LinkedHashMap linkedHashMap2 = new LinkedHashMap();
        int i5 = 0;
        fw0 fw0VarN = g6.E().a.listIterator(0);
        int i7 = 0;
        int i8 = 0;
        int i9 = 0;
        rl0 rl0Var2 = null;
        rl0 rl0Var3 = null;
        while (fw0VarN.hasNext()) {
            wp2 wp2Var = (wp2) fw0VarN.next();
            int i10 = i5;
            while (i10 < wp2Var.a) {
                int i11 = wp2Var.d[i10];
                int i12 = i5;
                if (i11 != 4) {
                    StringBuilder sb2 = new StringBuilder(rl0.c(wp2Var.a(i10)));
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
                    j$.util.Map.EL.merge(linkedHashMap2, sb2.toString(), 1, new zo1());
                }
                i10++;
                i5 = i12;
            }
            int i13 = i5;
            int i14 = wp2Var.b.c;
            if (i14 == 1) {
                i8++;
            } else if (i14 == 2) {
                i7++;
                if (wp2Var.b() && rl0Var2 == null) {
                    rl0Var2 = wp2Var.b.d[i13];
                }
            } else if (i14 == 3) {
                i9++;
                if (wp2Var.b()) {
                    rl0Var3 = wp2Var.b.d[i13];
                }
            }
            i5 = i13;
        }
        int i15 = i5;
        sb.append("\nVideo: ");
        mg0 mg0Var = g6;
        mg0Var.A1();
        sb.append(rl0.c(mg0Var.V));
        if (m0() != null) {
            sb.append("\nVideo dropped by the extractor: ");
            sb.append(m0());
        }
        sb.append("\nAudio: ");
        mg0 mg0Var2 = g6;
        mg0Var2.A1();
        sb.append(rl0.c(mg0Var2.W));
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
        mg0 mg0Var3 = g6;
        mg0Var3.A1();
        if (mg0Var3.V != null) {
            mg0 mg0Var4 = g6;
            mg0Var4.A1();
            rl0Var2 = mg0Var4.V;
        }
        String str6 = "yes";
        if (rl0Var2 == null || (str2 = rl0Var2.p) == null || (i3 = rl0Var2.w) == -1 || (i4 = rl0Var2.x) == -1) {
            linkedHashMap = linkedHashMap2;
            i = i8;
            rl0Var = rl0Var3;
            i2 = i9;
        } else {
            float f = rl0Var2.B;
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
                                            rl0Var = rl0Var3;
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
                                        rl0Var3 = rl0Var3;
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
                            rl0Var3 = rl0Var;
                        } catch (Exception e3) {
                            e = e3;
                            i = i8;
                            rl0Var = rl0Var3;
                        }
                    }
                    i = i8;
                    rl0Var = rl0Var3;
                    i2 = i9;
                    i16 = i17 + 1;
                    i8 = i;
                    i9 = i2;
                    linkedHashMap2 = linkedHashMap;
                    str6 = str3;
                    rl0Var3 = rl0Var;
                }
                linkedHashMap = linkedHashMap2;
                i = i8;
                rl0Var = rl0Var3;
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
        vt1 vt1Var = this.H;
        boolean z = vt1Var.J && vt1Var.K;
        if (z) {
            ybVarB = F0(this);
            str = null;
        } else {
            ry1 ry1Var = yb.e;
            str = null;
            ybVarB = yb.b(this, tb.i, null, yb.f);
        }
        StringBuilder sb4 = new StringBuilder();
        int[] iArr = {5, 6, 18, 17, 7, 8, 14};
        String[] strArr = {"AC3", "E-AC3", "E-AC3 JOC", "AC4", "DTS", "DTS-HD", "TrueHD"};
        for (int i20 = 0; i20 < 7; i20++) {
            if (ys2.l(ybVarB.a, iArr[i20])) {
                sb4.append(sb4.length() == 0 ? "" : " ");
                sb4.append(strArr[i20]);
            }
        }
        String str8 = z ? ", declared by the app, not by the route" : "";
        StringBuilder sb5 = new StringBuilder();
        sb5.append(ybVarB.b);
        sb5.append(" ch, ");
        sb5.append(sb4.length() == 0 ? "PCM only" : "bitstream " + ((Object) sb4));
        sb5.append(str8);
        sb.append(sb5.toString());
        sb.append("\nSubtitle: ");
        sb.append(rl0Var != null ? rl0.c(rl0Var) : "none");
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
        long duration = g6.getDuration();
        sb.append("\nPosition: ");
        sb.append(g6.O0());
        sb.append('/');
        sb.append(duration == -9223372036854775807L ? "unknown" : String.valueOf(duration));
        sb.append(" ms, buffered ");
        sb.append(g6.v());
        sb.append(" ms, state ");
        sb.append(L3(g6.C()));
        sb.append(g6.J() ? " (playing)" : g6.w() ? " (play when ready)" : " (paused)");
        sb.append(", item ");
        sb.append(g6.V() + 1);
        sb.append('/');
        sb.append(g6.a1());
        mg0 mg0Var5 = g6;
        mg0Var5.A1();
        k10 k10Var = mg0Var5.g0;
        if (k10Var != null) {
            synchronized (k10Var) {
            }
            sb.append("\nVideo frames: ");
            sb.append(k10Var.e);
            sb.append(" rendered, ");
            sb.append(k10Var.g);
            sb.append(" dropped (");
            sb.append(k10Var.i);
            sb.append(" in a row), ");
            sb.append(k10Var.f);
            sb.append(" skipped");
            if (k10Var.l > 0) {
                sb.append(", mean offset ");
                sb.append(k10Var.k / ((long) k10Var.l));
                sb.append(" us");
            }
        }
        long jO = g6.o();
        if (jO != -9223372036854775807L) {
            sb.append("\nLive: ");
            sb.append(jO);
            sb.append(" ms behind the edge");
        }
        sb.append("\nPlayback: decoder priority ");
        sb.append(this.H.N);
        sb.append(", speed ");
        sb.append(this.H.m);
        sb.append(", resize ");
        sb.append(this.H.i);
        sb.append(this.H.x ? ", tunneling" : "");
        sb.append(this.H.y ? ", frame rate matching" : "");
        sb.append(this.d3 > 0 ? ", back buffer " + (this.d3 / 1000) + "s" : "");
        vt1 vt1Var2 = this.H;
        sb.append((vt1Var2.y && vt1Var2.C) ? ", resolution matching" : "");
        sb.append(this.H.Q ? ", map DV7" : "");
        sb.append(this.H.S ? ", no DV" : "");
        sb.append(this.H.R ? ", no HDR10+ under DV" : "");
        sb.append(R() ? ", dialogue lift" : "");
        sb.append(o0() ? ", night mode" : "");
        sb.append("\nRecovery: retries source=");
        sb.append(this.Z);
        sb.append(" decoder=");
        sb.append(this.g0);
        sb.append(" freeze=");
        sb.append(this.o0);
        sb.append((!l6 || this.H.S) ? "" : ", forced HEVC for Dolby Vision");
        sb.append("; audio restart pending=");
        sb.append(this.Q2);
        sb.append(" inFlight=");
        sb.append(this.O2);
        sb.append(" settling=");
        sb.append(this.S2);
        if (this.t0 != 0) {
            sb.append(", last reselect ");
            sb.append(SystemClock.elapsedRealtime() - this.t0);
            sb.append(" ms ago");
        }
        pb0 pb0Var = this.S;
        String str9 = pb0Var != null ? pb0Var.n : str;
        if (str9 != null) {
            sb.append("\nDolby Vision profile 7: ");
            sb.append(str9);
        }
        if (!this.H.N0.isEmpty()) {
            sb.append("\nAudio passthrough revoked: ");
            sb.append(this.H.N0);
        }
        String strU = gt2.U();
        if (strU.isEmpty()) {
            return;
        }
        sb.append("\n\nTrace:\n");
        sb.append(strU);
    }

    public final gs1 g0() {
        mg0 mg0Var;
        int iV;
        if (this.k3 == null || (mg0Var = g6) == null || (iV = mg0Var.V()) < 0 || iV >= this.k3.f.size()) {
            return null;
        }
        return (gs1) this.k3.f.get(iV);
    }

    public final void g2(String str, wf0 wf0Var) {
        w3.b().q("Playback ".concat(str), new gk((Object) this, str, (Serializable) wf0Var, (byte) 7));
    }

    public final void g3() {
        List listI0 = I0();
        ArrayList arrayList = new ArrayList();
        int i = 0;
        while (true) {
            ArrayList arrayList2 = (ArrayList) listI0;
            if (i >= arrayList2.size()) {
                r2.q(this, this.V1, new np1(this, (byte) 14), getString(R.string.button_crop), arrayList);
                return;
            } else {
                arrayList.add(new l70(((oq1) arrayList2.get(i)).c, null, c1((oq1) arrayList2.get(i)), new ko1(this, i, (byte) 2)));
                i++;
            }
        }
    }

    public final String g4() {
        if (g6 == null) {
            return null;
        }
        if (this.j3) {
            return "#" + g6.V();
        }
        Uri uriD0 = d0();
        if (uriD0 == null) {
            return null;
        }
        return uriD0.toString();
    }

    public final void h(int i) {
        oq1 oq1Var = (oq1) ((ArrayList) I0()).get(i);
        float f = oq1Var.b;
        this.Y1 = f;
        this.B.t(oq1Var.a, f);
        r2.E(this.B, oq1Var.c, R.drawable.ic_aspect_ratio_24dp);
        J4();
        d2();
    }

    public final LinkedHashMap h0() {
        int iV;
        int iI0 = i0();
        if (iI0 >= 0) {
            return R1(((js1) g0().t.get(iI0)).c);
        }
        if (g6 != null) {
            ArrayList arrayList = this.b4;
            if (!arrayList.isEmpty() && (iV = g6.V()) >= 0 && iV < arrayList.size()) {
                return (LinkedHashMap) arrayList.get(iV);
            }
        }
        return this.c4;
    }

    public final void h2(long j) {
        long j2 = j - this.G2;
        if (Math.abs(j2) > 1000) {
            this.F2 = true;
        }
        TextView textView = (TextView) this.B.findViewById(R.id.exo_position);
        if (textView != null) {
            StringBuilder sb = new StringBuilder();
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(ys2.L(sb, new Formatter(sb, Locale.getDefault()), j));
            if (this.F2 && L6) {
                int length = spannableStringBuilder.length();
                spannableStringBuilder.append((CharSequence) "  ").append((CharSequence) gt2.u(j2));
                spannableStringBuilder.setSpan(new ForegroundColorSpan(this.H0.g), length, spannableStringBuilder.length(), 33);
                spannableStringBuilder.setSpan(new AbsoluteSizeSpan(Math.round((textView.getTextSize() * 14.0f) / 16.0f)), length, spannableStringBuilder.length(), 33);
            }
            textView.setText(spannableStringBuilder);
        }
        O2(j);
    }

    public final void h3() {
        int i;
        boolean z;
        rl0 rl0Var;
        ArrayList arrayListI = I();
        gs1 gs1VarG0 = g0();
        int iI0 = i0();
        byte b = 0;
        boolean z2 = iI0 >= 0 && gs1VarG0.t.size() >= 2;
        if (arrayListI.size() >= 2 || z2) {
            ArrayList arrayList = new ArrayList();
            if (z2) {
                int i2 = 0;
                while (i2 < gs1VarG0.t.size()) {
                    js1 js1Var = (js1) gs1VarG0.t.get(i2);
                    int[] iArr = (int[]) this.t3.get(Integer.valueOf(g6.V()));
                    String string = null;
                    for (hs1 hs1Var : js1Var.c) {
                        if (string == null || S1(hs1Var.a) > S1(string)) {
                            string = hs1Var.a;
                        }
                    }
                    if (i2 == iI0 && iArr != null && iArr[0] == i2) {
                        string = string == null ? getString(iArr[1]) : getString(iArr[1]) + " · " + string;
                    }
                    arrayList.add(new l70(js1Var.a, string, i2 == iI0, new ko1(this, i2, b)));
                    i2++;
                }
                z = true;
                i = 2;
                if (arrayListI.size() >= 2) {
                    arrayList.add(new l70(getString(R.string.audio_in_file)));
                }
            } else {
                i = 2;
                z = true;
            }
            for (pq1 pq1Var : (!z2 || arrayListI.size() >= i) ? arrayListI : Collections.EMPTY_LIST) {
                boolean z3 = pq1Var.g;
                boolean z4 = pq1Var.e;
                String str = pq1Var.a;
                String string2 = pq1Var.b;
                if (z3) {
                    if (z4 && (rl0Var = this.r3) != null) {
                        if (rl0Var.equals(pq1Var.c.d[pq1Var.d])) {
                            int i3 = this.s3;
                            string2 = string2 == null ? getString(i3) : getString(i3) + " · " + string2;
                        }
                    }
                    arrayList.add(new l70(str, string2, z4, new kd(this, pq1Var, arrayListI, (byte) 24)));
                } else {
                    l70 l70Var = new l70(str, string2 == null ? getString(R.string.notice_track_unsupported) : string2 + " · " + getString(R.string.notice_track_unsupported), false, new lo1(this, b));
                    l70Var.h = z;
                    arrayList.add(l70Var);
                }
                z = true;
            }
            r2.q(this, this.V1, new lo1(this, (byte) 1), getString(z2 ? R.string.audio_voices_title : R.string.audio_title), arrayList);
        }
    }

    public final fp2 h4(rl0 rl0Var, ArrayList arrayList, boolean z) {
        String str = rl0Var.d;
        String[] strArr = gt2.a;
        fp2 fp2Var = fp2.f;
        String strD = x91.D(str);
        Iterator it = arrayList.iterator();
        int i = -1;
        int i2 = 0;
        while (it.hasNext()) {
            rl0 rl0Var2 = (rl0) it.next();
            if (i < 0 && rl0Var2.equals(rl0Var)) {
                i = i2;
            }
            if (Objects.equals(strD, x91.D(rl0Var2.d))) {
                i2++;
            }
        }
        if (strD == null) {
            strD = null;
        } else if (!z) {
            strD = rl0Var.d;
        }
        return new fp2(null, j4(rl0Var), Integer.valueOf(Math.max(0, i)), strD != null ? new String[]{strD} : null, Integer.valueOf(i2));
    }

    public final int i0() {
        mg0 mg0Var = g6;
        if (mg0Var == null) {
            return -1;
        }
        return Q4(mg0Var.V());
    }

    public final void i2(final wf0 wf0Var, final dj2 dj2Var, final String str) {
        final String strJ3 = J3(dj2Var);
        final String str2 = K3() ? "at-start" : "mid-stream";
        w3.b().p(wf0Var, new p3() { // from class: gp1
            @Override // io.sentry.p3
            public final void f(v0 v0Var) {
                LoudnessEnhancer loudnessEnhancer = PlayerActivity.e6;
                String str3 = str;
                String str4 = str3 != null ? "stuck-recovered" : "stuck";
                String str5 = strJ3;
                String str6 = str2;
                v0Var.i(Arrays.asList(str4, str5, str6));
                v0Var.g("player.stall_class", str5);
                v0Var.g("player.stall_when", str6);
                dj2 dj2Var2 = dj2Var;
                v0Var.g("player.stuck_type", dj2Var2 != null ? String.valueOf((int) dj2Var2.l) : "unknown");
                if (str3 != null) {
                    v0Var.g("player.stuck_recovery", str3);
                    v0Var.l(s4.INFO);
                }
                this.l.q0(wf0Var, v0Var);
            }
        });
    }

    public final void i3(wf0 wf0Var) {
        j3(v0(wf0Var), u0(wf0Var), wf0Var.l == 4001 ? H3(wf0Var.q) : null);
    }

    public final hp2 i4() {
        hp2 hp2Var = P6;
        if (hp2Var != null) {
            return hp2Var;
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
                    gt2.K("voice studios: " + e);
                    hp2 hp2Var2 = new hp2(arrayList, map, map2);
                    P6 = hp2Var2;
                    return hp2Var2;
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
            gt2.K("voice studios: " + e);
        }
        hp2 hp2Var3 = new hp2(arrayList, map, map2);
        P6 = hp2Var3;
        return hp2Var3;
    }

    public final void j() {
        if (this.K0 == null) {
            return;
        }
        boolean zK4 = K4();
        this.K0.setOrientation(zK4 ? 1 : 0);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.l1.getLayoutParams();
        layoutParams.width = zK4 ? -1 : -2;
        this.l1.setLayoutParams(layoutParams);
        LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) this.m1.getLayoutParams();
        layoutParams2.width = zK4 ? -1 : 0;
        layoutParams2.weight = zK4 ? 0.0f : 1.0f;
        layoutParams2.topMargin = zK4 ? this.V1.b(4.0f) : 0;
        this.m1.setLayoutParams(layoutParams2);
        s4();
        p4();
    }

    public final SpannableStringBuilder j0(String str) {
        if (str == null || str.isEmpty()) {
            return null;
        }
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        HashMap map = this.Q5;
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
                gt2.K("decoder kind: " + e);
            }
            map.put(str, boolValueOf);
        }
        d(spannableStringBuilder, getString(boolValueOf.booleanValue() ? R.string.stats_hardware : R.string.stats_software));
        f(spannableStringBuilder, str.replace(".decoder", ""));
        return spannableStringBuilder;
    }

    public final void j1(String str, String str2) {
        r0();
        zn2 zn2Var = this.V4;
        String str3 = this.H.C0;
        if (str3 == null || str3.isEmpty()) {
            str3 = Build.MODEL;
        }
        zn2Var.h(str, str2, str3);
        zn2Var.I = true;
        zn2Var.l.e();
    }

    public final void j2(String str) {
        StringBuilder sbK = we2.k("video freeze: ", str, " (");
        sbK.append(this.p0);
        sbK.append("/2)");
        gt2.K(sbK.toString());
        w3.b().q("Video frozen while audio plays", new yj((Object) this, (Object) str, (byte) 16));
    }

    public final void j3(String str, String str2, String str3) {
        this.Y = this.H.c;
        b7 b7Var = this.l3;
        if (b7Var != null) {
            b7Var.e = str;
        }
        mg0 mg0Var = g6;
        if (mg0Var != null && i6 && mg0Var.x()) {
            this.H.z(g6.O0());
        }
        Intent intentPutExtra = new Intent(this, (Class<?>) ErrorActivity.class).putExtra("title", getString(R.string.error_report_title)).putExtra("summary", str).putExtra("report", str2);
        if (str3 != null) {
            intentPutExtra.putExtra("message", str3);
        }
        startActivity(intentPutExtra);
    }

    public final String j4(rl0 rl0Var) {
        String str = rl0Var.b;
        if (str != null && !str.isEmpty() && (str == null || !str.matches("[a-z]{3}\\d{1,2}"))) {
            return str;
        }
        String str2 = rl0Var.a;
        if (str2 != null) {
            return (String) this.s.get(str2);
        }
        return null;
    }

    public final void k() {
        int iE4;
        if (this.p != null && (iE4 = e4(1)) >= 0) {
            i41 i41Var = this.p.c;
            ep2 ep2Var = i41Var == null ? null : i41Var.c[iE4];
            int iB = (ep2Var == null || this.y4 == null || ((rl0) this.u4.a) == null) ? -1 : ep2Var.b(this.y4);
            j50 j50VarD = this.p.d();
            if (iB < 0) {
                SparseArray sparseArray = j50VarD.S;
                Map map = (Map) sparseArray.get(iE4);
                if (map != null && !map.isEmpty()) {
                    sparseArray.remove(iE4);
                }
            } else {
                j50VarD.q(iE4, ep2Var, new l50(new int[]{this.z4}, iB));
            }
            j50VarD.p(iE4, this.G4);
            q50 q50Var = this.p;
            q50Var.getClass();
            q50Var.o(new k50(j50VarD));
        }
    }

    public final void k0() {
        if (g6 == null) {
            return;
        }
        U(null);
        if (this.J4 != null) {
            W();
            C4();
        }
        vj2 vj2Var = this.F4;
        if (vj2Var != null) {
            vj2Var.c();
        }
        vj2 vj2Var2 = this.r4;
        if (vj2Var2 != null) {
            vj2Var2.c();
        }
        this.G4 = true;
        int iE4 = this.p == null ? -1 : e4(1);
        if (iE4 < 0) {
            mg0 mg0Var = g6;
            k50 k50Var = (k50) mg0Var.A0();
            k50Var.getClass();
            j50 j50Var = new j50(k50Var);
            j50Var.d(3);
            j50Var.j(3, true);
            mg0Var.q0(j50Var.b());
            return;
        }
        j50 j50VarD = this.p.d();
        j50VarD.n();
        j50VarD.r();
        j50VarD.p(iE4, true);
        q50 q50Var = this.p;
        q50Var.getClass();
        q50Var.o(new k50(j50VarD));
    }

    public final void k2() {
        byte b;
        int i;
        float f;
        vt1 vt1Var = this.H;
        if (!vt1Var.y || vt1Var.C) {
            return;
        }
        Uri uriD0 = d0();
        String string = uriD0 != null ? uriD0.toString() : null;
        if (string == null || string.equals(this.M5)) {
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
            ip2 ip2Var = (ip2) it.next();
            if (ip2Var.c == 1) {
                f = ip2Var.d;
                if (f > 0.0f) {
                    i = ip2Var.e;
                    break;
                }
            }
        }
        if (f <= 0.0f) {
            return;
        }
        this.M5 = string;
        String[] strArr = gt2.a;
        runOnUiThread(new ct2(this, f, i, b));
    }

    public final void k3(String str) {
        ContextThemeWrapper contextThemeWrapperE = r2.e(this);
        ClipboardManager clipboardManager = (ClipboardManager) getSystemService("clipboard");
        if (clipboardManager != null) {
            clipboardManager.setPrimaryClip(ClipData.newPlainText("", str));
        }
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        Bitmap bitmapT = gt2.T((int) (Math.min(displayMetrics.widthPixels, displayMetrics.heightPixels) * 0.6f), str);
        if (bitmapT == null) {
            y3(getString(R.string.together_created, this.V4.f()), null);
            return;
        }
        ImageView imageView = new ImageView(contextThemeWrapperE);
        imageView.setImageBitmap(bitmapT);
        imageView.setAdjustViewBounds(true);
        int iRound = Math.round(displayMetrics.density * 16.0f);
        imageView.setPadding(iRound, iRound, iRound, iRound);
        r2.w(this, getString(R.string.together_qr_title, this.V4.f()), getString(R.string.together_qr_hint), imageView);
    }

    public final String[] k4(rl0 rl0Var, int i) {
        String string;
        String strJ4 = j4(rl0Var);
        String strM1 = m1(rl0Var.d);
        if (strJ4 == null || strJ4.isEmpty()) {
            string = (strM1 == null || strM1.isEmpty()) ? getString(R.string.audio_track_number, Integer.valueOf(i)) : strM1;
        } else {
            string = strJ4;
        }
        StringBuilder sb = new StringBuilder(qy.f(rl0Var, false));
        if (strJ4 != null && !strJ4.isEmpty() && strM1 != null && !strM1.isEmpty()) {
            if (sb.length() > 0) {
                sb.append(" · ");
            }
            sb.append(strM1);
        }
        return new String[]{string, sb.length() == 0 ? null : sb.toString()};
    }

    public final void l(rn2 rn2Var, int i, int i2, ArrayList arrayList) {
        String str = rn2Var.a;
        boolean z = rn2Var.b;
        this.Q3 = str;
        String str2 = (str == null || !str.equals(this.S3)) ? null : this.T3;
        this.R3 = str2;
        if (str2 == null && str != null) {
            Thread thread = new Thread(new kp1(this, str, z), "TitleImdb");
            thread.setDaemon(true);
            thread.start();
        }
        this.U3 = z;
        this.V3 = i;
        this.W3 = i2;
        mg0 mg0Var = g6;
        this.X3 = mg0Var != null ? mg0Var.V() : -1;
        this.Y3 = null;
        this.Z3 = -1;
        if (arrayList != null) {
            ArrayList arrayList2 = new ArrayList(arrayList.size());
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                qn2 qn2Var = (qn2) it.next();
                if (qn2Var.a > 0) {
                    arrayList2.add(qn2Var);
                }
            }
            this.Y3 = arrayList2;
            for (int i3 = 0; i3 < arrayList2.size(); i3++) {
                if (((qn2) arrayList2.get(i3)).a == i && ((qn2) arrayList2.get(i3)).b == i2) {
                    this.Z3 = i3;
                    break;
                }
            }
        }
        mg0 mg0Var2 = g6;
        if (mg0Var2 == null) {
            return;
        }
        if (!this.H.Y) {
            t1(mg0Var2.E(), true, null, this.C4);
            return;
        }
        boolean z2 = this.C4;
        String string = getString(R.string.subtitle_search_language_title);
        vt1 vt1Var = this.H;
        ArrayList arrayListC0 = gt2.c0(z2 ? vt1Var.V : vt1Var.U);
        LinkedHashMap linkedHashMapB = gt2.b();
        ArrayList arrayList3 = new ArrayList(Arrays.asList(gt2.v()));
        for (pq1 pq1Var : I()) {
            String str3 = pq1Var.f;
            if (str3 != null && !arrayList3.contains(str3)) {
                arrayList3.add(pq1Var.f);
            }
        }
        k20.p(this, string, R.string.pref_language_subtitle_none, R.string.pref_language_audio_add, arrayListC0, linkedHashMapB, arrayList3, new ag0((byte) 4, this, z2));
    }

    public final void l1(int i, WindowInsets windowInsets) {
        int iA;
        if (windowInsets != null) {
            int i2 = Build.VERSION.SDK_INT;
            if (i2 >= 31) {
                boolean zIsVisible = windowInsets.isVisible(WindowInsets.Type.statusBars());
                boolean z = getResources().getConfiguration().orientation == 2;
                lo1 lo1Var = this.U4;
                if (!zIsVisible || (C6 && (!z || L6))) {
                    this.B.removeCallbacks(lo1Var);
                } else {
                    this.B.postDelayed(lo1Var, 2500L);
                }
            }
            int systemWindowInsetLeft = windowInsets.getSystemWindowInsetLeft();
            int systemWindowInsetRight = windowInsets.getSystemWindowInsetRight();
            int iE = this.V1.e();
            int iMax = Math.max(Math.max(systemWindowInsetLeft, systemWindowInsetRight), this.V1.d());
            findViewById(R.id.exo_top).getLayoutParams().height = 0;
            boolean z2 = L6;
            as2 as2Var = this.V1;
            if (z2) {
                iA = as2Var.z() ? as2Var.a(32.0f) : as2Var.a(16.0f);
            } else {
                iA = iMax + (as2Var.z() ? as2Var.a(32.0f) : as2Var.a(16.0f));
            }
            boolean z3 = L6;
            as2 as2Var2 = this.V1;
            int iA2 = z3 ? as2Var2.a(12.0f) : Math.max(as2Var2.a(12.0f), windowInsets.getStableInsetBottom());
            View viewFindViewById = findViewById(R.id.exo_bottom_bar);
            int iJ = this.V1.j();
            as2 as2Var3 = this.V1;
            int iA3 = as2Var3.z() ? as2Var3.a(12.0f) : as2Var3.b(10.0f);
            int iJ2 = this.V1.j();
            as2 as2Var4 = this.V1;
            int iA4 = as2Var4.z() ? as2Var4.a(14.0f) : as2Var4.b(4.0f);
            String[] strArr = gt2.a;
            viewFindViewById.setPadding(iJ, iA3, iJ2, iA4);
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) viewFindViewById.getLayoutParams();
            layoutParams.setMargins(iA, 0, iA, iA2);
            viewFindViewById.setLayoutParams(layoutParams);
            as2 as2Var5 = this.V1;
            int iG = as2Var5.g() + (as2Var5.z() ? as2Var5.a(12.0f) : as2Var5.b(4.0f)) + as2Var5.l() + (as2Var5.z() ? as2Var5.a(12.0f) : as2Var5.b(10.0f));
            int iA5 = this.V1.a(8.0f) + (as2Var5.z() ? as2Var5.a(14.0f) : as2Var5.b(4.0f)) + iG + iA2;
            this.L0 = iA5;
            boolean z4 = L6;
            as2 as2Var6 = this.V1;
            int iD = z4 ? as2Var6.d() : iA + as2Var6.j();
            if (i2 >= 35) {
                findViewById(R.id.exo_left).getLayoutParams().width = windowInsets.getInsets(WindowInsets.Type.navigationBars()).left;
                findViewById(R.id.exo_right).getLayoutParams().width = windowInsets.getInsets(WindowInsets.Type.navigationBars()).right;
            }
            boolean z5 = getResources().getConfiguration().orientation == 2;
            int safeInsetTop = (i2 < 28 || windowInsets.getDisplayCutout() == null) ? 0 : windowInsets.getDisplayCutout().getSafeInsetTop();
            if (!z5 || L6) {
                safeInsetTop = i2 >= 30 ? Math.max(windowInsets.getSystemWindowInsetTop(), windowInsets.getInsetsIgnoringVisibility(WindowInsets.Type.statusBars()).top) : windowInsets.getSystemWindowInsetTop();
            }
            LinearLayout linearLayout = this.D0;
            linearLayout.setPadding(iD, iE + safeInsetTop + (L6 ? 0 : this.V1.a(12.0f)), iD, i);
            FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) linearLayout.getLayoutParams();
            layoutParams2.setMargins(0, 0, 0, 0);
            linearLayout.setLayoutParams(layoutParams2);
            View childAt = this.E0.getChildAt(0);
            childAt.getLayoutParams().height = this.V1.p() + safeInsetTop;
            childAt.setLayoutParams(childAt.getLayoutParams());
            Button button = this.d5;
            if (button != null) {
                px pxVar = (px) button.getLayoutParams();
                if (!K4()) {
                    ((ViewGroup.MarginLayoutParams) pxVar).bottomMargin = iA5;
                }
                ((ViewGroup.MarginLayoutParams) pxVar).rightMargin = iD;
                this.d5.setLayoutParams(pxVar);
            }
            TextView textView = this.s1;
            if (textView != null) {
                px pxVar2 = (px) textView.getLayoutParams();
                ((ViewGroup.MarginLayoutParams) pxVar2).bottomMargin = iA5;
                ((ViewGroup.MarginLayoutParams) pxVar2).leftMargin = iD;
                ((ViewGroup.MarginLayoutParams) pxVar2).rightMargin = iD;
                this.s1.setLayoutParams(pxVar2);
            }
            TextView textView2 = this.r1;
            if (textView2 != null) {
                px pxVar3 = (px) textView2.getLayoutParams();
                ((ViewGroup.MarginLayoutParams) pxVar3).leftMargin = iD;
                this.r1.setLayoutParams(pxVar3);
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
        if (g6 == null) {
            return;
        }
        float fP0 = p0(f);
        this.K = fP0;
        g6.g(fP0);
    }

    public final void l3(long j) {
        mg0 mg0Var = g6;
        long duration = mg0Var != null ? mg0Var.getDuration() : -9223372036854775807L;
        StringBuilder sb = new StringBuilder(gt2.t(j));
        if (duration > 0) {
            sb.append(" · ");
            sb.append(Math.round((j * 100.0f) / duration));
            sb.append('%');
        }
        ty tyVar = this.B;
        long j2 = j - tyVar.n0;
        tyVar.u(j2 < 0 ? R.drawable.ic_rewind_24dp : R.drawable.ic_fast_forward_24dp, gt2.u(j2) + "\n" + ((Object) sb));
    }

    public final List l4(String str) {
        if (!this.H.Z) {
            return Collections.EMPTY_LIST;
        }
        kk2[] kk2VarArr = lk2.a;
        ArrayList arrayList = new ArrayList(2);
        if ("ukr".equals(str)) {
            arrayList.add("rus");
        }
        if (!"eng".equals(str)) {
            arrayList.add("eng");
        }
        return arrayList;
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
        mg0 mg0Var = g6;
        if (mg0Var == null || mg0Var.E().a.isEmpty() || g6.E().a()) {
            return null;
        }
        for (ip2 ip2Var : b0()) {
            int i = ip2Var.c;
            String str = ip2Var.f;
            if (i == 1 && str != null) {
                return str.startsWith("V_") ? str.substring(2) : str;
            }
        }
        return null;
    }

    public final void m2() {
        this.j3 = false;
        this.k3 = null;
        this.l3 = null;
        this.m3 = null;
        this.n3 = null;
        this.o3 = null;
        this.p3.clear();
        this.q3.clear();
        this.u3 = null;
        this.v3 = null;
        this.M4 = false;
        this.N4 = false;
        this.w3 = null;
        this.x3 = null;
        this.y3 = null;
        this.z3 = null;
        this.A3.clear();
        this.B3.clear();
        this.C3 = 0;
        this.D3 = 0;
        this.E3 = null;
        this.u.clear();
        this.F3 = -1;
        this.G3 = -1;
        this.H3 = null;
        this.I3 = null;
        this.Q3 = null;
        this.R3 = null;
        this.U3 = false;
        this.V3 = -1;
        this.W3 = -1;
        this.X3 = -1;
        this.Y3 = null;
        this.Z3 = -1;
        this.J3.clear();
        this.K3.clear();
        this.L3.clear();
        this.M3.clear();
        this.N3.clear();
        this.O3.clear();
        this.P3.clear();
        this.b4.clear();
        this.c4 = new LinkedHashMap();
        this.d4 = (byte) 0;
        this.e4 = null;
        this.f4 = -1;
        this.g4 = 0;
        this.h4 = null;
        this.t3.clear();
        this.L4.clear();
        this.H.P0 = true;
        sx0 sx0Var = this.b5;
        if (sx0Var != null) {
            sx0Var.m = null;
            sx0Var.n = Collections.EMPTY_LIST;
        }
        this.c5 = false;
        this.t5 = false;
        this.v5 = false;
        this.z5 = -9223372036854775807L;
        this.A5 = -9223372036854775807L;
        this.B5 = -9223372036854775807L;
        this.C5 = -9223372036854775807L;
        this.F5 = -9223372036854775807L;
        N();
        P();
        W0();
        this.i4 = 0.0d;
        this.m4 = null;
        this.j4 = null;
        this.k4 = null;
        u();
        this.n4 = false;
        Dialog dialog = this.L1;
        if (dialog != null && dialog.isShowing()) {
            this.L1.dismiss();
        }
        Dialog dialog2 = this.M1;
        if (dialog2 != null && dialog2.isShowing()) {
            this.M1.dismiss();
        }
        this.o4 = 0.0d;
        vj2 vj2Var = this.F4;
        if (vj2Var != null) {
            vj2Var.e(0.0d);
        }
        W();
        this.G4 = false;
        Dialog dialog3 = this.N1;
        if (dialog3 != null && dialog3.isShowing()) {
            this.N1.dismiss();
        }
        CustomDefaultTimeBar customDefaultTimeBar = this.x2;
        if (customDefaultTimeBar != null) {
            customDefaultTimeBar.h();
        }
    }

    public final void m3() {
        int iV = g6.V();
        boolean z = iV < this.M3.size();
        Uri uri = z ? (Uri) this.M3.get(iV) : null;
        Uri uri2 = z ? (Uri) this.N3.get(iV) : null;
        CharSequence charSequence = iV < this.A3.size() ? ((p81) this.A3.get(iV)).d.a : null;
        if ((!"first".equals(this.H.A0) && !"every".equals(this.H.A0)) || (uri == null && (uri2 == null || TextUtils.isEmpty(charSequence)))) {
            this.T0.c();
            return;
        }
        this.B.c();
        v81 v81Var = this.T0;
        sl2 sl2Var = v81Var.B;
        ImageView imageView = v81Var.l;
        View view = v81Var.m;
        v81Var.animate().cancel();
        v81Var.setAlpha(1.0f);
        v81Var.setVisibility(0);
        v81Var.u.setVisibility(8);
        ValueAnimator valueAnimator = v81Var.w;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        v81Var.y = 0;
        v81Var.z = 0;
        v81Var.e(false);
        v81Var.t = charSequence;
        if (uri2 != null) {
            view.setVisibility(0);
            g02 g02VarD = com.bumptech.glide.a.d(v81Var.getContext().getApplicationContext());
            g02VarD.getClass();
            new zz1(g02VarD.l, g02VarD, Drawable.class, g02VarD.m).z(uri2).x(imageView);
        } else {
            com.bumptech.glide.a.d(v81Var.getContext().getApplicationContext()).m(imageView);
            view.setVisibility(8);
        }
        if (uri != null) {
            v81Var.f(false);
            g02 g02VarD2 = com.bumptech.glide.a.d(v81Var.getContext().getApplicationContext());
            g02VarD2.getClass();
            zz1 zz1VarZ = new zz1(g02VarD2.l, g02VarD2, Bitmap.class, g02VarD2.m).a(g02.v).z(uri);
            zz1VarZ.w(sl2Var, zz1VarZ);
        } else {
            com.bumptech.glide.a.d(v81Var.getContext().getApplicationContext()).l(sl2Var);
            v81Var.f(true);
        }
        if (!gt2.C(v81Var.getContext())) {
            v81Var.v.start();
        }
        this.d1 = false;
        this.c1 = false;
        this.Y0 = false;
        this.X0 = false;
        this.Z0 = 0L;
        this.b1 = 0;
        this.e1 = kp2.p.get();
        this.B.removeCallbacks(this.g1);
        this.B.post(this.g1);
    }

    public final l70 m4(xq1 xq1Var) {
        String string;
        String[] strArrK4 = k4(xq1Var.a, xq1Var.d);
        if (strArrK4[1] == null) {
            string = getString(R.string.notice_track_unsupported);
        } else {
            string = strArrK4[1] + " · " + getString(R.string.notice_track_unsupported);
        }
        l70 l70Var = new l70(strArrK4[0], string, false, new to1(this, (byte) 3));
        l70Var.h = true;
        return l70Var;
    }

    public final void n(int i, CharSequence charSequence) {
        Drawable drawable;
        this.p5 = true;
        this.d5.setText(charSequence);
        Button button = this.d5;
        if (i == 3) {
            drawable = this.f5;
        } else {
            drawable = i == 4 ? this.g5 : this.e5;
        }
        Drawable drawable2 = drawable;
        if ("ring".equals(this.H.o0)) {
            if (this.h5 == null || this.d6 != i) {
                int iP = gt2.p(28);
                uq1 uq1Var = new uq1(drawable2, lj.n(this, R.attr.accentSkip, lj.n(this, R.attr.colorPrimary, -1)), this.H0.e, gt2.p(2), gt2.p(6));
                this.h5 = uq1Var;
                uq1Var.setBounds(0, 0, iP, iP);
                this.d6 = i;
            }
            drawable2 = this.h5;
        } else {
            this.h5 = null;
            this.d6 = 0;
            if (i == 4 && drawable2 != null) {
                int i2 = this.k5;
                drawable2.setBounds(0, 0, i2, i2);
            } else if (i != 4) {
                drawable2 = null;
            }
        }
        button.setCompoundDrawablesRelative(drawable2, null, null, null);
        this.d5.setClickable(true);
        this.d5.setFocusable(true);
    }

    public final String n0(int i) {
        al alVarV1 = v1(i);
        String str = (String) alVarV1.o;
        String str2 = (String) alVarV1.n;
        if (str2 != null) {
            return "imdb:" + str2;
        }
        if (str == null) {
            return null;
        }
        return "tmdb:" + str;
    }

    public final void n1(String str, String str2, fp2 fp2Var, ArrayList arrayList) {
        if (fp2Var == null) {
            return;
        }
        String str3 = fp2Var.b;
        Integer num = fp2Var.a;
        if (num != null && num.intValue() >= 0) {
            gt2.K(str + "_index " + num + " (" + str2 + "): " + i4().e(arrayList, fp2Var));
        }
        if (str3 != null) {
            gt2.K(str + "_label \"" + str3 + "\" (" + str2 + "): not in the file");
        }
    }

    public final boolean n2() {
        ty tyVar = this.B;
        np1 np1Var = this.g2;
        tyVar.removeCallbacks(np1Var);
        ty tyVar2 = this.B;
        np1 np1Var2 = this.j2;
        tyVar2.removeCallbacks(np1Var2);
        mg0 mg0Var = g6;
        boolean z = mg0Var != null && mg0Var.J();
        vt1 vt1Var = this.H;
        boolean z2 = (vt1Var == null || vt1Var.H <= 0 || !i6 || d1() || this.Z4) ? false : true;
        if (z || z2) {
            getWindow().addFlags(128);
        } else {
            getWindow().clearFlags(128);
        }
        if (z2 && !z) {
            this.B.postDelayed(np1Var2, ((long) this.H.H) * 60000);
        }
        View view = this.f2;
        if (view == null) {
            return false;
        }
        boolean z3 = view.getVisibility() == 0;
        if (z3) {
            this.f2.animate().cancel();
            this.f2.animate().alpha(0.0f).setDuration(300L).withEndAction(new to1(this, (byte) 26));
        }
        if (z2 && !z) {
            this.B.postDelayed(np1Var, 60000L);
        }
        return z3;
    }

    public final void n3(CharSequence charSequence, boolean z, int i) {
        ty tyVar = this.B;
        if (tyVar != null) {
            tyVar.u(0, null);
        }
        me2 me2VarY = o61.y(this, charSequence, z, i);
        if (me2VarY == null) {
            Toast.makeText(this, charSequence, z ? 1 : 0).show();
        } else {
            me2VarY.g();
        }
    }

    public final void n4() {
        if (this.G1 == null || this.F1) {
            return;
        }
        gs1 gs1VarG0 = g0();
        int iI0 = i0();
        this.y1 = g6 != null && (I().size() >= 2 || (iI0 >= 0 && gs1VarG0.t.size() >= 2));
        q();
        rl0 rl0VarJ0 = J0();
        String strJ4 = rl0VarJ0 != null ? j4(rl0VarJ0) : null;
        String strM1 = rl0VarJ0 != null ? m1(rl0VarJ0.d) : null;
        if (iI0 >= 0) {
            strJ4 = ((js1) gs1VarG0.t.get(iI0)).a;
        } else if (strJ4 == null || strJ4.isEmpty()) {
            strJ4 = strM1 != null ? strM1 : getString(R.string.button_audio_track);
        }
        this.A1 = strJ4;
        this.G1.setText(A2() ? null : this.A1);
    }

    public final void o(float f) {
        this.i5 = f;
        uq1 uq1Var = this.h5;
        if (uq1Var != null) {
            float fMax = Math.max(0.0f, Math.min(1.0f, f));
            if (fMax != uq1Var.e) {
                uq1Var.e = fMax;
                uq1Var.invalidateSelf();
            }
        }
    }

    public final boolean o0() {
        Boolean bool = this.j4;
        return bool != null ? bool.booleanValue() : this.H.M;
    }

    public final String o1() {
        Uri uri = this.J4;
        if (uri == null) {
            uri = this.H.e;
        }
        if (uri != null) {
            String strO = zi0.o(uri);
            String[] strArr = gt2.a;
            fp2 fp2Var = fp2.f;
            String strD = x91.D(strO);
            if (strD != null) {
                return strD;
            }
        }
        mg0 mg0Var = g6;
        if (mg0Var == null) {
            return null;
        }
        fw0 fw0VarN = mg0Var.E().a.listIterator(0);
        while (fw0VarN.hasNext()) {
            wp2 wp2Var = (wp2) fw0VarN.next();
            if (wp2Var.b.c == 3) {
                for (int i = 0; i < wp2Var.a; i++) {
                    if (wp2Var.e[i]) {
                        String str = wp2Var.a(i).d;
                        String[] strArr2 = gt2.a;
                        fp2 fp2Var2 = fp2.f;
                        return x91.D(str);
                    }
                }
            }
        }
        return null;
    }

    public final void o2() {
        mg0 mg0Var;
        if (i6 && (mg0Var = g6) != null && mg0Var.J()) {
            this.B.setControllerShowTimeoutMs(3500);
        }
    }

    public final void o3(Dialog dialog) {
        this.T1 = true;
        this.B.c();
        m();
        dialog.setOnDismissListener(new a70(this, (byte) 1));
        dialog.show();
    }

    public final void o4() {
        if (g6 == null || this.o1 == null) {
            return;
        }
        boolean zF1 = f1();
        this.B.findViewById(R.id.exo_duration).setVisibility(!zF1 ? 0 : 8);
        if (!C6 && !this.G0) {
            this.o1.setVisibility(8);
            return;
        }
        TextView textView = this.o1;
        if (zF1) {
            textView.setText(R.string.time_live);
            this.o1.setTextColor(getColor(R.color.live_red));
            this.o1.setVisibility(0);
            return;
        }
        textView.setTextColor(this.J0.g);
        long duration = g6.getDuration();
        if (duration == -9223372036854775807L || duration <= 0) {
            this.o1.setVisibility(8);
            return;
        }
        long jMax = Math.max(0L, duration - g6.O0());
        float f = g6.getPlaybackParameters().a;
        if (f <= 0.0f) {
            f = 1.0f;
        }
        this.o1.setText(getString(R.string.time_ends_at, DateFormat.getTimeFormat(this).format(new Date(System.currentTimeMillis() + ((long) (jMax / f))))));
        this.o1.setVisibility(0);
    }

    @Override // android.app.Activity
    public final void onActivityResult(int i, int i2, Intent intent) {
        u2();
        if (i2 == -1 && this.Y2) {
            b2(true);
        }
        if (i == 10) {
            if (i2 == -1) {
                Uri data = intent.getData();
                try {
                    getContentResolver().takePersistableUriPermission(data, 3);
                    vt1 vt1Var = this.H;
                    vt1Var.g = data;
                    SharedPreferences.Editor editorEdit = vt1Var.b.edit();
                    if (data == null) {
                        editorEdit.remove("scopeUri");
                    } else {
                        editorEdit.putString("scopeUri", data.toString());
                    }
                    editorEdit.apply();
                    vt1 vt1Var2 = this.H;
                    vt1Var2.s = false;
                    SharedPreferences.Editor editorEdit2 = vt1Var2.b.edit();
                    editorEdit2.putBoolean("askScope", false);
                    editorEdit2.apply();
                    H2();
                } catch (SecurityException e) {
                    e.printStackTrace();
                }
            }
        } else if (i == 100) {
            HashMap map = this.B0;
            this.B0 = null;
            this.H.p();
            H6 = this.H.B0;
            p();
            v4();
            mg0 mg0Var = g6;
            if (mg0Var != null) {
                mg0Var.f(H6 ? 1.0f : Math.min(I6, 100.0f) / 100.0f);
            }
            F4(this);
            w4();
            B4();
            n2();
            HashMap mapV = this.H.v();
            if (this.I0) {
                return;
            }
            if (g6 != null && (map == null || !map.equals(mapV))) {
                this.K4 = true;
                b2(true);
                Z0();
            }
        } else {
            super.onActivityResult(i, i2, intent);
        }
        if (i2 == -1 && this.Y2) {
            Z0();
        }
    }

    @Override // android.app.Activity, android.view.ContextThemeWrapper
    public final void onApplyThemeResource(Resources.Theme theme, int i, boolean z) {
        super.onApplyThemeResource(theme, i, z);
        theme.applyStyle(vt1.a(this, false), true);
    }

    /* JADX WARN: Code restructure failed: missing block: B:31:0x007d, code lost:
    
        if (r13.H.G == false) goto L32;
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
            boolean r2 = com.brouken.player.PlayerActivity.M6
            r3 = 2131230958(0x7f0800ee, float:1.8077983E38)
            r4 = 2131952359(0x7f1302e7, float:1.9541159E38)
            r5 = 3000(0xbb8, double:1.482E-320)
            if (r2 == 0) goto L27
            long r7 = r13.d2
            long r7 = r0 - r7
            int r7 = (r7 > r5 ? 1 : (r7 == r5 ? 0 : -1))
            if (r7 <= 0) goto L27
            r13.d2 = r0
            r13.z3()
            ty r0 = r13.B
            java.lang.String r13 = r13.getString(r4)
            defpackage.r2.D(r0, r13, r3, r5)
            return
        L27:
            boolean r7 = com.brouken.player.PlayerActivity.L6
            r8 = 0
            if (r7 == 0) goto L9d
            boolean r7 = com.brouken.player.PlayerActivity.i6
            if (r7 == 0) goto L9d
            if (r2 != 0) goto L9d
            long r9 = r13.e2
            long r9 = r0 - r9
            int r2 = (r9 > r5 ? 1 : (r9 == r5 ? 0 : -1))
            if (r2 <= 0) goto L9d
            long r9 = r13.H2
            r11 = 0
            int r2 = (r9 > r11 ? 1 : (r9 == r11 ? 0 : -1))
            if (r2 < 0) goto L6f
            long r9 = r13.I2
            int r2 = (r9 > r11 ? 1 : (r9 == r11 ? 0 : -1))
            if (r2 < 0) goto L4a
            r2 = 1
            goto L4b
        L4a:
            r2 = r8
        L4b:
            r13.L()
            if (r2 == 0) goto L60
            mg0 r2 = com.brouken.player.PlayerActivity.g6
            if (r2 == 0) goto L60
            p82 r7 = defpackage.p82.c
            r2.t1(r7)
            mg0 r2 = com.brouken.player.PlayerActivity.g6
            long r9 = r13.J2
            r2.o1(r9)
        L60:
            ty r2 = r13.B
            sy r7 = r2.C0
            r2.removeCallbacks(r7)
            ty r2 = r13.B
            sy r2 = r2.C0
            r2.run()
            goto L7f
        L6f:
            boolean r2 = com.brouken.player.PlayerActivity.C6
            if (r2 == 0) goto L79
            ty r2 = r13.B
            r2.c()
            goto L7f
        L79:
            vt1 r2 = r13.H
            boolean r2 = r2.G
            if (r2 != 0) goto L9d
        L7f:
            r13.e2 = r0
            vt1 r0 = r13.H
            boolean r0 = r0.G
            if (r0 != 0) goto L9c
            v81 r0 = r13.T0
            boolean r0 = r0.isShown()
            if (r0 == 0) goto L93
            defpackage.o61.M(r13, r4, r8, r3)
            return
        L93:
            ty r0 = r13.B
            java.lang.String r13 = r13.getString(r4)
            defpackage.r2.D(r0, r13, r3, r5)
        L9c:
            return
        L9d:
            r13.A2 = r8
            super.onBackPressed()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.brouken.player.PlayerActivity.onBackPressed():void");
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        E4(configuration.orientation);
        p();
        j();
        if (C6 && !this.T1 && !this.G) {
            gt2.i0(this, this.B, true);
        }
        as2 as2Var = new as2(this, L6);
        as2 as2Var2 = this.V1;
        if (as2Var2 != null && as2Var.a == as2Var2.a && as2Var.d == as2Var2.d && as2Var.e == as2Var2.e) {
            return;
        }
        this.V1 = as2Var;
        Dialog[] dialogArr = {this.J1, this.K1, this.L1, this.N1, this.M1, this.O1, r2.a};
        for (int i = 0; i < 7; i++) {
            Dialog dialog = dialogArr[i];
            if (dialog != null && dialog.isShowing()) {
                dialog.dismiss();
            }
        }
        kr1 kr1Var = this.w2;
        if (kr1Var != null) {
            kr1Var.requestApplyInsets();
        }
    }

    /* JADX WARN: Code duplicated, block: B:27:0x009f  */
    @Override // android.app.Activity
    public final void onCreate(Bundle bundle) {
        Intent intent;
        Bundle bundle2;
        boolean z;
        Window window;
        PlayerActivity playerActivity = h6;
        if (playerActivity == null || playerActivity == this || playerActivity.isFinishing()) {
            intent = null;
            bundle2 = null;
        } else {
            intent = h6.getIntent();
            bundle2 = new Bundle();
            h6.B2(bundle2);
            h6.D2(bundle2);
            h6.N0();
        }
        vt1 vt1Var = new vt1(this);
        this.H = vt1Var;
        H6 = vt1Var.B0;
        I6 = vt1Var.q;
        F6 = 0.0f;
        final byte b = 0;
        G6 = false;
        if (getIntent().getData() != null || "android.intent.action.SEND".equals(getIntent().getAction()) || ks1.b(getIntent())) {
            gt2.a0(this, this.H.R0);
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
        boolean zF = gt2.F(this);
        L6 = zF;
        this.V1 = as2.c(this, zF);
        this.H0 = new pr(this, false);
        this.J0 = new pr(this, true);
        this.T0 = new v81(this);
        ((ViewGroup) findViewById(android.R.id.content)).addView(this.T0);
        this.U0 = bundle == null ? 1 : 0;
        if (bundle == null) {
            m6 = false;
        }
        final byte b3 = 2;
        if (i >= 33) {
            getOnBackInvokedDispatcher().registerOnBackInvokedCallback(0, new y7(this, b3));
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
                        K6 = true;
                    }
                }
            } else if (intent2.getData() != null || ks1.b(intent2)) {
                Q0(intent2);
            } else if (intent != null && (intent.getData() != null || ks1.b(intent))) {
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
        this.f2 = findViewById(R.id.dim_overlay);
        this.n = (AudioManager) getSystemService("audio");
        ty tyVar = (ty) findViewById(R.id.video_view);
        this.B = tyVar;
        View viewFindViewById = tyVar.findViewById(R.id.subtitle_secondary);
        viewFindViewById.addOnLayoutChangeListener(new View.OnLayoutChangeListener(this) { // from class: op1
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
                        LoudnessEnhancer loudnessEnhancer = PlayerActivity.e6;
                        playerActivity2.D0();
                        View view2 = (View) playerActivity2.k1.getParent();
                        int iB = playerActivity2.V1.b(2.0f);
                        if (view2 != null && playerActivity2.k1.getVisibility() == 0 && playerActivity2.o1.getVisibility() == 0) {
                            View view3 = playerActivity2.S0.getVisibility() == 0 ? playerActivity2.S0 : playerActivity2.R0;
                            int bottom = view3.getBottom() + ((ViewGroup.MarginLayoutParams) view3.getLayoutParams()).bottomMargin;
                            iB = Math.max(view3.getBottom() - bottom, (((playerActivity2.o1.getBaseline() + (playerActivity2.o1.getTop() + ((View) playerActivity2.o1.getParent()).getTop())) - view2.getTop()) - playerActivity2.k1.getBaseline()) - bottom);
                        }
                        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) playerActivity2.k1.getLayoutParams();
                        if (marginLayoutParams.topMargin != iB) {
                            marginLayoutParams.topMargin = iB;
                            playerActivity2.k1.setLayoutParams(marginLayoutParams);
                        }
                        break;
                    case 1:
                        LoudnessEnhancer loudnessEnhancer2 = PlayerActivity.e6;
                        if (i5 - i3 != i10 - i8) {
                            playerActivity2.G4();
                        }
                        break;
                    case 2:
                        LoudnessEnhancer loudnessEnhancer3 = PlayerActivity.e6;
                        if (playerActivity2.r1 != null) {
                            ViewGroup viewGroup = (ViewGroup) view;
                            int iMax = 0;
                            for (int i11 = 0; i11 < viewGroup.getChildCount() - 1; i11++) {
                                View childAt = viewGroup.getChildAt(i11);
                                if (childAt.getVisibility() != 8) {
                                    iMax = Math.max(iMax, childAt.getBottom());
                                }
                            }
                            int iB2 = playerActivity2.V1.b(8.0f) + i3 + iMax;
                            px pxVar = (px) playerActivity2.r1.getLayoutParams();
                            if (((ViewGroup.MarginLayoutParams) pxVar).topMargin != iB2) {
                                ((ViewGroup.MarginLayoutParams) pxVar).topMargin = iB2;
                                playerActivity2.r1.setLayoutParams(pxVar);
                            }
                            break;
                        }
                        break;
                    case VideoDecoderOutputBuffer.COLORSPACE_BT2020 /* 3 */:
                        LoudnessEnhancer loudnessEnhancer4 = PlayerActivity.e6;
                        playerActivity2.d4();
                        break;
                    case 4:
                        LoudnessEnhancer loudnessEnhancer5 = PlayerActivity.e6;
                        if (i4 - i2 != i9 - i7) {
                            playerActivity2.E1 = true;
                        }
                        break;
                    case 5:
                        LoudnessEnhancer loudnessEnhancer6 = PlayerActivity.e6;
                        if (i5 - i3 != i10 - i8 && playerActivity2.K4()) {
                            playerActivity2.D4();
                            break;
                        }
                        break;
                    default:
                        LoudnessEnhancer loudnessEnhancer7 = PlayerActivity.e6;
                        if (i5 - i3 != playerActivity2.v4) {
                            playerActivity2.D4();
                        }
                        break;
                }
            }
        });
        final byte b4 = 5;
        this.p4 = new a82((TextView) viewFindViewById, new np1(this, b4), this.E4);
        this.B.findViewById(R.id.exo_content_frame).addOnLayoutChangeListener(new View.OnLayoutChangeListener(this) { // from class: op1
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
                        LoudnessEnhancer loudnessEnhancer = PlayerActivity.e6;
                        playerActivity2.D0();
                        View view2 = (View) playerActivity2.k1.getParent();
                        int iB = playerActivity2.V1.b(2.0f);
                        if (view2 != null && playerActivity2.k1.getVisibility() == 0 && playerActivity2.o1.getVisibility() == 0) {
                            View view3 = playerActivity2.S0.getVisibility() == 0 ? playerActivity2.S0 : playerActivity2.R0;
                            int bottom = view3.getBottom() + ((ViewGroup.MarginLayoutParams) view3.getLayoutParams()).bottomMargin;
                            iB = Math.max(view3.getBottom() - bottom, (((playerActivity2.o1.getBaseline() + (playerActivity2.o1.getTop() + ((View) playerActivity2.o1.getParent()).getTop())) - view2.getTop()) - playerActivity2.k1.getBaseline()) - bottom);
                        }
                        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) playerActivity2.k1.getLayoutParams();
                        if (marginLayoutParams.topMargin != iB) {
                            marginLayoutParams.topMargin = iB;
                            playerActivity2.k1.setLayoutParams(marginLayoutParams);
                        }
                        break;
                    case 1:
                        LoudnessEnhancer loudnessEnhancer2 = PlayerActivity.e6;
                        if (i5 - i3 != i10 - i8) {
                            playerActivity2.G4();
                        }
                        break;
                    case 2:
                        LoudnessEnhancer loudnessEnhancer3 = PlayerActivity.e6;
                        if (playerActivity2.r1 != null) {
                            ViewGroup viewGroup = (ViewGroup) view;
                            int iMax = 0;
                            for (int i11 = 0; i11 < viewGroup.getChildCount() - 1; i11++) {
                                View childAt = viewGroup.getChildAt(i11);
                                if (childAt.getVisibility() != 8) {
                                    iMax = Math.max(iMax, childAt.getBottom());
                                }
                            }
                            int iB2 = playerActivity2.V1.b(8.0f) + i3 + iMax;
                            px pxVar = (px) playerActivity2.r1.getLayoutParams();
                            if (((ViewGroup.MarginLayoutParams) pxVar).topMargin != iB2) {
                                ((ViewGroup.MarginLayoutParams) pxVar).topMargin = iB2;
                                playerActivity2.r1.setLayoutParams(pxVar);
                            }
                            break;
                        }
                        break;
                    case VideoDecoderOutputBuffer.COLORSPACE_BT2020 /* 3 */:
                        LoudnessEnhancer loudnessEnhancer4 = PlayerActivity.e6;
                        playerActivity2.d4();
                        break;
                    case 4:
                        LoudnessEnhancer loudnessEnhancer5 = PlayerActivity.e6;
                        if (i4 - i2 != i9 - i7) {
                            playerActivity2.E1 = true;
                        }
                        break;
                    case 5:
                        LoudnessEnhancer loudnessEnhancer6 = PlayerActivity.e6;
                        if (i5 - i3 != i10 - i8 && playerActivity2.K4()) {
                            playerActivity2.D4();
                            break;
                        }
                        break;
                    default:
                        LoudnessEnhancer loudnessEnhancer7 = PlayerActivity.e6;
                        if (i5 - i3 != playerActivity2.v4) {
                            playerActivity2.D4();
                        }
                        break;
                }
            }
        });
        View viewFindViewById2 = this.B.findViewById(R.id.exo_subtitles);
        final byte b5 = 6;
        if (viewFindViewById2 != null) {
            viewFindViewById2.addOnLayoutChangeListener(new View.OnLayoutChangeListener(this) { // from class: op1
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
                            LoudnessEnhancer loudnessEnhancer = PlayerActivity.e6;
                            playerActivity2.D0();
                            View view2 = (View) playerActivity2.k1.getParent();
                            int iB = playerActivity2.V1.b(2.0f);
                            if (view2 != null && playerActivity2.k1.getVisibility() == 0 && playerActivity2.o1.getVisibility() == 0) {
                                View view3 = playerActivity2.S0.getVisibility() == 0 ? playerActivity2.S0 : playerActivity2.R0;
                                int bottom = view3.getBottom() + ((ViewGroup.MarginLayoutParams) view3.getLayoutParams()).bottomMargin;
                                iB = Math.max(view3.getBottom() - bottom, (((playerActivity2.o1.getBaseline() + (playerActivity2.o1.getTop() + ((View) playerActivity2.o1.getParent()).getTop())) - view2.getTop()) - playerActivity2.k1.getBaseline()) - bottom);
                            }
                            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) playerActivity2.k1.getLayoutParams();
                            if (marginLayoutParams.topMargin != iB) {
                                marginLayoutParams.topMargin = iB;
                                playerActivity2.k1.setLayoutParams(marginLayoutParams);
                            }
                            break;
                        case 1:
                            LoudnessEnhancer loudnessEnhancer2 = PlayerActivity.e6;
                            if (i5 - i3 != i10 - i8) {
                                playerActivity2.G4();
                            }
                            break;
                        case 2:
                            LoudnessEnhancer loudnessEnhancer3 = PlayerActivity.e6;
                            if (playerActivity2.r1 != null) {
                                ViewGroup viewGroup = (ViewGroup) view;
                                int iMax = 0;
                                for (int i11 = 0; i11 < viewGroup.getChildCount() - 1; i11++) {
                                    View childAt = viewGroup.getChildAt(i11);
                                    if (childAt.getVisibility() != 8) {
                                        iMax = Math.max(iMax, childAt.getBottom());
                                    }
                                }
                                int iB2 = playerActivity2.V1.b(8.0f) + i3 + iMax;
                                px pxVar = (px) playerActivity2.r1.getLayoutParams();
                                if (((ViewGroup.MarginLayoutParams) pxVar).topMargin != iB2) {
                                    ((ViewGroup.MarginLayoutParams) pxVar).topMargin = iB2;
                                    playerActivity2.r1.setLayoutParams(pxVar);
                                }
                                break;
                            }
                            break;
                        case VideoDecoderOutputBuffer.COLORSPACE_BT2020 /* 3 */:
                            LoudnessEnhancer loudnessEnhancer4 = PlayerActivity.e6;
                            playerActivity2.d4();
                            break;
                        case 4:
                            LoudnessEnhancer loudnessEnhancer5 = PlayerActivity.e6;
                            if (i4 - i2 != i9 - i7) {
                                playerActivity2.E1 = true;
                            }
                            break;
                        case 5:
                            LoudnessEnhancer loudnessEnhancer6 = PlayerActivity.e6;
                            if (i5 - i3 != i10 - i8 && playerActivity2.K4()) {
                                playerActivity2.D4();
                                break;
                            }
                            break;
                        default:
                            LoudnessEnhancer loudnessEnhancer7 = PlayerActivity.e6;
                            if (i5 - i3 != playerActivity2.v4) {
                                playerActivity2.D4();
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
        this.n2 = imageButton;
        imageButton.setBackground(new InsetDrawable((Drawable) gt2.b0(this.H0.b, 10000.0f), this.V1.b(10.0f)));
        ViewGroup.LayoutParams layoutParams = this.n2.getLayoutParams();
        layoutParams.width = this.V1.b(90.0f);
        layoutParams.height = this.V1.b(90.0f);
        this.n2.setLayoutParams(layoutParams);
        this.n2.setImageTintList(ColorStateList.valueOf(this.H0.n));
        ImageButton imageButton2 = this.n2;
        ImageView.ScaleType scaleType = ImageView.ScaleType.FIT_CENTER;
        imageButton2.setScaleType(scaleType);
        this.n2.setPadding(0, 0, 0, 0);
        this.n2.setForeground(gt2.j(this, this.V1.b(10.0f), this.V1.b(10.0f), this.H0.j));
        CircularProgressIndicator circularProgressIndicator = (CircularProgressIndicator) findViewById(R.id.loading);
        this.r2 = circularProgressIndicator;
        circularProgressIndicator.setIndicatorSize(this.V1.b(60.0f));
        this.r2.setIndicatorInset(0);
        this.r2.setIndicatorColor(this.H0.n);
        this.r2.setTrackColor(this.H0.k);
        if (i >= 26) {
            this.r2.setDefaultFocusHighlightEnabled(false);
        }
        TextView textView2 = (TextView) findViewById(R.id.loading_speed);
        this.s2 = textView2;
        textView2.setTextSize(2, this.V1.q(13.0f, 14.0f, 14.0f, 15.0f));
        this.s2.setTextColor(this.H0.f);
        this.s2.setBackground(gt2.b0(this.H0.b, this.V1.b(8.0f)));
        this.s2.setPadding(this.V1.a(10.0f), this.V1.a(4.0f), this.V1.a(10.0f), this.V1.a(4.0f));
        FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) this.s2.getLayoutParams();
        layoutParams2.topMargin = this.V1.b(12.0f) + (this.V1.b(60.0f) / 2);
        this.s2.setLayoutParams(layoutParams2);
        this.o2 = (ImageButton) findViewById(R.id.exo_prev);
        this.p2 = (ImageButton) findViewById(R.id.exo_next);
        c3();
        this.B.setShowNextButton(false);
        this.B.setShowPreviousButton(false);
        this.B.setShowFastForwardButton(false);
        this.B.setShowRewindButton(false);
        this.B.setRepeatToggleModes(1);
        this.B.setControllerHideOnTouch(false);
        this.B.setControllerAutoShow(true);
        ((DoubleTapPlayerView) this.B).setDoubleTapEnabled(false);
        CustomDefaultTimeBar customDefaultTimeBar = (CustomDefaultTimeBar) this.B.findViewById(R.id.exo_progress);
        this.x2 = customDefaultTimeBar;
        nq1 nq1Var = new nq1(this);
        customDefaultTimeBar.getClass();
        customDefaultTimeBar.F.add(nq1Var);
        ImageButton imageButton3 = new ImageButton(this, null, 0, R.style.ExoStyledControls_Button_Bottom);
        this.v1 = imageButton3;
        imageButton3.setImageResource(R.drawable.ic_playlist_24dp);
        this.v1.setId(View.generateViewId());
        this.v1.setContentDescription("Playlist");
        this.v1.setVisibility(8);
        this.v1.setOnClickListener(new vo1(this, (byte) 12));
        TextView textViewI1 = I1(0, getString(R.string.button_quality));
        this.w1 = textViewI1;
        textViewI1.setOnClickListener(new vo1(this, (byte) 13));
        TextView textViewI2 = I1(R.drawable.ic_audiotrack_plate_24dp, getString(R.string.button_audio_track));
        this.G1 = textViewI2;
        textViewI2.setOnClickListener(new vo1(this, (byte) 14));
        TextView textViewI3 = I1(R.drawable.ic_subtitles_24dp, getString(R.string.subtitle_title));
        this.H1 = textViewI3;
        textViewI3.setCompoundDrawableTintList(this.H0.p);
        this.H1.setOnClickListener(new vo1(this, (byte) 15));
        final byte b6 = 4;
        this.H1.setOnLongClickListener(new View.OnLongClickListener(this) { // from class: mp1
            public final /* synthetic */ PlayerActivity m;

            {
                this.m = this;
            }

            @Override // android.view.View.OnLongClickListener
            public final boolean onLongClick(View view) {
                byte b7 = b6;
                PlayerActivity playerActivity2 = this.m;
                switch (b7) {
                    case DecoderInputBuffer.BUFFER_REPLACEMENT_MODE_DISABLED /* 0 */:
                        LoudnessEnhancer loudnessEnhancer = PlayerActivity.e6;
                        playerActivity2.A1(null);
                        return true;
                    case 1:
                        LoudnessEnhancer loudnessEnhancer2 = PlayerActivity.e6;
                        playerActivity2.y0 = true;
                        if (playerActivity2.B.getResizeMode() != 4) {
                            playerActivity2.B.setResizeMode(4);
                        }
                        playerActivity2.A0 = playerActivity2.B.getVideoSurfaceView().getScaleX();
                        ty tyVar2 = playerActivity2.B;
                        tyVar2.removeCallbacks(tyVar2.C0);
                        playerActivity2.B.u(R.drawable.ic_fit_screen_24dp, ((int) (playerActivity2.A0 * 100.0f)) + "%");
                        playerActivity2.B.c();
                        playerActivity2.z0 = true;
                        playerActivity2.J4();
                        return true;
                    case 2:
                        LoudnessEnhancer loudnessEnhancer3 = PlayerActivity.e6;
                        playerActivity2.g3();
                        return true;
                    case VideoDecoderOutputBuffer.COLORSPACE_BT2020 /* 3 */:
                        if (PlayerActivity.M6) {
                            return false;
                        }
                        playerActivity2.w3();
                        return true;
                    default:
                        LoudnessEnhancer loudnessEnhancer4 = PlayerActivity.e6;
                        playerActivity2.A1("subtitlesScreen");
                        return true;
                }
            }
        });
        ImageButton imageButton4 = new ImageButton(this, null, 0, R.style.ExoStyledControls_Button_Bottom);
        this.I1 = imageButton4;
        imageButton4.setImageResource(R.drawable.ic_more_vert_24dp);
        this.I1.setId(View.generateViewId());
        this.I1.setContentDescription(getString(R.string.button_more));
        this.I1.setOnClickListener(new vo1(this, b3));
        this.I1.setOnLongClickListener(new View.OnLongClickListener(this) { // from class: mp1
            public final /* synthetic */ PlayerActivity m;

            {
                this.m = this;
            }

            @Override // android.view.View.OnLongClickListener
            public final boolean onLongClick(View view) {
                byte b7 = b;
                PlayerActivity playerActivity2 = this.m;
                switch (b7) {
                    case DecoderInputBuffer.BUFFER_REPLACEMENT_MODE_DISABLED /* 0 */:
                        LoudnessEnhancer loudnessEnhancer = PlayerActivity.e6;
                        playerActivity2.A1(null);
                        return true;
                    case 1:
                        LoudnessEnhancer loudnessEnhancer2 = PlayerActivity.e6;
                        playerActivity2.y0 = true;
                        if (playerActivity2.B.getResizeMode() != 4) {
                            playerActivity2.B.setResizeMode(4);
                        }
                        playerActivity2.A0 = playerActivity2.B.getVideoSurfaceView().getScaleX();
                        ty tyVar2 = playerActivity2.B;
                        tyVar2.removeCallbacks(tyVar2.C0);
                        playerActivity2.B.u(R.drawable.ic_fit_screen_24dp, ((int) (playerActivity2.A0 * 100.0f)) + "%");
                        playerActivity2.B.c();
                        playerActivity2.z0 = true;
                        playerActivity2.J4();
                        return true;
                    case 2:
                        LoudnessEnhancer loudnessEnhancer3 = PlayerActivity.e6;
                        playerActivity2.g3();
                        return true;
                    case VideoDecoderOutputBuffer.COLORSPACE_BT2020 /* 3 */:
                        if (PlayerActivity.M6) {
                            return false;
                        }
                        playerActivity2.w3();
                        return true;
                    default:
                        LoudnessEnhancer loudnessEnhancer4 = PlayerActivity.e6;
                        playerActivity2.A1("subtitlesScreen");
                        return true;
                }
            }
        });
        ImageButton imageButton5 = new ImageButton(this, null, 0, R.style.ExoStyledControls_Button_Bottom);
        this.l2 = imageButton5;
        imageButton5.setImageResource(R.drawable.ic_update_24dp);
        this.l2.setId(View.generateViewId());
        this.l2.setContentDescription(getString(R.string.button_update));
        this.l2.setVisibility(8);
        this.l2.setOnClickListener(new vo1(this, (byte) 3));
        if (gt2.B(this)) {
            this.F = ic.a();
            if (x4(R.drawable.ic_play_arrow_24dp, R.string.exo_controls_play_description, 1, 1)) {
                ImageButton imageButton6 = new ImageButton(this, null, 0, R.style.ExoStyledControls_Button_Bottom);
                this.W1 = imageButton6;
                imageButton6.setContentDescription(getString(R.string.button_pip));
                this.W1.setImageResource(R.drawable.ic_picture_in_picture_alt_24dp);
                this.W1.setOnClickListener(new vo1(this, b6));
            }
        }
        ImageButton imageButton7 = new ImageButton(this, null, 0, R.style.ExoStyledControls_Button_Bottom);
        this.X1 = imageButton7;
        imageButton7.setId(2147483547);
        this.X1.setContentDescription(getString(R.string.button_crop));
        J4();
        this.X1.setOnClickListener(new vo1(this, b4));
        if (!L6 || i < 24) {
            this.X1.setOnLongClickListener(new View.OnLongClickListener(this) { // from class: mp1
                public final /* synthetic */ PlayerActivity m;

                {
                    this.m = this;
                }

                @Override // android.view.View.OnLongClickListener
                public final boolean onLongClick(View view) {
                    byte b7 = b3;
                    PlayerActivity playerActivity2 = this.m;
                    switch (b7) {
                        case DecoderInputBuffer.BUFFER_REPLACEMENT_MODE_DISABLED /* 0 */:
                            LoudnessEnhancer loudnessEnhancer = PlayerActivity.e6;
                            playerActivity2.A1(null);
                            return true;
                        case 1:
                            LoudnessEnhancer loudnessEnhancer2 = PlayerActivity.e6;
                            playerActivity2.y0 = true;
                            if (playerActivity2.B.getResizeMode() != 4) {
                                playerActivity2.B.setResizeMode(4);
                            }
                            playerActivity2.A0 = playerActivity2.B.getVideoSurfaceView().getScaleX();
                            ty tyVar2 = playerActivity2.B;
                            tyVar2.removeCallbacks(tyVar2.C0);
                            playerActivity2.B.u(R.drawable.ic_fit_screen_24dp, ((int) (playerActivity2.A0 * 100.0f)) + "%");
                            playerActivity2.B.c();
                            playerActivity2.z0 = true;
                            playerActivity2.J4();
                            return true;
                        case 2:
                            LoudnessEnhancer loudnessEnhancer3 = PlayerActivity.e6;
                            playerActivity2.g3();
                            return true;
                        case VideoDecoderOutputBuffer.COLORSPACE_BT2020 /* 3 */:
                            if (PlayerActivity.M6) {
                                return false;
                            }
                            playerActivity2.w3();
                            return true;
                        default:
                            LoudnessEnhancer loudnessEnhancer4 = PlayerActivity.e6;
                            playerActivity2.A1("subtitlesScreen");
                            return true;
                    }
                }
            });
        } else {
            this.X1.setOnLongClickListener(new View.OnLongClickListener(this) { // from class: mp1
                public final /* synthetic */ PlayerActivity m;

                {
                    this.m = this;
                }

                @Override // android.view.View.OnLongClickListener
                public final boolean onLongClick(View view) {
                    byte b7 = b2;
                    PlayerActivity playerActivity2 = this.m;
                    switch (b7) {
                        case DecoderInputBuffer.BUFFER_REPLACEMENT_MODE_DISABLED /* 0 */:
                            LoudnessEnhancer loudnessEnhancer = PlayerActivity.e6;
                            playerActivity2.A1(null);
                            return true;
                        case 1:
                            LoudnessEnhancer loudnessEnhancer2 = PlayerActivity.e6;
                            playerActivity2.y0 = true;
                            if (playerActivity2.B.getResizeMode() != 4) {
                                playerActivity2.B.setResizeMode(4);
                            }
                            playerActivity2.A0 = playerActivity2.B.getVideoSurfaceView().getScaleX();
                            ty tyVar2 = playerActivity2.B;
                            tyVar2.removeCallbacks(tyVar2.C0);
                            playerActivity2.B.u(R.drawable.ic_fit_screen_24dp, ((int) (playerActivity2.A0 * 100.0f)) + "%");
                            playerActivity2.B.c();
                            playerActivity2.z0 = true;
                            playerActivity2.J4();
                            return true;
                        case 2:
                            LoudnessEnhancer loudnessEnhancer3 = PlayerActivity.e6;
                            playerActivity2.g3();
                            return true;
                        case VideoDecoderOutputBuffer.COLORSPACE_BT2020 /* 3 */:
                            if (PlayerActivity.M6) {
                                return false;
                            }
                            playerActivity2.w3();
                            return true;
                        default:
                            LoudnessEnhancer loudnessEnhancer4 = PlayerActivity.e6;
                            playerActivity2.A1("subtitlesScreen");
                            return true;
                    }
                }
            });
        }
        ImageButton imageButton8 = new ImageButton(this, null, 0, R.style.ExoStyledControls_Button_Bottom);
        this.a2 = imageButton8;
        imageButton8.setContentDescription(getString(R.string.button_rotate));
        this.a2.setImageResource(R.drawable.ic_screen_rotation_24dp);
        this.a2.setOnClickListener(new vo1(this, b5));
        ImageButton imageButton9 = new ImageButton(this, null, 0, R.style.ExoStyledControls_Button_Bottom);
        this.b2 = imageButton9;
        imageButton9.setImageResource(R.drawable.ic_lock_24dp);
        this.b2.setImageTintList(this.H0.p);
        this.b2.setId(View.generateViewId());
        this.b2.setContentDescription(getString(R.string.button_lock));
        this.b2.setOnClickListener(new vo1(this, (byte) 7));
        final int dimensionPixelOffset = getResources().getDimensionPixelOffset(R.dimen.exo_styled_bottom_bar_time_padding);
        FrameLayout frameLayout = new FrameLayout(this);
        this.E0 = frameLayout;
        frameLayout.setVisibility(4);
        this.E0.setAlpha(0.0f);
        View view = new View(this);
        int iP = this.V1.p();
        ShapeDrawable shapeDrawable = new ShapeDrawable(new RectShape());
        shapeDrawable.setShaderFactory(new bq1(this));
        view.setBackground(shapeDrawable);
        this.E0.addView(view, new FrameLayout.LayoutParams(-1, iP));
        LinearLayout linearLayout = new LinearLayout(this);
        this.D0 = linearLayout;
        linearLayout.setOrientation(0);
        this.D0.setGravity(48);
        this.D0.setLayoutParams(new FrameLayout.LayoutParams(-1, -2));
        this.D0.setClipChildren(false);
        this.D0.addOnLayoutChangeListener(new View.OnLayoutChangeListener(this) { // from class: op1
            public final /* synthetic */ PlayerActivity b;

            {
                this.b = this;
            }

            @Override // android.view.View.OnLayoutChangeListener
            public final void onLayoutChange(View view2, int i2, int i3, int i4, int i5, int i7, int i8, int i9, int i10) {
                byte b7 = b;
                PlayerActivity playerActivity2 = this.b;
                switch (b7) {
                    case DecoderInputBuffer.BUFFER_REPLACEMENT_MODE_DISABLED /* 0 */:
                        LoudnessEnhancer loudnessEnhancer = PlayerActivity.e6;
                        playerActivity2.D0();
                        View view3 = (View) playerActivity2.k1.getParent();
                        int iB = playerActivity2.V1.b(2.0f);
                        if (view3 != null && playerActivity2.k1.getVisibility() == 0 && playerActivity2.o1.getVisibility() == 0) {
                            View view4 = playerActivity2.S0.getVisibility() == 0 ? playerActivity2.S0 : playerActivity2.R0;
                            int bottom = view4.getBottom() + ((ViewGroup.MarginLayoutParams) view4.getLayoutParams()).bottomMargin;
                            iB = Math.max(view4.getBottom() - bottom, (((playerActivity2.o1.getBaseline() + (playerActivity2.o1.getTop() + ((View) playerActivity2.o1.getParent()).getTop())) - view3.getTop()) - playerActivity2.k1.getBaseline()) - bottom);
                        }
                        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) playerActivity2.k1.getLayoutParams();
                        if (marginLayoutParams.topMargin != iB) {
                            marginLayoutParams.topMargin = iB;
                            playerActivity2.k1.setLayoutParams(marginLayoutParams);
                        }
                        break;
                    case 1:
                        LoudnessEnhancer loudnessEnhancer2 = PlayerActivity.e6;
                        if (i5 - i3 != i10 - i8) {
                            playerActivity2.G4();
                        }
                        break;
                    case 2:
                        LoudnessEnhancer loudnessEnhancer3 = PlayerActivity.e6;
                        if (playerActivity2.r1 != null) {
                            ViewGroup viewGroup = (ViewGroup) view2;
                            int iMax = 0;
                            for (int i11 = 0; i11 < viewGroup.getChildCount() - 1; i11++) {
                                View childAt = viewGroup.getChildAt(i11);
                                if (childAt.getVisibility() != 8) {
                                    iMax = Math.max(iMax, childAt.getBottom());
                                }
                            }
                            int iB2 = playerActivity2.V1.b(8.0f) + i3 + iMax;
                            px pxVar = (px) playerActivity2.r1.getLayoutParams();
                            if (((ViewGroup.MarginLayoutParams) pxVar).topMargin != iB2) {
                                ((ViewGroup.MarginLayoutParams) pxVar).topMargin = iB2;
                                playerActivity2.r1.setLayoutParams(pxVar);
                            }
                            break;
                        }
                        break;
                    case VideoDecoderOutputBuffer.COLORSPACE_BT2020 /* 3 */:
                        LoudnessEnhancer loudnessEnhancer4 = PlayerActivity.e6;
                        playerActivity2.d4();
                        break;
                    case 4:
                        LoudnessEnhancer loudnessEnhancer5 = PlayerActivity.e6;
                        if (i4 - i2 != i9 - i7) {
                            playerActivity2.E1 = true;
                        }
                        break;
                    case 5:
                        LoudnessEnhancer loudnessEnhancer6 = PlayerActivity.e6;
                        if (i5 - i3 != i10 - i8 && playerActivity2.K4()) {
                            playerActivity2.D4();
                            break;
                        }
                        break;
                    default:
                        LoudnessEnhancer loudnessEnhancer7 = PlayerActivity.e6;
                        if (i5 - i3 != playerActivity2.v4) {
                            playerActivity2.D4();
                        }
                        break;
                }
            }
        });
        this.N0 = new FrameLayout(this);
        LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(-2, this.V1.m());
        layoutParams3.setMarginEnd(this.V1.b(16.0f));
        layoutParams3.gravity = 48;
        this.N0.setLayoutParams(layoutParams3);
        this.N0.setBackgroundColor(getColor(R.color.placeholder_card));
        int iP2 = gt2.p(4);
        this.N0.setClipToOutline(true);
        this.N0.setOutlineProvider(new i70(iP2, (byte) 1));
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
        this.P0.setMinWidth(gt2.p(54));
        this.P0.setGravity(17);
        this.P0.setTextColor(getColor(R.color.ink_tertiary));
        this.P0.setTextSize(2, this.V1.w());
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
        layoutParams4.setMarginEnd(gt2.p(16));
        linearLayout2.setLayoutParams(layoutParams4);
        TextView textView5 = new TextView(this);
        this.R0 = textView5;
        textView5.setTextColor(this.J0.e);
        this.R0.setTypeface(Typeface.create("sans-serif-medium", 0));
        this.R0.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
        this.R0.setTextSize(2, this.V1.u());
        this.R0.setMaxLines(1);
        TextView textView6 = this.R0;
        TextUtils.TruncateAt truncateAt = TextUtils.TruncateAt.END;
        textView6.setEllipsize(truncateAt);
        this.R0.setTextDirection(5);
        this.R0.setIncludeFontPadding(false);
        this.h1 = (this.V1.m() * 18) / 25;
        this.i1 = (this.V1.m() * 16) / 5;
        this.S0 = new ImageView(this);
        LinearLayout.LayoutParams layoutParams5 = new LinearLayout.LayoutParams(-2, -2);
        layoutParams5.bottomMargin = this.V1.b(4.0f);
        this.S0.setLayoutParams(layoutParams5);
        this.S0.setAdjustViewBounds(true);
        this.S0.setMaxHeight(this.h1);
        this.S0.setMaxWidth(this.i1);
        this.S0.setScaleType(ImageView.ScaleType.FIT_START);
        this.S0.setVisibility(8);
        linearLayout2.addView(this.S0);
        linearLayout2.addView(this.R0);
        TextView textViewY = Y(this.V1.b(2.0f));
        this.k1 = textViewY;
        textViewY.setTextColor(this.J0.f);
        this.k1.setTextSize(2, this.V1.t());
        linearLayout2.addView(this.k1);
        this.K0 = new LinearLayout(this);
        LinearLayout.LayoutParams layoutParams6 = new LinearLayout.LayoutParams(-1, -2);
        layoutParams6.topMargin = this.V1.b(6.0f);
        linearLayout2.addView(this.K0, layoutParams6);
        TextView textViewY2 = Y(0);
        this.l1 = textViewY2;
        Y0(textViewY2, R.drawable.ic_theaters_24dp, 4, 20);
        this.K0.addView(this.l1);
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(vt.f(this.J0.g, 89));
        gradientDrawable.setSize(Math.max(1, this.V1.b(1.0f)), Math.round(this.V1.v() * getResources().getDisplayMetrics().scaledDensity * 0.72f));
        ImageView imageView2 = new ImageView(this);
        imageView2.setScaleType(ImageView.ScaleType.CENTER);
        imageView2.setImageDrawable(gradientDrawable);
        this.n1 = imageView2;
        LinearLayout.LayoutParams layoutParams7 = new LinearLayout.LayoutParams(-2, -1);
        layoutParams7.setMarginStart(this.V1.b(12.0f));
        layoutParams7.setMarginEnd(this.V1.b(12.0f));
        this.K0.addView(this.n1, layoutParams7);
        TextView textViewY3 = Y(0);
        this.m1 = textViewY3;
        Y0(textViewY3, R.drawable.ic_audiotrack_24dp, 3, 21);
        this.K0.addView(this.m1);
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
        yl1 yl1Var = new yl1(this);
        this.u1 = yl1Var;
        yl1Var.setFormat12Hour("h:mm a");
        this.u1.setFormat24Hour("HH:mm");
        this.u1.setTextColor(this.J0.h);
        this.u1.setOutlineColor(this.J0.i);
        this.u1.setTypeface(Typeface.create("sans-serif-medium", 0));
        this.u1.setTextSize(2, this.V1.q(20.0f, 21.0f, 22.0f, 22.0f));
        linearLayout4.addView(this.u1);
        linearLayout3.addView(linearLayout4);
        TextView textView7 = new TextView(this);
        this.o1 = textView7;
        textView7.setTextColor(this.J0.g);
        this.o1.setTextSize(2, this.V1.t());
        this.o1.setVisibility(8);
        LinearLayout.LayoutParams layoutParams10 = new LinearLayout.LayoutParams(-2, -2);
        layoutParams10.gravity = 8388613;
        layoutParams10.topMargin = this.V1.a(2.0f);
        linearLayout3.addView(this.o1, layoutParams10);
        this.u1.setIncludeFontPadding(false);
        layoutParams8.topMargin = Math.max(0, this.u1.getPaint().getFontMetricsInt().ascent - this.R0.getPaint().getFontMetricsInt().ascent);
        linearLayout3.setLayoutParams(layoutParams8);
        Rect rect = new Rect();
        this.u1.getPaint().getTextBounds("0", 0, 1, rect);
        ((LinearLayout.LayoutParams) this.S0.getLayoutParams()).topMargin = Math.max(0, (-this.R0.getPaint().getFontMetricsInt().ascent) + rect.top);
        this.D0.addView(linearLayout3);
        this.D0.addOnLayoutChangeListener(new View.OnLayoutChangeListener(this) { // from class: op1
            public final /* synthetic */ PlayerActivity b;

            {
                this.b = this;
            }

            @Override // android.view.View.OnLayoutChangeListener
            public final void onLayoutChange(View view2, int i2, int i3, int i4, int i5, int i7, int i8, int i9, int i10) {
                byte b7 = b3;
                PlayerActivity playerActivity2 = this.b;
                switch (b7) {
                    case DecoderInputBuffer.BUFFER_REPLACEMENT_MODE_DISABLED /* 0 */:
                        LoudnessEnhancer loudnessEnhancer = PlayerActivity.e6;
                        playerActivity2.D0();
                        View view3 = (View) playerActivity2.k1.getParent();
                        int iB = playerActivity2.V1.b(2.0f);
                        if (view3 != null && playerActivity2.k1.getVisibility() == 0 && playerActivity2.o1.getVisibility() == 0) {
                            View view4 = playerActivity2.S0.getVisibility() == 0 ? playerActivity2.S0 : playerActivity2.R0;
                            int bottom = view4.getBottom() + ((ViewGroup.MarginLayoutParams) view4.getLayoutParams()).bottomMargin;
                            iB = Math.max(view4.getBottom() - bottom, (((playerActivity2.o1.getBaseline() + (playerActivity2.o1.getTop() + ((View) playerActivity2.o1.getParent()).getTop())) - view3.getTop()) - playerActivity2.k1.getBaseline()) - bottom);
                        }
                        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) playerActivity2.k1.getLayoutParams();
                        if (marginLayoutParams.topMargin != iB) {
                            marginLayoutParams.topMargin = iB;
                            playerActivity2.k1.setLayoutParams(marginLayoutParams);
                        }
                        break;
                    case 1:
                        LoudnessEnhancer loudnessEnhancer2 = PlayerActivity.e6;
                        if (i5 - i3 != i10 - i8) {
                            playerActivity2.G4();
                        }
                        break;
                    case 2:
                        LoudnessEnhancer loudnessEnhancer3 = PlayerActivity.e6;
                        if (playerActivity2.r1 != null) {
                            ViewGroup viewGroup = (ViewGroup) view2;
                            int iMax = 0;
                            for (int i11 = 0; i11 < viewGroup.getChildCount() - 1; i11++) {
                                View childAt = viewGroup.getChildAt(i11);
                                if (childAt.getVisibility() != 8) {
                                    iMax = Math.max(iMax, childAt.getBottom());
                                }
                            }
                            int iB2 = playerActivity2.V1.b(8.0f) + i3 + iMax;
                            px pxVar = (px) playerActivity2.r1.getLayoutParams();
                            if (((ViewGroup.MarginLayoutParams) pxVar).topMargin != iB2) {
                                ((ViewGroup.MarginLayoutParams) pxVar).topMargin = iB2;
                                playerActivity2.r1.setLayoutParams(pxVar);
                            }
                            break;
                        }
                        break;
                    case VideoDecoderOutputBuffer.COLORSPACE_BT2020 /* 3 */:
                        LoudnessEnhancer loudnessEnhancer4 = PlayerActivity.e6;
                        playerActivity2.d4();
                        break;
                    case 4:
                        LoudnessEnhancer loudnessEnhancer5 = PlayerActivity.e6;
                        if (i4 - i2 != i9 - i7) {
                            playerActivity2.E1 = true;
                        }
                        break;
                    case 5:
                        LoudnessEnhancer loudnessEnhancer6 = PlayerActivity.e6;
                        if (i5 - i3 != i10 - i8 && playerActivity2.K4()) {
                            playerActivity2.D4();
                            break;
                        }
                        break;
                    default:
                        LoudnessEnhancer loudnessEnhancer7 = PlayerActivity.e6;
                        if (i5 - i3 != playerActivity2.v4) {
                            playerActivity2.D4();
                        }
                        break;
                }
            }
        });
        this.E0.addView(this.D0);
        this.B.getOverlayFrameLayout().addView(this.E0, new FrameLayout.LayoutParams(-1, -1));
        int iO = this.V1.o();
        int iP3 = gt2.p(12);
        Button button = new Button(this);
        this.d5 = button;
        button.setText(R.string.button_skip);
        this.d5.setAllCaps(false);
        this.d5.setTextColor(this.H0.e);
        this.d5.setTextSize(2, this.V1.r());
        this.d5.setTypeface(typeface);
        this.d5.setMinHeight(iO);
        this.d5.setMinimumHeight(iO);
        this.k5 = gt2.p(r5);
        Drawable drawable = getDrawable(R.drawable.ic_double_arrow_24dp);
        if (drawable != null) {
            drawable.mutate();
            drawable.setTint(this.H0.e);
        }
        this.e5 = drawable;
        Drawable drawable2 = getDrawable(R.drawable.ic_play_arrow_24dp);
        if (drawable2 != null) {
            drawable2.mutate();
            drawable2.setTint(this.H0.e);
        }
        this.f5 = drawable2;
        Drawable drawable3 = getDrawable(R.drawable.ic_double_arrow_back_24dp);
        if (drawable3 != null) {
            drawable3.mutate();
            drawable3.setTint(this.H0.e);
        }
        this.g5 = drawable3;
        this.d5.setCompoundDrawablesRelative(null, null, null, null);
        this.d5.setCompoundDrawablePadding(gt2.p(8));
        this.d5.setCompoundDrawableTintList(ColorStateList.valueOf(this.H0.e));
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.focus_ring_width);
        pr prVar = this.H0;
        this.l5 = new wq1(prVar.b, (prVar.j & 16777215) | 1191182336, dimensionPixelSize, iP3);
        this.d5.setBackground(new InsetDrawable((Drawable) this.l5, 0, gt2.p(4), 0, gt2.p(4)));
        this.d5.setPadding(gt2.p(20), 0, gt2.p(20), 0);
        this.d5.setOnClickListener(new vo1(this, (byte) 8));
        final byte b7 = 3;
        this.d5.setOnLongClickListener(new View.OnLongClickListener(this) { // from class: mp1
            public final /* synthetic */ PlayerActivity m;

            {
                this.m = this;
            }

            @Override // android.view.View.OnLongClickListener
            public final boolean onLongClick(View view2) {
                byte b8 = b7;
                PlayerActivity playerActivity2 = this.m;
                switch (b8) {
                    case DecoderInputBuffer.BUFFER_REPLACEMENT_MODE_DISABLED /* 0 */:
                        LoudnessEnhancer loudnessEnhancer = PlayerActivity.e6;
                        playerActivity2.A1(null);
                        return true;
                    case 1:
                        LoudnessEnhancer loudnessEnhancer2 = PlayerActivity.e6;
                        playerActivity2.y0 = true;
                        if (playerActivity2.B.getResizeMode() != 4) {
                            playerActivity2.B.setResizeMode(4);
                        }
                        playerActivity2.A0 = playerActivity2.B.getVideoSurfaceView().getScaleX();
                        ty tyVar2 = playerActivity2.B;
                        tyVar2.removeCallbacks(tyVar2.C0);
                        playerActivity2.B.u(R.drawable.ic_fit_screen_24dp, ((int) (playerActivity2.A0 * 100.0f)) + "%");
                        playerActivity2.B.c();
                        playerActivity2.z0 = true;
                        playerActivity2.J4();
                        return true;
                    case 2:
                        LoudnessEnhancer loudnessEnhancer3 = PlayerActivity.e6;
                        playerActivity2.g3();
                        return true;
                    case VideoDecoderOutputBuffer.COLORSPACE_BT2020 /* 3 */:
                        if (PlayerActivity.M6) {
                            return false;
                        }
                        playerActivity2.w3();
                        return true;
                    default:
                        LoudnessEnhancer loudnessEnhancer4 = PlayerActivity.e6;
                        playerActivity2.A1("subtitlesScreen");
                        return true;
                }
            }
        });
        this.d5.setOnFocusChangeListener(new rs(this, b7));
        px pxVar = new px(-2, -2);
        pxVar.c = 8388693;
        pxVar.setMargins(0, 0, gt2.p(24), gt2.p(96));
        this.d5.setLayoutParams(pxVar);
        this.d5.setVisibility(8);
        this.C0.addView(this.d5);
        TextView textView8 = new TextView(this);
        this.m5 = textView8;
        textView8.setText("2.0×");
        this.m5.setAllCaps(false);
        this.m5.setTextColor(this.H0.e);
        this.m5.setTextSize(2, this.V1.q(13.0f, 14.0f, 14.0f, 15.0f));
        this.m5.setTypeface(typeface);
        this.m5.setGravity(16);
        byte b8 = 9;
        this.m5.setPadding(gt2.p(14), gt2.p(9), gt2.p(16), gt2.p(9));
        this.m5.setClickable(false);
        this.m5.setFocusable(false);
        this.n5 = getDrawable(R.drawable.exo_icon_fastforward);
        this.o5 = getDrawable(R.drawable.exo_icon_rewind);
        int iP4 = gt2.p(18);
        Drawable drawable4 = this.n5;
        if (drawable4 != null) {
            drawable4.setBounds(0, 0, iP4, iP4);
        }
        Drawable drawable5 = this.o5;
        if (drawable5 != null) {
            drawable5.setBounds(0, 0, iP4, iP4);
        }
        this.m5.setCompoundDrawablesRelative(null, null, this.n5, null);
        this.m5.setCompoundDrawablePadding(gt2.p(6));
        this.m5.setCompoundDrawableTintList(ColorStateList.valueOf(this.H0.n));
        GradientDrawable gradientDrawable2 = new GradientDrawable();
        gradientDrawable2.setColor(this.H0.b);
        gradientDrawable2.setCornerRadius(this.V1.b(8.0f));
        this.m5.setBackground(gradientDrawable2);
        px pxVar2 = new px(-2, -2);
        pxVar2.c = 49;
        pxVar2.setMargins(0, gt2.p(28), 0, 0);
        this.m5.setLayoutParams(pxVar2);
        this.m5.setVisibility(8);
        this.C0.addView(this.m5);
        yl1 yl1Var2 = new yl1(this);
        this.t1 = yl1Var2;
        yl1Var2.setFormat12Hour("h:mm a");
        this.t1.setFormat24Hour("HH:mm");
        this.t1.setTextColor(this.J0.h);
        this.t1.setOutlineColor(this.J0.i);
        this.t1.setTypeface(typeface);
        this.t1.setTextSize(2, this.V1.q(20.0f, 21.0f, 22.0f, 22.0f));
        px pxVar3 = new px(-2, -2);
        pxVar3.c = 8388659;
        this.t1.setLayoutParams(pxVar3);
        this.t1.setVisibility(8);
        this.C0.addView(this.t1);
        TextView textView9 = new TextView(this);
        this.r1 = textView9;
        textView9.setTextColor(this.H0.f);
        this.r1.setTextSize(2, this.V1.v());
        this.r1.setFontFeatureSettings("tnum");
        GradientDrawable gradientDrawable3 = new GradientDrawable();
        gradientDrawable3.setColor(this.H0.b);
        gradientDrawable3.setCornerRadius(this.V1.b(12.0f));
        this.r1.setBackground(gradientDrawable3);
        this.r1.setPadding(this.V1.b(12.0f), this.V1.b(10.0f), this.V1.b(12.0f), this.V1.b(10.0f));
        px pxVar4 = new px(-2, -2);
        pxVar4.c = 8388659;
        this.r1.setLayoutParams(pxVar4);
        this.r1.setVisibility(8);
        this.C0.addView(this.r1);
        int iN = lj.n(new ContextThemeWrapper(this, vt1.a(this, false)), R.attr.accentInk, -1);
        new ColorStateList(new int[][]{new int[]{android.R.attr.state_selected}, new int[0]}, new int[]{iN, -419430401});
        TextView textView10 = new TextView(this);
        this.q1 = textView10;
        textView10.setTextColor(-419430401);
        this.q1.setTextSize(2, this.V1.v());
        GradientDrawable gradientDrawable4 = new GradientDrawable();
        gradientDrawable4.setColor(-872415232);
        gradientDrawable4.setCornerRadius(this.V1.b(8.0f));
        this.q1.setBackground(gradientDrawable4);
        byte b9 = 10;
        this.q1.setPadding(gt2.p(10), gt2.p(5), gt2.p(10), gt2.p(5));
        Drawable drawable6 = getDrawable(R.drawable.ic_together_24dp);
        if (drawable6 != null) {
            int iRound = Math.round(this.q1.getTextSize());
            drawable6.setBounds(0, 0, iRound, iRound);
            this.q1.setCompoundDrawablesRelative(drawable6, null, null, null);
            this.q1.setCompoundDrawablePadding(this.V1.b(6.0f));
        }
        this.q1.setCompoundDrawableTintList(ColorStateList.valueOf(iN));
        LinearLayout.LayoutParams layoutParams11 = new LinearLayout.LayoutParams(-2, -2);
        layoutParams11.gravity = 8388613;
        layoutParams11.topMargin = this.V1.a(8.0f);
        this.q1.setVisibility(8);
        ((ViewGroup) this.o1.getParent()).addView(this.q1, layoutParams11);
        TextView textView11 = new TextView(this);
        this.s1 = textView11;
        textView11.setTextColor(this.H0.f);
        this.s1.setFontFeatureSettings("tnum");
        this.s1.setTextSize(2, this.V1.v());
        this.s1.setMaxLines(3);
        this.s1.setEllipsize(truncateAt);
        GradientDrawable gradientDrawable5 = new GradientDrawable();
        gradientDrawable5.setColor(this.H0.b);
        gradientDrawable5.setCornerRadius(this.V1.b(8.0f));
        this.s1.setBackground(gradientDrawable5);
        this.s1.setPadding(this.V1.b(14.0f), this.V1.b(6.0f), this.V1.b(14.0f), this.V1.b(6.0f));
        px pxVar5 = new px(-2, -2);
        pxVar5.c = 8388691;
        this.s1.setLayoutParams(pxVar5);
        this.s1.setVisibility(8);
        this.C0.addView(this.s1);
        TextView textView12 = new TextView(this);
        this.a5 = textView12;
        textView12.setTextColor(this.H0.e);
        this.a5.setTextSize(2, this.V1.v());
        GradientDrawable gradientDrawable6 = new GradientDrawable();
        gradientDrawable6.setColor(this.H0.b);
        gradientDrawable6.setCornerRadius(this.V1.b(8.0f));
        this.a5.setBackground(gradientDrawable6);
        this.a5.setPadding(gt2.p(10), gt2.p(5), gt2.p(10), gt2.p(5));
        px pxVar6 = new px(-2, -2);
        pxVar6.c = 17;
        ((ViewGroup.MarginLayoutParams) pxVar6).topMargin = this.V1.b(12.0f) + (this.V1.b(90.0f) / 2);
        this.a5.setLayoutParams(pxVar6);
        this.a5.setVisibility(8);
        this.C0.addView(this.a5);
        final byte b10 = 3;
        this.u1.addOnLayoutChangeListener(new View.OnLayoutChangeListener(this) { // from class: op1
            public final /* synthetic */ PlayerActivity b;

            {
                this.b = this;
            }

            @Override // android.view.View.OnLayoutChangeListener
            public final void onLayoutChange(View view2, int i2, int i3, int i4, int i5, int i7, int i8, int i9, int i10) {
                byte b11 = b10;
                PlayerActivity playerActivity2 = this.b;
                switch (b11) {
                    case DecoderInputBuffer.BUFFER_REPLACEMENT_MODE_DISABLED /* 0 */:
                        LoudnessEnhancer loudnessEnhancer = PlayerActivity.e6;
                        playerActivity2.D0();
                        View view3 = (View) playerActivity2.k1.getParent();
                        int iB = playerActivity2.V1.b(2.0f);
                        if (view3 != null && playerActivity2.k1.getVisibility() == 0 && playerActivity2.o1.getVisibility() == 0) {
                            View view4 = playerActivity2.S0.getVisibility() == 0 ? playerActivity2.S0 : playerActivity2.R0;
                            int bottom = view4.getBottom() + ((ViewGroup.MarginLayoutParams) view4.getLayoutParams()).bottomMargin;
                            iB = Math.max(view4.getBottom() - bottom, (((playerActivity2.o1.getBaseline() + (playerActivity2.o1.getTop() + ((View) playerActivity2.o1.getParent()).getTop())) - view3.getTop()) - playerActivity2.k1.getBaseline()) - bottom);
                        }
                        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) playerActivity2.k1.getLayoutParams();
                        if (marginLayoutParams.topMargin != iB) {
                            marginLayoutParams.topMargin = iB;
                            playerActivity2.k1.setLayoutParams(marginLayoutParams);
                        }
                        break;
                    case 1:
                        LoudnessEnhancer loudnessEnhancer2 = PlayerActivity.e6;
                        if (i5 - i3 != i10 - i8) {
                            playerActivity2.G4();
                        }
                        break;
                    case 2:
                        LoudnessEnhancer loudnessEnhancer3 = PlayerActivity.e6;
                        if (playerActivity2.r1 != null) {
                            ViewGroup viewGroup = (ViewGroup) view2;
                            int iMax = 0;
                            for (int i11 = 0; i11 < viewGroup.getChildCount() - 1; i11++) {
                                View childAt = viewGroup.getChildAt(i11);
                                if (childAt.getVisibility() != 8) {
                                    iMax = Math.max(iMax, childAt.getBottom());
                                }
                            }
                            int iB2 = playerActivity2.V1.b(8.0f) + i3 + iMax;
                            px pxVar7 = (px) playerActivity2.r1.getLayoutParams();
                            if (((ViewGroup.MarginLayoutParams) pxVar7).topMargin != iB2) {
                                ((ViewGroup.MarginLayoutParams) pxVar7).topMargin = iB2;
                                playerActivity2.r1.setLayoutParams(pxVar7);
                            }
                            break;
                        }
                        break;
                    case VideoDecoderOutputBuffer.COLORSPACE_BT2020 /* 3 */:
                        LoudnessEnhancer loudnessEnhancer4 = PlayerActivity.e6;
                        playerActivity2.d4();
                        break;
                    case 4:
                        LoudnessEnhancer loudnessEnhancer5 = PlayerActivity.e6;
                        if (i4 - i2 != i9 - i7) {
                            playerActivity2.E1 = true;
                        }
                        break;
                    case 5:
                        LoudnessEnhancer loudnessEnhancer6 = PlayerActivity.e6;
                        if (i5 - i3 != i10 - i8 && playerActivity2.K4()) {
                            playerActivity2.D4();
                            break;
                        }
                        break;
                    default:
                        LoudnessEnhancer loudnessEnhancer7 = PlayerActivity.e6;
                        if (i5 - i3 != playerActivity2.v4) {
                            playerActivity2.D4();
                        }
                        break;
                }
            }
        });
        if (!L6) {
            bl2 bl2Var = new bl2(this);
            this.c2 = bl2Var;
            bl2Var.setVisibility(8);
            px pxVar7 = new px(gt2.p(260), gt2.p(48));
            pxVar7.c = 81;
            ((ViewGroup.MarginLayoutParams) pxVar7).bottomMargin = gt2.p(48);
            this.c2.setLayoutParams(pxVar7);
            this.c2.setOnUnlockListener(new np1(this, b3));
            this.c2.setOnStartTouchingListener(new np1(this, (byte) 3));
            this.c2.setOnStopTouchingListener(new np1(this, (byte) 4));
            this.C0.addView(this.c2);
        }
        if (i >= 35) {
            z = false;
            getWindow().setNavigationBarContrastEnforced(false);
        } else {
            z = false;
        }
        kr1 kr1Var = (kr1) this.B.findViewById(R.id.exo_controller);
        this.w2 = kr1Var;
        if (L6) {
            kr1Var.setTimeBarScrubbingEnabled(z);
        }
        TextView textView13 = (TextView) this.B.findViewById(R.id.exo_position);
        TextView textView14 = (TextView) this.B.findViewById(R.id.exo_duration);
        StringBuilder sb = new StringBuilder();
        this.w2.setProgressUpdateListener(new pp1(this, textView13, sb, new Formatter(sb, Locale.getDefault()), textView14));
        this.w2.setOnApplyWindowInsetsListener(new View.OnApplyWindowInsetsListener() { // from class: qp1
            @Override // android.view.View.OnApplyWindowInsetsListener
            public final WindowInsets onApplyWindowInsets(View view2, WindowInsets windowInsets) {
                LoudnessEnhancer loudnessEnhancer = PlayerActivity.e6;
                this.a.l1(dimensionPixelOffset, windowInsets);
                return windowInsets;
            }
        });
        this.x2.setAdMarkerColor(Color.argb(0, 255, 255, 255));
        this.x2.setPlayedAdMarkerColor(Color.argb(152, 255, 255, 255));
        int i2 = this.H0.n;
        this.x2.setPlayedColor(i2);
        this.x2.setScrubberColor(i2);
        this.x2.setUnplayedColor(this.H0.k);
        this.x2.k(this.H0.j);
        try {
            qy qyVar = new qy(getResources());
            this.q = qyVar;
            qyVar.n = this.s;
            Field declaredField = kr1.class.getDeclaredField("trackNameProvider");
            declaredField.setAccessible(true);
            declaredField.set(this.w2, this.q);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }
        findViewById(R.id.delete).setOnClickListener(new vo1(this, b8));
        findViewById(R.id.next).setOnClickListener(new vo1(this, b9));
        this.n2.setOnClickListener(new vo1(this, (byte) 11));
        findViewById(R.id.exo_bottom_bar).setOnTouchListener(new rp1());
        this.l = new vq1(this, (byte) 0);
        vj vjVar = new vj(this);
        this.I = vjVar;
        vjVar.c = this.H.p;
        this.B.setBrightnessControl(vjVar);
        LinearLayout linearLayout5 = (LinearLayout) this.B.findViewById(R.id.exo_basic_controls);
        ImageButton imageButton10 = (ImageButton) linearLayout5.findViewById(R.id.exo_subtitle);
        this.m2 = imageButton10;
        imageButton10.setVisibility(8);
        linearLayout5.removeAllViews();
        ImageButton imageButton11 = this.W1;
        if (imageButton11 != null) {
            gt2.Z(this, imageButton11, false);
        }
        gt2.Z(this, this.X1, false);
        BottomBarLayout bottomBarLayout = (BottomBarLayout) findViewById(R.id.exo_bottom_bar);
        bottomBarLayout.setBackground(gt2.b0(this.H0.b, this.V1.h()));
        bottomBarLayout.setClipChildren(false);
        bottomBarLayout.setClipToPadding(false);
        View viewFindViewById3 = findViewById(R.id.plate_time_row);
        LinearLayout.LayoutParams layoutParams12 = (LinearLayout.LayoutParams) viewFindViewById3.getLayoutParams();
        layoutParams12.height = this.V1.l();
        if (!L6) {
            int iA = (this.V1.a(48.0f) - this.V1.l()) / 2;
            layoutParams12.height = this.V1.a(48.0f);
            int i3 = -iA;
            layoutParams12.topMargin = i3;
            layoutParams12.bottomMargin = i3;
        }
        viewFindViewById3.setLayoutParams(layoutParams12);
        View viewFindViewById4 = findViewById(R.id.plate_row);
        LinearLayout.LayoutParams layoutParams13 = (LinearLayout.LayoutParams) viewFindViewById4.getLayoutParams();
        layoutParams13.height = this.V1.g();
        as2 as2Var = this.V1;
        layoutParams13.topMargin = as2Var.z() ? as2Var.a(12.0f) : as2Var.b(4.0f);
        viewFindViewById4.setLayoutParams(layoutParams13);
        int[] iArr = {R.id.exo_position, R.id.exo_duration};
        for (int i4 = 0; i4 < 2; i4++) {
            int i5 = iArr[i4];
            TextView textView15 = (TextView) findViewById(i5);
            pr prVar2 = this.H0;
            textView15.setTextColor(i5 == R.id.exo_position ? prVar2.e : prVar2.g);
            textView15.setTextSize(2, this.V1.x());
            if (i5 == R.id.exo_position) {
                textView15.setTypeface(Typeface.create("sans-serif-medium", 0));
            }
        }
        LinearLayout.LayoutParams layoutParams14 = (LinearLayout.LayoutParams) this.x2.getLayoutParams();
        layoutParams14.setMarginStart(this.V1.b(8.0f));
        layoutParams14.setMarginEnd(this.V1.b(8.0f));
        this.x2.setLayoutParams(layoutParams14);
        HorizontalScrollView horizontalScrollView = (HorizontalScrollView) getLayoutInflater().inflate(R.layout.controls, (ViewGroup) null);
        final LinearLayout linearLayout6 = (LinearLayout) horizontalScrollView.findViewById(R.id.controls);
        this.C1 = linearLayout6;
        linearLayout6.setClipChildren(false);
        horizontalScrollView.setClipChildren(false);
        View[] viewArr = {this.w1, this.G1, this.H1, this.v1, this.l2, this.I1};
        for (int i7 = 0; i7 < 6; i7++) {
            View view2 = viewArr[i7];
            if (view2 instanceof ImageButton) {
                R3((ImageButton) view2, this.V1.g(), linearLayout6.getChildCount() > 0);
            } else if (linearLayout6.getChildCount() > 0) {
                LinearLayout.LayoutParams layoutParams15 = (LinearLayout.LayoutParams) view2.getLayoutParams();
                as2 as2Var2 = this.V1;
                layoutParams15.setMarginStart(as2Var2.z() ? as2Var2.a(8.0f) : 0);
            }
            linearLayout6.addView(view2);
        }
        this.l2.setSelected(true);
        a2();
        linearLayout5.addView(horizontalScrollView, new LinearLayout.LayoutParams(-2, -1));
        LinearLayout linearLayout7 = (LinearLayout) findViewById(R.id.plate_left);
        this.B1 = linearLayout7;
        linearLayout7.setClipChildren(false);
        if (L6) {
            View viewFindViewById5 = findViewById(R.id.next);
            View viewFindViewById6 = findViewById(R.id.delete);
            View[] viewArr2 = {this.o2, this.n2, this.p2, viewFindViewById5, viewFindViewById6};
            for (int i8 = 0; i8 < 5; i8++) {
                View view3 = viewArr2[i8];
                ((ViewGroup) view3.getParent()).removeView(view3);
            }
            this.n2.setBackground(gt2.b0(this.H0.c, 10000.0f));
            this.n2.setForeground(gt2.j(this, 0, 0, this.H0.j));
            this.n2.setPadding(0, 0, 0, 0);
            this.n2.setScaleType(ImageView.ScaleType.MATRIX);
            float fK = this.V1.k() / this.V1.a(24.0f);
            Matrix matrix = new Matrix();
            matrix.setScale(fK, fK);
            matrix.postTranslate((this.V1.a(48.0f) - (this.V1.a(72.0f) * fK)) / 2.0f, (this.V1.a(48.0f) - (this.V1.a(72.0f) * fK)) / 2.0f);
            this.n2.setImageMatrix(matrix);
            R3(this.o2, this.V1.g(), false);
            this.o2.setImageTintList(ColorStateList.valueOf(this.H0.e));
            int iG = (this.V1.g() - Math.round((this.V1.g() * 26.0f) / 46.0f)) / 2;
            this.o2.setPadding(iG, iG, iG, iG);
            this.B1.addView(this.o2);
            LinearLayout.LayoutParams layoutParams16 = new LinearLayout.LayoutParams(this.V1.a(48.0f), this.V1.a(48.0f));
            as2 as2Var3 = this.V1;
            layoutParams16.setMarginStart(as2Var3.z() ? as2Var3.a(8.0f) : 0);
            this.B1.addView(this.n2, layoutParams16);
            boolean z2 = true;
            View[] viewArr3 = {this.p2, viewFindViewById5, viewFindViewById6};
            int i9 = 0;
            while (i9 < 3) {
                View view4 = viewArr3[i9];
                ImageButton imageButton12 = (ImageButton) view4;
                R3(imageButton12, this.V1.g(), z2);
                imageButton12.setImageTintList(ColorStateList.valueOf(this.H0.e));
                int iG2 = (this.V1.g() - Math.round((this.V1.g() * 26.0f) / 46.0f)) / 2;
                view4.setPadding(iG2, iG2, iG2, iG2);
                this.B1.addView(view4);
                i9++;
                z2 = true;
            }
            int iN2 = (this.V1.n() - this.V1.b(60.0f)) / 2;
            this.r2.setBackground(gt2.b0(this.H0.b, 10000.0f));
            this.r2.setIndicatorInset(iN2);
            this.B.getViewTreeObserver().addOnGlobalFocusChangeListener(new ViewTreeObserver.OnGlobalFocusChangeListener() { // from class: sp1
                @Override // android.view.ViewTreeObserver.OnGlobalFocusChangeListener
                public final void onGlobalFocusChanged(View view5, View view6) {
                    LoudnessEnhancer loudnessEnhancer = PlayerActivity.e6;
                    if (view6 != null) {
                        PlayerActivity playerActivity2 = this.l;
                        if (view6 == playerActivity2.x2 || view6.getParent() == null) {
                            return;
                        }
                        if (view6.getParent() == playerActivity2.B1 || view6.getParent() == linearLayout6) {
                            view6.setNextFocusUpId(R.id.exo_progress);
                            playerActivity2.x2.setNextFocusDownId(view6.getId());
                        }
                    }
                }
            });
        } else {
            ArrayList<ImageButton> arrayList = new ArrayList();
            arrayList.add(this.b2);
            arrayList.add(this.a2);
            arrayList.add(this.X1);
            ImageButton imageButton13 = this.W1;
            if (imageButton13 != null) {
                arrayList.add(imageButton13);
            }
            for (ImageButton imageButton14 : arrayList) {
                R3(imageButton14, this.V1.g(), this.B1.getChildCount() > 0);
                this.B1.addView(imageButton14);
            }
        }
        int i10 = Build.VERSION.SDK_INT;
        if (i10 > 23) {
            horizontalScrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() { // from class: tp1
                @Override // android.view.View.OnScrollChangeListener
                public final void onScrollChange(View view5, int i11, int i12, int i13, int i14) {
                    LoudnessEnhancer loudnessEnhancer = PlayerActivity.e6;
                    this.a.o2();
                }
            });
        }
        p();
        final byte b11 = 4;
        View.OnLayoutChangeListener onLayoutChangeListener = new View.OnLayoutChangeListener(this) { // from class: op1
            public final /* synthetic */ PlayerActivity b;

            {
                this.b = this;
            }

            @Override // android.view.View.OnLayoutChangeListener
            public final void onLayoutChange(View view5, int i11, int i12, int i13, int i14, int i15, int i16, int i17, int i18) {
                byte b12 = b11;
                PlayerActivity playerActivity2 = this.b;
                switch (b12) {
                    case DecoderInputBuffer.BUFFER_REPLACEMENT_MODE_DISABLED /* 0 */:
                        LoudnessEnhancer loudnessEnhancer = PlayerActivity.e6;
                        playerActivity2.D0();
                        View view6 = (View) playerActivity2.k1.getParent();
                        int iB = playerActivity2.V1.b(2.0f);
                        if (view6 != null && playerActivity2.k1.getVisibility() == 0 && playerActivity2.o1.getVisibility() == 0) {
                            View view7 = playerActivity2.S0.getVisibility() == 0 ? playerActivity2.S0 : playerActivity2.R0;
                            int bottom = view7.getBottom() + ((ViewGroup.MarginLayoutParams) view7.getLayoutParams()).bottomMargin;
                            iB = Math.max(view7.getBottom() - bottom, (((playerActivity2.o1.getBaseline() + (playerActivity2.o1.getTop() + ((View) playerActivity2.o1.getParent()).getTop())) - view6.getTop()) - playerActivity2.k1.getBaseline()) - bottom);
                        }
                        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) playerActivity2.k1.getLayoutParams();
                        if (marginLayoutParams.topMargin != iB) {
                            marginLayoutParams.topMargin = iB;
                            playerActivity2.k1.setLayoutParams(marginLayoutParams);
                        }
                        break;
                    case 1:
                        LoudnessEnhancer loudnessEnhancer2 = PlayerActivity.e6;
                        if (i14 - i12 != i18 - i16) {
                            playerActivity2.G4();
                        }
                        break;
                    case 2:
                        LoudnessEnhancer loudnessEnhancer3 = PlayerActivity.e6;
                        if (playerActivity2.r1 != null) {
                            ViewGroup viewGroup = (ViewGroup) view5;
                            int iMax = 0;
                            for (int i19 = 0; i19 < viewGroup.getChildCount() - 1; i19++) {
                                View childAt = viewGroup.getChildAt(i19);
                                if (childAt.getVisibility() != 8) {
                                    iMax = Math.max(iMax, childAt.getBottom());
                                }
                            }
                            int iB2 = playerActivity2.V1.b(8.0f) + i12 + iMax;
                            px pxVar8 = (px) playerActivity2.r1.getLayoutParams();
                            if (((ViewGroup.MarginLayoutParams) pxVar8).topMargin != iB2) {
                                ((ViewGroup.MarginLayoutParams) pxVar8).topMargin = iB2;
                                playerActivity2.r1.setLayoutParams(pxVar8);
                            }
                            break;
                        }
                        break;
                    case VideoDecoderOutputBuffer.COLORSPACE_BT2020 /* 3 */:
                        LoudnessEnhancer loudnessEnhancer4 = PlayerActivity.e6;
                        playerActivity2.d4();
                        break;
                    case 4:
                        LoudnessEnhancer loudnessEnhancer5 = PlayerActivity.e6;
                        if (i13 - i11 != i17 - i15) {
                            playerActivity2.E1 = true;
                        }
                        break;
                    case 5:
                        LoudnessEnhancer loudnessEnhancer6 = PlayerActivity.e6;
                        if (i14 - i12 != i18 - i16 && playerActivity2.K4()) {
                            playerActivity2.D4();
                            break;
                        }
                        break;
                    default:
                        LoudnessEnhancer loudnessEnhancer7 = PlayerActivity.e6;
                        if (i14 - i12 != playerActivity2.v4) {
                            playerActivity2.D4();
                        }
                        break;
                }
            }
        };
        viewFindViewById4.addOnLayoutChangeListener(onLayoutChangeListener);
        linearLayout6.addOnLayoutChangeListener(onLayoutChangeListener);
        this.B1.addOnLayoutChangeListener(onLayoutChangeListener);
        viewFindViewById4.getViewTreeObserver().addOnPreDrawListener(new wo1(this, (byte) 1));
        final View viewFindViewById7 = findViewById(R.id.exo_controls_background);
        final View viewFindViewById8 = findViewById(R.id.exo_bottom_bar);
        this.B.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() { // from class: up1
            /* JADX WARN: Code duplicated, block: B:9:0x001c  */
            @Override // android.view.ViewTreeObserver.OnPreDrawListener
            public final boolean onPreDraw() {
                float alpha;
                LoudnessEnhancer loudnessEnhancer = PlayerActivity.e6;
                PlayerActivity playerActivity2 = this.l;
                kr1 kr1Var2 = playerActivity2.w2;
                if (kr1Var2 == null || kr1Var2.getVisibility() != 0) {
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
                View[] viewArr4 = {playerActivity2.r1, playerActivity2.s1, playerActivity2.q1};
                for (int i12 = 0; i12 < 3; i12++) {
                    View view6 = viewArr4[i12];
                    if (view6 != null && view6.getVisibility() == 0) {
                        view6.setAlpha(alpha);
                    }
                }
                return true;
            }
        });
        this.B.setControllerVisibilityListener(new li(this));
        YouTubeOverlay youTubeOverlay = (YouTubeOverlay) findViewById(R.id.youtube_overlay);
        this.E = youTubeOverlay;
        youTubeOverlay.E = new li(this);
        if (L6 && i10 >= 30) {
            gt2.X(this);
        }
        r1();
        h6 = this;
    }

    @Override // android.app.Activity
    public final void onDestroy() {
        if (!this.C) {
            b2(false);
        }
        if (h6 == this) {
            h6 = null;
        }
        a82 a82Var = this.p4;
        if (a82Var != null) {
            a82Var.b();
        }
        zn2 zn2Var = this.V4;
        if (zn2Var != null) {
            zn2Var.k();
            this.V4 = null;
        }
        super.onDestroy();
    }

    @Override // android.app.Activity
    public final boolean onGenericMotionEvent(MotionEvent motionEvent) {
        if (M6) {
            return true;
        }
        if ((motionEvent.getSource() & 2) != 0) {
            if (motionEvent.getAction() == 8) {
                float axisValue = motionEvent.getAxisValue(9);
                gt2.a(this, this.n, this.B, axisValue > 0.0f, Math.abs(axisValue) > 1.0f);
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
                gt2.a(this, this.n, this.B, axisValue2 < 0.0f, true);
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
    
        if (P2(true, r9.getRepeatCount() > 0) != false) goto L121;
     */
    /* JADX WARN: Code restructure failed: missing block: B:110:0x011d, code lost:
    
        if (P2(false, r9.getRepeatCount() > 0) != false) goto L121;
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
            if (!this.E2) {
                ty tyVar = this.B;
                tyVar.postDelayed(tyVar.C0, 1000L);
            }
            if (this.H2 >= 0) {
                ty tyVar2 = this.B;
                np1 np1Var = this.N2;
                tyVar2.removeCallbacks(np1Var);
                this.B.postDelayed(np1Var, 520L);
            }
        } else {
            if (i == 24 || i == 25) {
                ty tyVar3 = this.B;
                tyVar3.postDelayed(tyVar3.C0, 800L);
                return true;
            }
            if (i == 89 || i == 90 || i == 104 || i == 105) {
                if (!this.E2) {
                    ty tyVar4 = this.B;
                    tyVar4.postDelayed(tyVar4.C0, 1000L);
                }
                if (this.H2 >= 0) {
                    ty tyVar5 = this.B;
                    np1 np1Var2 = this.N2;
                    tyVar5.removeCallbacks(np1Var2);
                    this.B.postDelayed(np1Var2, 520L);
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
            this.K4 = false;
            if (O0(intent)) {
                return;
            }
            if ("android.intent.action.VIEW".equals(action) && (data != null || ks1.b(intent))) {
                T2();
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
                    T2();
                    m2();
                    this.H.x(this, uri, null);
                    K6 = true;
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
        C2();
    }

    @Override // android.app.Activity
    public final void onPictureInPictureModeChanged(boolean z, Configuration configuration) {
        super.onPictureInPictureModeChanged(z, configuration);
        this.G = z;
        r4();
        if (z) {
            this.B.c();
            W0();
            X0();
            a3(false);
            z4();
            w4();
            D4();
            this.B.setScale(1.0f);
            w5 w5Var = new w5(this, (byte) 5);
            this.m = w5Var;
            uy0.I(this, w5Var, new IntentFilter("media_control"));
            return;
        }
        D4();
        w4();
        z4();
        vt1 vt1Var = this.H;
        float f = vt1Var.k;
        if (f > 0.0f) {
            this.B.t(vt1Var.i, f);
        } else if (vt1Var.i == 4) {
            this.B.setScale(vt1Var.j);
        }
        w5 w5Var2 = this.m;
        if (w5Var2 != null) {
            unregisterReceiver(w5Var2);
            this.m = null;
        }
        this.B.setControllerAutoShow(true);
        mg0 mg0Var = g6;
        if (mg0Var != null) {
            if (mg0Var.J()) {
                gt2.i0(this, this.B, false);
            } else if (!M6) {
                this.B.j();
            }
        }
        if (M6) {
            z3();
        }
    }

    @Override // android.app.Activity
    public final void onResume() {
        super.onResume();
        this.A2 = true;
        n2();
        u2();
    }

    @Override // android.app.Activity
    public final void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        B2(bundle);
        D2(bundle);
    }

    /* JADX WARN: Code duplicated, block: B:62:0x0125  */
    @Override // android.app.Activity
    public final void onStart() {
        String str;
        super.onStart();
        Handler handler = this.J5;
        cq1 cq1Var = this.K5;
        handler.removeCallbacks(cq1Var);
        ks1 ks1Var = this.k3;
        if (ks1Var != null) {
            long j = ks1Var.g;
            if (j > 0) {
                handler.postDelayed(cq1Var, j);
            }
        }
        if (this.H0 != null) {
            boolean zM = vt1.m(this);
            boolean z = !zM && vt1.l(this);
            int i = -16777216;
            int iN = zM ? lj.n(new ContextThemeWrapper(this, vt1.a(this, true)), R.attr.accentFill, -16777216) : lj.n(new ContextThemeWrapper(this, vt1.a(this, false)), R.attr.accentInk, -1);
            int i2 = -419430401;
            if (zM) {
                i = -419430401;
                i2 = -570425344;
            } else if (!z) {
                i = -872415232;
            }
            new ColorStateList(new int[][]{new int[]{android.R.attr.state_selected}, new int[0]}, new int[]{iN, i2});
            pr prVar = this.H0;
            if (prVar.a != zM || prVar.b != i || prVar.n != iN) {
                this.I0 = true;
                if (l6) {
                    m6 = true;
                }
                if (g6 != null) {
                    b2(true);
                }
                recreate();
                return;
            }
        }
        this.Y2 = true;
        mg0 mg0Var = g6;
        if (mg0Var == null) {
            str = ", no player";
        } else {
            mg0Var.A1();
            if (mg0Var.w0.f != null) {
                str = ", player failed while away";
            } else {
                str = ", state " + L3(g6.C());
            }
        }
        gt2.K("onStart".concat(str));
        F4(this);
        if (Build.VERSION.SDK_INT >= 31) {
            this.B.removeCallbacks(this.U4);
            gt2.i0(this, this.B, true);
        }
        this.B.removeCallbacks(this.i0);
        this.z2 = false;
        if (M6) {
            z3();
        } else {
            this.B.j();
        }
        mg0 mg0Var2 = g6;
        if (mg0Var2 == null) {
            Z0();
        } else {
            mg0Var2.A1();
            if (mg0Var2.w0.f == null) {
                this.Y = null;
                if (this.i2) {
                    this.i2 = false;
                    mg0 mg0Var3 = g6;
                    k50 k50Var = (k50) mg0Var3.A0();
                    k50Var.getClass();
                    j50 j50Var = new j50(k50Var);
                    j50Var.j(1, false);
                    mg0Var3.q0(j50Var.b());
                }
                if (this.c0 != -9223372036854775807L) {
                    this.j0 = false;
                    this.B.postDelayed(this.k0, 3000L);
                }
                if (g6.C() == 2) {
                    this.O = 0;
                    if (this.B != null) {
                        M();
                        this.N = kp2.p.get();
                        this.B.postDelayed(this.P, 30000L);
                    }
                }
            } else if (this.Y == null) {
                mg0 mg0Var4 = g6;
                mg0Var4.A1();
                if (!Y1(mg0Var4.w0.f)) {
                    this.K4 = true;
                    b2(false);
                    Z0();
                }
            } else {
                this.K4 = true;
                b2(false);
                Z0();
            }
        }
        zn2 zn2Var = this.V4;
        if (zn2Var != null && zn2Var.B != null && !zn2Var.K) {
            fl2 fl2Var = zn2Var.n;
            fl2Var.g = false;
            fl2Var.b(SystemClock.elapsedRealtime() + 3000);
            zn2Var.K = true;
            zn2Var.m.postDelayed(zn2Var.Q, 250L);
        }
        z4();
    }

    @Override // android.app.Activity
    public final void onStop() {
        zn2 zn2Var;
        mg0 mg0Var;
        lq1 lq1Var;
        super.onStop();
        this.Y2 = false;
        gt2.K("onStop".concat(isFinishing() ? ", finishing" : ""));
        this.J5.removeCallbacks(this.K5);
        if (Build.VERSION.SDK_INT >= 31) {
            this.B.removeCallbacks(this.U4);
        }
        this.B.u(0, null);
        zn2 zn2Var2 = this.V4;
        if (zn2Var2 != null) {
            zn2Var2.K = false;
            zn2Var2.m.removeCallbacks(zn2Var2.Q);
        }
        if (this.C) {
            return;
        }
        if (!isChangingConfigurations()) {
            T2();
        }
        this.B.removeCallbacks(this.X5);
        DisplayManager displayManager = this.R4;
        if (displayManager != null && (lq1Var = this.S4) != null) {
            displayManager.unregisterDisplayListener(lq1Var);
        }
        if (isFinishing() || (mg0Var = g6) == null || !i6) {
            if (isFinishing() && (zn2Var = this.V4) != null) {
                zn2Var.k();
            }
            b2(false);
            return;
        }
        this.B2 = false;
        mg0Var.k(false);
        qq1 qq1Var = this.D;
        if (qq1Var != null && qq1Var.d) {
            gt2.K("background: releasing the passthrough output");
            this.O2 = false;
            this.i2 = true;
            mg0 mg0Var2 = g6;
            k50 k50Var = (k50) mg0Var2.A0();
            k50Var.getClass();
            j50 j50Var = new j50(k50Var);
            j50Var.j(1, true);
            mg0Var2.q0(j50Var.b());
        }
        M();
        this.B.removeCallbacks(this.k0);
        if (L6) {
            return;
        }
        this.B.postDelayed(this.i0, x6);
    }

    @Override // android.app.Activity
    public final void onUserInteraction() {
        super.onUserInteraction();
        this.Y4 = 0;
    }

    @Override // android.app.Activity
    public final void onUserLeaveHint() {
        mg0 mg0Var;
        vt1 vt1Var = this.H;
        if (vt1Var != null && vt1Var.u && (mg0Var = g6) != null && mg0Var.J() && gt2.B(this)) {
            s0();
        } else {
            super.onUserLeaveHint();
            T2();
        }
    }

    public final void p() {
        if (L6 || this.H1 == null) {
            return;
        }
        boolean zK4 = K4();
        this.D1 = 0;
        this.E1 = true;
        q();
        as2 as2Var = this.V1;
        int iB = as2Var.z() ? 0 : as2Var.b(6.0f);
        int i = this.V1.i();
        boolean zA2 = A2();
        Drawable drawable = zA2 ? getDrawable(R.drawable.ic_high_quality_24dp) : null;
        if (drawable != null) {
            drawable.setBounds(0, 0, i, i);
        }
        this.w1.setCompoundDrawablesRelative(drawable, null, null, null);
        this.w1.setCompoundDrawableTintList(this.H0.p);
        TextView[] textViewArr = {this.w1, this.G1, this.H1};
        for (int i2 = 0; i2 < 3; i2++) {
            TextView textView = textViewArr[i2];
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) textView.getLayoutParams();
            layoutParams.width = zA2 ? this.V1.g() : -2;
            textView.setLayoutParams(layoutParams);
            textView.setCompoundDrawablePadding(zA2 ? 0 : this.V1.b(8.0f));
            if (zA2) {
                textView.setPadding((this.V1.g() - i) / 2, 0, 0, 0);
            } else {
                TextView textView2 = this.w1;
                as2 as2Var2 = this.V1;
                if (textView == textView2) {
                    int iB2 = as2Var2.b(16.0f) + iB;
                    textView.setPadding(iB2, 0, iB2, 0);
                } else {
                    textView.setPadding(as2Var2.b(12.0f) + iB, 0, this.V1.b(16.0f) + iB, 0);
                }
            }
        }
        this.R0.setMaxLines(zK4 ? 2 : 1);
        y4();
        n4();
        C4();
        v4();
    }

    public final boolean p1() {
        if (g6 != null) {
            rl0 rl0Var = (rl0) this.u4.a;
            fw0 fw0VarN = g6.E().a.listIterator(0);
            while (fw0VarN.hasNext()) {
                wp2 wp2Var = (wp2) fw0VarN.next();
                if (wp2Var.b.c == 3) {
                    for (int i = 0; i < wp2Var.a; i++) {
                        if (wp2Var.e[i] && !wp2Var.a(i).equals(rl0Var)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r9v10, types: [boolean, byte] */
    /* JADX WARN: Type inference failed for: r9v17 */
    /* JADX WARN: Type inference failed for: r9v8 */
    public final void p3() {
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
        vt1 vt1Var;
        final PlayerActivity playerActivity = this;
        mg0 mg0Var = g6;
        boolean z2 = mg0Var != null && mg0Var.a1() > 1;
        ArrayList arrayList = new ArrayList();
        if (z2) {
            for (int i5 = 0; i5 < g6.a1(); i5++) {
                arrayList.add(g6.Z0(i5));
            }
        } else {
            arrayList.addAll(playerActivity.A3);
        }
        if (arrayList.size() <= 1) {
            return;
        }
        int size = arrayList.size();
        int iV = z2 ? g6.V() : playerActivity.C3;
        Object[] objArr = new View[1];
        ContextThemeWrapper contextThemeWrapperE = r2.e(playerActivity);
        int iN = lj.n(contextThemeWrapperE, R.attr.colorOnSurface, -1);
        LinearLayout linearLayoutC = we2.c(contextThemeWrapperE, 1);
        int iP = gt2.p(10);
        linearLayoutC.setPadding(iP, iP, iP, iP);
        boolean z3 = playerActivity.getResources().getConfiguration().orientation == 2 || playerActivity.V1.a == 4;
        final boolean z4 = z3 && ((vt1Var = playerActivity.H) == null || vt1Var.F);
        LinearLayout linearLayout = new LinearLayout(contextThemeWrapperE);
        linearLayout.setOrientation(0);
        linearLayout.setGravity(16);
        int i7 = 16;
        TextView textView = new TextView(contextThemeWrapperE);
        textView.setText(playerActivity.getString(R.string.playlist));
        textView.setTextColor(iN);
        textView.setTextSize(2, playerActivity.V1.y());
        textView.setTypeface(Typeface.DEFAULT_BOLD);
        textView.setPadding(gt2.p(8), gt2.p(10), 0, gt2.p(10));
        textView.setMaxLines(1);
        textView.setEllipsize(TextUtils.TruncateAt.END);
        linearLayout.addView(textView, new LinearLayout.LayoutParams(0, -2, 1.0f));
        TextView textView2 = new TextView(contextThemeWrapperE);
        textView2.setText(playerActivity.getResources().getQuantityString(R.plurals.playlist_items, size, Integer.valueOf(size)));
        textView2.setTextColor(lj.n(contextThemeWrapperE, R.attr.colorOnSurfaceVariant, contextThemeWrapperE.getColor(R.color.ink_secondary)));
        textView2.setTextSize(2, playerActivity.V1.t());
        textView2.setSingleLine(true);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        layoutParams.setMarginStart(gt2.p(8));
        linearLayout.addView(textView2, layoutParams);
        if (z3) {
            MaterialButton materialButtonM = r2.m(contextThemeWrapperE, playerActivity.V1, z4 ? R.drawable.ic_view_list_24dp : R.drawable.ic_view_grid_24dp, playerActivity.getString(z4 ? R.string.playlist_view_list : R.string.playlist_view_grid), false);
            materialButtonM.setOnClickListener(new View.OnClickListener() { // from class: yp1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    LoudnessEnhancer loudnessEnhancer = PlayerActivity.e6;
                    PlayerActivity playerActivity2 = this.l;
                    vt1 vt1Var2 = playerActivity2.H;
                    boolean z5 = !z4;
                    vt1Var2.F = z5;
                    SharedPreferences.Editor editorEdit = vt1Var2.b.edit();
                    editorEdit.putBoolean("playlistGrid", z5);
                    editorEdit.apply();
                    playerActivity2.p3();
                }
            });
            LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-2, -2);
            layoutParams2.setMarginStart(playerActivity.V1.b(16.0f));
            linearLayout.addView(materialButtonM, layoutParams2);
        }
        MaterialButton materialButtonM2 = r2.m(contextThemeWrapperE, playerActivity.V1, R.drawable.ic_close_24dp, contextThemeWrapperE.getString(R.string.error_close), false);
        materialButtonM2.setId(R.id.picker_close);
        linearLayout.addView(materialButtonM2);
        linearLayoutC.addView(linearLayout);
        int iB = playerActivity.V1.b(z3 ? 120.0f : 190.0f);
        String str = null;
        if (z4) {
            HorizontalScrollView horizontalScrollView2 = new HorizontalScrollView(contextThemeWrapperE);
            horizontalScrollView2.setHorizontalScrollBarEnabled(false);
            horizontalScrollView2.setHorizontalFadingEdgeEnabled(true);
            horizontalScrollView2.setFadingEdgeLength(playerActivity.V1.b(28.0f));
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
            p81 p81Var = (p81) arrayList.get(i8);
            x81 x81Var = p81Var.d;
            CharSequence charSequenceK0 = playerActivity.k3 != null ? gt2.k0(Q3(playerActivity.L3, i8, str)) : str;
            if (charSequenceK0 == null) {
                if (x81Var == null) {
                    charSequenceK0 = str;
                } else {
                    charSequenceK0 = x81Var.f;
                    if (charSequenceK0 == null) {
                        charSequenceK0 = x81Var.a;
                    }
                }
            }
            if (charSequenceK0 == null || charSequenceK0.length() == 0) {
                charSequenceK0 = "Video " + (i8 + 1);
            }
            Uri uri = x81Var != null ? x81Var.n : null;
            if (uri == null && i8 == iV) {
                uri = playerActivity.b6;
            }
            k81 k81Var = p81Var.b;
            Uri uri2 = k81Var != null ? k81Var.a : null;
            Object obj3 = obj2;
            boolean z5 = i8 == iV;
            Uri uri3 = uri;
            if (z4) {
                LinearLayout linearLayoutC2 = we2.c(contextThemeWrapperE, 1);
                int iP2 = gt2.p(6);
                linearLayoutC2.setPadding(iP2, iP2, iP2, gt2.p(8));
                i = 4;
                playerActivity = this;
                int i9 = i8;
                linearLayoutC2.addView(playerActivity.N1(contextThemeWrapperE, i8, uri3, uri2, z5), new LinearLayout.LayoutParams(-1, ((iB - (iP2 * 2)) * 9) / 16));
                TextView textView3 = new TextView(contextThemeWrapperE);
                textView3.setText(charSequenceK0);
                textView3.setTextColor(lj.n(contextThemeWrapperE, R.attr.colorOnSurface, -1));
                boolean z7 = z5;
                textView3.setTextSize(2, playerActivity.V1.q(15.0f, 16.0f, 17.0f, 18.0f));
                if (z7) {
                    textView3.setTypeface(Typeface.DEFAULT_BOLD);
                }
                LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(-1, -2);
                layoutParams3.topMargin = gt2.p(8);
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
                linearLayout3.setPadding(gt2.p(8), gt2.p(7), gt2.p(10), gt2.p(7));
                linearLayout3.setMinimumHeight(playerActivity.V1.o());
                LinearLayout.LayoutParams layoutParams4 = new LinearLayout.LayoutParams(playerActivity.V1.b(88.0f), playerActivity.V1.b(50.0f));
                layoutParams4.setMarginEnd(gt2.p(12));
                layoutParams4.gravity = 16;
                i2 = i8;
                z = z5;
                contextThemeWrapperE = contextThemeWrapperE;
                linearLayout3.addView(playerActivity.N1(contextThemeWrapperE, i2, uri3, uri2, z), layoutParams4);
                LinearLayout linearLayout4 = new LinearLayout(contextThemeWrapperE);
                linearLayout4.setOrientation(1);
                TextView textView4 = new TextView(contextThemeWrapperE);
                textView4.setText(charSequenceK0);
                textView4.setTextColor(lj.n(contextThemeWrapperE, R.attr.colorOnSurface, -1));
                c = 0;
                textView4.setTextSize(2, playerActivity.V1.q(15.0f, 16.0f, 17.0f, 18.0f));
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
                r2.j(playerActivity, playerActivity.V1, linearLayout3, textView4);
                view = linearLayout3;
            }
            view.setClickable(r9);
            view.setFocusable((boolean) r9);
            view.setBackground(r2.t(contextThemeWrapperE, i3));
            view.setSelected(z);
            if (z) {
                objArr2[i3] = view;
            }
            view.setOnClickListener(new zj(playerActivity, i2, r9));
            if (z4 != 0) {
                b = -2;
                obj3.addView(view, new LinearLayout.LayoutParams(iB, -2));
            } else {
                b = -2;
                LinearLayout.LayoutParams layoutParams6 = new LinearLayout.LayoutParams(-1, -2);
                layoutParams6.bottomMargin = i2 == size + (-1) ? 0 : gt2.p(i);
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
        scrollView.setPadding(0, gt2.p(8), 0, 0);
        Dialog dialog = playerActivity.K1;
        if (dialog != null) {
            dialog.dismiss();
        }
        Dialog dialog2 = new Dialog(playerActivity, android.R.style.Theme.Translucent.NoTitleBar);
        playerActivity.K1 = dialog2;
        r2.v(playerActivity, playerActivity.V1, dialog2, scrollView, z8);
        playerActivity.o3(playerActivity.K1);
        byte b2 = 0;
        GLSurfaceView gLSurfaceView = objArr3[0];
        if (gLSurfaceView != 0) {
            gLSurfaceView.post(new zp1(gLSurfaceView, horizontalScrollView4, scrollView, b2));
        }
    }

    public final void p4() {
        mg0 mg0Var = g6;
        if (mg0Var == null) {
            return;
        }
        int iV = mg0Var.V();
        ks1 ks1Var = this.k3;
        gs1 gs1Var = (ks1Var == null || iV < 0 || iV >= ks1Var.f.size()) ? null : (gs1) this.k3.f.get(iV);
        String strK0 = gs1Var == null ? null : gt2.k0(gs1Var.c);
        if (gs1Var != null && ks1.c(gs1Var.j, strK0)) {
            strK0 = null;
        }
        boolean z = (!K4() || strK0 == null || strK0.isEmpty()) ? false : true;
        if (z) {
            String strT0 = t0(null, gs1Var.i, gs1Var.j);
            if (strT0 != null) {
                strK0 = we2.f(strT0, "\n", strK0);
            }
        } else {
            p81 p81VarZ = g6.z();
            CharSequence charSequence = p81VarZ == null ? null : p81VarZ.d.f;
            strK0 = charSequence == null ? null : charSequence.toString();
            if (strK0 == null && iV == this.D3) {
                strK0 = t0(null, this.F3, this.G3);
            }
        }
        this.k1.setMaxLines(z ? 2 : 1);
        W2(this.k1, strK0);
    }

    public final void q() {
        TextView textView = this.G1;
        if (textView != null) {
            textView.setVisibility((!this.y1 || a0(textView)) ? 8 : 0);
        }
        View[] viewArr = {this.W1, this.X1, this.w1};
        for (int i = 0; i < 3; i++) {
            View view = viewArr[i];
            if (view != null) {
                view.setVisibility((!J1(view) || a0(view)) ? 8 : 0);
            }
        }
    }

    public final void q0(wf0 wf0Var, v0 v0Var) {
        if (wf0Var != null) {
            v0Var.g("player.error_code", wf0Var.b());
        }
        Uri uriD0 = d0();
        if (gt2.E(uriD0)) {
            v0Var.m("media_uri", gt2.l0(uriD0));
        }
        v0Var.g("decoder.priority", String.valueOf(this.H.N));
        v0Var.g("player.tunneling", String.valueOf(this.H.x));
        Boolean bool = u6;
        if (bool != null) {
            v0Var.g("decoder.ffmpeg", String.valueOf(bool));
        }
        mg0 mg0Var = g6;
        if (mg0Var == null) {
            return;
        }
        mg0Var.A1();
        rl0 rl0Var = mg0Var.V;
        mg0 mg0Var2 = g6;
        mg0Var2.A1();
        rl0 rl0Var2 = mg0Var2.W;
        if (rl0Var != null) {
            v0Var.g("media.video_mime", String.valueOf(rl0Var.p));
            v0Var.g("media.video_hw_decoder", String.valueOf(!S0(rl0Var)));
        }
        if (rl0Var2 != null) {
            v0Var.g("media.audio_mime", String.valueOf(rl0Var2.p));
        }
        String str = this.T;
        if (str != null) {
            v0Var.g("decoder.video_name", str);
        }
        String str2 = this.U;
        if (str2 != null) {
            v0Var.g("decoder.audio_name", str2);
        }
        v0Var.g("media.is_live", String.valueOf(g6.Q0()));
        qq1 qq1Var = this.D;
        if (qq1Var != null) {
            v0Var.g("audio.sink_passthrough", String.valueOf(qq1Var.d));
        }
        if (wf0Var != null) {
            MediaCodec.CodecException codecException = (MediaCodec.CodecException) A0(wf0Var, MediaCodec.CodecException.class);
            if (codecException != null) {
                v0Var.g("codec.error_code", "0x" + Integer.toHexString(codecException.getErrorCode()));
                v0Var.g("codec.transient", String.valueOf(codecException.isTransient()));
                v0Var.g("codec.recoverable", String.valueOf(codecException.isRecoverable()));
                v0Var.m("codec_diagnostic", String.valueOf(codecException.getDiagnosticInfo()));
            }
            j71 j71Var = (j71) A0(wf0Var, j71.class);
            if (j71Var != null) {
                v0Var.g("video.surface_valid", String.valueOf(j71Var.m));
            }
        }
        if (this.t0 != 0) {
            v0Var.g("player.reselect_ms_ago", String.valueOf(SystemClock.elapsedRealtime() - this.t0));
        }
        StringBuilder sb = new StringBuilder();
        g(sb);
        v0Var.m("player_state", sb.toString());
        v0Var.J(new io.sentry.a("trace.txt", null, gt2.h.matcher(gt2.U()).replaceAll("$1").getBytes(StandardCharsets.UTF_8)));
    }

    public final void q1() {
        int i;
        if (!this.H.y) {
            H0();
            return;
        }
        if (this.B2) {
            DisplayManager displayManager = this.R4;
            if (displayManager == null) {
                displayManager = (DisplayManager) getSystemService("display");
                this.R4 = displayManager;
            }
            lq1 lq1Var = this.S4;
            if (lq1Var == null) {
                lq1Var = new lq1(this);
                this.S4 = lq1Var;
            }
            displayManager.registerDisplayListener(lq1Var, null);
        }
        float fN4 = N4();
        mg0 mg0Var = g6;
        byte b = 0;
        if (mg0Var != null) {
            fw0 fw0VarN = mg0Var.E().a.listIterator(0);
            i = 0;
            while (fw0VarN.hasNext()) {
                wp2 wp2Var = (wp2) fw0VarN.next();
                if (wp2Var.b.c == 2) {
                    for (int i2 = 0; i2 < wp2Var.a; i2++) {
                        if (wp2Var.e[i2]) {
                            rl0 rl0VarA = wp2Var.a(i2);
                            int i3 = gt2.D(rl0VarA) ? rl0VarA.x : rl0VarA.w;
                            if (i3 > i) {
                                i = i3;
                            }
                        }
                    }
                }
            }
            if (i <= 0) {
                mg0 mg0Var2 = g6;
                mg0Var2.A1();
                rl0 rl0Var = mg0Var2.V;
                if (rl0Var != null) {
                    i = gt2.D(rl0Var) ? rl0Var.x : rl0Var.w;
                }
            }
        } else {
            i = 0;
        }
        int iMax = Math.max(i, 0);
        if (fN4 > 0.0f) {
            String[] strArr = gt2.a;
            runOnUiThread(new ct2(this, fN4, iMax, b));
        } else {
            Uri uriD0 = d0();
            String[] strArr2 = gt2.a;
            Thread thread = this.c3;
            if (thread != null) {
                thread.interrupt();
            }
            Thread thread2 = new Thread(new t11(this, uriD0, iMax));
            this.c3 = thread2;
            thread2.start();
        }
        this.B.removeCallbacks(this.X5);
        if (this.B2) {
            this.B.postDelayed(this.X5, this.g3 ? 6000L : 3000L);
        }
    }

    public final void q2(int i, int i2) {
        Integer numValueOf;
        String str;
        String str2;
        ArrayList arrayList = new ArrayList();
        for (ip2 ip2Var : b0()) {
            if (ip2Var.c == i2) {
                arrayList.add(ip2Var);
            }
        }
        Collections.sort(arrayList, new cc((byte) 21));
        fw0 fw0VarN = g6.E().a.listIterator(0);
        int i3 = 0;
        while (fw0VarN.hasNext()) {
            wp2 wp2Var = (wp2) fw0VarN.next();
            if (wp2Var.b.c == i) {
                for (int i4 = 0; i4 < wp2Var.a; i4++) {
                    rl0 rl0Var = wp2Var.b.d[i4];
                    String str3 = rl0Var.a;
                    String str4 = null;
                    if (str3 != null) {
                        try {
                            numValueOf = Integer.valueOf(Integer.parseInt(str3));
                        } catch (NumberFormatException unused) {
                            numValueOf = null;
                        }
                        if (numValueOf != null) {
                            for (ip2 ip2Var2 : b0()) {
                                int i5 = ip2Var2.a;
                                String str5 = ip2Var2.b;
                                if (i5 == numValueOf.intValue() && str5 != null && !str5.isEmpty()) {
                                    str4 = str5;
                                    break;
                                }
                            }
                        }
                    }
                    if (str4 == null && i3 < arrayList.size() && (str2 = ((ip2) arrayList.get(i3)).b) != null && !str2.isEmpty()) {
                        str4 = str2;
                    }
                    if (str4 != null && (str = rl0Var.a) != null) {
                        this.s.put(str, str4);
                    }
                    i3++;
                }
            }
        }
    }

    public final void q3(boolean z) {
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

    public final void q4() {
        mg0 mg0Var;
        mg0 mg0Var2;
        boolean z = false;
        boolean z2 = (this.q2 || (mg0Var2 = g6) == null || !mg0Var2.f1()) ? false : true;
        if (!this.q2 && (mg0Var = g6) != null && mg0Var.e1()) {
            z = true;
        }
        ImageButton imageButton = this.o2;
        if (imageButton != null && imageButton.isEnabled() != z2) {
            gt2.Z(this, this.o2, z2);
        }
        ImageButton imageButton2 = this.p2;
        if (imageButton2 == null || imageButton2.isEnabled() == z) {
            return;
        }
        gt2.Z(this, this.p2, z);
    }

    public final void r() {
        if (this.p == null) {
            return;
        }
        ks1 ks1Var = this.k3;
        String[] strArr = ks1Var == null ? null : ks1Var.d.d;
        List listC0 = (strArr == null || strArr.length <= 0) ? gt2.c0(this.H.T) : Arrays.asList(strArr);
        if (listC0.isEmpty()) {
            return;
        }
        q50 q50Var = this.p;
        j50 j50VarD = q50Var.d();
        j50VarD.q = sp2.g((String[]) listC0.toArray(new String[0]));
        q50Var.o(new k50(j50VarD));
    }

    public final void r0() {
        cz1.e(this.H.F0);
        String str = this.H.G0;
        String str2 = s12.d;
        String strTrim = str == null ? "" : str.trim();
        if (strTrim.isEmpty() || !(strTrim.startsWith("http://") || strTrim.startsWith("https://"))) {
            s12.d = "https://siaivo.isroot.in/lparty/";
        } else {
            int iIndexOf = strTrim.indexOf(63);
            if (iIndexOf != -1) {
                strTrim = strTrim.substring(0, iIndexOf);
            }
            s12.d = strTrim;
        }
        if (this.V4 == null) {
            this.V4 = new zn2(new li(this));
        }
    }

    public final void r1() {
        if (this.H.J0) {
            long jCurrentTimeMillis = System.currentTimeMillis();
            vt1 vt1Var = this.H;
            if (jCurrentTimeMillis - vt1Var.K0 < 3600000) {
                return;
            }
            vt1Var.K0 = jCurrentTimeMillis;
            SharedPreferences.Editor editorEdit = vt1Var.b.edit();
            editorEdit.putLong("updateLastCheck", jCurrentTimeMillis);
            editorEdit.apply();
            ns2.a(new mo1(this, (byte) 0));
        }
    }

    public final void r2() {
        this.s.clear();
        if (g6 == null || b0().isEmpty()) {
            return;
        }
        q2(1, 2);
        q2(3, 3);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void r3() {
        String string;
        String string2;
        int i;
        TextView textView;
        int i2 = 0;
        if (g6 == null) {
            n3(getString(R.string.quality_unavailable), false, R.drawable.ic_high_quality_24dp);
            return;
        }
        ArrayList arrayListJ = J();
        if (arrayListJ.size() < 2) {
            n3(getString(R.string.quality_unavailable), false, R.drawable.ic_high_quality_24dp);
            return;
        }
        int iS2 = S2(arrayListJ);
        View[] viewArr = new View[1];
        ContextThemeWrapper contextThemeWrapperE = r2.e(this);
        int iN = lj.n(contextThemeWrapperE, R.attr.colorOnSurface, -1);
        int iN2 = lj.n(contextThemeWrapperE, R.attr.colorSecondaryContainer, contextThemeWrapperE.getColor(R.color.brand_container));
        int iN3 = lj.n(contextThemeWrapperE, R.attr.colorOnSecondaryContainer, -1);
        LinearLayout linearLayoutC = we2.c(contextThemeWrapperE, 1);
        int iP = gt2.p(10);
        linearLayoutC.setPadding(iP, iP, iP, iP);
        TextView textView2 = new TextView(contextThemeWrapperE);
        textView2.setText(getString(R.string.quality_title));
        textView2.setTextColor(iN);
        textView2.setTextSize(2, this.V1.y());
        textView2.setTypeface(Typeface.DEFAULT_BOLD);
        int i3 = 1;
        textView2.setPadding(gt2.p(10), gt2.p(10), gt2.p(10), gt2.p(10));
        linearLayoutC.addView(r2.s(contextThemeWrapperE, this.V1, textView2, null));
        View view = new View(contextThemeWrapperE);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, gt2.p(1));
        layoutParams.bottomMargin = gt2.p(4);
        view.setLayoutParams(layoutParams);
        view.setBackgroundColor(lj.n(contextThemeWrapperE, R.attr.colorOutlineVariant, contextThemeWrapperE.getColor(R.color.divider)));
        linearLayoutC.addView(view);
        int i4 = 0;
        while (i4 < arrayListJ.size()) {
            yq1 yq1Var = (yq1) arrayListJ.get(i4);
            int i5 = i4 == iS2 ? i3 : i2;
            LinearLayout linearLayout = new LinearLayout(contextThemeWrapperE);
            linearLayout.setOrientation(i2);
            linearLayout.setGravity(16);
            int i7 = i2;
            ArrayList arrayList = arrayListJ;
            linearLayout.setPadding(gt2.p(12), gt2.p(10), gt2.p(12), gt2.p(10));
            boolean z = i3;
            linearLayout.setClickable(z);
            linearLayout.setFocusable(z);
            linearLayout.setMinimumHeight(this.V1.o());
            linearLayout.setBackground(r2.t(contextThemeWrapperE, i5 != 0 ? iN2 : i7));
            if (i5 != 0) {
                viewArr[i7] = linearLayout;
            }
            LinearLayout linearLayoutC2 = we2.c(contextThemeWrapperE, 1);
            int i8 = iS2;
            LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(i7, -2, 1.0f);
            layoutParams2.gravity = 16;
            linearLayoutC2.setLayoutParams(layoutParams2);
            TextView textView3 = new TextView(contextThemeWrapperE);
            byte b = yq1Var.d;
            String str = yq1Var.c;
            if (b != 0) {
                string = b != 1 ? yq1Var.a : getString(R.string.quality_maximum);
            } else {
                string = getString(R.string.quality_auto);
            }
            textView3.setText(string);
            textView3.setTextColor(i5 != 0 ? iN3 : iN);
            textView3.setTextSize(2, this.V1.s());
            if (i5 != 0) {
                textView3.setTypeface(Typeface.DEFAULT_BOLD);
            }
            linearLayoutC2.addView(textView3);
            byte b2 = yq1Var.d;
            if (b2 != 0) {
                string2 = b2 != 1 ? yq1Var.b : getString(R.string.quality_maximum_badge);
            } else {
                string2 = getString(R.string.quality_auto_description);
            }
            if (string2 == null || string2.isEmpty()) {
                i = 2;
                textView = null;
            } else {
                textView = new TextView(contextThemeWrapperE);
                textView.setText(string2);
                textView.setTextColor(lj.n(contextThemeWrapperE, R.attr.colorOnSurfaceVariant, contextThemeWrapperE.getColor(R.color.ink_secondary)));
                i = 2;
                textView.setTextSize(2, this.V1.t());
                linearLayoutC2.addView(textView);
            }
            linearLayout.addView(linearLayoutC2);
            as2 as2Var = this.V1;
            TextView[] textViewArr = new TextView[i];
            textViewArr[0] = textView3;
            textViewArr[1] = textView;
            r2.j(this, as2Var, linearLayout, textViewArr);
            if (str != null && !str.isEmpty()) {
                TextView textView4 = new TextView(contextThemeWrapperE);
                textView4.setText(str);
                textView4.setTextColor(lj.n(contextThemeWrapperE, R.attr.colorOnSurfaceVariant, contextThemeWrapperE.getColor(R.color.ink_secondary)));
                textView4.setTextSize(2, this.V1.t());
                textView4.setGravity(8388629);
                textView4.setSingleLine(true);
                LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(-2, -2);
                layoutParams3.setMarginEnd(gt2.p(10));
                layoutParams3.gravity = 16;
                textView4.setLayoutParams(layoutParams3);
                linearLayout.addView(textView4);
            }
            linearLayout.setOnClickListener(new qk(this, yq1Var, (byte) 3));
            linearLayoutC.addView(linearLayout);
            i4++;
            arrayListJ = arrayList;
            iS2 = i8;
            i2 = 0;
            i3 = 1;
        }
        ScrollView scrollView = new ScrollView(contextThemeWrapperE);
        scrollView.addView(linearLayoutC);
        scrollView.setPadding(0, gt2.p(8), 0, 0);
        Dialog dialog = this.J1;
        if (dialog != null) {
            dialog.dismiss();
        }
        Dialog dialog2 = new Dialog(this, android.R.style.Theme.Translucent.NoTitleBar);
        this.J1 = dialog2;
        r2.v(this, this.V1, dialog2, scrollView, false);
        o3(this.J1);
        View view2 = viewArr[0];
        if (view2 != null) {
            view2.post(new c4(viewArr, (byte) 1));
        }
    }

    public final void r4() {
        ty tyVar;
        if (this.E0 == null) {
            return;
        }
        this.B.invalidate();
        boolean z = this.F0 && !this.G && this.u0;
        if (z == this.G0) {
            return;
        }
        this.G0 = z;
        if (!z || (tyVar = this.B) == null) {
            return;
        }
        cq1 cq1Var = this.I5;
        tyVar.removeCallbacks(cq1Var);
        this.B.post(cq1Var);
    }

    public final void s() {
        int iE4;
        if (this.p != null && (iE4 = e4(2)) >= 0) {
            j50 j50VarD = this.p.d();
            if (((rl0) this.u4.a) == null || this.w4 == null) {
                SparseArray sparseArray = j50VarD.S;
                Map map = (Map) sparseArray.get(iE4);
                if (map != null && !map.isEmpty()) {
                    sparseArray.remove(iE4);
                }
            } else {
                j50VarD.q(iE4, new ep2(this.w4), new l50(new int[]{this.x4}, 0));
            }
            j50VarD.p(iE4, false);
            q50 q50Var = this.p;
            q50Var.getClass();
            q50Var.o(new k50(j50VarD));
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
        if (g6 == null) {
            return;
        }
        this.B.setControllerAutoShow(false);
        this.B.c();
        mg0 mg0Var = g6;
        mg0Var.A1();
        rl0 rl0Var = mg0Var.V;
        if (rl0Var != null) {
            int i = rl0Var.x;
            int i2 = rl0Var.w;
            View videoSurfaceView = this.B.getVideoSurfaceView();
            if (videoSurfaceView instanceof SurfaceView) {
                ((SurfaceView) videoSurfaceView).getHolder().setFixedSize(i2, i);
            }
            Rational rational = gt2.D(rl0Var) ? new Rational(i, i2) : new Rational(i2, i);
            int i3 = Build.VERSION.SDK_INT;
            Rational rational2 = this.i3;
            Rational rational3 = this.h3;
            if (i3 >= 33 && getPackageManager().hasSystemFeature("android.software.expanded_picture_in_picture") && (rational.floatValue() > rational3.floatValue() || rational.floatValue() < rational2.floatValue())) {
                mb1.e(this.F).setExpandedAspectRatio(rational);
            }
            if (rational.floatValue() > rational3.floatValue()) {
                rational = rational3;
            } else if (rational.floatValue() < rational2.floatValue()) {
                rational = rational2;
            }
            mb1.e(this.F).setAspectRatio(rational);
        }
        enterPictureInPictureMode(mb1.e(this.F).build());
    }

    public final void s1() {
        String str;
        mg0 mg0Var;
        mg0 mg0Var2 = g6;
        if (mg0Var2 == null || this.b5 == null) {
            return;
        }
        vt1 vt1Var = this.H;
        boolean z = vt1Var.k0;
        if (!z || !vt1Var.p0 || this.t5) {
            if (this.t5) {
                str = "the launcher sent them";
            } else {
                str = !z ? "skipping is off" : "online search is off";
            }
            gt2.K("segments: not searching, ".concat(str));
            return;
        }
        int iV = mg0Var2.V();
        al alVarV1 = v1(iV);
        if (alVarV1.m()) {
            gt2.K("segments: no title id, not searching");
            return;
        }
        N();
        int i = this.u5;
        final String str2 = (String) alVarV1.n;
        final String str3 = (String) alVarV1.o;
        final int i2 = alVarV1.l;
        final int i3 = alVarV1.m;
        final double dC0 = c0();
        final cb1 cb1Var = new cb1(i, iV, this);
        an anVar = w92.a;
        Thread thread = new Thread(new Runnable() { // from class: g92
            @Override // java.lang.Runnable
            public final void run() {
                try {
                    w92.w(str2, str3, i2, i3, dC0, new x62((Object) cb1Var, (byte) 2));
                } catch (Throwable th) {
                    gt2.K("segments: lookup failed " + th);
                    w3.b().u(th);
                }
            }
        }, "SegmentFinder");
        thread.setDaemon(true);
        thread.start();
        this.b3 = thread;
        if (this.v5 || (mg0Var = g6) == null || !mg0Var.e1()) {
            return;
        }
        int iV2 = g6.V();
        al alVarV2 = v1(g6.b1());
        if (alVarV2.m()) {
            return;
        }
        al alVarV3 = v1(iV2);
        if (TextUtils.equals((String) alVarV2.n, (String) alVarV3.n) && TextUtils.equals((String) alVarV2.o, (String) alVarV3.o) && alVarV2.l == alVarV3.l && alVarV2.m == alVarV3.m) {
            return;
        }
        this.v5 = true;
        final String str4 = (String) alVarV2.n;
        final String str5 = (String) alVarV2.o;
        final int i4 = alVarV2.l;
        final int i5 = alVarV2.m;
        final mb1 mb1Var = new mb1((byte) 24);
        final double d = 0.0d;
        Thread thread2 = new Thread(new Runnable() { // from class: g92
            @Override // java.lang.Runnable
            public final void run() {
                try {
                    w92.w(str4, str5, i4, i5, d, new x62((Object) mb1Var, (byte) 2));
                } catch (Throwable th) {
                    gt2.K("segments: lookup failed " + th);
                    w3.b().u(th);
                }
            }
        }, "SegmentFinder");
        thread2.setDaemon(true);
        thread2.start();
    }

    public final boolean s2(long j) {
        if (this.V4 != null || this.S5 != 0 || this.U5 || d1()) {
            return false;
        }
        long duration = g6.getDuration();
        if ((duration != -9223372036854775807L && duration - j < 30000) || O4() < 20) {
            return false;
        }
        Uri uriD0 = d0();
        String string = uriD0 != null ? uriD0.toString() : null;
        if (string == null || string.equals(y6) || !Y1(null)) {
            return false;
        }
        gt2.K("video freeze: the screen was started over");
        y6 = string;
        z6 = j;
        return true;
    }

    public final void s3(boolean z) {
        CustomDefaultTimeBar customDefaultTimeBar = this.x2;
        pr prVar = this.H0;
        customDefaultTimeBar.setScrubberColor(z ? prVar.e : prVar.n);
        View viewFindViewById = findViewById(R.id.plate_row);
        viewFindViewById.animate().cancel();
        viewFindViewById.animate().alpha(z ? 0.38f : 1.0f).setDuration(gt2.C(this) ? 0L : 200L);
    }

    public final void s4() {
        ImageView imageView = this.n1;
        if (imageView == null) {
            return;
        }
        imageView.setVisibility((!K4() && this.l1.getVisibility() == 0 && this.m1.getVisibility() == 0) ? 0 : 8);
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
        mg0 mg0Var = g6;
        if (mg0Var != null) {
            boolean z = this.U5;
            mg0Var.A1();
            if (mg0Var.R == z) {
                return;
            }
            mg0Var.R = z;
            mg0Var.l.r.b(23, z ? 1 : 0, 0).b();
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
        if (str != null && !str.isEmpty() && !ks1.c(i2, str)) {
            arrayList.add(str);
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        return TextUtils.join(" · ", arrayList);
    }

    public final void t1(xp2 xp2Var, final boolean z, ArrayList arrayList, boolean z2) {
        List listC0;
        List listSubList;
        List<String> listSingletonList;
        Uri uri;
        if (g6 == null || xp2Var.a.isEmpty() || g6.C() == 1) {
            return;
        }
        if (!z && f1()) {
            gt2.K("subtitles: live stream, not searching");
            return;
        }
        boolean z3 = false;
        boolean z4 = (this.H.W || z) ? false : true;
        if (arrayList == null || arrayList.isEmpty()) {
            vt1 vt1Var = this.H;
            listC0 = gt2.c0(z2 ? vt1Var.V : vt1Var.U);
        } else {
            listC0 = arrayList;
        }
        if (listC0.isEmpty()) {
            if (z) {
                String language = Locale.getDefault().getLanguage();
                String[] strArr = gt2.a;
                fp2 fp2Var = fp2.f;
                String strD = x91.D(language);
                if (strD == null) {
                    gt2.K("subtitles: no language list and no device language, not searching");
                    return;
                }
                listC0 = Collections.singletonList(strD);
            } else if (z2 || gt2.c0(this.H.V).isEmpty()) {
                gt2.K("subtitles: no subtitle language set, not searching".concat(this.H.W ? "" : " (online search is off too)"));
                return;
            }
        }
        if (z2) {
            listSubList = Collections.EMPTY_LIST;
        } else {
            HashSet hashSet = new HashSet();
            fw0 fw0VarN = xp2Var.a.listIterator(0);
            while (fw0VarN.hasNext()) {
                wp2 wp2Var = (wp2) fw0VarN.next();
                if (wp2Var.b.c == 3) {
                    for (int i = 0; i < wp2Var.a; i++) {
                        rl0 rl0VarA = wp2Var.a(i);
                        if (!h1(rl0VarA)) {
                            String str = rl0VarA.d;
                            String[] strArr2 = gt2.a;
                            fp2 fp2Var2 = fp2.f;
                            String strD2 = x91.D(str);
                            if (strD2 == null) {
                                String str2 = rl0VarA.b;
                                strD2 = gt2.I((str2 == null || !str2.matches("[a-z]{3}\\d{1,2}")) ? j4(rl0VarA) : rl0VarA.b, listC0);
                            }
                            if (strD2 != null) {
                                hashSet.add(strD2);
                            }
                        }
                    }
                }
            }
            int size = listC0.size();
            for (int i2 = 0; i2 < listC0.size(); i2++) {
                if (hashSet.contains(listC0.get(i2))) {
                    size = i2;
                    break;
                }
            }
            listSubList = (size == 0 || (this.H.X && size < listC0.size())) ? Collections.EMPTY_LIST : listC0.subList(0, size);
        }
        if (z2) {
            listSingletonList = listC0;
        } else if (arrayList == null && this.p4 != null && K2() && !I2() && ((uri = this.H.c) == null || !uri.equals(this.A4))) {
            ArrayList arrayListC0 = gt2.c0(this.H.V);
            if (!arrayListC0.isEmpty()) {
                HashSet hashSet2 = new HashSet();
                Iterator it = w0().iterator();
                while (it.hasNext()) {
                    String strO = zi0.o((Uri) it.next());
                    String[] strArr3 = gt2.a;
                    fp2 fp2Var3 = fp2.f;
                    String strD3 = x91.D(strO);
                    if (strD3 != null) {
                        hashSet2.add(strD3);
                    }
                }
                String strO1 = o1();
                Iterator it2 = arrayListC0.iterator();
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
                gt2.K("subtitles: nothing wanted (want=" + listC0 + " already satisfied by the tracks present), not searching");
                return;
            }
            if (z2) {
                listSingletonList = listC0;
                listC0 = listSubList;
            }
        } else {
            listC0 = listSubList;
        }
        StringBuilder sb = new StringBuilder("subtitles: search ");
        sb.append(z ? "manual" : "auto");
        sb.append(", online=");
        sb.append(this.H.W);
        sb.append(", strict=");
        sb.append(this.H.X);
        sb.append(", translate=");
        sb.append(this.H.Z);
        sb.append(", sources=os rest stremio shegu, list=");
        this.H.getClass();
        this.H.getClass();
        this.H.getClass();
        this.H.getClass();
        sb.append(this.H.U);
        sb.append("/");
        sb.append(this.H.V);
        gt2.K(sb.toString());
        final al alVarV1 = v1(g6.V());
        if (alVarV1.m()) {
            gt2.K("subtitles: no title id, not searching");
            if (z) {
                String string = getString(R.string.subtitle_search_none);
                StringBuilder sb2 = new StringBuilder();
                g(sb2);
                y3(string, sb2.toString().trim());
                return;
            }
            return;
        }
        ArrayList arrayList3 = new ArrayList(listC0);
        for (String str4 : listSingletonList) {
            if (!arrayList3.contains(str4)) {
                arrayList3.add(str4);
            }
        }
        Iterator it3 = new ArrayList(arrayList3).iterator();
        while (it3.hasNext()) {
            for (String str5 : l4((String) it3.next())) {
                if (!arrayList3.contains(str5)) {
                    arrayList3.add(str5);
                }
            }
        }
        StringBuilder sb3 = new StringBuilder(((String) alVarV1.n) + "|" + ((String) alVarV1.o) + "|" + alVarV1.l + "|" + alVarV1.m);
        sb3.append("|");
        sb3.append(arrayList3);
        sb3.append("|1234");
        this.H.getClass();
        this.H.getClass();
        this.H.getClass();
        this.H.getClass();
        final String string2 = sb3.toString();
        if (z) {
            W6.remove(string2);
        } else {
            if (string2.equals(this.w5)) {
                return;
            }
            Long l = (Long) W6.get(string2);
            if (l != null && System.currentTimeMillis() - l.longValue() < 1800000) {
                return;
            }
        }
        final int iV = g6.V();
        ArrayList arrayListT3 = T3(alVarV1);
        final String str6 = (String) arrayListT3.get(0);
        P();
        this.w5 = string2;
        boolean z5 = (z || listC0.isEmpty() || !A(listC0, arrayListT3, iV, false)) ? false : true;
        if (!z && !listSingletonList.isEmpty() && A(listSingletonList, arrayListT3, iV, true)) {
            z3 = true;
        }
        if (z5) {
            listC0 = Collections.EMPTY_LIST;
        }
        final List list = listC0;
        if (z3) {
            listSingletonList = Collections.EMPTY_LIST;
        }
        final List list2 = listSingletonList;
        if (list.isEmpty() && list2.isEmpty()) {
            gt2.K("subtitles: already cached for this item, not searching");
            return;
        }
        if (z4) {
            gt2.K("subtitles: online search is off, only the cache was asked");
            return;
        }
        final int i3 = this.y5;
        final Uri uri2 = this.H.c;
        final long duration = g6.getDuration();
        this.H.getClass();
        final x62 x62Var = this.x;
        if (z) {
            W3(R.string.subtitle_search_searching);
        }
        Thread thread = new Thread(new Runnable() { // from class: dp1
            @Override // java.lang.Runnable
            public final void run() {
                al alVar;
                uf1 uf1Var;
                PlayerActivity playerActivity = this.l;
                h00 h00Var = x62Var;
                Uri uri3 = uri2;
                long j = duration;
                List list3 = list;
                al alVar2 = alVarV1;
                String str7 = str6;
                int i4 = i3;
                int i5 = iV;
                boolean z7 = z;
                List list4 = list2;
                String str8 = string2;
                LoudnessEnhancer loudnessEnhancer = PlayerActivity.e6;
                AtomicBoolean atomicBoolean = new AtomicBoolean();
                fk2 fk2VarX3 = null;
                try {
                    uf1 uf1VarA = vf1.a(h00Var, uri3, j);
                    if (list3.isEmpty()) {
                        alVar = alVar2;
                        uf1Var = uf1VarA;
                    } else {
                        alVar = alVar2;
                        uf1Var = uf1VarA;
                        fk2VarX3 = playerActivity.X3(alVar, list3, str7, i4, i5, uri3, j, uf1Var, atomicBoolean, false, z7);
                    }
                    if (!list4.isEmpty() && i4 == playerActivity.y5 && !Thread.currentThread().isInterrupted()) {
                        fk2 fk2VarX4 = playerActivity.X3(alVar, list4, str7, i4, i5, uri3, j, uf1Var, atomicBoolean, true, z7);
                        if (fk2VarX3 == null) {
                            fk2VarX3 = fk2VarX4;
                        }
                    }
                } catch (Throwable th) {
                    gt2.K("subtitles: search failed " + th);
                }
                if (fk2VarX3 == null && atomicBoolean.get() && !Thread.currentThread().isInterrupted()) {
                    PlayerActivity.W6.put(str8, Long.valueOf(System.currentTimeMillis()));
                }
                byte b = 1;
                boolean z8 = fk2VarX3 == null;
                if (z7 && z8 && !Thread.currentThread().isInterrupted()) {
                    playerActivity.runOnUiThread(new ko1(playerActivity, i4, b));
                }
            }
        }, "SubtitleSearch");
        thread.setDaemon(true);
        this.x5 = thread;
        thread.start();
    }

    public final void t2(Bundle bundle) {
        Bundle bundle2;
        long[] jArr;
        if (bundle == null || !this.j3 || (bundle2 = bundle.getBundle("apiSession")) == null) {
            return;
        }
        String string = bundle2.getString("uri");
        Uri uri = string == null ? null : Uri.parse(string);
        int i = bundle2.getInt("index");
        if (i >= 0) {
            ArrayList arrayList = this.A3;
            if (i < arrayList.size()) {
                this.C3 = i;
                if (uri != null) {
                    arrayList.set(i, I3(i, uri));
                }
            }
        }
        if (uri != null) {
            this.H.c = uri;
        }
        long[] longArray = bundle2.getLongArray("episodePositions");
        if (longArray != null && (jArr = this.E3) != null && longArray.length == jArr.length) {
            this.E3 = longArray;
        }
        this.g4 = bundle2.getInt("stickyQuality");
        this.h4 = bundle2.getString("stickyVoice");
        if (this.k3 != null && bundle2.containsKey("playlistSession")) {
            Bundle bundle3 = bundle2.getBundle("playlistSession");
            int size = this.k3.f.size();
            b7 b7Var = new b7(size);
            long[] longArray2 = bundle3 != null ? bundle3.getLongArray("durations") : null;
            if (longArray2 != null && longArray2.length == size) {
                System.arraycopy(longArray2, 0, (long[]) b7Var.c, 0, size);
                boolean[] booleanArray = bundle3.getBooleanArray("finished");
                if (booleanArray != null && booleanArray.length == size) {
                    System.arraycopy(booleanArray, 0, (boolean[]) b7Var.d, 0, size);
                }
                int[] intArray = bundle3.getIntArray("visitIndex");
                long[] longArray3 = bundle3.getLongArray("visitStarted");
                long[] longArray4 = bundle3.getLongArray("visitPosition");
                long[] longArray5 = bundle3.getLongArray("visitDuration");
                if (intArray != null && longArray3 != null && longArray4 != null && longArray5 != null) {
                    for (int i2 = 0; i2 < intArray.length; i2++) {
                        a7 a7Var = new a7(longArray3[i2], intArray[i2]);
                        a7Var.c = longArray4[i2];
                        a7Var.d = longArray5[i2];
                        ((ArrayList) b7Var.b).add(a7Var);
                    }
                }
                b7Var.a = bundle3.getBoolean("everPlayed");
                b7Var.e = bundle3.getString("error");
                b7Var.f = fp2.a(bundle3.getBundle("audio"));
                b7Var.g = fp2.a(bundle3.getBundle("subtitle"));
                String[] stringArray = bundle3.getStringArray("audioChosenBy");
                String[] strArr = (String[]) b7Var.h;
                if (stringArray != null && stringArray.length == strArr.length) {
                    System.arraycopy(stringArray, 0, strArr, 0, strArr.length);
                }
                String[] stringArray2 = bundle3.getStringArray("subtitleChosenBy");
                String[] strArr2 = (String[]) b7Var.i;
                if (stringArray2 != null && stringArray2.length == strArr2.length) {
                    System.arraycopy(stringArray2, 0, strArr2, 0, strArr2.length);
                }
            }
            this.l3 = b7Var;
        }
        this.H.l = bundle2.getInt("aspectClass", -1);
        this.H.y(bundle2.getString("audioTrack"), bundle2.getString("subtitleTrack"), bundle2.getInt("resizeMode"), bundle2.getFloat("scale"), bundle2.getFloat("aspectRatio"), bundle2.getFloat("speed"));
        if (bundle2.containsKey("position")) {
            this.H.z(bundle2.getLong("position"));
        }
    }

    public final void t3(rn2 rn2Var, ArrayList arrayList, ArrayList arrayList2, to1 to1Var) {
        ArrayList arrayList3 = new ArrayList(arrayList2.size() + 1);
        arrayList3.add(new l70(R.drawable.ic_keyboard_24dp, null, getString(R.string.subtitle_search_type), null, false, new hk(this, rn2Var, arrayList, new xp1(this, rn2Var, arrayList, arrayList2, to1Var, (byte) 0), (byte) 10)));
        Iterator it = arrayList2.iterator();
        while (it.hasNext()) {
            Integer num = (Integer) it.next();
            int iIntValue = num.intValue();
            arrayList3.add(new l70(getString(iIntValue == 0 ? R.string.subtitle_search_specials : R.string.subtitle_search_season, num), null, false, new sb1(this, rn2Var, arrayList, iIntValue, arrayList2, to1Var)));
        }
        r2.p(this, this.V1, new np1(this, (byte) 10), rn2Var.c, arrayList3, 34, 48, to1Var);
    }

    /* JADX WARN: Code duplicated, block: B:13:0x001a  */
    public final void t4() {
        int i;
        if (g6 == null) {
            return;
        }
        float fC1 = C1();
        mg0 mg0Var = g6;
        if (mg0Var == null) {
            i = 0;
        } else {
            mg0Var.A1();
            rl0 rl0Var = mg0Var.V;
            if (rl0Var == null || (i = rl0Var.x) <= 0) {
                i = 0;
            }
        }
        el elVar = this.e3;
        if (elVar != null) {
            int i2 = this.o0;
            elVar.y = fC1;
            elVar.z = i;
            elVar.A = i2;
        }
        dl dlVar = this.f3;
        if (dlVar != null) {
            int i3 = this.o0;
            dlVar.v = fC1;
            dlVar.w = i;
            dlVar.x = i3;
        }
    }

    public final void u() {
        bc0 bc0Var = this.l4;
        if (bc0Var != null) {
            bc0Var.k = o0();
        }
        xi xiVar = f6;
        if (xiVar != null) {
            boolean zR = R();
            int i = xiVar.m;
            xiVar.j = zR;
            xiVar.m = i;
        }
    }

    public final String u0(wf0 wf0Var) {
        StringBuilder sb = new StringBuilder("Error code: ");
        sb.append(wf0Var.b());
        Uri uriD0 = d0();
        if (uriD0 != null) {
            sb.append("\nMedia: ");
            sb.append(gt2.V(uriD0, this.H.I0));
        }
        String strX = X(wf0Var);
        if (!strX.isEmpty()) {
            sb.append("\nCodec: ");
            sb.append(strX);
        }
        g(sb);
        sb.append("\n\n");
        StringWriter stringWriter = new StringWriter();
        wf0Var.printStackTrace(new PrintWriter(stringWriter));
        sb.append(stringWriter.toString());
        return sb.toString();
    }

    /* JADX WARN: Code duplicated, block: B:15:0x0058  */
    public final b90[] u1() {
        b90 b90VarH;
        b90 b90Var;
        wi1 wi1Var;
        wi1 wi1Var2;
        wi1 wi1Var3;
        wi1 wi1VarC;
        b90 b90Var2 = null;
        if ("file".equals(this.H.c.getScheme())) {
            File file = new File(this.H.c.getSchemeSpecificPart());
            File parentFile = file.getParentFile();
            if (parentFile != null) {
                return new b90[]{b90.a(file), b90.a(parentFile)};
            }
        } else {
            boolean zF = xi1.f(this.H.c);
            vt1 vt1Var = this.H;
            if (zF) {
                Uri uri = vt1Var.c;
                if (x80.d(uri)) {
                    if (uri.getQueryParameter("id") == null) {
                        wi1Var2 = null;
                    } else {
                        String strB = x80.b(uri);
                        String queryParameter = uri.getQueryParameter("p");
                        List<String> pathSegments = uri.getPathSegments();
                        if (pathSegments.size() < 2 || queryParameter == null || queryParameter.isEmpty() || "0".equals(queryParameter)) {
                            wi1Var3 = new wi1(null, new Uri.Builder().scheme("dlna").encodedAuthority(uri.getAuthority()).appendQueryParameter("c", strB).appendQueryParameter("id", "0").build(), uri.getHost(), true, 0L, 0L, x80.b);
                            wi1Var2 = wi1Var3;
                        } else {
                            Uri.Builder builderEncodedAuthority = new Uri.Builder().scheme("dlna").encodedAuthority(uri.getAuthority());
                            for (int i = 0; i < pathSegments.size() - 1; i++) {
                                builderEncodedAuthority.appendPath(pathSegments.get(i));
                            }
                            wi1Var2 = new wi1(null, builderEncodedAuthority.appendQueryParameter("c", strB).appendQueryParameter("id", queryParameter).build(), pathSegments.get(pathSegments.size() - 2), true, 0L, 0L, x80.b);
                        }
                    }
                } else if (vo2.e(uri)) {
                    if (vo2.d(uri) == null) {
                        wi1Var2 = null;
                    } else {
                        String strA = vo2.a(uri);
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
                            wi1VarC = vo2.c(this, uriBuild, pathSegments3.isEmpty() ? uriBuild.getHost() : pathSegments3.get(pathSegments3.size() - 1));
                        } else {
                            Uri.Builder builderEncodedAuthority3 = new Uri.Builder().scheme(uri.getScheme()).encodedAuthority(uri.getAuthority());
                            for (int i2 = 0; i2 < pathSegments2.size() - 1; i2++) {
                                builderEncodedAuthority3.appendPath(pathSegments2.get(i2));
                            }
                            wi1VarC = vo2.c(this, builderEncodedAuthority3.appendQueryParameter("b", strA).appendQueryParameter("h", vo2.d(uri)).build(), pathSegments2.get(pathSegments2.size() - 2));
                        }
                        wi1Var2 = wi1VarC;
                    }
                } else if (x91.W(uri)) {
                    Uri uriA = xi1.a(uri);
                    if (uriA == null) {
                        wi1Var2 = null;
                    } else {
                        List<String> pathSegments4 = uriA.getPathSegments();
                        wi1Var3 = new wi1(null, uriA, pathSegments4.isEmpty() ? String.valueOf(uriA.getHost()) : pathSegments4.get(pathSegments4.size() - 1), true, 0L, 0L, new v00((Activity) this, (byte) 5));
                        wi1Var2 = wi1Var3;
                    }
                } else {
                    if (pb2.e(uri)) {
                        Uri uriA2 = xi1.a(uri);
                        if (uriA2 != null) {
                            List<String> pathSegments5 = uriA2.getPathSegments();
                            wi1Var3 = new wi1(null, uriA2, pathSegments5.isEmpty() ? String.valueOf(uriA2.getHost()) : pathSegments5.get(pathSegments5.size() - 1), true, 0L, 0L, new v00((Activity) this, (byte) 12));
                            wi1Var2 = wi1Var3;
                        }
                    } else if (w00.b(uri)) {
                        List<String> pathSegments6 = uri.getPathSegments();
                        if (!pathSegments6.isEmpty()) {
                            Uri.Builder builderPath = uri.buildUpon().path("");
                            for (int i3 = 0; i3 < pathSegments6.size() - 1; i3++) {
                                builderPath.appendPath(pathSegments6.get(i3));
                            }
                            Uri uriBuild2 = builderPath.build();
                            List<String> pathSegments7 = uriBuild2.getPathSegments();
                            wi1Var = new wi1(null, uriBuild2, pathSegments7.isEmpty() ? uriBuild2.getHost() : pathSegments7.get(pathSegments7.size() - 1), true, 0L, 0L, new v00((Activity) this, (byte) 0));
                            wi1Var2 = wi1Var;
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
                            wi1Var = new wi1(null, uriBuild3, pathSegments9.isEmpty() ? "" : pathSegments9.get(pathSegments9.size() - 1), true, 0L, 0L, new v00((Activity) this, (byte) 14));
                            wi1Var2 = wi1Var;
                        }
                    }
                    wi1Var2 = null;
                }
                if (wi1Var2 != null) {
                    Uri uri3 = this.H.c;
                    List<String> pathSegments10 = uri3.getPathSegments();
                    return new b90[]{new wi1(null, uri3, pathSegments10.isEmpty() ? "" : pathSegments10.get(pathSegments10.size() - 1), false, 0L, 0L, null), wi1Var2};
                }
            } else if (vt1Var.g != null) {
                boolean zEquals = "com.android.externalstorage.documents".equals(vt1Var.c.getHost());
                vt1 vt1Var2 = this.H;
                if (zEquals) {
                    b90VarH = zi0.j(this, vt1Var2.g, vt1Var2.c);
                } else {
                    bd2 bd2VarB = b90.b(this, vt1Var2.g);
                    Uri uri4 = this.H.c;
                    bd2 bd2Var = new bd2(b90Var2);
                    bd2Var.c = this;
                    bd2Var.d = uri4;
                    b90VarH = zi0.h(bd2VarB, bd2Var);
                }
                if (b90VarH != null && (b90Var = b90VarH.a) != null) {
                    return new b90[]{b90VarH, b90Var};
                }
            }
        }
        return null;
    }

    public final void u2() {
        if (this.y2 || this.H.t) {
            try {
                Settings.System.putInt(getContentResolver(), "accelerometer_rotation", 0);
            } catch (Exception e) {
                e.printStackTrace();
            }
            this.y2 = false;
            vt1 vt1Var = this.H;
            vt1Var.t = false;
            SharedPreferences.Editor editorEdit = vt1Var.b.edit();
            editorEdit.putBoolean("restoreAutoRotate", false);
            editorEdit.commit();
        }
    }

    public final void u3(vd2 vd2Var) {
        if (M6 && this.H.n0) {
            V0();
        } else {
            this.s5 = vd2Var;
            x3(2, getString(R.string.button_skip), true);
        }
    }

    public final void u4(boolean z) {
        ty tyVar = this.B;
        if (tyVar != null) {
            tyVar.removeCallbacks(this.R);
        }
        cq1 cq1Var = this.v2;
        if (z) {
            boolean zHasFocus = this.n2.hasFocus();
            if (!L6) {
                this.n2.setVisibility(4);
            }
            this.r2.setVisibility(0);
            if (zHasFocus && !L6) {
                this.r2.setFocusable(true);
                this.r2.requestFocus();
            }
            Uri uriF0 = f0();
            if (uriF0 == null) {
                uriF0 = this.H.c;
            }
            if (!gt2.A(uriF0) || this.u2) {
                return;
            }
            this.u2 = true;
            this.t2 = kp2.p.get();
            this.B.postDelayed(cq1Var, 2500L);
            return;
        }
        this.u2 = false;
        ty tyVar2 = this.B;
        if (tyVar2 != null) {
            tyVar2.removeCallbacks(cq1Var);
        }
        TextView textView = this.s2;
        if (textView != null) {
            textView.setVisibility(8);
        }
        this.B.removeCallbacks(this.f1);
        if (this.V0 == 0) {
            this.T0.c();
        }
        this.r2.setAlpha(1.0f);
        this.s2.setAlpha(1.0f);
        boolean zHasFocus2 = this.r2.hasFocus();
        this.r2.setFocusable(false);
        this.r2.setVisibility(8);
        this.n2.setVisibility(0);
        if (K6 || zHasFocus2) {
            K6 = false;
            this.n2.requestFocus();
        }
    }

    public final void v() {
        this.B.setResizeMode(this.H.i);
        vt1 vt1Var = this.H;
        float f = vt1Var.k;
        this.Y1 = f;
        if (f > 0.0f) {
            this.B.t(vt1Var.i, f);
        } else {
            int i = vt1Var.i;
            ty tyVar = this.B;
            if (i == 4) {
                tyVar.setScale(vt1Var.j);
            } else {
                tyVar.setScale(1.0f);
            }
        }
        J4();
    }

    public final String v0(wf0 wf0Var) {
        StringBuilder sb = new StringBuilder(wf0Var.b());
        String strZ = ErrorActivity.z(wf0Var);
        if (strZ != null) {
            sb.append('\n');
            sb.append(strZ);
        }
        Uri uriD0 = d0();
        if (gt2.E(uriD0) || (uriD0 != null && !this.H.I0)) {
            sb.append("\n\n");
            sb.append(gt2.V(uriD0, this.H.I0));
        }
        return sb.toString();
    }

    /* JADX WARN: Code duplicated, block: B:24:0x0045  */
    /* JADX WARN: Code duplicated, block: B:36:0x0067  */
    public final al v1(int i) {
        Integer num;
        Integer num2;
        int i2;
        int[] iArr;
        int[] iArr2 = null;
        String strQ3 = this.Q3 != null ? null : Q3(this.O3, i, this.H3);
        String strQ4 = this.Q3;
        if (strQ4 == null) {
            strQ4 = Q3(this.P3, i, this.I3);
        }
        boolean z = i == this.D3;
        int i3 = -1;
        int iIntValue = z ? this.F3 : -1;
        if (g6 == null || i < 0) {
            num = null;
        } else {
            ArrayList arrayList = this.J3;
            if (i < arrayList.size()) {
                num = (Integer) arrayList.get(i);
            } else {
                num = null;
            }
        }
        if (num != null) {
            iIntValue = num.intValue();
        }
        int iIntValue2 = z ? this.G3 : -1;
        if (g6 == null || i < 0) {
            num2 = null;
        } else {
            ArrayList arrayList2 = this.K3;
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
            String strQ5 = Q3(this.L3, i, null);
            if (strQ5 != null) {
                Pattern[] patternArr = T6;
                int length = patternArr.length;
                int i4 = 0;
                while (true) {
                    if (i4 >= length) {
                        iArr2 = null;
                        break;
                    }
                    Matcher matcher = patternArr[i4].matcher(strQ5);
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
        if (this.Q3 == null) {
            i2 = iIntValue2;
            i3 = iIntValue;
        } else if (this.U3) {
            i2 = -1;
        } else if (i == this.X3) {
            i3 = this.V3;
            i2 = this.W3;
        } else {
            ArrayList arrayList3 = this.Y3;
            if (arrayList3 != null && this.Z3 >= 0) {
                Iterator it = arrayList3.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        int i5 = (i - this.X3) + this.Z3;
                        if (i5 >= 0 && i5 < this.Y3.size()) {
                            qn2 qn2Var = (qn2) this.Y3.get(i5);
                            iArr = new int[]{qn2Var.a, qn2Var.b};
                            break;
                        }
                        if (iIntValue < 1) {
                            iIntValue = 1;
                        }
                        iArr = new int[]{iIntValue, iIntValue2};
                        break;
                    }
                    qn2 qn2Var2 = (qn2) it.next();
                    if (qn2Var2.a == iIntValue && qn2Var2.b == iIntValue2) {
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
        return new al(i3, i2, strQ3, strQ4);
    }

    public final void v2(Bundle bundle) {
        Bundle bundle2 = bundle == null ? null : bundle.getBundle("trackChoice");
        if (bundle2 == null) {
            return;
        }
        this.n3 = fp2.a(bundle2.getBundle("stickyAudio"));
        this.o3 = fp2.a(bundle2.getBundle("stickySubtitle"));
        ArrayList<String> stringArrayList = bundle2.getStringArrayList("audioChosenFor");
        if (stringArrayList != null) {
            this.p3.addAll(stringArrayList);
        }
        ArrayList<String> stringArrayList2 = bundle2.getStringArrayList("subtitleChosenFor");
        if (stringArrayList2 != null) {
            this.q3.addAll(stringArrayList2);
        }
    }

    public final void v3(boolean z) {
        if (this.d5 == null) {
            return;
        }
        if (M6 && this.H.n0) {
            return;
        }
        if (this.z5 == -9223372036854775807L || !F3(z)) {
            W0();
            n3(getString(R.string.notification_skipped), false, R.drawable.ic_double_arrow_24dp);
            return;
        }
        this.D5 = SystemClock.uptimeMillis() + 5000;
        this.i5 = 1.0f;
        x3(4, getString(R.string.notification_skipped_back), true);
        this.B.postDelayed(this.r5, 5000L);
        ValueAnimator valueAnimator = this.j5;
        if (valueAnimator != null) {
            valueAnimator.cancel();
            this.j5 = null;
        }
        ty tyVar = this.B;
        cq1 cq1Var = this.L5;
        tyVar.removeCallbacks(cq1Var);
        this.B.postOnAnimation(cq1Var);
    }

    /* JADX WARN: Code duplicated, block: B:23:0x005c  */
    public final void v4() {
        String str;
        String string;
        mg0 mg0Var = g6;
        if (mg0Var == null) {
            return;
        }
        mg0Var.A1();
        rl0 rl0Var = mg0Var.V;
        y4();
        n4();
        boolean zEquals = "detailed".equals(this.H.x0);
        TextView textView = this.l1;
        float fN4 = N4();
        boolean z = false;
        boolean z2 = this.w1.getVisibility() != 0;
        String string2 = null;
        if (rl0Var == null) {
            string = null;
        } else {
            StringBuilder sb = new StringBuilder();
            if (z2) {
                e(sb, p2(rl0Var.w, rl0Var.x));
            }
            if (zEquals) {
                String strE = qy.e(rl0Var.p);
                if (strE == null) {
                    strE = qy.e(rl0Var.l);
                }
                e(sb, strE);
            }
            st stVar = rl0Var.H;
            if (stVar != null) {
                int i = stVar.c;
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
            if (zEquals && fN4 > 0.0f) {
                e(sb, String.format(Locale.US, "%.2f fps", Float.valueOf(fN4)));
            }
            string = sb.toString();
        }
        W2(textView, string);
        TextView textView2 = this.m1;
        rl0 rl0VarJ0 = J0();
        boolean z3 = this.G1.getVisibility() != 0;
        if (rl0VarJ0 != null) {
            String strM1 = m1(rl0VarJ0.d);
            String strJ4 = j4(rl0VarJ0);
            StringBuilder sb2 = new StringBuilder();
            if (z3 && strJ4 != null && !strJ4.isEmpty()) {
                e(sb2, strJ4);
            }
            if (!z3 && (strJ4 == null || strJ4.isEmpty())) {
                z = true;
            }
            if (strM1 != null && !z && !strM1.equals(strJ4)) {
                e(sb2, strM1);
            }
            if (zEquals) {
                e(sb2, qy.f(rl0VarJ0, true));
            } else {
                int i2 = rl0VarJ0.J;
                if (i2 > 2) {
                    e(sb2, gt2.s(i2));
                }
            }
            string2 = sb2.toString();
        }
        W2(textView2, string2);
        s4();
    }

    public final void w(dp2 dp2Var, int i) {
        if (g6 != null) {
            if (this.J4 != null) {
                W();
                C4();
            }
            this.G4 = false;
            int iE4 = this.p == null ? -1 : e4(1);
            if (iE4 >= 0) {
                j50 j50VarD = this.p.d();
                j50VarD.p(iE4, false);
                q50 q50Var = this.p;
                q50Var.getClass();
                q50Var.o(new k50(j50VarD));
            }
            mg0 mg0Var = g6;
            k50 k50Var = (k50) mg0Var.A0();
            k50Var.getClass();
            j50 j50Var = new j50(k50Var);
            j50Var.d(3);
            j50Var.j(3, false);
            j50Var.i(new pp2(dp2Var, Collections.singletonList(Integer.valueOf(i))));
            mg0Var.q0(j50Var.b());
            this.y4 = dp2Var;
            this.z4 = i;
            k();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final ArrayList w0() {
        File[] fileArrListFiles;
        k81 k81Var;
        ArrayList arrayList = new ArrayList();
        mg0 mg0Var = g6;
        if (mg0Var != null) {
            p81 p81VarZ = mg0Var.z();
            Object[] objArr = 0;
            if (p81VarZ != null && (k81Var = p81VarZ.b) != null) {
                fw0 fw0VarN = k81Var.g.listIterator(0);
                while (fw0VarN.hasNext()) {
                    o81 o81Var = (o81) fw0VarN.next();
                    if (!arrayList.contains(o81Var.a)) {
                        arrayList.add(o81Var.a);
                    }
                }
            }
            Uri[] uriArr = {this.J4, this.H.e, this.t4};
            for (int i = 0; i < 3; i++) {
                Uri uri = uriArr[i];
                if (uri != null && !arrayList.contains(uri)) {
                    arrayList.add(uri);
                }
            }
            ArrayList<Uri> arrayList2 = new ArrayList();
            mg0 mg0Var2 = g6;
            if (mg0Var2 != null) {
                al alVarV1 = v1(mg0Var2.V());
                if (!alVarV1.m() && (fileArrListFiles = getCacheDir().listFiles(new cp1(T3(alVarV1), objArr == true ? 1 : 0))) != null) {
                    Arrays.sort(fileArrListFiles, new cc((byte) 22));
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

    public final long w2(Uri uri, long j, boolean z) {
        this.V0 = 0L;
        if (j <= 0 || "never".equals(this.H.y0)) {
            return 0L;
        }
        Long l = uri == null ? null : (Long) vt1.r(this, "lengths").get(uri.toString());
        if (l != null && j >= l.longValue() - (l.longValue() / 20)) {
            return 0L;
        }
        if (!z) {
            return j;
        }
        if (j < 30000) {
            return 0L;
        }
        this.V0 = j;
        return j;
    }

    public final void w3() {
        mk1[] mk1VarArr;
        if (g6 == null) {
            return;
        }
        Dialog dialog = this.L1;
        if (dialog != null) {
            dialog.dismiss();
        }
        sx0 sx0Var = this.b5;
        byte b = 1;
        if (sx0Var == null) {
            mk1VarArr = new mk1[0];
            break;
        }
        Iterator it = sx0Var.n.iterator();
        while (true) {
            if (!it.hasNext()) {
                mk1VarArr = new mk1[0];
                break;
            }
            if (((vd2) it.next()).c != 2) {
                String[] strArr = R6;
                int length = strArr.length;
                CharSequence[] charSequenceArr = new CharSequence[length];
                for (int i = 0; i < length; i++) {
                    charSequenceArr[i] = getString(S6[i]);
                }
                vt1 vt1Var = this.H;
                String str = vt1Var.l0.equals(vt1Var.m0) ? this.H.l0 : null;
                String str2 = this.m4;
                if (str2 == null) {
                    str2 = str;
                }
                mo1 mo1Var = new mo1(this, (byte) 4);
                mk1 mk1Var = new mk1();
                mk1Var.m = charSequenceArr;
                mk1Var.n = strArr;
                mk1Var.l = str2;
                mk1Var.o = str;
                mk1Var.p = mo1Var;
                mk1VarArr = new mk1[]{mk1Var};
                break;
            }
        }
        mk1[] mk1VarArr2 = mk1VarArr;
        Dialog dialogI = tf.i(this, this.V1, getString(R.string.skip_session_title), 30.0d, 0.25d, mk1VarArr2, null, new nk1(mk1VarArr2.length != 0 ? getString(R.string.skip_offset_title) : null, this.i4, new mo1(this, b)));
        this.L1 = dialogI;
        o3(dialogI);
    }

    public final void w4() {
        if (this.t1 == null) {
            return;
        }
        boolean z = this.H.r0 && !this.G;
        yl1 yl1Var = this.u1;
        if (yl1Var != null) {
            yl1Var.setAlpha(z ? 0.0f : 1.0f);
        }
        if (z) {
            d4();
        }
        this.t1.setVisibility(z ? 0 : 8);
    }

    public final void x(yq1 yq1Var) {
        mg0 mg0Var = g6;
        if (mg0Var != null) {
            int i = yq1Var.f;
            String str = yq1Var.h;
            dp2 dp2Var = yq1Var.e;
            byte b = yq1Var.d;
            if (b == 3) {
                if (str == null || str.trim().isEmpty()) {
                    return;
                }
                Uri uri = Uri.parse(str);
                if (uri.equals(f0())) {
                    return;
                }
                this.d4 = (byte) 3;
                this.e4 = null;
                this.f4 = -1;
                this.g4 = S1(yq1Var.a);
                b4(uri, Math.max(0L, g6.O0()), g6.w());
                return;
            }
            this.d4 = b;
            this.e4 = dp2Var;
            this.f4 = i;
            this.g4 = 0;
            k50 k50Var = (k50) mg0Var.A0();
            k50Var.getClass();
            j50 j50Var = new j50(k50Var);
            j50Var.d(2);
            j50Var.h(b == 1);
            if (b == 1) {
                j50Var.e();
            } else {
                j50Var.m();
            }
            if (b == 2 && dp2Var != null) {
                j50Var.i(new pp2(dp2Var, Collections.singletonList(Integer.valueOf(i))));
            }
            g6.q0(j50Var.b());
        }
    }

    public final void x1(boolean z) {
        Intent intent = new Intent(z ? "android.media.action.OPEN_AUDIO_EFFECT_CONTROL_SESSION" : "android.media.action.CLOSE_AUDIO_EFFECT_CONTROL_SESSION");
        mg0 mg0Var = g6;
        mg0Var.A1();
        intent.putExtra("android.media.extra.AUDIO_SESSION", ((Integer) mg0Var.C.j()).intValue());
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

    public final void x2() {
        if (M6 && this.H.n0) {
            return;
        }
        if (this.u0) {
            x3(2, getString(R.string.button_skip), false);
            A4(this.s5);
        } else if (this.c6 == 2) {
            V0();
        }
    }

    public final void x3(int i, String str, boolean z) {
        if (this.d5 == null) {
            return;
        }
        if (this.G || this.Z4) {
            W0();
            return;
        }
        ty tyVar = this.B;
        if (tyVar != null) {
            tyVar.removeCallbacks(this.r5);
        }
        boolean z2 = this.c6 != i;
        this.c6 = i;
        boolean z3 = this.d5.getVisibility() != 0;
        this.d5.animate().cancel();
        if (!z2 || z3) {
            n(i, str);
            Button button = this.d5;
            if (z3) {
                button.setAlpha(0.0f);
                this.d5.setVisibility(0);
                this.d5.animate().alpha(1.0f).setDuration(250L).setInterpolator(J6).start();
                this.d5.post(new np1(this, (byte) 13));
            } else {
                button.setAlpha(1.0f);
            }
        } else {
            n(i, str);
            this.d5.setClickable(false);
            this.d5.setAlpha(1.0f);
            this.d5.animate().alpha(0.4f).setDuration(100L).withEndAction(new np1(this, (byte) 12)).start();
            ty tyVar2 = this.B;
            if (tyVar2 != null) {
                lo1 lo1Var = this.q5;
                tyVar2.removeCallbacks(lo1Var);
                this.B.postDelayed(lo1Var, 200L);
            }
        }
        if (L6 && z) {
            if (z3 || z2) {
                this.d5.requestFocus();
            }
        }
    }

    public final boolean x4(int i, int i2, int i3, int i4) {
        try {
            ArrayList arrayList = new ArrayList();
            PendingIntent broadcast = PendingIntent.getBroadcast(this, i4, new Intent("media_control").putExtra("control_type", i3), 67108864);
            Icon iconCreateWithResource = Icon.createWithResource(this, i);
            String string = getString(i2);
            arrayList.add(new RemoteAction(iconCreateWithResource, string, string, broadcast));
            mb1.e(this.F).setActions(arrayList);
            setPictureInPictureParams(mb1.e(this.F).build());
            return true;
        } catch (IllegalStateException e) {
            e.printStackTrace();
            return false;
        }
    }

    public final void y(long j, final Integer num) {
        this.V0 = j;
        final byte b = 1;
        this.T0.e(true);
        this.r2.setAlpha(0.0f);
        this.s2.setAlpha(0.0f);
        StringBuilder sb = new StringBuilder();
        String strL = ys2.L(sb, new Formatter(sb, Locale.getDefault()), j);
        final byte b2 = 0;
        r2.d(this, getString(R.string.resume_ask_title), getString(R.string.resume_ask_message, strL), getString(R.string.resume_ask_continue), new Runnable(this) { // from class: aq1
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
                        LoudnessEnhancer loudnessEnhancer = PlayerActivity.e6;
                        playerActivity.b(false, num2);
                        break;
                    default:
                        LoudnessEnhancer loudnessEnhancer2 = PlayerActivity.e6;
                        playerActivity.b(true, num2);
                        break;
                }
            }
        }, getString(R.string.resume_ask_start_over), new Runnable(this) { // from class: aq1
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
                        LoudnessEnhancer loudnessEnhancer = PlayerActivity.e6;
                        playerActivity.b(false, num2);
                        break;
                    default:
                        LoudnessEnhancer loudnessEnhancer2 = PlayerActivity.e6;
                        playerActivity.b(true, num2);
                        break;
                }
            }
        }, true);
    }

    public final Uri y0() {
        b90[] b90VarArrU1 = u1();
        if (b90VarArrU1 != null) {
            boolean z = false;
            b90 b90Var = b90VarArrU1[0];
            ArrayList<b90> arrayListA = zi0.A(b90VarArrU1[1]);
            String strC = b90Var == null ? null : b90Var.c();
            if (strC == null) {
                b90Var = null;
            } else {
                for (b90 b90Var2 : arrayListA) {
                    if (b90Var2.c().equals(strC)) {
                        z = true;
                    } else if (!z || !zi0.x(b90Var2)) {
                    }
                }
                b90Var2 = null;
            }
            if (b90Var2 != null) {
                return b90Var2.e();
            }
        }
        return null;
    }

    public final JSONObject y2() {
        int i;
        Uri uriF0 = f0();
        if (uriF0 != null && gt2.E(uriF0)) {
            String strW = this.w3;
            if (strW == null) {
                strW = gt2.w(this, uriF0);
            }
            String str = this.I3;
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
                return jSONObjectPut.put("title", strW).put("poster", gt2.E(this.x3) ? this.x3.toString() : "").put("tmdb", i).put("source", "tmdb").put("type", this.F3 > 0 ? "tv" : "movie");
            } catch (Exception unused2) {
            }
        }
        return null;
    }

    /* JADX WARN: Type inference failed for: r7v8, types: [qo1] */
    public final j5 y3(String str, final String str2) {
        ContextThemeWrapper contextThemeWrapperE = r2.e(this);
        if (L6) {
            a51 a51Var = new a51(contextThemeWrapperE);
            e5 e5Var = a51Var.a;
            e5Var.f = str;
            a51Var.setPositiveButton(android.R.string.ok, new po1());
            if (str2 != null) {
                ?? r7 = new DialogInterface.OnClickListener() { // from class: qo1
                    @Override // android.content.DialogInterface.OnClickListener
                    public final void onClick(DialogInterface dialogInterface, int i) {
                        LoudnessEnhancer loudnessEnhancer = PlayerActivity.e6;
                        PlayerActivity playerActivity = this.l;
                        String str3 = str2;
                        playerActivity.j3(str3, str3, null);
                    }
                };
                e5Var.k = e5Var.a.getText(R.string.error_details);
                e5Var.l = r7;
            }
            j5 j5VarCreate = a51Var.create();
            j5VarCreate.show();
            return j5VarCreate;
        }
        ty tyVar = this.B;
        if (tyVar != null) {
            tyVar.u(0, null);
        }
        me2 me2VarY = o61.y(this, str, true, R.drawable.ic_info_24dp);
        E6 = me2VarY;
        if (me2VarY == null) {
            return null;
        }
        if (str2 != null) {
            qk qkVar = new qk(this, str2, (byte) 2);
            CharSequence text = me2VarY.h.getText(R.string.error_details);
            Button actionView = ((SnackbarContentLayout) me2VarY.i.getChildAt(0)).getActionView();
            if (TextUtils.isEmpty(text)) {
                actionView.setVisibility(8);
                actionView.setOnClickListener(null);
                me2VarY.B = false;
            } else {
                me2VarY.B = true;
                actionView.setVisibility(0);
                actionView.setText(text);
                actionView.setOnClickListener(new qk(me2VarY, qkVar, (byte) 4));
            }
            me2 me2Var = E6;
            ((SnackbarContentLayout) me2Var.i.getChildAt(0)).getActionView().setTextColor(lj.n(this, R.attr.colorPrimary, -1));
        }
        E6.g();
        return null;
    }

    public final void y4() {
        rl0 rl0Var;
        String strP2;
        if (this.w1 == null || this.F1) {
            return;
        }
        ArrayList arrayListJ = J();
        this.x1 = g6 != null && arrayListJ.size() >= 2;
        q();
        int iS2 = arrayListJ.isEmpty() ? -1 : S2(arrayListJ);
        mg0 mg0Var = g6;
        if (mg0Var != null) {
            mg0Var.A1();
            rl0Var = mg0Var.V;
        } else {
            rl0Var = null;
        }
        if (iS2 < 0 || ((yq1) arrayListJ.get(iS2)).d != 3) {
            strP2 = rl0Var != null ? p2(rl0Var.w, rl0Var.x) : null;
        } else {
            strP2 = ((yq1) arrayListJ.get(iS2)).a;
        }
        if (strP2 == null) {
            strP2 = getString(R.string.quality_auto);
        }
        this.z1 = strP2;
        this.w1.setText(A2() ? null : this.z1);
    }

    public final void z(rn2 rn2Var, ArrayList arrayList, Runnable runnable) {
        int i;
        int i2;
        ContextThemeWrapper contextThemeWrapperE = r2.e(this);
        mg0 mg0Var = g6;
        al alVarV1 = mg0Var != null ? v1(mg0Var.V()) : null;
        LinearLayout linearLayoutC = we2.c(contextThemeWrapperE, 1);
        int iP = gt2.p(10);
        linearLayoutC.setPadding(iP, iP, iP, iP);
        Dialog dialog = new Dialog(this, android.R.style.Theme.Translucent.NoTitleBar);
        c70 c70Var = new c70(dialog, runnable, (byte) 2);
        TextView textView = new TextView(contextThemeWrapperE);
        textView.setText(getString(R.string.subtitle_search_type));
        textView.setTextColor(lj.n(contextThemeWrapperE, R.attr.colorOnSurface, -1));
        textView.setTextSize(2, this.V1.y());
        textView.setTypeface(Typeface.DEFAULT_BOLD);
        textView.setPadding(gt2.p(6), gt2.p(10), gt2.p(6), gt2.p(10));
        linearLayoutC.addView(r2.s(contextThemeWrapperE, this.V1, textView, c70Var));
        EditText editTextF = r2.F(linearLayoutC, getString(R.string.subtitle_search_season_label), null);
        editTextF.setInputType(2);
        if (alVarV1 != null && (i2 = alVarV1.l) >= 0) {
            editTextF.setText(String.valueOf(i2));
        }
        EditText editTextF2 = r2.F(linearLayoutC, getString(R.string.subtitle_search_episode_label), null);
        editTextF2.setInputType(2);
        editTextF2.setImeOptions(268435462);
        if (alVarV1 != null && (i = alVarV1.m) >= 1) {
            editTextF2.setText(String.valueOf(i));
        }
        final vp1 vp1Var = new vp1(this, dialog, rn2Var, editTextF, editTextF2, arrayList);
        MaterialButton materialButton = new MaterialButton(contextThemeWrapperE, null, R.attr.materialButtonStyle);
        materialButton.setText(android.R.string.ok);
        materialButton.setTextSize(2, this.V1.r());
        materialButton.setInsetTop(0);
        materialButton.setInsetBottom(0);
        materialButton.setMinHeight(this.V1.b(48.0f));
        gt2.r(materialButton);
        materialButton.setOnClickListener(new qs(vp1Var, (byte) 9));
        editTextF2.setOnEditorActionListener(new TextView.OnEditorActionListener() { // from class: wp1
            @Override // android.widget.TextView.OnEditorActionListener
            public final boolean onEditorAction(TextView textView2, int i3, KeyEvent keyEvent) {
                LoudnessEnhancer loudnessEnhancer = PlayerActivity.e6;
                vp1Var.run();
                return true;
            }
        });
        LinearLayout linearLayout = new LinearLayout(contextThemeWrapperE);
        linearLayout.setOrientation(0);
        linearLayout.setGravity(8388613);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        layoutParams.topMargin = gt2.p(12);
        linearLayout.addView(materialButton);
        linearLayoutC.addView(linearLayout, layoutParams);
        ScrollView scrollView = new ScrollView(contextThemeWrapperE);
        scrollView.addView(linearLayoutC, new ViewGroup.LayoutParams(-1, -2));
        r2.v(this, this.V1, dialog, scrollView, false);
        gt2.G(dialog, scrollView);
        gt2.O(dialog, c70Var);
        editTextF.requestFocus();
        o3(dialog);
    }

    public final void z0() {
        this.B.removeCallbacks(this.W5);
        this.S5 = 0L;
        this.T5 = 0;
        this.U5 = false;
        this.V5 = false;
        C2();
        mg0 mg0Var = g6;
        if (mg0Var != null) {
            mg0Var.k(false);
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

    public final SpannableString z2(String str) {
        SpannableString spannableString = new SpannableString(str);
        float dimension = getResources().getDimension(R.dimen.exo_error_message_text_size) / getResources().getDisplayMetrics().scaledDensity;
        if (dimension > 0.0f) {
            spannableString.setSpan(new RelativeSizeSpan(this.V1.q(13.0f, 14.0f, 14.0f, 15.0f) / dimension), 0, spannableString.length(), 33);
        }
        return spannableString;
    }

    public final void z3() {
        bl2 bl2Var = this.c2;
        if (bl2Var == null || this.G) {
            return;
        }
        bl2Var.setVisibility(0);
        ty tyVar = this.B;
        if (tyVar != null) {
            lo1 lo1Var = this.P5;
            tyVar.removeCallbacks(lo1Var);
            this.B.postDelayed(lo1Var, 1400L);
        }
    }

    public final void z4() {
        String string;
        String string2;
        zn2 zn2Var = this.V4;
        boolean z = zn2Var != null && zn2Var.j();
        TextView textView = this.q1;
        if (textView != null) {
            boolean z2 = z && C6 && !this.G && !M6;
            if (z2) {
                zn2 zn2Var2 = this.V4;
                if (zn2Var2.L) {
                    string2 = getString(R.string.together_badge, zn2Var2.f(), Integer.valueOf(this.V4.N));
                } else {
                    string2 = getString(zn2Var2.M ? R.string.together_offline : R.string.together_connecting, zn2Var2.f());
                }
                textView.setText(string2);
                int iN = lj.n(new ContextThemeWrapper(this, vt1.a(this, false)), R.attr.accentInk, -1);
                new ColorStateList(new int[][]{new int[]{android.R.attr.state_selected}, new int[0]}, new int[]{iN, -419430401});
                TextView textView2 = this.q1;
                if (this.V4.L) {
                    iN = -419430401;
                }
                textView2.setTextColor(iN);
            }
            x0(this.q1, z2);
        }
        TextView textView3 = this.a5;
        if (textView3 == null) {
            return;
        }
        boolean z3 = z && this.V4.s;
        String string3 = z ? this.V4.O : null;
        if ((!z3 && string3 == null) || this.G || M6) {
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
        this.a5.setVisibility(0);
    }
}
