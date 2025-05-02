package com.acevy.habit_tracker.data.local.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.acevy.habit_tracker.data.local.entity.NotificationLogEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NotificationLogDao {

//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun <NotificationLogEntity> insertNotification(notification: NotificationLogEntity)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNotification(notification: NotificationLogEntity)

    @Query("SELECT * FROM notification_logs ORDER BY isActive DESC, scheduledTime ASC")
    fun getAllNotifications(): Flow<List<NotificationLogEntity>>

    @Query("DELETE FROM notification_logs WHERE isActive = 0 AND timestamp < :beforeTimestamp")
    suspend fun clearNotificationsBefore(beforeTimestamp: Long)

    @Query("DELETE FROM notification_logs WHERE habitId = :habitId")
    suspend fun deleteByHabitId(habitId: Int)
}
