package defpackage;

import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.graphics.drawable.ShapeDrawable;
import com.brouken.player.PlayerActivity;

/* JADX INFO: compiled from: r8-map-id-0cee80150bab987b3edc91bcfc1e04786a1418881e5c92a3b999d44d61001263 */
/* JADX INFO: loaded from: classes.dex */
public final class mq1 extends ShapeDrawable.ShaderFactory {
    public final /* synthetic */ PlayerActivity a;

    public mq1(PlayerActivity playerActivity) {
        this.a = playerActivity;
    }

    @Override // android.graphics.drawable.ShapeDrawable.ShaderFactory
    public final Shader resize(int i, int i2) {
        int i3 = this.a.K0.o & 16777215;
        return new LinearGradient(0.0f, 0.0f, 0.0f, i2, new int[]{(-1291845632) | i3, 1493172224 | i3, i3}, new float[]{0.0f, 0.5f, 1.0f}, Shader.TileMode.CLAMP);
    }
}
