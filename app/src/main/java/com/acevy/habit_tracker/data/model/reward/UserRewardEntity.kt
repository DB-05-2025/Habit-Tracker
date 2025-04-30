package com.acevy.habit_tracker.data.model.reward

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_reward")
data class UserRewardEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val userId: Long,
    val rewardTypeId: Long,
    val amount: Int,
    val source: String, // "habit_completed", "challenge", etc
    val earnedAt: Long = System.currentTimeMillis()
)