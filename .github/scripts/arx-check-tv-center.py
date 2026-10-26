#!/usr/bin/env python3
"""Fail the Android TV preview if Play/Pause moves onto the time bar.

The vendor 2.4.9+ TV interface changed the location of playback controls.
ARX deliberately retains center playback controls across updates.
"""
from pathlib import Path
import xml.etree.ElementTree as ET

ANDROID = "{http://schemas.android.com/apk/res/android}"
path = Path("app/src/main/res/layout/exo_player_control_view.xml")
root = ET.parse(path).getroot()
parents = {child: parent for parent in root.iter() for child in parent}
def find(element_id):
    return next((node for node in root.iter()
                 if node.get(ANDROID + "id") == "@+id/" + element_id), None)
def is_child_of(ancestor, node):
    while node in parents:
        node = parents[node]
        if node is ancestor:
            return True
    return False

center = find("exo_center_controls")
play = find("exo_play_pause")
timeline = find("plate_time_row")
bottom = find("exo_bottom_bar")
assert all(node is not None for node in (center, play, timeline, bottom)), "Player controls missing"
assert center.get(ANDROID + "layout_gravity") == "center", "Center control overlay moved"
assert is_child_of(center, play), "Play/Pause button no longer inside center overlay"
assert not is_child_of(timeline, play), "Play/Pause button moved onto timeline"
assert not is_child_of(bottom, play), "Play/Pause button moved into bottom TV bar"
print("PASS: Android TV Play/Pause button centered, separate from timeline")
