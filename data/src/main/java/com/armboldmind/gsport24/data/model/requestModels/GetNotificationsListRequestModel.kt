package com.armboldmind.gsport24.data.model.requestModels

class GetNotificationsListRequestModel(
    pageNumber: Int,
    pageSize: Int,
    descending: Boolean = true,
    isOpened:Boolean?
) : PaginationRequestModel(pageNumber, pageSize, descending)