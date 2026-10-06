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
import defpackage.d82;
import defpackage.e82;
import defpackage.g82;

/* JADX INFO: compiled from: r8-map-id-ba8d2c2760819bd03a19aa4133a73a2576203ef152dace5b65b91a4c4fd30466 */
/* JADX INFO: loaded from: classes.dex */
public final class SecondsView extends ConstraintLayout {
    public long B;
    public int C;
    public boolean D;
    public int E;
    public boolean F;
    public final g82 G;
    public final g82 H;
    public final g82 I;
    public final g82 J;
    public final g82 K;

    public SecondsView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.B = 750L;
        this.C = 0;
        this.D = true;
        this.E = R.drawable.ic_play_triangle;
        this.F = false;
        LayoutInflater.from(context).inflate(R.layout.yt_seconds_view, (ViewGroup) this, true);
        this.G = new g82(this, new d82(this, (byte) 4), new e82(this, (byte) 2), new d82(this, (byte) 5));
        this.H = new g82(this, new d82(this, (byte) 6), new e82(this, (byte) 3), new d82(this, (byte) 7));
        this.I = new g82(this, new d82(this, (byte) 8), new e82(this, (byte) 4), new d82(this, (byte) 9));
        this.J = new g82(this, new d82(this, (byte) 0), new e82(this, (byte) 0), new d82(this, (byte) 1));
        this.K = new g82(this, new d82(this, (byte) 2), new e82(this, (byte) 1), new d82(this, (byte) 3));
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
