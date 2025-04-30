package com.acevy.habit_tracker.data.model.userlevel

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_level")
data class UserLevelEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val userId: Long,
    val level: Int = 1,
    val currentXp: Int = 0,
    val updatedAt: Long = System.currentTimeMillis()
)