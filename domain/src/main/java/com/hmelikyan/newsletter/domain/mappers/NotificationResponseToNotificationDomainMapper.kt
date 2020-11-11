package com.hmelikyan.newsletter.domain.mappers

import com.hmelikyan.newsletter.data.model.responseModels.NotificationResponseModel
import com.hmelikyan.newsletter.domain.entities.NotificationDomain
import com.hmelikyan.newsletter.root.shared.mapperBase.Mapper

object NotificationResponseToNotificationDomainMapper:Mapper<NotificationResponseModel,NotificationDomain> {
    override suspend fun invoke(input: NotificationResponseModel): NotificationDomain {
        return with(input){
            NotificationDomain(
                id = id ?: 0,
                title = title ?: "",
                description = body ?: "",
                date = receiveDate ?: "",
                isOpened = opened ?: false,
                notificationType = 0,
                tripId = tripId ?: 0,
                isTripStarted = false
            )
        }
    }
}