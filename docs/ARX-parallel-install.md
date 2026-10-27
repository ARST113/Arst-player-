# ARX TPlayer: original-compatible and side-by-side Android packages

## Why there are two APKs

Android determines whether an APK upgrades an installed app **solely from the
manifest package/applicationId** (and compatible signing), not from the visible
app name. Two apps cannot coexist under the same package ID.

Both flavors retain the same Java namespace `com.brouken.player`,
`@string/app_name = ARX TPlayer`, icon, API extras, nested playlist parser,
`ACTION_VIEW` intent filters, external subtitle and HTTP/HTTPS/RTSP support,
playlist result semantics and center TV Play/Pause position.

| Gradle task | Android package ID | Purpose | In-app self-update |
|---|---|---|---|
| `:app:assembleLatestUniversalRelease` | `com.justplus.player` | Legacy-compatible install/update; mutually exclusive with official Just+ Player | Existing updater setting |
| `:app:assembleLatestParallelRelease` | `com.lampaua.player.arx` | Standalone ARX TPlayer; install **alongside** official Just+ Player | Disabled (until ARX package-aware updater is implemented) |

Output: `app/build/outputs/apk/latestParallel/release/ARX.TPlayer.v<version>-parallel.apk`.
Both use the pinned `arx` release certificate.

**Important**: Installing the parallel APK does not replace `com.justplus.player`
and does not migrate its private settings, cached progress or stored credentials.
Android isolates these per package. Do not try to force an in-place update of the
official APK to the parallel identity. Users can have both and switch between them.

## Lampa / playlist compatibility

Native Lampa Android **v1.13.3 and v1.13.4** explicitly recognizes the
Just+ nested playlist contract for package IDs beginning with
`com.justplus.player` **or** `com.lampaua.player` (the code uses
`JUSTPLUS_PLAYER_PREFIXES.any { name.startsWith(it) }`).
Thus `com.lampaua.player.arx` receives the full `playlist` Bundle and
`result_callback`, unlike the former preview `com.arst.tplayer`, which
Lampa treated as a generic external video player.
We do **not** use the DDD player package or protocol.

Verified source: https://github.com/lampa-app/LAMPA/blob/v1.13.4/app/src/main/java/top/rootu/lampa/MainActivity.kt

Android players are addressed in one of two ways:

- **Implicit intent**: Lampa sends `ACTION_VIEW`, HTTP/video URI and
  `video/*` MIME. Both players advertise the same filters; Android's chooser
  can offer both. ARX accepts the original playlist extras and its result
  callbacks without format changes.
- **Package-scoped or explicit intent**: if the source plugin hardcodes
  `com.justplus.player`, Android *must* select that package. Giving an APK the
  same label does not change this. Select `com.lampaua.player.arx` in the
  Lampa Android player chooser or reset the saved player preference; Lampa
  then sends the full nested playlist Bundle to the selected APK,
  URI and extras. The activity class remains `com.brouken.player.PlayerActivity`.

This build preserves the **literal API result action**
`com.justplus.player.result` for nested playlists regardless of the Android
package identifier, so a caller that checks the official action does not break.
The generic `com.mxtech.intent.result.VIEW` result is unchanged. PendingIntent
callbacks are supplied by the launcher and similarly remain unchanged.

The `FileProvider` authority intentionally uses `${applicationId}.provider`,
so providers do not clash when both APKs are installed.

Android package selection test (when both players are installed):

```sh
adb shell pm list packages | grep -E 'com.(justplus.player|arst.tplayer)'
adb shell am start -W -a android.intent.action.VIEW \
  -p com.lampaua.player.arx \
  -d 'http://127.0.0.1:8765/tv-h264.mp4' -t video/mp4
```

## Continuous verification

`.github/workflows/arx-2.4.11-build.yml` produces both signed, separately
named GitHub Actions artifacts and verifies signatures, package identifiers
and **identical visible app labels**.

`.github/workflows/arx-2.4.11-tv-emulator.yml` installs the verified official
APK and parallel ARX on the *same* Android TV API 33 emulator; it asserts both
packages are installed, launches the parallel variant with a package-scoped
implicit video intent, waits for Media3 READY/first decoded frame, captures
screenshots and logcat. Incomplete/failing runs must never be reported as PASS.

## Remaining caveat

Launcher-specific Lampa plugins that do not provide player selection and
always hardcode the original package must be adapted in the Lampa integration,
not by spoofing the same Android package (which would make co-installation
impossible). No assumptions about every third-party Lampa plugin's behavior
are made without testing it.

## Migration from former parallel beta

The initial parallel preview used `com.arst.tplayer` and was therefore not
recognized by Lampa's Just+ playlist allowlist, even though its plain HTTP
video playback worked. The new package `com.lampaua.player.arx` is a
**different Android application** and will install alongside that preview too.
Uninstall the old `com.arst.tplayer` preview if it is no longer needed;
do not remove the official `com.justplus.player`. Select the new ARX player
in Lampa's chooser (or use Settings → Player → reset default player).

Older Lampa Android clients released before the `com.lampaua.player` prefix
was added are not guaranteed to recognize this new identity. The pinned
native client versions **1.13.3 and 1.13.4** do.
