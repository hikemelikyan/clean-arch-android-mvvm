package com.hmelikyan.newsletter.domain.repository

import androidx.paging.PagingData
import com.hmelikyan.newsletter.data.model.requestModels.GetNotificationsListRequestModel
import com.hmelikyan.newsletter.data.model.responseModels.NotificationResponseModel
import com.hmelikyan.newsletter.data.root.ApiException
import com.hmelikyan.newsletter.domain.entities.NotificationDomain
import kotlinx.coroutines.flow.Flow
import kotlin.jvm.Throws

interface NotificationsRepository {
    @Throws(ApiException::class)
    suspend fun getNotifications(model: GetNotificationsListRequestModel): List<NotificationDomain>?

    suspend fun getNotificationsList(model:GetNotificationsListRequestModel): Flow<PagingData<NotificationResponseModel>>
}