package com.acevy.habit_tracker.domain.usecase

import com.acevy.habit_tracker.domain.repository.UserLevelRepository

// Ambil user level by userId — return UserLevel?
class GetUserLevelUseCase(private val repo: UserLevelRepository) {
    suspend operator fun invoke(userId: Long) = repo.getUserLevelByUserId(userId)
}