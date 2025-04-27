package com.acevy.habit_tracker.data.local.database

import androidx.room.*
import com.acevy.habit_tracker.data.local.dao.userlevel.UserLevelDao
import com.acevy.habit_tracker.data.model.userlevel.UserLevelEntity

@Database(
    entities = [UserLevelEntity::class],
    version = 1
)

abstract class AppDatabase : RoomDatabase() {
    abstract fun userLevelDao(): UserLevelDao
}