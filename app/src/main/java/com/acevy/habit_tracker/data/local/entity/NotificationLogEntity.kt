package com.acevy.habit_tracker.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notification_logs")
data class NotificationLogEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val habitId: Int,
    val habitTitle: String,
    val habitEmoji: String = "⏰", // Default emoji jika tidak ada
    val message: String,
    val timestamp: Long,
    val isActive: Boolean, // Apakah pengingat akan terjadi di masa depan (true) atau sudah terjadi (false)
    val scheduledTime: Long,// Waktu notifikasi dijadwalkan
    val habitUpdatedAt: Long
)
