package com.brouken.player;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.animation.LinearInterpolator;
import com.justplus.player.R;
import defpackage.ah;
import defpackage.gu1;
import defpackage.sj;
import defpackage.wt2;

/* JADX INFO: compiled from: r8-map-id-d5d7d661e67c62f2588b5a5666a7a20281e417b1121a202706e8fa77676a2bdd */
/* JADX INFO: loaded from: classes.dex */
public class BrandWash extends View {
    public static final /* synthetic */ int s = 0;
    public final Paint l;
    public final Matrix m;
    public final int n;
    public final int o;
    public RadialGradient p;
    public ValueAnimator q;
    public float r;

    public BrandWash(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.l = new Paint(1);
        this.m = new Matrix();
        this.r = 0.25f;
        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(context, gu1.a(context, false));
        this.n = sj.n(contextThemeWrapper, R.attr.accentContainer, context.getColor(R.color.brand_container));
        this.o = sj.n(contextThemeWrapper, R.attr.accentGround, context.getColor(R.color.black));
    }

    @Override // android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (wt2.C(getContext())) {
            return;
        }
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        this.q = valueAnimatorOfFloat;
        valueAnimatorOfFloat.setDuration(5000L);
        this.q.setRepeatCount(-1);
        this.q.setRepeatMode(2);
        this.q.setInterpolator(new LinearInterpolator());
        this.q.addUpdateListener(new ah(this, (byte) 1));
        this.q.start();
    }

    @Override // android.view.View
    public final void onDetachedFromWindow() {
        ValueAnimator valueAnimator = this.q;
        if (valueAnimator != null) {
            valueAnimator.cancel();
            this.q = null;
        }
        super.onDetachedFromWindow();
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        if (this.p == null) {
            return;
        }
        float width = getWidth() - (getWidth() * this.r);
        float height = getHeight() * this.r;
        Matrix matrix = this.m;
        matrix.setTranslate(width, height);
        this.p.setLocalMatrix(matrix);
        canvas.drawRect(0.0f, 0.0f, getWidth(), getHeight(), this.l);
    }

    @Override // android.view.View
    public final void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        RadialGradient radialGradient = new RadialGradient(0.0f, 0.0f, Math.max(Math.max(i, i2) * 1.1f, 1.0f), this.n, this.o, Shader.TileMode.CLAMP);
        this.p = radialGradient;
        this.l.setShader(radialGradient);
    }
}
