package com.hmelikyan.newsletter.data.root

import android.content.Context
import com.hmelikyan.newsletter.data.model.responseModels.root.Response
import com.hmelikyan.newsletter.root.di.ApplicationContext
import com.hmelikyan.newsletter.root.shared.utils.NetworkConnectivityChecker
import retrofit2.HttpException
import javax.inject.Inject

class NetworkHelperImpl
@Inject
constructor(
    @ApplicationContext private val context: Context,
    private val connectivityChecker: NetworkConnectivityChecker
) : NetworkHelper {

    private val defaultInternalErrorMessage:String by lazy { context.getString(com.hmelikyan.newsletter.data.R.string.default_internal_error_message) }
    private val defaultNetworkErrorMessage:String by lazy { context.getString(com.hmelikyan.newsletter.data.R.string.default_network_error_message) }

    override suspend fun <R> call(action: suspend () -> Response<R>): R? = callInternal { action() }

    override suspend fun <R> withPaging(action: suspend () -> R): R {
        TODO("not implemented")
    }

    @Throws(Exception::class)
    private suspend fun <R> callInternal(action: suspend () -> Response<R>): R? {
        return if (!connectivityChecker.isNetworkAvailable) {
            throw ApiException(UIState.NETWORK_ERROR, defaultNetworkErrorMessage, null)
        } else {
            val response = action()
            val responseBody = response.body()
            when {
                !response.isSuccessful -> throw ApiException(UIState.SERVER_ERROR, defaultInternalErrorMessage, HttpException(response))
                responseBody == null -> throw ApiException(UIState.SERVER_ERROR, defaultInternalErrorMessage,null)
                !responseBody.success -> throw ApiException(UIState.SERVER_ERROR, response.body()?.message.toString(),null)
                else -> responseBody.data
            }
        }
    }

    /*private suspend fun <R> callInternal(action: suspend () -> Response<R>): Response<R> {
        return flow {
            try {
                if (!connectivityChecker.isNetworkAvailable) {
                    Result.makeErrorResult(UIState.NETWORK_ERROR, defaultNetworkErrorMessage)
                } else {
                    Result.makeLoadingResult<R>("Please wait.")
                    val response = action()
                    val responseBody = response.body()
                    when {
                        !response.isSuccessful -> Result.makeErrorResult<R>(UIState.SERVER_ERROR, defaultInternalErrorMessage, HttpException(response))
                        responseBody == null -> Result.makeErrorResult<R>(UIState.SERVER_ERROR, defaultInternalErrorMessage,null)
                        !responseBody.success -> Result.makeErrorResult<R>(UIState.SERVER_ERROR, response.body()?.message.toString(),null)
                        responseBody.data is Collection<*> && responseBody.data.isEmpty() -> Result.makeEmptyResult<R>(null)
                        else -> Result.makeSuccessResult(responseBody.data)
                    }
                }
            } catch (e:Exception){
                Result.makeErrorResult<R>(UIState.INTERNAL_ERROR,e.message, e)
            }
        }
    }*/


}