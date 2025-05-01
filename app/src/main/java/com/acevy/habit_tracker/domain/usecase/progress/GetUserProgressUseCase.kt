package com.acevy.habit_tracker.domain.usecase.progress

import com.acevy.habit_tracker.data.local.entity.UserProgressEntity
import com.acevy.habit_tracker.domain.repository.ProgressRepository
import kotlinx.coroutines.flow.Flow

class GetUserProgressUseCase(
    private val repository: ProgressRepository
) {
    operator fun invoke(): Flow<UserProgressEntity?> {
        return repository.getProgress()
    }
}
