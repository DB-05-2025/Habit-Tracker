package com.acevy.habit_tracker.ui.components.cards

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
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
fun HabitStackCard(
    modifier: Modifier = Modifier,
    title: String,
    habits: List<String>,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .border(1.dp, AppColors.GrayLight, AppShapes.Rounded16),
        shape = AppShapes.Rounded16,
        colors = CardDefaults.cardColors(containerColor = AppColors.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    style = AppType.semiBold14,
                    color = AppColors.GrayDark
                )
                Spacer(modifier = Modifier.height(8.dp))
                habits.take(2).forEach {
                    Text("• $it", style = AppType.body12, color = AppColors.GrayDark)
                }
            }

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Edit",
                    tint = AppColors.BlueIcon,
                    modifier = Modifier.clickable { onEditClick() }
                )
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete",
                    tint = AppColors.RedIcon,
                    modifier = Modifier.clickable { onDeleteClick() }
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewHabitStackCard() {
    HabitTrackerTheme {
        HabitStackCard(
            title = "Rutinitas Selasa",
            habits = listOf("Bekerja", "Mabar"),
            onEditClick = {},
            onDeleteClick = {}
        )
    }
}
