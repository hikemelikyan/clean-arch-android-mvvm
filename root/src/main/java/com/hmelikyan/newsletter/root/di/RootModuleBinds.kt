package com.hmelikyan.newsletter.root.di

import android.content.Context
import com.hmelikyan.newsletter.root.utils.NetworkConnectivityChecker
import com.hmelikyan.newsletter.root.utils.NetworkConnectivityCheckerImpl
import com.hmelikyan.newsletter.root.utils.SharedPreferencesHelper
import com.hmelikyan.newsletter.root.utils.SharedPreferencesHelperImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Qualifier

@Module(includes = [RootModule::class])
abstract class RootModuleBinds {

    @Binds
    abstract fun provideSharedPreferences(sharedPreferencesHelperImpl: SharedPreferencesHelperImpl): SharedPreferencesHelper

    @Binds
    abstract fun bindsNetworkConnectivityChecker(checkerImpl: NetworkConnectivityCheckerImpl): NetworkConnectivityChecker

}