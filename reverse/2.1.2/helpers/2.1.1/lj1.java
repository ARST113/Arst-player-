package defpackage;

import java.nio.charset.Charset;
import java.util.Set;

/* JADX INFO: compiled from: r8-map-id-ba8d2c2760819bd03a19aa4133a73a2576203ef152dace5b65b91a4c4fd30466 */
/* JADX INFO: loaded from: classes.dex */
public final class lj1 extends rj1 {
    public final byte[] j;
    public final byte[] k;
    public final byte[] l;
    public final byte[] m;
    public final byte[] n;
    public final byte[] o;
    public byte[] p;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public lj1(byte[] bArr, byte[] bArr2, String str, String str2, byte[] bArr3, Set set, xz2 xz2Var) {
        super(set, xz2Var);
        byte[] bArr4 = xx0.r;
        this.j = bArr == null ? bArr4 : bArr;
        this.k = bArr2 == null ? bArr4 : bArr2;
        this.l = str != null ? str.getBytes(qj1.a) : bArr4;
        this.m = str2 != null ? str2.getBytes(qj1.a) : bArr4;
        this.n = bArr4;
        this.o = bArr3 == null ? bArr4 : bArr3;
        this.g = set;
    }

    public final void m0(xk xkVar) {
        xkVar.i("NTLMSSP\u0000", dr.a);
        xkVar.k(3L);
        Set set = this.g;
        tj1 tj1Var = tj1.NTLMSSP_NEGOTIATE_VERSION;
        int i = (set.contains(tj1Var) || this.p != null) ? 72 : 64;
        if (this.p != null) {
            i += 16;
        }
        byte[] bArr = this.j;
        int iT = xx0.T(xkVar, bArr, i);
        byte[] bArr2 = this.k;
        int iT2 = xx0.T(xkVar, bArr2, iT);
        byte[] bArr3 = this.m;
        int iT3 = xx0.T(xkVar, bArr3, iT2);
        byte[] bArr4 = this.l;
        int iT4 = xx0.T(xkVar, bArr4, iT3);
        byte[] bArr5 = this.n;
        int iT5 = xx0.T(xkVar, bArr5, iT4);
        byte[] bArr6 = this.o;
        xx0.T(xkVar, bArr6, iT5);
        xkVar.k(x91.b0(this.g));
        if (this.g.contains(tj1Var)) {
            xk xkVar2 = new xk();
            xkVar2.f((byte) 6);
            xkVar2.f((byte) 1);
            xkVar2.j(7600);
            xkVar2.h(new byte[]{0, 0, 0}, 3);
            xkVar2.f((byte) 15);
            byte[] bArrC = xkVar2.c();
            xkVar.h(bArrC, bArrC.length);
        } else if (this.p != null) {
            xkVar.l(0L);
        }
        byte[] bArr7 = this.p;
        if (bArr7 != null) {
            xkVar.h(bArr7, 16);
        }
        xkVar.h(bArr, bArr.length);
        xkVar.h(bArr2, bArr2.length);
        xkVar.h(bArr3, bArr3.length);
        xkVar.h(bArr4, bArr4.length);
        xkVar.h(bArr5, bArr5.length);
        xkVar.h(bArr6, bArr6.length);
    }

    public final String toString() {
        String str;
        StringBuilder sb = new StringBuilder("NtlmAuthenticate{\n  mic=");
        byte[] bArr = this.p;
        sb.append(bArr != null ? sp0.w(bArr) : "[]");
        sb.append(",\n  lmResponse=");
        sb.append(sp0.w(this.j));
        sb.append(",\n  ntResponse=");
        sb.append(sp0.w(this.k));
        sb.append(",\n  domainName='");
        byte[] bArr2 = this.m;
        if (bArr2 != null) {
            str = new String(bArr2, qj1.a);
        } else {
            Charset charset = qj1.a;
            str = "";
        }
        sb.append(str);
        sb.append("',\n  userName='");
        byte[] bArr3 = this.l;
        sb.append(bArr3 != null ? new String(bArr3, qj1.a) : "");
        sb.append("',\n  workstation='");
        byte[] bArr4 = this.n;
        return we2.h(sb, bArr4 != null ? new String(bArr4, qj1.a) : "", "',\n  encryptedRandomSessionKey=[<secret>],\n}");
    }
}
