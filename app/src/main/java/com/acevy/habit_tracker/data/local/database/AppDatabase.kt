package com.acevy.habit_tracker.data.local.database

import androidx.room.*
import com.acevy.habit_tracker.data.local.dao.HabitDao
import com.acevy.habit_tracker.data.local.dao.RewardTypeDao
import com.acevy.habit_tracker.data.local.dao.UserLevelDao
import com.acevy.habit_tracker.data.local.dao.UserRewardDao
import com.acevy.habit_tracker.data.model.HabitEntity
import com.acevy.habit_tracker.data.model.RewardTypeEntity
import com.acevy.habit_tracker.data.model.UserLevelEntity
import com.acevy.habit_tracker.data.model.UserRewardEntity

@Database(
    entities = [
        HabitEntity::class,
        RewardTypeEntity::class,
        UserRewardEntity::class,
        UserLevelEntity::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun habitDao(): HabitDao
    abstract fun rewardTypeDao(): RewardTypeDao
    abstract fun userRewardDao(): UserRewardDao
    abstract fun userLevelDao(): UserLevelDao
}