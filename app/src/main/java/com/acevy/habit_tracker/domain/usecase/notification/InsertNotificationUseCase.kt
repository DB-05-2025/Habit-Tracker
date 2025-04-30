package com.acevy.habit_tracker.domain.usecase.notification

import com.acevy.habit_tracker.data.local.entity.NotificationLogEntity
import com.acevy.habit_tracker.domain.repository.NotificationRepository

class InsertNotificationUseCase(
    private val repository: NotificationRepository
) {
    suspend operator fun invoke(notification: NotificationLogEntity) {
        repository.insertNotification(notification)
    }
}
