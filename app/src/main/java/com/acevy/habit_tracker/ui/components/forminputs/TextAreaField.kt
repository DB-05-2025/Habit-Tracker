package com.acevy.habit_tracker.ui.components.forminputs

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.acevy.habit_tracker.ui.theme.AppColors
import com.acevy.habit_tracker.ui.theme.AppShapes
import com.acevy.habit_tracker.ui.theme.AppType
import com.acevy.habit_tracker.ui.theme.HabitTrackerTheme

@Composable
fun TextAreaField(
    modifier: Modifier = Modifier,
    value: String,
    placeholder: String = "",
    onValueChange: (String) -> Unit,
) {
    Box(modifier = modifier) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp),
            shape = AppShapes.Rounded16,
            textStyle = AppType.body14,
            placeholder = {
                Text(
                    text = placeholder,
                    style = AppType.body14.copy(color = AppColors.GrayLight)
                )
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = AppColors.White,
                unfocusedContainerColor = AppColors.White,
                focusedIndicatorColor = AppColors.GreenPrimary,
                unfocusedIndicatorColor = AppColors.GrayLight,
                disabledIndicatorColor = AppColors.GrayLight
            ),
            maxLines = 5,
            singleLine = false,
        )
    }
}

@Preview(name = "TextAreaField Default", showBackground = true)
@Composable
fun PreviewTextAreaField() {
    var value by remember { mutableStateOf("") }

    HabitTrackerTheme {
        TextAreaField(
            value = value,
            onValueChange = { value = it }
        )
    }
}