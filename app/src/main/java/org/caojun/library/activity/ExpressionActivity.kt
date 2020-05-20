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
            blinkExpression.doLeftBlink()
            blinkExpression.doLeftTilting()
            blinkExpression.doLeftMove()
        }

        btnRight.setOnClickListener {
            blinkExpression.doRightBlink()
            blinkExpression.doRightTilting()
            blinkExpression.doRightMove()
        }

        btnBlinkLeft.setOnClickListener {
            blinkExpression.doLeftBlink()
        }

        btnBlinkRight.setOnClickListener {
            blinkExpression.doRightBlink()
        }

        btnMouthLeft.setOnClickListener {
            blinkExpression.doLeftTilting()
        }

        btnMouthRight.setOnClickListener {
            blinkExpression.doRightTilting()
        }

        btnEyebrowLeft.setOnClickListener {
            blinkExpression.doLeftMove()
        }

        btnEyebrowRight.setOnClickListener {
            blinkExpression.doRightMove()
        }
    }
}