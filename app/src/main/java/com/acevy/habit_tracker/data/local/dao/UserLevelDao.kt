package com.acevy.habit_tracker.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.acevy.habit_tracker.data.model.UserLevelEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserLevelDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdate(userLevel: UserLevelEntity)

    @Query("SELECT * FROM user_level WHERE userId = :userId LIMIT 1")
    fun getUserLevel(userId: Long): Flow<UserLevelEntity?>
}