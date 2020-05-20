package org.caojun.expression

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import android.widget.SeekBar
import org.jetbrains.anko.runOnUiThread

/**
 * 包含SeekBar的表情，可用于显示进度
 */
class WaveExpression : RelativeLayout {

    private var sbMouth: SeekBar? = null
    private var wvEyeLeft: WaveView? = null
    private var wvEyeRight: WaveView? = null

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        LayoutInflater.from(context)
            .inflate(R.layout.view_expression_wave, this, true)

        sbMouth = findViewById(R.id.sbMouth)
        wvEyeLeft = findViewById(R.id.wvEyeLeft)
        wvEyeRight = findViewById(R.id.wvEyeRight)
    }

    fun setProgress(progress: Int) {
        context.runOnUiThread {
            sbMouth?.progress = progress
            val percent = progress / 100F
            wvEyeLeft?.waveHeightPercent = percent
            wvEyeRight?.waveHeightPercent = percent
        }
    }
}