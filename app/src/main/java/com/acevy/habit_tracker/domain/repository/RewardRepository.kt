package com.acevy.habit_tracker.domain.repository

import com.acevy.habit_tracker.domain.model.RewardType
import com.acevy.habit_tracker.domain.model.UserLevel
import com.acevy.habit_tracker.domain.model.UserReward

interface RewardRepository {
    suspend fun addUserReward(reward: UserReward)
    suspend fun getRewardsByUser(userId: Long): List<UserReward>

    suspend fun getUserLevel(userId: Long): UserLevel?
    suspend fun upsertUserLevel(level: UserLevel)

    suspend fun getRewardTypeByName(name: String): RewardType?
    suspend fun insertRewardType(type: RewardType)
}