#!/usr/bin/env python3
"""Evidence-only index of recovered vendor Java; JADX source is not authoritative."""
from collections import Counter
from pathlib import Path
from hashlib import sha256
import re

versions = ("2.4.8", "2.4.11")
roots = {v: Path("out") / ("jadx-" + v) / "sources" for v in versions}
files = {v: {p.relative_to(roots[v]).as_posix(): p for p in roots[v].rglob("*.java")}
         for v in versions}
for v in versions:
    print("version", v, "decompiled Java files", len(files[v]))
a, b = (files[x] for x in versions)
changed = {p for p in a.keys() | b.keys()
           if p not in a or p not in b or sha256(a[p].read_bytes()).digest() != sha256(b[p].read_bytes()).digest()}
print("Java files changed after decompilation:", len(changed))
print("NOTE: Obfuscation/R8 mapping shifts change files without necessarily changing their behavior.")
owned = sorted(k for k in changed if k.startswith("com/brouken/player/"))
print("Changed app classes:", len(owned))
for path in owned[:260]:
    print(" ", "ADDED" if path not in a else "REMOVED" if path not in b else "DIFFERS", path)

patterns = {
    "tv-centered-play-button": r"exo_play_pause|exo_center_controls|exo_basic_controls|plate_time_row",
    "tv-seek-readout": r"onScrub(Start|Move|Stop)|scrubbing|seekProgress|keySeekStart|seekChange",
    "pause-failed-link-resume": r"setResult\(|getCurrentPosition\(|onPlayerError|PlaybackException|RESULT_CANCELED",
    "blu-ray-dolby-vision": r"Dv7Converter|DOLBY_VISION|DolbyVision|dvhe|dvh1|profile.?8|profile.?7",
    "transport-surround": r"HDMV|TrueHD|LPCM|DTS_HD|E_AC3|IEC61937|MpegAudio|Ac3Extractor",
    "http-429-and-http1": r"HTTP_1_1|Protocol\.HTTP|429|TOO_MANY_REQUESTS|Retry-After",
    "tv-focus-and-picker": r"requestFocus\(|getSelected|listDialog|preferredLanguages",
    "update-installer": r"PACKAGE_INSTALLER|ACTION_VIEW|pendingInstall|updatePending",
}
for label, pattern in patterns.items():
    compiled = re.compile(pattern, re.IGNORECASE)
    print("\n== Evidence area:", label, "==")
    for v in versions:
        print("VERSION", v)
        limit = 0
        for path in sorted(files[v]):
            if not path.startswith("com/brouken/player/"):
                continue
            if not path.endswith(".java"):
                continue
            for i, line in enumerate(files[v][path].read_text(errors="replace").splitlines(), 1):
                if compiled.search(line):
                    print(f"{path}:{i}: {line.strip()[:220]}")
                    limit += 1
                    if limit >= 120: break
            if limit >= 120: break
        print("Matches shown:", limit, "(cap 120)")
