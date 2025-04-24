package com.acevy.habit_tracker.ui.components.cards

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.acevy.habit_tracker.ui.theme.AppColors
import com.acevy.habit_tracker.ui.theme.AppShapes
import com.acevy.habit_tracker.ui.theme.AppType
import com.acevy.habit_tracker.ui.theme.HabitTrackerTheme

@Composable
fun SelectableLargeCard(
    modifier: Modifier = Modifier,
    emoji: String,
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val borderColor = if (isSelected) AppColors.GreenPrimary else AppColors.GrayLight
    val backgroundColor = if (isSelected) AppColors.OffWhite else AppColors.White

    Card(
        modifier = modifier
            .width(170.dp)
            .height(150.dp)
            .border(
                width = 1.dp,
                color = borderColor,
                shape = AppShapes.Rounded16
            )
            .background(backgroundColor, AppShapes.Rounded16)
            .clickable { onClick() },
        shape = AppShapes.Rounded16,
        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 0.dp)
    ) {
        Column(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = emoji,
                fontSize = 32.sp,
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = label,
                style = AppType.semiBold14,
                color = AppColors.GrayDark
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSelectableCard() {
    HabitTrackerTheme {
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            SelectableLargeCard(
                emoji = "👨‍💻",
                label = "Pekerjaan",
                isSelected = true,
                onClick = {}
            )
            SelectableLargeCard(
                emoji = "🎨",
                label = "Seni",
                isSelected = false,
                onClick = {}
            )
        }
    }
}