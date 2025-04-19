package com.acevy.habit_tracker.domain.repository

import com.acevy.habit_tracker.domain.model.Habit
import kotlinx.coroutines.flow.Flow

interface HabitRepository {
    suspend fun addHabit(habit: Habit)
    suspend fun updateHabit(habit: Habit)
    suspend fun deleteHabit(habit: Habit)
    fun getHabitsByUser(userId: Long): Flow<List<Habit>>
}