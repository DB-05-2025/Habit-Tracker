package com.acevy.habit_tracker.data.local.database

import androidx.room.*
import com.acevy.habit_tracker.data.local.dao.habit.HabitDao
import com.acevy.habit_tracker.data.local.dao.habitlog.HabitLogDao
import com.acevy.habit_tracker.data.local.dao.user.UserDao
import com.acevy.habit_tracker.data.local.dao.userlevel.UserLevelDao
import com.acevy.habit_tracker.data.model.habit.HabitEntity
import com.acevy.habit_tracker.data.model.habitlog.HabitLogEntity
import com.acevy.habit_tracker.data.model.user.UserEntity
import com.acevy.habit_tracker.data.model.userlevel.UserLevelEntity

@Database(
    entities = [UserEntity::class,
        UserLevelEntity::class,
        HabitEntity::class,
        HabitLogEntity::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun userLevelDao(): UserLevelDao
    abstract fun habitDao(): HabitDao
    abstract fun habitLogDao(): HabitLogDao
}