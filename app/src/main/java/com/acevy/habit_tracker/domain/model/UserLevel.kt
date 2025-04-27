package com.acevy.habit_tracker.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserLevel(
    val userLevelId: Long,
    val userId: Long,
    val level: Int,
    val currentXp: Int,
    val updatedAt: Long
) : Parcelable