package defpackage;

import android.media.audiofx.LoudnessEnhancer;
import androidx.media3.decoder.DecoderInputBuffer;
import com.brouken.player.PlayerActivity;
import java.util.ArrayList;

/* JADX INFO: compiled from: r8-map-id-ba8d2c2760819bd03a19aa4133a73a2576203ef152dace5b65b91a4c4fd30466 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class xp1 implements Runnable {
    public final /* synthetic */ byte l;
    public final /* synthetic */ PlayerActivity m;
    public final /* synthetic */ rn2 n;
    public final /* synthetic */ ArrayList o;
    public final /* synthetic */ ArrayList p;
    public final /* synthetic */ to1 q;

    public /* synthetic */ xp1(PlayerActivity playerActivity, rn2 rn2Var, ArrayList arrayList, ArrayList arrayList2, to1 to1Var, byte b) {
        this.l = b;
        this.m = playerActivity;
        this.n = rn2Var;
        this.o = arrayList;
        this.p = arrayList2;
        this.q = to1Var;
    }

    @Override // java.lang.Runnable
    public final void run() {
        byte b = this.l;
        to1 to1Var = this.q;
        ArrayList arrayList = this.p;
        ArrayList arrayList2 = this.o;
        rn2 rn2Var = this.n;
        PlayerActivity playerActivity = this.m;
        switch (b) {
            case DecoderInputBuffer.BUFFER_REPLACEMENT_MODE_DISABLED /* 0 */:
                LoudnessEnhancer loudnessEnhancer = PlayerActivity.e6;
                playerActivity.t3(rn2Var, arrayList2, arrayList, to1Var);
                break;
            default:
                LoudnessEnhancer loudnessEnhancer2 = PlayerActivity.e6;
                playerActivity.t3(rn2Var, arrayList2, arrayList, to1Var);
                break;
        }
    }
}
