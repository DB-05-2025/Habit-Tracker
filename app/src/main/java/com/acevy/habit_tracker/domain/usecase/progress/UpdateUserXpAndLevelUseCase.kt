package com.acevy.habit_tracker.domain.usecase.progress

import com.acevy.habit_tracker.data.local.entity.UserProgressEntity
import com.acevy.habit_tracker.domain.repository.ProgressRepository
import kotlinx.coroutines.flow.firstOrNull

class UpdateUserXpAndLevelUseCase(
    private val repo: ProgressRepository
) {
    suspend operator fun invoke(xpEarned: Int) {
        val now = System.currentTimeMillis()

        // Ambil progress lama, atau buat data baru
        val progress = repo.getProgress().firstOrNull() ?: UserProgressEntity(
            id = 1,
            level = 1,
            currentXp = 0,
            updatedAt = now
        )

        var newXp = progress.currentXp + xpEarned
        var newLevel = progress.level

        while (newXp >= getXpThresholdForLevel(newLevel)) {
            newXp -= getXpThresholdForLevel(newLevel)
            newLevel++
        }

        val updatedProgress = progress.copy(
            level = newLevel,
            currentXp = newXp,
            updatedAt = now
        )

        repo.upsertProgress(updatedProgress)
    }

    private fun getXpThresholdForLevel(level: Int): Int {
        return 100 + (level - 1) * 20
    }
}