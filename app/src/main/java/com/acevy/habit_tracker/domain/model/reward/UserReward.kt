package com.acevy.habit_tracker.domain.model.reward

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserReward(
    val userRewardId: Long,
    val userId: Long,
    val amount: Int,
    val source: String,
    val earnedAt: Long,
) : Parcelable