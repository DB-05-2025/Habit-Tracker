package com.acevy.habit_tracker

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.acevy.habit_tracker.data.local.database.AppDatabase
import com.acevy.habit_tracker.data.repository.userlevel.UserLevelRepositoryImpl
import com.acevy.habit_tracker.domain.usecase.userlevel.UpdateUserXpAndLevelUseCase
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

        val userLevelRepo = UserLevelRepositoryImpl(db.userLevelDao())
        val updateUserXpAndLevelUseCase = UpdateUserXpAndLevelUseCase(userLevelRepo)

        // --- Insert Dummy userId dan XP ---
        val dummyUserId = 1L
        val xpEarned = 130 // Misal user dapet XP dari menyelesaikan habit

        lifecycleScope.launch {
            try {
                updateUserXpAndLevelUseCase(dummyUserId, xpEarned)
                Log.d("UserLevelUpdate", "XP user berhasil ditambahkan: $xpEarned XP")

                val updatedUserLevel = userLevelRepo.getUserLevelByUserId(dummyUserId)
                if (updatedUserLevel != null) {
                    Log.d(
                        "UserLevelCheck",
                        "Level: ${updatedUserLevel.level}, XP: ${updatedUserLevel.currentXp}"
                    )
                } else {
                    Log.e("UserLevelCheck", "User level tidak ditemukan")
                }
            } catch (e: Exception) {
                Log.e("UserLevelUpdate", "Gagal update XP/Level: ${e.message}")
            }
        }

        setContent {
            HabitTrackerTheme {

            }
        }
    }
}