package com.acevy.habit_tracker.domain.usecase.progress

import com.acevy.habit_tracker.data.local.entity.HabitLogEntity
import com.acevy.habit_tracker.data.local.entity.HabitStatus
import com.acevy.habit_tracker.domain.model.HabitWithProgress
import com.acevy.habit_tracker.domain.repository.HabitRepository
import com.acevy.habit_tracker.domain.repository.TrackRepository
import kotlinx.coroutines.flow.first
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class GetCompletedHabitsUseCase(
    private val habitRepo: HabitRepository,
    private val trackRepo: TrackRepository
) {
    suspend operator fun invoke(): List<HabitWithProgress> {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val calendar = Calendar.getInstance()

        // Ambil log 30 hari ke belakang
        val allLogs = mutableListOf<HabitLogEntity>()
        repeat(30) {
            val dateStr = dateFormat.format(calendar.time)
            allLogs += trackRepo.getLogsByDate(dateStr).first()
            calendar.add(Calendar.DAY_OF_MONTH, -1)
        }

        val allHabits = habitRepo.getAllHabits().first()

        return allHabits.mapNotNull { habit ->
            // Filter log sesuai habit
            val logs = allLogs.filter { it.habitId == habit.id }

            // Hanya ambil log dari hari-hari sesuai repeatDays
            val validLogs = logs.filter {
                val cal = Calendar.getInstance().apply {
                    time = dateFormat.parse(it.date)!!
                }
                val dayIndex = (cal.get(Calendar.DAY_OF_WEEK) + 5) % 7
                dayIndex in habit.repeatDays
            }

            val total = validLogs.size
            val done = validLogs.count { it.status == HabitStatus.DONE }

            if (total > 0 && total == done) {
                HabitWithProgress(
                    id = habit.id,
                    title = habit.title,
                    done = done,
                    total = total
                )
            } else null
        }
    }
}