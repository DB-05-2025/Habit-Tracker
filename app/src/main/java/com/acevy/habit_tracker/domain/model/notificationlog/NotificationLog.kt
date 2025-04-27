package com.acevy.habit_tracker.domain.model.notificationlog

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NotificationLog(
    val notificationId: Long,
    val userId: Long,
    val habitId: Long?,
    val title: String,
    val message: String,
    val sentAt: Long,
    val type: String,
) : Parcelable