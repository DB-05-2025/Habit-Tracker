package com.acevy.habit_tracker.domain.usecase.habitstack

import com.acevy.habit_tracker.domain.model.habitstack.HabitStack
import com.acevy.habit_tracker.domain.repository.habitstack.HabitStackRepository

class UpdateHabitStackUseCase(private val repo: HabitStackRepository) {
    suspend operator fun invoke(habitStack: HabitStack) = repo.updateHabitStack(habitStack)
}