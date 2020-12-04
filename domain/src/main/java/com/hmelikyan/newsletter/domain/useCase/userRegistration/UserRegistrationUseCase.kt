package com.hmelikyan.newsletter.domain.useCase.userRegistration

import com.hmelikyan.newsletter.data.model.requestModels.ReqModelRegister
import com.hmelikyan.newsletter.data.root.Result
import kotlinx.coroutines.flow.Flow

interface UserRegistrationUseCase {

	suspend fun registerUser(model:ReqModelRegister) : Flow<Result<Any>>

}