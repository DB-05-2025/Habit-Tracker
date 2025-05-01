package com.acevy.habit_tracker.domain.usecase.notification

import com.acevy.habit_tracker.data.local.entity.NotificationLogEntity
import com.acevy.habit_tracker.domain.repository.NotificationRepository
import kotlinx.coroutines.flow.Flow


class GetAllNotificationsUseCase(
    private val repository: NotificationRepository
) {
    operator fun invoke(): Flow<List<NotificationLogEntity>> {
        return repository.getAllNotifications()
    }
}
