# ARX TPlayer: port 2.3.0

Upstream release: https://github.com/just-plus-player/just-plus-player/releases/tag/v2.3.0

## Implemented on the work branch

- [x] Remove the political About banner, its preference and both unused layouts.
- [x] Point About > Source code and in-app update feed to the independent ARX repository.
- [x] Interpret ARX tag revision, e.g. arx-v2.3.0-arx1 = 2003001.
- [x] Set candidate app versionName 2.3.0-arx1 (versionCode 2003001).

## Remaining to port or verify

- [ ] Preferred-order audio/subtitle lists; language inference from track names and studio labels.
- [ ] Forced-only subtitle mode, respecting language preference and explicit selection.
- [ ] Clock, ETA and LIVE: off/on pause/always; migrate existing boolean preferences.
- [ ] Dolby Vision / MediaTek decoder handling and AVI MP4V.
- [ ] Release video decoder on TV sleep and return paused.
- [ ] Remote focus/edge fades, AMOLED, subtitle preview, reset notice and language fixes.
- [ ] Android CI build and installation, upgrade, TV remote and media smoke tests.

This is a **development branch**, not a stable 2.3.0 release. Do not publish an APK until verified.
