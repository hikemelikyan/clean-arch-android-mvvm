package com.hmelikyan.newsletter.root.di

import android.annotation.SuppressLint
import android.content.Context
import android.provider.Settings
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
class RootModule {

    @SuppressLint("HardwareIds")
    @Provides
    @DeviceID
    fun providesDeviceId(context: Context): String {
        return Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
    }
}