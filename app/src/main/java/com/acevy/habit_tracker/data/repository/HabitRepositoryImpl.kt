package com.acevy.habit_tracker.data.repository

import com.acevy.habit_tracker.data.local.dao.HabitDao
import com.acevy.habit_tracker.data.mapper.toDomain
import com.acevy.habit_tracker.data.mapper.toEntity
import com.acevy.habit_tracker.domain.model.Habit
import com.acevy.habit_tracker.domain.repository.HabitRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class HabitRepositoryImpl(private val dao: HabitDao) : HabitRepository {

    override suspend fun addHabit(habit: Habit) {
        dao.insertHabit(habit.toEntity())
    }

    override suspend fun updateHabit(habit: Habit) {
        dao.updateHabit(habit.toEntity())
    }

    override suspend fun deleteHabit(habit: Habit) {
        dao.deleteHabit(habit.toEntity())
    }

    override fun getHabitsByUser(userId: Long): Flow<List<Habit>> {
        return dao.getHabitsByUser(userId).map { list -> list.map { it.toDomain() } }
    }
}