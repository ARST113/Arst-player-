# ARX TPlayer — reconstruction of Just+ 2.4.4 (in progress)

Baseline: `master` currently builds ARX 2.4.1-arx1, already without full vendor 2.4.x feature parity.

## Evidence from real official APKs

The `arx/v2.4.4-port` workflow downloads both official APKs directly from release assets, verifies SHA-256, uses JADX 1.5.3 on both DEX sets, and publishes reproducible audit evidence.

| Official release | SHA-256 |
| --- | --- |
| Just+ 2.4.1 | `7739082ceb67bb4665ad0c17a32d9fa70f032e818bada881b1d1e3868651a09d` |
| Just+ 2.4.4 | `c4c955bd69d48272f43066b028dd54a370545bf6aaca2a0720ca57e3f99dff0d` |

Both official APKs contain 1,392 ZIP entries. Exactly 23 paths differ: DEX, a few XML resources, binary resource table, metadata and signatures. None of the `lib/` entries changed, so the existing 2.4.1 native FFmpeg JNI stays pinned; there is no justification for blindly replacing audio or video libraries.

**Caution:** The two releases were obfuscated with different R8 maps. A naïve JADX file diff flags thousands of renamed/rewritten classes; it is *not* evidence of thousands of behavioral changes. Compare stable strings and their surrounding methods, then validate the implementation at runtime.

The new 2.4.4 `PlayerActivity` DEX builds a playlist callback snapshot and, for periodic notifications, changes `end_by` from `user` to the exact string **`interval`**. This was confirmed from the decompiled official APK, not inferred from release notes. The 2.4.1 APK has no corresponding conversion.

## Implemented on this feature branch

- **2.4.4 periodic progress callbacks**: restore the already-parsed nested `playlist.report_interval` and `playlist.result_callback` contract. Timer runs while playback is active, takes snapshots from the current media index/position/history, emits `end_by=interval`, and cancels on pause, stop, destroy or API session replacement. Exact deduplication and the existing final `user`/`completion` callbacks remain.
- **2.4.4 TV surface recovery**: release the hardware video track on genuine TV background transitions so Media3 rebuilds the codec for the reattached surface on return, while retaining the timeline, buffer and playback position. Keep the pre-existing Realtek Dolby Vision exception and the screen-restart watchdog/fallback; this exception still requires physical-box validation.
- **2.4.2 subtitle ordering**: preferred languages keep their explicit priority; all other subtitle tracks sort by their top-line displayed name rather than by language code. Regression unit tests cover this and preferred-track precedence.
- **APK audit**: SHA-verified comparison and DEX inspection preserved in CI artifacts, not committed vendor APKs or recompiled decompiled vendor code.

## New in this experimental 2.4.4 preview

- **Ten-band PCM EQ**: software biquads at 31–16,000 Hz with six locally configured presets, ±12 dB, and immediate control from More or Settings. Bypasses direct encoded passthrough.
- **Experimental Dolby Digital / Dolby Digital Plus encoder**: restored the vendor's exact `Ac3Encoder` native JNI interface (`nAvailable/nOpen/nPush/nPull/nDelay/nFinish/nRelease`) and attached an `AudioOutputProvider` wrapper *after* Media3's EQ, volume and night-mode processors. A Dolby bitstream is attempted for 48 kHz decoded PCM with 3–8 channels, only when the receiver reports passthrough support. Off by default. Modes: Off / Automatic / Always, bitrate 448 or 640 kbit/s. This branch compiles, but **bitstream correctness, 5.1 speaker ordering, synchronization, 7.1 handling, FFmpeg native ABI and 448/640 options all require physical-receiver validation**. Turn Off if sound is absent or out of sync. Forced passthrough capabilities intentionally skip the experimental provider until validated.
- **Picture editor**: five persistent GPU parameters, Brightness, Contrast, Saturation, Warmth and Lightness, five presets. Media3 GPU video effects; no processing for DRM, Dolby Vision or native software-decoded video. Exact vendor gamma/shadows/sharpness controls are **not** yet replicated.
- **Ambient Light**: four modes Glow/Cinema/Echo/Mirror plus Off, edge sampling from a 32×18 bitmap via PixelCopy or TextureView, adjustable reach, brightness and refresh speed, adaptive sampling backoff, automatically disabled for protected or Dolby Vision playback. TV placement/letterboxing must be checked on hardware.
- **Widevine and ClearKey manifest/explicit licence**: Media3 DRM configuration accepted via launcher bundles or URI parameters; disables picture and Ambient UI for DRM streams. Header mapping and DRM URL support are incomplete relative to all possible licence-server protocols.
- **Refresh-rate matching selector**: Legacy, Off, Automatic, Same, Double and Highest; earlier preferences survive via Legacy. Resolution match remains optional.
- **Incomplete MKV on TorrServer**: bounded recovery skips a 2-second hole after ordinary re-read attempts are exhausted, at most three times per item. Needs field validation before calling it equivalent to the vendor solution.
- **TV Resize/Zoom**: existing crop/aspect picker exposed in the More menu.
- **2.4.4 callbacks, TV decoder restoration and 2.4.2 subtitle ordering**, documented above.

## Known omissions and risks

- Picture **gamma, shadows, sharpness**: dedicated GPU controls absent.
- DRM licence formats outside the implemented Widevine/ClearKey configuration, including custom POST bodies and licence transforms, have not been verified.
- The HTTPS >30-second pause fix and exact vendor refresh-rate/OEM recovery paths need on-device regression tests.
- Ambient Light on atypical TV surface layouts and HDR paths requires hardware validation.
- Dolby encoder is experimental: the native ABI matches the reference but no real HDMI audio has yet been heard or captured. It is off by default.
- Original 2.4.4 is a closed APK with obfuscated R8 output; compilation and feature-similar implementation are **not proof of complete functional parity**.

## Verification and release policy

GitHub Actions on the feature branch runs APK checksum comparison, source compilation and JVM unit tests. A successful emulator or compiler does not establish hardware decoder, HDMI passthrough, Dolby Vision or TorrServer parity.

Do **not** merge or publish this branch as a fully compatible Just+ 2.4.4 replacement solely on the basis of a successful Gradle run. Test Lampa nested playlists (callback interval + final result), TV HOME/settings/sleep/return, seek and stream switching, DV profiles, AC3/EAC3/DTS output and TorrServer incomplete MKV on real hardware. Stable `master` remains on its existing ARX baseline until validated.


## Suggested first hands-on test sequence

1. Install signed `ARX.TPlayer.v2.4.4-arx1-preview.apk` over previous ARX without uninstalling, confirm settings survive. This APK is **experimental**.
2. Play the same Lampa playlist as before. Verify loading splash and post-buffer first frame, audio/subtitle/quality selections, switching episode, progress callback every requested interval, Back/final callback.
3. Open More > Equalizer. Toggle it and test a voice preset on an AAC file. With native DTS/AC3 passthrough, EQ should bypass.
4. Open More > Picture and Ambient Light, test wide-screen letterbox, bright and dark scenes, remote focus, video seek, and navigation back to the player.
5. Test Settings > match refresh rate with 24/25/30/60 FPS clips, then disable before trying unusual resolutions.
6. Test HOME/Settings/standby/return on Android TV, HEVC/HDR10/Dolby Vision including Realtek if available.
7. Only if connected to a receiver known to decode DD/DD+: enable experimental Dolby encode and test a 48 kHz multichannel source. If audio is missing, distorted or out of sync, immediately turn the mode Off. Collect model, Android version and a logcat trace.
8. Test seeking inside partially downloaded TorrServer MKV; report any loop, jump, or missing subtitles.

Do not promote this PR to stable until these regressions have been checked and outstanding discrepancies decided explicitly.
