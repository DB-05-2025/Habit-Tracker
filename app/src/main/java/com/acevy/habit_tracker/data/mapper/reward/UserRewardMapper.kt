package com.acevy.habit_tracker.data.mapper.reward

import com.acevy.habit_tracker.data.model.reward.UserRewardEntity
import com.acevy.habit_tracker.domain.model.reward.UserReward

fun UserReward.toEntity() = UserRewardEntity(
    userRewardId = userRewardId,
    userId = userId,
    amount = amount,
    source = source,
    earnedAt = earnedAt
)

fun UserRewardEntity.toDomain() = UserReward(
    userRewardId = userRewardId,
    userId = userId,
    amount = amount,
    source = source,
    earnedAt = earnedAt
)