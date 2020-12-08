package com.armboldmind.gsport24.domain.di

import com.armboldmind.gsport24.data.di.NetworkModule
import com.armboldmind.gsport24.data.di.NetworkModuleBinds
import com.armboldmind.gsport24.data.repository.AuthRepository
import com.armboldmind.gsport24.data.repository.AuthRepositoryImpl
import dagger.Binds
import dagger.Module

@Module(includes = [NetworkModule::class,NetworkModuleBinds::class])
abstract class DomainModuleRepositoryBinds {

    @Binds
    abstract fun bindsNotificationsRepository(authRepositoryImpl : AuthRepositoryImpl): AuthRepository

}