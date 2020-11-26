package com.hmelikyan.newsletter.ui.screens.supportChatAdapter

import android.view.LayoutInflater
import com.hmelikyan.newsletter.databinding.ActivitySupportChatBinding
import com.hmelikyan.newsletter.mvvm.ui.BaseActivityMVVM
import com.hmelikyan.newsletter.mvvm.vm.ViewCommand
import com.hmelikyan.newsletter.ui.screens.supportChatAdapter.adapters.SupportChatAdapter

class SupportChatActivity: BaseActivityMVVM<ActivitySupportChatBinding, SupportChatViewModel>() {
    override val viewModelType: Class<SupportChatViewModel>
        get() = SupportChatViewModel::class.java
    override val inflate: (LayoutInflater) -> ActivitySupportChatBinding
        get() = ActivitySupportChatBinding::inflate

    override fun initView(binding: ActivitySupportChatBinding, viewModel: SupportChatViewModel) {
        binding.apply {
            setSupportActionBar(toolbar)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            setViewModel(viewModel)
            viewModel.getMessages()
            ivAttachFile.setOnClickListener { showToast("Attach file") }
            ivAttachPhoto.setOnClickListener { showToast("Attach image") }
            chatRecycler.adapter = SupportChatAdapter()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun proceedViewCommand(command: ViewCommand) {

    }
}