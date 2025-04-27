package com.acevy.habit_tracker.data.repository.habit

import com.acevy.habit_tracker.data.local.dao.habit.HabitDao
import com.acevy.habit_tracker.data.mapper.habit.toDomain
import com.acevy.habit_tracker.data.mapper.habit.toEntity
import com.acevy.habit_tracker.domain.model.habit.Habit
import com.acevy.habit_tracker.domain.repository.habit.HabitRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class HabitRepositoryImpl(private val dao: HabitDao) : HabitRepository {
    override suspend fun insertHabit(habit: Habit): Long = dao.insertHabit(habit.toEntity())

    override suspend fun updateHabit(habit: Habit) = dao.updateHabit(habit.toEntity())

    override suspend fun deleteHabit(habit: Habit) = dao.deleteHabit(habit.toEntity())

    override fun getHabitsByUser(userId: Long): Flow<List<Habit>> =
        dao.getHabitsByUser(userId).map { list -> list.map { it.toDomain() } }
}