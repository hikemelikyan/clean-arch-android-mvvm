package com.hmelikyan.newsletter.domain.useCase.userRegistration

import com.hmelikyan.newsletter.data.model.requestModels.ReqModelRegister
import com.hmelikyan.newsletter.data.repository.AuthRepository
import com.hmelikyan.newsletter.data.root.Result
import com.hmelikyan.newsletter.data.root.ResultFactory
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