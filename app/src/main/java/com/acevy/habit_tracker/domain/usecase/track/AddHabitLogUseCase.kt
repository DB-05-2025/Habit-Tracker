package com.acevy.habit_tracker.domain.usecase.track

import com.acevy.habit_tracker.data.local.entity.HabitLogEntity
import com.acevy.habit_tracker.domain.repository.TrackRepository

class AddHabitLogUseCase(
    private val repository: TrackRepository
) {
    suspend operator fun invoke(log: HabitLogEntity) {
        repository.addLog(log)
    }
}
