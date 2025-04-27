package com.acevy.habit_tracker.ui.model

data class HabitFormState(
    val title: String = "",
    val note: String = "",
    val repeatDays: Set<Int> = emptySet(),
    val reminderEnabled: Boolean = false
)