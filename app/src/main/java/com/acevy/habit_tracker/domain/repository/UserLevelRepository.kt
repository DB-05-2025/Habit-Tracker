package com.acevy.habit_tracker.domain.repository

import com.acevy.habit_tracker.domain.model.UserLevel
import kotlinx.coroutines.flow.Flow

interface UserLevelRepository {
    suspend fun insertOrUpdate(userLevel: UserLevel)
    fun getUserLevel(userId: Long): Flow<UserLevel?>
}