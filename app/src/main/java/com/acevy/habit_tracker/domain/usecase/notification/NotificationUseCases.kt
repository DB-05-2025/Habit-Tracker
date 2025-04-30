package com.acevy.habit_tracker.domain.usecase.notification

data class NotificationUseCases(
    val insertNotification: InsertNotificationUseCase,
    val getAllNotifications: GetAllNotificationsUseCase,
    val clearOldNotifications: ClearOldNotificationsUseCase
)
