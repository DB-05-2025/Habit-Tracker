package com.acevy.habit_tracker.domain.model.habitstack

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class HabitStack(
    val habitStackId: Long,
    val userId: Long,
//    val name: String?, /*TAMBAHAN DEVY*/
    val primaryHabitId: Long,
    val stackedHabitId: Long,
    val createdAt: Long,
) : Parcelable