package com.acevy.habit_tracker.data.local.dao

import androidx.room.*
import com.acevy.habit_tracker.data.model.UserLevelEntity

@Dao
interface UserLevelDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertUserLevel(level: UserLevelEntity)

    @Query("SELECT * FROM user_level WHERE userId = :userId LIMIT 1")
    suspend fun getByUserId(userId: Long): UserLevelEntity?
}