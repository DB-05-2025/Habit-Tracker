package com.acevy.habit_tracker.domain.repository

import com.acevy.habit_tracker.domain.model.HabitLog
import kotlinx.coroutines.flow.Flow

interface HabitLogRepository {
    suspend fun addLog(log: HabitLog)
    suspend fun getLogByDate(habitId: Long, date: String): HabitLog?
    fun getLogsByHabit(habitId: Long): Flow<List<HabitLog>>
    suspend fun deleteLog(log: HabitLog)
}