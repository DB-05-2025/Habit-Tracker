package com.acevy.habit_tracker.domain.repository

import com.acevy.habit_tracker.domain.model.UserReward
import kotlinx.coroutines.flow.Flow

interface UserRewardRepository {
    suspend fun insertUserReward(reward: UserReward)
    fun getRewardsByUser(userId: Long): Flow<List<UserReward>>
}