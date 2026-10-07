package defpackage;

import android.content.ContentProviderClient;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.drm.DrmManagerClient;
import android.media.MediaDrm;
import android.media.MediaMetadataRetriever;
import android.view.ContextThemeWrapper;
import android.widget.LinearLayout;
import androidx.media3.decoder.VideoDecoderOutputBuffer;
import io.sentry.s;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;

/* JADX INFO: compiled from: r8-map-id-0cee80150bab987b3edc91bcfc1e04786a1418881e5c92a3b999d44d61001263 */
/* JADX INFO: loaded from: classes.dex */
public abstract /* synthetic */ class lf2 {
    public static final /* synthetic */ int[] a = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64};

    public static long a(z20 z20Var) {
        byte[] bArr = (byte[]) z20Var.b.get("exo_len");
        if (bArr != null) {
            return ByteBuffer.wrap(bArr).getLong();
        }
        return -1L;
    }

    public static int b(String str, int i, int i2) {
        return (str.hashCode() + i) * i2;
    }

    public static LinearLayout c(ContextThemeWrapper contextThemeWrapper, int i) {
        LinearLayout linearLayout = new LinearLayout(contextThemeWrapper);
        linearLayout.setOrientation(i);
        return linearLayout;
    }

    public static Object d(int i, ArrayList arrayList) {
        return arrayList.get(arrayList.size() - i);
    }

    public static String e(String str, String str2) {
        return str + str2;
    }

    public static String f(String str, String str2, String str3) {
        return str + str2 + str3;
    }

    public static String g(StringBuilder sb, int i, String str) {
        sb.append(i);
        sb.append(str);
        return sb.toString();
    }

    public static String h(StringBuilder sb, String str, String str2) {
        sb.append(str);
        sb.append(str2);
        return sb.toString();
    }

    public static StringBuilder i(String str, int i, String str2, int i2, String str3) {
        StringBuilder sb = new StringBuilder(str);
        sb.append(i);
        sb.append(str2);
        sb.append(i2);
        sb.append(str3);
        return sb;
    }

    public static StringBuilder j(String str, long j, String str2) {
        StringBuilder sb = new StringBuilder(str);
        sb.append(j);
        sb.append(str2);
        return sb;
    }

    public static StringBuilder k(String str, String str2, String str3) {
        StringBuilder sb = new StringBuilder(str);
        sb.append(str2);
        sb.append(str3);
        return sb;
    }

    public static void l(int i, HashMap map, String str, int i2, String str2) {
        map.put(str, Integer.valueOf(i));
        map.put(str2, Integer.valueOf(i2));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void m(Cursor cursor) throws Exception {
        if (cursor instanceof AutoCloseable) {
            cursor.close();
            return;
        }
        if (cursor instanceof ExecutorService) {
            f1.e((ExecutorService) cursor);
            return;
        }
        if (cursor instanceof TypedArray) {
            ((TypedArray) cursor).recycle();
            return;
        }
        if (cursor instanceof MediaMetadataRetriever) {
            ((MediaMetadataRetriever) cursor).release();
            return;
        }
        if (cursor instanceof MediaDrm) {
            ((MediaDrm) cursor).release();
            return;
        }
        if (cursor instanceof DrmManagerClient) {
            ((DrmManagerClient) cursor).release();
        } else if (cursor instanceof ContentProviderClient) {
            ((ContentProviderClient) cursor).release();
        } else {
            r12.f();
        }
    }

    public static /* synthetic */ void n(s sVar) throws Exception {
        if (sVar instanceof AutoCloseable) {
            sVar.close();
        } else {
            if (sVar instanceof ExecutorService) {
                throw null;
            }
            r12.f();
        }
    }

    public static /* synthetic */ void o(Object obj) {
        if (obj == null) {
            return;
        }
        q90.f();
    }

    public static void p(String str, int i, String str2) {
        ql.K(str2, str + i);
    }

    public static void q(String str, String str2, String str3) {
        ql.K(str3, str + str2);
    }

    public static /* synthetic */ boolean r(Object obj) {
        return obj != null;
    }

    public static /* synthetic */ String s(int i) {
        if (i == 1) {
            return "FAVORITES";
        }
        if (i == 2) {
            return "FILES";
        }
        if (i == 3) {
            return "NETWORK";
        }
        if (i == 4) {
            return "IPTV";
        }
        throw null;
    }

    public static /* synthetic */ String t(int i) {
        switch (i) {
            case 1:
                return "NONE";
            case 2:
                return "LEFT";
            case VideoDecoderOutputBuffer.COLORSPACE_BT2020 /* 3 */:
                return "TOP";
            case 4:
                return "RIGHT";
            case 5:
                return "BOTTOM";
            case 6:
                return "BASELINE";
            case 7:
                return "CENTER";
            case 8:
                return "CENTER_X";
            case 9:
                return "CENTER_Y";
            default:
                throw null;
        }
    }

    public static /* synthetic */ int u(int i) {
        if (i != 0) {
            return i - 1;
        }
        throw null;
    }

    public static void v(ia0 ia0Var, ia0 ia0Var2) {
        if (ia0Var == ia0Var2) {
            return;
        }
        if (ia0Var2 != null) {
            ia0Var2.c(null);
        }
        if (ia0Var != null) {
            ia0Var.b(null);
        }
    }

    public static /* synthetic */ String w(int i) {
        if (i == 1) {
            return "FAVORITES";
        }
        if (i == 2) {
            return "FILES";
        }
        if (i != 3) {
            return i != 4 ? "null" : "IPTV";
        }
        return "NETWORK";
    }

    public static /* synthetic */ String x(int i) {
        switch (i) {
            case 1:
                return "INITIALIZE";
            case 2:
                return "RESOURCE_CACHE";
            case VideoDecoderOutputBuffer.COLORSPACE_BT2020 /* 3 */:
                return "DATA_CACHE";
            case 4:
                return "SOURCE";
            case 5:
                return "ENCODE";
            case 6:
                return "FINISHED";
            default:
                return "null";
        }
    }

    public static /* synthetic */ int[] y(int i) {
        int[] iArr = new int[i];
        System.arraycopy(a, 0, iArr, 0, i);
        return iArr;
    }
}
