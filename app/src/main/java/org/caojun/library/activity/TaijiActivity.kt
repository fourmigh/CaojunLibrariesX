package org.caojun.library.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_taiji.*
import org.caojun.library.R

class TaijiActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_taiji)

        tbClockwise.setOnCheckedChangeListener { buttonView, isChecked ->
            taijiView.isClockwise = isChecked
            if (isChecked) {
                tbClockwise.textOn = getString(R.string.clockwise_true)
            } else {
                tbClockwise.textOff = getString(R.string.clockwise_false)
            }
        }

        tbSpeed.setOnCheckedChangeListener { buttonView, isChecked ->
            taijiView.isLinear = !isChecked
            if (isChecked) {
                tbSpeed.textOn = getString(R.string.accelerate)
            } else {
                tbSpeed.textOff = getString(R.string.linear)
            }
        }
    }
}