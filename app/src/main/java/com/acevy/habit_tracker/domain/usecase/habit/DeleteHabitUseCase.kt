package com.acevy.habit_tracker.domain.usecase.habit

import com.acevy.habit_tracker.data.local.entity.HabitEntity
import com.acevy.habit_tracker.domain.repository.HabitRepository

class DeleteHabitUseCase(
    private val repository: HabitRepository
) {
    suspend operator fun invoke(habit: HabitEntity) {
        repository.deleteHabit(habit)
    }
}