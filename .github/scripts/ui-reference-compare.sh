#!/usr/bin/env bash
set -euo pipefail

OUT="ui-reference"
PKG="com.justplus.player"
ACT="com.brouken.player.PlayerActivity"
URL="http://213.171.26.189:2367/stream/MobLand.S01E04.1080p.WEB-DL.RGzsRutracker.mkv?link=3198e8861318732299b77913076857c75a6bc754&index=4&play"
mkdir -p "$OUT"

# Suppress Android's first-use immersive-mode education overlay. Without this, a fresh
# install can capture the system cling instead of the player chrome and make the visual
# comparison nondeterministic.
adb shell settings put secure immersive_mode_confirmations confirmed >/dev/null 2>&1 || true

open_and_capture() {
  local name="$1"
  local apk="$2"

  adb uninstall "$PKG" >/dev/null 2>&1 || true
  adb install "$apk"
  adb shell am force-stop "$PKG" || true
  adb logcat -c

  adb shell "am start -W -n $PKG/$ACT -a android.intent.action.VIEW -t video/x-matroska -d '$URL'"     > "$OUT/$name-am-start.txt"

  local deadline=$(( $(date +%s) + 90 ))
  while (( $(date +%s) < deadline )); do
    adb logcat -d -v threadtime > "$OUT/$name-logcat.txt" || true
    if grep -q "first frame rendered" "$OUT/$name-logcat.txt" \
      || grep -q "state READY" "$OUT/$name-logcat.txt" \
      || grep -Eq "record=com\\.justplus\\.player/.*playbackState=PlaybackState \\{state=PLAYING\\(3\\)" "$OUT/$name-logcat.txt"; then
      break
    fi
    sleep 2
  done

  # Let the frame settle, then explicitly show controller chrome from the centre of the
  # fixed Pixel 6 landscape emulator (2400x1080). The previous lower-left tap could land
  # in the gesture/navigation area and occasionally leave the official APK controller hidden.
  sleep 2
  adb shell input tap 1200 540 || true
  sleep 1
  adb exec-out screencap -p > "$OUT/$name.png"
  adb shell uiautomator dump /sdcard/window.xml >/dev/null 2>&1 || true
  adb pull /sdcard/window.xml "$OUT/$name-window.xml" >/dev/null 2>&1 || true
  adb shell dumpsys activity activities > "$OUT/$name-activity.txt" || true
}

open_and_capture official /tmp/JustPlus.Player.v2.1.3.apk

ARX_APK="$(find app/build/outputs/apk/latestUniversal/debug -name '*.apk' -type f | head -n1)"
test -n "$ARX_APK"
open_and_capture arx "$ARX_APK"

python3 - <<'PY'
from pathlib import Path
from PIL import Image, ImageOps, ImageDraw

out = Path("ui-reference")
a = Image.open(out/"official.png").convert("RGB")
b = Image.open(out/"arx.png").convert("RGB")
h = max(a.height, b.height)
def pad(im):
    if im.height == h:
        return im
    canvas = Image.new("RGB", (im.width, h), "black")
    canvas.paste(im, (0, 0))
    return canvas
a, b = pad(a), pad(b)
canvas = Image.new("RGB", (a.width+b.width, h), "black")
canvas.paste(a, (0,0))
canvas.paste(b, (a.width,0))
canvas.save(out/"official-vs-arx.png")
print("official", a.size, "arx", b.size)
PY
