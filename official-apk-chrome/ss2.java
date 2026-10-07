package defpackage;

import android.content.Context;
import android.content.res.Configuration;
import com.brouken.player.PlayerActivity;

/* JADX INFO: compiled from: r8-map-id-0cee80150bab987b3edc91bcfc1e04786a1418881e5c92a3b999d44d61001263 */
/* JADX INFO: loaded from: classes.dex */
public final class ss2 {
    public final int a;
    public final float b;
    public final float c;
    public final int d;
    public final int e;

    public ss2(Context context, boolean z) {
        Configuration configuration = context.getResources().getConfiguration();
        this.b = context.getResources().getDisplayMetrics().density;
        this.d = configuration.screenWidthDp;
        this.e = configuration.orientation;
        if (z) {
            this.a = 4;
            this.c = 1.3f;
            return;
        }
        int i = configuration.smallestScreenWidthDp;
        if (i >= 720) {
            this.a = 3;
            this.c = 1.25f;
        } else if (i >= 600) {
            this.a = 2;
            this.c = 1.15f;
        } else {
            this.a = 1;
            this.c = 1.0f;
        }
    }

    public static ss2 c(PlayerActivity playerActivity, boolean z) {
        return new ss2(playerActivity, z);
    }

    public final int a(float f) {
        return Math.round(f * this.b);
    }

    public final int b(float f) {
        return Math.round(f * this.c * this.b);
    }

    public final int d() {
        if (z()) {
            return a(48.0f);
        }
        return 0;
    }

    public final int e() {
        if (z()) {
            return a(27.0f);
        }
        return 0;
    }

    public final int f(Configuration configuration) {
        int i = configuration.screenWidthDp;
        if (i < 600) {
            return a(Math.min(i - 8, 640));
        }
        return Math.min(d() + a(360.0f), a(i - 56));
    }

    public final int g() {
        return z() ? a(40.0f) : b(48.0f);
    }

    public final int h() {
        return a(28.0f);
    }

    public final int i() {
        return z() ? a(24.0f) : b(22.0f);
    }

    public final int j() {
        return z() ? a(20.0f) : b(16.0f);
    }

    public final float k() {
        return (a(48.0f) * 3.0f) / 7.0f;
    }

    public final int l() {
        return z() ? a(28.0f) : b(24.0f);
    }

    public final int m() {
        int iU = lf2.u(this.a);
        if (iU == 1) {
            return a(80.0f);
        }
        if (iU != 2) {
            return iU != 3 ? a(74.0f) : a(96.0f);
        }
        return a(88.0f);
    }

    public final int n() {
        return (b(60.0f) * 6) / 5;
    }

    public final int o() {
        int iU = lf2.u(this.a);
        if (iU == 1 || iU == 2) {
            return a(52.0f);
        }
        return iU != 3 ? a(48.0f) : a(56.0f);
    }

    public final int p() {
        int iU = lf2.u(this.a);
        if (iU == 1 || iU == 2) {
            return a(190.0f);
        }
        return iU != 3 ? a(150.0f) : a(170.0f);
    }

    public final float q(float f, float f2, float f3, float f4) {
        int iU = lf2.u(this.a);
        if (iU == 1) {
            return f2;
        }
        if (iU != 2) {
            return iU != 3 ? f : f4;
        }
        return f3;
    }

    public final float r() {
        return q(15.0f, 16.0f, 16.0f, 18.0f);
    }

    public final float s() {
        return q(16.0f, 17.0f, 18.0f, 20.0f);
    }

    public final float t() {
        return q(13.0f, 14.0f, 15.0f, 16.0f);
    }

    public final float u() {
        return q(22.0f, 24.0f, 25.0f, 26.0f);
    }

    public final float v() {
        return q(12.0f, 13.0f, 13.0f, 14.0f);
    }

    public final float w() {
        return q(20.0f, 21.0f, 22.0f, 22.0f);
    }

    public final float x() {
        return q(14.0f, 15.0f, 15.0f, 16.0f);
    }

    public final float y() {
        return q(18.0f, 20.0f, 21.0f, 22.0f);
    }

    public final boolean z() {
        return this.a == 4;
    }
}
