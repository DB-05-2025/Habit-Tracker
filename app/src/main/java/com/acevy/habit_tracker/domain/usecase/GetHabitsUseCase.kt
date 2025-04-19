package com.acevy.habit_tracker.domain.usecase

import com.acevy.habit_tracker.domain.repository.HabitRepository

class GetHabitsUseCase(private val repo: HabitRepository) {
    operator fun invoke(userId: Long) = repo.getHabitsByUser(userId)
}