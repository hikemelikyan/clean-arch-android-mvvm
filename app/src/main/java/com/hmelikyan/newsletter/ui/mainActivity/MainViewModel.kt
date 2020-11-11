package com.hmelikyan.newsletter.ui.mainActivity

import com.hmelikyan.newsletter.data.model.requestModels.GetNotificationsListRequestModel
import com.hmelikyan.newsletter.data.root.UIState
import com.hmelikyan.newsletter.domain.useCase.GetNotificationsListUseCase
import com.hmelikyan.newsletter.mvvm.vm.BaseViewModel
import com.hmelikyan.newsletter.ui.commands.Commands
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class MainViewModel : BaseViewModel(){

    @Inject
    lateinit var getListUseCase:GetNotificationsListUseCase

    init {
        launchDefault {
            val response = getListUseCase.getNotificationList(getDefaultModel())
            response.collect {
                if(it.uiState == UIState.SUCCESS){
                    _viewCommands.postValue(Commands.TestViewCommand(it.data))
                }
            }
        }
    }


    private fun getDefaultModel():GetNotificationsListRequestModel{
        return GetNotificationsListRequestModel(
            1,10,false,null
        )
    }

}