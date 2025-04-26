package com.acevy.habit_tracker.ui.navigation

sealed class Screen(val route: String) {
    data object Onboarding : Screen("onboarding")
    data object GetStarted : Screen("getstarted")
    data object Main : Screen("main")
    data object Home : Screen("home")
    data object Habit : Screen("habit")
}