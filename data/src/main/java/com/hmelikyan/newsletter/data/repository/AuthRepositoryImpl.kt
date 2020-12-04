package com.hmelikyan.newsletter.data.repository

import com.hmelikyan.newsletter.data.model.requestModels.ReqModelRegister
import com.hmelikyan.newsletter.data.model.requestModels.ReqModelRegisterEmail
import com.hmelikyan.newsletter.data.model.requestModels.ReqModelRegisterPhone
import com.hmelikyan.newsletter.data.model.requestModels.ReqModelVerifyPhoneOrEmail
import com.hmelikyan.newsletter.data.root.Entity
import com.hmelikyan.newsletter.data.root.NetworkHelper
import com.hmelikyan.newsletter.data.services.IAuthorizationService
import com.hmelikyan.newsletter.root.mapperBase.Mapper
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Inject

class AuthRepositoryImpl
@Inject
constructor(
	private val networkHelper : NetworkHelper,
	retrofit : Retrofit
) : AuthRepository {

	private val mService : IAuthorizationService = retrofit.create()

	override suspend fun <T : Entity> getSmsCode(model : ReqModelRegisterPhone, mapper : Mapper<Int?, T>) : T {
		return mapper.invoke(networkHelper.proceed { mService.getSmsCode(model) })
	}

	override suspend fun verifyPhone(model : ReqModelVerifyPhoneOrEmail) : Boolean? {
		return networkHelper.proceed { mService.verifyPhoneNumber(model) }
	}

	override suspend fun getSmsCodeByEmail(model : ReqModelRegisterEmail) : Boolean? {
		return networkHelper.proceed { mService.getSmsByEmail(model) }
	}

	override suspend fun verifyEmail(model : ReqModelVerifyPhoneOrEmail) : Boolean? {
		return networkHelper.proceed { mService.verifyEmail(model) }
	}

	override suspend fun registerUser(model : ReqModelRegister) : Any? {
		return networkHelper.proceed { mService.registerUser(model) }
	}

}