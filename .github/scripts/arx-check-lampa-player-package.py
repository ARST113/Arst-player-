#!/usr/bin/env python3
"""Verify the parallel ARX Android identity gets Lampa's native Just+ playlist Bundle.

Usage: python3 .github/scripts/arx-check-lampa-player-package.py <tagged-Lampa-MainActivity.kt>...
Use *tagged* source rather than an assumption about arbitrary older Lampa clients.
"""
import pathlib
import re
import sys

project = pathlib.Path("app/build.gradle").read_text(encoding="utf-8")
flavor = re.search(r"\bparallel\s*\{(?P<body>.*?)\n\s*\}", project, flags=re.S)
if flavor is None:
    raise SystemExit("FAIL: Gradle parallel flavor missing")
application = re.search(r'\bapplicationId\s+"([^"]+)"', flavor.group("body"))
if application is None:
    raise SystemExit("FAIL: Gradle parallel applicationId missing")
package_id = application.group(1)
if 'applicationId "com.justplus.player"' not in project:
    raise SystemExit("FAIL: legacy Just+ compatible universal ID was changed")
if package_id in {"com.justplus.player", "com.lampaua.player", "top.rootu.dddplayer"}:
    raise SystemExit("FAIL: parallel APK conflicts with original/UA/DDD install identity")
if not sys.argv[1:]:
    raise SystemExit("FAIL: supply pinned Lampa MainActivity.kt reference files")

for source_path in sys.argv[1:]:
    source = pathlib.Path(source_path).read_text(encoding="utf-8")
    matched = re.search(r"JUSTPLUS_PLAYER_PREFIXES\s*=\s*listOf\(([^)]*)\)", source)
    if not matched:
        raise SystemExit(f"FAIL: missing Lampa Just+ package prefix list: {source_path}")
    prefixes = re.findall(r'"([^"]+)"', matched.group(1))
    if not prefixes or not any(package_id.startswith(p) for p in prefixes):
        raise SystemExit(f"FAIL: {package_id} does not match Lampa playlist prefixes {prefixes} ({source_path})")
    if "JUSTPLUS_PLAYER_PREFIXES.any { name.startsWith(it) }" not in source:
        raise SystemExit(f"FAIL: Lampa prefix comparison changed: {source_path}")
    if 'configureJustPlusPlaylistIntent(intent, playerPackage,' not in source:
        raise SystemExit(f"FAIL: Lampa no longer routes matching packages to native playlist contract: {source_path}")
    if 'putExtra("playlist", Bundle().apply' not in source:
        raise SystemExit(f"FAIL: Lampa playlist Bundle handling changed: {source_path}")
    print(f"PASS: {pathlib.Path(source_path).name}: {package_id} gets native Just+ playlist Bundle via {prefixes}")

print(f"PASS: parallel APK '{package_id}' preserves both side-by-side installation and native Lampa playlist routing")
