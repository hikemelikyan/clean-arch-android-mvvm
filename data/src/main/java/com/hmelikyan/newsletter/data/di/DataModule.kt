package com.hmelikyan.newsletter.data.di

import com.hmelikyan.newsletter.data.Data
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
class DataModule {

    @Provides
    fun provides():Data{
        return Data()
    }

}