package com.acevy.habit_tracker.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val id: Long,
    val firstName: String,
    val lastName: String,
    val gender: String,
    val birthDate: String,
    val createdAt: Long,
) : Parcelable