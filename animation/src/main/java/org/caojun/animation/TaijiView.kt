package org.caojun.animation

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.LinearInterpolator
import androidx.appcompat.widget.AppCompatImageView
import kotlin.math.abs

class TaijiView : AppCompatImageView {

    //顺时针
    var isClockwise = false
    //匀速/加速
    var isLinear = true
    //单次时间
    var duration = 2000L
    //旋转度数
    var rotationAngle = 360F

    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        setImageResource(R.drawable.ic_taiji)
        startAnimation()
    }

    private fun startAnimation() {
        var rotation = abs(rotationAngle)
        if (!isClockwise) {
            rotation = - rotation
        }
        this.rotation = 0F
        val objectAnimator = ObjectAnimator.ofFloat(this, "rotation", rotation)
        if (isLinear) {
            objectAnimator.interpolator = LinearInterpolator()
        } else {
            objectAnimator.interpolator = AccelerateDecelerateInterpolator()
        }
        objectAnimator.duration = duration
        objectAnimator.repeatMode = ValueAnimator.RESTART
        objectAnimator.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?, isReverse: Boolean) {
                startAnimation()
            }
        })
        objectAnimator.start()
    }
}