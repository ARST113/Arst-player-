package defpackage;

import android.media.audiofx.LoudnessEnhancer;
import android.os.Build;
import android.os.SystemClock;
import com.brouken.player.PlayerActivity;
import com.justplus.player.R;

/* JADX INFO: compiled from: r8-map-id-0cee80150bab987b3edc91bcfc1e04786a1418881e5c92a3b999d44d61001263 */
/* JADX INFO: loaded from: classes.dex */
public final class si implements c71, ns1 {
    public final PlayerActivity l;

    public /* synthetic */ si(PlayerActivity playerActivity) {
        this.l = playerActivity;
    }

    public static void a(long j) {
        vg0 vg0Var = PlayerActivity.n6;
        if (vg0Var != null) {
            vg0Var.t1(c92.c);
            PlayerActivity.n6.o1(j);
        }
    }

    public static boolean f() {
        vg0 vg0Var = PlayerActivity.n6;
        return (vg0Var == null || !vg0Var.w() || PlayerActivity.n6.C() == 4) ? false : true;
    }

    public static long g() {
        vg0 vg0Var = PlayerActivity.n6;
        if (vg0Var == null) {
            return 0L;
        }
        return Math.max(0L, vg0Var.O0());
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x0073, code lost:
    
        if (android.os.Build.VERSION.SDK_INT >= 26) goto L45;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x007a, code lost:
    
        if (android.os.Build.VERSION.SDK_INT >= 34) goto L45;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static int h(zl0 zl0Var) {
        String str = zl0Var.p;
        if (str == null || !ef1.m(str)) {
            return uh0.g(0, 0, 0, 0);
        }
        String str2 = zl0Var.p;
        String str3 = qt2.a;
        str2.getClass();
        switch (str2) {
            case "image/avif":
                break;
            case "image/heic":
            case "image/heif":
                break;
            case "image/jpeg":
            case "image/webp":
            case "image/bmp":
            case "image/png":
                return uh0.g(4, 0, 0, 0);
        }
        return uh0.g(1, 0, 0, 0);
    }

    public void b(int i) {
        String string;
        LoudnessEnhancer loudnessEnhancer = PlayerActivity.l6;
        PlayerActivity playerActivity = this.l;
        playerActivity.O();
        if (i <= 0) {
            s2.E(playerActivity.B, playerActivity.getString(R.string.sleep_timer_cancelled), R.drawable.ic_sleep_24dp);
            return;
        }
        playerActivity.a6 = i;
        playerActivity.Z5 = (((long) i) * 60000) + SystemClock.uptimeMillis();
        playerActivity.B.postDelayed(playerActivity.d6, 1000L);
        cz czVar = playerActivity.B;
        int i2 = i % 60;
        if (i2 == 0) {
            string = playerActivity.getString(R.string.sleep_timer_hours, String.valueOf(i / 60));
        } else {
            string = i > 60 ? playerActivity.getString(R.string.sleep_timer_hours_minutes, Integer.valueOf(i / 60), Integer.valueOf(i2)) : playerActivity.getString(R.string.sleep_timer_minutes, Integer.valueOf(i));
        }
        s2.E(czVar, playerActivity.getString(R.string.sleep_timer_set, string), R.drawable.ic_sleep_24dp);
    }

    @Override // defpackage.c71
    public d71 c(hv1 hv1Var) {
        PlayerActivity playerActivity;
        int i = Build.VERSION.SDK_INT;
        if (i < 31 && ((playerActivity = this.l) == null || i < 28 || !playerActivity.getPackageManager().hasSystemFeature("com.amazon.hardware.tv_screen"))) {
            return new pj1((byte) 23).c(hv1Var);
        }
        int i2 = ef1.i(((zl0) hv1Var.d).p);
        ql.x("DMCodecAdapterFactory", "Creating an asynchronous MediaCodec adapter for track type ".concat(qt2.O(i2)));
        be2 be2Var = new be2(new mb(i2, (byte) 0), new mb(i2, (byte) 1));
        be2Var.m = true;
        return be2Var.c(hv1Var);
    }

    public void d(int i, String str) {
        int i2;
        LoudnessEnhancer loudnessEnhancer = PlayerActivity.l6;
        PlayerActivity playerActivity = this.l;
        if (playerActivity.B == null || playerActivity.G || PlayerActivity.U6) {
            return;
        }
        int iU = lf2.u(i);
        if (iU == 0) {
            i2 = R.string.together_act_paused;
        } else if (iU != 1) {
            i2 = iU != 3 ? R.string.together_act_seeked : R.string.together_act_left;
        } else {
            i2 = R.string.together_act_resumed;
        }
        if (str == null || str.isEmpty()) {
            str = playerActivity.getString(R.string.together_act_somebody);
        }
        s2.D(playerActivity.B, playerActivity.A2(playerActivity.getString(i2, str)), R.drawable.ic_together_24dp, 1400L);
    }

    public void e() {
        this.l.A4();
    }
}
