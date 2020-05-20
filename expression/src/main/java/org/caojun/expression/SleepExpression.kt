package org.caojun.expression

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import android.view.animation.TranslateAnimation
import android.widget.ImageView
import android.widget.RelativeLayout
import org.caojun.utils.ResourcesUtils
import org.jetbrains.anko.runOnUiThread

/**
 * 瞌睡表情动画，使用变形、位移
 */
class SleepExpression : RelativeLayout {

    companion object {
        const val DURATION = 1500L
        const val REPEAT_COUNT = 1
    }

    private var ivEyebrowLeft: ImageView? = null
    private var ivEyebrowRight: ImageView? = null
    private var ivEyeLeft: ImageView? = null
    private var ivEyeRight: ImageView? = null
    private var ivMouth: ImageView? = null

    //眼皮上下位移
    private var isLeftMove = false
    private var isRightMove = false
    private var isLeftMoveAnimationStarted = false
    private var isRightMoveAnimationStarted = false
    private val taLeftMove =
        TranslateAnimation(
            Animation.RELATIVE_TO_PARENT,
            0F,
            Animation.RELATIVE_TO_PARENT,
            0F,
            Animation.RELATIVE_TO_PARENT,
            0F,
            Animation.RELATIVE_TO_PARENT,
            ResourcesUtils.getDimension(context, R.dimen.sleep_y))
    private val taRightMove =
        TranslateAnimation(
            Animation.RELATIVE_TO_PARENT,
            0F,
            Animation.RELATIVE_TO_PARENT,
            0F,
            Animation.RELATIVE_TO_PARENT,
            0F,
            Animation.RELATIVE_TO_PARENT,
            ResourcesUtils.getDimension(context, R.dimen.sleep_y))

    //闭嘴
    private var isMouthBlink = false
    private var isMouthBlinkAnimationStarted = false
    private val saMouthBlink =
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
            .inflate(R.layout.view_expression_sleep, this, true)

        ivEyebrowLeft = findViewById(R.id.ivEyebrowLeft)
        ivEyebrowRight = findViewById(R.id.ivEyebrowRight)
        ivEyeLeft = findViewById(R.id.ivEyeLeft)
        ivEyeRight = findViewById(R.id.ivEyeRight)
        ivMouth = findViewById(R.id.ivMouth)

        //闭嘴
        saMouthBlink.duration = DURATION
        saMouthBlink.repeatCount = REPEAT_COUNT
        saMouthBlink.repeatMode = Animation.REVERSE
        saMouthBlink.setAnimationListener(object :
            Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {
                isMouthBlinkAnimationStarted = true
            }

            override fun onAnimationEnd(animation: Animation) {
                isMouthBlinkAnimationStarted = false
                if (isMouthBlink) {
                    ivMouth?.startAnimation(saMouthBlink)
                }
            }

            override fun onAnimationRepeat(animation: Animation) {}
        })

        //眼皮上下位移
        taLeftMove.duration = DURATION
        taLeftMove.repeatCount = REPEAT_COUNT
        taLeftMove.repeatMode = Animation.REVERSE
        taRightMove.duration = DURATION
        taRightMove.repeatCount = REPEAT_COUNT
        taRightMove.repeatMode = Animation.REVERSE
        taLeftMove.setAnimationListener(object :
            Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {
                isLeftMoveAnimationStarted = true
            }

            override fun onAnimationEnd(animation: Animation) {
                isLeftMoveAnimationStarted = false
                if (isLeftMove) {
                    ivEyeLeft?.startAnimation(taLeftMove)
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
                    ivEyeRight?.startAnimation(taRightMove)
                }
            }

            override fun onAnimationRepeat(animation: Animation) {}
        })
    }

    /**
     * 闭嘴动画/停止
     */
    fun doMouthBlink() {
        isMouthBlink = !isMouthBlink
        if (isMouthBlinkAnimationStarted) {
            return
        }
        context.runOnUiThread {
            ivMouth?.startAnimation(saMouthBlink)
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
            ivEyeLeft?.startAnimation(taLeftMove)
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
            ivEyeRight?.startAnimation(taRightMove)
        }
    }
}