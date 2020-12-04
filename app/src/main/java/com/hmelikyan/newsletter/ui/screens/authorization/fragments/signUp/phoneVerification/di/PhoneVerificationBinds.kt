package com.hmelikyan.newsletter.ui.screens.authorization.fragments.signUp.phoneVerification.di

import com.hmelikyan.newsletter.domain.di.DomainModuleRepositoryBinds
import com.hmelikyan.newsletter.domain.useCase.phoneVerification.PhoneVerificationUseCase
import com.hmelikyan.newsletter.domain.useCase.phoneVerification.PhoneVerificationUseCaseImpl
import dagger.Binds
import dagger.Module

@Module(includes = [DomainModuleRepositoryBinds::class])
interface PhoneVerificationBinds {

    @Binds
    fun bindsPhoneVerificationUseCase(phoneVerificationUseCase : PhoneVerificationUseCaseImpl): PhoneVerificationUseCase
}