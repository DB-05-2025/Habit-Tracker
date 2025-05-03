package com.acevy.habit_tracker.ui.components.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ShowChart
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.DashboardCustomize
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.acevy.habit_tracker.ui.model.NavigationItem
import com.acevy.habit_tracker.ui.navigation.Screen
import com.acevy.habit_tracker.ui.theme.AppColors
import com.acevy.habit_tracker.ui.theme.AppShapes
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
        NavigationItem("Kemajuan", Icons.AutoMirrored.Filled.ShowChart, Screen.Progress),
        NavigationItem("Notifications", Icons.Default.Notifications, Screen.Notification),
        NavigationItem("Journaling", Icons.Default.Book, Screen.Journal),
    )

    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(AppColors.DarkBlue)
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        navItems.forEach { item ->
            val isSelected = currentRoute == item.screen.route || currentRoute?.startsWith(item.screen.route) == true

            val iconTint = if (isSelected) AppColors.DarkBlue else AppColors.OffWhite
            val pillColor = if (isSelected) AppColors.OffWhite else Color.Transparent

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.clickable {
                    navController.navigate(item.screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .width(60.dp)
                        .height(20.dp)
                        .clip(AppShapes.Rounded16)
                        .background(pillColor)
                ) {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title,
                        tint = iconTint,
                        modifier = Modifier.size(20.dp)
                    )
                }

                if (isSelected) {
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = item.title,
                        style = AppType.semiBold12,
                        color = AppColors.OffWhite
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF1B2633)
@Composable
fun PreviewBottomNavBar() {
    val fakeNavController = rememberNavController()
    BottomNavBar(navController = fakeNavController)
}