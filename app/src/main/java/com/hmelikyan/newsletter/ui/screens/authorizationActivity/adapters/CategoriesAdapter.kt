package com.hmelikyan.newsletter.ui.screens.authorizationActivity.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.Glide
import com.hmelikyan.newsletter.R
import com.hmelikyan.newsletter.databinding.AdapterCategoryItemBinding
import com.hmelikyan.newsletter.domain.entities.NotificationDomain
import com.hmelikyan.newsletter.mvvm.ui.BaseViewHolder
import com.hmelikyan.newsletter.root.shared.ext.inflater

class CategoriesAdapter: ListAdapter<NotificationDomain, CategoriesAdapter.CategoryViewHolder>(NotificationDomain.Companion) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(AdapterCategoryItemBinding.inflate(parent.context.inflater(),parent,false))
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    inner class CategoryViewHolder(binding: AdapterCategoryItemBinding) : BaseViewHolder<AdapterCategoryItemBinding, NotificationDomain>(binding) {
        override fun bind(data: NotificationDomain) {
            binding.apply {
                tvCategory.text = data.description
                tvTime.text = data.id.toString()
                Glide.with(holderContext)
                    .load("https://www.talkwalker.com/images/2020/blog-headers/image-analysis.png")
                    .centerCrop()
                    .into(image)
                image.setShadowColor(when(bindingAdapterPosition % 3){
                    0 -> R.color.alto
                    1 -> R.color.colorAccent
                    else -> R.color.pickled_radish
                })
            }
        }
    }

}