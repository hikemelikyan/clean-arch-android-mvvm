package com.hmelikyan.newsletter.ui.mainActivity.adapters

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.hmelikyan.newsletter.data.model.responseModels.NotificationResponseModel
import com.hmelikyan.newsletter.databinding.AdapterTestItemBinding
import com.hmelikyan.newsletter.mvvm.ui.BaseViewHolder
import com.hmelikyan.newsletter.root.shared.ext.inflater

class TestAdapter :PagingDataAdapter<NotificationResponseModel,TestAdapter.TestViewHolder>(NotificationResponseModel.Companion){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TestViewHolder {
        return TestViewHolder(AdapterTestItemBinding.inflate(parent.context.inflater(),parent,false))
    }

    override fun onBindViewHolder(holder: TestViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    inner class TestViewHolder(binding: AdapterTestItemBinding) :BaseViewHolder<AdapterTestItemBinding,NotificationResponseModel>(binding) {

        override fun bind(data: NotificationResponseModel) {

        }

    }

}