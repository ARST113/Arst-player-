package com.brouken.player;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.animation.PathInterpolator;
import com.justplus.player.R;
import defpackage.eu;
import defpackage.l50;
import defpackage.wt2;
import java.lang.reflect.Field;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: compiled from: r8-map-id-d5d7d661e67c62f2588b5a5666a7a20281e417b1121a202706e8fa77676a2bdd */
/* JADX INFO: loaded from: classes.dex */
public class CustomDefaultTimeBar extends l50 {
    public static final /* synthetic */ int z0 = 0;
    public final Rect a0;
    public final Rect b0;
    public Field c0;
    public final int d0;
    public ValueAnimator e0;
    public boolean f0;
    public int g0;
    public boolean h0;
    public final Paint i0;
    public final Paint j0;
    public final Paint k0;
    public final Paint l0;
    public final Paint m0;
    public final Paint n0;
    public final Paint o0;
    public final Paint p0;
    public final RectF q0;
    public final Rect r0;
    public final Field s0;
    public final Field t0;
    public final Field u0;
    public long[] v0;
    public long[] w0;
    public int[] x0;
    public long y0;

    public CustomDefaultTimeBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, attributeSet, 0);
        this.i0 = j(-1);
        this.j0 = j(-1);
        this.k0 = j(-855638017);
        this.l0 = j(872415231);
        this.m0 = j(-1);
        this.n0 = j(0);
        this.o0 = j(0);
        Paint paintJ = j(-1);
        paintJ.setStyle(Paint.Style.STROKE);
        paintJ.setStrokeWidth(getResources().getDimension(R.dimen.focus_ring_width));
        this.p0 = paintJ;
        this.q0 = new RectF();
        int iCeil = (int) Math.ceil(getResources().getDimension(R.dimen.focus_ring_width));
        setPadding(getPaddingLeft() + iCeil, getPaddingTop(), getPaddingRight() + iCeil, getPaddingBottom());
        try {
            Field declaredField = l50.class.getDeclaredField("scrubberBar");
            declaredField.setAccessible(true);
            this.a0 = (Rect) declaredField.get(this);
            Field declaredField2 = l50.class.getDeclaredField("progressBar");
            declaredField2.setAccessible(true);
            this.b0 = (Rect) declaredField2.get(this);
            Field declaredField3 = l50.class.getDeclaredField("m");
            declaredField3.setAccessible(true);
            this.r0 = (Rect) declaredField3.get(this);
            Field declaredField4 = l50.class.getDeclaredField("S");
            this.s0 = declaredField4;
            declaredField4.setAccessible(true);
            Field declaredField5 = l50.class.getDeclaredField("R");
            this.t0 = declaredField5;
            declaredField5.setAccessible(true);
            Field declaredField6 = l50.class.getDeclaredField("N");
            this.u0 = declaredField6;
            declaredField6.setAccessible(true);
            Field declaredField7 = l50.class.getDeclaredField("barHeight");
            this.c0 = declaredField7;
            declaredField7.setAccessible(true);
            this.d0 = this.c0.getInt(this);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            this.c0 = null;
            e.printStackTrace();
        }
        this.F.add(new b(this));
    }

    public static Paint j(int i) {
        Paint paint = new Paint(1);
        paint.setColor(i);
        return paint;
    }

    public final void h() {
        this.v0 = null;
        this.w0 = null;
        this.x0 = null;
        this.y0 = 0L;
        invalidate();
    }

    public final void i(Canvas canvas, float f, float f2, Paint paint) {
        if (f2 <= f) {
            return;
        }
        Rect rect = this.b0;
        float fMin = Math.min(rect.height(), f2 - f) / 2.0f;
        float f3 = rect.top;
        float f4 = rect.bottom;
        RectF rectF = this.q0;
        rectF.set(f, f3, f2, f4);
        canvas.drawRoundRect(rectF, fMin, fMin, paint);
    }

    public final void k(int i) {
        this.p0.setColor(i);
        invalidate();
    }

    @Override // defpackage.l50, android.view.View
    public final void onDraw(Canvas canvas) {
        Rect rect;
        Rect rect2;
        Field field;
        long j;
        long j2;
        int dimensionPixelSize;
        Rect rect3 = this.b0;
        if (rect3 == null || (rect = this.r0) == null || (rect2 = this.a0) == null || (field = this.t0) == null) {
            super.onDraw(canvas);
            return;
        }
        int i = rect3.left;
        int i2 = rect3.right;
        int iWidth = rect3.width();
        float f = i;
        i(canvas, f, i2, this.l0);
        try {
            j = field.getLong(this);
        } catch (IllegalAccessException | RuntimeException unused) {
            j = 0;
        }
        if (iWidth <= 0 || j <= 0) {
            return;
        }
        float fMin = Math.min(Math.max(rect2.right, i), i2);
        i(canvas, f, Math.min(rect.right, i2), this.k0);
        float f2 = iWidth;
        try {
            j2 = this.s0.getLong(this);
        } catch (IllegalAccessException | RuntimeException unused2) {
            j2 = 0;
        }
        float f3 = j2 / j;
        float f4 = 0.0f;
        float f5 = 1.0f;
        if (f3 < 0.0f) {
            f3 = 0.0f;
        } else if (f3 > 1.0f) {
            f3 = 1.0f;
        }
        float f6 = (f3 * f2) + f;
        boolean z = this.h0;
        Paint paint = this.i0;
        if (!z || f6 >= fMin) {
            i(canvas, f, fMin, paint);
        } else {
            i(canvas, f, fMin, this.j0);
            i(canvas, f, f6, paint);
        }
        long[] jArr = this.v0;
        Paint paint2 = this.m0;
        if (jArr != null && this.y0 > 0) {
            int i3 = 0;
            while (true) {
                long[] jArr2 = this.v0;
                if (i3 >= jArr2.length) {
                    break;
                }
                float f7 = jArr2[i3];
                long j3 = this.y0;
                float f8 = f7 / j3;
                if (f8 < f4) {
                    f8 = f4;
                } else if (f8 > 1.0f) {
                    f8 = 1.0f;
                }
                float f9 = (f8 * f2) + f;
                float f10 = f4;
                float f11 = this.w0[i3] / j3;
                if (f11 < f10) {
                    f11 = f10;
                } else if (f11 > 1.0f) {
                    f11 = 1.0f;
                }
                float f12 = (f11 * f2) + f;
                int i4 = this.x0[i3];
                Paint paint3 = this.n0;
                paint3.setColor(i4);
                i(canvas, f9, f12, paint3);
                if (!this.h0 && fMin >= f9 && fMin <= f12) {
                    int i5 = this.x0[i3] | (-16777216);
                    Paint paint4 = this.o0;
                    paint4.setColor(i5);
                    paint2 = paint4;
                }
                i3++;
                f4 = f10;
            }
        }
        float f13 = f4;
        if (this.h0 || isFocused()) {
            dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.exo_styled_progress_dragged_thumb_size) / 2;
        } else {
            dimensionPixelSize = isEnabled() ? getResources().getDimensionPixelSize(R.dimen.exo_styled_progress_enabled_thumb_size) / 2 : 0;
        }
        float f14 = dimensionPixelSize;
        Field field2 = this.u0;
        if (field2 != null) {
            try {
                f5 = field2.getFloat(this);
            } catch (IllegalAccessException | RuntimeException unused3) {
            }
        }
        float f15 = f14 * f5;
        if (f15 > f13) {
            canvas.drawCircle(fMin, rect3.exactCenterY(), f15, paint2);
            if (isFocused()) {
                Paint paint5 = this.p0;
                canvas.drawCircle(fMin, rect3.exactCenterY(), (paint5.getStrokeWidth() / 2.0f) + f15, paint5);
            }
        }
    }

    @Override // defpackage.l50, android.view.View
    public final void onFocusChanged(boolean z, int i, Rect rect) {
        int i2;
        int iP = this.d0;
        super.onFocusChanged(z, i, rect);
        if (this.c0 == null) {
            return;
        }
        ValueAnimator valueAnimator = this.e0;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        try {
            i2 = this.c0.getInt(this);
        } catch (IllegalAccessException unused) {
            this.c0 = null;
            i2 = iP;
        }
        if (z) {
            iP += wt2.p(4);
        }
        if (i2 == iP) {
            return;
        }
        ValueAnimator valueAnimatorOfInt = ValueAnimator.ofInt(i2, iP);
        this.e0 = valueAnimatorOfInt;
        valueAnimatorOfInt.setDuration(150L);
        this.e0.setInterpolator(new PathInterpolator(0.2f, 0.0f, 0.0f, 1.0f));
        this.e0.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.brouken.player.a
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator2) {
                int i3 = CustomDefaultTimeBar.z0;
                int iIntValue = ((Integer) valueAnimator2.getAnimatedValue()).intValue();
                CustomDefaultTimeBar customDefaultTimeBar = this.a;
                Field field = customDefaultTimeBar.c0;
                if (field == null) {
                    return;
                }
                try {
                    field.setInt(customDefaultTimeBar, iIntValue);
                    customDefaultTimeBar.requestLayout();
                } catch (IllegalAccessException unused2) {
                    customDefaultTimeBar.c0 = null;
                }
            }
        });
        this.e0.start();
    }

    /* JADX WARN: Code duplicated, block: B:23:0x0061  */
    @Override // defpackage.l50, android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        MotionEvent motionEventObtainNoHistory;
        Rect rect;
        int action = motionEvent.getAction();
        Rect rect2 = this.a0;
        if (action != 0 || rect2 == null) {
            if (!this.f0 && rect2 != null && (motionEvent.getAction() == 2 || motionEvent.getAction() == 1)) {
                int iAbs = Math.abs(((int) motionEvent.getX()) - this.g0);
                if (motionEvent.getAction() == 2 || iAbs > wt2.p(6)) {
                    this.f0 = true;
                    motionEventObtainNoHistory = MotionEvent.obtainNoHistory(motionEvent);
                    motionEventObtainNoHistory.setAction(0);
                    rect = this.b0;
                    if (rect != null) {
                        motionEventObtainNoHistory.setLocation(Math.min(Math.max(motionEvent.getX(), rect.left), rect.right - 1), rect.centerY());
                    }
                    super.onTouchEvent(motionEventObtainNoHistory);
                    motionEventObtainNoHistory.recycle();
                }
            }
            return super.onTouchEvent(motionEvent);
        }
        this.f0 = false;
        int x = (int) motionEvent.getX();
        this.g0 = x;
        if (Math.abs(rect2.right - x) <= wt2.p(24)) {
            this.f0 = true;
            if (!this.f0) {
                int iAbs2 = Math.abs(((int) motionEvent.getX()) - this.g0);
                if (motionEvent.getAction() == 2) {
                }
                this.f0 = true;
                motionEventObtainNoHistory = MotionEvent.obtainNoHistory(motionEvent);
                motionEventObtainNoHistory.setAction(0);
                rect = this.b0;
                if (rect != null) {
                    motionEventObtainNoHistory.setLocation(Math.min(Math.max(motionEvent.getX(), rect.left), rect.right - 1), rect.centerY());
                }
                super.onTouchEvent(motionEventObtainNoHistory);
                motionEventObtainNoHistory.recycle();
            }
            return super.onTouchEvent(motionEvent);
        }
        return true;
    }

    @Override // defpackage.l50
    public final void setBufferedColor(int i) {
        super.setBufferedColor(i);
        this.k0.setColor(i);
    }

    @Override // defpackage.l50
    public final void setPlayedColor(int i) {
        super.setPlayedColor(i);
        this.i0.setColor(i);
        this.j0.setColor(eu.f(i, (Color.alpha(i) * 45) / 100));
    }

    @Override // defpackage.l50
    public final void setScrubberColor(int i) {
        super.setScrubberColor(i);
        this.m0.setColor(i);
    }

    @Override // defpackage.l50
    public final void setUnplayedColor(int i) {
        super.setUnplayedColor(i);
        this.l0.setColor(i);
    }

    @Override // android.view.View
    public final void setTranslationY(float f) {
    }
}
