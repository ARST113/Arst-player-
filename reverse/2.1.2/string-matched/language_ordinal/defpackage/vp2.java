package defpackage;

import android.os.Bundle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-d5d7d661e67c62f2588b5a5666a7a20281e417b1121a202706e8fa77676a2bdd */
/* JADX INFO: loaded from: classes.dex */
public final class vp2 {
    public static final vp2 f = new vp2(-1, null, null, new String[0], null);
    public final Integer a;
    public final String b;
    public final Integer c;
    public final String[] d;
    public final Integer e;

    public vp2(Integer num, String str, Integer num2, String[] strArr, Integer num3) {
        this.a = num;
        this.b = (str == null || str.trim().isEmpty()) ? null : str;
        this.c = num2;
        this.d = strArr;
        this.e = num3;
    }

    public static vp2 a(Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        return new vp2(bundle.containsKey("index") ? Integer.valueOf(bundle.getInt("index")) : null, bundle.getString("label"), bundle.containsKey("ordinal") ? Integer.valueOf(bundle.getInt("ordinal")) : null, bundle.getStringArray("languages"), bundle.containsKey("count") ? Integer.valueOf(bundle.getInt("count")) : null);
    }

    /* JADX WARN: Code duplicated, block: B:14:0x004c  */
    /* JADX WARN: Code duplicated, block: B:17:0x006f  */
    public static vp2 c(Bundle bundle, String str, String str2, ArrayList arrayList) {
        Integer num;
        List listAsList;
        String[] strArr;
        Integer num2;
        String strF = jf2.f(str2, ".", str);
        boolean zEquals = "subtitle".equals(str);
        Integer numI0 = ha1.i0(bundle.get(str.concat("_index")), strF.concat("_index"), arrayList);
        if (numI0 == null) {
            num = numI0;
        } else if (numI0.intValue() < (zEquals ? -1 : 0)) {
            String strConcat = strF.concat("_index");
            StringBuilder sb = new StringBuilder();
            sb.append(numI0);
            sb.append(zEquals ? " is below -1 (off)" : " is negative; audio cannot be off");
            ha1.h0(arrayList, strConcat, sb.toString());
            num = null;
        } else {
            num = numI0;
        }
        String strA0 = ha1.a0(bundle.get(str.concat("_label")), strF.concat("_label"), arrayList);
        Object obj = bundle.get(str.concat("_languages"));
        String strConcat2 = strF.concat("_languages");
        if (obj != null) {
            if (obj instanceof String[]) {
                listAsList = Arrays.asList((String[]) obj);
            } else if (obj instanceof ArrayList) {
                listAsList = (ArrayList) obj;
            } else {
                if (obj instanceof CharSequence) {
                    String strTrim = obj.toString().trim();
                    if (!strTrim.isEmpty()) {
                        listAsList = Arrays.asList(strTrim.split("[\\s,]+"));
                    }
                } else {
                    ha1.h0(arrayList, strConcat2, ha1.O(obj).concat(" is not a list of language codes"));
                }
                strArr = null;
            }
            if (listAsList.isEmpty()) {
                strArr = new String[0];
            } else {
                ArrayList arrayList2 = new ArrayList();
                for (int i = 0; i < listAsList.size(); i++) {
                    Object obj2 = listAsList.get(i);
                    if (obj2 != null && !obj2.toString().trim().isEmpty()) {
                        String strD = obj2 instanceof CharSequence ? ha1.D(obj2.toString()) : null;
                        if (strD == null) {
                            ha1.h0(arrayList, strConcat2 + "[" + i + "]", ha1.O(obj2).concat(" is not a language code"));
                        } else if (!arrayList2.contains(strD)) {
                            arrayList2.add(strD);
                        }
                    }
                }
                if (arrayList2.isEmpty()) {
                    strArr = null;
                } else {
                    strArr = (String[]) arrayList2.toArray(new String[0]);
                }
            }
        } else {
            strArr = null;
        }
        String strA1 = ha1.a0(bundle.get(str.concat("_language")), strF.concat("_language"), arrayList);
        if (strA1 != null) {
            String strD2 = ha1.D(strA1);
            if (strD2 == null) {
                ha1.h0(arrayList, strF.concat("_language"), "\"" + strA1 + "\" is not a language code");
            } else if (strArr != null) {
                ha1.h0(arrayList, strF.concat("_language"), "ignored: " + str + "_languages is given");
            } else {
                strArr = new String[]{strD2};
            }
        }
        String[] strArr2 = (zEquals || strArr == null || strArr.length != 0) ? strArr : null;
        Integer numI1 = ha1.i0(bundle.get(str.concat("_language_ordinal")), strF.concat("_language_ordinal"), arrayList);
        if (numI1 != null && numI1.intValue() < 0) {
            ha1.h0(arrayList, strF.concat("_language_ordinal"), numI1 + " is negative");
            numI1 = null;
        }
        if (numI1 != null && (strArr2 == null || strArr2.length == 0)) {
            ha1.h0(arrayList, strF.concat("_language_ordinal"), "ignored: no language to count in");
            numI1 = null;
        }
        Integer numI2 = ha1.i0(bundle.get(str.concat("_language_count")), strF.concat("_language_count"), arrayList);
        if (numI2 == null || numI2.intValue() >= 1) {
            num2 = numI2;
        } else {
            ha1.h0(arrayList, strF.concat("_language_count"), numI2 + " is below 1");
            num2 = null;
        }
        return new vp2(num, strA0, numI1, strArr2, num2);
    }

    public final boolean b() {
        Integer num = this.a;
        if (num != null && num.intValue() == -1) {
            return true;
        }
        String[] strArr = this.d;
        return strArr != null && strArr.length == 0;
    }

    public final Bundle d() {
        Bundle bundle = new Bundle();
        Integer num = this.a;
        if (num != null) {
            bundle.putInt("index", num.intValue());
        }
        bundle.putString("label", this.b);
        Integer num2 = this.c;
        if (num2 != null) {
            bundle.putInt("ordinal", num2.intValue());
        }
        bundle.putStringArray("languages", this.d);
        Integer num3 = this.e;
        if (num3 != null) {
            bundle.putInt("count", num3.intValue());
        }
        return bundle;
    }

    public final void e(String str, Bundle bundle) {
        String strConcat = str.concat("_language");
        String[] strArr = this.d;
        bundle.putString(strConcat, (strArr == null || strArr.length == 0) ? null : strArr[0]);
        bundle.putString(str.concat("_label"), this.b);
        Integer num = this.c;
        if (num != null) {
            bundle.putInt(str.concat("_language_ordinal"), num.intValue());
        }
        Integer num2 = this.e;
        if (num2 != null) {
            bundle.putInt(str.concat("_language_count"), num2.intValue());
        }
        boolean zB = b();
        Integer num3 = this.a;
        if (zB || num3 != null) {
            bundle.putInt(str.concat("_index"), b() ? -1 : num3.intValue());
        }
    }
}
