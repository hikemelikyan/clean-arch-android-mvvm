package com.hmelikyan.newsletter.ui.screens.authorization.fragments.signUp.phoneVerification

import androidx.lifecycle.MutableLiveData
import com.hmelikyan.newsletter.Application
import com.hmelikyan.newsletter.data.di.NetworkModule
import com.hmelikyan.newsletter.data.model.requestModels.ReqModelRegisterPhone
import com.hmelikyan.newsletter.data.model.requestModels.ReqModelVerifyPhoneOrEmail
import com.hmelikyan.newsletter.domain.useCase.phoneVerification.PhoneVerificationUseCase
import com.hmelikyan.newsletter.mvvm.vm.BaseViewModel
import com.hmelikyan.newsletter.root.di.RootModule
import com.hmelikyan.newsletter.ui.commands.Commands
import com.hmelikyan.newsletter.ui.screens.authorization.fragments.signUp.phoneVerification.di.DaggerPhoneVerificationComponent
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