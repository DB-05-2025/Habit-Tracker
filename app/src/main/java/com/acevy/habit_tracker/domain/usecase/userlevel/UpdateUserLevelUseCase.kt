package com.acevy.habit_tracker.domain.usecase.userlevel

import com.acevy.habit_tracker.domain.model.userlevel.UserLevel
import com.acevy.habit_tracker.domain.repository.userlevel.UserLevelRepository

// Update data UserLevel yang sudah ada
class UpdateUserLevelUseCase(private val repo: UserLevelRepository) {
    suspend operator fun invoke(userLevel: UserLevel) = repo.updateUserLevel(userLevel)
}