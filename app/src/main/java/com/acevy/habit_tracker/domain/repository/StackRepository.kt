package com.acevy.habit_tracker.domain.repository

import com.acevy.habit_tracker.data.local.entity.HabitStackEntity
import kotlinx.coroutines.flow.Flow

interface StackRepository {
    suspend fun insertStack(data: HabitStackEntity)
    suspend fun updateStack(data: HabitStackEntity)
    suspend fun deleteStack(data: HabitStackEntity)
    fun getAllStacks(): Flow<List<HabitStackEntity>>
    fun getStackById(id: Int): Flow<HabitStackEntity?>
}
