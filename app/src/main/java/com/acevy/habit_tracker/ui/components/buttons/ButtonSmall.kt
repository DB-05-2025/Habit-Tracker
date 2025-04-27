package com.acevy.habit_tracker.ui.components.buttons

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.acevy.habit_tracker.ui.theme.*

@Composable
fun ButtonSmall(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    variant: ButtonVariant = ButtonVariant.Filled,
    fullWidth: Boolean = false
) {
    val shape = RoundedCornerShape(8.dp)

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
            .height(20.dp)
    } else {
        modifier
            .wrapContentWidth()
            .height(20.dp)
    }

    Button(
        onClick = onClick,
        modifier = buttonModifier,
        shape = shape,
        colors = colors,
        border = border,
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 0.dp)
    ) {
        Text(
            text = text,
            style = AppType.medium12
        )
    }
}

@Preview(name = "Filled Small Button", showBackground = true)
@Composable
fun PreviewFilledSmallButton() {
    HabitTrackerTheme {
        ButtonSmall(
            text = "Atur",
            onClick = {},
            variant = ButtonVariant.Filled
        )
    }
}

@Preview(name = "Outlined Small Button", showBackground = true)
@Composable
fun PreviewOutlinedSmallButton() {
    HabitTrackerTheme {
        ButtonSmall(
            text = "Batal",
            onClick = {},
            variant = ButtonVariant.Outlined
        )
    }
}
