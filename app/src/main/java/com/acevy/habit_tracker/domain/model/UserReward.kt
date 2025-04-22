package com.acevy.habit_tracker.domain.model

data class UserReward(
    val id: Long,
    val userId: Long,
    val rewardTypeId: Long,
    val amount: Int,
    val source: String,
    val earnedAt: Long
)