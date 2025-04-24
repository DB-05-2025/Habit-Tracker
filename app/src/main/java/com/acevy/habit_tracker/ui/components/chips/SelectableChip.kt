package com.acevy.habit_tracker.ui.components.chips

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.acevy.habit_tracker.ui.theme.AppColors
import com.acevy.habit_tracker.ui.theme.AppType

@Composable
fun SelectableChip(
    modifier: Modifier = Modifier,
    text: String,
    selected: Boolean,
    onClick: () -> Unit,
) {
    Surface(
        modifier = modifier
            .clip(RoundedCornerShape(50))
            .clickable(onClick = onClick),
        color = if (selected) AppColors.GreenPrimary else AppColors.White,
        contentColor = if (selected) AppColors.White else AppColors.GreenPrimary,
        border = BorderStroke(2.dp, AppColors.GreenPrimary),
        shadowElevation = 0.dp
    ) {
        Text(
            text = text,
            style = AppType.semiBold14,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSelectableChip() {
    var selected by remember { mutableStateOf("Kesehatan") }
    val categories = listOf("Kesehatan", "Pekerjaan", "Seni", "Sosial")

    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        categories.forEach { category ->
            SelectableChip(
                text = category,
                selected = selected == category,
                onClick = { selected = category }
            )
        }
    }
}