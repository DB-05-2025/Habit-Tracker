package com.acevy.habit_tracker.ui.model

data class HabitOption(
    val id: Long,
    val name: String,
    var isSelected: Boolean = false
)