package com.hmelikyan.newsletter.domain.di

import com.hmelikyan.newsletter.domain.repository.NotificationsRepository
import com.hmelikyan.newsletter.domain.repository.NotificationsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
abstract class DomainModule {

    @Binds
    abstract fun bindsNotificationsRepository(notificationsRepository: NotificationsRepositoryImpl): NotificationsRepository

}