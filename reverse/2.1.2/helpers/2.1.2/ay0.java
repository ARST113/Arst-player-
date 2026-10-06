package defpackage;

import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: compiled from: r8-map-id-d5d7d661e67c62f2588b5a5666a7a20281e417b1121a202706e8fa77676a2bdd */
/* JADX INFO: loaded from: classes.dex */
public final class ay0 implements je2 {
    public final ArrayList l;
    public final ArrayList m;
    public final double n;

    public ay0(String str) {
        ArrayList arrayList = new ArrayList();
        this.l = arrayList;
        ArrayList arrayList2 = new ArrayList();
        this.m = arrayList2;
        if (str == null || str.isEmpty()) {
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            Object objOpt = jSONObject.opt("duration_ms");
            double d = 0.0d;
            if (objOpt != null) {
                try {
                    double d2 = Double.parseDouble(String.valueOf(objOpt));
                    if (!Double.isNaN(d2) && d2 > 0.0d) {
                        d = d2 / 1000.0d;
                    }
                } catch (NumberFormatException unused) {
                }
            }
            this.n = d;
            b(jSONObject.optJSONArray("skip"), arrayList);
            b(jSONObject.optJSONArray("ad"), arrayList2);
        } catch (Exception unused2) {
            arrayList.clear();
            arrayList2.clear();
        }
    }

    public static void b(JSONArray jSONArray, ArrayList arrayList) {
        if (jSONArray == null) {
            return;
        }
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject jSONObjectOptJSONObject = jSONArray.optJSONObject(i);
            if (jSONObjectOptJSONObject != null) {
                double dOptDouble = jSONObjectOptJSONObject.optDouble("start", 0.0d);
                double dOptDouble2 = jSONObjectOptJSONObject.optDouble("end", 0.0d);
                if (!Double.isNaN(dOptDouble) && !Double.isNaN(dOptDouble2) && dOptDouble2 > dOptDouble) {
                    arrayList.add(new h92(dOptDouble, dOptDouble2));
                }
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:128:0x01ce  */
    /* JADX WARN: Code duplicated, block: B:131:0x01db  */
    /* JADX WARN: Code duplicated, block: B:133:0x01e4  */
    /* JADX WARN: Code duplicated, block: B:136:0x01ee A[LOOP:9: B:134:0x01e8->B:136:0x01ee, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:139:0x0208  */
    /* JADX WARN: Code duplicated, block: B:142:0x0212 A[LOOP:10: B:140:0x020c->B:142:0x0212, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:65:0x00fb  */
    /* JADX WARN: Code duplicated, block: B:68:0x0104  */
    @Override // defpackage.je2
    public final ArrayList a(double d) {
        wl0 wl0Var;
        ArrayList arrayList;
        ArrayList arrayList2;
        h92 h92Var;
        h92 h92Var2;
        ArrayList arrayList3;
        ArrayList arrayList4;
        double d2;
        ArrayList<h92> arrayList5 = this.l;
        ArrayList<h92> arrayList6 = this.m;
        if (d <= 0.0d || Double.isNaN(d)) {
            arrayList = new ArrayList();
            if (arrayList5 != null) {
                for (h92 h92Var3 : arrayList5) {
                    arrayList.add(new h92(h92Var3.a, h92Var3.b));
                }
            }
            arrayList2 = new ArrayList();
            if (arrayList6 != null) {
                for (h92 h92Var4 : arrayList6) {
                    arrayList2.add(new h92(h92Var4.a, h92Var4.b));
                }
            }
            wl0Var = new wl0(arrayList, arrayList2);
        } else {
            double d3 = this.n;
            if (d3 <= 0.0d) {
                arrayList = new ArrayList();
                if (arrayList5 != null) {
                    while (r2.hasNext()) {
                        arrayList.add(new h92(h92Var3.a, h92Var3.b));
                    }
                }
                arrayList2 = new ArrayList();
                if (arrayList6 != null) {
                    while (r3.hasNext()) {
                        arrayList2.add(new h92(h92Var4.a, h92Var4.b));
                    }
                }
                wl0Var = new wl0(arrayList, arrayList2);
            } else {
                if (arrayList5 == null || arrayList5.isEmpty()) {
                    h92Var = null;
                } else {
                    double d4 = d3 > 0.0d ? 0.55d * d3 : Double.POSITIVE_INFINITY;
                    h92 h92Var5 = null;
                    for (h92 h92Var6 : arrayList5) {
                        double d5 = h92Var6.a;
                        if (d5 >= 30.0d && (d3 <= 0.0d || d5 >= d4)) {
                            if (h92Var5 == null || h92Var6.b > h92Var5.b) {
                                h92Var5 = h92Var6;
                            }
                        }
                    }
                    h92Var = h92Var5;
                }
                double dMax = h92Var != null ? Math.max(0.0d, h92Var.b - h92Var.a) : 0.0d;
                double d6 = h92Var != null ? h92Var.a : 0.0d;
                double d7 = 60.0d;
                boolean z = h92Var != null && Math.abs(h92Var.b - d3) <= 15.0d && h92Var.a >= (d3 - Math.max(dMax, 60.0d)) - 15.0d;
                ArrayList arrayList7 = new ArrayList();
                for (h92 h92Var7 : arrayList5) {
                    ArrayList arrayList8 = arrayList7;
                    arrayList8.add(ha1.E(h92Var7, d, z && h92Var7 == h92Var, dMax, d6, d3));
                    arrayList7 = arrayList8;
                    d7 = d7;
                }
                double d8 = d7;
                ArrayList<h92> arrayList9 = arrayList7;
                ArrayList arrayList10 = new ArrayList();
                Iterator it = arrayList6.iterator();
                while (it.hasNext()) {
                    arrayList10.add(ha1.E((h92) it.next(), d, false, dMax, d6, d3));
                }
                if (arrayList9.isEmpty()) {
                    arrayList3 = arrayList9;
                } else {
                    if (arrayList9.isEmpty()) {
                        h92Var2 = null;
                    } else {
                        double d9 = d3 > 0.0d ? d3 * 0.45d : Double.POSITIVE_INFINITY;
                        h92Var2 = null;
                        for (h92 h92Var8 : arrayList9) {
                            double d10 = h92Var8.a;
                            if (d10 >= 30.0d && d10 <= d9 && (h92Var2 == null || d10 > h92Var2.a)) {
                                h92Var2 = h92Var8;
                            }
                        }
                        if (h92Var2 == null) {
                            if (arrayList9.isEmpty()) {
                                h92Var2 = null;
                            } else {
                                h92Var2 = null;
                                for (h92 h92Var9 : arrayList9) {
                                    if (h92Var2 == null || h92Var9.a < h92Var2.a) {
                                        h92Var2 = h92Var9;
                                    }
                                }
                            }
                        }
                    }
                    if (h92Var2 == null) {
                        arrayList3 = arrayList9;
                    } else {
                        double d11 = h92Var2.a;
                        arrayList3 = new ArrayList();
                        for (h92 h92Var10 : arrayList9) {
                            double d12 = h92Var10.a;
                            double d13 = h92Var10.b;
                            if (d12 >= 30.0d) {
                                arrayList4 = arrayList9;
                                d2 = d11;
                                if (d13 < d3 - d8 || d12 >= d2 - 1.0d || d13 <= d2 + 15.0d) {
                                    arrayList3.add(h92Var10);
                                }
                            } else {
                                Iterator it2 = arrayList9.iterator();
                                while (true) {
                                    if (it2.hasNext()) {
                                        h92 h92Var11 = (h92) it2.next();
                                        arrayList4 = arrayList9;
                                        d2 = d11;
                                        double d14 = h92Var11.a;
                                        if (d14 >= 30.0d) {
                                            double d15 = h92Var11.b;
                                            if ((d13 <= d14 || d12 >= d15) && d13 <= d14 + 15.0d) {
                                            }
                                        }
                                        arrayList9 = arrayList4;
                                        d11 = d2;
                                    } else {
                                        arrayList4 = arrayList9;
                                        d2 = d11;
                                        if (d13 < d3 - d8) {
                                            arrayList3.add(h92Var10);
                                        }
                                    }
                                }
                            }
                            arrayList9 = arrayList4;
                            d11 = d2;
                        }
                    }
                }
                wl0Var = new wl0(arrayList3, arrayList10);
            }
        }
        ArrayList arrayList11 = new ArrayList();
        for (h92 h92Var12 : wl0Var.a) {
            arrayList11.add(new ie2(h92Var12.a, h92Var12.b, 1));
        }
        for (h92 h92Var13 : wl0Var.b) {
            arrayList11.add(new ie2(h92Var13.a, h92Var13.b, 2));
        }
        return arrayList11;
    }
}
