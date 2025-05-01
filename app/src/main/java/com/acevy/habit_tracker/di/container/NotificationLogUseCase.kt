package com.acevy.habit_tracker.di.container

import com.acevy.habit_tracker.domain.usecase.notificationlog.GetNotificationLogsByUserUseCase
import com.acevy.habit_tracker.domain.usecase.notificationlog.InsertNotificationLogUseCase

data class NotificationLogUseCases(
    val insertNotificationLogUseCase: InsertNotificationLogUseCase,
    val getNotificationLogsByUserUseCase: GetNotificationLogsByUserUseCase
)