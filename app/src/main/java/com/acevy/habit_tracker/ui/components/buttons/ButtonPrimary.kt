package com.acevy.habit_tracker.ui.components.buttons

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.acevy.habit_tracker.ui.theme.AppColors
import com.acevy.habit_tracker.ui.theme.AppShapes
import com.acevy.habit_tracker.ui.theme.AppType
import com.acevy.habit_tracker.ui.theme.HabitTrackerTheme

enum class ButtonVariant {
    Filled, Outlined
}

@Composable
fun ButtonPrimary(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    variant: ButtonVariant = ButtonVariant.Filled,
    fullWidth: Boolean = false
) {
    val shape = AppShapes.Rounded16

    val colors = when (variant) {
        ButtonVariant.Filled -> ButtonDefaults.buttonColors(
            containerColor = AppColors.DarkBlue,
            contentColor = AppColors.White
        )
        ButtonVariant.Outlined -> ButtonDefaults.buttonColors(
            containerColor = AppColors.White,
            contentColor = AppColors.DarkBlue
        )
    }

    val border = when (variant) {
        ButtonVariant.Outlined -> BorderStroke(1.dp, AppColors.DarkBlue)
        else -> null
    }

    val buttonModifier = if (fullWidth) {
        modifier
            .fillMaxWidth()
            .height(40.dp)
    } else {
        modifier
            .wrapContentWidth()
            .height(40.dp)
    }

    Button(
        onClick = onClick,
        modifier = buttonModifier,
        shape = shape,
        colors = colors,
        border = border,
        contentPadding = PaddingValues(horizontal = 24.dp)
    ) {
        Text(
            text = text,
            style = AppType.bold16
        )
    }
}

@Preview(name = "Filled Button", showBackground = true)
@Composable
fun PreviewFilledAppButton() {
    HabitTrackerTheme {
        ButtonPrimary(
            text = "Mulai",
            onClick = {},
            variant = ButtonVariant.Filled,
            fullWidth = true
        )
    }
}

@Preview(name = "Outlined Button", showBackground = true)
@Composable
fun PreviewOutlinedAppButton() {
    HabitTrackerTheme {
        ButtonPrimary(
            text = "Lewati",
            onClick = {},
            variant = ButtonVariant.Outlined
        )
    }
}