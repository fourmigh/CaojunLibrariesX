package org.caojun.animation

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Point
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.appcompat.widget.AppCompatImageView
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import kotlin.math.max

class WaterRippleView : AppCompatImageView {

    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        doAsync {
            isActivated = true
            while (isActivated) {
                for (i in listWaterRipple.size - 1 downTo 0) {
                    val waterRipple = listWaterRipple[i]
//                    waterRipple.radius++
//                    if (waterRipple.radius >= waterRipple.maxRadius) {
//                        listWaterRipple.remove(waterRipple)
//                        uiThread {
//                            setBackgroundColor(waterRipple.color)
//                        }
//                    }
                    if (waterRipple.radius < waterRipple.maxRadius) {
                        waterRipple.radius++
                    }
                }
                invalidate()
                Thread.sleep(10)
            }
        }
    }

    private class WaterRipple(val center: Point, val maxRadius: Int, val color: Int, var radius: Int = 0)

    private val listWaterRipple = ArrayList<WaterRipple>()
    private val paint = Paint()

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event?.actionMasked == MotionEvent.ACTION_DOWN) {
            val color = Color.parseColor(getRandomColor())
            val posX = event.x.toInt()
            val posY = event.y.toInt()
            val waterRipple = WaterRipple(Point(posX, posY), max(width, height), color)
            listWaterRipple.add(0, waterRipple)
        }
        return super.onTouchEvent(event)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        for (i in listWaterRipple.size - 1 downTo 0) {
            val waterRipple = listWaterRipple[i]
            paint.color = waterRipple.color
            canvas?.drawCircle(waterRipple.center.x.toFloat(), waterRipple.center.y.toFloat(), waterRipple.radius.toFloat(), paint)
        }
    }

    private fun getRandom(min: Int, max: Int): Int {
        return (Math.random() * (max + 1 - min) + min).toInt()
    }

    private fun getRandomColor(): String {
//        var A = Integer.toHexString(getRandom(0, 255)).toUpperCase()
//        var R = Integer.toHexString(getRandom(0, 255)).toUpperCase()
//        var G = Integer.toHexString(getRandom(0, 255)).toUpperCase()
//        var B = Integer.toHexString(getRandom(0, 255)).toUpperCase()
//        A = if (A.length == 1) "0$A" else A
//        R = if (R.length == 1) "0$R" else R
//        G = if (G.length == 1) "0$G" else G
//        B = if (B.length == 1) "0$B" else B
//        return "#$A$R$G$B"
        val sb = StringBuffer("#")
        while (sb.length < 8) {
            var color = Integer.toHexString(getRandom(0, 255))
            color = if (color.length == 1) "0$color" else color
            sb.append(color)
        }
        return sb.toString()
    }
}