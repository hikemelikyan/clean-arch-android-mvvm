package com.hmelikyan.newsletter.domain.useCase.phoneVerification

import com.hmelikyan.newsletter.data.model.requestModels.ReqModelRegisterPhone
import com.hmelikyan.newsletter.data.model.requestModels.ReqModelVerifyPhoneOrEmail
import com.hmelikyan.newsletter.data.root.ApiException
import com.hmelikyan.newsletter.data.root.Result
import com.hmelikyan.newsletter.domain.entities.SmsCodeIdDomain
import kotlinx.coroutines.flow.Flow

interface PhoneVerificationUseCase {

	@Throws(ApiException::class)
	suspend fun getVerificationSmsCode(requestModel : ReqModelRegisterPhone) : Flow<Result<SmsCodeIdDomain>>

	@Throws(ApiException::class)
	suspend fun verifyPhone(model : ReqModelVerifyPhoneOrEmail) : Flow<Result<Boolean>>

}