package com.acevy.habit_tracker.ui.screens.notification

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.acevy.habit_tracker.ui.ViewModelFactory
import com.acevy.habit_tracker.ui.components.cards.NotificationCard
import com.acevy.habit_tracker.ui.components.indicators.AsyncImageWithIndicator
import com.acevy.habit_tracker.ui.theme.AppColors
import com.acevy.habit_tracker.ui.theme.AppType
import com.acevy.habit_tracker.ui.viewmodel.HabitViewModel
import com.acevy.habit_tracker.ui.viewmodel.NotificationViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NotificationScreen(
    modifier: Modifier = Modifier,
    notificationViewModel: NotificationViewModel = viewModel(factory = ViewModelFactory(LocalContext.current)),
    habitViewModel: HabitViewModel = viewModel(factory = ViewModelFactory(LocalContext.current))
) {
    val notifications by notificationViewModel.notificationsUiState.collectAsState()
    val habits by habitViewModel.habitList.collectAsState()

    LaunchedEffect(habits) {
        notificationViewModel.generateNotificationsFromHabits(habits)
    }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        contentPadding = PaddingValues(vertical = 24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Text(
                text = "Notifikasi",
                style = AppType.bold20.copy(textAlign = TextAlign.Start),
                color = AppColors.GrayDark,
                modifier = Modifier.fillMaxWidth()
            )
        }

        if (notifications.isEmpty()) {
            item {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    AsyncImageWithIndicator(
                        model = "https://ofcnpgzapkplcbfngucb.supabase.co/storage/v1/object/sign/habit-tracker/empty-notif.png?token=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6InN0b3JhZ2UtdXJsLXNpZ25pbmcta2V5XzFmMGNmZDEyLTQzNTktNDZlOS1iODRiLTMyYmViZDFhODY3ZiJ9.eyJ1cmwiOiJoYWJpdC10cmFja2VyL2VtcHR5LW5vdGlmLnBuZyIsImlhdCI6MTc0NjE1MDc2NCwiZXhwIjoxNzc3Njg2NzY0fQ._1PT7O1XHPGu1hm3SdRB_kZ7h4ucDl1YBJbwene2ImM",
                        contentDescription = "Empty Notification",
                        modifier = Modifier
                            .height(200.dp)
                            .padding(vertical = 8.dp)
                    )

                    Text(
                        text = "Masih belum ada notif nih",
                        style = AppType.body14,
                        color = AppColors.GrayDark
                    )
                }
            }
        } else {
            items(notifications) { notif ->
                NotificationCard(
                    emoji = notif.emoji,
                    habitTitle = notif.habitTitle,
                    timeInfo = notif.timeInfo,
                    isActive = notif.isActive
                )
            }
        }
    }
}
