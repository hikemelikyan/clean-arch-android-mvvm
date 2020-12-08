package com.armboldmind.gsport24.ui.screens.authorization.fragments.signUp.phoneVerification.di

import com.armboldmind.gsport24.domain.di.DomainModuleRepositoryBinds
import com.armboldmind.gsport24.domain.useCase.phoneVerification.PhoneVerificationUseCase
import com.armboldmind.gsport24.domain.useCase.phoneVerification.PhoneVerificationUseCaseImpl
import dagger.Binds
import dagger.Module

@Module(includes = [DomainModuleRepositoryBinds::class])
interface PhoneVerificationBinds {

    @Binds
    fun bindsPhoneVerificationUseCase(phoneVerificationUseCase : PhoneVerificationUseCaseImpl): PhoneVerificationUseCase
}