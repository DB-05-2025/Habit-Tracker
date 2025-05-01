package com.acevy.habit_tracker.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.acevy.habit_tracker.data.local.entity.NotificationLogEntity
import com.acevy.habit_tracker.domain.usecase.notification.NotificationUseCases
import com.acevy.habit_tracker.ui.model.NotificationItemCardUiState
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.concurrent.TimeUnit
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class NotificationViewModel(
    private val useCases: NotificationUseCases
) : ViewModel() {

    val notificationsUiState: StateFlow<List<NotificationItemCardUiState>> = useCases
        .getAllNotifications()
        .map { notifications ->
            notifications.map { notification ->
                convertToUiState(notification)
            }
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            emptyList()
        )

    private fun convertToUiState(notification: NotificationLogEntity): NotificationItemCardUiState {
        val currentTime = System.currentTimeMillis()
        val timeInfo = if (notification.isActive) {
            formatTimeUntil(notification.scheduledTime - currentTime)
        } else {
            formatTimeAgo(currentTime - notification.timestamp)
        }

        return NotificationItemCardUiState(
            emoji = notification.habitEmoji,
            habitTitle = notification.habitTitle,
            timeInfo = timeInfo,
            isActive = notification.isActive
        )
    }

    private fun formatTimeUntil(millisUntil: Long): String {
        return when {
            millisUntil <= 0 -> "Sekarang"
            millisUntil < TimeUnit.MINUTES.toMillis(1) -> "Dalam beberapa detik"
            millisUntil < TimeUnit.HOURS.toMillis(1) -> {
                val minutes = TimeUnit.MILLISECONDS.toMinutes(millisUntil)
                "Sekitar $minutes menit lagi"
            }
            millisUntil < TimeUnit.DAYS.toMillis(1) -> {
                val hours = TimeUnit.MILLISECONDS.toHours(millisUntil)
                "Sekitar $hours jam lagi"
            }
            else -> {
                val days = TimeUnit.MILLISECONDS.toDays(millisUntil)
                "$days hari lagi"
            }
        }
    }

    private fun formatTimeAgo(millisAgo: Long): String {
        return when {
            millisAgo < TimeUnit.MINUTES.toMillis(1) -> "Baru saja"
            millisAgo < TimeUnit.HOURS.toMillis(1) -> {
                val minutes = TimeUnit.MILLISECONDS.toMinutes(millisAgo)
                "Sekitar $minutes menit yang lalu"
            }
            millisAgo < TimeUnit.DAYS.toMillis(1) -> {
                val hours = TimeUnit.MILLISECONDS.toHours(millisAgo)
                "Sekitar $hours jam yang lalu"
            }
            millisAgo < TimeUnit.DAYS.toMillis(7) -> {
                val days = TimeUnit.MILLISECONDS.toDays(millisAgo)
                "$days hari yang lalu"
            }
            else -> {
                val sdf = SimpleDateFormat("dd MMM yyyy", Locale("id"))
                sdf.format(Date(System.currentTimeMillis() - millisAgo))
            }
        }
    }

    fun insertNotification(notification: NotificationLogEntity) {
        viewModelScope.launch {
            useCases.insertNotification(notification)
        }
    }

    fun clearOldNotifications(before: Long) {
        viewModelScope.launch {
            useCases.clearOldNotifications(before)
        }
    }

    fun generateNotificationsFromHabits(
        habits: List<com.acevy.habit_tracker.data.local.entity.HabitEntity>
    ) {
        val currentTime = System.currentTimeMillis()
        val habitsWithReminders = habits.filter { !it.reminderTime.isNullOrEmpty() }

        viewModelScope.launch {
            // Hapus notifikasi yang sudah ada dan tidak aktif (lebih dari 7 hari)
            clearOldNotifications(currentTime - TimeUnit.DAYS.toMillis(7))

            // Buat notifikasi baru untuk setiap habit yang memiliki pengingat
            for (habit in habitsWithReminders) {
                val reminderParts = habit.reminderTime?.split(":") ?: continue
                if (reminderParts.size != 2) continue

                val hour = reminderParts[0].toIntOrNull() ?: continue
                val minute = reminderParts[1].toIntOrNull() ?: continue

                // Hitung waktu pengingat berikutnya
                val calendar = java.util.Calendar.getInstance()
                calendar.set(java.util.Calendar.HOUR_OF_DAY, hour)
                calendar.set(java.util.Calendar.MINUTE, minute)
                calendar.set(java.util.Calendar.SECOND, 0)
                calendar.set(java.util.Calendar.MILLISECOND, 0)

                // Jika waktu sudah lewat hari ini, tambahkan 1 hari
                if (calendar.timeInMillis < currentTime) {
                    calendar.add(java.util.Calendar.DAY_OF_YEAR, 1)
                }

                // Pastikan hari pengulangan cocok
                while (!habit.repeatDays.contains(calendar.get(java.util.Calendar.DAY_OF_WEEK) - 1)) {
                    calendar.add(java.util.Calendar.DAY_OF_YEAR, 1)
                }

                val scheduledTime = calendar.timeInMillis
                val isActive = scheduledTime > currentTime

                val notification = NotificationLogEntity(
                    habitId = habit.id,
                    habitTitle = habit.title,
                    habitEmoji = getEmojiForHabit(habit.title),
                    message = "Waktunya untuk ${habit.title}!",
                    timestamp = currentTime,
                    isActive = isActive,
                    scheduledTime = scheduledTime
                )

                insertNotification(notification)
            }
        }
    }

    private fun getEmojiForHabit(title: String): String {
        return when {
            title.contains("kerja", ignoreCase = true) -> "💼"
            title.contains("olahraga", ignoreCase = true) -> "💪"
            title.contains("baca", ignoreCase = true) -> "📖"
            title.contains("makan", ignoreCase = true) -> "🍽️"
            title.contains("minum", ignoreCase = true) -> "🥤"
            title.contains("tidur", ignoreCase = true) -> "😴"
            title.contains("belajar", ignoreCase = true) -> "📚"
            title.contains("medita", ignoreCase = true) -> "🧘"
            title.contains("yoga", ignoreCase = true) -> "🧘‍♀️"
            title.contains("jalan", ignoreCase = true) -> "🚶"
            title.contains("lari", ignoreCase = true) -> "🏃"
            title.contains("renang", ignoreCase = true) -> "🏊"
            title.contains("sepeda", ignoreCase = true) -> "🚴"
            title.contains("mandi", ignoreCase = true) -> "🚿"
            title.contains("masak", ignoreCase = true) -> "👨‍🍳"
            title.contains("musik", ignoreCase = true) -> "🎵"
            title.contains("gitar", ignoreCase = true) -> "🎸"
            title.contains("piano", ignoreCase = true) -> "🎹"
            else -> "⏰"
        }
    }
}