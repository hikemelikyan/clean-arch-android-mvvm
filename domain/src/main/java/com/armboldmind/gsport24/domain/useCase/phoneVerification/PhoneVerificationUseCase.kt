package com.armboldmind.gsport24.domain.useCase.phoneVerification

import com.armboldmind.gsport24.data.model.requestModels.ReqModelRegisterPhone
import com.armboldmind.gsport24.data.model.requestModels.ReqModelVerifyPhoneOrEmail
import com.armboldmind.gsport24.data.root.ApiException
import com.armboldmind.gsport24.data.root.Result
import com.armboldmind.gsport24.domain.entities.SmsCodeIdDomain
import kotlinx.coroutines.flow.Flow

interface PhoneVerificationUseCase {

	@Throws(ApiException::class)
	suspend fun getVerificationSmsCode(requestModel : ReqModelRegisterPhone) : Flow<Result<SmsCodeIdDomain>>

	@Throws(ApiException::class)
	suspend fun verifyPhone(model : ReqModelVerifyPhoneOrEmail) : Flow<Result<Boolean>>

}