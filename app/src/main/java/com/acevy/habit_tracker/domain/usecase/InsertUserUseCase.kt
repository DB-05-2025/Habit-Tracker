package com.acevy.habit_tracker.domain.usecase

import com.acevy.habit_tracker.domain.model.User
import com.acevy.habit_tracker.domain.repository.UserRepository

class InsertUserUseCase(private val repo: UserRepository) {
    suspend operator fun invoke(user: User): Long = repo.insertUser(user)
}