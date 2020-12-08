package com.armboldmind.gsport24.ui.screens.authorization.fragments.signUp.personalInfo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.armboldmind.gsport24.databinding.AdapterEnumItemBinding
import com.armboldmind.gsport24.mvvm.ui.BaseListAdapter
import com.armboldmind.gsport24.mvvm.ui.BaseViewHolder

class EnumRecyclerAdapter(private val onItemClick : (EnumRecyclerItem) -> Unit) : BaseListAdapter<EnumRecyclerItem, AdapterEnumItemBinding>(Callback) {

	override fun inflate(inflater : LayoutInflater, parent : ViewGroup?, attachToParent : Boolean) : AdapterEnumItemBinding {
		return AdapterEnumItemBinding.inflate(inflater, parent, attachToParent)
	}

	override fun BaseViewHolder<AdapterEnumItemBinding, EnumRecyclerItem>.bindActionTo(data : EnumRecyclerItem) {
		binding.text.text = data.name
		binding.text.setOnClickListener { onItemClick(data) }
	}

}

data class EnumRecyclerItem(
	val id : Int,
	val name : String,
	val isChecked : Boolean
)

private val Callback = object : DiffUtil.ItemCallback<EnumRecyclerItem>() {
	override fun areItemsTheSame(oldItem : EnumRecyclerItem, newItem : EnumRecyclerItem) : Boolean {
		return oldItem.id == newItem.id
	}

	override fun areContentsTheSame(oldItem : EnumRecyclerItem, newItem : EnumRecyclerItem) : Boolean {
		return oldItem.name == newItem.name
	}

}