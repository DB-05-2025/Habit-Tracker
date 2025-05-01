package com.acevy.habit_tracker.data.repository

import com.acevy.habit_tracker.data.local.entity.HabitLogEntity
import com.acevy.habit_tracker.data.local.room.dao.HabitLogDao
import com.acevy.habit_tracker.domain.repository.TrackRepository
import kotlinx.coroutines.flow.Flow

class TrackRepositoryImpl(
    private val dao: HabitLogDao
) : TrackRepository {

    override suspend fun addLog(log: HabitLogEntity) {
        dao.insertLog(log)
    }

    override fun getLogsByDate(date: String): Flow<List<HabitLogEntity>> {
        return dao.getLogsByDate(date)
    }

    override suspend fun deleteLogsByHabitId(habitId: Int) {
        dao.deleteLogsByHabitId(habitId)
    }

    override suspend fun updateLog(log: HabitLogEntity) {
        dao.updateLog(log)
    }

    override suspend fun getAllLogsOnce(): List<HabitLogEntity> {
        return dao.getAllLogsOnce()
    }
}
