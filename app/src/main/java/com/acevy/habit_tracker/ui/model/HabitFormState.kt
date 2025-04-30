package com.acevy.habit_tracker.ui.model

data class HabitFormState(
    val title: String,
    val repeatDays: Set<Int>,
    val reminderTime: String,
    val note: String? = null,
)