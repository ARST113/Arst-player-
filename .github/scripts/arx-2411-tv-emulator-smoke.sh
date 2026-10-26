#!/usr/bin/env bash
# Android TV 2.4.11: actual decoder, D-pad, centered play control and focus/lifecycle.
set -euo pipefail
export LC_ALL=C
PKG=com.justplus.player
ACT=com.brouken.player.PlayerActivity
OUT=arx-2411-tv-evidence
mkdir -p "$OUT"
printf 'test\tstatus\tdetails\n' > "$OUT/results.tsv"
FAILURES=0

record() {
  printf '%s\t%s\t%s\n' "$1" "$2" "$3" | tee -a "$OUT/results.tsv"
  if [[ "$2" == FAIL ]]; then
    echo "::error::$1: $3"
    FAILURES=$((FAILURES+1))
  elif [[ "$2" == UNVERIFIED ]]; then
    echo "::warning::$1: $3"
  fi
}
capture() {
  local name="$1"
  adb exec-out screencap -p > "$OUT/$name.png" || true
  if timeout 25 adb shell uiautomator dump /sdcard/arx-2411-window.xml >/dev/null 2>&1; then
    adb pull /sdcard/arx-2411-window.xml "$OUT/$name.xml" >/dev/null 2>&1 || true
  fi
  adb shell dumpsys window windows > "$OUT/$name-window.txt" 2>/dev/null || true
}
cleanup() {
  adb logcat -d -v threadtime > "$OUT/final-logcat.txt" 2>/dev/null || true
  adb shell dumpsys activity activities > "$OUT/final-activities.txt" 2>/dev/null || true
  adb shell dumpsys media.codec > "$OUT/final-codecs.txt" 2>/dev/null || true
  adb exec-out screencap -p > "$OUT/final-screen.png" 2>/dev/null || true
}
trap cleanup EXIT
adb shell pm list features > "$OUT/features.txt"
adb shell getprop ro.build.characteristics > "$OUT/characteristics.txt"
if ! grep -q 'android.software.leanback' "$OUT/features.txt"; then
  record image FAIL 'Android TV leanback feature missing'
  exit 1
fi
adb shell wm size > "$OUT/screen-size.txt"
adb shell wm density > "$OUT/screen-density.txt"
record image PASS 'Android TV with leanback feature'

# The signed APK is ARM-only; use a debug build with x86 available for this emulator.
APK="$(find app/build/outputs/apk/latestUniversal/debug -type f -name '*.apk' | head -n1)"
test -s "$APK"
adb install -r "$APK" > "$OUT/install.txt" 2>&1
adb reverse tcp:8765 tcp:8765
adb shell settings put secure immersive_mode_confirmations confirmed || true
adb shell am force-stop "$PKG"
adb logcat -c
adb shell am start -W -n "$PKG/$ACT" -a android.intent.action.VIEW \
  -d http://127.0.0.1:8765/tv-h264.mp4 -t video/mp4 > "$OUT/arx-launch.txt" 2>&1
ready=0; frame=0; playing=0
deadline=$((SECONDS+95))
while ((SECONDS < deadline)); do
  adb logcat -d -v threadtime > "$OUT/arx-start-logcat.txt" 2>/dev/null || true
  grep -q 'state READY' "$OUT/arx-start-logcat.txt" && ready=1 || true
  grep -q 'first frame rendered' "$OUT/arx-start-logcat.txt" && frame=1 || true
  grep -q 'playing=true' "$OUT/arx-start-logcat.txt" && playing=1 || true
  if ((ready && frame && playing)); then break; fi
  sleep 3
done
capture arx-playing
if ((ready && frame && playing)); then
  record playback PASS 'H.264/AAC: READY, playing=true, first decoded frame rendered'
else
  record playback FAIL "READY=$ready playing=$playing first_frame=$frame"
  exit 1
fi

# Show controls by remote (KEYCODE_DPAD_CENTER). Capture actual overlay, not only XML.
adb shell input keyevent 23
sleep 1
capture arx-controls
# XML hierarchy includes bounds for accessibility-visible controls. Verify placement
# when the actual play/pause view is exported to UIAutomator.
python3 - "$OUT/arx-controls.xml" "$OUT/screen-size.txt" > "$OUT/arx-play-button-geometry.txt" <<'PY'
from pathlib import Path
import re,sys,xml.etree.ElementTree as ET
xml=Path(sys.argv[1]); bounds=Path(sys.argv[2]).read_text()
size=re.findall(r'(\d+)x(\d+)',bounds)
if not xml.is_file() or not size:
    print("UNVERIFIED: UI hierarchy or resolution missing")
    sys.exit(2)
width,height=map(int,size[-1])
try:
    root=ET.parse(xml).getroot()
except Exception as exc:
    print("UNVERIFIED: UIAutomator XML",exc)
    sys.exit(2)
matches=[]
for e in root.iter("node"):
    ident=e.attrib.get("resource-id","")
    desc=e.attrib.get("content-desc","")
    if ident.endswith(":id/exo_play_pause") or (desc.lower() in ("play","pause","воспроизвести","пауза")):
        b=[int(n) for n in re.findall(r'\d+',e.attrib.get("bounds",""))]
        if len(b)==4:
            cx=(b[0]+b[2])/2;cy=(b[1]+b[3])/2
            matches.append((cx,cy,ident,desc,e.attrib.get("focused","")))
if not matches:
    print("UNVERIFIED: center play/pause not exposed in accessibility XML")
    sys.exit(2)
cx,cy,ident,desc,focus=matches[0]
print(f"screen={width}x{height} play_button=({cx},{cy}) fractional=({cx/width:.3f},{cy/height:.3f}) focused={focus} id={ident} desc={desc}")
if .32<=cx/width<=.68 and .20<=cy/height<=.78:
    print("PASS: center button physically located away from bottom timeline")
    sys.exit(0)
print("FAIL: button relocated outside central video zone")
sys.exit(1)
PY
rc=$?
if ((rc==0)); then
  record centered-play PASS "$(head -n1 "$OUT/arx-play-button-geometry.txt")"
elif ((rc==2)); then
  record centered-play UNVERIFIED "$(head -n1 "$OUT/arx-play-button-geometry.txt"); static centered-layout test runs separately"
else
  record centered-play FAIL "$(tail -n1 "$OUT/arx-play-button-geometry.txt")"
fi

# D-pad navigation and timeline manipulation, each with a real screenshot.
for step in '20 down' '22 right' '22 right2' '21 left' '19 up' '23 select'; do
  key="$(awk '{print $1}' <<< "$step")"
  label="$(awk '{print $2}' <<< "$step")"
  adb shell input keyevent "$key"
  sleep 1
  capture "arx-dpad-$label"
done
adb shell input keyevent 22
sleep 1
capture arx-scrub-during
adb shell input keyevent 23
sleep 3
capture arx-scrub-after
record remote-input PASS 'D-pad DOWN RIGHT LEFT UP CENTER delivered; screenshots/UI hierarchy captured (visual result requires review)'

# Pause and resume through media keys and leave/return through HOME.
adb shell input keyevent 127
sleep 2
capture arx-paused
adb shell input keyevent 126
sleep 2
capture arx-resumed
adb shell input keyevent 127
adb shell input keyevent 3
sleep 2
capture arx-home
adb shell monkey -p "$PKG" -c android.intent.category.LEANBACK_LAUNCHER 1 > "$OUT/reenter-monkey.txt" 2>&1 || \
  adb shell am start -W -n "$PKG/$ACT" -a android.intent.action.VIEW \
    -d http://127.0.0.1:8765/tv-h264.mp4 -t video/mp4 > "$OUT/reenter-launch.txt" 2>&1 || true
sleep 4
capture arx-after-home
adb logcat -d -v threadtime > "$OUT/arx-lifecycle-logcat.txt" || true
if grep -A 8 'FATAL EXCEPTION:' "$OUT/arx-lifecycle-logcat.txt" | grep -q "Process: $PKG"; then
  record lifecycle FAIL 'Process crash during pause, Home and return'
else
  record lifecycle PASS 'No app process fatal exception; paused/background/return screenshots captured'
fi

# Real PlayPause position is verified by XML when possible; do not claim focus
# behavior or successful seek from the mere absence of a crash.
record remote-focus UNVERIFIED 'D-pad focus/seek and cleared seek delta require visual comparison of PNG/XML artifacts'
record expired-url-resume UNVERIFIED 'Expired-link + Android process death requires dedicated external-launcher instrumentation'
if ((FAILURES > 0)); then exit 1; fi
