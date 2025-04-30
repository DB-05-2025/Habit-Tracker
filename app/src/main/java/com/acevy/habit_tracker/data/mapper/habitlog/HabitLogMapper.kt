package com.acevy.habit_tracker.data.mapper.habitlog

import com.acevy.habit_tracker.data.model.habitlog.HabitLogEntity
import com.acevy.habit_tracker.domain.model.habitlog.HabitLog

fun HabitLog.toEntity() = HabitLogEntity(
    id, habitId, date, status, note, createdAt, updatedAt
)

fun HabitLogEntity.toDomain() = HabitLog(
    id, habitId, date, status, note, createdAt, updatedAt
)