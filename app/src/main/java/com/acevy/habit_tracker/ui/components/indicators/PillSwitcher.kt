package com.acevy.habit_tracker.ui.components.indicators

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.acevy.habit_tracker.ui.theme.AppColors
import com.acevy.habit_tracker.ui.theme.AppShapes
import com.acevy.habit_tracker.ui.theme.AppType
import com.acevy.habit_tracker.ui.theme.HabitTrackerTheme

@Composable
fun PillSwitcher(
    selectedIndex: Int,
    onClick: (Int) -> Unit,
    showStacking: Boolean = true
) {
    val tabItems = if (showStacking) listOf("Tracking", "Stacking") else listOf("Tracking")

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .background(AppColors.GrayLight, shape = AppShapes.Rounded16)
            .padding(0.dp)
    ) {
        tabItems.forEachIndexed { index, label ->
            val isSelected = index == selectedIndex
            val bgColor = if (isSelected) AppColors.GreenPrimary else AppColors.GrayLight
            val textColor = if (isSelected) AppColors.White else AppColors.GrayDark

            Box(
                modifier = Modifier
                    .weight(1f)
                    .clip(AppShapes.Rounded16)
                    .background(bgColor)
                    .clickable { onClick(index) }
                    .padding(vertical = 8.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(label, style = AppType.semiBold14, color = textColor)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPillSwitcher_TrackingSelected() {
    HabitTrackerTheme {
        PillSwitcher(selectedIndex = 0, onClick = {})
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPillSwitcher_StackingSelected() {
    HabitTrackerTheme {
        PillSwitcher(selectedIndex = 1, onClick = {})
    }
}
