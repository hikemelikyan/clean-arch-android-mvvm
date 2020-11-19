package com.hmelikyan.newsletter.domain.repository

import com.hmelikyan.newsletter.data.model.requestModels.GetNotificationsListRequestModel
import com.hmelikyan.newsletter.data.root.ApiException
import com.hmelikyan.newsletter.domain.entities.NotificationDomain
import kotlin.jvm.Throws

interface NotificationsRepository {
    @Throws(ApiException::class)
    suspend fun getNotifications(model: GetNotificationsListRequestModel): List<NotificationDomain>?
}