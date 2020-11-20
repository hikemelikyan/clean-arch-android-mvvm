package com.hmelikyan.newsletter.domain.useCase

import androidx.paging.PagingData
import com.hmelikyan.newsletter.data.model.requestModels.GetNotificationsListRequestModel
import com.hmelikyan.newsletter.data.model.responseModels.NotificationResponseModel
import com.hmelikyan.newsletter.data.root.ApiException
import com.hmelikyan.newsletter.data.root.Result
import com.hmelikyan.newsletter.domain.entities.NotificationDomain
import kotlinx.coroutines.flow.Flow
import kotlin.jvm.Throws

interface GetNotificationsListUseCase  {

    @Throws(ApiException::class)
    suspend fun getNotificationList(model:GetNotificationsListRequestModel): Flow<Result<List<NotificationDomain>>>

    suspend fun getNotificationsList(model: GetNotificationsListRequestModel) : Flow<Result<PagingData<NotificationResponseModel>>>

}