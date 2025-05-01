package com.acevy.habit_tracker.domain.usecase.progress

import com.acevy.habit_tracker.data.local.entity.HabitLogEntity
import com.acevy.habit_tracker.domain.repository.TrackRepository

class GetAllLogsUseCase(
    private val repo: TrackRepository
) {
    suspend operator fun invoke(): List<HabitLogEntity> {
        return repo.getAllLogsOnce()
    }
}

