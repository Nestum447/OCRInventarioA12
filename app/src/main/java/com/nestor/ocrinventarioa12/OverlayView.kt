package com.nestor.ocrinventarioa12

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

class OverlayView(context: Context, attrs: AttributeSet?) :
    View(context, attrs) {

    private val paint = Paint().apply {
        color = Color.GREEN
        style = Paint.Style.STROKE
        strokeWidth = 6f
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val rectWidth = width * 0.7f
        val rectHeight = height * 0.2f

        val left = (width - rectWidth) / 2
        val top = (height - rectHeight) / 2
        val right = left + rectWidth
        val bottom = top + rectHeight

        canvas.drawRect(left, top, right, bottom, paint)
    }

    fun getCropRect(): Rect {
        val rectWidth = width * 0.7f
        val rectHeight = height * 0.2f

        val left = ((width - rectWidth) / 2).toInt()
        val top = ((height - rectHeight) / 2).toInt()
        val right = (left + rectWidth).toInt()
        val bottom = (top + rectHeight).toInt()

        return Rect(left, top, right, bottom)
    }
}
