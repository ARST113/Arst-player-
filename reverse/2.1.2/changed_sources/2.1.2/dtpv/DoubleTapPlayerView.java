package com.brouken.player.dtpv;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import androidx.core.view.GestureDetectorCompat;
import defpackage.aw1;
import defpackage.bs1;
import defpackage.cz;
import defpackage.n90;

/* JADX INFO: compiled from: r8-map-id-d5d7d661e67c62f2588b5a5666a7a20281e417b1121a202706e8fa77676a2bdd */
/* JADX INFO: loaded from: classes.dex */
public class DoubleTapPlayerView extends cz {
    public final GestureDetectorCompat J0;
    public final n90 K0;
    public final int L0;
    public boolean M0;

    public DoubleTapPlayerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.L0 = -1;
        n90 n90Var = new n90(this);
        this.K0 = n90Var;
        this.J0 = new GestureDetectorCompat(context, n90Var);
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, aw1.b, 0, 0);
            this.L0 = typedArrayObtainStyledAttributes != null ? typedArrayObtainStyledAttributes.getResourceId(0, -1) : -1;
            if (typedArrayObtainStyledAttributes != null) {
                typedArrayObtainStyledAttributes.recycle();
            }
        }
        this.M0 = true;
    }

    private final bs1 getController() {
        return this.K0.n;
    }

    private final void setController(bs1 bs1Var) {
        this.K0.n = bs1Var;
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
                if (callbackFindViewById instanceof bs1) {
                    setController((bs1) callbackFindViewById);
                }
            } catch (Exception e) {
                e.printStackTrace();
                Log.e("DoubleTapPlayerView", "controllerRef is either invalid or not PlayerDoubleTapListener: ${e.message}");
            }
        }
    }

    @Override // defpackage.cz, android.view.View
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
