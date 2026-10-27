package com.brouken.player;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

/**
 * Debug-only Android TV probe of the nested Just+ playlist API. This is a
 * *launcher*, not a replacement for the app's player. It sends two genuine
 * Parcelable Bundle playlist entries to the same exported PlayerActivity that
 * Lampa/third-party callers address. The target package is the actual APK's
 * installed ID, allowing the parallel variant to be tested independently.
 *
 * adb shell am start -W -n com.lampaua.player.arx/com.brouken.player.PlaylistContractSmokeActivity \\
 *     --es fixture_url http://127.0.0.1:8765/tv-h264.mp4
 */
public final class PlaylistContractSmokeActivity extends Activity {
    public static final String TAG = "ARX_PLAYLIST_SMOKE";
    private static final int REQUEST = 4011;

    public static class Callback extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.i(TAG, "PLAYLIST_CALLBACK end_by=" + intent.getStringExtra("end_by")
                    + " index=" + intent.getIntExtra("index", -1));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final String url = getIntent().getStringExtra("fixture_url") == null
                ? "http://127.0.0.1:8765/tv-h264.mp4"
                : getIntent().getStringExtra("fixture_url");
        final Bundle first = new Bundle();
        first.putString("uri", url);
        first.putString("title", "ARX playlist episode A");
        first.putInt("season", 1);
        first.putInt("episode", 1);
        final Bundle second = new Bundle();
        second.putString("uri", url);
        second.putString("title", "ARX playlist episode B");
        second.putInt("season", 1);
        second.putInt("episode", 2);
        final Bundle playlist = new Bundle();
        playlist.putString("title", "ARX playlist Lampa contract test");
        // Actual Lampa v1.13.4 places the SHOW logo in the ROOT playlist
        // Bundle (not in item Bundles). This probes the corrected startup art.
        playlist.putString("logo", "http://127.0.0.1:8765/tv-series-logo.png");
        playlist.putInt("start_index", 0);
        playlist.putString("resume_mode", "never");
        playlist.putParcelableArray("items", new Bundle[]{first, second});

        final Intent callback = new Intent(this, Callback.class);
        final int flags = PendingIntent.FLAG_UPDATE_CURRENT
                | (Build.VERSION.SDK_INT >= 31 ? PendingIntent.FLAG_MUTABLE : 0);
        playlist.putParcelable("result_callback",
                PendingIntent.getBroadcast(this, REQUEST, callback, flags));

        final Intent view = new Intent(Intent.ACTION_VIEW)
                .setComponent(new ComponentName(getPackageName(),
                        "com.brouken.player.PlayerActivity"))
                .setDataAndType(Uri.parse(url), "video/mp4")
                .putExtra("playlist", playlist);
        Log.i(TAG, "PLAYLIST_LAUNCH target=" + view.getComponent().flattenToShortString()
                + " items=" + ((android.os.Parcelable[]) playlist.getParcelableArray("items")).length
                + " fixture=" + url);
        try {
            startActivityForResult(view, REQUEST);
        } catch (RuntimeException e) {
            Log.e(TAG, "PLAYLIST_LAUNCH_FAILED", e);
            finish();
        }
    }

    @Override
    protected void onActivityResult(int request, int result, Intent data) {
        super.onActivityResult(request, result, data);
        if (request == REQUEST) {
            Bundle extras = data == null ? null : data.getExtras();
            Log.i(TAG, "PLAYLIST_RESULT resultCode=" + result
                    + " end_by=" + (extras == null ? "none" : extras.getString("end_by"))
                    + " index=" + (extras == null ? -1 : extras.getInt("index", -1)));
        }
        finish();
    }
}
