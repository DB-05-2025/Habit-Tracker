package com.acevy.habit_tracker

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.acevy.habit_tracker.data.local.database.AppDatabase
import com.acevy.habit_tracker.data.repository.habitlog.HabitLogRepositoryImpl
import com.acevy.habit_tracker.domain.model.habitlog.HabitLog
import com.acevy.habit_tracker.domain.usecase.habitlog.GetLogsByHabitUseCase
import com.acevy.habit_tracker.domain.usecase.habitlog.InsertHabitLogUseCase
import com.acevy.habit_tracker.ui.theme.HabitTrackerTheme
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // --- Init DB, Repository, UseCase ---
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "habit-db"
        ).build()

        val habitLogRepo = HabitLogRepositoryImpl(db.habitLogDao())
        val insertHabitLogUseCase = InsertHabitLogUseCase(habitLogRepo)
        val getLogsByHabitUseCase = GetLogsByHabitUseCase(habitLogRepo)

        // --- Insert Dummy Habit ---
        val dummyHabitLog = HabitLog(
            habitLogId = 0,
            habitId = 1,
            date = "2025-04-23",
            status = "completed",
            note = "Selesai baca 10 halaman",
            createdAt = System.currentTimeMillis(),
            updatedAt = System.currentTimeMillis()
        )

        lifecycleScope.launch {
            try {
                insertHabitLogUseCase(dummyHabitLog)
                Log.d("HabitLogInsert", "HabitLog berhasil ditambahkan")

                val logs = getLogsByHabitUseCase(1).first()
                Log.d("HabitLogCheck", "Total log untuk habit 1: ${logs.size}")
                logs.forEach { log ->
                    Log.d("HabitLogCheck", "Log: ${log.date}, Status: ${log.status}")
                }
            } catch (e: Exception) {
                Log.e("HabitLogInsert", "Gagal insert habitlog: ${e.message}")
            }
        }

        setContent {
            HabitTrackerTheme {

            }
        }
    }
}
