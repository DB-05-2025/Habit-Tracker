package com.acevy.habit_tracker.data.repository.notificationlog

import com.acevy.habit_tracker.data.local.dao.notificationlog.NotificationLogDao
import com.acevy.habit_tracker.data.mapper.notificationlog.toDomain
import com.acevy.habit_tracker.data.mapper.notificationlog.toEntity
import com.acevy.habit_tracker.domain.model.notificationlog.NotificationLog
import com.acevy.habit_tracker.domain.repository.notificationlog.NotificationLogRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class NotificationLogRepositoryImpl(private val dao: NotificationLogDao) :
    NotificationLogRepository {
    override suspend fun insertNotificationLog(notificationLog: NotificationLog): Long =
        dao.insertNotificationLog(notificationLog.toEntity())

    override fun getNotificationLogsByUser(userId: Long): Flow<List<NotificationLog>> =
        dao.getNotificationLogsByUser(userId).map { list -> list.map { it.toDomain() } }
}