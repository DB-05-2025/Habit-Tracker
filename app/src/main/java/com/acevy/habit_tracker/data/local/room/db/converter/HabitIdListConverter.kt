package com.acevy.habit_tracker.data.local.room.db.converter

import androidx.room.TypeConverter

class HabitIdListConverter {
    @TypeConverter
    fun fromList(value: List<Int>): String = value.joinToString(",")

    @TypeConverter
    fun toList(value: String): List<Int> =
        if (value.isEmpty()) emptyList() else value.split(",").map { it.toInt() }
}
