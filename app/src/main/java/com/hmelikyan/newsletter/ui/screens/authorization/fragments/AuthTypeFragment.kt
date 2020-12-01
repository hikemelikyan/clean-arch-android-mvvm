package com.hmelikyan.newsletter.ui.screens.authorization.fragments

import android.Manifest
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.hmelikyan.newsletter.databinding.FragmentAuthTypeBinding
import com.hmelikyan.newsletter.mvvm.ui.BaseFragment
import com.hmelikyan.newsletter.shared.util.permissionChecker

class AuthTypeFragment : BaseFragment<FragmentAuthTypeBinding>() {

	override val inflate : (LayoutInflater, ViewGroup?, Boolean) -> FragmentAuthTypeBinding
		get() = FragmentAuthTypeBinding::inflate

	override fun initView(binding : FragmentAuthTypeBinding) {
		binding.tvSignIn.setOnClickListener {
			val extra = FragmentNavigatorExtras(
                binding.appIcon to binding.appIcon.transitionName,
                binding.tvSignIn to binding.tvSignIn.transitionName
            )
			findNavController().navigate(AuthTypeFragmentDirections.actionAuthTypeFragmentToSignInFragment(), extra)
		}
		binding.tvSignUp.setOnClickListener {

			permissionChecker {
				withFragment(this@AuthTypeFragment)

				permissions(Manifest.permission.CAMERA)

				check {
					doOnSuccess {
						showToast("Permission granted.")
					}
					doOnFailure {
						showToast("Permission denied.")
					}
					doOnNeverAsk {
						showToast("Permission denied with never ask again")
					}
				}
			}

//            val extra = FragmentNavigatorExtras(
//                binding.appIcon to binding.appIcon.transitionName
//            )
//            findNavController().navigate(AuthTypeFragmentDirections.actionAuthTypeFragmentToNavGraphSignUp() , extra)
		}
	}

}