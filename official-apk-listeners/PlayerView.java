package androidx.media3.ui;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.media3.exoplayer.image.ImageOutput;
import androidx.media3.ui.PlayerView;
import com.justplus.player.R;
import defpackage.bf0;
import defpackage.bs1;
import defpackage.bv2;
import defpackage.cz;
import defpackage.dx2;
import defpackage.ha1;
import defpackage.jw1;
import defpackage.kg2;
import defpackage.ms1;
import defpackage.nr1;
import defpackage.ns1;
import defpackage.o4;
import defpackage.oq2;
import defpackage.os1;
import defpackage.ps1;
import defpackage.pw0;
import defpackage.q90;
import defpackage.qt2;
import defpackage.to1;
import defpackage.tv2;
import defpackage.u2;
import defpackage.vr1;
import defpackage.wr1;
import defpackage.ya;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-0cee80150bab987b3edc91bcfc1e04786a1418881e5c92a3b999d44d61001263 */
/* JADX INFO: loaded from: classes.dex */
public abstract class PlayerView extends FrameLayout {
    public static final /* synthetic */ int S = 0;
    public final Class A;
    public final Method B;
    public final Object C;
    public to1 D;
    public boolean E;
    public ns1 F;
    public vr1 G;
    public int H;
    public int I;
    public Drawable J;
    public int K;
    public boolean L;
    public CharSequence M;
    public int N;
    public boolean O;
    public boolean P;
    public boolean Q;
    public boolean R;
    public final ms1 l;
    public final AspectRatioFrameLayout m;
    public final View n;
    public final View o;
    public final boolean p;
    public final ps1 q;
    public final ImageView r;
    public final ImageView s;
    public final SubtitleView t;
    public final View u;
    public final TextView v;
    public final wr1 w;
    public final FrameLayout x;
    public final FrameLayout y;
    public final Handler z;

    /* JADX WARN: Multi-variable type inference failed */
    public PlayerView(Context context, AttributeSet attributeSet, int i) {
        int i2;
        int i3;
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        boolean z5;
        boolean z6;
        int i10;
        boolean z7;
        int i11;
        Class<ExoPlayer> cls;
        Object objNewProxyInstance;
        Method method;
        super(context, attributeSet, i);
        ms1 ms1Var = new ms1((cz) this);
        this.l = ms1Var;
        this.z = new Handler(Looper.getMainLooper());
        if (isInEditMode()) {
            this.m = null;
            this.n = null;
            this.o = null;
            this.p = false;
            this.q = null;
            this.r = null;
            this.s = null;
            this.t = null;
            this.u = null;
            this.v = null;
            this.w = null;
            this.x = null;
            this.y = null;
            this.A = null;
            this.B = null;
            this.C = null;
            ImageView imageView = new ImageView(context);
            Resources resources = getResources();
            String str = qt2.a;
            imageView.setImageDrawable(resources.getDrawable(R.drawable.exo_edit_mode_logo, context.getTheme()));
            imageView.setBackgroundColor(resources.getColor(R.color.exo_edit_mode_background_color, null));
            addView(imageView);
            return;
        }
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, jw1.d, i, 0);
            try {
                boolean zHasValue = typedArrayObtainStyledAttributes.hasValue(42);
                int color = typedArrayObtainStyledAttributes.getColor(42, 0);
                int resourceId = typedArrayObtainStyledAttributes.getResourceId(22, R.layout.exo_player_view);
                boolean z8 = typedArrayObtainStyledAttributes.getBoolean(50, true);
                int i12 = typedArrayObtainStyledAttributes.getInt(3, 1);
                int resourceId2 = typedArrayObtainStyledAttributes.getResourceId(9, 0);
                int i13 = typedArrayObtainStyledAttributes.getInt(15, 0);
                boolean z9 = typedArrayObtainStyledAttributes.getBoolean(51, true);
                int i14 = typedArrayObtainStyledAttributes.getInt(45, 1);
                int i15 = typedArrayObtainStyledAttributes.getInt(28, 0);
                z = z9;
                i2 = typedArrayObtainStyledAttributes.getInt(38, 5000);
                boolean z10 = typedArrayObtainStyledAttributes.getBoolean(14, true);
                boolean z11 = typedArrayObtainStyledAttributes.getBoolean(4, true);
                int integer = typedArrayObtainStyledAttributes.getInteger(35, 0);
                this.L = typedArrayObtainStyledAttributes.getBoolean(16, this.L);
                boolean z12 = typedArrayObtainStyledAttributes.getBoolean(13, true);
                typedArrayObtainStyledAttributes.recycle();
                z4 = z12;
                z2 = z10;
                z6 = z8;
                i9 = color;
                i3 = resourceId;
                i5 = resourceId2;
                i7 = i15;
                z3 = z11;
                i4 = integer;
                i10 = i12;
                z5 = zHasValue;
                i8 = i14;
                i6 = i13;
            } catch (Throwable th) {
                typedArrayObtainStyledAttributes.recycle();
                throw th;
            }
        } else {
            i2 = 5000;
            i3 = R.layout.exo_player_view;
            z = true;
            z2 = true;
            z3 = true;
            z4 = true;
            i4 = 0;
            i5 = 0;
            i6 = 0;
            i7 = 0;
            i8 = 1;
            i9 = 0;
            z5 = false;
            z6 = true;
            i10 = 1;
        }
        LayoutInflater.from(context).inflate(i3, this);
        setDescendantFocusability(262144);
        AspectRatioFrameLayout aspectRatioFrameLayout = (AspectRatioFrameLayout) findViewById(R.id.exo_content_frame);
        this.m = aspectRatioFrameLayout;
        if (aspectRatioFrameLayout != null) {
            aspectRatioFrameLayout.setResizeMode(i7);
        }
        View viewFindViewById = findViewById(R.id.exo_shutter);
        this.n = viewFindViewById;
        if (viewFindViewById != null && z5) {
            viewFindViewById.setBackgroundColor(i9);
        }
        if (aspectRatioFrameLayout == null || i8 == 0) {
            this.o = null;
            z7 = false;
        } else {
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(-1, -1);
            if (i8 != 2) {
                if (i8 == 3) {
                    try {
                        int i16 = kg2.w;
                        this.o = (View) kg2.class.getConstructor(Context.class).newInstance(context);
                        z7 = true;
                    } catch (Exception e) {
                        throw new IllegalStateException("spherical_gl_surface_view requires an ExoPlayer dependency", e);
                    }
                } else if (i8 != 4) {
                    SurfaceView surfaceView = new SurfaceView(context);
                    if (Build.VERSION.SDK_INT >= 34) {
                        u2.i(surfaceView);
                    }
                    this.o = surfaceView;
                } else {
                    try {
                        int i17 = bv2.m;
                        this.o = (View) bv2.class.getConstructor(Context.class).newInstance(context);
                    } catch (Exception e2) {
                        throw new IllegalStateException("video_decoder_gl_surface_view requires an ExoPlayer dependency", e2);
                    }
                }
                this.o.setLayoutParams(layoutParams);
                this.o.setOnClickListener(ms1Var);
                this.o.setClickable(false);
                aspectRatioFrameLayout.addView(this.o, 0);
            } else {
                this.o = new TextureView(context);
            }
            z7 = false;
            this.o.setLayoutParams(layoutParams);
            this.o.setOnClickListener(ms1Var);
            this.o.setClickable(false);
            aspectRatioFrameLayout.addView(this.o, 0);
        }
        this.p = z7;
        this.q = Build.VERSION.SDK_INT == 34 ? new ps1() : null;
        this.x = (FrameLayout) findViewById(R.id.exo_ad_overlay);
        this.y = (FrameLayout) findViewById(R.id.exo_overlay);
        this.r = (ImageView) findViewById(R.id.exo_image);
        this.I = i6;
        try {
            cls = ExoPlayer.class;
            Class<?>[] clsArr = new Class[1];
            i11 = 0;
            try {
                clsArr[0] = ImageOutput.class;
                method = cls.getMethod("setImageOutput", clsArr);
                final cz czVar = (cz) this;
                objNewProxyInstance = Proxy.newProxyInstance(ImageOutput.class.getClassLoader(), new Class[]{ImageOutput.class}, new InvocationHandler() { // from class: ls1
                    @Override // java.lang.reflect.InvocationHandler
                    public final Object invoke(Object obj, Method method2, Object[] objArr) {
                        int i18 = PlayerView.S;
                        if (!method2.getName().equals("onImageAvailable")) {
                            return null;
                        }
                        Bitmap bitmap = (Bitmap) objArr[1];
                        cz czVar2 = czVar;
                        czVar2.z.post(new hj1(czVar2, bitmap, (byte) 7));
                        return null;
                    }
                });
            } catch (ClassNotFoundException | NoSuchMethodException unused) {
                cls = null;
                objNewProxyInstance = null;
                method = null;
            }
        } catch (ClassNotFoundException | NoSuchMethodException unused2) {
            i11 = 0;
        }
        this.A = cls;
        this.B = method;
        this.C = objNewProxyInstance;
        ImageView imageView2 = (ImageView) findViewById(R.id.exo_artwork);
        this.s = imageView2;
        this.H = (!z6 || i10 == 0 || imageView2 == null) ? i11 : i10;
        if (i5 != 0) {
            this.J = getContext().getDrawable(i5);
        }
        SubtitleView subtitleView = (SubtitleView) findViewById(R.id.exo_subtitles);
        this.t = subtitleView;
        if (subtitleView != null) {
            subtitleView.a();
            subtitleView.b();
        }
        View viewFindViewById2 = findViewById(R.id.exo_buffering);
        this.u = viewFindViewById2;
        if (viewFindViewById2 != null) {
            viewFindViewById2.setVisibility(8);
        }
        this.K = i4;
        TextView textView = (TextView) findViewById(R.id.exo_error_message);
        this.v = textView;
        if (textView != null) {
            textView.setVisibility(8);
        }
        wr1 wr1Var = (wr1) findViewById(R.id.exo_controller);
        View viewFindViewById3 = findViewById(R.id.exo_controller_placeholder);
        if (wr1Var != null) {
            this.w = wr1Var;
        } else if (viewFindViewById3 != null) {
            wr1Var = new wr1(context, attributeSet);
            this.w = wr1Var;
            wr1Var.setId(R.id.exo_controller);
            wr1Var.setLayoutParams(viewFindViewById3.getLayoutParams());
            ViewGroup viewGroup = (ViewGroup) viewFindViewById3.getParent();
            int iIndexOfChild = viewGroup.indexOfChild(viewFindViewById3);
            viewGroup.removeView(viewFindViewById3);
            viewGroup.addView(wr1Var, iIndexOfChild);
        } else {
            this.w = null;
            wr1Var = null;
        }
        this.N = wr1Var != null ? i2 : i11;
        this.Q = z2;
        this.O = z3;
        this.P = z4;
        this.E = (!z || wr1Var == null) ? i11 : 1;
        if (wr1Var != null) {
            bs1 bs1Var = wr1Var.l;
            byte b = bs1Var.A;
            if (b != 3 && b != 2) {
                bs1Var.f();
                bs1Var.i(2);
            }
            ms1 ms1Var2 = this.l;
            ms1Var2.getClass();
            wr1Var.v.add(ms1Var2);
        }
        if (z) {
            setClickable(true);
        }
        o();
    }

    private void setImage(Drawable drawable) {
        ImageView imageView = this.r;
        if (imageView == null) {
            return;
        }
        imageView.setImageDrawable(drawable);
        r();
    }

    private void setImageOutput(to1 to1Var) {
        Class cls = this.A;
        if (cls == null || !cls.isAssignableFrom(to1Var.getClass())) {
            return;
        }
        try {
            Method method = this.B;
            method.getClass();
            Object obj = this.C;
            obj.getClass();
            try {
                method.invoke(to1Var, obj);
            } catch (InvocationTargetException e) {
                e = e;
                q90.m(e);
            }
        } catch (IllegalAccessException | InvocationTargetException e2) {
            e = e2;
        }
    }

    public final boolean a() {
        to1 to1Var = this.D;
        return to1Var != null && this.C != null && to1Var.W(30) && to1Var.E().b(4);
    }

    public final void b() {
        ImageView imageView = this.r;
        if (imageView != null) {
            imageView.setVisibility(4);
        }
        if (imageView != null) {
            imageView.setImageResource(android.R.color.transparent);
        }
    }

    public final void c() {
        wr1 wr1Var = this.w;
        if (wr1Var != null) {
            wr1Var.f();
        }
    }

    public final boolean d() {
        wr1 wr1Var = this.w;
        return wr1Var != null && wr1Var.i();
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void dispatchDraw(Canvas canvas) {
        ps1 ps1Var;
        super.dispatchDraw(canvas);
        if (Build.VERSION.SDK_INT == 34 && (ps1Var = this.q) != null && this.R) {
            ps1Var.b();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final boolean dispatchKeyEvent(KeyEvent keyEvent) {
        to1 to1Var = this.D;
        if (to1Var != null && to1Var.W(16) && this.D.l()) {
            return super.dispatchKeyEvent(keyEvent);
        }
        int keyCode = keyEvent.getKeyCode();
        boolean z = keyCode == 19 || keyCode == 270 || keyCode == 22 || keyCode == 271 || keyCode == 20 || keyCode == 269 || keyCode == 21 || keyCode == 268 || keyCode == 23;
        wr1 wr1Var = this.w;
        if (z && s() && !wr1Var.i()) {
            g(true);
            return true;
        }
        if ((s() && wr1Var.c(keyEvent)) || super.dispatchKeyEvent(keyEvent)) {
            g(true);
            return true;
        }
        if (z && s()) {
            g(true);
        }
        return false;
    }

    public final boolean e() {
        to1 to1Var = this.D;
        return to1Var != null && to1Var.W(16) && this.D.l() && this.D.w();
    }

    public final void f(Bitmap bitmap) {
        setImage(new BitmapDrawable(getResources(), bitmap));
        to1 to1Var = this.D;
        if (to1Var != null && to1Var.W(30) && to1Var.E().b(2)) {
            return;
        }
        ImageView imageView = this.r;
        if (imageView != null) {
            imageView.setVisibility(0);
            r();
        }
        View view = this.n;
        if (view != null) {
            view.setVisibility(0);
        }
    }

    public final void g(boolean z) {
        if (!(e() && this.P) && s()) {
            wr1 wr1Var = this.w;
            boolean z2 = wr1Var.i() && wr1Var.getShowTimeoutMs() <= 0;
            boolean zI = i();
            if (z || z2 || zI) {
                k(zI);
            }
        }
    }

    public List<o4> getAdOverlayInfos() {
        ArrayList arrayList = new ArrayList();
        FrameLayout frameLayout = this.y;
        if (frameLayout != null) {
            arrayList.add(new o4(frameLayout));
        }
        wr1 wr1Var = this.w;
        if (wr1Var != null) {
            arrayList.add(new o4(wr1Var));
        }
        return pw0.l(arrayList);
    }

    public ViewGroup getAdViewGroup() {
        FrameLayout frameLayout = this.x;
        ha1.o(frameLayout, "exo_ad_overlay must be present for ad playback");
        return frameLayout;
    }

    public int getArtworkDisplayMode() {
        return this.H;
    }

    public boolean getControllerAutoShow() {
        return this.O;
    }

    public boolean getControllerHideOnTouch() {
        return this.Q;
    }

    public int getControllerShowTimeoutMs() {
        return this.N;
    }

    public Drawable getDefaultArtwork() {
        return this.J;
    }

    public int getImageDisplayMode() {
        return this.I;
    }

    public FrameLayout getOverlayFrameLayout() {
        return this.y;
    }

    public to1 getPlayer() {
        return this.D;
    }

    public int getResizeMode() {
        AspectRatioFrameLayout aspectRatioFrameLayout = this.m;
        aspectRatioFrameLayout.getClass();
        return aspectRatioFrameLayout.getResizeMode();
    }

    public SubtitleView getSubtitleView() {
        return this.t;
    }

    @Deprecated
    public boolean getUseArtwork() {
        return this.H != 0;
    }

    public boolean getUseController() {
        return this.E;
    }

    public View getVideoSurfaceView() {
        return this.o;
    }

    public final boolean h(Drawable drawable) {
        ImageView imageView = this.s;
        if (imageView != null && drawable != null) {
            int intrinsicWidth = drawable.getIntrinsicWidth();
            int intrinsicHeight = drawable.getIntrinsicHeight();
            if (intrinsicWidth > 0 && intrinsicHeight > 0) {
                float width = intrinsicWidth / intrinsicHeight;
                ImageView.ScaleType scaleType = ImageView.ScaleType.FIT_XY;
                if (this.H == 2) {
                    width = getWidth() / getHeight();
                    scaleType = ImageView.ScaleType.CENTER_CROP;
                }
                AspectRatioFrameLayout aspectRatioFrameLayout = this.m;
                if (aspectRatioFrameLayout != null) {
                    aspectRatioFrameLayout.setAspectRatio(width);
                }
                imageView.setScaleType(scaleType);
                imageView.setImageDrawable(drawable);
                imageView.setVisibility(0);
                return true;
            }
        }
        return false;
    }

    public final boolean i() {
        to1 to1Var = this.D;
        if (to1Var == null) {
            return true;
        }
        int iC = to1Var.C();
        if (!this.O) {
            return false;
        }
        if (this.D.W(17) && this.D.r0().p()) {
            return false;
        }
        if (iC != 1 && iC != 4) {
            to1 to1Var2 = this.D;
            to1Var2.getClass();
            if (to1Var2.w()) {
                return false;
            }
        }
        return true;
    }

    public final void j() {
        k(i());
    }

    public final void k(boolean z) {
        if (s()) {
            int i = z ? 0 : this.N;
            wr1 wr1Var = this.w;
            wr1Var.setShowTimeoutMs(i);
            bs1 bs1Var = wr1Var.l;
            wr1 wr1Var2 = bs1Var.a;
            if (!wr1Var2.k()) {
                wr1Var2.setVisibility(0);
                wr1Var2.m();
                ImageView imageView = wr1Var2.F;
                if (imageView != null) {
                    imageView.requestFocus();
                }
            }
            bs1Var.k();
        }
    }

    public final void l() {
        if (!s() || this.D == null) {
            return;
        }
        wr1 wr1Var = this.w;
        if (!wr1Var.i()) {
            g(true);
        } else if (this.Q) {
            wr1Var.f();
        }
    }

    public final void m() {
        to1 to1Var = this.D;
        tv2 tv2VarO = to1Var != null ? to1Var.O() : tv2.d;
        int i = tv2VarO.a;
        int i2 = tv2VarO.b;
        float f = this.p ? 0.0f : (i2 == 0 || i == 0) ? 0.0f : (i * tv2VarO.c) / i2;
        AspectRatioFrameLayout aspectRatioFrameLayout = this.m;
        if (aspectRatioFrameLayout != null) {
            aspectRatioFrameLayout.setAspectRatio(f);
        }
    }

    /* JADX WARN: Code duplicated, block: B:14:0x0020  */
    public final void n() {
        boolean z;
        View view = this.u;
        if (view != null) {
            to1 to1Var = this.D;
            if (to1Var == null || to1Var.C() != 2) {
                z = false;
            } else {
                int i = this.K;
                z = true;
                if (i != 2 && (i != 1 || !this.D.w())) {
                    z = false;
                }
            }
            view.setVisibility(z ? 0 : 8);
        }
    }

    public final void o() {
        wr1 wr1Var = this.w;
        if (wr1Var == null || !this.E) {
            setContentDescription(null);
        } else if (wr1Var.i()) {
            setContentDescription(this.Q ? getResources().getString(R.string.exo_controls_hide) : null);
        } else {
            setContentDescription(getResources().getString(R.string.exo_controls_show));
        }
    }

    @Override // android.view.View
    public final boolean onTrackballEvent(MotionEvent motionEvent) {
        if (!s() || this.D == null) {
            return false;
        }
        g(true);
        return true;
    }

    public final void p() {
        TextView textView = this.v;
        if (textView != null) {
            CharSequence charSequence = this.M;
            if (charSequence != null) {
                textView.setText(charSequence);
                textView.setVisibility(0);
            } else {
                to1 to1Var = this.D;
                if (to1Var != null) {
                    to1Var.i();
                }
                textView.setVisibility(8);
            }
        }
    }

    @Override // android.view.View
    public final boolean performClick() {
        l();
        return super.performClick();
    }

    public final void q(boolean z) {
        byte[] bArr;
        Drawable drawable;
        to1 to1Var = this.D;
        boolean zH = false;
        boolean z2 = (to1Var == null || !to1Var.W(30) || to1Var.E().a.isEmpty()) ? false : true;
        boolean z3 = this.L;
        ImageView imageView = this.s;
        View view = this.n;
        if (!z3 && (!z2 || z)) {
            if (imageView != null) {
                imageView.setImageResource(android.R.color.transparent);
                imageView.setVisibility(4);
            }
            if (view != null) {
                view.setVisibility(0);
            }
            b();
        }
        if (z2) {
            to1 to1Var2 = this.D;
            boolean z4 = to1Var2 != null && to1Var2.W(30) && to1Var2.E().b(2);
            boolean zA = a();
            if (!z4 && !zA) {
                if (view != null) {
                    view.setVisibility(0);
                }
                b();
            }
            ImageView imageView2 = this.r;
            boolean z5 = (view == null || view.getVisibility() != 4 || imageView2 == null || (drawable = imageView2.getDrawable()) == null || drawable.getAlpha() == 0) ? false : true;
            if (zA && !z4 && z5) {
                if (view != null) {
                    view.setVisibility(0);
                }
                if (imageView2 != null) {
                    imageView2.setVisibility(0);
                    r();
                }
            } else if (z4 && !zA && z5) {
                b();
            }
            if (!z4 && !zA && this.H != 0) {
                imageView.getClass();
                if (to1Var != null && to1Var.W(18) && (bArr = to1Var.L0().k) != null) {
                    zH = h(new BitmapDrawable(getResources(), BitmapFactory.decodeByteArray(bArr, 0, bArr.length)));
                }
                if (zH || h(this.J)) {
                    return;
                }
            }
            if (imageView != null) {
                imageView.setImageResource(android.R.color.transparent);
                imageView.setVisibility(4);
            }
        }
    }

    public final void r() {
        Drawable drawable;
        AspectRatioFrameLayout aspectRatioFrameLayout;
        ImageView imageView = this.r;
        if (imageView == null || (drawable = imageView.getDrawable()) == null) {
            return;
        }
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();
        if (intrinsicWidth <= 0 || intrinsicHeight <= 0) {
            return;
        }
        float width = intrinsicWidth / intrinsicHeight;
        ImageView.ScaleType scaleType = ImageView.ScaleType.FIT_XY;
        if (this.I == 1) {
            width = getWidth() / getHeight();
            scaleType = ImageView.ScaleType.CENTER_CROP;
        }
        if (imageView.getVisibility() == 0 && (aspectRatioFrameLayout = this.m) != null) {
            aspectRatioFrameLayout.setAspectRatio(width);
        }
        imageView.setScaleType(scaleType);
    }

    public final boolean s() {
        if (!this.E) {
            return false;
        }
        this.w.getClass();
        return true;
    }

    public void setArtworkDisplayMode(int i) {
        ha1.s(i == 0 || this.s != null);
        if (this.H != i) {
            this.H = i;
            q(false);
        }
    }

    public void setAspectRatioListener(ya yaVar) {
        AspectRatioFrameLayout aspectRatioFrameLayout = this.m;
        aspectRatioFrameLayout.getClass();
        aspectRatioFrameLayout.setAspectRatioListener(yaVar);
    }

    public void setControllerAnimationEnabled(boolean z) {
        wr1 wr1Var = this.w;
        wr1Var.getClass();
        wr1Var.setAnimationEnabled(z);
    }

    public void setControllerAutoShow(boolean z) {
        this.O = z;
    }

    public void setControllerHideDuringAds(boolean z) {
        this.P = z;
    }

    public void setControllerHideOnTouch(boolean z) {
        this.w.getClass();
        this.Q = z;
        o();
    }

    @Deprecated
    public void setControllerOnFullScreenModeChangedListener(nr1 nr1Var) {
        wr1 wr1Var = this.w;
        wr1Var.getClass();
        wr1Var.setOnFullScreenModeChangedListener(nr1Var);
    }

    public void setControllerShowTimeoutMs(int i) {
        wr1 wr1Var = this.w;
        wr1Var.getClass();
        this.N = i;
        if (wr1Var.i()) {
            j();
        }
    }

    @Deprecated
    public void setControllerVisibilityListener(vr1 vr1Var) {
        wr1 wr1Var = this.w;
        wr1Var.getClass();
        vr1 vr1Var2 = this.G;
        if (vr1Var2 == vr1Var) {
            return;
        }
        if (vr1Var2 != null) {
            wr1Var.v.remove(vr1Var2);
        }
        this.G = vr1Var;
        if (vr1Var != null) {
            wr1Var.getClass();
            wr1Var.v.add(vr1Var);
            setControllerVisibilityListener((ns1) null);
        }
    }

    public void setCustomErrorMessage(CharSequence charSequence) {
        ha1.s(this.v != null);
        this.M = charSequence;
        p();
    }

    public void setDefaultArtwork(Drawable drawable) {
        if (this.J != drawable) {
            this.J = drawable;
            q(false);
        }
    }

    public void setEnableComposeSurfaceSyncWorkaround(boolean z) {
        this.R = z;
    }

    public void setErrorMessageProvider(bf0 bf0Var) {
        if (bf0Var != null) {
            p();
        }
    }

    public void setFullscreenButtonClickListener(os1 os1Var) {
        wr1 wr1Var = this.w;
        wr1Var.getClass();
        wr1Var.setOnFullScreenModeChangedListener(this.l);
    }

    public void setFullscreenButtonState(boolean z) {
        wr1 wr1Var = this.w;
        wr1Var.getClass();
        wr1Var.o(z);
    }

    public void setImageDisplayMode(int i) {
        ha1.s(this.r != null);
        if (this.I != i) {
            this.I = i;
            r();
        }
    }

    public void setKeepContentOnPlayerReset(boolean z) {
        if (this.L != z) {
            this.L = z;
            q(false);
        }
    }

    public void setMediaRouteButtonViewProvider(dx2 dx2Var) {
        wr1 wr1Var = this.w;
        wr1Var.getClass();
        wr1Var.setMediaRouteButtonViewProvider(dx2Var);
    }

    /* JADX WARN: Code duplicated, block: B:72:0x00e9  */
    public void setPlayer(to1 to1Var) {
        boolean z = true;
        ha1.s(Looper.myLooper() == Looper.getMainLooper());
        ha1.h(to1Var == null || to1Var.u0() == Looper.getMainLooper());
        to1 to1Var2 = this.D;
        if (to1Var2 == to1Var) {
            return;
        }
        View view = this.o;
        ms1 ms1Var = this.l;
        if (to1Var2 != null) {
            to1Var2.e0(ms1Var);
            if (to1Var2.W(27)) {
                if (view instanceof TextureView) {
                    to1Var2.N((TextureView) view);
                } else if (view instanceof SurfaceView) {
                    to1Var2.i0((SurfaceView) view);
                }
            }
            Class cls = this.A;
            if (cls != null && cls.isAssignableFrom(to1Var2.getClass())) {
                try {
                    Method method = this.B;
                    method.getClass();
                    try {
                        method.invoke(to1Var2, null);
                    } catch (InvocationTargetException e) {
                        e = e;
                        q90.m(e);
                        return;
                    }
                } catch (IllegalAccessException | InvocationTargetException e2) {
                    e = e2;
                }
            }
        }
        SubtitleView subtitleView = this.t;
        if (subtitleView != null) {
            subtitleView.setCues(null);
        }
        this.D = to1Var;
        if (s()) {
            this.w.setPlayer(to1Var);
        }
        n();
        p();
        q(true);
        if (to1Var == null) {
            c();
            return;
        }
        if (to1Var.W(27)) {
            if (view instanceof TextureView) {
                to1Var.H0((TextureView) view);
            } else if (view instanceof SurfaceView) {
                to1Var.h0((SurfaceView) view);
            }
            if (to1Var.W(30)) {
                pw0 pw0Var = to1Var.E().a;
                int i = 0;
                loop0: while (true) {
                    if (i >= pw0Var.size()) {
                        z = false;
                        break;
                    }
                    if (((oq2) pw0Var.get(i)).b.c == 2) {
                        oq2 oq2Var = (oq2) pw0Var.get(i);
                        for (int i2 = 0; i2 < oq2Var.d.length; i2++) {
                            if (oq2Var.c(i2, false)) {
                                break loop0;
                            }
                        }
                    }
                    i++;
                }
                if (z) {
                    m();
                }
            } else {
                m();
            }
        }
        if (subtitleView != null && to1Var.W(28)) {
            subtitleView.setCues(to1Var.M().a);
        }
        to1Var.H(ms1Var);
        setImageOutput(to1Var);
        g(false);
    }

    public void setRepeatToggleModes(int i) {
        wr1 wr1Var = this.w;
        wr1Var.getClass();
        wr1Var.setRepeatToggleModes(i);
    }

    public void setResizeMode(int i) {
        AspectRatioFrameLayout aspectRatioFrameLayout = this.m;
        aspectRatioFrameLayout.getClass();
        aspectRatioFrameLayout.setResizeMode(i);
    }

    public void setShowBuffering(int i) {
        if (this.K != i) {
            this.K = i;
            n();
        }
    }

    public void setShowFastForwardButton(boolean z) {
        wr1 wr1Var = this.w;
        wr1Var.getClass();
        wr1Var.setShowFastForwardButton(z);
    }

    @Deprecated
    public void setShowMultiWindowTimeBar(boolean z) {
        wr1 wr1Var = this.w;
        wr1Var.getClass();
        wr1Var.setShowMultiWindowTimeBar(z);
    }

    public void setShowNextButton(boolean z) {
        wr1 wr1Var = this.w;
        wr1Var.getClass();
        wr1Var.setShowNextButton(z);
    }

    public void setShowPlayButtonIfPlaybackIsSuppressed(boolean z) {
        wr1 wr1Var = this.w;
        wr1Var.getClass();
        wr1Var.setShowPlayButtonIfPlaybackIsSuppressed(z);
    }

    public void setShowPreviousButton(boolean z) {
        wr1 wr1Var = this.w;
        wr1Var.getClass();
        wr1Var.setShowPreviousButton(z);
    }

    public void setShowRewindButton(boolean z) {
        wr1 wr1Var = this.w;
        wr1Var.getClass();
        wr1Var.setShowRewindButton(z);
    }

    public void setShowShuffleButton(boolean z) {
        wr1 wr1Var = this.w;
        wr1Var.getClass();
        wr1Var.setShowShuffleButton(z);
    }

    public void setShowSubtitleButton(boolean z) {
        wr1 wr1Var = this.w;
        wr1Var.getClass();
        wr1Var.setShowSubtitleButton(z);
    }

    public void setShowVrButton(boolean z) {
        wr1 wr1Var = this.w;
        wr1Var.getClass();
        wr1Var.setShowVrButton(z);
    }

    public void setShutterBackgroundColor(int i) {
        View view = this.n;
        if (view != null) {
            view.setBackgroundColor(i);
        }
    }

    public void setTimeBarScrubbingEnabled(boolean z) {
        wr1 wr1Var = this.w;
        wr1Var.getClass();
        wr1Var.setTimeBarScrubbingEnabled(z);
    }

    @Deprecated
    public void setUseArtwork(boolean z) {
        setArtworkDisplayMode(!z ? 1 : 0);
    }

    public void setUseController(boolean z) {
        boolean z2 = true;
        wr1 wr1Var = this.w;
        ha1.s((z && wr1Var == null) ? false : true);
        if (!z && !hasOnClickListeners()) {
            z2 = false;
        }
        setClickable(z2);
        if (this.E == z) {
            return;
        }
        this.E = z;
        if (s()) {
            wr1Var.setPlayer(this.D);
        } else if (wr1Var != null) {
            wr1Var.f();
            wr1Var.setPlayer(null);
        }
        o();
    }

    @Override // android.view.View
    public void setVisibility(int i) {
        super.setVisibility(i);
        View view = this.o;
        if (view instanceof SurfaceView) {
            view.setVisibility(i);
        }
    }

    public void setControllerVisibilityListener(ns1 ns1Var) {
        this.F = ns1Var;
        if (ns1Var != null) {
            setControllerVisibilityListener((vr1) null);
        }
    }
}
