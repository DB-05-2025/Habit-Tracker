package com.acevy.habit_tracker.domain.usecase

import com.acevy.habit_tracker.domain.model.UserReward
import com.acevy.habit_tracker.domain.repository.UserRewardRepository

class InsertUserRewardUseCase(private val repo: UserRewardRepository) {
    suspend operator fun invoke(reward: UserReward) = repo.insertUserReward(reward)
}