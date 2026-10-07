package defpackage;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.LinearGradient;
import android.graphics.PorterDuff;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.util.Xml;
import android.widget.ImageView;
import androidx.media3.decoder.VideoDecoderOutputBuffer;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: compiled from: r8-map-id-0cee80150bab987b3edc91bcfc1e04786a1418881e5c92a3b999d44d61001263 */
/* JADX INFO: loaded from: classes.dex */
public class n8 implements ji, bp0 {
    public final /* synthetic */ byte l;
    public int m;
    public Object n;
    public Object o;

    public n8(qx qxVar) {
        this.l = (byte) 2;
        this.o = ag.J(150, new r80((Object) this, (byte) 3));
        this.n = qxVar;
    }

    public static n8 f(Resources resources, int i, Resources.Theme theme) {
        int next;
        float f;
        float f2;
        Shader.TileMode tileMode;
        Shader radialGradient;
        Shader.TileMode tileMode2;
        XmlResourceParser xml = resources.getXml(i);
        AttributeSet attributeSetAsAttributeSet = Xml.asAttributeSet(xml);
        do {
            next = xml.next();
            if (next == 2) {
                break;
            }
        } while (next != 1);
        if (next != 2) {
            throw new XmlPullParserException("No start tag found");
        }
        String name = xml.getName();
        name.getClass();
        if (!name.equals("gradient")) {
            if (name.equals("selector")) {
                ColorStateList colorStateListB = du.b(resources, xml, attributeSetAsAttributeSet, theme);
                return new n8((Shader) null, colorStateListB, colorStateListB.getDefaultColor());
            }
            throw new XmlPullParserException(xml.getPositionDescription() + ": unsupported complex color tag " + name);
        }
        String name2 = xml.getName();
        if (!name2.equals("gradient")) {
            throw new XmlPullParserException(xml.getPositionDescription() + ": invalid gradient color tag " + name2);
        }
        TypedArray typedArrayD = ij0.D(resources, theme, attributeSetAsAttributeSet, hw1.d);
        float f3 = xml.getAttributeValue("http://schemas.android.com/apk/res/android", "startX") != null ? typedArrayD.getFloat(8, 0.0f) : 0.0f;
        float f4 = xml.getAttributeValue("http://schemas.android.com/apk/res/android", "startY") != null ? typedArrayD.getFloat(9, 0.0f) : 0.0f;
        float f5 = xml.getAttributeValue("http://schemas.android.com/apk/res/android", "endX") != null ? typedArrayD.getFloat(10, 0.0f) : 0.0f;
        float f6 = xml.getAttributeValue("http://schemas.android.com/apk/res/android", "endY") != null ? typedArrayD.getFloat(11, 0.0f) : 0.0f;
        float f7 = xml.getAttributeValue("http://schemas.android.com/apk/res/android", "centerX") != null ? typedArrayD.getFloat(3, 0.0f) : 0.0f;
        float f8 = xml.getAttributeValue("http://schemas.android.com/apk/res/android", "centerY") != null ? typedArrayD.getFloat(4, 0.0f) : 0.0f;
        int i2 = xml.getAttributeValue("http://schemas.android.com/apk/res/android", "type") != null ? typedArrayD.getInt(2, 0) : 0;
        int color = xml.getAttributeValue("http://schemas.android.com/apk/res/android", "startColor") != null ? typedArrayD.getColor(0, 0) : 0;
        boolean z = xml.getAttributeValue("http://schemas.android.com/apk/res/android", "centerColor") != null;
        int color2 = xml.getAttributeValue("http://schemas.android.com/apk/res/android", "centerColor") != null ? typedArrayD.getColor(7, 0) : 0;
        int color3 = xml.getAttributeValue("http://schemas.android.com/apk/res/android", "endColor") != null ? typedArrayD.getColor(1, 0) : 0;
        int i3 = xml.getAttributeValue("http://schemas.android.com/apk/res/android", "tileMode") != null ? typedArrayD.getInt(6, 0) : 0;
        float f9 = xml.getAttributeValue("http://schemas.android.com/apk/res/android", "gradientRadius") != null ? typedArrayD.getFloat(5, 0.0f) : 0.0f;
        typedArrayD.recycle();
        int depth = xml.getDepth() + 1;
        ArrayList arrayList = new ArrayList(20);
        float f10 = f9;
        ArrayList arrayList2 = new ArrayList(20);
        while (true) {
            int next2 = xml.next();
            f = f5;
            if (next2 == 1) {
                f2 = f6;
                break;
            }
            int depth2 = xml.getDepth();
            f2 = f6;
            if (depth2 < depth && next2 == 3) {
                break;
            }
            if (next2 == 2 && depth2 <= depth && xml.getName().equals("item")) {
                TypedArray typedArrayD2 = ij0.D(resources, theme, attributeSetAsAttributeSet, hw1.e);
                boolean zHasValue = typedArrayD2.hasValue(0);
                boolean zHasValue2 = typedArrayD2.hasValue(1);
                if (!zHasValue || !zHasValue2) {
                    throw new XmlPullParserException(xml.getPositionDescription() + ": <item> tag requires a 'color' attribute and a 'offset' attribute!");
                }
                int color4 = typedArrayD2.getColor(0, 0);
                float f11 = typedArrayD2.getFloat(1, 0.0f);
                typedArrayD2.recycle();
                arrayList2.add(Integer.valueOf(color4));
                arrayList.add(Float.valueOf(f11));
            }
            f5 = f;
            f6 = f2;
        }
        ng0 ng0Var = arrayList2.size() > 0 ? new ng0(arrayList2, arrayList) : null;
        if (ng0Var == null) {
            ng0Var = z ? new ng0(color, color2, color3) : new ng0(color, color3);
        }
        if (i2 != 1) {
            if (i2 != 2) {
                int[] iArr = (int[]) ng0Var.m;
                float[] fArr = (float[]) ng0Var.n;
                if (i3 != 1) {
                    tileMode2 = i3 != 2 ? Shader.TileMode.CLAMP : Shader.TileMode.MIRROR;
                } else {
                    tileMode2 = Shader.TileMode.REPEAT;
                }
                radialGradient = new LinearGradient(f3, f4, f, f2, iArr, fArr, tileMode2);
            } else {
                radialGradient = new SweepGradient(f7, f8, (int[]) ng0Var.m, (float[]) ng0Var.n);
            }
        } else {
            if (f10 <= 0.0f) {
                throw new XmlPullParserException("<gradient> tag requires 'gradientRadius' attribute with radial type");
            }
            int[] iArr2 = (int[]) ng0Var.m;
            float[] fArr2 = (float[]) ng0Var.n;
            if (i3 != 1) {
                tileMode = i3 != 2 ? Shader.TileMode.CLAMP : Shader.TileMode.MIRROR;
            } else {
                tileMode = Shader.TileMode.REPEAT;
            }
            radialGradient = new RadialGradient(f7, f8, f10, iArr2, fArr2, tileMode);
        }
        return new n8(radialGradient, (ColorStateList) null, 0);
    }

    public void A(x32 x32Var) {
        String strB = x32Var.c.b("CSeq");
        strB.getClass();
        int i = Integer.parseInt(strB);
        i32 i32Var = (i32) this.o;
        SparseArray sparseArray = i32Var.p;
        ha1.s(sparseArray.get(i) == null);
        sparseArray.append(i, x32Var);
        i32Var.s.g(w32.f(x32Var));
        this.n = x32Var;
    }

    public int B(byte[] bArr, byte[] bArr2) throws IOException {
        vh1 vh1Var = (vh1) this.o;
        f52 f52Var = (f52) vh1Var.b(new e52(vh1Var.l, vh1Var.n, vh1Var.p.m.a, 1163287L, vh1Var.q, new na(bArr, bArr.length), vh1Var.r), vh1.t);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(4096);
        try {
            byteArrayOutputStream.write(f52Var.e);
            if (tj1.b(((u52) f52Var.a).j).equals(tj1.STATUS_BUFFER_OVERFLOW)) {
                byteArrayOutputStream.write(vh1Var.g());
            }
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            System.arraycopy(byteArray, 0, bArr2, 0, byteArray.length);
            return byteArray.length;
        } catch (IOException e) {
            throw new b72(e);
        }
    }

    public void C(int i, long j, long j2) {
        c91 c91Var = new c91(1, i, null, 3, null, qt2.p0(j), qt2.p0(j2));
        pc1 pc1Var = (pc1) this.n;
        pc1Var.getClass();
        g(new nk((Object) this, (Object) pc1Var, (Object) c91Var, (byte) 6));
    }

    @Override // defpackage.ji
    public ii a(nh0 nh0Var, long j) {
        long position = nh0Var.getPosition();
        long jI = i(nh0Var);
        long jY = nh0Var.y();
        nh0Var.D(Math.max(6, ((wk0) this.n).c));
        long jI2 = i(nh0Var);
        long jY2 = nh0Var.y();
        if (jI > j || jI2 <= j) {
            return jI2 <= j ? new ii(-2, jI2, jY2) : new ii(-1, jI, position);
        }
        return new ii(0, -9223372036854775807L, jY);
    }

    public void c() {
        cw cwVar;
        ImageView imageView = (ImageView) this.n;
        Drawable drawable = imageView.getDrawable();
        if (drawable != null) {
            z90.a(drawable);
        }
        if (drawable == null || (cwVar = (cw) this.o) == null) {
            return;
        }
        int[] drawableState = imageView.getDrawableState();
        PorterDuff.Mode mode = i8.b;
        k12.o(drawable, cwVar, drawableState);
    }

    public iz1 d(boolean z) {
        sw0 sw0Var;
        sw0 sw0Var2;
        if (z && (sw0Var2 = (sw0) this.o) != null) {
            throw sw0Var2.a();
        }
        iz1 iz1VarG = iz1.g(this.m, (Object[]) this.n, this);
        if (!z || (sw0Var = (sw0) this.o) == null) {
            return iz1VarG;
        }
        throw sw0Var.a();
    }

    public tw0 e() {
        return d(false);
    }

    public void g(cx cxVar) {
        for (uc1 uc1Var : (CopyOnWriteArrayList) this.o) {
            qt2.d0(uc1Var.a, new q7((Object) cxVar, (Object) uc1Var.b, (byte) 29));
        }
    }

    public void h(int i, zl0 zl0Var, int i2, Object obj, long j) {
        g(new fk((Object) this, (Object) new c91(1, i, zl0Var, i2, obj, qt2.p0(j), -9223372036854775807L), (byte) 15));
    }

    public long i(nh0 nh0Var) {
        int iE;
        uk0 uk0Var = (uk0) this.o;
        wk0 wk0Var = (wk0) this.n;
        while (nh0Var.y() < nh0Var.getLength() - 6) {
            int i = this.m;
            long jY = nh0Var.y();
            gn1 gn1Var = new gn1(17);
            int i2 = 0;
            boolean zE = false;
            nh0Var.j(0, gn1Var.a, 2);
            if (gn1Var.g(0, ByteOrder.BIG_ENDIAN) != i) {
                nh0Var.m();
                nh0Var.D((int) (jY - nh0Var.getPosition()));
            } else {
                byte[] bArr = gn1Var.a;
                while (i2 < 15 && (iE = nh0Var.E(2 + i2, bArr, 15 - i2)) != -1) {
                    i2 += iE;
                }
                gn1Var.M(i2 + 2);
                nh0Var.m();
                nh0Var.D((int) (jY - nh0Var.getPosition()));
                zE = gj0.e(gn1Var, wk0Var, i, uk0Var);
            }
            if (zE) {
                break;
            }
            nh0Var.D(1);
        }
        if (nh0Var.y() < nh0Var.getLength() - 6) {
            return uk0Var.a;
        }
        nh0Var.D((int) (nh0Var.getLength() - nh0Var.y()));
        return wk0Var.j;
    }

    public Object j(int i) {
        SparseArray sparseArray = (SparseArray) this.n;
        if (this.m == -1) {
            this.m = 0;
        }
        while (true) {
            int i2 = this.m;
            if (i2 <= 0 || i >= sparseArray.keyAt(i2)) {
                break;
            }
            this.m--;
        }
        while (this.m < sparseArray.size() - 1 && i >= sparseArray.keyAt(this.m + 1)) {
            this.m++;
        }
        return sparseArray.valueAt(this.m);
    }

    public x32 k(int i, String str, Map map, Uri uri) {
        i32 i32Var = (i32) this.o;
        int i2 = this.m;
        this.m = i2 + 1;
        nf1 nf1Var = new nf1(str, i2);
        if (i32Var.x != null) {
            i32Var.t.getClass();
            try {
                nf1Var.H("Authorization", i32Var.x.a(i32Var.t, uri, i));
            } catch (hn1 e) {
                i32Var.g(new uz(e));
            }
        }
        for (Map.Entry entry : map.entrySet()) {
            nf1Var.H((String) entry.getKey(), (String) entry.getValue());
        }
        return new x32(uri, i, new k32(nf1Var), "");
    }

    public boolean m() {
        ColorStateList colorStateList;
        return ((Shader) this.n) == null && (colorStateList = (ColorStateList) this.o) != null && colorStateList.isStateful();
    }

    public void n(s21 s21Var, int i) {
        o(s21Var, i, -1, null, 0, null, -9223372036854775807L, -9223372036854775807L);
    }

    public void o(s21 s21Var, int i, int i2, zl0 zl0Var, int i3, Object obj, long j, long j2) {
        g(new sc1(this, s21Var, new c91(i, i2, zl0Var, i3, obj, qt2.p0(j), qt2.p0(j2)), (byte) 1));
    }

    public void p(s21 s21Var, int i) {
        r(s21Var, i, -1, null, 0, null, -9223372036854775807L, -9223372036854775807L);
    }

    @Override // defpackage.bp0
    public void q(Object obj) {
        List list = (List) obj;
        hb1 hb1Var = (hb1) this.o;
        pa1 pa1Var = hb1Var.i;
        t91 t91Var = (t91) this.n;
        int i = this.m;
        pa1Var.getClass();
        pa1 pa1Var2 = hb1Var.i;
        if (i == -1) {
            pa1Var2.t();
            pa1Var2.t.o0(list);
        } else {
            pa1Var2.t();
            pa1Var2.t.r(i, list);
        }
        oo1 oo1Var = new oo1();
        oo1Var.a.b(20);
        oo1Var.b();
        pa1Var2.n(t91Var);
    }

    public void r(s21 s21Var, int i, int i2, zl0 zl0Var, int i3, Object obj, long j, long j2) {
        g(new sc1(this, s21Var, new c91(i, i2, zl0Var, i3, obj, qt2.p0(j), qt2.p0(j2)), (byte) 0));
    }

    public void s(s21 s21Var, int i, int i2, zl0 zl0Var, int i3, Object obj, long j, long j2, IOException iOException, boolean z) {
        g(new tc1(this, s21Var, new c91(i, i2, zl0Var, i3, obj, qt2.p0(j), qt2.p0(j2)), iOException, z));
    }

    public void t(s21 s21Var, int i, IOException iOException, boolean z) {
        s(s21Var, i, -1, null, 0, null, -9223372036854775807L, -9223372036854775807L, iOException, z);
    }

    public String toString() {
        switch (this.l) {
            case VideoDecoderOutputBuffer.COLORSPACE_BT2020 /* 3 */:
                StringBuilder sb = new StringBuilder("FileNotifyInformation{action=");
                sb.append((yj0) this.n);
                sb.append(", fileName='");
                return lf2.h(sb, (String) this.o, "'}");
            case 13:
                StringBuilder sb2 = new StringBuilder();
                if (((mv1) this.n) == mv1.HTTP_1_0) {
                    sb2.append("HTTP/1.0");
                } else {
                    sb2.append("HTTP/1.1");
                }
                sb2.append(' ');
                sb2.append(this.m);
                sb2.append(' ');
                sb2.append((String) this.o);
                return sb2.toString();
            default:
                return super.toString();
        }
    }

    public void u(AttributeSet attributeSet, int i) {
        int resourceId;
        ImageView imageView = (ImageView) this.n;
        Context context = imageView.getContext();
        int[] iArr = mw1.f;
        yj2 yj2VarN = yj2.N(i, 0, context, attributeSet, iArr);
        TypedArray typedArray = (TypedArray) yj2VarN.n;
        lw2.m(imageView, imageView.getContext(), iArr, attributeSet, (TypedArray) yj2VarN.n, i, 0);
        try {
            Drawable drawable = imageView.getDrawable();
            if (drawable == null && (resourceId = typedArray.getResourceId(1, -1)) != -1 && (drawable = ha1.z(imageView.getContext(), resourceId)) != null) {
                imageView.setImageDrawable(drawable);
            }
            if (drawable != null) {
                z90.a(drawable);
            }
            if (typedArray.hasValue(2)) {
                imageView.setImageTintList(yj2VarN.F(2));
            }
            if (typedArray.hasValue(3)) {
                imageView.setImageTintMode(z90.c(typedArray.getInt(3, -1), null));
            }
        } finally {
            yj2VarN.S();
        }
    }

    public void v(s21 s21Var, int i, int i2) {
        w(s21Var, i, -1, null, 0, null, -9223372036854775807L, -9223372036854775807L, i2);
    }

    public void w(s21 s21Var, int i, int i2, zl0 zl0Var, int i3, Object obj, long j, long j2, int i4) {
        g(new rc1(this, s21Var, new c91(i, i2, zl0Var, i3, obj, qt2.p0(j), qt2.p0(j2)), i4));
    }

    public n8 x(Object obj, Object obj2) {
        int i = (this.m + 1) * 2;
        Object[] objArr = (Object[]) this.n;
        if (i > objArr.length) {
            this.n = Arrays.copyOf(objArr, iw0.b(objArr.length, i));
        }
        ij0.c(obj, obj2);
        Object[] objArr2 = (Object[]) this.n;
        int i2 = this.m;
        int i3 = i2 * 2;
        objArr2[i3] = obj;
        objArr2[i3 + 1] = obj2;
        this.m = i2 + 1;
        return this;
    }

    public n8 y(Iterable iterable) {
        if (iterable instanceof Collection) {
            int size = (((Collection) iterable).size() + this.m) * 2;
            Object[] objArr = (Object[]) this.n;
            if (size > objArr.length) {
                this.n = Arrays.copyOf(objArr, iw0.b(objArr.length, size));
            }
        }
        Iterator it = iterable.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            x(entry.getKey(), entry.getValue());
        }
        return this;
    }

    public void z() {
        ((x32) this.n).getClass();
        qw0 qw0Var = ((x32) this.n).c.a;
        HashMap map = new HashMap();
        for (String str : qw0Var.p.keySet()) {
            if (!str.equals("CSeq") && !str.equals("User-Agent") && !str.equals("Session") && !str.equals("Authorization")) {
                map.put(str, (String) sj.q(qw0Var.i(str)));
            }
        }
        x32 x32Var = (x32) this.n;
        A(k(x32Var.b, ((i32) this.o).u, map, x32Var.a));
    }

    public /* synthetic */ n8(Object obj, int i, String str, byte b) {
        this.l = b;
        this.n = obj;
        this.m = i;
        this.o = str;
    }

    public n8(vh1 vh1Var) {
        this.l = (byte) 11;
        this.n = new AtomicInteger();
        this.m = 16384;
        this.o = vh1Var;
    }

    public n8(int i, k32 k32Var, String str) {
        this.l = (byte) 10;
        this.m = i;
        this.n = k32Var;
        this.o = str;
    }

    public n8(ImageView imageView) {
        this.l = (byte) 0;
        this.m = 0;
        this.n = imageView;
    }

    @Override // defpackage.ji
    public void b() {
    }

    public n8(r12 r12Var) {
        this.l = (byte) 12;
        this.n = new SparseArray();
        this.o = r12Var;
        this.m = -1;
    }

    @Override // defpackage.bp0
    public void l(Throwable th) {
    }

    public n8(Shader shader, ColorStateList colorStateList, int i) {
        this.l = (byte) 1;
        this.n = shader;
        this.o = colorStateList;
        this.m = i;
    }

    public n8(wk0 wk0Var, int i) {
        this.l = (byte) 4;
        this.n = wk0Var;
        this.m = i;
        this.o = new uk0();
    }

    public n8(CopyOnWriteArrayList copyOnWriteArrayList, int i, pc1 pc1Var) {
        this.l = (byte) 8;
        this.o = copyOnWriteArrayList;
        this.m = i;
        this.n = pc1Var;
    }

    public n8(i32 i32Var) {
        this.l = (byte) 9;
        this.o = i32Var;
    }

    public n8(int i) {
        this.l = (byte) 5;
        this.n = new Object[i * 2];
        this.m = 0;
    }

    public /* synthetic */ n8() {
        this.l = (byte) 3;
    }

    public n8(hb1 hb1Var, t91 t91Var, int i) {
        this.l = (byte) 7;
        this.o = hb1Var;
        this.n = t91Var;
        this.m = i;
    }
}
