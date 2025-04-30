package com.acevy.habit_tracker.domain.repository.habitlog

import com.acevy.habit_tracker.domain.model.habitlog.HabitLog
import kotlinx.coroutines.flow.Flow

interface HabitLogRepository {
    suspend fun insertHabitLog(log: HabitLog)
    suspend fun getLogByDate(habitId: Long, date: String): HabitLog?
    fun getLogsByHabit(habitId: Long): Flow<List<HabitLog>>
    suspend fun deleteLog(log: HabitLog)
}