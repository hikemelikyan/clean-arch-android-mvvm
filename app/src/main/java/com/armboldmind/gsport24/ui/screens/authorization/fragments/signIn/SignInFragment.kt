package com.armboldmind.gsport24.ui.screens.authorization.fragments.signIn

import android.content.Intent
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.ViewGroup
import com.armboldmind.gsport24.databinding.FragmentSignInBinding
import com.armboldmind.gsport24.mvvm.ui.BaseRequestFragment
import com.armboldmind.gsport24.mvvm.vm.ViewCommand
import com.armboldmind.gsport24.ui.screens.testActivity.TestActivity

class SignInFragment: BaseRequestFragment<FragmentSignInBinding, SignInViewModel>() {

    override val inflate: (LayoutInflater, ViewGroup?, Boolean) -> FragmentSignInBinding
        get() = FragmentSignInBinding::inflate

    override val viewModelType: Class<SignInViewModel>
        get() = SignInViewModel::class.java

    override fun initView(binding: FragmentSignInBinding, viewModel: SignInViewModel) {
        sharedElementEnterTransition = TransitionInflater.from(requireContext()).inflateTransition(android.R.transition.move)
        binding.apply {
            tvSignIn.setOnClickListener { startActivity(Intent(requireActivity(), TestActivity::class.java)) }
        }
    }

    override fun proceedViewCommand(command: ViewCommand) {

    }

}