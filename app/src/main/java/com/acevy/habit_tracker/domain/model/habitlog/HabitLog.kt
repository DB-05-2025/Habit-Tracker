package com.acevy.habit_tracker.domain.model.habitlog

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class HabitLog(
    val habitLogId: Long,
    val habitId: Long,
    val date: String,
    val status: String,
    val note: String?,
    val createdAt: Long,
    val updatedAt: Long
) : Parcelable