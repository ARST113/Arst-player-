package com.brouken.player;

import android.app.AlertDialog;
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

    void show(){
        load();
        final LinearLayout layout=new LinearLayout(activity);
        layout.setOrientation(LinearLayout.VERTICAL);
        int pad=(int)(16*activity.getResources().getDisplayMetrics().density);
        layout.setPadding(pad,pad,pad,pad);
        final TextView modeLabel=new TextView(activity);
        modeLabel.setText("Current mode: "+MODES[mode]);
        layout.addView(modeLabel);
        for(int i=0;i<3;i++){
            final int index=i;
            final TextView label=new TextView(activity);
            int value=i==0?brightness:i==1?reach:speed;
            label.setText((i==0?"Brightness":i==1?"Reach":"Response")+": "+value+"%");
            layout.addView(label);
            SeekBar bar=new SeekBar(activity);
            bar.setMax(100);bar.setProgress(value);layout.addView(bar);
            bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
                public void onStartTrackingTouch(SeekBar b){}
                public void onStopTrackingTouch(SeekBar b){}
                public void onProgressChanged(SeekBar b,int value,boolean user){
                    if(!user)return;
                    if(index==0) brightness=value;
                    else if(index==1)reach=value;
                    else speed=value;
                    prefs().edit().putInt(index==0?BRIGHTNESS:index==1?REACH:SPEED,value).apply();
                    label.setText((index==0?"Brightness":index==1?"Reach":"Response")+": "+value+"%");
                }
            });
        }
        AlertDialog dialog=new AlertDialog.Builder(activity)
                .setTitle("Ambient light")
                .setSingleChoiceItems(MODES,mode,(d,which)->{
                    mode=which;
                    prefs().edit().putInt(MODE,which).apply();
                    modeLabel.setText("Current mode: "+MODES[which]);
                    d.dismiss();
                    if(which==0) hide();
                    if(!running)start();
                })
                .setView(layout)
                .setNegativeButton(android.R.string.cancel,null)
                .create();
        dialog.show();
    }
}
