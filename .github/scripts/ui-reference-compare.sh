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
# The published reference screenshot uses the resolved light player chrome. Both clean
# installs default to themeMode=system, so pin the emulator itself to light for a fair comparison.
adb shell cmd uimode night no >/dev/null 2>&1 || true

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

  # Controller visibility is a toggle. On a fresh install it is not deterministic whether it
  # is still visible by the time playback becomes ready, so a blind tap can either show OR hide it.
  # Capture both toggle states (and the initial state), then choose the frame containing the largest
  # light neutral plate in the bottom third. System light mode is pinned above, so this is deterministic.
  sleep 1
  adb exec-out screencap -p > "$OUT/$name-before.png"
  adb shell input tap 1200 540 || true
  sleep 0.7
  adb exec-out screencap -p > "$OUT/$name-after1.png"
  adb shell input tap 1200 540 || true
  sleep 0.7
  adb exec-out screencap -p > "$OUT/$name-after2.png"

  NAME="$name" python3 - <<'PY'
import os
from pathlib import Path
from PIL import Image

out = Path("ui-reference")
name = os.environ["NAME"]
candidates = [out/f"{name}-before.png", out/f"{name}-after1.png", out/f"{name}-after2.png"]

def plate_score(path):
    im = Image.open(path).convert("RGB")
    w, h = im.size
    # Released light chrome is a large near-neutral bright plate near the bottom.
    # Count only that region so bright video frames do not win accidentally.
    crop = im.crop((0, int(h * 0.62), w, h))
    px = crop.load()
    score = 0
    for y in range(crop.height):
        for x in range(crop.width):
            r, g, b = px[x, y]
            if (r + g + b) >= 570 and max(r, g, b) - min(r, g, b) <= 36:
                score += 1
    return score

best = max(candidates, key=plate_score)
Image.open(best).save(out/f"{name}.png")
print(name, "controller candidate", best.name, "score", plate_score(best))
PY

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
