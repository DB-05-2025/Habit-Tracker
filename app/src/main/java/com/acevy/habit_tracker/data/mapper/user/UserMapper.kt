package com.acevy.habit_tracker.data.mapper.user

import com.acevy.habit_tracker.data.model.user.UserEntity
import com.acevy.habit_tracker.domain.model.user.User

fun User.toEntity() = UserEntity(
    userId = userId,
    name = name,
    createdAt = createdAt,
    updatedAt = updatedAt
)

fun UserEntity.toDomain() = User(
    userId = userId,
    name = name,
    createdAt = createdAt,
    updatedAt = updatedAt
)