package com.brouken.player;

import defpackage.on2;

/* JADX INFO: compiled from: r8-map-id-d5d7d661e67c62f2588b5a5666a7a20281e417b1121a202706e8fa77676a2bdd */
/* JADX INFO: loaded from: classes.dex */
public final class b implements on2 {
    public final /* synthetic */ CustomDefaultTimeBar l;

    public b(CustomDefaultTimeBar customDefaultTimeBar) {
        this.l = customDefaultTimeBar;
    }

    @Override // defpackage.on2
    public final void n(long j) {
        this.l.h0 = true;
    }

    @Override // defpackage.on2
    public final void o(long j, boolean z) {
        this.l.h0 = false;
    }

    @Override // defpackage.on2
    public final void p(long j) {
    }
}
