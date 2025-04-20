package com.acevy.habit_tracker.domain.usecase

import com.acevy.habit_tracker.domain.repository.HabitLogRepository

class GetHabitLogByDateUseCase(private val repo: HabitLogRepository) {
    suspend operator fun invoke(habitId: Long, date: String) = repo.getLogByDate(habitId, date)
}