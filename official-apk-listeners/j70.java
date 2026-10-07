package defpackage;

import android.content.DialogInterface;
import android.media.audiofx.LoudnessEnhancer;
import androidx.media3.decoder.DecoderInputBuffer;
import com.brouken.player.PlayerActivity;

/* JADX INFO: compiled from: r8-map-id-0cee80150bab987b3edc91bcfc1e04786a1418881e5c92a3b999d44d61001263 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class j70 implements DialogInterface.OnDismissListener {
    public final /* synthetic */ byte l;
    public final /* synthetic */ Object m;

    public /* synthetic */ j70(Object obj, byte b) {
        this.l = b;
        this.m = obj;
    }

    @Override // android.content.DialogInterface.OnDismissListener
    public final void onDismiss(DialogInterface dialogInterface) {
        byte b = this.l;
        Object obj = this.m;
        switch (b) {
            case DecoderInputBuffer.BUFFER_REPLACEMENT_MODE_DISABLED /* 0 */:
                ((c90) obj).run();
                break;
            default:
                PlayerActivity playerActivity = (PlayerActivity) obj;
                LoudnessEnhancer loudnessEnhancer = PlayerActivity.l6;
                playerActivity.X1 = false;
                yt2.k0(playerActivity, playerActivity.B, PlayerActivity.L6);
                break;
        }
    }
}
