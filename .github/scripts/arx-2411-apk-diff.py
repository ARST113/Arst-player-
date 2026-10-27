#!/usr/bin/env python3
"""Binary facts for official APKs; do not infer feature parity from file hashes."""
from collections import defaultdict
from hashlib import sha256
from pathlib import Path
from zipfile import ZipFile

versions = ("2.4.8", "2.4.9", "2.4.10", "2.4.11")
records = {}
for v in versions:
    with ZipFile(Path("vendor") / (v + ".apk")) as apk:
        records[v] = {info.filename: (info.file_size, sha256(apk.read(info.filename)).hexdigest())
                      for info in apk.infolist()}

for a, b in zip(versions, versions[1:]):
    before, after = records[a], records[b]
    keys = sorted(before.keys() | after.keys())
    changed = [name for name in keys if before.get(name) != after.get(name)]
    classes = [p for p in changed if p.startswith("classes") and p.endswith(".dex")]
    native = [p for p in changed if p.startswith("lib/") and p.endswith(".so")]
    print("== VERSION", a, "->", b, "==")
    print("archives:", len(before), len(after), "changed:", len(changed))
    print("DEX:", len(classes), classes)
    print("native libraries:", len(native))
    for name in native:
        print(" NATIVE", name, "old=", before.get(name), "new=", after.get(name))
    groups = defaultdict(list)
    for name in changed:
        groups[name.split("/", 1)[0]].append(name)
    print("changed by top-level directory:", {k: len(v) for k, v in groups.items()})
    for name in changed:
        if name.startswith("lib/") or name.startswith("classes") or name.startswith("res/") or name.endswith(".xml"):
            action = "ADDED" if name not in before else "REMOVED" if name not in after else "UPDATED"
            print(" ", action, name, before.get(name), "->", after.get(name))
