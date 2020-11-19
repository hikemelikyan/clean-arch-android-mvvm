package com.hmelikyan.newsletter.mvvm.ui

import android.os.Bundle
import android.view.LayoutInflater
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.hmelikyan.newsletter.R
import com.hmelikyan.newsletter.mvvm.vm.BaseViewModel
import com.hmelikyan.newsletter.mvvm.vm.ViewCommand
import com.hmelikyan.newsletter.ui.commands.Commands

abstract class BaseActivityMVVM<VB : ViewBinding, VM : BaseViewModel> : BaseActivity() {

    private lateinit var _binding: VB
    protected val mBinding: VB
        get() = _binding

    private val _viewModel by lazy { ViewModelProvider.NewInstanceFactory().create(viewModelType) }
    val mViewModel: VM
        get() = _viewModel

    protected abstract val viewModelType: Class<VM>

    protected abstract val inflate: (LayoutInflater) -> VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = inflate(layoutInflater)
        _viewModel.viewCommands.observe(this) {
            proceedInternalCommands(it)
        }
        setContentView(StateLayout.create(this) {
            withComponentActivity(this@BaseActivityMVVM)
            withContent(_binding.root)
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