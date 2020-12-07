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
import com.hmelikyan.newsletter.root.ext.show
import com.hmelikyan.newsletter.shared.ext.validationError
import com.hmelikyan.newsletter.ui.commands.Commands

class PhoneVerificationFragment : BaseRequestFragment<FragmentPhoneVerificationBinding, PhoneVerificationViewModel>() {

	override val inflate : (LayoutInflater, ViewGroup?, Boolean) -> FragmentPhoneVerificationBinding
		get() = FragmentPhoneVerificationBinding::inflate

	override val viewModelType : Class<PhoneVerificationViewModel>
		get() = PhoneVerificationViewModel::class.java

	override fun initView(binding : FragmentPhoneVerificationBinding, viewModel : PhoneVerificationViewModel) {
		sharedElementEnterTransition = TransitionInflater.from(requireContext()).inflateTransition(android.R.transition.move)
		binding.apply {
			setViewModel(viewModel)
			tvSendCode.setOnClickListener {
				if (mViewModel.codeId.value != null) {
					if (mViewModel.phoneCode.value.isNullOrEmpty()) {
						etPin.validationError { it.length >= 4 }
						tvSendCode.isLoading(false)
					} else {
						mViewModel.verifyPhoneNumber()
					}
				} else {
					if (mViewModel.phoneNumber.value.isNullOrEmpty()) {
						etPhone.validationError { it.length in 9..12 }
						tvSendCode.isLoading(false)
					} else {
						mViewModel.getSmsCode()
					}
				}
			}
		}
	}

	override fun proceedViewCommand(command : ViewCommand) {
		when (command) {
			is Commands.CodeSent -> showCodeEdit()
			is Commands.PhoneVerified -> navigateToEmailVerification(command.codeId)
		}
	}

	private fun showCodeEdit() {
		val set2 = ConstraintSet()
		set2.clone(mBinding.rootLayout.also {
			mBinding.etPin.show()
			mBinding.tvSendCode.setText(getString(R.string.next))
		})

		val transition : Transition = ChangeBounds()
		transition.interpolator = OvershootInterpolator()
		transition.duration = FrameMetricsAggregator.ANIMATION_DURATION.toLong()
		set2.applyTo(mBinding.rootLayout)
		TransitionManager.beginDelayedTransition(mBinding.rootLayout, transition)
	}

	private fun navigateToEmailVerification(codeId : Int) {
		mBinding.apply {
			val extras = FragmentNavigatorExtras(
				appIcon to appIcon.transitionName,
				tvSendCode to tvSendCode.transitionName,
				title to title.transitionName
			)
			findNavController().navigate(PhoneVerificationFragmentDirections.actionPhoneVerificationFragmentToEmailVerificationFragment(codeId), extras)
		}
	}
}