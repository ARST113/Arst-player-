#!/usr/bin/env bash
set -euo pipefail
OUT=mp4-second-pass
mkdir -p "$OUT"
STREAM_URL="$(sed -n "s/^      STREAM_URL: '//p" .github/workflows/diagnose-mp4-audio.yml | sed "s/'$//")"
test -n "$STREAM_URL"
# Read real MP4 packet offsets near first corrupt region.
timeout 150s ffprobe -v error -rw_timeout 18000000 -read_intervals '280%+65' -select_streams a:0 \
    -show_packets -show_entries packet=pts_time,dts_time,duration_time,pos,size \
    -of json "$STREAM_URL" > "$OUT/packet-window.json"
python3 - <<'PY' > "$OUT/position-summary.txt"
import json
packets=json.load(open("mp4-second-pass/packet-window.json")).get("packets",[])
print("packet_count",len(packets))
for p in packets:
    try: t=float(p["pts_time"])
    except (KeyError,ValueError): continue
    if 293 <= t < 308 and round(t*3) != round((t-0.02133)*3):
        print(f"pts={t:.5f} pos={p.get('pos')} size={p.get('size')} duration={p.get('duration_time')}")
print("first",packets[0] if packets else "no packets")
PY
# Find actual packet range around detected error; byte-identical re-requests narrow down
# whether intermittently corrupt network bytes caused decoder failure.
python3 - <<'PY' > "$OUT/ranges.txt"
import json
packets=json.load(open("mp4-second-pass/packet-window.json")).get("packets",[])
for t in (295,297,299,301,303):
  candidates=[p for p in packets if p.get("pos") not in (None,'N/A')
              and p.get("pts_time") not in (None,'N/A') and abs(float(p['pts_time'])-t)<.03]
  if candidates:
    pos=int(candidates[0]['pos'])
    print(t, max(0,pos-512), pos+8191)
PY
while read -r t start end; do
  [[ -n "$start" ]] || continue
  for repeat in 1 2; do
    curl -fLsS --retry 2 --max-time 55 -r "$start-$end" "$STREAM_URL" \
       -o "$OUT/range-$t-$repeat.bin"
    bytes="$(wc -c < "$OUT/range-$t-$repeat.bin")"
    hash="$(sha256sum "$OUT/range-$t-$repeat.bin" | awk '{print $1}')"
    printf '%s,%s,%s,%s\n' "$t" "$repeat" "$bytes" "$hash" >> "$OUT/sha256-twice.csv"
  done
  if cmp -s "$OUT/range-$t-1.bin" "$OUT/range-$t-2.bin"; then
    echo "MATCH at $t sec" | tee -a "$OUT/range-compare.txt"
  else
    echo "DIFFERENT at $t sec" | tee -a "$OUT/range-compare.txt"
  fi
done < "$OUT/ranges.txt"
rm -f "$OUT"/range-*.bin "$OUT/packet-window.json"

# Narrow and repeat the AAC decoding failures. Exit from ffmpeg can be zero despite
# dozens of corrupt packets; count stderr instead of relying on the return code.
for t in 285 290 294 296 298 300 302 304 308 312; do
 for rep in 1 2; do
   logfile="$OUT/decode-$t-$rep.log"
   timeout 105s ffmpeg -nostdin -hide_banner -loglevel warning -rw_timeout 18000000 \
     -ss "$t" -i "$STREAM_URL" -t 5 -map 0:a:0 -vn -sn -dn \
     -f null - > /dev/null 2>"$logfile" || true
   errors="$(grep -c 'Error submitting packet to decoder' "$logfile" || true)"
   duplicate="$(grep -c 'channel element 0.0 duplicate' "$logfile" || true)"
   other="$(grep -icE 'invalid|corrupt|timeout|fail' "$logfile" || true)"
   printf '%s,%s,%s,%s,%s\n' "$t" "$rep" "$errors" "$duplicate" "$other" | tee -a "$OUT/error-grid.csv"
 done
done

echo 'This test establishes repeatable corrupted AAC frame payloads and/or unstable HTTP bytes; it does not claim successful recovery in Media3.'
