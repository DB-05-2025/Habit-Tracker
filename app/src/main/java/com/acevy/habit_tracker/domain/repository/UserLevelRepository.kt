package com.acevy.habit_tracker.domain.repository

import com.acevy.habit_tracker.domain.model.UserLevel
import kotlinx.coroutines.flow.Flow

interface UserLevelRepository {
    suspend fun insertUserLevel(userLevel: UserLevel): Long
    suspend fun updateUserLevel(userLevel: UserLevel)
    suspend fun getUserLevelByUserId(userId: Long): UserLevel?
}