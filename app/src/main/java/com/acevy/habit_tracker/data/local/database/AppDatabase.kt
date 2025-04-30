package com.acevy.habit_tracker.data.local.database

import androidx.room.*
import com.acevy.habit_tracker.data.local.dao.habit.HabitDao
import com.acevy.habit_tracker.data.model.habit.HabitEntity
import com.acevy.habit_tracker.data.local.dao.habitstack.HabitStackDao
import com.acevy.habit_tracker.data.local.dao.reward.UserRewardDao
import com.acevy.habit_tracker.data.model.habitstack.HabitStackEntity
import com.acevy.habit_tracker.data.model.reward.UserRewardEntity

@Database(
    entities = [
        HabitEntity::class,
        HabitStackEntity::class,
        UserRewardEntity::class,
    ],
    version = 1
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun habitDao(): HabitDao
    abstract fun habitStackDao(): HabitStackDao
    abstract fun userRewardDao(): UserRewardDao
}