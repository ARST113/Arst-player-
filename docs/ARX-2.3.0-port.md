# ARX TPlayer — Just+ Player 2.3.0 parity tracking

Official reference: https://github.com/just-plus-player/just-plus-player/releases/tag/v2.3.0
APK inspected: JustPlus.Player.v2.3.0.apk (2026-10-09), SHA-256 `5deeb32876fef5ae76ea0441a7fe80b6564ab5acc03eeeb276af69fdab910639`

This is a **behavioral reconstruction** of a release whose source is not published.
Matching the versionName is not evidence of 1:1 behavior. The status below differentiates
implemented source changes from checks on actual hardware.

## Completed source changes

- [x] Remove the About political banner and its dedicated layout, strings and flag colors.
- [x] ARX developer/source/release feed separated from Just+, with an upstream-author credit and link.
- [x] ARX update tags, versionCode 2003001, versionName 2.3.0-arx1.
- [x] Preserve the original and preferred-language audio menu ordering, with name-based language inference.
- [x] Prefer recognized, untagged audio over a lower-priority tagged fallback, without overriding an explicit Lampa/viewer choice.
- [x] Sort subtitles by preferred language, device language and English; forced tracks first within a language.
- [x] Prefer a recognized untagged subtitle over a lower-priority automatically selected tagged track.
- [x] Forced-only mode with secondary-subtitle protection and manual/Lampa precedence.
- [x] Clock, end time and LIVE overlay visibility: off / paused / always. Legacy clock migration guards against `setDefaultValues` writing the new key.
- [x] Persist AppCompat application locales on Android <=12 using AppLocalesMetadataHolderService.
- [x] Avoid the misleading file-average 0.0 Mbps readout for unknown-length streams.
- [x] Release the video decoder when a TV actually sleeps; preserve paused playback on return. Keep a cautious exception for unverified Realtek DV drivers.
- [x] Prefer the system-provided MediaTek DV decoder on compatible Fire TV and MediaTek hardware.
- [x] Recognize explicit `mp4v` / `mp4v.*` codec tags when the MPEG-4 Visual MIME is absent in an AVI track.
- [x] GitHub CI compiles a signed ARM APK, checks version/signature and verifies that the four native FFmpeg library SHA-256 hashes match the official 2.3.0 APK.
- [x] Add JVM regression tests for language inference, preference ordering and forced-track names (test runtime check in progress).

## Outstanding work needed for claimed 1:1 parity

- [ ] Video and audio selection: real MKV fixtures covering tagged/untagged tracks, dubbed studios, multiple languages, explicit user overrides and Lampa track requests.
- [ ] Finish the official expanded language/dubbing-studio dictionary; our dictionary currently covers a useful subset.
- [ ] Dolby Vision profile 8 with mixed HDR10+ dynamic metadata on MediaTek: confirm strip/ignore behavior without corrupting HEVC access units.
- [ ] AVI MP4V: run a real file through Media3 extractor -> selected video renderer -> native FFmpeg and verify rendered frames.
- [ ] TV sleep/resume: hardware test on Fire TV, MediaTek and Realtek including repeat suspend, seeking, audio passthrough and return paused.
- [ ] Subtitle preview reproduces real subtitle size across phone/tablet/TV densities.
- [ ] Correct placement of the notice after resetting a settings screen.
- [ ] AMOLED translucent video controller background.
- [ ] Android TV focus outline consistency, selected-theme tick/focus and long-list edge fades.
- [ ] Android <=12 cold-restart locale persistence and in-place upgrade path validated on device.
- [ ] Lampa nested-playlist poster/splash, full auto-hide of seek controls, AC3/EAC3/TrueHD/DTS, 4K HDR and Dolby Vision smoke tests.
- [ ] Compare final APK/UI with official release across video, audio, subtitles, browsing, settings, controls, TV and playback lifecycles.

The author's description of update-time \"mutations\" is not enough to establish a specific protection method.
Compare runtime behavior and DEX before claiming any correspondence.

## Manual test fixtures

1. AVI with `MP4V` stream: check presence of video and native renderer selection.
2. MKV: two tagged languages, one preferred untagged language, one forced-only and one full subtitle.
3. Fire TV / MediaTek: Dolby Vision P8 + HDR10+ stream (verify picture, HDR output, seeking).
4. Realtek TV box: 4K DV, screen sleep, wake, pause and restart.
5. Lampa: nested playlist with explicit audio/subtitle request and an episode transition.
6. Android 12: set non-system language, force-stop, relaunch, restart device and recheck.

**Status:** WIP draft PR; signed build compilation has passed for preceding commits. No stable release or merge until CI regression tests and targeted device checks pass.
