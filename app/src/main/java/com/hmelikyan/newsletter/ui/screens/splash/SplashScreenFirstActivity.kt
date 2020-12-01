package com.hmelikyan.newsletter.ui.screens.splash

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity

class SplashScreenFirstActivity:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        super.onCreate(savedInstanceState)
        startActivity(Intent(this,SplashScreenSecondActivity::class.java))
        finish()
    }
}