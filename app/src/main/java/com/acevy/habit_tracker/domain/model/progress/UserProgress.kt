package com.acevy.habit_tracker.domain.model.progress

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserProgress(
    val userName: String,
    val level: Int,
    val currentXp: Int,
    val totalHabits: Int,
    val habitTodayCount: Int,
    val completedHabits: Int,
    val skippedHabits: Int,
) : Parcelable