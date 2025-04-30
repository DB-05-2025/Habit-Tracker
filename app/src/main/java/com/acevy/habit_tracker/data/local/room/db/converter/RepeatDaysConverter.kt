package com.acevy.habit_tracker.data.local.room.db.converter

import androidx.room.TypeConverter

class RepeatDaysConverter {
    @TypeConverter
    fun fromList(value: List<Int>): String {
        return value.joinToString(",")
    }

    @TypeConverter
    fun toList(value: String): List<Int> {
        return if (value.isEmpty()) emptyList()
        else value.split(",").map { it.toInt() }
    }
}
