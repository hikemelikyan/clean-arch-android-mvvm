package com.hmelikyan.newsletter.ui.screens.authorizationActivity

import android.view.LayoutInflater
import com.hmelikyan.newsletter.databinding.ActivityAuthorizationBinding
import com.hmelikyan.newsletter.mvvm.ui.BaseActivityMVVM
import com.hmelikyan.newsletter.mvvm.vm.ViewCommand
import com.hmelikyan.newsletter.ui.screens.authorizationActivity.adapters.CategoriesAdapter

class AuthorizationActivity :BaseActivityMVVM<ActivityAuthorizationBinding, AuthActivityViewModel>(){
    private val adapter:CategoriesAdapter by lazy { CategoriesAdapter() }
    override val viewModelType: Class<AuthActivityViewModel>
        get() = AuthActivityViewModel::class.java
    override val inflate: (LayoutInflater) -> ActivityAuthorizationBinding
        get() = ActivityAuthorizationBinding::inflate

    override fun proceedViewCommand(command: ViewCommand) {

    }

    override fun initView(binding: ActivityAuthorizationBinding, viewModel: AuthActivityViewModel) {
        binding.viewModel = viewModel
        binding.rvCategories.adapter = adapter
    }

}