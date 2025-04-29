package com.acevy.habit_tracker.domain.usecase.habitlog

import com.acevy.habit_tracker.domain.model.habitlog.HabitLog
import com.acevy.habit_tracker.domain.repository.habitlog.HabitLogRepository

class InsertHabitLogUseCase(private val repo: HabitLogRepository) {
    suspend operator fun invoke(log: HabitLog): Long = repo.insertHabitLog(log)
}