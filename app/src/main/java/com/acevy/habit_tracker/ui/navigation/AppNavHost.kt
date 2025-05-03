package com.acevy.habit_tracker.ui.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import com.acevy.habit_tracker.data.local.datastore.UserPreferences
import com.acevy.habit_tracker.ui.ViewModelFactory
import com.acevy.habit_tracker.ui.layout.MainScreenWithBottomNav
import com.acevy.habit_tracker.ui.screens.habit.habittrack.AddHabitScreen
import com.acevy.habit_tracker.ui.screens.habit.HabitScreen
import com.acevy.habit_tracker.ui.screens.habit.habitstack.AddStackScreen
import com.acevy.habit_tracker.ui.screens.habit.habitstack.UpdateStackScreen
import com.acevy.habit_tracker.ui.screens.habit.habittrack.UpdateHabitScreen
import com.acevy.habit_tracker.ui.screens.home.HomeScreen
import com.acevy.habit_tracker.ui.screens.journal.JournalScreen
import com.acevy.habit_tracker.ui.screens.notification.NotificationScreen
import com.acevy.habit_tracker.ui.screens.onboarding.GetStartedScreen
import com.acevy.habit_tracker.ui.screens.onboarding.OnboardingScreen
import com.acevy.habit_tracker.ui.screens.progress.ProgressScreen
import com.acevy.habit_tracker.ui.screens.splash.SplashScreen
import com.acevy.habit_tracker.ui.viewmodel.HabitViewModel
import com.acevy.habit_tracker.utils.ReminderScheduler
import kotlinx.coroutines.delay

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {
    val context = LocalContext.current
    val userPreferences = remember { UserPreferences(context) }
    val isOnboardingCompleted by userPreferences.onboardingCompletedFlow.collectAsState(initial = null)

    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route,
        modifier = modifier
    ) {
        composable(Screen.Splash.route) {
            val context = LocalContext.current
            var isDelayFinished by remember { mutableStateOf(false) }

            LaunchedEffect(Unit) {
                delay(2000)
                isDelayFinished = true
            }

            when {
                !isDelayFinished -> {
                    SplashScreen()
                }

                isOnboardingCompleted == true -> {
                    val viewModel: HabitViewModel = viewModel(factory = ViewModelFactory(context))

                    LaunchedEffect(Unit) {
                        val habits = viewModel.getAllHabitsOnce()
                        ReminderScheduler.scheduleTodayReminders(context, habits)

                        navController.navigate(Screen.Main.route) {
                            popUpTo(Screen.Splash.route) { inclusive = true }
                        }
                    }
                }

                isOnboardingCompleted == false -> {
                    LaunchedEffect(Unit) {
                        navController.navigate(Screen.Onboarding.route) {
                            popUpTo(Screen.Splash.route) { inclusive = true }
                        }
                    }
                }
            }
        }


        composable(Screen.Onboarding.route) {
            var hasFinished by remember { mutableStateOf(false) }

            if (hasFinished) {
                LaunchedEffect(Unit) {
                    navController.navigate(Screen.GetStarted.route) {
                        popUpTo(Screen.Onboarding.route) { inclusive = true }
                    }
                }
            }

            OnboardingScreen(
                onFinish = {
                    hasFinished = true
                },
                userPreferences = userPreferences
            )
        }
        composable(Screen.GetStarted.route) {
            var submittedName by remember { mutableStateOf<String?>(null) }

            if (submittedName != null) {
                LaunchedEffect(Unit) {
                    userPreferences.setUserName(submittedName!!)
                    userPreferences.setOnboardingCompleted(true)
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

            composable(Screen.Notification.route) {
                MainScreenWithBottomNav(
                    navController = navController,
                    currentRoute = Screen.Notification.route
                ) { padding ->
                    NotificationScreen(modifier = Modifier.padding(padding))
                }
            }

            composable(Screen.Journal.route) {
                MainScreenWithBottomNav(
                    navController = navController,
                    currentRoute = Screen.Journal.route
                ) { padding ->
                    JournalScreen(modifier = Modifier.padding(padding))
                }
            }

            composable(Screen.Progress.route) {
                MainScreenWithBottomNav(
                    navController = navController,
                    currentRoute = Screen.Notification.route
                ) { padding ->
                    ProgressScreen(
                        userPreferences = userPreferences,
                        modifier = Modifier.padding(padding)
                    )
                }
            }

            composable(Screen.Habit.route) {
                MainScreenWithBottomNav(
                    navController = navController,
                    currentRoute = Screen.Habit.route
                ) { padding ->
                    HabitScreen(
                        modifier = Modifier.padding(padding),
                        navController = navController
                    )
                }
            }

            composable(Screen.AddHabit.route) {
                AddHabitScreen(
                    onBack = { navController.popBackStack() },
                    navController = navController
                )
            }

            composable(
                route = Screen.UpdateHabit.route,
                arguments = listOf(navArgument("habitId") { type = NavType.IntType })
            ) { backStackEntry ->
                val habitId = backStackEntry.arguments?.getInt("habitId") ?: return@composable
                val viewModel: HabitViewModel = viewModel(factory = ViewModelFactory(LocalContext.current))
                val habit by viewModel.getHabitById(habitId).collectAsState(initial = null)

                habit?.let {
                    UpdateHabitScreen(
                        habit = it,
                        onBack = { navController.popBackStack() },
                        navController = navController,
                        viewModel = viewModel
                    )
                }
            }

            composable(Screen.AddStack.route) {
                AddStackScreen(
                    onBack = { navController.popBackStack() },
                    navController = navController
                )
            }

            composable(
                route = Screen.UpdateStack.route,
                arguments = listOf(navArgument("stackId") { type = NavType.IntType })
            ) { backStackEntry ->
                val stackId = backStackEntry.arguments?.getInt("stackId") ?: return@composable
                UpdateStackScreen(
                    stackId = stackId,
                    onBack = { navController.popBackStack() },
                    navController = navController
                )
            }
        }
    }
}
