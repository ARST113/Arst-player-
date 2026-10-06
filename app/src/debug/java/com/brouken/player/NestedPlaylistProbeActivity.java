package com.brouken.player;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

public final class NestedPlaylistProbeActivity extends Activity {
    @Override
    protected void onCreate(Bundle state) {
        super.onCreate(state);

        Bundle first = new Bundle();
        first.putString("uri", "http://10.0.2.2:8765/one.mp4");
        first.putString("episode_title", "Episode One");
        first.putInt("season", 1);
        first.putInt("episode", 1);

        Bundle second = new Bundle();
        second.putString("uri", "http://10.0.2.2:8765/two.mp4");
        second.putString("episode_title", "Episode Two");
        second.putInt("season", 1);
        second.putInt("episode", 2);
        second.putInt("position_sec", 0);

        Bundle playlist = new Bundle();
        playlist.putString("title", "Nested Playlist Probe");
        playlist.putInt("start_index", 1);
        playlist.putParcelableArray("items", new Bundle[]{first, second});
        playlist.putInt("report_interval_sec", 120);

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setClass(this, PlayerActivity.class);
        intent.setDataAndType(Uri.parse("http://10.0.2.2:8765/two.mp4"), "video/*");
        intent.putExtra("playlist", playlist);
        startActivity(intent);
        finish();
    }
}
