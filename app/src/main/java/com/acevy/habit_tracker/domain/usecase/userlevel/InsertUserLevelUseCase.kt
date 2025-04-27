package com.acevy.habit_tracker.domain.usecase.userlevel

import com.acevy.habit_tracker.domain.model.userlevel.UserLevel
import com.acevy.habit_tracker.domain.repository.userlevel.UserLevelRepository

// Digunakan kalau user baru belum punya UserLevel -> buat UserLevel awal.
class InsertUserLevelUseCase(private val repo: UserLevelRepository) {
    suspend operator fun invoke(userLevel: UserLevel): Long {
        return repo.insertUserLevel(userLevel)
    }
}