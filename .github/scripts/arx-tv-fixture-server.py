#!/usr/bin/env python3
"""Local-only Android TV video and Lampa-logo fixture server.

The deliberately delayed video endpoint makes the first-buffering screen
observable in a screenshot, while the PNG is an artificial recognizable
series-logo test image supplied through playlist.logo.
"""
import argparse
import http.server
import os
import pathlib
import struct
import time
import zlib


def make_logo(path: pathlib.Path) -> None:
    """Create a transparent PNG with a distinctive bright-green logo pattern."""
    w, h = 320, 96
    rows = []
    for y in range(h):
        line = bytearray([0])
        for x in range(w):
            if 20 <= x <= 299 and 14 <= y <= 81:
                # Distinctive test-only colour; screenshot tests look for it
                # in the CENTER, not in the player seekbar/focus accents.
                is_cutout = ((35 < x < 60 or 100 < x < 125) and y < 64)
                line.extend((37, 237, 80, 0 if is_cutout else 255))
            else:
                line.extend((0, 0, 0, 0))
        rows.append(bytes(line))

    def chunk(kind: bytes, data: bytes) -> bytes:
        return struct.pack(">I", len(data)) + kind + data + struct.pack(
            ">I", zlib.crc32(kind + data) & 0xFFFFFFFF
        )
    png = (
        b"\x89PNG\r\n\x1a\n"
        + chunk(b"IHDR", struct.pack(">IIBBBBB", w, h, 8, 6, 0, 0, 0))
        + chunk(b"IDAT", zlib.compress(b"".join(rows)))
        + chunk(b"IEND", b"")
    )
    path.write_bytes(png)


class Handler(http.server.SimpleHTTPRequestHandler):
    def do_GET(self):
        if self.path.split("?", 1)[0] == "/tv-slow.mp4":
            time.sleep(9)
            self.path = "/tv-h264.mp4"
        return super().do_GET()


def main():
    parser = argparse.ArgumentParser()
    parser.add_argument("--port", type=int, default=8765)
    parser.add_argument("--directory", default="arx-2411-tv-fixtures")
    args = parser.parse_args()
    directory = pathlib.Path(args.directory).resolve()
    if not (directory / "tv-h264.mp4").is_file():
        raise SystemExit("missing test video")
    make_logo(directory / "tv-series-logo.png")
    (directory / "tv-subtitles.vtt").write_text(
        "WEBVTT\n\n00:00:02.000 --> 00:00:07.000\n"
        "ARX / Just+ shared subtitle fixture\n", encoding="utf-8"
    )
    os.chdir(directory)
    server = http.server.ThreadingHTTPServer(("127.0.0.1", args.port), Handler)
    server.daemon_threads = True
    server.serve_forever()


if __name__ == "__main__":
    main()
