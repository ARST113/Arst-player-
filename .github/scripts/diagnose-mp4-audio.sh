#!/usr/bin/env bash
set -uo pipefail
OUT=mp4-audio-diagnostics
mkdir -p "$OUT"
if [[ -z "$STREAM_URL" ]]; then echo 'STREAM_URL unset'; exit 1; fi
printf 'stage\tresult\tmessage\n' > "$OUT/results.tsv"
report() { printf '%s\t%s\t%s\n' "$1" "$2" "$3" | tee -a "$OUT/results.tsv"; }

curl -sS -L --max-time 25 --connect-timeout 12 -I "$STREAM_URL" \
   -o "$OUT/http-head.txt" -w 'http_status=%{http_code}\nremote=%{remote_ip}\n' \
   > "$OUT/http-head-result.txt" 2> "$OUT/http-head-stderr.txt"
report HEAD "$?" 'Metadata probe; HEAD may be refused by TorrServer'
curl -sS -L --max-time 45 --connect-timeout 12 --range 0-131071 \
   "$STREAM_URL" -D "$OUT/range-headers.txt" -o "$OUT/header-bytes.bin" \
   -w 'http_status=%{http_code}\nbytes=%{size_download}\nremote=%{remote_ip}\n' \
   > "$OUT/range-result.txt" 2> "$OUT/range-stderr.txt"
report RANGE "$?" 'Range 0-131071'
if [[ -f "$OUT/header-bytes.bin" ]]; then
python3 - <<'PY' > mp4-audio-diagnostics/header-atoms.txt
from pathlib import Path
b=Path("mp4-audio-diagnostics/header-bytes.bin").read_bytes()
print("downloaded",len(b))
for k in (b'ftyp',b'moov',b'moof',b'mdat',b'sidx',b'cmov',b'free',b'wide',b'mp42',b'isom',b'dash',b'qt  '):
    hits=[str(i) for i in range(len(b)) if b.startswith(k,i)]
    print(k.decode('ascii','replace'), ','.join(hits[:12]))
print("first_64_hex",b[:64].hex())
PY
fi
rm -f "$OUT/header-bytes.bin"

timeout 150s ffprobe -v warning -rw_timeout 18000000 -probesize 12000000 \
    -analyzeduration 18000000 -show_format -show_streams -print_format json \
    "$STREAM_URL" > "$OUT/streams.json" 2> "$OUT/ffprobe-stderr.txt"
probe_rc=$?
report FFPROBE "$probe_rc" 'Format, tracks, time base and codec metadata'
if [[ "$probe_rc" -eq 0 ]]; then
python3 - <<'PY' > "$OUT/stream-summary.txt"
import json
j=json.load(open("mp4-audio-diagnostics/streams.json"))
f=j.get("format",{})
print("format=",f.get("format_name"),"duration=",f.get("duration"),"size=",f.get("size"),"probe_score=",f.get("probe_score"))
for i,s in enumerate(j.get("streams",[])):
 print("index=",i,"type=",s.get("codec_type"),"codec=",s.get("codec_name"),"tag=",s.get("codec_tag_string"),
       "sample_rate=",s.get("sample_rate"),"channels=",s.get("channels"),"layout=",s.get("channel_layout"),
       "time_base=",s.get("time_base"),"start_time=",s.get("start_time"),"duration=",s.get("duration"),
       "extradata_size=",s.get("extradata_size"),"bit_rate=",s.get("bit_rate"),"profile=",s.get("profile"),
       "disposition=",s.get("disposition"),"language=",s.get("tags",{}).get("language"))
PY
fi

timeout 110s ffprobe -v error -rw_timeout 18000000 -read_intervals '%+160' \
   -select_streams a:0 -show_packets \
   -show_entries packet=pts_time,dts_time,duration_time,flags,size \
   -of json "$STREAM_URL" > "$OUT/audio-packets.json" 2> "$OUT/packet-stderr.txt"
packet_rc=$?
report PACKETS "$packet_rc" 'First 160 seconds: packet PTS, DTS, durations and gaps'
if [[ "$packet_rc" -eq 0 ]]; then
python3 - <<'PY' > "$OUT/packet-analysis.txt"
import json
pk=json.load(open("mp4-audio-diagnostics/audio-packets.json")).get("packets",[])
print("packet_count",len(pk))
issues=[]
last_end=None
last_pts=None
for p in pk:
 try:
  pts=float(p["pts_time"]); dur=float(p.get("duration_time",0))
 except (ValueError,KeyError,TypeError):continue
 if last_end is not None:
  gap=pts-last_end
  if gap>0.15 or gap<(-.15):issues.append((round(pts,4), round(gap,4),"positive gap" if gap>0 else "overlap/reorder"))
 if last_pts is not None and pts+1e-5<last_pts: issues.append((round(pts,4),round(pts-last_pts,4),"nonmonotonic PTS"))
 last_end=pts+dur
 last_pts=pts
print("anomaly_count",len(issues))
for item in issues[:160]:print(*item,sep="\t")
PY
fi
rm -f "$OUT/audio-packets.json"

for offset in 0 240 600; do
 label="audio-from-$offset"
 timeout 160s ffmpeg -nostdin -hide_banner -loglevel info -rw_timeout 18000000 \
   -ss "$offset" -i "$STREAM_URL" -t 80 -map 0:a:0 -vn -sn -dn \
   -af 'silencedetect=noise=-49dB:d=0.8,astats=metadata=1:reset=0' \
   -f null - > "$OUT/$label.stdout.txt" 2> "$OUT/$label.log"
 rc=$?
 report "$label" "$rc" '80 sec audio decode, duration and silence detection'
 grep -iE 'silence_(start|end|duration)|non.monoton|invalid|corrupt|error|nan|sample rate|channel layout|drop|failed|time=' \
   "$OUT/$label.log" | tail -n 120 > "$OUT/$label-events.txt" || true
done
echo 'Silence reports alone do not prove a player defect.'
