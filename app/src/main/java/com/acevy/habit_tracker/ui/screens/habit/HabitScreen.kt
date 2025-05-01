package com.acevy.habit_tracker.ui.screens.habit

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.acevy.habit_tracker.ui.ViewModelFactory
import com.acevy.habit_tracker.ui.theme.AppColors
import com.acevy.habit_tracker.ui.theme.AppType
import com.acevy.habit_tracker.ui.components.indicators.PillSwitcher
import com.acevy.habit_tracker.ui.screens.habit.habitstack.HabitStackingPage
import com.acevy.habit_tracker.ui.screens.habit.habittrack.HabitTrackingPage
import com.acevy.habit_tracker.ui.viewmodel.HabitViewModel
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HabitScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: HabitViewModel = viewModel(factory = ViewModelFactory(LocalContext.current))
) {
    val coroutineScope = rememberCoroutineScope()
    val habits by viewModel.habitList.collectAsState()
    val pageCount = if (habits.size >= 2) 2 else 1
    val pagerState = rememberPagerState(initialPage = 0, pageCount = { pageCount })

    Log.d("DBUG HABIT ROOM", "HabitScreen: $habits")
    Log.d("DBUG HABIT ROOM SIZE", "HabitScreen: ${habits.size}")

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Habits", style = AppType.bold20, modifier = Modifier.padding(top = 12.dp)) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = AppColors.White)
            )
        },
        modifier = Modifier.padding(horizontal = 8.dp),
        containerColor = AppColors.White,
    ) { paddingValues ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            PillSwitcher(
                showStacking = habits.size >= 2,
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