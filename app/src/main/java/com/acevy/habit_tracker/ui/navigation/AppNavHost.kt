package com.acevy.habit_tracker.ui.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.acevy.habit_tracker.data.local.datastore.UserPreferences
import com.acevy.habit_tracker.ui.layout.MainScreenWithBottomNav
import com.acevy.habit_tracker.ui.screens.habit.HabitScreen
import com.acevy.habit_tracker.ui.screens.home.HomeScreen
import com.acevy.habit_tracker.ui.screens.onboarding.GetStartedScreen
import com.acevy.habit_tracker.ui.screens.onboarding.OnboardingScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val userPreferences = remember { UserPreferences(context) }
    val startDestination = remember { mutableStateOf(Screen.Onboarding.route) }

    val isOnboardingCompleted by userPreferences.onboardingCompletedFlow.collectAsState(initial = false)

    LaunchedEffect(Unit) {
        startDestination.value = if (isOnboardingCompleted) Screen.Home.route else Screen.Onboarding.route
    }

    NavHost(
        navController = navController,
        startDestination = startDestination.value,
        modifier = modifier
    ) {
        composable(Screen.Onboarding.route) {
            var hasFinished by remember { mutableStateOf(false) }

            if (hasFinished) {
                LaunchedEffect(Unit) {
                    userPreferences.setOnboardingCompleted(true)
                    navController.navigate(Screen.GetStarted.route) {
                        popUpTo(Screen.Onboarding.route) { inclusive = true }
                    }
                }
            }

            OnboardingScreen(
                onFinish = {
                    hasFinished = true
                }
            )
        }
        composable(Screen.GetStarted.route) {
            var submittedName by remember { mutableStateOf<String?>(null) }

            if (submittedName != null) {
                LaunchedEffect(Unit) {
                    userPreferences.setUserName(submittedName!!)
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.GetStarted.route) { inclusive = true }
                    }
                }
            }

            GetStartedScreen(
                onSubmit = { name ->
                    submittedName = name
                }
            )
        }
        navigation(
            startDestination = Screen.Home.route,
            route = Screen.Main.route
        ) {
            composable(Screen.Home.route) {
                MainScreenWithBottomNav(
                    navController = navController,
                    currentRoute = Screen.Home.route
                ) { padding ->
                    HomeScreen(
                        userPreferences = userPreferences,
                        navController = navController,
                        modifier = Modifier.padding(padding)
                    )
                }
            }

            composable(Screen.Habit.route) {
                MainScreenWithBottomNav(
                    navController = navController,
                    currentRoute = Screen.Habit.route
                ) { padding ->
                    HabitScreen(modifier = Modifier.padding(padding))
                }
            }
        }
    }
}
