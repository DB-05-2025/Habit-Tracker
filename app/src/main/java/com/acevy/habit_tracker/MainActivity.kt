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
import com.acevy.habit_tracker.data.repository.HabitRepositoryImpl
import com.acevy.habit_tracker.di.HabitListViewModelFactory
import com.acevy.habit_tracker.domain.model.Habit
import com.acevy.habit_tracker.domain.usecase.AddHabitUseCase
import com.acevy.habit_tracker.domain.usecase.GetHabitsUseCase
import com.acevy.habit_tracker.ui.viewmodel.HabitListViewModel
import kotlinx.coroutines.flow.first
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.acevy.habit_tracker.data.local.database.AppDatabase
import com.acevy.habit_tracker.data.model.RewardTypeEntity
import com.acevy.habit_tracker.data.model.UserRewardEntity
import com.acevy.habit_tracker.ui.theme.HabitTrackerTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        
        // feat/habit-module
        // --- Init DB, Repository, UseCase ---
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "habit-db"
        )
            .fallbackToDestructiveMigration() // opsional, jika belum pakai migration
            .build()
            
        val habitRepo = HabitRepositoryImpl(db.habitDao())
        val addHabitUseCase = AddHabitUseCase(habitRepo)
        val getHabitsUseCase = GetHabitsUseCase(habitRepo)

        // --- Insert Dummy Habit ---
        val dummyHabit = Habit(
            id = 0,
            userId = 1,
            title = "Coba Habit Insert",
            description = "Test insert dari MainActivity",
            goalType = "daily",
            category = "Kesehatan",
            startDate = "2025-04-22",
            endDate = null,
            reminderTime = "06:30",
            reminderType = "weekly",
            reminderDays = listOf(1, 3, 5),
            createdAt = System.currentTimeMillis(),
            updatedAt = System.currentTimeMillis()
        )

        lifecycleScope.launch {
            try {
                addHabitUseCase(dummyHabit)
                Log.d("HabitInsert", "✅ Habit berhasil ditambahkan")

                val list = getHabitsUseCase(1).first()
                Log.d("HabitCheck", "Total habit user 1: ${list.size}")
            } catch (e: Exception) {
                Log.e("HabitInsert", "❌ Gagal insert habit: ${e.message}")
            }
        }

        // feat/reward-module
        lifecycleScope.launch {
            val rewardTypeDao = db.rewardTypeDao()
            val userRewardDao = db.userRewardDao()

            // Insert RewardType "XP" jika belum ada
            val existingTypes = rewardTypeDao.getAll()
            val xpTypeId = existingTypes.firstOrNull { it.name == "XP" }?.id ?: run {
                rewardTypeDao.insertRewardType(
                    RewardTypeEntity(
                        name = "XP",
                        description = "Experience Points"
                    )
                )
                rewardTypeDao.getAll().first { it.name == "XP" }.id
            }

            // Insert reward ke userId = 1
            userRewardDao.insertUserReward(
                UserRewardEntity(
                    userId = 1L,
                    rewardTypeId = xpTypeId,
                    amount = 50,
                    source = "habit_completed"
                )
            )

            // Fetch & log reward milik userId = 1
            val rewards = userRewardDao.getRewardsByUser(1L)
            rewards.forEach {
                Log.d(
                    "REWARD_TEST",
                    "User earned ${it.amount} from ${it.source}, typeId=${it.rewardTypeId}"
                )
            }
        }

        setContent {
            HabitTrackerTheme {

            }
        }
    }
}