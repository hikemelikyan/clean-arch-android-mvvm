package com.hmelikyan.newsletter.domain

import com.hmelikyan.newsletter.data.model.requestModels.GetNotificationsListRequestModel
import com.hmelikyan.newsletter.data.model.responseModels.NotificationModel
import com.hmelikyan.newsletter.data.repository.NotificationsRepository
import com.hmelikyan.newsletter.domain.useCase.UseCase
import javax.inject.Inject

class FirstTestUseCase @Inject constructor(
    private val notificationsRepository: NotificationsRepository
) : UseCase<GetNotificationsListRequestModel, NotificationModel> {

    override suspend fun exec(requestParams: GetNotificationsListRequestModel): NotificationModel {
        return notificationsRepository.getNotifications(requestParams)
    }

}