package com.acevy.habit_tracker.data.repository

import com.acevy.habit_tracker.data.local.entity.NotificationLogEntity
import com.acevy.habit_tracker.data.local.room.dao.NotificationLogDao
import com.acevy.habit_tracker.domain.repository.NotificationRepository
import kotlinx.coroutines.flow.Flow

class NotificationRepositoryImpl(
    private val dao: NotificationLogDao
) : NotificationRepository {

    override suspend fun insertNotification(notification: NotificationLogEntity) {
        dao.insertNotification(notification)
    }

    override fun getAllNotifications(): Flow<List<NotificationLogEntity>> {
        return dao.getAllNotifications()
    }

    override suspend fun clearOldNotifications(beforeTimestamp: Long) {
        dao.clearNotificationsBefore(beforeTimestamp)
    }
}
