package org.caojun.expression

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.animation.Animation
import android.widget.ImageView
import android.widget.RelativeLayout
import org.jetbrains.anko.runOnUiThread

/**
 * 故障表情动画，使用旋转
 */
class ErrorExpression : RelativeLayout {

    companion object {
        const val DURATION = 1000L
        const val REPEAT_COUNT_ROTATING = 0
        const val REPEAT_COUNT_BLINK = 1
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
    private val raLeftClockwiseRotating = AnimationManager.Builder().setRotateAnimation(
        0F,
        360F,
        Animation.RELATIVE_TO_SELF,
        0.5F,
        Animation.RELATIVE_TO_SELF,
        0.5F,
        DURATION,
        REPEAT_COUNT_ROTATING
    ).create()
    private val raLeftAntiClockwiseRotating = AnimationManager.Builder().setRotateAnimation(
        0F,
        -360F,
        Animation.RELATIVE_TO_SELF,
        0.5F,
        Animation.RELATIVE_TO_SELF,
        0.5F,
        DURATION,
        REPEAT_COUNT_ROTATING
    ).create()

    //右眼珠旋转
    private var isRightClockwise: Boolean? = null
    private var isRightRotating = false
    private var isRightRotatingAnimationStarted = false
    private val raRightClockwiseRotating = AnimationManager.Builder().setRotateAnimation(
        0F,
        360F,
        Animation.RELATIVE_TO_SELF,
        0.5F,
        Animation.RELATIVE_TO_SELF,
        0.5F,
        DURATION,
        REPEAT_COUNT_ROTATING
    ).create()
    private val raRightAntiClockwiseRotating = AnimationManager.Builder().setRotateAnimation(
        0F,
        -360F,
        Animation.RELATIVE_TO_SELF,
        0.5F,
        Animation.RELATIVE_TO_SELF,
        0.5F,
        DURATION,
        REPEAT_COUNT_ROTATING
    ).create()

    //左眼珠缩放
    private var isLeftBlink = false
    private var isLeftBlinkAnimationStarted = false
    private val saLeftBlink = AnimationManager.Builder().setScaleAnimation(
        1.0f,
        0.3f,
        1.0f,
        0.3f,
        Animation.RELATIVE_TO_SELF,
        0.5f,
        Animation.RELATIVE_TO_SELF,
        0.5f,
        DURATION,
        REPEAT_COUNT_BLINK,
        Animation.REVERSE
    ).create()

    //右眼珠缩放
    private var isRightBlink = false
    private var isRightBlinkAnimationStarted = false
    private val saRightBlink = AnimationManager.Builder().setScaleAnimation(
        1.0f,
        0.3f,
        1.0f,
        0.3f,
        Animation.RELATIVE_TO_SELF,
        0.5f,
        Animation.RELATIVE_TO_SELF,
        0.5f,
        DURATION,
        REPEAT_COUNT_BLINK,
        Animation.REVERSE
    ).create()

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        LayoutInflater.from(context)
            .inflate(R.layout.view_expression_error, this, true)

        ivEyebrowLeft = findViewById(R.id.ivEyebrowLeft)
        ivEyebrowRight = findViewById(R.id.ivEyebrowRight)
        ivEyeLeft = findViewById(R.id.ivEyeLeft)
        ivEyeRight = findViewById(R.id.ivEyeRight)
        ivMouth = findViewById(R.id.ivMouth)

        //眼珠旋转
        val alLeft = object :
            Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {
                isLeftRotatingAnimationStarted = true
            }

            override fun onAnimationEnd(animation: Animation) {
                isLeftRotatingAnimationStarted = false
                if (isLeftRotating) {
                    if (isLeftClockwise == true) {
                        ivEyeLeft?.startAnimation(raLeftClockwiseRotating.animation())
                    } else if (isLeftClockwise == false) {
                        ivEyeLeft?.startAnimation(raLeftAntiClockwiseRotating.animation())
                    }
                } else if (isLeftBlink) {
                    ivEyeLeft?.startAnimation(saLeftBlink.animation())
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
                        ivEyeRight?.startAnimation(raRightClockwiseRotating.animation())
                    } else if (isRightClockwise == false) {
                        ivEyeRight?.startAnimation(raRightAntiClockwiseRotating.animation())
                    }
                } else if (isRightBlink) {
                    ivEyeRight?.startAnimation(saRightBlink.animation())
                }
            }

            override fun onAnimationRepeat(animation: Animation) {}
        }
        raRightClockwiseRotating.setAnimationListener(alRight)
        raRightAntiClockwiseRotating.setAnimationListener(alRight)

        //左眼珠缩放
        saLeftBlink.setAnimationListener(object :
            Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {
                isLeftBlinkAnimationStarted = true
            }

            override fun onAnimationEnd(animation: Animation) {
                isLeftBlinkAnimationStarted = false
                if (isLeftBlink) {
                    ivEyeLeft?.startAnimation(saLeftBlink.animation())
                } else if (isLeftRotating) {
                    if (isLeftClockwise == true) {
                        ivEyeLeft?.startAnimation(raLeftClockwiseRotating.animation())
                    } else if (isLeftClockwise == false) {
                        ivEyeLeft?.startAnimation(raLeftAntiClockwiseRotating.animation())
                    }
                }
            }

            override fun onAnimationRepeat(animation: Animation) {}
        })

        //右眼珠缩放
        saRightBlink.setAnimationListener(object :
            Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {
                isRightBlinkAnimationStarted = true
            }

            override fun onAnimationEnd(animation: Animation) {
                isRightBlinkAnimationStarted = false
                if (isRightBlink) {
                    ivEyeRight?.startAnimation(saRightBlink.animation())
                } else if (isRightRotating) {
                    if (isRightClockwise == true) {
                        ivEyeRight?.startAnimation(raRightClockwiseRotating.animation())
                    } else if (isRightClockwise == false) {
                        ivEyeRight?.startAnimation(raRightAntiClockwiseRotating.animation())
                    }
                }
            }

            override fun onAnimationRepeat(animation: Animation) {}
        })
    }

    /**
     * 左眼珠缩放
     */
    fun doLeftBlink() {
        isLeftBlink = if (isLeftRotating) {
            true
        } else {
            !isLeftBlink
        }
        if (isLeftBlink) {
            isLeftRotating = false
        }
        if (isLeftBlinkAnimationStarted || isLeftRotatingAnimationStarted) {
            return
        }
        context.runOnUiThread {
            ivEyeLeft?.startAnimation(saLeftBlink.animation())
        }
    }

    /**
     * 右眼珠缩放
     */
    fun doRightBlink() {
        isRightBlink = if (isRightRotating) {
            true
        } else {
            !isRightBlink
        }
        if (isRightBlink) {
            isRightRotating = false
        }
        if (isRightBlinkAnimationStarted || isRightRotatingAnimationStarted) {
            return
        }
        context.runOnUiThread {
            ivEyeRight?.startAnimation(saRightBlink.animation())
        }
    }

    /**
     * 左眼珠旋转/停止
     */
    fun doLeftRotating(isClockwise: Boolean) {
        if (isLeftBlink) {
            isLeftRotating = true
        } else if (isLeftClockwise == null || isLeftClockwise == isClockwise) {
            isLeftRotating = !isLeftRotating
        }
        if (isLeftRotating) {
            isLeftBlink = false
        }
        isLeftClockwise = isClockwise
        if (isLeftRotatingAnimationStarted || isLeftBlinkAnimationStarted) {
            return
        }
        val raLeftRotating =
            if (isClockwise) raLeftClockwiseRotating else raLeftAntiClockwiseRotating
        context.runOnUiThread {
            ivEyeLeft?.startAnimation(raLeftRotating.animation())
        }
    }

    /**
     * 右眼珠旋转/停止
     */
    fun doRightRotating(isClockwise: Boolean) {
        if (isRightBlink) {
            isRightRotating = true
        } else if (isRightClockwise == null || isRightClockwise == isClockwise) {
            isRightRotating = !isRightRotating
        }
        if (isRightRotating) {
            isRightBlink = false
        }
        isRightClockwise = isClockwise
        if (isRightRotatingAnimationStarted || isRightBlinkAnimationStarted) {
            return
        }
        val raRightRotating =
            if (isClockwise) raRightClockwiseRotating else raRightAntiClockwiseRotating
        context.runOnUiThread {
            ivEyeRight?.startAnimation(raRightRotating.animation())
        }
    }
}