#!/usr/bin/env python3
"""Machine-readable comparison of the two captures taken on the static fixture.

With a fixture whose picture never changes, anything left between the published APK and ARX is
chrome. This prints the numbers a review asks for -- plate box, rail thickness, scrubber size,
header lines, scrim profile -- and a difference map, so an iteration is judged by measurement
instead of by eye against a different video frame.
"""
import sys
from pathlib import Path

import numpy as np
from PIL import Image

out = Path(sys.argv[1] if len(sys.argv) > 1 else "ui-reference")
lines = []


def say(line=""):
    lines.append(line)


NAMES = ("official-static", "arx-static")
if not all((out / f"{n}.png").exists() for n in NAMES):
    say("chrome report: static captures missing on this run")
    (out / "chrome-report.txt").write_text("\n".join(lines) + "\n")
    print("\n".join(lines))
    raise SystemExit(0)


def load(name):
    return np.asarray(Image.open(out / f"{name}.png").convert("RGB")).astype(int)


imgs = {n: load(n) for n in NAMES}
for n in NAMES:
    say(f"{n} size {imgs[n].shape[1]}x{imgs[n].shape[0]}")


def bands(mask, x0, y0):
    rows = mask.sum(axis=1)
    found, cur = [], None
    for y, v in enumerate(rows):
        if v > 4:
            cur = [y, y] if cur is None else [cur[0], y]
        else:
            if cur is not None:
                found.append(tuple(cur))
                cur = None
    if cur:
        found.append(tuple(cur))
    res = []
    for a, b in found:
        cols = np.nonzero(mask[a:b + 1].any(axis=0))[0]
        res.append((a + y0, b + y0, cols.min() + x0, cols.max() + x0))
    return res


for n in NAMES:
    im = imgs[n]
    lum = im.mean(axis=2)
    r, g, b = im[..., 0], im[..., 1], im[..., 2]
    say(f"--- {n}")
    for label, x0, x1 in (("header-left", 150, 1500), ("header-right", 1500, 2360)):
        for a, bb, c, d in bands(lum[60:300, x0:x1] > 110, x0, 60):
            say(f"  {label:13s} y[{a},{bb}] x[{c},{d}]")

    col = lum[810:870, 900]
    rail = [i + 810 for i, v in enumerate(col) if v < 215]
    say(f"  rail at x=900: {rail[0] if rail else '-'}..{rail[-1] if rail else '-'} ({len(rail)} px)")
    # The rail's tone is what a palette regression moves; the diff below masks its band, so this is
    # where the buffered/unplayed colours are read.
    for x in (900, 1500, 2000):
        say(f"  rail colour at x={x}: {tuple(im[836, x])}")

    red = (r > 120) & (r - g > 40) & (r - b > 40)
    ys, xs = np.nonzero(red[790:880, 300:450])
    say(f"  scrubber: {'%dx%d' % (xs.max()-xs.min()+1, ys.max()-ys.min()+1) if len(xs) else 'none'}")
    ys, xs = np.nonzero(red[400:700, 1000:1400])
    say(f"  hero glyph: {'%dx%d' % (xs.max()-xs.min()+1, ys.max()-ys.min()+1) if len(xs) else 'none'}")

    plate = (r > 200) & (np.maximum(np.maximum(r, g), b) - np.minimum(np.minimum(r, g), b) <= 12)
    plate[: int(im.shape[0] * 0.6)] = False
    ys, xs = np.nonzero(plate)
    if len(xs):
        say(f"  light plate bbox x[{xs.min()},{xs.max()}] y[{ys.min()},{ys.max()}]")

    prof = lum[:, 300:2100].mean(axis=1)
    say("  scrim profile: " + " ".join(f"{y}:{prof[y]:.1f}" for y in range(100, 560, 40)))

a, b = imgs[NAMES[0]], imgs[NAMES[1]]
if a.shape == b.shape:
    d = np.abs(a - b).max(axis=2)
    diff = d > 24
    ys, xs = np.nonzero(diff)
    say("--- difference")
    say(f"  over threshold: {int(diff.sum())} px ({100.0 * diff.sum() / diff.size:.3f}%)")
    if len(xs):
        say(f"  bbox x[{xs.min()},{xs.max()}] y[{ys.min()},{ys.max()}]")
    for label, (y0, y1, x0, x1) in {
        "header": (60, 320, 0, 2400),
        "hero": (400, 700, 1000, 1400),
        "plate": (790, 1030, 0, 2400),
    }.items():
        say(f"  {label}: {int(diff[y0:y1, x0:x1].sum())} px")

    # The status bar, the clock, the position readout and the scrubber carry the moment the capture was
    # taken, not the chrome; the rail band is masked because how much of it is coral depends on the
    # playback position — its thickness and tone are reported as numbers above instead. What is left is
    # what a layout port actually owns.
    mask = np.ones(diff.shape, bool)
    for y0, y1, x0, x1 in ((0, 72, 0, 2400), (90, 215, 1880, 2270), (805, 875, 190, 430),
                           (805, 875, 2040, 2270), (795, 885, 280, 520), (822, 852, 350, 2100)):
        mask[y0:y1, x0:x1] = False
    static_only = diff & mask
    say(f"  outside clock/position/scrubber: {int(static_only.sum())} px")
    blocks = []
    size = 60
    for y in range(0, diff.shape[0], size):
        for x in range(0, diff.shape[1], size):
            n = int(static_only[y:y + size, x:x + size].sum())
            if n > 400:
                blocks.append((n, y, x))
    for n, y, x in sorted(blocks, reverse=True)[:12]:
        say(f"    block y{y}-{y+size} x{x}-{x+size}: {n} px")

    Image.fromarray(np.clip(d * 3, 0, 255).astype(np.uint8)).save(out / "chrome-diff.png")

text = "\n".join(lines)
(out / "chrome-report.txt").write_text(text + "\n")
print(text)
