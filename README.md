# Vayana - A fork of Vayana

<h1>Vayana - Personal Fork</h1>

<p><strong>A personal fork of <a href="https://github.com/rjwarrier/Vayana">Vayana</a> by Ranjith Jayadevan.</strong></p>
<p>A calm, local-first <a href="docs/USER_GUIDE.md#reading-epub-books">EPUB</a> and <a href="docs/USER_GUIDE.md#reading-pdf-books">PDF</a> reader for Android, built for regular screens and <a href="docs/USER_GUIDE.md#e-ink-and-accessibility">E‑Ink devices</a>.</p>

<p>
    <img alt="Fork of v0.93" src="https://img.shields.io/badge/fork%20of-v0.93-00695c?style=flat-square">
    <img alt="Android 8.0 and newer" src="https://img.shields.io/badge/Android-8.0%2B-3DDC84?style=flat-square&logo=android&logoColor=white">
    <img alt="Kotlin" src="https://img.shields.io/badge/Kotlin-2.3-7F52FF?style=flat-square&logo=kotlin&logoColor=white">
    <img alt="Material 3" src="https://img.shields.io/badge/Material-3-6750A4?style=flat-square&logo=materialdesign&logoColor=white">
    <img alt="GPL-3.0" src="https://img.shields.io/badge/licence-GPL--3.0-blue?style=flat-square">
</p>
</div>
</div>

## About this fork

This is a personal fork of [Vayana](https://github.com/rjwarrier/Vayana), an open-source EPUB and PDF reader for Android.

A huge thanks to [Ranjith Jayadevan](https://github.com/rjwarrier) for building such a genuinely great e-book reader. Vayana is one of those rare apps that is calm, thoughtful, and actually does what it promises — no accounts, no ads, no nonsense. If you find this useful, please consider [supporting the original project](https://www.buymeacoffee.com/ranjithj).

> **This fork tracks the upstream Vayana project.** For general usage questions, bug reports unrelated to this fork's changes, and the full documentation, refer to the [original repository](https://github.com/rjwarrier/Vayana).

---

## What's different in this fork

### PDF support fixes

The original Vayana had issues with PDF support that affected reading experience. This fork addresses those issues:

- Fixed PDF page rendering and navigation behaviour.
- Improved PDF byte-range serving so pages load correctly in the WebView reader.
- Fixed the reader reopening a PDF on the correct page after closing.
- Improved PDF page preview generation and caching.

These fixes are present in the commit history of this fork. If you use Vayana primarily for PDF reading, this fork may work better for you.

### Debug build label

The debug APK is labelled **Vayana** in the launcher, so it can coexist alongside a standard Vayana install during testing.

---

## Attribution

**Original project:** [Vayana](https://github.com/rjwarrier/Vayana)  
**Original author:** [Ranjith Jayadevan](https://github.com/rjwarrier)  
**Original licence:** [GNU General Public License v3.0](LICENSE)

This fork is released under the same licence. All original copyright notices are preserved.

---

## Licence

Copyright (C) 2026 Ranjith Jayadevan  
Modifications copyright (C) 2026 Rey

Vayana and this fork are free software: you can redistribute and/or modify them under the terms of the [GNU General Public License](LICENSE) as published by the Free Software Foundation, either version 3 of the Licence, or (at your option) any later version.

Distributed in the hope that it will be useful, but **without any warranty**. See the [LICENSE](LICENSE) file for details.

In short: you are free to use, study, modify and share this. If you distribute a modified version, it must also be released under the GPL with its source code available.

---

## Build from source

### Requirements

- JDK 17 or newer
- Android SDK 37
- Git

```bash
git clone https://github.com/reyanjawed010-ctrl/My-Vayana-Fork.git
cd My-Vayana-Fork
./gradlew :app:assembleDebug
```

On Windows:

```powershell
.\gradlew.bat :app:assembleDebug
```

The debug APK (labelled **Vayana** in the launcher) is written to `app/build/outputs/apk/debug/`.

---

## Project structure

```
app/                    Phone application shell and navigation
wear/                   Wear OS physical-reading companion
core/                   Database, settings, files, backup, sync and design system
feature/                Library, reader, discovery, notes, search, statistics and settings
reader/engine-api/      Reader engine contract
reader/engine-web/      Foliate-based EPUB engine and WebView bridge
format/epub/            EPUB metadata and import support
format/pdf/             PDF metadata, rendering and text support
dictionary/             Dictionary API and offline dictionaries
build-logic/            Shared Android and Kotlin build conventions
```

---

## Documentation

For full documentation, refer to the original Vayana repository:

- [Vayana website](https://ranjithj.in/vayana/)
- [User guide](docs/USER_GUIDE.md)
- [Documentation index](docs/README.md)
- [Wear OS companion](docs/WEAR_OS.md)
- [GitHub sync setup](docs/GITHUB_SYNC_SETUP.md)
