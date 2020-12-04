package com.hmelikyan.newsletter.domain.useCase.phoneVerification

import com.hmelikyan.newsletter.data.model.requestModels.ReqModelRegisterPhone
import com.hmelikyan.newsletter.data.model.requestModels.ReqModelVerifyPhoneOrEmail
import com.hmelikyan.newsletter.data.repository.AuthRepository
import com.hmelikyan.newsletter.data.root.Result
import com.hmelikyan.newsletter.data.root.ResultFactory
import com.hmelikyan.newsletter.domain.entities.SmsCodeIdDomain
import com.hmelikyan.newsletter.domain.mappers.VerificationCodeIdMapper
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PhoneVerificationUseCaseImpl
@Inject
constructor(
	private val authRepository : AuthRepository,
	private val resultFactory : ResultFactory
) : PhoneVerificationUseCase {

	override suspend fun getVerificationSmsCode(requestModel : ReqModelRegisterPhone) : Flow<Result<SmsCodeIdDomain>> {
		return resultFactory.getResult { authRepository.getSmsCode(requestModel,VerificationCodeIdMapper) }
	}

	override suspend fun verifyPhone(model : ReqModelVerifyPhoneOrEmail) : Flow<Result<Boolean>> {
		return resultFactory.getResult { authRepository.verifyPhone(model) }
	}
}