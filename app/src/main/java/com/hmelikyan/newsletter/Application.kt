package com.hmelikyan.newsletter

import android.app.Application
import com.hmelikyan.newsletter.data.di.NetworkModule
import com.hmelikyan.newsletter.di.AppComponent
import com.hmelikyan.newsletter.di.DaggerAppComponent
import com.hmelikyan.newsletter.root.di.DeviceID
import com.hmelikyan.newsletter.root.di.RootModule
import com.hmelikyan.newsletter.root.utils.SharedPreferencesHelper
import javax.inject.Inject

class Application : Application() {

    companion object {
        private lateinit var mInstance: com.hmelikyan.newsletter.Application

        fun getInstance() = mInstance
    }

    private lateinit var appComponent: AppComponent

    @Inject
    lateinit var mShared: SharedPreferencesHelper

    @Inject
    @DeviceID
    lateinit var deviceId: String

    override fun onCreate() {
        super.onCreate()
        mInstance = this
        appComponent = DaggerAppComponent.builder()
            .withRootModule(RootModule(this))
            .withNetworkModule(NetworkModule())
            .build()

        appComponent.inject(this)

        mShared.saveDeviceId(deviceId)
 }

    fun getAppComponent(): AppComponent {
        return appComponent
    }
}