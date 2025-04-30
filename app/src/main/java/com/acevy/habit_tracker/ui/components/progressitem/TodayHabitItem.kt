package com.acevy.habit_tracker.ui.components.progressitem

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Text
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.acevy.habit_tracker.ui.theme.AppColors
import com.acevy.habit_tracker.ui.theme.AppType
import com.acevy.habit_tracker.ui.theme.HabitTrackerTheme

@Composable
fun TodayHabitItem(
    name: String,
    isCompleted: Boolean
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

        Icon(
            imageVector = if (isCompleted) Icons.Default.Check else Icons.Default.Close,
            contentDescription = if (isCompleted) "Selesai" else "Belum",
            tint = if (isCompleted) Color(0xFF00C853) else Color.Red
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewTodayHabitItem() {
    HabitTrackerTheme {
        Column(modifier = Modifier.padding(16.dp)) {
            TodayHabitItem(name = "Bekerja", isCompleted = true)
            Spacer(modifier = Modifier.height(8.dp))
            TodayHabitItem(name = "Makan", isCompleted = false)
        }
    }
}
