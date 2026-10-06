package defpackage;

import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-d5d7d661e67c62f2588b5a5666a7a20281e417b1121a202706e8fa77676a2bdd */
/* JADX INFO: loaded from: classes.dex */
public final class ge2 {
    public je2 a;
    public List b;
    public List c;
    public double d;

    public static long b(long j, List list) {
        if (j <= 0) {
            return Long.MAX_VALUE;
        }
        long j2 = j - (j / 20);
        if (list == null) {
            return j2;
        }
        Iterator it = list.iterator();
        long jMin = j2;
        while (it.hasNext()) {
            ie2 ie2Var = (ie2) it.next();
            int i = ie2Var.c;
            double d = ie2Var.a;
            if (i == 1 && ie2Var.a() >= j2 && ie2Var.a() - Math.round(d * 1000.0d) <= j * 0.15d) {
                jMin = Math.min(jMin, Math.round(d * 1000.0d));
            }
        }
        return jMin;
    }

    public final ie2 a(double d) {
        for (ie2 ie2Var : this.c) {
            if (!ie2Var.g && d >= ie2Var.a && d < ie2Var.b - 1.0d) {
                return ie2Var;
            }
        }
        return null;
    }
}
