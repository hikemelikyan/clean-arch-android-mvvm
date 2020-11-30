package com.hmelikyan.newsletter.mvvm.ui

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.hmelikyan.newsletter.R
import com.hmelikyan.newsletter.mvvm.vm.BaseViewModel
import com.hmelikyan.newsletter.mvvm.vm.ViewCommand
import com.hmelikyan.newsletter.ui.commands.Commands

abstract class BaseRequestActivity<VB : ViewBinding, VM : BaseViewModel> : BaseActivity<VB>() {

    private val _viewModel by lazy { ViewModelProvider.NewInstanceFactory().create(viewModelType) }
    val mViewModel: VM
        get() = _viewModel

    protected abstract val viewModelType: Class<VM>

    protected abstract fun initView(binding:VB,viewModel:VM)

    @Deprecated("This function is deprecated for BaseRequestActivity child",ReplaceWith("initView(binding:VB,viewModel:VM)"))
    override fun initView(binding: VB) {  }

    override fun onCreate(savedInstanceState: Bundle?) {
        savedInstanceState?.putBoolean(DISCARD_CONTENT, true)
        super.onCreate(savedInstanceState)
        initView(mBinding,_viewModel)
        _viewModel.viewCommands.observe(this) {
            proceedInternalCommands(it)
        }
        setContentView(StateLayout.create(this) {
            withComponentActivity(this@BaseRequestActivity)
            withContent(mBinding.root)
            withStateListener(_viewModel.uiState)
            attach()
        })
    }

    private fun proceedInternalCommands(command: ViewCommand) {
        when (command) {
            is Commands.NetworkErrorViewCommand -> showNetworkErrorSnackBar(getString(R.string.default_network_error_message))
            is Commands.ShowMessageViewCommand -> showToast(command.resId)
            is Commands.ShowMessageTextViewCommand -> showServerError(command.errorMessage)
            else -> proceedViewCommand(command)
        }
    }

    protected abstract fun proceedViewCommand(command: ViewCommand)

}