package com.acevy.habit_tracker.data.local.room.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.acevy.habit_tracker.data.local.entity.HabitEntity
import com.acevy.habit_tracker.data.local.entity.HabitLogEntity
import com.acevy.habit_tracker.data.local.room.dao.HabitDao
import com.acevy.habit_tracker.data.local.room.dao.HabitLogDao
import com.acevy.habit_tracker.data.local.room.dao.HabitStackDao
import com.acevy.habit_tracker.data.local.room.dao.NotificationLogDao
import com.acevy.habit_tracker.data.local.room.dao.UserProgressDao
import com.acevy.habit_tracker.data.local.room.db.converter.HabitIdListConverter
import com.acevy.habit_tracker.data.local.room.db.converter.HabitStatusConverter
import com.acevy.habit_tracker.data.local.room.db.converter.RepeatDaysConverter

@Database(
    entities = [HabitEntity::class, HabitLogEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(
    RepeatDaysConverter::class,
    HabitStatusConverter::class,
    HabitIdListConverter::class
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun habitDao(): HabitDao
    abstract fun habitLogDao(): HabitLogDao
    abstract fun habitStackDao(): HabitStackDao
    abstract fun userProgressDao(): UserProgressDao
    abstract fun notificationLogDao(): NotificationLogDao
}

