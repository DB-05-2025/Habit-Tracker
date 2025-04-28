package com.acevy.habit_tracker.data.mapper.habitstack

import com.acevy.habit_tracker.data.model.habitstack.HabitStackEntity
import com.acevy.habit_tracker.domain.model.habitstack.HabitStack

fun HabitStack.toEntity() = HabitStackEntity(
    habitStackId = habitStackId,
    userId = userId,
    primaryHabitId = primaryHabitId,
    stackedHabitId = stackedHabitId,
    createdAt = createdAt
)

fun HabitStackEntity.toDomain() = HabitStack(
    habitStackId = habitStackId,
    userId = userId,
    primaryHabitId = primaryHabitId,
    stackedHabitId = stackedHabitId,
    createdAt = createdAt
)