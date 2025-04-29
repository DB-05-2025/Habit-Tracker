package com.acevy.habit_tracker.ui.screens.progress

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ListAlt
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material.icons.filled.Stairs
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.acevy.habit_tracker.data.local.datastore.UserPreferences
import com.acevy.habit_tracker.ui.components.accordions.DisabledAccordion
import com.acevy.habit_tracker.ui.components.accordions.ExpandableAccordion
import com.acevy.habit_tracker.ui.components.cards.ProgressGreetingCard
import com.acevy.habit_tracker.ui.components.cards.StatCard
import com.acevy.habit_tracker.ui.components.progressitem.CompletedHabitItem
import com.acevy.habit_tracker.ui.components.progressitem.MissedHabitItem
import com.acevy.habit_tracker.ui.components.progressitem.TodayHabitItem
import com.acevy.habit_tracker.ui.model.StatData
import com.acevy.habit_tracker.ui.theme.AppColors
import com.acevy.habit_tracker.ui.theme.AppType

@Composable
fun ProgressScreen(
    userPreferences: UserPreferences,
    modifier: Modifier = Modifier
) {
    val userName by userPreferences.userNameFlow.collectAsState(initial = "")
    val statList = listOf(
        StatData("Level 0", "saat ini", Icons.Default.Stairs, AppColors.RedIcon),
        StatData("0 Habit", "telah dibuat", Icons.AutoMirrored.Filled.ListAlt, AppColors.PurpleIcon),
        StatData("0 XP", "telah dicapai", Icons.Default.Flag, AppColors.BlueIcon)
    )

    val todayHabits = listOf(
        "Bekerja" to true,
        "Olahraga" to false,
        "Mandi" to true,
    )

    val completedHabits = listOf(
        "Makan" to 5,
        "Tidur" to 7
    )

    val missedHabits = listOf(
        Triple("Belajar", 3, 7),
        Triple("Ngoding", 2, 5),
        Triple("Membaca", 6, 10)
    )

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        contentPadding = PaddingValues(vertical = 24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Text(
                text = "Kemajuan",
                style = AppType.bold20,
                color = AppColors.GrayDark
            )
        }

        item {
            ProgressGreetingCard(userName)
        }

        item {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                statList.forEach { (title, subtitle, icon, tint) ->
                    StatCard(title, subtitle, icon, tint)
                }
            }
        }

//        DisabledAccordion("⏳ Sedang Berjalan")
//        DisabledAccordion("🎉 Selesai")
//        DisabledAccordion("😔 Terlewat")

        item {
            ExpandableAccordion(
                title = "⏳ Sedang Berjalan",
                items = todayHabits
            ) { (name, done) ->
                TodayHabitItem(name = name, isCompleted = done)
            }
        }

        item {
            ExpandableAccordion(
                title = "🎉 Selesai",
                items = completedHabits
            ) { (name, total) ->
                CompletedHabitItem(name = name, total = total)
            }
        }

        item {
            ExpandableAccordion(
                title = "😔 Terlewat",
                items = missedHabits
            ) { (name, completed, total) ->
                MissedHabitItem(name = name, completed = completed, total = total)
            }
        }

    }
}