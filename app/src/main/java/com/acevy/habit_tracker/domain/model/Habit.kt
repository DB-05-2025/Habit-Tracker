package com.acevy.habit_tracker.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Habit(
    val id: Long,
    val userId: Long,
    val title: String,
    val description: String,
    val goalType: String,
    val category: String,
    val startDate: String,
    val endDate: String?,
    val reminderTime: String?,
    val reminderType: String?,
    val reminderDays: List<Int>?,
    val createdAt: Long,
    val updatedAt: Long,
) : Parcelable