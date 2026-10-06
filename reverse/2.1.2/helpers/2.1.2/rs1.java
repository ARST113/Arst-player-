package defpackage;

import android.net.Uri;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-d5d7d661e67c62f2588b5a5666a7a20281e417b1121a202706e8fa77676a2bdd */
/* JADX INFO: loaded from: classes.dex */
public final class rs1 {
    public final Uri a;
    public final String b;
    public final String c;
    public final Uri d;
    public final Uri e;
    public final Uri f;
    public final String g;
    public final String h;
    public final int i;
    public final int j;
    public final String[] k;
    public final long l;
    public final long m;
    public final long n;
    public final vp2 o;
    public final vp2 p;
    public final String q;
    public final List r;
    public final List s;
    public final List t;
    public final boolean u;
    public final List v;

    public rs1(Uri uri, String str, String str2, Uri uri2, Uri uri3, Uri uri4, String str3, String str4, int i, int i2, String[] strArr, long j, long j2, long j3, vp2 vp2Var, vp2 vp2Var2, String str5, List list, List list2, List list3, boolean z, List list4) {
        this.a = uri;
        this.b = str;
        this.c = str2;
        this.d = uri2;
        this.e = uri3;
        this.f = uri4;
        this.g = str3;
        this.h = str4;
        this.i = i;
        this.j = i2;
        this.k = strArr;
        this.l = j;
        this.m = j2;
        this.n = j3;
        this.o = vp2Var;
        this.p = vp2Var2;
        this.q = str5;
        this.r = list;
        this.s = list2;
        this.t = list3;
        this.u = z;
        this.v = list4;
    }

    public final int a(String str) {
        int i = 0;
        while (true) {
            List list = this.t;
            if (i >= list.size()) {
                return -1;
            }
            us1 us1Var = (us1) list.get(i);
            if (!us1Var.b.toString().equals(str)) {
                Iterator it = us1Var.c.iterator();
                while (it.hasNext()) {
                    if (((ss1) it.next()).b.equals(str)) {
                    }
                }
                i++;
            }
            return i;
        }
    }
}
