package com.armboldmind.gsport24.ui.screens.splash

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.lifecycle.lifecycleScope
import com.armboldmind.gsport24.databinding.ActivitySplashScreenBinding
import com.armboldmind.gsport24.ui.screens.authorization.AuthorizationActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashScreenSecondActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        super.onCreate(savedInstanceState)
        val mBinding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        lifecycleScope.launch {
            delay(2000)
            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this@SplashScreenSecondActivity,mBinding.appIcon,mBinding.appIcon.transitionName)
            startActivity(Intent(this@SplashScreenSecondActivity,AuthorizationActivity::class.java),options.toBundle())
            delay(1000)
            finishAfterTransition()
        }
    }
}