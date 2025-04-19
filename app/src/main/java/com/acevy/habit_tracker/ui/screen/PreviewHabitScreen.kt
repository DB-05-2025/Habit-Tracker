package com.acevy.habit_tracker.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.acevy.habit_tracker.domain.model.Habit
import com.acevy.habit_tracker.ui.viewmodel.HabitListViewModel
import java.util.*

@Composable
fun PreviewHabitScreen(viewModel: HabitListViewModel) {
    val habits by viewModel.habitList.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadHabits(userId = 1L)
    }

    Column(modifier = Modifier.padding(16.dp)) {
        Button(
            onClick = {
                viewModel.addHabit(
                    Habit(
                        id = 0L,
                        userId = 1L,
                        title = "Habit Test ${UUID.randomUUID().toString().take(4)}",
                        description = "Coba habit dari MainActivity",
                        goalType = "daily",
                        reminderTime = "07:00",
                        createdAt = System.currentTimeMillis(),
                        updatedAt = System.currentTimeMillis()
                    )
                )
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Tambah Habit")
        }

        Spacer(modifier = Modifier.height(16.dp))

        habits.forEach {
            Text("• ${it.title} (${it.goalType})")
        }
    }
}