package com.acevy.habit_tracker.domain.usecase.stack

import com.acevy.habit_tracker.data.local.entity.HabitStackEntity
import com.acevy.habit_tracker.domain.repository.StackRepository
import kotlinx.coroutines.flow.Flow

class GetAllStacksUseCase(
    private val repository: StackRepository
) {
    operator fun invoke(): Flow<List<HabitStackEntity>> {
        return repository.getAllStacks()
    }
}
