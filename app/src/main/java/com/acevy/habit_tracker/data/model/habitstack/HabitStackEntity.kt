package com.acevy.habit_tracker.data.model.habitstack

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "habit_stack")
data class HabitStackEntity(
    @PrimaryKey(autoGenerate = true) val habitStackId: Long = 0,
    val userId: Long,
    val primaryHabitId: Long,
    val stackedHabitId: Long,
    val createdAt: Long = System.currentTimeMillis(),
)