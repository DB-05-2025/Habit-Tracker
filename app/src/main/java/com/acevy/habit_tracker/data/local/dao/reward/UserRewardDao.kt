package com.acevy.habit_tracker.data.local.dao.reward

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.acevy.habit_tracker.data.model.reward.UserRewardEntity

@Dao
interface UserRewardDao {
    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insertUserReward(reward: UserRewardEntity)

    @Query("SELECT * FROM user_reward WHERE userId = :userId ORDER BY earnedAt DESC")
    suspend fun getRewardsByUser(userId: Long): List<UserRewardEntity>
}