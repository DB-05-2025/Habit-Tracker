package com.acevy.habit_tracker.ui.model

data class HabitOption(
    val id: Int,
    val name: String,
    var isSelected: Boolean = false
)