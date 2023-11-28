package com.example.gym


import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity

class SplashScreenActivity : AppCompatActivity() {
    private val splashDelay: Long = 2500

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Handler().postDelayed({
            // Create an Intent to start the new activity
            val intent = Intent(this, LoginScreenActivity::class.java)
            startActivity(intent)
            finish()
        }, splashDelay)
    }
}