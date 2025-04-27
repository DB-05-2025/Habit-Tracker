package com.acevy.habit_tracker.domain.usecase.habitstack

import com.acevy.habit_tracker.domain.model.habitstack.HabitStack
import com.acevy.habit_tracker.domain.repository.habitstack.HabitStackRepository

class DeleteHabitStackUseCase(private val repo: HabitStackRepository) {
    suspend operator fun invoke(habitStack: HabitStack) = repo.deleteHabitStack(habitStack)
}