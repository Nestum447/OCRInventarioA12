package com.nestor.ocrinventarioa12

import android.content.Context
import android.graphics.*
import androidx.exifinterface.media.ExifInterface
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.latin.TextRecognizerOptions
import java.io.File

object OCRProcessor {

    fun processImage(
        context: Context,
        file: File,
        overlayView: OverlayView,
        viewModel: InventoryViewModel
    ) {

        val originalBitmap = BitmapFactory.decodeFile(file.absolutePath) ?: return

        // 🔥 1️⃣ Corregir rotación EXIF
        val rotatedBitmap = fixRotation(file, originalBitmap)

        // 🔥 2️⃣ Obtener rectángulo del overlay
        val cropRect = overlayView.getCropRect()

        val scaleX = rotatedBitmap.width.toFloat() / overlayView.width
        val scaleY = rotatedBitmap.height.toFloat() / overlayView.height

        val realRect = Rect(
            (cropRect.left * scaleX).toInt(),
            (cropRect.top * scaleY).toInt(),
            (cropRect.right * scaleX).toInt(),
            (cropRect.bottom * scaleY).toInt()
        )

        // 🔥 3️⃣ Asegurar que el rectángulo no se salga del bitmap
        val safeRect = Rect(
            realRect.left.coerceIn(0, rotatedBitmap.width - 1),
            realRect.top.coerceIn(0, rotatedBitmap.height - 1),
            realRect.right.coerceIn(1, rotatedBitmap.width),
            realRect.bottom.coerceIn(1, rotatedBitmap.height)
        )

        val croppedBitmap = Bitmap.createBitmap(
            rotatedBitmap,
            safeRect.left,
            safeRect.top,
            safeRect.width(),
            safeRect.height()
        )

        // 🔥 4️⃣ Escalar x2 para mejorar lectura de números pequeños
        val scaledBitmap = Bitmap.createScaledBitmap(
            croppedBitmap,
            croppedBitmap.width * 2,
            croppedBitmap.height * 2,
            true
        )

        // 🔥 5️⃣ Convertir a escala de grises (mejora OCR)
        val processedBitmap = toGrayscale(scaledBitmap)

        val image = InputImage.fromBitmap(processedBitmap, 0)
        val recognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS)

        recognizer.process(image)
            .addOnSuccessListener { visionText ->

                val cleanedText = visionText.textBlocks
                    .joinToString(" ") { it.text }
                    .replace("\n", " ")
                    .replace("[^A-Za-z0-9]".toRegex(), "")
                    .trim()

                if (cleanedText.isNotEmpty()) {
                    viewModel.insert(cleanedText)
                }
            }
            .addOnFailureListener {
                it.printStackTrace()
            }
    }

    // 🔄 Corregir rotación
    private fun fixRotation(file: File, bitmap: Bitmap): Bitmap {
        val exif = ExifInterface(file.absolutePath)
        val orientation = exif.getAttributeInt(
            ExifInterface.TAG_ORIENTATION,
            ExifInterface.ORIENTATION_NORMAL
        )

        val rotation = when (orientation) {
            ExifInterface.ORIENTATION_ROTATE_90 -> 90f
            ExifInterface.ORIENTATION_ROTATE_180 -> 180f
            ExifInterface.ORIENTATION_ROTATE_270 -> 270f
            else -> 0f
        }

        if (rotation == 0f) return bitmap

        val matrix = Matrix()
        matrix.postRotate(rotation)
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true)
    }

    // 🎯 Escala de grises
    private fun toGrayscale(bitmap: Bitmap): Bitmap {
        val width = bitmap.width
        val height = bitmap.height

        val grayscale = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(grayscale)
        val paint = Paint()
        val colorMatrix = ColorMatrix()
        colorMatrix.setSaturation(0f)
        val filter = ColorMatrixColorFilter(colorMatrix)
        paint.colorFilter = filter
        canvas.drawBitmap(bitmap, 0f, 0f, paint)

        return grayscale
    }
}
