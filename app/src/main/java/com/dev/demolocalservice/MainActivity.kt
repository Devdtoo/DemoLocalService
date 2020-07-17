package com.dev.demolocalservice

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startServiceButton.setOnClickListener {
            val intent = Intent(this, MyService::class.java)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                startForegroundService(Intent(this, MyService::class.java))
                startForegroundService(intent)
            }
            startService(intent)
        }
    }
}