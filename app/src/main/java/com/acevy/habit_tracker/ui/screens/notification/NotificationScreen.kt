package com.acevy.habit_tracker.ui.screens.notification

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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.acevy.habit_tracker.ui.components.cards.NotificationCard
import com.acevy.habit_tracker.ui.model.NotificationItemCardUiState
import com.acevy.habit_tracker.ui.theme.AppColors
import com.acevy.habit_tracker.ui.theme.AppType

@Composable
fun NotificationScreen(modifier: Modifier = Modifier) {
    val dummyNotifications = remember {
        listOf(
            NotificationItemCardUiState("💼", "Bekerja", "Sekitar 2 jam yang lalu", isActive = false),
            NotificationItemCardUiState("💪", "Olahraga", "Sekitar 15 menit lagi", isActive = true),
            NotificationItemCardUiState("📖", "Membaca Buku", "5 jam yang lalu", isActive = false)
        )
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

        if (dummyNotifications.isEmpty()) {
            item {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    AsyncImage(
                        model = "https://ofcnpgzapkplcbfngucb.supabase.co/storage/v1/object/sign/habit-tracker/empty-notif.png?token=...",
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
            items(dummyNotifications) { notif ->
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
