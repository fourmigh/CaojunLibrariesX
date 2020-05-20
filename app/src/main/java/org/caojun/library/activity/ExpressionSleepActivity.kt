package org.caojun.library.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_expression_sleep.*
import org.caojun.library.R

class ExpressionSleepActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_expression_sleep)

        btnLeft.setOnClickListener {
            sleepExpression.doLeftMove()
        }

        btnRight.setOnClickListener {
            sleepExpression.doRightMove()
        }

        btnMouth.setOnClickListener {
            sleepExpression.doMouthBlink()
        }
    }
}