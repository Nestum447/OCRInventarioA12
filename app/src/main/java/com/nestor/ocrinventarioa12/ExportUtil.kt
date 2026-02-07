package com.nestor.ocrinventarioa12

import android.content.ContentValues
import android.content.Context
import android.os.Build
import android.provider.MediaStore
import android.widget.Toast
import java.io.OutputStreamWriter
import java.text.SimpleDateFormat
import java.util.*

object ExportUtil {

    fun exportToCSV(context: Context, items: List<InventoryEntity>) {

        if (items.isEmpty()) {
            Toast.makeText(context, "No hay datos para exportar", Toast.LENGTH_SHORT).show()
            return
        }

        val fileName = "inventario_${
            SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault())
                .format(Date())
        }.csv"

        val contentValues = ContentValues().apply {
            put(MediaStore.MediaColumns.DISPLAY_NAME, fileName)
            put(MediaStore.MediaColumns.MIME_TYPE, "text/csv")
            put(MediaStore.MediaColumns.RELATIVE_PATH, "Download/")
        }

        val uri = context.contentResolver.insert(
            MediaStore.Files.getContentUri("external"),
            contentValues
        )

        uri?.let {
            val outputStream = context.contentResolver.openOutputStream(it)
            val writer = OutputStreamWriter(outputStream)

            writer.append("ID,Texto\n")

            items.forEach {
                writer.append("${it.id},\"${it.text}\"\n")
            }

            writer.flush()
            writer.close()

            Toast.makeText(context, "Archivo guardado en Download", Toast.LENGTH_LONG).show()
        }
    }
}
