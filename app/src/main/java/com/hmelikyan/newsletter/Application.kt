package com.hmelikyan.newsletter

import android.app.Application
import com.hmelikyan.newsletter.data.di.NetworkModule
import com.hmelikyan.newsletter.di.AppComponent
import com.hmelikyan.newsletter.di.DaggerAppComponent
import com.hmelikyan.newsletter.root.di.DeviceID
import com.hmelikyan.newsletter.root.di.RootModule
import com.hmelikyan.newsletter.root.shared.utils.SharedPreferencesHelper
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
        mShared.saveAuthToken("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIzMDc4IiwianRpIjoiM2UyNzdkM2QtNDlkMS00NDRhLWJjMzYtYTI2YWU4ZDFhNDEyIiwiaWF0IjoxNjA2Mzg2NjY2LCJwZXJzb25JZCI6IjMwNzgiLCJyb2xlIjoiVXNlciIsIm5iZiI6MTYwNjM4NjY2NiwiZXhwIjoxNjA2OTkxNDY2LCJpc3MiOiJCYWdnbGUuQXBpIiwiYXVkIjoiQmFnZ2xlLlVzZXIifQ.w6sQZbzuRfd9z85OWV2RC5q2ZRI2FYjwL1qrnSfdct4")
    }

    fun getAppComponent(): AppComponent {
        return appComponent
    }
}