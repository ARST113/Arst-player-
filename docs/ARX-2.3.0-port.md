# ARX TPlayer: reconstruction of Just+ Player 2.3.0

Upstream: https://github.com/just-plus-player/just-plus-player/releases/tag/v2.3.0

## Implemented and compiled

- [x] Remove political banner from About, references and unused flag-colour resources.
- [x] ARX About source link, developer identity and in-app update feed point to ARX. Original upstream authorship documented in README.
- [x] Preserve Android upgrade codes, interpret release tags `arx-v2.3.0-arxN`.
- [x] Candidate package `2.3.0-arx1` / versionCode 2003001.
- [x] Audio sort: original, preferred languages, other language/name.
- [x] Subtitle sort: preferred languages, device locale, English, other languages; forced tracks before full tracks within a language.
- [x] Partial language-name inference for common languages and ISO-639-2 tags (full dubbing studio catalogue not yet ported).
- [x] New optional forced-only subtitle mode on preferred audio. Manual track selection and explicit Lampa track requests take precedence.
- [x] Clock, time-until-finish and LIVE settings: off / when paused / always. Previous boolean clock preference migrated.
- [x] English, Russian and Ukrainian labels for the new settings.
- [x] Android release compilation in Actions (source-level checks; not a hardware runtime test).

## Outstanding parity / validation

- [ ] Verify subtitle auto-selection on actual files with multiple tracks, missing language tags, forced flags, signs-only names and Lampa source overrides. No end-to-end playback test yet.
- [ ] Complete recognised dubbing-studio dictionary and initial audio selection for untagged tracks.
- [ ] Dolby Vision / MediaTek fallback: real decoder-capability probes and profile 8 HDR10+ handling.
- [ ] MPEG-4 MP4V in AVI: extractor and decoder smoke test.
- [ ] Release video decoder on TV sleep, resume paused, ensure Realtek devices retain playback.
- [ ] Fix Android <= 12 language persistence, 0.0Mbps stream statistics, reset-message inset, AMOLED overlay and remote focus / edge fade.
- [ ] Run install/upgrade, selected language persistence and device smoke tests, including 4K HDR, AC3/EAC3 and Lampa nested playlist.
- [ ] Independent verification of author-described "mutations"; do not speculate about anti-tamper logic without evidence.

**Status: candidate compilation / draft PR only; not a verified 2.3.0 parity release.**
