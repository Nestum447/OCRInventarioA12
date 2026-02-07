package com.nestor.ocrinventarioa12

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Rect
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

        val originalBitmap = BitmapFactory.decodeFile(file.absolutePath)

        if (originalBitmap == null) return

        // Obtener rectángulo del overlay
        val cropRect = overlayView.getCropRect()

        // Escalar coordenadas del overlay al tamaño real del bitmap
        val scaleX = originalBitmap.width.toFloat() / overlayView.width
        val scaleY = originalBitmap.height.toFloat() / overlayView.height

        val realRect = Rect(
            (cropRect.left * scaleX).toInt(),
            (cropRect.top * scaleY).toInt(),
            (cropRect.right * scaleX).toInt(),
            (cropRect.bottom * scaleY).toInt()
        )

        val croppedBitmap = Bitmap.createBitmap(
            originalBitmap,
            realRect.left,
            realRect.top,
            realRect.width(),
            realRect.height()
        )

        val image = InputImage.fromBitmap(croppedBitmap, 0)
        val recognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS)

        recognizer.process(image)
            .addOnSuccessListener { visionText ->

                val detectedText = visionText.text.trim()

                if (detectedText.isNotEmpty()) {
                    viewModel.insert(detectedText)
                }
            }
            .addOnFailureListener {
                it.printStackTrace()
            }
    }
}
