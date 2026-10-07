#!/usr/bin/env python3
"""Machine-readable comparison of the captures taken on the static fixture.

With a fixture whose picture never changes, anything left between the published APK and ARX is
chrome. This prints the numbers a review asks for -- plate box, rail thickness and tone, scrubber
size, header lines, scrim profile -- and a difference map with the moment-dependent readouts masked
out, so an iteration is judged by measurement instead of by eye against a different video frame.

Each pair of captures is reported on its own: `*-static` is the plain launch, `*-playlist` is the
same clip with the legacy video_list contract carrying two items, which is what puts the transport's
previous/next buttons on screen.
"""
import sys
from pathlib import Path

import numpy as np
from PIL import Image

out = Path(sys.argv[1] if len(sys.argv) > 1 else "ui-reference")
lines = []


def say(line=""):
    lines.append(line)


PAIRS = ("static", "playlist")

# The status bar, the clock, the position readout and the scrubber carry the moment the capture was
# taken, not the chrome; the rail band is masked because how much of it is coral follows the playback
# position -- its thickness and its tone are reported as numbers instead.
DYNAMIC = (
    (0, 72, 0, 2400),
    (90, 215, 1880, 2270),
    (805, 875, 190, 430),
    (805, 875, 2040, 2270),
    (795, 885, 280, 520),
    (822, 852, 350, 2100),
)


def load(name):
    return np.asarray(Image.open(out / f"{name}.png").convert("RGB")).astype(int)


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


def describe(name, im):
    lum = im.mean(axis=2)
    r, g, b = im[..., 0], im[..., 1], im[..., 2]
    say(f"--- {name} {im.shape[1]}x{im.shape[0]}")
    for label, x0, x1 in (("header-left", 150, 1500), ("header-right", 1500, 2360)):
        for a, bb, c, d in bands(lum[60:300, x0:x1] > 110, x0, 60):
            say(f"  {label:13s} y[{a},{bb}] x[{c},{d}]")

    col = lum[810:870, 900]
    rail = [i + 810 for i, v in enumerate(col) if v < 215]
    say(f"  rail at x=900: {rail[0] if rail else '-'}..{rail[-1] if rail else '-'} ({len(rail)} px)")
    for x in (900, 1500, 2000):
        say(f"  rail colour at x={x}: {tuple(int(v) for v in im[836, x])}")

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


def compare(a_name, b_name):
    if a_name is None or b_name is None:
        return
    a, b = load(a_name), load(b_name)
    if a.shape != b.shape:
        say(f"--- difference {a_name} vs {b_name}: sizes differ")
        return
    d = np.abs(a - b).max(axis=2)
    diff = d > 24
    ys, xs = np.nonzero(diff)
    say(f"--- difference {a_name} vs {b_name}")
    say(f"  over threshold: {int(diff.sum())} px ({100.0 * diff.sum() / diff.size:.3f}%)")
    if len(xs):
        say(f"  bbox x[{xs.min()},{xs.max()}] y[{ys.min()},{ys.max()}]")
    for label, (y0, y1, x0, x1) in {
        "header": (60, 320, 0, 2400),
        "hero": (400, 700, 1000, 1400),
        "plate": (790, 1030, 0, 2400),
    }.items():
        say(f"  {label}: {int(diff[y0:y1, x0:x1].sum())} px")

    mask = np.ones(diff.shape, bool)
    for y0, y1, x0, x1 in DYNAMIC:
        mask[y0:y1, x0:x1] = False
    static_only = diff & mask
    say(f"  outside the moving readouts: {int(static_only.sum())} px")
    blocks = []
    size = 60
    for y in range(0, diff.shape[0], size):
        for x in range(0, diff.shape[1], size):
            n = int(static_only[y:y + size, x:x + size].sum())
            if n > 400:
                blocks.append((n, y, x))
    for n, y, x in sorted(blocks, reverse=True)[:12]:
        say(f"    block y{y}-{y+size} x{x}-{x+size}: {n} px")
    Image.fromarray(np.clip(d * 3, 0, 255).astype(np.uint8)).save(out / f"chrome-diff-{a_name.split('-')[-1]}.png")


reported = False
for pair in PAIRS:
    a_name = f"official-{pair}"
    b_name = f"arx-{pair}"
    if not ((out / f"{a_name}.png").exists() and (out / f"{b_name}.png").exists()):
        continue
    reported = True
    imgs = {a_name: load(a_name), b_name: load(b_name)}
    for n in (a_name, b_name):
        describe(n, imgs[n])
    compare(a_name, b_name)

if not reported:
    say("chrome report: no static captures on this run")

text = "\n".join(lines)
(out / "chrome-report.txt").write_text(text + "\n")
print(text)
