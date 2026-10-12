#!/usr/bin/env bash
set -euo pipefail
OUT=mp4-context-audio
mkdir -p "$OUT"
STREAM_URL="$(sed -n "s/^      STREAM_URL: '//p" .github/workflows/diagnose-mp4-audio.yml | sed "s/'$//")"
test -n "$STREAM_URL"
printf 'context,run,start_s,duration_s,invalid_frames,duplicate_elements,decoded_pcm_bytes,pcm_sha256,status\n' > "$OUT/decoded.csv"
for spec in '240:95' '240:95' '0:340' '310:30' '315:15' '318:12' '320:12'; do
 IFS=: read -r start duration <<< "$spec"
 id="start-$start-duration-$duration"
 index="$(find "$OUT" -maxdepth 1 -name "$id-*.txt" | wc -l)"
 file="$OUT/$id-$index.pcm"
 logfile="$OUT/$id-$index.txt"
 set +e
 timeout 180s ffmpeg -nostdin -hide_banner -loglevel warning -rw_timeout 18000000 \
   -ss "$start" -i "$STREAM_URL" -t "$duration" -map 0:a:0 \
   -vn -sn -dn -c:a pcm_s16le -f s16le "$file" 2>"$logfile"
 rc=$?
 set -e
 invalid="$(grep -c 'Error submitting packet to decoder' "$logfile" || true)"
 dup="$(grep -c 'channel element 0.0 duplicate' "$logfile" || true)"
 bytes="$(wc -c < "$file" || echo 0)"
 hash="$(sha256sum "$file" | awk '{print $1}')"
 printf '%s,%s,%s,%s,%s,%s,%s,%s,%s,%s\n' "$id" "$index" "$start" "$duration" "$invalid" "$dup" "$bytes" "$hash" "$rc" | tee -a "$OUT/decoded.csv"
 rm -f "$file"
done
echo 'If corruption occurs only after decoding preceding AAC frames, short-seek tests can hide it.' | tee "$OUT/conclusion-notes.txt"
