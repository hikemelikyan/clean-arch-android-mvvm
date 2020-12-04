package com.hmelikyan.newsletter.ui.commands

import androidx.annotation.StringRes
import com.hmelikyan.newsletter.mvvm.vm.ViewCommand

sealed class Commands {

	/**
	 * base UI commands
	 * */
	object NetworkError : ViewCommand
	class ShowMessage(@StringRes val resId : Int) : ViewCommand
	class ShowMessageText(val errorMessage : String) : ViewCommand
	object StateLoading : ViewCommand
	object StateEmpty : ViewCommand

	/**
	 * signUp global
	 * */
	class CodeSent(val id : Int) : ViewCommand

	/**
	 * phone verification
	 */
	class PhoneVerified(val codeId:Int) : ViewCommand

	/**
	 * email verification
	 * */
	class EmailVerified(val codeId:Int): ViewCommand
}