package com.hmelikyan.newsletter.mvvm.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.hmelikyan.newsletter.data.root.UIState
import com.hmelikyan.newsletter.root.shared.utils.SingleLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

open class BaseViewModel : ViewModel(), CoroutineScope {

    protected val _viewCommands = SingleLiveData<ViewCommand>()
    val viewCommands: LiveData<ViewCommand>
        get() = _viewCommands

    protected val _uiState = SingleLiveData<UIState>()
    val uiState: LiveData<UIState>
        get() = _uiState

    override val coroutineContext: CoroutineContext
        get() = Job() + Dispatchers.Default

    fun CoroutineScope.launchIO(block: suspend () -> Unit) {
        launch(context = Dispatchers.IO) { block() }
    }

    fun CoroutineScope.launchMain(block: suspend () -> Unit) {
        launch(context = Dispatchers.Main) { block() }
    }

    fun CoroutineScope.launchDefault(block: suspend () -> Unit) {
        launch(context = Dispatchers.Default) { block() }
    }

}