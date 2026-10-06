package com.brouken.player.dtpv.youtube.views;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import com.justplus.player.R;
import defpackage.ks;
import defpackage.lj;
import defpackage.ls;

/* JADX INFO: compiled from: r8-map-id-d5d7d661e67c62f2588b5a5666a7a20281e417b1121a202706e8fa77676a2bdd */
/* JADX INFO: loaded from: classes.dex */
public final class CircleClipTapView extends View {
    public static final /* synthetic */ int A = 0;
    public final Paint l;
    public final Paint m;
    public int n;
    public int o;
    public final Path p;
    public boolean q;
    public float r;
    public float s;
    public float t;
    public final int u;
    public final int v;
    public ValueAnimator w;
    public boolean x;
    public float y;
    public Runnable z;

    public CircleClipTapView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Paint paint = new Paint();
        this.l = paint;
        Paint paint2 = new Paint();
        this.m = paint2;
        this.n = 0;
        this.o = 0;
        this.p = new Path();
        this.q = true;
        this.r = 0.0f;
        this.s = 0.0f;
        this.t = 0.0f;
        this.u = 0;
        this.v = 0;
        this.w = null;
        this.x = false;
        Paint.Style style = Paint.Style.FILL;
        paint.setStyle(style);
        paint.setAntiAlias(true);
        paint.setColor(context.getColor(R.color.dtpv_yt_background_circle_color));
        paint2.setStyle(style);
        paint2.setAntiAlias(true);
        paint2.setColor(context.getColor(R.color.dtpv_yt_tap_circle_color));
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        this.n = displayMetrics.widthPixels;
        this.o = displayMetrics.heightPixels;
        float f = displayMetrics.density;
        this.u = (int) (30.0f * f);
        this.v = (int) (f * 400.0f);
        b();
        this.w = getCircleAnimator();
        this.y = 80.0f;
        this.z = new ks((byte) 0);
    }

    private final ValueAnimator getCircleAnimator() {
        if (this.w == null) {
            ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
            this.w = valueAnimatorOfFloat;
            valueAnimatorOfFloat.setDuration(getAnimationDuration());
            this.w.addUpdateListener(new lj(this, (byte) 1));
            this.w.addListener(new ls(this));
        }
        return this.w;
    }

    public final void a(Runnable runnable) {
        this.x = true;
        getCircleAnimator().end();
        runnable.run();
        this.x = false;
        getCircleAnimator().start();
    }

    public final void b() {
        float f = this.n * 0.5f;
        Path path = this.p;
        path.reset();
        boolean z = this.q;
        float f2 = z ? 0.0f : this.n;
        int i = z ? 1 : -1;
        path.moveTo(f2, 0.0f);
        float f3 = i;
        path.lineTo(((f - this.y) * f3) + f2, 0.0f);
        float f4 = this.y;
        int i2 = this.o;
        path.quadTo(((f + f4) * f3) + f2, i2 / 2.0f, ((f - f4) * f3) + f2, i2);
        path.lineTo(f2, this.o);
        path.close();
        invalidate();
    }

    public final void c(float f, float f2) {
        this.r = f;
        this.s = f2;
        boolean z = f <= ((float) (getResources().getDisplayMetrics().widthPixels / 2));
        if (this.q != z) {
            this.q = z;
            b();
        }
    }

    public final long getAnimationDuration() {
        ValueAnimator valueAnimator = this.w;
        if (valueAnimator != null) {
            return valueAnimator.getDuration();
        }
        return 650L;
    }

    public final float getArcSize() {
        return this.y;
    }

    public final int getCircleBackgroundColor() {
        return this.l.getColor();
    }

    public final int getCircleColor() {
        return this.m.getColor();
    }

    public final Runnable getPerformAtEnd() {
        return this.z;
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Path path = this.p;
        if (canvas != null) {
            canvas.clipPath(path);
        }
        if (canvas != null) {
            canvas.drawPath(path, this.l);
        }
        if (canvas != null) {
            canvas.drawCircle(this.r, this.s, this.t, this.m);
        }
    }

    @Override // android.view.View
    public final void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.n = i;
        this.o = i2;
        b();
    }

    public final void setAnimationDuration(long j) {
        getCircleAnimator().setDuration(j);
    }

    public final void setArcSize(float f) {
        this.y = f;
        b();
    }

    public final void setCircleBackgroundColor(int i) {
        this.l.setColor(i);
    }

    public final void setCircleColor(int i) {
        this.m.setColor(i);
    }

    public final void setPerformAtEnd(Runnable runnable) {
        this.z = runnable;
    }
}
