package com.acevy.habit_tracker.domain.repository.habit

import com.acevy.habit_tracker.domain.model.habit.Habit
import kotlinx.coroutines.flow.Flow

interface HabitRepository {
    suspend fun insertHabit(habit: Habit): Long
    suspend fun updateHabit(habit: Habit)
    suspend fun deleteHabit(habit: Habit)
    fun getHabitsByUser(userId: Long): Flow<List<Habit>>
}