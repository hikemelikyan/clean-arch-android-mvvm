package com.hmelikyan.newsletter.data.root

import android.content.Context
import com.hmelikyan.newsletter.data.model.responseModels.root.Response
import com.hmelikyan.newsletter.root.shared.utils.NetworkConnectivityChecker
import kotlinx.coroutines.yield
import retrofit2.HttpException
import javax.inject.Inject

class NetworkHelperImpl
@Inject
constructor(
    private val context: Context,
    private val connectivityChecker: NetworkConnectivityChecker
) : NetworkHelper {

    private val defaultInternalErrorMessage:String by lazy { context.getString(com.hmelikyan.newsletter.data.R.string.default_internal_error_message) }
    private val defaultNetworkErrorMessage:String by lazy { context.getString(com.hmelikyan.newsletter.data.R.string.default_network_error_message) }

    override suspend fun <R> call(action: suspend () -> Response<R>): Result<R> =
        callInternal { action() }

    override suspend fun <R> withPaging(action: suspend () -> R): R {
        TODO("not implemented")
    }

    private suspend fun <R> callInternal(action: suspend () -> Response<R>): Result<R> {
        try {
            return if (!connectivityChecker.isNetworkAvailable) {
                Result.makeNetworkErrorResult(defaultNetworkErrorMessage)
            } else {
                val response = action()
                val responseBody = response.body()
                when {
                    !response.isSuccessful -> Result.makeServerErrorResult(defaultInternalErrorMessage, HttpException(response))
                    responseBody == null -> Result.makeServerErrorResult(defaultInternalErrorMessage,null)
                    !responseBody.success -> Result.makeServerErrorResult(response.body()?.message.toString(),null)
                    responseBody.data is Collection<*> && responseBody.data.isEmpty() -> Result.makeEmptyResult(null)
                    else -> Result.makeSuccessResult(responseBody.data)
                }
            }
        } catch (e: Exception) {
            return Result.makeInternalErrorResult(e.message, e)
        }
    }


}