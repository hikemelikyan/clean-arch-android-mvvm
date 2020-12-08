package com.armboldmind.gsport24.data.model.requestModels

open class PaginationRequestModel(
    var pageNumber: Int,
    var pageSize: Int,
    val descending: Boolean = true,
)