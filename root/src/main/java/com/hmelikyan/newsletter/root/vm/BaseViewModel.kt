package com.hmelikyan.newsletter.root.vm

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

open class BaseViewModel : CoroutineScope {



    override val coroutineContext: CoroutineContext
        get() = Job() + Dispatchers.Default

    fun CoroutineScope.launchIO(block: () -> Unit) {
        launch(context = Dispatchers.IO) {

        }
    }

    fun CoroutineScope.launchMain(block: () -> Unit) {
        launch(context = Dispatchers.Main) {

        }
    }

    fun CoroutineScope.launchDefault(block: () -> Unit) {
        launch(context = Dispatchers.Default) {

        }
    }

}