package com.acevy.habit_tracker.ui.components.cards

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.acevy.habit_tracker.ui.theme.AppColors
import com.acevy.habit_tracker.ui.theme.AppShapes
import com.acevy.habit_tracker.ui.theme.AppType
import com.acevy.habit_tracker.ui.theme.HabitTrackerTheme

@Composable
fun HabitCardItem(
    modifier: Modifier = Modifier,
    habitName: String,
    isChecked: Boolean = false,
    isCheckable: Boolean = false,
    showTrailingIcon: Boolean = true,
    onCheckedChange: (Boolean) -> Unit = {},
    onClick: () -> Unit = {}
) {
    val borderColor = if (isChecked) AppColors.GreenPrimary else AppColors.GrayLight
    val backgroundColor = if (isChecked) AppColors.OffWhite else AppColors.White

    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(60.dp)
            .clickable { onClick() },
        shape = AppShapes.Rounded16,
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        border = BorderStroke(1.5.dp, borderColor),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 0.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = habitName,
                style = AppType.body14,
                color = AppColors.GrayDark
            )

            if (showTrailingIcon) {
                if (isCheckable) {
                    Checkbox(
                        checked = isChecked,
                        onCheckedChange = onCheckedChange,
                        colors = CheckboxDefaults.colors(
                            checkedColor = AppColors.GreenPrimary,
                            uncheckedColor = AppColors.GrayLight
                        )
                    )
                } else {
                    Icon(
                        imageVector = Icons.Default.MoreVert,
                        contentDescription = null,
                        tint = AppColors.GrayDark
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewHabitCardItem() {
    HabitTrackerTheme {
        var isChecked by remember { mutableStateOf(true) }

        Column(verticalArrangement = Arrangement.spacedBy(12.dp), modifier = Modifier.padding(16.dp)) {
            HabitCardItem(habitName = "Bekerja", isChecked = isChecked, isCheckable = true, onCheckedChange = { isChecked = it })
            HabitCardItem(habitName = "Olahraga", isChecked = false, isCheckable = true)
            HabitCardItem(habitName = "Makan", showTrailingIcon = true)
        }
    }
}
