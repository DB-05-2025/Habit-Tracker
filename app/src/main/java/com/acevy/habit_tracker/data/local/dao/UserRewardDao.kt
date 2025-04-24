package com.acevy.habit_tracker.data.local.dao

import androidx.room.*
import com.acevy.habit_tracker.data.model.UserRewardEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserRewardDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserReward(reward: UserRewardEntity)

    @Query("SELECT * FROM user_reward WHERE userId = :userId ORDER BY earnedAt DESC")
    fun getRewardsByUser(userId: Long): Flow<List<UserRewardEntity>>
}