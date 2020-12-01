package com.hmelikyan.newsletter.ui.screens.authorization.fragments.signUp.emailVerification

import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.OvershootInterpolator
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.app.FrameMetricsAggregator
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.transition.ChangeBounds
import androidx.transition.Transition
import androidx.transition.TransitionManager
import com.hmelikyan.newsletter.R
import com.hmelikyan.newsletter.databinding.FragmentEmailVerificationBinding
import com.hmelikyan.newsletter.mvvm.ui.BaseRequestFragment
import com.hmelikyan.newsletter.mvvm.vm.ViewCommand
import com.hmelikyan.newsletter.root.shared.ext.show
import com.hmelikyan.newsletter.ui.screens.authorization.fragments.signUp.phoneVerification.PhoneVerificationFragmentDirections

class EmailVerificationFragment: BaseRequestFragment<FragmentEmailVerificationBinding, EmailVerificationViewModel>() {
    private var isCodeSent = false

    override val inflate: (LayoutInflater, ViewGroup?, Boolean) -> FragmentEmailVerificationBinding
        get() = FragmentEmailVerificationBinding::inflate

    override val viewModelType: Class<EmailVerificationViewModel>
        get() = EmailVerificationViewModel::class.java

    override fun initView(binding: FragmentEmailVerificationBinding, viewModel: EmailVerificationViewModel) {
        sharedElementEnterTransition = TransitionInflater.from(requireContext()).inflateTransition(android.R.transition.move)
        binding.apply {
            tvSendCode.setOnClickListener {
                if(isCodeSent){
                    val extras = FragmentNavigatorExtras(
                        appIcon to appIcon.transitionName,
//                        tvSendCode to tvSendCode.transitionName,
                        title to title.transitionName
                    )
                    findNavController().navigate(EmailVerificationFragmentDirections.actionEmailVerificationFragmentToPersonalInfoFragment(),extras)
                }else{
                    isCodeSent = !isCodeSent
                    showCodeEdit()
                    changeSendCodeTitle()
                }
            }
        }
    }

    private fun showCodeEdit() {
        val set2 = ConstraintSet()
        set2.clone(mBinding.rootLayout.also { mBinding.etPin.show() })

        val transition: Transition = ChangeBounds()
        transition.interpolator = OvershootInterpolator()
        transition.duration = FrameMetricsAggregator.ANIMATION_DURATION.toLong()
        set2.applyTo(mBinding.rootLayout)
        TransitionManager.beginDelayedTransition(mBinding.rootLayout, transition)
    }

    private fun changeSendCodeTitle() {
        mBinding.tvSendCode.text = getString(R.string.next)
    }

    override fun proceedViewCommand(command: ViewCommand) {

    }
}