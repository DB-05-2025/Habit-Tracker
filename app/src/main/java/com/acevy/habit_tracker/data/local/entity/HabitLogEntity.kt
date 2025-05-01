package com.acevy.habit_tracker.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.acevy.habit_tracker.data.local.room.db.converter.HabitStatusConverter

@Entity(tableName = "habit_logs")
@TypeConverters(HabitStatusConverter::class)
data class HabitLogEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val habitId: Int,
    val date: String, // yyyy-MM-dd
    val status: HabitStatus,
    val note: String? = null,
    val createdAt: Long = System.currentTimeMillis()
)
