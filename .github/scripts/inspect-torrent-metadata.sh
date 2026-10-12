#!/usr/bin/env bash
set -euo pipefail
OUT="torrent-metadata-inspection"
mkdir -p "$OUT"
SERVER='http://213.171.26.189:2367'
HASH='db96dda9c3a1e6b1bbc38402378ea822d009fbf4'
STREAM_URL="$(sed -n "s/^      STREAM_URL: '//p" .github/workflows/diagnose-mp4-audio.yml | sed "s/'$//")"

echo 'READ-ONLY API inspection for exact torrent infohash and cache stats' > "$OUT/README.txt"
curl --silent --show-error --max-time 30 -i "$SERVER/echo" -o "$OUT/echo.http" || true
for action in torrents cache; do
    if [[ "$action" = "torrents" ]]; then
        body='{"action":"get","hash":"db96dda9c3a1e6b1bbc38402378ea822d009fbf4"}'
    else
        body='{"action":"get","hash":"db96dda9c3a1e6b1bbc38402378ea822d009fbf4"}'
    fi
    curl --silent --show-error --max-time 45 -D "$OUT/$action.headers" \
        -H 'Content-Type: application/json' -d "$body" \
        "$SERVER/$action" -o "$OUT/$action.json" || true
done

# Capture status and expose the requested target only. Do not leak unrelated torrents.
python3 - <<'PY' > "$OUT/summary.txt"
import json
from pathlib import Path
folder=Path('torrent-metadata-inspection')
for name in ('torrents','cache'):
    f=folder/(name+'.json')
    print('===',name,'===')
    if not f.exists():
        print('no response');continue
    txt=f.read_text(errors='replace')
    print('response_chars',len(txt))
    try:
        data=json.loads(txt)
    except ValueError:
        print('not JSON',txt[:240]);continue
    if not isinstance(data,dict):
        print('type',type(data).__name__);continue
    print('top_fields',list(data)[:40])
    wanted=['hash','name','title','stat','stat_string','torrent_size','loaded_size',
            'download_speed','active_peers','total_peers','connected_seeders','pieces_dirtied_bad',
            'pieces_dirtied_good','piecesCount','piecesLength','capacity','filled']
    for k in wanted:
        if k in data:print(k,repr(data[k])[:300])
    files=data.get('file_stats') or data.get('files') or []
    print('file_stats_count',len(files))
    if isinstance(files,list):
        for file in files:
            if not isinstance(file,dict):continue
            path=file.get('path') or file.get('name') or ''
            ix=file.get('id',file.get('index'))
            if ix in (3,4,5) or ('5 серия' in path):
                print('NEAR_TARGET',json.dumps(file,ensure_ascii=False))
    for field in ('torrent','pieces','readers'):
        inner=data.get(field)
        if isinstance(inner,dict):
            print(field, 'keys',list(inner)[:30] if field!='pieces' else 'present')
            if field=='torrent':
                for k in wanted:
                    if k in inner:print('torrent.'+k,repr(inner[k])[:300])
                files=inner.get('file_stats') or []
                print('torrent.file_stats_count',len(files))
                for file in files:
                    if isinstance(file,dict) and (file.get('id') in (3,4,5) or '5 серия' in str(file.get('path'))):
                        print('torrent.NEAR_TARGET',json.dumps(file,ensure_ascii=False))
        elif isinstance(inner,list):
            print(field,'length',len(inner))
PY

# Check the actual MP4 payload is the exact fifth file. Bounded partial download.
curl -sS -L --max-time 35 -r 0-32767 "$STREAM_URL" \
    -D "$OUT/file.headers" -o "$OUT/file-prefix.bin" || true
python3 - <<'PY' >> "$OUT/summary.txt"
from pathlib import Path
b=Path('torrent-metadata-inspection/file-prefix.bin')
print('===stream sample===')
if b.exists():
 d=b.read_bytes()
 print('size',len(d),'prefix_hex',d[:32].hex())
 print('ftyp',d.find(b'ftyp'),'mdat',d.find(b'mdat'),'moov',d.find(b'moov'))
PY
rm -f "$OUT/file-prefix.bin"
