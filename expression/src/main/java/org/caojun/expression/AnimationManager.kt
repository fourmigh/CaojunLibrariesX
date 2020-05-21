package org.caojun.expression

import android.animation.ObjectAnimator
import android.view.animation.*

class AnimationManager {

    private var scaleAnimation: ScaleAnimation? = null
    private var rotateAnimation: RotateAnimation? = null
    private var translateAnimation: TranslateAnimation? = null
    private var objectAnimator: ObjectAnimator? = null

    fun start() {
        scaleAnimation?.start()
        rotateAnimation?.start()
        translateAnimation?.start()
        objectAnimator?.start()
    }

    fun cancel() {
        scaleAnimation?.cancel()
        rotateAnimation?.cancel()
        translateAnimation?.cancel()
        objectAnimator?.cancel()
    }

    fun animation(): Animation? {
        if (scaleAnimation != null) {
            return scaleAnimation
        }
        if (rotateAnimation != null) {
            return rotateAnimation
        }
        if (translateAnimation != null) {
            return translateAnimation
        }
        return null
    }

    fun setAnimationListener(listener: Animation.AnimationListener?) {
        scaleAnimation?.setAnimationListener(listener)
        rotateAnimation?.setAnimationListener(listener)
        translateAnimation?.setAnimationListener(listener)
    }

    class Builder {
        private var scaleAnimation: ScaleAnimation? = null
        private var rotateAnimation: RotateAnimation? = null
        private var translateAnimation: TranslateAnimation? = null
        private var objectAnimator: ObjectAnimator? = null

        fun setScaleAnimation(
            fromX: Float, toX: Float, fromY: Float, toY: Float,
            pivotXType: Int, pivotXValue: Float,
            pivotYType: Int, pivotYValue: Float,
            duration: Long, repeatCount: Int, repeatMode: Int
        ): Builder {
            scaleAnimation = ScaleAnimation(
                fromX,
                toX,
                fromY,
                toY,
                pivotXType,
                pivotXValue,
                pivotYType,
                pivotYValue
            )
            scaleAnimation?.duration = duration
            scaleAnimation?.repeatCount = repeatCount
            scaleAnimation?.repeatMode = repeatMode
            if (repeatCount <= 0) {
                scaleAnimation?.interpolator = LinearInterpolator()
            }
            return this
        }

        fun setRotateAnimation(
            fromDegrees: Float, toDegrees: Float,
            pivotXType: Int, pivotXValue: Float,
            pivotYType: Int, pivotYValue: Float,
            duration: Long, repeatCount: Int, repeatMode: Int
        ): Builder {
            rotateAnimation = RotateAnimation(
                fromDegrees,
                toDegrees,
                pivotXType,
                pivotXValue,
                pivotYType,
                pivotYValue
            )
            rotateAnimation?.duration = duration
            rotateAnimation?.repeatCount = repeatCount
            rotateAnimation?.repeatMode = repeatMode
            if (repeatCount <= 0) {
                rotateAnimation?.interpolator = LinearInterpolator()
            }
            return this
        }

        fun setTranslateAnimation(
            fromXType: Int, fromXValue: Float,
            toXType: Int, toXValue: Float,
            fromYType: Int, fromYValue: Float,
            toYType: Int, toYValue: Float,
            duration: Long, repeatCount: Int, repeatMode: Int
        ): Builder {
            translateAnimation = TranslateAnimation(
                fromXType, fromXValue,
                toXType, toXValue,
                fromYType, fromYValue,
                toYType, toYValue
            )
            translateAnimation?.duration = duration
            translateAnimation?.repeatCount = repeatCount
            translateAnimation?.repeatMode = repeatMode
            if (repeatCount <= 0) {
                translateAnimation?.interpolator = LinearInterpolator()
            }
            return this
        }

        fun setObjectAnimator(
            duration: Long, repeatCount: Int,
            target: Any?,
            propertyName: String,
            vararg values: Float): Builder {
            objectAnimator = ObjectAnimator.ofFloat(target, propertyName, *values)
            objectAnimator?.duration = duration
            objectAnimator?.repeatCount = repeatCount
            return this
        }

        fun create(): AnimationManager {
            val animationManager = AnimationManager()
            animationManager.scaleAnimation = scaleAnimation
            animationManager.rotateAnimation = rotateAnimation
            animationManager.translateAnimation = translateAnimation
            animationManager.objectAnimator = objectAnimator
            return animationManager
        }
    }
}