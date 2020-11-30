package com.hmelikyan.newsletter.ui.mainActivity

import android.content.Intent
import android.view.LayoutInflater
import com.hmelikyan.newsletter.databinding.ActivityMainBinding
import com.hmelikyan.newsletter.mvvm.ui.BaseRequestActivity
import com.hmelikyan.newsletter.mvvm.vm.ViewCommand
import com.hmelikyan.newsletter.ui.commands.Commands
import com.hmelikyan.newsletter.ui.screens.testActivity.TestActivity
import com.hmelikyan.newsletter.ui.screens.supportChatAdapter.SupportChatActivity

class MainActivity : BaseRequestActivity<ActivityMainBinding, MainViewModel>() {

    override val viewModelType: Class<MainViewModel>
        get() = MainViewModel::class.java
    override val inflate: (LayoutInflater) -> ActivityMainBinding
        get() = ActivityMainBinding::inflate

    override fun proceedViewCommand(command: ViewCommand) {
        when (command) {
            is Commands.TestViewCommand -> showToast(command.list?.size.toString())
            is Commands.ShowLoadingViewCommand -> showToast("Loading")
        }
    }

    override fun initView(binding: ActivityMainBinding, viewModel: MainViewModel) {
        binding.btn.setOnClickListener {
            startActivity(Intent(this, TestActivity::class.java))
        }
        binding.btnSignIn.setOnClickListener {
            startActivity(Intent(this, SupportChatActivity::class.java))
        }
        binding.btnSignUp.setOnClickListener {
            showToast("Test")
        }
    }

}