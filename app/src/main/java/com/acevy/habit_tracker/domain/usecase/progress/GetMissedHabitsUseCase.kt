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

class GetMissedHabitsUseCase(
    private val habitRepo: HabitRepository,
    private val trackRepo: TrackRepository
) {
    suspend operator fun invoke(): List<HabitWithProgress> {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val calendar = Calendar.getInstance()

        // Ambil log 30 hari terakhir
        val allLogs = mutableListOf<HabitLogEntity>()
        repeat(30) {
            val dateStr = dateFormat.format(calendar.time)
            allLogs += trackRepo.getLogsByDate(dateStr).first()
            calendar.add(Calendar.DAY_OF_MONTH, -1)
        }

        val allHabits = habitRepo.getAllHabits().first()

        return allHabits.mapNotNull { habit ->
            val logs = allLogs.filter { it.habitId == habit.id }

            // Filter log yang sesuai hari aktif habit
            val validLogs = logs.filter {
                val cal = Calendar.getInstance().apply {
                    time = dateFormat.parse(it.date)!!
                }
                val dayIndex = (cal.get(Calendar.DAY_OF_WEEK) + 5) % 7
                dayIndex in habit.repeatDays
            }

            val total = validLogs.size
            val done = validLogs.count { it.status == HabitStatus.DONE }

            // Bedanya di sini:
            if (total > 0 && done < total) {
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