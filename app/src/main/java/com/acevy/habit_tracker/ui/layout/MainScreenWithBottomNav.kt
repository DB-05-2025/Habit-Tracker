package com.acevy.habit_tracker.ui.layout

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.navigation.NavHostController
import com.acevy.habit_tracker.ui.components.navigation.BottomNavBar
import com.acevy.habit_tracker.ui.theme.AppColors
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun MainScreenWithBottomNav(
    navController: NavHostController,
    currentRoute: String,
    content: @Composable (PaddingValues) -> Unit
) {
    val systemUiController = rememberSystemUiController()

    SideEffect {
        systemUiController.setNavigationBarColor(
            color = AppColors.DarkBlue,
            darkIcons = false
        )
    }

    Scaffold(
        bottomBar = {
            BottomNavBar(navController = navController)
        },
//        floatingActionButton = {
//            when (currentRoute) {
//                Screen.Habit.route -> {
//
//                }
//            }
//        },
        containerColor = AppColors.White
    ) { paddingValues ->
        content(paddingValues)
    }
}
