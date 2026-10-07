package defpackage;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.media.audiofx.LoudnessEnhancer;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.Settings;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.media3.decoder.DecoderInputBuffer;
import androidx.media3.decoder.VideoDecoderOutputBuffer;
import com.brouken.player.BrowserActivity;
import com.brouken.player.ErrorActivity;
import com.brouken.player.PlayerActivity;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.button.MaterialButtonToggleGroup;
import com.justplus.player.R;
import j$.util.Objects;
import java.util.ArrayList;
import java.util.HashSet;

/* JADX INFO: compiled from: r8-map-id-0cee80150bab987b3edc91bcfc1e04786a1418881e5c92a3b999d44d61001263 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class xo1 implements Runnable {
    public final /* synthetic */ byte l;
    public final /* synthetic */ PlayerActivity m;

    public /* synthetic */ xo1(PlayerActivity playerActivity, byte b) {
        this.l = b;
        this.m = playerActivity;
    }

    @Override // java.lang.Runnable
    public final void run() {
        u81 u81Var;
        zl0 zl0Var;
        int i;
        byte b = 26;
        byte b2 = 10;
        byte b3 = 9;
        switch (this.l) {
            case DecoderInputBuffer.BUFFER_REPLACEMENT_MODE_DISABLED /* 0 */:
                PlayerActivity playerActivity = this.m;
                LoudnessEnhancer loudnessEnhancer = PlayerActivity.l6;
                playerActivity.p3(s2.a);
                break;
            case 1:
                PlayerActivity playerActivity2 = this.m;
                LoudnessEnhancer loudnessEnhancer2 = PlayerActivity.l6;
                playerActivity2.o3(playerActivity2.getString(R.string.notice_track_unsupported_hint), true, R.drawable.ic_memory_24dp);
                break;
            case 2:
                PlayerActivity playerActivity3 = this.m;
                LoudnessEnhancer loudnessEnhancer3 = PlayerActivity.l6;
                cz czVar = playerActivity3.B;
                if (czVar != null && !PlayerActivity.K6) {
                    yt2.k0(playerActivity3, czVar, false);
                    break;
                } else if (czVar != null && !playerActivity3.X1) {
                    yt2.k0(playerActivity3, czVar, true);
                    break;
                }
                break;
            case VideoDecoderOutputBuffer.COLORSPACE_BT2020 /* 3 */:
                PlayerActivity playerActivity4 = this.m;
                LoudnessEnhancer loudnessEnhancer4 = PlayerActivity.l6;
                playerActivity4.p3(s2.a);
                break;
            case 4:
                PlayerActivity playerActivity5 = this.m;
                if (PlayerActivity.n6 != null) {
                    Uri uri = playerActivity5.y4;
                    zl0 zl0Var2 = (zl0) playerActivity5.z4.a;
                    ArrayList arrayList = new ArrayList();
                    arrayList.add(new u70(playerActivity5.getString(R.string.subtitle_off), null, !playerActivity5.J2(), new ep1(playerActivity5, (byte) 7)));
                    for (jr1 jr1Var : PlayerActivity.K()) {
                        zl0 zl0Var3 = jr1Var.a;
                        String str = zl0Var3.p;
                        if (str != null && !ef1.m(str)) {
                            if (PlayerActivity.n6 != null && !zl0Var3.equals((zl0) playerActivity5.z4.a)) {
                                nw0 nw0VarListIterator = PlayerActivity.n6.E().a.listIterator(0);
                                while (true) {
                                    if (nw0VarListIterator.hasNext()) {
                                        oq2 oq2Var = (oq2) nw0VarListIterator.next();
                                        if (oq2Var.b.c == 3) {
                                            int i2 = 0;
                                            while (true) {
                                                if (i2 >= oq2Var.a) {
                                                    continue;
                                                } else if (!oq2Var.e[i2] || !oq2Var.a(i2).equals(zl0Var3)) {
                                                    i2++;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            if (jr1Var.e) {
                                String[] strArrL4 = playerActivity5.l4(zl0Var3, jr1Var.d);
                                arrayList.add(new u70(strArrL4[0], strArrL4[1], zl0Var3.equals(zl0Var2), new ld(playerActivity5, jr1Var, zl0Var3)));
                            } else {
                                arrayList.add(playerActivity5.n4(jr1Var));
                            }
                        }
                    }
                    HashSet hashSet = new HashSet();
                    z81 z81VarZ = PlayerActivity.n6.z();
                    if (z81VarZ != null && (u81Var = z81VarZ.b) != null) {
                        nw0 nw0VarListIterator2 = u81Var.g.listIterator(0);
                        while (nw0VarListIterator2.hasNext()) {
                            hashSet.add(((y81) nw0VarListIterator2.next()).a);
                        }
                    }
                    for (Uri uri2 : playerActivity5.w0()) {
                        if (uri2.equals(uri)) {
                            arrayList.add(new u70(playerActivity5.W3(uri2), null, true, new ap1(playerActivity5, uri2, (byte) 1)));
                        } else if (!uri2.equals(playerActivity5.H.e) && !uri2.equals(playerActivity5.O4)) {
                            if (!hashSet.contains(uri2)) {
                                arrayList.add(new u70(playerActivity5.W3(uri2), null, false, new ap1(playerActivity5, uri2, (byte) 2)));
                            }
                        }
                    }
                    arrayList.add(u70.a());
                    arrayList.add(new u70(R.drawable.ic_search_24dp, null, playerActivity5.getString(R.string.subtitle_search_manual), null, false, new ep1(playerActivity5, (byte) 8)));
                    s2.q(playerActivity5, playerActivity5.Z1, new ep1(playerActivity5, (byte) 9), playerActivity5.getString(R.string.subtitle_secondary_title), arrayList);
                    break;
                }
                break;
            case 5:
                PlayerActivity playerActivity6 = this.m;
                LoudnessEnhancer loudnessEnhancer5 = PlayerActivity.l6;
                playerActivity6.k0();
                playerActivity6.t3 = xp2.f;
                playerActivity6.Q4(false);
                break;
            case 6:
                PlayerActivity playerActivity7 = this.m;
                LoudnessEnhancer loudnessEnhancer6 = PlayerActivity.l6;
                playerActivity7.I4 = null;
                playerActivity7.B1(false);
                break;
            case 7:
                PlayerActivity playerActivity8 = this.m;
                LoudnessEnhancer loudnessEnhancer7 = PlayerActivity.l6;
                playerActivity8.p3(s2.a);
                break;
            case 8:
                PlayerActivity playerActivity9 = this.m;
                LoudnessEnhancer loudnessEnhancer8 = PlayerActivity.l6;
                Button button = playerActivity9.i5;
                if (button != null && playerActivity9.j6 != 1 && playerActivity9.u5) {
                    button.setClickable(true);
                    playerActivity9.i5.setFocusable(true);
                    break;
                }
                break;
            case 9:
                PlayerActivity playerActivity10 = this.m;
                LoudnessEnhancer loudnessEnhancer9 = PlayerActivity.l6;
                oz1.e(playerActivity10.H.I0);
                playerActivity10.z3(playerActivity10.getString(R.string.together_searching), null, null);
                ro2.g(new yo1(playerActivity10, (byte) 3));
                break;
            case 10:
                PlayerActivity playerActivity11 = this.m;
                LinearLayout linearLayoutF = s2.f(s2.e(playerActivity11));
                EditText editTextF = s2.F(linearLayoutF, playerActivity11.getString(R.string.together_code), "ABC234");
                editTextF.setInputType(4097);
                s2.h(playerActivity11, playerActivity11.getString(R.string.together_join), linearLayoutF, playerActivity11.getString(android.R.string.ok), new ld(playerActivity11, editTextF, s2.F(linearLayoutF, playerActivity11.getString(R.string.together_password), null), b));
                break;
            case 11:
                PlayerActivity playerActivity12 = this.m;
                LoudnessEnhancer loudnessEnhancer10 = PlayerActivity.l6;
                playerActivity12.p3(s2.a);
                break;
            case 12:
                PlayerActivity playerActivity13 = this.m;
                LoudnessEnhancer loudnessEnhancer11 = PlayerActivity.l6;
                try {
                    if (Settings.System.getInt(playerActivity13.getContentResolver(), "accelerometer_rotation") == 0) {
                        Settings.System.putInt(playerActivity13.getContentResolver(), "accelerometer_rotation", 1);
                        playerActivity13.C2 = true;
                        hu1 hu1Var = playerActivity13.H;
                        hu1Var.t = true;
                        SharedPreferences.Editor editorEdit = hu1Var.b.edit();
                        editorEdit.putBoolean("restoreAutoRotate", true);
                        editorEdit.commit();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                String[] strArr = yt2.a;
                int i3 = Build.VERSION.SDK_INT;
                Uri uriBuildDocumentUri = i3 >= 26 ? DocumentsContract.buildDocumentUri("com.android.externalstorage.documents", "primary:" + Environment.DIRECTORY_MOVIES) : null;
                Intent intent = new Intent("android.intent.action.OPEN_DOCUMENT_TREE");
                intent.putExtra("android.content.extra.SHOW_ADVANCED", true);
                if (i3 >= 26 && uriBuildDocumentUri != null) {
                    intent.putExtra("android.provider.extra.INITIAL_URI", uriBuildDocumentUri);
                }
                if (intent.resolveActivity(playerActivity13.getPackageManager()) == null) {
                    playerActivity13.z3(playerActivity13.getText(R.string.error_files_missing).toString(), intent.toString(), null);
                } else {
                    playerActivity13.startActivityForResult(intent, 10);
                }
                break;
            case 13:
                PlayerActivity playerActivity14 = this.m;
                LoudnessEnhancer loudnessEnhancer12 = PlayerActivity.l6;
                hu1 hu1Var2 = playerActivity14.H;
                hu1Var2.s = false;
                SharedPreferences.Editor editorEdit2 = hu1Var2.b.edit();
                editorEdit2.putBoolean("askScope", false);
                editorEdit2.apply();
                Uri uriY0 = playerActivity14.y0();
                playerActivity14.d3 = uriY0;
                if (uriY0 != null) {
                    playerActivity14.F3();
                }
                break;
            case 14:
                PlayerActivity playerActivity15 = this.m;
                LoudnessEnhancer loudnessEnhancer13 = PlayerActivity.l6;
                playerActivity15.h3();
                break;
            case 15:
                PlayerActivity playerActivity16 = this.m;
                LoudnessEnhancer loudnessEnhancer14 = PlayerActivity.l6;
                playerActivity16.s0();
                break;
            case 16:
                this.m.X0();
                break;
            case 17:
                PlayerActivity playerActivity17 = this.m;
                LoudnessEnhancer loudnessEnhancer15 = PlayerActivity.l6;
                playerActivity17.I4 = null;
                playerActivity17.B1(false);
                break;
            case 18:
                PlayerActivity playerActivity18 = this.m;
                LoudnessEnhancer loudnessEnhancer16 = PlayerActivity.l6;
                ArrayList arrayList2 = new ArrayList();
                ro2 ro2Var = playerActivity18.a5;
                if (ro2Var == null || !ro2Var.j()) {
                    arrayList2.add(new u70(R.drawable.ic_add_24dp, null, playerActivity18.getString(R.string.together_create), null, false, new ep1(playerActivity18, (byte) 12)));
                    arrayList2.add(new u70(R.drawable.ic_search_24dp, null, playerActivity18.getString(R.string.together_find), null, false, new xo1(playerActivity18, b3)));
                    arrayList2.add(new u70(R.drawable.ic_link_24dp, null, playerActivity18.getString(R.string.together_enter_code), null, false, new xo1(playerActivity18, b2)));
                } else {
                    arrayList2.add(new u70(R.drawable.ic_share_24dp, null, playerActivity18.getString(R.string.together_share), playerActivity18.a5.f(), false, new ep1(playerActivity18, (byte) 10)));
                    arrayList2.add(new u70(R.drawable.ic_close_24dp, null, playerActivity18.getString(R.string.together_leave), null, false, new ep1(playerActivity18, (byte) 11)));
                }
                s2.q(playerActivity18, playerActivity18.Z1, new ep1(playerActivity18, (byte) 13), playerActivity18.getString(R.string.together_title), arrayList2);
                break;
            case 19:
                PlayerActivity playerActivity19 = this.m;
                LoudnessEnhancer loudnessEnhancer17 = PlayerActivity.l6;
                StringBuilder sb = new StringBuilder();
                playerActivity19.g(sb);
                String strTrim = sb.toString().trim();
                if (!strTrim.isEmpty()) {
                    vg0 vg0Var = PlayerActivity.n6;
                    if (vg0Var == null) {
                        zl0Var = null;
                    } else {
                        vg0Var.A1();
                        zl0Var = vg0Var.V;
                    }
                    String string = playerActivity19.getString(R.string.stats_report_title);
                    String strC = zl0Var == null ? null : zl0.c(zl0Var);
                    int i4 = ErrorActivity.P;
                    playerActivity19.startActivity(new Intent(playerActivity19, (Class<?>) ErrorActivity.class).putExtra("title", string).putExtra("summary", strC).putExtra("report", strTrim));
                    break;
                }
                break;
            case 20:
                PlayerActivity playerActivity20 = this.m;
                LoudnessEnhancer loudnessEnhancer18 = PlayerActivity.l6;
                int i5 = BrowserActivity.F0;
                playerActivity20.startActivity(new Intent(playerActivity20, (Class<?>) BrowserActivity.class));
                break;
            case 21:
                PlayerActivity playerActivity21 = this.m;
                LoudnessEnhancer loudnessEnhancer19 = PlayerActivity.l6;
                sj.a(playerActivity21, new yo1(playerActivity21, (byte) 2));
                break;
            case 22:
                this.m.A1(null);
                break;
            case 23:
                PlayerActivity playerActivity22 = this.m;
                LoudnessEnhancer loudnessEnhancer20 = PlayerActivity.l6;
                playerActivity22.p3(s2.a);
                break;
            case 24:
                PlayerActivity playerActivity23 = this.m;
                LoudnessEnhancer loudnessEnhancer21 = PlayerActivity.l6;
                playerActivity23.s3();
                break;
            case 25:
                PlayerActivity playerActivity24 = this.m;
                LoudnessEnhancer loudnessEnhancer22 = PlayerActivity.l6;
                playerActivity24.i3();
                break;
            case 26:
                this.m.H0();
                break;
            case 27:
                float[] fArr = gj0.x;
                PlayerActivity playerActivity25 = this.m;
                if (PlayerActivity.n6 != null) {
                    Dialog dialog = playerActivity25.T1;
                    if (dialog != null) {
                        dialog.dismiss();
                    }
                    ss2 ss2Var = playerActivity25.Z1;
                    String string2 = playerActivity25.getString(R.string.speed_title);
                    float fM4 = playerActivity25.M4();
                    yo1 yo1Var = new yo1(playerActivity25, (byte) 5);
                    final float[] fArr2 = {gj0.A(fM4)};
                    int iF = (ss2Var.f(playerActivity25.getResources().getConfiguration()) - s2.r(playerActivity25, ss2Var)) - (yt2.p(24) * 2);
                    ContextThemeWrapper contextThemeWrapperE = s2.e(playerActivity25);
                    int iN = sj.n(contextThemeWrapperE, R.attr.colorOnSurface, -1);
                    final int iN2 = sj.n(contextThemeWrapperE, R.attr.colorPrimary, contextThemeWrapperE.getColor(R.color.brand_accent));
                    LinearLayout linearLayoutC = lf2.c(contextThemeWrapperE, 1);
                    TextView textView = new TextView(contextThemeWrapperE);
                    textView.setText(string2);
                    textView.setTextColor(iN);
                    textView.setTextSize(2, ss2Var.y());
                    textView.setTypeface(Typeface.create("sans-serif-medium", 0));
                    textView.setMinHeight(ss2Var.a(48.0f));
                    textView.setGravity(16);
                    linearLayoutC.addView(s2.s(contextThemeWrapperE, ss2Var, textView, null));
                    TextView textView2 = new TextView(contextThemeWrapperE);
                    textView2.setTextSize(2, ss2Var.q(40.0f, 44.0f, 46.0f, 48.0f));
                    textView2.setTypeface(Typeface.create("sans-serif-light", 0));
                    textView2.setFontFeatureSettings("tnum");
                    textView2.setGravity(17);
                    textView2.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
                    final MaterialButton materialButtonN = gj0.n(contextThemeWrapperE, ss2Var, R.drawable.ic_remove_24dp, playerActivity25.getString(R.string.speed_slower));
                    final MaterialButton materialButtonN2 = gj0.n(contextThemeWrapperE, ss2Var, R.drawable.ic_add_24dp, playerActivity25.getString(R.string.speed_faster));
                    LinearLayout linearLayout = new LinearLayout(contextThemeWrapperE);
                    linearLayout.setOrientation(0);
                    linearLayout.setGravity(16);
                    linearLayout.addView(materialButtonN);
                    linearLayout.addView(textView2);
                    linearLayout.addView(materialButtonN2);
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(Math.min(iF, ss2Var.b(280.0f)), -2);
                    layoutParams.gravity = 1;
                    layoutParams.topMargin = yt2.p(8);
                    linearLayout.setLayoutParams(layoutParams);
                    linearLayoutC.addView(linearLayout);
                    final MaterialButton[] materialButtonArr = new MaterialButton[5];
                    final MaterialButtonToggleGroup materialButtonToggleGroup = new MaterialButtonToggleGroup(contextThemeWrapperE, null);
                    materialButtonToggleGroup.setSingleSelection(true);
                    int i6 = 0;
                    while (i6 < 5) {
                        MaterialButton materialButtonR = yt2.R(contextThemeWrapperE, ss2Var, gj0.k(fArr[i6]));
                        materialButtonArr[i6] = materialButtonR;
                        materialButtonToggleGroup.addView(materialButtonR, new LinearLayout.LayoutParams(0, -2, 1.0f));
                        i6++;
                        textView2 = textView2;
                        iN = iN;
                        fArr = fArr;
                    }
                    float[] fArr3 = fArr;
                    final int i7 = iN;
                    final TextView textView3 = textView2;
                    LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, -2);
                    layoutParams2.topMargin = yt2.p(16);
                    materialButtonToggleGroup.setLayoutParams(layoutParams2);
                    linearLayoutC.addView(materialButtonToggleGroup);
                    final MaterialButton materialButton = new MaterialButton(contextThemeWrapperE, null, R.attr.materialButtonOutlinedStyle);
                    materialButton.setText(playerActivity25.getString(R.string.skip_offset_reset));
                    materialButton.setTextSize(2, ss2Var.r());
                    materialButton.setInsetTop(0);
                    materialButton.setInsetBottom(0);
                    materialButton.setMinHeight(ss2Var.b(48.0f));
                    materialButton.setTextColor(dz0.v(materialButton.getContext(), R.color.dialog_button_dismissive));
                    yt2.r(materialButton);
                    LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(-2, -2);
                    layoutParams3.gravity = 1;
                    layoutParams3.topMargin = yt2.p(16);
                    materialButton.setLayoutParams(layoutParams3);
                    linearLayoutC.addView(materialButton);
                    byte b4 = 5;
                    final kq1 kq1Var = new kq1(runnableArr, yo1Var, fArr2, b4);
                    Runnable[] runnableArr = {new Runnable() { // from class: fg2
                        @Override // java.lang.Runnable
                        public final void run() {
                            float[] fArr4 = fArr2;
                            int i8 = 0;
                            String strK = gj0.k(fArr4[0]);
                            TextView textView4 = textView3;
                            textView4.setText(strK);
                            textView4.setTextColor(Math.abs(fArr4[0] - 1.0f) < 0.001f ? i7 : iN2);
                            materialButtonN.setEnabled(fArr4[0] > 0.251f);
                            materialButtonN2.setEnabled(fArr4[0] < 3.999f);
                            materialButton.setEnabled(Math.abs(fArr4[0] - 1.0f) >= 0.001f);
                            float f = fArr4[0];
                            while (true) {
                                float[] fArr5 = gj0.x;
                                if (i8 >= 5) {
                                    i8 = -1;
                                    break;
                                } else if (Math.abs(fArr5[i8] - f) < 0.001f) {
                                    break;
                                } else {
                                    i8++;
                                }
                            }
                            MaterialButtonToggleGroup materialButtonToggleGroup2 = materialButtonToggleGroup;
                            if (i8 >= 0) {
                                materialButtonToggleGroup2.l(materialButtonArr[i8].getId(), true);
                            } else {
                                materialButtonToggleGroup2.m(new HashSet());
                            }
                        }
                    }};
                    for (int i8 = 0; i8 < 5; i8++) {
                        final float f = fArr3[i8];
                        materialButtonArr[i8].setOnClickListener(new View.OnClickListener() { // from class: gg2
                            @Override // android.view.View.OnClickListener
                            public final void onClick(View view) {
                                fArr2[0] = f;
                                kq1Var.run();
                            }
                        });
                    }
                    materialButton.setOnClickListener(new xk(fArr2, kq1Var, b4));
                    final hg2 hg2Var = new hg2(fArr2, kq1Var, (byte) 0);
                    final hg2 hg2Var2 = new hg2(fArr2, kq1Var, (byte) 1);
                    materialButtonN.setOnClickListener(new zs(hg2Var, (byte) 16));
                    materialButtonN2.setOnClickListener(new zs(hg2Var2, (byte) 17));
                    final Runnable[] runnableArr2 = new Runnable[1];
                    byte b5 = 6;
                    runnableArr2[0] = new kq1(materialButtonN, hg2Var, runnableArr2, b5);
                    materialButtonN.setOnLongClickListener(new View.OnLongClickListener() { // from class: ig2
                        @Override // android.view.View.OnLongClickListener
                        public final boolean onLongClick(View view) {
                            hg2Var.run();
                            materialButtonN.postDelayed(runnableArr2[0], 80L);
                            return true;
                        }
                    });
                    final Runnable[] runnableArr3 = new Runnable[1];
                    runnableArr3[0] = new kq1(materialButtonN2, hg2Var2, runnableArr3, b5);
                    materialButtonN2.setOnLongClickListener(new View.OnLongClickListener() { // from class: ig2
                        @Override // android.view.View.OnLongClickListener
                        public final boolean onLongClick(View view) {
                            hg2Var2.run();
                            materialButtonN2.postDelayed(runnableArr3[0], 80L);
                            return true;
                        }
                    });
                    runnableArr[0].run();
                    ScrollView scrollView = new ScrollView(contextThemeWrapperE);
                    scrollView.addView(linearLayoutC, new ViewGroup.LayoutParams(-1, -2));
                    scrollView.setPadding(yt2.p(24), yt2.p(16), yt2.p(24), yt2.p(20));
                    Dialog dialog2 = new Dialog(playerActivity25, android.R.style.Theme.Translucent.NoTitleBar);
                    s2.v(playerActivity25, ss2Var, dialog2, scrollView, false);
                    dialog2.setTitle(string2);
                    if (ss2Var.a == 4) {
                        float f2 = fArr2[0];
                        int i9 = 0;
                        while (true) {
                            if (i9 >= 5) {
                                i = -1;
                            } else if (Math.abs(fArr3[i9] - f2) < 0.001f) {
                                i = i9;
                            } else {
                                i9++;
                            }
                        }
                        MaterialButton materialButton2 = materialButtonArr[Math.max(i, 0)];
                        Objects.requireNonNull(materialButton2);
                        materialButton2.post(new ob0(materialButton2, (byte) 0));
                    }
                    playerActivity25.T1 = dialog2;
                    playerActivity25.p3(dialog2);
                    break;
                }
                break;
            case 28:
                PlayerActivity playerActivity26 = this.m;
                LoudnessEnhancer loudnessEnhancer23 = PlayerActivity.l6;
                Dialog dialog3 = playerActivity26.Q1;
                if (dialog3 != null) {
                    dialog3.dismiss();
                }
                Dialog dialogI = ag.i(playerActivity26, playerActivity26.Z1, playerActivity26.getString(R.string.sound_session_title), 0.0d, 1.0d, null, new p40[]{new p40(playerActivity26.getString(R.string.pref_dynamic_range), playerActivity26.getString(R.string.pref_dynamic_range_on), playerActivity26.getString(R.string.pref_dynamic_range_off), playerActivity26.o0(), playerActivity26.H.P, new yo1(playerActivity26, (byte) 8)), new p40(playerActivity26.getString(R.string.pref_centre_boost), playerActivity26.getString(R.string.pref_centre_boost_on), playerActivity26.getString(R.string.pref_centre_boost_off), playerActivity26.R(), playerActivity26.H.O, new yo1(playerActivity26, (byte) 9))}, new wk1[0]);
                playerActivity26.Q1 = dialogI;
                playerActivity26.p3(dialogI);
                break;
            default:
                PlayerActivity playerActivity27 = this.m;
                if (PlayerActivity.n6 != null) {
                    Dialog dialog4 = playerActivity27.R1;
                    if (dialog4 != null) {
                        dialog4.dismiss();
                    }
                    ArrayList arrayList3 = new ArrayList();
                    boolean zL2 = playerActivity27.L2();
                    if (playerActivity27.p1() || playerActivity27.O4 != null) {
                        arrayList3.add(new wk1(zL2 ? playerActivity27.getString(R.string.subtitle_main_title) : null, playerActivity27.t4, new yo1(playerActivity27, (byte) 6)));
                    }
                    if (playerActivity27.u4 != null && playerActivity27.J2()) {
                        arrayList3.add(new wk1(zL2 ? playerActivity27.getString(R.string.subtitle_secondary_title) : null, playerActivity27.v4, new yo1(playerActivity27, (byte) 7)));
                    }
                    if (!arrayList3.isEmpty()) {
                        Dialog dialogI2 = ag.i(playerActivity27, playerActivity27.Z1, playerActivity27.getString(R.string.subtitle_offset_title), 180.0d, 0.25d, null, null, (wk1[]) arrayList3.toArray(new wk1[0]));
                        playerActivity27.R1 = dialogI2;
                        playerActivity27.p3(dialogI2);
                        break;
                    }
                }
                break;
        }
    }
}
