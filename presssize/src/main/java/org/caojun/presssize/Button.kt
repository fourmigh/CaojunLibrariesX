package org.caojun.presssize

import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import android.view.MotionEvent

class Button : androidx.appcompat.widget.AppCompatButton {

    private var textSizeNormal = 24F
    private var textSizePressed = 36F
    companion object {
        const val UNIT = TypedValue.COMPLEX_UNIT_PX
    }

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?): this(context, attrs, androidx.appcompat.R.attr.buttonStyle)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int): super(context, attrs, defStyleAttr) {
        val t = context.obtainStyledAttributes(attrs, R.styleable.PressSize)

        try {
            textSizeNormal = t.getDimension(R.styleable.PressSize_android_textSize, textSizeNormal)
            textSizePressed = t.getDimension(R.styleable.PressSize_textSizePressed, textSizePressed)

            setTextSize(UNIT, textSizeNormal)
        } catch (e: Exception) {
        } finally {
            t.recycle()
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event?.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                if (textSize != textSizePressed) {
                    setTextSize(UNIT, textSizePressed)
                }
            }
            MotionEvent.ACTION_UP -> {
                if (textSize != textSizeNormal) {
                    setTextSize(UNIT, textSizeNormal)
                }
            }
        }
        return super.onTouchEvent(event)
    }
}