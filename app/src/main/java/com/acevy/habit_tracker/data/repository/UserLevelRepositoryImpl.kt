package com.acevy.habit_tracker.data.repository

import com.acevy.habit_tracker.data.local.dao.UserLevelDao
import com.acevy.habit_tracker.data.mapper.toDomain
import com.acevy.habit_tracker.data.mapper.toEntity
import com.acevy.habit_tracker.domain.model.UserLevel
import com.acevy.habit_tracker.domain.repository.UserLevelRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserLevelRepositoryImpl(
    private val dao: UserLevelDao,
) : UserLevelRepository {
    override suspend fun insertOrUpdate(userLevel: UserLevel) {
        dao.insertOrUpdate(userLevel.toEntity())
    }

    override fun getUserLevel(userId: Long): Flow<UserLevel?> {
        return dao.getUserLevel(userId).map { it?.toDomain() }
    }
}