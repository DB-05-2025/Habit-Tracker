package com.acevy.habit_tracker.ui.components.accordions

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.acevy.habit_tracker.ui.theme.AppColors
import com.acevy.habit_tracker.ui.theme.AppType
import com.acevy.habit_tracker.ui.theme.HabitTrackerTheme

@Composable
fun ExpandableAccordion(
    title: String,
    content: @Composable () -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        // HEADER - hijau dengan rounded top + bawah kalau collapsed
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(
                    RoundedCornerShape(
                        topStart = 16.dp,
                        topEnd = 16.dp,
                        bottomStart = if (!expanded) 16.dp else 0.dp,
                        bottomEnd = if (!expanded) 16.dp else 0.dp
                    )
                )
                .background(AppColors.GreenPrimary)
                .clickable { expanded = !expanded }
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = title,
                    style = AppType.bold16,
                    color = AppColors.White
                )

                Icon(
                    imageVector = if (expanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                    contentDescription = "Expand",
                    tint = AppColors.White
                )
            }
        }

        // KONTEN - putih dengan rounded bottom kalau expanded
        AnimatedVisibility(visible = expanded) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp))
                    .background(AppColors.White)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    content()
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewExpandableAccordion() {
    HabitTrackerTheme {
        Column(modifier = Modifier.padding(16.dp)) {
            ExpandableAccordion("⏳ Sedang Berjalan") {
                Text("Habit 1: Bekerja")
                Text("Habit 2: Olahraga")
            }

            Spacer(modifier = Modifier.height(8.dp))

            ExpandableAccordion("🎉 Selesai") {
                Text("Habit 1: Makan 7/7")
                Text("Habit 2: Tidur 5/5")
            }

            Spacer(modifier = Modifier.height(8.dp))

            ExpandableAccordion("😔 Terlewat") {
                Text("Habit 1: Bekerja 5/10")
                Text("Habit 2: Tidur 1/5")
            }
        }
    }
}

