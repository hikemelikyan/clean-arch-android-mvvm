package com.hmelikyan.newsletter.ui.screens.authorizationActivity.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
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
                text.text = data.id.toString()
            }
        }
    }

}