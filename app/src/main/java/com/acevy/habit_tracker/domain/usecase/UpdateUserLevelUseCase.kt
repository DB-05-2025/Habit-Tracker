package com.acevy.habit_tracker.domain.usecase

import com.acevy.habit_tracker.domain.model.UserLevel
import com.acevy.habit_tracker.domain.repository.UserLevelRepository

class UpdateUserLevelUseCase(private val repo: UserLevelRepository) {
    suspend operator fun invoke(userLevel: UserLevel) {
        repo.insertOrUpdate(userLevel)
    }
}