package com.acevy.habit_tracker.data.mapper

import com.acevy.habit_tracker.data.model.HabitLogEntity
import com.acevy.habit_tracker.domain.model.HabitLog

fun HabitLog.toEntity() = HabitLogEntity(
    id, habitId, date, status, note, createdAt, updatedAt
)

fun HabitLogEntity.toDomain() = HabitLog(
    id, habitId, date, status, note, createdAt, updatedAt
)