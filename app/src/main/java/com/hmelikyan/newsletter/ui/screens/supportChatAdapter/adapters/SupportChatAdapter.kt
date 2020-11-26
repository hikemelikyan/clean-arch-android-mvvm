package com.hmelikyan.newsletter.ui.screens.supportChatAdapter.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import com.hmelikyan.newsletter.R
import com.hmelikyan.newsletter.databinding.AdapterReceivedImageItemBinding
import com.hmelikyan.newsletter.databinding.AdapterReceivedMessageItemBinding
import com.hmelikyan.newsletter.databinding.AdapterSentImageItemBinding
import com.hmelikyan.newsletter.databinding.AdapterSentMessageItemBinding
import com.hmelikyan.newsletter.mvvm.ui.BaseMultiTypeListAdapter
import com.hmelikyan.newsletter.mvvm.ui.BaseViewHolder
import com.hmelikyan.newsletter.ui.screens.supportChatAdapter.*

private const val TYPE_1 = 1
private const val TYPE_2 = 2
private const val TYPE_3 = 3
private const val TYPE_4 = 4
private const val TYPE_5 = 5
private const val TYPE_6 = 6

class SupportChatAdapter : BaseMultiTypeListAdapter<Any, ViewBinding>(object : DiffUtil.ItemCallback<Any>(){
    override fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean {
        return false
    }

    override fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean {
        return false
    }
}) {

    override fun inflate(inflater: LayoutInflater, parent: ViewGroup?, attachToParent: Boolean): Map<Int, ViewBinding> {
        return mapOf(
            TYPE_1 to AdapterReceivedMessageItemBinding.inflate(inflater,parent,attachToParent),
            TYPE_2 to AdapterSentMessageItemBinding.inflate(inflater,parent,attachToParent),
            TYPE_3 to AdapterSentImageItemBinding.inflate(inflater,parent,attachToParent),
            TYPE_4 to AdapterReceivedImageItemBinding.inflate(inflater,parent,attachToParent),
//            TYPE_5 to AdapterReceivedImageItemBinding.inflate(inflater,parent,attachToParent),
//            TYPE_6 to AdapterReceivedImageItemBinding.inflate(inflater,parent,attachToParent)
        )
    }

    override fun isForType(item: Any): Int {
        return when (item) {
            is SentMessageModel -> TYPE_1
            is ReceivedMessageModel -> TYPE_2
            is SentPhotoModel -> TYPE_3
            is ReceivedPhotoModel -> TYPE_4
//            is SentFileModel -> TYPE_5
//            is ReceivedFileModel -> TYPE_6
            else -> error("Type is not supported.")
        }
    }

    override fun bindActions(): Map<Int, BaseViewHolder<ViewBinding, Any>.(data: Any) -> Unit> {
        return mapOf(
            TYPE_1 to {
                binding as AdapterReceivedMessageItemBinding
                it as SentMessageModel
                binding.text.text = holderContext.getString(R.string.lorem)
            },
            TYPE_2 to {
                binding as AdapterSentMessageItemBinding
                it as ReceivedMessageModel
                binding.text.text = holderContext.getString(R.string.lorem)
            },
            TYPE_3 to {
                binding as AdapterSentImageItemBinding
                it as SentPhotoModel
                Glide.with(holderContext)
                    .load(it.url)
                    .into(binding.image)
            },
            TYPE_4 to {
                binding as AdapterReceivedImageItemBinding
                it as ReceivedPhotoModel
                Glide.with(holderContext)
                    .load(it.url)
                    .into(binding.image)
            },
            TYPE_5 to {
                binding as AdapterReceivedImageItemBinding
                it as SentFileModel
                // TODO: 11/26/2020 for future handling
            },
            TYPE_6 to {
                binding as AdapterReceivedImageItemBinding
                it as ReceivedFileModel
                // TODO: 11/26/2020 for future handling
            }
        )
    }
}