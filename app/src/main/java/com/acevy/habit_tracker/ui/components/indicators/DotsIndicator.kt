package com.acevy.habit_tracker.ui.components.indicators

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.acevy.habit_tracker.ui.theme.AppColors
import androidx.compose.ui.unit.dp


@Composable
fun DotsIndicator(
    modifier: Modifier = Modifier,
    currentPage: Int,
    totalPages: Int,
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        modifier = modifier
    ) {
        repeat(totalPages) { index ->
            val isSelected = index == currentPage
            Box(
                modifier = modifier
                    .width(if (isSelected) 20.dp else 8.dp)
                    .height(if (isSelected) 8.dp else 8.dp)
                    .clip(CircleShape)
                    .background(AppColors.DarkBlue)
            )
        }
    }
}