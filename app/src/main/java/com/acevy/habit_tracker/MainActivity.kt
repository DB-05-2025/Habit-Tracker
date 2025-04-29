package com.acevy.habit_tracker

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.acevy.habit_tracker.data.local.database.AppDatabase
import com.acevy.habit_tracker.data.repository.habit.HabitRepositoryImpl
import com.acevy.habit_tracker.data.repository.habitlog.HabitLogRepositoryImpl
import com.acevy.habit_tracker.data.repository.user.UserRepositoryImpl
import com.acevy.habit_tracker.data.repository.userlevel.UserLevelRepositoryImpl
import com.acevy.habit_tracker.domain.model.user.User
import com.acevy.habit_tracker.domain.model.userlevel.UserLevel
import com.acevy.habit_tracker.domain.usecase.progress.GetUserProgressUseCase
import com.acevy.habit_tracker.ui.theme.HabitTrackerTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "habit-db"
        ).build()

        val userRepo = UserRepositoryImpl(db.userDao())
        val userLevelRepo = UserLevelRepositoryImpl(db.userLevelDao())
        val habitRepo = HabitRepositoryImpl(db.habitDao())
        val habitLogRepo = HabitLogRepositoryImpl(db.habitLogDao())

        val getUserProgressUseCase = GetUserProgressUseCase(
            userRepo, userLevelRepo, habitRepo, habitLogRepo
        )

        val dummyUser = User(
            userId = 1,
            name = "Darul",
            createdAt = System.currentTimeMillis(),
            updatedAt = System.currentTimeMillis()
        )

        val dummyLevel = UserLevel(
            userLevelId = 1,
            userId = 1,
            level = 3,
            currentXp = 90,
            updatedAt = System.currentTimeMillis()
        )

        lifecycleScope.launch {
            try {
                userRepo.insertUser(dummyUser)
                userLevelRepo.insertUserLevel(dummyLevel)

                val progress = getUserProgressUseCase(1)
                progress?.let {
                    Log.d("Progress", "User: ${it.userName}")
                    Log.d("Progress", "Level: ${it.level} (${it.currentXp} XP)")
                    Log.d("Progress", "Total Habit: ${it.totalHabits}")
                    Log.d("Progress", "Completed: ${it.completedHabits}")
                    Log.d("Progress", "Skipped: ${it.skippedHabits}")
                    Log.d("Progress", "Today’s Habit Count: ${it.habitTodayCount}")
                } ?: Log.e("Progress", "User not found")
            } catch (e: Exception) {
                Log.e("Progress", "Gagal ambil progress: ${e.message}")
            }
        }
        setContent {
            HabitTrackerTheme {

            }
        }
    }
}