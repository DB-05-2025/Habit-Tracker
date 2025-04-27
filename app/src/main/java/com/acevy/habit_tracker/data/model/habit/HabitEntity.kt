package com.acevy.habit_tracker.data.model.habit

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "habit")
data class HabitEntity(
    @PrimaryKey(autoGenerate = true) val habitId: Long = 0,
    val userId: Long,
    val title: String,
    val note: String?,
    val repeatDays: List<Int>?, // 0=Sunday, 6=Saturday
    val reminderTime: String?, // format HH:mm
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis(),
)