package com.acevy.habit_tracker.domain.repository.userlevel

import com.acevy.habit_tracker.domain.model.userlevel.UserLevel

interface UserLevelRepository {
    suspend fun insertUserLevel(userLevel: UserLevel): Long
    suspend fun updateUserLevel(userLevel: UserLevel)
    suspend fun getUserLevelByUserId(userId: Long): UserLevel?
}