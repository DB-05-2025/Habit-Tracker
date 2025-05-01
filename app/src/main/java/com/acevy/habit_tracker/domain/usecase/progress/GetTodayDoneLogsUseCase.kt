package com.acevy.habit_tracker.domain.usecase.progress

import com.acevy.habit_tracker.data.local.entity.HabitLogEntity
import com.acevy.habit_tracker.domain.repository.TrackRepository
import kotlinx.coroutines.flow.Flow

class GetTodayDoneLogsUseCase(
    private val repo: TrackRepository
) {
    operator fun invoke(date: String): Flow<List<HabitLogEntity>> {
        return repo.getLogsByDate(date)
    }
}
