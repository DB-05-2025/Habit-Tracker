package com.acevy.habit_tracker.ui.screens.home

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.acevy.habit_tracker.data.local.datastore.UserPreferences
import com.acevy.habit_tracker.data.local.entity.HabitStatus
import com.acevy.habit_tracker.ui.ViewModelFactory
import com.acevy.habit_tracker.ui.components.cards.HabitCardItem
import com.acevy.habit_tracker.ui.components.cards.HomeProgressCard
import com.acevy.habit_tracker.ui.components.indicators.AsyncImageWithIndicator
import com.acevy.habit_tracker.ui.components.navigation.BottomNavBar
import com.acevy.habit_tracker.ui.model.HabitItemUiState
import com.acevy.habit_tracker.ui.theme.AppColors
import com.acevy.habit_tracker.ui.theme.AppType
import com.acevy.habit_tracker.ui.viewmodel.LogViewModel
import com.acevy.habit_tracker.utils.getFormattedToday
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeScreen(
    userPreferences: UserPreferences,
    navController: NavController,
    modifier: Modifier = Modifier,
    logViewModel: LogViewModel = viewModel(factory = ViewModelFactory(LocalContext.current))
) {
    val userName by userPreferences.userNameFlow.collectAsState(initial = "")
    val today = remember {
        SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
    }
    val todayDisplay = remember {
        val rawDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(today)
        SimpleDateFormat("EEEE, d MMMM yyyy", Locale("id", "ID")).format(rawDate!!)
    }

    val todayLogs by logViewModel.todayHabitStatus.collectAsState()

    val total = todayLogs.size
    val done = todayLogs.count { it.status == HabitStatus.DONE }
    val progress = if (total > 0) done.toFloat() / total else 0f


    LaunchedEffect(Unit) {
        logViewModel.fetchAndGenerate(today)
    }

    Scaffold(
        bottomBar = {
            BottomNavBar(navController = navController)
        },
        containerColor = AppColors.White
    ) { innerPadding ->
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 24.dp),
            contentPadding = PaddingValues(vertical = 24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Text(
                    text = todayDisplay,
                    style = AppType.body12,
                    color = AppColors.GrayDark
                )
            }

            item {
                Text(
                    buildAnnotatedString {
                        append("Hello, ")
                        withStyle(SpanStyle(color = AppColors.GreenPrimary)) {
                            append(userName)
                        }
                        append("!")
                    },
                    style = AppType.bold20,
                    color = AppColors.GrayDark
                )
            }

            item {
                HomeProgressCard(
                    percent = progress,
                    text = "$done dari $total kebiasaan\nhari ini kamu telah\ndiselesaikan"
                )            }

            item {
                Text(
                    text = "Kebiasaan Hari ini",
                    style = AppType.bold16,
                    color = AppColors.GrayDark
                )
            }

            if (todayLogs.isEmpty()) {
                item {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        AsyncImageWithIndicator(
                            model = "https://ofcnpgzapkplcbfngucb.supabase.co/storage/v1/object/sign/habit-tracker/empty-habit.png?token=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6InN0b3JhZ2UtdXJsLXNpZ25pbmcta2V5XzFmMGNmZDEyLTQzNTktNDZlOS1iODRiLTMyYmViZDFhODY3ZiJ9.eyJ1cmwiOiJoYWJpdC10cmFja2VyL2VtcHR5LWhhYml0LnBuZyIsImlhdCI6MTc0NTgzMDc5MCwiZXhwIjoxNzc3MzY2NzkwfQ.fyk0hSnp_HViUogl20cPgi7dt235fwSNLqMFq9bah1A",
                            contentDescription = "Empty Habit",
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(180.dp)
                                .padding(vertical = 16.dp)
                        )

                        Text(
                            text = "Yuk buat habit baru dulu",
                            style = AppType.body14,
                            color = AppColors.GrayDark
                        )
                    }
                }
            } else {
                items(todayLogs) { habit ->
                    HabitCardItem(
                        habitName = habit.title,
                        isCheckable = true,
                        isChecked = habit.status == HabitStatus.DONE,
                        onCheckedChange = { checked ->
                            val newStatus = if (checked) HabitStatus.DONE else HabitStatus.PENDING
                            logViewModel.updateLogStatus(
                                logId = habit.logId,
                                habitId = habit.habitId,
                                status = newStatus,
                                date = today
                            )
                        }
                    )
                }
            }
        }
    }
}
