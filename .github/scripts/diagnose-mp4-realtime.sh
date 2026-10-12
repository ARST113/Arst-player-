#!/usr/bin/env bash
set -euo pipefail
OUT=mp4-realtime-repro
mkdir -p "$OUT"
STREAM_URL="$(sed -n "s/^      STREAM_URL: '//p" .github/workflows/diagnose-mp4-audio.yml | sed "s/'$//")"
test -n "$STREAM_URL"
printf 'run,mode,decoded_duration,errors,duplicate_elements,silence_starts,exit_code\n' > "$OUT/decode-grid.csv"

for rep in 1 2 3; do
    file="$OUT/exact-original-$rep.log"
    set +e
    timeout 130s ffmpeg -nostdin -hide_banner -loglevel info -rw_timeout 18000000 \
      -ss 240 -i "$STREAM_URL" -t 80 -map 0:a:0 -vn -sn -dn \
      -af 'silencedetect=noise=-49dB:d=0.8,astats=metadata=1:reset=0' \
      -f null - > /dev/null 2> "$file"
    rc=$?
    set -e
    count="$(grep -c 'Error submitting packet to decoder' "$file" || true)"
    dup="$(grep -c 'channel element 0.0 duplicate' "$file" || true)"
    silence="$(grep -c 'silence_start:' "$file" || true)"
    printf '%s,%s,%s,%s,%s,%s,%s\n' "$rep" 'identical-to-first' 80 "$count" "$dup" "$silence" "$rc" | tee -a "$OUT/decode-grid.csv"
    grep -iE 'invalid|duplicate|silence_start|silence_end|video:0kB audio:' "$file" | tail -n 110 > "$OUT/exact-events-$rep.txt" || true
done

# Slow input to wall-clock speed: closely resembles an Android player reading from TorrServer.
file="$OUT/realtime-from-300.log"
set +e
timeout 110s ffmpeg -nostdin -hide_banner -loglevel warning -rw_timeout 18000000 \
 -ss 300 -re -i "$STREAM_URL" -t 38 -map 0:a:0 -vn -sn -dn \
 -f null - > /dev/null 2>"$file"
rc=$?
set -e
count="$(grep -c 'Error submitting packet to decoder' "$file" || true)"
dup="$(grep -c 'channel element 0.0 duplicate' "$file" || true)"
printf '%s,%s,%s,%s,%s,%s,%s\n' '1' 'realtime-input' 38 "$count" "$dup" 0 "$rc" | tee -a "$OUT/decode-grid.csv"

timeout 90s ffprobe -v error -rw_timeout 18000000 -read_intervals '316%+6' \
    -select_streams a:0 -show_packets -show_entries packet=pts_time,pos,size \
    -of json "$STREAM_URL" > "$OUT/pts-mapped.json"
python3 - <<'PY' > "$OUT/packet-offsets.txt"
import json
ps=json.load(open("mp4-realtime-repro/pts-mapped.json")).get('packets',[])
for target in (317.3,318.5,319.5):
 items=[p for p in ps if p.get('pts_time') and p.get('pos') not in (None,'N/A') and abs(float(p['pts_time'])-target)<.022]
 if items:
  p=min(items,key=lambda p:abs(float(p['pts_time'])-target))
  print(f"{target}\t{p['pts_time']}\t{p['pos']}\t{p['size']}")
PY
while read -r target pts pos size; do
  test -n "$pos" || continue
  start="$pos"; end=$((pos + size - 1))
  for rep in 1 2; do
    curl -fsSL --range "$start-$end" --max-time 40 "$STREAM_URL" \
       -o "$OUT/payload-$target-$rep"
    hash="$(sha256sum "$OUT/payload-$target-$rep" | awk '{print $1}')"
    received="$(wc -c < "$OUT/payload-$target-$rep")"
    printf '%s\t%s\t%s\t%s\t%s\n' "$pts" "$rep" "$pos" "$received" "$hash" >> "$OUT/packet-hashes.tsv"
  done
  cmp "$OUT/payload-$target-1" "$OUT/payload-$target-2" || echo "MISMATCH near $target" >> "$OUT/packet-range-mismatch.txt"
done < "$OUT/packet-offsets.txt"
rm -f "$OUT"/payload-*
