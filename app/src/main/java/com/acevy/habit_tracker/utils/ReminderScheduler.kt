package com.acevy.habit_tracker.utils

import android.content.Context
import android.util.Log
import androidx.work.*
import com.acevy.habit_tracker.data.local.entity.HabitEntity
import com.acevy.habit_tracker.worker.ReminderWorker
import java.util.*
import java.util.concurrent.TimeUnit

object ReminderScheduler {

    fun scheduleTodayReminders(context: Context, habits: List<HabitEntity>) {
        val calendar = Calendar.getInstance()
        val today = (calendar.get(Calendar.DAY_OF_WEEK) + 5) % 7 // Senin = 0, Minggu = 6

        for (habit in habits) {
            if (habit.reminderTime.isNullOrEmpty() || !habit.repeatDays.contains(today)) continue

            val (hour, minute) = habit.reminderTime.split(":").mapNotNull { it.toIntOrNull() }

            val reminderCal = Calendar.getInstance().apply {
                set(Calendar.HOUR_OF_DAY, hour)
                set(Calendar.MINUTE, minute)
                set(Calendar.SECOND, 0)
                set(Calendar.MILLISECOND, 0)
                add(Calendar.MINUTE, -30) // 🔔 30 menit sebelumnya
            }

            val delay = reminderCal.timeInMillis - System.currentTimeMillis()
            if (delay <= 0) continue // waktu sudah lewat

            val data = workDataOf(
                "habitTitle" to habit.title,
                "habitEmoji" to getEmojiForHabit(habit.title)
            )

            val request = OneTimeWorkRequestBuilder<ReminderWorker>()
                .setInitialDelay(delay, TimeUnit.MILLISECONDS)
                .setInputData(data)
                .addTag("habit-${habit.id}")
                .build()

            Log.d(
                "ReminderScheduler",
                "⏰ Menjadwalkan '${habit.title}' pada ${Date(reminderCal.timeInMillis)}"
            )

            WorkManager.getInstance(context).enqueue(request)
        }
    }

    fun cancelReminderForHabit(context: Context, habitId: Int) {
        WorkManager.getInstance(context).cancelAllWorkByTag("habit-$habitId")
        Log.d("ReminderScheduler", "❌ Reminder dibatalkan untuk habit-$habitId")
    }

    private fun getEmojiForHabit(title: String): String {
        return when {
            title.contains("kerja", true) -> "💼"
            title.contains("olahraga", true) -> "💪"
            title.contains("baca", true) -> "📖"
            title.contains("makan", true) -> "🍽️"
            title.contains("minum", true) -> "🥤"
            title.contains("tidur", true) -> "😴"
            title.contains("belajar", true) -> "📚"
            title.contains("medita", true) -> "🧘"
            title.contains("yoga", true) -> "🧘‍♀️"
            title.contains("jalan", true) -> "🚶"
            title.contains("lari", true) -> "🏃"
            title.contains("renang", true) -> "🏊"
            title.contains("sepeda", true) -> "🚴"
            title.contains("mandi", true) -> "🚿"
            title.contains("masak", true) -> "👨‍🍳"
            title.contains("musik", true) -> "🎵"
            title.contains("gitar", true) -> "🎸"
            title.contains("piano", true) -> "🎹"
            else -> "⏰"
        }
    }
}