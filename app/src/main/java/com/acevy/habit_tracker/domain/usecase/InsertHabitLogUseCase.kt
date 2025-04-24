package com.acevy.habit_tracker.domain.usecase

import com.acevy.habit_tracker.domain.model.HabitLog
import com.acevy.habit_tracker.domain.repository.HabitLogRepository

class InsertHabitLogUseCase(private val repo: HabitLogRepository) {
    suspend operator fun invoke(log: HabitLog) = repo.insertHabitLog(log)
}