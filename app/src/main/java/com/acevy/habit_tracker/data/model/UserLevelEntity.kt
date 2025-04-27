package com.acevy.habit_tracker.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_level")
data class UserLevelEntity(
    @PrimaryKey(autoGenerate = true) val userLevelId: Long = 0,
    val userId: Long,
    val level: Int,
    val currentXp: Int,
    val updatedAt: Long = System.currentTimeMillis(),
)