package com.acevy.habit_tracker.data.mapper.notificationlog

import com.acevy.habit_tracker.data.model.notificationlog.NotificationLogEntity
import com.acevy.habit_tracker.domain.model.notificationlog.NotificationLog

fun NotificationLog.toEntity() = NotificationLogEntity(
    notificationId = notificationId,
    userId = userId,
    habitId = habitId,
    title = title,
    message = message,
    sentAt = sentAt,
    type = type
)

fun NotificationLogEntity.toDomain() = NotificationLog(
    notificationId = notificationId,
    userId = userId,
    habitId = habitId,
    title = title,
    message = message,
    sentAt = sentAt,
    type = type
)