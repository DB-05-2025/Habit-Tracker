package com.acevy.habit_tracker.data.repository

import com.acevy.habit_tracker.data.local.entity.HabitStackEntity
import com.acevy.habit_tracker.data.local.room.dao.HabitStackDao
import com.acevy.habit_tracker.domain.repository.StackRepository
import kotlinx.coroutines.flow.Flow

class StackRepositoryImpl(
    private val dao: HabitStackDao
) : StackRepository {

    override suspend fun insertStack(data: HabitStackEntity) = dao.insertStack(data)

    override suspend fun updateStack(data: HabitStackEntity) = dao.updateStack(data)

    override suspend fun deleteStack(data: HabitStackEntity) = dao.deleteStack(data)

    override fun getAllStacks(): Flow<List<HabitStackEntity>> = dao.getAllStacks()

    override fun getStackById(id: Int): Flow<HabitStackEntity?> = dao.getStackById(id)
}
