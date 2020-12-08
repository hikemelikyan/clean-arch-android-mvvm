package com.armboldmind.gsport24.ui.screens.authorization.fragments.signIn

import com.armboldmind.gsport24.data.root.UIState
import com.armboldmind.gsport24.mvvm.vm.BaseViewModel

class SignInViewModel: BaseViewModel() {

    init {
        switchUIState(UIState.SUCCESS)
    }

}