package com.hmelikyan.newsletter.data.model.responseModels

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NotificationModel(val id: Int,
                             val title: String,
                             val body: String,
                             @SerializedName("receiveDate") val date: String,
                             val opened: Boolean,
                             val type: Int,
                             val tripId: Int,
                             val tripStatus:Int
):Parcelable