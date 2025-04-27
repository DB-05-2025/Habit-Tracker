package com.acevy.habit_tracker.ui.screens.progress

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ListAlt
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material.icons.filled.ListAlt
import androidx.compose.material.icons.filled.Stairs
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.acevy.habit_tracker.data.local.datastore.UserPreferences
import com.acevy.habit_tracker.ui.components.accordions.DisabledAccordion
import com.acevy.habit_tracker.ui.components.cards.ProgressGreetingCard
import com.acevy.habit_tracker.ui.components.cards.StatCard
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

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp)
    ) {
        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Kemajuan",
            style = AppType.bold20,
            color = AppColors.GrayDark
        )

        Spacer(modifier = Modifier.height(16.dp))

        ProgressGreetingCard(userName)

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            statList.forEach { (title, subtitle, icon, tint) ->
                StatCard(title, subtitle, icon, tint)
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        DisabledAccordion("⏳ Sedang Berjalan")
        DisabledAccordion("🎉 Selesai")
        DisabledAccordion("😔 Terlewat")
    }
}
