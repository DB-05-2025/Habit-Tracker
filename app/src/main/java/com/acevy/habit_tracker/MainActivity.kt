package com.acevy.habit_tracker

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.acevy.habit_tracker.data.local.database.AppDatabase
import com.acevy.habit_tracker.data.repository.habitstack.HabitStackRepositoryImpl
import com.acevy.habit_tracker.domain.model.habitstack.HabitStack
import com.acevy.habit_tracker.domain.usecase.habitstack.GetHabitStacksByUserUseCase
import com.acevy.habit_tracker.domain.usecase.habitstack.InsertHabitStackUseCase
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

        val habitStackRepo = HabitStackRepositoryImpl(db.habitStackDao())
        val insertHabitStackUseCase = InsertHabitStackUseCase(habitStackRepo)
        val getHabitStacksByUserUseCase = GetHabitStacksByUserUseCase(habitStackRepo)

        // --- Insert Dummy HabitStack ---
        val dummyHabitStack = HabitStack(
            habitStackId = 0,
            userId = 1,
            primaryHabitId = 1,
            stackedHabitId = 2,
            createdAt = System.currentTimeMillis()
        )

        lifecycleScope.launch {
            try {
                insertHabitStackUseCase(dummyHabitStack)
                Log.d("HabitStackInsert", "HabitStack berhasil ditambahkan")

                val stacks = getHabitStacksByUserUseCase(1).first()
                Log.d("HabitStackCheck", "Total stack user 1: ${stacks.size}")
                stacks.forEach { stack ->
                    Log.d(
                        "HabitStackCheck",
                        "Primary: ${stack.primaryHabitId}, Stacked: ${stack.stackedHabitId}"
                    )
                }
            } catch (e: Exception) {
                Log.e("HabitStackInsert", "Gagal insert habitstack: ${e.message}")
            }
        }

        setContent {
            HabitTrackerTheme {

            }
        }
    }
}