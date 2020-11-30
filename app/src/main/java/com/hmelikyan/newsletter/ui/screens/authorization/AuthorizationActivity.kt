package com.hmelikyan.newsletter.ui.screens.authorization

import android.view.LayoutInflater
import androidx.navigation.findNavController
import com.hmelikyan.newsletter.R
import com.hmelikyan.newsletter.databinding.ActivityAuthorizationBinding
import com.hmelikyan.newsletter.mvvm.ui.BaseActivity
import com.hmelikyan.newsletter.ui.screens.authorization.fragments.AuthTypeFragment

class AuthorizationActivity: BaseActivity<ActivityAuthorizationBinding>() {

    override val inflate: (LayoutInflater) -> ActivityAuthorizationBinding
        get() = ActivityAuthorizationBinding::inflate

    override fun initView(binding: ActivityAuthorizationBinding) {
        binding.apply {
            val navController = findNavController(R.id.fragment_container)
            navController.addOnDestinationChangedListener { _, destination, _ ->
                setSupportActionBar(mBinding.toolbar)
                supportActionBar?.setDisplayHomeAsUpEnabled(destination.label != AuthTypeFragment::class.simpleName)
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}