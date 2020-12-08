package com.armboldmind.gsport24.data.services

import com.armboldmind.gsport24.data.model.requestModels.ReqModelRegister
import com.armboldmind.gsport24.data.model.requestModels.ReqModelRegisterEmail
import com.armboldmind.gsport24.data.model.requestModels.ReqModelRegisterPhone
import com.armboldmind.gsport24.data.model.requestModels.ReqModelVerifyPhoneOrEmail
import com.armboldmind.gsport24.data.model.responseModels.root.Response
import com.armboldmind.gsport24.data.root.IBaseRetrofitService
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