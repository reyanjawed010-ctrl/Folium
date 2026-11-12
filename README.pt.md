<div align="center">
  <picture>
    <source media="(prefers-color-scheme: dark)" srcset="core/resources/src/main/res/drawable-nodpi/vayana_share_dark_reader.webp">
    <source media="(prefers-color-scheme: light)" srcset="core/resources/src/main/res/drawable-nodpi/vayana_share_light.webp">
    <img alt="Vayana — um leitor de livros digitais para Android. Leia sem interrupções." src="core/resources/src/main/res/drawable-nodpi/vayana_share_light.webp" width="820">
  </picture>

  <h1>Folium - Um fork de Vayana</h1>

  <p><strong>Um fork pessoal de <a href="https://github.com/rjwarrier/Vayana">Vayana</a> por Ranjith Jayadevan.</strong></p>
  <p>Um leitor de livros digitais local por primeiro <a href="docs/USER_GUIDE.pt.md#reading-epub-books">EPUB</a> e <a href="docs/USER_GUIDE.pt.md#reading-pdf-books">PDF</a> para Android, criado para telas normais e <a href="docs/USER_GUIDE.pt.md#e-ink-and-accessibility">E‑Ink</a>.</p>

  <p>
    <img alt="Fork of v0.93" src="https://img.shields.io/badge/fork%20of-v0.93-00695c?style=flat-square">
    <img alt="Android 8.0 and newer" src="https://img.shields.io/badge/Android-8.0%2B-3DDC84?style=flat-square&logo=android&logoColor=white">
    <img alt="Kotlin" src="https://img.shields.io/badge/Kotlin-2.3-7F52FF?style=flat-square&logo=kotlin&logoColor=white">
    <img alt="Material 3" src="https://img.shields.io/badge/Material-3-6750A4?style=flat-square&logo=materialdesign&logoColor=white">
    <img alt="GPL-3.0" src="https://img.shields.io/badge/licence-GPL--3.0-blue?style=flat-square">
  </p>
</div>

---

## Sobre este fork

Trata-se de um fork pessoal de [Vayana](https://github.com/rjwarrier/Vayana), um leitor de livros eletrônicos EPUB e PDF open source para Android.

A maioria dos aplicativos de leitura se limite a exibir um livro. O Folium trata a leitura como uma prática conectada: organize o que deseja ler, mantenha o foco no texto, guarde passagens importantes, aprenda palavras novas, revisite o que importa e leve seu progresso de forma segura entre dispositivos.

O Folium faz isso sem exigir uma conta Folium ou exibir anúncios durante a leitura. Votre biblioteca é local por padrão. Os recursos opcionais on-line, como o [enriquecimento com Goodreads](docs/USER_GUIDE.pt.md#book-details-and-reading-status) e a [sincronização com o GitHub gerenciada por você](docs/USER_GUIDE.pt.md#github-sync), restent sob seu controle.

> **Este fork acompanha o projeto amup Vayana.** Para perguntas gerais, relatórios de bugs não relacionados às alterações deste fork e a documentação completa, acesse o [repositório original](https://github.com/rjwarrier/Vayana).

---

## O que é diferente neste fork

### Suporte PDF

O suporte PDF original tinha problemas que afetavam a experiência de leitura. Este fork corrige esses problemas :

- Correção do renderização de páginas PDF e comportamento de navegação.
- Melhoria no fornecimento de ranges de bytes PDF para que as páginas carreguem corretamente no leitor WebView.
- Correção da reabertura de um PDF na página correta após fechamento.
- Melhoria na geração e no cache de prévias de páginas PDF.

Estas correções estão presentes no histórico de commits deste fork. Se você usa Folium principalmente para leitura de PDF, este fork pode funcionar melhor para você.

### Rótulo de build de débog

O APK de débog é etiquetado **Folium** no lançador, para que ele possa coexistir ao lado duma instalação padrão Vayana durante os testes.
