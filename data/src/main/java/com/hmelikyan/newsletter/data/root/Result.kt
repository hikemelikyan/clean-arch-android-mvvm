package com.hmelikyan.newsletter.data.root

data class Result<R>(
    val uiState: UIState,
    val data: R?,
    val msg: String?,
    internal val error: Throwable?
) {

    companion object {
        fun <R> makeSuccessResult(data: R?) = Result(UIState.SUCCESS, data, null, null)

        fun <E> makeInternalErrorResult(msg: String?, error: Throwable) =
            Result<E>(UIState.INTERNAL_ERROR, null, msg, error)

        fun <E> makeNetworkErrorResult(msg: String, error: Throwable? = null) =
            Result<E>(UIState.NETWORK_ERROR, null, msg, error)

        fun <E> makeServerErrorResult(msg: String?, error: Throwable?) =
            Result<E>(UIState.SERVER_ERROR, null, msg, error)

        fun <E> makeEmptyResult(message: String?) = Result<E>(UIState.EMPTY, null, message, null)

        fun <L> makeLoadingResult(message: String?) = Result<L>(UIState.LOADING, null, message, null)

    }
}