package com.acevy.habit_tracker.data.local.database

import androidx.room.*
import com.acevy.habit_tracker.data.local.dao.UserRewardDao
import com.acevy.habit_tracker.data.model.UserRewardEntity

@Database(
    entities = [UserRewardEntity::class],
    version = 1
)

abstract class AppDatabase : RoomDatabase() {
    abstract fun userRewardDao(): UserRewardDao
}