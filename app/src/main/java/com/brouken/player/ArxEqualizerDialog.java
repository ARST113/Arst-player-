package com.brouken.player;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.TextView;

/** In-player and settings EQ editor. All values persist and update decoded audio live. */
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
    private static final String[] PRESET_NAMES = {
            "Flat", "Bass", "Cinema", "Voice", "Treble", "Music"
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
        for (int i = 0; i < values.length; i++) values[i] = p.getInt(KEY_PREFIX + i, 0);
        return values;
    }

    public static void show(Context context, ArxEqualizerProcessor active) {
        final SharedPreferences preferences = prefs(context);
        final float[] levels = bands(context);
        final boolean[] on = {enabled(context)};
        final LinearLayout main = new LinearLayout(context);
        main.setOrientation(LinearLayout.VERTICAL);
        int pad = Math.round(16 * context.getResources().getDisplayMetrics().density);
        main.setPadding(pad,pad,pad,pad);

        CheckBox enable = new CheckBox(context);
        enable.setText("Enable equalizer (decoded PCM)");
        enable.setChecked(on[0]);
        main.addView(enable);

        Button preset = new Button(context);
        preset.setText("Preset: Custom  —  tap to change");
        main.addView(preset);

        final SeekBar[] sliders = new SeekBar[levels.length];
        final TextView[] captions = new TextView[levels.length];
        final Runnable update = () -> {
            SharedPreferences.Editor editor = preferences.edit().putBoolean(KEY_ENABLED,on[0]);
            for (int i = 0; i < levels.length; i++)
                editor.putInt(KEY_PREFIX + i, Math.round(levels[i]));
            editor.apply();
            if (active != null) active.setBands(on[0], levels);
        };

        enable.setOnCheckedChangeListener((button, checked) -> {
            on[0] = checked;
            update.run();
        });
        for (int i = 0; i < levels.length; i++) {
            final int index = i;
            TextView caption = new TextView(context);
            captions[i] = caption;
            main.addView(caption);
            SeekBar seek = new SeekBar(context);
            sliders[i] = seek;
            seek.setMax(24);
            seek.setProgress(Math.round(levels[i])+12);
            seek.setContentDescription(ArxEqualizerProcessor.HZ[i]+" Hz");
            main.addView(seek);
            caption.setText(ArxEqualizerProcessor.HZ[i]+" Hz  "+Math.round(levels[i])+" dB");
            seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                public void onStartTrackingTouch(SeekBar bar) {}
                public void onStopTrackingTouch(SeekBar bar) {}
                public void onProgressChanged(SeekBar bar, int progress, boolean fromUser) {
                    if (!fromUser) return;
                    levels[index] = progress - 12;
                    captions[index].setText(ArxEqualizerProcessor.HZ[index]+" Hz  "+
                            Math.round(levels[index])+" dB");
                    preset.setText("Preset: Custom  —  tap to change");
                    update.run();
                }
            });
        }

        final int[] presetIndex = {-1};
        preset.setOnClickListener(v -> {
            presetIndex[0] = (presetIndex[0]+1) % PRESETS.length;
            float[] values = PRESETS[presetIndex[0]];
            System.arraycopy(values,0,levels,0,levels.length);
            for (int i=0;i<levels.length;i++) {
                sliders[i].setProgress(Math.round(levels[i])+12);
                captions[i].setText(ArxEqualizerProcessor.HZ[i]+" Hz  "+
                        Math.round(levels[i])+" dB");
            }
            preset.setText("Preset: "+PRESET_NAMES[presetIndex[0]]);
            update.run();
        });

        ScrollView scroll = new ScrollView(context);
        scroll.addView(main);
        new AlertDialog.Builder(context)
                .setTitle("Equalizer · 10 bands")
                .setView(scroll)
                .setPositiveButton(android.R.string.ok,(d,w)->update.run())
                .show();
    }
}
