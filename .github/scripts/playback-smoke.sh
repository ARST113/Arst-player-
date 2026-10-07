#!/usr/bin/env bash
set -euo pipefail

PKG="com.justplus.player"
ACTIVITY="com.brouken.player.PlayerActivity"
SMOKE_DIR="/tmp/arx-playback-smoke"
PORT=8090
URL="http://10.0.2.2:${PORT}/smoke.mp4"
LOG="${SMOKE_DIR}/logcat.txt"
SHOT="${SMOKE_DIR}/playback.png"

mkdir -p "${SMOKE_DIR}"

APK="$(find app/build/outputs/apk/latestUniversal/debug -name '*.apk' -type f | head -n1)"
if [[ -z "${APK}" ]]; then
  echo "::error::Debug APK not found"
  exit 1
fi

if ! command -v ffmpeg >/dev/null 2>&1; then
  echo "::error::ffmpeg is not installed on the runner"
  exit 1
fi

# A real MP4: moving test pattern, H.264, 1280x720, 24 fps, 8 seconds.
# Generated locally so the smoke test has no dependency on a third-party media host.
ffmpeg -hide_banner -loglevel error -y \
  -f lavfi -i "testsrc2=size=1280x720:rate=24" \
  -t 8 -an -c:v libx264 -pix_fmt yuv420p \
  -profile:v main -level 3.1 -g 48 -movflags +faststart \
  "${SMOKE_DIR}/smoke.mp4"

python3 -m http.server "${PORT}" --bind 0.0.0.0 --directory "${SMOKE_DIR}" \
  >"${SMOKE_DIR}/http.log" 2>&1 &
HTTP_PID=$!
trap 'kill "${HTTP_PID}" >/dev/null 2>&1 || true' EXIT

for _ in {1..20}; do
  if curl -fsS -o /dev/null "http://127.0.0.1:${PORT}/smoke.mp4"; then
    break
  fi
  sleep 0.5
done
curl -fsS -o /dev/null "http://127.0.0.1:${PORT}/smoke.mp4"

adb wait-for-device
adb install -r "${APK}"
adb shell am force-stop "${PKG}" || true
adb logcat -c

echo "Opening real video: ${URL}"
adb shell am start -W \
  -n "${PKG}/${ACTIVITY}" \
  -a android.intent.action.VIEW \
  -d "${URL}" \
  -t video/mp4

deadline=$((SECONDS + 50))
decoder=0
ready=0
first_frame=0

while (( SECONDS < deadline )); do
  adb logcat -d > "${LOG}"

  grep -Fq "video decoder: init" "${LOG}" && decoder=1 || true
  grep -Fq "state READY" "${LOG}" && ready=1 || true
  grep -Fq "first frame rendered" "${LOG}" && first_frame=1 || true

  if grep -Fq "Process: ${PKG}" "${LOG}" && grep -Fq "FATAL EXCEPTION" "${LOG}"; then
    echo "::error::Player crashed while opening the smoke video"
    break
  fi

  if (( decoder && ready && first_frame )); then
    break
  fi
  sleep 1
done

# Keep human-readable evidence even on failure.
adb exec-out screencap -p > "${SHOT}" || true
adb shell dumpsys activity activities > "${SMOKE_DIR}/activity.txt" || true
adb logcat -d > "${LOG}" || true

echo "decoder=${decoder} ready=${ready} first_frame=${first_frame}"
grep -E "video decoder: init|state (READY|BUFFERING|IDLE|ENDED)|first frame rendered|ERROR_CODE_|FATAL EXCEPTION|Process: ${PKG}" "${LOG}" | tail -n 120 || true

if (( !decoder || !ready || !first_frame )); then
  echo "::error::Real playback smoke failed: decoder=${decoder}, ready=${ready}, first_frame=${first_frame}"
  exit 1
fi

echo "Real playback smoke passed: decoder initialized, player READY, first frame rendered."
