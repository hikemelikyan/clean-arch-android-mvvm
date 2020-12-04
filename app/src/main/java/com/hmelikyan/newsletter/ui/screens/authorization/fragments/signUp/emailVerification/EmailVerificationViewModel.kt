package com.hmelikyan.newsletter.ui.screens.authorization.fragments.signUp.emailVerification

import androidx.lifecycle.MutableLiveData
import com.hmelikyan.newsletter.Application
import com.hmelikyan.newsletter.data.di.NetworkModule
import com.hmelikyan.newsletter.data.model.requestModels.ReqModelRegisterEmail
import com.hmelikyan.newsletter.data.model.requestModels.ReqModelVerifyPhoneOrEmail
import com.hmelikyan.newsletter.domain.useCase.emailVerification.EmailVerificationUseCase
import com.hmelikyan.newsletter.mvvm.vm.BaseViewModel
import com.hmelikyan.newsletter.root.di.RootModule
import com.hmelikyan.newsletter.ui.commands.Commands
import com.hmelikyan.newsletter.ui.screens.authorization.fragments.signUp.emailVerification.di.DaggerEmailVerificationComponent
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