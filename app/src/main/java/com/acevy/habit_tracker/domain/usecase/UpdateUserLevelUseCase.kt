package com.acevy.habit_tracker.domain.usecase

import com.acevy.habit_tracker.domain.model.UserLevel
import com.acevy.habit_tracker.domain.repository.UserLevelRepository

// Update data UserLevel yang sudah ada
class UpdateUserLevelUseCase(private val repo: UserLevelRepository) {
    suspend operator fun invoke(userLevel: UserLevel) {
        repo.updateUserLevel(userLevel)
    }
}