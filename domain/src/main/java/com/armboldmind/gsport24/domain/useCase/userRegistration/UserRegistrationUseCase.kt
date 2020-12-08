package com.armboldmind.gsport24.domain.useCase.userRegistration

import com.armboldmind.gsport24.data.model.requestModels.ReqModelRegister
import com.armboldmind.gsport24.data.root.Result
import kotlinx.coroutines.flow.Flow

interface UserRegistrationUseCase {

	suspend fun registerUser(model:ReqModelRegister) : Flow<Result<Any>>

}