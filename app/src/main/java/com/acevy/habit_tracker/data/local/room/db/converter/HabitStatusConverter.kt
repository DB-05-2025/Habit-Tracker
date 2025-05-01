package com.acevy.habit_tracker.data.local.room.db.converter

import androidx.room.TypeConverter
import com.acevy.habit_tracker.data.local.entity.HabitStatus

class HabitStatusConverter {
    @TypeConverter
    fun fromStatus(status: HabitStatus): String = status.name

    @TypeConverter
    fun toStatus(value: String): HabitStatus = HabitStatus.valueOf(value)
}
