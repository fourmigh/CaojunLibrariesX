package org.caojun.expression

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.animation.Animation
import android.widget.ImageView
import android.widget.RelativeLayout
import org.jetbrains.anko.runOnUiThread

/**
 * 表情动画，使用旋转、变形、位移
 */
class BlinkExpression : RelativeLayout {

    companion object {
        const val DURATION = 500L
        const val REPEAT_COUNT = 1
    }

    private var ivEyebrowLeft: ImageView? = null
    private var ivEyebrowRight: ImageView? = null
    private var ivEyeLeft: ImageView? = null
    private var ivEyeRight: ImageView? = null
    private var ivMouth: ImageView? = null

    //眨眼
    private var isLeftBlink = false
    private var isRightBlink = false
    private var isLeftBlinkAnimationStarted = false
    private var isRightBlinkAnimationStarted = false
    private val saLeftBlink = AnimationManager.Builder().setScaleAnimation(
        1.0f,
        1.0f,
        1.0f,
        0.1f,
        Animation.RELATIVE_TO_SELF,
        1f,
        Animation.RELATIVE_TO_SELF,
        0.5f,
        DURATION,
        REPEAT_COUNT,
        Animation.REVERSE
    ).create()
    private val saRightBlink = AnimationManager.Builder().setScaleAnimation(
        1.0f,
        1.0f,
        1.0f,
        0.1f,
        Animation.RELATIVE_TO_SELF,
        1f,
        Animation.RELATIVE_TO_SELF,
        0.5f,
        DURATION,
        REPEAT_COUNT,
        Animation.REVERSE
    ).create()

    //左撇嘴
    private var isLeftTilting = false
    private var isLeftTiltingAnimationStarted = false
    private val raLeftTilting = AnimationManager.Builder().setRotateAnimation(
        0F,
        30F,
        Animation.RELATIVE_TO_SELF,
        0.5F,
        Animation.RELATIVE_TO_SELF,
        -1F,
        DURATION,
        REPEAT_COUNT,
        Animation.REVERSE
    ).create()

    //右撇嘴
    private var isRightTilting = false
    private var isRightTiltingAnimationStarted = false
    private val raRightTilting = AnimationManager.Builder().setRotateAnimation(
        0F,
        -30F,
        Animation.RELATIVE_TO_SELF,
        0.5F,
        Animation.RELATIVE_TO_SELF,
        -1F,
        DURATION,
        REPEAT_COUNT,
        Animation.REVERSE
    ).create()

    //眉毛上下位移
    private var isLeftMove = false
    private var isRightMove = false
    private var isLeftMoveAnimationStarted = false
    private var isRightMoveAnimationStarted = false
    private val taLeftMove = AnimationManager.Builder().setTranslateAnimation(
        Animation.RELATIVE_TO_PARENT,
        0F,
        Animation.RELATIVE_TO_PARENT,
        0F,
        Animation.RELATIVE_TO_PARENT,
        0F,
        Animation.RELATIVE_TO_PARENT,
        0.02F,
        DURATION,
        REPEAT_COUNT,
        Animation.REVERSE
    ).create()
    private val taRightMove = AnimationManager.Builder().setTranslateAnimation(
        Animation.RELATIVE_TO_PARENT,
        0F,
        Animation.RELATIVE_TO_PARENT,
        0F,
        Animation.RELATIVE_TO_PARENT,
        0F,
        Animation.RELATIVE_TO_PARENT,
        0.02F,
        DURATION,
        REPEAT_COUNT,
        Animation.REVERSE
    ).create()

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        LayoutInflater.from(context)
            .inflate(R.layout.view_expression_blink, this, true)

        ivEyebrowLeft = findViewById(R.id.ivEyebrowLeft)
        ivEyebrowRight = findViewById(R.id.ivEyebrowRight)
        ivEyeLeft = findViewById(R.id.ivEyeLeft)
        ivEyeRight = findViewById(R.id.ivEyeRight)
        ivMouth = findViewById(R.id.ivMouth)

        //眨眼
        saLeftBlink.setAnimationListener(object :
            Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {
                isLeftBlinkAnimationStarted = true
            }

            override fun onAnimationEnd(animation: Animation) {
                isLeftBlinkAnimationStarted = false
                if (isLeftBlink) {
                    ivEyeLeft?.startAnimation(saLeftBlink.animation())
                }
            }

            override fun onAnimationRepeat(animation: Animation) {}
        })
        saRightBlink.setAnimationListener(object :
            Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {
                isRightBlinkAnimationStarted = true
            }

            override fun onAnimationEnd(animation: Animation) {
                isRightBlinkAnimationStarted = false
                if (isRightBlink) {
                    ivEyeRight?.startAnimation(saRightBlink.animation())
                }
            }

            override fun onAnimationRepeat(animation: Animation) {}
        })

        //左撇嘴
        raLeftTilting.setAnimationListener(object :
            Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {
                isLeftTiltingAnimationStarted = true
                ivMouth?.setImageResource(R.drawable.ic_mouth_ani)
            }

            override fun onAnimationEnd(animation: Animation) {
                isLeftTiltingAnimationStarted = false
                when {
                    isLeftTilting -> {
                        ivMouth?.startAnimation(raLeftTilting.animation())
                    }
                    isRightTilting -> {
                        ivMouth?.startAnimation(raRightTilting.animation())
                    }
                    else -> {
                        ivMouth?.setImageResource(R.drawable.ic_mouth)
                    }
                }
            }

            override fun onAnimationRepeat(animation: Animation) {}
        })

        //右撇嘴
        raRightTilting.setAnimationListener(object :
            Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {
                isRightTiltingAnimationStarted = true
                ivMouth?.setImageResource(R.drawable.ic_mouth_ani)
            }

            override fun onAnimationEnd(animation: Animation) {
                isRightTiltingAnimationStarted = false
                when {
                    isRightTilting -> {
                        ivMouth?.startAnimation(raRightTilting.animation())
                    }
                    isLeftTilting -> {
                        ivMouth?.startAnimation(raLeftTilting.animation())
                    }
                    else -> {
                        ivMouth?.setImageResource(R.drawable.ic_mouth)
                    }
                }
            }

            override fun onAnimationRepeat(animation: Animation) {}
        })

        //眉毛移动
        taLeftMove.setAnimationListener(object :
            Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {
                isLeftMoveAnimationStarted = true
            }

            override fun onAnimationEnd(animation: Animation) {
                isLeftMoveAnimationStarted = false
                if (isLeftMove) {
                    ivEyebrowLeft?.startAnimation(taLeftMove.animation())
                }
            }

            override fun onAnimationRepeat(animation: Animation) {}
        })
        taRightMove.setAnimationListener(object :
            Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {
                isRightMoveAnimationStarted = true
            }

            override fun onAnimationEnd(animation: Animation) {
                isRightMoveAnimationStarted = false
                if (isRightMove) {
                    ivEyebrowRight?.startAnimation(taRightMove.animation())
                }
            }

            override fun onAnimationRepeat(animation: Animation) {}
        })
    }

    /**
     * 眨左眼/停止
     */
    fun doLeftBlink() {
        isLeftBlink = !isLeftBlink
        if (isLeftBlinkAnimationStarted) {
            return
        }
        context.runOnUiThread {
            ivEyeLeft?.startAnimation(saLeftBlink.animation())
        }
    }

    /**
     * 眨右眼/停止
     */
    fun doRightBlink() {
        isRightBlink = !isRightBlink
        if (isRightBlinkAnimationStarted) {
            return
        }
        context.runOnUiThread {
            ivEyeRight?.startAnimation(saRightBlink.animation())
        }
    }

    /**
     * 左撇嘴/停止
     */
    fun doLeftTilting() {
        isLeftTilting = !isLeftTilting
        if (isLeftTilting) {
            isRightTilting = false
        }
        if (isRightTiltingAnimationStarted) {
            return
        }
        if (isLeftTiltingAnimationStarted) {
            return
        }
        context.runOnUiThread {
            ivMouth?.startAnimation(raLeftTilting.animation())
        }
    }

    /**
     * 右撇嘴/停止
     */
    fun doRightTilting() {
        isRightTilting = !isRightTilting
        if (isRightTilting) {
            isLeftTilting = false
        }
        if (isLeftTiltingAnimationStarted) {
            return
        }
        if (isRightTiltingAnimationStarted) {
            return
        }
        context.runOnUiThread {
            ivMouth?.startAnimation(raRightTilting.animation())
        }
    }

    /**
     * 左眉毛移动/停止
     */
    fun doLeftMove() {
        isLeftMove = !isLeftMove
        if (isLeftMoveAnimationStarted) {
            return
        }
        context.runOnUiThread {
            ivEyebrowLeft?.startAnimation(taLeftMove.animation())
        }
    }

    /**
     * 右眉毛移动/停止
     */
    fun doRightMove() {
        isRightMove = !isRightMove
        if (isRightMoveAnimationStarted) {
            return
        }
        context.runOnUiThread {
            ivEyebrowRight?.startAnimation(taRightMove.animation())
        }
    }
}