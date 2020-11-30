package com.hmelikyan.newsletter.data.repository

import androidx.paging.PagingData
import com.hmelikyan.newsletter.data.model.requestModels.GetNotificationsListRequestModel
import com.hmelikyan.newsletter.data.model.responseModels.NotificationResponseModel
import com.hmelikyan.newsletter.data.root.ApiException
import com.hmelikyan.newsletter.data.root.Entity
import com.hmelikyan.newsletter.root.shared.mapperBase.Mapper
import kotlinx.coroutines.flow.Flow
import kotlin.jvm.Throws

interface NotificationsRepository {
    @Throws(ApiException::class)
    suspend fun <T : Entity> getNotifications(model: GetNotificationsListRequestModel,mapper:Mapper<NotificationResponseModel,T>): List<T>?

    suspend fun getNotificationsList(model:GetNotificationsListRequestModel): Flow<PagingData<NotificationResponseModel>>
}