package com.acevy.habit_tracker.domain.usecase

import com.acevy.habit_tracker.domain.model.UserLevel
import com.acevy.habit_tracker.domain.repository.RewardRepository

class UpdateUserLevelUseCase(
    private val repo: RewardRepository,
) {
    suspend operator fun invoke(userId: Long, additionalXp: Int) {
        val now = System.currentTimeMillis()

        val current = repo.getUserLevel(userId) ?: UserLevel(
            id = 0,
            userId = userId,
            level = 1,
            currentXp = 0,
            updatedAt = now
        )

        var newLevel = current.level
        var newXp = current.currentXp + additionalXp

        while (newXp >= xpNeededForLevel(newLevel + 1)) {
            newXp -= xpNeededForLevel(newLevel + 1)
            newLevel += 1
        }

        val updated = current.copy(
            level = newLevel,
            currentXp = newXp,
            updatedAt = now
        )

        repo.upsertUserLevel(updated)
    }

    private fun xpNeededForLevel(level: Int): Int {
        return when (level) {
            in 1..10 -> 100
            in 11..30 -> 200
            in 31..60 -> 300
            in 61..100 -> 500
            else -> 1000
        }
    }
}