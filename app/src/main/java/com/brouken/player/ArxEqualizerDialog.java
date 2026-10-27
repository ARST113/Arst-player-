package com.brouken.player;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.google.android.material.materialswitch.MaterialSwitch;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.content.res.ColorStateList;
import android.graphics.Color;
import com.google.android.material.color.MaterialColors;

/**
 * Ten-band PCM equalizer editor hosted in the native player side sheet.
 * Preset/band preferences remain byte-for-byte compatible with earlier ARX.
 */
public final class ArxEqualizerDialog {
    private static final String KEY_ENABLED = "arx_eq_enabled";
    private static final String KEY_PREFIX = "arx_eq_band_";
    private static final float[][] PRESETS = {
            {0,0,0,0,0,0,0,0,0,0},
            {5,5,4,2,0,0,0,1,1,1},
            {0,0,1,3,4,3,2,1,0,0},
            {0,0,0,1,2,3,3,2,1,0},
            {3,3,2,0,-1,-1,0,2,3,3},
            {1,2,2,1,0,0,1,2,2,2}
    };
    private static final int[] PRESET_LABELS = {
            R.string.arx_eq_flat, R.string.arx_eq_bass, R.string.arx_eq_cinema,
            R.string.arx_eq_voice, R.string.arx_eq_treble, R.string.arx_eq_music
    };

    private ArxEqualizerDialog() {}

    private static SharedPreferences prefs(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static boolean enabled(Context c) {
        return prefs(c).getBoolean(KEY_ENABLED, false);
    }

    public static float[] bands(Context c) {
        float[] values = new float[ArxEqualizerProcessor.HZ.length];
        SharedPreferences p = prefs(c);
        for (int i=0; i<values.length; i++) values[i]=p.getInt(KEY_PREFIX+i,0);
        return values;
    }

    public static void show(Context context, ArxEqualizerProcessor active) {
        final Activity activity = (Activity) context;
        final Context ctx = ArxControlsUi.context(activity);
        final SharedPreferences prefs = prefs(activity);
        final float[] levels = bands(activity);
        final boolean[] on = {enabled(activity)};
        final LinearLayout body = ArxControlsUi.column(ctx);
        body.addView(ArxControlsUi.caption(ctx, activity.getString(R.string.arx_eq_notice)));

        // Material 3 switch row: the upstream pane presents this as a toggle,
        // not an old Android checkbox. Keeps full remote-focus semantics.
        final MaterialSwitch enable = new MaterialSwitch(ctx);
        enable.setText(activity.getString(R.string.arx_eq_enable));
        enable.setTextSize(16f);
        enable.setTextColor(MaterialColors.getColor(ctx, R.attr.colorOnSurface, Color.WHITE));
        enable.setButtonTintList(ColorStateList.valueOf(
                MaterialColors.getColor(ctx, R.attr.colorPrimary, Color.RED)));
        enable.setChecked(on[0]);
        enable.setMinHeight(Utils.dpToPx(56));
        enable.setPadding(Utils.dpToPx(14), Utils.dpToPx(5),
                Utils.dpToPx(14), Utils.dpToPx(5));
        enable.setBackground(Dialogs.pickerRow(ctx, Color.TRANSPARENT));
        enable.setFocusable(true);
        body.addView(enable);
        body.addView(ArxControlsUi.caption(ctx, activity.getString(R.string.arx_eq_preset)));

        final String[] presetNames = new String[PRESET_LABELS.length];
        for (int i=0;i<presetNames.length;i++) presetNames[i]=activity.getString(PRESET_LABELS[i]);
        final int[] chosen = {-1};
        for (int i=0;i<PRESETS.length;i++) {
            if (java.util.Arrays.equals(levels, PRESETS[i])) { chosen[0]=i; break; }
        }
        final TextView[] labels = new TextView[levels.length];
        final SeekBar[] bars = new SeekBar[levels.length];
        final TextView[] presetButton = new TextView[1];
        final Runnable save = () -> {
            SharedPreferences.Editor editor = prefs.edit().putBoolean(KEY_ENABLED,on[0]);
            for (int i=0;i<levels.length;i++) editor.putInt(KEY_PREFIX+i,Math.round(levels[i]));
            editor.apply();
            if (active != null) active.setBands(on[0], levels);
        };
        enable.setOnCheckedChangeListener((button,checked) -> {
            on[0]=checked;
            save.run();
        });
        final Runnable refresh = () -> {
            presetButton[0].setText(activity.getString(R.string.arx_ui_preset,
                    chosen[0] < 0 ? activity.getString(R.string.arx_eq_custom)
                            : presetNames[chosen[0]]));
            for (int i=0;i<levels.length;i++) {
                bars[i].setProgress(Math.round(levels[i])+12);
                labels[i].setText(ArxEqualizerProcessor.HZ[i]+" Hz  "+
                        Math.round(levels[i])+" dB");
            }
        };

        presetButton[0]=ArxControlsUi.action(ctx, "", () ->
            ArxControlsUi.chooser(activity, R.string.arx_eq_preset, presetNames, chosen[0], index -> {
                chosen[0]=index;
                System.arraycopy(PRESETS[index],0,levels,0,levels.length);
                refresh.run();
                save.run();
            }));
        body.addView(presetButton[0]);
        // Each band is independently focusable through the native TV
        // SeekBar; a focused row shows frequency and signed gain in dB.
        body.addView(ArxControlsUi.caption(ctx, activity.getString(R.string.arx_eq_bands)));

        for (int i=0;i<levels.length;i++) {
            final int index=i;
            labels[i]=ArxControlsUi.valueLabel(ctx, "");
            body.addView(labels[i]);
            bars[i]=ArxControlsUi.seekBar(ctx, body,24,Math.round(levels[i])+12,
                    ArxEqualizerProcessor.HZ[i]+" Hz",new SeekBar.OnSeekBarChangeListener() {
                public void onStartTrackingTouch(SeekBar bar) {}
                public void onStopTrackingTouch(SeekBar bar) {}
                public void onProgressChanged(SeekBar bar,int p,boolean fromUser) {
                    if (!fromUser) return;
                    levels[index]=p-12;
                    chosen[0]=-1;
                    labels[index].setText(ArxEqualizerProcessor.HZ[index]+" Hz  "+
                            Math.round(levels[index])+" dB");
                    presetButton[0].setText(activity.getString(R.string.arx_ui_preset,
                            activity.getString(R.string.arx_eq_custom)));
                    save.run();
                }
            });
        }
        refresh.run();
        ArxControlsUi.sheet(activity, activity.getString(R.string.arx_eq_title), body);
    }
}
