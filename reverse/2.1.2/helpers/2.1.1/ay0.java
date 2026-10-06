package defpackage;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: compiled from: r8-map-id-ba8d2c2760819bd03a19aa4133a73a2576203ef152dace5b65b91a4c4fd30466 */
/* JADX INFO: loaded from: classes.dex */
public final class ay0 {
    public static final ay0 l;
    public static final ay0 m;
    public static final /* synthetic */ ay0[] n;

    static {
        ay0 ay0Var = new ay0("Synchronously", 0);
        l = ay0Var;
        ay0 ay0Var2 = new ay0("Asynchronously", 1);
        m = ay0Var2;
        n = new ay0[]{ay0Var, ay0Var2};
    }

    public static ay0 valueOf(String str) {
        return (ay0) Enum.valueOf(ay0.class, str);
    }

    public static ay0[] values() {
        return (ay0[]) n.clone();
    }
}
