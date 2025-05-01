package com.acevy.habit_tracker.domain.repository

import com.acevy.habit_tracker.data.local.entity.HabitEntity
import kotlinx.coroutines.flow.Flow

interface HabitRepository {
    suspend fun insertHabit(habit: HabitEntity)
    suspend fun updateHabit(habit: HabitEntity)
    suspend fun deleteHabit(habit: HabitEntity)

    fun getAllHabits(): Flow<List<HabitEntity>>
    fun getHabitById(id: Int): Flow<HabitEntity?>
}

