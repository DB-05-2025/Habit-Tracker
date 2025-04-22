package com.acevy.habit_tracker.data.local.database

import androidx.room.*
import com.acevy.habit_tracker.data.local.dao.HabitDao
import com.acevy.habit_tracker.data.model.HabitEntity

@Database(entities = [HabitEntity::class], version = 2)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun habitDao(): HabitDao
}