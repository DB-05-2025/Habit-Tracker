package com.acevy.habit_tracker.domain.usecase.reward

import com.acevy.habit_tracker.domain.model.reward.UserReward
import com.acevy.habit_tracker.domain.repository.reward.UserRewardRepository

class InsertUserRewardUseCase(private val repo: UserRewardRepository) {
    suspend operator fun invoke(reward: UserReward): Long = repo.insertUserReward(reward)
}