package com.acevy.habit_tracker.data.local.dao

import androidx.room.*
import com.acevy.habit_tracker.data.model.RewardTypeEntity

@Dao
interface RewardTypeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRewardType(rewardType: RewardTypeEntity)

    @Query("SELECT * FROM reward_type WHERE id = :id")
    suspend fun getById(id: Long): RewardTypeEntity?

    @Query("SELECT * FROM reward_type")
    suspend fun getAll(): List<RewardTypeEntity>
}