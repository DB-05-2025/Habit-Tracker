package com.acevy.habit_tracker.domain.model.habit

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Habit(
    val habitId: Long,
    val userId: Long,
    val title: String,
    val note: String?,
    val repeatDays: List<Int>?,
    val reminderTime: String?,
    val createdAt: Long,
    val updatedAt: Long
) : Parcelable