package com.hmelikyan.newsletter.domain.di

import com.hmelikyan.newsletter.data.di.NetworkModule
import com.hmelikyan.newsletter.data.di.NetworkModuleBinds
import com.hmelikyan.newsletter.domain.repository.NotificationsRepository
import com.hmelikyan.newsletter.domain.repository.NotificationsRepositoryImpl
import dagger.Binds
import dagger.Module

@Module(includes = [NetworkModule::class,NetworkModuleBinds::class])
abstract class DomainModuleRepositoryBinds {

    @Binds
    abstract fun bindsNotificationsRepository(notificationsRepositoryImpl: NotificationsRepositoryImpl): NotificationsRepository

}