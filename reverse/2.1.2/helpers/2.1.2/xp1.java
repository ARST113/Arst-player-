package defpackage;

import android.media.audiofx.LoudnessEnhancer;
import com.brouken.player.PlayerActivity;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

/* JADX INFO: compiled from: r8-map-id-d5d7d661e67c62f2588b5a5666a7a20281e417b1121a202706e8fa77676a2bdd */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class xp1 implements cy0 {
    @Override // defpackage.cy0
    public final r12 a(gx1 gx1Var) {
        LoudnessEnhancer loudnessEnhancer = PlayerActivity.i6;
        gv1 gv1Var = gx1Var.e;
        pu0 pu0Var = (pu0) gv1Var.b;
        if (((gr0) gv1Var.d).a("Cookie") == null) {
            StringBuilder sb = new StringBuilder();
            ArrayList arrayList = PlayerActivity.y6;
            synchronized (arrayList) {
                try {
                    long jCurrentTimeMillis = System.currentTimeMillis();
                    Iterator it = arrayList.iterator();
                    while (it.hasNext()) {
                        ux uxVar = (ux) it.next();
                        if (uxVar.c < jCurrentTimeMillis) {
                            it.remove();
                        } else if (uxVar.a(pu0Var)) {
                            if (sb.length() > 0) {
                                sb.append("; ");
                            }
                            sb.append(uxVar.a);
                            sb.append('=');
                            sb.append(uxVar.b);
                        }
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            if (sb.length() > 0) {
                vk1 vk1VarN = gv1Var.n();
                vk1VarN.a("Cookie", sb.toString());
                gv1Var = new gv1(vk1VarN);
            }
        }
        r12 r12VarB = gx1Var.b(gv1Var);
        gr0 gr0Var = r12VarB.q;
        Pattern pattern = ux.k;
        List<ux> listF = y61.F(pu0Var, gr0Var);
        if (listF.isEmpty()) {
            return r12VarB;
        }
        synchronized (PlayerActivity.y6) {
            try {
                for (ux uxVar2 : listF) {
                    Iterator it2 = PlayerActivity.y6.iterator();
                    while (it2.hasNext()) {
                        ux uxVar3 = (ux) it2.next();
                        if (uxVar3.a.equals(uxVar2.a) && uxVar3.d.equals(uxVar2.d) && uxVar3.e.equals(uxVar2.e)) {
                            it2.remove();
                        }
                    }
                    PlayerActivity.y6.add(uxVar2);
                }
            } catch (Throwable th2) {
                throw th2;
            }
        }
        return r12VarB;
    }
}
