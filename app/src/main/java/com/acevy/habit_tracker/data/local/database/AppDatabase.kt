package com.acevy.habit_tracker.data.local.database

import androidx.room.*
import com.acevy.habit_tracker.data.local.dao.habitstack.HabitStackDao
import com.acevy.habit_tracker.data.model.habitstack.HabitStackEntity

@Database(entities = [HabitStackEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun habitStackDao(): HabitStackDao
}