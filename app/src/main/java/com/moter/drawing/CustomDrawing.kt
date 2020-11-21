package com.moter.drawing

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.DisplayMetrics
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import androidx.core.view.setPadding

class CustomDrawing(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private val TAG = CustomDrawing::class.java.name
    private val paint = Paint()
    private val path = Path()

    private var firstX: Float? = null
    private var firstY: Float? = null

    init {
        paint.isAntiAlias = true
        paint.strokeWidth = 10f
        paint.color = if (context?.isDarkThemeOn()!!) Color.WHITE else Color.BLACK
        paint.style = Paint.Style.STROKE
        paint.strokeJoin = Paint.Join.ROUND

        val cornerPathEffect = CornerPathEffect(10.0f)
        paint.pathEffect = cornerPathEffect

    }


    override fun onDraw(canvas: Canvas?) {
        Log.d(TAG, "onDraw")

        for (i in 150..screenRectPx.width() - 150 step 50) {
            canvas?.drawPoint(i.toFloat(), 150f, paint)
            canvas?.drawPoint(i.toFloat(), 500f, paint)
        }

        for (i in 150..500 step 50) {
            canvas?.drawPoint(150f, i.toFloat(), paint)
            canvas?.drawPoint(screenRectPx.width() - 150f, i.toFloat(), paint)
        }

        canvas?.drawPath(path, paint)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val eventX = event?.x
        val eventY = event?.y
        if (firstX == null) firstX = eventX
        if (firstY == null) firstY = eventY

        Log.d(TAG, "x coordinate : $eventX")
        Log.d(TAG, "y coordinate : $eventY")

        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                Log.d(TAG, "action down")
                eventY?.let {
                    eventX?.let { it1 ->
                        path.moveTo(it1, it)
                        path.lineTo(it1 + 1, it + 1)
                    }
                }
                return true
            }
            MotionEvent.ACTION_MOVE -> {
                Log.d(TAG, "action move")
                eventY?.let {
                    eventX?.let { it1 -> path.lineTo(it1, it) }
                }
            }
            MotionEvent.ACTION_UP -> {
                Log.d(TAG, "action up")
                if (eventX!! in firstX!! - 10..firstX!! + 10 && eventY!! in firstY!! - 10..firstY!! + 10) {
                    Log.d(TAG,"line joined")
                }
            }
        }
        invalidate()
        return true
    }


    fun clear() {
        path.reset()
        firstX = null
        firstY = null
        invalidate()
    }
}