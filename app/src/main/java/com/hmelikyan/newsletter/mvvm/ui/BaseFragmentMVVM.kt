package com.hmelikyan.newsletter.mvvm.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.hmelikyan.newsletter.mvvm.vm.BaseViewModel

abstract class BaseFragmentMVVM<VB : ViewBinding, VM : BaseViewModel> : BaseFragment(){

    private lateinit var _binding: VB
    protected val mBinding: VB
        get() = _binding

    private val _viewModel by lazy { ViewModelProvider.NewInstanceFactory().create(viewModelType) }
    val mViewModel: VM
        get() = _viewModel

    protected abstract val viewModelType: Class<VM>

    protected abstract val inflate: (LayoutInflater,ViewGroup?, Boolean) -> VB

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if(!::_binding.isInitialized){
            _binding = inflate(inflater,container,false)
            initView()
        }
        return _binding.root
    }

    protected abstract fun initView()
}