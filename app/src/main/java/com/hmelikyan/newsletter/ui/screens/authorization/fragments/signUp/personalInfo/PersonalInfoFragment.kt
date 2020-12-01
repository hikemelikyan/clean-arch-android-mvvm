package com.hmelikyan.newsletter.ui.screens.authorization.fragments.signUp.personalInfo

import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.hmelikyan.newsletter.databinding.FragmentPersonalInfoBinding
import com.hmelikyan.newsletter.mvvm.ui.BaseRequestFragment
import com.hmelikyan.newsletter.mvvm.vm.ViewCommand
import com.hmelikyan.newsletter.ui.screens.authorization.fragments.signUp.emailVerification.EmailVerificationFragmentDirections

class PersonalInfoFragment: BaseRequestFragment<FragmentPersonalInfoBinding, PersonalInfoViewModel>() {
    override val inflate: (LayoutInflater, ViewGroup?, Boolean) -> FragmentPersonalInfoBinding
        get() = FragmentPersonalInfoBinding::inflate

    override val viewModelType: Class<PersonalInfoViewModel>
        get() = PersonalInfoViewModel::class.java

    override fun initView(binding: FragmentPersonalInfoBinding, viewModel: PersonalInfoViewModel) {
        sharedElementEnterTransition = TransitionInflater.from(requireContext()).inflateTransition(android.R.transition.move)
        binding.apply {
            tvNext.setOnClickListener {
                val extras = FragmentNavigatorExtras(
                    appIcon to appIcon.transitionName,
                    tvNext to tvNext.transitionName,
                    title to title.transitionName
                )
                findNavController().navigate(PersonalInfoFragmentDirections.actionPersonalInfoFragmentToCreatePasswordFragment(),extras)
            }
        }
    }

    override fun proceedViewCommand(command: ViewCommand) {

    }
}
