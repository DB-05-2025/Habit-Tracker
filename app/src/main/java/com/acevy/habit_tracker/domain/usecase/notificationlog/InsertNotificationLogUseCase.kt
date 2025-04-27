package com.acevy.habit_tracker.domain.usecase.notificationlog

import com.acevy.habit_tracker.domain.model.notificationlog.NotificationLog
import com.acevy.habit_tracker.domain.repository.notificationlog.NotificationLogRepository

class InsertNotificationLogUseCase(private val repo: NotificationLogRepository) {
    suspend operator fun invoke(notificationLog: NotificationLog): Long =
        repo.insertNotificationLog(notificationLog)
}