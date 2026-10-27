package com.brouken.player;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.preference.PreferenceManager;
import android.view.Gravity;
import android.view.PixelCopy;
import android.view.SurfaceView;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;

/**
 * Low-resolution ambient color sampler. Four light modes and adaptive rate.
 * Does not read secure (DRM) or Dolby Vision frames. Unsupported surfaces
 * fail closed, without modifying the video output, codec or decoder.
 */
final class ArxAmbientLight {
    private static final String MODE = "arx_ambient_mode";
    private static final String BRIGHTNESS = "arx_ambient_brightness";
    private static final String REACH = "arx_ambient_reach";
    private static final String SPEED = "arx_ambient_speed";
    private static final String[] MODES = {"Off", "Glow", "Cinema", "Echo", "Mirror"};
    private final PlayerActivity activity;
    private final View surface;
    private final CoordinatorLayout root;
    private final View[] edge = new View[4];
    private final Handler handler = new Handler(Looper.getMainLooper());
    private final Bitmap snapshot = Bitmap.createBitmap(32,18, Bitmap.Config.ARGB_8888);
    private boolean running, pending;
    private int mode;
    private int brightness, reach, speed;
    private int[] oldColors = new int[]{Color.BLACK,Color.BLACK,Color.BLACK,Color.BLACK};
    private int slowFrames;

    ArxAmbientLight(PlayerActivity activity, View surface) {
        this.activity=activity; this.surface=surface;
        root=activity.findViewById(R.id.coordinatorLayout);
        load();
        for(int i=0;i<4;i++) {
            edge[i]=new View(activity);
            edge[i].setFocusable(false);
            edge[i].setImportantForAccessibility(View.IMPORTANT_FOR_ACCESSIBILITY_NO);
            root.addView(edge[i],1,new CoordinatorLayout.LayoutParams(0,0));
        }
        hide();
    }

    private SharedPreferences prefs(){
        return PreferenceManager.getDefaultSharedPreferences(activity);
    }

    private void load(){
        SharedPreferences p=prefs();
        mode=p.getInt(MODE,0);
        brightness=p.getInt(BRIGHTNESS,70);
        reach=p.getInt(REACH,60);
        speed=p.getInt(SPEED,60);
    }

    boolean active(){return mode>0 && Build.VERSION.SDK_INT>=26;}

    private void hide(){for(View v:edge)v.setVisibility(View.GONE);}

    void start(){
        if(running) return;
        running=true;
        handler.post(poll);
    }
    void stop(){
        running=false;
        handler.removeCallbacks(poll);
        hide();
    }

    private final Runnable poll=new Runnable(){
        @Override public void run(){
            if(!running)return;
            final Format format=PlayerActivity.player==null?null:PlayerActivity.player.getVideoFormat();
            if(!active() || format==null || PlayerActivity.player == null ||
                    !PlayerActivity.player.isPlaying() ||
                    MimeTypes.VIDEO_DOLBY_VISION.equals(format.sampleMimeType) ||
                    ArxDrm.isProtected(PlayerActivity.player.getCurrentMediaItem())) {
                hide();
                handler.postDelayed(this,500);
                return;
            }
            layoutBars(format);
            if(!pending && Build.VERSION.SDK_INT>=26) {
                long started=System.currentTimeMillis();
                pending=true;
                try {
                    if(surface instanceof SurfaceView) {
                        PixelCopy.request((SurfaceView)surface,snapshot, result-> {
                            pending=false;
                            if(running && result==PixelCopy.SUCCESS) colors();
                            if(System.currentTimeMillis()-started>300)slowFrames++;
                            else slowFrames=Math.max(0,slowFrames-1);
                        },handler);
                    } else if(surface instanceof TextureView) {
                        Bitmap bitmap=((TextureView)surface).getBitmap(32,18);
                        pending=false;
                        if(bitmap!=null){
                            for(int x=0;x<32;x++)for(int y=0;y<18;y++)
                                snapshot.setPixel(x,y,bitmap.getPixel(x,y));
                            bitmap.recycle();
                            colors();
                        }
                    } else pending=false;
                }catch(RuntimeException error){
                    pending=false;
                    Utils.log("ambient capture disabled: "+error.getClass().getSimpleName());
                    slowFrames=30;
                }
            }
            // Reduce refresh rate under load, but keep sampling instead of freezing on one frame.
            int delay=200+(100-speed)*5+(Math.min(15,slowFrames)*140);
            handler.postDelayed(this,delay);
        }
    };

    private void layoutBars(Format video) {
        int width=root.getWidth(),height=root.getHeight();
        if(width<10||height<10||video.width<=0||video.height<=0){hide();return;}
        double ratio=(double)video.width/video.height;
        double frameRatio=(double)width/height;
        int band=(int)(Math.max(0, frameRatio>ratio
                ? (width-height*ratio)/2 : (height-width/ratio)/2));
        if(band<2){hide();return;}
        int spread=Math.max(1,(int)(band*reach/100.0));
        for(int i=0;i<4;i++){
            boolean vertical=i<2;
            boolean show=frameRatio>ratio?vertical:!vertical;
            if(!show){edge[i].setVisibility(View.GONE);continue;}
            edge[i].setVisibility(View.VISIBLE);
            CoordinatorLayout.LayoutParams params=new CoordinatorLayout.LayoutParams(
                    vertical?spread:width,vertical?height:spread);
            params.gravity=i==0?Gravity.LEFT:i==1?Gravity.RIGHT:i==2?Gravity.TOP:Gravity.BOTTOM;
            edge[i].setLayoutParams(params);
        }
    }

    private void colors() {
        int[] c=new int[4];
        int[][] coords={{1,8},{30,8},{16,1},{16,16}};
        for(int i=0;i<4;i++){
            long red=0,green=0,blue=0;
            int count=0;
            for(int a=-3;a<=3;a++){
                for(int b=-2;b<=2;b++){
                    int x=Math.max(0,Math.min(31,coords[i][0]+(i<2?b:a)));
                    int y=Math.max(0,Math.min(17,coords[i][1]+(i<2?a:b)));
                    int p=snapshot.getPixel(x,y);
                    red+=Color.red(p);green+=Color.green(p);blue+=Color.blue(p);count++;
                }
            }
            float f=(brightness/100f)*(mode==2?.65f:mode==3?.85f:1f);
            int r=(int)Math.min(255,(red/count)*f);
            int g=(int)Math.min(255,(green/count)*f);
            int bl=(int)Math.min(255,(blue/count)*f);
            c[i]=Color.rgb(r,g,bl);
        }
        if(mode==3){int v=c[0];c[0]=c[1];c[1]=v;}
        if(mode==4){int v=c[2];c[2]=c[3];c[3]=v;}
        for(int i=0;i<4;i++){
            oldColors[i]=mix(oldColors[i],c[i],.22f+speed/150f);
            int full=oldColors[i];
            GradientDrawable.Orientation orientation=i==0
                    ?GradientDrawable.Orientation.LEFT_RIGHT
                    :i==1?GradientDrawable.Orientation.RIGHT_LEFT
                    :i==2?GradientDrawable.Orientation.TOP_BOTTOM
                    :GradientDrawable.Orientation.BOTTOM_TOP;
            edge[i].setBackground(new GradientDrawable(orientation,
                    new int[]{Color.TRANSPARENT,full}));
        }
    }

    private static int mix(int from,int to,float t){
        t=Math.min(1f,t);
        return Color.rgb((int)(Color.red(from)*(1-t)+Color.red(to)*t),
                (int)(Color.green(from)*(1-t)+Color.green(to)*t),
                (int)(Color.blue(from)*(1-t)+Color.blue(to)*t));
    }

    void show() {
        load();
        final android.content.Context ctx = ArxControlsUi.context(activity);
        final LinearLayout body = ArxControlsUi.column(ctx);
        body.addView(ArxControlsUi.caption(ctx,
                activity.getString(R.string.arx_ambient_notice)));
        body.addView(ArxControlsUi.caption(ctx, activity.getString(R.string.arx_ambient_mode)));
        final TextView label = ArxControlsUi.valueLabel(ctx, "");
        body.addView(label);

        final int[] modeLabels = {
                R.string.arx_menu_off, R.string.arx_ambient_glow,
                R.string.arx_ambient_cinema, R.string.arx_ambient_echo,
                R.string.arx_ambient_mirror
        };
        final android.widget.RadioButton[] radios =
                new android.widget.RadioButton[modeLabels.length];
        final Runnable refresh = () -> {
            label.setText(activity.getString(R.string.arx_ui_mode,
                    activity.getString(modeLabels[mode])));
            for (int j=0;j<radios.length;j++) {
                if (radios[j] != null) radios[j].setChecked(j == mode);
            }
        };
        for (int i=0;i<modeLabels.length;i++) {
            final int index=i;
            final android.widget.RadioButton radio = new android.widget.RadioButton(ctx);
            radios[i]=radio;
            radio.setText(activity.getString(modeLabels[i]));
            radio.setTextSize(17f);
            radio.setTextColor(com.google.android.material.color.MaterialColors.getColor(ctx,
                    R.attr.colorOnSurface, Color.WHITE));
            radio.setButtonTintList(android.content.res.ColorStateList.valueOf(
                    com.google.android.material.color.MaterialColors.getColor(ctx,
                            R.attr.colorPrimary, Color.RED)));
            radio.setPadding(Utils.dpToPx(8), Utils.dpToPx(5),
                    Utils.dpToPx(8), Utils.dpToPx(5));
            radio.setMinHeight(Utils.dpToPx(50));
            radio.setBackground(Dialogs.pickerRow(ctx, Color.TRANSPARENT));
            radio.setOnClickListener(v -> {
                mode=index;
                prefs().edit().putInt(MODE,index).apply();
                refresh.run();
                if (mode==0) hide();
                else if (!running) start();
            });
            body.addView(radio);
        }
        refresh.run();
        final int[] sliderLabels = {
                R.string.arx_picture_brightness, R.string.arx_ambient_reach,
                R.string.arx_ambient_response
        };
        for (int i=0;i<sliderLabels.length;i++) {
            final int index=i;
            int initial=i==0?brightness:i==1?reach:speed;
            final TextView caption=ArxControlsUi.valueLabel(ctx,
                    activity.getString(R.string.arx_ui_percent,
                            activity.getString(sliderLabels[i]),initial));
            body.addView(caption);
            ArxControlsUi.seekBar(ctx,body,100,initial,
                    activity.getString(sliderLabels[i]),new SeekBar.OnSeekBarChangeListener() {
                public void onStartTrackingTouch(SeekBar bar) {}
                public void onStopTrackingTouch(SeekBar bar) {}
                public void onProgressChanged(SeekBar bar,int value,boolean user) {
                    if(!user) return;
                    if(index==0) brightness=value;
                    else if(index==1) reach=value;
                    else speed=value;
                    prefs().edit().putInt(index==0?BRIGHTNESS:index==1?REACH:SPEED,value).apply();
                    caption.setText(activity.getString(R.string.arx_ui_percent,
                            activity.getString(sliderLabels[index]),value));
                }
            });
        }
        ArxControlsUi.sheet(activity,activity.getString(R.string.arx_ambient_title),body);
    }
}
