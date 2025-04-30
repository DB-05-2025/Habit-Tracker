package com.acevy.habit_tracker.domain.model.userlevel

data class UserLevel(
    val id: Long,
    val userId: Long,
    val level: Int,
    val currentXp: Int,
    val updatedAt: Long,
)