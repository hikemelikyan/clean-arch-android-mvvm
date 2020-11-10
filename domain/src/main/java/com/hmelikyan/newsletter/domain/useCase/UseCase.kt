package com.hmelikyan.newsletter.domain.useCase

import com.hmelikyan.newsletter.data.root.Result

interface UseCase<P : UseCase.Params, R : Any> {

    suspend fun exec(requestParams: P): Result<R>

    abstract class Params

}