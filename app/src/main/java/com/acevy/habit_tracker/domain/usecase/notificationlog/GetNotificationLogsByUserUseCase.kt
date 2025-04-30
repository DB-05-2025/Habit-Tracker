package com.acevy.habit_tracker.domain.usecase.notificationlog

import com.acevy.habit_tracker.domain.repository.notificationlog.NotificationLogRepository

class GetNotificationLogsByUserUseCase(private val repo: NotificationLogRepository) {
    operator fun invoke(userId: Long) = repo.getNotificationLogsByUser(userId)
}