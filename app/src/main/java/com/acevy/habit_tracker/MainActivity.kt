package com.acevy.habit_tracker

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.acevy.habit_tracker.data.local.database.AppDatabase
import com.acevy.habit_tracker.data.repository.HabitLogRepositoryImpl
import com.acevy.habit_tracker.domain.model.HabitLog
import com.acevy.habit_tracker.domain.usecase.InsertHabitLogUseCase
import com.acevy.habit_tracker.ui.theme.HabitTrackerTheme
import com.acevy.habit_tracker.ui.onboard.OnboardingScreen
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // --- Init DB, Repository, UseCase ---
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "habit-db"
        ).build()

        val habitLogDao = db.habitLogDao()
        val habitLogRepo = HabitLogRepositoryImpl(habitLogDao)
        val insertHabitLogUseCase = InsertHabitLogUseCase(habitLogRepo)

        // --- Insert Dummy Habit ---
        val dummyLog = HabitLog(
            id = 0,
            habitId = 1,
            date = "2025-04-22",
            status = "completed",
            note = "Berhasil dilakukan sesuai jadwal",
            createdAt = System.currentTimeMillis(),
            updatedAt = System.currentTimeMillis()
        )

        lifecycleScope.launch {
            try {
                insertHabitLogUseCase(dummyLog)
                Log.d("HabitLogTest", "HabitLog berhasil ditambahkan")
            } catch (e: Exception) {
                Log.e("HabitLogTest", "Gagal insert habit log: ${e.message}")
            }
        }
        installSplashScreen()

        setContent {
            HabitTrackerTheme {
                HabitTrackerApp()
            }
        }
    }
}