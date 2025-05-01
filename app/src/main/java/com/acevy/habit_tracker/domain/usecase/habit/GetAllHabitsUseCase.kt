package com.acevy.habit_tracker.domain.usecase.habit

import com.acevy.habit_tracker.data.local.entity.HabitEntity
import com.acevy.habit_tracker.domain.repository.HabitRepository
import kotlinx.coroutines.flow.Flow

class GetAllHabitsUseCase(
    private val repository: HabitRepository
) {
    operator fun invoke(): Flow<List<HabitEntity>> {
        return repository.getAllHabits()
    }
}
