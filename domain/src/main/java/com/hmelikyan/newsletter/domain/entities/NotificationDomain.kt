package com.hmelikyan.newsletter.domain.entities

data class NotificationDomain (
    val id: Int,
    val title: String,
    val description: String,
    val date: String,
    val isOpened: Boolean,
    val notificationType: Int,
    val tripId: Int,
    val isTripStarted: Boolean
)