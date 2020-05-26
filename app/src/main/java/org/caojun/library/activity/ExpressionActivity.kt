package org.caojun.library.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_expression.*
import org.caojun.library.R

class ExpressionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_expression)

        btnLeft.setOnClickListener {
            blinkExpression.blinkLeftEye()
            blinkExpression.tiltMouthLeft()
            blinkExpression.moveLeftEyebrow()
        }

        btnRight.setOnClickListener {
            blinkExpression.blinkRightEye()
            blinkExpression.tiltMouthRight()
            blinkExpression.moveRightEyebrow()
        }

        btnBlinkLeft.setOnClickListener {
            blinkExpression.blinkLeftEye()
        }

        btnBlinkRight.setOnClickListener {
            blinkExpression.blinkRightEye()
        }

        btnMouthLeft.setOnClickListener {
            blinkExpression.tiltMouthLeft()
        }

        btnMouthRight.setOnClickListener {
            blinkExpression.tiltMouthRight()
        }

        btnEyebrowLeft.setOnClickListener {
            blinkExpression.moveLeftEyebrow()
        }

        btnEyebrowRight.setOnClickListener {
            blinkExpression.moveRightEyebrow()
        }
    }
}