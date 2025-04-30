package com.acevy.habit_tracker.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_progress")
data class UserProgressEntity(
    @PrimaryKey val id: Int = 1, // Singleton, hanya satu user
    val level: Int = 1,
    val currentExp: Int = 0,
    val updatedAt: Long = System.currentTimeMillis()
)
