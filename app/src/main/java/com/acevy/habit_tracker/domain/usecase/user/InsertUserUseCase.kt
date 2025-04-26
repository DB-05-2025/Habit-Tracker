package com.acevy.habit_tracker.domain.usecase.user

import com.acevy.habit_tracker.domain.model.user.User
import com.acevy.habit_tracker.domain.repository.user.UserRepository

class InsertUserUseCase(private val repo: UserRepository) {
    suspend operator fun invoke(user: User): Long = repo.insertUser(user)
}