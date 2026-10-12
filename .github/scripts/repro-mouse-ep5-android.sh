#!/usr/bin/env bash
set -euo pipefail
OUT="mp4-android-emulator"
mkdir -p "$OUT"
PKG='com.justplus.player'
ACTIVITY='com.brouken.player.PlayerActivity'
URL="$(sed -n "s/^      STREAM_URL: '//p" .github/workflows/diagnose-mp4-audio.yml | sed "s/'$//")"
APK="$(find app/build/outputs/apk -name '*debug*.apk' -type f | head -n1)"
test -s "$APK" || { find app/build -name '*.apk'; exit 1; }
adb install -r "$APK"
adb logcat -c
adb shell am force-stop "$PKG" || true
adb shell "am start -W -n $PKG/$ACTIVITY -a android.intent.action.VIEW -t video/mp4 -d '$URL'" \
  > "$OUT/launch.txt"
sleep 30
adb logcat -d -v threadtime > "$OUT/first-30s.log"

# Attempt to expose controller and find a SeekBar. If custom controller lacks
# an accessible seekbar, inject DPAD_RIGHT seek commands but do not claim target.
adb shell input tap 520 880 || true
adb shell uiautomator dump /sdcard/arx-window.xml >/dev/null 2>&1 || true
adb pull /sdcard/arx-window.xml "$OUT/window.xml" >/dev/null 2>&1 || true
python3 - <<'PY' > "$OUT/seek-command.txt"
import re
from pathlib import Path
s=Path("mp4-android-emulator/window.xml").read_text(errors='replace') if Path("mp4-android-emulator/window.xml").exists() else ''
found=re.findall(r'<node[^>]*(?:class="android.widget.SeekBar"[^>]*bounds="(\[\d+,\d+\]\[\d+,\d+\])"|bounds="(\[\d+,\d+\]\[\d+,\d+\])"[^>]*class="android.widget.SeekBar")',s)
if found:
 bounds=next(x for x in found[0] if x)
 nums=list(map(int,re.findall(r'\d+',bounds)))
 x1,y1,x2,y2=nums
 # 300s/3783s = 0.0793. Use x1+0.08 width, allowing thumb margin.
 x=int(x1+.08*(x2-x1));y=(y1+y2)//2
 print(f'tap:{x}:{y}')
else:
 print('dpad-fallback:30:seek-target-unverified')
PY
if grep -q '^tap:' "$OUT/seek-command.txt"; then
  IFS=: read -r act x y < "$OUT/seek-command.txt"
  adb shell input tap "$x" "$y"
else
  for i in $(seq 1 30); do
    adb shell input keyevent KEYCODE_DPAD_RIGHT
  done
fi
sleep 65
adb logcat -d -v threadtime > "$OUT/after-seek.log"
adb shell dumpsys media.audio_flinger > "$OUT/audio-flinger.txt" || true
adb shell dumpsys media.codec > "$OUT/media-codec.txt" || true
adb exec-out screencap -p > "$OUT/after-seek.png" || true
python3 - <<'PY' | tee "$OUT/result.txt"
from pathlib import Path
s=Path("mp4-android-emulator/after-seek.log").read_text(errors='replace')
patterns={
'READY': 'state READY',
'PLAYING': 'playing=true',
'FIRST_FRAME': 'first frame rendered',
'AUDIO_INIT': 'audio decoder: init',
'CODEC_ERROR': 'AudioSink$WriteException',
'FATAL_CRASH': 'FATAL EXCEPTION',
'ERROR_CODE': 'ERROR_CODE_',
'AUDIO_UNDERRUN': 'underrun',
'AUDIO_RENDERER_ERROR': 'AudioRenderer error',
}
for k,v in patterns.items():
 print(k, s.lower().count(v.lower()))
print('seek',Path("mp4-android-emulator/seek-command.txt").read_text().strip())
print('NOTE: No real speaker/output capture: the emulator can report renderer errors but cannot independently verify audible dropout.')
PY
grep -Ei 'ERROR_CODE_|AudioSink|AudioRenderer|MediaCodecAudio|underrun|decoder.*audio|state READY|playing=true|first frame rendered|FATAL EXCEPTION' \
  "$OUT/after-seek.log" | tail -n 300 > "$OUT/renderer-events.txt" || true
# Debug APK should at least play; a failure is diagnostic evidence, not CI-code error.
