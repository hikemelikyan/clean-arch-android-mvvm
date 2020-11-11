package com.hmelikyan.newsletter.domain.di

import com.hmelikyan.newsletter.domain.useCase.GetNotificationsListUseCase
import com.hmelikyan.newsletter.domain.useCase.GetNotificationsListUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
interface DomainModuleBinds {

    @Binds
    fun bindsNotificationListUseCase(useCaseImpl: GetNotificationsListUseCaseImpl): GetNotificationsListUseCase

}