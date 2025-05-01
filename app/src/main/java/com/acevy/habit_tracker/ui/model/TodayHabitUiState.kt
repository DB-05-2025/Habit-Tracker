package com.acevy.habit_tracker.ui.model

import com.acevy.habit_tracker.data.local.entity.HabitStatus

data class TodayHabitUiState(
    val habitId: Int,
    val title: String,
    val status: HabitStatus
)
