package com.acevy.habit_tracker.data.mapper

import com.acevy.habit_tracker.data.model.UserLevelEntity
import com.acevy.habit_tracker.domain.model.UserLevel

fun UserLevel.toEntity() = UserLevelEntity(
    id, userId, level, currentXp, updatedAt
)

fun UserLevelEntity.toDomain() = UserLevel(
    id, userId, level, currentXp, updatedAt
)