package com.acevy.habit_tracker

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.acevy.habit_tracker.data.local.database.AppDatabase
import com.acevy.habit_tracker.data.repository.UserLevelRepositoryImpl
import com.acevy.habit_tracker.domain.model.UserLevel
import com.acevy.habit_tracker.domain.usecase.GetUserLevelUseCase
import com.acevy.habit_tracker.domain.usecase.UpdateUserLevelByXpUseCase
import com.acevy.habit_tracker.domain.usecase.UpdateUserLevelUseCase
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

        val userLevelDao = db.userLevelDao()
        val userLevelRepo = UserLevelRepositoryImpl(userLevelDao)

        val getUserLevelUseCase = GetUserLevelUseCase(userLevelRepo)
        val updateUserLevelUseCase = UpdateUserLevelUseCase(userLevelRepo)
        val updateUserLevelByXpUseCase = UpdateUserLevelByXpUseCase(
            getUserLevelUseCase, updateUserLevelUseCase
        )

        // --- Insert Dummy User Level ---
        val levelData = UserLevel(
            id = 0,
            userId = 1,
            level = 2,
            currentXp = 150,
            updatedAt = System.currentTimeMillis()
        )
        val dummyXp = 250
        val userId = 1L

        lifecycleScope.launch {
            try {
                updateUserLevelUseCase(levelData)
                Log.d("UserLevelTest", "User level berhasil disimpan")

                updateUserLevelByXpUseCase(userId, dummyXp)
                Log.d("XPTest", "XP $dummyXp berhasil diproses")

                // Tampilkan hasil level akhir
                getUserLevelUseCase(userId).collect { level ->
                    level?.let {
                        Log.d("XPTest", "Level: ${it.level}, XP: ${it.currentXp}")
                    }
                }
            } catch (e: Exception) {
                Log.e("UserLevelTest", "Gagal simpan user level: ${e.message}")

                Log.e("XPTest", "Gagal update XP: ${e.message}")
            }
        }

        setContent {
            HabitTrackerTheme {

            }
        }
    }
}