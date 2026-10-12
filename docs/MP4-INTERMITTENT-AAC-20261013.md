# ARX TPlayer / Just+ 2.4.1 — intermittent sound dropout on TorrServer MP4

**Status (2026-10-13): one actual AAC decode/dropout event captured; later identical and longer tests CLEAN. Root cause not proven. No ARX source fix justified yet.**

## Input media

The user supplied a direct TorrServer `/stream/…mp4?link=…&index=4&play` URL to the file `Мышь 5 серия.mp4`. Full tokenized URL is in `.github/workflows/diagnose-mp4-audio.yml` within this isolated diagnostics branch; keep the link out of unrelated GitHub releases.

Response: HTTP 200 to HEAD, 206 Partial Content on a 128 KiB Range, `Content-Type: video/mp4`, `Accept-Ranges: bytes`, `X-Stream-Timeout: 30`, file size **1,658,028,804 bytes**. Header atoms: `ftyp` at offset 4, `mdat` at offset 44, no `moov` in the first 128 KiB. This is a conventional MP4 ISO BMFF container with a non-initial index, not an MPEG-TS/DASH disguised as .mp4.

FFprobe: duration 3783.080 s; video H.264 High / 1920×818 / 25 fps / ~3,307 kb/s, audio AAC-LC 2.0 / 48 kHz / ~195 kb/s. `time_base` audio 1/48000, video 1/90000. `Lavf59.27.100` muxer tag.

## Captured real failure (initial GitHub Actions run)

[Run 38315752858](https://github.com/ARST113/Arst-player-/actions/runs/38315752858) / artifact `mp4-audio-diagnostics`:

- An 80-second AAC decode from position 240 seconds logged **99** instances of both `[aac] channel element 0.0 duplicate` and `Error submitting packet to decoder: Invalid data found when processing input`.
- Decoded PCM output was **14,604 KiB**, compared with the expected **15,000 KiB** for 80s × 48,000 samples/s × stereo × 16 bits.
- Exactly 396 KiB were missing = 99 × 1,024 AAC samples × 4 bytes, representing **2.112 seconds** of decoded sound.
- During the same initial run, 0–80s and 600–680s decoded cleanly.
- Initial audio packet PTS scan over the first 160 seconds: 7,500 packets, no >150 ms discontinuities.
- FFmpeg command in `.github/scripts/diagnose-mp4-audio.sh`. This is a confirmed FFmpeg decoding event, not a subjective playback report.

## Subsequent repeat runs: clean

[Second pass 38316021982](https://github.com/ARST113/Arst-player-/actions/runs/38316021982): 10 five-second windows from 285 to 317 seconds, each decoded twice, **zero AAC errors**. Double Range reads (8704 bytes each) at ~295, 297, 299, 301, 303 seconds had identical SHA-256 between repetitions. These hashes do **not** validate the entire region near the first failure.

[Long-context run 38316366508](https://github.com/ARST113/Arst-player-/actions/runs/38316366508): 240–335s twice clean, identical PCM hash; 0–340s, 310–340s, 315–330s, 318–330s and 320–332s all clean and complete PCM. Therefore the first invalid AAC event is **not a consistently corrupted section** under subsequent reads.

[Exact-command and realtime run 38316623194](https://github.com/ARST113/Arst-player-/actions/runs/38316623194): original FFmpeg filter chain and start/duration repeated **three times**, all yielded full 15,000 KiB with **0** AAC errors. Input-rate-throttled 300–338s also clean. A last attempted short FFprobe read at 316–322s returned zero packets, hence no trustworthy exact packet hash was obtained for that subrange.

## Interpretation and missing proof

Evidence supports **intermittent delivery/decoder failure** rather than permanent damaged AAC frames, but it does not establish which side is at fault. TorrServer and nginx may have supplied transiently invalid bytes, or the playback path/decoder may have reacted badly to a temporary read pattern. A warm cache or fully fetched torrent piece may explain successful later runs. The same Range bytes were confirmed identical only outside the suspected corrupted moment.

The media is a standard H.264/AAC MP4; a remote-index layout may affect strict stream players such as Vimu, but the refusal cause is not established. DDD/LibVLC may tolerate malformed data differently; its clean playback does not invalidate the logged FFmpeg failure.

**No code change to ARX is proposed without an Android `logcat` and/or two captures of the exact bytes at the moment the error recurs.** Native `libffmpegJNI.so` changes alone cannot reconstruct absent or garbled AAC payloads. Rewriting the container with `-c copy -movflags +faststart` can improve seeking but will not repair undecodable audio frames. Re-encoding audio may conceal a gap, not recreate lost source samples.

## Future reproducible conditions to log

1. Position (hh:mm:ss) of each actual audio loss on a physical ATV; note video continues/pauses and whether seeking backwards restores sound.
2. Track TorrServer read/retry/cache and proxy `Range` status at that point.
3. Android `logcat`: MediaCodecAudioRenderer, DecoderAudioRenderer, AudioSink, unexpected timestamps, audio underrun, renderer error and network retries.
4. Capture bounded byte positions on both successful and failing HTTP reads and compare SHA-256. Avoid committing copyrighted media to the public repo.


## Exact magnet / torrent metadata (read-only, 2026-10-13)

The user's \`web+torrs://db96dda9c3a1e6b1bbc38402378ea822d009fbf4\` and magnet infohash **match** the torrent used by the video stream. In MatriX.146.0.0 \`POST /torrents {"action":"get","hash":"…"}\` reported the matching database entry, with total \`torrent_size = 40648345630\` bytes, and \`POST /cache\` furnished the active torrent's file_stats.

- 20 episodes, torrent size about 40.65 GB decimal, piece size **4,194,304 bytes (4 MiB)**, total pieces **9692**.
- Target \`Мышь 5 серия.mp4\`: actual **id 4** in the torrent file list, **1,658,028,804 bytes**, exactly the HTTP response size. The order of files is NOT episode order: episode 1 is id 19; episode 5 id 4.
- Torrent cache configured **67,108,864 bytes (64 MiB)**; the first idle-state \`/cache\` snapshot showed \`Filled=0\`, \`Readers=[]\`. Database action \`/torrents/get\` returns \`stat=5\` (stored torrent), while active \`/cache\` showed \`stat=3\` (working). These values are compatible, not a contradiction.
- \`/echo\` returned \`MatriX.146.0.0\`. A fresh 32-KiB HTTP Range read of the fifth file succeeded with 206 and same MP4 header atoms.
- Source: GitHub Actions run [38317319402](https://github.com/ARST113/Arst-player-/actions/runs/38317319402). Its raw response artifact was intentionally purged because the API also returned unrelated provider-account metadata; **do not upload unfiltered API JSON again**.

## Sampling under active realtime reading

[Run 38317496800](https://github.com/ARST113/Arst-player-/actions/runs/38317496800), sanitized artifact \`torrent-live-cache-observation\`, sampled \`/cache\` roughly every 1.3 seconds for 33 iterations while FFmpeg read/decompressed the AAC audio at realtime input speed from the 300-second mark.

- 7 active peers from 4 s after starting, 0 pending; total peers reported between 7 and 25.
- \`download_speed\` typically 2.6–8.9 MB/s, well above fifth episode's combined H.264/AAC bitrate of ~0.44 MB/s.
- \`Filled\` grew from 0 to **82.44 MiB**, even though reported capacity is **64 MiB**. This is an observed temporary difference between a capacity setting and a dynamically reported cache fill, not proof of a software defect.
- One realtime audio reader during most of the test, no FFmpeg warnings, and no AAC decode errors.
- By the end of the test there were no readers; 82.44 MiB remained reported in the cache. This clarifies the earlier snapshot at 0 only reflected one inactive moment and does not mean the server never caches pieces.
- The server *currently* fetched data much faster than needed. This neither proves the original fault was on the client nor excludes an intermittent peer/HTTP delivery problem at the original time.

**Current conclusion:** normal H.264/AAC MP4 with \`moov\` likely near end, unusual torrent file order, and a **real but unrepeated** FFmpeg failure that dropped 99 AAC frames once. There is no demonstrated recurring media corruption or general insufficient torrent throughput. No ARX engine change until the failure can be correlated with HTTP sample bytes, Android decoder/AudioSink logs or server piece errors. Test both original Just+ and ARX 2.4.1 with the same 5th episode, especially around 4:56–5:20 and after a longer pause/seek.
