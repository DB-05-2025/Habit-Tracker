package com.acevy.habit_tracker.domain.usecase

import com.acevy.habit_tracker.domain.model.UserLevel
import com.acevy.habit_tracker.domain.repository.UserLevelRepository

// Logika otomatis tambah XP -> naik Level -> threshold bertahap -> insert/update otomatis.
class UpdateUserXpAndLevelUseCase(private val repo: UserLevelRepository) {

    suspend operator fun invoke(userId: Long, xpEarned: Int) {
        val userLevel = repo.getUserLevelByUserId(userId)

        val updatedLevel = if (userLevel == null) {
            // Jika user belum punya data level
            UserLevel(
                userLevelId = 0,
                userId = userId,
                level = 1,
                currentXp = 0,
                updatedAt = System.currentTimeMillis()
            )
        } else {
            userLevel
        }

        var newXp = updatedLevel.currentXp + xpEarned
        var newLevel = updatedLevel.level

        // Loop XP Threshold untuk naik level
        while (newXp >= getXpThresholdForLevel(newLevel)) {
            newXp -= getXpThresholdForLevel(newLevel)
            newLevel++
        }

        val finalLevel = updatedLevel.copy(
            level = newLevel,
            currentXp = newXp,
            updatedAt = System.currentTimeMillis()
        )

        if (userLevel == null) {
            repo.insertUserLevel(finalLevel)
        } else {
            repo.updateUserLevel(finalLevel)
        }
    }

    private fun getXpThresholdForLevel(level: Int): Int {
        return 100 + (level - 1) * 20
    }
}

