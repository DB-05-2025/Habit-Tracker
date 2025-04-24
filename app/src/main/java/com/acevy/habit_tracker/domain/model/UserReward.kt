package com.acevy.habit_tracker.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserReward(
    val id: Long,
    val userId: Long,
    val amount: Int,
    val source: String,
    val earnedAt: Long,
) : Parcelable