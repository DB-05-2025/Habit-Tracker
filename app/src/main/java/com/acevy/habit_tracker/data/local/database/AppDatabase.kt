package com.acevy.habit_tracker.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.acevy.habit_tracker.data.local.dao.HabitLogDao
import com.acevy.habit_tracker.data.model.HabitLogEntity

@Database(
    entities = [
        HabitLogEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun habitLogDao(): HabitLogDao
}