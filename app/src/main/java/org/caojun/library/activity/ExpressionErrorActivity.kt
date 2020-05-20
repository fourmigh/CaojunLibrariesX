package org.caojun.library.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_expression_error.*
import org.caojun.library.R

class ExpressionErrorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_expression_error)

        btnLeftClockwise.setOnClickListener {
            errorExpression.doLeftRotating(true)
        }

        btnLeftAntiClockwise.setOnClickListener {
            errorExpression.doLeftRotating(false)
        }

        btnRightClockwise.setOnClickListener {
            errorExpression.doRightRotating(true)
        }

        btnRightAntiClockwise.setOnClickListener {
            errorExpression.doRightRotating(false)
        }

        btnLeftBlink.setOnClickListener {
            errorExpression.doLeftBlink()
        }

        btnRightBlink.setOnClickListener {
            errorExpression.doRightBlink()
        }
    }
}