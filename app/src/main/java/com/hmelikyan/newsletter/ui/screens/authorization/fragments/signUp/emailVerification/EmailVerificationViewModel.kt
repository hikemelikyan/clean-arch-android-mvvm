package com.hmelikyan.newsletter.ui.screens.authorization.fragments.signUp.emailVerification

import com.hmelikyan.newsletter.data.root.UIState
import com.hmelikyan.newsletter.mvvm.vm.BaseViewModel

class EmailVerificationViewModel: BaseViewModel() {

    init {
        switchUIState(UIState.SUCCESS)
    }

}