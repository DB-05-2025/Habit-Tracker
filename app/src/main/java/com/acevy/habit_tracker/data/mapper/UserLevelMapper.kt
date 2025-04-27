package com.acevy.habit_tracker.data.mapper

import com.acevy.habit_tracker.data.model.UserLevelEntity
import com.acevy.habit_tracker.domain.model.UserLevel

fun UserLevel.toEntity() = UserLevelEntity(
    userLevelId = userLevelId,
    userId = userId,
    level = level,
    currentXp = currentXp,
    updatedAt = updatedAt
)

fun UserLevelEntity.toDomain() = UserLevel(
    userLevelId = userLevelId,
    userId = userId,
    level = level,
    currentXp = currentXp,
    updatedAt = updatedAt
)