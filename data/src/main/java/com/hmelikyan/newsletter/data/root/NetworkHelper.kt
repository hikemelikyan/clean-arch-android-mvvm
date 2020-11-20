package com.hmelikyan.newsletter.data.root

import androidx.paging.PagingData
import com.hmelikyan.newsletter.data.model.requestModels.PaginationRequestModel
import com.hmelikyan.newsletter.data.model.responseModels.root.PaginationResponseModel
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
    suspend fun <I : PaginationRequestModel, O : PaginationResponseModel<T>, T : Any> paginate(
        action: PagingBuilder<I,O,T>.() -> Unit
    ): Flow<PagingData<T>>

    abstract class PagingBuilder<I : PaginationRequestModel, O : PaginationResponseModel<T>, T : Any>{
        abstract fun withModel(model:I)
        abstract fun withAction(action: suspend (I) -> Response<O>)
        internal abstract fun proceed():Flow<PagingData<T>>
    }

}