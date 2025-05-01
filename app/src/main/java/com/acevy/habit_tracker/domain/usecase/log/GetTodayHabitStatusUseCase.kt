package com.acevy.habit_tracker.domain.usecase.log

import android.util.Log
import com.acevy.habit_tracker.data.local.entity.HabitLogEntity
import com.acevy.habit_tracker.data.local.entity.HabitStatus
import com.acevy.habit_tracker.data.local.room.dao.HabitDao
import com.acevy.habit_tracker.data.local.room.dao.HabitLogDao
import com.acevy.habit_tracker.domain.model.HabitWithStatus
import java.util.Calendar

class GetTodayHabitStatusUseCase(
    private val habitDao: HabitDao,
    private val logDao: HabitLogDao
) {
    suspend operator fun invoke(date: String): List<HabitWithStatus> {
        val logs = logDao.getLogsByDateOnce(date)
        val allHabits = habitDao.getAllHabitsOnce()

        val calendar = Calendar.getInstance()
        val todayIndex = (calendar.get(Calendar.DAY_OF_WEEK) + 5) % 7

        return logs.mapNotNull { log ->
            val habit = allHabits.find { it.id == log.habitId }
            if (habit != null && todayIndex in habit.repeatDays) {
                HabitWithStatus(
                    logId = log.id,
                    habitId = log.habitId,
                    title = habit.title,
                    status = log.status
                )
            } else {
                null
            }
        }
    }
}
