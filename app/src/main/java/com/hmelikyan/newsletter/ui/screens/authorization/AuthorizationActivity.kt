package com.hmelikyan.newsletter.ui.screens.authorization

import android.view.LayoutInflater
import android.view.WindowManager
import androidx.navigation.findNavController
import com.hmelikyan.newsletter.R
import com.hmelikyan.newsletter.databinding.ActivityAuthorizationBinding
import com.hmelikyan.newsletter.mvvm.ui.BaseActivity
import com.hmelikyan.newsletter.root.utils.permissionChecker.permissionChecker
import com.hmelikyan.newsletter.ui.screens.authorization.fragments.AuthTypeFragment

class AuthorizationActivity : BaseActivity<ActivityAuthorizationBinding>() {

	private var finishOnBack = true

	override val inflate : (LayoutInflater) -> ActivityAuthorizationBinding
		get() = ActivityAuthorizationBinding::inflate

	override fun initView(binding : ActivityAuthorizationBinding) {
		window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
		binding.apply {
			val navController = findNavController(R.id.fragment_container)
			navController.addOnDestinationChangedListener { _, destination, _ ->
				setSupportActionBar(mBinding.toolbar)
				finishOnBack = destination.label == AuthTypeFragment::class.simpleName
				supportActionBar?.setDisplayHomeAsUpEnabled(destination.label != AuthTypeFragment::class.simpleName)
			}
		}
	}

	override fun onSupportNavigateUp() : Boolean {
		onBackPressed()
		return true
	}

	override fun onBackPressed() {
		if (finishOnBack) {
			finish()
		} else {
			super.onBackPressed()
		}
	}
}