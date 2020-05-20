package org.caojun.expression

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.view.animation.RotateAnimation
import android.view.animation.ScaleAnimation
import android.widget.ImageView
import android.widget.RelativeLayout
import org.jetbrains.anko.runOnUiThread

/**
 * 故障表情动画，使用旋转
 */
class ErrorExpression : RelativeLayout {

    companion object {
        const val DURATION = 1000L
        const val REPEAT_COUNT = 1
    }

    private var ivEyebrowLeft: ImageView? = null
    private var ivEyebrowRight: ImageView? = null
    private var ivEyeLeft: ImageView? = null
    private var ivEyeRight: ImageView? = null
    private var ivMouth: ImageView? = null

    //左眼珠旋转
    private var isLeftClockwise: Boolean? = null
    private var isLeftRotating = false
    private var isLeftRotatingAnimationStarted = false
    private val raLeftClockwiseRotating =
        RotateAnimation(
            0F,
            360F,
            Animation.RELATIVE_TO_SELF,
            0.5F,
            Animation.RELATIVE_TO_SELF,
            0.5F
        )
    private val raLeftAntiClockwiseRotating =
        RotateAnimation(
            0F,
            -360F,
            Animation.RELATIVE_TO_SELF,
            0.5F,
            Animation.RELATIVE_TO_SELF,
            0.5F
        )

    //右眼珠旋转
    private var isRightClockwise: Boolean? = null
    private var isRightRotating = false
    private var isRightRotatingAnimationStarted = false
    private val raRightClockwiseRotating =
        RotateAnimation(
            0F,
            360F,
            Animation.RELATIVE_TO_SELF,
            0.5F,
            Animation.RELATIVE_TO_SELF,
            0.5F
        )
    private val raRightAntiClockwiseRotating =
        RotateAnimation(
            0F,
            -360F,
            Animation.RELATIVE_TO_SELF,
            0.5F,
            Animation.RELATIVE_TO_SELF,
            0.5F
        )

    //左眼珠缩放
    private var isLeftBlink = false
    private var isLeftBlinkAnimationStarted = false
    private val saLeftBlink =
        ScaleAnimation(
            1.0f,
            0.3f,
            1.0f,
            0.3f,
            Animation.RELATIVE_TO_SELF,
            0.5f,
            Animation.RELATIVE_TO_SELF,
            0.5f
        )

    //右眼珠缩放
    private var isRightBlink = false
    private var isRightBlinkAnimationStarted = false
    private val saRightBlink =
        ScaleAnimation(
            1.0f,
            0.3f,
            1.0f,
            0.3f,
            Animation.RELATIVE_TO_SELF,
            0.5f,
            Animation.RELATIVE_TO_SELF,
            0.5f
        )

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        LayoutInflater.from(context)
            .inflate(R.layout.view_expression_error, this, true)

        ivEyebrowLeft = findViewById(R.id.ivEyebrowLeft)
        ivEyebrowRight = findViewById(R.id.ivEyebrowRight)
        ivEyeLeft = findViewById(R.id.ivEyeLeft)
        ivEyeRight = findViewById(R.id.ivEyeRight)
        ivMouth = findViewById(R.id.ivMouth)

        //眼珠旋转
        raLeftClockwiseRotating.duration = DURATION
        raLeftClockwiseRotating.repeatCount = REPEAT_COUNT
        raLeftClockwiseRotating.repeatMode = Animation.START_ON_FIRST_FRAME
        raLeftClockwiseRotating.interpolator = LinearInterpolator()
        raLeftAntiClockwiseRotating.duration = DURATION
        raLeftAntiClockwiseRotating.repeatCount = REPEAT_COUNT
        raLeftAntiClockwiseRotating.repeatMode = Animation.START_ON_FIRST_FRAME
        raLeftAntiClockwiseRotating.interpolator = LinearInterpolator()
        raRightClockwiseRotating.duration = DURATION
        raRightClockwiseRotating.repeatCount = REPEAT_COUNT
        raRightClockwiseRotating.repeatMode = Animation.START_ON_FIRST_FRAME
        raRightClockwiseRotating.interpolator = LinearInterpolator()
        raRightAntiClockwiseRotating.duration = DURATION
        raRightAntiClockwiseRotating.repeatCount = REPEAT_COUNT
        raRightAntiClockwiseRotating.repeatMode = Animation.START_ON_FIRST_FRAME
        raRightAntiClockwiseRotating.interpolator = LinearInterpolator()

        val alLeft = object :
            Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {
                isLeftRotatingAnimationStarted = true
            }

            override fun onAnimationEnd(animation: Animation) {
                isLeftRotatingAnimationStarted = false
                if (isLeftRotating) {
                    if (isLeftClockwise == true) {
                        ivEyeLeft?.startAnimation(raLeftClockwiseRotating)
                    } else if (isLeftClockwise == false) {
                        ivEyeLeft?.startAnimation(raLeftAntiClockwiseRotating)
                    }
                }
            }

            override fun onAnimationRepeat(animation: Animation) {}
        }
        raLeftClockwiseRotating.setAnimationListener(alLeft)
        raLeftAntiClockwiseRotating.setAnimationListener(alLeft)

        val alRight = object :
            Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {
                isRightRotatingAnimationStarted = true
            }

            override fun onAnimationEnd(animation: Animation) {
                isRightRotatingAnimationStarted = false
                if (isRightRotating) {
                    if (isRightClockwise == true) {
                        ivEyeRight?.startAnimation(raRightClockwiseRotating)
                    } else if (isRightClockwise == false) {
                        ivEyeRight?.startAnimation(raRightAntiClockwiseRotating)
                    }
                }
            }

            override fun onAnimationRepeat(animation: Animation) {}
        }
        raRightClockwiseRotating.setAnimationListener(alRight)
        raRightAntiClockwiseRotating.setAnimationListener(alRight)

        //左眼珠缩放
        saLeftBlink.duration = DURATION
        saLeftBlink.repeatCount = REPEAT_COUNT
        saLeftBlink.repeatMode = Animation.REVERSE
        saLeftBlink.setAnimationListener(object :
            Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {
                isLeftBlinkAnimationStarted = true
            }

            override fun onAnimationEnd(animation: Animation) {
                isLeftBlinkAnimationStarted = false
                if (isLeftBlink) {
                    ivEyeLeft?.startAnimation(saLeftBlink)
                }
            }

            override fun onAnimationRepeat(animation: Animation) {}
        })

        //右眼珠缩放
        saRightBlink.duration = DURATION
        saRightBlink.repeatCount = REPEAT_COUNT
        saRightBlink.repeatMode = Animation.REVERSE
        saRightBlink.setAnimationListener(object :
            Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {
                isRightBlinkAnimationStarted = true
            }

            override fun onAnimationEnd(animation: Animation) {
                isRightBlinkAnimationStarted = false
                if (isRightBlink) {
                    ivEyeRight?.startAnimation(saRightBlink)
                }
            }

            override fun onAnimationRepeat(animation: Animation) {}
        })
    }

    /**
     * 左眼珠缩放
     */
    fun doLeftBlink() {
        isLeftBlink = !isLeftBlink
        if (isLeftBlinkAnimationStarted) {
            return
        }
        context.runOnUiThread {
            ivEyeLeft?.startAnimation(saLeftBlink)
        }
    }

    /**
     * 右眼珠缩放
     */
    fun doRightBlink() {
        isRightBlink = !isRightBlink
        if (isRightBlinkAnimationStarted) {
            return
        }
        context.runOnUiThread {
            ivEyeRight?.startAnimation(saRightBlink)
        }
    }

    /**
     * 左眼珠旋转/停止
     */
    fun doLeftRotating(isClockwise: Boolean) {
        if (isLeftClockwise == null || isLeftClockwise == isClockwise) {
            isLeftRotating = !isLeftRotating
        }
        isLeftClockwise = isClockwise
        if (isLeftRotatingAnimationStarted) {
            return
        }
        val raLeftRotating = if (isClockwise) raLeftClockwiseRotating else raLeftAntiClockwiseRotating
        context.runOnUiThread {
            ivEyeLeft?.startAnimation(raLeftRotating)
        }
    }

    /**
     * 右眼珠旋转/停止
     */
    fun doRightRotating(isClockwise: Boolean) {
        if (isRightClockwise == null || isRightClockwise == isClockwise) {
            isRightRotating = !isRightRotating
        }
        isRightClockwise = isClockwise
        if (isRightRotatingAnimationStarted) {
            return
        }
        val raRightRotating = if (isClockwise) raRightClockwiseRotating else raRightAntiClockwiseRotating
        context.runOnUiThread {
            ivEyeRight?.startAnimation(raRightRotating)
        }
    }
}