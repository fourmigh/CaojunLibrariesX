package org.caojun.library.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_press_size.*
import org.caojun.library.R

class PressSizeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_press_size)

        button.setOnClickListener {
            Log.d("PressSizeActivity", "button.setOnClickListener")
        }

        checkBox.setOnCheckedChangeListener { buttonView, isChecked ->
            Log.d("PressSizeActivity", "checkBox.setOnCheckedChangeListener: $isChecked")
        }

        checkbox.setOnCheckedChangeListener { buttonView, isChecked ->
            Log.d("PressSizeActivity", "checkbox.setOnCheckedChangeListener: $isChecked")
        }
    }
}