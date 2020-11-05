package com.hmelikyan.newsletter.domain.di

import com.hmelikyan.newsletter.domain.data.AuthRepository
import com.hmelikyan.newsletter.domain.data.AuthRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
abstract class DomainModule {

    @Binds
    abstract fun bindsAuthRepository(authRepository:AuthRepositoryImpl):AuthRepository

}