#!/usr/bin/env bash
set -euo pipefail

PKG="com.justplus.player"
ACTIVITY="com.brouken.player.PlayerActivity"
OUT="playback-smoke"
mkdir -p "$OUT"

APK="$(find app/build/outputs/apk/latestUniversal/debug -name '*.apk' -type f | head -n1)"
test -n "$APK"
adb install -r "$APK"

adb logcat -c
adb shell am force-stop "$PKG"

adb shell am start -W   -n "$PKG/$ACTIVITY"   -a android.intent.action.VIEW   -d http://10.0.2.2:8000/smoke.mp4   -t video/mp4   > "$OUT/am-start.txt"

deadline=$(( $(date +%s) + 45 ))
ready=0
playing=0
frame=0

while (( $(date +%s) < deadline )); do
  adb logcat -d -v threadtime > "$OUT/logcat.txt" || true
  grep -q "state READY" "$OUT/logcat.txt" && ready=1 || true
  grep -q "playing=true" "$OUT/logcat.txt" && playing=1 || true
  grep -q "first frame rendered" "$OUT/logcat.txt" && frame=1 || true
  if (( ready && playing && frame )); then
    break
  fi
  sleep 1
done

adb logcat -d -v threadtime > "$OUT/logcat.txt"
adb shell dumpsys activity activities > "$OUT/activity.txt"
adb shell dumpsys media.codec > "$OUT/media-codec.txt" || true

adb shell input tap 500 500 || true
sleep 1
adb shell uiautomator dump /sdcard/window.xml || true
adb pull /sdcard/window.xml "$OUT/window.xml" || true
adb exec-out screencap -p > "$OUT/player.png" || true

echo "ready=$ready playing=$playing first_frame=$frame" | tee "$OUT/result.txt"
grep -E "video input:|video decoder: init|state (READY|BUFFERING|IDLE|ENDED)|playing=|first frame rendered|ERROR_CODE_|FATAL EXCEPTION"   "$OUT/logcat.txt" | tail -n 120 | tee -a "$OUT/result.txt" || true

if (( !ready || !playing || !frame )); then
  echo "::error::Real playback smoke failed: READY=$ready playing=$playing first_frame=$frame"
  exit 1
fi

python3 - <<'PY'
import re
from pathlib import Path
p = Path("playback-smoke/window.xml")
if not p.exists():
    print("UI dump unavailable; READY + playing + first frame already prove playback.")
    raise SystemExit(0)
xml = p.read_text(errors="replace")
m = re.search(r'resource-id="com\.justplus\.player:id/exo_position"[^>]*text="([^"]+)"', xml)
if not m:
    m = re.search(r'text="([^"]+)"[^>]*resource-id="com\.justplus\.player:id/exo_position"', xml)
if not m:
    print("exo_position not exposed; playback proof already passed.")
    raise SystemExit(0)
value = m.group(1)
print("exo_position:", value)
parts = [int(x) for x in value.split(":") if x.isdigit()]
seconds = 0
for x in parts:
    seconds = seconds * 60 + x
if seconds <= 0:
    raise SystemExit("Playback clock did not advance past 00:00")
PY
