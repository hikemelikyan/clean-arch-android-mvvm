package com.hmelikyan.newsletter.domain.useCase.emailVerification

import com.hmelikyan.newsletter.data.model.requestModels.ReqModelRegisterEmail
import com.hmelikyan.newsletter.data.model.requestModels.ReqModelVerifyPhoneOrEmail
import com.hmelikyan.newsletter.data.root.Result
import kotlinx.coroutines.flow.Flow

interface EmailVerificationUseCase {

	suspend fun getVerificationCodeByEmail(model:ReqModelRegisterEmail) : Flow<Result<Boolean>>

	suspend fun verifyEmail(model:ReqModelVerifyPhoneOrEmail) : Flow<Result<Boolean>>

}