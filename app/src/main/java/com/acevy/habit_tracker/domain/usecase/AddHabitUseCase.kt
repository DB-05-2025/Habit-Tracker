package com.acevy.habit_tracker.domain.usecase

import com.acevy.habit_tracker.domain.model.Habit
import com.acevy.habit_tracker.domain.repository.HabitRepository

class AddHabitUseCase(private val repo: HabitRepository) {
    suspend operator fun invoke(habit: Habit) = repo.addHabit(habit)
}