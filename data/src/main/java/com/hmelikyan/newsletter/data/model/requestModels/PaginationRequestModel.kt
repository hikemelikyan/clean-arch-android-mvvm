package com.hmelikyan.newsletter.data.model.requestModels

open class PaginationRequestModel(
    val pageNumber: Int,
    val pageSize: Int,
    val descending: Boolean = true,
)