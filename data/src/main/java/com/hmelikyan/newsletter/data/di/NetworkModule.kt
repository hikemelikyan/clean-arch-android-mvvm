package com.hmelikyan.newsletter.data.di

import com.hmelikyan.newsletter.root.BuildConfig
import com.hmelikyan.newsletter.root.common.AppConstants
import com.hmelikyan.newsletter.root.utils.SharedPreferencesHelper
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

@Qualifier
annotation class AuthInterceptor

@InstallIn(ApplicationComponent::class)
@Module
class NetworkModule {

    @Provides
    @AuthInterceptor
    fun providesAuthInterceptor(sharedPreferences: SharedPreferencesHelper):Interceptor{
        return Interceptor {
            val initialRequest = it.request()
            val request = initialRequest.newBuilder()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + sharedPreferences.readString(AppConstants.TOKEN))
                .header("deviceToken", sharedPreferences.readString(AppConstants.FIREBASE_TOKEN) ?: "")
                .header("deviceId", sharedPreferences.readString(AppConstants.DEVICE_ID) ?: "")
                .header("osType", "Android")
                .removeHeader("Pragma")
                .build()
            val response = it.proceed(request)
            response.cacheResponse()
            response
        }
    }

    @Provides
    fun providesOkHttpClient(@AuthInterceptor authInterceptor:Interceptor): OkHttpClient {
            return OkHttpClient.Builder()
                .addInterceptor(authInterceptor)
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .build()
    }


    @Provides
    @Singleton
    fun providesRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(client)
            .baseUrl(BuildConfig.BASEURL)
            .addConverterFactory(GsonConverterFactory.create())
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()
    }

}
