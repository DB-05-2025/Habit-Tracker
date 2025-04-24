package com.acevy.habit_tracker.data.repository

import com.acevy.habit_tracker.data.local.dao.HabitLogDao
import com.acevy.habit_tracker.data.mapper.toDomain
import com.acevy.habit_tracker.data.mapper.toEntity
import com.acevy.habit_tracker.domain.model.HabitLog
import com.acevy.habit_tracker.domain.repository.HabitLogRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class HabitLogRepositoryImpl(
    private val dao: HabitLogDao,
) : HabitLogRepository {

    override suspend fun insertHabitLog(log: HabitLog) {
        dao.insertHabitLog(log.toEntity())
    }

    override suspend fun getLogByDate(habitId: Long, date: String): HabitLog? {
        return dao.getLogByDate(habitId, date)?.toDomain()
    }

    override fun getLogsByHabit(habitId: Long): Flow<List<HabitLog>> {
        return dao.getLogsByHabit(habitId).map { list -> list.map { it.toDomain() } }
    }

    override suspend fun deleteLog(log: HabitLog) {
        dao.deleteHabitLog(log.toEntity())
    }

}