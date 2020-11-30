package com.hmelikyan.newsletter.mvvm.ui

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.hmelikyan.newsletter.R
import com.hmelikyan.newsletter.mvvm.vm.BaseViewModel
import com.hmelikyan.newsletter.mvvm.vm.ViewCommand
import com.hmelikyan.newsletter.ui.commands.Commands

abstract class BaseFragment<VB:ViewBinding> : Fragment(), IBaseView {

    protected lateinit var mActivity: BaseActivity<*>

    companion object {
        const val DISCARD_CONTENT = "DiscardContent"
    }

    private lateinit var _binding: VB
    protected val mBinding:VB
        get() = _binding

    protected abstract val inflate: (LayoutInflater,ViewGroup?, Boolean) -> VB

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BaseActivity<*>) {
            mActivity = context
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if(!::_binding.isInitialized){
            _binding = inflate(inflater,container,false)
            if (savedInstanceState?.getBoolean(DISCARD_CONTENT) == null || !savedInstanceState.getBoolean(DISCARD_CONTENT)) {
                initView(_binding)
            }
        }
        return _binding.root
    }

    protected abstract fun initView(binding:VB)

    override fun showServerError(message: String) {
        mActivity.showServerError(message)
    }

    override fun showServerError(@StringRes resId: Int) {
        showServerError(resources.getString(resId))
    }

    override fun showNetworkErrorSnackBar(message: String) {
        mActivity.showNetworkErrorSnackBar(message)
    }

    override fun showNetworkErrorSnackBar(@StringRes resId: Int) {
        showNetworkErrorSnackBar(resources.getString(resId))
    }

    override fun showToast(message: String) {
        mActivity.showToast(message)
    }

    override fun showToast(@StringRes resId: Int) {
        showToast(resources.getString(resId))
    }

    fun hasPermission(permission: String): Boolean {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.M || requireContext().checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED
    }
}