package defpackage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.BadParcelableException;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import io.sentry.q1;
import j$.time.Duration;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Collection;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.Random;
import java.util.TreeMap;
import java.util.concurrent.locks.Lock;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

/* JADX INFO: compiled from: r8-map-id-d5d7d661e67c62f2588b5a5666a7a20281e417b1121a202706e8fa77676a2bdd */
/* JADX INFO: loaded from: classes.dex */
public abstract class ha1 implements qm1 {
    public static final t90 a = new t90(13);
    public static final byte[] b = {112, 114, 111, 0};
    public static final byte[] c = {112, 114, 109, 0};
    public static final String[] d = {"Camera:MotionPhoto", "GCamera:MotionPhoto", "Camera:MicroVideo", "GCamera:MicroVideo"};
    public static final String[] e = {"Camera:MotionPhotoPresentationTimestampUs", "GCamera:MotionPhotoPresentationTimestampUs", "Camera:MicroVideoPresentationTimestampUs", "GCamera:MicroVideoPresentationTimestampUs"};
    public static final String[] f = {"Camera:MicroVideoOffset", "GCamera:MicroVideoOffset"};

    public static Integer A(String str, Bundle bundle) {
        Double dH = H(str, bundle);
        if (dH == null) {
            return null;
        }
        return Integer.valueOf((int) Math.floor(dH.doubleValue()));
    }

    public static boolean B(Uri uri) {
        return uri != null && "content".equals(uri.getScheme()) && "media".equals(uri.getAuthority());
    }

    public static boolean C(long j, ue0 ue0Var) {
        return (j & ue0Var.getValue()) > 0;
    }

    public static String D(String str) {
        if (str != null && !str.trim().isEmpty()) {
            try {
                String iSO3Language = Locale.forLanguageTag(ot2.Z(str.trim().replace('_', '-'))).getISO3Language();
                if (!iSO3Language.isEmpty() && !"und".equals(iSO3Language)) {
                    return iSO3Language;
                }
            } catch (MissingResourceException unused) {
            }
        }
        return null;
    }

    public static h92 E(h92 h92Var, double d2, boolean z, double d3, double d4, double d5) {
        double d6 = h92Var.a;
        double d7 = h92Var.a;
        double d8 = h92Var.b;
        if (d5 == 0.0d) {
            return new h92(d6, d8);
        }
        double d9 = d2 - d5;
        if (Math.abs(d9) <= 2.0d) {
            return new h92(d7, d8);
        }
        if (d9 < 0.0d) {
            return new h92(d7, d8);
        }
        if (z) {
            double dMax = d2 - Math.max(1.0d, d8 - d6);
            if (d3 > 0.0d && d9 < d3) {
                dMax -= Math.floor(d9 * 0.3d);
            }
            return new h92(Math.max(0.0d, Math.min(d2 - 1.0d, dMax)), d2);
        }
        if (d6 < 30.0d) {
            return new h92(d7, d8);
        }
        if (d9 <= 20.0d && d4 > d6) {
            double d10 = d8 - d6;
            if (d10 == 0.0d) {
                d10 = 1.0d;
            }
            double dMax2 = Math.max(d2 - (d5 - d6), Math.floor(d6 / 45.0d) + d6 + Math.floor(d9 * (d9 > 10.0d ? 0.75d : 2.0d)));
            double d11 = d10 + dMax2;
            double dMax3 = Math.max(0.0d, Math.min(d2, dMax2));
            return new h92(dMax3, Math.max(1.0d + dMax3, Math.min(d2, d11)));
        }
        double d12 = d2 - (d5 - d6);
        double d13 = d2 - (d5 - d8);
        if (d3 > 0.0d && d9 < d3) {
            double d14 = d8 - d6;
            if (d14 == 0.0d) {
                d14 = 1.0d;
            }
            double dFloor = Math.floor(0.84d * d9) + d6;
            double dFloor2 = Math.floor(0.52d * d9) + Math.floor(d6 / 45.0d) + d6;
            if (d12 <= dFloor) {
                dFloor = Math.floor(0.2d * d9) + d12;
            }
            d12 = (d9 < 100.0d || dFloor2 <= d6 || dFloor2 >= dFloor) ? dFloor : dFloor2;
            d13 = d12 + d14;
        }
        double dMax4 = Math.max(0.0d, Math.min(d2, d12));
        return new h92(dMax4, Math.max(1.0d + dMax4, Math.min(d2, d13)));
    }

    public static Double F(String str, Bundle bundle) {
        Double dH = H(str.concat("_ms"), bundle);
        if (dH != null) {
            return dH;
        }
        Double dH2 = H(str.concat("_sec"), bundle);
        if (dH2 == null) {
            return null;
        }
        return Double.valueOf(dH2.doubleValue() * 1000.0d);
    }

    public static JSONObject G(String str, String str2, String str3) {
        JSONObject jSONObjectC = c(str, str2);
        if (jSONObjectC == null) {
            return null;
        }
        if (str3 == null) {
            str3 = "";
        }
        try {
            return jSONObjectC.put("n", str3);
        } catch (Exception unused) {
            return null;
        }
    }

    public static Double H(String str, Bundle bundle) {
        Object obj = bundle.get(str);
        if (obj instanceof Number) {
            double dDoubleValue = ((Number) obj).doubleValue();
            if (Double.isNaN(dDoubleValue) || Double.isInfinite(dDoubleValue)) {
                return null;
            }
            return Double.valueOf(dDoubleValue);
        }
        if (!(obj instanceof CharSequence)) {
            return null;
        }
        try {
            double d2 = Double.parseDouble(obj.toString().trim());
            if (!Double.isNaN(d2) && !Double.isInfinite(d2)) {
                return Double.valueOf(d2);
            }
            return null;
        } catch (NumberFormatException unused) {
            return null;
        }
    }

    public static Parcelable[] K(String str, Bundle bundle) {
        Object obj = bundle.get(str);
        if (obj instanceof Parcelable[]) {
            return (Parcelable[]) obj;
        }
        if (!(obj instanceof ArrayList)) {
            return null;
        }
        ArrayList arrayList = (ArrayList) obj;
        int size = arrayList.size();
        Parcelable[] parcelableArr = new Parcelable[size];
        for (int i = 0; i < size; i++) {
            parcelableArr[i] = arrayList.get(i) instanceof Parcelable ? (Parcelable) arrayList.get(i) : null;
        }
        return parcelableArr;
    }

    public static pr L(String str) throws XmlPullParserException, IOException {
        byte b2;
        XmlPullParser xmlPullParserNewPullParser = XmlPullParserFactory.newInstance().newPullParser();
        xmlPullParserNewPullParser.setInput(new StringReader(str));
        xmlPullParserNewPullParser.next();
        if (!aq0.p(xmlPullParserNewPullParser, "x:xmpmeta")) {
            throw hn1.a("Couldn't find xmp metadata", null);
        }
        nw0 nw0Var = pw0.m;
        cz1 cz1VarM = cz1.p;
        long j = -9223372036854775807L;
        loop0: do {
            xmlPullParserNewPullParser.next();
            b2 = 4;
            if (aq0.p(xmlPullParserNewPullParser, "rdf:Description")) {
                int i = 0;
                for (int i2 = 0; i2 < 4; i2++) {
                    String strI = aq0.i(xmlPullParserNewPullParser, d[i2]);
                    if (strI != null) {
                        if (Integer.parseInt(strI) != 1) {
                            break loop0;
                        }
                        int i3 = 0;
                        while (true) {
                            if (i3 < 4) {
                                String strI2 = aq0.i(xmlPullParserNewPullParser, e[i3]);
                                if (strI2 != null) {
                                    j = Long.parseLong(strI2);
                                    if (j != -1) {
                                        break;
                                    }
                                    break;
                                }
                                i3++;
                            }
                            j = -9223372036854775807L;
                            break;
                        }
                        while (true) {
                            if (i >= 2) {
                                nw0 nw0Var2 = pw0.m;
                                cz1VarM = cz1.p;
                                break;
                            }
                            String strI3 = aq0.i(xmlPullParserNewPullParser, f[i]);
                            if (strI3 != null) {
                                cz1VarM = pw0.q(new zf1(0L, 0L, "image/jpeg"), new zf1(Long.parseLong(strI3), 0L, "video/mp4"));
                                break;
                            }
                            i++;
                        }
                    }
                }
                return null;
            }
            if (aq0.p(xmlPullParserNewPullParser, "Container:Directory")) {
                cz1VarM = M(xmlPullParserNewPullParser, "Container", "Item");
            } else if (aq0.p(xmlPullParserNewPullParser, "GContainer:Directory")) {
                cz1VarM = M(xmlPullParserNewPullParser, "GContainer", "GContainerItem");
            }
        } while (!aq0.o(xmlPullParserNewPullParser, "x:xmpmeta"));
        if (cz1VarM.isEmpty()) {
            break loop0;
        }
        return new pr(j, cz1VarM, b2);
        return null;
    }

    public static cz1 M(XmlPullParser xmlPullParser, String str, String str2) throws XmlPullParserException, IOException {
        mw0 mw0VarK = pw0.k();
        String strConcat = str.concat(":Item");
        String strConcat2 = str.concat(":Directory");
        do {
            xmlPullParser.next();
            if (aq0.p(xmlPullParser, strConcat)) {
                String strConcat3 = str2.concat(":Mime");
                String strConcat4 = str2.concat(":Semantic");
                String strConcat5 = str2.concat(":Length");
                String strConcat6 = str2.concat(":Padding");
                String strI = aq0.i(xmlPullParser, strConcat3);
                String strI2 = aq0.i(xmlPullParser, strConcat4);
                String strI3 = aq0.i(xmlPullParser, strConcat5);
                String strI4 = aq0.i(xmlPullParser, strConcat6);
                if (strI == null || strI2 == null) {
                    return cz1.p;
                }
                mw0VarK.c(new zf1(strI3 != null ? Long.parseLong(strI3) : 0L, strI4 != null ? Long.parseLong(strI4) : 0L, strI));
            }
        } while (!aq0.o(xmlPullParser, strConcat2));
        return mw0VarK.f();
    }

    public static long N(JSONObject jSONObject) {
        return Math.max(0L, Math.round(jSONObject.optDouble("p", 0.0d) * 1000.0d));
    }

    public static String O(Object obj) {
        if (!(obj instanceof CharSequence)) {
            return String.valueOf(obj);
        }
        return "\"" + obj + "\"";
    }

    public static int[] P(ByteArrayInputStream byteArrayInputStream, int i) {
        int[] iArr = new int[i];
        int iJ = 0;
        for (int i2 = 0; i2 < i; i2++) {
            iJ += (int) y61.J(byteArrayInputStream, 2);
            iArr[i2] = iJ;
        }
        return iArr;
    }

    public static z60[] Q(FileInputStream fileInputStream, byte[] bArr, byte[] bArr2, z60[] z60VarArr) throws IOException {
        byte[] bArr3 = dn1.o;
        if (!Arrays.equals(bArr, bArr3)) {
            if (!Arrays.equals(bArr, dn1.p)) {
                bl.h("Unsupported meta version");
                return null;
            }
            int iJ = (int) y61.J(fileInputStream, 2);
            byte[] bArrI = y61.I(fileInputStream, (int) y61.J(fileInputStream, 4), (int) y61.J(fileInputStream, 4));
            if (fileInputStream.read() > 0) {
                bl.h("Content found after the end of file");
                return null;
            }
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArrI);
            try {
                z60[] z60VarArrS = S(byteArrayInputStream, bArr2, iJ, z60VarArr);
                byteArrayInputStream.close();
                return z60VarArrS;
            } catch (Throwable th) {
                try {
                    byteArrayInputStream.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        }
        if (Arrays.equals(dn1.j, bArr2)) {
            bl.h("Requires new Baseline Profile Metadata. Please rebuild the APK with Android Gradle Plugin 7.2 Canary 7 or higher");
            return null;
        }
        if (!Arrays.equals(bArr, bArr3)) {
            bl.h("Unsupported meta version");
            return null;
        }
        int iJ2 = (int) y61.J(fileInputStream, 1);
        byte[] bArrI2 = y61.I(fileInputStream, (int) y61.J(fileInputStream, 4), (int) y61.J(fileInputStream, 4));
        if (fileInputStream.read() > 0) {
            bl.h("Content found after the end of file");
            return null;
        }
        ByteArrayInputStream byteArrayInputStream2 = new ByteArrayInputStream(bArrI2);
        try {
            z60[] z60VarArrR = R(byteArrayInputStream2, iJ2, z60VarArr);
            byteArrayInputStream2.close();
            return z60VarArrR;
        } catch (Throwable th3) {
            try {
                byteArrayInputStream2.close();
            } catch (Throwable th4) {
                th3.addSuppressed(th4);
            }
            throw th3;
        }
    }

    public static z60[] R(ByteArrayInputStream byteArrayInputStream, int i, z60[] z60VarArr) {
        if (byteArrayInputStream.available() == 0) {
            return new z60[0];
        }
        if (i != z60VarArr.length) {
            bl.h("Mismatched number of dex files found in metadata");
            return null;
        }
        String[] strArr = new String[i];
        int[] iArr = new int[i];
        for (int i2 = 0; i2 < i; i2++) {
            int iJ = (int) y61.J(byteArrayInputStream, 2);
            iArr[i2] = (int) y61.J(byteArrayInputStream, 2);
            strArr[i2] = new String(y61.H(byteArrayInputStream, iJ), StandardCharsets.UTF_8);
        }
        for (int i3 = 0; i3 < i; i3++) {
            z60 z60Var = z60VarArr[i3];
            if (!z60Var.b.equals(strArr[i3])) {
                bl.h("Order of dexfiles in metadata did not match baseline");
                return null;
            }
            int i4 = iArr[i3];
            z60Var.e = i4;
            z60Var.h = P(byteArrayInputStream, i4);
        }
        return z60VarArr;
    }

    public static z60[] S(ByteArrayInputStream byteArrayInputStream, byte[] bArr, int i, z60[] z60VarArr) {
        z60 z60Var;
        if (byteArrayInputStream.available() == 0) {
            return new z60[0];
        }
        if (i != z60VarArr.length) {
            bl.h("Mismatched number of dex files found in metadata");
            return null;
        }
        for (int i2 = 0; i2 < i; i2++) {
            y61.J(byteArrayInputStream, 2);
            String str = new String(y61.H(byteArrayInputStream, (int) y61.J(byteArrayInputStream, 2)), StandardCharsets.UTF_8);
            long J = y61.J(byteArrayInputStream, 4);
            int iJ = (int) y61.J(byteArrayInputStream, 2);
            if (z60VarArr.length <= 0) {
                z60Var = null;
                break;
            }
            int iIndexOf = str.indexOf("!");
            if (iIndexOf < 0) {
                iIndexOf = str.indexOf(":");
            }
            String strSubstring = iIndexOf > 0 ? str.substring(iIndexOf + 1) : str;
            int i3 = 0;
            while (true) {
                if (i3 >= z60VarArr.length) {
                    z60Var = null;
                    break;
                }
                if (z60VarArr[i3].b.equals(strSubstring)) {
                    z60Var = z60VarArr[i3];
                    break;
                }
                i3++;
            }
            if (z60Var == null) {
                bl.h("Missing profile key: ".concat(str));
                return null;
            }
            z60Var.d = J;
            int[] iArrP = P(byteArrayInputStream, iJ);
            if (Arrays.equals(bArr, dn1.n)) {
                z60Var.e = iJ;
                z60Var.h = iArrP;
            }
        }
        return z60VarArr;
    }

    public static z60[] T(FileInputStream fileInputStream, byte[] bArr, String str) throws IOException {
        if (!Arrays.equals(bArr, dn1.k)) {
            bl.h("Unsupported version");
            return null;
        }
        int iJ = (int) y61.J(fileInputStream, 1);
        byte[] bArrI = y61.I(fileInputStream, (int) y61.J(fileInputStream, 4), (int) y61.J(fileInputStream, 4));
        if (fileInputStream.read() > 0) {
            bl.h("Content found after the end of file");
            return null;
        }
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArrI);
        try {
            z60[] z60VarArrV = V(byteArrayInputStream, str, iJ);
            byteArrayInputStream.close();
            return z60VarArrV;
        } catch (Throwable th) {
            try {
                byteArrayInputStream.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    public static Object U(Parcel parcel, Parcelable.Creator creator) {
        if (parcel.readInt() != 0) {
            return creator.createFromParcel(parcel);
        }
        return null;
    }

    public static z60[] V(ByteArrayInputStream byteArrayInputStream, String str, int i) throws IOException {
        int i2 = 0;
        if (byteArrayInputStream.available() == 0) {
            return new z60[0];
        }
        z60[] z60VarArr = new z60[i];
        for (int i3 = 0; i3 < i; i3++) {
            int iJ = (int) y61.J(byteArrayInputStream, 2);
            int iJ2 = (int) y61.J(byteArrayInputStream, 2);
            z60VarArr[i3] = new z60(str, new String(y61.H(byteArrayInputStream, iJ), StandardCharsets.UTF_8), y61.J(byteArrayInputStream, 4), iJ2, (int) y61.J(byteArrayInputStream, 4), (int) y61.J(byteArrayInputStream, 4), new int[iJ2], new TreeMap());
        }
        int i4 = 0;
        while (i4 < i) {
            z60 z60Var = z60VarArr[i4];
            int iAvailable = byteArrayInputStream.available();
            int i5 = z60Var.f;
            int i6 = z60Var.g;
            TreeMap treeMap = z60Var.i;
            int i7 = iAvailable - i5;
            int iJ3 = i2;
            while (byteArrayInputStream.available() > i7) {
                iJ3 += (int) y61.J(byteArrayInputStream, 2);
                treeMap.put(Integer.valueOf(iJ3), 1);
                int iJ4 = (int) y61.J(byteArrayInputStream, 2);
                while (iJ4 > 0) {
                    y61.J(byteArrayInputStream, 2);
                    int iJ5 = (int) y61.J(byteArrayInputStream, 1);
                    if (iJ5 != 6 && iJ5 != 7) {
                        while (iJ5 > 0) {
                            y61.J(byteArrayInputStream, 1);
                            int i8 = i2;
                            int i9 = i4;
                            for (int iJ6 = (int) y61.J(byteArrayInputStream, 1); iJ6 > 0; iJ6--) {
                                y61.J(byteArrayInputStream, 2);
                            }
                            iJ5--;
                            i2 = i8;
                            i4 = i9;
                        }
                    }
                    iJ4--;
                    i2 = i2;
                    i4 = i4;
                }
            }
            int i10 = i2;
            int i11 = i4;
            if (byteArrayInputStream.available() != i7) {
                bl.h("Read too much data during profile line parse");
                return null;
            }
            z60Var.h = P(byteArrayInputStream, z60Var.e);
            BitSet bitSetValueOf = BitSet.valueOf(y61.H(byteArrayInputStream, (((i6 * 2) + 7) & (-8)) / 8));
            for (int i12 = i10; i12 < i6; i12++) {
                int i13 = bitSetValueOf.get(i12) ? 2 : i10;
                if (bitSetValueOf.get(i12 + i6)) {
                    i13 |= 4;
                }
                if (i13 != 0) {
                    Integer numValueOf = (Integer) treeMap.get(Integer.valueOf(i12));
                    if (numValueOf == null) {
                        numValueOf = Integer.valueOf(i10);
                    }
                    treeMap.put(Integer.valueOf(i12), Integer.valueOf(i13 | numValueOf.intValue()));
                }
            }
            i4 = i11 + 1;
            i2 = i10;
        }
        return z60VarArr;
    }

    public static void W(ga1 ga1Var, float f2) {
        e22 e22Var = (e22) ga1Var.m;
        lo loVar = (lo) ga1Var.n;
        boolean useCompatPadding = loVar.getUseCompatPadding();
        boolean preventCornerOverlap = loVar.getPreventCornerOverlap();
        if (f2 != e22Var.e || e22Var.f != useCompatPadding || e22Var.g != preventCornerOverlap) {
            e22Var.e = f2;
            e22Var.f = useCompatPadding;
            e22Var.g = preventCornerOverlap;
            e22Var.b(null);
            e22Var.invalidateSelf();
        }
        if (!loVar.getUseCompatPadding()) {
            ga1Var.H(0, 0, 0, 0);
            return;
        }
        e22 e22Var2 = (e22) ga1Var.m;
        float f3 = e22Var2.e;
        float f4 = e22Var2.a;
        int iCeil = (int) Math.ceil(f22.a(f3, f4, loVar.getPreventCornerOverlap()));
        int iCeil2 = (int) Math.ceil(f22.b(f3, f4, loVar.getPreventCornerOverlap()));
        ga1Var.H(iCeil, iCeil2, iCeil, iCeil2);
    }

    public static boolean X(Uri uri) {
        return uri != null && "ftp".equals(uri.getScheme());
    }

    public static String Y(String str, Bundle bundle) {
        Object obj = bundle.get(str);
        if (obj != null) {
            String strTrim = obj.toString().trim();
            if (!strTrim.isEmpty()) {
                return strTrim;
            }
        }
        return null;
    }

    public static String[] Z(Bundle bundle) {
        Object obj = bundle.get("headers");
        if (obj instanceof String[]) {
            return (String[]) obj;
        }
        if (!(obj instanceof ArrayList)) {
            return null;
        }
        ArrayList arrayList = (ArrayList) obj;
        int size = arrayList.size();
        String[] strArr = new String[size];
        for (int i = 0; i < size; i++) {
            strArr[i] = arrayList.get(i) == null ? null : arrayList.get(i).toString();
        }
        return strArr;
    }

    public static String a0(Object obj, String str, ArrayList arrayList) {
        if (obj != null) {
            if (!(obj instanceof CharSequence)) {
                h0(arrayList, str, O(obj).concat(" is not text"));
                return null;
            }
            String strTrim = obj.toString().trim();
            if (!strTrim.isEmpty()) {
                return strTrim;
            }
        }
        return null;
    }

    public static String b(String str, int i, int i2) {
        if (i < 0) {
            return ck0.I("%s (%s) must not be negative", str, Integer.valueOf(i));
        }
        if (i2 >= 0) {
            return ck0.I("%s (%s) must not be greater than size (%s)", str, Integer.valueOf(i), Integer.valueOf(i2));
        }
        bl.d(uh0.r(i2, "negative size: "));
        return null;
    }

    public static EnumSet b0(long j, Class cls) {
        if (!ue0.class.isAssignableFrom(cls)) {
            bl.d("Can only be used with EnumWithValue enums.");
            return null;
        }
        EnumSet enumSetNoneOf = EnumSet.noneOf(cls);
        for (Object obj : (Enum[]) cls.getEnumConstants()) {
            if (C(j, (ue0) obj)) {
                enumSetNoneOf.add(obj);
            }
        }
        return enumSetNoneOf;
    }

    public static JSONObject c(String str, String str2) {
        try {
            return new JSONObject().put("t", str).put("u", str2);
        } catch (Exception unused) {
            return null;
        }
    }

    public static long c0(Collection collection) {
        long value = 0;
        for (Object obj : collection) {
            if (!(obj instanceof ue0)) {
                bl.d("Can only be used with EnumWithValue enums.");
                return 0L;
            }
            value |= ((ue0) obj).getValue();
        }
        return value;
    }

    public static boolean d(Bundle bundle) {
        Object obj = bundle.get("selected");
        if (obj instanceof Boolean) {
            return ((Boolean) obj).booleanValue();
        }
        return obj != null && "true".equals(obj.toString());
    }

    public static boolean d0(ByteArrayOutputStream byteArrayOutputStream, byte[] bArr, z60[] z60VarArr) throws IOException {
        int i;
        int i2;
        int length;
        byte[] bArr2 = dn1.n;
        byte[] bArr3 = dn1.m;
        byte[] bArr4 = dn1.j;
        int i3 = 0;
        if (!Arrays.equals(bArr, bArr4)) {
            byte[] bArr5 = dn1.k;
            if (Arrays.equals(bArr, bArr5)) {
                byte[] bArrV = v(z60VarArr, bArr5);
                y61.Y(byteArrayOutputStream, z60VarArr.length, 1);
                y61.Y(byteArrayOutputStream, bArrV.length, 4);
                byte[] bArrK = y61.k(bArrV);
                y61.Y(byteArrayOutputStream, bArrK.length, 4);
                byteArrayOutputStream.write(bArrK);
                return true;
            }
            if (Arrays.equals(bArr, bArr3)) {
                y61.Y(byteArrayOutputStream, z60VarArr.length, 1);
                for (z60 z60Var : z60VarArr) {
                    int size = z60Var.i.size() * 4;
                    String strX = x(z60Var.a, z60Var.b, bArr3);
                    Charset charset = StandardCharsets.UTF_8;
                    y61.Z(byteArrayOutputStream, strX.getBytes(charset).length);
                    y61.Z(byteArrayOutputStream, z60Var.h.length);
                    y61.Y(byteArrayOutputStream, size, 4);
                    y61.Y(byteArrayOutputStream, z60Var.c, 4);
                    byteArrayOutputStream.write(strX.getBytes(charset));
                    Iterator it = z60Var.i.keySet().iterator();
                    while (it.hasNext()) {
                        y61.Z(byteArrayOutputStream, ((Integer) it.next()).intValue());
                        y61.Z(byteArrayOutputStream, 0);
                    }
                    for (int i4 : z60Var.h) {
                        y61.Z(byteArrayOutputStream, i4);
                    }
                }
                return true;
            }
            byte[] bArr6 = dn1.l;
            if (Arrays.equals(bArr, bArr6)) {
                byte[] bArrV2 = v(z60VarArr, bArr6);
                y61.Y(byteArrayOutputStream, z60VarArr.length, 1);
                y61.Y(byteArrayOutputStream, bArrV2.length, 4);
                byte[] bArrK2 = y61.k(bArrV2);
                y61.Y(byteArrayOutputStream, bArrK2.length, 4);
                byteArrayOutputStream.write(bArrK2);
                return true;
            }
            if (!Arrays.equals(bArr, bArr2)) {
                return false;
            }
            y61.Z(byteArrayOutputStream, z60VarArr.length);
            for (z60 z60Var2 : z60VarArr) {
                String str = z60Var2.a;
                TreeMap treeMap = z60Var2.i;
                String strX2 = x(str, z60Var2.b, bArr2);
                Charset charset2 = StandardCharsets.UTF_8;
                y61.Z(byteArrayOutputStream, strX2.getBytes(charset2).length);
                y61.Z(byteArrayOutputStream, treeMap.size());
                y61.Z(byteArrayOutputStream, z60Var2.h.length);
                y61.Y(byteArrayOutputStream, z60Var2.c, 4);
                byteArrayOutputStream.write(strX2.getBytes(charset2));
                Iterator it2 = treeMap.keySet().iterator();
                while (it2.hasNext()) {
                    y61.Z(byteArrayOutputStream, ((Integer) it2.next()).intValue());
                }
                for (int i5 : z60Var2.h) {
                    y61.Z(byteArrayOutputStream, i5);
                }
            }
            return true;
        }
        ArrayList arrayList = new ArrayList(3);
        ArrayList arrayList2 = new ArrayList(3);
        ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
        try {
            y61.Z(byteArrayOutputStream2, z60VarArr.length);
            int i6 = 2;
            int i7 = 2;
            for (z60 z60Var3 : z60VarArr) {
                y61.Y(byteArrayOutputStream2, z60Var3.c, 4);
                y61.Y(byteArrayOutputStream2, z60Var3.d, 4);
                y61.Y(byteArrayOutputStream2, z60Var3.g, 4);
                String strX3 = x(z60Var3.a, z60Var3.b, bArr4);
                Charset charset3 = StandardCharsets.UTF_8;
                int length2 = strX3.getBytes(charset3).length;
                y61.Z(byteArrayOutputStream2, length2);
                i7 = i7 + 14 + length2;
                byteArrayOutputStream2.write(strX3.getBytes(charset3));
            }
            byte[] byteArray = byteArrayOutputStream2.toByteArray();
            if (i7 != byteArray.length) {
                throw new IllegalStateException("Expected size " + i7 + ", does not match actual size " + byteArray.length);
            }
            p03 p03Var = new p03(1, false, byteArray);
            byteArrayOutputStream2.close();
            arrayList.add(p03Var);
            ByteArrayOutputStream byteArrayOutputStream3 = new ByteArrayOutputStream();
            int i8 = 0;
            int i9 = 0;
            while (i8 < z60VarArr.length) {
                try {
                    z60 z60Var4 = z60VarArr[i8];
                    y61.Z(byteArrayOutputStream3, i8);
                    y61.Z(byteArrayOutputStream3, z60Var4.e);
                    i9 = i9 + 4 + (z60Var4.e * i6);
                    int[] iArr = z60Var4.h;
                    int length3 = iArr.length;
                    int i10 = i3;
                    while (i3 < length3) {
                        int i11 = iArr[i3];
                        y61.Z(byteArrayOutputStream3, i11 - i10);
                        i3++;
                        i6 = i6;
                        i10 = i11;
                    }
                    i8++;
                    i3 = 0;
                } catch (Throwable th) {
                    try {
                        byteArrayOutputStream3.close();
                        throw th;
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                        throw th;
                    }
                }
            }
            int i12 = i6;
            byte[] byteArray2 = byteArrayOutputStream3.toByteArray();
            if (i9 != byteArray2.length) {
                throw new IllegalStateException("Expected size " + i9 + ", does not match actual size " + byteArray2.length);
            }
            p03 p03Var2 = new p03(3, true, byteArray2);
            byteArrayOutputStream3.close();
            arrayList.add(p03Var2);
            ByteArrayOutputStream byteArrayOutputStream4 = new ByteArrayOutputStream();
            int i13 = 0;
            for (int i14 = 0; i14 < z60VarArr.length; i14++) {
                try {
                    z60 z60Var5 = z60VarArr[i14];
                    Iterator it3 = z60Var5.i.entrySet().iterator();
                    int iIntValue = 0;
                    while (it3.hasNext()) {
                        iIntValue |= ((Integer) ((Map.Entry) it3.next()).getValue()).intValue();
                    }
                    ByteArrayOutputStream byteArrayOutputStream5 = new ByteArrayOutputStream();
                    try {
                        l0(byteArrayOutputStream5, iIntValue, z60Var5);
                        byte[] byteArray3 = byteArrayOutputStream5.toByteArray();
                        byteArrayOutputStream5.close();
                        ByteArrayOutputStream byteArrayOutputStream6 = new ByteArrayOutputStream();
                        try {
                            m0(byteArrayOutputStream6, z60Var5);
                            byte[] byteArray4 = byteArrayOutputStream6.toByteArray();
                            byteArrayOutputStream6.close();
                            y61.Z(byteArrayOutputStream4, i14);
                            int length4 = byteArray3.length + 2 + byteArray4.length;
                            int i15 = i13 + 6;
                            y61.Y(byteArrayOutputStream4, length4, 4);
                            y61.Z(byteArrayOutputStream4, iIntValue);
                            byteArrayOutputStream4.write(byteArray3);
                            byteArrayOutputStream4.write(byteArray4);
                            i13 = i15 + length4;
                        } catch (Throwable th3) {
                            try {
                                byteArrayOutputStream6.close();
                                throw th3;
                            } catch (Throwable th4) {
                                th3.addSuppressed(th4);
                                throw th3;
                            }
                        }
                    } catch (Throwable th5) {
                        try {
                            byteArrayOutputStream5.close();
                            throw th5;
                        } catch (Throwable th6) {
                            th5.addSuppressed(th6);
                            throw th5;
                        }
                    }
                } catch (Throwable th7) {
                    try {
                        byteArrayOutputStream4.close();
                        throw th7;
                    } catch (Throwable th8) {
                        th7.addSuppressed(th8);
                        throw th7;
                    }
                }
            }
            byte[] byteArray5 = byteArrayOutputStream4.toByteArray();
            if (i13 != byteArray5.length) {
                throw new IllegalStateException("Expected size " + i13 + ", does not match actual size " + byteArray5.length);
            }
            p03 p03Var3 = new p03(4, true, byteArray5);
            byteArrayOutputStream4.close();
            arrayList.add(p03Var3);
            long size2 = 12 + ((long) (arrayList.size() * 16));
            y61.Y(byteArrayOutputStream, arrayList.size(), 4);
            int i16 = 0;
            while (i16 < arrayList.size()) {
                p03 p03Var4 = (p03) arrayList.get(i16);
                int i17 = p03Var4.a;
                byte[] bArr7 = p03Var4.b;
                if (i17 != 1) {
                    i = i12;
                    if (i17 == i) {
                        i2 = 1;
                    } else if (i17 == 3) {
                        i2 = i;
                    } else if (i17 == 4) {
                        i2 = 3;
                    } else {
                        if (i17 != 5) {
                            throw null;
                        }
                        i2 = 4;
                    }
                } else {
                    i = i12;
                    i2 = 0;
                }
                y61.Y(byteArrayOutputStream, i2, 4);
                y61.Y(byteArrayOutputStream, size2, 4);
                if (p03Var4.c) {
                    long length5 = bArr7.length;
                    byte[] bArrK3 = y61.k(bArr7);
                    arrayList2.add(bArrK3);
                    y61.Y(byteArrayOutputStream, bArrK3.length, 4);
                    y61.Y(byteArrayOutputStream, length5, 4);
                    length = bArrK3.length;
                } else {
                    arrayList2.add(bArr7);
                    y61.Y(byteArrayOutputStream, bArr7.length, 4);
                    y61.Y(byteArrayOutputStream, 0L, 4);
                    length = bArr7.length;
                }
                size2 += (long) length;
                i16++;
                i12 = i;
            }
            for (int i18 = 0; i18 < arrayList2.size(); i18++) {
                byteArrayOutputStream.write((byte[]) arrayList2.get(i18));
            }
            return true;
        } catch (Throwable th9) {
            try {
                byteArrayOutputStream2.close();
                throw th9;
            } catch (Throwable th10) {
                th9.addSuppressed(th10);
                throw th9;
            }
        }
    }

    public static void e(int i, int i2, String str, boolean z) {
        if (z) {
            return;
        }
        bl.d(ck0.I(str, Integer.valueOf(i), Integer.valueOf(i2)));
    }

    public static JSONObject e0(String str, String str2, long j, long j2, boolean z, float f2, String str3, String str4) {
        JSONObject jSONObjectC = c(str, str2);
        if (jSONObjectC == null) {
            return null;
        }
        try {
            jSONObjectC.put("s", z ? "playing" : "paused");
            jSONObjectC.put("p", j2 / 1000.0d);
            jSONObjectC.put("sq", j);
            jSONObjectC.put("sp", f2);
            if (str3 != null) {
                jSONObjectC.put("v", str3);
                jSONObjectC.put("n", str4);
            }
            return jSONObjectC;
        } catch (Exception unused) {
            return null;
        }
    }

    public static void f(int i, String str, boolean z) {
        if (z) {
            return;
        }
        bl.d(ck0.I(str, Integer.valueOf(i)));
    }

    public static Bundle f0(Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        bundle.setClassLoader(ha1.class.getClassLoader());
        try {
            bundle.isEmpty();
            return bundle;
        } catch (BadParcelableException unused) {
            Log.e("MediaSessionCompat", "Could not unparcel the data.");
            return null;
        }
    }

    public static void g(String str, boolean z) {
        if (z) {
            return;
        }
        bl.d(str);
    }

    public static ue0 g0(long j, Class cls, ue0 ue0Var) {
        for (ue0 ue0Var2 : (ue0[]) cls.getEnumConstants()) {
            if (ue0Var2.getValue() == j) {
                return ue0Var2;
            }
        }
        return ue0Var;
    }

    public static void h(boolean z) {
        if (z) {
            return;
        }
        p12.f();
    }

    public static void h0(ArrayList arrayList, String str, String str2) {
        arrayList.add(str + ": " + str2);
    }

    public static void i(boolean z, String str, long j) {
        if (z) {
            return;
        }
        bl.d(ck0.I(str, Long.valueOf(j)));
    }

    public static Integer i0(Object obj, String str, ArrayList arrayList) {
        Double dValueOf;
        if (obj != null) {
            if (obj instanceof Number) {
                dValueOf = Double.valueOf(((Number) obj).doubleValue());
            } else if (obj instanceof CharSequence) {
                String strTrim = obj.toString().trim();
                if (!strTrim.isEmpty()) {
                    try {
                        dValueOf = Double.valueOf(Double.parseDouble(strTrim));
                    } catch (NumberFormatException unused) {
                        dValueOf = null;
                    }
                }
            } else {
                dValueOf = null;
            }
            if (dValueOf != null && !dValueOf.isNaN() && !dValueOf.isInfinite() && dValueOf.doubleValue() == Math.rint(dValueOf.doubleValue()) && dValueOf.doubleValue() >= -2.147483648E9d && dValueOf.doubleValue() <= 2.147483647E9d) {
                return Integer.valueOf((int) dValueOf.doubleValue());
            }
            h0(arrayList, str, O(obj).concat(" is not a whole number"));
            return null;
        }
        return null;
    }

    public static void j(boolean z, String str, long j, long j2) {
        if (z) {
            return;
        }
        bl.d(ck0.I(str, Long.valueOf(j), Long.valueOf(j2)));
    }

    public static void j0(ByteArrayOutputStream byteArrayOutputStream, z60 z60Var) throws IOException {
        m0(byteArrayOutputStream, z60Var);
        int i = z60Var.g;
        int[] iArr = z60Var.h;
        int length = iArr.length;
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            int i4 = iArr[i2];
            y61.Z(byteArrayOutputStream, i4 - i3);
            i2++;
            i3 = i4;
        }
        byte[] bArr = new byte[(((i * 2) + 7) & (-8)) / 8];
        for (Map.Entry entry : z60Var.i.entrySet()) {
            int iIntValue = ((Integer) entry.getKey()).intValue();
            int iIntValue2 = ((Integer) entry.getValue()).intValue();
            if ((iIntValue2 & 2) != 0) {
                int i5 = iIntValue / 8;
                bArr[i5] = (byte) (bArr[i5] | (1 << (iIntValue % 8)));
            }
            if ((iIntValue2 & 4) != 0) {
                int i6 = iIntValue + i;
                int i7 = i6 / 8;
                bArr[i7] = (byte) ((1 << (i6 % 8)) | bArr[i7]);
            }
        }
        byteArrayOutputStream.write(bArr);
    }

    public static void k(boolean z, String str, Object obj) {
        if (z) {
            return;
        }
        bl.d(ck0.I(str, obj));
    }

    public static void k0(ByteArrayOutputStream byteArrayOutputStream, z60 z60Var, String str) throws IOException {
        Charset charset = StandardCharsets.UTF_8;
        y61.Z(byteArrayOutputStream, str.getBytes(charset).length);
        y61.Z(byteArrayOutputStream, z60Var.e);
        y61.Y(byteArrayOutputStream, z60Var.f, 4);
        y61.Y(byteArrayOutputStream, z60Var.c, 4);
        y61.Y(byteArrayOutputStream, z60Var.g, 4);
        byteArrayOutputStream.write(str.getBytes(charset));
    }

    public static void l(boolean z, String str, Object obj, Comparable comparable) {
        if (z) {
            return;
        }
        bl.d(ck0.I(str, obj, comparable));
    }

    public static void l0(ByteArrayOutputStream byteArrayOutputStream, int i, z60 z60Var) throws IOException {
        int i2 = z60Var.g;
        byte[] bArr = new byte[(((Integer.bitCount(i & (-2)) * i2) + 7) & (-8)) / 8];
        for (Map.Entry entry : z60Var.i.entrySet()) {
            int iIntValue = ((Integer) entry.getKey()).intValue();
            int iIntValue2 = ((Integer) entry.getValue()).intValue();
            int i3 = 0;
            for (int i4 = 1; i4 <= 4; i4 <<= 1) {
                if (i4 != 1 && (i4 & i) != 0) {
                    if ((i4 & iIntValue2) == i4) {
                        int i5 = (i3 * i2) + iIntValue;
                        int i6 = i5 / 8;
                        bArr[i6] = (byte) ((1 << (i5 % 8)) | bArr[i6]);
                    }
                    i3++;
                }
            }
        }
        byteArrayOutputStream.write(bArr);
    }

    public static void m(int i, int i2) {
        String strI;
        if (i < 0 || i >= i2) {
            if (i < 0) {
                strI = ck0.I("%s (%s) must not be negative", "index", Integer.valueOf(i));
            } else {
                if (i2 < 0) {
                    bl.d(uh0.r(i2, "negative size: "));
                    return;
                }
                strI = ck0.I("%s (%s) must be less than size (%s)", "index", Integer.valueOf(i), Integer.valueOf(i2));
            }
            throw new IndexOutOfBoundsException(strI);
        }
    }

    public static void m0(ByteArrayOutputStream byteArrayOutputStream, z60 z60Var) {
        int i = 0;
        for (Map.Entry entry : z60Var.i.entrySet()) {
            int iIntValue = ((Integer) entry.getKey()).intValue();
            if ((((Integer) entry.getValue()).intValue() & 1) != 0) {
                y61.Z(byteArrayOutputStream, iIntValue - i);
                y61.Z(byteArrayOutputStream, 0);
                i = iIntValue;
            }
        }
    }

    public static void n(Object obj) {
        obj.getClass();
    }

    public static void o(Object obj, String str) {
        if (obj == null) {
            throw new NullPointerException(str);
        }
    }

    public static void p(int i, int i2) {
        if (i < 0 || i > i2) {
            throw new IndexOutOfBoundsException(b("index", i, i2));
        }
    }

    public static void q(int i, int i2, int i3) {
        String strB;
        if (i < 0 || i2 < i || i2 > i3) {
            if (i < 0 || i > i3) {
                strB = b("start index", i, i3);
            } else {
                strB = (i2 < 0 || i2 > i3) ? b("end index", i2, i3) : ck0.I("end index (%s) must not be less than start index (%s)", Integer.valueOf(i2), Integer.valueOf(i));
            }
            throw new IndexOutOfBoundsException(strB);
        }
    }

    public static void r(String str, boolean z) {
        if (z) {
            return;
        }
        bl.h(str);
    }

    public static void s(boolean z) {
        if (z) {
            return;
        }
        q1.f();
    }

    public static sh0 t(Context context, Uri uri) throws IOException {
        sh0 sh0Var = new sh0();
        sh0Var.g = (char) 60000;
        Charset.defaultCharset();
        sh0Var.b = null;
        sh0Var.c = null;
        sh0Var.d = null;
        boolean zE = false;
        sh0Var.a = (short) 0;
        sh0Var.e = gf2.h;
        sh0Var.f = gf2.i;
        boolean z = true;
        sh0Var.p = true;
        sh0Var.k = new ArrayList();
        sh0Var.l = false;
        sh0Var.m = null;
        sh0Var.n = qh0.s;
        sh0Var.o = new ov1(sh0Var);
        sh0Var.u = Duration.ofMillis(-1L);
        new Random();
        sh0Var.z = true;
        sh0Var.B = new q30();
        Duration duration = Duration.ZERO;
        Duration.ofSeconds(1L);
        sh0Var.F = new r80((Object) sh0Var, (byte) 5);
        sh0Var.I = Boolean.getBoolean("org.apache.commons.net.ftp.ipAddressFromPasvResponse");
        sh0Var.j();
        sh0Var.G = true;
        sh0Var.g = (char) 15000;
        sh0Var.a = (short) 20000;
        Duration durationOfSeconds = Duration.ofSeconds(20L);
        if (durationOfSeconds != null) {
            duration = durationOfSeconds;
        }
        sh0Var.u = duration;
        try {
            sh0Var.b(uri.getPort() > 0 ? uri.getPort() : 21, uri.getHost());
            if (!lt2.E(sh0Var.j)) {
                throw new bo0(sh0Var.j, sh0Var.e());
            }
            String strK = gj1.k(context, uri);
            String strI = gj1.i(context, uri);
            if (strK.isEmpty()) {
                strK = "anonymous";
                strI = "anonymous@";
            }
            sh0Var.g("USER", strK);
            if (lt2.E(sh0Var.j)) {
                zE = true;
            } else {
                int i = sh0Var.j;
                if (i < 300 || i >= 400) {
                    z = false;
                }
                if (z) {
                    zE = lt2.E(sh0Var.g("PASS", strI));
                }
            }
            if (!zE) {
                throw new bo0(sh0Var.j, sh0Var.e());
            }
            sh0Var.t = (byte) 2;
            sh0Var.w = null;
            sh0Var.v = -1;
            if (lt2.E(sh0Var.g("TYPE", "I"))) {
                sh0Var.x = (byte) 2;
            }
            return sh0Var;
        } catch (IOException e2) {
            w(sh0Var);
            throw e2;
        }
    }

    public static xi u(wi wiVar, Drawable drawable, int i, int i2) {
        Bitmap bitmap;
        Drawable current = drawable.getCurrent();
        boolean z = false;
        if (current instanceof BitmapDrawable) {
            bitmap = ((BitmapDrawable) current).getBitmap();
        } else if (current instanceof Animatable) {
            bitmap = null;
        } else {
            if (i != Integer.MIN_VALUE || current.getIntrinsicWidth() > 0) {
                if (i2 != Integer.MIN_VALUE || current.getIntrinsicHeight() > 0) {
                    if (current.getIntrinsicWidth() > 0) {
                        i = current.getIntrinsicWidth();
                    }
                    if (current.getIntrinsicHeight() > 0) {
                        i2 = current.getIntrinsicHeight();
                    }
                    Lock lock = wq2.b;
                    lock.lock();
                    Bitmap bitmapG = wiVar.g(i, i2, Bitmap.Config.ARGB_8888);
                    try {
                        Canvas canvas = new Canvas(bitmapG);
                        current.setBounds(0, 0, i, i2);
                        current.draw(canvas);
                        canvas.setBitmap(null);
                        lock.unlock();
                        bitmap = bitmapG;
                    } catch (Throwable th) {
                        lock.unlock();
                        throw th;
                    }
                } else if (Log.isLoggable("DrawableToBitmap", 5)) {
                    Log.w("DrawableToBitmap", "Unable to draw " + current + " to Bitmap with Target.SIZE_ORIGINAL because the Drawable has no intrinsic height");
                }
                z = true;
            } else if (Log.isLoggable("DrawableToBitmap", 5)) {
                Log.w("DrawableToBitmap", "Unable to draw " + current + " to Bitmap with Target.SIZE_ORIGINAL because the Drawable has no intrinsic width");
            }
            bitmap = null;
            z = true;
        }
        if (!z) {
            wiVar = a;
        }
        return xi.c(wiVar, bitmap);
    }

    public static byte[] v(z60[] z60VarArr, byte[] bArr) throws IOException {
        int i = 0;
        int length = 0;
        for (z60 z60Var : z60VarArr) {
            length += ((((z60Var.g * 2) + 7) & (-8)) / 8) + (z60Var.e * 2) + x(z60Var.a, z60Var.b, bArr).getBytes(StandardCharsets.UTF_8).length + 16 + z60Var.f;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(length);
        if (Arrays.equals(bArr, dn1.l)) {
            int length2 = z60VarArr.length;
            while (i < length2) {
                z60 z60Var2 = z60VarArr[i];
                k0(byteArrayOutputStream, z60Var2, x(z60Var2.a, z60Var2.b, bArr));
                j0(byteArrayOutputStream, z60Var2);
                i++;
            }
        } else {
            for (z60 z60Var3 : z60VarArr) {
                k0(byteArrayOutputStream, z60Var3, x(z60Var3.a, z60Var3.b, bArr));
            }
            int length3 = z60VarArr.length;
            while (i < length3) {
                j0(byteArrayOutputStream, z60VarArr[i]);
                i++;
            }
        }
        if (byteArrayOutputStream.size() == length) {
            return byteArrayOutputStream.toByteArray();
        }
        throw new IllegalStateException("The bytes saved do not match expectation. actual=" + byteArrayOutputStream.size() + " expected=" + length);
    }

    public static void w(sh0 sh0Var) {
        try {
            dv0.a(sh0Var.b);
            dv0.a(sh0Var.c);
            dv0.a(sh0Var.d);
            sh0Var.b = null;
            sh0Var.c = null;
            sh0Var.d = null;
            sh0Var.q = null;
            sh0Var.r = null;
            sh0Var.l = false;
            sh0Var.m = null;
            sh0Var.j();
        } catch (Exception unused) {
        }
    }

    public static String x(String str, String str2, byte[] bArr) {
        byte[] bArr2 = dn1.m;
        byte[] bArr3 = dn1.n;
        Object obj = (Arrays.equals(bArr, bArr3) || Arrays.equals(bArr, bArr2)) ? ":" : "!";
        if (str.length() <= 0) {
            if ("!".equals(obj)) {
                return str2.replace(":", "!");
            }
            if (":".equals(obj)) {
                return str2.replace("!", ":");
            }
        } else {
            if (str2.equals("classes.dex")) {
                return str;
            }
            if (str2.contains("!") || str2.contains(":")) {
                if ("!".equals(obj)) {
                    return str2.replace(":", "!");
                }
                if (":".equals(obj)) {
                    return str2.replace("!", ":");
                }
            } else if (!str2.endsWith(".apk")) {
                return jf2.h(new StringBuilder(str), (Arrays.equals(bArr, bArr3) || Arrays.equals(bArr, bArr2)) ? ":" : "!", str2);
            }
        }
        return str2;
    }

    public static Drawable z(Context context, int i) {
        return i12.d().f(context, i);
    }

    public abstract void I(int i);

    public abstract void J(Typeface typeface, boolean z);

    public void y(pc2 pc2Var, float f2, float f3) {
        float f4 = f3 * f2;
        pc2Var.d(0.0f, f4, 180.0f, 90.0f);
        double d2 = f4;
        pc2Var.c((float) (Math.sin(Math.toRadians(90.0d)) * d2), (float) (Math.sin(Math.toRadians(0.0d)) * d2));
    }
}
