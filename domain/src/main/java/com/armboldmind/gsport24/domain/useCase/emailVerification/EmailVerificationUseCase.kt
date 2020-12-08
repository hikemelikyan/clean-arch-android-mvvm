package com.armboldmind.gsport24.domain.useCase.emailVerification

import com.armboldmind.gsport24.data.model.requestModels.ReqModelRegisterEmail
import com.armboldmind.gsport24.data.model.requestModels.ReqModelVerifyPhoneOrEmail
import com.armboldmind.gsport24.data.root.Result
import kotlinx.coroutines.flow.Flow

interface EmailVerificationUseCase {

	suspend fun getVerificationCodeByEmail(model:ReqModelRegisterEmail) : Flow<Result<Boolean>>

	suspend fun verifyEmail(model:ReqModelVerifyPhoneOrEmail) : Flow<Result<Boolean>>

}