package com.hmelikyan.newsletter.root.shared.utils

import android.content.Context
import android.net.ConnectivityManager
import javax.inject.Inject

class NetworkConnectivityCheckerImpl
@Inject
constructor(context: Context) : NetworkConnectivityChecker {

    private val connectivityManager: ConnectivityManager by lazy {
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }

    override val isNetworkAvailable: Boolean
        get() = connectivityManager.activeNetworkInfo?.isConnected == true

}