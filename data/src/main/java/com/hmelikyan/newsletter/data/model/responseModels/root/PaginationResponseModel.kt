package com.hmelikyan.newsletter.data.model.responseModels.root

open class PaginationResponseModel<T>(
    val pageCount:Int,
    val totalCount:Int,
    val data:List<T>?
)