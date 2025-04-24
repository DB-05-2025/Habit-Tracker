package com.acevy.habit_tracker

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.acevy.habit_tracker.data.local.database.AppDatabase
import com.acevy.habit_tracker.data.repository.UserRepositoryImpl
import com.acevy.habit_tracker.domain.model.User
import com.acevy.habit_tracker.domain.usecase.GetUserByIdUseCase
import com.acevy.habit_tracker.domain.usecase.InsertUserUseCase
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

        val userDao = db.userDao()
        val userRepo = UserRepositoryImpl(userDao)
        val insertUserUseCase = InsertUserUseCase(userRepo)
        val getUserByIdUseCase = GetUserByIdUseCase(userRepo)

        // --- Insert Dummy User ---
        val newUser = User(
            id = 0,
            firstName = "Darul",
            lastName = "Annas",
            gender = "male",
            birthDate = "1996-09-25",
            createdAt = System.currentTimeMillis()
        )

        lifecycleScope.launch {
            try {
                val insertedId = insertUserUseCase(newUser)
                Log.d("UserInsert", "User berhasil ditambahkan dengan id=$insertedId")
            } catch (e: Exception) {
                Log.e("UserInsert", "Gagal insert user: ${e.message}")
            }
        }

        setContent {
            HabitTrackerTheme {

            }
        }
    }
}