package com.acevy.habit_tracker.domain.usecase.habitstack

import com.acevy.habit_tracker.domain.repository.habitstack.HabitStackRepository

class GetHabitStacksByUserUseCase(private val repo: HabitStackRepository) {
    operator fun invoke(userId: Long) = repo.getHabitStacksByUser(userId)
}