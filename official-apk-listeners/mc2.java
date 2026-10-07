package defpackage;

import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;

/* JADX INFO: compiled from: r8-map-id-0cee80150bab987b3edc91bcfc1e04786a1418881e5c92a3b999d44d61001263 */
/* JADX INFO: loaded from: classes.dex */
public final class mc2 extends qc2 {
    public final oc2 c;
    public final float d;
    public final float e;

    public mc2(oc2 oc2Var, float f, float f2) {
        this.c = oc2Var;
        this.d = f;
        this.e = f2;
    }

    @Override // defpackage.qc2
    public final void a(Matrix matrix, ec2 ec2Var, int i, Canvas canvas) {
        oc2 oc2Var = this.c;
        float f = oc2Var.c;
        float f2 = this.e;
        float f3 = oc2Var.b;
        float f4 = this.d;
        RectF rectF = new RectF(0.0f, 0.0f, (float) Math.hypot(f - f2, f3 - f4), 0.0f);
        Matrix matrix2 = this.a;
        matrix2.set(matrix);
        matrix2.preTranslate(f4, f2);
        matrix2.preRotate(b());
        ec2Var.getClass();
        rectF.bottom += i;
        rectF.offset(0.0f, -i);
        int i2 = ec2Var.c;
        int[] iArr = ec2.i;
        iArr[0] = i2;
        iArr[1] = ec2Var.b;
        iArr[2] = ec2Var.a;
        Paint paint = (Paint) ec2Var.f;
        float f5 = rectF.left;
        paint.setShader(new LinearGradient(f5, rectF.top, f5, rectF.bottom, iArr, ec2.j, Shader.TileMode.CLAMP));
        canvas.save();
        canvas.concat(matrix2);
        canvas.drawRect(rectF, paint);
        canvas.restore();
    }

    public final float b() {
        oc2 oc2Var = this.c;
        return (float) Math.toDegrees(Math.atan((oc2Var.c - this.e) / (oc2Var.b - this.d)));
    }
}
