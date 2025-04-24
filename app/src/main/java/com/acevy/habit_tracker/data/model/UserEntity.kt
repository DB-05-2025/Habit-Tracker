package com.acevy.habit_tracker.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val firstName: String,
    val lastName: String,
    val gender: String,              // "male", "female", "other"
    val birthDate: String,          // yyyy-MM-dd
    val createdAt: Long = System.currentTimeMillis()
)