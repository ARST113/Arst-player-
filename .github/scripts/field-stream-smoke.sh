#!/usr/bin/env bash
set -euo pipefail

PKG="com.justplus.player"
ACTIVITY="com.brouken.player.PlayerActivity"
ROOT="field-stream-smoke"
mkdir -p "$ROOT"

APK="$(find app/build/outputs/apk/latestUniversal/debug -name '*.apk' -type f | head -n1)"
test -n "$APK"
adb install -r "$APK"

run_case() {
  local name="$1"
  local url="$2"
  local expect_hevc="$3"
  local dir="$ROOT/$name"
  mkdir -p "$dir"

  echo "=== CASE: $name ==="
  adb shell am force-stop "$PKG" || true
  adb logcat -c

  # Keep the URL quoted on the device shell: its query string contains '&'.
  adb shell "am start -W     -n $PKG/$ACTIVITY     -a android.intent.action.VIEW     -t video/x-matroska     -d '$url'" > "$dir/am-start.txt"

  local deadline=$(( $(date +%s) + 120 ))
  local ready=0
  local playing=0
  local frame=0

  while (( $(date +%s) < deadline )); do
    adb logcat -d -v threadtime > "$dir/logcat.txt" || true

    grep -q "state READY" "$dir/logcat.txt" && ready=1 || true
    grep -q "playing=true" "$dir/logcat.txt" && playing=1 || true
    grep -q "first frame rendered" "$dir/logcat.txt" && frame=1 || true

    if grep -q "FATAL EXCEPTION" "$dir/logcat.txt" &&
       grep -q "Process: $PKG" "$dir/logcat.txt"; then
      echo "::error::$name crashed"
      break
    fi

    if (( ready && playing && frame )); then
      break
    fi
    sleep 2
  done

  adb logcat -d -v threadtime > "$dir/logcat.txt" || true
  adb shell dumpsys media.codec > "$dir/media-codec.txt" || true
  adb shell dumpsys activity activities > "$dir/activity.txt" || true

  adb shell input tap 540 1050 || true
  sleep 1
  adb exec-out screencap -p > "$dir/player.png" || true
  adb shell uiautomator dump /sdcard/window.xml >/dev/null 2>&1 || true
  adb pull /sdcard/window.xml "$dir/window.xml" >/dev/null 2>&1 || true

  {
    echo "case=$name"
    echo "ready=$ready"
    echo "playing=$playing"
    echo "first_frame=$frame"
    echo
    echo "--- relevant playback log ---"
    grep -E "video input:|video decoder: init|audio decoder: init|state (IDLE|BUFFERING|READY|ENDED)|playing=|first frame rendered|rebuild: HEVC|NextLib FFmpeg|ERROR_CODE_|codec error|FATAL EXCEPTION"       "$dir/logcat.txt" | tail -n 200 || true
  } | tee "$dir/result.txt"

  if [[ "$expect_hevc" == "yes" ]]; then
    if ! grep -q "video input: video/hevc" "$dir/logcat.txt"; then
      echo "::error::$name did not expose a HEVC video track"
      return 1
    fi

    decoder="$(grep "video decoder: init" "$dir/logcat.txt" | tail -n1 || true)"
    if grep -q "ffmpegLavc.*-hevc" "$dir/logcat.txt"; then
      echo "HEVC path: FFmpeg/libavcodec decoder active. $decoder" | tee -a "$dir/result.txt"
    elif grep -q "NextLib FFmpeg/libavcodec video decoder" "$dir/logcat.txt"; then
      echo "HEVC path: NextLib FFmpeg fallback was exercised. $decoder" | tee -a "$dir/result.txt"
    else
      echo "HEVC path: MediaCodec decoder active; FFmpeg fallback was not required. $decoder" | tee -a "$dir/result.txt"
    fi  fi

  if (( !ready || !playing || !frame )); then
    echo "::error::$name playback failed: READY=$ready playing=$playing first_frame=$frame"
    return 1
  fi

  echo "$name: PASS"
}

fail=0
run_case "mobland-h264" "$MOBLAND_URL" "no" || fail=1
run_case "undead-hevc10" "$HEVC_URL" "yes" || fail=1
exit "$fail"
