package com.armboldmind.gsport24.data.model.requestModels

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ReqModelRegister(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val fatherName: String,
    val dateOfBirth: String,
    val gender: Int,
    val password: String? = null
): Parcelable