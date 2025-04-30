package com.acevy.habit_tracker.data.model.notificationlog

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notification_log")
data class NotificationLogEntity(
    @PrimaryKey(autoGenerate = true) val notificationId: Long = 0,
    val userId: Long,
    val habitId: Long?,
    val title: String,
    val message: String,
    val sentAt: Long,
    val type: String, // user_reminder, auto_reminder
)