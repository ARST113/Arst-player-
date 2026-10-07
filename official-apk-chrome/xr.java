package defpackage;

import android.app.Activity;
import android.content.res.ColorStateList;
import android.view.ContextThemeWrapper;
import com.justplus.player.R;

/* JADX INFO: compiled from: r8-map-id-0cee80150bab987b3edc91bcfc1e04786a1418881e5c92a3b999d44d61001263 */
/* JADX INFO: loaded from: classes.dex */
public final class xr {
    public final boolean a;
    public final int b;
    public final int c;
    public final int d;
    public final int e;
    public final int f;
    public final int g;
    public final int h;
    public final int i;
    public final int j;
    public final int k;
    public final int l;
    public final int m;
    public final int n;
    public final int o;
    public final ColorStateList p;

    public xr(Activity activity, boolean z) {
        boolean z2 = !z && hu1.m(activity);
        this.a = z2;
        boolean z3 = (z2 || z || !hu1.l(activity)) ? false : true;
        int iN = z2 ? sj.n(new ContextThemeWrapper(activity, hu1.a(activity, true)), R.attr.accentFill, -16777216) : sj.n(new ContextThemeWrapper(activity, hu1.a(activity, false)), R.attr.accentInk, -1);
        this.n = iN;
        int i = -419430401;
        if (z2) {
            this.b = -419430401;
            this.c = 335544320;
            this.d = -723723;
            i = -570425344;
            this.e = -570425344;
            this.f = -1291845632;
            this.g = -1979711488;
            this.h = -872415232;
            this.i = -1;
            this.j = -14935265;
            this.k = -3552820;
            this.l = -7434605;
            this.m = -4342335;
            this.o = -1;
        } else {
            this.b = z3 ? -16777216 : -872415232;
            this.c = 452984831;
            this.d = z3 ? -16777216 : -15461356;
            this.e = -419430401;
            this.f = -1275068417;
            this.g = -1711276033;
            this.h = -1023410177;
            this.i = -16777216;
            this.j = -1;
            this.k = -13092548;
            this.l = -1056964609;
            this.m = 872415231;
            this.o = -16777216;
        }
        this.p = new ColorStateList(new int[][]{new int[]{android.R.attr.state_selected}, new int[0]}, new int[]{iN, i});
    }
}
