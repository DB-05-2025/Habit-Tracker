package com.acevy.habit_tracker.domain.model

import com.acevy.habit_tracker.data.local.entity.HabitStatus

data class HabitWithStatus(
    val logId: Int,
    val habitId: Int,
    val title: String,
    val status: HabitStatus
)