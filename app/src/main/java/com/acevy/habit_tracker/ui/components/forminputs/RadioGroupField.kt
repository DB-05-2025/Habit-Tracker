package com.acevy.habit_tracker.ui.components.forminputs

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
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
import com.acevy.habit_tracker.ui.theme.AppType
import com.acevy.habit_tracker.ui.theme.HabitTrackerTheme

@Composable
fun RadioGroupField(
    modifier: Modifier = Modifier,
    options: List<String>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit,
) {
    Column(modifier = modifier) {
        options.forEach { option ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onOptionSelected(option) }
            ) {
                RadioButton(
                    selected = selectedOption == option,
                    onClick = { onOptionSelected(option) },
                    colors = RadioButtonDefaults.colors(
                        selectedColor = AppColors.GreenPrimary,
                        unselectedColor = AppColors.GrayLight
                    )
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = option,
                    style = AppType.body14,
                    color = AppColors.GrayDark
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewRadioGroupField() {
    var selectedOption by remember { mutableStateOf("Harian") }
    val options = listOf("Setiap jam", "Harian", "Mingguan", "Bulanan", "Ulangi")

    HabitTrackerTheme {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Frekuensi",
                style = AppType.bold16,
                color = AppColors.GrayDark
            )
            Spacer(modifier = Modifier.height(8.dp))
            RadioGroupField(
                options = options,
                selectedOption = selectedOption,
                onOptionSelected = { selectedOption = it }
            )
        }
    }
}