package com.acevy.habit_tracker.domain.repository.habitlog

import com.acevy.habit_tracker.domain.model.habitlog.HabitLog
import kotlinx.coroutines.flow.Flow

interface HabitLogRepository {
    suspend fun insertHabitLog(log: HabitLog): Long
    suspend fun updateHabitLog(log: HabitLog)
    suspend fun deleteHabitLog(log: HabitLog)
    fun getLogsByHabitId(habitId: Long): Flow<List<HabitLog>>
}