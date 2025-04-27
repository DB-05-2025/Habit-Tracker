package com.acevy.habit_tracker.domain.usecase.userlevel

import com.acevy.habit_tracker.domain.repository.userlevel.UserLevelRepository

// Ambil user level by userId — return UserLevel?
class GetUserLevelUseCase(private val repo: UserLevelRepository) {
    suspend operator fun invoke(userId: Long) = repo.getUserLevelByUserId(userId)
}