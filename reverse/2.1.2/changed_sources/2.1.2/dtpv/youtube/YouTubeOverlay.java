package com.brouken.player.dtpv.youtube;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.media3.exoplayer.ExoPlayer;
import com.brouken.player.PlayerActivity;
import com.brouken.player.dtpv.DoubleTapPlayerView;
import com.brouken.player.dtpv.youtube.views.CircleClipTapView;
import com.brouken.player.dtpv.youtube.views.SecondsView;
import com.justplus.player.R;
import defpackage.a92;
import defpackage.aw1;
import defpackage.bs1;
import defpackage.f4;
import defpackage.n90;
import defpackage.si;
import defpackage.u03;
import defpackage.vg0;
import defpackage.yw;

/* JADX INFO: compiled from: r8-map-id-d5d7d661e67c62f2588b5a5666a7a20281e417b1121a202706e8fa77676a2bdd */
/* JADX INFO: loaded from: classes.dex */
public final class YouTubeOverlay extends ConstraintLayout implements bs1 {
    public final int B;
    public DoubleTapPlayerView C;
    public ExoPlayer D;
    public si E;
    public final int F;
    public int G;

    public YouTubeOverlay(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.B = -1;
        LayoutInflater.from(context).inflate(R.layout.yt_overlay, (ViewGroup) this, true);
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, aw1.c, 0, 0);
            this.B = typedArrayObtainStyledAttributes.getResourceId(5, -1);
            setAnimationDuration(typedArrayObtainStyledAttributes.getInt(0, 650));
            this.F = typedArrayObtainStyledAttributes.getInt(6, 10);
            setIconAnimationDuration(typedArrayObtainStyledAttributes.getInt(4, 750));
            setArcSize(typedArrayObtainStyledAttributes.getDimensionPixelSize(1, getContext().getResources().getDimensionPixelSize(R.dimen.dtpv_yt_arc_size)));
            setTapCircleColor(typedArrayObtainStyledAttributes.getColor(7, getContext().getColor(R.color.dtpv_yt_tap_circle_color)));
            setCircleBackgroundColor(typedArrayObtainStyledAttributes.getColor(2, getContext().getColor(R.color.dtpv_yt_background_circle_color)));
            setTextAppearance(typedArrayObtainStyledAttributes.getResourceId(8, R.style.YTOSecondsTextAppearance));
            setIcon(typedArrayObtainStyledAttributes.getResourceId(3, R.drawable.ic_play_triangle));
            typedArrayObtainStyledAttributes.recycle();
        } else {
            setArcSize(getContext().getResources().getDimensionPixelSize(R.dimen.dtpv_yt_arc_size));
            setTapCircleColor(getContext().getColor(R.color.dtpv_yt_tap_circle_color));
            setCircleBackgroundColor(getContext().getColor(R.color.dtpv_yt_background_circle_color));
            setAnimationDuration(650L);
            setIconAnimationDuration(750L);
            this.F = 10;
            setTextAppearance(R.style.YTOSecondsTextAppearance);
        }
        ((SecondsView) findViewById(R.id.seconds_view)).setForward(true);
        m(true);
        ((CircleClipTapView) findViewById(R.id.circle_clip_tap_view)).setPerformAtEnd(new f4(this, (byte) 24));
    }

    private void setAnimationDuration(long j) {
        ((CircleClipTapView) findViewById(R.id.circle_clip_tap_view)).setAnimationDuration(j);
    }

    private void setArcSize(float f) {
        ((CircleClipTapView) findViewById(R.id.circle_clip_tap_view)).setArcSize(f);
    }

    private final void setCircleBackgroundColor(int i) {
        ((CircleClipTapView) findViewById(R.id.circle_clip_tap_view)).setCircleBackgroundColor(i);
    }

    private void setIcon(int i) {
        ((SecondsView) findViewById(R.id.seconds_view)).setIcon(i);
    }

    private void setIconAnimationDuration(long j) {
        ((SecondsView) findViewById(R.id.seconds_view)).setCycleDuration(j);
    }

    private void setTapCircleColor(int i) {
        ((CircleClipTapView) findViewById(R.id.circle_clip_tap_view)).setCircleColor(i);
    }

    private final void setTextAppearance(int i) {
        ((SecondsView) findViewById(R.id.seconds_view)).getTextView().setTextAppearance(i);
        this.G = i;
    }

    public final long getAnimationDuration() {
        return ((CircleClipTapView) findViewById(R.id.circle_clip_tap_view)).getAnimationDuration();
    }

    public final float getArcSize() {
        return ((CircleClipTapView) findViewById(R.id.circle_clip_tap_view)).getArcSize();
    }

    public final int getCircleBackgroundColor() {
        return ((CircleClipTapView) findViewById(R.id.circle_clip_tap_view)).getCircleBackgroundColor();
    }

    public final int getIcon() {
        return ((SecondsView) findViewById(R.id.seconds_view)).getIcon();
    }

    public final long getIconAnimationDuration() {
        return ((SecondsView) findViewById(R.id.seconds_view)).getCycleDuration();
    }

    public final TextView getSecondsTextView() {
        return ((SecondsView) findViewById(R.id.seconds_view)).getTextView();
    }

    public final int getSeekSeconds() {
        return this.F;
    }

    public int getTapCircleColor() {
        return ((CircleClipTapView) findViewById(R.id.circle_clip_tap_view)).getCircleColor();
    }

    public final int getTextAppearance() {
        return this.G;
    }

    public final void m(boolean z) {
        yw ywVar = new yw();
        ywVar.c((ConstraintLayout) findViewById(R.id.root_constraint_layout));
        SecondsView secondsView = (SecondsView) findViewById(R.id.seconds_view);
        if (z) {
            ywVar.b(secondsView.getId(), 6);
            ywVar.d(secondsView.getId(), 7, 7);
        } else {
            ywVar.b(secondsView.getId(), 7);
            ywVar.d(secondsView.getId(), 6, 6);
        }
        ConstraintLayout constraintLayout = (ConstraintLayout) findViewById(R.id.root_constraint_layout);
        ywVar.a(constraintLayout);
        constraintLayout.setConstraintSet(null);
        constraintLayout.requestLayout();
    }

    public final void n(float f, float f2) {
        ExoPlayer exoPlayer;
        DoubleTapPlayerView doubleTapPlayerView;
        if (!PlayerActivity.R6 && (exoPlayer = this.D) != null && ((vg0) exoPlayer).a1() >= 1 && ((vg0) this.D).O0() >= 0 && (doubleTapPlayerView = this.C) != null && doubleTapPlayerView.getWidth() >= 0) {
            long jO0 = ((vg0) this.D).O0();
            double d = f;
            if (d >= ((double) this.C.getWidth()) * 0.35d || jO0 > 500) {
                if (d <= ((double) this.C.getWidth()) * 0.65d || jO0 < ((vg0) this.D).getDuration() - 500) {
                    if (getVisibility() != 0) {
                        if (d >= ((double) this.C.getWidth()) * 0.35d && d <= ((double) this.C.getWidth()) * 0.65d) {
                            return;
                        }
                        si siVar = this.E;
                        if (siVar != null) {
                            PlayerActivity playerActivity = siVar.l;
                            playerActivity.E.setAlpha(1.0f);
                            playerActivity.E.setVisibility(0);
                        }
                        SecondsView secondsView = (SecondsView) findViewById(R.id.seconds_view);
                        secondsView.setVisibility(0);
                        secondsView.m();
                        secondsView.F = true;
                        secondsView.G.start();
                    }
                    double width = ((double) this.C.getWidth()) * 0.35d;
                    int i = this.F;
                    if (d < width) {
                        SecondsView secondsView2 = (SecondsView) findViewById(R.id.seconds_view);
                        if (secondsView2.D) {
                            m(false);
                            secondsView2.setForward(false);
                            secondsView2.setSeconds(0);
                        }
                        ((CircleClipTapView) findViewById(R.id.circle_clip_tap_view)).a(new u03(this, f, f2, (byte) 0));
                        SecondsView secondsView3 = (SecondsView) findViewById(R.id.seconds_view);
                        secondsView3.setSeconds(secondsView3.getSeconds() + i);
                        ExoPlayer exoPlayer2 = this.D;
                        o((exoPlayer2 != null ? Long.valueOf(((vg0) exoPlayer2).O0() - ((long) (i * 1000))) : null).longValue());
                        return;
                    }
                    if (d > ((double) this.C.getWidth()) * 0.65d) {
                        SecondsView secondsView4 = (SecondsView) findViewById(R.id.seconds_view);
                        if (!secondsView4.D) {
                            m(true);
                            secondsView4.setForward(true);
                            secondsView4.setSeconds(0);
                        }
                        ((CircleClipTapView) findViewById(R.id.circle_clip_tap_view)).a(new u03(this, f, f2, (byte) 1));
                        SecondsView secondsView5 = (SecondsView) findViewById(R.id.seconds_view);
                        secondsView5.setSeconds(secondsView5.getSeconds() + i);
                        ExoPlayer exoPlayer3 = this.D;
                        o((exoPlayer3 != null ? Long.valueOf(((vg0) exoPlayer3).O0() + ((long) (i * 1000))) : null).longValue());
                    }
                }
            }
        }
    }

    public final void o(long j) {
        ExoPlayer exoPlayer = this.D;
        if (exoPlayer == null || this.C == null) {
            return;
        }
        ((vg0) exoPlayer).t1(a92.c);
        ExoPlayer exoPlayer2 = this.D;
        if (j <= 0) {
            ((vg0) exoPlayer2).o1(0L);
            return;
        }
        long duration = ((vg0) exoPlayer2).getDuration();
        if (j >= duration) {
            ((vg0) this.D).o1(duration);
            return;
        }
        n90 n90Var = this.C.K0;
        n90Var.o = true;
        Handler handler = n90Var.l;
        f4 f4Var = n90Var.m;
        handler.removeCallbacks(f4Var);
        handler.postDelayed(f4Var, n90Var.p);
        ((vg0) this.D).o1(j);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.B != -1) {
            this.C = (DoubleTapPlayerView) ((View) getParent()).findViewById(this.B);
        }
    }

    public YouTubeOverlay(Context context) {
        this(context, null);
        setVisibility(4);
    }
}
