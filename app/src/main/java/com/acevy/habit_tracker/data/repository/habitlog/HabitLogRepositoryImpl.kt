package com.acevy.habit_tracker.data.repository.habitlog

import com.acevy.habit_tracker.data.local.dao.habitlog.HabitLogDao
import com.acevy.habit_tracker.data.mapper.habitlog.toDomain
import com.acevy.habit_tracker.data.mapper.habitlog.toEntity
import com.acevy.habit_tracker.domain.model.habitlog.HabitLog
import com.acevy.habit_tracker.domain.repository.habitlog.HabitLogRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class HabitLogRepositoryImpl(
    private val dao: HabitLogDao,
) : HabitLogRepository {

    override suspend fun insertHabitLog(log: HabitLog): Long = dao.insertHabitLog(log.toEntity())

    override suspend fun updateHabitLog(log: HabitLog) = dao.updateHabitLog(log.toEntity())

    override suspend fun deleteHabitLog(log: HabitLog) = dao.deleteHabitLog(log.toEntity())

    override fun getLogsByHabitId(habitId: Long): Flow<List<HabitLog>> =
        dao.getLogsByHabitId(habitId).map { list -> list.map { it.toDomain() } }
}