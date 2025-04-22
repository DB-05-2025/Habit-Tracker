package com.acevy.habit_tracker.domain.usecase

import com.acevy.habit_tracker.domain.repository.HabitLogRepository

class GetLogsByHabitUseCase(private val repo: HabitLogRepository) {
    operator fun invoke(habitId: Long) = repo.getLogsByHabit(habitId)
}