package defpackage;

import android.net.Uri;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-ba8d2c2760819bd03a19aa4133a73a2576203ef152dace5b65b91a4c4fd30466 */
/* JADX INFO: loaded from: classes.dex */
public final class b7 {
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
    public b7(int i) {
        this.b = new ArrayList();
        this.c = new long[i];
        this.d = new boolean[i];
        this.h = new String[i];
        this.i = new String[i];
        for (int i2 = 0; i2 < i; i2++) {
            ((long[]) this.c)[i2] = -9223372036854775807L;
        }
    }

    public static int g(long j) {
        return (int) (Math.max(0L, j) / 1000);
    }

    public static void h(HashMap map, String str, String str2) throws xm1 {
        if (!map.containsKey(str) || str2.equals(map.get(str))) {
            map.put(str, str2);
            return;
        }
        throw xm1.b("The group ID " + str + " is associated with more than one pathway from variants", null);
    }

    public hw0 a(List list, HashMap map) throws xm1 {
        b7 b7Var;
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
            ds0 ds0Var = (ds0) list.get(i);
            Uri uri = ds0Var.a;
            ls0 ls0Var = new ls0(ds0Var.b, ds0Var.e, ds0Var.d);
            if (z && (str = (String) map.get(ds0Var.c)) == null) {
                b7Var = this;
            } else {
                String str2 = str;
                Uri uri2 = ds0Var.a;
                b7Var = this;
                b7Var.f(uri2, str2, i, arrayList, ls0Var, map2, map3);
            }
            i++;
            this = b7Var;
        }
        b7 b7Var2 = this;
        if (z) {
            ms0 ms0Var = (ms0) ((hw0) b7Var2.f).get(0);
            ms0Var.getClass();
            tw0 tw0VarA = ms0Var.a();
            for (int i2 = 0; i2 < arrayList.size(); i2++) {
                if (!((ms0) arrayList.get(i2)).a().equals(tw0VarA)) {
                    throw xm1.b("The set of available pathway IDs of a rendition redundant group is inconsistent with variant redundant groups", null);
                }
            }
        }
        return hw0.l(arrayList);
    }

    public hw0 b(int i) throws xm1 {
        fs0 fs0Var = (fs0) this.b;
        c();
        if (i == 1) {
            hw0 hw0Var = (hw0) this.g;
            if (hw0Var != null) {
                return hw0Var;
            }
            hw0 hw0VarA = a(fs0Var.e, (HashMap) this.c);
            this.g = hw0VarA;
            return hw0VarA;
        }
        if (i == 2) {
            hw0 hw0Var2 = (hw0) this.h;
            if (hw0Var2 != null) {
                return hw0Var2;
            }
            hw0 hw0VarA2 = a(fs0Var.f, (HashMap) this.d);
            this.h = hw0VarA2;
            return hw0VarA2;
        }
        if (i != 3) {
            uk.d("Invalid type for creating rendition redundant group list");
            return null;
        }
        hw0 hw0Var3 = (hw0) this.i;
        if (hw0Var3 != null) {
            return hw0Var3;
        }
        hw0 hw0VarA3 = a(fs0Var.g, (HashMap) this.e);
        this.i = hw0VarA3;
        return hw0VarA3;
    }

    public void c() {
        boolean z = this.a;
        fs0 fs0Var = (fs0) this.b;
        if (((hw0) this.f) == null) {
            ArrayList arrayList = new ArrayList();
            HashMap map = new HashMap();
            HashMap map2 = new HashMap();
            int i = 0;
            while (i < fs0Var.d.size()) {
                es0 es0Var = (es0) fs0Var.d.get(i);
                b7 b7Var = this;
                String strF = b7Var.f(es0Var.a, es0Var.g, i, arrayList, new ls0(es0Var.b, es0Var.h, null), map, map2);
                if (z) {
                    String str = es0Var.c;
                    if (str != null) {
                        h((HashMap) b7Var.c, str, strF);
                    }
                    String str2 = es0Var.d;
                    if (str2 != null) {
                        h((HashMap) b7Var.d, str2, strF);
                    }
                    String str3 = es0Var.e;
                    if (str3 != null) {
                        h((HashMap) b7Var.e, str3, strF);
                    }
                }
                i++;
                this = b7Var;
            }
            b7 b7Var2 = this;
            hw0 hw0VarL = hw0.l(arrayList);
            b7Var2.f = hw0VarL;
            if (z) {
                ms0 ms0Var = (ms0) hw0VarL.get(0);
                ms0Var.getClass();
                tw0 tw0VarA = ms0Var.a();
                for (int i2 = 1; i2 < ((hw0) b7Var2.f).size(); i2++) {
                    if (!((ms0) ((hw0) b7Var2.f).get(i2)).a().equals(tw0VarA)) {
                        throw xm1.b("The set of available pathway IDs is inconsistent among variant redundant groups", null);
                    }
                }
            }
        }
    }

    public void d(int i, long j, long j2, boolean z) {
        long[] jArr = (long[]) this.c;
        if (i(i)) {
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
            e(i, j, j2);
        }
    }

    public void e(int i, long j, long j2) {
        ArrayList arrayList = (ArrayList) this.b;
        if (i(i)) {
            if (j2 > 0) {
                ((long[]) this.c)[i] = j2;
            }
            a7 a7Var = arrayList.isEmpty() ? null : (a7) we2.d(1, arrayList);
            if (a7Var == null || a7Var.a != i) {
                return;
            }
            a7Var.c = Math.max(0L, j);
            if (j2 > 0) {
                a7Var.d = j2;
            }
        }
    }

    public String f(Uri uri, String str, int i, ArrayList arrayList, ls0 ls0Var, HashMap map, HashMap map2) throws xm1 {
        int i2;
        String str2;
        Integer num = (Integer) map.get(ls0Var);
        int i3 = 1;
        if (num == null) {
            if (str == null) {
                map2.put(ls0Var, 1);
                str = ".";
            } else {
                map2.put(ls0Var, 0);
            }
            ms0 ms0Var = new ms0(ls0Var, str, uri, i);
            map.put(ls0Var, Integer.valueOf(arrayList.size()));
            arrayList.add(ms0Var);
            return str;
        }
        if (str == null) {
            Integer num2 = (Integer) map2.get(ls0Var);
            num2.getClass();
            int iIntValue = num2.intValue();
            if (this.a && iIntValue >= 1) {
                throw xm1.b("At most one playlist URL within an HlsRedundantGroup can have an undefined pathway when Content Steering is enabled", null);
            }
            int i4 = iIntValue + 1;
            if (i4 <= 1) {
                x91.f(i4, "invalid count: %s", i4 >= 0);
                str2 = i4 == 0 ? "" : ".";
            } else {
                long j = i4;
                int i5 = (int) j;
                if (i5 != j) {
                    throw new ArrayIndexOutOfBoundsException(lh0.s(j, "Required array size too large: "));
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
            map2.put(ls0Var, Integer.valueOf(i4));
            str = str2;
        }
        ms0 ms0Var2 = (ms0) arrayList.get(num.intValue());
        Uri uri2 = (Uri) ms0Var2.b.get(str);
        if (uri2 == null || uri.equals(uri2)) {
            ms0Var2.b.put(str, uri);
            if (i != -1) {
                ms0Var2.c.add(Integer.valueOf(i));
            }
            return str;
        }
        throw xm1.b("Different playlist URLs are found for pathway ID " + str + " within the HlsRedundantGroup", null);
    }

    public boolean i(int i) {
        return i >= 0 && i < ((long[]) this.c).length;
    }

    public void j(int i, boolean z) {
        ArrayList arrayList = (ArrayList) this.b;
        a7 a7Var = arrayList.isEmpty() ? null : (a7) we2.d(1, arrayList);
        if (z && a7Var != null && a7Var.a == i) {
            return;
        }
        arrayList.add(new a7(System.currentTimeMillis() / 1000, i));
        if (arrayList.size() > 500) {
            arrayList.remove(0);
        }
    }

    public b7(fs0 fs0Var) {
        this.b = fs0Var;
        this.a = fs0Var.l != null;
        this.c = new HashMap();
        this.d = new HashMap();
        this.e = new HashMap();
    }
}
