package com.acevy.habit_tracker.data.local.dao.userlevel

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.acevy.habit_tracker.data.model.userlevel.UserLevelEntity

@Dao
interface UserLevelDao {
    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insertUserLevel(userLevel: UserLevelEntity): Long

    @Update
    suspend fun updateUserLevel(userLevel: UserLevelEntity)

    @Query("SELECT * FROM user_level WHERE userId = :userId LIMIT 1")
    suspend fun getUserLevelByUserId(userId: Long): UserLevelEntity?
}