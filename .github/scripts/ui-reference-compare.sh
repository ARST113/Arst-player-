#!/usr/bin/env bash
set -euo pipefail

OUT="ui-reference"
PKG="com.justplus.player"
ACT="com.brouken.player.PlayerActivity"
FIELD_URL="http://213.171.26.189:2367/stream/MobLand.S01E04.1080p.WEB-DL.RGzsRutracker.mkv?link=3198e8861318732299b77913076857c75a6bc754&index=4&play"
STATIC_PORT=8765
STATIC_URL="http://127.0.0.1:${STATIC_PORT}/chrome-fixture.mp4"
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
  local url="${3:-$FIELD_URL}"
  local mime="${4:-video/x-matroska}"
  shift 4 || true
  # Anything left is passed to `am start` verbatim, which is how the legacy video_list contract is
  # handed to both builds: the published APK carries the same keys under the same names.
  local extra="${*:-}"

  adb uninstall "$PKG" >/dev/null 2>&1 || true
  adb install "$apk"
  adb shell am force-stop "$PKG" || true
  adb logcat -c

  adb shell "am start -W -n $PKG/$ACT -a android.intent.action.VIEW -t $mime -d '$url' $extra"     > "$OUT/$name-am-start.txt"

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

try:
    import numpy as np
except ImportError:  # the workflow installs it; the loop below is the fallback
    np = None

out = Path("ui-reference")
name = os.environ["NAME"]
candidates = [out/f"{name}-before.png", out/f"{name}-after1.png", out/f"{name}-after2.png"]


def plate_score(path):
    im = Image.open(path).convert("RGB")
    w, h = im.size
    # Released light chrome is a large near-neutral bright plate near the bottom.
    # Count only that region so bright video frames do not win accidentally.
    crop = im.crop((0, int(h * 0.62), w, h))
    if np is not None:
        a = np.asarray(crop).astype(int)
        spread = a.max(axis=2) - a.min(axis=2)
        total = a.sum(axis=2)
        # Two profiles, because the same scorer has to find the plate in either appearance: the light
        # one is a large near-neutral bright wash, and over a dark plate the rail is the only bright
        # neutral thing there is (the fixture's grid is cyan and fails the neutrality test).
        light = (spread <= 36) & (total >= 570)
        dark_rail = (spread <= 30) & (total >= 450)
        return int(light.sum() + dark_rail.sum())
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

# A fixture whose picture never changes: two captures of the same app on it differ by nothing but
# chrome, so what is left between the published APK and ARX can be measured instead of eyeballed. The
# clip is committed (ffmpeg is not on every runner image), 45s of one slate frame crossed by a cyan
# grid, and it is served to the emulator over `adb reverse`. The grid is cyan and the ground is slate
# on purpose: neither is a near-neutral bright wash, so the plate detector cannot mistake the picture
# for chrome.
STATIC_DIR=".github/fixtures"
STATIC_SERVER_STARTED=0
cleanup_static_server() {
  if (( STATIC_SERVER_STARTED )); then
    pkill -f "http.server ${STATIC_PORT}" >/dev/null 2>&1 || true
  fi
}
trap cleanup_static_server EXIT

# Run a capture under a wall clock, so a wedged adb call can cost one pass instead of the whole job.
run_bounded() {
  local limit="$1"; shift
  "$@" </dev/null &
  local pid=$!
  ( sleep "$limit"; kill -9 "$pid" >/dev/null 2>&1 || true ) &
  local watchdog=$!
  local rc=0
  wait "$pid" || rc=$?
  kill "$watchdog" >/dev/null 2>&1 || true
  wait "$watchdog" >/dev/null 2>&1 || true
  return "$rc"
}

prepare_static_fixture() {
  test -f "$STATIC_DIR/chrome-fixture.mp4" || { echo "static fixture: clip missing from the checkout"; return 1; }
  setsid python3 -m http.server "$STATIC_PORT" --bind 127.0.0.1 --directory "$STATIC_DIR" \
    </dev/null >/tmp/static-http.log 2>&1 &
  STATIC_SERVER_STARTED=1
  local served=1
  for _ in 1 2 3 4 5 6; do
    sleep 1
    if curl -fsS -o /dev/null "http://127.0.0.1:${STATIC_PORT}/chrome-fixture.mp4"; then
      served=0
      break
    fi
  done
  if (( served != 0 )); then
    echo "static fixture: the local server did not answer"
    cat /tmp/static-http.log || true
    return 1
  fi
  if ! adb reverse "tcp:${STATIC_PORT}" "tcp:${STATIC_PORT}"; then
    echo "static fixture: adb reverse refused"
    return 1
  fi
  echo "static fixture: serving $STATIC_DIR/chrome-fixture.mp4 on ${STATIC_PORT}, reversed into the emulator"
  return 0
}

open_and_capture official /tmp/JustPlus.Player.v2.1.3.apk

ARX_APK="$(find app/build/outputs/apk/latestUniversal/debug -name '*.apk' -type f | head -n1)"
test -n "$ARX_APK"
open_and_capture arx "$ARX_APK"

if prepare_static_fixture; then
  echo "static fixture ready, capturing chrome on it"
  run_bounded 300 open_and_capture official-static /tmp/JustPlus.Player.v2.1.3.apk "$STATIC_URL" video/mp4 \
    || echo "static fixture: the published capture did not finish in time"
  run_bounded 300 open_and_capture arx-static "$ARX_APK" "$STATIC_URL" video/mp4 \
    || echo "static fixture: the ARX capture did not finish in time"
  # Two items through the legacy contract, which is what brings the transport's previous/next pair
  # onto the plate -- the plain launch shows the hero alone.
  echo "capturing the two-item playlist"
  run_bounded 300 open_and_capture official-playlist /tmp/JustPlus.Player.v2.1.3.apk "$STATIC_URL" video/mp4 \
    --esa video_list "$STATIC_URL,$STATIC_URL" --esa video_list.name "One,Two" \
    || echo "playlist: the published capture did not finish in time"
  run_bounded 300 open_and_capture arx-playlist "$ARX_APK" "$STATIC_URL" video/mp4 \
    --esa video_list "$STATIC_URL,$STATIC_URL" --esa video_list.name "One,Two" \
    || echo "playlist: the ARX capture did not finish in time"
  # The quality chip is the other plate control a plain launch cannot show: it appears only when the
  # launcher offers more than one variant of the same item.
  echo "capturing the quality variants"
  run_bounded 300 open_and_capture official-quality /tmp/JustPlus.Player.v2.1.3.apk "$STATIC_URL" video/mp4 \
    --esa quality_levels "1080p,720p" --esa quality_urls "$STATIC_URL,$STATIC_URL" \
    || echo "quality: the published capture did not finish in time"
  run_bounded 300 open_and_capture arx-quality "$ARX_APK" "$STATIC_URL" video/mp4 \
    --esa quality_levels "1080p,720p" --esa quality_urls "$STATIC_URL,$STATIC_URL" \
    || echo "quality: the ARX capture did not finish in time"
  # The released chrome resolves with the appearance, and its palette has a branch per mode: the light
  # passes above are pinned, and the rail's tone is exactly what a wrong branch gets wrong. Ask the
  # emulator for a dark system and take the plain launch once more.
  echo "capturing the dark appearance"
  adb shell cmd uimode night yes >/dev/null 2>&1 || true
  sleep 2
  run_bounded 300 open_and_capture official-dark /tmp/JustPlus.Player.v2.1.3.apk "$STATIC_URL" video/mp4 \
    || echo "dark: the published capture did not finish in time"
  run_bounded 300 open_and_capture arx-dark "$ARX_APK" "$STATIC_URL" video/mp4 \
    || echo "dark: the ARX capture did not finish in time"
  adb shell cmd uimode night no >/dev/null 2>&1 || true
else
  echo "no static fixture on this runner, chrome report will be skipped"
fi
cleanup_static_server

python3 .github/scripts/chrome-report.py "$OUT" || true

python3 - <<'PY'
from pathlib import Path
from PIL import Image, ImageOps, ImageDraw

out = Path("ui-reference")

def side_by_side(a_name, b_name, dest):
    a_path, b_path = out/a_name, out/b_name
    if not a_path.exists() or not b_path.exists():
        return
    a = Image.open(a_path).convert("RGB")
    b = Image.open(b_path).convert("RGB")
    h = max(a.height, b.height)

    def pad(im):
        if im.height == h:
            return im
        canvas = Image.new("RGB", (im.width, h), "black")
        canvas.paste(im, (0, 0))
        return canvas

    a, b = pad(a), pad(b)
    canvas = Image.new("RGB", (a.width+b.width, h), "black")
    canvas.paste(a, (0, 0))
    canvas.paste(b, (a.width, 0))
    canvas.save(out/dest)
    print(dest, a.size, b.size)

side_by_side("official.png", "arx.png", "official-vs-arx.png")
side_by_side("official-static.png", "arx-static.png", "official-vs-arx-static.png")
side_by_side("official-playlist.png", "arx-playlist.png", "official-vs-arx-playlist.png")
side_by_side("official-quality.png", "arx-quality.png", "official-vs-arx-quality.png")
side_by_side("official-dark.png", "arx-dark.png", "official-vs-arx-dark.png")
PY
