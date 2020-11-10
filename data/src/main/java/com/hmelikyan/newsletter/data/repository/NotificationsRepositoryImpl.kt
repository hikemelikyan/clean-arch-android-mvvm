package com.hmelikyan.newsletter.data.repository

import com.hmelikyan.newsletter.data.model.requestModels.GetNotificationsListRequestModel
import com.hmelikyan.newsletter.data.model.responseModels.NotificationModel
import com.hmelikyan.newsletter.data.model.responseModels.root.PaginationResponseModel
import com.hmelikyan.newsletter.data.networkDelegate
import com.hmelikyan.newsletter.data.root.NetworkHelper
import com.hmelikyan.newsletter.data.root.Result
import javax.inject.Inject


class NotificationsRepositoryImpl
@Inject
constructor(
    private val networkHelper: NetworkHelper
) : NotificationsRepository {

    private val mService: INotificationsService by networkDelegate()

    override suspend fun getNotifications(model: GetNotificationsListRequestModel) =
        networkHelper.call { mService.getNotificationsList(model) }

}