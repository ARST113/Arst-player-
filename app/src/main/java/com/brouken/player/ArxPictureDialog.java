package com.brouken.player;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
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
 * GPU picture grading for ordinary video, preserving original output for
 * secure DRM / Dolby Vision and software-decoded video.
 * Only Media3-provided GL effects are used; user can disable instantly.
 */
public final class ArxPictureDialog {
    private static final String[] NAMES = {"Brightness", "Contrast", "Saturation", "Warmth", "Lightness"};
    private static final String[] PRESETS = {"Neutral", "Vivid", "Cinema", "Bright", "Night"};
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
        int[] out = new int[NAMES.length];
        for (int i=0;i<out.length;i++)out[i]=pref.getInt(PREFIX+i,0);
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
        final int[] settings = values(context);
        LinearLayout layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.VERTICAL);
        final int pad = (int)(16*context.getResources().getDisplayMetrics().density);
        layout.setPadding(pad,pad,pad,pad);
        final TextView notice = new TextView(context);
        notice.setText("Picture effects require a GPU path. DRM, Dolby Vision and software video are left unchanged.");
        layout.addView(notice);
        Button presets = new Button(context);
        presets.setText("Preset: Custom — tap to select");
        layout.addView(presets);
        SeekBar[] bars = new SeekBar[settings.length];
        TextView[] labels = new TextView[settings.length];
        final Runnable save = () -> {
            SharedPreferences.Editor ed = prefs(context).edit();
            for(int i=0;i<settings.length;i++)ed.putInt(PREFIX+i,settings[i]);
            ed.apply();
            if (apply!=null) apply.run();
        };
        for(int i=0;i<settings.length;i++){
            final int index=i;
            TextView label=new TextView(context);
            labels[i]=label; layout.addView(label);
            SeekBar bar=new SeekBar(context);bars[i]=bar;
            bar.setMax(100);bar.setProgress(settings[i]+50);
            bar.setContentDescription(NAMES[i]);layout.addView(bar);
            label.setText(NAMES[i]+": "+settings[i]);
            bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
                public void onStartTrackingTouch(SeekBar s){}
                public void onStopTrackingTouch(SeekBar s){}
                public void onProgressChanged(SeekBar s,int p,boolean user){
                    if(!user)return;
                    settings[index]=p-50;
                    labels[index].setText(NAMES[index]+": "+settings[index]);
                    presets.setText("Preset: Custom — tap to select");
                    save.run();
                }
            });
        }
        final int[] presetIndex={-1};
        presets.setOnClickListener(v->{
            presetIndex[0]=(presetIndex[0]+1)%PRESETS.length;
            System.arraycopy(VALUES[presetIndex[0]],0,settings,0,settings.length);
            for(int i=0;i<settings.length;i++){
                bars[i].setProgress(settings[i]+50);
                labels[i].setText(NAMES[i]+": "+settings[i]);
            }
            presets.setText("Preset: "+PRESETS[presetIndex[0]]);
            save.run();
        });
        ScrollView scroll=new ScrollView(context); scroll.addView(layout);
        new AlertDialog.Builder(context).setTitle("Picture adjustments")
                .setView(scroll).setPositiveButton(android.R.string.ok,(d,w)->save.run()).show();
    }
}
