package com.acevy.habit_tracker.data.mapper

import com.acevy.habit_tracker.data.model.UserRewardEntity
import com.acevy.habit_tracker.domain.model.UserReward

fun UserReward.toEntity() = UserRewardEntity(
    id, userId, amount, source, earnedAt
)

fun UserRewardEntity.toDomain() = UserReward(
    id, userId, amount, source, earnedAt
)