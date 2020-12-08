package com.armboldmind.gsport24.ui.screens.authorization.fragments.signUp.phoneVerification.di

import com.armboldmind.gsport24.ui.screens.authorization.fragments.signUp.phoneVerification.PhoneVerificationViewModel
import dagger.Component

@Component(modules = [PhoneVerificationBinds::class])
interface PhoneVerificationComponent {

    fun inject(target: PhoneVerificationViewModel)

}
