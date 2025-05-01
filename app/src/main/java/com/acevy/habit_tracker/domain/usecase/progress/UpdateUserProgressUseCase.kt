package com.acevy.habit_tracker.domain.usecase.progress

import com.acevy.habit_tracker.data.local.entity.UserProgressEntity
import com.acevy.habit_tracker.domain.repository.ProgressRepository

class UpdateUserProgressUseCase(
    private val repository: ProgressRepository
) {
    suspend operator fun invoke(data: UserProgressEntity) {
        repository.upsertProgress(data)
    }
}
