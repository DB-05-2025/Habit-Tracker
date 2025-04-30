package com.acevy.habit_tracker.data.local.database

import androidx.room.*
import com.acevy.habit_tracker.data.local.dao.habit.HabitDao
import com.acevy.habit_tracker.data.model.habit.HabitEntity
import com.acevy.habit_tracker.data.local.dao.habitstack.HabitStackDao
import com.acevy.habit_tracker.data.local.dao.reward.UserRewardDao
import com.acevy.habit_tracker.data.local.dao.user.UserDao
import com.acevy.habit_tracker.data.local.dao.userlevel.UserLevelDao
import com.acevy.habit_tracker.data.model.habitstack.HabitStackEntity
import com.acevy.habit_tracker.data.model.reward.UserRewardEntity
import com.acevy.habit_tracker.data.model.user.UserEntity
import com.acevy.habit_tracker.data.model.userlevel.UserLevelEntity
import com.acevy.habit_tracker.data.local.dao.habitlog.HabitLogDao
import com.acevy.habit_tracker.data.local.dao.notificationlog.NotificationLogDao
import com.acevy.habit_tracker.data.model.habitlog.HabitLogEntity
import com.acevy.habit_tracker.data.model.notificationlog.NotificationLogEntity

@Database(
    entities = [
        UserEntity::class,
        HabitEntity::class,
        HabitLogEntity::class,
        HabitStackEntity::class,
        UserRewardEntity::class,
        UserLevelEntity::class,
        NotificationLogEntity::class,
    ],
    version = 1
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun habitDao(): HabitDao
    abstract fun habitLogDao(): HabitLogDao
    abstract fun habitStackDao(): HabitStackDao
    abstract fun userRewardDao(): UserRewardDao
    abstract fun userLevelDao(): UserLevelDao
    abstract fun notificationLogDao(): NotificationLogDao
}