package com.acevy.habit_tracker.data.local.database

import androidx.room.*
import com.acevy.habit_tracker.data.local.dao.HabitLogDao
import com.acevy.habit_tracker.data.model.HabitLogEntity

@Database(
    entities = [
        HabitLogEntity::class
    ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun habitLogDao(): HabitLogDao
}