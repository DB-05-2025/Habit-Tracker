package com.acevy.habit_tracker.ui.screens.habit

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.acevy.habit_tracker.ui.theme.AppColors
import com.acevy.habit_tracker.ui.theme.AppType
import com.acevy.habit_tracker.ui.components.indicators.PillSwitcher
import com.acevy.habit_tracker.ui.screens.habit.habitstack.HabitStackingPage
import com.acevy.habit_tracker.ui.screens.habit.habittrack.HabitTrackingPage
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HabitScreen(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState(initialPage = 0, pageCount = { 2 })

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Habits", style = AppType.bold20, modifier = Modifier.padding(top = 12.dp)) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = AppColors.White)
            )
        },
        modifier = Modifier.padding(horizontal = 8.dp),
        containerColor = AppColors.White
    ) { paddingValues ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            PillSwitcher(
                selectedIndex = pagerState.currentPage,
                onClick = { index ->
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                }
            )

            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .fillMaxSize()
            ) { page ->
                when (page) {
                    0 -> HabitTrackingPage(navController = navController)
                    1 -> HabitStackingPage(navController = navController)
                }
            }
        }
    }
}