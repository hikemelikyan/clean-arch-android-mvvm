package com.hmelikyan.newsletter.domain.repository

import androidx.paging.PagingData
import com.hmelikyan.newsletter.data.model.requestModels.GetNotificationsListRequestModel
import com.hmelikyan.newsletter.data.model.responseModels.NotificationResponseModel
import com.hmelikyan.newsletter.data.root.NetworkHelper
import com.hmelikyan.newsletter.data.services.INotificationsService
import com.hmelikyan.newsletter.domain.entities.NotificationDomain
import com.hmelikyan.newsletter.domain.mappers.NotificationResponseToNotificationDomainMapper
import kotlinx.coroutines.flow.Flow
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Inject


class NotificationsRepositoryImpl
@Inject
constructor(
    retrofit: Retrofit,
    private val networkHelper: NetworkHelper
) : NotificationsRepository {

    private val mService: INotificationsService = retrofit.create()

    override suspend fun getNotifications(model: GetNotificationsListRequestModel): List<NotificationDomain>? {
        return networkHelper.call { mService.getNotificationsList(model) }?.data?.map {
            NotificationResponseToNotificationDomainMapper.invoke(it)
        }
    }

    override suspend fun getNotificationsList(model: GetNotificationsListRequestModel): Flow<PagingData<NotificationResponseModel>> {
        return networkHelper.withModel(model) {
            paginate { mService.getNotificationsList(model) }
        }
    }
}