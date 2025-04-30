package com.acevy.habit_tracker.ui.components.accordions

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.acevy.habit_tracker.ui.theme.AppColors
import com.acevy.habit_tracker.ui.theme.AppShapes
import com.acevy.habit_tracker.ui.theme.AppType
import com.acevy.habit_tracker.ui.theme.HabitTrackerTheme

@Composable
fun DisabledAccordion(
    label: String,
    modifier: Modifier = Modifier
) {
    Surface(
        shape = AppShapes.Rounded16,
        color = AppColors.Gray,
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = label,
                style = AppType.semiBold14,
                color = AppColors.White
            )
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                imageVector = Icons.Default.ExpandMore,
                contentDescription = null,
                tint = AppColors.White
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDisabledAccordion() {
    HabitTrackerTheme {
        Column(modifier = Modifier.padding(16.dp)) {
            DisabledAccordion("⏳ Sedang Berjalan")
            DisabledAccordion("🎉 Selesai")
            DisabledAccordion("😔 Terlewat")
        }
    }
}
