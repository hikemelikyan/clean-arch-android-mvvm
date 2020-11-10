package com.hmelikyan.newsletter.data.model.responseModels.root

import retrofit2.Response

data class ResponseModel<T>(
    val success: Boolean,
    val data: T?,
    val message: List<ResponseMessageModel>
)

data class ResponseMessageModel(
    val key: Int,
    val message: String
)

typealias Response<T> = Response<ResponseModel<T>>