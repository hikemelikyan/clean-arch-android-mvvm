package com.hmelikyan.newsletter.ui.mainActivity

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import com.hmelikyan.newsletter.databinding.ActivityMainBinding
import com.hmelikyan.newsletter.mvvm.ui.BaseActivityMVVM
import com.hmelikyan.newsletter.mvvm.vm.ViewCommand
import com.hmelikyan.newsletter.ui.commands.Commands
import com.hmelikyan.newsletter.ui.screens.authorizationActivity.AuthorizationActivity

class MainActivity : BaseActivityMVVM<ActivityMainBinding,MainViewModel>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel.getTest()
    }

    override val viewModelType: Class<MainViewModel>
        get() = MainViewModel::class.java
    override val inflate: (LayoutInflater) -> ActivityMainBinding
        get() = ActivityMainBinding::inflate

    override fun proceedViewCommand(command: ViewCommand) {
        when(command){
            is Commands.TestViewCommand -> showToast(command.list?.size.toString())
            is Commands.ShowLoadingViewCommand -> showToast("Loading")
        }
    }

    override fun initView(binding: ActivityMainBinding, viewModel: MainViewModel) {
        binding.btn.setOnClickListener {
            startActivity(Intent(this, AuthorizationActivity::class.java))
        }
        binding.btnSignIn.setOnClickListener {
            showToast("Test")
        }
        binding.btnSignUp.setOnClickListener {
            showToast("Test")
        }
    }

}