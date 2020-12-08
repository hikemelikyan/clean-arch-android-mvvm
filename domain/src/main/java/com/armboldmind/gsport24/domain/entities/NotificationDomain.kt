package com.armboldmind.gsport24.domain.entities

import androidx.recyclerview.widget.DiffUtil
import com.armboldmind.gsport24.data.root.Entity

data class NotificationDomain (
    val id: Int,
    val title: String,
    val description: String,
    val date: String,
    val isOpened: Boolean,
    val notificationType: Int,
    val tripId: Int,
    val isTripStarted: Boolean
):Entity {
    companion object : DiffUtil.ItemCallback<NotificationDomain>(){
        override fun areItemsTheSame(oldItem: NotificationDomain, newItem: NotificationDomain): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: NotificationDomain, newItem: NotificationDomain): Boolean {
            return oldItem.date == newItem.date
        }

    }
}