package com.acevy.habit_tracker.domain.usecase

import com.acevy.habit_tracker.domain.model.UserLevel
import kotlinx.coroutines.flow.first

class UpdateUserLevelByXpUseCase(
    private val getUserLevelUseCase: GetUserLevelUseCase,
    private val updateUserLevelUseCase: UpdateUserLevelUseCase
) {
    suspend operator fun invoke(userId: Long, xpEarned: Int) {
        val userLevel = getUserLevelUseCase(userId).first() ?: UserLevel(
            id = 0,
            userId = userId,
            level = 1,
            currentXp = 0,
            updatedAt = System.currentTimeMillis()
        )

        var newXp = userLevel.currentXp + xpEarned
        var newLevel = userLevel.level

        // Looping untuk naik level jika XP cukup
        while (newXp >= getXpThresholdForLevel(newLevel)) {
            newXp -= getXpThresholdForLevel(newLevel)
            newLevel += 1
        }

        val updated = userLevel.copy(
            level = newLevel,
            currentXp = newXp,
            updatedAt = System.currentTimeMillis()
        )

        updateUserLevelUseCase(updated)
    }

    private fun getXpThresholdForLevel(level: Int): Int {
        return 100 + (level - 1) * 20 // Contoh: naik terus (100, 120, 140, ...)
    }
}

