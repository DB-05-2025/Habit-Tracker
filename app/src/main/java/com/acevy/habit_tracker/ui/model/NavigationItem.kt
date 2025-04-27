package com.acevy.habit_tracker.ui.model

import androidx.compose.ui.graphics.vector.ImageVector
import com.acevy.habit_tracker.ui.navigation.Screen

data class NavigationItem(
    val title: String,
    val icon: ImageVector,
    val screen: Screen
)