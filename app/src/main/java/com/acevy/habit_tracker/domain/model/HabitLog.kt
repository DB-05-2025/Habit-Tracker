package com.acevy.habit_tracker.domain.model

data class HabitLog(
    val id: Long,
    val habitId: Long,
    val date: String,
    val status: String,
    val note: String?,
    val createdAt: Long,
    val updatedAt: Long,
)