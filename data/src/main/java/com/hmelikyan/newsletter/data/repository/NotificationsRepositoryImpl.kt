package com.hmelikyan.newsletter.data.repository

import androidx.paging.PagingData
import com.hmelikyan.newsletter.data.model.requestModels.GetNotificationsListRequestModel
import com.hmelikyan.newsletter.data.model.responseModels.NotificationResponseModel
import com.hmelikyan.newsletter.data.root.Entity
import com.hmelikyan.newsletter.data.root.NetworkHelper
import com.hmelikyan.newsletter.data.services.INotificationsService
import com.hmelikyan.newsletter.root.shared.mapperBase.Mapper
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

    override suspend fun <T : Entity> getNotifications(model: GetNotificationsListRequestModel, mapper: Mapper<NotificationResponseModel, T>): List<T>? {
        return networkHelper.proceed { mService.getNotificationsList(model) }?.data?.map { mapper.invoke(it) }
    }

    override suspend fun getNotificationsList(model: GetNotificationsListRequestModel): Flow<PagingData<NotificationResponseModel>> {
        return networkHelper.withModel(model) {
            paginate { mService.getNotificationsList(model) }
        }
    }
}