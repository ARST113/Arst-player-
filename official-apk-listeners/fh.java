package defpackage;

import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableWrapper;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewOverlay;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.AccessibilityNodeInfo;
import com.google.android.material.focus.FocusRingDrawable;
import com.justplus.player.R;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.WeakHashMap;

/* JADX INFO: compiled from: r8-map-id-0cee80150bab987b3edc91bcfc1e04786a1418881e5c92a3b999d44d61001263 */
/* JADX INFO: loaded from: classes.dex */
public abstract class fh extends View {
    public static final /* synthetic */ int q1 = 0;
    public ValueAnimator A;
    public float A0;
    public ValueAnimator B;
    public float B0;
    public final int C;
    public ArrayList C0;
    public final int D;
    public int D0;
    public final int E;
    public int E0;
    public final int F;
    public float F0;
    public final int G;
    public int G0;
    public final int H;
    public float[] H0;
    public final int I;
    public int I0;
    public final int J;
    public int J0;
    public final int K;
    public int K0;
    public final int L;
    public int L0;
    public int M;
    public boolean M0;
    public final int N;
    public boolean N0;
    public int O;
    public ColorStateList O0;
    public int P;
    public ColorStateList P0;
    public int Q;
    public ColorStateList Q0;
    public int R;
    public ColorStateList R0;
    public int S;
    public ColorStateList S0;
    public int T;
    public final Path T0;
    public int U;
    public final RectF U0;
    public int V;
    public final RectF V0;
    public int W;
    public final RectF W0;
    public final RectF X0;
    public final Rect Y0;
    public final RectF Z0;
    public int a0;
    public final Rect a1;
    public int b0;
    public final Matrix b1;
    public int c0;
    public final ArrayList c1;
    public int d0;
    public Drawable d1;
    public int e0;
    public List e1;
    public boolean f0;
    public float f1;
    public Drawable g0;
    public float g1;
    public boolean h0;
    public ColorStateList h1;
    public Drawable i0;
    public ColorStateList i1;
    public boolean j0;
    public float j1;
    public ColorStateList k0;
    public int k1;
    public final Paint l;
    public Drawable l0;
    public final int l1;
    public final Paint m;
    public boolean m0;
    public final c4 m1;
    public final Paint n;
    public Drawable n0;
    public final bh n1;
    public final Paint o;
    public boolean o0;
    public final i4 o1;
    public final Paint p;
    public ColorStateList p0;
    public boolean p1;
    public final Paint q;
    public int q0;
    public final Paint r;
    public final int r0;
    public final dh s;
    public final int s0;
    public final AccessibilityManager t;
    public float t0;
    public ch u;
    public float u0;
    public final int v;
    public MotionEvent v0;
    public final ArrayList w;
    public final Rect w0;
    public final ArrayList x;
    public final ArrayList x0;
    public final ArrayList y;
    public List y0;
    public boolean z;
    public boolean z0;

    /* JADX WARN: Type inference failed for: r2v13, types: [bh] */
    public fh(ContextThemeWrapper contextThemeWrapper) {
        int i;
        super(ij0.L(R.attr.sliderStyle, R.style.Widget_MaterialComponents_Slider, contextThemeWrapper, null, new int[0]), null, R.attr.sliderStyle);
        this.w = new ArrayList();
        this.x = new ArrayList();
        this.y = new ArrayList();
        this.z = false;
        this.W = -1;
        this.a0 = -1;
        this.b0 = -1;
        this.f0 = false;
        this.h0 = false;
        this.j0 = false;
        this.m0 = false;
        this.o0 = false;
        this.w0 = new Rect();
        this.x0 = new ArrayList();
        this.y0 = new ArrayList();
        this.z0 = false;
        this.C0 = new ArrayList();
        this.D0 = -1;
        this.E0 = -1;
        this.F0 = 0.0f;
        this.G0 = 0;
        this.M0 = false;
        this.T0 = new Path();
        this.U0 = new RectF();
        this.V0 = new RectF();
        this.W0 = new RectF();
        this.X0 = new RectF();
        this.Y0 = new Rect();
        this.Z0 = new RectF();
        this.a1 = new Rect();
        this.b1 = new Matrix();
        this.c1 = new ArrayList();
        this.e1 = Collections.EMPTY_LIST;
        this.k1 = 0;
        final me2 me2Var = (me2) this;
        this.m1 = new c4(me2Var, (byte) 1);
        this.n1 = new ViewTreeObserver.OnGlobalLayoutListener() { // from class: bh
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public final void onGlobalLayout() {
                me2Var.G();
            }
        };
        this.o1 = new i4((Object) me2Var, (byte) 6);
        Context context = getContext();
        this.p1 = isShown();
        this.l = new Paint();
        this.m = new Paint();
        Paint paint = new Paint(1);
        this.n = paint;
        Paint.Style style = Paint.Style.FILL;
        paint.setStyle(style);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        Paint paint2 = new Paint(1);
        this.o = paint2;
        paint2.setStyle(style);
        Paint paint3 = new Paint();
        this.p = paint3;
        Paint.Style style2 = Paint.Style.STROKE;
        paint3.setStyle(style2);
        Paint.Cap cap = Paint.Cap.ROUND;
        paint3.setStrokeCap(cap);
        Paint paint4 = new Paint();
        this.q = paint4;
        paint4.setStyle(style2);
        paint4.setStrokeCap(cap);
        Paint paint5 = new Paint();
        this.r = paint5;
        paint5.setStyle(style);
        paint5.setStrokeCap(cap);
        this.D = context.getResources().getDimensionPixelSize(R.dimen.m3_slider_focus_ring_thumb_height_decrease);
        Resources resources = context.getResources();
        this.N = resources.getDimensionPixelSize(R.dimen.mtrl_slider_widget_height);
        int dimensionPixelOffset = resources.getDimensionPixelOffset(R.dimen.mtrl_slider_track_side_padding);
        this.E = dimensionPixelOffset;
        this.R = dimensionPixelOffset;
        this.F = resources.getDimensionPixelSize(R.dimen.mtrl_slider_thumb_radius);
        this.G = resources.getDimensionPixelSize(R.dimen.mtrl_slider_track_height);
        this.H = resources.getDimensionPixelSize(R.dimen.mtrl_slider_tick_radius);
        this.I = resources.getDimensionPixelSize(R.dimen.mtrl_slider_tick_radius);
        this.J = resources.getDimensionPixelSize(R.dimen.mtrl_slider_tick_min_spacing);
        this.s0 = resources.getDimensionPixelSize(R.dimen.mtrl_slider_label_padding);
        this.r0 = resources.getDimensionPixelOffset(R.dimen.m3_slider_track_icon_padding);
        this.L = resources.getDimensionPixelSize(R.dimen.mtrl_min_touch_target_size);
        ck0.e(context, null, R.attr.sliderStyle, R.style.Widget_MaterialComponents_Slider);
        int[] iArr = cw1.N;
        ck0.h(context, null, iArr, R.attr.sliderStyle, R.style.Widget_MaterialComponents_Slider, new int[0]);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(null, iArr, R.attr.sliderStyle, R.style.Widget_MaterialComponents_Slider);
        setOrientation(typedArrayObtainStyledAttributes.getInt(2, 0));
        this.v = typedArrayObtainStyledAttributes.getResourceId(11, R.style.Widget_MaterialComponents_Tooltip);
        this.A0 = typedArrayObtainStyledAttributes.getFloat(4, 0.0f);
        this.B0 = typedArrayObtainStyledAttributes.getFloat(5, 1.0f);
        setCentered(typedArrayObtainStyledAttributes.getBoolean(6, false));
        this.F0 = typedArrayObtainStyledAttributes.getFloat(3, 0.0f);
        this.G0 = typedArrayObtainStyledAttributes.getInt(7, 0);
        this.K = (int) Math.ceil(typedArrayObtainStyledAttributes.getDimension(12, ag.C(context)));
        boolean zHasValue = typedArrayObtainStyledAttributes.hasValue(28);
        int i2 = zHasValue ? 28 : 30;
        int i3 = zHasValue ? 28 : 29;
        ColorStateList colorStateListQ = ql.q(context, typedArrayObtainStyledAttributes, i2);
        setTrackInactiveTintList(colorStateListQ == null ? dz0.v(context, R.color.material_slider_inactive_track_color) : colorStateListQ);
        ColorStateList colorStateListQ2 = ql.q(context, typedArrayObtainStyledAttributes, i3);
        setTrackActiveTintList(colorStateListQ2 == null ? dz0.v(context, R.color.material_slider_active_track_color) : colorStateListQ2);
        ColorStateList colorStateListQ3 = ql.q(context, typedArrayObtainStyledAttributes, 13);
        setThumbTintList(colorStateListQ3 == null ? dz0.v(context, R.color.material_slider_thumb_color) : colorStateListQ3);
        if (typedArrayObtainStyledAttributes.hasValue(17)) {
            setThumbStrokeColor(ql.q(context, typedArrayObtainStyledAttributes, 17));
        }
        setThumbStrokeWidth(typedArrayObtainStyledAttributes.getDimension(18, 0.0f));
        ColorStateList colorStateListQ4 = ql.q(context, typedArrayObtainStyledAttributes, 8);
        setHaloTintList(colorStateListQ4 == null ? dz0.v(context, R.color.material_slider_halo_color) : colorStateListQ4);
        if (typedArrayObtainStyledAttributes.hasValue(26)) {
            i = typedArrayObtainStyledAttributes.getInt(26, -1);
        } else {
            i = typedArrayObtainStyledAttributes.getBoolean(27, true) ? 0 : 2;
        }
        this.I0 = i;
        boolean zHasValue2 = typedArrayObtainStyledAttributes.hasValue(21);
        int i4 = zHasValue2 ? 21 : 23;
        int i5 = zHasValue2 ? 21 : 22;
        ColorStateList colorStateListQ5 = ql.q(context, typedArrayObtainStyledAttributes, i4);
        setTickInactiveTintList(colorStateListQ5 == null ? dz0.v(context, R.color.material_slider_inactive_tick_marks_color) : colorStateListQ5);
        ColorStateList colorStateListQ6 = ql.q(context, typedArrayObtainStyledAttributes, i5);
        setTickActiveTintList(colorStateListQ6 == null ? dz0.v(context, R.color.material_slider_active_tick_marks_color) : colorStateListQ6);
        setThumbTrackGapSize(typedArrayObtainStyledAttributes.getDimensionPixelSize(19, 0));
        setTrackStopIndicatorSize(typedArrayObtainStyledAttributes.getDimensionPixelSize(41, 0));
        setTrackCornerSize(typedArrayObtainStyledAttributes.getDimensionPixelSize(31, -1));
        setTrackInsideCornerSize(typedArrayObtainStyledAttributes.getDimensionPixelSize(40, 0));
        setTrackIconActiveStart(ql.s(context, typedArrayObtainStyledAttributes, 35));
        setTrackIconActiveEnd(ql.s(context, typedArrayObtainStyledAttributes, 34));
        setTrackIconActiveColor(ql.q(context, typedArrayObtainStyledAttributes, 33));
        setTrackIconInactiveStart(ql.s(context, typedArrayObtainStyledAttributes, 38));
        setTrackIconInactiveEnd(ql.s(context, typedArrayObtainStyledAttributes, 37));
        setTrackIconInactiveColor(ql.q(context, typedArrayObtainStyledAttributes, 36));
        setTrackIconSize(typedArrayObtainStyledAttributes.getDimensionPixelSize(39, 0));
        int dimensionPixelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(16, 0) * 2;
        int dimensionPixelSize2 = typedArrayObtainStyledAttributes.getDimensionPixelSize(20, dimensionPixelSize);
        int dimensionPixelSize3 = typedArrayObtainStyledAttributes.getDimensionPixelSize(15, dimensionPixelSize);
        setThumbWidth(dimensionPixelSize2);
        setThumbHeight(dimensionPixelSize3);
        setHaloRadius(typedArrayObtainStyledAttributes.getDimensionPixelSize(9, 0));
        setThumbElevation(typedArrayObtainStyledAttributes.getDimension(14, 0.0f));
        setTrackHeight(typedArrayObtainStyledAttributes.getDimensionPixelSize(32, 0));
        setTickActiveRadius(typedArrayObtainStyledAttributes.getDimensionPixelSize(24, this.c0 / 2));
        setTickInactiveRadius(typedArrayObtainStyledAttributes.getDimensionPixelSize(25, this.c0 / 2));
        setLabelBehavior(typedArrayObtainStyledAttributes.getInt(10, 0));
        if (!typedArrayObtainStyledAttributes.getBoolean(0, true)) {
            setEnabled(false);
        }
        setValues(Float.valueOf(this.A0));
        typedArrayObtainStyledAttributes.recycle();
        setFocusable(true);
        setClickable(true);
        this.C = ViewConfiguration.get(context).getScaledTouchSlop();
        dh dhVar = new dh(me2Var);
        this.s = dhVar;
        lw2.n(this, dhVar);
        AccessibilityManager accessibilityManager = (AccessibilityManager) getContext().getSystemService("accessibility");
        this.t = accessibilityManager;
        if (Build.VERSION.SDK_INT >= 29) {
            this.l1 = accessibilityManager.getRecommendedTimeoutMillis(10000, 6);
        } else {
            this.l1 = 120000;
        }
    }

    /* JADX WARN: Code duplicated, block: B:19:0x00a2  */
    /* JADX WARN: Code duplicated, block: B:22:0x00bf  */
    /* JADX WARN: Code duplicated, block: B:23:0x00c1  */
    /* JADX WARN: Code duplicated, block: B:25:0x00c7 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:26:0x00c8  */
    public final void A(hp2 hp2Var, float f) {
        int iV;
        int intrinsicWidth;
        int iD;
        int intrinsicHeight;
        int iD2;
        Rect rect;
        ViewGroup viewGroupR;
        ViewOverlay overlay;
        String str = String.format(((float) ((int) f)) == f ? "%.0f" : "%.2f", Float.valueOf(f));
        if (!TextUtils.equals(hp2Var.R, str)) {
            hp2Var.R = str;
            hp2Var.U.e = true;
            hp2Var.invalidateSelf();
        }
        boolean zT = t();
        int i = this.R;
        int i2 = this.s0;
        if (zT) {
            iV = (i + ((int) (v(f) * this.L0))) - (hp2Var.getIntrinsicHeight() / 2);
            intrinsicWidth = hp2Var.getIntrinsicHeight() + iV;
            if (s()) {
                iD = d() - ((this.T / 2) + i2);
                intrinsicHeight = hp2Var.getIntrinsicWidth();
            } else {
                iD2 = (this.T / 2) + i2 + d();
                iD = hp2Var.getIntrinsicWidth() + iD2;
            }
            rect = this.Y0;
            rect.set(iV, iD2, intrinsicWidth, iD);
            if (t()) {
                RectF rectF = new RectF(rect);
                this.b1.mapRect(rectF);
                rectF.round(rect);
            }
            r60.b(nt2.r(this), this, rect);
            hp2Var.setBounds(rect);
            viewGroupR = nt2.r(this);
            if (viewGroupR == null) {
                overlay = null;
            } else {
                overlay = viewGroupR.getOverlay();
            }
            if (overlay == null) {
                return;
            }
            overlay.add(hp2Var);
        }
        iV = (i + ((int) (v(f) * this.L0))) - (hp2Var.getIntrinsicWidth() / 2);
        intrinsicWidth = hp2Var.getIntrinsicWidth() + iV;
        iD = d() - ((this.T / 2) + i2);
        intrinsicHeight = hp2Var.getIntrinsicHeight();
        iD2 = iD - intrinsicHeight;
        rect = this.Y0;
        rect.set(iV, iD2, intrinsicWidth, iD);
        if (t()) {
            RectF rectF2 = new RectF(rect);
            this.b1.mapRect(rectF2);
            rectF2.round(rect);
        }
        r60.b(nt2.r(this), this, rect);
        hp2Var.setBounds(rect);
        viewGroupR = nt2.r(this);
        if (viewGroupR == null) {
            overlay = null;
        } else {
            overlay = viewGroupR.getOverlay();
        }
        if (overlay == null) {
            return;
        }
        overlay.add(hp2Var);
    }

    public final void B(ArrayList arrayList) {
        ViewGroup viewGroupR;
        int resourceId;
        ViewGroup viewGroupR2;
        if (arrayList.isEmpty()) {
            bl.d("At least one value must be set");
            return;
        }
        Collections.sort(arrayList);
        if (this.C0.size() == arrayList.size() && this.C0.equals(arrayList)) {
            return;
        }
        this.C0 = arrayList;
        this.N0 = true;
        ArrayList arrayList2 = this.c1;
        byte b = 0;
        if (arrayList2.size() != this.C0.size()) {
            arrayList2.clear();
            for (int i = 0; i < this.C0.size(); i++) {
                n61 n61Var = new n61();
                n61Var.w();
                n61Var.t(getThumbTintList());
                qc0 qc0Var = new qc0(b);
                qc0 qc0Var2 = new qc0(b);
                qc0 qc0Var3 = new qc0(b);
                qc0 qc0Var4 = new qc0(b);
                float f = this.S / 2.0f;
                ha1 ha1VarH = gj0.h(0);
                k0 k0Var = new k0(f);
                k0 k0Var2 = new k0(f);
                k0 k0Var3 = new k0(f);
                k0 k0Var4 = new k0(f);
                hc2 hc2Var = new hc2();
                hc2Var.a = ha1VarH;
                hc2Var.b = ha1VarH;
                hc2Var.c = ha1VarH;
                hc2Var.d = ha1VarH;
                hc2Var.e = k0Var;
                hc2Var.f = k0Var2;
                hc2Var.g = k0Var3;
                hc2Var.h = k0Var4;
                hc2Var.i = qc0Var;
                hc2Var.j = qc0Var2;
                hc2Var.k = qc0Var3;
                hc2Var.l = qc0Var4;
                n61Var.setShapeAppearanceModel(hc2Var);
                n61Var.setBounds(0, 0, this.S, this.T);
                n61Var.s(getThumbElevation());
                n61Var.A(getThumbStrokeWidth());
                n61Var.z(getThumbStrokeColor());
                n61Var.setState(getDrawableState());
                arrayList2.add(n61Var);
            }
        }
        this.E0 = 0;
        F();
        ArrayList arrayList3 = this.w;
        if (arrayList3.size() > this.C0.size()) {
            List<hp2> listSubList = arrayList3.subList(this.C0.size(), arrayList3.size());
            for (hp2 hp2Var : listSubList) {
                if (isAttachedToWindow() && (viewGroupR2 = nt2.r(this)) != null) {
                    viewGroupR2.getOverlay().remove(hp2Var);
                    viewGroupR2.removeOnLayoutChangeListener(hp2Var.V);
                }
            }
            listSubList.clear();
        }
        while (arrayList3.size() < this.C0.size()) {
            Context context = getContext();
            int i2 = this.v;
            hp2 hp2Var2 = new hp2(context, i2);
            TypedArray typedArrayM = ck0.M(hp2Var2.S, null, cw1.T, 0, i2, new int[0]);
            Context context2 = hp2Var2.S;
            hp2Var2.c0 = context2.getResources().getDimensionPixelSize(R.dimen.mtrl_tooltip_arrowSize);
            boolean z = typedArrayM.getBoolean(8, true);
            hp2Var2.b0 = z;
            if (z) {
                gc2 gc2VarL = hp2Var2.k().l();
                gc2VarL.k = hp2Var2.G();
                hp2Var2.setShapeAppearanceModel(gc2VarL.a());
            } else {
                hp2Var2.c0 = 0;
            }
            CharSequence text = typedArrayM.getText(6);
            boolean zEquals = TextUtils.equals(hp2Var2.R, text);
            xm2 xm2Var = hp2Var2.U;
            if (!zEquals) {
                hp2Var2.R = text;
                xm2Var.e = true;
                hp2Var2.invalidateSelf();
            }
            um2 um2Var = (!typedArrayM.hasValue(0) || (resourceId = typedArrayM.getResourceId(0, 0)) == 0) ? null : new um2(context2, resourceId);
            if (um2Var != null && typedArrayM.hasValue(1)) {
                um2Var.k = ql.q(context2, typedArrayM, 1);
            }
            xm2Var.c(um2Var, context2);
            hp2Var2.t(ColorStateList.valueOf(typedArrayM.getColor(7, eu.d(eu.f(sj.J(context2, ag.D(context2, R.attr.colorOnBackground, hp2.class.getCanonicalName())), 153), eu.f(sj.J(context2, ag.D(context2, android.R.attr.colorBackground, hp2.class.getCanonicalName())), 229)))));
            hp2Var2.y(ColorStateList.valueOf(sj.J(context2, ag.D(context2, R.attr.colorSurface, hp2.class.getCanonicalName()))));
            hp2Var2.X = typedArrayM.getDimensionPixelSize(2, 0);
            hp2Var2.Y = typedArrayM.getDimensionPixelSize(4, 0);
            hp2Var2.Z = typedArrayM.getDimensionPixelSize(5, 0);
            hp2Var2.a0 = typedArrayM.getDimensionPixelSize(3, 0);
            typedArrayM.recycle();
            arrayList3.add(hp2Var2);
            if (isAttachedToWindow() && (viewGroupR = nt2.r(this)) != null) {
                int[] iArr = new int[2];
                viewGroupR.getLocationOnScreen(iArr);
                hp2Var2.d0 = iArr[0];
                viewGroupR.getWindowVisibleDisplayFrame(hp2Var2.W);
                viewGroupR.addOnLayoutChangeListener(hp2Var2.V);
            }
        }
        int i3 = arrayList3.size() == 1 ? 0 : 1;
        Iterator it = arrayList3.iterator();
        while (it.hasNext()) {
            ((hp2) it.next()).A(i3);
        }
        for (qk1 qk1Var : this.x) {
            Iterator it2 = this.C0.iterator();
            while (it2.hasNext()) {
                qk1Var.a(this, ((Float) it2.next()).floatValue(), false);
            }
        }
        postInvalidate();
    }

    public final boolean C(int i, float f) {
        ViewParent parent;
        this.E0 = i;
        if (Math.abs(f - ((Float) this.C0.get(i)).floatValue()) < 1.0E-4d) {
            return false;
        }
        float minSeparation = getMinSeparation();
        if (this.k1 == 0) {
            if (minSeparation == 0.0f) {
                minSeparation = 0.0f;
            } else {
                float f2 = (minSeparation - this.R) / this.L0;
                float f3 = this.A0;
                minSeparation = ((f3 - this.B0) * f2) + f3;
            }
        }
        if (s() || t()) {
            minSeparation = -minSeparation;
        }
        int i2 = i + 1;
        int i3 = i - 1;
        this.C0.set(i, Float.valueOf(bq0.c(f, i3 < 0 ? this.A0 : minSeparation + ((Float) this.C0.get(i3)).floatValue(), i2 >= this.C0.size() ? this.B0 : ((Float) this.C0.get(i2)).floatValue() - minSeparation)));
        Iterator it = this.x.iterator();
        while (it.hasNext()) {
            ((qk1) it.next()).a(this, ((Float) this.C0.get(i)).floatValue(), true);
        }
        AccessibilityManager accessibilityManager = this.t;
        if (accessibilityManager != null && accessibilityManager.isEnabled()) {
            Runnable runnable = this.u;
            if (runnable == null) {
                this.u = new ch(this);
            } else {
                removeCallbacks(runnable);
            }
            ch chVar = this.u;
            chVar.m = i;
            postDelayed(chVar, 200L);
            dh dhVar = this.s;
            View view = dhVar.i;
            if (i != Integer.MIN_VALUE && dhVar.h.isEnabled() && (parent = view.getParent()) != null) {
                AccessibilityEvent accessibilityEventK = dhVar.k(i, 2048);
                accessibilityEventK.setContentChangeTypes(0);
                parent.requestSendAccessibilityEvent(view, accessibilityEventK);
            }
        }
        return true;
    }

    public final void D() {
        double dRound;
        float f = this.j1;
        float f2 = this.F0;
        if (f2 > 0.0f) {
            int i = (int) ((this.B0 - this.A0) / f2);
            dRound = ((double) Math.round(f * i)) / ((double) i);
        } else {
            dRound = f;
        }
        if (s() || t()) {
            dRound = 1.0d - dRound;
        }
        float f3 = this.B0;
        float f4 = this.A0;
        C(this.D0, (float) ((dRound * ((double) (f3 - f4))) + ((double) f4)));
    }

    public final void E(int i, Rect rect) {
        int iV = this.R + ((int) (v(getValues().get(i).floatValue()) * this.L0));
        int iD = d();
        int iMax = Math.max(this.K, this.L) / 2;
        int iMax2 = Math.max(this.S / 2, iMax);
        int iMax3 = Math.max(this.T / 2, iMax);
        RectF rectF = new RectF(iV - iMax2, iD - iMax3, iV + iMax2, iD + iMax3);
        if (t()) {
            this.b1.mapRect(rectF);
        }
        rect.set((int) rectF.left, (int) rectF.top, (int) rectF.right, (int) rectF.bottom);
    }

    public final void F() {
        float f;
        float f2;
        float f3;
        float f4;
        RippleDrawable rippleDrawableN;
        float fV = (v(((Float) this.C0.get(this.E0)).floatValue()) * this.L0) + this.R;
        int iD = d();
        if (n() != null && getMeasuredWidth() > 0 && (rippleDrawableN = n()) != null) {
            int i = this.U;
            float f5 = i;
            float[] fArr = {fV - f5, iD - i, f5 + fV, i + iD};
            if (t()) {
                this.b1.mapPoints(fArr);
            }
            rippleDrawableN.setHotspotBounds((int) fArr[0], (int) fArr[1], (int) fArr[2], (int) fArr[3]);
        }
        float f6 = iD;
        FocusRingDrawable focusRingDrawableC = FocusRingDrawable.c(getBackground());
        if (focusRingDrawableC != null) {
            float dimensionPixelOffset = getResources().getDimensionPixelOffset(R.dimen.m3_slider_focus_ring_padding);
            float f7 = (dimensionPixelOffset * 2.0f) + (this.S / 2.0f);
            float f8 = (this.T / 2.0f) + dimensionPixelOffset;
            if (t()) {
                f = f6 - f8;
                float f9 = f6 + f8;
                f2 = fV - f7;
                f3 = fV + f7;
                f4 = f9;
            } else {
                f = fV - f7;
                f4 = fV + f7;
                f2 = f6 - f8;
                f3 = f6 + f8;
            }
            focusRingDrawableC.mutate();
            int i2 = (int) f;
            int i3 = (int) f2;
            int i4 = (int) f4;
            int i5 = (int) f3;
            el0 el0Var = focusRingDrawableC.z;
            if (el0Var.w == null) {
                el0Var.w = new Rect();
            }
            focusRingDrawableC.z.w.set(i2, i3, i4, i5);
        }
    }

    public final void G() {
        float f;
        boolean zT = t();
        boolean zS = s();
        float f2 = 0.5f;
        if (zT && zS) {
            f = 0.5f;
            f2 = -0.2f;
        } else {
            f = 1.2f;
            if (zT) {
                f2 = 1.2f;
                f = 0.5f;
            }
        }
        for (hp2 hp2Var : this.w) {
            hp2Var.g0 = f2;
            hp2Var.h0 = f;
            hp2Var.invalidateSelf();
        }
        int i = this.P;
        if (i == 0 || i == 1) {
            if (this.D0 == -1 || !isEnabled()) {
                l();
                return;
            } else {
                k(false);
                return;
            }
        }
        if (i == 2) {
            l();
            return;
        }
        if (i != 3) {
            q90.g(this.P, "Unexpected labelBehavior: ");
            return;
        }
        if (isEnabled()) {
            Rect rect = new Rect();
            nt2.r(this).getHitRect(rect);
            if (getLocalVisibleRect(rect)) {
                if (Build.VERSION.SDK_INT >= 24 ? this.p1 : isShown()) {
                    k(true);
                    return;
                }
            }
        }
        l();
    }

    public final void H() {
        if (this.V > 0 && this.d1 == null && this.e1.isEmpty()) {
            int i = this.S;
            this.W = i;
            this.b0 = this.T;
            this.a0 = this.V;
            int iRound = Math.round(i * 0.5f);
            FocusRingDrawable focusRingDrawableC = FocusRingDrawable.c(getBackground());
            z(iRound, (focusRingDrawableC == null || !focusRingDrawableC.z.c) ? -1 : this.T - this.D, Integer.valueOf(this.D0));
        }
    }

    public final void I() {
        int iMin;
        Q();
        float f = this.F0;
        if (f <= 0.0f) {
            J(this.G0);
            return;
        }
        int i = this.I0;
        if (i != 0) {
            iMin = 0;
            if (i == 1) {
                int i2 = (int) (((this.B0 - this.A0) / f) + 1.0f);
                if (i2 <= (this.L0 / this.J) + 1) {
                    iMin = i2;
                }
            } else if (i != 2) {
                wb1.g(this.I0, "Unexpected tickVisibilityMode: ");
                return;
            }
        } else {
            iMin = Math.min((int) (((this.B0 - this.A0) / f) + 1.0f), (this.L0 / this.J) + 1);
        }
        J(iMin);
    }

    public final void J(int i) {
        if (i == 0) {
            this.H0 = null;
            return;
        }
        float[] fArr = this.H0;
        if (fArr == null || fArr.length != i * 2) {
            this.H0 = new float[i * 2];
        }
        float f = this.L0 / (i - 1);
        float fD = d();
        for (int i2 = 0; i2 < i * 2; i2 += 2) {
            float[] fArr2 = this.H0;
            fArr2[i2] = ((i2 / 2.0f) * f) + this.R;
            fArr2[i2 + 1] = fD;
        }
        if (t()) {
            this.b1.mapPoints(this.H0);
        }
    }

    /* JADX WARN: Code duplicated, block: B:19:0x004b  */
    public final void K(Canvas canvas, Paint paint, RectF rectF, float f, int i) {
        float fMax;
        if (rectF.isEmpty()) {
            return;
        }
        if (this.C0.isEmpty() || this.V <= 0) {
            fMax = f;
        } else {
            float fS = S(((Float) this.C0.get((s() || t()) ? this.C0.size() - 1 : 0)).floatValue()) - this.R;
            if (fS < f) {
                fMax = Math.max(fS, this.e0);
            } else {
                fMax = f;
            }
        }
        if (!this.C0.isEmpty() && this.V > 0) {
            float fS2 = S(((Float) this.C0.get((s() || t()) ? 0 : this.C0.size() - 1)).floatValue()) - this.R;
            float f2 = this.L0;
            if (fS2 > f2 - f) {
                f = Math.max(f2 - fS2, this.e0);
            }
        }
        int iU = lf2.u(i);
        if (iU == 1) {
            f = this.e0;
        } else if (iU == 2) {
            fMax = this.e0;
        } else if (iU == 3) {
            fMax = this.e0;
            f = fMax;
        }
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeCap(Paint.Cap.BUTT);
        if (this.V > 0) {
            paint.setAntiAlias(true);
        }
        RectF rectF2 = new RectF(rectF);
        boolean zT = t();
        Matrix matrix = this.b1;
        if (zT) {
            matrix.mapRect(rectF2);
        }
        Path path = this.T0;
        path.reset();
        if (rectF.width() >= fMax + f) {
            path.addRoundRect(rectF2, t() ? new float[]{fMax, fMax, fMax, fMax, f, f, f, f} : new float[]{fMax, fMax, f, f, f, f, fMax, fMax}, Path.Direction.CW);
            canvas.drawPath(path, paint);
            return;
        }
        float fMin = Math.min(fMax, f);
        float fMax2 = Math.max(fMax, f);
        canvas.save();
        path.addRoundRect(rectF2, fMin, fMin, Path.Direction.CW);
        canvas.clipPath(path);
        int iU2 = lf2.u(i);
        RectF rectF3 = this.X0;
        if (iU2 == 1) {
            float f3 = rectF.left;
            rectF3.set(f3, rectF.top, (2.0f * fMax2) + f3, rectF.bottom);
        } else if (iU2 != 2) {
            rectF3.set(rectF.centerX() - fMax2, rectF.top, rectF.centerX() + fMax2, rectF.bottom);
        } else {
            float f4 = rectF.right;
            rectF3.set(f4 - (2.0f * fMax2), rectF.top, f4, rectF.bottom);
        }
        if (t()) {
            matrix.mapRect(rectF3);
        }
        canvas.drawRoundRect(rectF3, fMax2, fMax2, paint);
        canvas.restore();
    }

    public final void L() {
        Drawable drawableMutate = this.i0;
        if (drawableMutate != null) {
            boolean z = this.j0;
            if (!z && this.k0 != null) {
                drawableMutate = drawableMutate.mutate();
                this.i0 = drawableMutate;
                z = true;
                this.j0 = true;
            }
            if (z) {
                drawableMutate.setTintList(this.k0);
            }
        }
    }

    public final void M() {
        Drawable drawableMutate = this.g0;
        if (drawableMutate != null) {
            boolean z = this.h0;
            if (!z && this.k0 != null) {
                drawableMutate = drawableMutate.mutate();
                this.g0 = drawableMutate;
                z = true;
                this.h0 = true;
            }
            if (z) {
                drawableMutate.setTintList(this.k0);
            }
        }
    }

    public final void N() {
        Drawable drawableMutate = this.n0;
        if (drawableMutate != null) {
            boolean z = this.o0;
            if (!z && this.p0 != null) {
                drawableMutate = drawableMutate.mutate();
                this.n0 = drawableMutate;
                z = true;
                this.o0 = true;
            }
            if (z) {
                drawableMutate.setTintList(this.p0);
            }
        }
    }

    public final void O() {
        Drawable drawableMutate = this.l0;
        if (drawableMutate != null) {
            boolean z = this.m0;
            if (!z && this.p0 != null) {
                drawableMutate = drawableMutate.mutate();
                this.l0 = drawableMutate;
                z = true;
                this.m0 = true;
            }
            if (z) {
                drawableMutate.setTintList(this.p0);
            }
        }
    }

    public final void P(boolean z) {
        int paddingTop;
        int paddingBottom;
        boolean z2;
        if (t()) {
            paddingTop = getPaddingLeft();
            paddingBottom = getPaddingRight();
        } else {
            paddingTop = getPaddingTop();
            paddingBottom = getPaddingBottom();
        }
        int i = paddingBottom + paddingTop;
        int iMax = Math.max(this.N, Math.max(this.Q + i, this.T + i));
        boolean z3 = true;
        if (iMax == this.O) {
            z2 = false;
        } else {
            this.O = iMax;
            z2 = true;
        }
        int iMax2 = Math.max(Math.max(Math.max((this.S / 2) - this.F, 0), Math.max((this.Q - this.G) / 2, 0)), Math.max(Math.max(this.J0 - this.H, 0), Math.max(this.K0 - this.I, 0))) + this.E;
        if (this.R == iMax2) {
            z3 = false;
        } else {
            this.R = iMax2;
            if (isLaidOut()) {
                this.L0 = Math.max((t() ? getHeight() : getWidth()) - (this.R * 2), 0);
                I();
            }
        }
        if (t()) {
            float fD = d();
            Matrix matrix = this.b1;
            matrix.reset();
            matrix.setRotate(90.0f, fD, fD);
        }
        if (z2 || z) {
            requestLayout();
        } else if (z3) {
            postInvalidate();
        }
    }

    public final void Q() {
        if (this.N0) {
            float f = this.A0;
            float f2 = this.B0;
            if (f >= f2) {
                throw new IllegalStateException("valueFrom(" + f + ") must be smaller than valueTo(" + f2 + ")");
            }
            for (Float f3 : this.C0) {
                if (f3.floatValue() < this.A0 || f3.floatValue() > this.B0) {
                    throw new IllegalStateException("Slider value(" + f3 + ") must be greater or equal to valueFrom(" + this.A0 + "), and lower or equal to valueTo(" + this.B0 + ")");
                }
                if (this.F0 > 0.0f && !R(f3.floatValue())) {
                    float f4 = this.A0;
                    float f5 = this.F0;
                    throw new IllegalStateException("Value(" + f3 + ") must be equal to valueFrom(" + f4 + ") plus a multiple of stepSize(" + f5 + ") when using stepSize(" + f5 + ")");
                }
            }
            if (this.F0 > 0.0f && !R(this.B0)) {
                throw new IllegalStateException("The stepSize(" + this.F0 + ") must be 0, or a factor of the valueFrom(" + this.A0 + ")-valueTo(" + this.B0 + ") range");
            }
            float minSeparation = getMinSeparation();
            if (minSeparation < 0.0f) {
                throw new IllegalStateException("minSeparation(" + minSeparation + ") must be greater or equal to 0");
            }
            float f6 = this.F0;
            if (f6 > 0.0f && minSeparation > 0.0f) {
                if (this.k1 != 1) {
                    throw new IllegalStateException("minSeparation(" + minSeparation + ") cannot be set as a dimension when using stepSize(" + f6 + ")");
                }
                if (minSeparation < f6 || !p(minSeparation)) {
                    float f7 = this.F0;
                    throw new IllegalStateException("minSeparation(" + minSeparation + ") must be greater or equal and a multiple of stepSize(" + f7 + ") when using stepSize(" + f7 + ")");
                }
            }
            float f8 = this.F0;
            if (f8 != 0.0f) {
                if (((int) f8) != f8) {
                    Log.w("fh", "Floating point value used for stepSize(" + f8 + "). Using floats can have rounding errors which may result in incorrect values. Instead, consider using integers with a custom LabelFormatter to display the value correctly.");
                }
                float f9 = this.A0;
                if (((int) f9) != f9) {
                    Log.w("fh", "Floating point value used for valueFrom(" + f9 + "). Using floats can have rounding errors which may result in incorrect values. Instead, consider using integers with a custom LabelFormatter to display the value correctly.");
                }
                float f10 = this.B0;
                if (((int) f10) != f10) {
                    Log.w("fh", "Floating point value used for valueTo(" + f10 + "). Using floats can have rounding errors which may result in incorrect values. Instead, consider using integers with a custom LabelFormatter to display the value correctly.");
                }
            }
            this.N0 = false;
        }
    }

    public final boolean R(float f) {
        return p(new BigDecimal(Float.toString(f)).subtract(new BigDecimal(Float.toString(this.A0)), MathContext.DECIMAL64).doubleValue());
    }

    public final float S(float f) {
        return (v(f) * this.L0) + this.R;
    }

    public final void a(int i, Drawable drawable) {
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();
        if (intrinsicWidth == -1 && intrinsicHeight == -1) {
            drawable.setBounds(0, 0, i, this.T);
        } else {
            float fMax = Math.max(i, this.T) / Math.max(intrinsicWidth, intrinsicHeight);
            drawable.setBounds(0, 0, (int) (intrinsicWidth * fMax), (int) (intrinsicHeight * fMax));
        }
    }

    public final void b(Canvas canvas, RectF rectF, Drawable drawable, boolean z) {
        if (drawable != null) {
            int i = this.q0;
            float f = rectF.right - rectF.left;
            int i2 = this.r0;
            float f2 = (i2 * 2) + i;
            RectF rectF2 = this.Z0;
            if (f >= f2) {
                float f3 = z ^ (s() || t()) ? rectF.left + i2 : (rectF.right - i2) - i;
                float f4 = i;
                float fD = d() - (f4 / 2.0f);
                rectF2.set(f3, fD, f3 + f4, f4 + fD);
            } else {
                rectF2.setEmpty();
            }
            if (rectF2.isEmpty()) {
                return;
            }
            if (t()) {
                this.b1.mapRect(rectF2);
            }
            Rect rect = this.a1;
            rectF2.round(rect);
            drawable.setBounds(rect);
            drawable.draw(canvas);
        }
    }

    public final int c(int i) {
        if (!this.z0 || i != this.D0 || this.d1 != null || !this.e1.isEmpty()) {
            return this.V;
        }
        return this.V - ((this.S - Math.round(this.S * 0.5f)) / 2);
    }

    public final int d() {
        int i = this.O / 2;
        int i2 = this.P;
        int intrinsicHeight = 0;
        if (i2 == 1 || i2 == 3) {
            ArrayList arrayList = this.w;
            if (!arrayList.isEmpty()) {
                intrinsicHeight = ((hp2) arrayList.get(0)).getIntrinsicHeight();
            }
        }
        return i + intrinsicHeight;
    }

    @Override // android.view.View
    public final boolean dispatchHoverEvent(MotionEvent motionEvent) {
        return this.s.m(motionEvent) || super.dispatchHoverEvent(motionEvent);
    }

    @Override // android.view.View
    public final void drawableStateChanged() {
        super.drawableStateChanged();
        this.l.setColor(o(this.S0));
        this.m.setColor(o(this.R0));
        this.p.setColor(o(this.Q0));
        this.q.setColor(o(this.P0));
        this.r.setColor(o(this.Q0));
        for (hp2 hp2Var : this.w) {
            if (hp2Var.isStateful()) {
                hp2Var.setState(getDrawableState());
            }
        }
        int i = 0;
        while (true) {
            ArrayList arrayList = this.c1;
            if (i >= arrayList.size()) {
                int iO = o(this.O0);
                Paint paint = this.o;
                paint.setColor(iO);
                paint.setAlpha(63);
                return;
            }
            if (((n61) arrayList.get(i)).isStateful()) {
                ((n61) arrayList.get(i)).setState(getDrawableState());
            }
            i++;
        }
    }

    public final ValueAnimator e(boolean z) {
        int iB;
        TimeInterpolator timeInterpolatorW;
        float fFloatValue = z ? 0.0f : 1.0f;
        ValueAnimator valueAnimator = z ? this.B : this.A;
        if (valueAnimator != null && valueAnimator.isRunning()) {
            fFloatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
            valueAnimator.cancel();
        }
        byte b = 0;
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(fFloatValue, z ? 1.0f : 0.0f);
        if (z) {
            iB = ag.B(getContext(), R.attr.motionDurationMedium4, 83);
            timeInterpolatorW = gj0.w(getContext(), R.attr.motionEasingEmphasizedInterpolator, x6.e);
        } else {
            iB = ag.B(getContext(), R.attr.motionDurationShort3, 117);
            timeInterpolatorW = gj0.w(getContext(), R.attr.motionEasingEmphasizedAccelerateInterpolator, x6.c);
        }
        valueAnimatorOfFloat.setDuration(iB);
        valueAnimatorOfFloat.setInterpolator(timeInterpolatorW);
        valueAnimatorOfFloat.addUpdateListener(new ah(this, b));
        return valueAnimatorOfFloat;
    }

    public final void f(float f, float f2, float f3, float f4, Canvas canvas, RectF rectF, int i, int i2) {
        if (f2 - f > getTrackCornerSize() - i2) {
            rectF.set(f, f3, f2, f4);
        } else {
            rectF.setEmpty();
        }
        K(canvas, this.l, rectF, getTrackCornerSize(), i);
    }

    public final void g(Canvas canvas, float f, float f2) {
        for (int i = 0; i < this.C0.size(); i++) {
            float fS = S(((Float) this.C0.get(i)).floatValue());
            float fC = (this.S / 2.0f) + c(i);
            if (f >= fS - fC && f <= fS + fC) {
                return;
            }
        }
        boolean zT = t();
        Paint paint = this.r;
        if (zT) {
            canvas.drawPoint(f2, f, paint);
        } else {
            canvas.drawPoint(f, f2, paint);
        }
    }

    public final int getAccessibilityFocusedVirtualViewId() {
        return this.s.k;
    }

    public float getMinSeparation() {
        return 0.0f;
    }

    public abstract float getThumbElevation();

    public abstract int getThumbRadius();

    public abstract ColorStateList getThumbStrokeColor();

    public abstract float getThumbStrokeWidth();

    public abstract ColorStateList getThumbTintList();

    public abstract int getTrackCornerSize();

    public List<Float> getValues() {
        return new ArrayList(this.C0);
    }

    public final void h(Canvas canvas, int i, int i2, float f, Drawable drawable) {
        canvas.save();
        if (t()) {
            canvas.concat(this.b1);
        }
        canvas.translate((this.R + ((int) (v(f) * i))) - (drawable.getBounds().width() / 2.0f), i2 - (drawable.getBounds().height() / 2.0f));
        drawable.draw(canvas);
        canvas.restore();
    }

    /* JADX WARN: Code duplicated, block: B:23:0x0061  */
    public final void i(int i, int i2, Canvas canvas, Paint paint) {
        while (i < i2) {
            boolean zT = t();
            float[] fArr = this.H0;
            float f = zT ? fArr[i + 1] : fArr[i];
            int i3 = 0;
            while (true) {
                if (i3 >= this.C0.size()) {
                    if (!this.f0) {
                        float[] fArr2 = this.H0;
                        canvas.drawPoint(fArr2[i], fArr2[i + 1], paint);
                        break;
                        break;
                    }
                    float f2 = ((this.R * 2) + this.L0) / 2.0f;
                    float f3 = this.V;
                    if (f >= f2 - f3 && f <= f2 + f3) {
                        break;
                    }
                    float[] fArr3 = this.H0;
                    canvas.drawPoint(fArr3[i], fArr3[i + 1], paint);
                    break;
                }
                float fS = S(((Float) this.C0.get(i3)).floatValue());
                float fC = (this.S / 2.0f) + c(i3);
                if (f >= fS - fC && f <= fS + fC) {
                    break;
                } else {
                    i3++;
                }
            }
            i += 2;
        }
    }

    public final void j(Canvas canvas, RectF rectF, RectF rectF2) {
        if (this.g0 == null && this.i0 == null && this.l0 == null && this.n0 == null) {
            return;
        }
        if (this.C0.size() > 1) {
            Log.w("fh", "Track icons can only be used when only 1 thumb is present.");
        }
        b(canvas, rectF, this.g0, true);
        b(canvas, rectF2, this.l0, true);
        b(canvas, rectF, this.i0, false);
        b(canvas, rectF2, this.n0, false);
    }

    public final void k(boolean z) {
        if (!this.z) {
            this.z = true;
            ValueAnimator valueAnimatorE = e(true);
            this.A = valueAnimatorE;
            this.B = null;
            valueAnimatorE.start();
        }
        ArrayList arrayList = this.w;
        Iterator it = arrayList.iterator();
        if (z) {
            for (int i = 0; i < this.C0.size() && it.hasNext(); i++) {
                if (i != this.E0) {
                    A((hp2) it.next(), ((Float) this.C0.get(i)).floatValue());
                }
            }
        }
        if (!it.hasNext()) {
            throw new IllegalStateException(String.format("Not enough labels(%d) to display all the values(%d)", Integer.valueOf(arrayList.size()), Integer.valueOf(this.C0.size())));
        }
        A((hp2) it.next(), ((Float) this.C0.get(this.E0)).floatValue());
    }

    public final void l() {
        if (this.z) {
            this.z = false;
            ValueAnimator valueAnimatorE = e(false);
            this.B = valueAnimatorE;
            this.A = null;
            valueAnimatorE.addListener(new i3(this, (byte) 2));
            this.B.start();
        }
    }

    public final float[] m() {
        float fFloatValue = ((Float) this.C0.get(0)).floatValue();
        float fFloatValue2 = ((Float) lf2.d(1, this.C0)).floatValue();
        if (this.C0.size() == 1) {
            fFloatValue = this.A0;
        }
        float fV = v(fFloatValue);
        float fV2 = v(fFloatValue2);
        if (this.f0) {
            float fMin = Math.min(0.5f, fV2);
            fV2 = Math.max(0.5f, fV2);
            fV = fMin;
        }
        return (this.f0 || !(s() || t())) ? new float[]{fV, fV2} : new float[]{fV2, fV};
    }

    public final RippleDrawable n() {
        Drawable background = getBackground();
        if (background instanceof DrawableWrapper) {
            background = ((DrawableWrapper) background).getDrawable();
        }
        if (background instanceof RippleDrawable) {
            return (RippleDrawable) background;
        }
        return null;
    }

    public final int o(ColorStateList colorStateList) {
        return colorStateList.getColorForState(getDrawableState(), colorStateList.getDefaultColor());
    }

    @Override // android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.p1 = isShown();
        getViewTreeObserver().addOnScrollChangedListener(this.m1);
        getViewTreeObserver().addOnGlobalLayoutListener(this.n1);
        for (hp2 hp2Var : this.w) {
            ViewGroup viewGroupR = nt2.r(this);
            if (viewGroupR == null) {
                hp2Var.getClass();
            } else {
                hp2Var.getClass();
                int[] iArr = new int[2];
                viewGroupR.getLocationOnScreen(iArr);
                hp2Var.d0 = iArr[0];
                viewGroupR.getWindowVisibleDisplayFrame(hp2Var.W);
                viewGroupR.addOnLayoutChangeListener(hp2Var.V);
            }
        }
    }

    @Override // android.view.View
    public final void onDetachedFromWindow() {
        ch chVar = this.u;
        if (chVar != null) {
            removeCallbacks(chVar);
        }
        this.z = false;
        for (hp2 hp2Var : this.w) {
            ViewGroup viewGroupR = nt2.r(this);
            if (viewGroupR != null) {
                viewGroupR.getOverlay().remove(hp2Var);
                viewGroupR.removeOnLayoutChangeListener(hp2Var.V);
            }
        }
        getViewTreeObserver().removeOnScrollChangedListener(this.m1);
        getViewTreeObserver().removeOnGlobalLayoutListener(this.n1);
        super.onDetachedFromWindow();
    }

    /* JADX WARN: Code duplicated, block: B:127:0x027a  */
    /* JADX WARN: Code duplicated, block: B:84:0x019b  */
    /* JADX WARN: Code duplicated, block: B:85:0x01a1  */
    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        int iC;
        int iC2;
        int i;
        int iC3;
        float f;
        float f2;
        int i2;
        fh fhVar = this;
        if (fhVar.N0) {
            fhVar.Q();
            fhVar.I();
        }
        super.onDraw(canvas);
        int iD = fhVar.d();
        int i3 = fhVar.L0;
        float[] fArrM = fhVar.m();
        float f3 = iD;
        float f4 = fhVar.Q / 2.0f;
        float f5 = f3 - f4;
        float f6 = f4 + f3;
        float f7 = 0.5f;
        int i4 = 0;
        if (fhVar.f0 && fArrM[0] == 0.5f) {
            iC = fhVar.V;
        } else {
            iC = fhVar.c((fhVar.s() || fhVar.t()) ? fhVar.C0.size() - 1 : 0);
        }
        int i5 = iC;
        float trackCornerSize = fhVar.R - fhVar.getTrackCornerSize();
        float f8 = i3;
        float f9 = ((fArrM[0] * f8) + fhVar.R) - i5;
        RectF rectF = fhVar.V0;
        fhVar.f(trackCornerSize, f9, f5, f6, canvas, rectF, 2, i5);
        if (fhVar.f0 && fArrM[1] == 0.5f) {
            iC2 = fhVar.V;
        } else {
            iC2 = fhVar.c((fhVar.s() || fhVar.t()) ? 0 : fhVar.C0.size() - 1);
        }
        int i6 = iC2;
        int i7 = fhVar.R;
        float f10 = (fArrM[1] * f8) + i7 + i6;
        int trackCornerSize2 = fhVar.getTrackCornerSize();
        RectF rectF2 = fhVar.W0;
        int i8 = 3;
        fhVar.f(f10, trackCornerSize2 + i7 + i3, f5, f6, canvas, rectF2, 3, i6);
        int i9 = fhVar.L0;
        float[] fArrM2 = fhVar.m();
        float f11 = fhVar.R;
        float f12 = i9;
        float f13 = (fArrM2[1] * f12) + f11;
        float fC = (fArrM2[0] * f12) + f11;
        int i10 = 2;
        float fS = f13;
        RectF rectF3 = fhVar.U0;
        if (fC >= f13) {
            rectF3.setEmpty();
        } else {
            if (fhVar.C0.size() != 1 || fhVar.f0) {
                i8 = 4;
            } else if (!fhVar.s() && !fhVar.t()) {
                i8 = 2;
            }
            int i11 = i8;
            int i12 = 0;
            while (i12 < fhVar.C0.size()) {
                if (fhVar.C0.size() > 1) {
                    fS = i12 > 0 ? fhVar.S(((Float) fhVar.C0.get(i12 - 1)).floatValue()) : fC;
                    float fS2 = fhVar.S(((Float) fhVar.C0.get(i12)).floatValue());
                    if (fhVar.s() || fhVar.t()) {
                        fC = fS2;
                    } else {
                        fC = fS;
                        fS = fS2;
                    }
                }
                int trackCornerSize3 = fhVar.getTrackCornerSize();
                float f14 = f7;
                int iU = lf2.u(i11);
                if (iU != 1) {
                    if (iU != i10) {
                        i = i10;
                        if (iU == 3) {
                            if (i12 > 0) {
                                fC += fhVar.c(i12 - 1);
                                iC3 = fhVar.c(i12);
                            } else if (fArrM2[1] == f14) {
                                fC += fhVar.c(i12);
                            } else if (fArrM2[0] == f14) {
                                iC3 = fhVar.c(i12);
                            }
                        }
                    } else {
                        i = i10;
                        fC += fhVar.c(i12);
                        fS += trackCornerSize3;
                    }
                    f = fS;
                    f2 = fC;
                    if (f2 >= f) {
                        rectF3.setEmpty();
                    } else {
                        float f15 = fhVar.Q / 2.0f;
                        rectF3.set(f2, f3 - f15, f, f15 + f3);
                        fhVar.K(canvas, fhVar.m, rectF3, trackCornerSize3, i11);
                    }
                    i12++;
                    fS = f;
                    fC = f2;
                    f7 = f14;
                    i10 = i;
                } else {
                    i = i10;
                    fC -= trackCornerSize3;
                    iC3 = fhVar.c(i12);
                }
                fS -= iC3;
                f = fS;
                f2 = fC;
                if (f2 >= f) {
                    rectF3.setEmpty();
                } else {
                    float f16 = fhVar.Q / 2.0f;
                    rectF3.set(f2, f3 - f16, f, f16 + f3);
                    fhVar.K(canvas, fhVar.m, rectF3, trackCornerSize3, i11);
                }
                i12++;
                fS = f;
                fC = f2;
                f7 = f14;
                i10 = i;
            }
        }
        Canvas canvas2 = canvas;
        int i13 = i10;
        if (fhVar.s() || fhVar.t()) {
            fhVar.j(canvas2, rectF3, rectF);
        } else {
            fhVar.j(canvas2, rectF3, rectF2);
        }
        float[] fArr = fhVar.H0;
        if (fArr != null && fArr.length != 0) {
            float[] fArrM3 = fhVar.m();
            int iCeil = (int) Math.ceil(((fhVar.H0.length / 2.0f) - 1.0f) * fArrM3[0]);
            int iFloor = (int) Math.floor(((fhVar.H0.length / 2.0f) - 1.0f) * fArrM3[1]);
            Paint paint = fhVar.p;
            if (iCeil > 0) {
                fhVar.i(0, iCeil * 2, canvas2, paint);
            }
            if (iCeil <= iFloor) {
                fhVar.i(iCeil * 2, (iFloor + 1) * 2, canvas2, fhVar.q);
            }
            int i14 = (iFloor + 1) * 2;
            float[] fArr2 = fhVar.H0;
            if (i14 < fArr2.length) {
                fhVar.i(i14, fArr2.length, canvas2, paint);
            }
        }
        if (fhVar.c0 > 0 && !fhVar.C0.isEmpty()) {
            float fFloatValue = ((Float) lf2.d(1, fhVar.C0)).floatValue();
            float f17 = fhVar.B0;
            if (fFloatValue < f17) {
                fhVar.g(canvas2, fhVar.S(f17), f3);
            }
            if (fhVar.f0 || (fhVar.C0.size() > 1 && ((Float) fhVar.C0.get(0)).floatValue() > fhVar.A0)) {
                fhVar.g(canvas2, fhVar.S(fhVar.A0), f3);
            }
        }
        if ((fhVar.z0 || fhVar.isFocused()) && fhVar.isEnabled()) {
            int i15 = fhVar.L0;
            if (fhVar.n() == null) {
                float[] fArr3 = new float[i13];
                fArr3[0] = (fhVar.v(((Float) fhVar.C0.get(fhVar.E0)).floatValue()) * i15) + fhVar.R;
                fArr3[1] = f3;
                if (fhVar.t()) {
                    fhVar.b1.mapPoints(fArr3);
                }
                if (Build.VERSION.SDK_INT < 28) {
                    float f18 = fArr3[0];
                    float f19 = fhVar.U;
                    float f20 = fArr3[1];
                    canvas.clipRect(f18 - f19, f20 - f19, f18 + f19, f20 + f19, Region.Op.UNION);
                    canvas2 = canvas;
                }
                canvas2.drawCircle(fArr3[0], fArr3[1], fhVar.U, fhVar.o);
            } else {
                fhVar = fhVar;
            }
        } else {
            fhVar = fhVar;
        }
        fhVar.G();
        int i16 = fhVar.L0;
        while (i4 < fhVar.C0.size()) {
            float fFloatValue2 = ((Float) fhVar.C0.get(i4)).floatValue();
            Drawable drawable = fhVar.d1;
            if (drawable != null) {
                i2 = iD;
                fhVar.h(canvas2, i16, i2, fFloatValue2, drawable);
            } else {
                fh fhVar2 = fhVar;
                i2 = iD;
                if (i4 < fhVar2.e1.size()) {
                    fhVar2.h(canvas, i16, i2, fFloatValue2, (Drawable) fhVar2.e1.get(i4));
                } else {
                    if (!fhVar2.isEnabled()) {
                        canvas.drawCircle((fhVar2.v(fFloatValue2) * i16) + fhVar2.R, f3, fhVar2.getThumbRadius(), fhVar2.n);
                    }
                    fhVar2.h(canvas, i16, i2, fFloatValue2, (Drawable) fhVar2.c1.get(i4));
                }
            }
            i4++;
            fhVar = this;
            canvas2 = canvas;
            iD = i2;
        }
    }

    @Override // android.view.View
    public final void onFocusChanged(boolean z, int i, Rect rect) {
        super.onFocusChanged(z, i, rect);
        dh dhVar = this.s;
        if (!z) {
            y();
            this.D0 = -1;
            dhVar.j(this.E0);
            return;
        }
        if (this.D0 == -1) {
            int i2 = Integer.MAX_VALUE;
            if (i == 1) {
                u(Integer.MAX_VALUE);
            } else if (i == 2) {
                u(Integer.MIN_VALUE);
            } else if (i == 17) {
                u((s() || t()) ? -2147483647 : Integer.MAX_VALUE);
            } else if (i == 66) {
                if (!s() && !t()) {
                    i2 = Integer.MIN_VALUE;
                }
                u(i2);
            }
            this.D0 = this.E0;
        }
        y();
        H();
        dhVar.v(this.E0);
    }

    @Override // android.view.View
    public final void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setVisibleToUser(false);
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public final boolean onKeyDown(int i, KeyEvent keyEvent) {
        Float fValueOf;
        if (!isEnabled()) {
            return super.onKeyDown(i, keyEvent);
        }
        this.D0 = this.E0;
        boolean zIsLongPress = this.M0 | keyEvent.isLongPress();
        this.M0 = zIsLongPress;
        float fRound = this.F0;
        if (zIsLongPress) {
            if (fRound == 0.0f) {
                fRound = 1.0f;
            }
            float f = (this.B0 - this.A0) / fRound;
            if (f > 20.0f) {
                fRound *= Math.round(f / 20.0f);
            }
        } else if (fRound == 0.0f) {
            fRound = 1.0f;
        }
        if (i == 21) {
            if (!s()) {
                fRound = -fRound;
            }
            fValueOf = Float.valueOf(fRound);
        } else if (i == 22) {
            if (s()) {
                fRound = -fRound;
            }
            fValueOf = Float.valueOf(fRound);
        } else if (i != 69) {
            fValueOf = (i == 70 || i == 81) ? Float.valueOf(fRound) : null;
        } else {
            fValueOf = Float.valueOf(-fRound);
        }
        if (fValueOf != null) {
            if (C(this.D0, fValueOf.floatValue() + ((Float) this.C0.get(this.D0)).floatValue())) {
                F();
                postInvalidate();
            }
            return true;
        }
        if (i != 61) {
            return super.onKeyDown(i, keyEvent);
        }
        y();
        if (keyEvent.hasNoModifiers()) {
            return u(1);
        }
        if (keyEvent.isShiftPressed()) {
            return u(-1);
        }
        return false;
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public final boolean onKeyUp(int i, KeyEvent keyEvent) {
        this.M0 = false;
        return super.onKeyUp(i, keyEvent);
    }

    @Override // android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        Rect rect = this.w0;
        rect.left = 0;
        rect.top = 0;
        rect.right = i3 - i;
        rect.bottom = i4 - i2;
        ArrayList arrayList = this.x0;
        if (!arrayList.contains(rect)) {
            arrayList.add(rect);
        }
        WeakHashMap weakHashMap = lw2.a;
        if (Build.VERSION.SDK_INT >= 29) {
            hw2.c(this, arrayList);
        }
    }

    @Override // android.view.View
    public final void onMeasure(int i, int i2) {
        int i3 = this.P;
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(this.O + ((i3 == 1 || i3 == 3) ? ((hp2) this.w.get(0)).getIntrinsicHeight() : 0), 1073741824);
        if (t()) {
            super.onMeasure(iMakeMeasureSpec, i2);
        } else {
            super.onMeasure(i, iMakeMeasureSpec);
        }
    }

    @Override // android.view.View
    public final void onRestoreInstanceState(Parcelable parcelable) {
        eh ehVar = (eh) parcelable;
        super.onRestoreInstanceState(ehVar.getSuperState());
        this.A0 = ehVar.l;
        this.B0 = ehVar.m;
        B(ehVar.n);
        this.F0 = ehVar.o;
        if (ehVar.p) {
            requestFocus();
        }
    }

    @Override // android.view.View
    public final Parcelable onSaveInstanceState() {
        eh ehVar = new eh(super.onSaveInstanceState());
        ehVar.l = this.A0;
        ehVar.m = this.B0;
        ehVar.n = new ArrayList(this.C0);
        ehVar.o = this.F0;
        ehVar.p = hasFocus();
        return ehVar;
    }

    @Override // android.view.View
    public final void onSizeChanged(int i, int i2, int i3, int i4) {
        if (t()) {
            i = i2;
        }
        this.L0 = Math.max(i - (this.R * 2), 0);
        I();
        F();
    }

    @Override // android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        if (isEnabled()) {
            float y = t() ? motionEvent.getY() : motionEvent.getX();
            float x = t() ? motionEvent.getX() : motionEvent.getY();
            float f = (y - this.R) / this.L0;
            this.j1 = f;
            float fMax = Math.max(0.0f, f);
            this.j1 = fMax;
            this.j1 = Math.min(1.0f, fMax);
            int actionMasked = motionEvent.getActionMasked();
            if (actionMasked != 0) {
                int i = this.C;
                if (actionMasked == 1) {
                    this.z0 = false;
                    MotionEvent motionEvent2 = this.v0;
                    if (motionEvent2 != null && motionEvent2.getActionMasked() == 0) {
                        float f2 = i;
                        if (Math.abs(this.v0.getX() - motionEvent.getX()) <= f2 && Math.abs(this.v0.getY() - motionEvent.getY()) <= f2) {
                            me2 me2Var = (me2) this;
                            if (me2Var.getActiveThumbIndex() == -1) {
                                me2Var.setActiveThumbIndex(0);
                            }
                            w();
                        }
                    }
                    if (this.D0 != -1) {
                        D();
                        F();
                        y();
                        this.D0 = -1;
                        x();
                    }
                    invalidate();
                } else if (actionMasked == 2) {
                    if (!this.z0) {
                        if ((t() || !r(motionEvent) || Math.abs(y - this.t0) >= i) && (!t() || !q(motionEvent) || Math.abs(x - this.u0) >= i * 0.8f)) {
                            getParent().requestDisallowInterceptTouchEvent(true);
                            me2 me2Var2 = (me2) this;
                            if (me2Var2.getActiveThumbIndex() == -1) {
                                me2Var2.setActiveThumbIndex(0);
                            }
                            this.z0 = true;
                            H();
                            w();
                        }
                    }
                    D();
                    F();
                    invalidate();
                } else if (actionMasked == 3) {
                    this.z0 = false;
                    if (this.D0 != -1 && !this.y0.isEmpty()) {
                        for (int i2 = 0; i2 < this.C0.size(); i2++) {
                            if (i2 == this.D0) {
                                C(i2, ((Float) this.y0.get(i2)).floatValue());
                                break;
                            }
                        }
                    }
                    F();
                    y();
                    this.D0 = -1;
                    x();
                    invalidate();
                }
            } else {
                this.t0 = y;
                this.u0 = x;
                this.y0.clear();
                this.y0 = getValues();
                if ((t() || !r(motionEvent)) && (!t() || !q(motionEvent))) {
                    getParent().requestDisallowInterceptTouchEvent(true);
                    me2 me2Var3 = (me2) this;
                    if (me2Var3.getActiveThumbIndex() == -1) {
                        me2Var3.setActiveThumbIndex(0);
                    }
                    requestFocus();
                    this.z0 = true;
                    H();
                    w();
                    D();
                    F();
                    invalidate();
                }
            }
            setPressed(this.z0);
            this.v0 = MotionEvent.obtain(motionEvent);
            return true;
        }
        return false;
    }

    @Override // android.view.View
    public final void onVisibilityAggregated(boolean z) {
        super.onVisibilityAggregated(z);
        this.p1 = z;
    }

    @Override // android.view.View
    public final void onVisibilityChanged(View view, int i) {
        super.onVisibilityChanged(view, i);
        if (i != 0) {
            ViewGroup viewGroupR = nt2.r(this);
            ViewOverlay overlay = viewGroupR == null ? null : viewGroupR.getOverlay();
            if (overlay == null) {
                return;
            }
            Iterator it = this.w.iterator();
            while (it.hasNext()) {
                overlay.remove((hp2) it.next());
            }
        }
    }

    public final boolean p(double d) {
        double dDoubleValue = new BigDecimal(Double.toString(d)).divide(new BigDecimal(Float.toString(this.F0)), MathContext.DECIMAL64).doubleValue();
        return Math.abs(((double) Math.round(dDoubleValue)) - dDoubleValue) < 1.0E-4d;
    }

    public final boolean q(MotionEvent motionEvent) {
        if (motionEvent.getToolType(0) != 3) {
            for (ViewParent parent = getParent(); parent instanceof ViewGroup; parent = parent.getParent()) {
                ViewGroup viewGroup = (ViewGroup) parent;
                if ((viewGroup.canScrollHorizontally(1) || viewGroup.canScrollHorizontally(-1)) && viewGroup.shouldDelayChildPressedState()) {
                    return true;
                }
            }
        }
        return false;
    }

    public final boolean r(MotionEvent motionEvent) {
        if (motionEvent.getToolType(0) != 3) {
            for (ViewParent parent = getParent(); parent instanceof ViewGroup; parent = parent.getParent()) {
                ViewGroup viewGroup = (ViewGroup) parent;
                if ((viewGroup.canScrollVertically(1) || viewGroup.canScrollVertically(-1)) && viewGroup.shouldDelayChildPressedState()) {
                    return true;
                }
            }
        }
        return false;
    }

    public final boolean s() {
        return getLayoutDirection() == 1;
    }

    public void setActiveThumbIndex(int i) {
        this.D0 = i;
    }

    public abstract void setCentered(boolean z);

    public void setCustomThumbDrawablesForValues(Drawable... drawableArr) {
        this.d1 = null;
        this.e1 = new ArrayList();
        for (Drawable drawable : drawableArr) {
            List list = this.e1;
            Drawable drawableNewDrawable = drawable.mutate().getConstantState().newDrawable();
            a(this.S, drawableNewDrawable);
            list.add(drawableNewDrawable);
        }
        postInvalidate();
    }

    @Override // android.view.View
    public void setEnabled(boolean z) {
        super.setEnabled(z);
        setLayerType(z ? 0 : 2, null);
    }

    public abstract void setHaloRadius(int i);

    public abstract void setHaloTintList(ColorStateList colorStateList);

    public abstract void setLabelBehavior(int i);

    public abstract void setOrientation(int i);

    public void setSeparationUnit(int i) {
        this.k1 = i;
        this.N0 = true;
        postInvalidate();
    }

    public abstract void setThumbElevation(float f);

    public abstract void setThumbHeight(int i);

    public abstract void setThumbStrokeColor(ColorStateList colorStateList);

    public abstract void setThumbStrokeWidth(float f);

    public abstract void setThumbTintList(ColorStateList colorStateList);

    public abstract void setThumbTrackGapSize(int i);

    public abstract void setThumbWidth(int i);

    public abstract void setTickActiveRadius(int i);

    public abstract void setTickActiveTintList(ColorStateList colorStateList);

    public abstract void setTickInactiveRadius(int i);

    public abstract void setTickInactiveTintList(ColorStateList colorStateList);

    public abstract void setTrackActiveTintList(ColorStateList colorStateList);

    public abstract void setTrackCornerSize(int i);

    public abstract void setTrackHeight(int i);

    public abstract void setTrackIconActiveColor(ColorStateList colorStateList);

    public abstract void setTrackIconActiveEnd(Drawable drawable);

    public abstract void setTrackIconActiveStart(Drawable drawable);

    public abstract void setTrackIconInactiveColor(ColorStateList colorStateList);

    public abstract void setTrackIconInactiveEnd(Drawable drawable);

    public abstract void setTrackIconInactiveStart(Drawable drawable);

    public abstract void setTrackIconSize(int i);

    public abstract void setTrackInactiveTintList(ColorStateList colorStateList);

    public abstract void setTrackInsideCornerSize(int i);

    public abstract void setTrackStopIndicatorSize(int i);

    public void setValues(Float... fArr) {
        ArrayList arrayList = new ArrayList();
        Collections.addAll(arrayList, fArr);
        B(arrayList);
    }

    public final boolean t() {
        return this.M == 1;
    }

    public final boolean u(int i) {
        int i2 = this.E0;
        long j = ((long) i2) + ((long) i);
        long size = this.C0.size() - 1;
        if (j < 0) {
            j = 0;
        } else if (j > size) {
            j = size;
        }
        int i3 = (int) j;
        this.E0 = i3;
        if (i3 == i2) {
            return false;
        }
        this.D0 = i3;
        H();
        F();
        postInvalidate();
        return true;
    }

    public final float v(float f) {
        float f2 = this.A0;
        float f3 = (f - f2) / (this.B0 - f2);
        return (s() || t()) ? 1.0f - f3 : f3;
    }

    public final void w() {
        for (uk1 uk1Var : this.y) {
            uk1Var.getClass();
            uk1Var.a[0] = true;
        }
    }

    public final void x() {
        for (uk1 uk1Var : this.y) {
            uk1Var.getClass();
            uk1Var.a[0] = false;
        }
    }

    public final void y() {
        int i;
        if (this.V <= 0 || (i = this.W) == -1 || this.a0 == -1) {
            return;
        }
        z(i, this.b0, Integer.valueOf(this.D0));
    }

    public final void z(int i, int i2, Integer num) {
        byte b = 0;
        int i3 = 0;
        while (true) {
            ArrayList arrayList = this.c1;
            if (i3 >= arrayList.size()) {
                P(false);
                return;
            }
            if (num == null || i3 == num.intValue()) {
                n61 n61Var = (n61) arrayList.get(i3);
                qc0 qc0Var = new qc0(b);
                qc0 qc0Var2 = new qc0(b);
                qc0 qc0Var3 = new qc0(b);
                qc0 qc0Var4 = new qc0(b);
                float f = i / 2.0f;
                ha1 ha1VarH = gj0.h(0);
                k0 k0Var = new k0(f);
                k0 k0Var2 = new k0(f);
                k0 k0Var3 = new k0(f);
                k0 k0Var4 = new k0(f);
                hc2 hc2Var = new hc2();
                hc2Var.a = ha1VarH;
                hc2Var.b = ha1VarH;
                hc2Var.c = ha1VarH;
                hc2Var.d = ha1VarH;
                hc2Var.e = k0Var;
                hc2Var.f = k0Var2;
                hc2Var.g = k0Var3;
                hc2Var.h = k0Var4;
                hc2Var.i = qc0Var;
                hc2Var.j = qc0Var2;
                hc2Var.k = qc0Var3;
                hc2Var.l = qc0Var4;
                n61Var.setShapeAppearanceModel(hc2Var);
                ((n61) arrayList.get(i3)).setBounds(0, 0, i, i2 >= 0 ? i2 : this.T);
            }
            i3++;
        }
    }

    public void setValues(List<Float> list) {
        B(new ArrayList(list));
    }

    public void setCustomThumbDrawablesForValues(int... iArr) {
        Drawable[] drawableArr = new Drawable[iArr.length];
        for (int i = 0; i < iArr.length; i++) {
            drawableArr[i] = getResources().getDrawable(iArr[i]);
        }
        setCustomThumbDrawablesForValues(drawableArr);
    }
}
