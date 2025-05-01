package com.acevy.habit_tracker.domain.usecase.log

import android.util.Log
import com.acevy.habit_tracker.data.local.entity.HabitLogEntity
import com.acevy.habit_tracker.data.local.entity.HabitStatus
import com.acevy.habit_tracker.data.local.room.dao.HabitDao
import com.acevy.habit_tracker.data.local.room.dao.HabitLogDao
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class GenerateTodayLogsUseCase(
    private val habitDao: HabitDao,
    private val logDao: HabitLogDao
) {
    suspend operator fun invoke() {
        val calendar = java.util.Calendar.getInstance()
        val today = java.text.SimpleDateFormat("yyyy-MM-dd", java.util.Locale.getDefault()).format(calendar.time)
        val todayIndex = (calendar.get(java.util.Calendar.DAY_OF_WEEK) + 5) % 7

        Log.d("TODAY_LOG_GEN", "test masuk")
        logDao.clearLogsNotToday(today)


        val allHabits = habitDao.getAllHabitsOnce()
        Log.d("TODAY_LOG_GEN", "todayIndex: $todayIndex, today: $today")
        allHabits.forEach { habit ->
            Log.d("TODAY_LOG_GEN", "Habit: ${habit.title}, repeatDays: ${habit.repeatDays}")
            if (todayIndex in habit.repeatDays) {
                val log = logDao.getLogByHabitAndDate(habit.id, today)
                Log.d("TODAY_LOG_GEN", "HabitLog ditambahkan: ${habit.title} - $today")
                if (log == null) {
                    logDao.insertLog(
                        HabitLogEntity(
                            habitId = habit.id,
                            date = today,
                            status = HabitStatus.PENDING
                        )
                    )
                }
            }
        }
    }
}
