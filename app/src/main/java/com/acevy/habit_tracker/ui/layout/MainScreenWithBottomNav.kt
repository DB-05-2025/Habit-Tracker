package com.acevy.habit_tracker.ui.layout

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.acevy.habit_tracker.ui.components.navigation.BottomNavBar
import com.acevy.habit_tracker.ui.navigation.Screen
import com.acevy.habit_tracker.ui.theme.AppColors

@Composable
fun MainScreenWithBottomNav(
    navController: NavHostController,
    currentRoute: String,
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        bottomBar = {
            BottomNavBar(navController = navController)
        },
        floatingActionButton = {
            when (currentRoute) {
                Screen.Habit.route -> {
                    FloatingActionButton(
                        onClick = {
                            navController.navigate(Screen.AddHabit.route)
                        },
                        shape = CircleShape,
                        containerColor = AppColors.GreenPrimary
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Tambah Habit",
                            tint = Color.White
                        )
                    }
                }
            }
        },
        containerColor = AppColors.White
    ) { paddingValues ->
        content(paddingValues)
    }
}
