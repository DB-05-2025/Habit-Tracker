package com.acevy.habit_tracker.domain.usecase.track

import com.acevy.habit_tracker.domain.repository.TrackRepository

class DeleteLogsByHabitUseCase(
    private val repository: TrackRepository
) {
    suspend operator fun invoke(habitId: Int) {
        repository.deleteLogsByHabitId(habitId)
    }
}
