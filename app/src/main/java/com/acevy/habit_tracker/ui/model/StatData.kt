package com.acevy.habit_tracker.ui.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

data class StatData(
    val title: String,
    val subtitle: String,
    val icon: ImageVector,
    val iconTint: Color
)