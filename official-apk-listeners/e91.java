package defpackage;

import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.text.Layout;
import android.text.TextPaint;
import android.widget.TextView;

/* JADX INFO: compiled from: r8-map-id-0cee80150bab987b3edc91bcfc1e04786a1418881e5c92a3b999d44d61001263 */
/* JADX INFO: loaded from: classes.dex */
public final class e91 extends TextView {
    public float l;

    @Override // android.widget.TextView, android.view.View
    public final void onDraw(Canvas canvas) {
        Layout layout = getLayout();
        if (layout == null || this.l <= 0.0f) {
            return;
        }
        float textSize = getTextSize() * 0.6f;
        float lineRight = 0.0f;
        for (int i = 0; i < layout.getLineCount(); i++) {
            lineRight += (layout.getLineRight(i) - layout.getLineLeft(i)) + textSize;
        }
        float f = this.l * lineRight;
        int totalPaddingTop = getTotalPaddingTop();
        TextPaint paint = getPaint();
        int i2 = 0;
        while (true) {
            LinearGradient linearGradient = null;
            if (i2 >= layout.getLineCount() || f <= 0.0f) {
                break;
            }
            float lineLeft = layout.getLineLeft(i2);
            float lineRight2 = (layout.getLineRight(i2) - lineLeft) + textSize;
            float f2 = lineLeft + f;
            if (f < lineRight2) {
                linearGradient = new LinearGradient(f2 - textSize, 0.0f, f2, 0.0f, -1, 0, Shader.TileMode.CLAMP);
            }
            paint.setShader(linearGradient);
            canvas.save();
            canvas.clipRect(0, layout.getLineTop(i2) + totalPaddingTop, getWidth(), layout.getLineBottom(i2) + totalPaddingTop);
            super.onDraw(canvas);
            canvas.restore();
            f -= lineRight2;
            i2++;
        }
        paint.setShader(null);
    }
}
