package com.armboldmind.gsport24.domain.useCase.userRegistration

import com.armboldmind.gsport24.data.model.requestModels.ReqModelRegister
import com.armboldmind.gsport24.data.repository.AuthRepository
import com.armboldmind.gsport24.data.root.Result
import com.armboldmind.gsport24.data.root.ResultFactory
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRegistrationUseCaseImpl
@Inject
constructor(
	private val authRepository : AuthRepository,
	private val resultFactory : ResultFactory
) : UserRegistrationUseCase {

	override suspend fun registerUser(model : ReqModelRegister) : Flow<Result<Any>> {
		return resultFactory.getResult { authRepository.registerUser(model) }
	}
}