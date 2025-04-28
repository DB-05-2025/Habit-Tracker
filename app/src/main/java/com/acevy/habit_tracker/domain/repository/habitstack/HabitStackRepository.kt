package com.acevy.habit_tracker.domain.repository.habitstack

import com.acevy.habit_tracker.domain.model.habitstack.HabitStack
import kotlinx.coroutines.flow.Flow

interface HabitStackRepository {
    suspend fun insertHabitStack(habitStack: HabitStack): Long
    suspend fun updateHabitStack(habitStack: HabitStack)
    suspend fun deleteHabitStack(habitStack: HabitStack)
    fun getHabitStacksByUser(userId: Long): Flow<List<HabitStack>>
}