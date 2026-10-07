package defpackage;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.media.AudioManager;
import android.media.audiofx.LoudnessEnhancer;
import android.os.Build;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.core.view.GestureDetectorCompat;
import androidx.media3.ui.AspectRatioFrameLayout;
import androidx.media3.ui.PlayerView;
import com.brouken.player.LevelBar;
import com.brouken.player.PlayerActivity;
import com.justplus.player.R;
import java.util.Collections;
import java.util.Locale;

/* JADX INFO: compiled from: r8-map-id-0cee80150bab987b3edc91bcfc1e04786a1418881e5c92a3b999d44d61001263 */
/* JADX INFO: loaded from: classes.dex */
public abstract class cz extends PlayerView implements GestureDetector.OnGestureListener, ScaleGestureDetector.OnScaleGestureListener {
    public boolean A0;
    public final Rect B0;
    public final bz C0;
    public final AudioManager D0;
    public ck E0;
    public final TextView F0;
    public final View G0;
    public final LevelBar H0;
    public int I0;
    public final GestureDetectorCompat T;
    public float U;
    public float V;
    public boolean W;
    public long a0;
    public long b0;
    public long c0;
    public boolean d0;
    public boolean e0;
    public boolean f0;
    public float g0;
    public final float h0;
    public final float i0;
    public final float j0;
    public boolean k0;
    public boolean l0;
    public boolean m0;
    public long n0;
    public int o0;
    public final ScaleGestureDetector p0;
    public float q0;
    public float r0;
    public boolean s0;
    public float t0;
    public float u0;
    public float v0;
    public boolean w0;
    public long x0;
    public long y0;
    public final bz z0;

    public cz(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.I0 = 3;
        this.U = 0.0f;
        this.V = 0.0f;
        this.e0 = false;
        this.f0 = false;
        this.g0 = 0.0f;
        this.h0 = yt2.p(24);
        this.i0 = yt2.p(16);
        this.j0 = yt2.p(8);
        this.l0 = true;
        this.m0 = false;
        this.n0 = -1L;
        this.o0 = 0;
        this.q0 = 1.0f;
        this.s0 = false;
        this.t0 = 1.0f;
        this.v0 = 2.0f;
        this.z0 = new bz(this, (byte) 0);
        this.B0 = new Rect();
        this.C0 = new bz(this, (byte) 1);
        this.T = new GestureDetectorCompat(context, this);
        this.D0 = (AudioManager) context.getSystemService("audio");
        this.F0 = (TextView) findViewById(R.id.exo_error_message);
        this.G0 = findViewById(R.id.exo_progress);
        this.H0 = (LevelBar) findViewById(R.id.level_bar);
        this.p0 = new ScaleGestureDetector(context, this);
    }

    public final void A(int i) {
        u(i > 0 ? R.drawable.ic_volume_up_24dp : R.drawable.ic_volume_off_24dp, i + "%");
        y(200.0f, i, 8388629);
    }

    public final void B() {
        hu1 hu1Var;
        boolean z = PlayerActivity.U6;
        PlayerActivity.U6 = !z;
        this.m0 = true;
        if (!z && PlayerActivity.K6) {
            c();
        }
        if (getContext() instanceof PlayerActivity) {
            PlayerActivity playerActivity = (PlayerActivity) getContext();
            playerActivity.s4();
            if (PlayerActivity.U6) {
                int rotation = playerActivity.getWindowManager().getDefaultDisplay().getRotation();
                boolean z2 = playerActivity.getResources().getConfiguration().orientation == 1;
                boolean z3 = rotation == 2 || rotation == 3;
                if (z2) {
                    playerActivity.setRequestedOrientation(z3 ? 9 : 1);
                } else {
                    playerActivity.setRequestedOrientation(z3 ? 8 : 0);
                }
                playerActivity.h2 = -3001L;
                playerActivity.A3();
            } else {
                playerActivity.V();
            }
            if (PlayerActivity.U6 && (hu1Var = playerActivity.H) != null && hu1Var.q0) {
                playerActivity.W0();
            }
            playerActivity.E4();
            playerActivity.A4();
        }
    }

    public float getScaleFit() {
        return Math.min(getHeight() / getVideoSurfaceView().getHeight(), getWidth() / getVideoSurfaceView().getWidth());
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public final boolean onDown(MotionEvent motionEvent) {
        this.U = 0.0f;
        this.V = 0.0f;
        this.I0 = 3;
        this.m0 = false;
        return false;
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public final boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        return false;
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (Build.VERSION.SDK_INT >= 29) {
            View view = this.G0;
            Rect rect = this.B0;
            view.getGlobalVisibleRect(rect);
            rect.left = i;
            rect.right = i3;
            setSystemGestureExclusionRects(Collections.singletonList(rect));
        }
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public final void onLongPress(MotionEvent motionEvent) {
        vg0 vg0Var;
        hu1 hu1Var;
        if (PlayerActivity.U6 || this.p0.isInProgress() || this.I0 != 3 || !PlayerActivity.p6 || (vg0Var = PlayerActivity.n6) == null || !vg0Var.J()) {
            return;
        }
        if ("off".equals(((getContext() instanceof PlayerActivity) && (hu1Var = ((PlayerActivity) getContext()).H) != null) ? hu1Var.w : "adjust")) {
            return;
        }
        this.t0 = getContext() instanceof PlayerActivity ? ((PlayerActivity) getContext()).M4() : PlayerActivity.n6.getPlaybackParameters().a;
        this.s0 = true;
        this.m0 = true;
        this.u0 = motionEvent.getX();
        this.v0 = 2.0f;
        this.w0 = false;
        v(2.0f);
        c();
        x();
    }

    @Override // android.view.ScaleGestureDetector.OnScaleGestureListener
    public final boolean onScale(ScaleGestureDetector scaleGestureDetector) {
        if (PlayerActivity.U6 || !this.l0) {
            return false;
        }
        float scaleFactor = scaleGestureDetector.getScaleFactor();
        float f = ((((1.0f - scaleFactor) / 3.0f) * 2.0f) + scaleFactor) * this.q0;
        this.q0 = f;
        float f2 = this.r0;
        String[] strArr = yt2.a;
        float fMax = Math.max(f2, Math.min(f, 2.0f));
        this.q0 = fMax;
        setScale(fMax);
        if (getVideoSurfaceView().getAlpha() != 1.0f) {
            getVideoSurfaceView().setAlpha(1.0f);
        }
        this.F0.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        this.H0.setVisibility(8);
        u(R.drawable.ic_fit_screen_24dp, ((int) (this.q0 * 100.0f)) + "%");
        return true;
    }

    @Override // android.view.ScaleGestureDetector.OnScaleGestureListener
    public final boolean onScaleBegin(ScaleGestureDetector scaleGestureDetector) {
        if (PlayerActivity.U6) {
            return false;
        }
        this.q0 = getVideoSurfaceView().getScaleX();
        if (getResizeMode() != 4) {
            this.l0 = false;
            setAspectRatioListener(new he(this, (byte) 2));
            getVideoSurfaceView().setAlpha(0.0f);
            setResizeMode(4);
        } else {
            this.r0 = getScaleFit();
            this.l0 = true;
        }
        ImageButton imageButton = (ImageButton) findViewById(2147483547);
        if (imageButton != null) {
            imageButton.setImageResource(R.drawable.ic_fit_screen_24dp);
        }
        c();
        return true;
    }

    @Override // android.view.ScaleGestureDetector.OnScaleGestureListener
    public final void onScaleEnd(ScaleGestureDetector scaleGestureDetector) {
        if (PlayerActivity.U6) {
            return;
        }
        if (this.q0 - this.r0 < 0.001d) {
            setScale(1.0f);
            setResizeMode(0);
            ImageButton imageButton = (ImageButton) findViewById(2147483547);
            if (imageButton != null) {
                imageButton.setImageResource(R.drawable.ic_aspect_ratio_24dp);
            }
        }
        vg0 vg0Var = PlayerActivity.n6;
        if (vg0Var != null && !vg0Var.J()) {
            j();
        }
        if (getVideoSurfaceView().getAlpha() != 1.0f) {
            getVideoSurfaceView().setAlpha(1.0f);
        }
        ((PlayerActivity) getContext()).d2();
    }

    /* JADX WARN: Code duplicated, block: B:27:0x0068  */
    /* JADX WARN: Code duplicated, block: B:92:0x01ef  */
    @Override // android.view.GestureDetector.OnGestureListener
    public final boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        float f3;
        float f4;
        boolean z;
        int i;
        float f5;
        float fMax;
        CharSequence charSequence;
        boolean z2;
        hu1 hu1Var;
        int i2;
        long j;
        long j2;
        if (!this.p0.isInProgress() && !PlayerActivity.U6) {
            float y = motionEvent.getY();
            float f6 = this.h0;
            if (y >= f6 && motionEvent.getX() >= f6 && motionEvent.getY() <= getHeight() - f6 && motionEvent.getX() <= getWidth() - f6) {
                if (this.U != 0.0f) {
                    float f7 = this.V;
                    if (f7 != 0.0f) {
                        vg0 vg0Var = PlayerActivity.n6;
                        LevelBar levelBar = this.H0;
                        float f8 = this.i0;
                        if (vg0Var == null || !((i2 = this.I0) == 1 || i2 == 3)) {
                            f3 = 10.0f;
                            f4 = f8;
                            z = true;
                        } else {
                            float f9 = f7 + f;
                            this.V = f9;
                            if (Math.abs(f9) > f8 || (this.I0 == 1 && Math.abs(this.V) > this.j0)) {
                                setControllerAutoShow(false);
                                if (this.I0 == 3) {
                                    if (PlayerActivity.n6.J()) {
                                        this.k0 = true;
                                        PlayerActivity.n6.k(false);
                                    }
                                    this.F0.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                                    levelBar.setVisibility(8);
                                    this.a0 = PlayerActivity.n6.O0();
                                    this.b0 = 0L;
                                    this.c0 = PlayerActivity.n6.getDuration();
                                    z();
                                }
                                this.I0 = 1;
                                this.A0 = true;
                                String[] strArr = yt2.a;
                                float fMax2 = Math.max(0.5f, Math.min(Math.abs((f / Resources.getSystem().getDisplayMetrics().density) / 4.0f), 10.0f));
                                if (PlayerActivity.p6) {
                                    if (this.V > 0.0f) {
                                        z = true;
                                        j = 0;
                                        float f10 = fMax2 * 1000.0f;
                                        if ((this.a0 + this.b0) - f10 >= 0.0f) {
                                            PlayerActivity.n6.t1(c92.e);
                                            long j3 = (long) (this.b0 - f10);
                                            this.b0 = j3;
                                            j2 = this.a0 + j3;
                                            w(j2);
                                            f3 = 10.0f;
                                            f4 = f8;
                                        } else {
                                            f3 = 10.0f;
                                            f4 = f8;
                                            j2 = j;
                                        }
                                    } else {
                                        z = true;
                                        j = 0;
                                        PlayerActivity.n6.t1(c92.f);
                                        long j4 = this.c0;
                                        if (j4 == -9223372036854775807L) {
                                            long j5 = (long) ((fMax2 * 1000.0f) + this.b0);
                                            this.b0 = j5;
                                            j2 = this.a0 + j5;
                                            w(j2);
                                            f3 = 10.0f;
                                            f4 = f8;
                                        } else {
                                            long j6 = this.a0;
                                            f3 = 10.0f;
                                            f4 = f8;
                                            long j7 = this.b0;
                                            if (j6 + j7 + 1000 < j4) {
                                                long j8 = (long) ((fMax2 * 1000.0f) + j7);
                                                this.b0 = j8;
                                                j2 = j6 + j8;
                                                w(j2);
                                            } else {
                                                j2 = j;
                                            }
                                        }
                                    }
                                    String strU = yt2.u(this.b0);
                                    if (!d()) {
                                        strU = strU + "\n" + yt2.t(j2);
                                    }
                                    u(this.b0 < j ? R.drawable.ic_rewind_24dp : R.drawable.ic_fast_forward_24dp, strU);
                                    this.V = 1.0E-4f;
                                } else {
                                    f3 = 10.0f;
                                    f4 = f8;
                                    z = true;
                                }
                            } else {
                                f3 = 10.0f;
                                f4 = f8;
                                z = true;
                            }
                        }
                        if ((!(getContext() instanceof PlayerActivity) || (hu1Var = ((PlayerActivity) getContext()).H) == null || !hu1Var.v) && ((i = this.I0) == 2 || i == 3)) {
                            float f11 = this.U + f2;
                            this.U = f11;
                            AudioManager audioManager = this.D0;
                            if (i != 3) {
                                f5 = f2;
                            } else if (Math.abs(f11) > f4) {
                                float fY = yt2.y(getContext(), audioManager);
                                this.g0 = fY;
                                if (fY < 100.0f) {
                                    z2 = false;
                                } else {
                                    if (PlayerActivity.m6 == null) {
                                        try {
                                            LoudnessEnhancer loudnessEnhancer = PlayerActivity.l6;
                                            if (loudnessEnhancer == null || !loudnessEnhancer.hasControl()) {
                                                z2 = false;
                                            }
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                    z2 = z;
                                }
                                this.e0 = z2;
                                this.f0 = this.E0.c <= 0.0f ? z : false;
                                this.I0 = 2;
                                f5 = this.U;
                            }
                            float height = (f5 / getHeight()) * 100.0f * 1.25f;
                            if (motionEvent.getX() < getWidth() / 2) {
                                ck ckVar = this.E0;
                                boolean z3 = this.f0;
                                ValueAnimator valueAnimator = ckVar.b;
                                if (valueAnimator != null) {
                                    valueAnimator.cancel();
                                    ckVar.b = null;
                                }
                                float fMax3 = ckVar.c;
                                if (fMax3 < 0.0f) {
                                    fMax3 = (float) Math.max(0.0d, Math.min(100.0d, ((Math.sqrt(Math.max(0.0f, ckVar.b())) - 0.064d) / 0.936d) * 100.0d));
                                }
                                float f12 = fMax3 + height;
                                if (!z3 || f12 >= 0.0f) {
                                    fMax = Math.max(0.0f, Math.min(100.0f, f12));
                                    ckVar.c = fMax;
                                } else {
                                    ckVar.c = -1.0f;
                                    fMax = -1.0f;
                                }
                                if (fMax < 0.0f) {
                                    ckVar.a(-1.0f);
                                } else {
                                    double d = (((double) fMax) * 0.00936d) + 0.064d;
                                    ckVar.a((float) (d * d));
                                }
                                int iRound = Math.round(ckVar.c);
                                boolean z4 = ckVar.c < 0.0f ? z : false;
                                if (z4) {
                                    charSequence = "";
                                } else {
                                    charSequence = iRound + "%";
                                }
                                u(z4 ? R.drawable.ic_brightness_auto_24dp : R.drawable.ic_brightness_medium_24, charSequence);
                                if (z4) {
                                    levelBar.setVisibility(8);
                                } else {
                                    y(100.0f, iRound, 8388627);
                                }
                            } else {
                                this.g0 = Math.max(0.0f, Math.min(this.e0 ? 200.0f : 100.0f, this.g0 + height));
                                Context context = getContext();
                                float f13 = this.g0;
                                String[] strArr2 = yt2.a;
                                removeCallbacks(this.C0);
                                if (PlayerActivity.P6) {
                                    int iRound2 = Math.round((Math.min(f13, 100.0f) / 100.0f) * audioManager.getStreamMaxVolume(3));
                                    if (iRound2 != audioManager.getStreamVolume(3)) {
                                        try {
                                            audioManager.setStreamVolume(3, iRound2, 0);
                                        } catch (RuntimeException e2) {
                                            e2.printStackTrace();
                                        }
                                    }
                                } else {
                                    PlayerActivity.Q6 = Math.min(f13, 100.0f);
                                    vg0 vg0Var2 = PlayerActivity.n6;
                                    if (vg0Var2 != null) {
                                        vg0Var2.f(PlayerActivity.Q6 / 100.0f);
                                    }
                                }
                                PlayerActivity.N6 = f13 > 100.0f ? Math.min(f3, (f13 - 100.0f) / f3) : 0.0f;
                                yt2.c();
                                yt2.o0(context);
                                A(yt2.y(context, audioManager));
                            }
                        }
                        return z;
                    }
                }
                this.U = 1.0E-4f;
                this.V = 1.0E-4f;
            }
        }
        return false;
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public final boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    /* JADX WARN: Code duplicated, block: B:68:0x00d6  */
    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        boolean zT;
        vg0 vg0Var;
        hu1 hu1Var;
        wr1 wr1Var;
        bs1 bs1Var;
        byte b;
        if (PlayerActivity.V6) {
            setControllerShowTimeoutMs(3500);
            PlayerActivity.V6 = false;
        }
        if (Build.VERSION.SDK_INT >= 24 && this.I0 == 3) {
            this.p0.onTouchEvent(motionEvent);
        }
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            bf2 bf2Var = PlayerActivity.M6;
            if (bf2Var != null) {
                yl2 yl2VarO = yl2.O();
                nh nhVar = bf2Var.t;
                synchronized (yl2VarO.m) {
                    zT = yl2VarO.T(nhVar);
                }
                if (zT) {
                    PlayerActivity.M6.a(3);
                    this.W = false;
                } else {
                    removeCallbacks(this.C0);
                    this.W = true;
                }
            } else {
                removeCallbacks(this.C0);
                this.W = true;
            }
        } else if (actionMasked == 1 || actionMasked == 3) {
            this.A0 = false;
            if (this.s0) {
                this.s0 = false;
                removeCallbacks(this.z0);
                if (PlayerActivity.n6 != null) {
                    if (this.w0) {
                        PlayerActivity.O2(this.x0);
                    }
                    v(this.t0);
                }
                this.w0 = false;
                if (getContext() instanceof PlayerActivity) {
                    ((PlayerActivity) getContext()).b3(false);
                }
            }
            if (this.W) {
                if (this.I0 == 1) {
                    if (PlayerActivity.p6 && PlayerActivity.n6 != null) {
                        PlayerActivity.O2(this.a0 + this.b0);
                    }
                    u(0, null);
                } else {
                    postDelayed(this.C0, this.m0 ? 1400L : 400L);
                }
                if (this.k0) {
                    this.k0 = false;
                    vg0 vg0Var2 = PlayerActivity.n6;
                    if (vg0Var2 != null) {
                        vg0Var2.k(true);
                    }
                }
                setControllerAutoShow(true);
                if (this.d0) {
                    this.d0 = false;
                    if (!d() && (wr1Var = this.w) != null && (b = (bs1Var = wr1Var.l).A) != 3 && b != 2) {
                        bs1Var.f();
                        bs1Var.i(2);
                    }
                }
            }
        }
        if (this.s0 && motionEvent.getActionMasked() == 2) {
            String str = "adjust";
            if ((getContext() instanceof PlayerActivity) && (hu1Var = ((PlayerActivity) getContext()).H) != null) {
                str = hu1Var.w;
            }
            if ("adjust".equals(str)) {
                float x = motionEvent.getX();
                bz bzVar = this.z0;
                if (PlayerActivity.n6 != null) {
                    float f = x - this.u0;
                    String[] strArr = yt2.a;
                    float f2 = ((f / Resources.getSystem().getDisplayMetrics().density) / 40.0f) + 2.0f;
                    boolean z = !this.w0 ? f2 >= 1.0f : f2 >= 1.1f;
                    float fRound = Math.round(Math.min(4.0f, z ? (1.0f - f2) + 2.0f : Math.max(1.0f, f2)) * 10.0f) / 10.0f;
                    boolean z2 = this.w0;
                    if (z != z2 || fRound != this.v0) {
                        this.v0 = fRound;
                        if (z != z2) {
                            this.w0 = z;
                            if (z) {
                                this.A0 = true;
                                if (PlayerActivity.n6.J()) {
                                    this.k0 = true;
                                    PlayerActivity.n6.k(false);
                                }
                                v(this.t0);
                                PlayerActivity.n6.t1(c92.e);
                                this.x0 = PlayerActivity.n6.O0();
                                this.y0 = SystemClock.uptimeMillis();
                                z();
                                post(bzVar);
                            } else {
                                removeCallbacks(bzVar);
                                PlayerActivity.O2(this.x0);
                                if (this.k0 && (vg0Var = PlayerActivity.n6) != null) {
                                    this.k0 = false;
                                    vg0Var.k(true);
                                }
                            }
                        }
                        if (!this.w0) {
                            v(this.v0);
                        }
                        x();
                        return true;
                    }
                }
            }
        } else if (this.W) {
            this.T.a.onTouchEvent(motionEvent);
        }
        return true;
    }

    public void setBrightnessControl(ck ckVar) {
        this.E0 = ckVar;
    }

    public void setScale(float f) {
        if (Build.VERSION.SDK_INT >= 24) {
            View videoSurfaceView = getVideoSurfaceView();
            try {
                videoSurfaceView.setScaleX(f);
                videoSurfaceView.setScaleY(f);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:7:0x001b  */
    public final void t(int i, float f) {
        int i2;
        int i3;
        setScale(1.0f);
        setResizeMode(i);
        AspectRatioFrameLayout aspectRatioFrameLayout = (AspectRatioFrameLayout) findViewById(R.id.exo_content_frame);
        if (f <= 0.0f) {
            vg0 vg0Var = PlayerActivity.n6;
            if (vg0Var == null) {
                f = 0.0f;
            } else {
                vg0Var.A1();
                zl0 zl0Var = vg0Var.V;
                if (zl0Var == null || (i2 = zl0Var.w) <= 0 || (i3 = zl0Var.x) <= 0) {
                    f = 0.0f;
                } else {
                    float f2 = zl0Var.E;
                    f = (i2 * (f2 > 0.0f ? f2 : 1.0f)) / i3;
                }
            }
        }
        if (aspectRatioFrameLayout == null || f <= 0.0f) {
            return;
        }
        aspectRatioFrameLayout.setAspectRatio(f);
    }

    public final void u(int i, CharSequence charSequence) {
        TextView textView = this.F0;
        textView.setCompoundDrawablesWithIntrinsicBounds(i, 0, 0, 0);
        this.H0.setVisibility(8);
        if (charSequence != null) {
            bf2 bf2Var = y61.r;
            if (bf2Var != null) {
                bf2Var.a(3);
                y61.r = null;
            }
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) textView.getLayoutParams();
            int iU = y61.U(this);
            if (marginLayoutParams.topMargin != iU) {
                marginLayoutParams.topMargin = iU;
                textView.setLayoutParams(marginLayoutParams);
            }
        }
        setCustomErrorMessage(charSequence);
    }

    public final void v(float f) {
        if (getContext() instanceof PlayerActivity) {
            ((PlayerActivity) getContext()).l2(f);
        }
    }

    public final void w(long j) {
        if (getContext() instanceof PlayerActivity) {
            ((PlayerActivity) getContext()).P2(j);
        }
    }

    public final void x() {
        if (getContext() instanceof PlayerActivity) {
            PlayerActivity playerActivity = (PlayerActivity) getContext();
            float f = this.v0;
            boolean z = this.w0;
            TextView textView = playerActivity.r5;
            if (textView == null) {
                return;
            }
            textView.setText(String.format(Locale.US, "%.1f×", Float.valueOf(f)));
            playerActivity.r5.setCompoundDrawablesRelative(z ? playerActivity.t5 : null, null, z ? null : playerActivity.s5, null);
            playerActivity.b3(true);
        }
    }

    public final void y(float f, int i, int i2) {
        LevelBar levelBar = this.H0;
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) levelBar.getLayoutParams();
        if (layoutParams.gravity != i2) {
            layoutParams.gravity = i2;
            levelBar.setLayoutParams(layoutParams);
        }
        levelBar.p = i;
        levelBar.q = f;
        levelBar.invalidate();
        levelBar.setVisibility(0);
    }

    public final void z() {
        if (d()) {
            return;
        }
        this.d0 = true;
        wr1 wr1Var = this.w;
        if (wr1Var != null) {
            bs1 bs1Var = wr1Var.l;
            float f = bs1Var.E;
            View view = bs1Var.k;
            byte b = bs1Var.A;
            if (b == 0) {
                bs1Var.m.start();
                return;
            }
            if (b != 2) {
                return;
            }
            wr1 wr1Var2 = bs1Var.a;
            bs1Var.A = (byte) 1;
            if (b == 2) {
                wr1Var2.setVisibility(0);
            }
            if (view instanceof l50) {
                ((l50) view).b(false);
            }
            view.setTranslationY(f);
            bs1Var.e.setTranslationY(f);
        }
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public final void onShowPress(MotionEvent motionEvent) {
    }
}
