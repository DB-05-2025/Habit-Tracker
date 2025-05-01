package com.acevy.habit_tracker.domain.usecase.progress

import com.acevy.habit_tracker.data.local.entity.HabitStatus
import com.acevy.habit_tracker.domain.model.HabitWithStatus
import com.acevy.habit_tracker.domain.repository.HabitRepository
import com.acevy.habit_tracker.domain.repository.TrackRepository
import kotlinx.coroutines.flow.first
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class GetHabitsWithTodayStatusUseCase(
    private val habitRepo: HabitRepository,
    private val trackRepo: TrackRepository
) {
    suspend operator fun invoke(): List<HabitWithStatus> {
        val calendar = Calendar.getInstance()
        val todayDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(calendar.time)
        val todayIndex = (calendar.get(Calendar.DAY_OF_WEEK) + 5) % 7

        val allHabits = habitRepo.getAllHabits().first()
        val todayHabits = allHabits.filter { todayIndex in it.repeatDays }

        val logsToday = trackRepo.getLogsByDate(todayDate).first()

        return todayHabits.map { habit ->
            val log = logsToday.find { it.habitId == habit.id }

            val status = when (log?.status) {
                HabitStatus.DONE -> HabitStatus.DONE
                else -> HabitStatus.PENDING
            }

            HabitWithStatus(
                logId = log?.id ?: 0,
                habitId = habit.id,
                title = habit.title,
                status = status
            )
        }
    }
}
