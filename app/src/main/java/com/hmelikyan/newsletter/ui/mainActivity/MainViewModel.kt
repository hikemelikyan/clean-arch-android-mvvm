package com.hmelikyan.newsletter.ui.mainActivity

import android.util.Log
import com.hmelikyan.newsletter.Application
import com.hmelikyan.newsletter.data.model.requestModels.GetNotificationsListRequestModel
import com.hmelikyan.newsletter.data.root.UIState
import com.hmelikyan.newsletter.domain.useCase.GetNotificationsListUseCase
import com.hmelikyan.newsletter.mvvm.vm.BaseViewModel
import com.hmelikyan.newsletter.root.di.RootModule
import com.hmelikyan.newsletter.root.shared.utils.SharedPreferencesHelper
import com.hmelikyan.newsletter.ui.commands.Commands
import com.hmelikyan.newsletter.ui.mainActivity.di.DaggerMainActivityComponent
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class MainViewModel : BaseViewModel() {

    @Inject
    lateinit var getListUseCase: GetNotificationsListUseCase

    @Inject
    lateinit var mShared:SharedPreferencesHelper

    init {
        inject()
    }

    fun getTest() {
        launchDefault {
            val response = getListUseCase.getNotificationList(getDefaultModel())
            response.collect {
                _uiState.postValue(it.uiState)
                when (it.uiState) {
                    UIState.SUCCESS -> {
                        _viewCommands.postValue(Commands.TestViewCommand(it.data))
                    }
                    UIState.INTERNAL_ERROR -> {
                        _viewCommands.postValue(Commands.ShowMessageTextViewCommand("Internal error"))
                    }
                    UIState.LOADING -> {
                        _viewCommands.postValue(Commands.ShowLoadingViewCommand())
                    }
                    UIState.SERVER_ERROR -> {
                        _viewCommands.postValue(Commands.ShowMessageTextViewCommand(it.msg.toString()))
                    }
                    UIState.EMPTY -> {
                        _viewCommands.postValue(Commands.TestViewCommand(it.data))
                    }
                    UIState.NETWORK_ERROR -> {
                        _viewCommands.postValue(Commands.NetworkErrorViewCommand())
                    }
                }
            }
        }
    }

    fun getList(){
        launchDefault {
            val response = getListUseCase.getNotificationsList(getDefaultModel())
            response.collect {
                Log.d("UIState", "getList: ${it.uiState.name}")
                _uiState.postValue(it.uiState)
                it.data?.let {
                    _viewCommands.postValue(Commands.PagingViewCommand(it))
                }
            }
        }
    }

    private fun getDefaultModel(): GetNotificationsListRequestModel {
        return GetNotificationsListRequestModel(
            1, 10, false, null
        )
    }

    override fun inject() {
        DaggerMainActivityComponent.builder()
            .rootModule(RootModule(Application.getInstance()))
            .build()
            .inject(this)
    }

}