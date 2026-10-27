package com.brouken.player;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.media3.common.Effect;
import androidx.media3.effect.Brightness;
import androidx.media3.effect.Contrast;
import androidx.media3.effect.HslAdjustment;
import androidx.media3.effect.RgbAdjustment;
import java.util.ArrayList;
import java.util.List;

/**
 * ARX GPU picture grading. The controls are hosted by the native-style
 * Material 3 sheet used by all other Just+/ARX player panels.
 */
public final class ArxPictureDialog {
    private static final int[] LABELS = {
            R.string.arx_picture_brightness, R.string.arx_picture_contrast,
            R.string.arx_picture_saturation, R.string.arx_picture_warmth,
            R.string.arx_picture_lightness
    };
    private static final int[] PRESET_LABELS = {
            R.string.arx_menu_neutral, R.string.arx_picture_vivid, R.string.arx_picture_cinema,
            R.string.arx_picture_bright, R.string.arx_picture_night
    };
    private static final int[][] VALUES = {
            {0,0,0,0,0}, {6,12,24,4,2}, {-4,8,-6,5,-1},
            {12,6,8,3,6}, {-10,-8,-8,8,3}
    };
    private static final String PREFIX = "arx_picture_";

    private ArxPictureDialog() {}

    private static SharedPreferences prefs(Context c) {
        return PreferenceManager.getDefaultSharedPreferences(c);
    }

    public static int[] values(Context context) {
        SharedPreferences pref = prefs(context);
        int[] out = new int[LABELS.length];
        for (int i=0;i<out.length;i++) out[i]=pref.getInt(PREFIX+i,0);
        return out;
    }

    public static boolean active(Context context) {
        for (int x : values(context)) if (x != 0) return true;
        return false;
    }

    static List<Effect> effects(Context context) {
        int[] v = values(context);
        List<Effect> list = new ArrayList<>();
        if (v[0]!=0) list.add(new Brightness(v[0]/100f));
        if (v[1]!=0) list.add(new Contrast(v[1]/100f));
        if (v[2]!=0 || v[4]!=0)
            list.add(new HslAdjustment.Builder()
                    .adjustSaturation(v[2]).adjustLightness(v[4]/4f).build());
        if (v[3]!=0) {
            float warmth = v[3]/200f;
            list.add(new RgbAdjustment.Builder()
                    .setRedScale(1f+warmth).setBlueScale(1f-warmth).build());
        }
        return list;
    }

    public static void show(Context context, Runnable apply) {
        final Activity activity = (Activity) context;
        final Context ctx = ArxControlsUi.context(activity);
        final int[] settings = values(context);
        final LinearLayout body = ArxControlsUi.column(ctx);
        body.addView(ArxControlsUi.caption(ctx, activity.getString(R.string.arx_picture_notice)));

        final String[] presetNames = new String[PRESET_LABELS.length];
        for (int i=0;i<presetNames.length;i++) presetNames[i]=activity.getString(PRESET_LABELS[i]);
        final int[] chosen = {-1};
        for (int i=0;i<VALUES.length;i++) {
            if (java.util.Arrays.equals(settings, VALUES[i])) {
                chosen[0]=i;
                break;
            }
        }
        final TextView[] labels = new TextView[settings.length];
        final SeekBar[] bars = new SeekBar[settings.length];
        final TextView[] selectPreset = new TextView[1];
        final Runnable save = () -> {
            SharedPreferences.Editor ed = prefs(context).edit();
            for (int i=0;i<settings.length;i++) ed.putInt(PREFIX+i,settings[i]);
            ed.apply();
            if (apply != null) apply.run();
        };
        final Runnable refresh = () -> {
            selectPreset[0].setText(activity.getString(R.string.arx_ui_preset,
                    chosen[0] < 0 ? activity.getString(R.string.arx_picture_custom)
                            : presetNames[chosen[0]]));
            for (int i=0;i<settings.length;i++) {
                bars[i].setProgress(settings[i]+50);
                labels[i].setText(activity.getString(R.string.arx_ui_number,
                        activity.getString(LABELS[i]), settings[i]));
            }
        };

        selectPreset[0]=ArxControlsUi.action(ctx, "", () -> {
            ArxControlsUi.chooser(activity, R.string.arx_picture_preset, presetNames, chosen[0], index -> {
                chosen[0]=index;
                System.arraycopy(VALUES[index],0,settings,0,settings.length);
                refresh.run();
                save.run();
            });
        });
        body.addView(selectPreset[0]);

        for (int i=0;i<settings.length;i++) {
            final int index=i;
            labels[i]=ArxControlsUi.valueLabel(ctx, "");
            bars[i]=ArxControlsUi.seekBar(ctx, body, 100, settings[i]+50,
                    activity.getString(LABELS[i]),
                    new SeekBar.OnSeekBarChangeListener() {
                        public void onStartTrackingTouch(SeekBar bar) {}
                        public void onStopTrackingTouch(SeekBar bar) {}
                        public void onProgressChanged(SeekBar bar,int p,boolean fromUser) {
                            if (!fromUser) return;
                            settings[index]=p-50;
                            chosen[0]=-1;
                            labels[index].setText(activity.getString(R.string.arx_ui_number,
                                    activity.getString(LABELS[index]), settings[index]));
                            selectPreset[0].setText(activity.getString(R.string.arx_ui_preset,
                                    activity.getString(R.string.arx_picture_custom)));
                            save.run();
                        }
                    });
            // Labels lead sliders semantically; add at the right index without
            // detaching the SeekBar after its listener and accessibility setup.
            body.removeView(bars[i]);
            body.addView(labels[i]);
            body.addView(bars[i]);
        }
        refresh.run();
        ArxControlsUi.sheet(activity, activity.getString(R.string.arx_picture_title), body);
    }
}
