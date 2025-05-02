package com.acevy.habit_tracker.domain.usecase.notification

import com.acevy.habit_tracker.domain.repository.NotificationRepository

class DeleteNotificationByHabitIdUseCase(
    private val repo: NotificationRepository
) {
    suspend operator fun invoke(habitId: Int) {
        repo.deleteByHabitId(habitId)
    }
}
