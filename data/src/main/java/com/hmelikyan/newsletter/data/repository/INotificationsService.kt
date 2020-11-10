package com.hmelikyan.newsletter.data.repository

import com.hmelikyan.newsletter.data.model.requestModels.GetNotificationsListRequestModel
import com.hmelikyan.newsletter.data.model.responseModels.NotificationModel
import com.hmelikyan.newsletter.data.model.responseModels.root.PaginationResponseModel
import com.hmelikyan.newsletter.data.model.responseModels.root.Response
import com.hmelikyan.newsletter.data.root.IBaseRetrofitService
import retrofit2.http.Body
import retrofit2.http.POST

interface INotificationsService: IBaseRetrofitService {

    @POST("api/Notification/GetMobileNotifications")
    suspend fun getNotificationsList(@Body getNotificationsListRequestModel: GetNotificationsListRequestModel): Response<PaginationResponseModel<NotificationModel>>

}