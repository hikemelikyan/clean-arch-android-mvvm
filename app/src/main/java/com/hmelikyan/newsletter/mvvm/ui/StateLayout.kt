package com.hmelikyan.newsletter.mvvm.ui

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import com.hmelikyan.newsletter.data.root.UIState
import com.hmelikyan.newsletter.root.shared.ext.dpToPx
import com.hmelikyan.newsletter.root.shared.ext.hide
import com.hmelikyan.newsletter.root.shared.ext.show

class StateLayout private constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    companion object {
        fun create(context: Context, action: StateLayout.() -> StateLayout) =
            StateLayout(context).run(action)
    }

    private val stateObserver = Observer<UIState> { switchState(it) }
    private lateinit var lifecycle: Lifecycle
    private lateinit var uiStateListener: LiveData<UIState>
    private lateinit var content: View
    private val progressBar: ProgressBar by lazy {
        ProgressBar(context).apply {
            layoutParams = LayoutParams(25.dpToPx(), 25.dpToPx()).apply {
                gravity = Gravity.CENTER
            }
        }
    }
    private val previousVisibleView: MutableLiveData<View> by lazy { MutableLiveData() }

    fun withComponentActivity(activity: AppCompatActivity) {
        checkLifecycle { this.lifecycle = activity.lifecycle }
    }

    fun withComponentFragment(fragment: Fragment) {
        checkLifecycle { this.lifecycle = fragment.lifecycle }
    }

    fun withStateListener(uiStateListener: LiveData<UIState>) {
        checkStateListener { this.uiStateListener = uiStateListener }
    }

    fun withContent(content: View) {
        checkContent { this.content = content }
    }

    fun attach() = run {
        check(::uiStateListener.isInitialized) { "UIState listener is not initialized" }
        check(::lifecycle.isInitialized) { "Component lifecycle is not initialized" }
        check(::content.isInitialized) { "Content is not initialized" }
        addView(
            content,
            LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        )
        lifecycle.addObserver(ComponentViewLifecycleObserver())
        this
    }

    private fun checkLifecycle(action: () -> Unit) {
        check(!::lifecycle.isInitialized) { "Component lifecycle is already initialized." }
        action()
    }

    private fun checkContent(action: () -> Unit) {
        check(!::content.isInitialized) { "Content is already initialized." }
        action()
    }

    private fun checkStateListener(action: () -> Unit) {
        check(!::uiStateListener.isInitialized) { "UIState listener is already initialized." }
        action()
    }

    private fun switchState(state: UIState) {
        previousVisibleView.value?.hide()
        when (state) {
            UIState.NETWORK_ERROR -> {
                Log.d("StateLayout", "switchState: ${state.name}")
            }
            UIState.SUCCESS -> {
                Log.d("StateLayout", "switchState: ${state.name}")
            }
            UIState.INTERNAL_ERROR -> {
                Log.d("StateLayout", "switchState: ${state.name}")
            }
            UIState.LOADING -> {
                if (::progressBar.isInitialized) {
                    progressBar.show()
                } else {
                    addView(progressBar)
                }
                previousVisibleView.value = progressBar
            }
            UIState.SERVER_ERROR -> {
                Log.d("StateLayout", "switchState: ${state.name}")
            }
            UIState.EMPTY -> {
                Log.d("StateLayout", "switchState: ${state.name}")
            }
        }
    }

    private inner class ComponentViewLifecycleObserver : LifecycleObserver {

        @OnLifecycleEvent(Lifecycle.Event.ON_START)
        fun registerObserver() {
            uiStateListener.observeForever(stateObserver)
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
        fun unregisterObserver() {
            uiStateListener.removeObserver(stateObserver)
        }

    }

}