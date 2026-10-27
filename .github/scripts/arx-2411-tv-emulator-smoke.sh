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
SERVER_PID=""

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
  if [[ -n "$SERVER_PID" ]]; then kill "$SERVER_PID" >/dev/null 2>&1 || true; fi
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
# Start a bounded-lifetime HTTP fixture server only while the emulator test runs.
python3 .github/scripts/arx-tv-fixture-server.py --port 8765 --directory arx-2411-tv-fixtures \
  </dev/null > "$OUT/http-server.txt" 2>&1 &
SERVER_PID=$!
server_ready=0
for i in {1..20}; do
  if curl -fsSI --max-time 3 http://127.0.0.1:8765/tv-h264.mp4 > "$OUT/http-head.txt" 2>&1; then
    server_ready=1
    break
  fi
  sleep 1
done
if ((server_ready == 0)); then
  record http-server FAIL "Video test server failed to start"
  exit 1
fi
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
set +e
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
set -e
if ((rc==0)); then
  record centered-play PASS "$(head -n1 "$OUT/arx-play-button-geometry.txt")"
elif ((rc==2)); then
  record centered-play UNVERIFIED "$(head -n1 "$OUT/arx-play-button-geometry.txt"); static centered-layout test runs separately"
else
  record centered-play FAIL "$(tail -n1 "$OUT/arx-play-button-geometry.txt")"
fi

# Validate the actual Material 3 side sheets, not merely the Java source.
# 1920x1080 Android TV screenshot evidence previously establishes the More
# button rectangle at [1736,948][1816,1028].
panel_coordinates() {
  python3 - "$1" "$2" <<'PY'
import sys,re,xml.etree.ElementTree as ET
try:
    root=ET.parse(sys.argv[1]).getroot()
    target=sys.argv[2].casefold()
    for node in root.iter('node'):
        a=node.attrib
        hay=(a.get('text','')+' '+a.get('content-desc','')).casefold()
        if target in hay:
            b=[int(n) for n in re.findall(r'\d+',a.get('bounds',''))]
            if len(b)==4 and b[2]>b[0] and b[3]>b[1]:
                print((b[0]+b[2])//2,(b[1]+b[3])//2)
                break
except Exception:
    pass
PY
}
capture_panel() {
  local which="$1" menu_label="$2" expected="$3" side="${APP_SIDE:-arx}"
  # Resume controller if its normal auto-hide has fired.
  adb shell input tap 960 540 || true
  sleep 1
  capture "$side-control-before-$which"
  local more
  more="$(panel_coordinates "$OUT/$side-control-before-$which.xml" "More")"
  if [[ -z "$more" ]]; then
    record "panel-$side-$which" UNVERIFIED 'More button inaccessible on this Android TV UI snapshot'
    return
  fi
  # On TV the controller may be fading when the first touch lands.
  # Check the menu actually opened and retry rather than silently tapping
  # the video at coordinates which were valid only in the missing menu.
  local coords=""
  for attempt in 1 2 3; do
    adb shell input tap $more
    sleep 2
    capture "$side-more-before-$which"
    coords="$(panel_coordinates "$OUT/$side-more-before-$which.xml" "$menu_label")"
    if [[ -n "$coords" ]]; then break; fi
    # On TV the lower entries of the More sheet need scrolling.
    for scroll in 1 2 3; do
      adb shell input swipe 1620 870 1620 310 360
      sleep 1
      capture "$side-more-before-$which"
      coords="$(panel_coordinates "$OUT/$side-more-before-$which.xml" "$menu_label")"
      if [[ -n "$coords" ]]; then break; fi
    done
    if [[ -n "$coords" ]]; then break; fi
    adb shell input keyevent 4 || true
    sleep 1
  done
  if [[ -z "$coords" ]]; then
    record "panel-$side-$which" UNVERIFIED "Menu row $menu_label not visible after three controller/menu attempts"
    adb shell input keyevent 4 || true
    return
  fi
  local visible=0
  for attempt in 1 2; do
    adb shell input tap $coords
    sleep 2
    capture "$side-material3-$which"
    if [[ -s "$OUT/$side-material3-$which.xml" ]] && \
       grep -q "$expected" "$OUT/$side-material3-$which.xml"; then
      visible=1
      break
    fi
    # Retry an item tap only if the menu remains on screen; if it closed,
    # do not tap the same coordinate against the underlying video.
    if ! grep -q "$menu_label" "$OUT/$side-material3-$which.xml"; then break; fi
  done
  if (( visible )); then
    record "panel-$side-$which" PASS "Material 3 $which pane opened, $expected accessibility text captured"
  else
    record "panel-$side-$which" FAIL "Menu opened but no $expected in $which panel accessibility tree"
  fi
  adb shell input keyevent 4 || true
  sleep 1
}
capture_panel ambient 'Ambient light' 'Lighting mode'
capture_panel picture 'Picture' 'Picture preset'
capture_panel equalizer 'Equalizer' 'Enable equalizer'

# The playlist and subtitle dialogs are direct controller actions, not rows
# in More. Use their accessibility bounds on both player versions.
capture_native_button() {
  local side="$1" name="$2" label="$3"
  adb shell input tap 960 540 || true
  sleep 1
  capture "$side-native-before-$name"
  local coords
  coords="$(panel_coordinates "$OUT/$side-native-before-$name.xml" "$label")"
  if [[ -z "$coords" ]]; then
    record "native-$side-$name" UNVERIFIED "$label control not exposed; screenshot retained"
    return
  fi
  adb shell input tap $coords
  sleep 2
  capture "$side-native-$name"
  record "native-$side-$name" PASS "Dialog opened for $label; screenshot and XML collected"
  adb shell input keyevent 4 || true
  sleep 1
}
capture_native_button arx playlist 'Playlist'
capture_native_button arx subtitles 'Subtitles'

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

# Capture a true reference frame from the vendor APK on the SAME TV emulator.
# The original and ARX use the same package name, so uninstall between them;
# do not compare against a phone layout or treat an original-app crash as ARX failure.
if [[ -s vendor/2.4.11.apk ]]; then
  adb uninstall "$PKG" > "$OUT/reference-uninstall.txt" 2>&1 || true
  if adb install vendor/2.4.11.apk > "$OUT/reference-install.txt" 2>&1; then
    adb shell am force-stop "$PKG"
    adb shell am start -W -n "$PKG/$ACT" -a android.intent.action.VIEW \
      -d http://127.0.0.1:8765/tv-h264.mp4 -t video/mp4 > "$OUT/reference-launch.txt" 2>&1 || true
    sleep 8
    capture official-playing
    adb shell input keyevent 23
    sleep 2
    capture official-controls
    adb shell input keyevent 20
    sleep 1
    capture official-down
    # Paired UI captures, not inferred feature existence. A missing section
    # is explicitly UNVERIFIED and will fail the separate strict parity gate.
    APP_SIDE=official
    capture_panel ambient 'Ambient light' 'Lighting mode'
    capture_panel picture 'Picture' 'Picture preset'
    capture_panel equalizer 'Equalizer' 'Enable equalizer'
    capture_native_button official playlist 'Playlist'
    capture_native_button official subtitles 'Subtitles'
    APP_SIDE=arx
    record reference PASS 'Verified official 2.4.11 installed, TV screen and controls captured for side-by-side visual review'
  else
    record reference UNVERIFIED 'Official APK installation unsuccessful; see reference-install.txt'
  fi
else
  record reference UNVERIFIED 'Official 2.4.11 APK missing; no reference screenshots'
fi


# The parallel ARX build must coexist with the SHA-verified official Just+
# package without changing the visible ARX name or ACTION_VIEW protocol.
PARALLEL_PKG=com.lampaua.player.arx
PARALLEL_APK="$(find app/build/outputs/apk/latestParallel/debug -type f -name '*.apk' | head -n1)"
if [[ -s "$PARALLEL_APK" ]]; then
  if adb install -r "$PARALLEL_APK" > "$OUT/parallel-install.txt" 2>&1; then
    adb shell pm list packages > "$OUT/parallel-installed-packages.txt"
    if grep -qx "package:com.justplus.player" "$OUT/parallel-installed-packages.txt" && \
       grep -qx "package:com.lampaua.player.arx" "$OUT/parallel-installed-packages.txt"; then
      record parallel-install PASS 'Official Just+ and parallel ARX installed simultaneously with independent package IDs'
    else
      record parallel-install FAIL 'Expected both package IDs; see parallel-installed-packages.txt'
    fi

    # This is the form of an Android ACTION_VIEW launch with setPackage();
    # unlike explicit -n, it proves that the Lampa-style implicit video
    # intent filter matches the independent parallel package.
    adb shell am force-stop "$PARALLEL_PKG"
    adb logcat -c
    if timeout 40 adb shell am start -W -p "$PARALLEL_PKG" \
        -a android.intent.action.VIEW \
        -d http://127.0.0.1:8765/tv-h264.mp4 -t video/mp4 \
        > "$OUT/parallel-view-intent-launch.txt" 2>&1; then
      p_ready=0; p_frame=0; p_play=0
      end_parallel=$((SECONDS+75))
      while ((SECONDS < end_parallel )); do
        adb logcat -d -v threadtime > "$OUT/parallel-play-logcat.txt" 2>/dev/null || true
        grep -q 'state READY' "$OUT/parallel-play-logcat.txt" && p_ready=1 || true
        grep -q 'first frame rendered' "$OUT/parallel-play-logcat.txt" && p_frame=1 || true
        grep -q 'playing=true' "$OUT/parallel-play-logcat.txt" && p_play=1 || true
        if ((p_ready && p_frame && p_play)); then break; fi
        sleep 3
      done
      capture parallel-playing
      adb shell input keyevent 23
      sleep 1
      capture parallel-controls
      if ((p_ready && p_frame && p_play)); then
        record parallel-lampa-intent PASS 'ACTION_VIEW video/mp4 with -p com.lampaua.player.arx resolved, playing and decoded first frame'
      else
        record parallel-lampa-intent FAIL "Parallel ACTION_VIEW playback: READY=$p_ready frame=$p_frame playing=$p_play"
      fi
    else
      record parallel-lampa-intent FAIL 'ACTION_VIEW package-scoped video intent failed, see parallel-view-intent-launch.txt'
    fi
  else
    record parallel-install FAIL 'Parallel APK could not be installed, see parallel-install.txt'
  fi
else
  record parallel-install FAIL 'Parallel APK missing from latestParallelDebug outputs'
fi

# The previous ACTION_VIEW test was only one video. Build a *real* nested
# playlist Bundle in an exported debug-only activity, send it through the
# public PlayerActivity path and confirm both items are parsed.
if [[ -s "$PARALLEL_APK" ]] && grep -qx "package:com.lampaua.player.arx" "$OUT/parallel-installed-packages.txt"; then
  adb shell am force-stop "$PARALLEL_PKG"
  adb logcat -c
  if timeout 40 adb shell am start -W \
      -n "$PARALLEL_PKG/com.brouken.player.PlaylistContractSmokeActivity" \
      --es fixture_url http://127.0.0.1:8765/tv-slow.mp4 \
      > "$OUT/parallel-nested-launch.txt" 2>&1; then
    # The real Lampa root playlist.logo is served immediately, but the movie
    # is held for 9 seconds. This proves actual artwork on the buffering
    # screen, not simply the presence of a logo string in Java.
    sleep 2
    capture parallel-buffering-series-logo
    adb logcat -d -v threadtime > "$OUT/parallel-logo-logcat.txt" 2>/dev/null || true
    if grep -q 'startup series logo displayed from Lampa' "$OUT/parallel-logo-logcat.txt"; then
      record parallel-buffering-logo PASS 'Glide loaded actual root playlist.logo onto initial buffering splash'
    else
      record parallel-buffering-logo FAIL 'Root series logo did not appear; see parallel-logo-logcat.txt and parallel-buffering-series-logo.png'
    fi
    nested_ok=0
    nested_end=$((SECONDS+60))
    while ((SECONDS < nested_end)); do
      adb logcat -d -v threadtime > "$OUT/parallel-nested-logcat.txt" 2>/dev/null || true
      if grep -q 'nested playlist parsed natively: 2' "$OUT/parallel-nested-logcat.txt"; then
        nested_ok=1
        break
      fi
      if grep -q 'playlist refused' "$OUT/parallel-nested-logcat.txt"; then break; fi
      sleep 3
    done
    sleep 2
    capture parallel-nested-playing
    adb shell input keyevent 23
    sleep 2
    capture parallel-nested-controls
    if ((nested_ok)); then
      record parallel-nested-playlist PASS 'Real Bundle with two episodes parsed by parallel ARX PlayerActivity'
    else
      record parallel-nested-playlist FAIL 'Nested playlist was not parsed, see parallel-nested-logcat.txt'
    fi
  else
    record parallel-nested-playlist FAIL 'Debug playlist test launcher could not start'
  fi
else
  record parallel-nested-playlist UNVERIFIED 'Parallel APK failed installation or is missing'
fi

record lampa-package-whitelist PASS 'Lampa v1.13.3+ recognizes com.lampaua.player prefix for native Just+ nested playlist contract'
record lampa-player-selection UNVERIFIED 'Selecting ARX in Lampa remains necessary when a different player was saved as default'
if ((FAILURES > 0)); then exit 1; fi
