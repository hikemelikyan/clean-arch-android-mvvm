package com.hmelikyan.newsletter.ui.screens.authorizationActivity.adapters

import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.hmelikyan.newsletter.domain.entities.NotificationDomain

object BindingAdapters {
    @JvmStatic
    @BindingAdapter("category_items")
    fun setAdapterItems(rv:RecyclerView,items: LiveData<List<NotificationDomain>>){
        val adapter = rv.adapter as CategoriesAdapter
        items.observeForever { adapter.submitList(it) }
    }
}