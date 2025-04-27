package com.acevy.habit_tracker.domain.repository.reward

import com.acevy.habit_tracker.domain.model.reward.UserReward
import kotlinx.coroutines.flow.Flow

interface UserRewardRepository {
    suspend fun insertUserReward(reward: UserReward): Long
    fun getRewardsByUser(userId: Long): Flow<List<UserReward>>
}