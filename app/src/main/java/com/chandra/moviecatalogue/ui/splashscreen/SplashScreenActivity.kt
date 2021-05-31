package com.chandra.moviecatalogue.ui.splashscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.chandra.moviecatalogue.R
import com.chandra.moviecatalogue.ui.home.MainActivity

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        Handler(mainLooper).postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }, EXTRA_TIME)
    }

    companion object {
        private const val EXTRA_TIME:Long = 1500
    }
}