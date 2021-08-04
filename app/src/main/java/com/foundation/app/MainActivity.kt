package com.foundation.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<TextView>(R.id.tv_show).setOnClickListener {
            val dialog = ChangeFloorDialog(this@MainActivity)
            dialog.setCanceledOnTouchOutside(false)
            dialog.show()
        }

    }

}