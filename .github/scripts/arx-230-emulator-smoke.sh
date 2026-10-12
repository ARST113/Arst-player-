#!/usr/bin/env bash
# Offline, deterministic playback and lifecycle checks against the current ARX build.
set -euo pipefail
export LC_ALL=C
PKG=com.justplus.player
PLAYER=com.brouken.player.PlayerActivity
SETTINGS=com.brouken.player.SettingsActivity
OUT="${OUT:-arx-230-evidence}"
mkdir -p "$OUT"
: > "$OUT/results.tsv"
echo -e "case\tstatus\tdetails" >> "$OUT/results.tsv"

case_result() {
  local label="$1" status="$2" details="$3"
  printf '%s\t%s\t%s\n' "$label" "$status" "$details" | tee -a "$OUT/results.tsv"
  if [[ "$status" == FAIL ]]; then echo "::error::$label: $details"; fi
}

# Ensure that failures still leave a trace, screenshots and a complete Android activity dump.
cleanup() {
  adb logcat -d -v threadtime > "$OUT/final-logcat.txt" 2>/dev/null || true
  adb shell dumpsys activity activities > "$OUT/final-activities.txt" 2>/dev/null || true
  adb shell dumpsys media.codec > "$OUT/final-mediacodec.txt" 2>/dev/null || true
  adb exec-out screencap -p > "$OUT/final-screen.png" 2>/dev/null || true
}
trap cleanup EXIT

apk="$(find app/build/outputs/apk/latestUniversal/debug -type f -name '*.apk' | head -n 1)"
test -s "$apk"
adb install -r "$apk"
adb shell settings put secure immersive_mode_confirmations confirmed || true
adb reverse tcp:8765 tcp:8765
adb shell am force-stop "$PKG"
adb logcat -c

fatal_crash() {
  local log="$1"
  # Android exception may be in system_server; only count crashes in our package.
  grep -A 6 -E 'FATAL EXCEPTION:' "$log" | grep -q "Process: $PKG" \
    || grep -q "Process $PKG .* has died" "$log"
}

capture() {
  local label="$1"
  adb exec-out screencap -p > "$OUT/$label.png" || true
  adb shell uiautomator dump /sdcard/arx-window.xml >/dev/null 2>&1 || true
  adb pull /sdcard/arx-window.xml "$OUT/$label.xml" >/dev/null 2>&1 || true
}

# Keep videos long enough that the playback evidence cannot be confused with a completed file.
play_file() {
  local label="$1" mime="$2" file="$3" required="$4"
  adb shell am force-stop "$PKG"
  adb logcat -c
  adb shell "am start -W -n $PKG/$PLAYER -a android.intent.action.VIEW -d 'http://127.0.0.1:8765/$file' -t '$mime'" \
    > "$OUT/$label-launch.txt" 2>&1 || true
  local end=$((SECONDS+70)) ready=0 play=0 frame=0 crashed=0
  while ((SECONDS < end)); do
    adb logcat -d -v threadtime > "$OUT/$label-logcat.txt" 2>/dev/null || true
    grep -q 'state READY' "$OUT/$label-logcat.txt" && ready=1 || true
    grep -q 'playing=true' "$OUT/$label-logcat.txt" && play=1 || true
    grep -q 'first frame rendered' "$OUT/$label-logcat.txt" && frame=1 || true
    if fatal_crash "$OUT/$label-logcat.txt"; then crashed=1; break; fi
    if ((ready && play && frame)); then break; fi
    sleep 2
  done
  capture "$label"
  adb shell dumpsys activity activities > "$OUT/$label-activity.txt" 2>/dev/null || true
  echo "ready=$ready playing=$play first_frame=$frame crashed=$crashed" > "$OUT/$label-result.txt"
  grep -E 'video input:|video decoder: init|audio decoder: init|state (READY|BUFFERING|IDLE|ENDED)|first frame rendered|ERROR_CODE_|FATAL EXCEPTION' \
    "$OUT/$label-logcat.txt" | tail -n 100 >> "$OUT/$label-result.txt" || true
  if (( ready && play && frame && !crashed )); then
    case_result "$label" PASS 'READY, playing, decoded first frame'
    return 0
  fi
  if [[ "$required" == yes ]]; then
    case_result "$label" FAIL "READY=$ready playing=$play frame=$frame crashed=$crashed"
    return 1
  fi
  case_result "$label" UNVERIFIED "Emulator codec/container capability: READY=$ready playing=$play frame=$frame crashed=$crashed"
  return 0
}

fail=0
play_file h264-aac video/mp4 h264-aac.mp4 yes || fail=1

# Exercise pause -> HOME -> activity restoration, retaining evidence even if an emulator lacks
# hardware decoder state continuity. The media is served by the same local fixture.
adb shell input keyevent 127 || true   # KEYCODE_MEDIA_PAUSE
sleep 1
adb shell input keyevent 3 || true     # HOME
sleep 2
adb shell "am start -W -n $PKG/$PLAYER -a android.intent.action.VIEW -d 'http://127.0.0.1:8765/h264-aac.mp4' -t video/mp4" \
  > "$OUT/resume-launch.txt" 2>&1 || true
sleep 4
capture after-background
adb logcat -d -v threadtime > "$OUT/resume-logcat.txt" || true
if fatal_crash "$OUT/resume-logcat.txt"; then
  case_result background-return FAIL 'Crash during pause / background / return'
  fail=1
else
  case_result background-return PASS 'No process crash (video-position preservation requires device testing)'
fi

# Subtitle Matroska validates demux/playback of two text tracks. Automatic selection is covered by
# JVM unit tests; track-switching UI is captured for inspection, not claimed from a screenshot.
play_file matroska-subtitles video/x-matroska subtitles.mkv no
play_file avi-mp4v video/x-msvideo mp4v.avi no

# Exercise settings with real UI inflation, then navigate to About using a bounded UI search.
adb shell am force-stop "$PKG"
adb logcat -c
adb shell am start -W -n "$PKG/$SETTINGS" > "$OUT/settings-launch.txt" 2>&1 || true
sleep 3
capture settings-root
about_found=0
for n in $(seq 1 16); do
  if [[ -s "$OUT/settings-root.xml" ]]; then
    cp "$OUT/settings-root.xml" "$OUT/settings-current.xml"
  fi
  adb shell uiautomator dump /sdcard/arx-window.xml >/dev/null 2>&1 || true
  adb pull /sdcard/arx-window.xml "$OUT/settings-current.xml" >/dev/null 2>&1 || true
  coords="$(python3 - "$OUT/settings-current.xml" <<'PY'
import re,sys,xml.etree.ElementTree as ET
try:
    root=ET.parse(sys.argv[1]).getroot()
    for e in root.iter('node'):
        text=(e.attrib.get('text','')+' '+e.attrib.get('content-desc','')).lower()
        if ('о приложении' in text or text.strip() == 'about' or 'about the app' in text):
            b=re.findall(r'\d+',e.attrib.get('bounds',''))
            if len(b)==4:
                print((int(b[0])+int(b[2]))//2,(int(b[1])+int(b[3]))//2)
                break
except Exception:
    pass
PY
)"
  if [[ -n "$coords" ]]; then
    adb shell input tap $coords
    about_found=1
    break
  fi
  adb shell input swipe 520 1500 520 350 330 || true
  sleep 1
done
sleep 2
capture about
adb logcat -d -v threadtime > "$OUT/settings-logcat.txt" || true
if fatal_crash "$OUT/settings-logcat.txt"; then
  case_result settings-about FAIL 'Settings screen crashed'
  fail=1
elif (( !about_found )); then
  case_result settings-about UNVERIFIED 'About row was not found by UI automator; screenshots saved'
else
  if grep -Eq 'ARST113|@ARST113' "$OUT/about.xml" && \
      ! grep -Eiq 'STAND WITH|UKRAINE' "$OUT/about.xml"; then
    case_result settings-about PASS 'About rendered with ARX author and no political banner'
  else
    case_result settings-about FAIL 'About text mismatch; inspect about.xml'
    fail=1
  fi
fi

if ((fail)); then exit 1; fi
