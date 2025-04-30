package com.acevy.habit_tracker.domain.usecase.reward

import com.acevy.habit_tracker.domain.repository.reward.UserRewardRepository

class GetUserRewardsUseCase(private val repo: UserRewardRepository) {
    operator fun invoke(userId: Long) = repo.getRewardsByUser(userId)
}