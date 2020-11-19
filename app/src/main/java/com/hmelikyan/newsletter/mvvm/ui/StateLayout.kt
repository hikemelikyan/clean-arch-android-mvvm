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
import com.hmelikyan.newsletter.databinding.LayoutErrorViewBinding
import com.hmelikyan.newsletter.root.R
import com.hmelikyan.newsletter.root.shared.ext.dpToPx
import com.hmelikyan.newsletter.root.shared.ext.hide
import com.hmelikyan.newsletter.root.shared.ext.inflater
import com.hmelikyan.newsletter.root.shared.ext.show

class StateLayout private constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    companion object {
        fun create(context: Context, action: StateLayout.() -> StateLayout) =
            StateLayout(context).run(action)
    }

    /*
     * listener region
     * */
    private val stateObserver = Observer<UIState> { switchState(it) }
    private lateinit var lifecycle: Lifecycle
    private lateinit var uiStateListener: LiveData<UIState>
    /*
     * listener region end
     * */


    /*
     * view region
     * */
    private val previousVisibleView: MutableLiveData<View> by lazy { MutableLiveData() }
    private val viewMap = mutableMapOf<UIState, View>()
    private lateinit var content: View
    /* view region end*/

    /*
    component initialization region
    */
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
        lifecycle.addObserver(ComponentViewLifecycleObserver())
        this
    }
    /*
    component initialization region end
    */

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
        when (state) {
            UIState.INTERNAL_ERROR,UIState.SERVER_ERROR -> {
                if(viewMap[state] == null){
                    viewMap[state] = LayoutErrorViewBinding.inflate(context.inflater()).apply {
                        title.text = context.resources.getString(R.string.default_internal_error_message)
                    }.root.apply {
                        layoutParams = LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT).apply {
                            gravity = Gravity.CENTER
                        }
                    }
                    addView(viewMap[state])
                }
                showView(state)
            }
            UIState.NETWORK_ERROR -> {
                if(viewMap[state] == null) {
                    viewMap[state] = LayoutErrorViewBinding.inflate(context.inflater()).apply {
                        title.text = context.resources.getString(R.string.default_network_error_message)
                    }.root.apply {
                        layoutParams = LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT).apply {
                            gravity = Gravity.CENTER
                        }
                    }
                    addView(viewMap[state])
                }
                showView(state)
            }
            UIState.EMPTY -> {
                Log.d("StateLayout", "switchState: ${state.name}")
            }
            UIState.LOADING -> {
                if (viewMap[state] == null) {
                    viewMap[state] = ProgressBar(context).apply {
                        layoutParams = LayoutParams(25.dpToPx(), 25.dpToPx()).apply {
                            gravity = Gravity.CENTER
                        }
                    }
                    addView(viewMap[state])
                }
                showView(state)
            }
            UIState.SUCCESS -> {
                if(viewMap[state] == null){
                    viewMap[state] = content.apply {
                        layoutParams = LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT)
                    }
                    addView(viewMap[state])
                }
                showView(state)
            }
        }
        previousVisibleView.value?.hide()
    }

    private fun showView(state: UIState){
        previousVisibleView.value = viewMap[state]
        viewMap[state]?.show()
    }

    override fun addView(child: View?) {
        child?.hide()
        super.addView(child)
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