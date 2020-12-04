package com.hmelikyan.newsletter.ui.screens.authorization.fragments.signUp.emailVerification.di

import com.hmelikyan.newsletter.ui.screens.authorization.fragments.signUp.emailVerification.EmailVerificationViewModel
import dagger.Component

@Component(modules = [EmailVerificationBinds::class])
interface EmailVerificationComponent {

	fun inject(viewModel : EmailVerificationViewModel)
}