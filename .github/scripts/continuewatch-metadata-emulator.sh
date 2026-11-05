#!/usr/bin/env bash
# Android Emulator smoke: actual ARX debug APK, real Media3 video and the nested Lampa playlist contract.
set -Eeuo pipefail
PKG=com.justplus.player
ACT=com.brouken.player.MetadataSmokeActivity
OUT=metadata-emulator-evidence
mkdir -p "$OUT"
printf 'scenario\tparse\tplayback\tcrash\tstatus\n' > "$OUT/results.tsv"

collect() {
  adb logcat -d -v threadtime > "$OUT/final-logcat.txt" 2>/dev/null || true
  adb shell dumpsys activity activities > "$OUT/final-activity.txt" 2>/dev/null || true
  adb exec-out screencap -p > "$OUT/final-screen.png" 2>/dev/null || true
}
trap collect EXIT

apk="$(find app/build/outputs/apk/latestUniversal/debug -name '*.apk' -type f | head -n 1)"
test -s "$apk"
adb install -r "$apk"
adb reverse tcp:8765 tcp:8765
# Suppress the first-launch immersive-mode help overlay, which occluded the first run.
adb shell settings put secure immersive_mode_confirmations confirmed || true
adb shell settings put global window_animation_scale 0 || true
adb shell settings put global transition_animation_scale 0 || true
adb shell settings put global animator_duration_scale 0 || true

crash_found() {
  local file="$1"
  grep -A 8 -F 'FATAL EXCEPTION:' "$file" | grep -Fq "Process: $PKG" && return 0
  grep -Fq "Process $PKG" "$file" && grep -Fq 'has died' "$file" && return 0
  return 1
}
resolve_android_overlays() {
  local label="$1"
  # On first boot Google Pixel Launcher may show an ANR dialog over an actively
  # decoding player; the app itself is healthy. Dismiss the system window.
  for round in 1 2 3; do
    adb shell uiautomator dump /sdcard/overlay.xml >/dev/null 2>&1 || true
    adb pull /sdcard/overlay.xml "$OUT/$label-overlay-$round.xml" >/dev/null 2>&1 || true
    local focus
    focus="$(adb shell dumpsys window | grep -F 'mCurrentFocus=' | tail -n 1 || true)"
    if grep -Eq 'immersive_cling_title|Viewing full screen' "$OUT/$label-overlay-$round.xml" 2>/dev/null; then
      adb shell input tap 1700 500 || true
      sleep 1
      continue
    fi
    if [[ "$focus" == *"Not Responding"* || "$focus" == *"nexuslauncher"* || "$focus" == *"ANR"* ]]; then
      adb shell input tap 820 535 || true   # "Close app" on the launcher-only ANR modal
      sleep 1
      continue
    fi
    break
  done
  # Show controls to check Android-side title rendering.
  adb shell input keyevent 23 || true
  sleep 1
}

capture() {
  local scenario="$1"
  resolve_android_overlays "$scenario"
  adb exec-out screencap -p > "$OUT/$scenario.png" 2>/dev/null || true
  adb shell uiautomator dump /sdcard/window.xml > /dev/null 2>&1 || true
  adb pull /sdcard/window.xml "$OUT/$scenario.xml" > /dev/null 2>&1 || true
  adb shell dumpsys activity activities > "$OUT/$scenario-activity.txt" 2>/dev/null || true
}

fail=0
check_case() {
  local label="$1" scenario="$2" index="$3"
  adb shell am force-stop "$PKG" || true
  adb logcat -c
  adb shell am start -W -n "$PKG/$ACT" \
    --es scenario "$scenario" --ei index "$index" > "$OUT/$label-launch.txt" 2>&1 || true

  local parse=0 ready=0 frame=0 crash=0
  for n in $(seq 1 24); do
    adb logcat -d -v threadtime > "$OUT/$label-logcat.txt" 2>/dev/null || true
    grep -Fq "ARX_METADATA_SMOKE" "$OUT/$label-logcat.txt" && \
      grep -Fq "PARSE_OK scenario=$scenario" "$OUT/$label-logcat.txt" && parse=1 || true
    grep -Fq 'state READY' "$OUT/$label-logcat.txt" && ready=1 || true
    grep -Fq 'first frame rendered' "$OUT/$label-logcat.txt" && frame=1 || true
    if crash_found "$OUT/$label-logcat.txt"; then crash=1; break; fi
    if ((parse && ready && frame)); then break; fi
    sleep 2
  done

  # A valid series case must carry both names, per-episode stills, progress and TMDB ID.
  local data=0
  if ((parse)); then
    if grep -Fq 'positionMs=3000' "$OUT/$label-logcat.txt" && \
       grep -Fq 'thumbnail=https://lampac.art/' "$OUT/$label-logcat.txt" && \
       grep -Fq 'tmdbId=' "$OUT/$label-logcat.txt"; then data=1; fi
  fi

  capture "$label"
  if ((parse && data && ready && frame && !crash)); then
    printf '%s\tPASS\tPASS\tNO\tPASS\n' "$label" | tee -a "$OUT/results.tsv"
  else
    printf '%s\t%s\tready=%s frame=%s data=%s\t%s\tFAIL\n' \
      "$label" "$parse" "$ready" "$frame" "$data" "$crash" | tee -a "$OUT/results.tsv"
    echo "::error::$label: parser=$parse, fields=$data, READY=$ready, first_frame=$frame, crash=$crash"
    fail=1
  fi
}

check_case "series-episode-1" series 0
check_case "series-episode-2" series 1
check_case "movie" movie 0
check_case "anime-episode-2" anime 1

# Preserve optional next/previous key experiment separately: Android builds may
# use a custom playlist overlay rather than receiving KEYCODE_MEDIA_NEXT.
adb shell am force-stop "$PKG" || true
adb shell am start -W -n "$PKG/$ACT" --es scenario series --ei index 0 \
  > "$OUT/media-next-launch.txt" 2>&1 || true
sleep 6
adb shell input keyevent 87 || true
sleep 3
capture media-next
adb logcat -d -v threadtime > "$OUT/media-next-logcat.txt" 2>/dev/null || true
if grep -Fq 'Королевский тракт' "$OUT/media-next.xml" 2>/dev/null; then
  echo 'NEXT: second episode displayed in UI' | tee "$OUT/media-next-result.txt"
else
  echo 'NEXT: no definitive UI evidence for KEYCODE_MEDIA_NEXT, review screenshot/logcat' | tee "$OUT/media-next-result.txt"
fi

exit "$fail"
