package com.acevy.habit_tracker.domain.usecase.progress

import com.acevy.habit_tracker.domain.model.progress.UserProgress
import com.acevy.habit_tracker.domain.repository.habit.HabitRepository
import com.acevy.habit_tracker.domain.repository.habitlog.HabitLogRepository
import com.acevy.habit_tracker.domain.repository.user.UserRepository
import com.acevy.habit_tracker.domain.repository.userlevel.UserLevelRepository
import kotlinx.coroutines.flow.first
import java.time.LocalDate
import java.util.Calendar

class GetUserProgressUseCase(
    private val userRepo: UserRepository,
    private val userLevelRepo: UserLevelRepository,
    private val habitRepo: HabitRepository,
    private val habitLogRepo: HabitLogRepository
) {
    suspend operator fun invoke(userId: Long): UserProgress? {
        val user = userRepo.getUserById(userId).first() ?: return null
        val level = userLevelRepo.getUserLevelByUserId(userId)
        val habits = habitRepo.getHabitsByUser(userId).first()
        val logs = habitLogRepo.getLogsByHabitId(userId).first()

        val completed = logs.count { it.status == "completed" }
        val skipped = logs.count { it.status == "skipped" }

        val calendar = Calendar.getInstance()
        val todayIndex = (calendar.get(Calendar.DAY_OF_WEEK) - 1) % 7
        val habitsToday = habits.count { it.repeatDays?.contains(todayIndex) == true }

        return UserProgress(
            userName = user.name,
            level = level?.level ?: 1,
            currentXp = level?.currentXp ?: 0,
            totalHabits = habits.size,
            completedHabits = completed,
            skippedHabits = skipped,
            habitTodayCount = habitsToday
        )
    }
}