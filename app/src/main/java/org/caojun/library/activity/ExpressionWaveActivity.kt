package org.caojun.library.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_expression_wave.*
import org.caojun.library.R
import org.jetbrains.anko.doAsync

class ExpressionWaveActivity : AppCompatActivity() {

    private var powerPercent = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_expression_wave)

        doAsync {

            while (!this@ExpressionWaveActivity.isFinishing) {
                waveExpression.setProgress(powerPercent)

                powerPercent++
                if (powerPercent > 100) {
                    powerPercent = 0
                }
                Thread.sleep(100)
            }
        }
    }
}