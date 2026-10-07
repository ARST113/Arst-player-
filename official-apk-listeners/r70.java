package defpackage;

import android.graphics.Outline;
import android.view.View;
import android.view.ViewOutlineProvider;
import androidx.media3.decoder.DecoderInputBuffer;

/* JADX INFO: compiled from: r8-map-id-0cee80150bab987b3edc91bcfc1e04786a1418881e5c92a3b999d44d61001263 */
/* JADX INFO: loaded from: classes.dex */
public final class r70 extends ViewOutlineProvider {
    public final /* synthetic */ byte a;
    public final /* synthetic */ int b;

    public /* synthetic */ r70(int i, byte b) {
        this.a = b;
        this.b = i;
    }

    @Override // android.view.ViewOutlineProvider
    public final void getOutline(View view, Outline outline) {
        byte b = this.a;
        int i = this.b;
        switch (b) {
            case DecoderInputBuffer.BUFFER_REPLACEMENT_MODE_DISABLED /* 0 */:
                outline.setRoundRect(0, 0, view.getWidth(), view.getHeight(), i);
                break;
            case 1:
                outline.setRoundRect(0, 0, view.getWidth(), view.getHeight(), i);
                break;
            case 2:
                outline.setRoundRect(0, 0, view.getWidth(), view.getHeight(), i);
                break;
            default:
                outline.setRoundRect(0, 0, view.getWidth(), view.getHeight(), i);
                break;
        }
    }
}
