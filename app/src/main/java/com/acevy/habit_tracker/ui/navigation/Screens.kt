package com.acevy.habit_tracker.ui.navigation

sealed class Screen(val route: String) {
    data object Splash : Screen("splash")
    data object Onboarding : Screen("onboarding")
    data object GetStarted : Screen("getstarted")
    data object Main : Screen("main")
    data object Home : Screen("home")
    data object Progress : Screen("progress")
    data object Notification : Screen("notification")

    data object Habit : Screen("habit")
    data object AddHabit : Screen("addhabit")
    data object UpdateHabit : Screen("updatehabit")
    data object AddStack : Screen("addstack")
    data object UpdateStack : Screen("updatestack")
}