package com.brouken.player.dtpv;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import androidx.core.view.GestureDetectorCompat;
import defpackage.f90;
import defpackage.pv1;
import defpackage.qr1;
import defpackage.ty;

/* JADX INFO: compiled from: r8-map-id-ba8d2c2760819bd03a19aa4133a73a2576203ef152dace5b65b91a4c4fd30466 */
/* JADX INFO: loaded from: classes.dex */
public class DoubleTapPlayerView extends ty {
    public final GestureDetectorCompat J0;
    public final f90 K0;
    public final int L0;
    public boolean M0;

    public DoubleTapPlayerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.L0 = -1;
        f90 f90Var = new f90(this);
        this.K0 = f90Var;
        this.J0 = new GestureDetectorCompat(context, f90Var);
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, pv1.b, 0, 0);
            this.L0 = typedArrayObtainStyledAttributes != null ? typedArrayObtainStyledAttributes.getResourceId(0, -1) : -1;
            if (typedArrayObtainStyledAttributes != null) {
                typedArrayObtainStyledAttributes.recycle();
            }
        }
        this.M0 = true;
    }

    private final qr1 getController() {
        return this.K0.n;
    }

    private final void setController(qr1 qr1Var) {
        this.K0.n = qr1Var;
    }

    public final long getDoubleTapDelay() {
        return this.K0.p;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        int i = this.L0;
        if (i != -1) {
            try {
                KeyEvent.Callback callbackFindViewById = ((View) getParent()).findViewById(i);
                if (callbackFindViewById instanceof qr1) {
                    setController((qr1) callbackFindViewById);
                }
            } catch (Exception e) {
                e.printStackTrace();
                Log.e("DoubleTapPlayerView", "controllerRef is either invalid or not PlayerDoubleTapListener: ${e.message}");
            }
        }
    }

    @Override // defpackage.ty, android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        if (!this.M0) {
            super.onTouchEvent(motionEvent);
            return true;
        }
        if (!this.J0.a.onTouchEvent(motionEvent)) {
            super.onTouchEvent(motionEvent);
        }
        return true;
    }

    public final void setDoubleTapDelay(long j) {
        this.K0.p = j;
    }

    public final void setDoubleTapEnabled(boolean z) {
        this.M0 = z;
    }

    public DoubleTapPlayerView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public DoubleTapPlayerView(Context context) {
        this(context, null);
    }
}
