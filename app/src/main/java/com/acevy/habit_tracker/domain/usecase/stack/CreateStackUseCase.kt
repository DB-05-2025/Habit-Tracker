package com.acevy.habit_tracker.domain.usecase.stack

import com.acevy.habit_tracker.data.local.entity.HabitStackEntity
import com.acevy.habit_tracker.domain.repository.StackRepository

class CreateStackUseCase(
    private val repository: StackRepository
) {
    suspend operator fun invoke(stack: HabitStackEntity) {
        repository.insertStack(stack)
    }
}
