package defpackage;

import android.net.Uri;
import android.os.Bundle;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;

/* JADX INFO: compiled from: r8-map-id-d5d7d661e67c62f2588b5a5666a7a20281e417b1121a202706e8fa77676a2bdd */
/* JADX INFO: loaded from: classes.dex */
public final class c7 {
    public boolean a;
    public final Object b;
    public final Object c;
    public final Object d;
    public Serializable e;
    public Object f;
    public Object g;
    public Serializable h;
    public Serializable i;

    /* JADX WARN: Type inference failed for: r0v3, types: [java.io.Serializable, java.lang.String[]] */
    /* JADX WARN: Type inference failed for: r0v4, types: [java.io.Serializable, java.lang.String[]] */
    public c7(int i) {
        this.b = new ArrayList();
        this.c = new long[i];
        this.d = new boolean[i];
        this.h = new String[i];
        this.i = new String[i];
        for (int i2 = 0; i2 < i; i2++) {
            ((long[]) this.c)[i2] = -9223372036854775807L;
        }
    }

    public static String d(Object obj) {
        if (obj instanceof Bundle) {
            Bundle bundle = (Bundle) obj;
            StringBuilder sb = new StringBuilder("{");
            for (String str : new TreeSet(bundle.keySet())) {
                sb.append(str);
                sb.append('=');
                sb.append(d(bundle.get(str)));
                sb.append(',');
            }
            sb.append('}');
            return sb.toString();
        }
        if (!(obj instanceof Object[])) {
            if (obj instanceof int[]) {
                return Arrays.toString((int[]) obj);
            }
            return obj instanceof long[] ? Arrays.toString((long[]) obj) : String.valueOf(obj);
        }
        StringBuilder sb2 = new StringBuilder("[");
        for (Object obj2 : (Object[]) obj) {
            sb2.append(d(obj2));
            sb2.append(',');
        }
        sb2.append(']');
        return sb2.toString();
    }

    public static int h(long j) {
        return (int) (Math.max(0L, j) / 1000);
    }

    public static void i(HashMap map, String str, String str2) throws hn1 {
        if (!map.containsKey(str) || str2.equals(map.get(str))) {
            map.put(str, str2);
            return;
        }
        throw hn1.b("The group ID " + str + " is associated with more than one pathway from variants", null);
    }

    public pw0 a(List list, HashMap map) throws hn1 {
        c7 c7Var;
        boolean z = this.a;
        ArrayList arrayList = new ArrayList();
        HashMap map2 = new HashMap();
        HashMap map3 = new HashMap();
        int i = 0;
        while (true) {
            String str = null;
            if (i >= list.size()) {
                break;
            }
            ls0 ls0Var = (ls0) list.get(i);
            Uri uri = ls0Var.a;
            ts0 ts0Var = new ts0(ls0Var.b, ls0Var.e, ls0Var.d);
            if (z && (str = (String) map.get(ls0Var.c)) == null) {
                c7Var = this;
            } else {
                String str2 = str;
                Uri uri2 = ls0Var.a;
                c7Var = this;
                c7Var.g(uri2, str2, i, arrayList, ts0Var, map2, map3);
            }
            i++;
            this = c7Var;
        }
        c7 c7Var2 = this;
        if (z) {
            us0 us0Var = (us0) ((pw0) c7Var2.f).get(0);
            us0Var.getClass();
            bx0 bx0VarA = us0Var.a();
            for (int i2 = 0; i2 < arrayList.size(); i2++) {
                if (!((us0) arrayList.get(i2)).a().equals(bx0VarA)) {
                    throw hn1.b("The set of available pathway IDs of a rendition redundant group is inconsistent with variant redundant groups", null);
                }
            }
        }
        return pw0.l(arrayList);
    }

    public pw0 b(int i) throws hn1 {
        ns0 ns0Var = (ns0) this.b;
        c();
        if (i == 1) {
            pw0 pw0Var = (pw0) this.g;
            if (pw0Var != null) {
                return pw0Var;
            }
            pw0 pw0VarA = a(ns0Var.e, (HashMap) this.c);
            this.g = pw0VarA;
            return pw0VarA;
        }
        if (i == 2) {
            pw0 pw0Var2 = (pw0) this.h;
            if (pw0Var2 != null) {
                return pw0Var2;
            }
            pw0 pw0VarA2 = a(ns0Var.f, (HashMap) this.d);
            this.h = pw0VarA2;
            return pw0VarA2;
        }
        if (i != 3) {
            bl.d("Invalid type for creating rendition redundant group list");
            return null;
        }
        pw0 pw0Var3 = (pw0) this.i;
        if (pw0Var3 != null) {
            return pw0Var3;
        }
        pw0 pw0VarA3 = a(ns0Var.g, (HashMap) this.e);
        this.i = pw0VarA3;
        return pw0VarA3;
    }

    public void c() {
        boolean z = this.a;
        ns0 ns0Var = (ns0) this.b;
        if (((pw0) this.f) == null) {
            ArrayList arrayList = new ArrayList();
            HashMap map = new HashMap();
            HashMap map2 = new HashMap();
            int i = 0;
            while (i < ns0Var.d.size()) {
                ms0 ms0Var = (ms0) ns0Var.d.get(i);
                c7 c7Var = this;
                String strG = c7Var.g(ms0Var.a, ms0Var.g, i, arrayList, new ts0(ms0Var.b, ms0Var.h, null), map, map2);
                if (z) {
                    String str = ms0Var.c;
                    if (str != null) {
                        i((HashMap) c7Var.c, str, strG);
                    }
                    String str2 = ms0Var.d;
                    if (str2 != null) {
                        i((HashMap) c7Var.d, str2, strG);
                    }
                    String str3 = ms0Var.e;
                    if (str3 != null) {
                        i((HashMap) c7Var.e, str3, strG);
                    }
                }
                i++;
                this = c7Var;
            }
            c7 c7Var2 = this;
            pw0 pw0VarL = pw0.l(arrayList);
            c7Var2.f = pw0VarL;
            if (z) {
                us0 us0Var = (us0) pw0VarL.get(0);
                us0Var.getClass();
                bx0 bx0VarA = us0Var.a();
                for (int i2 = 1; i2 < ((pw0) c7Var2.f).size(); i2++) {
                    if (!((us0) ((pw0) c7Var2.f).get(i2)).a().equals(bx0VarA)) {
                        throw hn1.b("The set of available pathway IDs is inconsistent among variant redundant groups", null);
                    }
                }
            }
        }
    }

    public void e(int i, long j, long j2, boolean z) {
        long[] jArr = (long[]) this.c;
        if (j(i)) {
            if (j2 > 0) {
                jArr[i] = j2;
            }
            ((boolean[]) this.d)[i] = z;
            if (z) {
                long j3 = jArr[i];
                if (j3 > 0) {
                    j = j3;
                }
            }
            f(i, j, j2);
        }
    }

    public void f(int i, long j, long j2) {
        ArrayList arrayList = (ArrayList) this.b;
        if (j(i)) {
            if (j2 > 0) {
                ((long[]) this.c)[i] = j2;
            }
            b7 b7Var = arrayList.isEmpty() ? null : (b7) jf2.d(1, arrayList);
            if (b7Var == null || b7Var.a != i) {
                return;
            }
            if (b7Var.d != Math.max(0L, j)) {
                b7Var.c = System.currentTimeMillis() / 1000;
            }
            b7Var.d = Math.max(0L, j);
            if (j2 > 0) {
                b7Var.e = j2;
            }
        }
    }

    public String g(Uri uri, String str, int i, ArrayList arrayList, ts0 ts0Var, HashMap map, HashMap map2) throws hn1 {
        int i2;
        String str2;
        Integer num = (Integer) map.get(ts0Var);
        int i3 = 1;
        if (num == null) {
            if (str == null) {
                map2.put(ts0Var, 1);
                str = ".";
            } else {
                map2.put(ts0Var, 0);
            }
            us0 us0Var = new us0(ts0Var, str, uri, i);
            map.put(ts0Var, Integer.valueOf(arrayList.size()));
            arrayList.add(us0Var);
            return str;
        }
        if (str == null) {
            Integer num2 = (Integer) map2.get(ts0Var);
            num2.getClass();
            int iIntValue = num2.intValue();
            if (this.a && iIntValue >= 1) {
                throw hn1.b("At most one playlist URL within an HlsRedundantGroup can have an undefined pathway when Content Steering is enabled", null);
            }
            int i4 = iIntValue + 1;
            if (i4 <= 1) {
                ha1.f(i4, "invalid count: %s", i4 >= 0);
                str2 = i4 == 0 ? "" : ".";
            } else {
                long j = i4;
                int i5 = (int) j;
                if (i5 != j) {
                    throw new ArrayIndexOutOfBoundsException(uh0.s(j, "Required array size too large: "));
                }
                char[] cArr = new char[i5];
                ".".getChars(0, 1, cArr, 0);
                while (true) {
                    i2 = i5 - i3;
                    if (i3 >= i2) {
                        break;
                    }
                    System.arraycopy(cArr, 0, cArr, i3, i3);
                    i3 <<= 1;
                }
                System.arraycopy(cArr, 0, cArr, i3, i2);
                str2 = new String(cArr);
            }
            map2.put(ts0Var, Integer.valueOf(i4));
            str = str2;
        }
        us0 us0Var2 = (us0) arrayList.get(num.intValue());
        Uri uri2 = (Uri) us0Var2.b.get(str);
        if (uri2 == null || uri.equals(uri2)) {
            us0Var2.b.put(str, uri);
            if (i != -1) {
                us0Var2.c.add(Integer.valueOf(i));
            }
            return str;
        }
        throw hn1.b("Different playlist URLs are found for pathway ID " + str + " within the HlsRedundantGroup", null);
    }

    public boolean j(int i) {
        return i >= 0 && i < ((long[]) this.c).length;
    }

    public void k(int i, boolean z) {
        ArrayList arrayList = (ArrayList) this.b;
        b7 b7Var = arrayList.isEmpty() ? null : (b7) jf2.d(1, arrayList);
        if (z && b7Var != null && b7Var.a == i) {
            return;
        }
        arrayList.add(new b7(System.currentTimeMillis() / 1000, i));
        if (arrayList.size() > 500) {
            arrayList.remove(0);
        }
    }

    public c7(ns0 ns0Var) {
        this.b = ns0Var;
        this.a = ns0Var.l != null;
        this.c = new HashMap();
        this.d = new HashMap();
        this.e = new HashMap();
    }
}
