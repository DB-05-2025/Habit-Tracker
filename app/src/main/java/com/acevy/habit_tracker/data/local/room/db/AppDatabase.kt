package com.acevy.habit_tracker.data.local.room.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.acevy.habit_tracker.data.local.entity.HabitEntity
import com.acevy.habit_tracker.data.local.entity.HabitLogEntity
import com.acevy.habit_tracker.data.local.entity.HabitStackEntity
import com.acevy.habit_tracker.data.local.entity.NotificationLogEntity
import com.acevy.habit_tracker.data.local.entity.UserProgressEntity
import com.acevy.habit_tracker.data.local.room.dao.HabitDao
import com.acevy.habit_tracker.data.local.room.dao.HabitLogDao
import com.acevy.habit_tracker.data.local.room.dao.HabitStackDao
import com.acevy.habit_tracker.data.local.room.dao.NotificationLogDao
import com.acevy.habit_tracker.data.local.room.dao.UserProgressDao
import com.acevy.habit_tracker.data.local.room.db.converter.HabitStatusConverter
import com.acevy.habit_tracker.data.local.room.db.converter.IntListConverter

@Database(
    entities = [
        HabitEntity::class,
        HabitLogEntity::class,
        HabitStackEntity::class,
        NotificationLogEntity::class,
        UserProgressEntity::class],
    version = 2,
    exportSchema = false
)
@TypeConverters(
    HabitStatusConverter::class,
    IntListConverter::class
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun habitDao(): HabitDao
    abstract fun habitLogDao(): HabitLogDao
    abstract fun habitStackDao(): HabitStackDao
    abstract fun userProgressDao(): UserProgressDao
    abstract fun notificationLogDao(): NotificationLogDao
}

