package com.acevy.habit_tracker.ui.components.progressitem

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.acevy.habit_tracker.ui.theme.AppColors
import com.acevy.habit_tracker.ui.theme.AppType
import com.acevy.habit_tracker.ui.theme.HabitTrackerTheme

@Composable
fun CompletedHabitItem(
    name: String,
    total: Int
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = name,
            style = AppType.body14,
            color = AppColors.GrayDark,
            modifier = Modifier.weight(1f)
        )

        Text(
            text = "$total/$total",
            style = AppType.body14,
            color = AppColors.GreenPrimary
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCompletedHabitItem() {
    HabitTrackerTheme {
        Column(modifier = Modifier.padding(16.dp)) {
            CompletedHabitItem(name = "Bekerja", total = 7)
            Spacer(modifier = Modifier.height(8.dp))
            CompletedHabitItem(name = "Olahraga", total = 5)
        }
    }
}
