package com.brouken.player;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.RippleDrawable;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.media3.decoder.VideoDecoderOutputBuffer;
import androidx.media3.ui.SubtitleView;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceCategory;
import androidx.preference.PreferenceScreen;
import androidx.preference.SwitchPreferenceCompat;
import androidx.preference.TwoStatePreference;
import androidx.recyclerview.widget.RecyclerView;
import com.brouken.player.SettingsActivity;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.button.MaterialButtonToggleGroup;
import com.google.android.material.card.MaterialCardView;
import com.justplus.player.R;
import defpackage.cb2;
import defpackage.co;
import defpackage.db2;
import defpackage.gt2;
import defpackage.hy;
import defpackage.ir1;
import defpackage.l51;
import defpackage.lh0;
import defpackage.lj;
import defpackage.ll1;
import defpackage.ot1;
import defpackage.pv1;
import defpackage.qs;
import defpackage.rs;
import defpackage.st1;
import defpackage.tf;
import defpackage.uy0;
import defpackage.vt1;
import defpackage.we2;
import defpackage.zi0;
import defpackage.zp1;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

/* JADX INFO: compiled from: r8-map-id-ba8d2c2760819bd03a19aa4133a73a2576203ef152dace5b65b91a4c4fd30466 */
/* JADX INFO: loaded from: classes.dex */
public final class c extends ot1 {
    public final /* synthetic */ SettingsActivity.a i;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public c(SettingsActivity.a aVar, PreferenceScreen preferenceScreen) {
        super(preferenceScreen);
        this.i = aVar;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v14, types: [android.graphics.drawable.Drawable] */
    /* JADX WARN: Type inference failed for: r11v18 */
    /* JADX WARN: Type inference failed for: r11v19, types: [android.view.View$OnClickListener] */
    /* JADX WARN: Type inference failed for: r11v20 */
    /* JADX WARN: Type inference failed for: r1v2, types: [android.view.View, android.widget.TextView] */
    /* JADX WARN: Type inference failed for: r4v16, types: [java.lang.CharSequence, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r4v33, types: [android.view.View] */
    /* JADX WARN: Type inference failed for: r5v25, types: [java.lang.CharSequence, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r5v34, types: [int] */
    /* JADX WARN: Type inference failed for: r5v43 */
    /* JADX WARN: Type inference failed for: r5v44, types: [android.view.View$OnClickListener] */
    /* JADX WARN: Type inference failed for: r5v45 */
    /* JADX WARN: Type inference failed for: r5v46, types: [java.lang.CharSequence] */
    /* JADX WARN: Type inference failed for: r5v65 */
    /* JADX WARN: Type inference failed for: r5v66 */
    /* JADX WARN: Type inference failed for: r5v67 */
    /* JADX WARN: Type inference failed for: r5v68 */
    /* JADX WARN: Type inference failed for: r6v23 */
    /* JADX WARN: Type inference failed for: r6v24, types: [com.google.android.material.card.MaterialCardView] */
    /* JADX WARN: Type inference failed for: r6v25 */
    /* JADX WARN: Type inference failed for: r6v26 */
    /* JADX WARN: Type inference failed for: r6v33 */
    /* JADX WARN: Type inference failed for: r6v34 */
    /* JADX WARN: Type inference failed for: r9v22 */
    /* JADX WARN: Type inference failed for: r9v23, types: [android.view.View] */
    /* JADX WARN: Type inference failed for: r9v25 */
    /* JADX WARN: Type inference failed for: r9v42 */
    /* JADX WARN: Type inference failed for: r9v5 */
    /* JADX WARN: Type inference failed for: r9v8 */
    /* JADX WARN: Type inference failed for: r9v9 */
    @Override // defpackage.ot1, defpackage.gx1
    /* JADX INFO: renamed from: l */
    public final void e(st1 st1Var, int i) throws Throwable {
        int i2;
        int i3;
        Throwable th;
        CharSequence[] charSequenceArr;
        boolean z;
        ?? r9;
        boolean z2;
        CharSequence[] charSequenceArr2;
        SettingsActivity.a aVar;
        int i4;
        boolean z3;
        ?? r6;
        Context context;
        ?? r10;
        ?? ir1Var;
        int i5;
        int i6;
        super.e(st1Var, i);
        View view = st1Var.a;
        int i7 = db2.f;
        boolean z4 = false;
        boolean z5 = true;
        if (j(i) instanceof PreferenceCategory) {
            view.setClipToOutline(false);
            view.setOutlineProvider(ViewOutlineProvider.BACKGROUND);
            i2 = 5;
            i3 = 3;
        } else {
            boolean z6 = i == 0 || (j(i + (-1)) instanceof PreferenceCategory);
            i2 = 5;
            boolean z7 = i == this.e.size() - 1 || (j(i + 1) instanceof PreferenceCategory);
            i3 = 3;
            view.setOutlineProvider(new cb2(z6, z7));
            view.setClipToOutline(true);
            float f = z6 ? i7 : 0.0f;
            float f2 = z7 ? i7 : 0.0f;
            Context context2 = view.getContext();
            float[] fArr = {f, f, f, f, f2, f2, f2, f2};
            String[] strArr = gt2.a;
            GradientDrawable gradientDrawable = new GradientDrawable();
            gradientDrawable.setCornerRadii(fArr);
            gradientDrawable.setStroke(context2.getResources().getDimensionPixelSize(R.dimen.focus_ring_width), uy0.w(context2, R.color.focus_ring));
            view.setForeground(gradientDrawable);
            view.setBackground(new RippleDrawable(gt2.R(lj.J(view.getContext(), tf.E(view, R.attr.colorControlHighlight))), null, new ColorDrawable(-1)));
        }
        TextView textView = (TextView) st1Var.q(android.R.id.title);
        if (textView != null) {
            textView.setSingleLine(false);
            textView.setEllipsize(null);
            Preference preferenceJ = j(i);
            if (preferenceJ != null && !preferenceJ.B && preferenceJ.g()) {
                textView.setTextColor(lj.J(textView.getContext(), tf.E(textView, R.attr.colorOnSurface)));
            }
        }
        View viewQ = st1Var.q(android.R.id.icon);
        if (viewQ instanceof ImageView) {
            ((ImageView) viewQ).setColorFilter(lj.n(viewQ.getContext(), R.attr.colorPrimary, -1));
        }
        Preference preferenceJ2 = j(i);
        View viewQ2 = st1Var.q(android.R.id.summary);
        if (viewQ2 instanceof TextView) {
            TextView textView2 = (TextView) viewQ2;
            if (preferenceJ2 instanceof TwoStatePreference) {
                TwoStatePreference twoStatePreference = (TwoStatePreference) preferenceJ2;
                CharSequence charSequence = twoStatePreference.Z;
                CharSequence charSequence2 = twoStatePreference.a0;
                if (charSequence == null || charSequence2 == null) {
                    th = null;
                } else {
                    th = null;
                    if (!charSequence.toString().equals(charSequence2.toString())) {
                        charSequenceArr2 = new CharSequence[]{charSequence, charSequence2};
                        z = true;
                    }
                }
                z = z5;
                r9 = th;
            } else {
                th = null;
                if (!(preferenceJ2 instanceof ListPreference) || (charSequenceArr = ((ListPreference) preferenceJ2).e0) == null || charSequenceArr.length < 2) {
                    z = z5;
                    r9 = th;
                } else {
                    CharSequence charSequenceF = preferenceJ2.f();
                    int length = charSequenceArr.length;
                    int i8 = 0;
                    while (true) {
                        if (i8 >= length) {
                            z = z5;
                            r9 = th;
                        } else {
                            CharSequence charSequence3 = charSequenceArr[i8];
                            if (charSequence3 != null) {
                                if (charSequence3.toString().contentEquals(charSequenceF == null ? "" : charSequenceF)) {
                                    CharSequence charSequence4 = charSequenceArr[0];
                                    int length2 = charSequenceArr.length;
                                    CharSequence charSequence5 = charSequence4;
                                    int i9 = 0;
                                    while (i9 < length2) {
                                        CharSequence charSequence6 = charSequenceArr[i9];
                                        if (charSequence6 == null) {
                                            z2 = z5;
                                        } else {
                                            z2 = z5;
                                            if (charSequence6.length() < charSequence4.length()) {
                                                charSequence4 = charSequence6;
                                            }
                                            if (charSequence6.length() > charSequence5.length()) {
                                                charSequence5 = charSequence6;
                                            }
                                        }
                                        i9++;
                                        z5 = z2;
                                    }
                                    z = z5;
                                    if (charSequence4.toString().equals(charSequence5.toString())) {
                                        r9 = th;
                                    } else {
                                        CharSequence[] charSequenceArr3 = new CharSequence[2];
                                        charSequenceArr3[0] = charSequence5;
                                        charSequenceArr3[z ? 1 : 0] = charSequence4;
                                        r9 = charSequenceArr3;
                                    }
                                }
                            }
                            i8++;
                            z5 = z5;
                        }
                    }
                }
            }
            if (r9 == 0) {
                r9 = charSequenceArr2;
                textView2.setMinLines(0);
            } else {
                r9 = charSequenceArr2;
                ?? r4 = r9[0];
                ?? r5 = r9[z ? 1 : 0];
                SettingsActivity.a.V(textView2, r4, r5);
                ll1.a(textView2, new zp1(textView2, (Object) r4, (Object) r5, (byte) 4));
            }
        } else {
            z = true;
            th = null;
        }
        Preference preferenceJ3 = j(i);
        final SettingsActivity.a aVar2 = this.i;
        if (preferenceJ3 != null && "themeMode".equals(preferenceJ3.w) && (view instanceof MaterialButtonToggleGroup)) {
            MaterialButtonToggleGroup materialButtonToggleGroup = (MaterialButtonToggleGroup) view;
            LinkedHashSet linkedHashSet = materialButtonToggleGroup.x;
            final Context context3 = materialButtonToggleGroup.getContext();
            if (gt2.F(context3)) {
                materialButtonToggleGroup.findViewById(R.id.theme_mode_system).setVisibility(8);
                Set set = vt1.S0;
                if ("system".equals(PreferenceManager.getDefaultSharedPreferences(context3).getString("themeMode", "system"))) {
                    PreferenceManager.getDefaultSharedPreferences(context3).edit().putString("themeMode", "dark").apply();
                }
            }
            for (int i10 = 0; i10 < materialButtonToggleGroup.getChildCount(); i10++) {
                gt2.r((MaterialButton) materialButtonToggleGroup.getChildAt(i10));
            }
            linkedHashSet.clear();
            Set set2 = vt1.S0;
            String string = PreferenceManager.getDefaultSharedPreferences(context3).getString("themeMode", "system");
            if ("dark".equals(string)) {
                i6 = R.id.theme_mode_dark;
            } else {
                if ("light".equals(string)) {
                    i6 = R.id.theme_mode_light;
                } else {
                    i6 = R.id.theme_mode_system;
                }
                materialButtonToggleGroup.l(i6, z);
                linkedHashSet.add(new l51() { // from class: hb2
                    @Override // defpackage.l51
                    public final void a(int i11, boolean z8) {
                        String str;
                        if (z8) {
                            if (i11 == R.id.theme_mode_dark) {
                                str = "dark";
                            } else {
                                str = i11 == R.id.theme_mode_light ? "light" : "system";
                            }
                            Set set3 = vt1.S0;
                            Context context4 = context3;
                            PreferenceManager.getDefaultSharedPreferences(context4).edit().putString("themeMode", str).apply();
                            SettingsActivity.a aVar3 = aVar2;
                            Preference preferenceO = aVar3.O("amoledBlack");
                            if (preferenceO != null) {
                                SettingsActivity settingsActivity = (SettingsActivity) aVar3.H();
                                RecyclerView recyclerView = SettingsActivity.M;
                                preferenceO.u(!vt1.m(settingsActivity));
                            }
                            ((SettingsActivity) aVar3.H()).k().p(vt1.g(context4));
                        }
                    }
                });
            }
            materialButtonToggleGroup.l(i6, z);
            linkedHashSet.add(new l51() { // from class: hb2
                @Override // defpackage.l51
                public final void a(int i11, boolean z8) {
                    String str;
                    if (z8) {
                        if (i11 == R.id.theme_mode_dark) {
                            str = "dark";
                        } else {
                            str = i11 == R.id.theme_mode_light ? "light" : "system";
                        }
                        Set set3 = vt1.S0;
                        Context context4 = context3;
                        PreferenceManager.getDefaultSharedPreferences(context4).edit().putString("themeMode", str).apply();
                        SettingsActivity.a aVar3 = aVar2;
                        Preference preferenceO = aVar3.O("amoledBlack");
                        if (preferenceO != null) {
                            SettingsActivity settingsActivity = (SettingsActivity) aVar3.H();
                            RecyclerView recyclerView = SettingsActivity.M;
                            preferenceO.u(!vt1.m(settingsActivity));
                        }
                        ((SettingsActivity) aVar3.H()).k().p(vt1.g(context4));
                    }
                }
            });
        }
        Preference preferenceJ4 = j(i);
        if (preferenceJ4 != null && "accentTheme".equals(preferenceJ4.w) && (view instanceof HorizontalScrollView)) {
            HorizontalScrollView horizontalScrollView = (HorizontalScrollView) view;
            LinearLayout linearLayout = (LinearLayout) horizontalScrollView.getChildAt(0);
            Context context4 = linearLayout.getContext();
            boolean zM = vt1.m(context4);
            if (linearLayout.getChildCount() == 0) {
                int[] iArrY = we2.y(32);
                int length3 = iArrY.length;
                int i11 = 0;
                while (i11 < length3) {
                    int i12 = iArrY[i11];
                    MaterialCardView materialCardView = (MaterialCardView) LayoutInflater.from(context4).inflate(R.layout.accent_card, linearLayout, z4);
                    materialCardView.setTag(lh0.j(i12));
                    TypedArray typedArrayObtainStyledAttributes = context4.obtainStyledAttributes(zM ? lh0.k(i12) : lh0.h(i12), pv1.a);
                    HorizontalScrollView horizontalScrollView2 = horizontalScrollView;
                    SettingsActivity.a aVar3 = aVar2;
                    SettingsActivity.a.g0(materialCardView, R.id.accent_ground, typedArrayObtainStyledAttributes, 4, -16777216);
                    SettingsActivity.a.g0(materialCardView, R.id.accent_bar_top, typedArrayObtainStyledAttributes, 9, -12303292);
                    SettingsActivity.a.g0(materialCardView, R.id.accent_row, typedArrayObtainStyledAttributes, 9, -12303292);
                    SettingsActivity.a.g0(materialCardView, R.id.accent_chosen, typedArrayObtainStyledAttributes, 2, -7829368);
                    int i13 = i3;
                    SettingsActivity.a.g0(materialCardView, R.id.accent_pill, typedArrayObtainStyledAttributes, i13, -7829368);
                    boolean z8 = zM;
                    int i14 = i2;
                    int i15 = i11;
                    SettingsActivity.a.g0(materialCardView, R.id.accent_chip, typedArrayObtainStyledAttributes, i14, -1);
                    int color = typedArrayObtainStyledAttributes.getColor(i13, -7829368);
                    typedArrayObtainStyledAttributes.recycle();
                    TextView textView3 = (TextView) materialCardView.findViewById(R.id.accent_name);
                    switch (i12) {
                        case 1:
                            i5 = R.string.pref_accent_coral;
                            break;
                        case 2:
                            i5 = R.string.pref_accent_rosegold;
                            break;
                        case VideoDecoderOutputBuffer.COLORSPACE_BT2020 /* 3 */:
                            i5 = R.string.pref_accent_strawberry;
                            break;
                        case 4:
                            i5 = R.string.pref_accent_cottoncandy;
                            break;
                        case 5:
                            i5 = R.string.pref_accent_orchid;
                            break;
                        case 6:
                            i5 = R.string.pref_accent_violet;
                            break;
                        case 7:
                            i5 = R.string.pref_accent_rosepine;
                            break;
                        case 8:
                            i5 = R.string.pref_accent_dracula;
                            break;
                        case 9:
                            i5 = R.string.pref_accent_lavender;
                            break;
                        case 10:
                            i5 = R.string.pref_accent_catppuccin;
                            break;
                        case 11:
                            i5 = R.string.pref_accent_periwinkle;
                            break;
                        case 12:
                            i5 = R.string.pref_accent_kanagawa;
                            break;
                        case 13:
                            i5 = R.string.pref_accent_tokyonight;
                            break;
                        case 14:
                            i5 = R.string.pref_accent_ayu;
                            break;
                        case 15:
                            i5 = R.string.pref_accent_sapphire;
                            break;
                        case 16:
                            i5 = R.string.pref_accent_midnight;
                            break;
                        case 17:
                            i5 = R.string.pref_accent_nord;
                            break;
                        case 18:
                            i5 = R.string.pref_accent_solarized;
                            break;
                        case 19:
                            i5 = R.string.pref_accent_ocean;
                            break;
                        case 20:
                            i5 = R.string.pref_accent_teal;
                            break;
                        case 21:
                            i5 = R.string.pref_accent_everblush;
                            break;
                        case 22:
                            i5 = R.string.pref_accent_tako;
                            break;
                        case 23:
                            i5 = R.string.pref_accent_everforest;
                            break;
                        case 24:
                            i5 = R.string.pref_accent_forest;
                            break;
                        case 25:
                            i5 = R.string.pref_accent_monokai;
                            break;
                        case 26:
                            i5 = R.string.pref_accent_amber;
                            break;
                        case 27:
                            i5 = R.string.pref_accent_cloudflare;
                            break;
                        case 28:
                            i5 = R.string.pref_accent_gruvbox;
                            break;
                        case 29:
                            i5 = R.string.pref_accent_sunset;
                            break;
                        case 30:
                            i5 = R.string.pref_accent_mocha;
                            break;
                        case 31:
                            i5 = R.string.pref_accent_slate;
                            break;
                        case 32:
                            i5 = R.string.pref_accent_monochrome;
                            break;
                        default:
                            throw th;
                    }
                    textView3.setText(i5);
                    materialCardView.setStrokeColor(new ColorStateList(new int[][]{new int[]{android.R.attr.state_focused}, new int[]{android.R.attr.state_checked}, new int[0]}, new int[]{lj.n(context4, R.attr.colorOnSurface, -1), color, lj.n(context4, R.attr.colorOutlineVariant, -7829368)}));
                    materialCardView.setOnFocusChangeListener(new rs(materialCardView, (byte) 4));
                    linearLayout.addView(materialCardView);
                    i11 = i15 + 1;
                    aVar2 = aVar3;
                    zM = z8;
                    iArrY = iArrY;
                    horizontalScrollView = horizontalScrollView2;
                    z4 = false;
                    i2 = 5;
                    i3 = 3;
                }
            }
            HorizontalScrollView horizontalScrollView3 = horizontalScrollView;
            SettingsActivity.a aVar4 = aVar2;
            String string2 = PreferenceManager.getDefaultSharedPreferences(context4).getString("accentTheme", "coral");
            final ?? r7 = th;
            int i16 = 0;
            while (i16 < linearLayout.getChildCount()) {
                MaterialCardView materialCardView2 = (MaterialCardView) linearLayout.getChildAt(i16);
                boolean zEquals = string2.equals(materialCardView2.getTag());
                materialCardView2.setChecked(zEquals);
                if (zEquals) {
                    r6 = r7;
                    r6 = materialCardView2;
                }
                r6 = r7;
                int colorForState = materialCardView2.getStrokeColorStateList().getColorForState(new int[]{android.R.attr.state_checked}, 0);
                TextView textView4 = (TextView) materialCardView2.findViewById(R.id.accent_name);
                if (!zEquals) {
                    colorForState = lj.n(context4, R.attr.colorOnSurface, -1);
                }
                textView4.setTextColor(colorForState);
                materialCardView2.setStrokeWidth((materialCardView2.t || materialCardView2.isFocused()) ? materialCardView2.getResources().getDimensionPixelSize(R.dimen.focus_ring_width) : gt2.p(1));
                if (zEquals) {
                    context = context4;
                    ir1Var = th;
                    r10 = materialCardView2;
                } else {
                    context = context4;
                    r10 = materialCardView2;
                    ir1Var = new ir1(aVar4, context, materialCardView2, horizontalScrollView3, (byte) 1);
                }
                SettingsActivity.a aVar5 = aVar4;
                HorizontalScrollView horizontalScrollView4 = horizontalScrollView3;
                r10.setOnClickListener(ir1Var);
                i16++;
                horizontalScrollView3 = horizontalScrollView4;
                aVar4 = aVar5;
                context4 = context;
                r7 = r6;
            }
            aVar = aVar4;
            final HorizontalScrollView horizontalScrollView5 = horizontalScrollView3;
            z3 = true;
            final int i17 = aVar.n0;
            final boolean z9 = aVar.o0;
            i4 = -1;
            aVar.n0 = -1;
            aVar.o0 = false;
            if (i17 >= 0 || r7 != 0) {
                ll1.a(horizontalScrollView5, new Runnable() { // from class: ob2
                    @Override // java.lang.Runnable
                    public final void run() {
                        int iMax = i17;
                        View view2 = r7;
                        if (iMax < 0) {
                            iMax = Math.max(0, view2.getLeft() - view2.getWidth());
                        }
                        horizontalScrollView5.scrollTo(iMax, 0);
                        if (!z9 || view2 == null) {
                            return;
                        }
                        view2.requestFocus();
                    }
                });
            }
        } else {
            aVar = aVar2;
            i4 = -1;
            z3 = true;
        }
        Preference preferenceJ5 = j(i);
        if (preferenceJ5 != null && "aboutHeader".equals(preferenceJ5.w)) {
            View viewQ3 = st1Var.q(R.id.about_version);
            if (viewQ3 instanceof TextView) {
                ((TextView) viewQ3).setText("v2.1.1");
            }
            View viewQ4 = st1Var.q(R.id.about_device);
            if (viewQ4 instanceof TextView) {
                ((TextView) viewQ4).setText(SettingsActivity.a.b0());
            }
            ?? Q = st1Var.q(R.id.about_info);
            View viewQ5 = st1Var.q(R.id.about_copy_hint);
            boolean zF = gt2.F(aVar.I());
            boolean z10 = !zF;
            if (viewQ5 != null) {
                viewQ5.setVisibility(!zF ? 0 : 8);
            }
            if (Q != 0) {
                Q.setOnClickListener(!zF ? new qs(aVar, (byte) 15) : th);
                Q.setClickable(z10);
                Q.setFocusable(false);
                Q.setContentDescription(!zF ? aVar.m(R.string.error_copy) : th);
            }
        }
        Preference preferenceJ6 = j(i);
        if (preferenceJ6 == null) {
            return;
        }
        String str = preferenceJ6.w;
        boolean zEquals2 = "subtitleSecondaryPreview".equals(str);
        if (zEquals2 || "subtitlePreview".equals(str)) {
            view.setAlpha(preferenceJ6.g() ? 1.0f : 0.38f);
            View viewQ6 = st1Var.q(R.id.subtitle_preview_line);
            ?? r1 = (TextView) st1Var.q(R.id.subtitle_preview_hint);
            if (!(viewQ6 instanceof SubtitleView) || r1 == 0) {
                return;
            }
            ListPreference listPreference = (ListPreference) aVar.O(zEquals2 ? "subtitleSecondaryScale" : "subtitleScale");
            ListPreference listPreference2 = (ListPreference) aVar.O(zEquals2 ? "subtitleSecondaryTextColor" : "subtitleTextColor");
            ListPreference listPreference3 = (ListPreference) aVar.O(zEquals2 ? "subtitleSecondaryBackground" : "subtitleBackground");
            if (listPreference == null || listPreference2 == null || listPreference3 == null) {
                return;
            }
            Context context5 = view.getContext();
            int dimensionPixelSize = context5.getResources().getDimensionPixelSize(R.dimen.subtitle_preview_height);
            DisplayMetrics displayMetrics = context5.getResources().getDisplayMetrics();
            float fB = zi0.B(Float.parseFloat(listPreference.g0), (gt2.F(context5) || context5.getResources().getConfiguration().smallestScreenWidthDp >= 720) ? z3 : false) * 0.0533f;
            int color2 = Color.parseColor(listPreference2.g0);
            int color3 = Color.parseColor(listPreference3.g0);
            SwitchPreferenceCompat switchPreferenceCompat = (SwitchPreferenceCompat) aVar.O("subtitleStyleBold");
            ?? r8 = switchPreferenceCompat != null ? switchPreferenceCompat.Y : aVar.g0.d().getBoolean("subtitleStyleBold", false);
            String strM = aVar.m(R.string.pref_subtitle_preview_sample);
            SubtitleView subtitleView = (SubtitleView) viewQ6;
            subtitleView.setVisibility(zEquals2 ? 8 : 0);
            r1.setVisibility(zEquals2 ? 0 : 8);
            if (!zEquals2) {
                ListPreference listPreference4 = (ListPreference) aVar.O("subtitleEdge");
                if (listPreference4 == null) {
                    return;
                }
                subtitleView.setStyle(new co(color2, color3, 0, Integer.parseInt(listPreference4.g0), color2 == -16777216 ? i4 : -16777216, Typeface.create(Typeface.DEFAULT, (int) r8)));
                subtitleView.setFractionalTextSize(fB);
                subtitleView.setBottomPaddingFraction(0.05333333f);
                subtitleView.setCues(Collections.singletonList(new hy(strM, null, null, null, -3.4028235E38f, Integer.MIN_VALUE, Integer.MIN_VALUE, -3.4028235E38f, Integer.MIN_VALUE, Integer.MIN_VALUE, -3.4028235E38f, -3.4028235E38f, -3.4028235E38f, false, -16777216, Integer.MIN_VALUE, 0.0f, 0)));
                return;
            }
            float f3 = dimensionPixelSize;
            float fMin = f3 / Math.min(displayMetrics.widthPixels, displayMetrics.heightPixels);
            int iRound = Math.round(gt2.p(8) * fMin);
            int iRound2 = Math.round(gt2.p(4) * fMin);
            r1.setText(strM);
            r1.setMaxLines(2);
            r1.setTextColor(color2);
            r1.setTypeface(Typeface.create(Typeface.DEFAULT, (int) r8));
            r1.setTextSize(0, fB * f3);
            r1.setPadding(iRound, iRound2, iRound, iRound2);
            if (color3 == 0) {
                r1.setBackground(th);
            } else {
                GradientDrawable gradientDrawable2 = new GradientDrawable();
                gradientDrawable2.setCornerRadius(gt2.p(6) * fMin);
                gradientDrawable2.setColor(color3);
                r1.setBackground(gradientDrawable2);
            }
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) r1.getLayoutParams();
            layoutParams.bottomMargin = Math.round(f3 * 0.05333333f);
            r1.setLayoutParams(layoutParams);
        }
    }
}
