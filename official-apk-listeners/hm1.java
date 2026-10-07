package defpackage;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.Layout;
import android.text.TextPaint;
import android.widget.TextClock;
import com.brouken.player.PlayerActivity;

/* JADX INFO: compiled from: r8-map-id-0cee80150bab987b3edc91bcfc1e04786a1418881e5c92a3b999d44d61001263 */
/* JADX INFO: loaded from: classes.dex */
public final class hm1 extends TextClock {
    public final int l;
    public int m;

    public hm1(PlayerActivity playerActivity) {
        super(playerActivity, null);
        this.m = -16777216;
        this.l = yt2.p(1);
    }

    @Override // android.widget.TextView, android.view.View
    public final void onDraw(Canvas canvas) {
        Layout layout = getLayout();
        if (layout == null) {
            super.onDraw(canvas);
            return;
        }
        TextPaint paint = getPaint();
        Paint.Style style = paint.getStyle();
        float strokeWidth = paint.getStrokeWidth();
        int color = paint.getColor();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(this.l);
        paint.setColor(this.m);
        canvas.save();
        canvas.translate(getTotalPaddingLeft(), getTotalPaddingTop());
        layout.draw(canvas);
        canvas.restore();
        paint.setStyle(style);
        paint.setStrokeWidth(strokeWidth);
        paint.setColor(color);
        super.onDraw(canvas);
    }

    public void setOutlineColor(int i) {
        this.m = i;
        invalidate();
    }
}
