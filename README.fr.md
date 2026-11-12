<div align="center">
  <picture>
    <source media="(prefers-color-scheme: dark)" srcset="core/resources/src/main/res/drawable-nodpi/vayana_share_dark_reader.webp">
    <source media="(prefers-color-scheme: light)" srcset="core/resources/src/main/res/drawable-nodpi/vayana_share_light.webp">
    <img alt="Vayana — une liseuse pour Android. Lisez sans interruption." src="core/resources/src/main/res/drawable-nodpi/vayana_share_light.webp" width="820">
  </picture>

  <h1>Folium - Un fork de Vayana</h1>

  <p><strong>Un fork personnel de <a href="https://github.com/rjwarrier/Vayana">Vayana</a> par Ranjith Jayadevan.</strong></p>
  <p>Une liseuse locale au premier plan <a href="docs/USER_GUIDE.fr.md#reading-epub-books">EPUB</a> et <a href="docs/USER_GUIDE.fr.md#reading-pdf-books">PDF</a> pour Android, conçue pour les écrans classiques et <a href="docs/USER_GUIDE.fr.md#e-ink-and-accessibility">E‑Ink</a>.</p>

  <p>
    <img alt="Fork of v0.93" src="https://img.shields.io/badge/fork%20of-v0.93-00695c?style=flat-square">
    <img alt="Android 8.0 and newer" src="https://img.shields.io/badge/Android-8.0%2B-3DDC84?style=flat-square&logo=android&logoColor=white">
    <img alt="Kotlin" src="https://img.shields.io/badge/Kotlin-2.3-7F52FF?style=flat-square&logo=kotlin&logoColor=white">
    <img alt="Material 3" src="https://img.shields.io/badge/Material-3-6750A4?style=flat-square&logo=materialdesign&logoColor=white">
    <img alt="GPL-3.0" src="https://img.shields.io/badge/licence-GPL--3.0-blue?style=flat-square">
  </p>
</div>

---

## À propos de ce fork

Il s'agit d'un fork personnel de [Vayana](https://github.com/rjwarrier/Vayana), un lecteur de livres EPUB et PDF open source pour Android.

Un grand merci à [Ranjith Jayadevan](https://github.com/rjwarrier) d'avoir créé un lecteur aussi calme, réfléchi et qui tient réellement ses promesses — pas de comptes, pas de publicités, pas de superflu. Si vous trouvez cette application utile, merci de [soutenir le projet original](https://www.buymeacoffee.com/ranjithj).

> **Ce fork suit le projet amont Vayana.** Pour les questions générales, les bugs non liés aux modifications de ce fork et la documentation complète, reportez-vous vers le [dépôt original](https://github.com/rjwarrier/Vayana).

---

## Ce qui diffère dans ce fork

### Corrections de support PDF

Le support PDF d'origine présentaient des problèmes affectant l'expérience de lecture. Ce fork corrige ces problèmes :

- Correction du rendu des pages PDF et du comportement de navigation.
- Amélioration du service par plages d'octets PDF pour que les pages s'affichent correctement dans le lecteur WebView.
- Correction du rechargement d'un PDF à la page exacte après fermeture.
- Amélioration de la génération et du cache des aperçus de pages PDF.

Ces corrections sont présentes dans l'historique des commits de ce fork. Si vous utilisez Folium principalement pour la lecture de PDF, ce fork pourrait mieux fonctionner pour vous.

### Étiquette de build de débogage

L'APK de débogage est étiqueté **Folium** dans le lanceur, afin qu'il puisse coexister à côté d'une installation standard Vayana pendant les tests.
