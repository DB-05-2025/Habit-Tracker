package com.acevy.habit_tracker.domain.repository

import com.acevy.habit_tracker.data.local.entity.NotificationLogEntity
import kotlinx.coroutines.flow.Flow

interface NotificationRepository {
    suspend fun insertNotification(notification: NotificationLogEntity)
    fun getAllNotifications(): Flow<List<NotificationLogEntity>>
    suspend fun clearOldNotifications(beforeTimestamp: Long)
    suspend fun deleteByHabitId(habitId: Int)
}
