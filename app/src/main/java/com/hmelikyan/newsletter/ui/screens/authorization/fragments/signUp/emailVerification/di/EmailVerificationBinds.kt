package com.hmelikyan.newsletter.ui.screens.authorization.fragments.signUp.emailVerification.di

import com.hmelikyan.newsletter.domain.di.DomainModuleRepositoryBinds
import com.hmelikyan.newsletter.domain.useCase.emailVerification.EmailVerificationUseCase
import com.hmelikyan.newsletter.domain.useCase.emailVerification.EmailVerificationUseCaseImpl
import dagger.Binds
import dagger.Module

@Module(includes = [DomainModuleRepositoryBinds::class])
interface EmailVerificationBinds {

	@Binds
	fun bindsEmailVerificationUseCase(emailVerificationUseCase : EmailVerificationUseCaseImpl): EmailVerificationUseCase
}