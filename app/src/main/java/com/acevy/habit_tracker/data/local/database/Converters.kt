package com.acevy.habit_tracker.data.local.database

import androidx.room.TypeConverter

class Converters {
    @TypeConverter
    fun fromIntList(list: List<Int>?): String? = list?.joinToString(",")

    @TypeConverter
    fun toIntList(data: String?): List<Int>? =
        data?.split(",")?.mapNotNull { it.toIntOrNull() }
}