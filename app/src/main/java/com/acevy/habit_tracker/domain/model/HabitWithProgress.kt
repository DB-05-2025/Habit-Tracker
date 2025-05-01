package com.acevy.habit_tracker.domain.model

data class HabitWithProgress(
    val id: Int,
    val title: String,
    val done: Int,
    val total: Int
)