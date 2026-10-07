package defpackage;

import android.view.View;
import android.view.Window;
import android.view.WindowInsetsController;

/* JADX INFO: compiled from: r8-map-id-0cee80150bab987b3edc91bcfc1e04786a1418881e5c92a3b999d44d61001263 */
/* JADX INFO: loaded from: classes.dex */
public class j03 extends ck0 {
    public final WindowInsetsController s;
    public final Window t;

    public j03(Window window, nf1 nf1Var) {
        this.s = window.getInsetsController();
        this.t = window;
    }

    @Override // defpackage.ck0
    public final void D() {
        this.s.hide(1);
    }

    @Override // defpackage.ck0
    public final void Y(boolean z) {
        Window window = this.t;
        if (z) {
            if (window != null) {
                j0(16);
            }
            this.s.setSystemBarsAppearance(16, 16);
        } else {
            if (window != null) {
                k0(16);
            }
            this.s.setSystemBarsAppearance(0, 16);
        }
    }

    @Override // defpackage.ck0
    public final void Z(boolean z) {
        Window window = this.t;
        if (z) {
            if (window != null) {
                j0(8192);
            }
            this.s.setSystemBarsAppearance(8, 8);
        } else {
            if (window != null) {
                k0(8192);
            }
            this.s.setSystemBarsAppearance(0, 8);
        }
    }

    @Override // defpackage.ck0
    public void d0() {
        Window window = this.t;
        if (window == null) {
            this.s.setSystemBarsBehavior(2);
            return;
        }
        window.getDecorView().setTag(356039078, 2);
        k0(2048);
        j0(4096);
    }

    public final void j0(int i) {
        View decorView = this.t.getDecorView();
        decorView.setSystemUiVisibility(i | decorView.getSystemUiVisibility());
    }

    public final void k0(int i) {
        View decorView = this.t.getDecorView();
        decorView.setSystemUiVisibility((~i) & decorView.getSystemUiVisibility());
    }
}
