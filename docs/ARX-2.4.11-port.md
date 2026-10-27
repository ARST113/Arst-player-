# ARX TPlayer — Just+ Player 2.4.8 beta → 2.4.11

Base: ARX `arx/v2.4.8-port`. The stable `master` (2.4.4) remains unchanged.
This workstream is a **reconstruction**, not a merge of upstream 2.4.11 source.

## Source-of-truth and APK audit

Official Just+ 2.4.8 and 2.4.11 tags currently refer to the SAME public source commit
`e17236bf1ef284f5ab7e68018fdb0432c72d1bdf` (dated October 9, 2026).
The release changes are packaged in APKs, without matching published source diffs.
Do not infer that the public source tag implements 2.4.11 changes.

SHA-256 pins (verified by `arx-2.4.11-apk-audit.yml`):
- 2.4.8: `16ba12c72a3e69ea99d118a5a0489053b5be1a16fb0dcd5b2257b7ce3b67b06b`
- 2.4.9: `5e6af09099840c813797fabb8ea178f807156eb32fae83e255aab063f3696367`
- 2.4.10: `4a4e6a0d9bc1657b96ca55c757dad8f270d5caf9ac9f7b3421747e7be47a6f5a`
- 2.4.11: `05868eaf3cfbcd25516430226836278fb63f535c14f66a5ee62da92c1ce634ba`

Official archive comparisons:

| Delta | Changed APK paths | Changed DEX files | Changed native libs |
|---|---:|---:|---:|
| 2.4.8 → 2.4.9 | 10 | 2 | 0 |
| 2.4.9 → 2.4.10 | 10 | 2 | 0 |
| 2.4.10 → 2.4.11 | 8 | 2 | 0 |

JADX Java output diff is indicative only; R8 changes renumber and rename classes, so
counts and class hashes DO NOT prove behavioral differences.

Official release notes:
- https://github.com/just-plus-player/just-plus-player/releases/tag/v2.4.9
- https://github.com/just-plus-player/just-plus-player/releases/tag/v2.4.10
- https://github.com/just-plus-player/just-plus-player/releases/tag/v2.4.11

## ARX UX invariant — deliberate deviation from official TV design

**Keep Play/Pause at the center of the video, separate from the timeline.**
`app/src/main/res/layout/exo_player_control_view.xml` preserves
`exo_play_pause` under `exo_center_controls` with `layout_gravity=center`.

Do not transplant the upstream button layout to the bottom bar.
CI `.github/scripts/arx-check-tv-center.py` and
`TvCenterPlaybackControlsTest` enforce the placement structurally.
D-pad center-button focus and timebar D-pad navigation need emulator/hardware smoke tests.

## Implementation ledger

| Official change | Current ARX implementation | Verification |
|---|---|---|
| v2.4.9 HTTP/1.1 transport | Forced `okhttp3.Protocol.HTTP_1_1` on media OkHttp client | Compiles under Actions; network playback device test outstanding |
| v2.4.9 HTTP 429 retry | New `ArxHttp429RetryInterceptor`: GET/HEAD only, one retry, bounded wait, always closes first 429 response | Unit tests + build; network 429 end-to-end test outstanding |
| v2.4.10 language Add picker empty | Suppresses Add when no languages remain; explicitly skips empty choice dialog | Build; TV focus test outstanding |
| v2.4.11 seek overlay linger after TV scrub | On scrub stop, clears top readout after 300 ms; keeps center Play/Pause intact | Build; TV remote test outstanding |
| v2.4.11 paused link expiry loses progress | Saves last valid launcher-result URI/position/duration in API session state; restores it after process recreation so finish() can return position when new stream fails | Build; Android background kill/expired URL test outstanding |
| v2.4.9 DV P7 Blu-ray TS → P8.1 and HDR10 fallback | **Not yet reconstructed/verified**; requires TS enhancement layer parsing/converter hookup | Blu-ray physical-media fixtures |
| v2.4.9 EAC3 7.1/Atmos in TS | **Not yet reconstructed/verified**; existing 2.4.8 path incomplete | Audio samples + AVR test |
| v2.4.9 LPCM from Blu-ray TS | **Not yet reconstructed/verified** | .m2ts/.ts LPCM fixtures |
| v2.4.9 track order and TV timebar stability | **Not yet verified for parity** | Audio picker + D-pad timing matrix |
| v2.4.10 aspect picker, focus and per-frame persistence | **Not yet verified for parity** | TV D-pad + cold-load fixture |
| v2.4.10 language names and selected-row focus | **Not yet verified for parity** | Multilingual picker cases |
| v2.4.10 delayed update installation | **Not yet reconstructed** | Background download/install |
| v2.4.10 error reports full code names | Existing errors use `error.getErrorCodeName()`; parity not fully audited | Error-code fixture |
| v2.4.8 HDMV TrueHD/AC3-core/DTS-HD demux | Existing 2.4.8 beta only partially implemented this | TS real samples required |

## Regression matrix before stable release

1. Android TV: center Play/Pause/loader focus with remote; DOWN to timeline, LEFT/RIGHT,
   scrub+release and readout timeout. No Play/Pause button in timeline or lower row.
2. Smartphone: touch scrubbing, seek gesture, controller hide/show.
3. Android: activity backgrounded paused, process killed, re-enter after link expires;
   Lampa receives valid watched-position result rather than zero.
4. Local and remote 188-byte TS/192-byte M2TS; genuine DV P7 Blu-ray and HDR10 fallback.
5. Blu-ray TrueHD core, DTS-HD, EAC3 7.1/Atmos and LPCM (passthrough and PCM decode).
6. Network requests: forced HTTP/1.1, Range/header propagation, 429 one retry only,
   reconnects, TorrServer and Lampac behind HTTP reverse proxy.
7. Standard MKV, MP4, Dolby Vision previously working on the owner's device;
   no regression in skipping, subtitles, playlist callbacks, and software HEVC.

CI: `.github/workflows/arx-2.4.11-build.yml` builds signed ARM preview artifacts and
runs JUnit; `.github/workflows/arx-2.4.11-apk-audit.yml` preserves binary audit results.
**Do not mark 2.4.11 fully reconstructed, merge into stable, or create a stable release
until the items above are implemented and tested.**
