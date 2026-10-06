package com.brouken.player;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import com.justplus.player.R;

/* JADX INFO: compiled from: r8-map-id-ba8d2c2760819bd03a19aa4133a73a2576203ef152dace5b65b91a4c4fd30466 */
/* JADX INFO: loaded from: classes.dex */
public class LevelBar extends View {
    public final Paint l;
    public final int m;
    public final int n;
    public final int o;
    public float p;
    public float q;

    public LevelBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.l = new Paint(1);
        this.p = 0.0f;
        this.q = 100.0f;
        this.m = context.getColor(R.color.level_bar_track);
        this.n = context.getColor(R.color.white);
        this.o = context.getColor(R.color.volume_boost);
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        float width = getWidth();
        float height = getHeight();
        float f = width / 2.0f;
        int i = this.m;
        Paint paint = this.l;
        paint.setColor(i);
        canvas.drawRoundRect(0.0f, 0.0f, width, height, f, f, paint);
        float f2 = (1.0f - (this.p / this.q)) * height;
        paint.setColor(this.n);
        canvas.drawRoundRect(0.0f, f2, width, height, f, f, paint);
        if (this.p > 100.0f) {
            canvas.save();
            canvas.clipRect(0.0f, f2, width, (1.0f - (100.0f / this.q)) * height);
            paint.setColor(this.o);
            canvas.drawRoundRect(0.0f, f2, width, height, f, f, paint);
            canvas.restore();
        }
    }
}
