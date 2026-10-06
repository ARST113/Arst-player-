package com.brouken.player;

import android.animation.ObjectAnimator;
import android.app.Dialog;
import android.content.ContentUris;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.net.nsd.NsdManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.storage.StorageManager;
import android.os.storage.StorageVolume;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.text.format.DateFormat;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.widget.SearchView;
import androidx.media3.decoder.DecoderInputBuffer;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;
import com.justplus.player.R;
import defpackage.ag;
import defpackage.al;
import defpackage.bc2;
import defpackage.bw2;
import defpackage.c11;
import defpackage.c90;
import defpackage.ck0;
import defpackage.d90;
import defpackage.dc;
import defpackage.dk;
import defpackage.dt2;
import defpackage.dz0;
import defpackage.e90;
import defpackage.ec2;
import defpackage.ek;
import defpackage.f03;
import defpackage.f90;
import defpackage.fj1;
import defpackage.fk;
import defpackage.g03;
import defpackage.g7;
import defpackage.ga1;
import defpackage.gj0;
import defpackage.gj1;
import defpackage.gk;
import defpackage.gu1;
import defpackage.h03;
import defpackage.hj1;
import defpackage.i4;
import defpackage.ij0;
import defpackage.ij1;
import defpackage.ik;
import defpackage.ip2;
import defpackage.j03;
import defpackage.j70;
import defpackage.j90;
import defpackage.jd1;
import defpackage.jf2;
import defpackage.jj1;
import defpackage.jk;
import defpackage.jp2;
import defpackage.jw2;
import defpackage.kz1;
import defpackage.lb1;
import defpackage.ld;
import defpackage.lp2;
import defpackage.nf1;
import defpackage.oi1;
import defpackage.ok;
import defpackage.pk;
import defpackage.q7;
import defpackage.qd1;
import defpackage.qi0;
import defpackage.qs2;
import defpackage.s2;
import defpackage.se2;
import defpackage.sj;
import defpackage.t70;
import defpackage.tk;
import defpackage.u70;
import defpackage.ue2;
import defpackage.uk;
import defpackage.ve2;
import defpackage.vk;
import defpackage.vw1;
import defpackage.wk;
import defpackage.wl2;
import defpackage.wt2;
import defpackage.z80;
import defpackage.zk;
import java.io.File;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: compiled from: r8-map-id-d5d7d661e67c62f2588b5a5666a7a20281e417b1121a202706e8fa77676a2bdd */
/* JADX INFO: loaded from: classes.dex */
public class BrowserActivity extends g7 implements t70 {
    public static final /* synthetic */ int F0 = 0;
    public boolean C0;
    public boolean I;
    public boolean J;
    public boolean K;
    public oi1 M;
    public View N;
    public View O;
    public LinearLayout P;
    public ObjectAnimator Q;
    public RecyclerView U;
    public View V;
    public LinearLayout W;
    public View X;
    public LinearLayout Y;
    public TextView Z;
    public TextView a0;
    public MaterialButton b0;
    public Thread c0;
    public boolean d0;
    public boolean e0;
    public String f0;
    public String g0;
    public String h0;
    public volatile String i0;
    public volatile ArrayList j0;
    public String k0;
    public String l0;
    public String m0;
    public volatile HashMap y0;
    public boolean z0;
    public int E0 = 2;
    public long L = -3001;
    public final ek R = new ek(this, 9);
    public final ArrayList S = new ArrayList();
    public final ArrayList T = new ArrayList();
    public final ExecutorService n0 = Executors.newFixedThreadPool(2);
    public final HashMap o0 = new HashMap();
    public final HashMap p0 = new HashMap();
    public final HashMap q0 = new HashMap();
    public final SimpleDateFormat r0 = new SimpleDateFormat(DateFormat.getBestDateTimePattern(Locale.getDefault(), "ddMMyy"), Locale.getDefault());
    public String s0 = "rows";
    public final HashMap t0 = new HashMap();
    public final HashMap u0 = new HashMap();
    public final HashMap v0 = new HashMap();
    public final HashMap w0 = new HashMap();
    public Map x0 = Collections.EMPTY_MAP;
    public final HashMap A0 = new HashMap();
    public final HashMap B0 = new HashMap();
    public final ArrayList D0 = new ArrayList();

    public static Boolean G(j90 j90Var, int[] iArr, boolean z, boolean z2) {
        if (Thread.currentThread().isInterrupted()) {
            return null;
        }
        int i = iArr[0] - 1;
        iArr[0] = i;
        if (i < 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (j90 j90Var2 : ij0.A(j90Var)) {
            String strC = j90Var2.c();
            if (strC != null && (z || !strC.startsWith("."))) {
                if (!j90Var2.f()) {
                    if (wt2.z(strC)) {
                        return Boolean.TRUE;
                    }
                } else if (z2 || !new File(L(j90Var2), ".nomedia").exists()) {
                    arrayList.add(j90Var2);
                }
            }
        }
        Iterator it = arrayList.iterator();
        boolean z3 = false;
        while (it.hasNext()) {
            Boolean boolG = G((j90) it.next(), iArr, z, z2);
            Boolean bool = Boolean.TRUE;
            if (boolG == bool) {
                return bool;
            }
            z3 |= boolG == null;
        }
        if (z3) {
            return null;
        }
        return Boolean.FALSE;
    }

    public static String L(j90 j90Var) {
        String path = j90Var.e().getPath();
        return path == null ? "" : path;
    }

    public final String A() {
        StringBuilder sb = new StringBuilder();
        Set set = gu1.V0;
        sb.append(PreferenceManager.getDefaultSharedPreferences(this).getBoolean("showHiddenFiles", false));
        sb.append("|");
        sb.append(PreferenceManager.getDefaultSharedPreferences(this).getBoolean("ignoreNoMedia", false));
        sb.append("|");
        sb.append(wt2.h(this));
        return sb.toString();
    }

    public final void B(int i) {
        int i2;
        this.E0 = i;
        oi1 oi1Var = this.M;
        if (oi1Var != null) {
            int iU = jf2.u(i);
            if (iU == 0) {
                i2 = R.id.dest_favorites;
            } else if (iU != 2) {
                i2 = iU != 3 ? R.id.dest_files : R.id.dest_iptv;
            } else {
                i2 = R.id.dest_network;
            }
            oi1Var.setSelectedItemId(i2);
        }
        this.S.clear();
        this.f0 = null;
        View view = this.N;
        if (view != null) {
            view.clearAnimation();
        }
        this.N.setVisibility(8);
        this.O.setVisibility(0);
        this.U.setVisibility(0);
        invalidateOptionsMenu();
        S(null);
        N((View) this.U.getParent());
        N(this.O);
    }

    public final ArrayList C(j90 j90Var, boolean z) {
        HashMap map;
        boolean z2;
        boolean z3;
        boolean z4;
        ArrayList arrayListH;
        if (!z && (arrayListH = H(L(j90Var))) != null) {
            return arrayListH;
        }
        HashMap map2 = this.y0;
        Set set = gu1.V0;
        boolean z5 = false;
        boolean z6 = PreferenceManager.getDefaultSharedPreferences(this).getBoolean("showHiddenFiles", false);
        boolean z7 = PreferenceManager.getDefaultSharedPreferences(this).getBoolean("ignoreNoMedia", false);
        boolean zEquals = "file".equals(j90Var.e().getScheme());
        boolean z8 = (map2 != null && map2.containsKey(L(j90Var))) || !(map2 == null || !zEquals || this.I);
        int[] iArr = {200};
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        try {
            j90[] j90VarArrJ = j90Var.j();
            int length = j90VarArrJ.length;
            int i = 0;
            while (i < length) {
                j90 j90Var2 = j90VarArrJ[i];
                String strC = j90Var2.c();
                if (strC != null) {
                    if (z6 || !strC.startsWith(".")) {
                        if (j90Var2.f()) {
                            boolean z9 = map2 != null && map2.containsKey(L(j90Var2));
                            if (!zEquals || z9) {
                                map = map2;
                                z4 = z9;
                            } else {
                                map = map2;
                                z4 = z9;
                                if (!new File(L(j90Var2), ".nomedia").exists() || z7) {
                                }
                            }
                            if (!z8 || z4 || G(j90Var2, iArr, z6, z7) != Boolean.FALSE) {
                                arrayList.add(new wk(strC, j90Var2, false, true));
                            }
                        } else {
                            map = map2;
                            if (this.I) {
                                z3 = ij0.s(strC.toLowerCase());
                            } else {
                                z3 = zEquals ? wt2.z(strC) : ij0.x(j90Var2);
                            }
                            if (z3) {
                                z2 = false;
                                arrayList2.add(new wk(strC, j90Var2, false, false));
                                long jI = j90Var2.i();
                                if (jI > 0) {
                                    this.t0.put(j90Var2.e().toString(), Long.valueOf(jI));
                                }
                            }
                        }
                        z2 = false;
                    } else {
                        map = map2;
                    }
                    z2 = false;
                } else {
                    map = map2;
                    z2 = z5;
                }
                i++;
                z5 = z2;
                map2 = map;
            }
            Collections.sort(arrayList, new dc((byte) 3));
            Collections.sort(arrayList2, new dc((byte) 4));
            arrayList.addAll(arrayList2);
            return arrayList;
        } catch (Exception e) {
            e.printStackTrace();
            return arrayList;
        }
    }

    public final void D(wk wkVar, zk zkVar) {
        j90 j90Var = wkVar.b;
        String string = j90Var.e().toString();
        boolean zE = lp2.e(j90Var.e());
        HashMap map = this.p0;
        HashMap map2 = this.o0;
        if (zE) {
            long j = j90Var instanceof jp2 ? ((jp2) j90Var).j : 0L;
            if (j > 0) {
                map.put(string, Long.valueOf(j));
            }
            map2.put(string, j > 0 ? wt2.t(j) : "");
            Long l = (Long) this.x0.get(string);
            boolean z = l != null && l.longValue() > 0;
            zkVar.q(wkVar, j > 0 ? wt2.t(j) : null, z ? zkVar.s(wkVar, this) : null, z ? getString(R.string.browse_stopped_at, wt2.t(l.longValue())) : null, -1.0f);
            return;
        }
        if (!map2.containsKey(string)) {
            jd1 jd1Var = f90.a;
            long j2 = j90Var instanceof e90 ? ((e90) j90Var).j : 0L;
            if (j2 > 0) {
                map.put(string, Long.valueOf(j2));
                map2.put(string, wt2.t(j2));
            }
        }
        String str = (String) map2.get(string);
        if (str != null) {
            zkVar.q(wkVar, str.isEmpty() ? null : str, null, null, O(string));
        } else {
            this.n0.execute(new ok(this, wkVar, string, zkVar, (byte) 0));
        }
    }

    /* JADX WARN: Code duplicated, block: B:47:0x00bf  */
    public final void E(jj1 jj1Var) {
        String host;
        dz0.J(this, jj1Var);
        int i = jj1Var.d;
        String str = jj1Var.c;
        HashMap map = bc2.a;
        String str2 = jj1Var.a;
        boolean z = false;
        if ("sftp".equals(str2)) {
            getApplicationContext().getSharedPreferences("sftp_host_keys", 0).edit().remove((i <= 0 || i == 22) ? str : "[" + str + "]:" + i).apply();
        }
        ArrayList arrayListA = ag.a(this);
        for (int size = arrayListA.size() - 1; size >= 0; size--) {
            Uri uri = ((qi0) arrayListA.get(size)).b;
            if (uri != null && str2.equalsIgnoreCase(uri.getScheme()) && (host = uri.getHost()) != null && host.equalsIgnoreCase(str)) {
                if ("dlna".equals(str2)) {
                    arrayListA.remove(size);
                    z = true;
                } else if (i <= 0 || uri.getPort() <= 0 || uri.getPort() == i) {
                    String str3 = jj1Var.e;
                    String strReplaceAll = str3 == null ? "" : str3.replaceAll("^/+|/+$", "");
                    String path = uri.getPath();
                    String strReplaceAll2 = path != null ? path.replaceAll("^/+|/+$", "") : "";
                    if (strReplaceAll.isEmpty() || strReplaceAll2.equals(strReplaceAll) || strReplaceAll2.startsWith(strReplaceAll.concat("/"))) {
                        arrayListA.remove(size);
                        z = true;
                    }
                }
            }
        }
        if (z) {
            ag.O(this, arrayListA);
        }
        this.S.clear();
        S(null);
    }

    public final void F() {
        this.P.removeCallbacks(this.R);
        ObjectAnimator objectAnimator = this.Q;
        if (objectAnimator != null) {
            objectAnimator.cancel();
            this.Q = null;
        }
        if (this.P.getVisibility() == 0) {
            this.P.setVisibility(8);
            this.P.removeAllViews();
        }
    }

    /* JADX WARN: Code duplicated, block: B:105:0x01d7 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:126:? A[Catch: Exception -> 0x01b8, SYNTHETIC, TRY_LEAVE, TryCatch #2 {Exception -> 0x01b8, blocks: (B:95:0x01df, B:94:0x01dc, B:81:0x01b4, B:91:0x01d7), top: B:104:0x00b2, inners: #3 }] */
    public final ArrayList H(String str) throws Throwable {
        ArrayList arrayList;
        int columnIndex;
        Throwable th;
        HashMap map = this.y0;
        if (map == null || !map.containsKey(str)) {
            return null;
        }
        Set set = gu1.V0;
        boolean z = false;
        boolean z2 = PreferenceManager.getDefaultSharedPreferences(this).getBoolean("showHiddenFiles", false);
        ArrayList arrayList2 = new ArrayList();
        for (String str2 : map.keySet()) {
            int iLastIndexOf = str2.lastIndexOf(47);
            if (str.equals(iLastIndexOf > 0 ? str2.substring(0, iLastIndexOf) : null)) {
                File file = new File(str2);
                arrayList2.add(new wk(file.getName(), j90.a(file), false, true));
            }
        }
        ArrayList arrayList3 = new ArrayList();
        try {
            Cursor cursorQuery = getContentResolver().query(MediaStore.Video.Media.getContentUri("external"), new String[]{"_id", "_data", "_size", "duration", "date_modified", "width", "height"}, "_data LIKE ? AND _data NOT LIKE ?", new String[]{str + "/%", str + "/%/%"}, null);
            int columnIndex2 = -1;
            try {
                if (cursorQuery == null) {
                    columnIndex = -1;
                } else {
                    try {
                        columnIndex = cursorQuery.getColumnIndex("_data");
                    } catch (Throwable th2) {
                        th = th2;
                        arrayList = null;
                        th = th;
                        if (cursorQuery != null) {
                            throw th;
                        }
                        try {
                            cursorQuery.close();
                            throw th;
                        } catch (Throwable th3) {
                            th.addSuppressed(th3);
                            throw th;
                        }
                    }
                }
                int columnIndex3 = cursorQuery == null ? -1 : cursorQuery.getColumnIndex("_id");
                int columnIndex4 = cursorQuery == null ? -1 : cursorQuery.getColumnIndex("_size");
                int columnIndex5 = cursorQuery == null ? -1 : cursorQuery.getColumnIndex("duration");
                int columnIndex6 = cursorQuery == null ? -1 : cursorQuery.getColumnIndex("date_modified");
                int columnIndex7 = cursorQuery == null ? -1 : cursorQuery.getColumnIndex("width");
                if (cursorQuery != null) {
                    columnIndex2 = cursorQuery.getColumnIndex("height");
                }
                while (columnIndex >= 0 && cursorQuery.moveToNext()) {
                    String string = cursorQuery.getString(columnIndex);
                    if (string != null) {
                        File file2 = new File(string);
                        if (!z2) {
                            arrayList = null;
                            try {
                                if (file2.getName().startsWith(".")) {
                                }
                            } catch (Throwable th4) {
                                th = th4;
                                th = th;
                                if (cursorQuery != null) {
                                    throw th;
                                }
                                cursorQuery.close();
                                throw th;
                            }
                        }
                        vw1 vw1VarA = j90.a(file2);
                        arrayList3.add(new wk(file2.getName(), vw1VarA, z, z));
                        String string2 = Uri.fromFile(vw1VarA.b).toString();
                        long j = columnIndex5 < 0 ? 0L : cursorQuery.getLong(columnIndex5);
                        if (j > 0) {
                            this.p0.put(string2, Long.valueOf(j));
                            this.o0.put(string2, wt2.t(j));
                        }
                        if (columnIndex3 >= 0) {
                            this.q0.put(string2, ContentUris.withAppendedId(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, cursorQuery.getLong(columnIndex3)));
                        }
                        if (columnIndex4 >= 0) {
                            this.t0.put(string2, Long.valueOf(cursorQuery.getLong(columnIndex4)));
                        }
                        if (columnIndex6 >= 0) {
                            this.u0.put(string2, Long.valueOf(cursorQuery.getLong(columnIndex6) * 1000));
                        }
                        if (columnIndex7 < 0 || columnIndex2 < 0) {
                            columnIndex2 = columnIndex2;
                        } else {
                            columnIndex2 = columnIndex2;
                            Q(string2, cursorQuery.getInt(columnIndex7), cursorQuery.getInt(columnIndex2));
                        }
                        z = false;
                    }
                }
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
                if (this.I) {
                    return null;
                }
                Collections.sort(arrayList2, new dc((byte) 5));
                Collections.sort(arrayList3, new dc((byte) 6));
                arrayList2.addAll(arrayList3);
                return arrayList2;
            } catch (Exception e) {
                e = e;
                e.printStackTrace();
                return arrayList;
            }
        } catch (Exception e2) {
            e = e2;
            arrayList = null;
        }
    }

    public final wk I(Uri uri, String str, boolean z, boolean z2) {
        if (gj1.f(uri)) {
            return new wk(str, gj1.e(this, uri, str), z, z2);
        }
        String path = "file".equals(uri.getScheme()) ? uri.getPath() : uri.toString();
        File file = path == null ? null : new File(path);
        if (file != null && file.exists() && file.isDirectory() == z2) {
            return new wk(str, j90.a(file), z, z2);
        }
        return null;
    }

    public final void J() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new u70(R.drawable.ic_search_24dp, null, getString(R.string.together_find), null, false, new ek(this, (byte) 4)));
        arrayList.add(new u70(R.drawable.ic_link_24dp, null, getString(R.string.together_enter_code), null, false, new ek(this, (byte) 5)));
        s2.q(this, new qs2(this, wt2.F(this)), null, getString(R.string.together_join), arrayList);
    }

    public final void K(String str, String str2) {
        startActivity(new Intent(this, (Class<?>) PlayerActivity.class).putExtra("join_code", str).putExtra("join_password", str2));
    }

    public final ArrayList M() {
        ArrayList arrayList = new ArrayList();
        for (jj1 jj1Var : dz0.d(this)) {
            String str = jj1Var.b;
            fj1 fj1VarE = gj1.e(this, jj1Var.b(), jj1Var.b);
            arrayList.add(new wk(str, fj1VarE, true, fj1VarE.d));
        }
        return arrayList;
    }

    public final void N(View view) {
        if (view == null) {
            return;
        }
        view.clearAnimation();
        if (wt2.C(this)) {
            return;
        }
        TypedArray typedArrayObtainStyledAttributes = obtainStyledAttributes(new int[]{android.R.attr.windowAnimationStyle});
        try {
            int resourceId = typedArrayObtainStyledAttributes.getResourceId(0, 0);
            typedArrayObtainStyledAttributes.recycle();
            Animation animationLoadAnimation = null;
            if (resourceId != 0) {
                TypedArray typedArrayObtainStyledAttributes2 = obtainStyledAttributes(resourceId, new int[]{android.R.attr.activityOpenEnterAnimation});
                try {
                    int resourceId2 = typedArrayObtainStyledAttributes2.getResourceId(0, 0);
                    typedArrayObtainStyledAttributes2.recycle();
                    if (resourceId2 != 0) {
                        try {
                            animationLoadAnimation = AnimationUtils.loadAnimation(this, resourceId2);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                } catch (Throwable th) {
                    typedArrayObtainStyledAttributes2.recycle();
                    throw th;
                }
            }
            if (animationLoadAnimation != null) {
                view.startAnimation(animationLoadAnimation);
            }
        } catch (Throwable th2) {
            typedArrayObtainStyledAttributes.recycle();
            throw th2;
        }
    }

    public final float O(String str) {
        Long l = (Long) this.x0.get(str);
        Long l2 = (Long) this.p0.get(str);
        if (l == null || l2 == null || l2.longValue() <= 0) {
            return -1.0f;
        }
        return l.longValue() / l2.longValue();
    }

    public final void P() {
        ArrayList arrayList = this.S;
        wk wkVar = arrayList.isEmpty() ? null : (wk) jf2.d(1, arrayList);
        StringBuilder sb = new StringBuilder(jf2.w(this.E0));
        sb.append("\u0000");
        sb.append(wkVar == null ? "" : wkVar.b.e());
        sb.append("\u0000");
        sb.append(wkVar != null && ag.u(this, wkVar.b.e()));
        String string = sb.toString();
        if (string.equals(this.k0)) {
            return;
        }
        this.k0 = string;
        invalidateOptionsMenu();
    }

    public final void Q(String str, int i, int i2) {
        int iMin = Math.min(i, i2);
        if (iMin > 0) {
            this.v0.put(str, iMin + "p");
        }
    }

    public final boolean R() {
        ArrayList arrayList = this.S;
        wk wkVar = arrayList.isEmpty() ? null : (wk) jf2.d(1, arrayList);
        return wkVar != null && (wkVar.b instanceof fj1);
    }

    public final void S(wk wkVar) {
        if (!A().equals(this.m0)) {
            this.m0 = A();
            this.y0 = null;
            this.D0.clear();
            this.A0.clear();
        }
        if (this.N.getVisibility() == 0) {
            return;
        }
        int i = this.E0;
        if (i == 4) {
            this.h0 = null;
            setTitle(R.string.nav_iptv);
            X();
            P();
            this.V.setVisibility(8);
            U(R.string.iptv_body, false);
            this.Z.setText(R.string.iptv_title);
            this.Z.setVisibility(0);
            return;
        }
        if (i == 2 && ((!this.J || dz0.d(this).isEmpty()) && !wt2.h(this))) {
            this.h0 = null;
            this.S.clear();
            V();
            if (!wt2.Q(this)) {
                U(R.string.browse_needs_access, true);
                return;
            } else if (this.d0) {
                U(R.string.browse_needs_access, false);
                return;
            } else {
                this.d0 = true;
                requestPermissions(new String[]{"android.permission.READ_EXTERNAL_STORAGE"}, 2);
                return;
            }
        }
        ArrayList arrayList = this.S;
        if (!this.J && this.E0 == 2 && wt2.h(this)) {
            ArrayList arrayListG0 = g0();
            boolean z = arrayListG0.size() == 1;
            this.C0 = z;
            if (z && arrayList.isEmpty()) {
                arrayList.add((wk) arrayListG0.get(0));
            }
        } else {
            this.C0 = false;
        }
        wk wkVar2 = this.S.isEmpty() ? null : (wk) jf2.d(1, this.S);
        V();
        PreferenceManager.getDefaultSharedPreferences(this).edit().putString("browseDest", jf2.s(this.E0)).putString("browseTrail", c0()).apply();
        String str = c0() + "\u0000" + jf2.w(this.E0) + "\u0000" + this.f0;
        if (!str.equals(this.g0) && !this.T.isEmpty()) {
            this.T.clear();
            if (this.U.getAdapter() != null) {
                this.U.getAdapter().d();
            }
        }
        String str2 = this.f0;
        this.h0 = str;
        this.X.setVisibility(8);
        Thread thread = this.c0;
        if (thread != null) {
            thread.interrupt();
        }
        if (this.T.isEmpty()) {
            LinearLayout linearLayout = this.P;
            ek ekVar = this.R;
            linearLayout.removeCallbacks(ekVar);
            this.P.postDelayed(ekVar, 200L);
        }
        Thread thread2 = new Thread(new jk(this, str2, wkVar2, str, wkVar));
        this.c0 = thread2;
        thread2.start();
    }

    public final void T(int i, int i2, Runnable runnable) {
        U(i, false);
        if (i2 == 0) {
            return;
        }
        this.b0.setText(i2);
        this.b0.setOnClickListener(new pk(runnable, (byte) 0));
        this.b0.setVisibility(0);
        this.b0.requestFocus();
    }

    public final void U(int i, boolean z) {
        F();
        this.Z.setVisibility(8);
        this.Y.setVisibility(8);
        this.T.clear();
        if (this.U.getAdapter() != null) {
            this.U.getAdapter().d();
        }
        this.U.setVisibility(8);
        this.X.setVisibility(0);
        this.a0.setText(i);
        this.b0.setVisibility(z ? 0 : 8);
        if (z) {
            this.b0.setText(R.string.browse_grant_access);
            this.b0.setOnClickListener(new ik(this, (byte) 9));
            this.b0.requestFocus();
        }
    }

    public final void V() {
        String string;
        int i;
        ArrayList arrayList = this.S;
        wk wkVar = arrayList.isEmpty() ? null : (wk) jf2.d(1, arrayList);
        byte b = 2;
        if (wkVar == null) {
            int iU = jf2.u(this.E0);
            if (iU == 0) {
                i = R.string.nav_favorites;
            } else if (iU != 2) {
                i = iU != 3 ? R.string.browse_title : R.string.nav_iptv;
            } else {
                i = R.string.nav_network;
            }
            string = getString(i);
        } else {
            string = wkVar.a;
        }
        setTitle(string);
        X();
        this.W.removeAllViews();
        int size = arrayList.size();
        View view = this.V;
        if (size < 2) {
            view.setVisibility(8);
        } else {
            byte b2 = 0;
            view.setVisibility(0);
            int iN = sj.n(this, R.attr.colorSurfaceContainerHighest, -12303292);
            int i2 = 0;
            while (i2 < arrayList.size()) {
                int i3 = R.attr.colorOnSurfaceVariant;
                if (i2 > 0) {
                    TextView textView = new TextView(this);
                    textView.setText("›");
                    textView.setTextColor(sj.n(this, R.attr.colorOnSurfaceVariant, -7829368));
                    textView.setPadding(wt2.p(6), 0, wt2.p(6), 0);
                    this.W.addView(textView);
                }
                boolean z = i2 == arrayList.size() - 1;
                TextView textView2 = new TextView(this);
                textView2.setText(((wk) arrayList.get(i2)).a);
                textView2.setSingleLine(true);
                textView2.setGravity(17);
                textView2.setIncludeFontPadding(false);
                textView2.setPadding(wt2.p(12), 0, wt2.p(12), 0);
                if (z) {
                    i3 = R.attr.colorOnSurface;
                }
                textView2.setTextColor(sj.n(this, i3, -1));
                textView2.setBackground(s2.u(this, iN, wt2.p(8), getResources().getDimensionPixelSize(R.dimen.focus_ring_width)));
                if (!z) {
                    textView2.setClickable(true);
                    textView2.setFocusable(true);
                    textView2.setOnClickListener(new gk(this, i2, b2));
                }
                this.W.addView(textView2, new LinearLayout.LayoutParams(-2, wt2.p(32)));
                i2++;
            }
            this.W.post(new ek(this, b));
        }
        P();
    }

    public final void W() {
        F();
        Thread thread = this.c0;
        if (thread != null) {
            thread.interrupt();
        }
        if (this.N.getVisibility() != 0) {
            N(this.N);
        }
        this.N.setVisibility(0);
        View view = this.O;
        if (view != null) {
            view.clearAnimation();
        }
        View view2 = (View) this.U.getParent();
        if (view2 != null) {
            view2.clearAnimation();
        }
        this.O.setVisibility(8);
        this.U.setVisibility(8);
        this.X.setVisibility(8);
        this.V.setVisibility(8);
        this.N.findViewById(ag.a(this).isEmpty() ? R.id.home_card_files : R.id.home_card_favorites).requestFocus();
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [boolean] */
    public final void X() {
        ck0 ck0VarL = l();
        if (ck0VarL == null) {
            return;
        }
        ck0VarL.b0(this.J || this.K || this.S.size() > this.C0);
    }

    public final void Y() {
        if (this.U.getLayoutManager() instanceof GridLayoutManager) {
            GridLayoutManager gridLayoutManager = (GridLayoutManager) this.U.getLayoutManager();
            int iZ = Z();
            if (gridLayoutManager.F != iZ) {
                gridLayoutManager.t1(iZ);
            }
        }
    }

    public final int Z() {
        int width = (this.U.getWidth() - this.U.getPaddingLeft()) - this.U.getPaddingRight();
        if ("tiles".equals(this.s0)) {
            if (width > 0) {
                return Math.max(2, width / wt2.p(this.K ? 220 : 170));
            }
        } else if (!"columns".equals(this.s0) || getResources().getConfiguration().screenWidthDp < 600) {
            return 1;
        }
        return 2;
    }

    public final void a0(wk wkVar) {
        j90 j90Var = wkVar.b;
        Uri uriE = j90Var.e();
        if (ag.u(this, uriE)) {
            ArrayList arrayListA = ag.a(this);
            ag.y(arrayListA, uriE);
            ag.O(this, arrayListA);
        } else {
            Long l = (Long) this.p0.get(uriE.toString());
            String str = wkVar.a;
            boolean z = wkVar.c;
            String strB = gj1.b(j90Var);
            long jI = wkVar.c ? 0L : j90Var.i();
            long jLongValue = l != null ? l.longValue() : 0L;
            ArrayList arrayListA2 = ag.a(this);
            ag.y(arrayListA2, uriE);
            arrayListA2.add(new qi0(str.replaceAll("[\t\n]", " "), uriE, z, strB != null ? strB.replaceAll("[\t\n]", "") : "", jI, jLongValue));
            ag.O(this, arrayListA2);
        }
        P();
        if (this.E0 == 1 && this.S.isEmpty()) {
            S(null);
        } else if (this.U.getAdapter() != null) {
            this.U.getAdapter().d();
        }
    }

    public final Uri b0() {
        ArrayList arrayList = this.S;
        if (arrayList.isEmpty()) {
            return null;
        }
        Uri uriE = ((wk) jf2.d(1, arrayList)).b.e();
        if (lp2.e(uriE) && lp2.d(uriE) == null) {
            return uriE;
        }
        return null;
    }

    public final String c0() {
        StringBuilder sb = new StringBuilder();
        for (wk wkVar : this.S) {
            sb.append(wkVar.a);
            sb.append('\n');
            sb.append(wkVar.b.e());
            sb.append('\n');
        }
        return sb.toString();
    }

    public final void d0() {
        boolean z = this.C0;
        ArrayList arrayList = this.S;
        if (z && arrayList.size() == 1) {
            arrayList.clear();
        }
        if (!arrayList.isEmpty()) {
            S((wk) arrayList.remove(arrayList.size() - 1));
        } else if (!this.K || this.J || this.N.getVisibility() == 0) {
            finish();
        } else {
            W();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:81:0x0085, code lost:
    
        r13 = null;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final synchronized java.util.Map e0() {
        /*
            Method dump skipped, instruction units count: 239
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.brouken.player.BrowserActivity.e0():java.util.Map");
    }

    public final String f0(File file) {
        if (Build.VERSION.SDK_INT >= 24) {
            try {
                StorageManager storageManager = (StorageManager) getSystemService("storage");
                String description = null;
                StorageVolume storageVolume = storageManager == null ? null : storageManager.getStorageVolume(file);
                if (storageVolume != null) {
                    description = storageVolume.getDescription(this);
                }
                if (description != null && !description.isEmpty()) {
                    return description;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        String name = file.getName();
        return (name == null || name.isEmpty()) ? file.getAbsolutePath() : name;
    }

    public final ArrayList g0() {
        ArrayList<File> arrayList;
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        File externalStorageDirectory = wt2.h(this) ? Environment.getExternalStorageDirectory() : null;
        if (externalStorageDirectory != null && externalStorageDirectory.isDirectory()) {
            String string = getString(R.string.browse_internal);
            vw1 vw1VarA = j90.a(externalStorageDirectory);
            arrayList2.add(new wk(string, vw1VarA, true, vw1VarA.b.isDirectory()));
            arrayList3.add(externalStorageDirectory.getAbsolutePath());
        }
        if (wt2.h(this)) {
            arrayList = new ArrayList();
            StorageManager storageManager = (StorageManager) getSystemService("storage");
            ArrayList arrayList4 = new ArrayList();
            try {
                if (Build.VERSION.SDK_INT >= 24) {
                    arrayList4.addAll(storageManager.getStorageVolumes());
                } else {
                    arrayList4.addAll(Arrays.asList((Object[]) StorageManager.class.getMethod("getVolumeList", null).invoke(storageManager, null)));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            for (Object obj : arrayList4) {
                try {
                    String str = (String) obj.getClass().getMethod("getState", null).invoke(obj, null);
                    if ("mounted".equals(str) || "mounted_ro".equals(str)) {
                        File directory = Build.VERSION.SDK_INT >= 30 ? ((StorageVolume) obj).getDirectory() : new File((String) obj.getClass().getMethod("getPath", null).invoke(obj, null));
                        if (directory != null) {
                            arrayList.add(directory);
                        }
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        } else {
            arrayList = new ArrayList();
        }
        for (File file : arrayList) {
            if (file.isDirectory() && !arrayList3.contains(file.getAbsolutePath())) {
                arrayList3.add(file.getAbsolutePath());
                String strF0 = f0(file);
                vw1 vw1VarA2 = j90.a(file);
                arrayList2.add(new wk(strF0, vw1VarA2, true, vw1VarA2.b.isDirectory()));
            }
        }
        File[] externalFilesDirs = wt2.h(this) ? getExternalFilesDirs(null) : new File[0];
        int length = externalFilesDirs.length;
        for (int i = 0; i < length; i++) {
            File parentFile = externalFilesDirs[i];
            if (parentFile != null) {
                for (int i2 = 0; i2 < 4 && parentFile != null; i2++) {
                    parentFile = parentFile.getParentFile();
                }
                if (parentFile != null && parentFile.isDirectory() && !arrayList3.contains(parentFile.getAbsolutePath())) {
                    arrayList3.add(parentFile.getAbsolutePath());
                    String strF1 = f0(parentFile);
                    vw1 vw1VarA3 = j90.a(parentFile);
                    arrayList2.add(new wk(strF1, vw1VarA3, true, vw1VarA3.b.isDirectory()));
                }
            }
        }
        return arrayList2;
    }

    @Override // android.app.Activity, android.view.ContextThemeWrapper
    public final void onApplyThemeResource(Resources.Theme theme, int i, boolean z) {
        super.onApplyThemeResource(theme, i, z);
        theme.applyStyle(gu1.a(this, gu1.m(this)), true);
    }

    @Override // defpackage.g7, defpackage.av, android.app.Activity, android.content.ComponentCallbacks
    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (this.K || this.J) {
            return;
        }
        z(configuration.screenWidthDp >= 600);
    }

    @Override // defpackage.g7, defpackage.av, defpackage.zu, android.app.Activity
    public final void onCreate(Bundle bundle) {
        ck0 g03Var;
        byte b;
        int i;
        ArrayList arrayList = this.S;
        k().p(gu1.g(this));
        super.onCreate(bundle);
        boolean zM = gu1.m(this);
        byte b2 = 1;
        if (!zM && gu1.l(this)) {
            getTheme().applyStyle(R.style.ThemeOverlay_JustPlus_Amoled, true);
        }
        Window window = getWindow();
        nf1 nf1Var = new nf1(getWindow().getDecorView());
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 35) {
            g03Var = new j03(window, nf1Var);
        } else if (i2 >= 30) {
            g03Var = new h03(window, nf1Var);
        } else {
            g03Var = i2 >= 26 ? new g03(window, nf1Var) : new f03(window, nf1Var);
        }
        g03Var.Z(zM);
        byte b3 = 0;
        gj0.y(getWindow(), false);
        boolean booleanExtra = getIntent().getBooleanExtra("subtitles", false);
        this.I = booleanExtra;
        this.J = booleanExtra || getCallingActivity() != null;
        this.K = wt2.F(this);
        this.l0 = w();
        this.m0 = A();
        setContentView(R.layout.activity_browser);
        MaterialToolbar materialToolbar = (MaterialToolbar) findViewById(R.id.browse_toolbar);
        if (wt2.F(this)) {
            materialToolbar.setTouchscreenBlocksFocus(false);
        }
        this.U = (RecyclerView) findViewById(R.id.browse_list);
        this.V = findViewById(R.id.browse_crumbs_scroll);
        this.W = (LinearLayout) findViewById(R.id.browse_crumbs);
        this.X = findViewById(R.id.browse_blank);
        this.Y = (LinearLayout) findViewById(R.id.browse_blank_tiles);
        this.Z = (TextView) findViewById(R.id.browse_blank_title);
        this.a0 = (TextView) findViewById(R.id.browse_blank_text);
        this.b0 = (MaterialButton) findViewById(R.id.browse_blank_action);
        this.O = findViewById(R.id.browse_header);
        this.N = findViewById(R.id.browse_home);
        this.P = (LinearLayout) findViewById(R.id.browse_skeleton);
        v(materialToolbar);
        materialToolbar.setNavigationOnClickListener(new ik(this, (byte) 7));
        this.s0 = PreferenceManager.getDefaultSharedPreferences(this).getString("browseView", "rows");
        this.U.setLayoutManager(new GridLayoutManager());
        this.U.i(new uk());
        this.U.setHasFixedSize(true);
        this.U.setItemViewCacheSize(8);
        this.U.setAdapter(new al(this));
        qs2 qs2Var = new qs2(this, wt2.F(this));
        View viewFindViewById = findViewById(R.id.browse_root);
        fk fkVar = new fk(this, qs2Var, b2);
        WeakHashMap weakHashMap = jw2.a;
        bw2.j(viewFindViewById, fkVar);
        findViewById(R.id.browse_root).addOnLayoutChangeListener(new tk(this, b3));
        this.b0.setOnClickListener(new ik(this, (byte) 8));
        i().a(this, new vk(this));
        String string = bundle != null ? bundle.getString("dest") : PreferenceManager.getDefaultSharedPreferences(this).getString("browseDest", null);
        byte b4 = 4;
        int[] iArrY = jf2.y(4);
        int length = iArrY.length;
        int i3 = 0;
        while (true) {
            b = 2;
            if (i3 >= length) {
                i = 2;
                break;
            }
            i = iArrY[i3];
            if (jf2.s(i).equals(string) && i != 4) {
                break;
            } else {
                i3++;
            }
        }
        this.E0 = i;
        this.N.findViewById(R.id.home_card_favorites).setOnClickListener(new ik(this, b3));
        this.N.findViewById(R.id.home_card_files).setOnClickListener(new ik(this, b2));
        this.N.findViewById(R.id.home_card_network).setOnClickListener(new ik(this, b));
        this.N.findViewById(R.id.home_card_iptv).setOnClickListener(new ik(this, (byte) 3));
        this.N.findViewById(R.id.home_settings).setOnClickListener(new ik(this, b4));
        this.N.findViewById(R.id.home_link).setOnClickListener(new ik(this, (byte) 5));
        this.N.findViewById(R.id.home_room).setOnClickListener(new ik(this, (byte) 6));
        MaterialButton materialButton = (MaterialButton) this.N.findViewById(R.id.home_settings);
        ec2 ec2VarL = materialButton.getShapeAppearanceModel().l();
        kz1 kz1Var = new kz1(0.5f);
        ec2VarL.e = kz1Var;
        ec2VarL.f = kz1Var;
        ec2VarL.g = kz1Var;
        ec2VarL.h = kz1Var;
        materialButton.setShapeAppearance(ec2VarL.a());
        wt2.r((MaterialButton) this.N.findViewById(R.id.home_settings));
        wt2.r((MaterialButton) this.N.findViewById(R.id.home_link));
        wt2.r((MaterialButton) this.N.findViewById(R.id.home_room));
        int iN = sj.n(this, R.attr.colorSurfaceContainer, -12303292);
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.focus_ring_width_card);
        int[] iArr = {R.id.home_card_favorites, R.id.home_card_files, R.id.home_card_network, R.id.home_card_iptv};
        for (int i4 = 0; i4 < 4; i4++) {
            this.N.findViewById(iArr[i4]).setBackground(s2.u(this, iN, wt2.p(16), dimensionPixelSize));
        }
        if (!this.K && !this.J) {
            z(getResources().getConfiguration().screenWidthDp >= 600);
        }
        if (this.y0 == null && !this.z0) {
            this.z0 = true;
            new Thread(new ek(this, (byte) 3)).start();
        }
        String string2 = bundle != null ? bundle.getString("trail") : PreferenceManager.getDefaultSharedPreferences(this).getString("browseTrail", null);
        arrayList.clear();
        if (string2 != null && !string2.isEmpty()) {
            String[] strArrSplit = string2.split("\n");
            int i5 = 0;
            while (true) {
                int i6 = i5 + 1;
                if (i6 >= strArrSplit.length) {
                    break;
                }
                wk wkVarI = I(Uri.parse(strArrSplit[i6]), strArrSplit[i5], i5 == 0, true);
                if (wkVarI == null) {
                    break;
                }
                arrayList.add(wkVarI);
                i5 += 2;
            }
        }
        if (this.K && !this.J && arrayList.isEmpty()) {
            W();
        }
        if (bundle != null || this.J) {
            return;
        }
        gu1 gu1Var = new gu1(this);
        if (gu1Var.M0) {
            long jCurrentTimeMillis = System.currentTimeMillis();
            if (jCurrentTimeMillis - gu1Var.N0 < 3600000) {
                return;
            }
            gu1Var.N0 = jCurrentTimeMillis;
            SharedPreferences.Editor editorEdit = gu1Var.b.edit();
            editorEdit.putLong("updateLastCheck", jCurrentTimeMillis);
            editorEdit.apply();
            dt2.a(new fk((Object) this, (Object) gu1Var, (byte) 0));
        }
    }

    @Override // android.app.Activity
    public final boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.browse, menu);
        if (menu instanceof qd1) {
            ((qd1) menu).setGroupDividerEnabled(true);
        } else if (Build.VERSION.SDK_INT >= 28) {
            z80.k(menu);
        }
        SearchView searchView = (SearchView) menu.findItem(R.id.browse_search).getActionView();
        if (searchView == null) {
            return true;
        }
        searchView.setQueryHint(getString(R.string.browse_search));
        searchView.setOnQueryTextListener(new lb1((Object) this, (byte) 13));
        searchView.setOnCloseListener(new dk(this));
        return true;
    }

    @Override // defpackage.g7, android.app.Activity
    public final void onDestroy() {
        ObjectAnimator objectAnimator = this.Q;
        if (objectAnimator != null) {
            objectAnimator.cancel();
            this.Q = null;
        }
        this.n0.shutdownNow();
        super.onDestroy();
    }

    @Override // defpackage.g7, android.app.Activity, android.view.KeyEvent.Callback
    public final boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 111 || keyEvent.getRepeatCount() != 0) {
            return super.onKeyDown(i, keyEvent);
        }
        i().b();
        return true;
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public final boolean onKeyUp(int i, KeyEvent keyEvent) {
        if (i != 4 || keyEvent.isCanceled()) {
            return super.onKeyUp(i, keyEvent);
        }
        i().b();
        return true;
    }

    @Override // android.app.Activity
    public final boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.browse_open_link) {
            sj.a(this, new dk(this));
            return true;
        }
        if (menuItem.getItemId() == R.id.browse_join_room) {
            J();
            return true;
        }
        if (menuItem.getItemId() == R.id.browse_view) {
            boolean z = getResources().getConfiguration().screenWidthDp >= 600;
            String str = "rows";
            if ("rows".equals(this.s0)) {
                str = "tiles";
            } else if ("tiles".equals(this.s0) && z) {
                str = "columns";
            }
            if (!str.equals(this.s0)) {
                this.s0 = str;
                Set set = gu1.V0;
                PreferenceManager.getDefaultSharedPreferences(this).edit().putString("browseView", str).apply();
                Y();
                this.U.setAdapter(new al(this));
            }
            invalidateOptionsMenu();
            return true;
        }
        if (menuItem.getItemId() == R.id.browse_settings) {
            startActivity(new Intent(this, (Class<?>) SettingsActivity.class));
            return true;
        }
        if (menuItem.getItemId() == R.id.browse_exit) {
            finishAndRemoveTask();
            return true;
        }
        if (menuItem.getItemId() == R.id.browse_add_network) {
            Uri uriB0 = b0();
            if (uriB0 != null) {
                x(uriB0);
                return true;
            }
            y();
            return true;
        }
        if (menuItem.getItemId() != R.id.browse_favorite) {
            return super.onOptionsItemSelected(menuItem);
        }
        ArrayList arrayList = this.S;
        if (!arrayList.isEmpty()) {
            a0((wk) jf2.d(1, arrayList));
        }
        return true;
    }

    @Override // android.app.Activity
    public final boolean onPrepareOptionsMenu(Menu menu) {
        int i;
        if (menu instanceof qd1) {
            ((qd1) menu).D = true;
        }
        MenuItem menuItemFindItem = menu.findItem(R.id.browse_search);
        ArrayList arrayList = this.S;
        if (menuItemFindItem != null) {
            menuItemFindItem.setVisible((this.E0 == 4 || (arrayList.isEmpty() && ((i = this.E0) == 1 || i == 3))) ? false : true);
        }
        MenuItem menuItemFindItem2 = menu.findItem(R.id.browse_add_network);
        boolean z = b0() != null;
        menuItemFindItem2.setVisible(((this.J || this.E0 == 3) && arrayList.isEmpty()) || z);
        menuItemFindItem2.setTitle(z ? R.string.browse_torrent_add : R.string.browse_add_network);
        MenuItem menuItemFindItem3 = menu.findItem(R.id.browse_favorite);
        wk wkVar = arrayList.isEmpty() ? null : (wk) jf2.d(1, arrayList);
        menuItemFindItem3.setVisible((this.J || this.E0 == 4 || wkVar == null) ? false : true);
        if (wkVar != null) {
            boolean zU = ag.u(this, wkVar.b.e());
            menuItemFindItem3.setIcon(zU ? R.drawable.ic_star_24dp : R.drawable.ic_star_outline_24dp);
            menuItemFindItem3.setTitle(zU ? R.string.browse_favorite_remove : R.string.browse_favorite_add);
        }
        menu.findItem(R.id.browse_open_link).setVisible(!this.J);
        menu.findItem(R.id.browse_join_room).setVisible(!this.J);
        menu.findItem(R.id.browse_settings).setVisible(!this.J);
        menu.findItem(R.id.browse_exit).setVisible(!this.J);
        MenuItem menuItemFindItem4 = menu.findItem(R.id.browse_view);
        if (menuItemFindItem4 != null) {
            menuItemFindItem4.setVisible(this.E0 != 4);
            menuItemFindItem4.setIcon("tiles".equals(this.s0) ? R.drawable.ic_view_grid_24dp : ("columns".equals(this.s0) && (getResources().getConfiguration().screenWidthDp >= 600)) ? R.drawable.ic_view_columns_24dp : R.drawable.ic_view_list_24dp);
        }
        int iN = sj.n(this, R.attr.colorOnSurfaceVariant, -1);
        for (int i2 = 0; i2 < menu.size(); i2++) {
            Drawable icon = menu.getItem(i2).getIcon();
            if (icon != null) {
                icon.mutate().setTint(iN);
            }
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override // defpackage.g7, defpackage.av, android.app.Activity
    public final void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        if (i == 2) {
            S(null);
        }
    }

    @Override // defpackage.av, defpackage.zu, android.app.Activity
    public final void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putString("trail", c0());
        bundle.putString("dest", jf2.s(this.E0));
    }

    @Override // defpackage.g7, android.app.Activity
    public final void onStart() {
        super.onStart();
        if (w().equals(this.l0)) {
            S(null);
        } else {
            recreate();
        }
    }

    public final String w() {
        boolean zM = gu1.m(this);
        return gu1.a(this, zM) + "|" + zM + "|" + gu1.l(this);
    }

    public final void x(Uri uri) {
        LinearLayout linearLayoutF = s2.f(s2.e(this));
        s2.h(this, getString(R.string.browse_torrent_add), linearLayoutF, getString(android.R.string.ok), new ld(this, s2.F(linearLayoutF, getString(R.string.browse_torrent_link), "magnet:?xt=urn:btih:…"), uri, (byte) 2));
    }

    public final void y() {
        final byte b = 0;
        final byte b2 = 1;
        ij1 ij1Var = new ij1(this, new ek(this, b), new ek(this, b2));
        s2.e(this);
        String string = getString(R.string.browse_add_network);
        String string2 = getString(R.string.browse_network_manual);
        c90 c90Var = new c90(ij1Var, b2);
        final byte b3 = 2;
        c90 c90Var2 = new c90(ij1Var, b3);
        String string3 = getString(android.R.string.cancel);
        LinearLayout linearLayout = ij1Var.d;
        ga1 ga1VarC = s2.C(this, string, linearLayout, string2, c90Var, string3, null, null, true);
        ((Dialog) ga1VarC.m).setOnDismissListener(new j70(c90Var2, b));
        ij1Var.k = ga1VarC;
        linearLayout.removeAllViews();
        ga1 ga1Var = ij1Var.k;
        String string4 = ij1Var.a.getString(R.string.browse_add_network);
        TextView textView = (TextView) ga1Var.n;
        if (textView != null) {
            textView.setText(string4);
        }
        c11 c11Var = new c11(linearLayout.getContext());
        ij1Var.n = c11Var;
        c11Var.setIndeterminate(true);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        layoutParams.bottomMargin = wt2.p(8);
        linearLayout.addView(ij1Var.n, layoutParams);
        if (ij1Var.l > 0 || ij1Var.c()) {
            int i = ij1Var.l > 0 ? R.string.browse_network_searching : R.string.browse_network_none;
            TextView textView2 = new TextView(linearLayout.getContext());
            ij1Var.m = textView2;
            textView2.setText(i);
            ij1Var.m.setPadding(0, 0, 0, wt2.p(12));
            linearLayout.addView(ij1Var.m);
        }
        for (ue2 ue2Var : ij1Var.e) {
            ij1Var.d(ue2Var.b, ue2Var.a, "SMB", new hj1(ij1Var, ue2Var, b2));
        }
        for (d90 d90Var : ij1Var.f) {
            ij1Var.d(d90Var.a, d90Var.b, "DLNA", new hj1(ij1Var, d90Var, b3));
        }
        Iterator it = ij1Var.g.iterator();
        while (it.hasNext()) {
            ij1Var.g((ip2) it.next());
        }
        linearLayout.post(new c90(ij1Var, (byte) 3));
        final ve2 ve2Var = ij1Var.h;
        NsdManager nsdManager = (NsdManager) ve2Var.b.getSystemService("servicediscovery");
        ve2Var.h = nsdManager;
        if (nsdManager != null) {
            se2 se2Var = new se2(ve2Var, b);
            ve2Var.i = se2Var;
            try {
                nsdManager.discoverServices("_smb._tcp", 1, se2Var);
            } catch (Exception unused) {
            }
        }
        new Thread(new Runnable() { // from class: re2
            @Override // java.lang.Runnable
            public final void run() {
                byte b4 = b;
                byte b5 = 5;
                int i2 = 0;
                ve2 ve2Var2 = ve2Var;
                switch (b4) {
                    case DecoderInputBuffer.BUFFER_REPLACEMENT_MODE_DISABLED /* 0 */:
                        AtomicBoolean atomicBoolean = ve2Var2.e;
                        byte[] bArr = new byte[50];
                        bArr[0] = 82;
                        bArr[1] = 83;
                        bArr[2] = 0;
                        bArr[3] = 0;
                        bArr[5] = 1;
                        bArr[12] = 32;
                        bArr[13] = 67;
                        bArr[14] = 75;
                        for (int i3 = 15; i3 < 45; i3++) {
                            bArr[i3] = 65;
                        }
                        bArr[45] = 0;
                        bArr[47] = 33;
                        bArr[49] = 1;
                        try {
                            DatagramSocket datagramSocket = new DatagramSocket();
                            try {
                                datagramSocket.setBroadcast(true);
                                datagramSocket.setSoTimeout(700);
                                for (InetAddress inetAddress : ve2.a()) {
                                    if (atomicBoolean.get()) {
                                        datagramSocket.close();
                                        return;
                                    }
                                    try {
                                        datagramSocket.send(new DatagramPacket(bArr, 50, inetAddress, 137));
                                    } catch (IOException unused2) {
                                    }
                                }
                                int i4 = 512;
                                byte[] bArr2 = new byte[512];
                                long jCurrentTimeMillis = System.currentTimeMillis() + 6000;
                                while (!atomicBoolean.get() && System.currentTimeMillis() < jCurrentTimeMillis) {
                                    DatagramPacket datagramPacket = new DatagramPacket(bArr2, i4);
                                    try {
                                        datagramSocket.receive(datagramPacket);
                                        String hostAddress = datagramPacket.getAddress().getHostAddress();
                                        byte[] data = datagramPacket.getData();
                                        String str = null;
                                        if (datagramPacket.getLength() > 56) {
                                            int i5 = data[56] & 255;
                                            for (int i6 = 57; i2 < i5 && i6 + 17 <= datagramPacket.getLength(); i6 += 18) {
                                                int i7 = data[i6 + 15] & 255;
                                                boolean z = (data[i6 + 16] & 128) != 0;
                                                if (i7 == 0 && !z) {
                                                    String strTrim = new String(data, i6, 15).trim();
                                                    if (!strTrim.isEmpty()) {
                                                        str = strTrim;
                                                    }
                                                }
                                                i2++;
                                            }
                                        }
                                        ve2Var2.c(hostAddress, str);
                                        i2 = 0;
                                        i4 = 512;
                                    } catch (IOException unused3) {
                                    }
                                    break;
                                }
                                datagramSocket.close();
                                return;
                            } catch (Throwable th) {
                                try {
                                    datagramSocket.close();
                                    throw th;
                                } catch (Throwable th2) {
                                    th.addSuppressed(th2);
                                    throw th;
                                }
                            }
                        } catch (Exception unused4) {
                            return;
                        }
                    case 1:
                        ve2Var2.getClass();
                        for (int[] iArr : ve2.e()) {
                            int i8 = iArr[0];
                            int i9 = iArr[2];
                            for (int i10 = iArr[1]; i10 <= i9; i10++) {
                                if (ve2Var2.e.get()) {
                                    return;
                                }
                                ve2Var2.g.execute(new lc(ve2Var2, i8 | i10, b5));
                            }
                        }
                        return;
                    default:
                        ve2Var2.d();
                        return;
                }
            }
        }, "smb-netbios").start();
        new Thread(new Runnable() { // from class: re2
            @Override // java.lang.Runnable
            public final void run() {
                byte b4 = b2;
                byte b5 = 5;
                int i2 = 0;
                ve2 ve2Var2 = ve2Var;
                switch (b4) {
                    case DecoderInputBuffer.BUFFER_REPLACEMENT_MODE_DISABLED /* 0 */:
                        AtomicBoolean atomicBoolean = ve2Var2.e;
                        byte[] bArr = new byte[50];
                        bArr[0] = 82;
                        bArr[1] = 83;
                        bArr[2] = 0;
                        bArr[3] = 0;
                        bArr[5] = 1;
                        bArr[12] = 32;
                        bArr[13] = 67;
                        bArr[14] = 75;
                        for (int i3 = 15; i3 < 45; i3++) {
                            bArr[i3] = 65;
                        }
                        bArr[45] = 0;
                        bArr[47] = 33;
                        bArr[49] = 1;
                        try {
                            DatagramSocket datagramSocket = new DatagramSocket();
                            try {
                                datagramSocket.setBroadcast(true);
                                datagramSocket.setSoTimeout(700);
                                for (InetAddress inetAddress : ve2.a()) {
                                    if (atomicBoolean.get()) {
                                        datagramSocket.close();
                                        return;
                                    }
                                    try {
                                        datagramSocket.send(new DatagramPacket(bArr, 50, inetAddress, 137));
                                    } catch (IOException unused2) {
                                    }
                                }
                                int i4 = 512;
                                byte[] bArr2 = new byte[512];
                                long jCurrentTimeMillis = System.currentTimeMillis() + 6000;
                                while (!atomicBoolean.get() && System.currentTimeMillis() < jCurrentTimeMillis) {
                                    DatagramPacket datagramPacket = new DatagramPacket(bArr2, i4);
                                    try {
                                        datagramSocket.receive(datagramPacket);
                                        String hostAddress = datagramPacket.getAddress().getHostAddress();
                                        byte[] data = datagramPacket.getData();
                                        String str = null;
                                        if (datagramPacket.getLength() > 56) {
                                            int i5 = data[56] & 255;
                                            for (int i6 = 57; i2 < i5 && i6 + 17 <= datagramPacket.getLength(); i6 += 18) {
                                                int i7 = data[i6 + 15] & 255;
                                                boolean z = (data[i6 + 16] & 128) != 0;
                                                if (i7 == 0 && !z) {
                                                    String strTrim = new String(data, i6, 15).trim();
                                                    if (!strTrim.isEmpty()) {
                                                        str = strTrim;
                                                    }
                                                }
                                                i2++;
                                            }
                                        }
                                        ve2Var2.c(hostAddress, str);
                                        i2 = 0;
                                        i4 = 512;
                                    } catch (IOException unused3) {
                                    }
                                    break;
                                }
                                datagramSocket.close();
                                return;
                            } catch (Throwable th) {
                                try {
                                    datagramSocket.close();
                                    throw th;
                                } catch (Throwable th2) {
                                    th.addSuppressed(th2);
                                    throw th;
                                }
                            }
                        } catch (Exception unused4) {
                            return;
                        }
                    case 1:
                        ve2Var2.getClass();
                        for (int[] iArr : ve2.e()) {
                            int i8 = iArr[0];
                            int i9 = iArr[2];
                            for (int i10 = iArr[1]; i10 <= i9; i10++) {
                                if (ve2Var2.e.get()) {
                                    return;
                                }
                                ve2Var2.g.execute(new lc(ve2Var2, i8 | i10, b5));
                            }
                        }
                        return;
                    default:
                        ve2Var2.d();
                        return;
                }
            }
        }, "smb-sweep").start();
        ve2Var.d.postDelayed(new Runnable() { // from class: re2
            @Override // java.lang.Runnable
            public final void run() {
                byte b4 = b3;
                byte b5 = 5;
                int i2 = 0;
                ve2 ve2Var2 = ve2Var;
                switch (b4) {
                    case DecoderInputBuffer.BUFFER_REPLACEMENT_MODE_DISABLED /* 0 */:
                        AtomicBoolean atomicBoolean = ve2Var2.e;
                        byte[] bArr = new byte[50];
                        bArr[0] = 82;
                        bArr[1] = 83;
                        bArr[2] = 0;
                        bArr[3] = 0;
                        bArr[5] = 1;
                        bArr[12] = 32;
                        bArr[13] = 67;
                        bArr[14] = 75;
                        for (int i3 = 15; i3 < 45; i3++) {
                            bArr[i3] = 65;
                        }
                        bArr[45] = 0;
                        bArr[47] = 33;
                        bArr[49] = 1;
                        try {
                            DatagramSocket datagramSocket = new DatagramSocket();
                            try {
                                datagramSocket.setBroadcast(true);
                                datagramSocket.setSoTimeout(700);
                                for (InetAddress inetAddress : ve2.a()) {
                                    if (atomicBoolean.get()) {
                                        datagramSocket.close();
                                        return;
                                    }
                                    try {
                                        datagramSocket.send(new DatagramPacket(bArr, 50, inetAddress, 137));
                                    } catch (IOException unused2) {
                                    }
                                }
                                int i4 = 512;
                                byte[] bArr2 = new byte[512];
                                long jCurrentTimeMillis = System.currentTimeMillis() + 6000;
                                while (!atomicBoolean.get() && System.currentTimeMillis() < jCurrentTimeMillis) {
                                    DatagramPacket datagramPacket = new DatagramPacket(bArr2, i4);
                                    try {
                                        datagramSocket.receive(datagramPacket);
                                        String hostAddress = datagramPacket.getAddress().getHostAddress();
                                        byte[] data = datagramPacket.getData();
                                        String str = null;
                                        if (datagramPacket.getLength() > 56) {
                                            int i5 = data[56] & 255;
                                            for (int i6 = 57; i2 < i5 && i6 + 17 <= datagramPacket.getLength(); i6 += 18) {
                                                int i7 = data[i6 + 15] & 255;
                                                boolean z = (data[i6 + 16] & 128) != 0;
                                                if (i7 == 0 && !z) {
                                                    String strTrim = new String(data, i6, 15).trim();
                                                    if (!strTrim.isEmpty()) {
                                                        str = strTrim;
                                                    }
                                                }
                                                i2++;
                                            }
                                        }
                                        ve2Var2.c(hostAddress, str);
                                        i2 = 0;
                                        i4 = 512;
                                    } catch (IOException unused3) {
                                    }
                                    break;
                                }
                                datagramSocket.close();
                                return;
                            } catch (Throwable th) {
                                try {
                                    datagramSocket.close();
                                    throw th;
                                } catch (Throwable th2) {
                                    th.addSuppressed(th2);
                                    throw th;
                                }
                            }
                        } catch (Exception unused4) {
                            return;
                        }
                    case 1:
                        ve2Var2.getClass();
                        for (int[] iArr : ve2.e()) {
                            int i8 = iArr[0];
                            int i9 = iArr[2];
                            for (int i10 = iArr[1]; i10 <= i9; i10++) {
                                if (ve2Var2.e.get()) {
                                    return;
                                }
                                ve2Var2.g.execute(new lc(ve2Var2, i8 | i10, b5));
                            }
                        }
                        return;
                    default:
                        ve2Var2.d();
                        return;
                }
            }
        }, 6000L);
        wl2 wl2Var = ij1Var.i;
        Handler handler = (Handler) wl2Var.n;
        ArrayList arrayListU = wl2.u();
        byte b4 = 21;
        if (arrayListU.isEmpty()) {
            handler.post(new i4(wl2Var, b4));
        } else {
            Iterator it2 = arrayListU.iterator();
            while (it2.hasNext()) {
                new Thread(new q7((Object) wl2Var, it2.next(), (byte) 13), "dlna-ssdp").start();
            }
            handler.postDelayed(new i4(wl2Var, b4), 6000L);
        }
        final ve2 ve2Var2 = ij1Var.j;
        NsdManager nsdManager2 = (NsdManager) ve2Var2.b.getSystemService("servicediscovery");
        ve2Var2.h = nsdManager2;
        if (nsdManager2 != null) {
            se2 se2Var2 = new se2(ve2Var2, b2);
            ve2Var2.i = se2Var2;
            try {
                nsdManager2.discoverServices("_torrserver._tcp", 1, se2Var2);
            } catch (Exception unused2) {
            }
        }
        new Thread(new Runnable() { // from class: hp2
            @Override // java.lang.Runnable
            public final void run() {
                byte b5 = b;
                ve2 ve2Var3 = ve2Var2;
                switch (b5) {
                    case DecoderInputBuffer.BUFFER_REPLACEMENT_MODE_DISABLED /* 0 */:
                        ve2Var3.getClass();
                        for (int[] iArr : ve2.e()) {
                            int i2 = iArr[0];
                            for (int i3 = iArr[1]; i3 <= iArr[2]; i3++) {
                                if (!ve2Var3.e.get()) {
                                    ve2Var3.g.execute(new lc(ve2Var3, i2 | i3, (byte) 6));
                                }
                                break;
                            }
                        }
                        break;
                    default:
                        ve2Var3.d();
                        break;
                }
            }
        }, "torr-sweep").start();
        ve2Var2.d.postDelayed(new Runnable() { // from class: hp2
            @Override // java.lang.Runnable
            public final void run() {
                byte b5 = b2;
                ve2 ve2Var3 = ve2Var2;
                switch (b5) {
                    case DecoderInputBuffer.BUFFER_REPLACEMENT_MODE_DISABLED /* 0 */:
                        ve2Var3.getClass();
                        for (int[] iArr : ve2.e()) {
                            int i2 = iArr[0];
                            for (int i3 = iArr[1]; i3 <= iArr[2]; i3++) {
                                if (!ve2Var3.e.get()) {
                                    ve2Var3.g.execute(new lc(ve2Var3, i2 | i3, (byte) 6));
                                }
                                break;
                            }
                        }
                        break;
                    default:
                        ve2Var3.d();
                        break;
                }
            }
        }, 6000L);
    }

    public final void z(boolean z) {
        int i;
        oi1 oi1Var = (oi1) findViewById(z ? R.id.browse_rail : R.id.browse_bar);
        oi1 oi1Var2 = this.M;
        if (oi1Var == oi1Var2) {
            return;
        }
        if (oi1Var2 != null) {
            oi1Var2.setOnItemSelectedListener(null);
            this.M.setVisibility(8);
        }
        this.M = oi1Var;
        oi1Var.setVisibility(0);
        oi1 oi1Var3 = this.M;
        int iU = jf2.u(this.E0);
        if (iU == 0) {
            i = R.id.dest_favorites;
        } else if (iU != 2) {
            i = iU != 3 ? R.id.dest_files : R.id.dest_iptv;
        } else {
            i = R.id.dest_network;
        }
        oi1Var3.setSelectedItemId(i);
        this.M.setOnItemSelectedListener(new dk(this));
    }
}
