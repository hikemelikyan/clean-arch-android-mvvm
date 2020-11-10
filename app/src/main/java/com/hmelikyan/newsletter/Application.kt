package com.hmelikyan.newsletter

import android.app.Application
import com.hmelikyan.newsletter.di.DaggerAppComponent
import com.hmelikyan.newsletter.root.di.DeviceID
import com.hmelikyan.newsletter.root.shared.utils.SharedPreferencesHelper
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.internal.modules.ApplicationContextModule
import javax.inject.Inject

@HiltAndroidApp
class Application : Application() {

    companion object {
        private lateinit var mInstance: com.hmelikyan.newsletter.Application

        fun getInstance() = mInstance
    }

    @Inject
    lateinit var mShared: SharedPreferencesHelper

    @Inject
    @DeviceID
    lateinit var deviceId: String

    override fun onCreate() {
        super.onCreate()
        mInstance = this
        DaggerAppComponent.builder()
            .withAppModule(ApplicationContextModule(applicationContext))
            .build()
            .inject(this)

        mShared.saveDeviceId(deviceId)
        mShared.saveAuthToken("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIzMDgwIiwianRpIjoiZjJmNDRlOGQtNWRkNy00ZjM2LWE1MjEtZjYwY2UwYzdmYjVlIiwiaWF0IjoxNjA0NTYzNTU4LCJwZXJzb25JZCI6IjMwODAiLCJyb2xlIjoiVXNlciIsIm5iZiI6MTYwNDU2MzU1OCwiZXhwIjoxNjA1MTY4MzU4LCJpc3MiOiJCYWdnbGUuQXBpIiwiYXVkIjoiQmFnZ2xlLlVzZXIifQ.0c0uej4Fx_BqFG8QTBdlzoxBiFQgpgjzs8OwTUjB62I")
    }
}