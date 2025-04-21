package com.acevy.habit_tracker.domain.usecase

import com.acevy.habit_tracker.domain.model.RewardType
import com.acevy.habit_tracker.domain.model.UserReward
import com.acevy.habit_tracker.domain.repository.RewardRepository

class AddRewardUseCase(
    private val repo: RewardRepository,
) {
    suspend operator fun invoke(
        userId: Long,
        amount: Int,
        source: String,
        rewardTypeName: String = "XP",
    ) {
        // Pastikan reward type "XP" tersedia
        val rewardType = repo.getRewardTypeByName(rewardTypeName) ?: run {
            val newType = RewardType(0, rewardTypeName, "Experience Points")
            repo.insertRewardType(newType)
            repo.getRewardTypeByName(rewardTypeName)!!
        }

        // Insert reward XP
        val reward = UserReward(
            id = 0,
            userId = userId,
            rewardTypeId = rewardType.id,
            amount = amount,
            source = source,
            earnedAt = System.currentTimeMillis()
        )

        repo.addUserReward(reward)
    }
}