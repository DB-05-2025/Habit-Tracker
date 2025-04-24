package com.acevy.habit_tracker.domain.usecase

import com.acevy.habit_tracker.domain.repository.UserRewardRepository

class GetUserRewardsUseCase(private val repo: UserRewardRepository) {
    operator fun invoke(userId: Long) = repo.getRewardsByUser(userId)
}