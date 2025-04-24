package com.acevy.habit_tracker.domain.usecase

import com.acevy.habit_tracker.domain.repository.UserLevelRepository

class GetUserLevelUseCase(private val repo: UserLevelRepository) {
    operator fun invoke(userId: Long) = repo.getUserLevel(userId)
}