package com.acevy.habit_tracker.ui.screen


import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.acevy.habit_tracker.domain.model.HabitLog
import com.acevy.habit_tracker.ui.viewmodel.HabitLogViewModel
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun PreviewHabitLogScreen(viewModel: HabitLogViewModel, habitId: Long = 1L) {
    val logs by viewModel.logs.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadLogs(habitId)
    }

    Column(modifier = Modifier.padding(16.dp)) {
        Button(onClick = {
            val today = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
            viewModel.addLog(
                HabitLog(
                    id = 0,
                    habitId = habitId,
                    date = today,
                    status = "completed",
                    note = "Uji log dari preview",
                    createdAt = System.currentTimeMillis(),
                    updatedAt = System.currentTimeMillis()
                )
            )
        }) {
            Text("Tambah Log Hari Ini")
        }

        Spacer(modifier = Modifier.height(16.dp))

        logs.forEach {
            Text("• ${it.date} → ${it.status}")
        }
    }
}