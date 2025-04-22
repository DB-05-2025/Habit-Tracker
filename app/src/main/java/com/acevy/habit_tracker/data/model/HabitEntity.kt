package com.acevy.habit_tracker.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "habit")
data class HabitEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val userId: Long,
    val title: String,
    val description: String,
    val goalType: String,              // daily, weekly, etc
    val category: String,              // new
    val startDate: String,             // new
    val endDate: String?,              // new
    val reminderTime: String?,         // HH:mm
    val reminderType: String?,         // daily, weekly, monthly
    val reminderDays: List<Int>?,      // [1, 3, 5] or [1, 15]
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis(),
)
