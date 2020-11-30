package com.hmelikyan.newsletter.ui.screens.testActivity.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.hmelikyan.newsletter.databinding.AdapterCategoryItemBinding
import com.hmelikyan.newsletter.domain.entities.NotificationDomain
import com.hmelikyan.newsletter.mvvm.ui.BaseListAdapter
import com.hmelikyan.newsletter.mvvm.ui.BaseViewHolder

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
