package com.acevy.habit_tracker.domain.usecase

import com.acevy.habit_tracker.domain.model.HabitLog
import com.acevy.habit_tracker.domain.repository.HabitLogRepository

class AddHabitLogUseCase(private val repo: HabitLogRepository) {
    suspend operator fun invoke(log: HabitLog) = repo.addLog(log)
}