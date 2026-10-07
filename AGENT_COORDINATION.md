# ARX TPlayer 2.1.3 — Agent Coordination Bus

This branch exists only to provide a persistent communication surface for multiple ChatGPT project chats.

## Canonical state
- Repository: `ARST113/just-plus-player2`
- Working branch: `arx-2.1.3-port-wip`
- Working HEAD at bus creation: `7a0ac17b0eddae59fa4a18787ca046d778749a1c`
- Current release: `arx-v2.1.3-arx4`
- APK: `ARX.TPlayer.v2.1.3-arx4-release.apk`
- SHA-256: `2bc5c6df3405166c104066a4bb0df63f5b409954b10f7ede9ce0d45b94465f54`

## Governance
- Main coordinator chat owns integration decisions.
- Worker chats are read-only with respect to `arx-2.1.3-port-wip`.
- Worker chats must not push to the working branch.
- Worker chats report findings as comments on the coordination PR.
- Every worker message must begin with one of:
  - `[AGENT-1/UI]`
  - `[AGENT-2/PLAYLIST]`
  - `[AGENT-3/HEVC]`
- Cross-agent conflicts are escalated with `[BLOCKER]`.
- Accepted integration decisions are posted by the main coordinator with `[MAIN/DECISION]`.
- ARX HEVC/FFmpeg fallback must not be modified without explicit main-coordinator approval.
- This PR is a message bus and is not intended to be merged.

## Agent assignments
### Agent 1 — Official 2.1.3 UI/JADX parity
Audit all remaining UI and behavior differences versus official Just+ Player 2.1.3.

### Agent 2 — Playlist API / Lampa compatibility
Audit Intent/Bundle/playlist contracts, legacy Lampa compatibility, resume/history/metadata and official 2.1.3 behavior.

### Agent 3 — HEVC / Pixel 10 / Media3
Audit the HEVC 10-bit failure path on Pixel 10 Android 17 and compare fallback behavior with known working players.

## Reporting format
Each finding should include:
1. Priority: P0 / P1 / P2
2. Evidence
3. Current ARX behavior
4. Official/expected behavior
5. Exact file/class/method/resource
6. Minimal proposed fix
7. Confidence
