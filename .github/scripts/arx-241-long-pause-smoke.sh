#!/usr/bin/env bash
# A real pause longer than the 30-second HTTPS regression window.
# Local HTTP is deterministic: success does NOT prove the reported 2.4.0 HTTPS crash is fixed.
set -euo pipefail
PKG=com.justplus.player
OUT="arx-241-pause-evidence"
mkdir -p "$OUT"
adb reverse tcp:8765 tcp:8765
adb logcat -c
adb shell am force-stop "$PKG"
adb shell am start -W -n "$PKG/com.brouken.player.PlayerActivity" \
    -a android.intent.action.VIEW \
    -d "http://127.0.0.1:8765/pause.mp4" -t video/mp4 > "$OUT/launch.txt"
sleep 12
adb shell input keyevent 127 # pause
sleep 38
adb logcat -d -v threadtime > "$OUT/during-pause.log" || true
adb shell input keyevent 126 # resume
sleep 12
adb logcat -d -v threadtime > "$OUT/after-resume.log"
adb exec-out screencap -p > "$OUT/after-resume.png" || true
if grep -A 8 'FATAL EXCEPTION:' "$OUT/after-resume.log" | grep -q "Process: $PKG"; then
    echo "::error::ARX crashed during 38-second pause/resume"
    exit 1
fi
if ! grep -q 'first frame rendered' "$OUT/after-resume.log"; then
    echo "::error::No first rendered frame on local HTTP playback"
    exit 1
fi
if ! grep -q 'playing=true' "$OUT/after-resume.log"; then
    echo "::error::No playback confirmation after pause/resume"
    exit 1
fi
echo 'PASS: 38-second local HTTP pause/resume, video frame, playback, no fatal crash' | tee "$OUT/result.txt"
echo 'UNVERIFIED: HTTPS inactivity reconnect, DRM playback, Dolby encoding, equalizer, Ambient Light' | tee -a "$OUT/result.txt"
