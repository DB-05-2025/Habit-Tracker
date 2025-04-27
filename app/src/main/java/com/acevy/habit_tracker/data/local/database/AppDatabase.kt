package com.acevy.habit_tracker.data.local.database

import androidx.room.*
import com.acevy.habit_tracker.data.local.dao.user.UserDao
import com.acevy.habit_tracker.data.local.dao.userlevel.UserLevelDao
import com.acevy.habit_tracker.data.model.user.UserEntity
import com.acevy.habit_tracker.data.model.userlevel.UserLevelEntity

@Database(
    entities = [UserEntity::class],
               [UserLevelEntity::class],
    version = 1
)

abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun userLevelDao(): UserLevelDao
}