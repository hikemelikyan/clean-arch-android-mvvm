package com.hmelikyan.newsletter.ui.screens.authorizationActivity

import android.view.LayoutInflater
import androidx.recyclerview.widget.LinearLayoutManager
import com.hmelikyan.newsletter.databinding.ActivityAuthorizationBinding
import com.hmelikyan.newsletter.mvvm.ui.BaseActivityMVVM
import com.hmelikyan.newsletter.mvvm.vm.ViewCommand
import com.hmelikyan.newsletter.shared.util.JumpScrollListener
import com.hmelikyan.newsletter.ui.screens.authorizationActivity.adapters.CategoriesAdapter
import com.hmelikyan.newsletter.ui.screens.authorizationActivity.adapters.CategoriesAdapterHorizontal
import kotlinx.android.synthetic.main.activity_authorization.view.*

class AuthorizationActivity :
    BaseActivityMVVM<ActivityAuthorizationBinding, AuthActivityViewModel>() {
    private val adapter: CategoriesAdapter by lazy { CategoriesAdapter(::onItemClick) }
    private val horizontalAdapter: CategoriesAdapterHorizontal by lazy { CategoriesAdapterHorizontal() }

    override val viewModelType: Class<AuthActivityViewModel>
        get() = AuthActivityViewModel::class.java

    override val inflate: (LayoutInflater) -> ActivityAuthorizationBinding
        get() = ActivityAuthorizationBinding::inflate

    override fun proceedViewCommand(command: ViewCommand) {

    }

    override fun initView(binding: ActivityAuthorizationBinding, viewModel: AuthActivityViewModel) {
        binding.viewModel = viewModel
        binding.rvCategories.adapter = adapter
        binding.rvCategoriesHorizontal.apply {
            layoutManager = LinearLayoutManager(
                this@AuthorizationActivity,
                LinearLayoutManager.HORIZONTAL,
                false
            )
            adapter = horizontalAdapter
            addOnScrollListener(JumpScrollListener(horizontalAdapter))
        }
    }

    private fun onItemClick(position: Int) {
        showToast(position.toString())
    }

}