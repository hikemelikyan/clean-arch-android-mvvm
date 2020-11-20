package com.hmelikyan.newsletter.data.model.responseModels

import android.os.Parcelable
import androidx.recyclerview.widget.DiffUtil
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NotificationResponseModel(
    val id: Int?,
    val title: String?,
    val body: String?,
    val receiveDate: String?,
    val opened: Boolean?,
    val type: Int?,
    val tripId: Int?,
    val tripStatus: Int?
) : Parcelable{
    companion object : DiffUtil.ItemCallback<NotificationResponseModel>(){
        override fun areItemsTheSame(oldItem: NotificationResponseModel, newItem: NotificationResponseModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: NotificationResponseModel, newItem: NotificationResponseModel): Boolean {
            return oldItem.receiveDate == newItem.receiveDate
        }

    }
}