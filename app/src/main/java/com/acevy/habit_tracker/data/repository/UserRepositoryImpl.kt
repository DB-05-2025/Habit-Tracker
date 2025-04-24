package com.acevy.habit_tracker.data.repository

import com.acevy.habit_tracker.data.local.dao.UserDao
import com.acevy.habit_tracker.data.mapper.toDomain
import com.acevy.habit_tracker.data.mapper.toEntity
import com.acevy.habit_tracker.domain.model.User
import com.acevy.habit_tracker.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserRepositoryImpl(private val dao: UserDao) : UserRepository {
    override suspend fun insertUser(user: User): Long {
        return dao.insertUser(user.toEntity())
    }

    override fun getUserById(userId: Long): Flow<User?> {
        return dao.getUserById(userId).map { it?.toDomain() }
    }
}