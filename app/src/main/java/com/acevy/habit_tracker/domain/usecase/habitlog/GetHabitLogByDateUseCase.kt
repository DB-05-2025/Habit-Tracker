package com.acevy.habit_tracker.domain.usecase.habitlog

import com.acevy.habit_tracker.domain.repository.habitlog.HabitLogRepository

class GetHabitLogByDateUseCase(private val repo: HabitLogRepository) {
    suspend operator fun invoke(habitId: Long, date: String) = repo.getLogByDate(habitId, date)
}