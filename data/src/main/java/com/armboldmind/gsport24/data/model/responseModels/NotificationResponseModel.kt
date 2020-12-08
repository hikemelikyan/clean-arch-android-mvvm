package com.armboldmind.gsport24.data.model.responseModels

import android.os.Parcelable
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
) : Parcelable