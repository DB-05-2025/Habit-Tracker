package com.acevy.habit_tracker.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "habit_stacks")
data class HabitStackEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val habitIds: List<Int>,
    val createdAt: Long = System.currentTimeMillis()
)