package com.brouken.player;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

/** Session visit journal used by the Just+ Player 2.1.2 launcher API. */
final class PlaylistSessionJournal {

    private static final int MAX_VISITS = 500;

    static final class Visit {
        final int index;
        final long startedAtSec;
        long endedAtSec;
        long positionMs;
        long durationMs;

        Visit(int index, long positionMs, long durationMs) {
            this(index, nowSec(), nowSec(), positionMs, durationMs);
        }

        Visit(int index, long startedAtSec, long endedAtSec, long positionMs, long durationMs) {
            this.index = index;
            this.startedAtSec = startedAtSec;
            this.endedAtSec = endedAtSec;
            this.positionMs = Math.max(0L, positionMs);
            this.durationMs = durationMs > 0 ? durationMs : -1L;
        }

        Bundle bundle() {
            final Bundle b = new Bundle();
            b.putInt("index", index);
            b.putLong("started_at", startedAtSec);
            b.putLong("ended_at", endedAtSec);
            b.putLong("position_ms", Math.max(0L, positionMs));
            b.putInt("position_sec", seconds(positionMs));
            b.putLong("duration_ms", durationMs > 0 ? durationMs : -1L);
            b.putInt("duration_sec", durationMs > 0 ? seconds(durationMs) : -1);
            return b;
        }
    }

    private final ArrayList<Visit> visits = new ArrayList<>();

    void clear() {
        visits.clear();
    }

    void touch(int index, long positionMs, long durationMs) {
        if (index < 0) {
            return;
        }
        Visit current = current();
        if (current == null || current.index != index) {
            current = new Visit(index, positionMs, durationMs);
            visits.add(current);
            if (visits.size() > MAX_VISITS) {
                visits.remove(0);
            }
        }
        current.endedAtSec = nowSec();
        current.positionMs = Math.max(0L, positionMs);
        if (durationMs > 0) {
            current.durationMs = durationMs;
        }
    }

    void close(int index, long positionMs, long durationMs) {
        touch(index, positionMs, durationMs);
    }

    Bundle[] bundles() {
        final Bundle[] out = new Bundle[visits.size()];
        for (int i = 0; i < visits.size(); i++) {
            out[i] = visits.get(i).bundle();
        }
        return out;
    }

    void restore(Bundle[] history) {
        visits.clear();
        if (history == null) {
            return;
        }
        final int start = Math.max(0, history.length - MAX_VISITS);
        for (int i = start; i < history.length; i++) {
            final Bundle b = history[i];
            if (b == null) {
                continue;
            }
            final int index = b.getInt("index", -1);
            if (index < 0) {
                continue;
            }
            final long started = b.getLong("started_at", nowSec());
            final long ended = b.getLong("ended_at", started);
            final long position = b.containsKey("position_ms")
                    ? b.getLong("position_ms") : Math.max(0L, b.getInt("position_sec", 0) * 1000L);
            final long duration = b.containsKey("duration_ms")
                    ? b.getLong("duration_ms") : b.getInt("duration_sec", -1) * 1000L;
            visits.add(new Visit(index, started, ended, position, duration));
        }
    }

    List<Visit> snapshot() {
        return new ArrayList<>(visits);
    }

    private Visit current() {
        return visits.isEmpty() ? null : visits.get(visits.size() - 1);
    }

    private static long nowSec() {
        return System.currentTimeMillis() / 1000L;
    }

    private static int seconds(long valueMs) {
        if (valueMs <= 0) {
            return 0;
        }
        return (int) Math.min(Integer.MAX_VALUE, valueMs / 1000L);
    }
}
