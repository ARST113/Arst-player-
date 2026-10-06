package defpackage;

/* JADX INFO: compiled from: r8-map-id-ba8d2c2760819bd03a19aa4133a73a2576203ef152dace5b65b91a4c4fd30466 */
/* JADX INFO: loaded from: classes.dex */
public final class rs1 extends qs1 {
    public final Object n;

    public rs1(int i) {
        super(i);
        this.n = new Object();
    }

    @Override // defpackage.qs1, defpackage.ps1
    public final boolean h(Object obj) {
        boolean zH;
        obj.getClass();
        synchronized (this.n) {
            zH = super.h(obj);
        }
        return zH;
    }

    @Override // defpackage.qs1, defpackage.ps1
    public final Object t() {
        Object objT;
        synchronized (this.n) {
            objT = super.t();
        }
        return objT;
    }
}
