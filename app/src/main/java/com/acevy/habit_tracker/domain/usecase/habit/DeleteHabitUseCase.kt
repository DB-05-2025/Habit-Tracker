package com.acevy.habit_tracker.domain.usecase.habit

import com.acevy.habit_tracker.domain.model.habit.Habit
import com.acevy.habit_tracker.domain.repository.habit.HabitRepository

class DeleteHabitUseCase(private val repo: HabitRepository) {
    suspend operator fun invoke(habit: Habit) = repo.deleteHabit(habit)
}