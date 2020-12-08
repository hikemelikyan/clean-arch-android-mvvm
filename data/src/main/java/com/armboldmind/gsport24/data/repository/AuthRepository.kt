package com.armboldmind.gsport24.data.repository

import com.armboldmind.gsport24.data.model.requestModels.ReqModelRegister
import com.armboldmind.gsport24.data.model.requestModels.ReqModelRegisterEmail
import com.armboldmind.gsport24.data.model.requestModels.ReqModelRegisterPhone
import com.armboldmind.gsport24.data.model.requestModels.ReqModelVerifyPhoneOrEmail
import com.armboldmind.gsport24.data.root.Entity
import com.armboldmind.gsport24.root.mapperBase.Mapper

interface AuthRepository {

	suspend fun <T:Entity> getSmsCode(model:ReqModelRegisterPhone, mapper:Mapper<Int?,T>) : T

	suspend fun verifyPhone(model:ReqModelVerifyPhoneOrEmail) : Boolean?

	suspend fun getSmsCodeByEmail(model:ReqModelRegisterEmail) : Boolean?

	suspend fun verifyEmail(model:ReqModelVerifyPhoneOrEmail) : Boolean?

	suspend fun registerUser(model:ReqModelRegister) : Any?

}