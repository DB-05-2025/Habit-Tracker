package com.acevy.habit_tracker.data.mapper.userlevel

import com.acevy.habit_tracker.data.model.userlevel.UserLevelEntity
import com.acevy.habit_tracker.domain.model.userlevel.UserLevel

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