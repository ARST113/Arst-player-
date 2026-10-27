package com.brouken.player;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.annotation.StringRes;
import com.google.android.material.color.MaterialColors;

/**
 * ARX's local picture, ambient and PCM equalizer controls use the same
 * theme-aware side sheet as the native Just+ pickers. Deliberately no
 * android.app.AlertDialog here: that produces grey system modal windows over
 * the movie and ignores the existing player's accent and TV focus ring.
 */
final class ArxControlsUi {
    private ArxControlsUi() {}

    static Context context(Activity activity) {
        return Dialogs.dialogContext(activity);
    }

    static LinearLayout column(Context ctx) {
        final LinearLayout body = new LinearLayout(ctx);
        body.setOrientation(LinearLayout.VERTICAL);
        final int padding = Utils.dpToPx(12);
        body.setPadding(padding, Utils.dpToPx(8), padding, Utils.dpToPx(20));
        return body;
    }

    static TextView caption(Context ctx, CharSequence value) {
        final TextView text = new TextView(ctx);
        text.setText(value);
        text.setTextSize(15f);
        text.setTextColor(MaterialColors.getColor(ctx, R.attr.colorOnSurfaceVariant, Color.LTGRAY));
        text.setPadding(Utils.dpToPx(10), Utils.dpToPx(9), Utils.dpToPx(10), Utils.dpToPx(5));
        return text;
    }

    static TextView valueLabel(Context ctx, CharSequence value) {
        final TextView text = caption(ctx, value);
        text.setTextSize(16f);
        text.setTextColor(MaterialColors.getColor(ctx, R.attr.colorOnSurface, Color.WHITE));
        return text;
    }

    static TextView action(Context ctx, CharSequence value, Runnable run) {
        final TextView button = valueLabel(ctx, value);
        button.setMinHeight(Utils.dpToPx(54));
        button.setGravity(Gravity.CENTER_VERTICAL);
        button.setPadding(Utils.dpToPx(14), Utils.dpToPx(8), Utils.dpToPx(14), Utils.dpToPx(8));
        button.setBackground(Dialogs.pickerRow(ctx, Color.TRANSPARENT));
        button.setFocusable(true);
        button.setClickable(true);
        button.setOnClickListener(v -> run.run());
        return button;
    }

    static void slider(Context ctx, LinearLayout body, TextView label, int value,
                       int max, String description, android.widget.SeekBar.OnSeekBarChangeListener listener) {
        body.addView(label);
        final SeekBar bar = new SeekBar(ctx);
        bar.setMax(max);
        bar.setProgress(value);
        bar.setContentDescription(description);
        final int accent = MaterialColors.getColor(ctx, R.attr.colorPrimary, Color.RED);
        final ColorStateList tint = ColorStateList.valueOf(accent);
        bar.setProgressTintList(tint);
        bar.setThumbTintList(tint);
        final LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, Utils.dpToPx(46));
        params.bottomMargin = Utils.dpToPx(8);
        body.addView(bar, params);
        bar.setOnSeekBarChangeListener(listener);
    }

    static SeekBar seekBar(Context ctx, LinearLayout body, int max, int progress,
                           CharSequence description, SeekBar.OnSeekBarChangeListener listener) {
        final SeekBar bar = new SeekBar(ctx);
        bar.setMax(max);
        bar.setProgress(progress);
        bar.setContentDescription(description);
        final ColorStateList tint = ColorStateList.valueOf(
                MaterialColors.getColor(ctx, R.attr.colorPrimary, Color.RED));
        bar.setProgressTintList(tint);
        bar.setThumbTintList(tint);
        final LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, Utils.dpToPx(45));
        params.bottomMargin = Utils.dpToPx(6);
        body.addView(bar, params);
        bar.setOnSeekBarChangeListener(listener);
        return bar;
    }

    static Dialog sheet(Activity activity, CharSequence title, LinearLayout body) {
        final Context ctx = body.getContext();
        final UiMetrics ui = UiMetrics.of(activity, Utils.isTvBox(activity));
        final Dialog dialog = new Dialog(activity, android.R.style.Theme_Translucent_NoTitleBar);
        final LinearLayout outer = new LinearLayout(ctx);
        outer.setOrientation(LinearLayout.VERTICAL);
        outer.setPadding(Utils.dpToPx(10), Utils.dpToPx(10), Utils.dpToPx(10), Utils.dpToPx(10));

        final TextView header = new TextView(ctx);
        header.setText(title);
        header.setTextColor(MaterialColors.getColor(ctx, R.attr.colorOnSurface, Color.WHITE));
        header.setTextSize(21f);
        header.setTypeface(Typeface.DEFAULT_BOLD);
        header.setPadding(Utils.dpToPx(6), Utils.dpToPx(10), Utils.dpToPx(6), Utils.dpToPx(10));
        outer.addView(Dialogs.pickerHeader(ctx, ui, header));

        final ScrollView scroll = new ScrollView(ctx);
        scroll.setFillViewport(false);
        scroll.setVerticalScrollBarEnabled(true);
        scroll.addView(body);
        outer.addView(scroll, new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, 0, 1f));

        // pickerWindow owns correct TV/landscape side docking, dark surface,
        // scrim, cutouts, focusable close button and gesture inset handling.
        Dialogs.pickerWindow(activity, ui, dialog, outer);
        dialog.show();
        final View focus = firstFocusable(body);
        if (focus != null) focus.post(focus::requestFocus);
        return dialog;
    }

    private static View firstFocusable(View view) {
        if (view.isFocusable() && view.isEnabled()) return view;
        if (view instanceof ViewGroup) {
            final ViewGroup group = (ViewGroup) view;
            for (int i=0; i<group.getChildCount(); i++) {
                final View found = firstFocusable(group.getChildAt(i));
                if (found != null) return found;
            }
        }
        return null;
    }

    interface ChoiceListener { void select(int index); }

    static void chooser(Activity activity, @StringRes int title, String[] choices,
                        int selected, ChoiceListener onSelected) {
        final Context ctx = context(activity);
        final LinearLayout body = column(ctx);
        final RadioButton[] radios = new RadioButton[choices.length];
        final Dialog[] holder = new Dialog[1];
        for (int i=0; i<choices.length; i++) {
            final int index = i;
            final RadioButton radio = new RadioButton(ctx);
            radios[i] = radio;
            radio.setText(choices[i]);
            radio.setTextColor(MaterialColors.getColor(ctx, R.attr.colorOnSurface, Color.WHITE));
            radio.setTextSize(17f);
            radio.setPadding(Utils.dpToPx(8), Utils.dpToPx(8),
                    Utils.dpToPx(8), Utils.dpToPx(8));
            radio.setMinHeight(Utils.dpToPx(52));
            radio.setButtonTintList(ColorStateList.valueOf(MaterialColors.getColor(
                    ctx, R.attr.colorPrimary, Color.RED)));
            radio.setBackground(Dialogs.pickerRow(ctx, Color.TRANSPARENT));
            radio.setChecked(i == selected);
            radio.setOnClickListener(v -> {
                for (int j=0; j<radios.length; j++) radios[j].setChecked(j == index);
                onSelected.select(index);
                if (holder[0] != null) holder[0].dismiss();
            });
            body.addView(radio);
        }
        holder[0] = sheet(activity, activity.getString(title), body);
        if (selected >= 0 && selected < radios.length) {
            final RadioButton current = radios[selected];
            current.post(current::requestFocus);
        }
    }
}
