package com.acevy.habit_tracker.data.mapper

import com.acevy.habit_tracker.data.model.UserEntity
import com.acevy.habit_tracker.domain.model.User

fun User.toEntity() = UserEntity(
    id, firstName, lastName, gender, birthDate, createdAt
)

fun UserEntity.toDomain() = User(
    id, firstName, lastName, gender, birthDate, createdAt
)