package com.acevy.habit_tracker

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.acevy.habit_tracker.data.local.database.AppDatabase
import com.acevy.habit_tracker.data.repository.habit.HabitRepositoryImpl
import com.acevy.habit_tracker.domain.model.habit.Habit
import com.acevy.habit_tracker.domain.usecase.habit.InsertHabitUseCase
import com.acevy.habit_tracker.domain.usecase.habit.GetHabitsUseCase
import com.acevy.habit_tracker.ui.theme.HabitTrackerTheme
import kotlinx.coroutines.flow.first
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

        val habitRepo = HabitRepositoryImpl(db.habitDao())
        val insertHabitUseCase = InsertHabitUseCase(habitRepo)
        val getHabitsUseCase = GetHabitsUseCase(habitRepo)

        // --- Insert Dummy Habit ---
        val dummyHabit = Habit(
            habitId = 0,
            userId = 1,
            title = "Coba Habit Insert",
            note = "Test insert dari MainActivity",
            repeatDays = listOf(1, 3, 5),
            reminderTime = "06:30",
            createdAt = System.currentTimeMillis(),
            updatedAt = System.currentTimeMillis()
        )

        lifecycleScope.launch {
            try {
                insertHabitUseCase(dummyHabit)
                Log.d("HabitInsert", "Habit berhasil ditambahkan")

                val list = getHabitsUseCase(1).first()
                Log.d("HabitCheck", "Total habit user 1: ${list.size}")
            } catch (e: Exception) {
                Log.e("HabitInsert", "Gagal insert habit: ${e.message}")
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