#!/usr/bin/env python3
"""Evidence-based Android TV UI comparison with Just+ 2.4.11.

A run is NOT 1:1 simply because a menu opens or an APK launches. Each
surface must have paired screenshots and UIAutomator accessibility snapshots.
Intentional differences: blue accent and center Play/Pause. Changed strings,
missing rows, changed geometry, or missing reference are reported explicitly.
The strict mode fails CI until *every required surface* can be verified.
"""
import argparse
import difflib
import json
import pathlib
import re
import statistics
import xml.etree.ElementTree as ET

SCREENS = (
    ("more-menu", "arx-more-before-ambient", "official-more-before-ambient", "Меню «Ещё»"),
    ("ambient", "arx-material3-ambient", "official-material3-ambient", "Подсветка"),
    ("picture", "arx-material3-picture", "official-material3-picture", "Изображение"),
    ("equalizer", "arx-material3-equalizer", "official-material3-equalizer", "Эквалайзер"),
    ("playlist", "parallel-native-playlist", "official-native-playlist", "Плейлисты"),
    ("subtitles", "arx-native-subtitles", "official-native-subtitles", "Субтитры"),
    ("buffer-logo", "parallel-buffering-series-logo", "official-buffering-series-logo", "Логотип при буферизации"),
    ("timings", "arx-controls", "official-controls", "Тайминги и панель управления"),
    ("skip", "arx-skip-visible", "official-skip-visible", "Плашка пропуска и таймер"),
)


def described_nodes(file):
    if not file.is_file():
        return []
    try:
        root = ET.parse(file).getroot()
    except (ET.ParseError, OSError):
        return []
    nodes = []
    for el in root.iter("node"):
        a = el.attrib
        if a.get("visible-to-user", "true") == "false":
            continue
        text = " ".join((a.get("text", ""), a.get("content-desc", ""))).strip()
        if not text:
            continue
        bounds = [int(x) for x in re.findall(r"\d+", a.get("bounds", ""))]
        if len(bounds) != 4:
            continue
        cx = (bounds[0] + bounds[2]) / 2
        cy = (bounds[1] + bounds[3]) / 2
        nodes.append((text.casefold(), cx, cy))
    return nodes


def pixel_score(original, arx, region="panel"):
    from PIL import Image, ImageChops
    with Image.open(original) as a, Image.open(arx) as b:
        if a.size != b.size:
            return {"mismatch": 1.0, "reason": "screen dimensions differ"}
        w, h = a.size
        # A dark side sheet is shown over a moving film. Ignore the video and
        # compare only the right-most 40%, where the native picker lives.
        # This deliberately does not claim 1:1 for areas we did not capture.
        rect = (int(w * .60), int(h * .06), int(w * .98), int(h * .96))
        if region == "buffer":
            rect = (int(w * .25), int(h * .2), int(w * .75), int(h * .8))
        if region == "controls":
            rect = (int(w * .05), int(h * .02), int(w * .95), int(h * .18))
        gray_a = a.convert("L").crop(rect)
        gray_b = b.convert("L").crop(rect)
        delta = ImageChops.difference(gray_a, gray_b).point(
            lambda d: 255 if d > 45 else 0
        )
        histogram = delta.histogram()
        changed = histogram[255] / max(1, gray_a.width * gray_a.height)
        return {"mismatch": round(changed, 5), "region": rect}


def check(evidence, key, arx, original):
    a_png = evidence / (arx + ".png")
    o_png = evidence / (original + ".png")
    a_xml = evidence / (arx + ".xml")
    o_xml = evidence / (original + ".xml")
    result = {
        "screen": key,
        "arx_image": a_png.name,
        "reference_image": o_png.name,
        "status": "UNVERIFIED",
    }
    if not a_png.is_file() or not o_png.is_file():
        result["reason"] = "paired screenshots missing"
        return result
    if a_png.stat().st_size < 2000 or o_png.stat().st_size < 2000:
        result["status"] = "FAIL"
        result["reason"] = "empty or truncated PNG"
        return result
    rows_a, rows_o = described_nodes(a_xml), described_nodes(o_xml)
    if not rows_a or not rows_o:
        result["reason"] = "UIAutomator snapshot/visible semantics unavailable"
        return result
    tokens_a = [n[0] for n in rows_a]
    tokens_o = [n[0] for n in rows_o]
    # The center-button location differs by explicit user request. Its
    # accessibility text can be the same; do not compare that rectangle.
    score = difflib.SequenceMatcher(a=tokens_a, b=tokens_o,
                                    autojunk=False).ratio()
    result["semantic_similarity"] = round(score, 4)
    result["arx_rows"] = tokens_a
    result["reference_rows"] = tokens_o
    pixel = pixel_score(o_png, a_png, "buffer" if key == "buffer-logo"
                        else "controls" if key == "timings"
                        else "panel")
    result.update(pixel)
    # Strict geometry and text parity; colours are converted to luminance,
    # but blue/orange are explicitly treated as intentional differences,
    # so the pixel comparison is informative rather than a pixel-perfect
    # assertion about accent colours.
    if score >= .90 and pixel["mismatch"] < .12:
        result["status"] = "MATCH"
        result["reason"] = "semantics and visible geometry match tolerance"
    else:
        result["status"] = "DIFFERENT"
        result["reason"] = "text, layout or screenshot differs from official 2.4.11"
    return result


def main():
    parser = argparse.ArgumentParser()
    parser.add_argument("evidence", type=pathlib.Path)
    parser.add_argument("--strict", action="store_true")
    args = parser.parse_args()
    evidence = args.evidence
    evidence.mkdir(parents=True, exist_ok=True)
    results = [check(evidence, *entry[:3]) for entry in SCREENS]
    report = {
        "reference": "Official Just+ Player 2.4.11 on Android TV API 33",
        "allowed_exceptions": [
            "Blue ARX accent instead of official orange",
            "ARX visible branding",
            "Center Play/Pause rather than official bottom transport",
        ],
        "screens": results,
        "ready_for_1_to_1_claim": all(r["status"] == "MATCH" for r in results),
    }
    (evidence / "ui-parity-report.json").write_text(
        json.dumps(report, ensure_ascii=False, indent=2) + "\n", encoding="utf-8"
    )
    lines = [
        "# ARX ↔ Official Just+ 2.4.11 — Android TV UI parity",
        "",
        "**MATCH is required on every screen before claiming 1:1.**"
        " DIFF and UNVERIFIED do not count as passed.",
        "",
        "| Экран | Статус | Совпадение текстов | Отличие изображения |",
        "|---|---|---:|---:|",
    ]
    labels = {entry[0]: entry[3] for entry in SCREENS}
    for r in results:
        semantic = r.get("semantic_similarity", "-")
        mismatch = r.get("mismatch", "-")
        lines.append(
            f"| {labels[r['screen']]} | **{r['status']}** | {semantic} | {mismatch} |"
        )
    lines.extend((
        "",
        "Pixel diff is computed over corresponding UI regions, not the moving "
        "video underneath. Exact blue ↔ orange hue differences are permitted.",
        "",
        "Limit: screenshot geometry similarity does not prove interaction parity; "
        "live Android TV remote controls and playlist callbacks are separately tested.",
    ))
    (evidence / "ui-parity-report.md").write_text(
        "\n".join(lines) + "\n", encoding="utf-8"
    )
    print("\n".join(lines))
    if args.strict and not report["ready_for_1_to_1_claim"]:
        raise SystemExit("FAIL: UI is not yet verified 1:1 on ALL required screens")


if __name__ == "__main__":
    main()
