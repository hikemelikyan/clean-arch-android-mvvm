package com.hmelikyan.newsletter.root.di

import android.annotation.SuppressLint
import android.content.Context
import android.provider.Settings
import com.hmelikyan.newsletter.root.shared.utils.NetworkConnectivityChecker
import com.hmelikyan.newsletter.root.shared.utils.NetworkConnectivityCheckerImpl
import com.hmelikyan.newsletter.root.shared.utils.SharedPreferencesHelper
import com.hmelikyan.newsletter.root.shared.utils.SharedPreferencesHelperImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Qualifier

@Qualifier
annotation class DeviceID

@Module
@InstallIn(ApplicationComponent::class)
abstract class RootModuleBinds {

    @Binds
    abstract fun provideSharedPreferences(sharedPreferencesHelperImpl: SharedPreferencesHelperImpl): SharedPreferencesHelper

    @Binds
    abstract fun bindsNetworkConnectivityChecker(checkerImpl: NetworkConnectivityCheckerImpl): NetworkConnectivityChecker
}