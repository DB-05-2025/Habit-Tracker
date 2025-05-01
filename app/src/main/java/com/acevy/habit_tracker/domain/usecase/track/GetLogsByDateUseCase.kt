package com.acevy.habit_tracker.domain.usecase.track

import com.acevy.habit_tracker.data.local.entity.HabitLogEntity
import com.acevy.habit_tracker.domain.repository.TrackRepository
import kotlinx.coroutines.flow.Flow

class GetLogsByDateUseCase(
    private val repository: TrackRepository
) {
    operator fun invoke(date: String): Flow<List<HabitLogEntity>> {
        return repository.getLogsByDate(date)
    }
}
