package com.acevy.habit_tracker.data.repository

import com.acevy.habit_tracker.data.local.entity.HabitEntity
import com.acevy.habit_tracker.data.local.room.dao.HabitDao
import com.acevy.habit_tracker.domain.repository.HabitRepository
import kotlinx.coroutines.flow.Flow

class HabitRepositoryImpl(
    private val habitDao: HabitDao
) : HabitRepository {

    override suspend fun insertHabit(habit: HabitEntity) {
        habitDao.insertHabit(habit)
    }

    override suspend fun updateHabit(habit: HabitEntity) {
        habitDao.updateHabit(habit)
    }

    override suspend fun deleteHabit(habit: HabitEntity) {
        habitDao.deleteHabit(habit)
    }

    override fun getAllHabits(): Flow<List<HabitEntity>> {
        return habitDao.getAllHabits()
    }

    override fun getHabitById(id: Int): Flow<HabitEntity?> {
        return habitDao.getHabitById(id)
    }
}

