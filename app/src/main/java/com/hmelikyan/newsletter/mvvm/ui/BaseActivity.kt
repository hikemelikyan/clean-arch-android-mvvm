package com.hmelikyan.newsletter.mvvm.ui

import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.hmelikyan.newsletter.R
import com.hmelikyan.newsletter.mvvm.vm.BaseViewModel
import com.hmelikyan.newsletter.mvvm.vm.ViewCommand
import com.hmelikyan.newsletter.root.shared.ext.getColorCompat
import com.hmelikyan.newsletter.ui.commands.Commands

abstract class BaseActivity: AppCompatActivity(), IBaseView {

    override fun showServerError(message: String) {
        showServerErrorSnackBar(message)
    }

    override fun showServerError(resId: Int) {
        showServerError(resources.getString(resId))
    }

    override fun showNetworkErrorSnackBar(message: String) {
        showNetworkSnackBar(message)
    }

    override fun showNetworkErrorSnackBar(resId: Int) {
        showNetworkErrorSnackBar(resources.getString(resId))
    }

    private fun showNetworkSnackBar(message: String) {
        Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_INDEFINITE)
            .setTextColor(getColorCompat(android.R.color.white))
            .setBackgroundTint(getColorCompat(android.R.color.black))
            .setActionTextColor(getColorCompat(R.color.colorAccent))
            .setAction("Ok") {
                onRetryRequest()
            }.show()
    }

    private fun showServerErrorSnackBar(message: String) {
        Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_SHORT)
            .setTextColor(getColorCompat(android.R.color.white))
            .setBackgroundTint(getColorCompat(android.R.color.black))
            .show()
    }

    override fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun showToast(resId: Int) {
        showToast(resources.getString(resId))
    }

    open fun onRetryRequest(){}

    fun hasPermission(permission: String): Boolean {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.M || checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED
    }

}