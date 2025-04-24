package com.acevy.habit_tracker.data.mapper

import com.acevy.habit_tracker.data.model.HabitEntity
import com.acevy.habit_tracker.domain.model.Habit

fun Habit.toEntity() = HabitEntity(
    id = id,
    userId = userId,
    title = title,
    description = description,
    goalType = goalType,
    category = category,
    startDate = startDate,
    endDate = endDate,
    reminderTime = reminderTime,
    reminderType = reminderType,
    reminderDays = reminderDays,
    createdAt = createdAt,
    updatedAt = updatedAt
)

fun HabitEntity.toDomain() = Habit(
    id = id,
    userId = userId,
    title = title,
    description = description,
    goalType = goalType,
    category = category,
    startDate = startDate,
    endDate = endDate,
    reminderTime = reminderTime,
    reminderType = reminderType,
    reminderDays = reminderDays,
    createdAt = createdAt,
    updatedAt = updatedAt
)