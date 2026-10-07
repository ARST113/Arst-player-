package defpackage;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.res.Resources;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import androidx.media3.decoder.DecoderInputBuffer;
import com.brouken.player.PlayerActivity;
import com.justplus.player.R;
import java.util.ArrayList;

/* JADX INFO: compiled from: r8-map-id-0cee80150bab987b3edc91bcfc1e04786a1418881e5c92a3b999d44d61001263 */
/* JADX INFO: loaded from: classes.dex */
public final class bs1 {
    public boolean B;
    public boolean C;
    public final float E;
    public final wr1 a;
    public final View b;
    public final ViewGroup c;
    public final ViewGroup d;
    public final ViewGroup e;
    public final ViewGroup f;
    public final ViewGroup g;
    public final ViewGroup h;
    public final ViewGroup i;
    public final ViewGroup j;
    public final View k;
    public final View l;
    public final AnimatorSet m;
    public final AnimatorSet n;
    public final AnimatorSet o;
    public final AnimatorSet p;
    public final AnimatorSet q;
    public final ValueAnimator r;
    public final ValueAnimator s;
    public final xr1 t;
    public final xr1 u;
    public final xr1 v;
    public final tk y;
    public final xr1 w = new xr1(this, 5);
    public final xr1 x = new xr1(this, 6);
    public boolean D = true;
    public byte A = 0;
    public final ArrayList z = new ArrayList();

    public bs1(wr1 wr1Var) {
        this.a = wr1Var;
        final byte b = 0;
        this.t = new xr1(this, b);
        final byte b2 = 3;
        this.u = new xr1(this, b2);
        byte b3 = 4;
        this.v = new xr1(this, b3);
        this.y = new tk(this, b3);
        final byte b4 = 1;
        this.c = (ViewGroup) wr1Var.findViewById(R.id.exo_top_controls);
        this.b = wr1Var.findViewById(R.id.exo_controls_background);
        this.d = (ViewGroup) wr1Var.findViewById(R.id.exo_center_controls);
        this.f = (ViewGroup) wr1Var.findViewById(R.id.exo_minimal_controls);
        ViewGroup viewGroup = (ViewGroup) wr1Var.findViewById(R.id.exo_bottom_bar);
        this.e = viewGroup;
        this.j = (ViewGroup) wr1Var.findViewById(R.id.exo_time);
        View viewFindViewById = wr1Var.findViewById(R.id.exo_progress);
        this.k = viewFindViewById;
        this.g = (ViewGroup) wr1Var.findViewById(R.id.exo_basic_controls);
        this.h = (ViewGroup) wr1Var.findViewById(R.id.exo_extra_controls);
        this.i = (ViewGroup) wr1Var.findViewById(R.id.exo_extra_controls_scroll_view);
        View viewFindViewById2 = wr1Var.findViewById(R.id.exo_overflow_show);
        this.l = viewFindViewById2;
        View viewFindViewById3 = wr1Var.findViewById(R.id.exo_overflow_hide);
        if (viewFindViewById2 != null && viewFindViewById3 != null) {
            byte b5 = 14;
            viewFindViewById2.setOnClickListener(new zs(this, b5));
            viewFindViewById3.setOnClickListener(new zs(this, b5));
        }
        final byte b6 = 2;
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(1.0f, 0.0f);
        valueAnimatorOfFloat.setInterpolator(new LinearInterpolator());
        valueAnimatorOfFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener(this) { // from class: yr1
            public final /* synthetic */ bs1 b;

            {
                this.b = this;
            }

            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                byte b7 = b2;
                bs1 bs1Var = this.b;
                switch (b7) {
                    case DecoderInputBuffer.BUFFER_REPLACEMENT_MODE_DISABLED /* 0 */:
                        float fFloatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                        View view = bs1Var.b;
                        if (view != null) {
                            view.setAlpha(fFloatValue);
                        }
                        ViewGroup viewGroup2 = bs1Var.c;
                        if (viewGroup2 != null) {
                            viewGroup2.setAlpha(fFloatValue);
                        }
                        ViewGroup viewGroup3 = bs1Var.d;
                        if (viewGroup3 != null) {
                            viewGroup3.setAlpha(fFloatValue);
                        }
                        ViewGroup viewGroup4 = bs1Var.f;
                        if (viewGroup4 != null) {
                            viewGroup4.setAlpha(fFloatValue);
                        }
                        break;
                    case 1:
                        bs1Var.a(((Float) valueAnimator.getAnimatedValue()).floatValue());
                        break;
                    case 2:
                        bs1Var.a(((Float) valueAnimator.getAnimatedValue()).floatValue());
                        break;
                    default:
                        float fFloatValue2 = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                        View view2 = bs1Var.b;
                        if (view2 != null) {
                            view2.setAlpha(fFloatValue2);
                        }
                        ViewGroup viewGroup5 = bs1Var.c;
                        if (viewGroup5 != null) {
                            viewGroup5.setAlpha(fFloatValue2);
                        }
                        ViewGroup viewGroup6 = bs1Var.d;
                        if (viewGroup6 != null) {
                            viewGroup6.setAlpha(fFloatValue2);
                        }
                        ViewGroup viewGroup7 = bs1Var.f;
                        if (viewGroup7 != null) {
                            viewGroup7.setAlpha(fFloatValue2);
                        }
                        break;
                }
            }
        });
        valueAnimatorOfFloat.addListener(new zr1(this, (byte) 0));
        ValueAnimator valueAnimatorOfFloat2 = ValueAnimator.ofFloat(0.0f, 1.0f);
        valueAnimatorOfFloat2.setInterpolator(new LinearInterpolator());
        valueAnimatorOfFloat2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener(this) { // from class: yr1
            public final /* synthetic */ bs1 b;

            {
                this.b = this;
            }

            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                byte b7 = b;
                bs1 bs1Var = this.b;
                switch (b7) {
                    case DecoderInputBuffer.BUFFER_REPLACEMENT_MODE_DISABLED /* 0 */:
                        float fFloatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                        View view = bs1Var.b;
                        if (view != null) {
                            view.setAlpha(fFloatValue);
                        }
                        ViewGroup viewGroup2 = bs1Var.c;
                        if (viewGroup2 != null) {
                            viewGroup2.setAlpha(fFloatValue);
                        }
                        ViewGroup viewGroup3 = bs1Var.d;
                        if (viewGroup3 != null) {
                            viewGroup3.setAlpha(fFloatValue);
                        }
                        ViewGroup viewGroup4 = bs1Var.f;
                        if (viewGroup4 != null) {
                            viewGroup4.setAlpha(fFloatValue);
                        }
                        break;
                    case 1:
                        bs1Var.a(((Float) valueAnimator.getAnimatedValue()).floatValue());
                        break;
                    case 2:
                        bs1Var.a(((Float) valueAnimator.getAnimatedValue()).floatValue());
                        break;
                    default:
                        float fFloatValue2 = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                        View view2 = bs1Var.b;
                        if (view2 != null) {
                            view2.setAlpha(fFloatValue2);
                        }
                        ViewGroup viewGroup5 = bs1Var.c;
                        if (viewGroup5 != null) {
                            viewGroup5.setAlpha(fFloatValue2);
                        }
                        ViewGroup viewGroup6 = bs1Var.d;
                        if (viewGroup6 != null) {
                            viewGroup6.setAlpha(fFloatValue2);
                        }
                        ViewGroup viewGroup7 = bs1Var.f;
                        if (viewGroup7 != null) {
                            viewGroup7.setAlpha(fFloatValue2);
                        }
                        break;
                }
            }
        });
        valueAnimatorOfFloat2.addListener(new zr1(this, (byte) 1));
        Resources resources = wr1Var.getResources();
        float dimension = resources.getDimension(R.dimen.exo_styled_bottom_bar_height) - resources.getDimension(R.dimen.exo_styled_progress_bar_height);
        this.E = dimension;
        float dimension2 = resources.getDimension(R.dimen.exo_styled_bottom_bar_height);
        AnimatorSet animatorSet = new AnimatorSet();
        this.m = animatorSet;
        animatorSet.setDuration(250L);
        animatorSet.addListener(new as1(this, wr1Var, b));
        animatorSet.play(valueAnimatorOfFloat).with(d(viewFindViewById, 0.0f, dimension)).with(d(viewGroup, 0.0f, dimension));
        AnimatorSet animatorSet2 = new AnimatorSet();
        this.n = animatorSet2;
        animatorSet2.setDuration(250L);
        animatorSet2.addListener(new as1(this, wr1Var, b4));
        animatorSet2.play(d(viewFindViewById, dimension, dimension2)).with(d(viewGroup, dimension, dimension2));
        AnimatorSet animatorSet3 = new AnimatorSet();
        this.o = animatorSet3;
        animatorSet3.setDuration(250L);
        animatorSet3.addListener(new as1(this, wr1Var, b6));
        animatorSet3.play(valueAnimatorOfFloat).with(d(viewFindViewById, 0.0f, dimension2)).with(d(viewGroup, 0.0f, dimension2));
        AnimatorSet animatorSet4 = new AnimatorSet();
        this.p = animatorSet4;
        animatorSet4.setDuration(250L);
        animatorSet4.addListener(new zr1(this, (byte) 2));
        animatorSet4.play(valueAnimatorOfFloat2).with(d(viewFindViewById, dimension, 0.0f)).with(d(viewGroup, dimension, 0.0f));
        AnimatorSet animatorSet5 = new AnimatorSet();
        this.q = animatorSet5;
        animatorSet5.setDuration(250L);
        animatorSet5.addListener(new zr1(this, (byte) 3));
        animatorSet5.play(valueAnimatorOfFloat2).with(d(viewFindViewById, dimension2, 0.0f)).with(d(viewGroup, dimension2, 0.0f));
        ValueAnimator valueAnimatorOfFloat3 = ValueAnimator.ofFloat(0.0f, 1.0f);
        this.r = valueAnimatorOfFloat3;
        valueAnimatorOfFloat3.setDuration(250L);
        valueAnimatorOfFloat3.addUpdateListener(new ValueAnimator.AnimatorUpdateListener(this) { // from class: yr1
            public final /* synthetic */ bs1 b;

            {
                this.b = this;
            }

            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                byte b7 = b4;
                bs1 bs1Var = this.b;
                switch (b7) {
                    case DecoderInputBuffer.BUFFER_REPLACEMENT_MODE_DISABLED /* 0 */:
                        float fFloatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                        View view = bs1Var.b;
                        if (view != null) {
                            view.setAlpha(fFloatValue);
                        }
                        ViewGroup viewGroup2 = bs1Var.c;
                        if (viewGroup2 != null) {
                            viewGroup2.setAlpha(fFloatValue);
                        }
                        ViewGroup viewGroup3 = bs1Var.d;
                        if (viewGroup3 != null) {
                            viewGroup3.setAlpha(fFloatValue);
                        }
                        ViewGroup viewGroup4 = bs1Var.f;
                        if (viewGroup4 != null) {
                            viewGroup4.setAlpha(fFloatValue);
                        }
                        break;
                    case 1:
                        bs1Var.a(((Float) valueAnimator.getAnimatedValue()).floatValue());
                        break;
                    case 2:
                        bs1Var.a(((Float) valueAnimator.getAnimatedValue()).floatValue());
                        break;
                    default:
                        float fFloatValue2 = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                        View view2 = bs1Var.b;
                        if (view2 != null) {
                            view2.setAlpha(fFloatValue2);
                        }
                        ViewGroup viewGroup5 = bs1Var.c;
                        if (viewGroup5 != null) {
                            viewGroup5.setAlpha(fFloatValue2);
                        }
                        ViewGroup viewGroup6 = bs1Var.d;
                        if (viewGroup6 != null) {
                            viewGroup6.setAlpha(fFloatValue2);
                        }
                        ViewGroup viewGroup7 = bs1Var.f;
                        if (viewGroup7 != null) {
                            viewGroup7.setAlpha(fFloatValue2);
                        }
                        break;
                }
            }
        });
        valueAnimatorOfFloat3.addListener(new zr1(this, (byte) 4));
        ValueAnimator valueAnimatorOfFloat4 = ValueAnimator.ofFloat(1.0f, 0.0f);
        this.s = valueAnimatorOfFloat4;
        valueAnimatorOfFloat4.setDuration(250L);
        valueAnimatorOfFloat4.addUpdateListener(new ValueAnimator.AnimatorUpdateListener(this) { // from class: yr1
            public final /* synthetic */ bs1 b;

            {
                this.b = this;
            }

            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                byte b7 = b6;
                bs1 bs1Var = this.b;
                switch (b7) {
                    case DecoderInputBuffer.BUFFER_REPLACEMENT_MODE_DISABLED /* 0 */:
                        float fFloatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                        View view = bs1Var.b;
                        if (view != null) {
                            view.setAlpha(fFloatValue);
                        }
                        ViewGroup viewGroup2 = bs1Var.c;
                        if (viewGroup2 != null) {
                            viewGroup2.setAlpha(fFloatValue);
                        }
                        ViewGroup viewGroup3 = bs1Var.d;
                        if (viewGroup3 != null) {
                            viewGroup3.setAlpha(fFloatValue);
                        }
                        ViewGroup viewGroup4 = bs1Var.f;
                        if (viewGroup4 != null) {
                            viewGroup4.setAlpha(fFloatValue);
                        }
                        break;
                    case 1:
                        bs1Var.a(((Float) valueAnimator.getAnimatedValue()).floatValue());
                        break;
                    case 2:
                        bs1Var.a(((Float) valueAnimator.getAnimatedValue()).floatValue());
                        break;
                    default:
                        float fFloatValue2 = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                        View view2 = bs1Var.b;
                        if (view2 != null) {
                            view2.setAlpha(fFloatValue2);
                        }
                        ViewGroup viewGroup5 = bs1Var.c;
                        if (viewGroup5 != null) {
                            viewGroup5.setAlpha(fFloatValue2);
                        }
                        ViewGroup viewGroup6 = bs1Var.d;
                        if (viewGroup6 != null) {
                            viewGroup6.setAlpha(fFloatValue2);
                        }
                        ViewGroup viewGroup7 = bs1Var.f;
                        if (viewGroup7 != null) {
                            viewGroup7.setAlpha(fFloatValue2);
                        }
                        break;
                }
            }
        });
        valueAnimatorOfFloat4.addListener(new zr1(this, (byte) 5));
    }

    public static int c(View view) {
        if (view == null) {
            return 0;
        }
        int width = view.getWidth();
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (!(layoutParams instanceof ViewGroup.MarginLayoutParams)) {
            return width;
        }
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
        return marginLayoutParams.leftMargin + marginLayoutParams.rightMargin + width;
    }

    public static ObjectAnimator d(View view, float f, float f2) {
        return ObjectAnimator.ofFloat(view, "translationY", f, f2);
    }

    public static boolean j(View view) {
        int id = view.getId();
        return id == R.id.exo_bottom_bar || id == R.id.exo_media_route_button_placeholder || id == R.id.exo_prev || id == R.id.exo_next || id == R.id.exo_rew || id == R.id.exo_rew_with_amount || id == R.id.exo_ffwd || id == R.id.exo_ffwd_with_amount;
    }

    public final void a(float f) {
        ViewGroup viewGroup = this.i;
        if (viewGroup != null) {
            viewGroup.setTranslationX((int) ((1.0f - f) * viewGroup.getWidth()));
        }
        ViewGroup viewGroup2 = this.j;
        if (viewGroup2 != null) {
            viewGroup2.setAlpha(1.0f - f);
        }
        ViewGroup viewGroup3 = this.g;
        if (viewGroup3 != null) {
            viewGroup3.setAlpha(1.0f - f);
        }
    }

    public final boolean b(View view) {
        return view != null && this.z.contains(view);
    }

    public final void e(Runnable runnable, long j) {
        if (j >= 0) {
            this.a.postDelayed(runnable, j);
        }
    }

    public final void f() {
        xr1 xr1Var = this.x;
        wr1 wr1Var = this.a;
        wr1Var.removeCallbacks(xr1Var);
        wr1Var.removeCallbacks(this.u);
        wr1Var.removeCallbacks(this.w);
        wr1Var.removeCallbacks(this.v);
    }

    public final void g() {
        if (this.A == 3) {
            return;
        }
        f();
        int showTimeoutMs = this.a.getShowTimeoutMs();
        if (showTimeoutMs > 0) {
            if (!this.D) {
                e(this.x, showTimeoutMs);
            } else if (this.A == 1) {
                e(this.v, 2000L);
            } else {
                e(this.w, showTimeoutMs);
            }
        }
    }

    public final void h(View view, boolean z) {
        if (view == null) {
            return;
        }
        ArrayList arrayList = this.z;
        if (!z) {
            view.setVisibility(8);
            arrayList.remove(view);
            return;
        }
        if (this.B && j(view)) {
            view.setVisibility(4);
        } else {
            view.setVisibility(0);
        }
        arrayList.add(view);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void i(int i) {
        eg0 eg0Var;
        hu1 hu1Var;
        byte b = this.A;
        this.A = i;
        wr1 wr1Var = this.a;
        if (i == 2) {
            wr1Var.setVisibility(8);
        } else if (b == 2) {
            wr1Var.setVisibility(0);
        }
        if (b != i) {
            for (vr1 vr1Var : wr1Var.v) {
                int visibility = wr1Var.getVisibility();
                cz czVar = ((ms1) vr1Var).n;
                czVar.o();
                ns1 ns1Var = czVar.F;
                if (ns1Var != null) {
                    PlayerActivity playerActivity = ((si) ns1Var).l;
                    boolean z = PlayerActivity.K6;
                    boolean z2 = PlayerActivity.L6;
                    PlayerActivity.K6 = visibility == 0;
                    PlayerActivity.L6 = playerActivity.B.d();
                    if (!PlayerActivity.K6) {
                        playerActivity.v0 = false;
                    } else if (PlayerActivity.L6 || !z) {
                        playerActivity.v0 = true;
                    } else if (z2) {
                        playerActivity.v0 = false;
                    }
                    if (PlayerActivity.K6) {
                        playerActivity.w4();
                        nq1 nq1Var = playerActivity.O5;
                        cz czVar2 = playerActivity.B;
                        if (czVar2 != null) {
                            czVar2.removeCallbacks(nq1Var);
                            playerActivity.B.post(nq1Var);
                        }
                        playerActivity.B.post(new wp1(playerActivity, (byte) 18));
                    }
                    playerActivity.x4();
                    playerActivity.s4();
                    playerActivity.H4();
                    if (!PlayerActivity.K6) {
                        playerActivity.P3();
                    }
                    playerActivity.G2();
                    vg0 vg0Var = PlayerActivity.n6;
                    if (vg0Var != null && playerActivity.g5 != null && (hu1Var = playerActivity.H) != null && hu1Var.n0) {
                        ke2 ke2VarA = playerActivity.g5.a(vg0Var.O0() / 1000.0d);
                        if (ke2VarA != null && !playerActivity.a1(ke2VarA)) {
                            boolean z3 = ke2VarA.i;
                            String str = playerActivity.r4;
                            if (str == null) {
                                hu1 hu1Var2 = playerActivity.H;
                                str = z3 ? hu1Var2.p0 : hu1Var2.o0;
                            }
                            if ("brief".equals(str)) {
                                playerActivity.F(ke2VarA);
                            }
                        }
                    }
                    playerActivity.A4();
                    if (PlayerActivity.V6) {
                        PlayerActivity.V6 = false;
                        vg0 vg0Var2 = PlayerActivity.n6;
                        if (vg0Var2 == null || !vg0Var2.J()) {
                            playerActivity.B.setControllerShowTimeoutMs(-1);
                        } else {
                            playerActivity.B.setControllerShowTimeoutMs(3500);
                        }
                    }
                    if (playerActivity.X1) {
                        playerActivity.m();
                    } else {
                        yt2.k0(playerActivity, playerActivity.B, visibility == 0);
                    }
                    if (visibility != 0) {
                        playerActivity.o2 = false;
                    } else if (playerActivity.o2) {
                        playerActivity.B2.requestFocus();
                        if (PlayerActivity.L6) {
                            playerActivity.o2 = false;
                        }
                    } else if (!playerActivity.r2.requestFocus() && !PlayerActivity.T6) {
                        playerActivity.v2.setFocusable(true);
                        playerActivity.v2.requestFocus();
                    }
                    if (PlayerActivity.K6 && playerActivity.B.d() && (eg0Var = playerActivity.y0) != null) {
                        playerActivity.j3(eg0Var);
                        playerActivity.y0 = null;
                    }
                }
            }
        }
    }

    public final void k() {
        if (!this.D) {
            i(0);
            g();
            return;
        }
        byte b = this.A;
        if (b == 1) {
            this.p.start();
        } else if (b == 2) {
            this.q.start();
        } else if (b == 3) {
            this.C = true;
        } else if (b == 4) {
            return;
        }
        g();
    }
}
