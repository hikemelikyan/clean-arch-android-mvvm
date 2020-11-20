package com.hmelikyan.newsletter.data.root

import android.content.Context
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.hmelikyan.newsletter.data.model.requestModels.PaginationRequestModel
import com.hmelikyan.newsletter.data.model.responseModels.root.PaginationResponseModel
import com.hmelikyan.newsletter.data.model.responseModels.root.Response
import com.hmelikyan.newsletter.root.di.ApplicationContext
import com.hmelikyan.newsletter.root.shared.utils.NetworkConnectivityChecker
import kotlinx.coroutines.flow.Flow
import retrofit2.HttpException
import javax.inject.Inject

class NetworkHelperImpl
@Inject
constructor(
    @ApplicationContext private val context: Context,
    private val connectivityChecker: NetworkConnectivityChecker
) : NetworkHelper {

    private val defaultInternalErrorMessage: String by lazy { context.getString(com.hmelikyan.newsletter.data.R.string.default_internal_error_message) }
    private val defaultNetworkErrorMessage: String by lazy { context.getString(com.hmelikyan.newsletter.data.R.string.default_network_error_message) }

    override suspend fun <R> call(action: suspend () -> Response<R>): R? = callInternal { action() }

    override suspend fun <I : PaginationRequestModel, O : PaginationResponseModel<T>, T : Any> paginate(
        action: NetworkHelper.PagingBuilder<I, O, T>.() -> Unit
    ): Flow<PagingData<T>> {
        val pb = PagingBuilder<I, O, T>()
        action(pb)
        return pb.proceed()
    }

    private inner class PagingBuilder<I : PaginationRequestModel, O : PaginationResponseModel<T>, T : Any> :
        NetworkHelper.PagingBuilder<I, O, T>() {
        private var _model: I? = null
        private var _action: (suspend (I) -> Response<O>)? = null

        override infix fun withModel(model: I) {
            _model = model
        }

        private fun createDefaultPagingConfig(): PagingConfig {
            return PagingConfig(20, 5, false, 20)
        }

        override fun withAction(action: suspend (I) -> Response<O>) {
            _action = action
        }

        override fun proceed(): Flow<PagingData<T>> {
            return Pager(createDefaultPagingConfig()) {
                Paginator(Pair(_model!!, _action!!))
            }.flow
        }
    }

    private suspend fun <R> callInternal(action: suspend () -> Response<R>): R? {
        return if (!connectivityChecker.isNetworkAvailable) {
            throw ApiException(UIState.NETWORK_ERROR, defaultNetworkErrorMessage, null)
        } else {
            val response = action()
            val responseBody = response.body()
            when {
                !response.isSuccessful -> throw ApiException(
                    UIState.SERVER_ERROR,
                    defaultInternalErrorMessage,
                    HttpException(response)
                )
                responseBody == null -> throw ApiException(
                    UIState.SERVER_ERROR,
                    defaultInternalErrorMessage,
                    null
                )
                !responseBody.success -> throw ApiException(
                    UIState.SERVER_ERROR,
                    response.body()?.message.toString(),
                    null
                )
                else -> responseBody.data
            }
        }
    }

    private inner class Paginator<I : PaginationRequestModel, T : Any, O : PaginationResponseModel<T>>(
        private val modelAndRequestMediator: Pair<I, suspend (I) -> Response<O>>
    ) : PagingSource<Int, T>() {

        override suspend fun load(params: LoadParams<Int>): LoadResult<Int, T> {
            try {
                val model = modelAndRequestMediator.first
                val request = modelAndRequestMediator.second
                model.pageSize = params.loadSize
                model.pageNumber = params.key?.let { if (it == 0) 1 else it } ?: 1
                val result = callInternal { request(model) }
                return LoadResult.Page(
                    data = if (result?.data == null) emptyList() else result.data,
                    prevKey = if (model.pageNumber == 1) null else model.pageNumber - 1,
                    nextKey = when {
                        result?.data == null || result.pageCount == model.pageNumber -> null
                        else -> {
                            model.pageNumber + 1
                        }
                    }
                )
            } catch (e: Exception) {
                return LoadResult.Error(e)
            }
        }
    }

}