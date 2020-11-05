package com.hmelikyan.newsletter.root.di

import com.hmelikyan.newsletter.root.utils.SharedPreferencesHelper
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent


@InstallIn(ApplicationComponent::class)
@Module
interface AppModule {

    @Binds
    abstract fun provideSharedPreferences(mShared: SharedPreferencesHelper):SharedPreferencesHelper

}