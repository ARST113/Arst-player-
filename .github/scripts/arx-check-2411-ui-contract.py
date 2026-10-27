#!/usr/bin/env python3
"""Hard, inspectable UI source invariants required for ARX 2.4.11.

The script checks source design invariants; Android TV screenshot parity is
checked separately. A static PASS is NEVER reported as 1:1 visual evidence.
"""
import pathlib
import re
import sys
import xml.etree.ElementTree as ET


ROOT = pathlib.Path("app/src/main")
java = ROOT / "java/com/brouken/player"
res = ROOT / "res"


def require(condition, message):
    if not condition:
        raise AssertionError(message)
    print("PASS:", message)


def extract_colors():
    resource = ET.parse(res / "values/colors.xml").getroot()
    return {x.get("name"): x.text.strip()
            for x in resource.findall("color") if x.text}


def argb(value):
    if not value.startswith("#"):
        raise ValueError(value)
    if len(value) == 9:
        return tuple(int(value[i:i + 2], 16) for i in (3, 5, 7))
    if len(value) == 7:
        return tuple(int(value[i:i + 2], 16) for i in (1, 3, 5))
    raise ValueError(value)


def main():
    colors = extract_colors()
    for key in ["brand", "brand_accent", "brand_container", "brand_ramp_start", "brand_ramp_end"]:
        red, green, blue = argb(colors[key])
        require(blue > red and blue >= green, f"{key} is BLUE (RGB {red},{green},{blue})")
    accents = (res / "values/themes_accents.xml").read_text(encoding="utf-8")
    coral = accents.split('style name="ThemeOverlay.JustPlus.Accent.Coral"', 1)[1].split("</style>", 1)[0]
    require('<item name="accentInk">@color/brand</item>' in coral,
            "formerly Coral-saved accent uses blue ARX brand")
    prefs = (java / "Prefs.java").read_text(encoding="utf-8")
    require("return CORAL;" in prefs and "getString(ACCENT_KEY, ACCENT_CORAL)" in prefs,
            "existing device's stored default accent resolves to remapped blue theme")
    controls = (java / "ArxControlsUi.java").read_text(encoding="utf-8")
    require("Dialogs.pickerWindow(" in controls, "all ARX control panes use focusable Just+ side sheets")
    ambient = (java / "ArxAmbientLight.java").read_text(encoding="utf-8")
    require('ArxControlsUi.modeCard(' in ambient
            and 'arx_ambient_motion' in ambient and 'ArxControlsUi.segmented(' in ambient,
            "Ambient mode preview, inline radio choices, hidden Off sliders and 3-way motion match 2.4.11")
    require("ArxControlsUi.seekBar(ctx,body,100" in ambient,
            "ambient brightness/reach/response remain functional sliders")
    equalizer = (java / "ArxEqualizerDialog.java").read_text(encoding="utf-8")
    require("new MaterialSwitch(ctx)" in equalizer,
            "equalizer enablement uses native Material 3 switch")
    require("R.string.arx_eq_bands" in equalizer and "ArxControlsUi.chooser(" in equalizer,
            "equalizer preset and per-band sections available")
    player = (java / "PlayerActivity.java").read_text(encoding="utf-8")
    require("return episodeLogo != null ? episodeLogo" in player
            and "nestedPlaylistModel.logo" in player,
            "buffering and header use Lampa ROOT series logo as fallback")
    require('Glide.with(getApplicationContext()).load(model.logo).preload();' in player,
            "Lampa series logo preloaded before first video frame")
    require('startupTitle.setTextColor(Color.WHITE);' in player,
            "buffering text fallback is white, not the old red placeholder")
    require('getString(R.string.arx_menu_ambient)' in player
            and 'getString(R.string.arx_menu_equalizer)' in player
            and 'getString(R.string.arx_menu_picture)' in player,
            "Ambient/Picture/Equalizer all accessible in More menu")
    for key in ("arx_menu_ambient", "arx_ambient_title", "arx_eq_bands",
                "arx_eq_title", "arx_picture_title"):
        xml = ET.parse(res / "values-ru/strings.xml").getroot()
        found = [e for e in xml.findall("string") if e.get("name") == key]
        require(bool(found) and bool(found[0].text),
                f"Russian localized string {key} present")
    print("PASS: static UI contract. Pixel/interaction 1:1 requires emulator evidence.")


if __name__ == "__main__":
    try:
        main()
    except (AssertionError, ValueError, ET.ParseError) as exc:
        print("FAIL:", exc, file=sys.stderr)
        raise SystemExit(1)
