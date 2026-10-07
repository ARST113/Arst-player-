package defpackage;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.media.audiofx.LoudnessEnhancer;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.brouken.player.PlayerActivity;
import com.justplus.player.R;
import j$.util.Objects;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collections;
import java.util.Formatter;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: compiled from: r8-map-id-0cee80150bab987b3edc91bcfc1e04786a1418881e5c92a3b999d44d61001263 */
/* JADX INFO: loaded from: classes.dex */
public final class wr1 extends FrameLayout {
    public static final float[] S0;
    public final lr1 A;
    public to1 A0;
    public final PopupWindow B;
    public pr1 B0;
    public final int C;
    public boolean C0;
    public final ImageView D;
    public boolean D0;
    public final ImageView E;
    public boolean E0;
    public final ImageView F;
    public boolean F0;
    public final View G;
    public boolean G0;
    public final View H;
    public boolean H0;
    public final TextView I;
    public int I0;
    public final TextView J;
    public boolean J0;
    public final ImageView K;
    public int K0;
    public final ImageView L;
    public int L0;
    public final ImageView M;
    public long[] M0;
    public final ImageView N;
    public boolean[] N0;
    public final ImageView O;
    public final long[] O0;
    public final ImageView P;
    public final boolean[] P0;
    public final View Q;
    public long Q0;
    public final View R;
    public boolean R0;
    public final View S;
    public final TextView T;
    public final TextView U;
    public final rn2 V;
    public final StringBuilder W;
    public final Formatter a0;
    public final wn2 b0;
    public final yn2 c0;
    public final lj1 d0;
    public final Drawable e0;
    public final Drawable f0;
    public final Drawable g0;
    public final Drawable h0;
    public final Drawable i0;
    public final String j0;
    public final String k0;
    public final bs1 l;
    public final String l0;
    public final Resources m;
    public final Drawable m0;
    public final Handler n;
    public final Drawable n0;
    public final mr1 o;
    public final float o0;
    public final Class p;
    public final float p0;
    public final Method q;
    public final String q0;
    public final Method r;
    public final String r0;
    public final Class s;
    public final Drawable s0;
    public final Method t;
    public final Drawable t0;
    private final dq2 trackNameProvider;
    public final Method u;
    public final String u0;
    public final CopyOnWriteArrayList v;
    public final String v0;
    public final RecyclerView w;
    public final Drawable w0;
    public final rr1 x;
    public final Drawable x0;
    public final or1 y;
    public final String y0;
    public final lr1 z;
    public final String z0;

    static {
        a91.a("media3.ui");
        S0 = new float[]{0.25f, 0.5f, 0.75f, 1.0f, 1.25f, 1.5f, 1.75f, 2.0f};
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Unreachable blocks removed: 1, instructions: 2 */
    public wr1(Context context, AttributeSet attributeSet) throws NoSuchMethodException {
        int resourceId;
        int resourceId2;
        int resourceId3;
        int resourceId4;
        int resourceId5;
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        boolean z5;
        boolean z6;
        boolean z7;
        int i8;
        int i9;
        int i10;
        boolean z8;
        Method method;
        Method method2;
        Method method3;
        Class<?> cls;
        Method method4;
        int i11;
        Handler.Callback callback;
        Object obj;
        ImageView imageView;
        Typeface typefaceB;
        super(context, null, 0);
        Class<?> cls2 = Boolean.TYPE;
        this.F0 = true;
        this.I0 = 5000;
        this.L0 = 0;
        this.K0 = 200;
        int resourceId6 = R.drawable.exo_styled_controls_play;
        int resourceId7 = R.drawable.exo_styled_controls_simple_fastforward;
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, jw1.c, 0, 0);
            try {
                int resourceId8 = typedArrayObtainStyledAttributes.getResourceId(6, R.layout.exo_player_control_view);
                resourceId6 = typedArrayObtainStyledAttributes.getResourceId(12, R.drawable.exo_styled_controls_play);
                int resourceId9 = typedArrayObtainStyledAttributes.getResourceId(11, R.drawable.exo_styled_controls_pause);
                int resourceId10 = typedArrayObtainStyledAttributes.getResourceId(10, R.drawable.exo_styled_controls_next);
                resourceId7 = typedArrayObtainStyledAttributes.getResourceId(7, R.drawable.exo_styled_controls_simple_fastforward);
                int resourceId11 = typedArrayObtainStyledAttributes.getResourceId(15, R.drawable.exo_styled_controls_previous);
                int resourceId12 = typedArrayObtainStyledAttributes.getResourceId(20, R.drawable.exo_styled_controls_simple_rewind);
                int resourceId13 = typedArrayObtainStyledAttributes.getResourceId(9, R.drawable.exo_styled_controls_fullscreen_exit);
                resourceId = typedArrayObtainStyledAttributes.getResourceId(8, R.drawable.exo_styled_controls_fullscreen_enter);
                int resourceId14 = typedArrayObtainStyledAttributes.getResourceId(17, R.drawable.exo_styled_controls_repeat_off);
                int resourceId15 = typedArrayObtainStyledAttributes.getResourceId(18, R.drawable.exo_styled_controls_repeat_one);
                int resourceId16 = typedArrayObtainStyledAttributes.getResourceId(16, R.drawable.exo_styled_controls_repeat_all);
                resourceId2 = typedArrayObtainStyledAttributes.getResourceId(35, R.drawable.exo_styled_controls_shuffle_on);
                resourceId3 = typedArrayObtainStyledAttributes.getResourceId(34, R.drawable.exo_styled_controls_shuffle_off);
                resourceId4 = typedArrayObtainStyledAttributes.getResourceId(37, R.drawable.exo_styled_controls_subtitle_on);
                resourceId5 = typedArrayObtainStyledAttributes.getResourceId(36, R.drawable.exo_styled_controls_subtitle_off);
                int resourceId17 = typedArrayObtainStyledAttributes.getResourceId(42, R.drawable.exo_styled_controls_vr);
                this.I0 = typedArrayObtainStyledAttributes.getInt(32, this.I0);
                this.L0 = typedArrayObtainStyledAttributes.getInt(19, this.L0);
                z4 = typedArrayObtainStyledAttributes.getBoolean(29, true);
                z5 = typedArrayObtainStyledAttributes.getBoolean(26, true);
                z6 = typedArrayObtainStyledAttributes.getBoolean(28, true);
                z7 = typedArrayObtainStyledAttributes.getBoolean(27, true);
                z8 = typedArrayObtainStyledAttributes.getBoolean(30, false);
                boolean z9 = typedArrayObtainStyledAttributes.getBoolean(31, false);
                boolean z10 = typedArrayObtainStyledAttributes.getBoolean(33, false);
                this.J0 = typedArrayObtainStyledAttributes.getBoolean(39, false);
                setTimeBarMinUpdateInterval(typedArrayObtainStyledAttributes.getInt(38, this.K0));
                boolean z11 = typedArrayObtainStyledAttributes.getBoolean(2, true);
                typedArrayObtainStyledAttributes.recycle();
                i2 = resourceId11;
                i9 = resourceId8;
                i3 = resourceId9;
                i4 = resourceId10;
                i5 = resourceId14;
                i6 = resourceId15;
                i7 = resourceId16;
                i = resourceId17;
                i10 = resourceId13;
                z3 = z9;
                z2 = z10;
                i8 = resourceId12;
                z = z11;
            } catch (Throwable th) {
                typedArrayObtainStyledAttributes.recycle();
                throw th;
            }
        } else {
            resourceId = R.drawable.exo_styled_controls_fullscreen_enter;
            resourceId2 = R.drawable.exo_styled_controls_shuffle_on;
            resourceId3 = R.drawable.exo_styled_controls_shuffle_off;
            resourceId4 = R.drawable.exo_styled_controls_subtitle_on;
            resourceId5 = R.drawable.exo_styled_controls_subtitle_off;
            i = R.drawable.exo_styled_controls_vr;
            i2 = R.drawable.exo_styled_controls_previous;
            i3 = R.drawable.exo_styled_controls_pause;
            i4 = R.drawable.exo_styled_controls_next;
            i5 = R.drawable.exo_styled_controls_repeat_off;
            i6 = R.drawable.exo_styled_controls_repeat_one;
            i7 = R.drawable.exo_styled_controls_repeat_all;
            z = true;
            z2 = false;
            z3 = false;
            z4 = true;
            z5 = true;
            z6 = true;
            z7 = true;
            i8 = R.drawable.exo_styled_controls_simple_rewind;
            i9 = R.layout.exo_player_control_view;
            i10 = R.drawable.exo_styled_controls_fullscreen_exit;
            z8 = false;
        }
        int i12 = resourceId6;
        int i13 = resourceId;
        int i14 = resourceId7;
        LayoutInflater.from(context).inflate(i9, this);
        setDescendantFocusability(262144);
        this.o = new mr1(this);
        this.v = new CopyOnWriteArrayList();
        this.b0 = new wn2();
        this.c0 = new yn2();
        StringBuilder sb = new StringBuilder();
        this.W = sb;
        boolean z12 = z8;
        this.a0 = new Formatter(sb, Locale.getDefault());
        this.M0 = new long[0];
        this.N0 = new boolean[0];
        this.O0 = new long[0];
        this.P0 = new boolean[0];
        this.d0 = new lj1((Object) this, (byte) 4);
        try {
            method = ExoPlayer.class.getMethod("setScrubbingModeEnabled", cls2);
            try {
                method2 = ExoPlayer.class.getMethod("isScrubbingModeEnabled", null);
            } catch (ClassNotFoundException | NoSuchMethodException unused) {
                method2 = null;
            }
        } catch (ClassNotFoundException | NoSuchMethodException unused2) {
            method = null;
        }
        this.p = ExoPlayer.class;
        this.q = method;
        this.r = method2;
        try {
            cls = Class.forName("androidx.media3.transformer.CompositionPlayer");
            try {
                method3 = cls.getMethod("setScrubbingModeEnabled", cls2);
                try {
                    method4 = cls.getMethod("isScrubbingModeEnabled", null);
                } catch (ClassNotFoundException | NoSuchMethodException unused3) {
                    method4 = null;
                }
            } catch (ClassNotFoundException | NoSuchMethodException unused4) {
                method3 = null;
            }
        } catch (ClassNotFoundException | NoSuchMethodException unused5) {
            method3 = null;
            cls = null;
        }
        this.s = cls;
        this.t = method3;
        this.u = method4;
        this.T = (TextView) findViewById(R.id.exo_duration);
        this.U = (TextView) findViewById(R.id.exo_position);
        ImageView imageView2 = (ImageView) findViewById(R.id.exo_subtitle);
        this.N = imageView2;
        if (imageView2 != null) {
            imageView2.setOnClickListener(this.o);
        }
        ImageView imageView3 = (ImageView) findViewById(R.id.exo_fullscreen);
        this.O = imageView3;
        byte b = 10;
        zs zsVar = new zs(this, b);
        if (imageView3 == null) {
            i11 = 8;
        } else {
            i11 = 8;
            imageView3.setVisibility(8);
            imageView3.setOnClickListener(zsVar);
        }
        ImageView imageView4 = (ImageView) findViewById(R.id.exo_minimal_fullscreen);
        this.P = imageView4;
        zs zsVar2 = new zs(this, b);
        if (imageView4 != null) {
            imageView4.setVisibility(i11);
            imageView4.setOnClickListener(zsVar2);
        }
        View viewFindViewById = findViewById(R.id.exo_settings);
        this.Q = viewFindViewById;
        if (viewFindViewById != null) {
            viewFindViewById.setOnClickListener(this.o);
        }
        View viewFindViewById2 = findViewById(R.id.exo_playback_speed);
        this.R = viewFindViewById2;
        if (viewFindViewById2 != null) {
            viewFindViewById2.setOnClickListener(this.o);
        }
        View viewFindViewById3 = findViewById(R.id.exo_audio_track);
        this.S = viewFindViewById3;
        if (viewFindViewById3 != null) {
            viewFindViewById3.setOnClickListener(this.o);
        }
        rn2 rn2Var = (rn2) findViewById(R.id.exo_progress);
        View viewFindViewById4 = findViewById(R.id.exo_progress_placeholder);
        if (rn2Var != null) {
            this.V = rn2Var;
            obj = rn2Var;
            callback = null;
        } else if (viewFindViewById4 != null) {
            callback = null;
            l50 l50Var = new l50(context, null, attributeSet, R.style.ExoStyledControls_TimeBar);
            l50Var.setId(R.id.exo_progress);
            l50Var.setLayoutParams(viewFindViewById4.getLayoutParams());
            ViewGroup viewGroup = (ViewGroup) viewFindViewById4.getParent();
            int iIndexOfChild = viewGroup.indexOfChild(viewFindViewById4);
            viewGroup.removeView(viewFindViewById4);
            viewGroup.addView(l50Var, iIndexOfChild);
            this.V = l50Var;
            obj = l50Var;
        } else {
            callback = null;
            this.V = null;
            obj = null;
        }
        if (obj != null) {
            mr1 mr1Var = this.o;
            mr1Var.getClass();
            ((l50) obj).F.add(mr1Var);
        }
        this.n = qt2.r(callback);
        Resources resources = context.getResources();
        this.m = resources;
        ImageView imageView5 = (ImageView) findViewById(R.id.exo_play_pause);
        this.F = imageView5;
        if (imageView5 != null) {
            imageView5.setOnClickListener(this.o);
        }
        ImageView imageView6 = (ImageView) findViewById(R.id.exo_prev);
        this.D = imageView6;
        if (imageView6 != null) {
            imageView6.setImageDrawable(resources.getDrawable(i2, context.getTheme()));
            imageView6.setOnClickListener(this.o);
        }
        ImageView imageView7 = (ImageView) findViewById(R.id.exo_next);
        this.E = imageView7;
        if (imageView7 != null) {
            imageView7.setImageDrawable(resources.getDrawable(i4, context.getTheme()));
            imageView7.setOnClickListener(this.o);
        }
        ThreadLocal threadLocal = p12.a;
        if (context.isRestricted()) {
            imageView = imageView7;
            typefaceB = null;
        } else {
            imageView = imageView7;
            typefaceB = p12.b(context, R.font.roboto_medium_numbers, new TypedValue(), 0, null, false, false);
        }
        ImageView imageView8 = (ImageView) findViewById(R.id.exo_rew);
        TextView textView = (TextView) findViewById(R.id.exo_rew_with_amount);
        if (imageView8 != null) {
            imageView8.setImageDrawable(resources.getDrawable(i8, context.getTheme()));
            this.H = imageView8;
            this.J = null;
        } else if (textView != null) {
            textView.setTypeface(typefaceB);
            this.J = textView;
            this.H = textView;
        } else {
            this.J = null;
            this.H = null;
        }
        View view = this.H;
        if (view != null) {
            view.setOnClickListener(this.o);
        }
        ImageView imageView9 = (ImageView) findViewById(R.id.exo_ffwd);
        TextView textView2 = (TextView) findViewById(R.id.exo_ffwd_with_amount);
        if (imageView9 != null) {
            imageView9.setImageDrawable(resources.getDrawable(i14, context.getTheme()));
            this.G = imageView9;
            this.I = null;
        } else if (textView2 != null) {
            textView2.setTypeface(typefaceB);
            this.I = textView2;
            this.G = textView2;
        } else {
            this.I = null;
            this.G = null;
        }
        View view2 = this.G;
        if (view2 != null) {
            view2.setOnClickListener(this.o);
        }
        ImageView imageView10 = (ImageView) findViewById(R.id.exo_repeat_toggle);
        this.K = imageView10;
        if (imageView10 != null) {
            imageView10.setOnClickListener(this.o);
        }
        ImageView imageView11 = (ImageView) findViewById(R.id.exo_shuffle);
        this.L = imageView11;
        if (imageView11 != null) {
            imageView11.setOnClickListener(this.o);
        }
        this.o0 = resources.getInteger(R.integer.exo_media_button_opacity_percentage_enabled) / 100.0f;
        this.p0 = resources.getInteger(R.integer.exo_media_button_opacity_percentage_disabled) / 100.0f;
        ImageView imageView12 = (ImageView) findViewById(R.id.exo_vr);
        this.M = imageView12;
        if (imageView12 != null) {
            imageView12.setImageDrawable(resources.getDrawable(i, context.getTheme()));
            n(imageView12, false);
        }
        bs1 bs1Var = new bs1(this);
        this.l = bs1Var;
        bs1Var.D = z;
        rr1 rr1Var = new rr1(this, new String[]{resources.getString(R.string.exo_controls_playback_speed), resources.getString(R.string.exo_track_selection_title_audio)}, new Drawable[]{resources.getDrawable(R.drawable.exo_styled_controls_speed, context.getTheme()), resources.getDrawable(R.drawable.exo_styled_controls_audiotrack, context.getTheme())});
        this.x = rr1Var;
        this.C = resources.getDimensionPixelSize(R.dimen.exo_settings_offset);
        RecyclerView recyclerView = (RecyclerView) LayoutInflater.from(context).inflate(R.layout.exo_styled_settings_list, (ViewGroup) null);
        this.w = recyclerView;
        recyclerView.setAdapter(rr1Var);
        getContext();
        recyclerView.setLayoutManager(new LinearLayoutManager(1));
        PopupWindow popupWindow = new PopupWindow((View) recyclerView, -2, -2, true);
        this.B = popupWindow;
        popupWindow.setOnDismissListener(this.o);
        this.R0 = true;
        this.trackNameProvider = new pi(getResources());
        this.s0 = resources.getDrawable(resourceId4, context.getTheme());
        this.t0 = resources.getDrawable(resourceId5, context.getTheme());
        this.u0 = resources.getString(R.string.exo_controls_cc_enabled_description);
        this.v0 = resources.getString(R.string.exo_controls_cc_disabled_description);
        this.z = new lr1(this, (byte) 1);
        this.A = new lr1(this, (byte) 0);
        this.y = new or1(this, resources.getStringArray(R.array.exo_controls_playback_speeds), S0);
        this.e0 = resources.getDrawable(i12, context.getTheme());
        this.f0 = resources.getDrawable(i3, context.getTheme());
        this.w0 = resources.getDrawable(i10, context.getTheme());
        this.x0 = resources.getDrawable(i13, context.getTheme());
        this.g0 = resources.getDrawable(i5, context.getTheme());
        this.h0 = resources.getDrawable(i6, context.getTheme());
        this.i0 = resources.getDrawable(i7, context.getTheme());
        this.m0 = resources.getDrawable(resourceId2, context.getTheme());
        this.n0 = resources.getDrawable(resourceId3, context.getTheme());
        this.y0 = resources.getString(R.string.exo_controls_fullscreen_exit_description);
        this.z0 = resources.getString(R.string.exo_controls_fullscreen_enter_description);
        this.j0 = resources.getString(R.string.exo_controls_repeat_off_description);
        this.k0 = resources.getString(R.string.exo_controls_repeat_one_description);
        this.l0 = resources.getString(R.string.exo_controls_repeat_all_description);
        this.q0 = resources.getString(R.string.exo_controls_shuffle_on_description);
        this.r0 = resources.getString(R.string.exo_controls_shuffle_off_description);
        bs1Var.h((ViewGroup) findViewById(R.id.exo_bottom_bar), true);
        bs1Var.h(this.G, z5);
        bs1Var.h(this.H, z4);
        bs1Var.h(imageView6, z6);
        bs1Var.h(imageView, z7);
        bs1Var.h(imageView11, z12);
        bs1Var.h(this.N, z3);
        bs1Var.h(imageView12, z2);
        bs1Var.h(imageView10, this.L0 != 0);
        addOnLayoutChangeListener(new tk(this, (byte) 3));
    }

    public static boolean b(to1 to1Var, yn2 yn2Var) {
        zn2 zn2VarR0;
        int iO;
        if (to1Var.W(17) && (iO = (zn2VarR0 = to1Var.r0()).o()) > 1 && iO <= 100) {
            for (int i = 0; i < iO; i++) {
                if (zn2VarR0.m(i, yn2Var, 0L).m != -9223372036854775807L) {
                }
            }
            return true;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setPlaybackSpeed(float f) {
        to1 to1Var = this.A0;
        if (to1Var == null || !to1Var.W(13)) {
            return;
        }
        to1 to1Var2 = this.A0;
        to1Var2.setPlaybackParameters(new fo1(f, to1Var2.getPlaybackParameters().b));
    }

    public final boolean c(KeyEvent keyEvent) {
        int keyCode = keyEvent.getKeyCode();
        to1 to1Var = this.A0;
        if (to1Var == null) {
            return false;
        }
        if (keyCode != 90 && keyCode != 89 && keyCode != 85 && keyCode != 79 && keyCode != 126 && keyCode != 127 && keyCode != 87 && keyCode != 88) {
            return false;
        }
        if (keyEvent.getAction() != 0) {
            return true;
        }
        if (keyCode == 90) {
            if (to1Var.C() == 4 || !to1Var.W(12)) {
                return true;
            }
            to1Var.G0();
            return true;
        }
        if (keyCode == 89 && to1Var.W(11)) {
            to1Var.I0();
            return true;
        }
        if (keyEvent.getRepeatCount() != 0) {
            return true;
        }
        if (keyCode == 79 || keyCode == 85) {
            if (qt2.k0(to1Var, this.F0)) {
                qt2.Q(to1Var);
                return true;
            }
            qt2.P(to1Var);
            return true;
        }
        if (keyCode == 87) {
            if (!to1Var.W(9)) {
                return true;
            }
            to1Var.F0();
            return true;
        }
        if (keyCode == 88) {
            if (!to1Var.W(7)) {
                return true;
            }
            to1Var.N0();
            return true;
        }
        if (keyCode == 126) {
            qt2.Q(to1Var);
            return true;
        }
        if (keyCode != 127) {
            return true;
        }
        qt2.P(to1Var);
        return true;
    }

    public final void d(sx1 sx1Var) {
        this.w.setAdapter(sx1Var);
        u();
        this.R0 = false;
        PopupWindow popupWindow = this.B;
        popupWindow.dismiss();
        this.R0 = true;
        int width = getWidth() - popupWindow.getWidth();
        int i = this.C;
        popupWindow.showAsDropDown(findViewById(R.id.exo_bottom_bar), width - i, (-i) / 2);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final boolean dispatchKeyEvent(KeyEvent keyEvent) {
        return c(keyEvent) || super.dispatchKeyEvent(keyEvent);
    }

    public final dz1 e(pq2 pq2Var, int i) {
        ij0.d(4, "initialCapacity");
        Object[] objArrCopyOf = new Object[4];
        pw0 pw0Var = pq2Var.a;
        int i2 = 0;
        for (int i3 = 0; i3 < pw0Var.size(); i3++) {
            oq2 oq2Var = (oq2) pw0Var.get(i3);
            if (oq2Var.b.c == i) {
                for (int i4 = 0; i4 < oq2Var.a; i4++) {
                    if (oq2Var.c(i4, false)) {
                        zl0 zl0VarA = oq2Var.a(i4);
                        int i5 = zl0VarA.e;
                        tr1 tr1Var = new tr1(pq2Var, i3, i4, this.trackNameProvider.a(zl0VarA));
                        int i6 = i2 + 1;
                        int iB = iw0.b(objArrCopyOf.length, i6);
                        if (iB > objArrCopyOf.length) {
                            objArrCopyOf = Arrays.copyOf(objArrCopyOf, iB);
                        }
                        objArrCopyOf[i2] = tr1Var;
                        i2 = i6;
                    }
                }
            }
        }
        return pw0.j(i2, objArrCopyOf);
    }

    public final void f() {
        bs1 bs1Var = this.l;
        byte b = bs1Var.A;
        if (b == 3 || b == 2) {
            return;
        }
        bs1Var.f();
        if (!bs1Var.D) {
            bs1Var.i(2);
        } else if (bs1Var.A == 1) {
            bs1Var.n.start();
        } else {
            bs1Var.o.start();
        }
    }

    public final boolean g(to1 to1Var) {
        Class cls;
        return (to1Var == null || (cls = this.s) == null || !cls.isAssignableFrom(to1Var.getClass())) ? false : true;
    }

    public to1 getPlayer() {
        return this.A0;
    }

    public int getRepeatToggleModes() {
        return this.L0;
    }

    public boolean getShowShuffleButton() {
        return this.l.b(this.L);
    }

    public boolean getShowSubtitleButton() {
        return this.l.b(this.N);
    }

    public int getShowTimeoutMs() {
        return this.I0;
    }

    public boolean getShowVrButton() {
        return this.l.b(this.M);
    }

    public final boolean h(to1 to1Var) {
        Class cls;
        return (to1Var == null || (cls = this.p) == null || !cls.isAssignableFrom(to1Var.getClass())) ? false : true;
    }

    @Override // android.view.View
    public final boolean hasOverlappingRendering() {
        return false;
    }

    public final boolean i() {
        bs1 bs1Var = this.l;
        return bs1Var.A == 0 && bs1Var.a.k();
    }

    public final boolean j(to1 to1Var) {
        try {
            if (h(to1Var)) {
                Method method = this.r;
                method.getClass();
                Object objInvoke = method.invoke(to1Var, null);
                objInvoke.getClass();
                if (((Boolean) objInvoke).booleanValue()) {
                    return true;
                }
            }
            if (g(to1Var)) {
                Method method2 = this.u;
                method2.getClass();
                Object objInvoke2 = method2.invoke(to1Var, null);
                objInvoke2.getClass();
                if (((Boolean) objInvoke2).booleanValue()) {
                    return true;
                }
            }
            return false;
        } catch (IllegalAccessException e) {
            e = e;
            q90.m(e);
            return false;
        } catch (InvocationTargetException e2) {
            e = e2;
            q90.m(e);
            return false;
        }
    }

    public final boolean k() {
        return getVisibility() == 0;
    }

    public final void l(to1 to1Var, long j) {
        if (this.G0) {
            if (to1Var.W(17) && to1Var.W(10)) {
                zn2 zn2VarR0 = to1Var.r0();
                int iO = zn2VarR0.o();
                int i = 0;
                while (true) {
                    long jP0 = qt2.p0(zn2VarR0.m(i, this.c0, 0L).m);
                    if (j < jP0) {
                        break;
                    }
                    if (i == iO - 1) {
                        j = jP0;
                        break;
                    } else {
                        j -= jP0;
                        i++;
                    }
                }
                to1Var.K0(j, i);
            }
        } else if (to1Var.W(5)) {
            to1Var.B0(j);
        }
        s();
    }

    public final void m() {
        q();
        p();
        t();
        v();
        x();
        r();
        w();
    }

    public final void n(View view, boolean z) {
        if (view == null) {
            return;
        }
        view.setEnabled(z);
        view.setAlpha(z ? this.o0 : this.p0);
    }

    public final void o(boolean z) {
        if (this.C0 == z) {
            return;
        }
        this.C0 = z;
        String str = this.z0;
        Drawable drawable = this.x0;
        String str2 = this.y0;
        Drawable drawable2 = this.w0;
        ImageView imageView = this.O;
        if (imageView != null) {
            if (z) {
                imageView.setImageDrawable(drawable2);
                imageView.setContentDescription(str2);
            } else {
                imageView.setImageDrawable(drawable);
                imageView.setContentDescription(str);
            }
        }
        ImageView imageView2 = this.P;
        if (imageView2 == null) {
            return;
        }
        if (z) {
            imageView2.setImageDrawable(drawable2);
            imageView2.setContentDescription(str2);
        } else {
            imageView2.setImageDrawable(drawable);
            imageView2.setContentDescription(str);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        bs1 bs1Var = this.l;
        bs1Var.a.addOnLayoutChangeListener(bs1Var.y);
        this.D0 = true;
        if (i()) {
            bs1Var.g();
        }
        m();
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        bs1 bs1Var = this.l;
        bs1Var.a.removeOnLayoutChangeListener(bs1Var.y);
        this.D0 = false;
        removeCallbacks(this.d0);
        bs1Var.f();
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        View view = this.l.b;
        if (view != null) {
            view.layout(0, 0, i3 - i, i4 - i2);
        }
    }

    public final void p() {
        boolean zW;
        boolean zW2;
        boolean zW3;
        boolean zW4;
        boolean zW5;
        if (k() && this.D0) {
            to1 to1Var = this.A0;
            if (to1Var != null) {
                zW2 = (this.E0 && b(to1Var, this.c0)) ? to1Var.W(10) : to1Var.W(5);
                zW3 = to1Var.W(7);
                zW4 = to1Var.W(11);
                zW5 = to1Var.W(12);
                zW = to1Var.W(9);
            } else {
                zW = false;
                zW2 = false;
                zW3 = false;
                zW4 = false;
                zW5 = false;
            }
            Resources resources = this.m;
            View view = this.H;
            if (zW4) {
                to1 to1Var2 = this.A0;
                int iP0 = (int) ((to1Var2 != null ? to1Var2.P0() : 5000L) / 1000);
                TextView textView = this.J;
                if (textView != null) {
                    textView.setText(String.valueOf(iP0));
                }
                if (view != null) {
                    view.setContentDescription(resources.getQuantityString(R.plurals.exo_controls_rewind_by_amount_description, iP0, Integer.valueOf(iP0)));
                }
            }
            View view2 = this.G;
            if (zW5) {
                to1 to1Var3 = this.A0;
                int iN = (int) ((to1Var3 != null ? to1Var3.n() : 15000L) / 1000);
                TextView textView2 = this.I;
                if (textView2 != null) {
                    textView2.setText(String.valueOf(iN));
                }
                if (view2 != null) {
                    view2.setContentDescription(resources.getQuantityString(R.plurals.exo_controls_fastforward_by_amount_description, iN, Integer.valueOf(iN)));
                }
            }
            n(this.D, zW3);
            n(view, zW4);
            n(view2, zW5);
            n(this.E, zW);
            rn2 rn2Var = this.V;
            if (rn2Var != null) {
                rn2Var.setEnabled(zW2);
            }
        }
    }

    public final void q() {
        ImageView imageView;
        if (k() && this.D0 && (imageView = this.F) != null) {
            boolean zK0 = qt2.k0(this.A0, this.F0);
            Drawable drawable = zK0 ? this.e0 : this.f0;
            int i = zK0 ? R.string.exo_controls_play_description : R.string.exo_controls_pause_description;
            imageView.setImageDrawable(drawable);
            imageView.setContentDescription(this.m.getString(i));
            to1 to1Var = this.A0;
            boolean z = false;
            if (to1Var != null) {
                int iC = to1Var.C();
                boolean z2 = (to1Var.W(16) && to1Var.z() == null) ? false : true;
                boolean zW = to1Var.W(1);
                boolean z3 = iC == 1 && to1Var.W(2);
                boolean z4 = iC == 4 && to1Var.W(4);
                if (z2 && (zW || z3 || z4)) {
                    z = true;
                }
            }
            n(imageView, z);
        }
    }

    public final void r() {
        or1 or1Var;
        to1 to1Var = this.A0;
        if (to1Var == null) {
            return;
        }
        float f = to1Var.getPlaybackParameters().a;
        float f2 = Float.MAX_VALUE;
        int i = 0;
        int i2 = 0;
        while (true) {
            or1Var = this.y;
            float[] fArr = or1Var.d;
            if (i >= fArr.length) {
                break;
            }
            float fAbs = Math.abs(f - fArr[i]);
            if (fAbs < f2) {
                i2 = i;
                f2 = fAbs;
            }
            i++;
        }
        or1Var.e = i2;
        String str = or1Var.c[i2];
        rr1 rr1Var = this.x;
        rr1Var.d[0] = str;
        n(this.Q, rr1Var.h(1) || rr1Var.h(0));
    }

    public final void s() {
        long jP;
        long jC0;
        String strL;
        if (k() && this.D0) {
            to1 to1Var = this.A0;
            if (to1Var == null || !to1Var.W(16)) {
                jP = 0;
                jC0 = 0;
            } else {
                jP = to1Var.p() + this.Q0;
                jC0 = to1Var.C0() + this.Q0;
            }
            TextView textView = this.U;
            if (textView != null && !this.H0) {
                textView.setText(qt2.L(this.W, this.a0, jP));
            }
            rn2 rn2Var = this.V;
            if (rn2Var != null) {
                rn2Var.setPosition(jP);
                if (j(to1Var)) {
                    jC0 = jP;
                }
                rn2Var.setBufferedPosition(jC0);
            }
            pr1 pr1Var = this.B0;
            if (pr1Var != null) {
                aq1 aq1Var = (aq1) pr1Var;
                PlayerActivity playerActivity = aq1Var.a;
                TextView textView2 = aq1Var.b;
                StringBuilder sb = aq1Var.c;
                Formatter formatter = aq1Var.d;
                TextView textView3 = aq1Var.e;
                LoudnessEnhancer loudnessEnhancer = PlayerActivity.l6;
                if (!playerActivity.I2) {
                    if (PlayerActivity.f1()) {
                        textView2.setText(qt2.L(sb, formatter, Math.max(0L, SystemClock.elapsedRealtime() - playerActivity.t1)));
                    } else {
                        vg0 vg0Var = PlayerActivity.n6;
                        long jK = vg0Var == null ? -9223372036854775807L : vg0Var.K();
                        if (jK != -9223372036854775807L) {
                            hu1 hu1Var = playerActivity.H;
                            if (hu1Var == null || !hu1Var.v0) {
                                strL = qt2.L(sb, formatter, jK);
                            } else {
                                strL = "-" + qt2.L(sb, formatter, Math.max(0L, jK - jP));
                            }
                            textView3.setText(strL);
                        }
                    }
                }
            }
            lj1 lj1Var = this.d0;
            removeCallbacks(lj1Var);
            int iC = to1Var == null ? 1 : to1Var.C();
            if (to1Var != null && to1Var.J()) {
                long jMin = Math.min(rn2Var != null ? rn2Var.getPreferredUpdateDelay() : 1000L, 1000 - (jP % 1000));
                float f = to1Var.getPlaybackParameters().a;
                postDelayed(lj1Var, qt2.k(f > 0.0f ? (long) (jMin / f) : 1000L, this.K0, 1000L));
            } else {
                if (iC == 4 || iC == 1) {
                    return;
                }
                postDelayed(lj1Var, 1000L);
            }
        }
    }

    public void setAnimationEnabled(boolean z) {
        this.l.D = z;
    }

    public void setMediaRouteButtonViewProvider(dx2 dx2Var) {
        View viewFindViewById = findViewById(R.id.exo_media_route_button_placeholder);
        if (viewFindViewById == null) {
            bl.h("The media route button placeholder is missing.");
            return;
        }
        if (dx2Var == null) {
            viewFindViewById.setVisibility(8);
            return;
        }
        ViewGroup viewGroup = (ViewGroup) viewFindViewById.getParent();
        if (viewGroup == null) {
            bl.h("The media route button placeholder has no parent view.");
            return;
        }
        z11 z11VarA = dx2Var.a();
        le leVar = new le(this, viewFindViewById, viewGroup);
        Handler handler = this.n;
        Objects.requireNonNull(handler);
        z11VarA.a(new cp0(z11VarA, leVar, (byte) 0), new de(handler, (byte) 1));
    }

    @Deprecated
    public void setOnFullScreenModeChangedListener(nr1 nr1Var) {
        boolean z = nr1Var != null;
        ImageView imageView = this.O;
        if (imageView != null) {
            if (z) {
                imageView.setVisibility(0);
            } else {
                imageView.setVisibility(8);
            }
        }
        boolean z2 = nr1Var != null;
        ImageView imageView2 = this.P;
        if (imageView2 == null) {
            return;
        }
        if (z2) {
            imageView2.setVisibility(0);
        } else {
            imageView2.setVisibility(8);
        }
    }

    public void setPlayer(to1 to1Var) {
        ha1.s(Looper.myLooper() == Looper.getMainLooper());
        ha1.h(to1Var == null || to1Var.u0() == Looper.getMainLooper());
        to1 to1Var2 = this.A0;
        if (to1Var2 == to1Var) {
            return;
        }
        mr1 mr1Var = this.o;
        if (to1Var2 != null) {
            to1Var2.e0(mr1Var);
        }
        this.A0 = to1Var;
        if (to1Var != null) {
            to1Var.H(mr1Var);
        }
        m();
    }

    public void setProgressUpdateListener(pr1 pr1Var) {
        this.B0 = pr1Var;
    }

    public void setRepeatToggleModes(int i) {
        this.L0 = i;
        to1 to1Var = this.A0;
        if (to1Var != null && to1Var.W(15)) {
            int iP0 = this.A0.p0();
            if (i == 0 && iP0 != 0) {
                this.A0.a0(0);
            } else if (i == 1 && iP0 == 2) {
                this.A0.a0(1);
            } else if (i == 2 && iP0 == 1) {
                this.A0.a0(2);
            }
        }
        this.l.h(this.K, i != 0);
        t();
    }

    public void setShowFastForwardButton(boolean z) {
        this.l.h(this.G, z);
        p();
    }

    @Deprecated
    public void setShowMultiWindowTimeBar(boolean z) {
        this.E0 = z;
        w();
    }

    public void setShowNextButton(boolean z) {
        this.l.h(this.E, z);
        p();
    }

    public void setShowPlayButtonIfPlaybackIsSuppressed(boolean z) {
        this.F0 = z;
        q();
    }

    public void setShowPreviousButton(boolean z) {
        this.l.h(this.D, z);
        p();
    }

    public void setShowRewindButton(boolean z) {
        this.l.h(this.H, z);
        p();
    }

    public void setShowShuffleButton(boolean z) {
        this.l.h(this.L, z);
        v();
    }

    public void setShowSubtitleButton(boolean z) {
        this.l.h(this.N, z);
    }

    public void setShowTimeoutMs(int i) {
        this.I0 = i;
        if (i()) {
            this.l.g();
        }
    }

    public void setShowVrButton(boolean z) {
        this.l.h(this.M, z);
    }

    public void setTimeBarMinUpdateInterval(int i) {
        this.K0 = qt2.j(i, 16, 1000);
    }

    public void setTimeBarScrubbingEnabled(boolean z) {
        this.J0 = z;
    }

    public void setVrButtonListener(View.OnClickListener onClickListener) {
        ImageView imageView = this.M;
        if (imageView != null) {
            imageView.setOnClickListener(onClickListener);
            n(imageView, onClickListener != null);
        }
    }

    public final void t() {
        ImageView imageView;
        if (k() && this.D0 && (imageView = this.K) != null) {
            if (this.L0 == 0) {
                n(imageView, false);
                return;
            }
            to1 to1Var = this.A0;
            String str = this.j0;
            Drawable drawable = this.g0;
            if (to1Var == null || !to1Var.W(15)) {
                n(imageView, false);
                imageView.setImageDrawable(drawable);
                imageView.setContentDescription(str);
                return;
            }
            n(imageView, true);
            int iP0 = to1Var.p0();
            if (iP0 == 0) {
                imageView.setImageDrawable(drawable);
                imageView.setContentDescription(str);
            } else if (iP0 == 1) {
                imageView.setImageDrawable(this.h0);
                imageView.setContentDescription(this.k0);
            } else {
                if (iP0 != 2) {
                    return;
                }
                imageView.setImageDrawable(this.i0);
                imageView.setContentDescription(this.l0);
            }
        }
    }

    public final void u() {
        RecyclerView recyclerView = this.w;
        int bottom = 0;
        recyclerView.measure(0, 0);
        int width = getWidth();
        int i = this.C;
        int iMin = Math.min(recyclerView.getMeasuredWidth(), width - (i * 2));
        PopupWindow popupWindow = this.B;
        popupWindow.setWidth(iMin);
        View viewFindViewById = findViewById(R.id.exo_controls_background);
        if (viewFindViewById instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) viewFindViewById;
            if (viewGroup.getChildCount() > 0) {
                bottom = viewGroup.getChildAt(0).getBottom();
            }
        }
        int[] iArr = new int[2];
        findViewById(R.id.exo_bottom_bar).getLocationOnScreen(iArr);
        popupWindow.setHeight(Math.min((iArr[1] - bottom) - i, recyclerView.getMeasuredHeight()));
    }

    public final void v() {
        ImageView imageView;
        if (k() && this.D0 && (imageView = this.L) != null) {
            to1 to1Var = this.A0;
            if (!this.l.b(imageView)) {
                n(imageView, false);
                return;
            }
            String str = this.r0;
            Drawable drawable = this.n0;
            if (to1Var == null || !to1Var.W(14)) {
                n(imageView, false);
                imageView.setImageDrawable(drawable);
                imageView.setContentDescription(str);
                return;
            }
            n(imageView, true);
            if (to1Var.z0()) {
                drawable = this.m0;
            }
            imageView.setImageDrawable(drawable);
            if (to1Var.z0()) {
                str = this.q0;
            }
            imageView.setContentDescription(str);
        }
    }

    /* JADX WARN: Code duplicated, block: B:71:0x0139  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v0 */
    /* JADX WARN: Type inference failed for: r10v1, types: [int] */
    /* JADX WARN: Type inference failed for: r10v3 */
    /* JADX WARN: Type inference failed for: r21v0 */
    /* JADX WARN: Type inference failed for: r21v1 */
    /* JADX WARN: Type inference failed for: r21v2 */
    /* JADX WARN: Type inference failed for: r21v3 */
    /* JADX WARN: Type inference failed for: r21v4 */
    /* JADX WARN: Type inference failed for: r21v5 */
    /* JADX WARN: Type inference failed for: r21v6 */
    /* JADX WARN: Type inference failed for: r21v7 */
    /* JADX WARN: Type inference failed for: r21v8 */
    /* JADX WARN: Type inference failed for: r21v9 */
    /* JADX WARN: Type inference failed for: r2v11, types: [zn2] */
    /* JADX WARN: Type inference failed for: r2v13, types: [zn2] */
    /* JADX WARN: Type inference failed for: r2v15 */
    /* JADX WARN: Type inference failed for: r2v16 */
    /* JADX WARN: Type inference failed for: r2v18 */
    /* JADX WARN: Type inference failed for: r2v21 */
    /* JADX WARN: Type inference failed for: r2v22 */
    /* JADX WARN: Type inference failed for: r2v25 */
    /* JADX WARN: Type inference failed for: r2v34 */
    /* JADX WARN: Type inference failed for: r2v35 */
    /* JADX WARN: Type inference failed for: r2v36 */
    /* JADX WARN: Type inference failed for: r2v37 */
    /* JADX WARN: Type inference failed for: r4v12, types: [r4] */
    /* JADX WARN: Type inference failed for: r7v3, types: [java.lang.Object, wn2] */
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
    public final void w() {
        boolean z;
        long jY;
        int i;
        ?? r2;
        ?? r21;
        boolean z2;
        ?? r3;
        boolean[] zArr;
        boolean z3;
        ?? r22;
        int length;
        to1 to1Var = this.A0;
        if (to1Var == null) {
            return;
        }
        boolean z4 = this.E0;
        yn2 yn2Var = this.c0;
        boolean z5 = false;
        boolean z6 = true;
        this.G0 = z4 && b(to1Var, yn2Var);
        long j = 0;
        this.Q0 = 0L;
        zn2 zn2VarR0 = to1Var.W(17) ? to1Var.r0() : zn2.a;
        long j2 = -9223372036854775807L;
        if (zn2VarR0.p()) {
            z = true;
            if (to1Var.W(16)) {
                long jK = to1Var.K();
                if (jK != -9223372036854775807L) {
                    jY = qt2.Y(jK);
                } else {
                    jY = 0;
                }
            } else {
                jY = 0;
            }
            i = 0;
        } else {
            int iV = to1Var.V();
            boolean z7 = this.G0;
            int i2 = z7 ? 0 : iV;
            int iO = z7 ? zn2VarR0.o() - 1 : iV;
            i = 0;
            long j3 = 0;
            ?? r4 = zn2VarR0;
            while (i2 <= iO) {
                long j4 = j;
                if (i2 == iV) {
                    this.Q0 = qt2.p0(j3);
                }
                r4.n(i2, yn2Var);
                if (yn2Var.m == j2) {
                    ha1.s(this.G0 ^ z6);
                    break;
                }
                int i3 = yn2Var.n;
                ?? r5 = r4;
                while (i3 <= yn2Var.o) {
                    ?? r7 = this.b0;
                    r5.f(i3, r7, z5);
                    r7.getClass();
                    long j5 = j2;
                    int i4 = r4.c.a;
                    for (?? r10 = z5; r10 < i4; r10++) {
                        r7.d(r10);
                        long j6 = r7.e;
                        if (j6 >= j4) {
                            long[] jArr = this.M0;
                            if (i == jArr.length) {
                                if (jArr.length == 0) {
                                    r2 = r5;
                                    length = 1;
                                } else {
                                    r2 = r5;
                                    length = jArr.length * 2;
                                }
                                this.M0 = Arrays.copyOf(jArr, length);
                                this.N0 = Arrays.copyOf(this.N0, length);
                            }
                            r2 = r5;
                            this.M0[i] = qt2.p0(j6 + j3);
                            boolean[] zArr2 = this.N0;
                            p4 p4VarA = r4.c.a(r10);
                            int i5 = p4VarA.a;
                            if (i5 == -1) {
                                zArr = zArr2;
                                r22 = r2;
                                z2 = true;
                            } else {
                                int i6 = 0;
                                while (true) {
                                    if (i6 >= i5) {
                                        r3 = r2;
                                        zArr = zArr2;
                                        r21 = r3;
                                        z2 = true;
                                        z3 = false;
                                        break;
                                    }
                                    zArr = zArr2;
                                    int i7 = p4VarA.d[i6];
                                    r22 = r3;
                                    z2 = true;
                                    if (i7 == 0) {
                                        r3 = r2;
                                    } else if (i7 != 1) {
                                        i6++;
                                        zArr2 = zArr;
                                        r3 = r22;
                                    }
                                }
                                zArr[i] = z3 ^ z2;
                                i++;
                            }
                            z3 = z2;
                            r21 = r22;
                            zArr[i] = z3 ^ z2;
                            i++;
                        } else {
                            r2 = r5;
                            r21 = r2;
                            z2 = true;
                        }
                        z6 = z2;
                        iV = iV;
                        r2 = r21;
                        j4 = 0;
                    }
                    r2 = r5;
                    i3++;
                    j2 = j5;
                    r5 = r2;
                    z5 = false;
                    j4 = 0;
                }
                j3 += yn2Var.m;
                i2++;
                z6 = z6;
                r4 = r5;
                z5 = false;
                j = 0;
            }
            z = z6;
            jY = j3;
        }
        long jP0 = qt2.p0(jY);
        TextView textView = this.T;
        if (textView != null) {
            textView.setText(qt2.L(this.W, this.a0, jP0));
        }
        rn2 rn2Var = this.V;
        if (rn2Var != null) {
            rn2Var.setDuration(jP0);
            long[] jArr2 = this.O0;
            int length2 = jArr2.length;
            int i8 = i + length2;
            long[] jArr3 = this.M0;
            if (i8 > jArr3.length) {
                this.M0 = Arrays.copyOf(jArr3, i8);
                this.N0 = Arrays.copyOf(this.N0, i8);
            }
            System.arraycopy(jArr2, 0, this.M0, i, length2);
            System.arraycopy(this.P0, 0, this.N0, i, length2);
            long[] jArr4 = this.M0;
            boolean[] zArr3 = this.N0;
            l50 l50Var = (l50) rn2Var;
            if (i8 != 0 && (jArr4 == null || zArr3 == null)) {
                z = false;
            }
            ha1.h(z);
            l50Var.U = i8;
            l50Var.V = jArr4;
            l50Var.W = zArr3;
            l50Var.f();
        }
        s();
    }

    public final void x() {
        lr1 lr1Var = this.z;
        lr1Var.getClass();
        List list = Collections.EMPTY_LIST;
        lr1Var.c = list;
        lr1 lr1Var2 = this.A;
        lr1Var2.getClass();
        lr1Var2.c = list;
        to1 to1Var = this.A0;
        ImageView imageView = this.N;
        if (to1Var != null && to1Var.W(30) && this.A0.W(29)) {
            pq2 pq2VarE = this.A0.E();
            dz1 dz1VarE = e(pq2VarE, 1);
            lr1Var2.c = dz1VarE;
            wr1 wr1Var = lr1Var2.f;
            to1 to1Var2 = wr1Var.A0;
            rr1 rr1Var = wr1Var.x;
            to1Var2.getClass();
            lq2 lq2VarA0 = to1Var2.A0();
            if (dz1VarE.isEmpty()) {
                rr1Var.d[1] = wr1Var.getResources().getString(R.string.exo_track_selection_none);
            } else if (lr1Var2.h(lq2VarA0)) {
                for (int i = 0; i < dz1VarE.o; i++) {
                    tr1 tr1Var = (tr1) dz1VarE.get(i);
                    if (tr1Var.a.e[tr1Var.b]) {
                        rr1Var.d[1] = tr1Var.c;
                        break;
                    }
                }
            } else {
                rr1Var.d[1] = wr1Var.getResources().getString(R.string.exo_track_selection_auto);
            }
            if (this.l.b(imageView)) {
                lr1Var.i(e(pq2VarE, 3));
            } else {
                nw0 nw0Var = pw0.m;
                lr1Var.i(dz1.p);
            }
        }
        n(imageView, lr1Var.a() > 0);
        rr1 rr1Var2 = this.x;
        n(this.Q, rr1Var2.h(1) || rr1Var2.h(0));
    }
}
