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

## Deliberately not claimed as transferred

- 2.4.0: ten-band EQ, Dolby Digital / Dolby Digital Plus encoding, picture controls, Ambient Light and DRM are still missing from the ARX baseline.
- 2.4.1: vendor HTTPS pause/resume and protected-picture fixes need independent implementation/validation.
- 2.4.2: revised automatic refresh-rate modes, incomplete TorrServer MKV skip logic, Ambient Light liveness and crop/zoom reconnect handling need separate code-level porting.
- 2.4.3–2.4.4: exact OEM decoder-specific behavior cannot be considered verified until tested on real Android TV equipment.

## Verification and release policy

GitHub Actions on the feature branch runs APK checksum comparison, source compilation and JVM unit tests. A successful emulator or compiler does not establish hardware decoder, HDMI passthrough, Dolby Vision or TorrServer parity.

Do **not** merge or publish this branch as a fully compatible Just+ 2.4.4 replacement solely on the basis of a successful Gradle run. Test Lampa nested playlists (callback interval + final result), TV HOME/settings/sleep/return, seek and stream switching, DV profiles, AC3/EAC3/DTS output and TorrServer incomplete MKV on real hardware. Stable `master` remains on its existing ARX baseline until validated.
