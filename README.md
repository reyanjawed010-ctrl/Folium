<div align="center">
  <picture>
    <source media="(prefers-color-scheme: dark)" srcset="core/resources/src/main/res/drawable-nodpi/vayana_share_dark_reader.webp">
    <source media="(prefers-color-scheme: light)" srcset="core/resources/src/main/res/drawable-nodpi/vayana_share_light.webp">
    <img alt="Vayana — an Android e-reader. Read, undisturbed." src="core/resources/src/main/res/drawable-nodpi/vayana_share_light.webp" width="820">
  </picture>

  <h1>Folium - A fork of Vayana</h1>

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

---

## About this fork

This is a personal fork of [Vayana](https://github.com/rjwarrier/Vayana), an open-source EPUB and PDF reader for Android.

A huge thanks to [Ranjith Jayadevan](https://github.com/rjwarrier) for building such a genuinely great e-book reader. Folium is one of those rare apps that is calm, thoughtful, and actually does what it promises — no accounts, no ads, no nonsense. If you find this useful, please consider [supporting the original project](https://www.buymeacoffee.com/ranjithj).

> **This fork tracks the upstream Vayana project.** For general usage questions, bug reports unrelated to this fork's changes, and the full documentation, refer to the [original repository](https://github.com/rjwarrier/Vayana).

---

## What's different in this fork

### PDF support fixes

The original Vayana had issues with PDF support that affected reading experience. This fork addresses those issues:

- Fixed PDF page rendering and navigation behaviour.
- Improved PDF byte-range serving so pages load correctly in the WebView reader.
- Fixed the reader reopening a PDF on the correct page after closing.
- Improved PDF page preview generation and caching.

These fixes are present in the commit history of this fork. If you use Folium primarily for PDF reading, this fork may work better for you.

### Debug build label

The debug APK is labelled **Folium** in the launcher, so it can coexist alongside a standard Vayana install during testing.

---
