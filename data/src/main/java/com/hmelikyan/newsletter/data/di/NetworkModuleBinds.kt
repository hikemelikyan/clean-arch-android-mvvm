package com.hmelikyan.newsletter.data.di

import com.hmelikyan.newsletter.data.root.NetworkHelper
import com.hmelikyan.newsletter.data.root.NetworkHelperImpl
import com.hmelikyan.newsletter.root.BuildConfig
import com.hmelikyan.newsletter.root.shared.utils.SharedPreferencesHelper
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
interface NetworkModuleBinds {

    @Binds
    fun providesNetworkHelper(helperImpl: NetworkHelperImpl): NetworkHelper

}
