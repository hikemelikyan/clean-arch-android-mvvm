package com.hmelikyan.newsletter.ui.screens.authorization.fragments.signUp.phoneVerification.di

import com.hmelikyan.newsletter.ui.screens.authorization.fragments.signUp.phoneVerification.PhoneVerificationViewModel
import dagger.Component

@Component(modules = [PhoneVerificationBinds::class])
interface PhoneVerificationComponent {

    fun inject(target: PhoneVerificationViewModel)

}
