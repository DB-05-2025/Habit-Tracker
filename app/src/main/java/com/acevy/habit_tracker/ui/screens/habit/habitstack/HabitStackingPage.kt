package com.acevy.habit_tracker.ui.screens.habit.habitstack

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.acevy.habit_tracker.ui.components.buttons.ButtonPrimary
import com.acevy.habit_tracker.ui.components.cards.HabitStackCard
import com.acevy.habit_tracker.ui.navigation.Screen
import com.acevy.habit_tracker.ui.theme.AppType

@Composable
fun HabitStackingPage(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        Text("Rutinitas Selasa", style = AppType.bold16)

        Spacer(modifier = Modifier.height(16.dp))

        HabitStackCard(
            title = "Rutinitas Selasa",
            habits = listOf("Bekerja", "Mabar"),
            onEditClick = {},
            onDeleteClick = {}
        )

        Spacer(modifier = Modifier.weight(1f))

        ButtonPrimary(
            text = "Tambah Stack",
            onClick = { navController.navigate(Screen.AddStack.route) },
            fullWidth = true
        )
    }
}
