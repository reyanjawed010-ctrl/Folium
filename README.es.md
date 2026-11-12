<div align="center">
  <picture>
    <source media="(prefers-color-scheme: dark)" srcset="core/resources/src/main/res/drawable-nodpi/vayana_share_dark_reader.webp">
    <source media="(prefers-color-scheme: light)" srcset="core/resources/src/main/res/drawable-nodpi/vayana_share_light.webp">
    <img alt="Vayana — un lector de libros electrónicos para Android. Lee sin interrupciones." src="core/resources/src/main/res/drawable-nodpi/vayana_share_light.webp" width="820">
  </picture>

  <h1>Folium - Un fork de Vayana</h1>

  <p><strong>Un fork personal de <a href="https://github.com/rjwarrier/Vayana">Vayana</a> por Ranjith Jayadevan.</strong></p>
  <p>Un lector de libros electrónicos local por primera vez <a href="docs/USER_GUIDE.es.md#reading-epub-books">EPUB</a> y <a href="docs/USER_GUIDE.es.md#reading-pdf-books">PDF</a> para Android, creado para pantallas normales y <a href="docs/USER_GUIDE.es.md#e-ink-and-accessibility">E‑Ink</a>.</p>

  <p>
    <img alt="Fork of v0.93" src="https://img.shields.io/badge/fork%20of-v0.93-00695c?style=flat-square">
    <img alt="Android 8.0 and newer" src="https://img.shields.io/badge/Android-8.0%2B-3DDC84?style=flat-square&logo=android&logoColor=white">
    <img alt="Kotlin" src="https://img.shields.io/badge/Kotlin-2.3-7F52FF?style=flat-square&logo=kotlin&logoColor=white">
    <img alt="Material 3" src="https://img.shields.io/badge/Material-3-6750A4?style=flat-square&logo=materialdesign&logoColor=white">
    <img alt="GPL-3.0" src="https://img.shields.io/badge/licence-GPL--3.0-blue?style=flat-square">
  </p>
</div>

---

## Acerca de este fork

Se trata de un fork personal de [Vayana](https://github.com/rjwarrier/Vayana), un lector de libros electrónicos EPUB y PDF de código abierto para Android.

La mayoría de las aplicaciones de lectura se limitan a mostrar un libro. Folium trata la lectura como una práctica conectada: organiza lo que quieres leer, concéntrate en el texto, conserva pasajes significativos, aprende palabras nuevas, vuelve a lo que te importa y lleva tu progreso de forma segura entre dispositivos.

Folium lo hace sin exigir una cuenta Folium ni mostrar anuncios mientras lees. Tu biblioteca es local de forma predeterminada. Las funciones opcionales en línea, como el [enriquecimiento con Goodreads](docs/USER_GUIDE.es.md#book-details-and-reading-status) y la [sincronización con GitHub administrada por ti](docs/USER_GUIDE.es.md#github-sync), permanecen bajo tu control.

> **Este fork sigue el proyecto amup Vayana.** Para consultas generales, informes de errores no relacionados con las alteraciones de este fork y la documentación completa, accede al [repositorio original](https://github.com/rjwarrier/Vayana).

---

## ¿Qué hay de diferente en este fork?

### Soporte PDF

El soporte PDF original tenía problemas que afectaron la experiencia de lectura. Este fork corrige estos problemas:

- Corrección de la representación de páginas PDF y comportamiento de navegación.
- Mejora en la entrega de rangos de bytes PDF para que las páginas se carguen correctamente en el visor WebView.
- Corrección de la reanudación de un PDF en la página correcta después del cierre.
- Mejora en la generación y el caché de vistas previas de páginas PDF.

Estas correcciones están presentes en el historial de commits de este fork. Si usas Folium principalmente para la lectura de PDF, este fork puede funcionar mejor para ti.

### Etiqueta de compilación de depuración

El APK de depuración está etiquetado **Folium** en el lanzador, para que pueda coexistir al lado de una instalación estándar Vayana durante las pruebas.
