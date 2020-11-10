package com.hmelikyan.newsletter.data.repository

import com.hmelikyan.newsletter.data.model.requestModels.GetNotificationsListRequestModel
import com.hmelikyan.newsletter.data.model.responseModels.NotificationModel
import com.hmelikyan.newsletter.data.model.responseModels.root.PaginationResponseModel

interface NotificationsRepository {
    suspend fun getNotifications(model: GetNotificationsListRequestModel): PaginationResponseModel<NotificationModel>
}