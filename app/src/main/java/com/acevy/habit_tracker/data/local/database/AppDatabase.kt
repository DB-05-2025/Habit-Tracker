package com.acevy.habit_tracker.data.local.database

import androidx.room.*
import com.acevy.habit_tracker.data.local.dao.notificationlog.NotificationLogDao
import com.acevy.habit_tracker.data.model.notificationlog.NotificationLogEntity

@Database(entities = [NotificationLogEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun notificationLogDao(): NotificationLogDao
}