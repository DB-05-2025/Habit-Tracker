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
    val reminderTime: String?,
    val createdAt: Long,
    val updatedAt: Long,
) : Parcelable