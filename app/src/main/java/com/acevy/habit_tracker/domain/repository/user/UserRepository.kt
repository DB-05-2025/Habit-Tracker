package com.acevy.habit_tracker.domain.repository.user

import com.acevy.habit_tracker.domain.model.user.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun insertUser(user: User): Long
    fun getUserById(userId: Long): Flow<User?>
    suspend fun updateUser(user: User)
}