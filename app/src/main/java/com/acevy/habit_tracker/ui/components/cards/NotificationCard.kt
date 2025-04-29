package com.acevy.habit_tracker.ui.components.cards

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.acevy.habit_tracker.ui.theme.AppColors
import com.acevy.habit_tracker.ui.theme.AppShapes
import com.acevy.habit_tracker.ui.theme.AppType
import com.acevy.habit_tracker.ui.theme.HabitTrackerTheme
import com.acevy.habit_tracker.utils.generateNotificationMessage

@Composable
fun NotificationCard(
    modifier: Modifier = Modifier,
    emoji: String,
    habitTitle: String,
    timeInfo: String,
    isActive: Boolean = false
) {
    val backgroundColor = if (isActive) AppColors.OffWhite else AppColors.White
    val borderColor = if (isActive) AppColors.GreenPrimary else AppColors.GrayLight

    Card(
        modifier = modifier
            .fillMaxWidth()
            .shadow(
                elevation = 4.dp,
                shape = AppShapes.Rounded16,
                ambientColor = Color(0x40000000),
                spotColor = Color(0x40000000)
            )
            .border(1.5.dp, borderColor, AppShapes.Rounded16),
        shape = AppShapes.Rounded16,
        colors = CardDefaults.cardColors(containerColor = backgroundColor)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = emoji,
                fontSize = 40.sp,
                modifier = Modifier.padding(end = 16.dp)
            )

            Column {
                Text(
                    text = generateNotificationMessage(habitTitle),
                    style = AppType.body14,
                    color = AppColors.GrayDark
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = timeInfo,
                    style = AppType.roboto12,
                    color = AppColors.DarkBlue
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewNotificationCard() {
    HabitTrackerTheme {
        Column(modifier = Modifier.padding(16.dp)) {
            NotificationCard(
                emoji = "💼",
                habitTitle = "bekerja",
                timeInfo = "Sekitar 2 jam yang lalu",
                isActive = false
            )

            Spacer(modifier = Modifier.height(12.dp))

            NotificationCard(
                emoji = "💼",
                habitTitle = "main game",
                timeInfo = "Sekitar 15 menit lagi",
                isActive = true
            )
        }
    }
}

