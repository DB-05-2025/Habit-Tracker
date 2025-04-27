package com.acevy.habit_tracker.ui.components.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ShowChart
import androidx.compose.material.icons.filled.DashboardCustomize
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.ShowChart
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.acevy.habit_tracker.ui.model.NavigationItem
import com.acevy.habit_tracker.ui.navigation.Screen
import com.acevy.habit_tracker.ui.theme.AppColors
import com.acevy.habit_tracker.ui.theme.AppType

@Composable
fun BottomNavBar(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val navBackStackEntry = navController.currentBackStackEntryAsState().value
    val currentRoute = navBackStackEntry?.destination?.route

    val navItems = listOf(
        NavigationItem("Home", Icons.Default.Home, Screen.Home),
        NavigationItem("Habits", Icons.Default.DashboardCustomize, Screen.Habit),
        NavigationItem("Progress", Icons.AutoMirrored.Filled.ShowChart, Screen.Progress),
        NavigationItem("Notifications", Icons.Default.Notifications, Screen.Notification),
        )

    NavigationBar(modifier = modifier, containerColor = AppColors.White) {
        navItems.forEach { item ->
            NavigationBarItem(
                selected = currentRoute == item.screen.route || currentRoute?.startsWith(item.screen.route) == true,
                onClick = {
                    navController.navigate(item.screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title,
                        tint = if (currentRoute == item.screen.route)
                            AppColors.GreenPrimary
                        else
                            AppColors.Gray
                    )
                },
                label = {
                    Text(
                        text = item.title,
                        style = AppType.body10
                    )
                },
                alwaysShowLabel = false
            )
        }
    }
}

