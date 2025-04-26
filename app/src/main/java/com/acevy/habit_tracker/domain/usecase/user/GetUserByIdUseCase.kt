package com.acevy.habit_tracker.domain.usecase.user

import com.acevy.habit_tracker.domain.repository.user.UserRepository

class GetUserByIdUseCase(private val repo: UserRepository) {
    operator fun invoke(userId: Long) = repo.getUserById(userId)
}