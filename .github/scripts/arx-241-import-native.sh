#!/usr/bin/env bash
# Deterministic import of official Just+ 2.4.1 FFmpeg AUDIO JNI.
# This third-party binary is not reconstructed Java source; it is pinned to an immutable
# upstream APK and verified against the uploaded v2.4.1 original.
set -euo pipefail
URL="https://github.com/just-plus-player/just-plus-player/releases/download/v2.4.1/JustPlus.Player.v2.4.1.apk"
OFFICIAL_SHA="7739082ceb67bb4665ad0c17a32d9fa70f032e818bada881b1d1e3868651a09d"
TMP="$(mktemp -d)"
trap 'rm -rf "$TMP"' EXIT
curl -fLsS --retry 4 --retry-delay 2 --connect-timeout 30 "$URL" -o "$TMP/official.apk"
echo "$OFFICIAL_SHA  $TMP/official.apk" | sha256sum --check --status
for abi in arm64-v8a armeabi-v7a; do
    dest="app/src/main/jniLibs/$abi/libffmpegJNI.so"
    mkdir -p "$(dirname "$dest")"
    unzip -p "$TMP/official.apk" "lib/$abi/libffmpegJNI.so" > "$dest"
    if [[ "$abi" == "arm64-v8a" ]]; then
        expected="b9f2e4fb666ee383ec82728641ed78f1fa0d05b591e9a5dbfdd7c5a453b59567"
    else
        expected="2ad08381a044a5d621f2c14c17d4b2e0cb3a93c7fe1de310cbe8f6e296604574"
    fi
    echo "$expected  $dest" | sha256sum --check
done
# This is a versioned native audio decoder import only; the equalizer/Dolby-encoder UI
# and DRM/picture/ambient-light implementation still require Java integration.
echo "Verified official v2.4.1 FFmpeg JNI (ARMv7 + ARM64)."
