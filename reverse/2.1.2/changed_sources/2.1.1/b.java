package com.brouken.player;

import defpackage.ym2;

/* JADX INFO: compiled from: r8-map-id-ba8d2c2760819bd03a19aa4133a73a2576203ef152dace5b65b91a4c4fd30466 */
/* JADX INFO: loaded from: classes.dex */
public final class b implements ym2 {
    public final /* synthetic */ CustomDefaultTimeBar l;

    public b(CustomDefaultTimeBar customDefaultTimeBar) {
        this.l = customDefaultTimeBar;
    }

    @Override // defpackage.ym2
    public final void n(long j) {
        this.l.h0 = true;
    }

    @Override // defpackage.ym2
    public final void o(long j, boolean z) {
        this.l.h0 = false;
    }

    @Override // defpackage.ym2
    public final void p(long j) {
    }
}
