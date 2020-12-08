package com.armboldmind.gsport24.data.model.responseModels.root

open class PaginationResponseModel<T>(
    val pageCount:Int,
    val totalCount:Int,
    val data:List<T>?
)