package defpackage;

import android.net.nsd.NsdManager;
import android.net.nsd.NsdServiceInfo;
import androidx.media3.decoder.DecoderInputBuffer;
import java.net.InetAddress;

/* JADX INFO: compiled from: r8-map-id-ba8d2c2760819bd03a19aa4133a73a2576203ef152dace5b65b91a4c4fd30466 */
/* JADX INFO: loaded from: classes.dex */
public final class ge2 implements NsdManager.ResolveListener {
    public final /* synthetic */ byte a;
    public final /* synthetic */ Object b;

    public /* synthetic */ ge2(Object obj, byte b) {
        this.a = b;
        this.b = obj;
    }

    @Override // android.net.nsd.NsdManager.ResolveListener
    public final void onResolveFailed(NsdServiceInfo nsdServiceInfo, int i) {
        byte b = this.a;
    }

    @Override // android.net.nsd.NsdManager.ResolveListener
    public final void onServiceResolved(NsdServiceInfo nsdServiceInfo) {
        byte b = this.a;
        Object obj = this.b;
        switch (b) {
            case DecoderInputBuffer.BUFFER_REPLACEMENT_MODE_DISABLED /* 0 */:
                InetAddress host = nsdServiceInfo.getHost();
                if (host != null) {
                    ((ie2) obj).c(host.getHostAddress(), nsdServiceInfo.getServiceName());
                }
                break;
            default:
                InetAddress host2 = nsdServiceInfo.getHost();
                if (host2 != null) {
                    ((ie2) obj).b(host2.getHostAddress(), nsdServiceInfo.getPort() > 0 ? nsdServiceInfo.getPort() : 8090, nsdServiceInfo.getServiceName());
                    break;
                }
                break;
        }
    }

    private final void a(NsdServiceInfo nsdServiceInfo, int i) {
    }

    private final void b(NsdServiceInfo nsdServiceInfo, int i) {
    }
}
