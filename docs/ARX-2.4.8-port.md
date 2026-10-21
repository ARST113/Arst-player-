# ARX TPlayer: Just+ 2.4.4 → 2.4.8 port

Workstream starts from the **released 2.4.4 source** (stable branch `master`). Changes are isolated in `arx/v2.4.8-port`; they must not affect the 2.4.4 stable release.

## Vendor APK audit (21 October 2026)

Official 2.4.4: `c4c955bd69d48272f43066b028dd54a370545bf6aaca2a0720ca57e3f99dff0d`.
Official 2.4.8: `16ba12c72a3e69ea99d118a5a0489053b5be1a16fb0dcd5b2257b7ce3b67b06b`.

- Both APKs have 1,392 entries. 63 paths differ, primarily DEX/resources/signature.
- **Zero native libraries changed**, including libffmpegJNI.so and libffvideoJNI.so. No native binary replacement is required solely on these release deltas.
- R8 mappings were changed between builds. Decompiled Java class file hashes alone do not prove behavioral differences.
- The `.m2ts` and `.mts` extension changes are explicit vendor release features; support requires verifying that the transport-stream extractor can parse actual 192-byte M2TS and TrueHD/DTS-HD.
- Reproducible code/archive report: Actions workflow `.github/workflows/arx-2.4.8-apk-audit.yml`.

## Original release changes to reconstruct

### 2.4.5
- GPU picture processing workload optimization and automatic per-item GPU fallback.
- Audio fallback to own FFmpeg decoder for incorrect stream labels; tunneled PCM surround stereo downmix and tunneling off on failures.
- Remaining-time clock at changed playback speeds; missing subtitle language names.
- Screen insets, title shadows, consistent UI text sizes and D-pad bottom-button wrap.

### 2.4.6
- TrueHD passthrough framing for boxes that accept it but lose sound during native transmission.
- Permanent session codec revocation after repeated bitstream stalls; deduplicate equivalent dub tracks.
- Better dub alias/language normalization.
- Ignore misreported display refresh-rate setting; actual incoming network bitrate, accurate buffer fill and optional loading speed.
- Persist per-external-app frame resize mode; D-pad settings disabled-row fix.

### 2.4.7
- Open and browse Blu-ray `.m2ts` and camera `.mts`.
- Propagate Android MediaSession play/pause/seek commands from companion remote interfaces.

### 2.4.8
- Demux TrueHD, EAC3 and DTS-HD audio from Blu-ray `.m2ts` and `.ts`.
- Advertise AC3 core embedded in TrueHD as a separate selectable track while keeping TrueHD default.
- Read EAC3 up to 5.1 channels, discard extras beyond 5.1.
- Hide orphan separate 1080p Dolby Vision enhancement video; main base layer plays without DV on such files.

## Applied

- `Utils.supportedExtensionsVideo` recognizes `.m2ts` and `.mts`; browser selection can discover them. **Opening these files and decoding advanced TS audio is NOT yet claimed to work.**

## Test matrix before any 2.4.8 preview

- Local and SMB/DLNA .m2ts, .mts, .ts, ordinary .mkv/.mp4.
- Genuine 192-byte Blu-ray packetized M2TS, standard 188-byte TS (including non-zero start-offset seeks).
- TrueHD+embedded AC3 core and independent EAC3/DTS-HD, receiver pass-through vs FFmpeg decode and track ordering.
- Blu-ray Dolby Vision enhancement-layer filtering (do not hide ordinary dual-video streams).
- Android TV MediaSession external play/pause/seek, Home/standby/return, and Lampa playlist progress callbacks.
- HDR/Dolby Vision regression test against the user's known working 2.4.4 reference.

Do not publish 2.4.8 stable or claim binary parity until these checks are complete.
