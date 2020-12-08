package com.armboldmind.gsport24.ui.screens.authorization.fragments.signUp.emailVerification.di

import com.armboldmind.gsport24.ui.screens.authorization.fragments.signUp.emailVerification.EmailVerificationViewModel
import dagger.Component

@Component(modules = [EmailVerificationBinds::class])
interface EmailVerificationComponent {

	fun inject(viewModel : EmailVerificationViewModel)
}