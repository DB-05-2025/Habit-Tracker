package com.acevy.habit_tracker.domain.repository

import com.acevy.habit_tracker.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun insertUser(user: User): Long
    fun getUserById(userId: Long): Flow<User?>
}