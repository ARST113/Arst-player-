package com.brouken.player;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

/**
 * Debug-only Android emulator probe. Simulates the nested playlist Bundle
 * sent by the Lampa Android host to ARX TPlayer.
 * It does not ship in release APKs.
 */
public final class MetadataSmokeActivity extends Activity {
    private static final String TAG = "ARX_METADATA_SMOKE";
    private static final String VIDEO = "http://127.0.0.1:8765/episode.mp4";
    private static final String STILL_1 =
        "https://lampac.art/tmdb/img/t/p/w500//o4IX9Mm0kpLITVANJMx7inyEUaY.jpg?uid=ot54ujfw";
    private static final String STILL_2 =
        "https://lampac.art/tmdb/img/t/p/w500//l0GJx3IR8YasbztTJi5uK0XqkEo.jpg?uid=ot54ujfw";
    private static final String POSTER =
        "https://lampac.art/tmdb/img/t/p/w500//oYuLEt3zVCKq57qu2F8dT7NIa6f.jpg?uid=ot54ujfw";
    private static final String BACKDROP =
        "https://lampac.art/tmdb/img/t/p/w1280//2OMB0ynKlyIenMJWI2Dy9IWT4c.jpg?uid=ot54ujfw";

    private Bundle item(String title, String episodeTitle, String thumb, int season, int episode, String tmdb) {
        Bundle b = new Bundle();
        b.putString("uri", VIDEO);
        b.putString("title", title);
        b.putString("episode_title", episodeTitle);
        b.putString("thumbnail", thumb);
        b.putString("logo", POSTER);
        b.putString("background", BACKDROP);
        b.putString("tmdb_id", tmdb);
        b.putString("imdb_id", "tt0944947");
        b.putInt("season", season);
        b.putInt("episode", episode);
        b.putLong("position_ms", 3000L);
        return b;
    }

    @Override
    protected void onCreate(Bundle state) {
        super.onCreate(state);
        String scenario = getIntent().getStringExtra("scenario");
        if (scenario == null) scenario = "series";
        Bundle root = new Bundle();
        Bundle[] items;
        switch (scenario) {
            case "movie":
                root.putString("title", "Начало");
                root.putString("logo", POSTER);
                root.putString("background", BACKDROP);
                items = new Bundle[]{item("Начало", null, POSTER, 0, 0, "27205")};
                break;
            case "anime":
                root.putString("title", "Клинок, рассекающий демонов");
                root.putString("logo", POSTER);
                root.putString("background", BACKDROP);
                items = new Bundle[]{
                    item("Жестокость", "Жестокость", STILL_1, 1, 1, "85937"),
                    item("Взращиватель Урокодаки Саконджи", "Взращиватель Урокодаки Саконджи", STILL_2, 1, 2, "85937")
                };
                break;
            default:
                scenario = "series";
                root.putString("title", "Игра престолов");
                root.putString("logo", POSTER);
                root.putString("background", BACKDROP);
                items = new Bundle[]{
                    item("Зима близко", "Зима близко", STILL_1, 1, 1, "1399"),
                    item("Королевский тракт", "Королевский тракт", STILL_2, 1, 2, "1399")
                };
        }
        root.putParcelableArray("items", items);
        root.putInt("start_index", getIntent().getIntExtra("index", 0));
        root.putString("resume_mode", "never");

        PlaylistApi.Parsed parsed = PlaylistApi.parse(root);
        if (!parsed.ok()) {
            Log.e(TAG, "PARSE_FAIL " + scenario + " error=" + parsed.error);
            finish();
            return;
        }
        Log.i(TAG, "PARSE_OK scenario=" + scenario +
            " rootTitle=" + parsed.playlist.title +
            " startIndex=" + parsed.playlist.startIndex +
            " itemCount=" + parsed.playlist.items.size());
        for (int i=0; i<parsed.playlist.items.size(); i++) {
            PlaylistApi.Item p = parsed.playlist.items.get(i);
            Log.i(TAG, "ITEM " + i + " title=" + p.title +
                " episodeTitle=" + p.episodeTitle +
                " thumbnail=" + p.thumbnail +
                " logo=" + p.logo +
                " background=" + p.background +
                " tmdbId=" + p.tmdbId +
                " season=" + p.season + " episode=" + p.episode +
                " positionMs=" + p.positionMs);
        }

        Intent player = new Intent(Intent.ACTION_VIEW);
        player.setClass(this, PlayerActivity.class);
        player.putExtra("playlist", root);
        startActivityForResult(player, 41);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 41) {
            Log.i(TAG, "RETURN resultCode=" + resultCode +
                " index=" + (data == null ? "none" : data.getIntExtra("index", -1)) +
                " positionSec=" + (data == null ? "none" : data.getIntExtra("position_sec", -1)));
            finish();
        }
    }
}
