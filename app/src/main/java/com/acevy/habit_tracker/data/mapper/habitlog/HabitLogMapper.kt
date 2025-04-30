package com.acevy.habit_tracker.data.mapper.habitlog

import com.acevy.habit_tracker.data.model.habitlog.HabitLogEntity
import com.acevy.habit_tracker.domain.model.habitlog.HabitLog

fun HabitLog.toEntity() = HabitLogEntity(
    habitLogId = habitLogId,
    habitId = habitId,
    date = date,
    status = status,
    note = note,
    createdAt = createdAt,
    updatedAt = updatedAt
)

fun HabitLogEntity.toDomain() = HabitLog(
    habitLogId = habitLogId,
    habitId = habitId,
    date = date,
    status = status,
    note = note,
    createdAt = createdAt,
    updatedAt = updatedAt
)