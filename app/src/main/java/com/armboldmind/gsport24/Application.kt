package com.armboldmind.gsport24

import android.app.Application
import com.armboldmind.gsport24.data.di.NetworkModule
import com.armboldmind.gsport24.di.AppComponent
import com.armboldmind.gsport24.di.DaggerAppComponent
import com.armboldmind.gsport24.root.di.DeviceID
import com.armboldmind.gsport24.root.di.RootModule
import com.armboldmind.gsport24.root.utils.SharedPreferencesHelper
import javax.inject.Inject

class Application : Application() {

    companion object {
        private lateinit var mInstance: com.armboldmind.gsport24.Application

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