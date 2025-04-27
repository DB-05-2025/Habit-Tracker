package com.acevy.habit_tracker.data.local.database

import androidx.room.TypeConverter

class Converters {

    @TypeConverter
    fun fromIntList(list: List<Int>?): String? {
        return list?.joinToString(separator = ",")
    }

    @TypeConverter
    fun toIntList(data: String?): List<Int>? {
        return data?.takeIf { it.isNotBlank() }
            ?.split(",")
            ?.mapNotNull { it.toIntOrNull() }
    }
}