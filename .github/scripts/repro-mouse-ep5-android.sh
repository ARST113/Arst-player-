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
sleep 12
adb logcat -d -v threadtime > "$OUT/first-30s.log"
adb shell uiautomator dump /sdcard/first-window.xml >/dev/null 2>&1 || true
adb pull /sdcard/first-window.xml "$OUT/first-window.xml" >/dev/null 2>&1 || true
python3 - <<'PY' > "$OUT/dismiss-command.txt"
import xml.etree.ElementTree as ET
import re
from pathlib import Path
p=Path('mp4-android-emulator/first-window.xml')
if not p.exists():
 print('none');raise SystemExit
for node in ET.parse(p).getroot().iter('node'):
 if node.attrib.get('resource-id')=='android:id/ok' or node.attrib.get('text')=='Got it':
  vals=list(map(int,re.findall(r'\d+',node.attrib.get('bounds',''))))
  if len(vals)==4:
   print(str((vals[0]+vals[2])//2)+":"+str((vals[1]+vals[3])//2))
   break
else:print('none')
PY
button="$(cat "$OUT/dismiss-command.txt")"
if [[ "$button" != "none" ]]; then
  IFS=: read -r bx by <<< "$button"
  adb shell input tap "$bx" "$by" || true
  echo 'Dismissed Android immersive-mode tutorial' > "$OUT/dismissed.txt"
fi
sleep 2
adb shell input tap 1200 520 || true
sleep 2
adb shell uiautomator dump /sdcard/arx-window.xml >/dev/null 2>&1 || true
adb pull /sdcard/arx-window.xml "$OUT/window.xml" >/dev/null 2>&1 || true
python3 - <<'PY' > "$OUT/seek-command.txt"
import re
import xml.etree.ElementTree as ET
from pathlib import Path
p=Path("mp4-android-emulator/window.xml")
if not p.exists():
 print("dpad-fallback:30:seek-target-unverified");raise SystemExit
root=ET.parse(p).getroot()
candidates=[]
for node in root.iter('node'):
 a=node.attrib
 role=a.get('class','')
 rid=a.get('resource-id','')
 if role in ('android.widget.SeekBar','android.widget.ProgressBar') or 'exo_progress' in rid or 'time_bar' in rid or 'TimeBar' in role:
  vals=list(map(int,re.findall(r'\d+',a.get('bounds',''))))
  if len(vals)==4:
   x1,y1,x2,y2=vals
   if x2-x1>300:
    candidates.append((x2-x1,(x1,y1,x2,y2),role,rid))
if candidates:
 _,(x1,y1,x2,y2),role,rid=max(candidates,key=lambda a:a[0])
 x=int(x1+.0793*(x2-x1));y=(y1+y2)//2
 print(f'tap:{x}:{y}:{role}:{rid}')
else:
 print('dpad-fallback:30:seek-target-unverified')
PY
if grep -q '^tap:' "$OUT/seek-command.txt"; then
 IFS=: read -r act x y role rid < "$OUT/seek-command.txt"
 adb shell input tap "$x" "$y"
else
 for i in $(seq 1 30); do adb shell input keyevent KEYCODE_DPAD_RIGHT; done
fi
adb logcat -d -v threadtime > "$OUT/after-seek-action.log"

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
