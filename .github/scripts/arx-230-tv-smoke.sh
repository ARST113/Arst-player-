#!/usr/bin/env bash
set -euo pipefail
PKG=com.justplus.player
OUT=arx-230-tv-evidence
mkdir -p "$OUT"
trap 'adb logcat -d -v threadtime > "$OUT/logcat-final.txt" 2>/dev/null || true; adb shell dumpsys activity activities > "$OUT/activity-final.txt" 2>/dev/null || true; adb exec-out screencap -p > "$OUT/screen-final.png" 2>/dev/null || true' EXIT

adb shell pm list features > "$OUT/feature-list.txt"
adb shell getprop ro.build.characteristics | tee "$OUT/build-characteristics.txt"
if ! grep -q 'android.software.leanback' "$OUT/feature-list.txt"; then
  echo "::error::The emulator is not running an Android TV system image"
  exit 1
fi
APK="$(find app/build/outputs/apk/latestUniversal/debug -type f -name '*.apk' | head -n1)"
test -s "$APK"
adb install -r "$APK"
adb reverse tcp:8765 tcp:8765
adb shell am force-stop "$PKG"
adb logcat -c
adb shell "am start -W -n $PKG/com.brouken.player.PlayerActivity -a android.intent.action.VIEW -d 'http://127.0.0.1:8765/tv-h264.mp4' -t video/mp4" > "$OUT/launch.txt" 2>&1 || true
ready=0; frame=0; playing=0
end=$((SECONDS+90))
while (( SECONDS < end )); do
  adb logcat -d -v threadtime > "$OUT/logcat-play.txt" 2>/dev/null || true
  grep -q "state READY" "$OUT/logcat-play.txt" && ready=1 || true
  grep -q "first frame rendered" "$OUT/logcat-play.txt" && frame=1 || true
  grep -q "playing=true" "$OUT/logcat-play.txt" && playing=1 || true
  if ((ready && frame && playing)); then break; fi
  sleep 2
done
printf 'Android TV playback: ready=%s, frame=%s, playing=%s\n' "$ready" "$frame" "$playing" | tee "$OUT/result.txt"
adb exec-out screencap -p > "$OUT/tv-playing.png"
if (( !ready || !frame || !playing )); then
  echo "::error::TV emulator did not decode H264 fixture; inspect logcat-play.txt"
  exit 1
fi
# D-pad is the only navigation mechanism on a physical Android TV.
for key in 23 20 22 21 19 4; do
  adb shell input keyevent "$key"
  sleep 1
  adb exec-out screencap -p > "$OUT/dpad-$key.png"
done
adb shell uiautomator dump /sdcard/arx-tv.xml >/dev/null 2>&1 || true
adb pull /sdcard/arx-tv.xml "$OUT/controller-ui.xml" >/dev/null 2>&1 || true
adb shell input keyevent 127 || true
adb shell input keyevent 3 || true
sleep 2
adb shell dumpsys power > "$OUT/power-before.txt" || true
adb shell input keyevent 26 || true
sleep 4
adb shell dumpsys power > "$OUT/power-while-off.txt" || true
adb shell input keyevent 26 || true
sleep 3
adb shell dumpsys power > "$OUT/power-after.txt" || true
adb shell "am start -W -n $PKG/com.brouken.player.PlayerActivity -a android.intent.action.VIEW -d 'http://127.0.0.1:8765/tv-h264.mp4' -t video/mp4" > "$OUT/resume-launch.txt" 2>&1 || true
sleep 4
adb logcat -d -v threadtime > "$OUT/logcat-lifecycle.txt" || true
adb exec-out screencap -p > "$OUT/tv-wake.png" || true
if grep -A 6 'FATAL EXCEPTION:' "$OUT/logcat-lifecycle.txt" | grep -q "Process: $PKG"; then
  echo "::error::TV emulator crashed during D-pad/lifecycle sequence"
  exit 1
fi
echo "PASS: TV image with leanback, real first frame, D-pad and lifecycle without process crash" | tee -a "$OUT/result.txt"
echo "NOTE: actual Fire TV, HDMI codec, Realtek/MediaTek/DV and passthrough remain unverified" | tee -a "$OUT/result.txt"
