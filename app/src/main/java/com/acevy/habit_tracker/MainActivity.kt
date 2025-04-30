package com.acevy.habit_tracker

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.acevy.habit_tracker.data.local.database.AppDatabase
import com.acevy.habit_tracker.data.repository.reward.UserRewardRepositoryImpl
import com.acevy.habit_tracker.domain.model.reward.UserReward
import com.acevy.habit_tracker.domain.usecase.reward.InsertUserRewardUseCase
import com.acevy.habit_tracker.ui.theme.HabitTrackerTheme
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

        val userRewardDao = db.userRewardDao()
        val userRewardRepo = UserRewardRepositoryImpl(userRewardDao)
        val insertUserRewardUseCase = InsertUserRewardUseCase(userRewardRepo)

        // --- Insert Dummy Reward ---
        val dummyReward = UserReward(
            userRewardId = 0,
            userId = 1,
            amount = 50,
            source = "habit_completed",
            earnedAt = System.currentTimeMillis()
        ) 

        lifecycleScope.launch {
            try {
                insertUserRewardUseCase(dummyReward)
                Log.d("RewardInsert", "Reward berhasil disimpan")
            } catch (e: Exception) {
                Log.e("RewardInsert", "Gagal simpan reward: ${e.message}")
            }
        }

        setContent {
            HabitTrackerTheme {

            }
        }
    }
}