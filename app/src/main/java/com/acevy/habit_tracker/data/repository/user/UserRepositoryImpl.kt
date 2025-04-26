package com.acevy.habit_tracker.data.repository.user

import com.acevy.habit_tracker.data.local.dao.user.UserDao
import com.acevy.habit_tracker.data.mapper.user.toDomain
import com.acevy.habit_tracker.data.mapper.user.toEntity
import com.acevy.habit_tracker.domain.model.user.User
import com.acevy.habit_tracker.domain.repository.user.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserRepositoryImpl(private val dao: UserDao) : UserRepository {

    override suspend fun insertUser(user: User): Long {
        return dao.insertUser(user.toEntity())
    }

    override fun getUserById(userId: Long): Flow<User?> {
        return dao.getUserById(userId).map { it?.toDomain() }
    }

    override suspend fun updateUser(user: User) {
        dao.updateUser(user.toEntity())
    }
}