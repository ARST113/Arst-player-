package com.brouken.player;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.view.ContextThemeWrapper;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import androidx.media3.decoder.DecoderInputBuffer;
import androidx.media3.decoder.ffmpeg.FfmpegLibrary;
import androidx.preference.DialogPreference;
import androidx.preference.EditTextPreference;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceCategory;
import androidx.preference.PreferenceGroup;
import androidx.preference.PreferenceScreen;
import androidx.preference.SwitchPreferenceCompat;
import androidx.preference.TwoStatePreference;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.brouken.player.SettingsActivity;
import com.brouken.player.e;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.card.MaterialCardView;
import com.justplus.player.R;
import defpackage.al2;
import defpackage.aw1;
import defpackage.bl;
import defpackage.bu1;
import defpackage.bw2;
import defpackage.ck0;
import defpackage.cn0;
import defpackage.f03;
import defpackage.fk;
import defpackage.g03;
import defpackage.g20;
import defpackage.g31;
import defpackage.g5;
import defpackage.g7;
import defpackage.gu1;
import defpackage.h03;
import defpackage.h70;
import defpackage.hj1;
import defpackage.j03;
import defpackage.j40;
import defpackage.jf2;
import defpackage.jw2;
import defpackage.l5;
import defpackage.lb2;
import defpackage.lj1;
import defpackage.nf1;
import defpackage.nk;
import defpackage.nt2;
import defpackage.ob2;
import defpackage.p12;
import defpackage.pb2;
import defpackage.pt1;
import defpackage.qb2;
import defpackage.rb2;
import defpackage.rm0;
import defpackage.rx1;
import defpackage.s2;
import defpackage.t7;
import defpackage.ub1;
import defpackage.ul1;
import defpackage.ut1;
import defpackage.vb2;
import defpackage.vt1;
import defpackage.wt1;
import defpackage.wt2;
import defpackage.xf;
import defpackage.xx1;
import defpackage.y61;
import defpackage.zt1;
import defpackage.zv2;
import defpackage.zx1;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.WeakHashMap;

/* JADX INFO: compiled from: r8-map-id-d5d7d661e67c62f2588b5a5666a7a20281e417b1121a202706e8fa77676a2bdd */
/* JADX INFO: loaded from: classes.dex */
public class SettingsActivity extends g7 implements vt1 {
    public static RecyclerView M;
    public String I;
    public a J;
    public int K;
    public int L;

    /* JADX INFO: compiled from: r8-map-id-d5d7d661e67c62f2588b5a5666a7a20281e417b1121a202706e8fa77676a2bdd */
    public static class a extends wt1 {
        public static final String[] q0 = {"languageSubtitleSecondary", "subtitleSecondaryPreview", "subtitleSecondaryScale", "subtitleSecondaryTextColor", "subtitleSecondaryBackground"};
        public boolean o0;
        public int n0 = -1;
        public final vb2 p0 = new SharedPreferences.OnSharedPreferenceChangeListener() { // from class: vb2
            @Override // android.content.SharedPreferences.OnSharedPreferenceChangeListener
            public final void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String str) {
                RecyclerView recyclerView = this.a.h0;
                rx1 adapter = recyclerView == null ? null : recyclerView.getAdapter();
                if (adapter instanceof zt1) {
                    String[] strArr = {"subtitlePreview", "subtitleSecondaryPreview"};
                    for (int i = 0; i < 2; i++) {
                        int iK = ((zt1) adapter).k(strArr[i]);
                        if (iK != -1) {
                            adapter.a.c(iK, 1, null);
                        }
                    }
                }
            }
        };

        public static void V(TextView textView, CharSequence charSequence, CharSequence charSequence2) {
            if (textView.getParent() instanceof View) {
                View view = (View) textView.getParent();
                int width = (((view.getWidth() - view.getPaddingLeft()) - view.getPaddingRight()) - textView.getPaddingLeft()) - textView.getPaddingRight();
                if (width <= 0) {
                    return;
                }
                TextPaint paint = textView.getPaint();
                Layout.Alignment alignment = Layout.Alignment.ALIGN_NORMAL;
                int iMax = Math.max(new StaticLayout(charSequence, paint, width, alignment, textView.getLineSpacingMultiplier(), textView.getLineSpacingExtra(), false).getLineCount(), new StaticLayout(charSequence2, textView.getPaint(), width, alignment, textView.getLineSpacingMultiplier(), textView.getLineSpacingExtra(), false).getLineCount());
                if (textView.getMinLines() != iMax) {
                    textView.setMinLines(iMax);
                }
            }
        }

        public static String b0() {
            StringBuilder sb = new StringBuilder("Android: ");
            sb.append(Build.VERSION.RELEASE);
            sb.append(" (API ");
            sb.append(Build.VERSION.SDK_INT);
            sb.append(")\nDevice: ");
            sb.append(Build.MANUFACTURER);
            sb.append(' ');
            sb.append(Build.MODEL);
            sb.append(" (");
            sb.append(Build.DEVICE);
            sb.append(")\nABI: ");
            String[] strArr = Build.SUPPORTED_ABIS;
            sb.append(strArr.length > 0 ? strArr[0] : "?");
            sb.append("\nMedia3: 1.11.1");
            if (FfmpegLibrary.a.isAvailable()) {
                sb.append("\nFFmpeg: ");
                sb.append(FfmpegLibrary.c());
            }
            return sb.toString();
        }

        public static boolean c0(PreferenceGroup preferenceGroup) {
            for (int i = 0; i < preferenceGroup.Z.size(); i++) {
                Preference preferenceD = preferenceGroup.D(i);
                if (preferenceD instanceof PreferenceGroup) {
                    if (c0((PreferenceGroup) preferenceD)) {
                        return true;
                    }
                } else if ("appLanguage".equals(preferenceD.w)) {
                    return true;
                }
            }
            return false;
        }

        public static void g0(MaterialCardView materialCardView, int i, TypedArray typedArray, int i2, int i3) {
            materialCardView.findViewById(i).setBackgroundTintList(ColorStateList.valueOf(typedArray.getColor(i2, i3)));
        }

        public static void i0(Preference preference, LinkedHashMap linkedHashMap, String str, int i) {
            ArrayList<String> arrayListD0 = wt2.d0(str);
            if (arrayListD0.isEmpty()) {
                preference.w(preference.l.getString(i));
                return;
            }
            ArrayList arrayList = new ArrayList();
            for (String str2 : arrayListD0) {
                String str3 = (String) linkedHashMap.get(str2);
                if (str3 != null) {
                    str2 = str3;
                }
                arrayList.add(str2);
            }
            preference.w(TextUtils.join(", ", arrayList));
        }

        @Override // defpackage.pm0
        public final void A(int i, String[] strArr, int[] iArr) {
            boolean zShouldShowRequestPermissionRationale;
            Context contextI = i();
            if (i != 1 || contextI == null) {
                return;
            }
            if (!wt2.h(contextI)) {
                rm0 rm0Var = this.D;
                boolean zShouldShowRequestPermissionRationale2 = false;
                if (rm0Var != null) {
                    g7 g7Var = rm0Var.w;
                    int i2 = Build.VERSION.SDK_INT;
                    if (i2 >= 33 || !TextUtils.equals("android.permission.POST_NOTIFICATIONS", "android.permission.READ_EXTERNAL_STORAGE")) {
                        if (i2 < 32 && i2 == 31) {
                            try {
                                zShouldShowRequestPermissionRationale = ((Boolean) PackageManager.class.getMethod("shouldShowRequestPermissionRationale", String.class).invoke(g7Var.getApplication().getPackageManager(), "android.permission.READ_EXTERNAL_STORAGE")).booleanValue();
                            } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException unused) {
                                zShouldShowRequestPermissionRationale = g7Var.shouldShowRequestPermissionRationale("android.permission.READ_EXTERNAL_STORAGE");
                            }
                            zShouldShowRequestPermissionRationale2 = zShouldShowRequestPermissionRationale;
                        } else {
                            zShouldShowRequestPermissionRationale2 = g7Var.shouldShowRequestPermissionRationale("android.permission.READ_EXTERNAL_STORAGE");
                        }
                    }
                }
                if (!zShouldShowRequestPermissionRationale2) {
                    e0(contextI);
                    return;
                }
            }
            W();
        }

        @Override // defpackage.wt1, defpackage.pm0
        public final void C() {
            int intExtra;
            super.C();
            g7 g7VarG = g();
            if (g7VarG != null && g7VarG.getIntent() != null && (intExtra = g7VarG.getIntent().getIntExtra("say", 0)) != 0) {
                g7VarG.getIntent().removeExtra("say");
                RecyclerView recyclerView = SettingsActivity.M;
                y61.O(g7VarG, intExtra, false, R.drawable.ic_settings_24dp);
            }
            W();
            SharedPreferences sharedPreferencesD = this.g0.d();
            if (sharedPreferencesD != null) {
                sharedPreferencesD.registerOnSharedPreferenceChangeListener(this.p0);
            }
        }

        @Override // defpackage.wt1, defpackage.pm0
        public final void D() {
            SharedPreferences sharedPreferencesD = this.g0.d();
            if (sharedPreferencesD != null) {
                sharedPreferencesD.unregisterOnSharedPreferenceChangeListener(this.p0);
            }
            super.D();
        }

        @Override // defpackage.wt1, defpackage.pm0
        public final void E(View view, Bundle bundle) {
            rx1 adapter;
            PreferenceScreen preferenceScreen;
            super.E(view, bundle);
            Bundle bundle2 = this.q;
            if (bundle2 != null && bundle2.getString("androidx.preference.PreferenceFragmentCompat.PREFERENCE_ROOT") != null && (preferenceScreen = this.g0.g) != null && preferenceScreen.s != null) {
                H().setTitle(this.g0.g.s);
            }
            final RecyclerView recyclerView = this.h0;
            if (recyclerView != null) {
                ut1 ut1Var = this.f0;
                ut1Var.b = 0;
                ut1Var.a = null;
                RecyclerView recyclerView2 = ut1Var.d.h0;
                if (recyclerView2.A.size() != 0) {
                    zx1 zx1Var = recyclerView2.y;
                    if (zx1Var != null) {
                        zx1Var.c("Cannot invalidate item decorations during a scroll or layout");
                    }
                    recyclerView2.R();
                    recyclerView2.requestLayout();
                }
                ut1Var.b = 0;
                RecyclerView recyclerView3 = ut1Var.d.h0;
                if (recyclerView3.A.size() != 0) {
                    zx1 zx1Var2 = recyclerView3.y;
                    if (zx1Var2 != null) {
                        zx1Var2.c("Cannot invalidate item decorations during a scroll or layout");
                    }
                    recyclerView3.R();
                    recyclerView3.requestLayout();
                }
                recyclerView.i(new ob2(recyclerView.getContext()));
                recyclerView.getViewTreeObserver().addOnGlobalFocusChangeListener(new ViewTreeObserver.OnGlobalFocusChangeListener() { // from class: ac2
                    @Override // android.view.ViewTreeObserver.OnGlobalFocusChangeListener
                    public final void onGlobalFocusChanged(View view2, View view3) {
                        recyclerView.invalidate();
                    }
                });
                if (wt2.F(recyclerView.getContext())) {
                    ((SettingsActivity) H()).J = this;
                }
                if (recyclerView.getItemAnimator() instanceof j40) {
                    ((j40) recyclerView.getItemAnimator()).g = false;
                }
                recyclerView.post(new lj1((Object) recyclerView, (byte) 10));
            }
            SettingsActivity.M = this.h0;
            int iP = wt2.F(I()) ? wt2.p(48) : 0;
            SettingsActivity.M.setClipToPadding(false);
            RecyclerView recyclerView4 = SettingsActivity.M;
            g20 g20Var = new g20(iP, (byte) 8);
            WeakHashMap weakHashMap = jw2.a;
            bw2.j(recyclerView4, g20Var);
            zv2.c(SettingsActivity.M);
            if (this.q != null) {
                if (bundle == null || h0()) {
                    f0(0, 3);
                    return;
                }
                return;
            }
            SettingsActivity settingsActivity = (SettingsActivity) H();
            String str = settingsActivity.I;
            settingsActivity.I = null;
            if (h0()) {
                f0(0, 3);
                return;
            }
            if (str != null) {
                RecyclerView recyclerView5 = this.h0;
                adapter = recyclerView5 != null ? recyclerView5.getAdapter() : null;
                if (adapter instanceof zt1) {
                    f0(((zt1) adapter).k(str), 3);
                    return;
                }
                return;
            }
            if (bundle == null) {
                String stringExtra = settingsActivity.getIntent().getStringExtra("scrollTo");
                Preference preferenceO = stringExtra == null ? null : O(stringExtra);
                if (preferenceO instanceof PreferenceScreen) {
                    view.post(new hj1(this, settingsActivity, (PreferenceScreen) preferenceO));
                    return;
                }
                if (stringExtra == null) {
                    if (wt2.F(settingsActivity)) {
                        f0(0, 3);
                    }
                } else {
                    RecyclerView recyclerView6 = this.h0;
                    adapter = recyclerView6 != null ? recyclerView6.getAdapter() : null;
                    if (adapter instanceof zt1) {
                        f0(((zt1) adapter).k(stringExtra), 3);
                    }
                }
            }
        }

        @Override // defpackage.wt1
        public final rx1 P(PreferenceScreen preferenceScreen) {
            return new c(this, preferenceScreen);
        }

        @Override // defpackage.wt1
        public final zx1 Q() {
            return new d(1);
        }

        @Override // defpackage.wt1
        public final void R(String str) {
            PreferenceScreen preferenceScreen;
            CharSequence charSequenceD;
            Preference preferenceC;
            gu1.c(I());
            gu1.d(I());
            gu1.j(I());
            gu1.k(I());
            bu1 bu1Var = this.g0;
            if (bu1Var == null) {
                throw new RuntimeException("This should be called after super.onCreate.");
            }
            PreferenceScreen preferenceScreenE = bu1Var.e(I());
            if (str != null) {
                preferenceC = preferenceScreenE.C(str);
                if (!(preferenceC instanceof PreferenceScreen)) {
                    preferenceScreen = preferenceScreenE;
                    preferenceScreen = preferenceC;
                    bl.d(jf2.f("Preference object with key ", str, " is not a PreferenceScreen"));
                    return;
                }
            }
            preferenceScreen = preferenceScreenE;
            preferenceScreen = preferenceC;
            preferenceScreen = preferenceScreenE;
            PreferenceScreen preferenceScreen2 = preferenceScreen;
            bu1 bu1Var2 = this.g0;
            PreferenceScreen preferenceScreen3 = bu1Var2.g;
            final byte b = 1;
            if (preferenceScreen2 != preferenceScreen3) {
                if (preferenceScreen3 != null) {
                    preferenceScreen3.n();
                }
                bu1Var2.g = preferenceScreen2;
                this.i0 = true;
                if (this.j0) {
                    g5 g5Var = this.l0;
                    if (!g5Var.hasMessages(1)) {
                        g5Var.obtainMessage(1).sendToTarget();
                    }
                }
            }
            W();
            Preference preferenceO = O("appLanguage");
            final byte b2 = 0;
            if (preferenceO != null) {
                Context contextI = I();
                g31 g31VarB = t7.b();
                if (g31VarB.a.isEmpty()) {
                    charSequenceD = contextI.getString(R.string.pref_app_language_system);
                } else {
                    Locale locale = g31VarB.a.get(0);
                    charSequenceD = nt2.d(locale.getDisplayName(locale));
                }
                preferenceO.w(charSequenceD);
                preferenceO.q = new pb2(this, b2);
            }
            Preference preferenceO2 = O("amoledBlack");
            if (preferenceO2 != null) {
                preferenceO2.p = new pb2(this, (byte) 8);
            }
            Preference preferenceO3 = O("amoledBlack");
            if (preferenceO3 != null) {
                SettingsActivity settingsActivity = (SettingsActivity) H();
                RecyclerView recyclerView = SettingsActivity.M;
                preferenceO3.u(!gu1.m(settingsActivity));
            }
            Preference preferenceO4 = O("autoPiP");
            if (preferenceO4 != null) {
                preferenceO4.u(wt2.B(i()));
            }
            Preference preferenceO5 = O("frameRateMatching");
            if (preferenceO5 != null) {
                preferenceO5.u(true);
            }
            SwitchPreferenceCompat switchPreferenceCompat = (SwitchPreferenceCompat) O("allowSystemFrameRate");
            if (switchPreferenceCompat != null) {
                switchPreferenceCompat.u(Build.VERSION.SDK_INT >= 30);
            }
            EditTextPreference editTextPreference = (EditTextPreference) O("togetherNick");
            Preference preferenceO6 = O("togetherNickRandom");
            byte b3 = 9;
            if (editTextPreference != null && preferenceO6 != null) {
                preferenceO6.q = new ub1(editTextPreference, b3);
                String str2 = editTextPreference.e0;
                if (str2 == null || str2.trim().isEmpty()) {
                    editTextPreference.B(l5.a());
                }
                editTextPreference.p = new rb2(this, editTextPreference, b2);
            }
            EditTextPreference editTextPreference2 = (EditTextPreference) O("togetherPassword");
            if (editTextPreference2 != null) {
                editTextPreference2.W = new rb2(this, editTextPreference2, b);
                editTextPreference2.h();
            }
            EditTextPreference editTextPreference3 = (EditTextPreference) O("togetherRelay");
            byte b4 = 2;
            if (editTextPreference3 != null) {
                editTextPreference3.W = new rb2(this, editTextPreference3, b4);
                editTextPreference3.h();
            }
            EditTextPreference editTextPreference4 = (EditTextPreference) O("togetherInvitePage");
            byte b5 = 3;
            if (editTextPreference4 != null) {
                editTextPreference4.W = new rb2(this, editTextPreference4, b5);
                editTextPreference4.h();
            }
            Preference preferenceO7 = O("screenOrientation");
            if (preferenceO7 != null && wt2.F(i())) {
                preferenceO7.y();
            }
            Preference preferenceO8 = O("roundValueButtons");
            if (preferenceO8 != null && wt2.F(i())) {
                preferenceO8.y();
            }
            Preference preferenceO9 = O("systemVolume");
            if (preferenceO9 != null && wt2.F(i())) {
                preferenceO9.y();
            }
            Preference preferenceO10 = O("disableVolumeBrightnessGestures");
            if (preferenceO10 != null && wt2.F(i())) {
                preferenceO10.y();
            }
            Preference preferenceO11 = O("holdSpeedMode");
            if (preferenceO11 != null && wt2.F(i())) {
                preferenceO11.y();
            }
            Preference preferenceO12 = O("tvSingleBack");
            if (preferenceO12 != null && !wt2.F(i())) {
                preferenceO12.y();
            }
            LinkedHashMap linkedHashMapB = wt2.b();
            Y("languageAudio", linkedHashMapB, R.string.pref_language_audio, R.string.pref_language_audio_none, gu1.e(I()), new p12((byte) 3));
            String strF = gu1.f(I());
            byte b6 = 4;
            Y("languageSubtitle", linkedHashMapB, R.string.pref_language_subtitle, R.string.pref_language_subtitle_none, strF, new p12((byte) 4));
            byte b7 = 5;
            Y("languageSubtitleSecondary", linkedHashMapB, R.string.pref_language_subtitle_secondary, R.string.pref_language_subtitle_secondary_none, PreferenceManager.getDefaultSharedPreferences(I()).getString("languageSubtitleSecondary", ""), new p12((byte) 5));
            ListPreference listPreference = (ListPreference) O("subtitleSecondaryMode");
            if (listPreference != null) {
                U(listPreference, listPreference.g0);
                listPreference.p = new qb2(this, listPreference, b2);
            }
            ListPreference listPreference2 = (ListPreference) O("subtitleSearchMode");
            SwitchPreferenceCompat switchPreferenceCompat2 = (SwitchPreferenceCompat) O("subtitleTranslateOn");
            if (listPreference2 != null) {
                T(listPreference2, listPreference2.g0, switchPreferenceCompat2);
                listPreference2.p = new nk(this, listPreference2, switchPreferenceCompat2, b3);
            }
            if (switchPreferenceCompat2 != null) {
                switchPreferenceCompat2.p = new pb2(this, b);
            }
            Preference preferenceO13 = O("subtitleSourcesCategory");
            if (preferenceO13 != null) {
                preferenceO13.y();
            }
            Preference preferenceO14 = O("subtitleTranslateBackends");
            if (preferenceO14 != null) {
                preferenceO14.y();
            }
            Preference preferenceO15 = O("subtitleTranslateBackends");
            if (preferenceO15 != null) {
                LinkedHashMap linkedHashMapB2 = al2.b();
                I();
                i0(preferenceO15, linkedHashMapB2, al2.e("mozhi,google"), R.string.pref_subtitle_translate_backends_none);
                preferenceO15.q = new fk((Object) this, (Object) linkedHashMapB2, (byte) 18);
            }
            X("subtitleTextColor", "subtitleBackground");
            X("subtitleSecondaryTextColor", "subtitleSecondaryBackground");
            Preference preferenceO16 = O("audioPassthroughForce");
            if (preferenceO16 != null) {
                preferenceO16.p = new pb2(this, b4);
            }
            Preference preferenceO17 = O("forgetRememberedDubs");
            if (preferenceO17 != null) {
                Context contextI2 = I();
                SharedPreferences sharedPreferences = contextI2.getSharedPreferences("dubs", 0);
                preferenceO17.u((sharedPreferences.getString("habit", "").isEmpty() && sharedPreferences.getString("titles", "").isEmpty() && gu1.i(contextI2).isEmpty() && wt2.d0(PreferenceManager.getDefaultSharedPreferences(contextI2).getString("declinedAudioLanguages", "")).isEmpty()) ? false : true);
                preferenceO17.q = new pb2(this, b5);
            }
            Preference preferenceO18 = O("resetRevokedAudioMimes");
            if (preferenceO18 != null) {
                preferenceO18.q = new pb2(this, b6);
            }
            Preference preferenceO19 = O("crashReporting");
            if (preferenceO19 != null) {
                preferenceO19.y();
            }
            wt2.f0(I());
            Preference preferenceO20 = O("aboutDeco");
            if (preferenceO20 != null && wt2.F(I()) && !preferenceO20.B) {
                preferenceO20.B = true;
                preferenceO20.h();
            }
            Preference preferenceO21 = O("aboutScreen");
            if (preferenceO21 == null) {
                wt2.t = true;
            } else if ((preferenceO21 instanceof PreferenceScreen) && ((PreferenceScreen) preferenceO21).Z.size() == 0) {
                wt2.t = true;
            }
            Preference preferenceO22 = O("sendAppLog");
            if (preferenceO22 != null) {
                preferenceO22.q = new pb2(this, b7);
            }
            Preference preferenceO23 = O("checkUpdateNow");
            if (preferenceO23 != null) {
                preferenceO23.q = new pb2(this, (byte) 6);
            }
            Preference preferenceO24 = O("aboutSource");
            if (preferenceO24 != null) {
                preferenceO24.q = new pb2(this, (byte) 7);
            }
            final PreferenceScreen preferenceScreen4 = this.g0.g;
            if (preferenceScreen4 == null) {
                return;
            }
            Context context = preferenceScreen4.l;
            Bundle bundle = this.q;
            if (bundle == null || bundle.getString("androidx.preference.PreferenceFragmentCompat.PREFERENCE_ROOT") == null) {
                Preference preference = new Preference(context, null);
                preference.x(R.string.pref_reset_all);
                preference.q = new pt1(this) { // from class: wb2
                    public final /* synthetic */ SettingsActivity.a m;

                    {
                        this.m = this;
                    }

                    @Override // defpackage.pt1
                    public final void f(Preference preference2) {
                        byte b8 = b2;
                        PreferenceScreen preferenceScreen5 = preferenceScreen4;
                        SettingsActivity.a aVar = this.m;
                        switch (b8) {
                            case DecoderInputBuffer.BUFFER_REPLACEMENT_MODE_DISABLED /* 0 */:
                                ArrayList arrayList = new ArrayList();
                                aVar.Z(preferenceScreen5, arrayList);
                                s2.d(aVar.H(), aVar.m(R.string.pref_reset_ask_all), null, aVar.m(R.string.pref_reset_do), new sk(aVar, arrayList, SettingsActivity.a.c0(preferenceScreen5)), aVar.m(android.R.string.cancel), null, false);
                                break;
                            default:
                                ArrayList arrayList2 = new ArrayList();
                                aVar.Z(preferenceScreen5, arrayList2);
                                s2.d(aVar.H(), aVar.m(R.string.pref_reset_ask), null, aVar.m(R.string.pref_reset_do), new sk(aVar, arrayList2, SettingsActivity.a.c0(preferenceScreen5)), aVar.m(android.R.string.cancel), null, false);
                                break;
                        }
                    }
                };
                PreferenceCategory preferenceCategory = new PreferenceCategory(preferenceScreen4.l, null);
                preferenceScreen4.B(preferenceCategory);
                if (preference.M) {
                    preference.M = false;
                    preference.h();
                }
                if (preferenceCategory.M) {
                    preferenceCategory.M = false;
                    preferenceCategory.h();
                }
                preferenceCategory.B(preference);
                return;
            }
            if (!d0(preferenceScreen4) || "aboutScreen".equals(preferenceScreen4.w)) {
                return;
            }
            Preference preference2 = new Preference(context, null);
            preference2.x(R.string.pref_reset_section);
            preference2.q = new pt1(this) { // from class: wb2
                public final /* synthetic */ SettingsActivity.a m;

                {
                    this.m = this;
                }

                @Override // defpackage.pt1
                public final void f(Preference preference3) {
                    byte b8 = b;
                    PreferenceScreen preferenceScreen5 = preferenceScreen4;
                    SettingsActivity.a aVar = this.m;
                    switch (b8) {
                        case DecoderInputBuffer.BUFFER_REPLACEMENT_MODE_DISABLED /* 0 */:
                            ArrayList arrayList = new ArrayList();
                            aVar.Z(preferenceScreen5, arrayList);
                            s2.d(aVar.H(), aVar.m(R.string.pref_reset_ask_all), null, aVar.m(R.string.pref_reset_do), new sk(aVar, arrayList, SettingsActivity.a.c0(preferenceScreen5)), aVar.m(android.R.string.cancel), null, false);
                            break;
                        default:
                            ArrayList arrayList2 = new ArrayList();
                            aVar.Z(preferenceScreen5, arrayList2);
                            s2.d(aVar.H(), aVar.m(R.string.pref_reset_ask), null, aVar.m(R.string.pref_reset_do), new sk(aVar, arrayList2, SettingsActivity.a.c0(preferenceScreen5)), aVar.m(android.R.string.cancel), null, false);
                            break;
                    }
                }
            };
            PreferenceCategory preferenceCategory2 = new PreferenceCategory(preferenceScreen4.l, null);
            preferenceScreen4.B(preferenceCategory2);
            if (preference2.M) {
                preference2.M = false;
                preference2.h();
            }
            if (preferenceCategory2.M) {
                preferenceCategory2.M = false;
                preferenceCategory2.h();
            }
            preferenceCategory2.B(preference2);
        }

        @Override // defpackage.wt1
        public final void S(DialogPreference dialogPreference) {
            View viewInflate;
            View view;
            View view2;
            int i;
            Context contextI = i();
            if (contextI == null || !s2.b(contextI)) {
                super.S(dialogPreference);
                return;
            }
            if (dialogPreference instanceof ListPreference) {
                ListPreference listPreference = (ListPreference) dialogPreference;
                CharSequence[] charSequenceArr = listPreference.e0;
                CharSequence[] charSequenceArr2 = listPreference.f0;
                if (charSequenceArr == null || charSequenceArr2 == null) {
                    super.S(dialogPreference);
                    return;
                }
                g7 g7VarH = H();
                CharSequence charSequence = listPreference.Y;
                if (charSequence == null) {
                    charSequence = listPreference.s;
                }
                s2.c(g7VarH, charSequence, Arrays.asList(charSequenceArr), listPreference.B(listPreference.g0), new fk((Object) charSequenceArr2, (Object) listPreference, (byte) 20));
                return;
            }
            if (!(dialogPreference instanceof EditTextPreference)) {
                super.S(dialogPreference);
                return;
            }
            EditTextPreference editTextPreference = (EditTextPreference) dialogPreference;
            ContextThemeWrapper contextThemeWrapperE = s2.e(H());
            int i2 = editTextPreference.d0;
            if (i2 == 0) {
                view = null;
            } else {
                viewInflate = LayoutInflater.from(contextThemeWrapperE).inflate(i2, (ViewGroup) null);
            }
            if (view != null) {
                view = viewInflate;
                int iP = wt2.p(s2.b(contextThemeWrapperE) ? 6 : 24);
                view.setPaddingRelative(iP, view.getPaddingTop(), iP, view.getPaddingBottom());
            }
            EditText editTextF = view == null ? null : (EditText) view.findViewById(android.R.id.edit);
            if (editTextF != null) {
                TextView textView = (TextView) view.findViewById(android.R.id.message);
                if (textView != null) {
                    String str = editTextPreference.Z;
                    textView.setText(str);
                    if (str == null || str.length() == 0) {
                        view2 = view;
                        view2 = view;
                        i = 8;
                    } else {
                        view2 = view;
                        i = 0;
                    }
                    textView.setVisibility(i);
                    view2 = view;
                }
            } else {
                LinearLayout linearLayoutF = s2.f(contextThemeWrapperE);
                editTextF = s2.F(linearLayoutF, editTextPreference.s, null);
                view2 = linearLayoutF;
            }
            view2 = view;
            editTextF.setText(editTextPreference.e0);
            editTextF.setSelection(editTextF.getText().length());
            g7 g7VarH2 = H();
            CharSequence charSequence2 = editTextPreference.Y;
            if (charSequence2 == null) {
                charSequence2 = editTextPreference.s;
            }
            s2.h(g7VarH2, charSequence2, view2, m(android.R.string.ok), new hj1((Object) editTextF, (Object) editTextPreference, (byte) 13));
        }

        public final void T(ListPreference listPreference, String str, SwitchPreferenceCompat switchPreferenceCompat) {
            boolean z = switchPreferenceCompat == null || switchPreferenceCompat.Y;
            Preference preferenceO = O("subtitleTranslateBackends");
            if (preferenceO != null) {
                preferenceO.u(z);
            }
            Preference preferenceO2 = O("subtitleSearchScreen");
            int iB = listPreference.B(str);
            if (preferenceO2 == null || iB < 0) {
                return;
            }
            preferenceO2.w(listPreference.e0[iB]);
        }

        public final void U(ListPreference listPreference, String str) {
            boolean z = !"off".equals(str);
            for (int i = 0; i < 5; i++) {
                Preference preferenceO = O(q0[i]);
                if (preferenceO != null) {
                    preferenceO.u(z);
                }
            }
            Preference preferenceO2 = O("subtitleSecondaryScreen");
            int iB = listPreference.B(str);
            if (preferenceO2 == null || iB < 0) {
                return;
            }
            preferenceO2.w(listPreference.e0[iB]);
        }

        public final void W() {
            Preference preferenceO;
            Context contextI = i();
            if (contextI == null || (preferenceO = O("allFilesAccess")) == null) {
                return;
            }
            preferenceO.w(preferenceO.l.getString(wt2.h(contextI) ? R.string.pref_all_files_access_on : R.string.pref_all_files_access_off));
            preferenceO.q = new fk((Object) this, (Object) contextI, (byte) 19);
        }

        public final void X(String str, String str2) {
            ListPreference listPreference = (ListPreference) O(str);
            ListPreference listPreference2 = (ListPreference) O(str2);
            if (listPreference == null || listPreference2 == null) {
                return;
            }
            listPreference.p = new qb2(this, listPreference2, (byte) 1);
            listPreference2.p = new qb2(this, listPreference, (byte) 2);
        }

        public final void Y(String str, final LinkedHashMap linkedHashMap, final int i, final int i2, String str2, final e eVar) {
            Preference preferenceO = O(str);
            if (preferenceO == null) {
                return;
            }
            final String[] strArr = {str2};
            i0(preferenceO, linkedHashMap, strArr[0], i2);
            preferenceO.q = new pt1() { // from class: ub2
                @Override // defpackage.pt1
                public final void f(final Preference preference) {
                    final SettingsActivity.a aVar = this.l;
                    g7 g7VarH = aVar.H();
                    String strM = aVar.m(i);
                    final String[] strArr2 = strArr;
                    ArrayList arrayListD0 = wt2.d0(strArr2[0]);
                    ArrayList arrayList = new ArrayList(Arrays.asList(wt2.v()));
                    String[] stringArrayExtra = aVar.H().getIntent().getStringArrayExtra("mediaLanguages");
                    if (stringArrayExtra != null) {
                        for (String str3 : stringArrayExtra) {
                            if (!arrayList.contains(str3)) {
                                arrayList.add(str3);
                            }
                        }
                    }
                    final e eVar2 = eVar;
                    final LinkedHashMap linkedHashMap2 = linkedHashMap;
                    final int i3 = i2;
                    t20.p(g7VarH, strM, i3, R.string.pref_language_audio_add, arrayListD0, linkedHashMap2, arrayList, new pz0() { // from class: xb2
                        @Override // defpackage.pz0
                        public final void e(ArrayList arrayList2) {
                            String strJoin = TextUtils.join(",", arrayList2);
                            String[] strArr3 = strArr2;
                            strArr3[0] = strJoin;
                            eVar2.a(aVar.I(), strArr3[0]);
                            SettingsActivity.a.i0(preference, linkedHashMap2, strArr3[0], i3);
                        }
                    });
                }
            };
        }

        public final void Z(PreferenceGroup preferenceGroup, ArrayList arrayList) {
            for (int i = 0; i < preferenceGroup.Z.size(); i++) {
                Preference preferenceD = preferenceGroup.D(i);
                if (preferenceD instanceof PreferenceGroup) {
                    Z((PreferenceGroup) preferenceD, arrayList);
                } else {
                    String str = preferenceD.w;
                    if (str != null) {
                        Context contextI = I();
                        if (contextI.getSharedPreferences(bu1.a(contextI), 0).contains(str)) {
                            arrayList.add(str);
                        }
                    }
                }
            }
        }

        public final void a0(String str) {
            ClipboardManager clipboardManager;
            g7 g7VarG = g();
            if (g7VarG == null || (clipboardManager = (ClipboardManager) g7VarG.getSystemService("clipboard")) == null) {
                return;
            }
            clipboardManager.setPrimaryClip(ClipData.newPlainText(m(R.string.pref_about_header), str));
            if (Build.VERSION.SDK_INT < 33) {
                RecyclerView recyclerView = SettingsActivity.M;
                y61.O(g7VarG, R.string.error_copied, false, R.drawable.ic_content_copy_24dp);
            }
        }

        public final boolean d0(PreferenceGroup preferenceGroup) {
            for (int i = 0; i < preferenceGroup.Z.size(); i++) {
                Preference preferenceD = preferenceGroup.D(i);
                if (preferenceD instanceof PreferenceGroup) {
                    if (d0((PreferenceGroup) preferenceD)) {
                        return true;
                    }
                } else if (preferenceD.w == null) {
                    continue;
                } else {
                    if ((preferenceD instanceof TwoStatePreference) || (preferenceD instanceof ListPreference) || (preferenceD instanceof EditTextPreference)) {
                        return true;
                    }
                    Context contextI = I();
                    if (contextI.getSharedPreferences(bu1.a(contextI), 0).contains(preferenceD.w)) {
                        return true;
                    }
                }
            }
            return false;
        }

        public final void e0(Context context) {
            try {
                N(new Intent("android.settings.APPLICATION_DETAILS_SETTINGS", Uri.parse("package:" + context.getPackageName())));
            } catch (Exception unused) {
                y61.O(g(), R.string.browse_no_source, true, R.drawable.ic_folder_open_24dp);
            }
        }

        public final void f0(final int i, final int i2) {
            final int iMax;
            final RecyclerView recyclerView = this.h0;
            if (i2 <= 0 || i == -1 || recyclerView == null || !(recyclerView.getLayoutManager() instanceof LinearLayoutManager)) {
                return;
            }
            final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
            rx1 adapter = recyclerView.getAdapter();
            if (adapter instanceof zt1) {
                zt1 zt1Var = (zt1) adapter;
                iMax = Math.max(0, i);
                while (true) {
                    if (iMax < zt1Var.e.size()) {
                        Preference preferenceJ = zt1Var.j(iMax);
                        if (!(preferenceJ instanceof PreferenceCategory)) {
                            if (preferenceJ.B && preferenceJ.g()) {
                                break;
                            }
                        } else if (iMax > i) {
                        }
                        iMax++;
                    }
                    iMax = -1;
                    break;
                }
            }
            iMax = i;
            linearLayoutManager.c1(Math.max(0, i - 1));
            if (iMax == -1) {
                return;
            }
            ul1.a(recyclerView, new Runnable() { // from class: tb2
                @Override // java.lang.Runnable
                public final void run() {
                    RecyclerView recyclerView2 = recyclerView;
                    oy1 oy1VarG = recyclerView2.G(iMax);
                    int i3 = i;
                    if (oy1VarG == null) {
                        this.l.f0(i3, i2 - 1);
                    } else {
                        oy1VarG.a.requestFocus();
                        recyclerView2.post(new lc(linearLayoutManager, i3, (byte) 4));
                    }
                }
            });
        }

        public final boolean h0() {
            g7 g7VarG = g();
            if (g7VarG == null || g7VarG.getIntent() == null || !g7VarG.getIntent().getBooleanExtra("focusFirst", false)) {
                return false;
            }
            g7VarG.getIntent().removeExtra("focusFirst");
            return true;
        }
    }

    public static int w(Context context, int i) {
        return Math.max(wt2.p(wt2.F(context) ? 48 : 16), (i - Math.max(wt2.p(720), Math.min(wt2.p(960), i / 2))) / 2);
    }

    /* JADX WARN: Code duplicated, block: B:63:0x009e  */
    /* JADX WARN: Code duplicated, block: B:66:0x00a5 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:67:0x00a7  */
    /* JADX WARN: Code duplicated, block: B:69:0x00b1  */
    /* JADX WARN: Code duplicated, block: B:70:0x00b5  */
    @Override // defpackage.g7, defpackage.zu, android.app.Activity, android.view.Window.Callback
    public final boolean dispatchKeyEvent(KeyEvent keyEvent) {
        a aVar;
        RecyclerView recyclerView;
        View viewFindFocus;
        View viewFindViewById;
        if (keyEvent.getAction() == 0 && (aVar = this.J) != null && aVar.l >= 7) {
            int keyCode = keyEvent.getKeyCode();
            boolean z = false;
            boolean z2 = keyCode == 19 || keyCode == 20;
            boolean z3 = keyCode == 21 || keyCode == 22;
            if ((z2 || z3) && (recyclerView = aVar.h0) != null && wt2.F(recyclerView.getContext()) && (viewFindFocus = recyclerView.findFocus()) != null) {
                if (z2) {
                    boolean z4 = keyCode == 20;
                    View viewFocusSearch = recyclerView.focusSearch(viewFindFocus, z4 ? 130 : 33);
                    if (viewFocusSearch == null || viewFocusSearch == viewFindFocus) {
                        if (recyclerView.canScrollVertically(z4 ? 1 : -1)) {
                            if (z4) {
                                recyclerView.i0(0, recyclerView.getHeight(), false);
                            } else {
                                recyclerView.j0(0);
                            }
                        } else if (!z4 || ((viewFindViewById = aVar.H().findViewById(R.id.toolbar)) != null && viewFindViewById.requestFocus())) {
                        }
                        z = true;
                    } else {
                        ViewParent parent = viewFocusSearch.getParent();
                        while (true) {
                            if (parent != null) {
                                if (parent != recyclerView) {
                                    parent = parent.getParent();
                                } else if (viewFocusSearch.isEnabled()) {
                                    View viewC = recyclerView.C(viewFindFocus);
                                    View viewC2 = recyclerView.C(viewFocusSearch);
                                    if (viewC != null && viewC2 != null) {
                                        int iK = RecyclerView.K(viewC);
                                        int iK2 = RecyclerView.K(viewC2);
                                        if (iK != -1 && iK2 != -1 && (!z4 ? iK2 > iK : iK2 < iK)) {
                                        }
                                    }
                                }
                            }
                            if (recyclerView.canScrollVertically(z4 ? 1 : -1)) {
                                if (z4) {
                                    recyclerView.i0(0, recyclerView.getHeight(), false);
                                } else {
                                    recyclerView.j0(0);
                                }
                            } else if (!z4) {
                            }
                            z = true;
                        }
                    }
                } else {
                    View viewC3 = recyclerView.C(viewFindFocus);
                    boolean z5 = keyCode == 22;
                    View view = viewFindFocus;
                    while (true) {
                        if (view != null) {
                            if (!view.canScrollHorizontally(z5 ? 1 : -1)) {
                                if (view != viewC3) {
                                    Object parent2 = view.getParent();
                                    view = parent2 instanceof View ? (View) parent2 : null;
                                }
                            }
                            z = true;
                            z = !z;
                        }
                        View viewFocusSearch2 = viewFindFocus.focusSearch(z5 ? 66 : 17);
                        if (viewFocusSearch2 != null && viewFocusSearch2 != viewFindFocus) {
                            for (ViewParent parent3 = viewFocusSearch2.getParent(); parent3 != null; parent3 = parent3.getParent()) {
                                if (parent3 == viewC3) {
                                    z = true;
                                    break;
                                }
                            }
                        }
                        z = !z;
                    }
                }
            }
            if (z) {
                return true;
            }
        }
        return super.dispatchKeyEvent(keyEvent);
    }

    @Override // android.app.Activity, android.view.ContextThemeWrapper
    public final void onApplyThemeResource(Resources.Theme theme, int i, boolean z) {
        super.onApplyThemeResource(theme, i, z);
        theme.applyStyle(gu1.a(this, gu1.m(this)), true);
    }

    @Override // defpackage.g7, defpackage.av, defpackage.zu, android.app.Activity
    public final void onCreate(Bundle bundle) {
        ck0 g03Var;
        k().p(gu1.g(this));
        super.onCreate(bundle);
        boolean zM = gu1.m(this);
        y();
        byte b = 1;
        if (!zM && gu1.l(this)) {
            getTheme().applyStyle(R.style.ThemeOverlay_JustPlus_Amoled, true);
        }
        int i = Build.VERSION.SDK_INT;
        if (i >= 29) {
            getWindow().getDecorView().setSystemUiVisibility(768);
            getWindow().setNavigationBarColor(0);
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
        setContentView(R.layout.settings_activity);
        if (bundle == null) {
            xf xfVar = new xf(m());
            xfVar.g(R.id.settings, new a());
            xfVar.d(false);
        }
        v((Toolbar) findViewById(R.id.toolbar));
        ck0 ck0VarL = l();
        if (ck0VarL != null) {
            ck0VarL.b0(true);
        }
        CharSequence title = getTitle();
        cn0 cn0VarM = m();
        lb2 lb2Var = new lb2(this, title);
        ArrayList arrayList = cn0VarM.l;
        if (arrayList == null) {
            arrayList = new ArrayList();
            cn0VarM.l = arrayList;
        }
        arrayList.add(lb2Var);
        final MaterialToolbar materialToolbar = (MaterialToolbar) findViewById(R.id.toolbar);
        findViewById(R.id.settings_layout).addOnLayoutChangeListener(new View.OnLayoutChangeListener() { // from class: mb2
            @Override // android.view.View.OnLayoutChangeListener
            public final void onLayoutChange(View view, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10) {
                RecyclerView recyclerView = SettingsActivity.M;
                int iW = SettingsActivity.w(this.a, ((i5 - i3) - view.getPaddingLeft()) - view.getPaddingRight());
                MaterialToolbar materialToolbar2 = materialToolbar;
                int iMax = Math.max(0, iW - materialToolbar2.getContentInsetStart());
                materialToolbar2.setPadding(iMax, materialToolbar2.getPaddingTop(), iMax, materialToolbar2.getPaddingBottom());
            }
        });
        if (i >= 29) {
            ((LinearLayout) findViewById(R.id.settings_layout)).setOnApplyWindowInsetsListener(new h70(b));
        }
    }

    @Override // defpackage.g7
    public final void q() {
        getTheme().applyStyle(gu1.a(this, gu1.m(this)), true);
    }

    @Override // defpackage.g7
    public final boolean u() {
        if (m().N(-1, 0)) {
            return true;
        }
        finish();
        return true;
    }

    public final void x(PreferenceScreen preferenceScreen) {
        a aVar = new a();
        Bundle bundle = new Bundle();
        String str = preferenceScreen.w;
        bundle.putString("androidx.preference.PreferenceFragmentCompat.PREFERENCE_ROOT", str);
        aVar.L(bundle);
        this.I = str;
        xf xfVar = new xf(m());
        xfVar.g(R.id.settings, aVar);
        if (!xfVar.h) {
            bl.h("This FragmentTransaction is not allowed to be added to the back stack.");
            return;
        }
        xfVar.g = true;
        xfVar.i = null;
        xfVar.d(false);
        setTitle(preferenceScreen.s);
    }

    public final void y() {
        TypedArray typedArrayObtainStyledAttributes = obtainStyledAttributes(gu1.a(this, gu1.m(this)), aw1.a);
        this.K = typedArrayObtainStyledAttributes.getColor(4, -16777216);
        this.L = typedArrayObtainStyledAttributes.getColor(0, -12303292);
        typedArrayObtainStyledAttributes.recycle();
    }

    public final void z() {
        boolean z = !gu1.m(this) && gu1.l(this);
        getTheme().applyStyle(z ? R.style.ThemeOverlay_JustPlus_Amoled : R.style.ThemeOverlay_JustPlus_Ground, true);
        int color = z ? getColor(R.color.black) : this.K;
        getWindow().setBackgroundDrawable(new ColorDrawable(color));
        getWindow().setStatusBarColor(color);
        View viewFindViewById = findViewById(R.id.toolbar);
        if (viewFindViewById != null) {
            viewFindViewById.setBackgroundColor(color);
        }
        int color2 = z ? getColor(R.color.amoled_card) : this.L;
        RecyclerView recyclerView = M;
        int i = ob2.f;
        if (recyclerView == null) {
            return;
        }
        for (int i2 = 0; i2 < recyclerView.getItemDecorationCount(); i2++) {
            int itemDecorationCount = recyclerView.getItemDecorationCount();
            if (i2 < 0 || i2 >= itemDecorationCount) {
                throw new IndexOutOfBoundsException(i2 + " is an invalid index for size " + itemDecorationCount);
            }
            xx1 xx1Var = (xx1) recyclerView.A.get(i2);
            if (xx1Var instanceof ob2) {
                ((ob2) xx1Var).c.setColor(color2);
            }
        }
        recyclerView.invalidate();
    }
}
