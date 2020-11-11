package com.hmelikyan.newsletter.data.root

import com.hmelikyan.newsletter.data.model.responseModels.root.Response
import com.hmelikyan.newsletter.data.model.responseModels.root.ResponseModel
import kotlinx.coroutines.flow.Flow


interface NetworkHelper {

    @DslMarker
    annotation class ApiRequest

    @DslMarker
    annotation class PagingRequest

    @ApiRequest
    suspend fun <R> call(action: suspend () -> Response<R>): R?

    @PagingRequest
    suspend fun <R> withPaging(action: suspend () -> R): R

}