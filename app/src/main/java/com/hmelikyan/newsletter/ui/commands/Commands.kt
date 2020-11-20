package com.hmelikyan.newsletter.ui.commands

import androidx.annotation.StringRes
import androidx.paging.PagingData
import com.hmelikyan.newsletter.data.model.responseModels.NotificationResponseModel
import com.hmelikyan.newsletter.domain.entities.NotificationDomain
import com.hmelikyan.newsletter.mvvm.vm.ViewCommand

sealed class Commands {

    /**
     * base UI commands
     * */

    class NetworkErrorViewCommand : ViewCommand
    class ShowMessageViewCommand(@StringRes val resId: Int) : ViewCommand
    class ShowMessageTextViewCommand(val errorMessage: String) : ViewCommand

    /**
     * main activity
     * */
    class TestViewCommand(val list: List<NotificationDomain>?) : ViewCommand
    class ShowLoadingViewCommand() : ViewCommand
    class PagingViewCommand(val list:PagingData<NotificationResponseModel>) : ViewCommand

}