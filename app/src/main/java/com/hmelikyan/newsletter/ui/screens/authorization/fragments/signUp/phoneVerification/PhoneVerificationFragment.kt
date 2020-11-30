package com.hmelikyan.newsletter.ui.screens.authorization.fragments.signUp.phoneVerification

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
import com.hmelikyan.newsletter.databinding.FragmentPhoneVerificationBinding
import com.hmelikyan.newsletter.mvvm.ui.BaseRequestFragment
import com.hmelikyan.newsletter.mvvm.vm.ViewCommand
import com.hmelikyan.newsletter.root.shared.ext.show

class PhoneVerificationFragment : BaseRequestFragment<FragmentPhoneVerificationBinding, PhoneVerificationViewModel>() {

    private var isCodeSent = false

    override val inflate: (LayoutInflater, ViewGroup?, Boolean) -> FragmentPhoneVerificationBinding
        get() = FragmentPhoneVerificationBinding::inflate

    override val viewModelType: Class<PhoneVerificationViewModel>
        get() = PhoneVerificationViewModel::class.java

    override fun initView(binding: FragmentPhoneVerificationBinding, viewModel: PhoneVerificationViewModel) {
        sharedElementEnterTransition = TransitionInflater.from(requireContext()).inflateTransition(android.R.transition.move)
        binding.apply {
            tvSendCode.setOnClickListener {
                if(isCodeSent){
                    val extras = FragmentNavigatorExtras(
                        appIcon to appIcon.transitionName,
                        tvSendCode to tvSendCode.transitionName,
                        title to title.transitionName
                    )
                    findNavController().navigate(PhoneVerificationFragmentDirections.actionPhoneVerificationFragmentToEmailVerificationFragment(),extras)
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