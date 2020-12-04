package com.hmelikyan.newsletter.domain.di

import com.hmelikyan.newsletter.data.di.NetworkModule
import com.hmelikyan.newsletter.data.di.NetworkModuleBinds
import com.hmelikyan.newsletter.data.repository.AuthRepository
import com.hmelikyan.newsletter.data.repository.AuthRepositoryImpl
import dagger.Binds
import dagger.Module

@Module(includes = [NetworkModule::class,NetworkModuleBinds::class])
abstract class DomainModuleRepositoryBinds {

    @Binds
    abstract fun bindsNotificationsRepository(authRepositoryImpl : AuthRepositoryImpl): AuthRepository

}