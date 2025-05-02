package com.acevy.habit_tracker.ui.viewmodel

import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.acevy.habit_tracker.data.local.entity.HabitEntity
import com.acevy.habit_tracker.data.local.entity.NotificationLogEntity
import com.acevy.habit_tracker.domain.usecase.notification.NotificationUseCases
import com.acevy.habit_tracker.ui.model.NotificationItemCardUiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.concurrent.TimeUnit
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.ZoneId
import java.time.temporal.ChronoUnit
import java.util.Calendar

@RequiresApi(Build.VERSION_CODES.O)
class NotificationViewModel(
    private val useCases: NotificationUseCases
) : ViewModel() {

    private val _ticker = MutableStateFlow(System.currentTimeMillis())

    init {
        viewModelScope.launch {
            while (true) {
                delay(60 * 1000L)
                _ticker.value = System.currentTimeMillis()
            }
        }
    }

    val notificationsUiState: StateFlow<List<NotificationItemCardUiState>> = useCases
        .getAllNotifications()
        .map { notifications ->
            notifications.map { convertToUiState(it) }
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    private fun convertToUiState(notification: NotificationLogEntity): NotificationItemCardUiState {
        val currentTime = System.currentTimeMillis()
        val timeInfo = if (notification.isActive) {
            formatTimeUntil(notification.scheduledTime)
        } else {
            formatTimeAgo(currentTime - notification.scheduledTime)
        }

        return NotificationItemCardUiState(
            emoji = notification.habitEmoji,
            habitTitle = notification.habitTitle,
            timeInfo = timeInfo,
            isActive = notification.isActive
        )
    }

    private fun formatTimeUntil(scheduledTime: Long): String {
        val now = System.currentTimeMillis()
        val diffMillis = scheduledTime - now

        if (diffMillis <= 0) return "Sekarang"

        val minutes = TimeUnit.MILLISECONDS.toMinutes(diffMillis)
        val hours = TimeUnit.MILLISECONDS.toHours(diffMillis)
        val days = TimeUnit.MILLISECONDS.toDays(diffMillis)

        return when {
            days >= 1 -> "$days hari lagi"
            hours >= 1 -> "Sekitar $hours jam lagi"
            minutes >= 1 -> "Sekitar $minutes menit lagi"
            else -> "Dalam beberapa detik"
        }
    }

    private fun formatTimeAgo(millisAgo: Long): String {
        return when {
            millisAgo < TimeUnit.MINUTES.toMillis(1) -> "Baru saja"
            millisAgo < TimeUnit.HOURS.toMillis(1) -> "Sekitar ${millisAgo / 60000} menit yang lalu"
            millisAgo < TimeUnit.DAYS.toMillis(1) -> "Sekitar ${millisAgo / 3600000} jam yang lalu"
            millisAgo < TimeUnit.DAYS.toMillis(7) -> "${millisAgo / 86400000} hari yang lalu"
            else -> SimpleDateFormat("dd MMM yyyy", Locale("id")).format(Date(System.currentTimeMillis() - millisAgo))
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

    fun generateNotificationsFromHabits(habits: List<HabitEntity>) {
        val currentTime = System.currentTimeMillis()

        viewModelScope.launch {
            clearOldNotifications(currentTime - TimeUnit.DAYS.toMillis(1))

            val existingNotifications = useCases.getAllNotifications().first()

            for (habit in habits) {
                if (habit.reminderTime.isNullOrEmpty() || habit.repeatDays.isEmpty()) continue

                val lastNotif = existingNotifications.find { it.habitId == habit.id }
                if (lastNotif?.habitUpdatedAt == habit.updatedAt) continue

                useCases.deleteByHabitId(habit.id)

                val reminderParts = habit.reminderTime.split(":")
                if (reminderParts.size != 2) continue

                val hour = reminderParts[0].toIntOrNull() ?: continue
                val minute = reminderParts[1].toIntOrNull() ?: continue

                val now = Calendar.getInstance()

                var closestScheduledTime: Long? = null
                var isActive = false

                for (i in 0..6) {
                    val cal = now.clone() as Calendar
                    cal.add(Calendar.DAY_OF_YEAR, i)
                    val dayOfWeek = (cal.get(Calendar.DAY_OF_WEEK) + 5) % 7 // Senin = 0

                    if (habit.repeatDays.contains(dayOfWeek)) {
                        cal.set(Calendar.HOUR_OF_DAY, hour)
                        cal.set(Calendar.MINUTE, minute)
                        cal.set(Calendar.SECOND, 0)
                        cal.set(Calendar.MILLISECOND, 0)

                        val timeMillis = cal.timeInMillis

                        if (timeMillis >= currentTime) {
                            closestScheduledTime = timeMillis
                            isActive = true
                            break
                        } else if (i == 0 && closestScheduledTime == null) {
                            closestScheduledTime = timeMillis
                            isActive = false
                        }
                    }
                }

                if (closestScheduledTime == null) continue

                val notification = NotificationLogEntity(
                    habitId = habit.id,
                    habitTitle = habit.title,
                    habitEmoji = getEmojiForHabit(habit.title),
                    message = "Waktunya untuk ${habit.title}!",
                    timestamp = currentTime,
                    isActive = isActive,
                    scheduledTime = closestScheduledTime,
                    habitUpdatedAt = habit.updatedAt
                )

                insertNotification(notification)
            }
        }
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
