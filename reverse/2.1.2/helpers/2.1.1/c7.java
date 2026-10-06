package defpackage;

import j$.util.Objects;
import java.util.Arrays;

/* JADX INFO: compiled from: r8-map-id-ba8d2c2760819bd03a19aa4133a73a2576203ef152dace5b65b91a4c4fd30466 */
/* JADX INFO: loaded from: classes.dex */
public final class c7 extends iv0 {
    public final String b;
    public final String c;
    public final int d;
    public final byte[] e;

    public c7(String str, String str2, int i, byte[] bArr) {
        super("APIC");
        this.b = str;
        this.c = str2;
        this.d = i;
        this.e = bArr;
    }

    @Override // defpackage.iv0, defpackage.ne1
    public final void b(w81 w81Var) {
        w81Var.a(this.e, this.d);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || c7.class != obj.getClass()) {
            return false;
        }
        c7 c7Var = (c7) obj;
        return this.d == c7Var.d && this.b.equals(c7Var.b) && Objects.equals(this.c, c7Var.c) && Arrays.equals(this.e, c7Var.e);
    }

    public final int hashCode() {
        int iB = we2.b(this.b, (527 + this.d) * 31, 31);
        String str = this.c;
        return Arrays.hashCode(this.e) + ((iB + (str != null ? str.hashCode() : 0)) * 31);
    }

    @Override // defpackage.iv0
    public final String toString() {
        return this.a + ": mimeType=" + this.b + ", description=" + this.c;
    }
}
