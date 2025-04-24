package com.acevy.habit_tracker.ui.components.forminputs

import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.acevy.habit_tracker.ui.theme.AppColors
import com.acevy.habit_tracker.ui.theme.HabitTrackerTheme

@Composable
fun SwitchField(
    modifier: Modifier = Modifier,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
) {
    Switch(
        checked = checked,
        onCheckedChange = onCheckedChange,
        modifier = modifier,
        colors = SwitchDefaults.colors(
            checkedTrackColor = AppColors.GreenPrimary,
            uncheckedTrackColor = AppColors.GrayLight,
            checkedThumbColor = AppColors.White,
            uncheckedThumbColor = AppColors.White,
            uncheckedBorderColor = Color.Transparent
        )
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewSwitchField() {
    HabitTrackerTheme {
        var isChecked by remember { mutableStateOf(true) }

        SwitchField(
            checked = isChecked,
            onCheckedChange = { isChecked = it }
        )
    }
}