package com.acevy.habit_tracker.domain.model

data class UserLevel(
    val id: Long,
    val userId: Long,
    val level: Int,
    val currentXp: Int,
    val updatedAt: Long,
)