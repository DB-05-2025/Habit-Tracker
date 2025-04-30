package com.acevy.habit_tracker.data.local.dao.notificationlog

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.acevy.habit_tracker.data.model.notificationlog.NotificationLogEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NotificationLogDao {
    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insertNotificationLog(notificationLog: NotificationLogEntity): Long

    @Query("SELECT * FROM notification_log WHERE userId = :userId ORDER BY sentAt DESC")
    fun getNotificationLogsByUser(userId: Long): Flow<List<NotificationLogEntity>>
}