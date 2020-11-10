package com.hmelikyan.newsletter.root.shared.ext

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.hmelikyan.newsletter.root.R


fun <T : AppCompatActivity> Fragment.launch(extras: Bundle? = null, block: () -> Class<T>) {
    this.requireActivity().startActivity(Intent(this.requireActivity(), block()).apply {
        extras?.let { putExtras(it) }
    })
}

fun <T : AppCompatActivity> AppCompatActivity.launch(extras: Bundle? = null, block: () -> Class<T>) {
    startActivity(Intent(this, block()).apply {
        extras?.let { putExtras(it) }
    })
}

fun AppCompatActivity.setLightStatusBarWithFullScreen() {
    window.apply {
        decorView.systemUiVisibility = if (Build.VERSION.SDK_INT > 27) {
            navigationBarColor = ContextCompat.getColor(this@setLightStatusBarWithFullScreen, android.R.color.white)
            navigationBarDividerColor = ContextCompat.getColor(this@setLightStatusBarWithFullScreen, R.color.divider_color)
            statusBarColor = ContextCompat.getColor(this@setLightStatusBarWithFullScreen, android.R.color.transparent)
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR or View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
        } else {
            navigationBarColor = ContextCompat.getColor(this@setLightStatusBarWithFullScreen, android.R.color.black)
            if (Build.VERSION.SDK_INT > 22) {
                statusBarColor = ContextCompat.getColor(this@setLightStatusBarWithFullScreen, android.R.color.transparent)
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            } else {
                statusBarColor = ContextCompat.getColor(this@setLightStatusBarWithFullScreen, android.R.color.black)
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            }
        }
    }
}

fun AppCompatActivity.setLightStatusBar() {
    window.apply {
        if (Build.VERSION.SDK_INT > 22) {
            decorView.systemUiVisibility = if (Build.VERSION.SDK_INT > 27) {
                navigationBarColor = ContextCompat.getColor(this@setLightStatusBar, android.R.color.white)
                navigationBarDividerColor = ContextCompat.getColor(this@setLightStatusBar, R.color.divider_color)
                View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR or View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
            } else {
                navigationBarColor = ContextCompat.getColor(this@setLightStatusBar, android.R.color.black)
                View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            }
            statusBarColor = ContextCompat.getColor(this@setLightStatusBar, android.R.color.white)
        } else {
            statusBarColor = ContextCompat.getColor(this@setLightStatusBar, android.R.color.black)
        }
    }
}

fun AppCompatActivity.setDarkStatusBar() {
    window.apply {
        if (Build.VERSION.SDK_INT > 22) {
            decorView.systemUiVisibility = if (Build.VERSION.SDK_INT > 27) {
                navigationBarColor = ContextCompat.getColor(this@setDarkStatusBar, android.R.color.white)
                navigationBarDividerColor = ContextCompat.getColor(this@setDarkStatusBar, R.color.divider_color)
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
            } else {
                navigationBarColor = ContextCompat.getColor(this@setDarkStatusBar, android.R.color.black)
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            }
            statusBarColor = ContextCompat.getColor(this@setDarkStatusBar, android.R.color.transparent)
        } else {
            statusBarColor = ContextCompat.getColor(this@setDarkStatusBar, android.R.color.black)
        }
    }
}

fun AppCompatActivity.clearLightStatusBar() {
    window?.apply {
        if (Build.VERSION.SDK_INT > 22) {
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            statusBarColor = ContextCompat.getColor(this@clearLightStatusBar, android.R.color.transparent)
        } else {
            statusBarColor = ContextCompat.getColor(this@clearLightStatusBar, android.R.color.black)
        }
    }
}
