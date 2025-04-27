package com.acevy.habit_tracker.domain.usecase

import com.acevy.habit_tracker.domain.model.UserLevel
import com.acevy.habit_tracker.domain.repository.UserLevelRepository

// Digunakan kalau user baru belum punya UserLevel -> buat UserLevel awal.
class InsertUserLevelUseCase(private val repo: UserLevelRepository) {
    suspend operator fun invoke(userLevel: UserLevel): Long {
        return repo.insertUserLevel(userLevel)
    }
}