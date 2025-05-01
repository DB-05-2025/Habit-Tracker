package com.acevy.habit_tracker.domain.usecase.notification

import com.acevy.habit_tracker.domain.repository.NotificationRepository

class ClearOldNotificationsUseCase(
    private val repository: NotificationRepository
) {
    suspend operator fun invoke(beforeTimestamp: Long) {
        repository.clearOldNotifications(beforeTimestamp)
    }
}