package com.example.adoptme

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper


class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_activity)

        val handler = Handler(Looper.getMainLooper())
        Handler().postDelayed({
              val intent = Intent(this,LoginActivity::class.java)
              startActivity(intent)
              finish()

        },3000)


    }
}