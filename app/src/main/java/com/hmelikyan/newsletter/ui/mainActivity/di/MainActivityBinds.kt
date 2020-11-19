package com.hmelikyan.newsletter.ui.mainActivity.di

import com.hmelikyan.newsletter.domain.di.DomainModuleRepositoryBinds
import com.hmelikyan.newsletter.domain.useCase.GetNotificationsListUseCase
import com.hmelikyan.newsletter.domain.useCase.GetNotificationsListUseCaseImpl
import dagger.Binds
import dagger.Module

@Module(includes = [DomainModuleRepositoryBinds::class])
interface MainActivityBinds {

    @Binds
    fun bindsGetNotificationListUseCase(getNotificationsListUseCaseImpl: GetNotificationsListUseCaseImpl): GetNotificationsListUseCase
}