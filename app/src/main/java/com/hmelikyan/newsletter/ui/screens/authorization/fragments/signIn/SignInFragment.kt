package com.hmelikyan.newsletter.ui.screens.authorization.fragments.signIn

import android.content.Intent
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.ViewGroup
import com.hmelikyan.newsletter.databinding.FragmentSignInBinding
import com.hmelikyan.newsletter.mvvm.ui.BaseRequestFragment
import com.hmelikyan.newsletter.mvvm.vm.ViewCommand
import com.hmelikyan.newsletter.ui.screens.testActivity.TestActivity

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