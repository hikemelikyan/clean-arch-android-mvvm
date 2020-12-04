package com.hmelikyan.newsletter.data.repository

import com.hmelikyan.newsletter.data.model.requestModels.ReqModelRegister
import com.hmelikyan.newsletter.data.model.requestModels.ReqModelRegisterEmail
import com.hmelikyan.newsletter.data.model.requestModels.ReqModelRegisterPhone
import com.hmelikyan.newsletter.data.model.requestModels.ReqModelVerifyPhoneOrEmail
import com.hmelikyan.newsletter.data.root.Entity
import com.hmelikyan.newsletter.root.mapperBase.Mapper

interface AuthRepository {

	suspend fun <T:Entity> getSmsCode(model:ReqModelRegisterPhone, mapper:Mapper<Int?,T>) : T

	suspend fun verifyPhone(model:ReqModelVerifyPhoneOrEmail) : Boolean?

	suspend fun getSmsCodeByEmail(model:ReqModelRegisterEmail) : Boolean?

	suspend fun verifyEmail(model:ReqModelVerifyPhoneOrEmail) : Boolean?

	suspend fun registerUser(model:ReqModelRegister) : Any?

}