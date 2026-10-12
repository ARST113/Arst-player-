#!/usr/bin/env bash
set -euo pipefail
OUT="torrent-live-cache-observation"
mkdir -p "$OUT"
STREAM_URL="$(sed -n "s/^      STREAM_URL: '//p" .github/workflows/diagnose-mp4-audio.yml | sed "s/'$//")"
BASE='http://213.171.26.189:2367'
HASH='db96dda9c3a1e6b1bbc38402378ea822d009fbf4'

# Observe streaming at real-world pace. Never call API that edits torrent state or server settings.
ffmpeg -nostdin -hide_banner -loglevel warning -rw_timeout 18000000 \
  -ss 300 -re -i "$STREAM_URL" -t 25 -map 0:a:0 -vn -sn -dn \
  -f null - >/dev/null 2>"$OUT/ffmpeg.log" &
pid=$!

printf 'utc_time,readers,filled_mib,capacity_mib,cached_pieces,reported_peers,active_peers,pending_peers,download_bytes_per_sec,torrent_state\n' \
  > "$OUT/cache-series.csv"
for i in $(seq 1 33); do
  curl -sS --max-time 5 -H 'Content-Type: application/json' \
    -d '{"action":"get","hash":"db96dda9c3a1e6b1bbc38402378ea822d009fbf4"}' \
    "$BASE/cache" -o "$OUT/cache-snapshot.json" || true
  python3 - <<'PY' >> "$OUT/cache-series.csv"
import json
from pathlib import Path
from datetime import datetime,timezone
path=Path('torrent-live-cache-observation/cache-snapshot.json')
j=json.loads(path.read_text()) if path.exists() else {}
t=j.get('Torrent') or {}
now=datetime.now(timezone.utc).isoformat()
pieces=j.get('Pieces') or {}
readers=j.get('Readers') or []
field=lambda o,*names: next((o.get(k) for k in names if o.get(k) is not None),0)
print(','.join(map(str,(now,
 len(readers),
 round(field(j,'Filled','filled')/2**20,2),
 round(field(j,'Capacity','capacity')/2**20,2),
 len(pieces),
 field(t,'total_peers','TotalPeers'),
 field(t,'active_peers','ActivePeers'),
 field(t,'pending_peers','PendingPeers'),
 field(t,'download_speed','DownloadSpeed'),
 field(t,'stat_string','StatString')))))
PY
  sleep 1
done
wait "$pid" || true
rm -f "$OUT/cache-snapshot.json"
echo 'Live-cache sampling complete; CPU codec or actual audio output on ATV is not measured.' > "$OUT/notes.txt"
