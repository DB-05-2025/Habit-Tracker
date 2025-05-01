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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.acevy.habit_tracker.data.local.datastore.UserPreferences
import com.acevy.habit_tracker.data.local.entity.HabitStatus
import com.acevy.habit_tracker.ui.ViewModelFactory
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
import com.acevy.habit_tracker.ui.viewmodel.HabitViewModel
import com.acevy.habit_tracker.ui.viewmodel.ProgressViewModel

@Composable
fun ProgressScreen(
    userPreferences: UserPreferences,
    modifier: Modifier = Modifier,
    progressViewModel: ProgressViewModel = viewModel(factory = ViewModelFactory(LocalContext.current)),
    habitViewModel: HabitViewModel = viewModel(factory = ViewModelFactory(LocalContext.current))
) {
    val userName by userPreferences.userNameFlow.collectAsState(initial = "")
    val todayHabits = progressViewModel.todayHabits.value
    val completedHabits = progressViewModel.completedHabits.value
    val missedHabits = progressViewModel.missedHabits.value
    val progress = progressViewModel.progress.collectAsState().value
    val allHabits = habitViewModel.habitList.collectAsState().value
    val statList = listOf(
        StatData("Level ${progress?.level ?: 0}", "saat ini", Icons.Default.Stairs, AppColors.RedIcon),
        StatData("${allHabits.size} Habit", "telah dibuat", Icons.AutoMirrored.Filled.ListAlt, AppColors.PurpleIcon),
        StatData("${progress?.currentXp ?: 0} XP", "telah dicapai", Icons.Default.Flag, AppColors.BlueIcon)
    )

    val isHabitListEmpty = todayHabits.isEmpty() && completedHabits.isEmpty() && missedHabits.isEmpty()

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

        if (isHabitListEmpty) {
            item { DisabledAccordion("⏳ Sedang Berjalan") }
            item { DisabledAccordion("🎉 Selesai") }
            item { DisabledAccordion("😔 Terlewat") }
        } else {
            item {
                ExpandableAccordion(
                    title = "⏳ Sedang Berjalan",
                    items = todayHabits
                ) { item ->
                    TodayHabitItem(name = item.title, isCompleted = item.status == HabitStatus.DONE)
                }
            }

            item {
                ExpandableAccordion(
                    title = "🎉 Selesai",
                    items = completedHabits
                ) { item ->
                    CompletedHabitItem(name = item.title, total = item.total)
                }
            }

            item {
                ExpandableAccordion(
                    title = "😔 Terlewat",
                    items = missedHabits
                ) { item ->
                    MissedHabitItem(name = item.title, completed = item.done, total = item.total)
                }
            }
        }
    }
}