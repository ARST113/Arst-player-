package defpackage;

import android.animation.ValueAnimator;
import android.content.ContentResolver;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import android.provider.Settings;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.ProgressBar;
import com.justplus.player.R;
import java.util.ArrayList;
import java.util.Arrays;

/* JADX INFO: compiled from: r8-map-id-0cee80150bab987b3edc91bcfc1e04786a1418881e5c92a3b999d44d61001263 */
/* JADX INFO: loaded from: classes.dex */
public abstract class wg extends ProgressBar {
    public final xg l;
    public int m;
    public final boolean n;
    public final int o;
    public long p;
    public y6 q;
    public boolean r;
    public int s;
    public boolean t;
    public final tg u;
    public final ug v;
    public final ug w;
    public final vg x;
    public final vg y;

    public wg(Context context, AttributeSet attributeSet, int i, int i2) {
        super(ij0.M(context, attributeSet, i, R.style.Widget_MaterialComponents_ProgressIndicator), attributeSet, i);
        this.p = -1L;
        this.r = false;
        this.s = 4;
        this.u = new tg(this);
        this.v = new ug(this, (byte) 0);
        this.w = new ug(this, (byte) 1);
        this.x = new vg(this, (byte) 0);
        this.y = new vg(this, (byte) 1);
        Context context2 = getContext();
        this.l = a(context2, attributeSet);
        TypedArray typedArrayM = ck0.M(context2, attributeSet, cw1.b, i, i2, new int[0]);
        typedArrayM.getInt(7, -1);
        this.o = Math.min(typedArrayM.getInt(5, -1), 1000);
        typedArrayM.recycle();
        this.q = new y6();
        this.n = true;
    }

    private ea0 getCurrentDrawingDelegate() {
        if (isIndeterminate()) {
            if (getIndeterminateDrawable() == null) {
                return null;
            }
            return getIndeterminateDrawable().y;
        }
        if (getProgressDrawable() == null) {
            return null;
        }
        return getProgressDrawable().y;
    }

    public xg a(Context context, AttributeSet attributeSet) {
        d11 d11Var = new d11(context, attributeSet, R.attr.linearProgressIndicatorStyle, R.style.Widget_MaterialComponents_LinearProgressIndicator);
        ck0.e(context, attributeSet, R.attr.linearProgressIndicatorStyle, R.style.Widget_MaterialComponents_LinearProgressIndicator);
        int[] iArr = cw1.n;
        ck0.h(context, attributeSet, iArr, R.attr.linearProgressIndicatorStyle, R.style.Widget_MaterialComponents_LinearProgressIndicator, new int[0]);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, iArr, R.attr.linearProgressIndicatorStyle, R.style.Widget_MaterialComponents_LinearProgressIndicator);
        d11Var.q = typedArrayObtainStyledAttributes.getInt(0, 1);
        d11Var.r = typedArrayObtainStyledAttributes.getInt(1, 0);
        d11Var.t = typedArrayObtainStyledAttributes.getDimensionPixelSize(4, 0);
        if (typedArrayObtainStyledAttributes.hasValue(3)) {
            d11Var.u = Integer.valueOf(typedArrayObtainStyledAttributes.getDimensionPixelSize(3, 0));
        }
        TypedValue typedValuePeekValue = typedArrayObtainStyledAttributes.peekValue(2);
        if (typedValuePeekValue != null) {
            int i = typedValuePeekValue.type;
            if (i == 5) {
                d11Var.v = Math.min(TypedValue.complexToDimensionPixelSize(typedValuePeekValue.data, typedArrayObtainStyledAttributes.getResources().getDisplayMetrics()), d11Var.a / 2);
                d11Var.x = false;
                d11Var.y = true;
            } else if (i == 6) {
                d11Var.w = Math.min(typedValuePeekValue.getFraction(1.0f, 1.0f), 0.5f);
                d11Var.x = true;
                d11Var.y = true;
            }
        }
        typedArrayObtainStyledAttributes.recycle();
        d11Var.d();
        d11Var.s = d11Var.r == 1;
        return d11Var;
    }

    public final void b() {
        if (getVisibility() != 0) {
            removeCallbacks(this.v);
            return;
        }
        ug ugVar = this.w;
        removeCallbacks(ugVar);
        long jUptimeMillis = SystemClock.uptimeMillis() - this.p;
        long j = this.o;
        if (jUptimeMillis >= j) {
            ugVar.run();
        } else {
            postDelayed(ugVar, j - jUptimeMillis);
        }
    }

    public final void c() {
        if (getProgressDrawable() == null || getIndeterminateDrawable() == null) {
            return;
        }
        getIndeterminateDrawable().z.l(this.x);
    }

    public void d(int i) {
        if (!isIndeterminate()) {
            super.setProgress(i);
            if (getProgressDrawable() != null) {
                getProgressDrawable().jumpToCurrentState();
                return;
            }
            return;
        }
        if (getProgressDrawable() != null) {
            this.m = i;
            this.r = true;
            if (getIndeterminateDrawable().isVisible()) {
                y6 y6Var = this.q;
                ContentResolver contentResolver = getContext().getContentResolver();
                y6Var.getClass();
                if (Settings.Global.getFloat(contentResolver, "animator_duration_scale", 1.0f) != 0.0f) {
                    getIndeterminateDrawable().z.m();
                    return;
                }
            }
            this.x.a(getIndeterminateDrawable());
        }
    }

    public final boolean e() {
        if (!isAttachedToWindow() || getWindowVisibility() != 0) {
            return false;
        }
        View view = this;
        while (view.getVisibility() == 0) {
            Object parent = view.getParent();
            if (parent == null) {
                return getWindowVisibility() == 0;
            }
            if (!(parent instanceof View)) {
                return true;
            }
            view = (View) parent;
        }
        return false;
    }

    @Override // android.widget.ProgressBar
    public Drawable getCurrentDrawable() {
        return isIndeterminate() ? getIndeterminateDrawable() : getProgressDrawable();
    }

    public int getHideAnimationBehavior() {
        return this.l.h;
    }

    @Override // android.widget.ProgressBar
    public dx0 getIndeterminateDrawable() {
        return (dx0) super.getIndeterminateDrawable();
    }

    public int[] getIndicatorColor() {
        return this.l.e;
    }

    public int getIndicatorTrackGapSize() {
        return this.l.i;
    }

    @Override // android.widget.ProgressBar
    public v60 getProgressDrawable() {
        return (v60) super.getProgressDrawable();
    }

    public int getShowAnimationBehavior() {
        return this.l.g;
    }

    public int getTrackColor() {
        return this.l.f;
    }

    public int getTrackCornerRadius() {
        return this.l.b;
    }

    public float getTrackCornerRadiusFraction() {
        return this.l.c;
    }

    public int getTrackThickness() {
        return this.l.a;
    }

    public int getWaveAmplitude() {
        return this.l.l;
    }

    public int getWaveSpeed() {
        return this.l.m;
    }

    public int getWavelengthDeterminate() {
        return this.l.j;
    }

    public int getWavelengthIndeterminate() {
        return this.l.k;
    }

    @Override // android.view.View
    public final void invalidate() {
        super.invalidate();
        if (getCurrentDrawable() != null) {
            getCurrentDrawable().invalidateSelf();
        }
    }

    @Override // android.widget.ProgressBar, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        c();
        v60 progressDrawable = getProgressDrawable();
        vg vgVar = this.y;
        if (progressDrawable != null) {
            v60 progressDrawable2 = getProgressDrawable();
            ArrayList arrayList = progressDrawable2.r;
            if (arrayList == null) {
                arrayList = new ArrayList();
                progressDrawable2.r = arrayList;
            }
            if (!arrayList.contains(vgVar)) {
                progressDrawable2.r.add(vgVar);
            }
        }
        if (getIndeterminateDrawable() != null) {
            dx0 indeterminateDrawable = getIndeterminateDrawable();
            ArrayList arrayList2 = indeterminateDrawable.r;
            if (arrayList2 == null) {
                arrayList2 = new ArrayList();
                indeterminateDrawable.r = arrayList2;
            }
            if (!arrayList2.contains(vgVar)) {
                indeterminateDrawable.r.add(vgVar);
            }
        }
        if (e()) {
            if (this.o > 0) {
                this.p = SystemClock.uptimeMillis();
            }
            setVisibility(0);
        }
    }

    @Override // android.widget.ProgressBar, android.view.View
    public final void onDetachedFromWindow() {
        removeCallbacks(this.w);
        removeCallbacks(this.v);
        ((ba0) getCurrentDrawable()).d(false, false, false);
        dx0 indeterminateDrawable = getIndeterminateDrawable();
        vg vgVar = this.y;
        if (indeterminateDrawable != null) {
            getIndeterminateDrawable().f(vgVar);
            getIndeterminateDrawable().z.p();
        }
        if (getProgressDrawable() != null) {
            getProgressDrawable().f(vgVar);
        }
        super.onDetachedFromWindow();
    }

    @Override // android.widget.ProgressBar, android.view.View
    public final synchronized void onDraw(Canvas canvas) {
        try {
            int iSave = canvas.save();
            if (getPaddingLeft() != 0 || getPaddingTop() != 0) {
                canvas.translate(getPaddingLeft(), getPaddingTop());
            }
            if (getPaddingRight() != 0 || getPaddingBottom() != 0) {
                canvas.clipRect(0, 0, getWidth() - (getPaddingLeft() + getPaddingRight()), getHeight() - (getPaddingTop() + getPaddingBottom()));
            }
            getCurrentDrawable().draw(canvas);
            canvas.restoreToCount(iSave);
        } catch (Throwable th) {
            throw th;
        }
    }

    @Override // android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        getCurrentDrawingDelegate().g();
    }

    @Override // android.widget.ProgressBar, android.view.View
    public final synchronized void onMeasure(int i, int i2) {
        try {
            ea0 currentDrawingDelegate = getCurrentDrawingDelegate();
            if (currentDrawingDelegate == null) {
                return;
            }
            setMeasuredDimension(currentDrawingDelegate.f() < 0 ? View.getDefaultSize(getSuggestedMinimumWidth(), i) : currentDrawingDelegate.f() + getPaddingLeft() + getPaddingRight(), currentDrawingDelegate.e() < 0 ? View.getDefaultSize(getSuggestedMinimumHeight(), i2) : currentDrawingDelegate.e() + getPaddingTop() + getPaddingBottom());
        } catch (Throwable th) {
            throw th;
        }
    }

    @Override // android.view.View
    public final void onVisibilityChanged(View view, int i) {
        super.onVisibilityChanged(view, i);
        boolean z = i == 0;
        if (this.n) {
            ((ba0) getCurrentDrawable()).d(e(), false, z);
        }
    }

    @Override // android.view.View
    public final void onWindowVisibilityChanged(int i) {
        super.onWindowVisibilityChanged(i);
        if (this.n) {
            ((ba0) getCurrentDrawable()).d(e(), false, false);
        }
    }

    public void setAnimatorDurationScaleProvider(y6 y6Var) {
        this.q = y6Var;
        if (getProgressDrawable() != null) {
            getProgressDrawable().n = y6Var;
        }
        if (getIndeterminateDrawable() != null) {
            getIndeterminateDrawable().n = y6Var;
        }
    }

    public void setHideAfterMaxProgress(boolean z) {
        if (getProgressDrawable() == null) {
            return;
        }
        tg tgVar = this.u;
        if (z) {
            ArrayList arrayList = getProgressDrawable().z.i;
            if (arrayList.contains(tgVar)) {
                return;
            }
            arrayList.add(tgVar);
            return;
        }
        ArrayList arrayList2 = getProgressDrawable().z.i;
        int iIndexOf = arrayList2.indexOf(tgVar);
        if (iIndexOf >= 0) {
            arrayList2.set(iIndexOf, null);
        }
    }

    public void setHideAnimationBehavior(int i) {
        this.l.h = i;
        invalidate();
    }

    @Override // android.widget.ProgressBar
    public synchronized void setIndeterminate(boolean z) {
        try {
            if (z == isIndeterminate()) {
                return;
            }
            ba0 ba0Var = (ba0) getCurrentDrawable();
            if (ba0Var != null) {
                ba0Var.d(false, false, false);
            }
            super.setIndeterminate(z);
            ba0 ba0Var2 = (ba0) getCurrentDrawable();
            if (ba0Var2 != null) {
                ba0Var2.d(e(), false, false);
            }
            if ((ba0Var2 instanceof dx0) && e()) {
                ((dx0) ba0Var2).z.o();
            }
            this.r = false;
        } catch (Throwable th) {
            throw th;
        }
    }

    public void setIndeterminateAnimatorDurationScale(float f) {
        xg xgVar = this.l;
        if (xgVar.n != f) {
            xgVar.n = f;
            getIndeterminateDrawable().z.j();
        }
    }

    @Override // android.widget.ProgressBar
    public void setIndeterminateDrawable(Drawable drawable) {
        if (drawable instanceof dx0) {
            ((ba0) drawable).d(false, false, false);
            super.setIndeterminateDrawable(drawable);
        } else if (this.t) {
            bl.d("Cannot set framework drawable as indeterminate drawable.");
        } else {
            super.setIndeterminateDrawable(drawable);
        }
    }

    public void setIndicatorColor(int... iArr) {
        if (iArr.length == 0) {
            iArr = new int[]{sj.n(getContext(), R.attr.colorPrimary, -1)};
        }
        if (Arrays.equals(getIndicatorColor(), iArr)) {
            return;
        }
        this.l.e = iArr;
        getIndeterminateDrawable().z.j();
        invalidate();
    }

    public void setIndicatorTrackGapSize(int i) {
        xg xgVar = this.l;
        if (xgVar.i != i) {
            xgVar.i = i;
            xgVar.d();
            invalidate();
        }
    }

    @Override // android.widget.ProgressBar
    public synchronized void setProgress(int i) {
        if (isIndeterminate()) {
            return;
        }
        d(i);
    }

    @Override // android.widget.ProgressBar
    public void setProgressDrawable(Drawable drawable) {
        if (drawable instanceof v60) {
            v60 v60Var = (v60) drawable;
            v60Var.d(false, false, false);
            super.setProgressDrawable(v60Var);
            v60Var.setLevel((int) ((getProgress() / getMax()) * 10000.0f));
            return;
        }
        if (this.t) {
            bl.d("Cannot set framework drawable as progress drawable.");
        } else {
            super.setProgressDrawable(drawable);
        }
    }

    public void setShowAnimationBehavior(int i) {
        this.l.g = i;
        invalidate();
    }

    public void setTrackColor(int i) {
        xg xgVar = this.l;
        if (xgVar.f != i) {
            xgVar.f = i;
            invalidate();
        }
    }

    public void setTrackCornerRadius(int i) {
        xg xgVar = this.l;
        if (xgVar.b != i) {
            xgVar.b = Math.min(i, xgVar.a / 2);
            xgVar.d = false;
            invalidate();
        }
    }

    public void setTrackCornerRadiusFraction(float f) {
        xg xgVar = this.l;
        if (xgVar.c != f) {
            xgVar.c = Math.min(f, 0.5f);
            xgVar.d = true;
            invalidate();
        }
    }

    public void setTrackThickness(int i) {
        xg xgVar = this.l;
        if (xgVar.a != i) {
            xgVar.a = i;
            requestLayout();
        }
    }

    public void setVisibilityAfterHide(int i) {
        if (i == 0 || i == 4 || i == 8) {
            this.s = i;
        } else {
            bl.d("The component's visibility must be one of VISIBLE, INVISIBLE, and GONE defined in View.");
        }
    }

    public void setWaveAmplitude(int i) {
        xg xgVar = this.l;
        if (xgVar.l != i) {
            xgVar.l = Math.abs(i);
            requestLayout();
        }
    }

    public void setWaveAmplitudeRampProgressMax(float f) {
        v60 progressDrawable = getProgressDrawable();
        progressDrawable.m.p = f;
        progressDrawable.invalidateSelf();
        invalidate();
    }

    public void setWaveAmplitudeRampProgressMin(float f) {
        v60 progressDrawable = getProgressDrawable();
        progressDrawable.m.o = f;
        progressDrawable.invalidateSelf();
        invalidate();
    }

    public void setWaveSpeed(int i) {
        xg xgVar = this.l;
        xgVar.m = i;
        v60 progressDrawable = getProgressDrawable();
        boolean z = xgVar.m != 0;
        ValueAnimator valueAnimator = progressDrawable.D;
        if (z && !valueAnimator.isRunning()) {
            valueAnimator.start();
        } else {
            if (z || !valueAnimator.isRunning()) {
                return;
            }
            valueAnimator.cancel();
        }
    }

    public void setWavelength(int i) {
        setWavelengthDeterminate(i);
        setWavelengthIndeterminate(i);
    }

    public void setWavelengthDeterminate(int i) {
        xg xgVar = this.l;
        if (xgVar.j != i) {
            xgVar.j = Math.abs(i);
            if (isIndeterminate()) {
                return;
            }
            requestLayout();
        }
    }

    public void setWavelengthIndeterminate(int i) {
        xg xgVar = this.l;
        if (xgVar.k != i) {
            xgVar.k = Math.abs(i);
            if (isIndeterminate()) {
                requestLayout();
            }
        }
    }
}
