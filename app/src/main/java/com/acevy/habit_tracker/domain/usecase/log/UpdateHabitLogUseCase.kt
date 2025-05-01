package com.acevy.habit_tracker.domain.usecase.log

import com.acevy.habit_tracker.domain.repository.TrackRepository
import com.acevy.habit_tracker.data.local.entity.HabitLogEntity

class UpdateHabitLogUseCase(
    private val repository: TrackRepository
) {
    suspend operator fun invoke(log: HabitLogEntity) {
        repository.updateLog(log)
    }
}