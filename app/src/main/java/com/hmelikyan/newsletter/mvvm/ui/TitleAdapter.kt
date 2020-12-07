package com.hmelikyan.newsletter.mvvm.ui

import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.recyclerview.widget.RecyclerView
import com.hmelikyan.newsletter.databinding.AdapterTitleItemBinding
import com.hmelikyan.newsletter.root.ext.inflater

class TitleAdapter(@StringRes private val resId:Int): RecyclerView.Adapter<TitleAdapter.TitleViewHolder>() {

	override fun onCreateViewHolder(parent : ViewGroup, viewType : Int) : TitleViewHolder {
		return TitleViewHolder(AdapterTitleItemBinding.inflate(parent.context.inflater(),parent,false))
	}

	override fun onBindViewHolder(holder : TitleViewHolder, position : Int) {
		holder.bind(resId)
	}

	override fun getItemCount() : Int {
		return 1
	}

	inner class TitleViewHolder(binding : AdapterTitleItemBinding) : BaseViewHolder<AdapterTitleItemBinding, Int>(binding) {

		override fun bind(data : Int) {
			binding.title.text = holderContext.getString(data)
		}
	}
}