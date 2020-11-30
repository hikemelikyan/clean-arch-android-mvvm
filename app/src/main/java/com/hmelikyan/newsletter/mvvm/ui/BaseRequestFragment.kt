package com.hmelikyan.newsletter.mvvm.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.hmelikyan.newsletter.R
import com.hmelikyan.newsletter.mvvm.vm.BaseViewModel
import com.hmelikyan.newsletter.mvvm.vm.ViewCommand
import com.hmelikyan.newsletter.ui.commands.Commands

abstract class BaseRequestFragment<VB : ViewBinding, VM : BaseViewModel> : BaseFragment<VB>(){

    open val hasMainRequest = false

    private val _viewModel by lazy { ViewModelProvider.NewInstanceFactory().create(viewModelType) }
    val mViewModel: VM
        get() = _viewModel

    protected abstract val viewModelType: Class<VM>

    @Deprecated("This function is deprecated for BaseRequestFragment child",ReplaceWith("initView(binding:VB,viewModel:VM)"))
    override fun initView(binding: VB) {  }

    abstract fun initView(binding:VB, viewModel:VM)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        savedInstanceState?.putBoolean(DISCARD_CONTENT,true)
        super.onCreateView(inflater, container, savedInstanceState)
        initView(mBinding,_viewModel)
        _viewModel.viewCommands.observe(viewLifecycleOwner) {
            proceedInternalCommands(it)
        }
        return if(hasMainRequest) StateLayout.create(requireContext()) {
            withComponentFragment(this@BaseRequestFragment)
            withContent(mBinding.root)
            withStateListener(_viewModel.uiState)
            attach()
        } else mBinding.root
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