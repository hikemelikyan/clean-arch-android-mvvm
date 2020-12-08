package com.armboldmind.gsport24.mvvm.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.armboldmind.gsport24.R
import com.armboldmind.gsport24.data.root.Result
import com.armboldmind.gsport24.data.root.UIState
import com.armboldmind.gsport24.root.utils.SingleLiveData
import com.armboldmind.gsport24.ui.commands.Commands
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

open class BaseViewModel : ViewModel(), CoroutineScope {

	protected val _viewCommands = SingleLiveData<ViewCommand>()
	val viewCommands : LiveData<ViewCommand>
		get() = _viewCommands

	protected val _uiState = SingleLiveData<UIState>().apply { postValue(UIState.LOADING) }
	val uiState : LiveData<UIState>
		get() = _uiState

	override val coroutineContext : CoroutineContext
		get() = Job() + Dispatchers.Default

	protected open fun inject() {}

	protected fun switchUIState(state : UIState) {
		_uiState.postValue(UIState.SUCCESS)
	}

	fun CoroutineScope.launchIO(block : suspend () -> Unit) {
		launch(context = Dispatchers.IO) { block() }
	}

	fun CoroutineScope.launchMain(block : suspend () -> Unit) {
		launch(context = Dispatchers.Main) { block() }
	}

	fun CoroutineScope.launchDefault(block : suspend () -> Unit) {
		launch(context = Dispatchers.Default) { block() }
	}

	suspend fun <T> defaultHandler(result : Flow<Result<T>>, doOnSuccess : (T) -> Unit) {
		result.collect {
			_uiState.postValue(it.uiState)
			when (it.uiState) {
                UIState.SUCCESS -> it.data?.let(doOnSuccess)
                UIState.LOADING -> _viewCommands.postValue(Commands.StateLoading)
                UIState.EMPTY -> _viewCommands.postValue(Commands.StateEmpty)
                UIState.NETWORK_ERROR -> _viewCommands.postValue(Commands.NetworkError)
                UIState.INTERNAL_ERROR -> _viewCommands.postValue(Commands.ShowMessage(R.string.default_internal_error_message))
                UIState.SERVER_ERROR -> _viewCommands.postValue(Commands.ShowMessageText(it.msg ?: ""))
			}
		}
	}
}