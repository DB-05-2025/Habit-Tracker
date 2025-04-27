package com.acevy.habit_tracker

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.acevy.habit_tracker.data.local.database.AppDatabase
import com.acevy.habit_tracker.data.repository.notificationlog.NotificationLogRepositoryImpl
import com.acevy.habit_tracker.domain.model.notificationlog.NotificationLog
import com.acevy.habit_tracker.domain.usecase.notificationlog.InsertNotificationLogUseCase
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

        val notificationLogRepo = NotificationLogRepositoryImpl(db.notificationLogDao())
        val insertNotificationLogUseCase = InsertNotificationLogUseCase(notificationLogRepo)

        // --- Insert Dummy NotificationLog ---
        val dummyNotification = NotificationLog(
            notificationId = 0,
            userId = 1,
            habitId = 1,
            title = "Ingat Minum Air",
            message = "Sudahkah anda minum air hari ini?",
            sentAt = System.currentTimeMillis(),
            type = "user_reminder"
        )

        lifecycleScope.launch {
            try {
                val insertedId = insertNotificationLogUseCase(dummyNotification)
                Log.d("NotificationInsert", "Notification berhasil ditambahkan id=$insertedId")
            } catch (e: Exception) {
                Log.e("NotificationInsert", "Gagal insert notification: ${e.message}")
            }
        }

        setContent {
            HabitTrackerTheme {

            }
        }
    }
}