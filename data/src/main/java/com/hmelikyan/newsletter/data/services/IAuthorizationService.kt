package com.hmelikyan.newsletter.data.services

import com.hmelikyan.newsletter.data.model.requestModels.ReqModelRegister
import com.hmelikyan.newsletter.data.model.requestModels.ReqModelRegisterEmail
import com.hmelikyan.newsletter.data.model.requestModels.ReqModelRegisterPhone
import com.hmelikyan.newsletter.data.model.requestModels.ReqModelVerifyPhoneOrEmail
import com.hmelikyan.newsletter.data.model.responseModels.root.Response
import com.hmelikyan.newsletter.data.root.IBaseRetrofitService
import retrofit2.http.Body
import retrofit2.http.POST

interface IAuthorizationService : IBaseRetrofitService {

	@POST("api/User/RegisterPhone")
	suspend fun getSmsCode(@Body model : ReqModelRegisterPhone) : Response<Int>

	@POST("api/User/VerifyPhone")
	suspend fun verifyPhoneNumber(@Body model : ReqModelVerifyPhoneOrEmail) : Response<Boolean>

	@POST("api/User/RegisterEmail")
	suspend fun getSmsByEmail(@Body model : ReqModelRegisterEmail) : Response<Boolean>

	@POST("api/User/VerifyEmail")
	suspend fun verifyEmail(@Body model : ReqModelVerifyPhoneOrEmail) : Response<Boolean>

	@POST("/api/User/Register")
	suspend fun registerUser(@Body model:ReqModelRegister) : Response<Any>
}