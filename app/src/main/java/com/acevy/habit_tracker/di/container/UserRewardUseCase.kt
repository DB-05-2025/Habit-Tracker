package com.acevy.habit_tracker.di.container

import com.acevy.habit_tracker.domain.usecase.reward.GetUserRewardsUseCase
import com.acevy.habit_tracker.domain.usecase.reward.InsertUserRewardUseCase

data class UserRewardUseCases(
    val insertUserRewardUseCase: InsertUserRewardUseCase,
    val getUserRewardsUseCase: GetUserRewardsUseCase,
)