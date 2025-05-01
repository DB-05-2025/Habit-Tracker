package com.acevy.habit_tracker.data.local.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.acevy.habit_tracker.data.local.entity.UserProgressEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserProgressDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertProgress(progress: UserProgressEntity)

//    @Query("SELECT * FROM user_progress WHERE id = 1 LIMIT 1")
//    fun getProgress(): Flow<UserProgressEntity>
    @Query("SELECT * FROM user_progress WHERE id = 1 LIMIT 1")
    fun getProgress(): Flow<UserProgressEntity?>
}

