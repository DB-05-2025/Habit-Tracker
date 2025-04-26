package com.acevy.habit_tracker.domain.usecase.habit

import com.acevy.habit_tracker.domain.repository.habit.HabitRepository

class GetHabitsUseCase(private val repo: HabitRepository) {
    operator fun invoke(userId: Long) = repo.getHabitsByUser(userId)
}