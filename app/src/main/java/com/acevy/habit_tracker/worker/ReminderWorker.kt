package com.acevy.habit_tracker.worker

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.acevy.habit_tracker.R
import kotlin.random.Random

class ReminderWorker(
    context: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result {
        val habitTitle = inputData.getString("habitTitle") ?: return Result.failure()
        val habitEmoji = inputData.getString("habitEmoji") ?: "⏰"

        Log.d("ReminderWorker", "🚀 Notifikasi dijalankan untuk: $habitTitle $habitEmoji")


        val channelId = "habit_reminder_channel"
        val manager = applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "Habit Reminder",
                NotificationManager.IMPORTANCE_HIGH
            )
            manager.createNotificationChannel(channel)
        }

        val notification = NotificationCompat.Builder(applicationContext, channelId)
            .setContentTitle("Waktunya untuk $habitTitle!")
            .setContentText("Jangan lupa $habitTitle $habitEmoji")
            .setSmallIcon(R.drawable.baseline_notifications_active_24)
            .setAutoCancel(true)
            .build()

        manager.notify(Random.nextInt(), notification)
        return Result.success()
    }
}