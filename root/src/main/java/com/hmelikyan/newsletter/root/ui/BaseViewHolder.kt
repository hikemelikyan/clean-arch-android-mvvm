package com.hmelikyan.newsletter.root.ui

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseViewHolder<VB : ViewBinding, DATA : Any>(binding: VB) : RecyclerView.ViewHolder(binding.root) {

    protected val holderContext: Context = binding.root.context

    protected abstract fun VB.bind(data: DATA)
}