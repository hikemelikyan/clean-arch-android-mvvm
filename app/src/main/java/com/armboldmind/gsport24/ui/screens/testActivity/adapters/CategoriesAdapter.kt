package com.armboldmind.gsport24.ui.screens.testActivity.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.armboldmind.gsport24.databinding.AdapterCategoryItemBinding
import com.armboldmind.gsport24.domain.entities.NotificationDomain
import com.armboldmind.gsport24.mvvm.ui.BaseListAdapter
import com.armboldmind.gsport24.mvvm.ui.BaseViewHolder

class CategoriesAdapter(private val onItemClick:(Int)->Unit) : BaseListAdapter<NotificationDomain, AdapterCategoryItemBinding>(NotificationDomain.Companion) {

    override fun inflate(inflater: LayoutInflater, parent: ViewGroup?, attachToParent: Boolean): AdapterCategoryItemBinding {
        return AdapterCategoryItemBinding.inflate(inflater,parent,attachToParent)
    }

    override fun BaseViewHolder<AdapterCategoryItemBinding, NotificationDomain>.bindActionTo(data: NotificationDomain) {
        binding.apply {
            tvCategory.text = data.description
            tvTime.text = data.id.toString()
            image.setOnClickListener { onItemClick(bindingAdapterPosition) }
            Glide.with(holderContext)
                .load("https://www.talkwalker.com/images/2020/blog-headers/image-analysis.png")
                .into(image)
        }
    }

}
