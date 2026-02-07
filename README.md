📦 OCRInventarioA12
Aplicación Android de OCR para Optimización de Inventarios

OCRInventarioA12 es una aplicación Android diseñada para optimizar procesos de captura de inventario mediante reconocimiento óptico de caracteres (OCR) completamente offline.

La solución está construida utilizando:

CameraX

Google ML Kit (Text Recognition v2)

Arquitectura MVVM

Room Database

Procesamiento avanzado de imágenes

Está enfocada en entornos industriales y de almacén donde la velocidad, precisión y operación sin conexión son críticas


🚀 Descripción del Producto

El registro manual de inventario es lento y propenso a errores.

OCRInventarioA12 permite:

Capturar etiquetas de productos con la cámara

Extraer texto automáticamente en el dispositivo

Limpiar y estructurar el texto detectado

Guardar resultados en base de datos local

Exportar información en formato CSV

Diseñada para:

Almacenes

Centros de distribución

Procesos logísticos

Extracción de SKU y códigos numéricos

🏗 Arquitectura

El proyecto sigue buenas prácticas modernas de Android.

🔹 Patrón: MVVM (Model – View – ViewModel)

Ventajas:

Separación clara de responsabilidades

Código mantenible y escalable

Observación reactiva con LiveData

🧠 Tecnologías Utilizadas
📷 Cámara

CameraX (Lifecycle-aware)

Captura en alta calidad

Selector de cámara trasera

🧠 OCR

Google ML Kit Text Recognition v2 (Latin)

Procesamiento 100% on-device

Sin dependencia de internet

💾 Persistencia

Room Database

LiveData

ViewModel

RecyclerView reactivo

⚡ Optimización Implementada

Este proyecto no utiliza el OCR básico directo.
Incluye mejoras técnicas para aumentar precisión:

✅ Corrección automática de rotación (EXIF)

Evita errores por orientación de cámara.

✅ Recorte inteligente por región

Reduce ruido fuera del área de interés.

✅ Escalado 2x

Mejora detección de números pequeños.

✅ Conversión a escala de grises

Aumenta contraste y reduce ruido de color.

✅ Reconstrucción palabra por palabra

📊 Funcionalidades

📷 Captura de imagen optimizada

🎯 Selección de zona de escaneo

🧠 Extracción estructurada de texto

📳 Vibración al capturar imagen

📋 Lista reactiva de resultados

🗑 Eliminación individual o total

📤 Exportación a CSV

📶 Funcionamiento completamente offline

🔒 Diseño Offline-First

Toda la extracción se realiza en el dispositivo.

No se envían datos a servidores externos.

Ideal para:

Ambientes industriales

Lugares sin conectividad

Entornos con requerimientos de seguridad

🎯 Problemas Técnicos Resueltos

✔ Texto detectado sin espacios
✔ Texto incompleto
✔ Problemas de orientación
✔ Baja precisión en números pequeños
✔ Ruido visual en etiquetas industriales

🧪 Caso de Uso Real

Ejemplo en almacén:

Operador apunta cámara a etiqueta

Ajusta zona de escaneo

Captura imagen

Dispositivo vibra (confirmación)

OCR extrae código

Código se guarda en base local

Se exporta CSV para sistema ERP

📈 Posibles Mejoras Futuras

OCR en tiempo real con ImageAnalysis

Filtro por patrones (solo SKU válidos)

Exportación a Excel (.xlsx)

Sincronización en la nube

Integración con código de barras

Autenticación de usuarios

Arquitectura Clean Architecture completa

Modo oscuro


👨‍💻 Sobre el Desarrollador

Néstor Carpio
MBA Y Desarrollador Android enfocado en soluciones prácticas de automatización para entornos logísticos e industriales.

Este proyecto demuestra:

Integración avanzada de cámara

Procesamiento de imágenes

Uso aplicado de ML on-device

Arquitectura MVVM

Persistencia estructurada

Resolución de problemas reales


🏁 Valor para Empresas y Reclutadores

Este proyecto demuestra:

Capacidad de trabajar con APIs complejas (CameraX + ML Kit)

Entendimiento de procesamiento de imágenes

Optimización de rendimiento vs precisión

Diseño offline-first

Arquitectura mantenible

Aplicación en entornos reales

No es un ejemplo tutorial básico, es una solución aplicada a un problema industrial real.
