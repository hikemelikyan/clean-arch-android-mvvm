package com.hmelikyan.newsletter.mvvm.ui

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseViewHolder<VB : ViewBinding, DATA : Any>(binding: VB) : RecyclerView.ViewHolder(binding.root) {

    protected val holderContext: Context = binding.root.context

    abstract fun bind(data: DATA)
}