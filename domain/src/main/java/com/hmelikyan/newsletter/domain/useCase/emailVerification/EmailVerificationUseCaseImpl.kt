package com.hmelikyan.newsletter.domain.useCase.emailVerification

import com.hmelikyan.newsletter.data.model.requestModels.ReqModelRegisterEmail
import com.hmelikyan.newsletter.data.model.requestModels.ReqModelVerifyPhoneOrEmail
import com.hmelikyan.newsletter.data.repository.AuthRepository
import com.hmelikyan.newsletter.data.root.Result
import com.hmelikyan.newsletter.data.root.ResultFactory
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class EmailVerificationUseCaseImpl
@Inject
constructor(
	private val authRepository : AuthRepository,
	private val resultFactory : ResultFactory
) : EmailVerificationUseCase {

	override suspend fun getVerificationCodeByEmail(model:ReqModelRegisterEmail) : Flow<Result<Boolean>> {
		return resultFactory.getResult { authRepository.getSmsCodeByEmail(model) }
	}

	override suspend fun verifyEmail(model: ReqModelVerifyPhoneOrEmail) : Flow<Result<Boolean>> {
		return resultFactory.getResult { authRepository.verifyEmail(model) }
	}
}