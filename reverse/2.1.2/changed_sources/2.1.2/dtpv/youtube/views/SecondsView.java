package com.brouken.player.dtpv.youtube.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.justplus.player.R;
import defpackage.o82;
import defpackage.p82;
import defpackage.r82;

/* JADX INFO: compiled from: r8-map-id-d5d7d661e67c62f2588b5a5666a7a20281e417b1121a202706e8fa77676a2bdd */
/* JADX INFO: loaded from: classes.dex */
public final class SecondsView extends ConstraintLayout {
    public long B;
    public int C;
    public boolean D;
    public int E;
    public boolean F;
    public final r82 G;
    public final r82 H;
    public final r82 I;
    public final r82 J;
    public final r82 K;

    public SecondsView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.B = 750L;
        this.C = 0;
        this.D = true;
        this.E = R.drawable.ic_play_triangle;
        this.F = false;
        LayoutInflater.from(context).inflate(R.layout.yt_seconds_view, (ViewGroup) this, true);
        this.G = new r82(this, new o82(this, (byte) 4), new p82(this, (byte) 2), new o82(this, (byte) 5));
        this.H = new r82(this, new o82(this, (byte) 6), new p82(this, (byte) 3), new o82(this, (byte) 7));
        this.I = new r82(this, new o82(this, (byte) 8), new p82(this, (byte) 4), new o82(this, (byte) 9));
        this.J = new r82(this, new o82(this, (byte) 0), new p82(this, (byte) 0), new o82(this, (byte) 1));
        this.K = new r82(this, new o82(this, (byte) 2), new p82(this, (byte) 1), new o82(this, (byte) 3));
    }

    public final long getCycleDuration() {
        return this.B;
    }

    public final int getIcon() {
        return this.E;
    }

    public final int getSeconds() {
        return this.C;
    }

    public final TextView getTextView() {
        return (TextView) findViewById(R.id.tv_seconds);
    }

    public final void m() {
        this.F = false;
        this.G.cancel();
        this.H.cancel();
        this.I.cancel();
        this.J.cancel();
        this.K.cancel();
        findViewById(R.id.icon_1).setAlpha(0.0f);
        findViewById(R.id.icon_2).setAlpha(0.0f);
        findViewById(R.id.icon_3).setAlpha(0.0f);
    }

    public final void setCycleDuration(long j) {
        long j2 = j / 5;
        this.G.setDuration(j2);
        this.H.setDuration(j2);
        this.I.setDuration(j2);
        this.J.setDuration(j2);
        this.K.setDuration(j2);
        this.B = j;
    }

    public final void setForward(boolean z) {
        ((LinearLayout) findViewById(R.id.triangle_container)).setRotation(z ? 0.0f : 180.0f);
        this.D = z;
    }

    public final void setIcon(int i) {
        if (i > 0) {
            ((ImageView) findViewById(R.id.icon_1)).setImageResource(i);
            ((ImageView) findViewById(R.id.icon_2)).setImageResource(i);
            ((ImageView) findViewById(R.id.icon_3)).setImageResource(i);
        }
        this.E = i;
    }

    public final void setSeconds(int i) {
        ((TextView) findViewById(R.id.tv_seconds)).setText(getContext().getResources().getQuantityString(R.plurals.quick_seek_x_second, i, Integer.valueOf(i)));
        this.C = i;
    }
}
