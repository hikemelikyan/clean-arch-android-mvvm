package com.hmelikyan.newsletter.domain.useCase

import androidx.paging.PagingData
import com.hmelikyan.newsletter.data.model.requestModels.GetNotificationsListRequestModel
import com.hmelikyan.newsletter.data.model.responseModels.NotificationResponseModel
import com.hmelikyan.newsletter.data.root.ApiException
import com.hmelikyan.newsletter.data.root.Result
import com.hmelikyan.newsletter.data.root.ResultFactory
import com.hmelikyan.newsletter.data.root.UIState
import com.hmelikyan.newsletter.domain.entities.NotificationDomain
import com.hmelikyan.newsletter.domain.repository.NotificationsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetNotificationsListUseCaseImpl
@Inject
constructor(
    private val notificationsRepository: NotificationsRepository,
    private val resultFactory: ResultFactory
) : GetNotificationsListUseCase {

    override suspend fun getNotificationList(model: GetNotificationsListRequestModel): Flow<Result<List<NotificationDomain>>> {
        return resultFactory.getListResult { notificationsRepository.getNotifications(model) }
    }

    override suspend fun getNotificationsList(model: GetNotificationsListRequestModel): Flow<Result<PagingData<NotificationResponseModel>>> {
        return resultFactory.getPagingResult { notificationsRepository.getNotificationsList(model) }
    }
}