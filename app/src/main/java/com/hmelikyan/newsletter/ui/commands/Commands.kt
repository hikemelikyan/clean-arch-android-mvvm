package com.hmelikyan.newsletter.ui.commands

import androidx.annotation.StringRes
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

}