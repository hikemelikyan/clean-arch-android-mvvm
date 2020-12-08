package com.armboldmind.gsport24.ui.screens.authorization.fragments.signUp.emailVerification

import androidx.lifecycle.MutableLiveData
import com.armboldmind.gsport24.Application
import com.armboldmind.gsport24.data.di.NetworkModule
import com.armboldmind.gsport24.data.model.requestModels.ReqModelRegisterEmail
import com.armboldmind.gsport24.data.model.requestModels.ReqModelVerifyPhoneOrEmail
import com.armboldmind.gsport24.domain.useCase.emailVerification.EmailVerificationUseCase
import com.armboldmind.gsport24.mvvm.vm.BaseViewModel
import com.armboldmind.gsport24.root.di.RootModule
import com.armboldmind.gsport24.ui.commands.Commands
import com.armboldmind.gsport24.ui.screens.authorization.fragments.signUp.emailVerification.di.DaggerEmailVerificationComponent
import javax.inject.Inject

class EmailVerificationViewModel : BaseViewModel() {

	@Inject
	lateinit var emailAuthUseCase : EmailVerificationUseCase

	val email = MutableLiveData<String>()
	val code = MutableLiveData<String>()

	val isCodeSent = MutableLiveData<Boolean>()

	init {
		inject()
	}

	override fun inject() {
		DaggerEmailVerificationComponent.builder()
			.networkModule(NetworkModule())
			.rootModule(RootModule(Application.getInstance()))
			.build()
			.inject(this)
	}

	fun getSmsCodeByEmail(codeId:Int) {
		launchDefault {
			email.value?.let {
				val result = emailAuthUseCase.getVerificationCodeByEmail(ReqModelRegisterEmail(codeId,it))
				defaultHandler(result){
					_viewCommands.postValue(Commands.CodeSent(codeId))
					isCodeSent.postValue(true)
				}
			}
		}
	}

	fun verifyEmail(codeId:Int) {
		launchDefault {
			code.value?.let {
				val result = emailAuthUseCase.verifyEmail(ReqModelVerifyPhoneOrEmail(codeId,it))
				defaultHandler(result){
					_viewCommands.postValue(Commands.EmailVerified(codeId))
				}
			}
		}
	}
}