package com.armboldmind.gsport24.ui.screens.authorization.fragments.signUp.phoneVerification

import androidx.lifecycle.MutableLiveData
import com.armboldmind.gsport24.Application
import com.armboldmind.gsport24.data.di.NetworkModule
import com.armboldmind.gsport24.data.model.requestModels.ReqModelRegisterPhone
import com.armboldmind.gsport24.data.model.requestModels.ReqModelVerifyPhoneOrEmail
import com.armboldmind.gsport24.domain.useCase.phoneVerification.PhoneVerificationUseCase
import com.armboldmind.gsport24.mvvm.vm.BaseViewModel
import com.armboldmind.gsport24.root.di.RootModule
import com.armboldmind.gsport24.ui.commands.Commands
import com.armboldmind.gsport24.ui.screens.authorization.fragments.signUp.phoneVerification.di.DaggerPhoneVerificationComponent
import javax.inject.Inject

class PhoneVerificationViewModel : BaseViewModel() {

	@Inject
	lateinit var verificationUseCase : PhoneVerificationUseCase

	val phoneNumber = MutableLiveData<String>()
	val phoneCode = MutableLiveData<String>()

	val codeId = MutableLiveData<Int>()

	init {
		inject()
	}

	override fun inject() {
		DaggerPhoneVerificationComponent.builder()
			.networkModule(NetworkModule())
			.rootModule(RootModule(Application.getInstance()))
			.build()
			.inject(this)
	}

	fun getSmsCode() {
		launchDefault {
			phoneNumber.value?.let {
				val result = verificationUseCase.getVerificationSmsCode(ReqModelRegisterPhone(it))
				defaultHandler(result) {
					_viewCommands.postValue(Commands.CodeSent(it.id))
					codeId.postValue(it.id)
				}
			}
		}
	}

	fun verifyPhoneNumber() {
		launchDefault {
			phoneCode.value?.let {
				val result = verificationUseCase.verifyPhone(ReqModelVerifyPhoneOrEmail(codeId.value ?: 0, it))
				defaultHandler(result) {
					_viewCommands.postValue(Commands.PhoneVerified(codeId.value ?: 0 ))
				}
			}
		}
	}
}