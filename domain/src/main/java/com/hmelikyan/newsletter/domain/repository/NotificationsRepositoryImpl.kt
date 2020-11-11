package com.hmelikyan.newsletter.domain.repository

import com.hmelikyan.newsletter.data.model.requestModels.GetNotificationsListRequestModel
import com.hmelikyan.newsletter.data.networkDelegate
import com.hmelikyan.newsletter.data.root.NetworkHelper
import com.hmelikyan.newsletter.data.services.INotificationsService
import com.hmelikyan.newsletter.domain.entities.NotificationDomain
import com.hmelikyan.newsletter.domain.mappers.NotificationResponseToNotificationDomainMapper
import javax.inject.Inject


class NotificationsRepositoryImpl
@Inject
constructor(
    private val networkHelper: NetworkHelper
) : NotificationsRepository {

    private val mService: INotificationsService by networkDelegate()

    override suspend fun getNotifications(model: GetNotificationsListRequestModel): List<NotificationDomain>? {
        return networkHelper.call { mService.getNotificationsList(model) }?.data?.map {
            NotificationResponseToNotificationDomainMapper.invoke(it)
        }
    }
}