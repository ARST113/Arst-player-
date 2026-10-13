# ARX TPlayer — Just+ 2.4.1 reconstruction tracking

Reference: https://github.com/just-plus-player/just-plus-player/releases/tag/v2.4.0 and https://github.com/just-plus-player/just-plus-player/releases/tag/v2.4.1

Official APK: `JustPlus.Player.v2.4.1.apk`, SHA-256 `7739082ceb67bb4665ad0c17a32d9fa70f032e818bada881b1d1e3868651a09d`. The uploaded file and GitHub release asset match.

**ARX version 2.4.1-arx1 on `master`** incorporates the reconstructed Just+ 2.3.0 base and a SHA-pinned native audio FFmpeg JNI from official Just+ 2.4.1. It retains Lampa playlist support, poster/splash, controls, subtitle preferences, sleep/wake and ARX branding. **This is a feature-incomplete port, not a complete Just+ 2.4.1 implementation**. User approved publication of the tested ARX build into master with the below known gaps. Stable ARX distribution does not imply upstream feature parity.

## Implemented / verifiable
- [x] Preserve existing ARX signing key, `com.justplus.player`, and monotonic versionCode `2004002` for update-over-install from earlier ARX releases.
- [x] Original 2.4.1 release and SHA-256 verified against the supplied APK.
- [x] Reproducible build-time import of 2.4.1 FFmpeg **audio** JNI for ARMv7 and ARM64; expected hashes pinned.
- [x] Preserve 2.3.0 native video JNI (identical SHA-256 to 2.4.1 for ARMv7 and ARM64).
- [x] Preserve removal of political imagery from About and use the independent ARX update feed.
- [x] GitHub Actions phone and Android TV emulator tests validated for the preview: H.264 + subtitles + AVI + D-pad + sleep/wake + About + HTTP pause >30 seconds.
- [x] The experimental ARX preview was kept prerelease; the `2.4.1-arx1` release is a separately tagged, incrementally versioned APK from `master`. Its incomplete upstream parity is declared in README and release notes.

## Outstanding relative to Just+ 2.4.0 / 2.4.1
- [ ] 10-band real-time PCM equalizer with five presets and passthrough safeguards.
- [ ] Dolby Digital / Dolby Digital Plus encoding from multichannel PCM, bitrate options and AVR capability checks. The new JNI alone does not make this feature work.
- [ ] Picture adjustment renderer for supported non-DRM SDR/HDR, presets, correct DV/software decoder bypass.
- [ ] Ambient Light sampling/performance fallback and four modes.
- [ ] Widevine / ClearKey licence plumbing and DRM-safe UI/surface selection.
- [ ] Long-paused HTTPS stream reconnect and exact v2.4.1 crash regression (offline HTTP is a different test).
- [ ] TV Resize/Zoom menu parity and TV overscan/focus adjustments.
- [ ] Remaining settings, translations, buffer percent and HLS bitrate parity.
- [ ] On-device comparison of 2.4.1 and ARX in Lampa for playlists, track selection, seek, poster splash and UI.
- [ ] Native audio JNI compatibility and AC3/EAC3/DTS/TrueHD output on ARM hardware.

## Required user testing on Android TV
1. Install the **ARX release APK** over previous ARX 2.4.1-arx1-preview, 2.3.0-arx2 or 2.2.2-arx3 without uninstalling; confirm settings retained.
2. From Lampa start a multi-episode playlist. Inspect startup logo, buffering/poster, immediate subtitle/voice choices, seek, resume and next episode.
3. Play HTTPS/TorrServer streams; pause for >=40 seconds; resume, seek and switch episodes. Report any crash or endless spinner.
4. Check AC3, EAC3, DTS and passthrough (5.1/7.1) and compare with old ARX. Do not claim native encoder parity yet.
5. 4K HDR10/Dolby Vision on hardware, smoothness, first frame and remote D-pad focus.
6. About must show ARX attribution and must **not** show the unwanted political banner. Check controls completely disappear during playback.

### Release policy
Only features confirmed by the checks can be claimed supported. The published mainline 2.4.1-arx1 is a **tested ARX baseline**, not a declaration of functional parity with vendor Just+ 2.4.1. Outstanding capabilities above still require implementation. Android TV / Dolby Vision / HDMI passthrough must be validated on physical equipment; the original intermittent TorrServer AAC report is documented in a separate diagnostic branch.
