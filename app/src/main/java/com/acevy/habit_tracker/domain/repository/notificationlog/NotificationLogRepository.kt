package com.acevy.habit_tracker.domain.repository.notificationlog

import com.acevy.habit_tracker.domain.model.notificationlog.NotificationLog
import kotlinx.coroutines.flow.Flow

interface NotificationLogRepository {
    suspend fun insertNotificationLog(notificationLog: NotificationLog): Long
    fun getNotificationLogsByUser(userId: Long): Flow<List<NotificationLog>>
}