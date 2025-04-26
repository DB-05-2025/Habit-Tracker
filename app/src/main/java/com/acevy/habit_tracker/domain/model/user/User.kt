package com.acevy.habit_tracker.domain.model.user

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val userId: Long,
    val name: String,
    val createdAt: Long,
    val updatedAt: Long,
) : Parcelable