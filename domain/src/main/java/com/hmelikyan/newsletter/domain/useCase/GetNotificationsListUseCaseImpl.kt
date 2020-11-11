package com.hmelikyan.newsletter.domain.useCase

import com.hmelikyan.newsletter.data.model.requestModels.GetNotificationsListRequestModel
import com.hmelikyan.newsletter.data.root.ApiException
import com.hmelikyan.newsletter.data.root.Result
import com.hmelikyan.newsletter.data.root.UIState
import com.hmelikyan.newsletter.domain.entities.NotificationDomain
import com.hmelikyan.newsletter.domain.repository.NotificationsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetNotificationsListUseCaseImpl
@Inject
constructor(
    private val notificationsRepository: NotificationsRepository
) : GetNotificationsListUseCase {

    override suspend fun getNotificationList(model: GetNotificationsListRequestModel): Flow<Result<List<NotificationDomain>>> {
        return flow {
            emit(Result.makeLoadingResult())
            try {
                val result = notificationsRepository.getNotifications(model)
                if (result.isNullOrEmpty()) {
                    emit(Result.makeEmptyResult<List<NotificationDomain>>(null))
                }else{
                    emit(Result.makeSuccessResult(result))
                }
            } catch (e: ApiException) {
                emit(Result.makeErrorResult<List<NotificationDomain>>(e.state, e.message, e))
            } catch (e: Exception) {
                emit(Result.makeErrorResult<List<NotificationDomain>>(UIState.INTERNAL_ERROR))
            }
        }
    }

}