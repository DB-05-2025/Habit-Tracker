package com.acevy.habit_tracker.data.local.database

import androidx.room.*
import com.acevy.habit_tracker.data.local.dao.user.UserDao
import com.acevy.habit_tracker.data.model.user.UserEntity

@Database(
    entities = [UserEntity::class],
    version = 1
)

abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}