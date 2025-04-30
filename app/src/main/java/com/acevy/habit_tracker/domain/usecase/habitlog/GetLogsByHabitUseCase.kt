package com.acevy.habit_tracker.domain.usecase.habitlog

import com.acevy.habit_tracker.domain.repository.habitlog.HabitLogRepository

class GetLogsByHabitUseCase(private val repo: HabitLogRepository) {
    operator fun invoke(habitId: Long) = repo.getLogsByHabit(habitId)
}