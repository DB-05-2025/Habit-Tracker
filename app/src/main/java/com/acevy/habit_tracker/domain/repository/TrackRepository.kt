package com.acevy.habit_tracker.domain.repository

import com.acevy.habit_tracker.data.local.entity.HabitLogEntity
import kotlinx.coroutines.flow.Flow

interface TrackRepository {
    suspend fun addLog(log: HabitLogEntity)
    fun getLogsByDate(date: String): Flow<List<HabitLogEntity>>
    suspend fun deleteLogsByHabitId(habitId: Int)
    suspend fun updateLog(log: HabitLogEntity) // ➕
}
