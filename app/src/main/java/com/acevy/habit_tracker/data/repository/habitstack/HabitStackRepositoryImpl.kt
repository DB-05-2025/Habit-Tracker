package com.acevy.habit_tracker.data.repository.habitstack

import com.acevy.habit_tracker.data.local.dao.habitstack.HabitStackDao
import com.acevy.habit_tracker.data.mapper.habitstack.toDomain
import com.acevy.habit_tracker.data.mapper.habitstack.toEntity
import com.acevy.habit_tracker.domain.model.habitstack.HabitStack
import com.acevy.habit_tracker.domain.repository.habitstack.HabitStackRepository
import kotlinx.coroutines.flow.map

class HabitStackRepositoryImpl(private val dao: HabitStackDao) : HabitStackRepository {
    override suspend fun insertHabitStack(habitStack: HabitStack): Long =
        dao.insertHabitStack(habitStack.toEntity())

    override suspend fun updateHabitStack(habitStack: HabitStack) =
        dao.updateHabitStack(habitStack.toEntity())

    override suspend fun deleteHabitStack(habitStack: HabitStack) =
        dao.deleteHabitStack(habitStack.toEntity())

    override fun getHabitStacksByUser(userId: Long) =
        dao.getHabitStacksByUser(userId).map { list -> list.map { it.toDomain() } }
}