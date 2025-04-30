package com.acevy.habit_tracker.ui.components.progressitem

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Text
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.acevy.habit_tracker.ui.theme.AppColors
import com.acevy.habit_tracker.ui.theme.AppType
import com.acevy.habit_tracker.ui.theme.HabitTrackerTheme

@Composable
fun MissedHabitItem(
    name: String,
    completed: Int,
    total: Int
) {
    val progress = completed.toFloat() / total.coerceAtLeast(1)

    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 6.dp)) {
        Text(
            text = name,
            style = AppType.body14,
            color = AppColors.GrayDark
        )

        Spacer(modifier = Modifier.height(6.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            LinearProgressIndicator(
                progress = { progress },
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(8.dp)),
                color = AppColors.Orange,
                trackColor = AppColors.GrayLight,
                strokeCap = StrokeCap.Square,
                gapSize = 0.dp,
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = "$completed/$total",
                style = AppType.body12,
                color = AppColors.GrayDark
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMissedHabitItem() {
    HabitTrackerTheme {
        Column(modifier = Modifier.padding(16.dp)) {
            MissedHabitItem(name = "Makan", completed = 3, total = 7)
            Spacer(modifier = Modifier.height(8.dp))
            MissedHabitItem(name = "Olahraga", completed = 5, total = 10)
        }
    }
}
