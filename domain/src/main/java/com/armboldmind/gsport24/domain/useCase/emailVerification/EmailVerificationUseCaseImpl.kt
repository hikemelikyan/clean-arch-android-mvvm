package com.armboldmind.gsport24.domain.useCase.emailVerification

import com.armboldmind.gsport24.data.model.requestModels.ReqModelRegisterEmail
import com.armboldmind.gsport24.data.model.requestModels.ReqModelVerifyPhoneOrEmail
import com.armboldmind.gsport24.data.repository.AuthRepository
import com.armboldmind.gsport24.data.root.Result
import com.armboldmind.gsport24.data.root.ResultFactory
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