package com.acevy.habit_tracker

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.acevy.habit_tracker.ui.navigation.AppNavHost

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HabitTrackerApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    AppNavHost(
        navController = navController,
        modifier = modifier
    )
}