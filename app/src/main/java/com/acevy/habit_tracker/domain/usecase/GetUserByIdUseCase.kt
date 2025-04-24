package com.acevy.habit_tracker.domain.usecase

import com.acevy.habit_tracker.domain.repository.UserRepository

class GetUserByIdUseCase(private val repo: UserRepository) {
    operator fun invoke(userId: Long) = repo.getUserById(userId)
}