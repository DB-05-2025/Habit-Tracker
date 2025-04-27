package com.acevy.habit_tracker.data.mapper.habit

import com.acevy.habit_tracker.data.model.habit.HabitEntity
import com.acevy.habit_tracker.domain.model.habit.Habit

fun Habit.toEntity() = HabitEntity(
    habitId = habitId,
    userId = userId,
    title = title,
    note = note,
    repeatDays = repeatDays,
    reminderTime = reminderTime,
    createdAt = createdAt,
    updatedAt = updatedAt
)

fun HabitEntity.toDomain() = Habit(
    habitId = habitId,
    userId = userId,
    title = title,
    note = note,
    repeatDays = repeatDays,
    reminderTime = reminderTime,
    createdAt = createdAt,
    updatedAt = updatedAt
)