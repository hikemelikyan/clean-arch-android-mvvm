package com.hmelikyan.newsletter.ui.screens.authorization.fragments.signIn

import com.hmelikyan.newsletter.data.root.UIState
import com.hmelikyan.newsletter.mvvm.vm.BaseViewModel

class SignInViewModel: BaseViewModel() {

    init {
        switchUIState(UIState.SUCCESS)
    }

}