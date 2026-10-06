package defpackage;

import android.opengl.GLES20;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.util.SparseIntArray;
import j$.util.Objects;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-ba8d2c2760819bd03a19aa4133a73a2576203ef152dace5b65b91a4c4fd30466 */
/* JADX INFO: loaded from: classes.dex */
public final class vp2 implements h82 {
    public final int l;
    public final Object m;
    public final Cloneable n;
    public final Object o;
    public final Object p;

    /* JADX WARN: Type inference failed for: r5v6, types: [java.lang.Cloneable, xa0[]] */
    public vp2(String str, String str2) throws rp0 {
        byte b;
        int iGlCreateProgram = GLES20.glCreateProgram();
        this.l = iGlCreateProgram;
        ev2.i();
        a(str, iGlCreateProgram, 35633);
        a(str2, iGlCreateProgram, 35632);
        GLES20.glLinkProgram(iGlCreateProgram);
        int[] iArr = {0};
        GLES20.glGetProgramiv(iGlCreateProgram, 35714, iArr, 0);
        ev2.j("Unable to link shader program: \n" + GLES20.glGetProgramInfoLog(iGlCreateProgram), iArr[0] == 1);
        GLES20.glUseProgram(iGlCreateProgram);
        this.o = new HashMap();
        int[] iArr2 = new int[1];
        GLES20.glGetProgramiv(iGlCreateProgram, 35721, iArr2, 0);
        this.m = new fa0[iArr2[0]];
        int i = 0;
        while (true) {
            b = 16;
            if (i >= iArr2[0]) {
                break;
            }
            int i2 = this.l;
            int[] iArr3 = new int[1];
            GLES20.glGetProgramiv(i2, 35722, iArr3, 0);
            int i3 = iArr3[0];
            byte[] bArr = new byte[i3];
            GLES20.glGetActiveAttrib(i2, i, i3, new int[1], 0, new int[1], 0, new int[1], 0, bArr, 0);
            for (int i4 = 0; i4 < i3; i4++) {
                if (bArr[i4] == 0) {
                    i3 = i4;
                    break;
                }
            }
            String str3 = new String(bArr, 0, i3);
            GLES20.glGetAttribLocation(i2, str3);
            fa0 fa0Var = new fa0(b);
            ((fa0[]) this.m)[i] = fa0Var;
            ((HashMap) this.o).put(str3, fa0Var);
            i++;
        }
        this.p = new HashMap();
        int[] iArr4 = new int[1];
        GLES20.glGetProgramiv(this.l, 35718, iArr4, 0);
        this.n = new xa0[iArr4[0]];
        for (int i5 = 0; i5 < iArr4[0]; i5++) {
            int i6 = this.l;
            int[] iArr5 = new int[1];
            GLES20.glGetProgramiv(i6, 35719, iArr5, 0);
            int i7 = iArr5[0];
            byte[] bArr2 = new byte[i7];
            GLES20.glGetActiveUniform(i6, i5, i7, new int[1], 0, new int[1], 0, new int[1], 0, bArr2, 0);
            for (int i8 = 0; i8 < i7; i8++) {
                if (bArr2[i8] == 0) {
                    i7 = i8;
                    break;
                }
            }
            String str4 = new String(bArr2, 0, i7);
            GLES20.glGetUniformLocation(i6, str4);
            xa0 xa0Var = new xa0(b);
            ((xa0[]) this.n)[i5] = xa0Var;
            ((HashMap) this.p).put(str4, xa0Var);
        }
        ev2.i();
    }

    public static void a(String str, int i, int i2) throws rp0 {
        int iGlCreateShader = GLES20.glCreateShader(i2);
        GLES20.glShaderSource(iGlCreateShader, str);
        GLES20.glCompileShader(iGlCreateShader);
        int[] iArr = {0};
        GLES20.glGetShaderiv(iGlCreateShader, 35713, iArr, 0);
        ev2.j(GLES20.glGetShaderInfoLog(iGlCreateShader) + ", source: \n" + str, iArr[0] == 1);
        GLES20.glAttachShader(i, iGlCreateShader);
        GLES20.glDeleteShader(iGlCreateShader);
        ev2.i();
    }

    /* JADX WARN: Code duplicated, block: B:41:0x0141  */
    @Override // defpackage.h82
    public void b(wm1 wm1Var) {
        ln2 ln2Var;
        ln2 ln2Var2;
        SparseArray sparseArray;
        int i;
        qo qoVar;
        char c;
        SparseArray sparseArray2 = (SparseArray) this.n;
        SparseIntArray sparseIntArray = (SparseIntArray) this.o;
        qo qoVar2 = (qo) this.m;
        dr2 dr2Var = (dr2) this.p;
        SparseArray sparseArray3 = dr2Var.i;
        SparseBooleanArray sparseBooleanArray = dr2Var.j;
        k00 k00Var = dr2Var.g;
        List list = dr2Var.d;
        byte b = dr2Var.a;
        if (wm1Var.A() != 2) {
            return;
        }
        if (b == 1 || b == 2 || dr2Var.o == 1) {
            ln2Var = (ln2) list.get(0);
        } else {
            ln2Var = new ln2(((ln2) list.get(0)).d());
            list.add(ln2Var);
        }
        if ((wm1Var.A() & 128) == 0) {
            return;
        }
        wm1Var.O(1);
        int iH = wm1Var.H();
        wm1Var.O(3);
        wm1Var.k(0, qoVar2.b, 2);
        qoVar2.m(0);
        qoVar2.o(3);
        dr2Var.u = qoVar2.g(13);
        wm1Var.k(0, qoVar2.b, 2);
        qoVar2.m(0);
        qoVar2.o(4);
        wm1Var.O(qoVar2.g(12));
        if (b == 2 && dr2Var.s == null) {
            gr2 gr2VarI = k00Var.i(21, new un1(21, null, 0, null, ys2.b));
            dr2Var.s = gr2VarI;
            if (gr2VarI != null) {
                gr2VarI.c(ln2Var, dr2Var.n, new fr2(iH, 21, 8192));
            }
        }
        sparseArray2.clear();
        sparseIntArray.clear();
        int iA = wm1Var.a();
        while (iA > 0) {
            wm1Var.k(0, qoVar2.b, 5);
            qoVar2.m(0);
            int iG = qoVar2.g(8);
            qoVar2.o(3);
            int iG2 = qoVar2.g(13);
            qoVar2.o(4);
            int iG3 = qoVar2.g(12);
            int i2 = wm1Var.b;
            int i3 = i2 + iG3;
            int i4 = -1;
            String strTrim = null;
            ArrayList arrayList = null;
            int iA2 = 0;
            int i5 = iA;
            while (true) {
                if (wm1Var.b >= i3) {
                    qoVar = qoVar2;
                    break;
                }
                int iA3 = wm1Var.A();
                qoVar = qoVar2;
                int iA4 = wm1Var.b + wm1Var.A();
                if (iA4 > i3) {
                    break;
                }
                SparseArray sparseArray4 = sparseArray3;
                if (iA3 == 5) {
                    long jC = wm1Var.C();
                    if (jC == 1094921523) {
                        i4 = 129;
                    } else if (jC == 1161904947) {
                        i4 = 135;
                    } else if (jC == 1094921524) {
                        i4 = 172;
                    } else if (jC == 1212503619) {
                        i4 = 36;
                    }
                } else if (iA3 == 106) {
                    iA4 = iA4;
                    i4 = 129;
                } else if (iA3 == 122) {
                    i4 = 135;
                    iA4 = iA4;
                } else if (iA3 == 127) {
                    int iA5 = wm1Var.A();
                    if (iA5 == 21) {
                        i4 = 172;
                    } else if (iA5 == 14) {
                        i4 = 136;
                    } else if (iA5 == 33) {
                        i4 = 139;
                    }
                } else if (iA3 == 123) {
                    i4 = 138;
                } else if (iA3 == 10) {
                    strTrim = wm1Var.y(3, StandardCharsets.UTF_8).trim();
                    iA2 = wm1Var.A();
                } else if (iA3 == 89) {
                    ArrayList arrayList2 = new ArrayList();
                    while (wm1Var.b < iA4) {
                        String strTrim2 = wm1Var.y(3, StandardCharsets.UTF_8).trim();
                        wm1Var.A();
                        ln2 ln2Var3 = ln2Var;
                        byte[] bArr = new byte[4];
                        wm1Var.k(0, bArr, 4);
                        arrayList2.add(new er2(strTrim2, bArr));
                        ln2Var = ln2Var3;
                        iA4 = iA4;
                        iH = iH;
                    }
                    iA4 = iA4;
                    iH = iH;
                    ln2Var = ln2Var;
                    arrayList = arrayList2;
                    i4 = 89;
                } else {
                    iA4 = iA4;
                    iH = iH;
                    ln2Var = ln2Var;
                    if (iA3 == 111) {
                        i4 = 257;
                    }
                }
                wm1Var.O(iA4 - wm1Var.b);
                ln2Var = ln2Var;
                qoVar2 = qoVar;
                sparseArray3 = sparseArray4;
                iH = iH;
            }
            SparseArray sparseArray5 = sparseArray3;
            int i6 = iH;
            ln2 ln2Var4 = ln2Var;
            wm1Var.N(i3);
            un1 un1Var = new un1(i4, strTrim, iA2, arrayList, Arrays.copyOfRange(wm1Var.a, i2, i3));
            if (iG == 6 || iG == 5) {
                iG = i4;
            }
            int i7 = i5 - (iG3 + 5);
            int i8 = b == 2 ? iG : iG2;
            if (sparseBooleanArray.get(i8)) {
                c = 21;
            } else {
                c = 21;
                gr2 gr2VarI2 = (b == 2 && iG == 21) ? dr2Var.s : k00Var.i(iG, un1Var);
                if (b != 2 || iG2 < sparseIntArray.get(i8, 8192)) {
                    sparseIntArray.put(i8, iG2);
                    sparseArray2.put(i8, gr2VarI2);
                }
            }
            iA = i7;
            ln2Var = ln2Var4;
            qoVar2 = qoVar;
            sparseArray3 = sparseArray5;
            iH = i6;
        }
        SparseArray sparseArray6 = sparseArray3;
        int i9 = iH;
        ln2 ln2Var5 = ln2Var;
        int size = sparseIntArray.size();
        int i10 = 0;
        while (i10 < size) {
            int iKeyAt = sparseIntArray.keyAt(i10);
            int iValueAt = sparseIntArray.valueAt(i10);
            sparseBooleanArray.put(iKeyAt, true);
            dr2Var.k.put(iValueAt, true);
            gr2 gr2Var = (gr2) sparseArray2.valueAt(i10);
            if (gr2Var != null) {
                if (gr2Var != dr2Var.s) {
                    i = i9;
                    ln2Var2 = ln2Var5;
                    gr2Var.c(ln2Var2, dr2Var.n, new fr2(i, iKeyAt, 8192));
                } else {
                    ln2Var2 = ln2Var5;
                    i = i9;
                }
                sparseArray = sparseArray6;
                sparseArray.put(iValueAt, gr2Var);
            } else {
                ln2Var2 = ln2Var5;
                sparseArray = sparseArray6;
                i = i9;
            }
            i10++;
            sparseArray6 = sparseArray;
            i9 = i;
            ln2Var5 = ln2Var2;
        }
        SparseArray sparseArray7 = sparseArray6;
        if (b == 2) {
            if (dr2Var.p) {
                return;
            }
            dr2Var.n.f();
            dr2Var.o = 0;
            dr2Var.p = true;
            return;
        }
        sparseArray7.remove(this.l);
        int i11 = b == 1 ? 0 : dr2Var.o - 1;
        dr2Var.o = i11;
        if (i11 == 0) {
            dr2Var.n.f();
            dr2Var.p = true;
        }
    }

    public int d(String str) throws rp0 {
        int iGlGetAttribLocation = GLES20.glGetAttribLocation(this.l, str);
        GLES20.glEnableVertexAttribArray(iGlGetAttribLocation);
        ev2.i();
        return iGlGetAttribLocation;
    }

    public boolean e(vp2 vp2Var, int i) {
        return Objects.equals(((jz1[]) this.m)[i], ((jz1[]) vp2Var.m)[i]) && Objects.equals(((yg0[]) this.n)[i], ((yg0[]) vp2Var.n)[i]);
    }

    public boolean f(int i) {
        return ((jz1[]) this.m)[i] != null;
    }

    @Override // defpackage.h82
    public void c(ln2 ln2Var, fh0 fh0Var, fr2 fr2Var) {
    }

    /* JADX WARN: Type inference failed for: r4v2, types: [java.lang.Cloneable, yg0[]] */
    public vp2(jz1[] jz1VarArr, yg0[] yg0VarArr, xp2 xp2Var, Object obj) {
        x91.h(jz1VarArr.length == yg0VarArr.length);
        this.m = jz1VarArr;
        this.n = (yg0[]) yg0VarArr.clone();
        this.o = xp2Var;
        this.p = obj;
        this.l = jz1VarArr.length;
    }

    public vp2(dr2 dr2Var, int i) {
        this.p = dr2Var;
        this.m = new qo(new byte[5], 5);
        this.n = new SparseArray();
        this.o = new SparseIntArray();
        this.l = i;
    }
}
