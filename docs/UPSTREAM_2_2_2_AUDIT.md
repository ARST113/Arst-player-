# ARX TPlayer: техническая реконструкция официального Just+ 2.2.2

**Основа:** `arx-2.1.3-port-wip` (ветка сохранена без изменений).
**Цель:** функциональная совместимость с официальным релизом `just-plus-player/just-plus-player v2.2.2` при сохранении доказанно работающих ARX HEVC-восстановлений и совместимости Lampa.
**Статус:** начат аудит. Эта ветка пока **не является** восстановленной 2.2.2 и **не должна** маркироваться как финальный релиз 2.2.2.

## Проверенные факты об upstream APK

- Официальный APK `JustPlus.Player.v2.2.2.apk` (релиз 2026-10-07, 31 574 915 байт) имеет SHA-256 `1e69816c14cedc30929e9e59657a6a8ac766fcd22b584774fc85aa9ec79bd916`.
- Внутри APK есть `lib/arm64-v8a/libffvideoJNI.so` (1 569 544 байт), `lib/armeabi-v7a/libffvideoJNI.so` (1 328 444 байта), а также отдельная аудиобиблиотека `libffmpegJNI.so`.
- В `classes.dex` присутствует класс `Lcom/brouken/player/FfmpegVideoDecoder;`. Экспортированные JNI-функции `libffvideoJNI.so`: `nInit`, `nRelease`, `nFlush`, `nDecode`, `nFrameInfo`, `nCopyFrame`, `nRender`. Это собственный JNI-контракт, **не** бинарно-совместимая замена NextLib.
- В DEX есть `Landroidx/media3/decoder/ffmpeg/ExperimentalFfmpegVideoRenderer;`, строки `FfmpegVideoRenderer`, `Loaded FfmpegVideoRenderer.`, `createFfmpegVideoDecoder`, `ffvideoJNI` и `ask_resume`.
- Из официальных release notes: **2.2.1** добавил программное HEVC 4:4:4 и 4:2:2, устранил артефакты между keyframes и исправил изменение каналов в аудиотреках; **2.2.2** исправил зелёный экран для XviD/DivX и добавил режим показа видео в screen capture (в этом режиме отключаются HDR и tunneling).
- В 2.2.1 внешний контракт плейлиста изменён: `resume_mode` заменён булевым `ask_resume`. Если флаг отсутствует, возобновление позиции автоматическое. В 2.2.2 прогресс в Lampa отправляется при выходе, а не с двухминутным интервалом.
- Исходники upstream публично не обновляются после 2.0.11; 2.1.3, 2.2.1 и 2.2.2 доступны как сборки и release notes. Для достижения parity нужен аудит декомпилированного DEX и ресурсов APK.

## Сопоставление с текущей ARX 2.1.3

| Подсистема | ARX `arx-2.1.3-port-wip` | Официальная 2.2.2 | Действие |
|---|---|---|---|
| Программное видео | `io.github.anilbeesetti:nextlib-media3ext:1.11.1-0.16.0`, `FfmpegVideoRenderer` добавлен в `PlayerActivity.buildVideoRenderers` | Собственный `FfmpegVideoDecoder` + `libffvideoJNI.so`, интеграция в рендеринг Media3 | Восстановить Java-контракт и вывод кадров, затем сравнить качество и скорость |
| HEVC recovery | `forceHevcSoftwareStage`: MediaCodec → c2.android → OMX.google → NextLib | Собственное software decoding 4:2:2/4:4:4, обычный HEVC через MediaCodec | Сохранить ARX fallback до испытаний; затем сократить без потери аварийного восстановления |
| Видео YUV | Путь NextLib для software video | Собственное копирование/отрисовка кадров, исправление зелёного изображения в 2.2.2 | Проверить форматы пикселей, chroma subsampling, stride, colormatrix, crop, HDR |
| Аудио | Media3 FFmpeg audio + специальные обходы зависаний | Изменения в обработке смены channel layout (2.2.1) | Выяснить, изменена ли Java-обвязка, JNI или обе |
| API Lampa | Нынешняя реализация плейлистов и callback; audit по PR #2 ведётся отдельно | `ask_resume`, отчёт о прогрессе только при выходе | Обновить parsing и callback, проверить старых потребителей |
| Screen capture | Отдельного официального переключателя из 2.2.2 в ARX нет | Advanced → Decoder → Video visible to screen capture, с отключением HDR/tunneling | Перенести настройки и UI |
| UI и AVI | UI-реконструкция 2.1.3 | Коррекция видео XviD/DivX и некоторые изменения диалогов | Сверить resources и Java логику |
| Идентичность сборки | `versionName=2.1.3-arx5`, подписываемая ARX release-ветка | `versionName=2.2.2`, `versionCode=2002002` | Не повышать номер до завершения parity и сборочных тестов |

## Почему NextLib нельзя удалять сразу

`libffvideoJNI.so` содержит *native* обработчик конкретных методов `com.brouken.player.FfmpegVideoDecoder`; NextLib использует другой пакет, собственный Android `Renderer` и собственные интерфейсы JNI. Просто замена `.so` или исключение зависимости из Gradle приводит к отсутствующим классам или неразрешённым native-методам. Кроме того, ARX связывает с NextLib последний этап восстановления после реального сбоя hardware MediaCodec, включая ситуацию на Pixel 10 / Android 17. Присутствие HEVC 4:2:2/4:4:4 в upstream 2.2.2 не гарантирует, что проверен тот же failure path.

## Критерии принятия переноса

1. Сравнение декомпилированного `PlayerActivity`, `FfmpegVideoDecoder`, Media3 renderer, Prefs/Settings, API плейлиста и ресурсов из точного upstream APK.
2. Успешная сборка debug и release с сохранением прежнего package ID, подписи и способов интеграции Lampa.
3. Испытания на Pixel 10: проблемный HEVC 10-bit (тот же торрент/файл, что падал прежде), HEVC 4:2:2/4:4:4, обычный Main10, MKV/DV и переключение audio layout.
4. Испытания плейлиста: одиночный Intent, список эпизодов, nested playlist, `ask_resume`, переход к следующему, watched state и корректный callback Lampa.
5. Проверка screen capture, XviD/DivX и цветов software output на ARM.
6. Только после пункта 1–5 можно полностью убрать NextLib. До этого сохранить рабочую реализацию и возможность переключить старый видеотракт.

## Следующий автоматизированный шаг

Workflow `reverse-audit-v222.yml` загружает **точный** upstream APK, проверяет SHA-256, декомпилирует его через JADX и сохраняет пакет `com.brouken.player` и карту исходников в артефакте Actions. Это этап получения проверяемого исходного материала; декомпиляция может требовать ручной правки восстановленного Java-кода.

**Источники:** официальный GitHub Releases `v2.2.1`, `v2.2.2` и фактическое содержимое APK; ветка `arx-2.1.3-port-wip`.
