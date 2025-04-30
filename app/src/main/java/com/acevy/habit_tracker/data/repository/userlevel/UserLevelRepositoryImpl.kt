package com.acevy.habit_tracker.data.repository.userlevel

import com.acevy.habit_tracker.data.local.dao.userlevel.UserLevelDao
import com.acevy.habit_tracker.data.mapper.userlevel.toDomain
import com.acevy.habit_tracker.data.mapper.userlevel.toEntity
import com.acevy.habit_tracker.domain.model.userlevel.UserLevel
import com.acevy.habit_tracker.domain.repository.userlevel.UserLevelRepository

class UserLevelRepositoryImpl(
    private val dao: UserLevelDao,
) : UserLevelRepository {
    override suspend fun insertUserLevel(userLevel: UserLevel): Long =
        dao.insertUserLevel(userLevel.toEntity())

    override suspend fun updateUserLevel(userLevel: UserLevel) =
        dao.updateUserLevel(userLevel.toEntity())

    override suspend fun getUserLevelByUserId(userId: Long): UserLevel? =
        dao.getUserLevelByUserId(userId)?.toDomain()
}