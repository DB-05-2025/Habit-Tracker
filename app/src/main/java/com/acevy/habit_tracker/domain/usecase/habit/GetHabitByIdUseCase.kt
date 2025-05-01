package com.acevy.habit_tracker.domain.usecase.habit

import com.acevy.habit_tracker.data.local.entity.HabitEntity
import com.acevy.habit_tracker.domain.repository.HabitRepository
import kotlinx.coroutines.flow.Flow

class GetHabitByIdUseCase(private val repository: HabitRepository) {
    operator fun invoke(id: Int): Flow<HabitEntity?> {
        return repository.getHabitById(id)
    }
}
